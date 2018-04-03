	//===========================================
	//General functions for this JSP side - AJAX
	//===========================================
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  	//Overlay on tab (to mark visually a delay...)
    jq(function() {
  	  jq('#alinkInvoices').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
  	  jq('#alinkItemLines').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });
  	  jq('#alinkLogging').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });
  	  jq('#alinkArchive').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });
  	  jq('#getInvoiceListaSumButton').click(function() { 
  		if(jq('#invoiceListTotSum').val()!='' && jq('#invoiceListTotValidCurrency').val()!='' ){    
  			jq('#sveh_fabl').val(jq('#invoiceListTotSum').val());
  			jq('#sveh_vakd').val(jq('#invoiceListTotValidCurrency').val());	
  			jq('#sveh_vaku').val(jq('#invoiceListTotKurs').val());
  		}
	  });
  	  
    });
    
  	//-----------------------------------------------------------------------------
  	//jQuery CALCULATOR (related to jquery.calculator.js and jquery.calculator.css
  	//-----------------------------------------------------------------------------
  	jq(function() {
  		if (!jq("#sveh_fabl").attr("readonly")){
  			jq('#sveh_fabl').calculator({ showOn: 'button',  
  				buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
  		}
  	});
  	
  	//General functions
  	jq(function() {
  		jq( "#submit" ).click(function( event ) {
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
	  	});
  		
  		//=====================================
	  	//START Child window for general codes
	  	//=====================================
  		//Sender - Landkod
	    jq('#sveh_avlkIdLink').click(function() {
	    	jq('#sveh_avlkIdLink').attr('target','_blank');
	    	window.open('tdsexport_edit_childwindow_generalcodes.do?action=doInit&type=GCY&ctype=sveh_avlk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sveh_avlkIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sveh_avlkIdLink').click();
			}
	    });
	    //Receiver - Landkod
	    jq('#sveh_molkIdLink').click(function() {
	    	jq('#sveh_molkIdLink').attr('target','_blank');
	    	window.open('tdsexport_edit_childwindow_generalcodes.do?action=doInit&type=GCY&ctype=sveh_molk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sveh_molkIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sveh_molkIdLink').click();
			}
	    });
	    //Valutakod
	    jq('#sveh_vakdIdLink').click(function() {
	    	jq('#sveh_vakdIdLink').attr('target','_blank');
	    	window.open('tdsexport_edit_childwindow_generalcodes.do?action=doInit&type=MDX&ctype=sveh_vakd', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sveh_vakdIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sveh_vakdIdLink').click();
			}
	    });
	    //Valutakod Frakt
	    jq('#sveh_vuvaIdLink').click(function() {
	    	jq('#sveh_vuvaIdLink').attr('target','_blank');
	    	window.open('tdsexport_edit_childwindow_generalcodes.do?action=doInit&type=MDX&ctype=sveh_vuva', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sveh_vuvaIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sveh_vuvaIdLink').click();
			}
	    });
	    //Deklarant - Landkod
	    jq('#sveh_dklkIdLink').click(function() {
	    	jq('#sveh_dklkIdLink').attr('target','_blank');
	    	window.open('tdsexport_edit_childwindow_generalcodes.do?action=doInit&type=GCY&ctype=sveh_dklk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sveh_dklkIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sveh_dklkIdLink').click();
			}
	    });
	    //Exportland - Landkod
	    jq('#sveh_avutIdLink').click(function() {
	    	jq('#sveh_avutIdLink').attr('target','_blank');
	    	window.open('tdsexport_edit_childwindow_generalcodes.do?action=doInit&type=GCY&ctype=sveh_avut', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sveh_avutIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sveh_avutIdLink').click();
			}
	    });
	    //Bestämmelseland
	    jq('#sveh_aubeIdLink').click(function() {
	    	jq('#sveh_aubeIdLink').attr('target','_blank');
	    	window.open('tdsexport_edit_childwindow_generalcodes.do?action=doInit&type=GCY&ctype=sveh_aube', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sveh_aubeIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sveh_aubeIdLink').click();
			}
	    });
	    //Vid avgång - Landkod
	    jq('#sveh_trlkIdLink').click(function() {
	    	jq('#sveh_trlkIdLink').attr('target','_blank');
	    	window.open('tdsexport_edit_childwindow_generalcodes.do?action=doInit&type=GCY&ctype=sveh_trlk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sveh_trlkIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sveh_trlkIdLink').click();
			}
	    });
	    //Vid gränsen - Landkod
	    jq('#sveh_tralIdLink').click(function() {
	    	jq('#sveh_tralIdLink').attr('target','_blank');
	    	window.open('tdsexport_edit_childwindow_generalcodes.do?action=doInit&type=GCY&ctype=sveh_tral', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sveh_tralIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sveh_tralIdLink').click();
			}
	    });
	    //Utfartstullkontor
  	  	jq('#sveh_utfaIdLink').click(function() {
  	  		jq('#sveh_utfaIdLink').attr('target','_blank');
  	  		window.open('tdsexport_edit_childwindow_tullkontor.do?action=doInit&ctype=sveh_utfa&tkkode=' + jq('#sveh_utfa').val(), "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  	  	});
  	  	jq('#sveh_utfaIdLink').keypress(function(e){ //extra feature for the end user
  	  		if(e.which == 13) {
  	  			jq('#sveh_utfaIdLink').click();
  	  		}
  	  	});
  	  	
  	  	//Customer SENDER
  	  	jq('#sveh_avnaIdLink').click(function() {
  	  		jq('#sveh_avnaIdLink').attr('target','_blank');
  	  		window.open('tds_childwindow_customer.do?action=doFind&sonavn=' + jq('#sveh_avna').val() + '&ctype=sveh_avna', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  	  	});
  	  	jq('#sveh_avnaIdLink').keypress(function(e){ //extra feature for the end user
  	  		if(e.which == 13) {
  	  			jq('#sveh_avnaIdLink').click();
  	  		}
  	  	});
  	  	//Customer RECEIVER
  	  	jq('#sveh_monaIdLink').click(function() {
  	  		jq('#sveh_monaIdLink').attr('target','_blank');
  	  		window.open('tds_childwindow_customer.do?action=doFind&sonavn=' + jq('#sveh_mona').val() + '&ctype=sveh_mona', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  	  	});
  	  	jq('#sveh_monaIdLink').keypress(function(e){ //extra feature for the end user
  	  		if(e.which == 13) {
  	  			jq('#sveh_monaIdLink').click();
  	  		}
  	  	});
  	  	//Customer DEKLARANT
  	  	jq('#sveh_dknaIdLink').click(function() {
  	  		jq('#sveh_dknaIdLink').attr('target','_blank');
  	  		window.open('tds_childwindow_customer.do?action=doFind&sonavn=' + jq('#sveh_dkna').val() + '&ctype=sveh_dkna', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  	  	});
  	  	jq('#sveh_dknaIdLink').keypress(function(e){ //extra feature for the end user
  	  		if(e.which == 13) {
  	  			jq('#sveh_dknaIdLink').click();
  	  		}
  	  	});
	    
	    
	    //=====================================
	  	//END Child window for general codes
	  	//=====================================
  	});
  	
  	jq(function() { 
	    jq('#godsnrButton').click(function() {
	    	var ok= confirm('Är du säker att du vill uppdatera?');
	    	if(ok){
	    		var avd = jq("#sveh_syav").val() ;
		    	var opd = jq("#sveh_syop").val() ;
		    	var godnr = jq("#sveh_godn").val() ;
		    	if(avd!='' && opd!='' && godnr!=''){
			    	var requestParams = "&avd=" + avd + "&opd=" + opd +"&godn=" + godnr;
					jq.getJSON('updateGodsnrOnItemLinesExport.do', {
						applicationUser : jq('#applicationUser').val(),
						requestParams : requestParams,
						ajax : 'true'
					}, function(data) {
						//alert("Hello");
						var len = data.length;
						for ( var i = 0; i < len; i++) {
							if(data[i].errMsg!=''){
								alert(errMsg);
								jq("#sveh_godn").addClass( "isa_error" );
								jq("#sveh_godn").removeClass( "isa_success" );
							}else{
								jq("#sveh_godn").addClass( "isa_success" );
								jq("#sveh_godn").removeClass( "isa_error" );
							}
						}
					});
		    	}
	    	}
	    });
	});
  	
  	//onChange avd list
	jq(function() { 
	    jq('#avd').change(function() {
    			jq.getJSON('getSpecificTopicOmbud_TdsExport.do', {
				applicationUser : jq('#applicationUser').val(),
				avd : jq('#avd').val(),
				ajax : 'true'
			}, function(data) {
				//alert("Hello");
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#sveh_omeo').val(data[i].svea_omeo);
					jq('#sveh_omty').val(data[i].svea_omty);
				}
			});
	    });
	});

  	//onChange sign list
	jq(function() { 
	    jq('#sign').change(function() {
	    		jq.getJSON('getSignatureName_TdsExport.do', {
					applicationUser : jq('#applicationUser').val(),
					avd : jq('#avd').val(),
					sign : jq('#sign').val(),
					ajax : 'true'
				}, function(data) {
					//alert("Hello");
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						//alert(data[i].svth_tlf);
						jq('#sveh_omha').val(data[i].svth_namn);
						jq('#sveh_omtl').val(data[i].svth_tlf);
					}
				});
		    });
	});
	
	
	//-----------------------
  	//INIT CUSTOMER Object
  	//-----------------------
  	var map = {};
  	//init the customer object in javascript (will be put into a map)
  	var customer = new Object();
  	//fields
  	customer.kundnr = "";customer.knavn = "";customer.eori = "";customer.adr1 = "";
  	customer.adr2 = "";customer.adr3 = "";customer.postnr = "";customer.syland = "";
  	customer.kpers = "";customer.tlf = "";
  	//---------------------------------------------------------
  	//FETCH CUSTOMER from SENDER [AVSÄNDARE-EXPORTÖR] html area
  	//---------------------------------------------------------
	function searchSenderOwnWindow() {
		jq(function() {
			jq.getJSON('searchCustomer.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : jq('#search_sveh_avna').val(),
				customerNumber : jq('#search_sveh_avkn').val(),
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
		  jq('#sveh_avna').val("");
		  jq('#sveh_aveo').val("");
		  jq('#sveh_ava1').val("");
		  jq('#sveh_ava2').val("");
		  jq('#sveh_avpn').val("");
		  jq('#sveh_avpa').val("");
		  jq('#sveh_avlk').val("");
		  jq('#sveh_avha').val("");
		  jq('#sveh_avtl').val("");
		  //now populate (if applicable)
		  var key = jq('#senderList').val();
		  jq('#sveh_avkn').val(key);
		  customer = map[key];
		  //alert(customer.knavn);
		  jq('#sveh_avna').val(customer.knavn);
		  jq('#sveh_aveo').val(customer.eori);
		  jq('#sveh_ava1').val(customer.adr1);
		  jq('#sveh_ava2').val(customer.adr2);
		  jq('#sveh_avpn').val(customer.postnr);
		  jq('#sveh_avpa').val(customer.adr3);
		  jq('#sveh_avlk').val(customer.syland);
		  jq('#sveh_avha').val(customer.kpers);
		  jq('#sveh_avtl').val(customer.tlf);
	    });
	});
	
	//onClick for Sender dialog
	jq(function() { 
	    jq('#searchCustomerCloseCancel').click(function() {
	      //rescue the original fields
	      jq('#sveh_avkn').val(jq("#orig_sveh_avkn").val());	
		  jq('#sveh_avna').val(jq("#orig_sveh_avna").val());
		  jq('#sveh_aveo').val(jq("#orig_sveh_aveo").val());
		  jq('#sveh_ava1').val(jq("#orig_sveh_ava1").val());
		  jq('#sveh_ava2').val(jq("#orig_sveh_ava2").val());
		  jq('#sveh_avpn').val(jq("#orig_sveh_avpn").val());
		  jq('#sveh_avpa').val(jq("#orig_sveh_avpa").val());
		  jq('#sveh_avlk').val(jq("#orig_sveh_avlk").val());
		  jq('#sveh_avha').val(jq("#orig_sveh_avha").val());
		  jq('#sveh_avtl').val(jq("#orig_sveh_avtl").val());
	    });
	});
	
	//----------------------------------
	//Events Sender (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgCustomerSearch').click(function(){
    			jq("#search_sveh_avkn").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_avkn').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchSenderOwnWindow);
			}			
    		});
		jq('#search_sveh_avna').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchSenderOwnWindow);
			}			
    		});
	});

	
	//--------------------------------------------------------------------------------------
	//Extra behavior for Customer number ( without using (choose from list) extra roundtrip)
	//--------------------------------------------------------------------------------------
	jq(function() { 
	    jq('#sveh_avkn').blur(function() {
	    		var avknValue = jq('#sveh_avkn').val();
	    		if(avknValue!=null && avknValue!=""){
		    		jq.getJSON('searchCustomer.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : jq('#sveh_avkn').val(),
					ajax : 'true'
				}, function(data) {
					//alert("Hello");
					var len = data.length;
					for ( var i = 0; i < len; i++) {
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
						
					}
					if(len > 0){
					  	jq('#sveh_avkn').val(customer.kundnr);
					  	jq('#sveh_avna').val(customer.knavn);
					  	jq('#sveh_aveo').val(customer.eori);
					  	jq('#sveh_ava1').val(customer.adr1);
					  	jq('#sveh_ava2').val(customer.adr2);
					  	jq('#sveh_avpn').val(customer.postnr);
					  	jq('#sveh_avpa').val(customer.adr3);
					  	jq('#sveh_avlk').val(customer.syland);
					  	jq('#sveh_avha').val(customer.kpers);
					  	jq('#sveh_avtl').val(customer.tlf);
					  	
					}else{
						//init fields
						jq('#sveh_avna').val("");
					  	jq('#sveh_aveo').val("");
					  	jq('#sveh_ava1').val("");
					  	jq('#sveh_ava2').val("");
					  	jq('#sveh_avpn').val("");
					  	jq('#sveh_avpa').val("");
					  	jq('#sveh_avlk').val("");
					  	jq('#sveh_avha').val("");
					  	jq('#sveh_avtl').val("");
					}
				});
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
				customerName : jq('#search_sveh_mona').val(),
				customerNumber : jq('#search_sveh_mokn').val(),
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
		  jq('#sveh_mona').val("");
		  jq('#sveh_moeo').val("");
		  jq('#sveh_moa1').val("");
		  jq('#sveh_moa2').val("");
		  jq('#sveh_mopn').val("");
		  jq('#sveh_mopa').val("");
		  jq('#sveh_molk').val("");	
		  //now populate (if applicable)
		  var key = jq('#receiverList').val();
		  jq('#sveh_mokn').val(key);
		  customer = map[key];
		  jq('#sveh_moeo').val(customer.eori);
		  jq('#sveh_mona').val(customer.knavn);
		  jq('#sveh_moa1').val(customer.adr1);
		  jq('#sveh_moa2').val(customer.adr2);
		  jq('#sveh_mopn').val(customer.postnr);
		  jq('#sveh_mopa').val(customer.adr3);
		  jq('#sveh_molk').val(customer.syland);			  
		});
	});
	//onClick for Receiver(Mottagare) dialog
	jq(function() { 
	    jq('#searchCustomer10CloseCancel').click(function() {
	      //rescue the original fields
	      jq('#sveh_mokn').val(jq("#orig_sveh_mokn").val());	
		  jq('#sveh_mona').val(jq("#orig_sveh_mona").val());
		  jq('#sveh_moeo').val(jq("#orig_sveh_moeo").val());
		  jq('#sveh_moa1').val(jq("#orig_sveh_moa1").val());
		  jq('#sveh_moa2').val(jq("#orig_sveh_moa2").val());
		  jq('#sveh_mopn').val(jq("#orig_sveh_mopn").val());
		  jq('#sveh_mopa').val(jq("#orig_sveh_mopa").val());
		  jq('#sveh_molk').val(jq("#orig_sveh_molk").val());
		  
	    });
	});
	
	//----------------------------------
	//Events Receiver (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgReceiverSearch').click(function(){
    			jq("#search_sveh_mokn").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_mokn').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchReceiverOwnWindow);
			}			
    		});
		jq('#search_sveh_mona').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchReceiverOwnWindow);
			}			
    		});
	});

	//--------------------------------------------------------------------------------------
	//Extra behavior for Customer number ( without using (choose from list) extra roundtrip)
	//--------------------------------------------------------------------------------------
	jq(function() { 
	    jq('#sveh_mokn').blur(function() {
	    		var moknValue = jq('#sveh_mokn').val();
	    		if(moknValue!=null && moknValue!=""){
		    		jq.getJSON('searchCustomer.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : jq('#sveh_mokn').val(),
					ajax : 'true'
				}, function(data) {
					//alert("Hello");
					var len = data.length;
					for ( var i = 0; i < len; i++) {
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
						
					}
					if(len > 0){
						jq('#sveh_mokn').val(customer.kundnr);
					  	jq('#sveh_moeo').val(customer.eori);
					  	jq('#sveh_mona').val(customer.knavn);
					  	jq('#sveh_moa1').val(customer.adr1);
					  	jq('#sveh_moa2').val(customer.adr2);
					  	jq('#sveh_mopn').val(customer.postnr);
					  	jq('#sveh_mopa').val(customer.adr3);
					  	jq('#sveh_molk').val(customer.syland);			  

					}else{
						//init fields
						jq('#sveh_moeo').val("");
					  	jq('#sveh_mona').val("");
					  	jq('#sveh_moa1').val("");
					  	jq('#sveh_moa2').val("");
					  	jq('#sveh_mopn').val("");
					  	jq('#sveh_mopa').val("");
					  	jq('#sveh_molk').val("");			  
					}
				});
	    		}
		});
	});
	
	
  	//-----------------------------------------
	//FETCH CUSTOMER from DEKLARANT html area
  	//-----------------------------------------
	function searchDeklarantOwnWindow() {
		jq(function() {
			jq.getJSON('searchCustomer.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : jq('#search_sveh_dkna').val(),
				customerNumber : jq('#search_sveh_dkkn').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
					customer = new Object();
					customer.kundnr = data[i].kundnr;
					customer.knavn = data[i].knavn;
					customer.adr1 = data[i].adr1;
					customer.adr2 = data[i].adr2;
					customer.adr3 = data[i].adr3;
					customer.postnr = data[i].postnr;
					customer.kpers = data[i].kpers;
					
					//put the object in map now with customerNumber as key
					map[customer.kundnr] = customer;
				}
				//now that we have our options, give them to our select
				jq('#deklarantList').html(html);
			});
		});
	}
	//Sets deklarant values after user selection
	jq(function() { 
	    jq('#deklarantList').change(function() {
	      //init fields
		  jq('#sveh_dkna').val("");
		  jq('#sveh_dkeo').val("");
		  jq('#sveh_dka1').val("");
		  jq('#sveh_dka2').val("");
		  jq('#sveh_dkpn').val("");
		  jq('#sveh_dkpa').val("");
		  jq('#sveh_dklk').val("");
		  
		  //now populate (if applicable)
		  var key = jq('#deklarantList').val();
		  jq('#sveh_dkkn').val(key);
		  customer = map[key];
		  jq('#sveh_dkna').val(customer.knavn);
		  jq('#sveh_dkeo').val(customer.eori);
		  jq('#sveh_dka1').val(customer.adr1);
		  jq('#sveh_dka2').val(customer.adr2);
		  jq('#sveh_dkpn').val(customer.postnr);
		  jq('#sveh_dkpa').val(customer.adr3);
		  jq('#sveh_dklk').val(customer.syland);			  
		});
	});
	//onClick for Deklarant dialog
	jq(function() { 
	    jq('#searchCustomer20CloseCancel').click(function() {
	      //rescue the original fields
	      jq('#sveh_dkkn').val(jq("#orig_sveh_dkkn").val());	
		  jq('#sveh_dkna').val(jq("#orig_sveh_dkna").val());
		  jq('#sveh_dkeo').val(jq("#orig_sveh_dkeo").val());
		  jq('#sveh_dka1').val(jq("#orig_sveh_dka1").val());
		  jq('#sveh_dka2').val(jq("#orig_sveh_dka2").val());
		  jq('#sveh_dkpn').val(jq("#orig_sveh_dkpn").val());
		  jq('#sveh_dkpa').val(jq("#orig_sveh_dkpa").val());
		  jq('#sveh_dklk').val(jq("#orig_sveh_dklk").val());
		  
	    });
	});
	
	//----------------------------------
	//Events Deklarant (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgDeklarantSearch').click(function(){
    			jq("#search_sveh_dkkn").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_dkkn').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchDeklarantOwnWindow);
			}			
    		});
		jq('#search_sveh_dkna').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchDeklarantOwnWindow);
			}			
    		});
	});

	
	//--------------------------------------------------------------------------------------
	//Extra behavior for Customer number ( without using (choose from list) extra roundtrip)
	//--------------------------------------------------------------------------------------
	jq(function() { 
	    jq('#sveh_dkkn').blur(function() {
	    		var dkknValue = jq('#sveh_dkkn').val();
	    		if(dkknValue!=null && dkknValue!=""){
		    		jq.getJSON('searchCustomer.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : jq('#sveh_dkkn').val(),
					ajax : 'true'
				}, function(data) {
					//alert("Hello");
					var len = data.length;
					for ( var i = 0; i < len; i++) {
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
						
					}
					if(len > 0){
						jq('#sveh_dkkn').val(customer.kundnr);
						jq('#sveh_dkna').val(customer.knavn);
						jq('#sveh_dkeo').val(customer.eori);
						jq('#sveh_dka1').val(customer.adr1);
						jq('#sveh_dka2').val(customer.adr2);
						jq('#sveh_dkpn').val(customer.postnr);
						jq('#sveh_dkpa').val(customer.adr3);
						jq('#sveh_dklk').val(customer.syland);
						
					}else{
						//init fields
						jq('#sveh_dkna').val("");
						jq('#sveh_dkeo').val("");	
						jq('#sveh_dka1').val("");
						jq('#sveh_dka2').val("");
						jq('#sveh_dkpn').val("");
						jq('#sveh_dkpa').val("");
						jq('#sveh_dklk').val("");					}
				});
	    		}
		});
	});
	
	

	//Currency AJAX fetch
	jq(function() { 
	    jq('#sveh_vakd').change(function() {
	    	//alert('Hej');
	    	//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('getCurrencyRate.do', {
				applicationUser : jq('#applicationUser').val(),
				currencyCode : jq('#sveh_vakd').val(),
				ajax : 'true'
			}, function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					//data[i].svvk_krs;
					//data[i].svvs_omr;
					jq('#sveh_vaku').val(data[i].svvk_krs);
					jq('#sveh_vaom').val(data[i].svvs_omr);
				}
				
			});
	    });
	    jq('#sveh_vuva').change(function() {
	    	//alert('Hej');
	    	//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('getCurrencyRate.do', {
				applicationUser : jq('#applicationUser').val(),
				currencyCode : jq('#sveh_vuva').val(),
				ajax : 'true'
			}, function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					//data[i].svvk_krs;
					//data[i].svvs_omr;
					jq('#sveh_vuku').val(data[i].svvk_krs);
					//jq('#sveh_?').val(data[i].svvs_omr);
				}
				
			});
	    });
	});
	jq(function() { 
	    jq('#sveh_vakd').blur(function() {
	    	if(jq('#sveh_vaku').val()==''){
				jq.getJSON('getCurrencyRate.do', {
					applicationUser : jq('#applicationUser').val(),
					currencyCode : jq('#sveh_vakd').val(),
					ajax : 'true'
				}, function(data) {
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						//data[i].svvk_krs;
						//data[i].svvs_omr;
						jq('#sveh_vaku').val(data[i].svvk_krs);
						jq('#sveh_vaom').val(data[i].svvs_omr);
					}
				});
	    	}
	    });
	});

	//---------------
	//Tullkontor list
	//---------------
	//onChange
	jq(function() { 
	    jq('#tullkontorList').change(function() {
		    	jq('#sveh_utfa').val(""); //tds export
		    	//now populate (if applicable)
		    	var key = jq('#tullkontorList').val();
		    	jq('#sveh_utfa').val(key); //tds export
	    });
	});
	//On Keypress (13)
	jq(function() { 
	    jq('#tullkontorList').keypress(function() {
		    	if(e.which == 13) {
				//alert("hej till publiken");
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#sveh_utfa').val(""); //tds export
				//now populate (if applicable)
			    	var key = jq('#tullkontorList').val();
			    	jq('#sveh_utfa').val(key); //tds export
		    	}
	    });
	    
	});
	
	//----------------------------------
	//Events Tullkontor (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgUtfartstullKontor').click(function(){
    			jq("#search_sveh_utfa").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_utfa').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTullkontorOwnWindow);
			}			
    		});
		jq('#search_sveh_utfa_Code').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTullkontorOwnWindow);
			}			
    		});
	});
	
	
	  //-------------------------------------------
	  //START Model dialog ADMIN: "Update status"
	  //-------------------------------------------
	  //Initialize <div> here
	  jq(function() { 
		  jq("#dialogUpdateStatus").dialog({
			  autoOpen: false,
			  maxWidth:500,
	          maxHeight: 400,
	          width: 280,
	          height: 220,
			  modal: true
		  });
	  });
	  //Present dialog box onClick (href in parent JSP)
	  jq(function() {
		  jq("#updateStatusLink").click(function() {
			  //setters (add more if needed)
			  jq('#dialogUpdateStatus').dialog( "option", "title", "Update Status" );
			  
			  //deal with buttons for this modal window
			  jq('#dialogUpdateStatus').dialog({
				 buttons: [ 
		            {
					 id: "dialogSaveTU",	
					 text: "Ok",
					 click: function(){
						 		jq('#updateStatusForm').submit();
					 		}
				 	 },
		 	 		{
				 	 id: "dialogCancelTU",
				 	 text: "Cancel", 
					 click: function(){
						 		//back to initial state of form elements on modal dialog
						 		jq("#dialogSaveSU").button("option", "disabled", true);
						 		jq("#selectedStatus").val("");
						 		jq( this ).dialog( "close" ); 
					 		} 
		 	 		 } ] 
			  });
			  //init values
			  jq("#dialogSaveSU").button("option", "disabled", true);
			  //open now
			  jq('#dialogUpdateStatus').dialog('open');
		  });
	  });

	
	
	
	
	
	
