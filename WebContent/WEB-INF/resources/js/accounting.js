var jq = jQuery.noConflict();
var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Vennligst vent...";

var svlthTable;
var uttagTable;
var rattelseTable;
var svtx03fTable;

var uttagUrl_read = "accounting_uttag_list.do?action=2";
var inlaggUrl_read = "accounting_inlagg.do?action=2";
var rattelseUrl_create = "accounting_rattelse.do?action=1";
var doNotLoad = "&DO_NOT_LOAD";  //disabling datatables autoload of content

var selectionMessage ='';
var godsLokalkoder;
var now = new Date();


var selectedGodslokalkod = '';
var selectedGodsnr = '';
var selectedMrn = '';
var selectedArrivalFrom = '';
var selectedArrivalTo ='';


function initSvlthSearch() {
	console.log('::initSvlthSearch::');
	
	console.log("selectedArrivalFrom",selectedArrivalFrom);
	
	
	if (svlthTable != undefined) {
		console.log('svlthTable already set.');
		return;
	}
	console.log('initSvlthSearch');

	//Init datatables, done once, then reload with ajax
	svlthTable = jq('#svlthTable').DataTable({
		dom : '<"top"Bf>t<"bottom"lip><"clear">',
		ajax: {
	        "url": svlthUrl + doNotLoad,
	        "dataSrc": "dtoList"
	    },	
	    buttons: [
            {
                extend: 'colvis',
                text: 'Välj kolumner'
            },
            {
                extend: 'print', //pdfHtml5
                text: 'Skriv ut',
                orientation: 'landscape',  //portrait
                pageSize: 'LEGAL', //A3, A4 , A5 , A6 , legal , letter
                exportOptions: {
                    columns: ':visible',
                    search: 'applied',
                    order: 'applied'
                },
                title: "Tillfällig lagring",
                messageTop: function () {
                       return selectionMessage;
                },
                messageBottom: now
            },
	    	{
                extend: 'pdfHtml5',
                text: 'Skapa PDF',
                orientation: 'landscape',  //portrait
                pageSize: 'LEGAL', //A3, A4 , A5 , A6 , legal , letter
                exportOptions: {
                    columns: ':visible',
                    search: 'applied',
                    order: 'applied'
                },
                title: "Tillfällig lagring",
                messageTop: function () {
                       return selectionMessage;
                },
                messageBottom: now
            }
        ],
		mark: true,
	    responsive: true,
		columnDefs : [ 
            { responsivePriority: 1, targets: -2 },
            { responsivePriority: 2, targets: -1 }
		],	    
	    columns: [
	        { data: "svlth_igl" },
	    	{ data: "svlth_ign"	},
	    	{ data: "svlth_pos"	},
	        { data: null,
	        	render: function ( data, type, row, meta ) {
	        		return getDescription(row.svlth_h);
	        	}
	    	},
	    	{ data: "svlth_irn" },
	        { data: "svlth_iex" },
	        { data: "svlth_uex" },
	        { data: "svlth_uti" },
	    	{ data: null,
	        	render: function ( data, type, row, meta ) {
	        		if (row.svlth_h == 'I') {
		        		return row.svlth_int;	        			
	        		}
	        		if (row.svlth_h == 'U') {
		        		return row.svlth_unt;	        			
	        		}
	        		if (row.svlth_h == 'R') {
		        		return row.svlth_rnt;	        			
	        		}	        		
	        	}	
	        },
	    	{ data: "saldo" },
	        { data: "svlth_isl" },
	        { data: "svlth_ivb" },
	    	{ data: "svlth_id2" },   	
	        { data: "svlth_ud1",
	        	render: function ( data, type, row, meta ) {
	        		if (row.svlth_ud1 == '0') {
	        			return null;
	        		}
	        		return row.svlth_ud1;
	        	}	        	
	        },
	    	{ data: "svlth_ibr" },
	    	{ data: null,
	        	render: function ( data, type, row, meta ) {
	        		return getDescription(row.svlth_rty);
	        	}
	    	},
	    	{ data: "svlth_rnt" },
	        { data: "svlth_ivb2" },
	    	{ data: "svlth_ivb3" },
	    	{ data: "svlth_ivb4" },
	    	{ data: "svlth_ivb5" },	    	
	    	{ data: "svlth_rtx" },
	        { data: "svlth_ih1" },
	    	{ data: "svlth_ih2" },	        
	        { data: "svlth_ih3" },
	        { data: "svlth_ih4" },
	        { data: "svlth_ih5" },
	    	{ data: "svlth_itx" },
	    	{ data: null,
	        	render: function ( data, type, row, meta ) {
	        		return row.timestamp;
	        	} 	
	        },
	    	{ data: "svlth_ius" },
	        {
	            orderable:      false,
	            data:           null,
	            className: "dt-body-center",
	            render: function ( data, type, row, meta ) {
			    	let href;
			    	if (row.svlth_h == 'I' && row.saldo) {
				    	let url= uttagUrl_read + '&svlth_ign='+row.svlth_ign + '&svlth_pos='+row.svlth_pos+'&svlth_id1='+row.svlth_id1 + '&svlth_im1='+row.svlth_im1; 
				    	href = '<a href="'+url+'"' +'><img class= "img-fluid float-center" src="resources/images/unloading.png" onClick="setBlockUI();"></a>';
			    	} 
			    	return href;
			    },
	            defaultContent: ''
	    	},
	        {
	            orderable:      false,
	            data:           null,
	            className: "dt-body-center",
	            render: function ( data, type, row, meta ) {
			    	let href;
			    	if (row.svlth_h != 'R') { 
				    	let url= rattelseUrl_create+'&h_svlth_h='+row.svlth_h+'&h_svlth_ign='+row.svlth_ign+'&h_svlth_pos='+row.svlth_pos+'&h_svlth_id1='+row.svlth_id1+'&h_svlth_im1='+row.svlth_im1;
				    	href = '<a href="'+url+'"' +'><img class="img-fluid float-center" src="resources/images/log-icon.gif"  width="16" height="16" onClick="setBlockUI();"></a>';
			    	} 
			    	return href;
			    },
	            defaultContent: ''
	        }, 
	    ],
	    order: [[28, 'desc']],   //Arkiverad
	    lengthMenu : [ 25, 75, 100, 200, 500 ],
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
	console.log('svlth_ign',svlth_ign);
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
		"order" : [ [ 2, "desc" ] ],		
		"columns" : [ 
	    	{"data" : "svlth_uex"},
			{"data" : "svlth_ud1"},
			{"data" : "svlth_uti"}, 
			{"data" : "svlth_unt"}, 
			{"data" : "svlth_utx"},
	        { data: null,
	        	render: function ( data, type, row, meta ) {
	        		return row.timestamp;
	        	} 	
	        }			
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

	let runningUrl;
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
			{"data" : "svlth_rtx"},
	        { data: null,
	        	render: function ( data, type, row, meta ) {
	        		return row.timestamp;
	        	} 	
	        }
			],
		"lengthMenu" : [ 5, 10, 20 ],
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
	  	  data: { svlth_h : "I" , svlth_ign : h_svlth_ign,  svlth_pos : h_svlth_pos, svlth_id1 : h_svlth_id1, svlth_im1 : h_svlth_im1 }, 
		  dataType: 'json',
		  cache: false,
		  contentType: 'application/json',
		  success: function(data) {
			  let record = data.dtoList;
			  console.log("record", record);
			  jq("#mrn").text(record[0].svlth_irn);
			  jq("#godsnr").text(record[0].svlth_ign);
			  jq("#position").text(record[0].svlth_pos);
			  jq("#arrival").text(record[0].svlth_id2);
			  jq("#archive").text(record[0].svlth_id1);
			  jq("#saldo").text(record[0].saldo);
			  
		  }, 
		  error: function (jqXHR, exception) {
		    console.log("jqXHR",jqXHR);
		    console.log("exception",exception);
		  }	
	});	
	
}

function setEventHeader() {
	jq.ajax({
		  url: svlthUrl,
	  	  data: { svlth_h : h_svlth_h , svlth_ign : h_svlth_ign, svlth_pos : h_svlth_pos, svlth_id1 : h_svlth_id1, svlth_im1 : h_svlth_im1}, 
		  dataType: 'json',
		  cache: false,
		  contentType: 'application/json',
		  success: function(data) {
			  let record = data.dtoList;
			  if (record[0].svlth_h == "I") {
				  jq("#antal").text(record[0].svlth_int);  
				  jq("#saldo").text(record[0].saldo);
			  } 
			  if (record[0].svlth_h == "U") {
				  jq("#antal").text(record[0].svlth_unt);  
				  jq("#saldo").text("-");
			  } 
			  
			  jq("#event").text(getDescription(record[0].svlth_h));
			  jq("#mrn").text(record[0].svlth_irn);
			  jq("#godsnr").text(record[0].svlth_ign);
			  jq("#position").text(record[0].svlth_pos);
		  }, 
		  error: function (jqXHR, exception) {
		    console.log("jqXHR",jqXHR);
		    console.log("exception",exception);
		  }	
	});	
}

function getRunningSvlthUttagUrl() {
	let runningUrl = svlthUrl;
	runningUrl = runningUrl + "&svlth_ign=" + h_svlth_ign;
	runningUrl = runningUrl + "&svlth_pos=" + h_svlth_pos;
	runningUrl = runningUrl + "&svlth_h=U";
	
	return runningUrl;
}

function getRunningSvlthRattelseUrl() {
	let runningUrl = svlthUrl;
	runningUrl = runningUrl + "&svlth_ign=" + h_svlth_ign;
	runningUrl = runningUrl + "&svlth_pos=" + h_svlth_pos;
	runningUrl = runningUrl + "&svlth_rty="+h_svlth_h;
	runningUrl = runningUrl + "&svlth_h=R";
	
	return runningUrl;
}


function getRunningSvlthUrl() {
		selectionMessage = '';
		let runningUrl = svlthUrl;
	
		selectedGodslokalkod = glk;
		selectedGodsnr = jq('#selectGodsnr').val();
		selectedMrn = jq('#selectMrn').val();
		selectedArrivalFrom = jq('#selectArrivalFrom').val();
		selectedArrivalTo = jq('#selectArrivalTo').val();
		
		if (selectedGodslokalkod != "") {
			runningUrl = runningUrl + "&svlth_igl=" + selectedGodslokalkod;
//			selectionMessage = selectionMessage + _.findWhere(godsLokalkoder,{svltf_igl:selectedGodslokalkod}).svltf_txt;
			selectionMessage = selectionMessage + " Godslokalkod:"+selectedGodslokalkod;
		
		} 
		if (selectedGodsnr != "") {
			runningUrl = runningUrl + "&svlth_ign=" + selectedGodsnr;
			selectionMessage = selectionMessage + " Godsnummer:"+selectedGodsnr;
		} 
		if (selectedMrn != "") {
			runningUrl = runningUrl + "&svlth_irn=" + selectedMrn;
			selectionMessage = selectionMessage + " MRN:"+selectedMrn;
		} 
		if (selectedArrivalFrom != "") {
			runningUrl = runningUrl + "&svlth_id2F=" + selectedArrivalFrom;
			selectionMessage = selectionMessage + " F.o.m ankomstdatum:"+selectedArrivalFrom;
		} 
		if (selectedArrivalTo != "") {
			runningUrl = runningUrl + "&svlth_id2T=" + selectedArrivalTo;
			selectionMessage = selectionMessage + " T.o.m ankomstdatum:"+selectedArrivalTo;
		} 

		if (selectedGodsnr == "" && selectedMrn == "" && selectedArrivalFrom == "" && selectedArrivalTo == "") {
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

function getGodslokalkod(caller){
	jq.ajax({
			  type: 'GET',
			  url: svltfUrl,
			  dataType: 'json',
			  cache: true,
			  contentType: 'application/json',
			  success: function(data) {
				  
				_.each(data.dtoList, function( d) {
					jq(caller).append(jq('<option></option>').attr('value', d.svltf_igl).text(d.svltf_igl));
				});

				godsLokalkoder = data.dtoList;
				
				jq("#glkButton").removeAttr('disabled');
				
			  }, 
			  error: function (jqXHR, exception) {
				    console.log("jqXHR",jqXHR);
				    console.log("exception",exception);
			  }	
	});	
}


function loadGodslokalkoder(){
	jq.ajax({
			  type: 'GET',
			  url: svltfUrl,
			  dataType: 'json',
			  cache: true,
			  contentType: 'application/json',
			  success: function(data) {
				  
				godsLokalkoder = data.dtoList;
				
			  }, 
			  error: function (jqXHR, exception) {
				    console.log("jqXHR",jqXHR);
				    console.log("exception",exception);
			  }	
	});	
}



function generateGodsnummer() {
	setGodsnummer("#svlth_ign");
}


function setGodsnummer(caller){
	jq.ajax({
			  type: 'GET',
			  url: generateGodsnummerUrl + '&svlth_igl='+glk, 
			  dataType: 'text',
			  cache: true,
			  contentType: 'application/json',
			  success: function(data) {
				jq(caller).val(data);
			  }, 
			  error: function (jqXHR, exception) {
				    console.log("jqXHR",jqXHR);
				    console.log("exception",exception);
			  }	
	});	
}


jq(function() {
	
	jq("#godsLokalkodModal").on('hidden.bs.modal', function(){
		console.log("selected", jq("#selectGodslokalkod").val());
	    
		jq('#formRecord').submit();
	    
	});		
	
	
	jq("#formRecord").submit(function() {
		setBlockUI();	
	});

	jq("#svlth_id2").datepicker({ 
		dateFormat: 'yymmdd'
	});		
	
	jq("#selectArrivalFrom").datepicker({ 
		dateFormat: 'yymmdd'
	});	

	jq("#selectArrivalTo").datepicker({ 
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