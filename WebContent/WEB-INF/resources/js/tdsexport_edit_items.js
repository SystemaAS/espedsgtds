	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
    
  	//Overlay on tab (to mark visually a delay...)
    jq(function() {
    	jq('#alinkMainList').click(function() { 
      		setBlockUI();
      	  });	
  	  jq('#alinkHeader').click(function() { 
  		setBlockUI();
  	  });
  	  jq('#alinkInvoices').click(function() { 
  		setBlockUI();
	  });
  	  jq('#alinkLogging').click(function() { 
  		setBlockUI();
  	  });
  	  jq('#alinkArchive').click(function() { 
  		setBlockUI();
  	  });
  	  
    });
  	
  	jq(function() {
  		jq('#svev_vata').blur(function() {
  			//Check for codes
  			getTillaggskoderOnBlur();
  		});
  		jq('#svev_vati').blur(function() {
  			//Check for codes
  			getTillaggskoderOnBlur();
  		});
  		
  		jq('#svev_fabl').blur(function() {
  			//(1) Calculate
  			calculateStatisticalValues();
  			//(2) Check for codes
  			getBilagdaHandlingarYkoderOnBlur();
  		});
  		
  	});
  	
  	//General events
  	jq(function() {
	  	
	  	jq("#btnItemsSave").click(function( event ) {
	  		//Has to be done here since it is a "last resort" in case the user does not navigate to sviv_fabl (at some point in the GUI-flow)
	  		getBilagdaHandlingarYkoder_OnFormSubmit();
	  	});
	  	
	  	//Show child window (if applicable)
  		jq('#warningCodesLink').click(function() {
  			window.open('tdsexport_edit_items_childwindow_codes.do?action=doInit&lk='+ jq('#session_sveh_aube').val() + '&vata=' + jq('#svev_vata').val(), 
	 		"codesWin", "top=300px,left=450px,height=450px,width=800px,scrollbars=no,status=no,location=no");
  		});
  		jq('#warningCodesLinkBh').click(function() {
  			window.open('tdsexport_edit_items_childwindow_codes_bh.do?action=doInit&lk='+ jq('#session_sveh_aube').val() + '&vata=' + jq('#svev_vata').val(), 
	 		"codesWin", "top=300px,left=450px,height=450px,width=800px,scrollbars=no,status=no,location=no");
  		});
  		//Auto control - autoförtullning
  		//jq('#itemListControlButton').click(function() {
  			//jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
			//window.location = 'tdsexport_edit_items_autocontrol.do?svev_syav='+ jq('#avd').val() + '&svev_syop=' + jq('#opd').val() + '&fablAutoControl=' + jq('#fablAutoControl').val();
  		//});
  		
  		
  		//=====================================
  	  	//START Child window for general codes
  	  	//=====================================
  	  	//Ursp.land
  	  	jq('#svev_ulkdIdLink').click(function() {
  	    	jq('#svev_ulkdIdLink').attr('target','_blank');
  	    	window.open('tdsexport_edit_items_childwindow_generalcodes.do?action=doInit&type=GCY&ctype=svev_ulkd', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  	    });
  	    jq('#svev_ulkdIdLink').keypress(function(e){ //extra feature for the end user
  			if(e.which == 13) {
  				jq('#svev_ulkdIdLink').click();
  			}
  	    });	
  	    
  	    //Förfar.1
	    jq('#svev_eup1IdLink').click(function() {
	    	jq('#svev_eup1IdLink').attr('target','_blank');
	    	window.open('tdsexport_edit_items_childwindow_generalcodes.do?action=doInit&type=FF1&ctype=svev_eup1', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svev_eup1IdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svev_eup1IdLink').click();
			}
	    });
	    //Förfar.2
	    jq('#svev_eup2IdLink').click(function() {
	    	jq('#svev_eup2IdLink').attr('target','_blank');
	    	window.open('tdsexport_edit_items_childwindow_generalcodes.do?action=doInit&type=FFK&ctype=svev_eup2', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svev_eup2IdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svev_eup2IdLink').click();
			}
	    });
	    //Kollislag
	    jq('#svev_koslIdLink').click(function() {
	    	jq('#svev_koslIdLink').attr('target','_blank');
	    	window.open('tdsexport_edit_items_childwindow_generalcodes.do?action=doInit&type=KLI&ctype=svev_kosl', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svev_koslIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svev_koslIdLink').click();
			}
	    });
	  //Bilagda Handlingar
	    jq('#bilagdaHandIdLink').click(function() {
	    	jq('#bilagdaHandIdLink').attr('target','_blank');
	    	window.open('tdsexport_edit_items_childwindow_generalcodes.do?action=doInit&type=MCF&ctype=', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#bilagdaHandIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#bilagdaHandIdLink').click();
			}
	    });
	    //Särskilda Upplysningar
	    jq('#sarskildaUppIdLink').click(function() {
	    	jq('#sarskildaUppIdLink').attr('target','_blank');
	    	window.open('tdsexport_edit_items_childwindow_generalcodes.do?action=doInit&type=SAL&ctype=', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sarskildaUppIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sarskildaUppIdLink').click();
			}
	    });
	    //Särskilda Upplysningar 2
	    jq('#sarskildaUppIdLink2').click(function() {
	    	jq('#sarskildaUppIdLink2').attr('target','_blank');
	    	window.open('tdsexport_edit_items_childwindow_generalcodes.do?action=doInit&type=SAL&ctype=', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sarskildaUppIdLink2').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sarskildaUppIdLink2').click();
			}
	    });
	    //Tidigare Handlingar
	    jq('#tidigareHandlingarIdLink').click(function() {
	    	jq('#tidigareHandlingarIdLink').attr('target','_blank');
	    	window.open('tdsexport_edit_items_childwindow_generalcodes.do?action=doInit&type=THO&ctype=', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#tidigareHandlingarIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#tidigareHandlingarIdLink').click();
			}
	    });
	    
	    //Identifiering av lager - Landkod
	    jq('#svev_laglIdLink').click(function() {
	    	jq('#svev_laglIdLink').attr('target','_blank');
	    	window.open('tdsexport_edit_items_childwindow_generalcodes.do?action=doInit&type=GCY&ctype=svev_lagl', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#svev_laglIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#svev_laglIdLink').click();
			}
	    });
	    
	    
	    
  	    
  	    //====================================
  	  	// END Child window for general codes
  	  	//====================================
  	    
  	    //=====================================
  	  	//START Child window for tulltaxa 
  	  	//=====================================
  	  	jq('#svev_vataIdLink').click(function() {
  	    	jq('#svev_vataIdLink').attr('target','_blank');
  	    	window.open('tdsexport_edit_items_childwindow_tulltaxa.do?action=doInit&vkod=' + jq('#svev_vata').val() + '&caller=svev_vata', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  	    });
  	    jq('#svev_vataIdLink').keypress(function(e){ //extra feature for the end user
  			if(e.which == 13) {
  				jq('#svev_vataIdLink').click();
  			}
  	    });
  	    //Kundens vareregister (cross-table for tulltaxa)
	    jq('#kundensVaruregisterControlButton').click(function() {
	    	jq('#kundensVaruregisterControlButton').attr('target','_blank');
	    	window.open('tdsexport_edit_items_childwindow_kundensvarereg.do?action=doInit&senId=' + jq('#senderId').val(), "codeKundVareRegWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#kundensVaruregisterControlButton').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#kundensVaruregisterControlButton').click();
			}
	    });
  	    //====================================
  	  	// END Child window for tulltaxa
  	  	//====================================
  	    
  	   
  		
  		
	  	
  	});
  	
  	//CALCULATE Stat.Values
  	function calculateStatisticalValues() {
  		var svehStrParam = "sveh_vakd=" + jq('#session_sveh_vakd').val() + "&" + "sveh_vaku=" + jq('#session_sveh_vaku').val() + "&" + 	
							"sveh_fabl=" + jq('#session_sveh_fabl').val() + "&" +
							"sveh_vuva=" + jq('#session_sveh_vuva').val() + "&" +
							"sveh_vuku=" + jq('#session_sveh_vuku').val() + "&" +
							"sveh_vufr=" + jq('#session_sveh_vufr').val() + "&" + 
							"sveh_sydt=" + jq('#session_sveh_sydt').val() + "&";
		var svevStrParam = "svev_fabl=" + jq('#svev_fabl').val();	
		
		if(jq('#session_sveh_vakd').val()!='' && jq('#session_sveh_vaku').val() !='' && jq('#session_sveh_fabl').val()!='' && jq('#svev_fabl').val()!=''){
			jq.ajax({
				type: 'GET',
				url: 'calculateStatisticalValues_TdsExport.do',
				data: { 	applicationUser : jq('#applicationUser').val(),
						svehStr : svehStrParam, 
					  	svevStr : svevStrParam },
				dataType: 'json',
				success: function(data) {
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						
						//alert(data[i].debugPrintlnAjax);
						//debug info
						jq('#debugPrintlnAjaxInfo').text(data[i].debugPrintlnAjax);
						
						//tull och statistiskt värde
						jq('#svev_stva').val(data[i].svev_stva);
						jq('#svev_stva2').val(data[i].svev_stva2);
						//alert(callerType);
					}
				}
			});
		}
  	}
  	
  	
  	//------------------------------------------------
  	//Get Tilläggskoder Child window (if applicable)
  	//------------------------------------------------
  	function getTillaggskoderOnBlur(element){
  		jq('#tillaggskoderLink').attr('target','_blank');
  		//open chikd window
  		if(jq('#session_sveh_aube').val()!='' && jq('#svev_vata').val()!=''){
			jq.ajax({
				type: 'GET',
				url: 'getTillagskoder_TdsExport.do',
				data: { 	applicationUser : jq('#applicationUser').val(),
					countryCode : jq('#session_sveh_aube').val(), 
					itemCode : jq('#svev_vata').val() },
				dataType: 'json',
				async: false,	
				success: function(data) {
					var len = data.length;
					//only when the list has something to show (at least 1-record)
		 			if(len>0){
		 				if(jq('#svev_vati').val()==''){
		 					jq('#warningCodesFlagDiv').show();
		 				}else{
		 					jq('#warningCodesFlagDiv').hide();
		 				}
		 			}else{
		 				jq('#warningCodesFlagDiv').hide();
		 			}
				},
				error: function() {
					alert('Error loading ...');
				}
			});
  		}
	}

  	//---------------------------------------------
  	//Get Bilagda handlingar - Ykoder Child window
  	//(if applicable)
  	//---------------------------------------------
  	function getBilagdaHandlingarYkoderOnBlur(){
		jq('#bhandlingarYkoderLink').attr('target','_blank');
  		//open child window
  		if(jq('#session_sveh_aube').val()!='' && jq('#svev_vata').val()!=''){
			jq.ajax({
				type: 'GET',
				url: 'getBilagdaHandlingarYkoder_TdsExport.do',
				data: { 	applicationUser : jq('#applicationUser').val(),
					countryCode : jq('#session_sveh_aube').val(), 
					itemCode : jq('#svev_vata').val() },
				dataType: 'json',
				async: false,	
				success: function(data) {
					var len = data.length;
					//only when the list has something to show (at least 1-record)
		 			if(len>0){
		 				if(userValuesExist()==true){
		 					jq('#warningCodesFlagDivBh').hide();
		 				}else{
		 					jq('#warningCodesFlagDivBh').show();
		 				}
		 			}else{
		 				jq('#warningCodesFlagDivBh').hide();
		 			}	
				},
				error: function() {
					alert('Error loading ...');
				}
			});
  		}
  		
	}
  	
  	function getBilagdaHandlingarYkoder_OnFormSubmit(){
		jq('#bhandlingarYkoderLink').attr('target','_blank');
  		//open child window
  		if(jq('#session_sveh_aube').val()!='' && jq('#svev_vata').val()!=''){
			jq.ajax({
				type: 'GET',
				url: 'getBilagdaHandlingarYkoder_TdsExport.do',
				data: { 	applicationUser : jq('#applicationUser').val(),
					countryCode : jq('#session_sveh_aube').val(), 
					itemCode : jq('#svev_vata').val() },
				dataType: 'json',
				async: false,	
				success: function(data) {
					var len = data.length;
					//only when the list has something to show (at least 1-record)
		 			if(len>0){
		 				if(userValuesExist()==true){
		 					jq('#warningCodesFlagDivBh').hide();
		 					//submit form
		 					setBlockUI();
		 			  		jq( "#tdsExportEditTopicItemForm" ).submit();
		 				}else{
		 					//stay in page
		 					jq('#warningCodesFlagDivBh').show();
		 				}
		 			}else{
		 				jq('#warningCodesFlagDivBh').hide();
		 				//submit form
	 					setBlockUI();
	 			  		jq( "#tdsExportEditTopicItemForm" ).submit();
		 			}	
				},
				error: function() {
					alert('Error loading ...');
				}
			});
  		}
  		
	}
  	/**
  	 * 
  	 * @return
  	 */
  	function userValuesExist(){
  		var retval = false;
  		//bit1 usually holds the N380 code (invoice)
  		/*if(jq('#svev_bit1').val()!='' && ( jq('#svev_bit1').val().match("^Y") || jq('#svev_bit1').val().match("^X")) ){
  			retval = true;
  		}*/
  		if(jq('#svev_bit2').val()!='' && ( jq('#svev_bit2').val().match("^Y") || jq('#svev_bit2').val().match("^X") || jq('#svev_bit2').val().match("^C")) ){
  			retval = true;
  		}
  		if(jq('#svev_bit3').val()!='' && ( jq('#svev_bit3').val().match("^Y") || jq('#svev_bit3').val().match("^X") || jq('#svev_bit3').val().match("^C")) ){
  			retval = true;
  		}
  		if(jq('#svev_bit4').val()!='' && ( jq('#svev_bit4').val().match("^Y") || jq('#svev_bit4').val().match("^X") || jq('#svev_bit4').val().match("^C")) ){
  			retval = true;
  		}
  		if(jq('#svev_bit5').val()!='' && ( jq('#svev_bit5').val().match("^Y") || jq('#svev_bit5').val().match("^X") || jq('#svev_bit5').val().match("^C")) ){
  			retval = true;
  		}
  		return retval;
  	}
  	
  //-----------------------------------------------------------------------------
  	//jQuery CALCULATOR (related to jquery.calculator.js and jquery.calculator.css
  	//-----------------------------------------------------------------------------
  	jq(function() {
  		jq('#svev_fabl').calculator({ showOn: 'button',  
  			buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
  		jq('#svev_brut').calculator({ showOn: 'button',  
  			buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
  		jq('#svev_neto').calculator({ showOn: 'button',  
  			buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
  		jq('#svev_kota').calculator({ showOn: 'button',  
  			buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
  		jq('#svev_ankv').calculator({ showOn: 'button',  
  			buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
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
	  	  url: 'getSpecificTopicItemChosenFromGuiElement.do',
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
				//alert(data[i].svev_vasl);
				jq('#lineNr').val(data[i].svev_syli);
				jq('#svev_syli').val(data[i].svev_syli);
				jq('#svev_vano').val(data[i].svev_vano); jq('#svev_ulkd').val(data[i].svev_ulkd); 
				jq('#svev_vata').val(data[i].svev_vata); jq('#svev_vati').val(data[i].svev_vati); 
				jq('#svev_vat4').val(data[i].svev_vat4);
				jq('#svev_eup1').val(data[i].svev_eup1); jq('#svev_eup2').val(data[i].svev_eup2); 
				jq('#svev_brut').val(data[i].svev_brut); jq('#svev_neto').val(data[i].svev_neto); 
				jq('#svev_ankv').val(data[i].svev_ankv); jq('#svev_vasl').val(data[i].svev_vasl);
				jq('#svev_vas2').val(data[i].svev_vas2); jq('#svev_vas3').val(data[i].svev_vas3);
				jq('#svev_vas4').val(data[i].svev_vas4); jq('#svev_vas5').val(data[i].svev_vas5);
				
				jq('#svev_fabl').val(data[i].svev_fabl); 
				
				//Extra section
				jq('#svev_sutx').val(data[i].svev_sutx); jq('#svev_sut2').val(data[i].svev_sut2); 
				jq('#svev_sut3').val(data[i].svev_sut3); jq('#svev_sut4').val(data[i].svev_sut4);
				jq('#svev_sut5').val(data[i].svev_sut5);  
				
				
				jq('#svev_suko').val(data[i].svev_suko); 
				jq('#svev_suk6').val(data[i].svev_suk6); jq('#svev_sut6').val(data[i].svev_sut6);
				jq('#svev_sut7').val(data[i].svev_sut7); jq('#svev_sut8').val(data[i].svev_sut8);
				jq('#svev_sut9').val(data[i].svev_sut9); jq('#svev_suta').val(data[i].svev_suta);
				
				jq('#svev_sukb').val(data[i].svev_sukb);
				jq('#svev_sutb').val(data[i].svev_sutb); jq('#svev_sutc').val(data[i].svev_sutc);
				jq('#svev_sutd').val(data[i].svev_sutd); jq('#svev_sute').val(data[i].svev_sute);
				jq('#svev_sutf').val(data[i].svev_sutf);
				
				jq('#svev_tik1').val(data[i].svev_tik1); jq('#svev_tit1').val(data[i].svev_tit1); jq('#svev_tix1').val(data[i].svev_tix1);
				jq('#svev_tik2').val(data[i].svev_tik2); jq('#svev_tit2').val(data[i].svev_tit2); jq('#svev_tix2').val(data[i].svev_tix2);
				jq('#svev_tik3').val(data[i].svev_tik3); jq('#svev_tit3').val(data[i].svev_tit3); jq('#svev_tix3').val(data[i].svev_tix3);
				jq('#svev_tik4').val(data[i].svev_tik4); jq('#svev_tit4').val(data[i].svev_tit4); jq('#svev_tix4').val(data[i].svev_tix4);
				jq('#svev_tik5').val(data[i].svev_tik5); jq('#svev_tit5').val(data[i].svev_tit5); jq('#svev_tix5').val(data[i].svev_tix5);
				jq('#svev_tik6').val(data[i].svev_tik6); jq('#svev_tit6').val(data[i].svev_tit6); jq('#svev_tix6').val(data[i].svev_tix6);
				
				jq('#svev_godm').val(data[i].svev_godm); jq('#svev_kota').val(data[i].svev_kota); jq('#svev_kosl').val(data[i].svev_kosl);
				jq('#svev_god2').val(data[i].svev_god2); jq('#svev_kot2').val(data[i].svev_kot2); jq('#svev_kos2').val(data[i].svev_kos2);
				jq('#svev_god3').val(data[i].svev_god3); jq('#svev_kot3').val(data[i].svev_kot3); jq('#svev_kos3').val(data[i].svev_kos3);
				jq('#svev_god4').val(data[i].svev_god4); jq('#svev_kot4').val(data[i].svev_kot4); jq('#svev_kos4').val(data[i].svev_kos4);
				jq('#svev_god5').val(data[i].svev_god5); jq('#svev_kot5').val(data[i].svev_kot5); jq('#svev_kos5').val(data[i].svev_kos5);
				
				jq('#svev_co01').val(data[i].svev_co01); jq('#svev_co02').val(data[i].svev_co02); jq('#svev_co03').val(data[i].svev_co03);
				jq('#svev_co04').val(data[i].svev_co04); jq('#svev_co05').val(data[i].svev_co05); jq('#svev_co06').val(data[i].svev_co06);
				jq('#svev_co07').val(data[i].svev_co07); jq('#svev_co08').val(data[i].svev_co08); jq('#svev_co09').val(data[i].svev_co09);
				jq('#svev_co10').val(data[i].svev_co10); jq('#svev_co11').val(data[i].svev_co11); jq('#svev_co12').val(data[i].svev_co12);
				jq('#svev_co13').val(data[i].svev_co13); jq('#svev_co14').val(data[i].svev_co14); jq('#svev_co15').val(data[i].svev_co15);
				jq('#svev_co16').val(data[i].svev_co16); jq('#svev_co17').val(data[i].svev_co17); jq('#svev_co18').val(data[i].svev_co18);
				jq('#svev_co19').val(data[i].svev_co19); jq('#svev_co20').val(data[i].svev_co20); 
				
				jq('#svev_stva').val(data[i].svev_stva);jq('#svev_stva2').val(data[i].svev_stva2); 
				jq('#svev_fnkd').val(data[i].svev_fnkd); 
				jq('#svev_lagi').val(data[i].svev_lagi); 
				jq('#svev_komr').val(data[i].svev_komr);
				
				//drop downs
				jq('#svev_lagl').val(data[i].svev_lagl); 
				jq('#svev_lagt').val(data[i].svev_lagt);
				jq('#svev_call').val(data[i].svev_call);
				jq('#svev_betk').val(data[i].svev_betk);
				//bilagda handligar
				//alert(data[i].svev_bit1);
				jq('#svev_bit1').val(data[i].svev_bit1); jq('#svev_bii1').val(data[i].svev_bii1);
				jq('#svev_bit2').val(data[i].svev_bit2); jq('#svev_bii2').val(data[i].svev_bii2);
				jq('#svev_bit3').val(data[i].svev_bit3); jq('#svev_bii3').val(data[i].svev_bii3);
				jq('#svev_bit4').val(data[i].svev_bit4); jq('#svev_bii4').val(data[i].svev_bii4);
				jq('#svev_bit5').val(data[i].svev_bit5); jq('#svev_bii5').val(data[i].svev_bii5);
				jq('#svev_bit6').val(data[i].svev_bit6); jq('#svev_bii6').val(data[i].svev_bii6);
				jq('#svev_bit7').val(data[i].svev_bit7); jq('#svev_bii7').val(data[i].svev_bii7);
				jq('#svev_bit8').val(data[i].svev_bit8); jq('#svev_bii8').val(data[i].svev_bii8);
				jq('#svev_bit9').val(data[i].svev_bit9); jq('#svev_bii9').val(data[i].svev_bii9);
				
				//some aspects
				//1. We will show the user that there is more kolli antal in rubrik 31. (extraordinära uppgifter)
				if(jq('#svev_kot2').val() != "" || jq('#svev_kot3').val() != "" || jq('#svev_kot4').val() != "" || jq('#svev_kot5').val() != ""){
					jq('#kotaRubrik').css("color", "green");
				}
				
				jq('#svev_vata').focus();
			}
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	  }
	  	});
  	}
  	
  	//taric varukod search
  	function searchTaricVarukod() {
		jq(function() {
			jq.getJSON('searchTaricVarukod.do', {
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
		  jq('#svev_vata').val("");
		  //and populate (if applicable)
		  var key = jq('#taricVarukodList').val();
		  jq('#svev_vata').val(key);
		});
	});
  	
  	//calculate a net weight from the gross weight
  	jq(function() { 
	    jq('#svev_brut').blur(function() {
		  //init field(s)
    	  var grossWeight = jq('#svev_brut').val();
    	  if(grossWeight!=''){ grossWeight = grossWeight.replace(",","."); }
    	  grossWeight = Number(grossWeight);
    	  if(grossWeight < 1){
    		  if(jq('#svev_neto').val()==''){
				  var net = grossWeight * 0.9;
				  net = net.toString();
				  jq('#svev_neto').val(net.replace(".", ","));
			  }
    	  }else{
    		  if(jq('#svev_neto').val()==''){
				  var net = grossWeight * 0.9;
				  if(net>1 && Math.floor(net) != net){
					//Round the value in order to eliminate the decimal part
					net = Math.round(net);  
				  }	
				  net = net.toString();
				  jq('#svev_neto').val(net.replace(".", ","));
			  }
    		  
    		  /*OBSOLETE after DHL's meeting Larvik
    		  //Check if there are decimals...
    		  if(Math.floor(grossWeight) != grossWeight){ 
    			  var msg = "Fel, inga decimaler på vikter lika eller över 1 kg!";
    			  //alert(msg);
	    		  
    			  //Start dialog
    			  jq('<div></div>').dialog({
    		        modal: true,
    		        title: "Dialog - Error ",
    		        open: function() {
    			  		jq(this).html(msg);
    			  		
    			    },
    		        buttons: {
    				    Avbryt: function() {
    			            jq( this ).dialog( "close" );
    			            jq('#svev_brut').focus();
    			        }
    		        }
    			  });  //end dialog
    
	    	  }else{
	    		  if(jq('#svev_neto').val()==''){
					  var net = grossWeight * 0.9;
					  if(net>1 && Math.floor(net) != net){
						//Round the value in order to eliminate the decimal part
						net = Math.round(net);  
					  }	
					  net = net.toString();
					  jq('#svev_neto').val(net.replace(".", ","));
				  }
	    	  }
	    	  */
    	  }
		});
	    jq('#svev_neto').blur(function() {
	    	  
	    	  /*OBSOLETE after DHL's meeting Larvik
	    	  //init field(s)
	    	  var netWeight = jq('#svev_neto').val();
	    	  if(netWeight!=''){ netWeight = netWeight.replace(",","."); }
	    	  
	    	  netWeight = Number(netWeight);
	    	  if(netWeight < 1){
	    		  jq('#svev_neto').val(net.replace(".", ","));
	    	  }else{
	    		  //Check if there are decimals...
	    		  if(Math.floor(netWeight) != netWeight){ 
	    			  var msg = "Fel, inga decimaler på vikter lika eller över 1 kg!";
	    			  //alert(msg);
	    			  
	    			  //Start dialog
	    			  jq('<div></div>').dialog({
	    		        modal: true,
	    		        title: "Dialog - Error ",
	    		        open: function() {
	    				  	jq(this).html(msg);
	    			     },
	    			     buttons: {
	    			        Avbryt: function() {
	    			            jq( this ).dialog( "close" );
	    			            jq('#svev_neto').focus();
	    			        }
	    		        }
	    			  });  //end dialog
		    	  }
	    	  }
	    	  */
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
	
	
	//set item line into kundensvarureg.
  	function updateKundensVarReg(record) {
	  	var htmlValue = record.id;
	  	var applicationUserParam = jq('#applicationUser').val();
	  	var avdParam = jq('#avdItemList').val();
	  	var opdParam = jq('#opdItemList').val();
	  	
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getSpecificTopicItemChosenFromGuiElement.do',
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
				jq('#svew_knnr').val(jq('#senderId').val());
				jq('#svew_ulkd').val(data[i].svev_ulkd);
				jq('#svew_vata').val(data[i].svev_vata);
				jq('#svew_eup1').val(data[i].svev_eup1);
				jq('#description_svew').text(data[i].svev_vasl);
				//hidden fields
				jq('#svew_vasl').val(data[i].svev_vasl);
				jq('#svew_kota').val(data[i].svev_kota);
				jq('#svew_kosl').val(data[i].svev_kosl);
				jq('#svew_godm').val(data[i].svev_godm);
				jq('#svew_brut').val(data[i].svev_brut);
				jq('#svew_neto').val(data[i].svev_neto);
				jq('#svew_kono').val(data[i].svev_kono);
				jq('#svew_ankv').val(data[i].svev_ankv);
				jq('#svew_suko').val(data[i].svev_suko);
				jq('#svew_sutx').val(data[i].svev_sutx);
				jq('#svew_atin').val(data[i].svev_atin);
				jq('#svew_fabl').val(data[i].svev_fabl);
				jq('#svew_betk').val(data[i].svev_betk);
				jq('#svew_komr').val(data[i].svev_komr);
				jq('#svew_fnkd').val(data[i].svev_fnkd);
				jq('#svew_bit1').val(data[i].svev_bit1);
				jq('#svew_bii1').val(data[i].svev_bii1);
				jq('#svew_call').val(data[i].svev_call);
				
				//Start dialog
				//deal with buttons and attributes for this modal window
				jq('#dialogKundensVaruregister').dialog( "option", "title", "SPARA varukod i kundens varuregister" );
				jq('#dialogKundensVaruregister').dialog({
					 buttons: [ 
			            {
						 id: "dialogSaveTU",	
						 text: "Spara",
						 click: function(){
							 		setBlockUI();
			 		 		 		jq('#updateKundensVaruregisterForm').submit();
						 		}
					 	 },
			 	 		{
					 	 id: "dialogCancelTU",
					 	 text: "Avbryt", 
						 click: function(){
					 		 		jq( this ).dialog( "close" ); 
					 	 		} 
			 	 		 } ] 
				  });
				  //init values
				  jq("#dialogSaveTU").button("option", "disabled", true);
				  //open
				  jq('#dialogKundensVaruregister').dialog('open');
			}
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	  }
	  	});
  	}
  	//Events for the drop downs (some kind of "implicit validation" since some fields are mandatory)
    jq(function() {
  	  jq("#svew_knso").blur(function() {
  		  if(jq("#svew_knso").val()!='' && jq("#svew_knnr").val()!=''){
  			  jq("#dialogSaveTU").button("option", "disabled", false);
  		  }else{
  			  jq("#dialogSaveTU").button("option", "disabled", true);
  		  }
  	  });
  	  jq("#svew_knnr").blur(function() {
		  if(jq("#svew_knnr").val()!='' && jq("#svew_knso").val()!=''){
			  jq("#dialogSaveTU").button("option", "disabled", false);
		  }else{
			  jq("#dialogSaveTU").button("option", "disabled", true);
		  }
	  });
  	  
    });
	
	
    //========================================
    //Dialog Varupostkontroll in Autokontroll
    //========================================
    jq(function() {
  	  jq("#itemListControlButton").click(function() {

  		  
  		  jq("#dialogVarupostkontroll").dialog( "option", "title", "Varupostkontroll" );
  		  
  		  //deal with buttons for this modal window
  		  jq("#dialogVarupostkontroll").dialog({
  			 buttons: [ 
  	            {
  				 id: "dialogSaveTU",	
  				 text: "Gå vidare",
  				 click: function(){
  					 		jq('#varupostkontrollForm').submit();
  					 		setBlockUI();
  				 		}
  			 	 },
  	 	 		{
  			 	 id: "dialogCancelTU",
  			 	 text: "Avbryt", 
  				 click: function(){
  					 		//back to initial state of form elements on modal dialog
  					 		//jq("#dialogSaveSU").button("option", "disabled", true);
  					 		jq( this ).dialog( "close" ); 
  				 		} 
  	 	 		 } ] 
  		  });
  		  //init values
  		  //jq("#dialogSaveTU").button("option", "disabled", true);
  		  //open now
  		  jq("#dialogVarupostkontroll").dialog('open');
  		  
  	  });
    });
    //END dialog varupostkontroll
    
    
	
	//-------------------
    //Datatables jquery
    //-------------------
    //private function
    function filterGlobal () {
      jq('#tblItemLinesAll').dataTable().search(
      	jq('#tblItemLinesAll_filter').val()
      ).draw();
      
      jq('#tblItemLines').dataTable().search(
      	jq('#tblItemLines_filter').val()
      ).draw();
    }
	
	jq(document).ready(function() {
      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
      jq('#tblItemLinesAll').dataTable( {
    	  "dom": '<"top">t<"bottom"flip><"clear">',
    	  "scrollY":    "800px",
  		  //"scrollCollapse":  true,
  		  //"columnDefs": [{ "type": "num", "targets": 0 }],
  		  "order": [[ 9, "desc" ], [ 0, 'asc' ]],
  		  "autoWidth": false, //for optimization purposes when initializing the table
  		  "lengthMenu": [ 75, 100, 300, 400, 900]
  	  });
      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
      jq('#tblItemLines').dataTable( {
    	  "tabIndex": -1,
    	  "dom": '<"top">t<"bottom"fip><"clear">',
    	  "scrollY":    "180px",
  		  //"scrollCollapse":  true,
  		  //"columnDefs": [{ "type": "num", "targets": 1 }],
  		  "order": [[ 10, "desc" ], [ 1, 'asc' ]],
  		  "autoWidth": false, //for optimization purposes when initializing the table
  		  "lengthMenu": [ 20, 40, 100, 200, 300],
  		  "scroller": {
	        displayBuffer: 20
	      }
  	  });
      
      //event on input field for search
      jq('input.tblItemLines_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
      //event on input field for search
      jq('input.tblItemLinesAll_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
      
      	//Initialize Dialog for KundensVaruregister here
  		jq(function() { 
  		  jq("#dialogKundensVaruregister").dialog({
  			  autoOpen: false,
  			  maxWidth:600,
  	          maxHeight: 250,
  	          width: 600,
  	          height: 250,
  			  modal: true
  		  });
  		});
  		//Initialize Dialog for Varupostkontroll here
  		jq(function() { 
    		  jq("#dialogVarupostkontroll").dialog({
    			  autoOpen: false,
    			  maxWidth:500,
    	          maxHeight: 300,
    	          width: 400,
    	          height: 250,
    			  modal: true
    		  });
		});
  		
  		//buffering reasons. The datatable will collide with the form ... ugly scene in HTML
  		var nrOfLines = 0;
  		if(jq("#numberOfItemLinesInTopic").val()!=''){
  			nrOfLines = parseInt(jq("#numberOfItemLinesInTopic").val());
  		}	
  		//console.log(nrOfLines);
		if( nrOfLines < 30){
			jq('#svev_vata').focus();
		}
		
		jq('#warningCodesFlagDiv').hide();
	    jq('#warningCodesFlagDivBh').hide();
	  	
	    
	   
  	  	//to prevent hiding datepicker behind the autocomplete function
  	  	jq('.datepicker').on('click', function(e) {
  		   e.preventDefault();
  		   jq(this).attr("autocomplete", "off");  
  	  	});
	    
  	
	});

  	

  	
  	
	