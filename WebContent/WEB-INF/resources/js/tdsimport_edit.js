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
  			jq('#svih_fabl').val(jq('#invoiceListTotSum').val());
  			jq('#svih_vakd').val(jq('#invoiceListTotValidCurrency').val());	
  			jq('#svih_vaku').val(jq('#invoiceListTotKurs').val());
  		}
	  });
    });
  
    
	
  	//-----------------------------------------------------------------------------
  	//jQuery CALCULATOR (related to jquery.calculator.js and jquery.calculator.css
  	//-----------------------------------------------------------------------------
  	jq(function() {
  		if (!jq("#svih_fabl").attr("readonly")){
	  		jq('#svih_fabl').calculator({ showOn: 'button',  
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
	    jq('#svih_avlkIdLink').click(function() {
	    	jq('#svih_avlkIdLink').attr('target','_blank');
	    	window.open('tdsimport_edit_childwindow_generalcodes.do?action=doInit&type=GCY&ctype=svih_avlk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svih_avlkIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svih_avlkIdLink').click();
			}
	    });
	    //Receiver - Landkod
	    jq('#svih_molkIdLink').click(function() {
	    	jq('#svih_molkIdLink').attr('target','_blank');
	    	window.open('tdsimport_edit_childwindow_generalcodes.do?action=doInit&type=GCY&ctype=svih_molk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svih_molkIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svih_molkIdLink').click();
			}
	    });
	    //Valutakod
	    jq('#svih_vakdIdLink').click(function() {
	    	jq('#svih_vakdIdLink').attr('target','_blank');
	    	window.open('tdsimport_edit_childwindow_generalcodes.do?action=doInit&type=MDX&ctype=svih_vakd', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svih_vakdIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svih_vakdIdLink').click();
			}
	    });
	    
	    //Deklarant - Landkod
	    jq('#svih_dklkIdLink').click(function() {
	    	jq('#svih_dklkIdLink').attr('target','_blank');
	    	window.open('tdsimport_edit_childwindow_generalcodes.do?action=doInit&type=GCY&ctype=svih_dklk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svih_dklkIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svih_dklkIdLink').click();
			}
	    });
	    //Exportland - Landkod
	    jq('#svih_avutIdLink').click(function() {
	    	jq('#svih_avutIdLink').attr('target','_blank');
	    	window.open('tdsimport_edit_childwindow_generalcodes.do?action=doInit&type=GCY&ctype=svih_avut', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svih_avutIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svih_avutIdLink').click();
			}
	    });
	    //Vid gränsen - Landkod
	    jq('#svih_tralIdLink').click(function() {
	    	jq('#svih_tralIdLink').attr('target','_blank');
	    	window.open('tdsimport_edit_childwindow_generalcodes.do?action=doInit&type=GCY&ctype=svih_tral', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svih_tralIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svih_avutIdLink').click();
			}
	    });
	    
	    
	    //Customer SENDER
  	  	jq('#svih_avnaIdLink').click(function() {
  	  		jq('#svih_avnaIdLink').attr('target','_blank');
  	  		window.open('tds_childwindow_customer.do?action=doFind&sonavn=' + jq('#svih_avna').val() + '&ctype=svih_avna', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  	  	});
  	  	jq('#svih_avnaIdLink').keypress(function(e){ //extra feature for the end user
  	  		if(e.which == 13) {
  	  			jq('#svih_avnaIdLink').click();
  	  		}
  	  	});
  	  	//Customer RECEIVER
  	  	jq('#svih_monaIdLink').click(function() {
  	  		jq('#svih_monaIdLink').attr('target','_blank');
  	  		window.open('tds_childwindow_customer.do?action=doFind&sonavn=' + jq('#svih_mona').val() + '&ctype=svih_mona', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  	  	});
  	  	jq('#svih_monaIdLink').keypress(function(e){ //extra feature for the end user
  	  		if(e.which == 13) {
  	  			jq('#svih_monaIdLink').click();
  	  		}
  	  	});
  	  	//Customer DEKLARANT
  	  	jq('#svih_dknaIdLink').click(function() {
  	  		jq('#svih_dknaIdLink').attr('target','_blank');
  	  		window.open('tds_childwindow_customer.do?action=doFind&sonavn=' + jq('#svih_dkna').val() + '&ctype=svih_dkna', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  	  	});
  	  	jq('#svih_dknaIdLink').keypress(function(e){ //extra feature for the end user
  	  		if(e.which == 13) {
  	  			jq('#svih_dknaIdLink').click();
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
		    	var avd = jq("#svih_syav").val() ;
		    	var opd = jq("#svih_syop").val() ;
		    	var godnr = jq("#svih_godn").val() ;
		    	if(avd!='' && opd!='' && godnr!=''){
			    	var requestParams = "&avd=" + avd + "&opd=" + opd +"&godn=" + godnr;
					jq.getJSON('updateGodsnrOnItemLinesImport.do', {
						applicationUser : jq('#applicationUser').val(),
						requestParams : requestParams,
						ajax : 'true'
					}, function(data) {
						//alert("Hello");
						var len = data.length;
						for ( var i = 0; i < len; i++) {
							if(data[i].errMsg!=''){
								alert(errMsg);
								jq("#svih_godn").addClass( "isa_error" );
								jq("#svih_godn").removeClass( "isa_success" );
							}else{
								jq("#svih_godn").addClass( "isa_success" );
								jq("#svih_godn").removeClass( "isa_error" );
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
	    		
			jq.getJSON('getSpecificTopicOmbud_TdsImport.do', {
				applicationUser : jq('#applicationUser').val(),
				avd : jq('#avd').val(),
				ajax : 'true'
			}, function(data) {
				//alert("Hello");
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#svih_omeo').val(data[i].svia_omeo);
					jq('#svih_omty').val(data[i].svia_omty);
					//
					jq('#svih_omha').val(data[i].svia_omha);
					jq('#svih_dek1').val(data[i].svih_dek1);
					jq('#svih_dek2').val(data[i].svih_dek2);
					jq('#svih_tart').val(data[i].svih_tart);
					jq('#svih_mtyp').val(data[i].svih_mtyp);
					jq('#svih_eup1').val(data[i].svih_eup1);
				}
			});
	    });
	});
  	  	
  	//onChange sign list
	jq(function() { 
	    jq('#sign').change(function() {
	    		
	    	jq.getJSON('getSignatureName_TdsImport.do', {
				applicationUser : jq('#applicationUser').val(),
				avd : jq('#avd').val(),
				sign : jq('#sign').val(),
				ajax : 'true'
			}, function(data) {
				//alert("Hello");
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#svih_omha').val(data[i].svth_namn);
					jq('#svih_omtl').val(data[i].svth_tlf);
				}
			});
	    });
	});
	
	//onChange _lekd list
	jq(function() { 
	    jq('#svih_lekd').change(function() {
	    		setLevvillkor();
	    });
	});
	jq(function() { 
	    jq('#svih_leor').blur(function() {
	    		setLevvillkor();
	    });
	});
	//private function
	function setLevvillkor(){
		var lekdValue = jq('#svih_lekd').val();
    		var ort = jq('#svih_leor').val();
    		if(ort==""){
    			if(lekdValue=="DDP"){
    				jq('#svih_leor').val(jq('#svih_mopa').val());
    			}else{
    				jq('#svih_leor').val(jq('#svih_avpa').val());
    			}
    		}
	}
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
  	//FETCH CUSTOMER from SENDER  html area
  	//---------------------------------------------------------
	function searchSenderOwnWindow() {
		jq(function() {
			jq.getJSON('searchCustomer_TdsImport.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : jq('#search_svih_avna').val(),
				customerNumber : jq('#search_svih_avkn').val(),
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
		  jq('#svih_avna').val("");
		  jq('#svih_aveo').val("");
		  jq('#svih_ava1').val("");
		  jq('#svih_ava2').val("");
		  jq('#svih_avpn').val("");
		  jq('#svih_avpa').val("");
		  jq('#svih_avlk').val("");
		  jq('#svih_avha').val("");
		  jq('#svih_avtl').val("");
		  //now populate (if applicable)
		  var key = jq('#senderList').val();
		  jq('#svih_avkn').val(key);
		  customer = map[key];
		  jq('#svih_avna').val(customer.knavn);
		  jq('#svih_aveo').val(customer.eori);
		  jq('#svih_ava1').val(customer.adr1);
		  jq('#svih_ava2').val(customer.adr2);
		  jq('#svih_avpn').val(customer.postnr);
		  jq('#svih_avpa').val(customer.adr3);
		  jq('#svih_avlk').val(customer.syland);
		  jq('#svih_avha').val(customer.kpers);
		  jq('#svih_avtl').val(customer.tlf);
	    });
	});
	
	//onClick for Sender dialog
	jq(function() { 
	    jq('#searchCustomerCloseCancel').click(function() {
	      //rescue the original fields
	      jq('#svih_avkn').val(jq("#orig_svih_avkn").val());	
		  jq('#svih_avna').val(jq("#orig_svih_avna").val());
		  jq('#svih_aveo').val(jq("#orig_svih_aveo").val());
		  jq('#svih_ava1').val(jq("#orig_svih_ava1").val());
		  jq('#svih_ava2').val(jq("#orig_svih_ava2").val());
		  jq('#svih_avpn').val(jq("#orig_svih_avpn").val());
		  jq('#svih_avpa').val(jq("#orig_svih_avpa").val());
		  jq('#svih_avlk').val(jq("#orig_svih_avlk").val());
		  jq('#svih_avha').val(jq("#orig_svih_avha").val());
		  jq('#svih_avtl').val(jq("#orig_svih_avtl").val());
	    });
	});
	
	
	
	//----------------------------------
	//Events Sender (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgCustomerSearch').click(function(){
    			jq("#search_svih_avkn").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_svih_avkn').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchSenderOwnWindow);
			}			
    		});
		jq('#search_svih_avna').keypress(function(e){
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
	    jq('#svih_avkn').blur(function() {
	    	if(jq('#svih_avna').val()==''){
	    		var avknValue = jq('#svih_avkn').val();
	    		if(avknValue!=null && avknValue!=""){
		    		jq.getJSON('searchCustomer_TdsImport.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : jq('#svih_avkn').val(),
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
						jq('#svih_avkn').val(customer.kundnr);
						jq('#svih_avna').val(customer.knavn);
					  	jq('#svih_aveo').val(customer.eori);
					  	jq('#svih_ava1').val(customer.adr1);
					  	jq('#svih_ava2').val(customer.adr2);
					  	jq('#svih_avpn').val(customer.postnr);
					  	jq('#svih_avpa').val(customer.adr3);
					  	jq('#svih_avlk').val(customer.syland);
					  	jq('#svih_avha').val(customer.kpers);
					  	jq('#svih_avtl').val(customer.tlf);
						
					}else{
						//init fields
						jq('#svih_avna').val("");
					  	jq('#svih_aveo').val("");
					  	jq('#svih_ava1').val("");
					  	jq('#svih_ava2').val("");
					  	jq('#svih_avpn').val("");
					  	jq('#svih_avpa').val("");
					  	jq('#svih_avlk').val("");
					  	jq('#svih_avha').val("");
					  	jq('#svih_avtl').val("");
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
			jq.getJSON('searchCustomer_TdsImport.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : jq('#search_svih_mona').val(),
				customerNumber : jq('#search_svih_mokn').val(),
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
					customer.postnr = data[i].sypoge;
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
		  jq('#svih_mona').val("");
		  jq('#svih_moeo').val("");
		  jq('#svih_moa1').val("");
		  jq('#svih_moa2').val("");
		  jq('#svih_mopn').val("");
		  jq('#svih_mopa').val("");
		  jq('#svih_molk').val("");	
		  //now populate (if applicable)
		  var key = jq('#receiverList').val();
		  jq('#svih_mokn').val(key);
		  customer = map[key];
		  jq('#svih_moeo').val(customer.eori);
		  jq('#svih_mona').val(customer.knavn);
		  jq('#svih_moa1').val(customer.adr1);
		  jq('#svih_moa2').val(customer.adr2);
		  jq('#svih_mopn').val(customer.postnr);
		  jq('#svih_mopa').val(customer.adr3);
		  jq('#svih_molk').val(customer.syland);			  
		});
	});
	//onClick for Receiver(Mottagare) dialog
	jq(function() { 
	    jq('#searchCustomer10CloseCancel').click(function() {
	      //rescue the original fields
	      jq('#svih_mokn').val(jq("#orig_svih_mokn").val());	
		  jq('#svih_mona').val(jq("#orig_svih_mona").val());
		  jq('#svih_moeo').val(jq("#orig_svih_moeo").val());
		  jq('#svih_moa1').val(jq("#orig_svih_moa1").val());
		  jq('#svih_moa2').val(jq("#orig_svih_moa2").val());
		  jq('#svih_mopn').val(jq("#orig_svih_mopn").val());
		  jq('#svih_mopa').val(jq("#orig_svih_mopa").val());
		  jq('#svih_molk').val(jq("#orig_svih_molk").val());
		  
	    });
	});
	
	//----------------------------------
	//Events Receiver (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgReceiverSearch').click(function(){
    			jq("#search_svih_mokn").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_svih_mokn').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchReceiverOwnWindow);
			}			
    		});
		jq('#search_svih_mona').keypress(function(e){
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
	    jq('#svih_mokn').blur(function() {
	    	if(jq('#svih_mona').val()==''){
	    		var moknValue = jq('#svih_mokn').val();
	    		if(moknValue!=null && moknValue!=""){
		    		jq.getJSON('searchCustomer_TdsImport.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : jq('#svih_mokn').val(),
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
						customer.postnr = data[i].sypoge;
						customer.kpers = data[i].kpers;
						customer.tlf = data[i].tlf;
						customer.syland = data[i].syland;
						
					}
					if(len > 0){
					  	jq('#svih_mokn').val(customer.kundnr);
					  	jq('#svih_moeo').val(customer.eori);
					  	jq('#svih_mona').val(customer.knavn);
					  	jq('#svih_moa1').val(customer.adr1);
					  	jq('#svih_moa2').val(customer.adr2);
					  	jq('#svih_mopn').val(customer.postnr);
					  	jq('#svih_mopa').val(customer.adr3);
					  	jq('#svih_molk').val(customer.syland);
					  	jq('#svih_moha').val(customer.kpers);
					  	jq('#svih_motl').val(customer.tlf);
					  	
					}else{
						//init fields
						jq('#svih_moeo').val("");
					  	jq('#svih_mona').val("");
					  	jq('#svih_moa1').val("");
					  	jq('#svih_moa2').val("");
					  	jq('#svih_mopn').val("");
					  	jq('#svih_mopa').val("");
					  	jq('#svih_molk').val("");
					  	jq('#svih_moha').val("");
					  	jq('#svih_motl').val("");
					  	
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
			jq.getJSON('searchCustomer_TdsImport.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : jq('#search_svih_dkna').val(),
				customerNumber : jq('#search_svih_dkkn').val(),
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
		  jq('#svih_dkna').val("");
		  jq('#svih_dkeo').val("");
		  jq('#svih_dka1').val("");
		  jq('#svih_dka2').val("");
		  jq('#svih_dkpn').val("");
		  jq('#svih_dkpa').val("");
		  jq('#svih_dklk').val("");
		  
		  //now populate (if applicable)
		  var key = jq('#deklarantList').val();
		  jq('#svih_dkkn').val(key);
		  customer = map[key];
		  jq('#svih_dkna').val(customer.knavn);
		  jq('#svih_dkeo').val(customer.eori);
		  jq('#svih_dka1').val(customer.adr1);
		  jq('#svih_dka2').val(customer.adr2);
		  jq('#svih_dkpn').val(customer.postnr);
		  jq('#svih_dkpa').val(customer.adr3);
		  jq('#svih_dklk').val(customer.syland);			  
		});
	});
	//onClick for Deklarant dialog
	jq(function() { 
	    jq('#searchCustomer20CloseCancel').click(function() {
	      //rescue the original fields
	      jq('#svih_dkkn').val(jq("#orig_svih_dkkn").val());	
		  jq('#svih_dkna').val(jq("#orig_svih_dkna").val());
		  jq('#svih_dkeo').val(jq("#orig_svih_dkeo").val());
		  jq('#svih_dka1').val(jq("#orig_svih_dka1").val());
		  jq('#svih_dka2').val(jq("#orig_svih_dka2").val());
		  jq('#svih_dkpn').val(jq("#orig_svih_dkpn").val());
		  jq('#svih_dkpa').val(jq("#orig_svih_dkpa").val());
		  jq('#svih_dklk').val(jq("#orig_svih_dklk").val());
		  
	    });
	});
	
	//----------------------------------
	//Events Deklarant (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgDeklarantSearch').click(function(){
    			jq("#search_svih_dkkn").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_svih_dkkn').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchDeklarantOwnWindow);
			}			
    		});
		jq('#search_svih_dkna').keypress(function(e){
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
	    jq('#svih_dkkn').blur(function() {
	    	if(jq('#svih_dkna').val()==''){
	    		var dkknValue = jq('#svih_dkkn').val();
	    		if(dkknValue!=null && dkknValue!=""){
		    		jq.getJSON('searchCustomer_TdsImport.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : jq('#svih_dkkn').val(),
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
					  	jq('#svih_dkkn').val(customer.kundnr);
					  	jq('#svih_dkna').val(customer.knavn);
					  	jq('#svih_dkeo').val(customer.eori);
					  	jq('#svih_dka1').val(customer.adr1);
					  	jq('#svih_dka2').val(customer.adr2);
					  	jq('#svih_dkpn').val(customer.postnr);
					  	jq('#svih_dkpa').val(customer.adr3);
					  	jq('#svih_dklk').val(customer.syland);	
					  	jq('#svih_dkha').val(customer.kpers);
					  	jq('#svih_dktl').val(customer.tlf);
					}else{
						//init fields
						jq('#svih_dkna').val("");
					  	jq('#svih_dkeo').val("");
					  	jq('#svih_dka1').val("");
					  	jq('#svih_dka2').val("");
					  	jq('#svih_dkpn').val("");
					  	jq('#svih_dkpa').val("");
					  	jq('#svih_dklk').val("");	
					  	jq('#svih_dkha').val("");
					  	jq('#svih_dktl').val("");					  	
					}
				});
	    		}
	    	}
		});
	});
		

	//Currency AJAX fetch
	jq(function() { 
	    jq('#svih_vakd').change(function() {
	    	//alert('Hej');
	    	//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('getCurrencyRate_TdsImport.do', {
				applicationUser : jq('#applicationUser').val(),
				currencyCode : jq('#svih_vakd').val(),
				ajax : 'true'
			}, function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					//data[i].svvk_krs;
					//data[i].svvs_omr;
					jq('#svih_vaku').val(data[i].svvk_krs);
					jq('#svih_vaom').val(data[i].svvs_omr);
				}
				
			});
	    });
	});
	jq(function() { 
	    jq('#svih_vakd').blur(function() {
	    	if(jq('#svih_vaku').val()==''){
		    	//this parameters must match the AJAX controller parameter names in Spring exactly...
				jq.getJSON('getCurrencyRate_TdsImport.do', {
					applicationUser : jq('#applicationUser').val(),
					currencyCode : jq('#svih_vakd').val(),
					ajax : 'true'
				}, function(data) {
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						//data[i].svvk_krs;
						//data[i].svvs_omr;
						jq('#svih_vaku').val(data[i].svvk_krs);
						jq('#svih_vaom').val(data[i].svvs_omr);
					}
					
				});
	    	}
	    });
	});

	
	//Meddelandetyp Mandatory fields
	jq(function() { 
	    jq('#svih_mtyp').change(function() {
	    		var value = jq('#svih_mtyp').val();
	    		if(value=='HNU' || value=='HNK' || value=='HRT' || value=='HBK'){
	    			jq('.text12Red').hide();
	    		}else{
	    			jq('.text12Red').show();
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
			  //console.log("AAA");
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
						  	window.location = "tdsimport_edit.do?action=doFetch&avd=" + avd + "&opd=" + opd + "&sysg=" +  sign;
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
	  	
	
	  //aspects/functions on ready document...
	  jq(document).ready(function() {
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