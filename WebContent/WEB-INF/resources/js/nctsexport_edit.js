	//===========================================
	//General functions for this JSP side - AJAX
	//===========================================
	
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
	
	//date fields
    jq(function() {
  	  jq("#thtrdt").datepicker({ 
  		  dateFormat: 'yymmdd'
  	  });
  	  jq("#thddt").datepicker({ 
  		  dateFormat: 'yymmdd',
  		  defaultDate: "+4"
  	  });
  	  
    });
    jq(function() {
  		jq( "#submit" ).click(function( event ) {
  			setBlockUI();
	  	});
  		jq( "#submit2" ).click(function( event ) {
  			setBlockUI();
  	  	});
    });
	
    jq(function() {
  		jq( "#thtaid" ).blur(function(){
  			if(jq( "#thtgid" ).val()==''){
  				jq( "#thtgid" ).val(jq( "#thtaid" ).val());
  			}
  			if(jq( "#thtrm" ).val()==''){
  				jq( "#thtrm" ).val(jq( "#thtrmi" ).val());
  			}
	  	});
  		
    });
	
    
    jq(function() {
    	//Customer SENDER
	  	jq('#thnasIdLink').click(function() {
	  		jq('#thnasIdLink').attr('target','_blank');
	  		window.open('tds_childwindow_customer.do?action=doFind&sonavn=' + jq('#thnas').val() + '&ctype=thnas', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  	});
	  	jq('#thnasIdLink').keypress(function(e){ //extra feature for the end user
	  		if(e.which == 13) {
	  			jq('#thnasIdLink').click();
	  		}
	  	});
	  	
	  	//Customer RECEIVER
	  	jq('#thnakIdLink').click(function() {
	  		jq('#thnakIdLink').attr('target','_blank');
	  		window.open('tds_childwindow_customer.do?action=doFind&sonavn=' + jq('#thnak').val() + '&ctype=thnak', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  	});
	  	jq('#thnakIdLink').keypress(function(e){ //extra feature for the end user
	  		if(e.which == 13) {
	  			jq('#thnakIdLink').click();
	  		}
	  	});
	  	//Customer ANSVARIG
	  	jq('#thnaaIdLink').click(function() {
	  		jq('#thnaaIdLink').attr('target','_blank');
	  		window.open('tds_childwindow_customer.do?action=doFind&sonavn=' + jq('#thnaa').val() + '&ctype=thnaa', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  	});
	  	jq('#thnaaIdLink').keypress(function(e){ //extra feature for the end user
	  		if(e.which == 13) {
	  			jq('#thnaaIdLink').click();
	  		}
	  	});
    });
    
    jq(function() {
    	//Customer SENDER - SÄKERHET
	  	jq('#thnassIdLink').click(function() {
	  		jq('#thnassIdLink').attr('target','_blank');
	  		window.open('tds_childwindow_customer.do?action=doFind&sonavn=' + jq('#thnass').val() + '&ctype=thnass', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  	});
	  	jq('#thnassIdLink').keypress(function(e){ //extra feature for the end user
	  		if(e.which == 13) {
	  			jq('#thnassIdLink').click();
	  		}
	  	});
	  	
	  	//Customer RECEIVER - SÄKERHET
	  	jq('#thnaksIdLink').click(function() {
	  		jq('#thnaksIdLink').attr('target','_blank');
	  		window.open('tds_childwindow_customer.do?action=doFind&sonavn=' + jq('#thnaks').val() + '&ctype=thnaks', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  	});
	  	jq('#thnaksIdLink').keypress(function(e){ //extra feature for the end user
	  		if(e.which == 13) {
	  			jq('#thnaksIdLink').click();
	  		}
	  	});
	  	//Customer TRANSPORTÖR - SÄKERHET
	  	jq('#thnatIdLink').click(function() {
	  		jq('#thnatIdLink').attr('target','_blank');
	  		window.open('tds_childwindow_customer.do?action=doFind&sonavn=' + jq('#thnat').val() + '&ctype=thnat', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  	});
	  	jq('#thnatIdLink').keypress(function(e){ //extra feature for the end user
	  		if(e.which == 13) {
	  			jq('#thnatIdLink').click();
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
	});
	
	//------------------------------------------------------------------------------------------
	//Init Topic with CREATE NEW TOPIC since some values are fetched depending on the Avdelning
	//------------------------------------------------------------------------------------------	
	jq(function() { 
	    jq('#avd').change(function() {
	    		//alert('Hej');
	    		//this parameters must match the AJAX controller parameter names in Spring exactly...
	    		jq.getJSON('initCreateNewTopic.do', {
	    			applicationUser : jq('#applicationUser').val(),
	    			avd : jq('#avd').val(),
	    			ajax : 'true'
			}, 
			function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#thdst').val(data[i].thdst);
					jq('#thdk').val(data[i].thdk);
					jq('#thtina').val(data[i].thtina);
					jq('#thnaa').val(data[i].thnaa);
					jq('#thada1').val(data[i].thada1);
					jq('#thpna').val(data[i].thpna);
					jq('#thpsa').val(data[i].thpsa);
					jq('#thlka').val(data[i].thlka);
					jq('#thlsd').val(data[i].thlsd);
					jq('#thlas2').val(data[i].thlas2);
					jq('#thalk').val(data[i].thalk);
					jq('#thtrmi').val(data[i].thtrmi);
					jq('#thskfd').val(data[i].thskfd);
					jq('#thcats').val(data[i].thcats);
					jq('#thtsd1').val(data[i].thtsd1);
					
					jq('#thddt').val(data[i].thddt);
					jq('#thdkr').val(data[i].thdkr);
					
					jq('#thgkd').val(data[i].thgkd);
					jq('#thgft1').val(data[i].thgft1);
					jq('#thgadk').val(data[i].thgadk);
					jq('#thgbl').val(data[i].thgbl);
					
					jq('#thgvk').val(data[i].thgvk);
					jq('#thgbgi').val(data[i].thgbgi);
					jq('#thdsk').val(data[i].thdsk);
					jq('#thgadk').val(data[i].thgadk);
					jq('#thgbl').val(data[i].thgbl);
					//Sender
					jq('#thkns').val(data[i].thkns);
					jq('#thtins').val(data[i].thtins);
					jq('#thnas').val(data[i].thnas);
					jq('#thads1').val(data[i].thads1);
					jq('#thpns').val(data[i].thpns);
					jq('#thpss').val(data[i].thpss);
					jq('#thlks').val(data[i].thlks);
					//Receiver
					jq('#thknk').val(data[i].thknk);
					jq('#thtink').val(data[i].thtink);
					jq('#thnak').val(data[i].thnak);
					jq('#thadk1').val(data[i].thadk1);
					jq('#thpnk').val(data[i].thpnk);
					jq('#thpsk').val(data[i].thpsk);
					jq('#thlkk').val(data[i].thlkk);
					//
					jq('#thblk').val(data[i].thblk);
					jq('#thtrmi').val(data[i].thtrmi);
					jq('#thtsb').val(data[i].thtsb);
					jq('#thddt').val(data[i].thddt);
					ijq('#thgbl').val(data[i].thgbl);
					jq('#thlasd').val(data[i].thlasd);
					
					
				}
			});
	    });
	});
	
	jq(function() { 
	    jq('#calculateGuaranteeButton').click(function() {
	    		//alert('Hej');
	    		//this parameters must match the AJAX controller parameter names in Spring exactly...
	    		jq.getJSON('calculateGuaranteeAmount.do', {
	    			applicationUser : jq('#applicationUser').val(),
	    			avd : jq('#avd').val(),
	    			opd : jq('#opd').val(),
	    			ajax : 'true'
			}, 
			function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#thgbl').val(data[i].amount);
				}
			});
	    });
	});
	
	
	
	
	//--------------------------------------------------------------------------------------
	//Extra behavior for Customer number ( without using (choose from list) extra roundtrip)
	//--------------------------------------------------------------------------------------
	//SENDER
	jq(function() { 
	    jq('#thkns').blur(function() {
	    	if(jq('#thnas').val()==''){
	    		var avknValue = jq('#thkns').val();
	    		if(avknValue!=null && avknValue!=""){
		    		jq.getJSON('searchCustomer.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : jq('#thkns').val(),
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
					  	jq('#thkns').val(customer.kundnr);
					  	jq('#thnas').val(customer.knavn);
					  	jq('#thtins').val(customer.eori);
					  	jq('#thads1').val(customer.adr1);
					  	jq('#thpns').val(customer.postnr);
					  	jq('#thpss').val(customer.adr3);
					  	jq('#thlks').val(customer.syland);
					  	
					}else{
						//init fields
						jq('#thkns').val("");
					  	jq('#thnas').val("");
					  	jq('#thtins').val("");
					  	jq('#thads1').val("");
					  	jq('#thpns').val("");
					  	jq('#thpss').val("");
					  	jq('#thlks').val("");
					}
				});
	    		}
	    	}
		});
	}); 
	//RECEIVER   
    jq(function() { 
	    jq('#thknk').blur(function() {
	    	if(jq('#thnak').val()==''){
	    		var avknValue = jq('#thknk').val();
	    		if(avknValue!=null && avknValue!=""){
		    		jq.getJSON('searchCustomer.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : jq('#thknk').val(),
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
					  	jq('#thknk').val(customer.kundnr);
					  	jq('#thnak').val(customer.knavn);
					  	jq('#thtink').val(customer.eori);
					  	jq('#thadk1').val(customer.adr1);
					  	jq('#thpnk').val(customer.postnr);
					  	jq('#thpsk').val(customer.adr3);
					  	jq('#thlkk').val(customer.syland);
					  	
					}else{
						//init fields
						jq('#thknk').val("");
					  	jq('#thnak').val("");
					  	jq('#thtink').val("");
					  	jq('#thadk1').val("");
					  	jq('#thpnk').val("");
					  	jq('#thpsk').val("");
					  	jq('#thlkk').val("");
					}
				});
	    		}
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

	  
	  jq(function() {
		  //Valuta
	  	  jq('#thgvkIdLink').click(function() {
	    	jq('#thgvkIdLink').attr('target','_blank');
	    	window.open('nctsexport_edit_childwindow_generalcodes.do?action=doInit&type=MDX&ctype=thgvk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  	  });
	  	  jq('#thgvkIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#thgvkIdLink').click();
			}
	  	  });
	  });
	
	//ChildWindow Country Codes
	    function triggerChildWindowCountryCodes(record){
	    	var idLink = record.id;
	    	var id = idLink.replace("IdLink", "");
	    	jq(idLink).attr('target','_blank');
	    	window.open('nctsexport_edit_childwindow_generalcodes.do?action=doInit&type=GCY&ctype=' + id , "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    }
	    
	  //ChildWindow Language Codes
	    function triggerChildWindowLanguageCodes(record){
	    	var idLink = record.id;
	    	var id = idLink.replace("IdLink", "");
	    	jq(idLink).attr('target','_blank');
	    	window.open('nctsexport_edit_childwindow_generalcodes.do?action=doInit&type=012&ctype=' + id , "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    }
	  //ChildWindow Tullkontor Codes
	    function triggerChildWindowTullkontorCodes(record){
	    	var idLink = record.id;
	    	var id = idLink.replace("IdLink", "");
	    	//alert(idLink + "XX" + id);
	    	//alert(jq("#"+id).val());
	    	jq(idLink).attr('target','_blank');
	    	window.open('nctsexport_edit_childwindow_tullkontor.do?action=doInit&tkkode=' + jq("#"+id).val()+ '&ctype=' + id, "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    }
	
	    
	
	
	    jq(document).ready(function(){
	  	    
	  	    //aspects/functions on ready document...
	  		if(jq('#imgRedFlagAntalKolliInItems').length || jq('#imgRedFlagSumGrossWeightInItems').length || jq('#imgSumOfInvoiceAmountInItemLines').length){
	  			jq('#submitRedFlag').css('display', 'inline-block');	
	  		}else{
	  			jq('#submitRedFlag').css('display', 'none');
	  		}
	  		
	  		jq(document).ready(function() {
	  			//to prevent hiding datepicker behind the autocomplete function
	  			jq('.datepicker').on('click', function(e) {
	  			   e.preventDefault();
	  			   jq(this).attr("autocomplete", "off");  
	  			});
	  		});
	  	        
	  	  });
