	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	
  	jq(function() {
        jq('#alinkMainList').click(function() { 
    		setBlockUI();
    	  });	
          jq('#alinkHeader').click(function() { 
    		setBlockUI();
    	  });
    	  jq('#alinkItemLines').click(function() { 
    		setBlockUI();
    	  });
    	  jq('#alinkLogging').click(function() { 
    		setBlockUI();
    	  });
    	  jq('#alinkArchive').click(function() { 
    		setBlockUI();
    	  });
  	});
  	
  	function reloadThis() {
  		setBlockUI();
  		window.location = 'nctsexport_edit_items.do?action=doFetch&avd='+ jq('#avd').val() + '&sign=' + jq('#sign').val() +'&opd=' + jq('#opd').val();
  	}
  	
  	jq(function() {
	  	jq('#itemLinesImportButton').click(function() {
	    	jq('#itemLinesImportButton').attr('target','_blank');
	    	window.open('nctsexport_edit_items_childwindow_uppdragslist_gettoitemlines.do?action=doFind&avdNcts=' + jq('#avd').val() + '&opdNcts=' + jq('#opd').val(), "codeWinItemLinesImport", "top=300px,left=400px,height=500px,width=900px,scrollbars=no,status=no,location=no");
	    });
	    jq('#itemLinesImportButton').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#itemLinesImportButton').click();
			}
	    });
  	});
  	/**
  	 * gets a specific item line
  	 * 
  	 * @param record
  	 */
  	function getItemData(record) {
	  	var htmlValue = record.id;
	  	var applicationUserParam = jq('#applicationUser').val();
	  	var avdParam = jq('#avdItemList').val();
	  	var opdParam = jq('#opdItemList').val();
	  	//alert(htmlValue);
	  	
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getNctsExportSpecificTopicItemChosenFromGuiElement.do',
	  	  data: { applicationUser : applicationUserParam, 
	  		  	  elementValue : htmlValue, 
	  		  	  avd : avdParam, 
	  		  	  opd : opdParam },
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
			for ( var i = 0; i < len; i++) {
				jq('#lineNr').val(data[i].tvli);
				jq('#tvli').val(data[i].tvli); 
				jq('#tvvnt').val(data[i].tvvnt);jq('#tvvt').val(data[i].tvvt);
				jq('#tvvt2').val(data[i].tvvt2);jq('#tvvt3').val(data[i].tvvt3);
				jq('#tvvt4').val(data[i].tvvt4);jq('#tvvt5').val(data[i].tvvt5);
				
				jq('#tvtdr').val(data[i].tvtdr);jq('#tvdk').val(data[i].tvdk); 
				jq('#tvvktb').val(data[i].tvvktb); jq('#tvvktn').val(data[i].tvvktn); 
				jq('#tvnt').val(data[i].tvnt);jq('#tvdref').val(data[i].tvdref);
				jq('#tvdsk').val(data[i].tvdsk);
				
				jq('#tvdty').val(data[i].tvdty); 
				jq('#tvalk').val(data[i].tvalk);
				jq('#tvblk').val(data[i].tvblk);
				jq('#tveh').val(data[i].tveh);
				
				//Extra section
				jq('#tvvtsk').val(data[i].tvvtsk);jq('#tvtdt').val(data[i].tvtdt);
				jq('#tvtdsk').val(data[i].tvtdsk);jq('#tvtdo').val(data[i].tvtdo);
				jq('#tvtdos').val(data[i].tvtdos);
				jq('#tvdosk').val(data[i].tvdosk);jq('#tvmtxt').val(data[i].tvmtxt);
				jq('#tvmsk').val(data[i].tvmsk);jq('#tvtlo').val(data[i].tvtlo);
				jq('#tvmsk').val(data[i].tvmsk);jq('#tvtlo').val(data[i].tvtlo);
				jq('#tvexkd').val(data[i].tvexkd);jq('#tvexlk').val(data[i].tvexlk);
				jq('#tvavd2').val(data[i].tvavd2);jq('#tvtdn2').val(data[i].tvtdn2);
				jq('#tvcnr').val(data[i].tvcnr);jq('#tvmn').val(data[i].tvmn);
				jq('#tvmnsk').val(data[i].tvmnsk);jq('#tvtdn2').val(data[i].tvtdn2);
				jq('#tvnteh').val(data[i].tvnteh);
				jq('#tvcnr2').val(data[i].tvcnr2);jq('#tvcnr3').val(data[i].tvcnr3);
				jq('#tvcnr4').val(data[i].tvcnr4);jq('#tvcnr5').val(data[i].tvcnr5);
				jq('#tvcnr6').val(data[i].tvcnr6);
				jq('#tvfv').val(data[i].tvfv);jq('#tvfvnt').val(data[i].tvfvnt);
				jq('#tvkns').val(data[i].tvkns);
				jq('#tvnas').val(data[i].tvnas);
				jq('#tvads1').val(data[i].tvads1);jq('#tvpns').val(data[i].tvpns);
				jq('#tvpss').val(data[i].tvpss);jq('#tvlks').val(data[i].tvlks);
				jq('#tvsks').val(data[i].tvsks);jq('#tvtins').val(data[i].tvtins);
				jq('#tvknk').val(data[i].tvknk);
				jq('#tvnak').val(data[i].tvnak);
				jq('#tvadk1').val(data[i].tvadk1);jq('#tvpnk').val(data[i].tvpnk);
				jq('#tvpsk').val(data[i].tvpsk);jq('#tvlkk').val(data[i].tvlkk);
				jq('#tvskk').val(data[i].tvskk);jq('#tvtink').val(data[i].tvtink);
				jq('#tvln').val(data[i].tvln);jq('#tvrefl').val(data[i].tvrefl);
				
				//rubrik 31 (extra Godsmärke)
				jq('#tvmn2').val(data[i].tvmn2);jq('#tveh2').val(data[i].tveh2);
				jq('#tvnt2').val(data[i].tvnt2);jq('#tvnteh2').val(data[i].tvnteh2);
				jq('#tvmn3').val(data[i].tvmn3);jq('#tveh3').val(data[i].tveh3);
				jq('#tvnt3').val(data[i].tvnt3);jq('#tvnteh3').val(data[i].tvnteh3);
				jq('#tvmn4').val(data[i].tvmn4);jq('#tveh4').val(data[i].tveh4);
				jq('#tvnt4').val(data[i].tvnt4);jq('#tvnteh4').val(data[i].tvnteh4);
				jq('#tvmn5').val(data[i].tvmn5);jq('#tveh5').val(data[i].tveh5);
				jq('#tvnt5').val(data[i].tvnt5);jq('#tvnteh5').val(data[i].tvnteh5);
				//end rubrik 31

				
				
				
				//rubrik 44 (Särskilda- Tilläggsupplysningar) - Multilines
				jq('#tvdty2').val(data[i].tvdty2);jq('#tvdref2').val(data[i].tvdref2);
				jq('#tvdsk2').val(data[i].tvdsk2);jq('#tvdo2').val(data[i].tvdo2);
				jq('#tvdosk2').val(data[i].tvdosk2);
				jq('#tvmtxt2').val(data[i].tvmtxt2);jq('#tvmsk2').val(data[i].tvmsk2);
				jq('#tvtlo2').val(data[i].tvtlo2);jq('#tvexkd2').val(data[i].tvexkd2);
				jq('#tvexlk2').val(data[i].tvexlk2);
				
				jq('#tvdty3').val(data[i].tvdty3);jq('#tvdref3').val(data[i].tvdref3);
				jq('#tvdsk3').val(data[i].tvdsk3);jq('#tvdo3').val(data[i].tvdo3);
				jq('#tvdosk3').val(data[i].tvdosk3);
				jq('#tvmtxt3').val(data[i].tvmtxt3);jq('#tvmsk3').val(data[i].tvmsk3);
				jq('#tvtlo3').val(data[i].tvtlo3);jq('#tvexkd3').val(data[i].tvexkd3);
				jq('#tvexlk3').val(data[i].tvexlk3);
				
				jq('#tvdty4').val(data[i].tvdty4);jq('#tvdref4').val(data[i].tvdref4);
				jq('#tvdsk4').val(data[i].tvdsk4);jq('#tvdo4').val(data[i].tvdo4);
				jq('#tvdosk4').val(data[i].tvdosk4);
				jq('#tvmtxt4').val(data[i].tvmtxt4);jq('#tvmsk4').val(data[i].tvmsk4);
				jq('#tvtlo4').val(data[i].tvtlo4);jq('#tvexkd4').val(data[i].tvexkd4);
				jq('#tvexlk4').val(data[i].tvexlk4);
				
				
				jq('#tvdty_readonly').val(data[i].tvdty);jq('#tvdref_readonly').val(data[i].tvdref);
				jq('#tvdsk_readonly').val(data[i].tvdsk);jq('#tvdo').val(data[i].tvdo);
				// End rubrik 44
				
			}
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	  }
	  	});
  	}
  	
  	//taric varukod search (same function as in TDS EXPORT. Therefore the name: search_svvs_vata in the search field name)
  	function searchTaricVarukod() {
		jq(function() {
			jq.getJSON('searchTaricVarukodNcts.do', {
				applicationUser : jq('#applicationUser').val(),
				taricVarukod : jq('#search_svvs_vata').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].svvs_vata + '">' + data[i].svvs_vatak + ' ' + data[i].svvs_vata + ' ' + data[i].svvs_txtk +  '</option>';
				}
				//now that we have our options, give them to our select
				jq('#taricVarukodList').html(html);
			});
		});
	}
  	//set the taric varukod in target input text field
  	jq(function() { 
	    jq('#taricVarukodList').change(function() {
		  //init field(s)
		  jq('#tvvnt').val("");
		  //and populate (if applicable)
		  var key = jq('#taricVarukodList').val();
		  jq('#tvvnt').val(key.substring(0,6));
		  			  
		});
	});
  	
  	//----------------------------------
	//Events Varukod (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgTaricVarukodSearch').click(function(){
    			jq("#search_svvs_vata").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_svvs_vata').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTaricVarukod);
			}			
    		});
	});

	//On Keypress (13)
	jq(function() { 
	    jq('#taricVarukodList').keypress(function() {
		    	if(e.which == 13) {
				//alert("hej till publiken");
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#svev_vata').val(""); 
				//now populate (if applicable)
			    	var key = jq('#taricVarukodList').val();
			    	jq('#svev_vata').val(key); 
		    	}
	    });
	    
	});
	
	
	//---------------------------------------------------------
  	//FETCH CUSTOMER from SENDER [AVSÄNDARE] html area
  	//---------------------------------------------------------
	var map = {};
  	
  	//init the customer object in javascript (will be put into a map)
  	var customer = new Object();
  	//fields
  	customer.kundnr = "";customer.knavn = "";customer.eori = "";customer.adr1 = "";
  	customer.adr2 = "";customer.adr3 = "";customer.postnr = "";customer.syland = "";
  	customer.kpers = "";customer.tlf = "";
  	//---------------------------------------------------------
  	//FETCH CUSTOMER from SENDER [AVSÄNDARE] html area
  	// [Same as TDS-EXPORT but with GUI fields for NCTS]
  	//---------------------------------------------------------
	function searchSenderOwnWindow() {
		jq(function() {
			jq.getJSON('searchCustomer.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : jq('#search_tvnas').val(),
				customerNumber : jq('#search_tvkns').val(),
				ajax : 'true'
			}, function(data) {
				//alert("Hello");
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
					customer = new Object();
					customer.kundnr = data[i].kundnr;
					customer.knavn = data[i].knavn;
					customer.eori = data[i].eori;
					customer.adr1 = data[i].adr1;
					customer.adr2 = data[i].adr2;
					customer.adr3 = data[i].adr3;
					customer.postnr = data[i].postnr;
					customer.kpers = data[i].kpers;
					customer.tlf = data[i].tlf;
					customer.syland = data[i].syland;
				  	
					//put the object in map now with customerNumber as key
					map[customer.kundnr] = customer;
				}
				//now that we have our options, give them to our select
				jq('#senderList').html(html);
			});
		});
	}
	//onChange sender list
	jq(function() { 
	    jq('#senderList').change(function() {
	      //init fields
		  jq('#tvnas').val("");
		  jq('#tvtins').val("");
		  jq('#tvads1').val("");
		  jq('#tvpns').val("");
		  jq('#tvpss').val("");
		  jq('#tvlks').val("");
		  jq('#tvsks').val("");
		  
		  //now populate (if applicable)
		  var key = jq('#senderList').val();
		  jq('#tvkns').val(key);
		  customer = map[key];
		  jq('#tvnas').val(customer.knavn);
		  jq('#tvtins').val(customer.eori);
		  jq('#tvads1').val(customer.adr1);
		  jq('#tvpns').val(customer.postnr);
		  jq('#tvpss').val(customer.adr3);
		  jq('#tvlks').val(customer.syland);
		  jq('#tvsks').val("");
	    });
	});
	
	//onClick for Sender dialog
	jq(function() { 
	    jq('#searchCustomerCloseCancel').click(function() {
	      //rescue the original fields
	      jq('#tvkns').val(jq("#orig_tvkns").val());	
		  jq('#tvnas').val(jq("#orig_tvnas").val());
		  jq('#tvtins').val(jq("#orig_tvtins").val());
		  jq('#tvads1').val(jq("#orig_tvads1").val());
		  jq('#tvpns').val(jq("#orig_tvpns").val());
		  jq('#tvpss').val(jq("#orig_tvpss").val());
		  jq('#tvlks').val(jq("#orig_tvlks").val());
		  jq('#tvsks').val(jq("#orig_tvsks").val());
	    });
	});
	
	//---------------
	//Sender list
	//---------------
	//onChange
	jq(function() { 
	    jq('#senderList').change(function() {
		    	jq('#tvnas').val(""); 
		    	//now populate (if applicable)
		    	var key = jq('#senderList').val();
		    	jq('#tvnas').val(key); 
	    });
	});
	//On Keypress (13)
	jq(function() { 
	    jq('#senderList').keypress(function() {
		    	if(e.which == 13) {
				//alert("hej till publiken");
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#tvnas').val(""); 
				//now populate (if applicable)
			    	var key = jq('#senderList').val();
			    	jq('#tvnas').val(key); 
		    	}
	    });
	    
	});
	
	//----------------------------------
	//Events Sender (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgCustomerSearch').click(function(){
    			jq("#search_tvkns").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_tvkns').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchSenderOwnWindow);
			}			
    		});
		jq('#search_tvnas').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchSenderOwnWindow);
			}			
    		});
	});

	
  	//---------------------------------------------------------
	//FETCH CUSTOMER from RECEIVER [MOTTAGARE] html area
  	//---------------------------------------------------------
	function searchReceiverOwnWindow() {
		jq(function() {
			jq.getJSON('searchCustomer.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : jq('#search_tvnak').val(),
				customerNumber : jq('#search_tvknk').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
					customer = new Object();
					customer.kundnr = data[i].kundnr;
					customer.knavn = data[i].knavn;
					customer.eori = data[i].eori;
					customer.adr1 = data[i].adr1;
					customer.adr2 = data[i].adr2;
					customer.adr3 = data[i].adr3;
					customer.postnr = data[i].postnr;
					customer.kpers = data[i].kpers;
					customer.tlf = data[i].tlf;
					customer.syland = data[i].syland;
				  	
					//put the object in map now with customerNumber as key
					map[customer.kundnr] = customer;
				}
				//now that we have our options, give them to our select
				jq('#receiverList').html(html);
			});
		});
	}
	//Sets receiver values after user selection
	jq(function() { 
	    jq('#receiverList').change(function() {
		  //init all fields
		  jq('#tvnak').val("");
		  jq('#tvtink').val("");
		  jq('#tvadk1').val("");
		  jq('#tvpnk').val("");
		  jq('#tvpsk').val("");
		  jq('#tvlkk').val("");	
		  jq('#tvskk').val("");	
		  
		  //now populate (if applicable)
		  var key = jq('#receiverList').val();
		  jq('#tvknk').val(key);
		  customer = map[key];
		  jq('#tvtink').val(customer.eori);
		  jq('#tvnak').val(customer.knavn);
		  jq('#tvadk1').val(customer.adr1);
		  jq('#tvpnk').val(customer.postnr);
		  jq('#tvpsk').val(customer.adr3);
		  jq('#tvlkk').val(customer.syland);
		  jq('#tvskk').val("");			  
		});
	});
	//onClick for Receiver(Mottagare) dialog
	jq(function() { 
	    jq('#searchCustomer10CloseCancel').click(function() {
	      //rescue the original fields
	      jq('#tvknk').val(jq("#orig_tvknk").val());	
		  jq('#tvnak').val(jq("#orig_tvnak").val());
		  jq('#tvtink').val(jq("#orig_tvtink").val());
		  jq('#tvadk1').val(jq("#orig_tvadk1").val());
		  jq('#tvpnk').val(jq("#orig_tvpnk").val());
		  jq('#tvpsk').val(jq("#orig_tvpsk").val());
		  jq('#tvlkk').val(jq("#orig_tvlkk").val());
		  jq('#tvskk').val(jq("#orig_tvskk").val());
		  
	    });
	});

	//---------------
	//Receiver list
	//---------------
	//onChange
	jq(function() { 
	    jq('#receiverList').change(function() {
		    	jq('#tvnak').val(""); //tds export
		    	//now populate (if applicable)
		    	var key = jq('#receiverList').val();
		    	jq('#tvnak').val(key); //tds export
	    });
	});
	//On Keypress (13)
	jq(function() { 
	    jq('#receiverList').keypress(function() {
		    	if(e.which == 13) {
				//alert("hej till publiken");
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#tvnak').val(""); //tds export
				//now populate (if applicable)
			    	var key = jq('#receiverList').val();
			    	jq('#tvnak').val(key); //tds export
		    	}
	    });
	    
	});
	
	//----------------------------------
	//Events Receiver (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgReceiverSearch').click(function(){
    			jq("#search_tvnak").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_tvknk').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchReceiverOwnWindow);
			}			
    		});
		jq('#search_tvnak').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchReceiverOwnWindow);
			}			
    		});
	});

	
	jq(document).ready(function() {
	      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
	      
			jq('#tblItemLinesAll').dataTable( {
	    	  "dom": '<"top">t<"bottom"flip><"clear">',
	    	  "scrollY":    "800px",
	    	  "deferRender": true,
	  		  "scrollCollapse":  true,
	  		  "columnDefs": [{ "type": "num", "targets": 0 }],
	  		  "lengthMenu": [ 75, 100, 300, 400, 900]
	  	  });
	  	  
	      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
	      jq('#tblItemLines').dataTable( {
	    	  "dom": '<"top">t<"bottom"flip><"clear">',
	    	  "scrollY":    "180px",
	    	  "deferRender": true, //to speed the table load
	    	  "scrollCollapse":  true,
	  		  "columnDefs": [{ "type": "num", "targets": 0 }],
	  		  "lengthMenu": [ 75, 100, 300, 400, 900]
	  	  });
	      
	      //event on input field for search
	      jq('input.tblItemLines_filter').on( 'keyup click', function () {
	      		filterGlobal();
	      });
	      //event on input field for search
	      jq('input.tblItemLinesAll_filter').on( 'keyup click', function () {
	      		filterGlobal();
	      });

	});
	
	
	

		


  	
	