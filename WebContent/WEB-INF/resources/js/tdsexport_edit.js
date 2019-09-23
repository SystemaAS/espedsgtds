	//===========================================
	//General functions for this JSP side - AJAX
	//===========================================
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	
  	//Overlay on tab (to mark visually a delay...)
    jq(function() {
    	jq('#alinkMainList').click(function() { 
      		setBlockUI();
      	  });	
  	  jq('#alinkInvoices').click(function() { 
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
  	  jq('#getInvoiceListaSumButton').click(function() { 
  		if(jq('#invoiceListTotSum').val()!='' && jq('#invoiceListTotValidCurrency').val()!='' ){    
  			jq('#sveh_fabl').val(jq('#invoiceListTotSum').val());
  			jq('#sveh_vakd').val(jq('#invoiceListTotValidCurrency').val());	
  			jq('#sveh_vaku').val(jq('#invoiceListTotKurs').val());
  		}
	  });
  	  
    });
    
    jq(function() {
    	  //----------------------------------------------------------------------
      	  //including timepicker (jQuery addon to datepicker). Ref to parent JSP.
      	  //----------------------------------------------------------------------
      	  jq("#sveh_beat").datetimepicker({ 
      		  dateFormat: 'yymmdd',
    		  controlType: 'select',
    		  timeFormat: 'HHmm'  
    	  });
      	//no space characters allowed...
      	  jq('#sveh_beat').change(function() {
      		var val = jq("#sveh_beat").val();
      		jq("#sveh_beat").val(val.replace(' ', ''));
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
  		  setBlockUI();
		  jq("#sumOfInvoiceAmountInItemLines").val(jq("#sumOfInvoiceAmountInItemLines").val().replace(',', '.'));
	  	});
  		jq( "#submit2" ).click(function( event ) {
    		  setBlockUI();
  		  jq("#sumOfInvoiceAmountInItemLines").val(jq("#sumOfInvoiceAmountInItemLines").val().replace(',', '.'));
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
					//
					jq('#sveh_omha').val(data[i].svea_omha);
					jq('#sveh_dek1').val(data[i].sveh_dek1);
					jq('#sveh_dek2').val(data[i].sveh_dek2);
					jq('#sveh_tart').val(data[i].sveh_tart);
					jq('#sveh_mtyp').val(data[i].sveh_mtyp);
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
	    	if(jq('#sveh_avna').val()==''){
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
	    	if(jq('#sveh_mona').val()==''){
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
	    	if(jq('#sveh_dkna').val()==''){
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
	  
	  
	//-------------------------------------------
	  //START Model dialog: "Update proforma"
	  //-------------------------------------------
	  //Initialize <div> here
	  jq(function() { 
		  jq("#dialogUpdateProforma").dialog({
			  autoOpen: false,
			  maxWidth:5500,
	          maxHeight: 500,
	          width: 400,
	          height: 300,
			  modal: true
		  });
	  });
	  //Present dialog box onClick (href in parent JSP)
	  jq(function() {
		  jq("#updateProformaLink").click(function() {
			  //setters (add more if needed)
			  jq('#dialogUpdateProforma').dialog( "option", "title", "Uppdatera ärende" );
			  
			  //deal with buttons for this modal window
			  jq('#dialogUpdateProforma').dialog({
				 buttons: [ 
		            {
					 id: "dialogSaveTU",	
					 text: "Ok",
					 click: function(){
						 		jq('#updateProformaForm').submit();
					 		}
				 	 },
		 	 		{
				 	 id: "dialogCancelTU",
				 	 text: "Cancel", 
					 click: function(){
						 		//back to initial state of form elements on modal dialog
						 		//jq("#dialogSaveTU").button("option", "disabled", true);
						 		jq( this ).dialog( "close" ); 
					 		} 
		 	 		 } ] 
			  });
			  //init values
			  //jq("#dialogSaveTU").button("option", "disabled", true);
			  //open now
			  jq('#dialogUpdateProforma').dialog('open');
		  });
	  });
	  //-------------------------------------------
	  //END Model dialog: "Update proforma"
	  //-------------------------------------------
	
	    

	//----------------------------------------------
	  //START Model dialog: "Print delere (skilleark)"
	  //----------------------------------------------
	  //Initialize <div> here
	  jq(function() { 
		  jq("#dialogPrintSkilleArk").dialog({
			  autoOpen: false,
			  maxWidth:400,
	          maxHeight: 300,
	          width: 280,
	          height: 180,
			  modal: true
		  });
	  });
	  //----------------------------
	  //Present dialog box onClick 
	  //----------------------------
	  jq(function() {
		  jq("#printSkilleArkImg").click(function() {
			  console.log("AAA");
			  presentPrintSkilleArkDialog();
		  });
		  
	  });
	  function presentPrintSkilleArkDialog(){
		//setters (add more if needed)
		  jq('#dialogPrintSkilleArk').dialog( "option", "title", "Print Försättsblad" );
		  //deal with buttons for this modal window
		  jq('#dialogPrintSkilleArk').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Fortsätt",
				 click: function(){
					 		jq('#skilleArkForm').submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU",
			 	 text: "Avbryt", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSaveTU").button("option", "disabled", true);
					 		jq("#selectedType").val("");
					 		jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  jq("#dialogSaveTU").button("option", "disabled", true);
		  //open now
		  jq('#dialogPrintSkilleArk').dialog('open');
	  }
	  //Events for the drop downs (some kind of "implicit validation" since all drop downs are mandatory)
	  jq(function() {
		  jq("#selectedType").change(function() {
			  if(jq("#selectedType").val()!=''){
				  jq("#dialogSaveTU").button("option", "disabled", false);
				  
			  }else{
				  jq("#dialogSaveTU").button("option", "disabled", true);
			  }
		  });
		  
	  });
	  //-------------------------------------------
	  //END Model dialog: "Print skilleark"
	  //-------------------------------------------
	
	
	//-------------------------------------------
	  //START Model dialog: "File upload"
	  //-------------------------------------------
	  //Initialize <div> here
	  jq(function() { 
		  jq("#dialogUploadArchiveDocument").dialog({
			  autoOpen: false,
			  maxWidth:400,
	          maxHeight: 300,
	          width: 400,
	          height: 300,
			  modal: true
		  });
	  });
	  //----------------------------
	  //Present dialog box onClick 
	  //----------------------------
	  jq(function() {
		  jq("#uploadFileImg").click(function() {
			  presentUploadFileDialog();
		  });
		  
	  });
	  function presentUploadFileDialog(){
		//setters (add more if needed)
		  jq('#dialogUploadArchiveDocument').dialog( "option", "title", "Upload dokument" );
		  //deal with buttons for this modal window
		  jq('#dialogUploadArchiveDocument').dialog({
			 buttons: [ 
			     /* N/A (look at file-change event instead     
	            {
	             	
				 id: "dialogSaveTU",	
				 text: "Ok",
				 click: function(){
					 		jq('#uploadFileForm').submit();
				 		}
			 	 },*/
	 	 		{
			 	 id: "dialogCancelTU",
			 	 text: "Avbryt", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		//jq("#dialogSaveTU").button("option", "disabled", true);
					 		//jq("#wstype").val("");
					 		jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  //jq("#dialogSaveTU").button("option", "disabled", false);
		  //open now
		  jq('#dialogUploadArchiveDocument').dialog('open');
	  }
	  
	  //Events for the drop downs (some kind of "implicit validation" since all drop downs are mandatory)
	  jq(function() {
		  jq("#fileUpload").change(function() {
			  uploadFile();
		  });
		  
	  });
	  //Upload file
	  function uploadFile(){
			//grab all form data  
			  var form = new FormData(document.getElementById('uploadFileForm'));
			  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
			  
			  jq.ajax({
			  	  type: 'POST',
			  	  url: 'uploadFileToArchive.do',
			  	  data: form,  
			  	  dataType: 'text',
			  	  cache: false,
			  	  processData: false,
			  	  contentType: false,
		  		  success: function(data) {
				  	  var len = data.length;
			  		  if(len>0){
			  			jq("#file").val("");
					  	//Check for errors or successfully processed
					  	var exists = data.indexOf("ERROR");
					  	if(exists>0){
					  		//ERROR on back-end
					  		jq("#file").addClass( "isa_error" );
					  		jq("#file").removeClass( "isa_success" );
					  	}else{
					  		//OK
					  		jq("#file").addClass( "isa_success" );
					  		jq("#file").removeClass( "isa_error" );
					  	}
					  	//response to end user 
					  	alert(data);
					  	if(data.indexOf('[OK') == 0) {
						  	var trip = '';
						  	var avd = jq("#wsavd").val();
						  	var opd = jq("#wsopd").val();
						  	var sign = jq("#sign").val();
						  	//reload
						  	window.location = "tdsexport_edit.do?action=doFetch&avd=" + avd + "&opd=" + opd + "&sysg=" +  sign;
					  	}
					  	//unblock
					  	jq.unblockUI();
			  		  }
			  	  }, 
			  	  error: function() {
			  		  jq.unblockUI();
			  		  alert('Error loading ...');
			  		  jq("#file").val("");
			  		  //cosmetics
			  		  jq("#file").addClass( "isa_error" );
			  		  jq("#file").removeClass( "isa_success" );
				  }
			  });
			    
			  
		  }
	  
	  //-------------------------------------------
	  //END Model dialog: "File upload"
	  //-------------------------------------------
	  
	 
	 jq(document).ready(function(){
  	    jq(this).scrollTop(0); //needed for Chrome (bug)
  	    //checkbox proforma
  	    if (jq("#updateProformaCheckbox").is(':checked')){
	        jq("#updateProformaIcon").show();
	    }else{
	    	jq("#updateProformaIcon").hide();
	    }
  	    
  	    //aspects/functions on ready document...
  		if(jq('#imgRedFlagAntalKolliInItems').length || jq('#imgRedFlagSumGrossWeightInItems').length || jq('#imgSumOfInvoiceAmountInItemLines').length){
  			jq('#submitRedFlag').css('display', 'inline-block');	
  		}else{
  			jq('#submitRedFlag').css('display', 'none');
  		}
  		
  		
	   //to prevent hiding datepicker behind the autocomplete function
	   jq('.datepicker').on('click', function(e) {
		   e.preventDefault();
		   jq(this).attr("autocomplete", "off");  
	   });
  
  		
  	        
  	  });
	  
	  	//checkbox proforma
	  	jq(function() {
	  		jq('#updateProformaCheckbox').change(function() {
		    	if (jq(this).prop('checked')){
		    		jq('#updateProformaIcon').show();
		    		jq('#currentCheckboxProforma').val("1");
	    		}else{
	    			jq('#updateProformaIcon').hide();
	    			//set fallbacks back
	    			/*
	    			jq('#dkeh_godt').val(jq('#dkeh_godt_dummy').val());
	    			*/
	    		}
		    });
		    
		    
		    
		});
	
	
	
