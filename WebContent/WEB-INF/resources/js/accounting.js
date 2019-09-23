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
	let runningUrl;
	
	jq('#selectGodsnr').val(sessionStorage.getItem('selectedGodsnr'));
	jq('#selectMrn').val(sessionStorage.getItem('selectedMrn'));
	if (sessionStorage.getItem('selectedArrivalFrom') != null) {
		jq('#selectArrivalFrom').val(sessionStorage.getItem('selectedArrivalFrom'));
	}
	jq('#selectArrivalTo').val(sessionStorage.getItem('selectedArrivalTo'));

	if (jq('#selectGodsnr').val() != "" || jq('#selectMrn').val()!= "" || jq('#selectArrivalFrom').val() !="" || jq('#selectArrivalTo').val() != "") {
		runningUrl = getRunningSvlthUrl();
	} else {
		runningUrl = svlthUrl + doNotLoad;  // a bit overkill

	}
	
	console.log("runningUrl",runningUrl);
	
	if (svlthTable != undefined) {
		return;
	}

	//Init datatables, done once, then reload with ajax
	svlthTable = jq('#svlthTable').DataTable({
		
		dom : '<"top"Bf>t<"bottom"lip><"clear">',
		ajax: {
	        "url": runningUrl,
	        "dataSrc": "dtoList"
	    },	
	
	    buttons: {
			dom: {  //to enable using className on buttons
			    button: {
			      tag: 'button',
			      className: ''
			    }
			},
			buttons: [
			    {
			        extend: 'colvis',
			        text: 'Välj kolumner',
			        collectionLayout: 'fixed two-column',
			        className: 'btn-sm',
			    },
			    {
			        extend: 'print',
			        text: 'Skriv ut',
			        orientation: 'landscape',
			        pageSize: 'A3', //https://pdfmake.github.io/docs/document-definition-object/page/
			        exportOptions: {
			            columns: ':visible',
			            search: 'applied',
			            order: 'applied'
			        },
			        title: "Tillfällig lagring",
			        messageTop: function () {
			               return selectionMessage;
			        },
			        messageBottom: now,
			        className: 'btn-sm'
			    },
				{
			        extend: 'pdfHtml5',
			        text: 'Skapa rapport',
			        orientation: 'landscape',  //portrait
			        pageSize: 'A3', //https://pdfmake.github.io/docs/document-definition-object/page/
			        pageMargins: [ 4, 6, 4, 6 ],
			        exportOptions: {
			           columns: [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 28 ]
			        },
			        title: "Tillfällig lagring",
			        messageTop: function () {
			               return selectionMessage;
			        },
			        messageBottom: now,
			        className: 'buttonGrayWithGreenFrame'
			    }
			]
	    },
	    searchHighlight: true,
	    responsive: true,
		columnDefs : [ 
			{ responsivePriority: 1, targets: -2 },
            { responsivePriority: 2, targets: -1 }
		],
	    columns: [
	    	{ data: "svlth_ign"},
	    	{ data: "svlth_pos"},
	        { data: null,
	        	render: function ( data, type, row, meta ) {
	        		return getDescription(row.svlth_h);
	        	}
	    	},
	    	{ data: null, //MRN
	        	render: function ( data, type, row, meta ) {
	        		if (row.svlth_h == 'I') {
		        		return row.svlth_irn;	        			
	        		}
	        		if (row.svlth_h == 'U') {
		        		return row.svlth_irn;	        			
	        		}
	        		if (row.svlth_h == 'R') {
		        		return row.svlth_rrn;	        			
	        		}	        		
	        	}	    		
	    	},
	    	{ data: null, //Utgående handling
	        	render: function ( data, type, row, meta ) {
	        		if (row.svlth_h == 'I') {
		        		return row.svlth_uex;	        			
	        		}
	        		if (row.svlth_h == 'U') {
	        			if (row.svlth_uex_concat != undefined) {
	        				let uex = row.svlth_uex + row.svlth_uex_concat;
	        				return uex;
	        			} else{
		        			return row.svlth_uex;	        			
	        			}
	        		}
	        		if (row.svlth_h == 'R') {
		        		return row.svlth_rex;	        			
	        		}	        		
	        	}		    		
	    	}, 
	        { data: null,  //tullid
	        	render: function ( data, type, row, meta ) {
	        		if (row.svlth_h == 'I') {
		        		return row.svlth_uti;	        			
	        		}
	        		if (row.svlth_h == 'U') {
		        		return row.svlth_uti;	        			
	        		}
	        		if (row.svlth_h == 'R') {
		        		return row.svlth_ruti;	        			
	        		}	        		
	        	}	        	
	        },
	    	{ data: null,  //Antal
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
	        { data: null, //Kollislag
	        	render: function ( data, type, row, meta ) {
	        		if (row.svlth_h == 'I') {
		        		return row.svlth_isl;	        			
	        		}
	        		if (row.svlth_h == 'U') {
		        		return row.svlth_isl;	        			
	        		}
	        		if (row.svlth_h == 'R') {
		        		return row.svlth_rsl;	        			
	        		}	        		
	        	}	
	        },
	        { data: null, //Beskrivning
	        	render: function ( data, type, row, meta ) {
	        		if (row.svlth_h == 'I') {
		        		return row.svlth_ivb;	        			
	        		}
	        		if (row.svlth_h == 'U') {
		        		return row.svlth_ivb;	        			
	        		}
	        		if (row.svlth_h == 'R') {
		        		return row.svlth_rvb;	        			
	        		}	        		
	        	}	
	        },
	        { data: null,
	        	render: function ( data, type, row, meta ) {
	        		//original FM -->return dateFormatter(row.svlth_id2);

	        		if (row.svlth_h == 'I') {
		        		return dateFormatter(row.svlth_id2);
	        		}
	        		if (row.svlth_h == 'U') {
		        		return dateFormatter(row.svlth_id2);
	        		}
	        		if (row.svlth_h == 'R') {
	        			let result = row.svlth_rud1;
	        			if (result != null && row.svlth_rty != null) {
	        				//only when rty = (I)nlägg
	        				if(row.svlth_rty == 'I'){
		        				if (result == '0') {
		        					result = null;
		        				}else{
		        					result =  dateFormatter(row.svlth_rud1);
		        				}
	        				}else{
	        					result = null;
	        				}
	        			}else{
	        				result = null;
	        			} 
	        			return result;
	        		}
	        		
	        		
	        	}   	
	        },
	        { data: null,
	        	render: function ( data, type, row, meta ) {
	        		if (row.svlth_ud1 == '0') {
	        			return null;
	        		}
	        		
	        		if (row.svlth_h == 'I') {
		        		return dateFormatter(row.svlth_ud1);
	        		}
	        		if (row.svlth_h == 'U') {
		        		return dateFormatter(row.svlth_ud1);
	        		}
	        		
	        		if (row.svlth_h == 'R') {
	        			let result = row.svlth_rud1;
	        			if (result != null && row.svlth_rty != null) {
	        				//only when rty = (U)ttag
	        				if(row.svlth_rty == 'U'){
		        				if (result == '0') {
		        					result = null;
		        				}else{
		        					result =  dateFormatter(row.svlth_rud1);
		        				}
	        				}else{
	        					result = null;
	        				}
	        			}else{
	        				result = null;
	        			} 
	        			return result;
	        		}
	        		
	        		//original FM -->return dateFormatter(row.svlth_ud1);
	        		
	        	}	        	
	        },
	        { data: null, //Vikt
	        	render: function ( data, type, row, meta ) {
	        		if (row.svlth_h == 'I') {
		        		return decimalConverter(row.svlth_ibr);
	        		}
	        		if (row.svlth_h == 'U') {
		        		return decimalConverter(row.svlth_ibr);
	        		}
	        		if (row.svlth_h == 'R') {
	        			let result = '';
	        			if (row.svlth_rbr != null) {
	        				result = decimalConverter(row.svlth_rbr);
	        			}
		        		return result;	         			
	        		}	        		
	        	}	
	        },
	        
	    	{ data: null,
	        	render: function ( data, type, row, meta ) {
	        		return getDescription(row.svlth_rty);
	        	}
	    	},
	    	
	    	{ data: "svlth_rnt" },
	    	{ data: "svlth_rtx" },
	    	{ data: "svlth_iex" },
	        { data: "svlth_ivb2" },
	    	{ data: "svlth_ivb3" },
	    	{ data: "svlth_ivb4" },
	    	{ data: "svlth_ivb5" },	    	
	        { data: "svlth_ih1" },
	    	{ data: "svlth_ih2" },	        
	        { data: "svlth_ih3" },
	        { data: "svlth_ih4" },
	        { data: "svlth_ih5" },
	        { data: "svlth_uex_concat" },        
	    	{ data: "svlth_itx" },
	    	{ data: null,
	        	render: function ( data, type, row, meta ) {
	        		return row.timestamp;
	        	} ,
	        	className: 'dt-body-nowrap'
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
	        { data: "svlth_rud1" },
	    ],
	    order: [[0, 'desc'],[1, 'asc']], 
	    lengthMenu : [ 25, 75, 100, 200, 500 ],
		language : {
			url : getLanguage(lang)
		}        
	
	});
	
	
    jq('#svlthTable').on( 'draw.dt', function () {
    	svlthTable.columns( [ 14, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 29, 32 ] ).visible( false);
    });	
    
    
    jq('#svlthTable').on( 'processing.dt', function ( e, settings, processing ) {
    	if (processing) {
    	   	setBlockUI();
    	} else {
    		unBlockUI();
    	}
    	
    }).dataTable();
    		
    
	
} //end initSvlthSearch


function loadSvlth() {
	let runningUrl;
	runningUrl = getRunningSvlthUrl();
	console.log("runningUrl" + runningUrl);
	if (runningUrl == null) {
		alert('Du måste fylla i något fält för sökning');
		return null;
	}
	
	svlthTable.ajax.url(runningUrl);
	svlthTable.ajax.reload();

}

function loadSvlthUttag() {
	let runningUrl;
	runningUrl= getRunningSvlthUttagUrl();
	console.log("runningUrl" + runningUrl);
	
	if (uttagTable != undefined) {
		uttagTable.ajax.url(runningUrl);
		uttagTable.ajax.reload();		
		
		return;
	}	
	
	setUttagHeader();
	
	uttagTable = jq('#uttagTable').DataTable({
		dom : '<"top">t<"bottom"flip><"clear">',
	    ajax: {
	        url: runningUrl,
	        dataSrc: "dtoList"
	    },	
	    mark: true,
		responsive : true,
		order : [ [ 2, "desc" ] ],		
		columns : [ 
	    	{ data : "svlth_uex"},
			{ data : null,
	        	render: function ( data, type, row, meta ) {

	        		return dateFormatter(row.svlth_ud1);
	        	}	    		
			},
			{ data : "svlth_uti"}, 
			{ data : "svlth_unt"}, 
			{ data : "svlth_utx"},
	        { data: null,
	        	render: function ( data, type, row, meta ) {
	        		return row.timestamp;
	        	} 	
	        }			
			],
		lengthMenu : [ 10, 25, 100 ],
		order: [[1, 'desc']], 
		language : {
			url : getLanguage(lang)
		}

	});	

}


function loadEvent() {
	let runningUrl;
	runningUrl= getRunningSvlthRattelseUrl();
	console.log("runningUrl" + runningUrl);
	
	if (rattelseTable != undefined) {
		rattelseTable.ajax.url(runningUrl);
		rattelseTable.ajax.reload();		
		
		return;
	}	
	
	setEventHeader();
	
		rattelseTable = jq('#rattelseTable').DataTable({
			dom : '<"top">t<"bottom"flip><"clear">',
		    ajax: {
		        url: runningUrl,
		        dataSrc: "dtoList"
		    },	
		    mark: true,
			responsive : true,
			columns : [ 
		    	{ data : "svlth_rrn"},
		    	{ data : null, 
		        	render: function ( data, type, row, meta ) {
		        		if (row.svlth_rd2 == '0') {
		        			return null;
		        		}
		        		return dateFormatter(row.svlth_rd2);
		        	} 
		    	},
		    	{ data : "svlth_rnt"},
		    	{ data : "svlth_rsl"},
		    	{ data : null,
		        	render: function ( data, type, row, meta ) {
		        		return row.svlth_rvb; 
		        	},
		        	 defaultContent: ''
		    	},
		    	{ data : null,
		        	render: function ( data, type, row, meta ) {
		        		if (row.svlth_rbr != null) {
		        			let rbr = row.svlth_rbr.toString();
		        			let result = rbr.replace(".",",");   	
		        			return result;
		        		}
		        	},
		        	 defaultContent: ''
		    	},
		    	
		    	
		    	{ data : "svlth_rex"},
		    	{ data : "svlth_ruti"},		  
		    	{ data : "svlth_rnt"},		  
		    	{ data : null, 
		        	render: function ( data, type, row, meta ) {
		        		if (row.svlth_rud1 == '0') {
		        			return null;
		        		}
		        		return dateFormatter(row.svlth_rud1);
		        	} 
		    	},		    	
		    	{ data : "svlth_rtx"},
		        { data: null,
		        	render: function ( data, type, row, meta ) {
		        		return row.timestamp;
		        	} 	
		        }
				],
			lengthMenu : [ 5, 10, 20 ],
			order: [[11, 'desc']], 
			language : {
				url : getLanguage(lang)
			}
	
		});	
		
		
	if (h_svlth_h == 'I') {
		jq('#rattelseTable').on( 'draw.dt', function () {
			rattelseTable.columns( [6,7,8,9 ] ).visible( false);
		});	
		jq("#uttag").hide();
	} else { // 'U'
		jq('#rattelseTable').on( 'draw.dt', function () {
			rattelseTable.columns( [0,1,2,3,4,5 ] ).visible( false);
		});	
		jq("#inlagg").hide();
	}
	
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

	
function clearValuesUttag() {
    jq("#svlth_uex").val("");
    jq("#svlth_uti").val("");
    jq("#svlth_unt").val("");
    jq("#svlth_utx").val("");
    jq("#svlth_ud1").val("");
}

function clearValuesRattelse() {
	//Inlagg
	jq("#svlth_rrn").val("");	
	jq("#svlth_rex").val("");	
	jq("#svlth_rd2").val("");	
	jq("#svlth_rnt").val("");	
	jq("#svlth_rsl").val("");		
	jq("#svlth_rbr").val("");	
	jq("#svlth_rvb").val("");		
	//Uttag
	jq("#svlth_rexU").val("");
	jq("#svlth_ruti").val(""); 
	jq("#svlth_rntU").val(""); 	
	jq("#svlth_rud1").val(""); 		
	//Common
	jq("#svlth_rtxU").val("");
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
			  jq("#mrn").text(record[0].svlth_irn);
			  jq("#godsnr").text(record[0].svlth_ign);
			  jq("#position").text(record[0].svlth_pos);
			  jq("#arrival").text(dateFormatter(record[0].svlth_id2));
			  jq("#archive").text(dateFormatter(record[0].svlth_id1));
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
			  jq("#beskrivning").text(record[0].svlth_ivb);
			  jq("#vikt").text(decimalConverter(record[0].svlth_ibr));
			  jq("#slag").text(record[0].svlth_isl);

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
			runningUrl = runningUrl + "&svlth_id2F=" + selectedArrivalFrom.replace(/-/g,"");
			selectionMessage = selectionMessage + " F.o.m ankomstdatum:"+selectedArrivalFrom;
		} 
		if (selectedArrivalTo != "") {
			runningUrl = runningUrl + "&svlth_id2T=" + selectedArrivalTo.replace(/-/g,"");
			selectionMessage = selectionMessage + " T.o.m ankomstdatum:"+selectedArrivalTo;
		} 

		if (selectedGodsnr == "" && selectedMrn == "" && selectedArrivalFrom == "" && selectedArrivalTo == "") {
			return null;
		}
		
		//cache query params
		sessionStorage.setItem('selectedGodsnr', selectedGodsnr);
		sessionStorage.setItem('selectedMrn', selectedMrn);
		sessionStorage.setItem('selectedArrivalFrom', selectedArrivalFrom);
		sessionStorage.setItem('selectedArrivalTo', selectedArrivalTo);
		
		return runningUrl;	
		
}


function initSvtx03fSearch(caller) {
	svtx03fTable = jq('#svtx03fTable').DataTable({
		dom : '<"top"f>t<"bottom"lip><"clear">',
		ajax: {
	        url: kollislagkoderUrl + doNotLoad,
	        dataSrc: "dtoList"
	    },	
		mark: true,			
		responsive : true,
		select : true,
		destroy : true,
		scrollY : "300px",
		scrollCollapse : false,
		order : [ [ 1, "desc" ] ],
		columnDefs : [ 
			{
				targets : 1,
				className: 'dt-body-center',
			    render: function ( data, type, row, meta ) {
	           		return '<a>' +
	       			'<img class="img-fluid float-center" title="Välg" src="resources/images/bebullet.gif">' +
	       			'</a>'    	
			    }
			}
		],			
		columns : [ 
			{data : "svtx03_03"}, 
	    	{
	        	class:          "choose dt-body-center",
	        	orderable:      false,
	            data:           null,
	            defaultContent: ''
	    	},		
			{data : "svtx03_04"}
		 ],
		lengthMenu : [ 10, 25, 75],
		language : {
			url : getLanguage(lang)
		},
		

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


//Deprecated remomve when appropriate
function getFieldChangeCodes(caller) {
	if (h_svlth_h == 'I') {
		jq(caller).append(jq('<option></option>').attr('value', 'MRN').text("MRN"));
		jq(caller).append(jq('<option></option>').attr('value', 'ANTAL').text("Räknat antal"));
		jq(caller).append(jq('<option></option>').attr('value', 'BESK1').text("Varubeskrivning 1"));
		jq(caller).append(jq('<option></option>').attr('value', 'BESK2').text("Varubeskrivning 2"));
		jq(caller).append(jq('<option></option>').attr('value', 'BESK3').text("Varubeskrivning 3"));
		jq(caller).append(jq('<option></option>').attr('value', 'BESK4').text("Varubeskrivning 4"));
		jq(caller).append(jq('<option></option>').attr('value', 'BESK5').text("Varubeskrivning 5"));
		jq(caller).append(jq('<option></option>').attr('value', 'EXTR').text("Extern referans"));
		jq(caller).append(jq('<option></option>').attr('value', 'ANKD').text("Ankomstdatum"));
		jq(caller).append(jq('<option></option>').attr('value', 'VIKT').text("Vikt"));
		jq(caller).append(jq('<option></option>').attr('value', 'SLAG').text("Kollislag"));
		jq(caller).append(jq('<option></option>').attr('value', 'TH1').text("Tidigare handling 1"));
		jq(caller).append(jq('<option></option>').attr('value', 'TH2').text("Tidigare handling 2"));
		jq(caller).append(jq('<option></option>').attr('value', 'TH3').text("Tidigare handling 3"));
		jq(caller).append(jq('<option></option>').attr('value', 'TH4').text("Tidigare handling 4"));
		jq(caller).append(jq('<option></option>').attr('value', 'TH5').text("Tidigare handling 5"));

		
	} else if (h_svlth_h == 'U') {
		jq(caller).append(jq('<option></option>').attr('value', 'ANTAL').text("Räknat antal"));
		jq(caller).append(jq('<option></option>').attr('value', 'UH1').text("Utgående handling 1"));
		//TODO UH1-UH10
		jq(caller).append(jq('<option></option>').attr('value', 'UTD').text("Uttagsdatum"));
		jq(caller).append(jq('<option></option>').attr('value', 'TUID').text("Tullid"));
	} else {
		console.log("h_svlth_h not set correct.");
		alert("Teknisk fel: h_svlth_h inte satt.");
	}
	
}



function generateGodsnummer() {
	setBlockUI();
	setGodsnummer("#svlth_ign");
	unBlockUI();
}


function setGodsnummer(caller){
	jq.ajax({
			  type: 'GET',
			  url: generateGodsnummerUrl + '&svlth_igl='+glk, 
			  dataType: 'text',
			  cache: true,
			  contentType: 'application/json',
			  success: function(data) {
				  let godsnummerconcat = glk + data;
				  jq(caller).val(godsnummerconcat);
			  }, 
			  error: function (jqXHR, exception) {
				    console.log("jqXHR",jqXHR);
				    console.log("exception",exception);
			  }	
	});	
}

function dateFormatter(dateInt) {
	let dateString = dateInt.toString();
	let dateDate = dateFns.parse(dateString);
	let result = dateFns.format(new Date(dateDate),'YY-MM-DD');	          		

	return result;	
}

function decimalConverter(value) {
	let valueString = value.toString();
	let result = valueString.replace(".",",");
	return result;	 
}

jq(function() {
	
	jq("#godsLokalkodModal").on('hidden.bs.modal', function(){
		jq('#glkformRecord').submit();
	});		

	
//	jq('#in-fukker-button').click(function(evt) {
//		console.log("#fukker-button clicked!");
//		setBlockUI();	
//		var trimmed = jq("#svlth_id2").val().replace(/-/g,"")
//		jq("#svlth_id2").val(trimmed);
//		jq("#informRecord").submit();
//	});	
	
	
	jq("#informRecord").submit(function() {
		setBlockUI();	
		var trimmed = jq("#svlth_id2").val().replace(/-/g,"")
		jq("#svlth_id2").val(trimmed);
	});
	
	jq("#rattformRecord").submit(function() {
		setBlockUI();	
		var trimmed = jq("#svlth_rd2").val().replace(/-/g,"")
		jq("#svlth_rd2").val(trimmed);	
		var trimmed = jq("#svlth_rud1").val().replace(/-/g,"")
		jq("#svlth_rud1").val(trimmed);	
	});

//	jq('#ut-fukker-button').click(function(evt) {	
//		setBlockUI();	
//		var trimmed = jq("#svlth_ud1").val().replace(/-/g,"")
//		jq("#svlth_ud1").val(trimmed);	
//		jq("#utformRecord").submit();
//	});	
		
	jq("#utformRecord").submit(function() {
		setBlockUI();	
		var trimmed = jq("#svlth_ud1").val().replace(/-/g,"")
		jq("#svlth_ud1").val(trimmed);		
	});	
	

	
	
	
	jq("#svlth_id2").datepicker({ 
		dateFormat: 'yy-mm-dd'
	});		

	jq("#svlth_rd2").datepicker({ 
		dateFormat: 'yy-mm-dd'
	});		
	
	jq("#svlth_ud1").datepicker({ 
		dateFormat: 'yy-mm-dd'
	});		
	
	jq("#svlth_rud1").datepicker({ 
		dateFormat: 'yy-mm-dd'
	});		
	
	jq("#selectArrivalFrom").datepicker({ 
		dateFormat: 'yy-mm-dd',
	});	

	jq("#selectArrivalTo").datepicker({ 
		dateFormat: 'yy-mm-dd'
	});		

	
	jq('a#kollislag_Link').click(function() {
		jq('#kollislag_Link').attr('target','_blank');
    	window.open('childwindow_codes.do?caller=svlth_isl', "codeWin", "top=300px,left=500px,height=500px,width=800px,scrollbars=no,status=no,location=no");
	}); 	

	jq('a#kollislag_Link2').click(function() {
		jq('#kollislag_Link2').attr('target','_blank');
    	window.open('childwindow_codes.do?caller=svlth_rsl', "codeWin", "top=300px,left=500px,height=500px,width=800px,scrollbars=no,status=no,location=no");
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