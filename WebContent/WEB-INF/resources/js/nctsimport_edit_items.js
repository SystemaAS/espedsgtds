	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();

  	jq(function() {
  	  jq('#alinkMainList').click(function() { 
		setBlockUI();
	  });
  	  jq('#alinkHeader').click(function() { 
		setBlockUI();
	  });
	  jq('#alinkUnloading').click(function() { 
		setBlockUI();
	  });
	  jq('#alinkUnloadingItemLines').click(function() { 
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
  	
  	//date fields
    jq(function() {
  	  jq("#tvgdt").datepicker({ 
  		  dateFormat: 'yymmdd' 
  	  });
  	  jq("#tvgodt").datepicker({ 
  		  dateFormat: 'yymmdd' 
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
	  	  url: 'getNctsImportSpecificTopicItemChosenFromGuiElement.do',
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
				jq('#tvst').val(data[i].tvst);
				jq('#tvinf').val(data[i].tvinf);
				
				jq('#tvstsk').val(data[i].tvstsk); jq('#tvstlk').val(data[i].tvstlk); jq('#tvctl').val(data[i].tvctl);
				jq('#tvflg').val(data[i].tvflg); jq('#tvinsk').val(data[i].tvinsk); jq('#tvgdt').val(data[i].tvgdt);
				jq('#tvgm').val(data[i].tvgm); jq('#tvgmsk').val(data[i].tvgmsk); jq('#tvgmst').val(data[i].tvgmst);
				jq('#tvgmss').val(data[i].tvgmss); jq('#tvgmlk').val(data[i].tvgmlk); jq('#tvdant').val(data[i].tvdant);
				jq('#tvdfkd').val(data[i].tvdfkd); jq('#tvdfsk').val(data[i].tvdfsk); jq('#tvtaid').val(data[i].tvtaid);
				
				jq('#tvtask').val(data[i].tvtask); jq('#tvtalk').val(data[i].tvtalk); jq('#tvgodt').val(data[i].tvgodt);
				jq('#tvom').val(data[i].tvom); jq('#tvomsk').val(data[i].tvomsk); jq('#tvomst').val(data[i].tvomst);
				jq('#tvomss').val(data[i].tvomss); 
				jq('#tvomlk').val(data[i].tvomlk); jq('#tvcnr').val(data[i].tvcnr);
				
				jq('#tvcnr2').val(data[i].tvcnr2); jq('#tvcnr3').val(data[i].tvcnr3); jq('#tvcnr4').val(data[i].tvcnr4);
				jq('#tvcnr5').val(data[i].tvcnr5);
				 
				
				
				//jq('#tvdty_readonly').val(data[i].tvdty);jq('#tvdref_readonly').val(data[i].tvdref);
				//jq('#tvdsk_readonly').val(data[i].tvdsk);jq('#tvdo').val(data[i].tvdo);
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


	
	//ChildWindow Country Codes
    function triggerChildWindowCountryCodes(record){
    	var idLink = record.id;
    	var id = idLink.replace("IdLink", "");
    	jq(idLink).attr('target','_blank');
    	window.open('nctsimport_edit_items_childwindow_generalcodes.do?action=doInit&type=GCY&ctype=' + id , "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    }
    
  //ChildWindow Language Codes
    function triggerChildWindowLanguageCodes(record){
    	var idLink = record.id;
    	var id = idLink.replace("IdLink", "");
    	jq(idLink).attr('target','_blank');
    	window.open('nctsimport_edit_items_childwindow_generalcodes.do?action=doInit&type=012&ctype=' + id , "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    }
	
	

		


  	
	