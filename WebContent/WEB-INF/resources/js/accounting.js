var jq = jQuery.noConflict();
var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Vennligst vent...";

var svlthTable;
var uttagTable;

var inlaggUrl_read = "accounting_inlagg.do?action=2";
var doNotLoad = "&DO_NOT_LOAD";  //disabling datatables autoload of content

function initSvlthSearch() {
	console.log('svlthTable', svlthTable);
	if (svlthTable != undefined) {
		console.log('svlthTable already set.');
		return;
	}
	console.log('initSvlthSearch');

	//Init datatables, done once, then reload with ajax
	svlthTable = jq('#svlthTable').DataTable({
		"dom" : '<"top"f>t<"bottom"lip><"clear">',
		"ajax": {
	        "url": svlthUrl + doNotLoad,
	        "dataSrc": "dtoList"
	    },	
		mark: true,
	    responsive: true,
		"columnDefs" : [ 
			{
				"targets" : 1,
				className: 'dt-body-center',
			    "render": function ( data, type, row, meta ) {
			    	var url= inlaggUrl_read+'&svlth_irn='+row.svlth_irn; 
			    	var href = '<a href="'+url+'"' +'><img class= "img-fluid float-center" src="resources/images/update.gif" onClick="setBlockUI();"></a>';
			    	return href;
			    }			
			}
		],	    
	    "columns": [
	        { "data": "svlth_igl" },
	    	{
	            "orderable":      false,
	            "data":           null,
	            "defaultContent": ''
	    	},
	        { "data": "svlth_iex" },
	        { "data": "svlth_irn" },
	        { "data": "svlth_ign" },
	        { "data": "svlth_id1" },
	        { "data": "svlth_id2" },
	        { "data": "svlth_ih1" },
	    	{ "data": "svlth_ih2" },	        
	        { "data": "svlth_ih3" },
	        { "data": "svlth_ih4" },
	        { "data": "svlth_ih5" },
	        { "data": "svlth_int" },
	        { "data": "svlth_isl" },
	    	{ "data": "svlth_ibr" },
	    	{ "data": "svlth_ivb" },
	        { "data": "svlth_itx" }
	    ],
		"lengthMenu" : [ 25, 75, 100 ],
		"language" : {
			"url" : getLanguage(lang)
		}        
	
	});

    jq('#svlthTable').on( 'draw.dt', function () {
        unBlockUI();
    });	
	
} //end initSvlthSearch


function loadSvlth() {
	let runningUrl;
	runningUrl = getRunningSvlthUrl();
	console.log("runningUrl=" + runningUrl);

	setBlockUI();
	
	svlthTable.ajax.url(runningUrl);
	svlthTable.ajax.reload();
//	unBlockUI(); is done in draw.dt

}

function loadSvlthUttag() {
	console.log('loadSvlthUttag');
	//clearKostbLineValues();

	let runningUrl;
	console.log('svlth_irn',svlth_irn);
	runningUrl= getRunningSvlthUttagUrl();
	console.log("runningUrl=" + runningUrl);
		
	console.log('uttagTable', uttagTable);
	if (uttagTable != undefined) {
		console.log('uttagTable already set.');
		
		uttagTable.ajax.url(runningUrl);
		uttagTable.ajax.reload();		
		
		return;
	}	
	
	setUttagHeader();
	
	uttagTable = jq('#uttagTable').DataTable({
		"dom" : '<"top">t<"bottom"flip><"clear">',
	    "ajax": {
	        "url": runningUrl,
	        "dataSrc": "dtoList"
	    },	
	    mark: true,
		responsive : true,
		"order" : [ [ 1, "desc" ] ],		
		"columns" : [ 
	    	{"data" : "svlth_uex"},
			{"data" : "svlth_ud1"},
			{"data" : "svlth_uti"}, 
			{"data" : "svlth_unt"}, 
			{"data" : "svlth_utx"}
			],
		"lengthMenu" : [ 10, 25, 100 ],
		"language" : {
			"url" : getLanguage('NO')
		}

	});	
	
	
	
	
	
	
	
	
	
	
	
	

}


function setUttagHeader() {
	jq.ajax({
		  url: svlthUrl,
	  	  data: { svlth_irn : svlth_irn }, 
		  dataType: 'json',
		  cache: false,
		  contentType: 'application/json',
		  success: function(data) {
			  let record = data.dtoList;
			  console.log("record", record);
			  jq("#mrn").text(record[0].svlth_irn);
			  jq("#godsnr").text(record[0].svlth_ign);
			  jq("#arrival").text(record[0].svlth_id2);
			  jq("#archive").text(record[0].svlth_id1);

			  jq("#saldo").text(23);

			  
		  }, 
		  error: function (jqXHR, exception) {
		  	console.log("svlth_irn don't exist", svlth_irn);
		    console.log("jqXHR",jqXHR);
		    console.log("exception",exception);
		  }	
	});	
	
}




function getRunningSvlthUttagUrl() {
	let runningUrl = svlthUrl;
	runningUrl = runningUrl + "&svlth_irn=" + svlth_irn;
	runningUrl = runningUrl + "&svlth_h=U";
	
	return runningUrl;
}


//Inlagg only
function getRunningSvlthUrl() {
		let runningUrl = svlthUrl;
	
		let selectedMrn = jq('#selectMrn').val();
		let selectedArrival = jq('#selectArrival').val();
		let selectedRegdate = jq('#selectRegdate').val();
		
		if (selectedMrn != "") {
			runningUrl = runningUrl + "&svlth_irn=" + selectedMrn;
		} 
		runningUrl = runningUrl + "&svlth_h=I";

		if (selectedArrival != "") {
			runningUrl = runningUrl + "&svlth_id2=" + selectedArrival;
		} 
		if (selectedRegdate != "") {
			runningUrl = runningUrl + "&svlth_id1=" + selectedRegdate;
		} 
		
		
		return runningUrl;	
		
}







jq(function() {

	jq("#selectArrival").datepicker({ 
		dateFormat: 'yymmdd'
	});	
	
	jq("#selectRegdate").datepicker({ 
		dateFormat: 'yymmdd'
	});	
	
	/*the asterix*/
	jq("input[required]").parent("label").addClass("required");
	
});  


jq(document).keypress(function(e) {
    if(e.which == 13) {
        jq("#submitBtn").click();
    }
});

window.addEventListener('error', function(e) {
	var error = e.error;
	jq.unblockUI();
	console.log("Event e", e);

	alert('Uforutsett fel har intreffet. \n Error: '+error);

});