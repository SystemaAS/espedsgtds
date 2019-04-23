var jq = jQuery.noConflict();
var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Vennligst vent...";

var svlthTable;
var uttagTable;
var svtx03fTable;

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
		dom : '<"top"f>t<"bottom"lip>B<"clear">',
		ajax: {
	        "url": svlthUrl + doNotLoad,
	        "dataSrc": "dtoList"
	    },	
	    //https://datatables.net/reference/button/
	    //https://datatables.net/extensions/buttons/
	    //https://datatables.net/extensions/buttons/examples/html5/pdfPage.html
	    buttons: [
            {
                extend: 'pdfHtml5',
                orientation: 'landscape',
                pageSize: 'LEGAL'
            },
            {
                extend: 'print',
                orientation: 'landscape',
                pageSize: 'LEGAL'
            }
        ],
		mark: true,
	    responsive: true,
		columnDefs : [ 
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
	    columns: [
	        { "data": "svlth_igl" },
	    	{
	            "orderable":      false,
	            "data":           null,
	            "defaultContent": ''
	    	},
	        { "data": "saldo" },
	        { "data": "svlth_int" },
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
	        { "data": "svlth_isl" },
	    	{ "data": "svlth_ibr" },
	    	{ "data": "svlth_ivb" },
	        { "data": "svlth_itx" }
	    ],
		lengthMenu : [ 25, 75, 100 ],
		language : {
			url : getLanguage(lang)
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

//	clearValues();

}

function loadSvtx03f() {
	let runningUrl;
	runningUrl = getRunningSvtx03fUrl();
	console.log("svtx03f runningUrl=" + runningUrl);

	setBlockUI();
	
	svtx03fTable.ajax.url(runningUrl);
	svtx03fTable.ajax.reload();
	//	unBlockUI(); is done in draw.dt

}

function getRunningSvtx03fUrl() {
	let runningUrl = kollislagkoderUrl;

	var selectedId = jq('#selectId').val();
	var selectedBeskrivning = jq('#selectBeskrivning').val();

	if (selectedId != "") {
		runningUrl = runningUrl + "&svtx03_03=" + selectedId;
	} 
	if (selectedBeskrivning != "") {
		runningUrl = runningUrl + "&svtx03_04=" + selectedBeskrivning;
	} 		
	
	console.log("svtx03f runningUrl", runningUrl);
	
	return runningUrl;
}

	
function clearValues() {
    jq("#svlth_uex").val("");
    jq("#svlth_uti").val("");
    jq("#svlth_unt").val("");
    jq("#svlth_utx").val("");
    
 //   jq("#action").val(2);  //READ 	

}


function setUttagHeader() {
	jq.ajax({
		  url: svlthUrl,
	  	  data: { svlth_h : "I" , svlth_irn : svlth_irn }, 
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
			  jq("#saldo").text(record[0].saldo);
			  
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


function initSvtx03fSearch(caller) {
	console.log("initSvtx03fSearch, caller", caller);

	svtx03fTable = jq('#svtx03fTable').DataTable({
		"dom" : '<"top"f>t<"bottom"lip><"clear">',
		"ajax": {
	        "url": kollislagkoderUrl + doNotLoad,
	        "dataSrc": "dtoList"
	    },	
		mark: true,			
		responsive : true,
		select : true,
		destroy : true,
		"scrollY" : "300px",
		"scrollCollapse" : false,
		"order" : [ [ 1, "desc" ] ],
		"columnDefs" : [ 
			{
				"targets" : 1,
				className: 'dt-body-center',
			    "render": function ( data, type, row, meta ) {
	           		return '<a>' +
	       			'<img class="img-fluid float-center" title="Välg" src="resources/images/bebullet.gif">' +
	       			'</a>'    	
			    }
			}
		],			
		"columns" : [ 
			{"data" : "svtx03_03"}, 
	    	{
	        	"class":          "choose dt-body-center",
	        	"orderable":      false,
	            "data":           null,
	            "defaultContent": ''
	    	},		
			{"data" : "svtx03_04"}
		 ],
		"lengthMenu" : [ 10, 25, 75],
		"language" : {
			"url" : getLanguage(lang)
		},
		
	    initComplete: function () {
	    	//levefInitialized = true;
	    }

	});

	svtx03fTable.on( 'click', 'td.choose img', function () {	
	    let row = svtx03fTable.row( jq(this).parents('tr') ).data();	

		opener.jq(caller).val(row.svtx03_03);
		opener.jq(caller).change();
		opener.jq(caller).focus();
		
		window.close();
		
	});	
	
	
	svtx03fTable.on( 'draw.dt', function () {
	    unBlockUI();
	});		
	
}//end initSvtx03fSearch




//deprecated, remove when appropriate
function getKollislagKode(caller){
	console.log('getKollislagKode, caller',caller);
	jq.ajax({
			  type: 'GET',
			  url: kollislagUrl,
			  dataType: 'json',
			  cache: true,
			  contentType: 'application/json',
			  success: function(data) {
				_.each(data.dtoList, function( d) {
					jq(caller).append(jq('<option></option>').attr('value', d.svtx03_03).text(d.svtx03_04).attr('title', d.svtx03_03));	
				});
				
			  }, 
			  error: function (jqXHR, exception) {
				    alert('Error loading kollislag...look in console log.');
				    console.log("jqXHR",jqXHR);
				    console.log("exception",exception);
			  }	
	});	
}




jq(function() {
	
	jq("#formRecord").submit(function() {
		setBlockUI();	
	});

	jq("#svlth_id2").datepicker({ 
		dateFormat: 'yymmdd'
	});		
	
	jq("#selectArrival").datepicker({ 
		dateFormat: 'yymmdd'
	});	
	
	jq("#selectRegdate").datepicker({ 
		dateFormat: 'yymmdd'
	});	
	
	jq('a#kollislag_Link').click(function() {
		jq('#kollislag_Link').attr('target','_blank');
    	window.open('childwindow_codes.do?caller=svlth_isl', "codeWin", "top=300px,left=500px,height=500px,width=800px,scrollbars=no,status=no,location=no");
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