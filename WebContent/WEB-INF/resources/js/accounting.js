var jq = jQuery.noConflict();
var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Vennligst vent...";

var svlthTable;
var uttagTable;
var rattelseTable;
var svtx03fTable;

var inlaggUrl_read = "accounting_inlagg.do?action=2";
var rattelseUrl_create = "accounting_rattelse.do?action=1";
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
                pageSize: 'LEGAL',
                exportOptions: {
                    columns: [ 1,3,4,5,6,7,8 ]
                }
            },
            {
                extend: 'print',
                orientation: 'landscape',
                pageSize: 'LEGAL',
                exportOptions: {
                    columns: [ 1,3,4,5,6,7,8 ]
                }
            }
        ],
		mark: true,
	    responsive: true,
		columnDefs : [ 
            { responsivePriority: 1, targets: 0 },
            { responsivePriority: 2, targets: -1 }
		],	    
	    columns: [
	        {
	            orderable:      false,
	            data:           null,
	            render: function ( data, type, row, meta ) {
			    	let href;
			    	if (row.svlth_h == 'I') {
				    	let url= inlaggUrl_read+'&svlth_irn='+row.svlth_irn; 
				    	href = '<a href="'+url+'"' +'><img class= "img-fluid float-center" src="resources/images/update.gif" onClick="setBlockUI();"></a>';
			    	} 
			    	return href;
			    },
	            defaultContent: '-'
	    	},
	        { data: "svlth_ign"	},
	    	{ data: "svlth_h",
	        	render: function ( data, type, row, meta ) {
	        		return getDescription(row.svlth_h);
	        		
	        		
//	        		if (row.svlth_h == 'I') {
//	        			return 'Inlägg';
//	        		}
//	        		if (row.svlth_h == 'U') {
//	        			return 'Uttag';
//	        		}
//	        		if (row.svlth_h == 'R') {
//	        			return 'Rättelse';
//	        		}
//	        		return row.svlth_h;
	        	},
	    	},
	    	{ data: "saldo" },
	        { data: "svlth_int" },
	        { data: "svlth_iex" },
	        { data: "svlth_irn" },
	        { data: "svlth_id2" },
	        { data: "svlth_ud1" },
	        { data: "svlth_igl" },
	        { data: "svlth_isl" },
	    	{ data: "svlth_ibr" },
	    	{ data: "svlth_ivb" },
	        { data: "svlth_ih1" },
	    	{ data: "svlth_ih2" },	        
	        { data: "svlth_ih3" },
	        { data: "svlth_ih4" },
	        { data: "svlth_ih5" },
	    	{ data: "svlth_itx" },
	        { data: "svlth_id1" },
	        {
	            orderable:      false,
	            data:           null,
	            className: "dt-body-center",
	            render: function ( data, type, row, meta ) {
			    	let href;
			    	if (row.svlth_h != 'R') { 
				    	let url= rattelseUrl_create + '&svlth_h='+row.svlth_h + '&svlth_irn='+row.svlth_irn  + '&svlth_id1='+row.svlth_id1  + '&svlth_ud1='+row.svlth_ud1  + '&svlth_um1='+row.svlth_um1
				    	href = '<a title="Skapa" href="'+url+'"' +'><img class="img-fluid float-center" src="resources/images/log-icon.gif"  width="16" height="16" onClick="setBlockUI();"></a>';
			    	} 
			    	return href;
			    },
	            defaultContent: '-'
	        }, 
	    ],
	    order: [[5, 'asc']],
	    lengthMenu : [ 25, 75, 100 ],
		language : {
			url : getLanguage(lang)
		}        
	
	});

    jq('#svlthTable').on( 'draw.dt', function () {
        unBlockUI();
    });	
    
    jq('#svlthTable tbody').on( 'click', 'button', function () {
        let data = svlthTable.row( jq(this).parents('tr') ).data();
       // console.log("data[svlth_irn]",data[svlth_irn]);
        console.log("data[svlth_irn]",data['svlth_irn']);
        alert( "'rättelse av: "+ data['svlth_irn'] );
    } );   
    
    
    
	
} //end initSvlthSearch


function loadSvlth() {
	let runningUrl;
	runningUrl = getRunningSvlthUrl();
	console.log("runningUrl" + runningUrl);
	if (runningUrl == null) {
		alert('Du måste fylla i något fält för sökning');
		return null;
	}
	
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
	console.log("runningUrl" + runningUrl);
	
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
			"url" : getLanguage(lang)
		}

	});	

//	clearValues();

}


function loadEvent() {
	console.log('loadEvent');

	console.log('svlth_irn',svlth_irn);
	console.log('svlth_h',svlth_h);
	console.log('loadEvent');

	let runningUrl;
	console.log('svlth_irn',svlth_irn);
	runningUrl= getRunningSvlthRattelseUrl();
	console.log("runningUrl" + runningUrl);
	
	console.log('uttagTable', uttagTable);
	if (rattelseTable != undefined) {
		console.log('rattelseTable already set.');
		
		rattelseTable.ajax.url(runningUrl);
		rattelseTable.ajax.reload();		
		
		return;
	}	
	
	setEventHeader();

	rattelseTable = jq('#rattelseTable').DataTable({
		"dom" : '<"top">t<"bottom"flip><"clear">',
	    "ajax": {
	        "url": runningUrl,
	        "dataSrc": "dtoList"
	    },	
	    mark: true,
		responsive : true,
		"order" : [ [ 1, "desc" ] ],		
		"columns" : [ 
	    	{"data" : "svlth_rnt"},
			{"data" : "svlth_rtx"}
			],
		"lengthMenu" : [ 2, 5, 20 ],
		"language" : {
			"url" : getLanguage(lang)
		}

	});	


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


function getDescription(svlth_h){
	if (svlth_h == 'I') {
		return 'Inlägg';
	}
	if (svlth_h == 'U') {
		return 'Uttag';
	}
	if (svlth_h == 'R') {
		return 'Rättelse';
	}
	return svlth_h;	
	
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

function setEventHeader() {
	
	console.log("svlth_um1", svlth_um1);
	
	jq.ajax({
		  url: svlthUrl,
	  	  data: { svlth_h : svlth_h , svlth_irn : svlth_irn, svlth_id1 : svlth_id1, svlth_ud1 : svlth_ud1, svlth_um1 : svlth_um1 }, 
		  dataType: 'json',
		  cache: false,
		  contentType: 'application/json',
		  success: function(data) {
			  let record = data.dtoList;
			  if (record[0].svlth_h == "I") {
				  jq("#antal").text(record[0].svlth_int);  
			  } 
			  if (record[0].svlth_h == "U") {
				  jq("#antal").text(record[0].svlth_unt);  
			  } 
			  
			  jq("#event").text(getDescription(record[0].svlth_h));
			  jq("#mrn").text(record[0].svlth_irn);
			  jq("#godsnr").text(record[0].svlth_ign);
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

function getRunningSvlthRattelseUrl() {
	let runningUrl = svlthUrl;
	runningUrl = runningUrl + "&svlth_irn=" + svlth_irn;
	runningUrl = runningUrl + "&svlth_h=R";
	
	return runningUrl;
}


function getRunningSvlthUrl() {
		let runningUrl = svlthUrl;
	
		let selectedGodsnr = jq('#selectGodsnr').val();
		let selectedMrn = jq('#selectMrn').val();
		let selectedArrival = jq('#selectArrival').val();
		
		if (selectedGodsnr != "") {
			runningUrl = runningUrl + "&svlth_ign=" + selectedGodsnr;
		} 
		if (selectedMrn != "") {
			runningUrl = runningUrl + "&svlth_irn=" + selectedMrn;
		} 
		if (selectedArrival != "") {
			runningUrl = runningUrl + "&svlth_id2=" + selectedArrival;
		} 
	
		if (selectedGodsnr == "" && selectedMrn == "" && selectedArrival == "") {
			return null;
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