	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	
  	jq(document).ready(function() {
  		jq('#warningCodesFlagDiv').hide();
  		
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
  		
  	});
  	
  	//Overlay on tab (to mark visually a delay...)
    jq(function() {
	  jq('#alinkMainList').click(function() { 
  		setBlockUI();
  	  });    	
  	  jq('#alinkInvoices').click(function() { 
  		setBlockUI();
  	  });
  	  jq('#alinkHeader').click(function() { 
  		setBlockUI();
  	  });
  	  jq('#alinkLogging').click(function() { 
  		setBlockUI();
	  });
  	  jq('#alinkArchive').click(function() { 
  		setBlockUI();
	  });
  	  
    });
    
  	//-----------------------------------------------------------------------------
  	//jQuery CALCULATOR (related to jquery.calculator.js and jquery.calculator.css
  	//-----------------------------------------------------------------------------
  	jq(function() {
  		jq('#sviv_fabl').calculator({ showOn: 'button',  
  			buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
  		jq('#sviv_brut').calculator({ showOn: 'button',  
  			buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
  		jq('#sviv_neto').calculator({ showOn: 'button',  
  			buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
  		jq('#sviv_kota').calculator({ showOn: 'button',  
  			buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
  		jq('#sviv_ankv').calculator({ showOn: 'button',  
  			buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
  	});
  	
  	//---------------------------------------
  	//Trigger points for "calculateAvgifter"
  	//---------------------------------------
  	jq(function() {
  		jq('#sviv_vata').blur(function() {
  			//Check for codes
  			getTillaggskoderOnBlur();
  		});
  		
  		jq('#sviv_fabl').blur(function() {
  			//(1) Calculate
  			calculateAvgifter();
  			//(2) Check for codes
  			getBilagdaHandlingarYkoder();
  		});
  			
  		//Button (Beräkna) calculate all avgifter (inkl. stat.värde)
  		jq('#calcAvgifterAjax').click(function() {
  			calculateAvgifter();
  		});
  		
  		jq('#sviv_stva').change(function() {
  			jq('#sviv_stva2').val(jq('#sviv_stva').val());
  			//calculateAvgifter();
  		});
  		
  	});
  	
  	
  	function getBilagdaHandlingarYkoder(element){
  		//jq('#tillaggskoderLink').attr('target','_blank');
  		//open child window
  		if(jq('#session_svih_avut').val()!='' && jq('#sviv_vata').val()!=''){
			jq.ajax({
				type: 'GET',
				url: 'getTillagskoder_TdsImport.do',
				data: { 	applicationUser : jq('#applicationUser').val(),
					countryCode : jq('#session_svih_avut').val(), 
					itemCode : jq('#sviv_vata').val() },
					dataType: 'json',
				success: function(data) {
					var len = data.length;
					//only when the list has something to show (at least 1-record)
		 			if(len>0){
		 				if(jq('#sviv_vati').val()==''){
		 					jq('#warningCodesFlagDiv').show();
		 					//Step 2 (getBilagdaHandlingarYkoder2 won't be necessary since we are already fast in this exception)
		 				}
		 			}else{
		 				jq('#warningCodesFlagDiv').hide();
		 				//Step 2  only if the getTillagskoder_TdsImport is OK
		 				getBilagdaHandlingarYkoder2();
		 			}
				},
				error: function() {
					alert('Error loading ...');
				}
			});
  		}
	}
  	
  	
  	//General events
  	jq(function() {
	  	jq( "#submit" ).click(function( event ) {
	  		setBlockUI();
	  	});
	  	//Show child window (if applicable)
  		jq('#warningCodesLink').click(function() {
  			window.open('tdsimport_edit_items_childwindow_codes.do?action=doInit&lk=' 
	 			+ jq('#session_svih_avut').val() + '&vata=' + jq('#sviv_vata').val() + '&fokd=' + jq('#sviv_fokd').val(), 
	 			"codesWin", "top=300px,left=450px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  		});
  		//Auto control - autoförtullning
	  	jq('#itemListControlButton').click(function() {
	  		setBlockUI();
			window.location = 'tdsimport_edit_items_autocontrol.do?sviv_syav='+ jq('#avd').val() + '&sviv_syop=' + jq('#opd').val() + '&fablAutoControl=' + jq('#fablAutoControl').val();
  		});
	  	
	  	//=====================================
	  	//START Child window for general codes
	  	//=====================================
	  	//Ursp.land
	  	jq('#sviv_ulkdIdLink').click(function() {
	    	jq('#sviv_ulkdIdLink').attr('target','_blank');
	    	window.open('tdsimport_edit_items_childwindow_generalcodes.do?action=doInit&type=GCY&ctype=sviv_ulkd', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sviv_ulkdIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sviv_ulkdIdLink').click();
			}
	    });	
	    //Förmånskod
	    jq('#sviv_fokdIdLink').click(function() {
	    	jq('#sviv_fokdIdLink').attr('target','_blank');
	    	window.open('tdsimport_edit_items_childwindow_generalcodes.do?action=doInit&type=FOR&ctype=sviv_fokd', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sviv_fokdIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sviv_fokdIdLink').click();
			}
	    });
	    //Förfar.1
	    jq('#sviv_eup1IdLink').click(function() {
	    	jq('#sviv_eup1IdLink').attr('target','_blank');
	    	window.open('tdsimport_edit_items_childwindow_generalcodes.do?action=doInit&type=FF1&ctype=sviv_eup1', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sviv_eup1IdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sviv_eup1IdLink').click();
			}
	    });
	    //Förfar.2
	    jq('#sviv_eup2IdLink').click(function() {
	    	jq('#sviv_eup2IdLink').attr('target','_blank');
	    	window.open('tdsimport_edit_items_childwindow_generalcodes.do?action=doInit&type=FFK&ctype=sviv_eup2', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sviv_eup2IdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sviv_eup2IdLink').click();
			}
	    });
	    //Kollislag
	    jq('#sviv_koslIdLink').click(function() {
	    	jq('#sviv_koslIdLink').attr('target','_blank');
	    	window.open('tdsimport_edit_items_childwindow_generalcodes.do?action=doInit&type=KLI&ctype=sviv_kosl', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sviv_koslIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sviv_koslIdLink').click();
			}
	    });
	    
	    //Avg.Beräkningar
	    jq('#avgBerakIdLink').click(function() {
	    	jq('#avgBerakIdLink').attr('target','_blank');
	    	window.open('tdsimport_edit_items_childwindow_generalcodes.do?action=doInit&type=CHN&ctype=', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#avgBerakIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#avgBerakIdLink').click();
			}
	    });
	    //Bilagda Handlingar
	    jq('#bilagdaHandIdLink').click(function() {
	    	jq('#bilagdaHandIdLink').attr('target','_blank');
	    	window.open('tdsimport_edit_items_childwindow_generalcodes.do?action=doInit&type=MCF&ctype=', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#bilagdaHandIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#bilagdaHandIdLink').click();
			}
	    });
	    //Särskilda Upplysningar
	    jq('#sarskildaUppIdLink').click(function() {
	    	jq('#sarskildaUppIdLink').attr('target','_blank');
	    	window.open('tdsimport_edit_items_childwindow_generalcodes.do?action=doInit&type=SAL&ctype=', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sarskildaUppIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sarskildaUppIdLink').click();
			}
	    });
	    //Särskilda Upplysningar 2
	    jq('#sarskildaUppIdLink2').click(function() {
	    	jq('#sarskildaUppIdLink2').attr('target','_blank');
	    	window.open('tdsimport_edit_items_childwindow_generalcodes.do?action=doInit&type=SAL&ctype=', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sarskildaUppIdLink2').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sarskildaUppIdLink2').click();
			}
	    });
	    //Tidigare Handlingar
	    jq('#tidigareHandlingarIdLink').click(function() {
	    	jq('#tidigareHandlingarIdLink').attr('target','_blank');
	    	window.open('tdsimport_edit_items_childwindow_generalcodes.do?action=doInit&type=THO&ctype=', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#tidigareHandlingarIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#tidigareHandlingarIdLink').click();
			}
	    });
	    
	    //Identifiering av lager - Landkod
	    jq('#sviv_laglIdLink').click(function() {
	    	jq('#sviv_laglIdLink').attr('target','_blank');
	    	window.open('tdsimport_edit_items_childwindow_generalcodes.do?action=doInit&type=GCY&ctype=sviv_lagl', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sviv_laglIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sviv_laglIdLink').click();
			}
	    });
	    //====================================
	  	// END Child window for general codes
	  	//====================================
	    
	    
	    //=====================================
	  	//START Child window for tulltaxa 
	  	//=====================================
	  	jq('#sviv_vataIdLink').click(function() {
	    	jq('#sviv_vataIdLink').attr('target','_blank');
	    	window.open('tdsimport_edit_items_childwindow_tulltaxa.do?action=doInit&vkod=' + jq('#sviv_vata').val() + '&caller=sviv_vata', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sviv_vataIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sviv_vataIdLink').click();
			}
	    });
	    //Kundens vareregister (cross-table for tulltaxa)
	    jq('#kundensVaruregisterControlButton').click(function() {
	    	jq('#kundensVaruregisterControlButton').attr('target','_blank');
	    	window.open('tdsimport_edit_items_childwindow_kundensvarereg.do?action=doInit&recId=' + jq('#receiverId').val(), "codeKundVareRegWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
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
  	
  	//----------------
  	//Get förmånskod 
  	//----------------
  	function getFormanskodOnBlurVata(element){
			//Only vid Create new... at the moment
  			if(jq('#lineNr').val()=='' || jq('#lineNr').val()=='Ny' ){
				//only if this mandatory params exists
				if(jq('#sviv_ulkd').val()!='' && jq('#sviv_vata').val()!='' ){
					//set some default related fields GUI values here
					//now call the ajax main function 
					jq.ajax({
						type: 'GET',
						url: 'getFormanskod_TdsImport.do',
						data: { 	applicationUser : jq('#applicationUser').val(),
							countryCode : jq('#sviv_ulkd').val(), 
							itemCode : jq('#sviv_vata').val() },
							dataType: 'json',
					success: function(data) {
							var len = data.length;
			 			for ( var i = 0; i < len; i++) {
			 				//Förmånskod
			 				jq('#sviv_fokd').val(data[i].fokd);
			 			}
			 		},
			 		error: function() {
				  	    alert('Error loading ...');
			 		}
					});
				}
			}
			
		}
  	//------------------------------------------------
  	//Get Tilläggskoder Child window (if applicable)
  	//------------------------------------------------
  	function getTillaggskoderOnBlur(element){
  		jq('#tillaggskoderLink').attr('target','_blank');
  		//open child window
  		if(jq('#session_svih_avut').val()!='' && jq('#sviv_vata').val()!=''){
			jq.ajax({
				type: 'GET',
				url: 'getTillagskoder_TdsImport.do',
				data: { 	applicationUser : jq('#applicationUser').val(),
					countryCode : jq('#session_svih_avut').val(), 
					itemCode : jq('#sviv_vata').val() },
					dataType: 'json',
				success: function(data) {
					var len = data.length;
					//only when the list has something to show (at least 1-record)
		 			if(len>0){
		 				if(jq('#sviv_vati').val()==''){
		 					jq('#warningCodesFlagDiv').show();
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
  	function getBilagdaHandlingarYkoder2(){
		jq('#bhandlingarYkoderLink').attr('target','_blank');
  		//open child window
  		if(jq('#session_svih_avut').val()!='' && jq('#sviv_vata').val()!=''){
			jq.ajax({
				type: 'GET',
				url: 'getBilagdaHandlingarYkoder_TdsImport.do',
				data: { 	applicationUser : jq('#applicationUser').val(),
					countryCode : jq('#session_svih_avut').val(), 
					itemCode : jq('#sviv_vata').val(),
					formansCode : jq('#sviv_fokd').val() },
					dataType: 'json',
				success: function(data) {
					var len = data.length;
					//only when the list has something to show (at least 1-record)
		 			if(len>0){
		 				if(userValuesExist()==true){
		 					jq('#warningCodesFlagDiv').hide();
		 				}else{
		 					jq('#warningCodesFlagDiv').show();
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
  	
  	function userValuesExist(){
  		var retval = false;
  		if(jq('#sviv_bit1').val()!='' && ( jq('#sviv_bit1').val().match("^Y") || jq('#sviv_bit1').val().match("^X")) ){
  			retval = true;
  		}
  		if(jq('#sviv_bit2').val()!='' && ( jq('#sviv_bit2').val().match("^Y") || jq('#sviv_bit2').val().match("^X")) ){
  			retval = true;
  		}
  		if(jq('#sviv_bit3').val()!='' && ( jq('#sviv_bit3').val().match("^Y") || jq('#sviv_bit3').val().match("^X")) ){
  			retval = true;
  		}
  		if(jq('#sviv_bit4').val()!='' && ( jq('#sviv_bit4').val().match("^Y") || jq('#sviv_bit4').val().match("^X")) ){
  			retval = true;
  		}
  		if(jq('#sviv_bit5').val()!='' && ( jq('#sviv_bit5').val().match("^Y") || jq('#sviv_bit5').val().match("^X")) ){
  			retval = true;
  		}
  		return retval;
  	}
  	
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
	  	
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getSpecificTopicItemChosenFromGuiElement_TdsImport.do',
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
				//alert(data[i].sviv_vasl);
				jq('#lineNr').val(""); jq('#lineNr').val(data[i].sviv_syli);
				jq('#sviv_syli').val(""); jq('#sviv_syli').val(data[i].sviv_syli);
				jq('#sviv_vano').val(""); jq('#sviv_vano').val(data[i].sviv_vano); 
				jq('#sviv_ulkd').val(""); jq('#sviv_ulkd').val(data[i].sviv_ulkd);
				jq('#sviv_vata').val(""); jq('#sviv_vata').val(data[i].sviv_vata);
				
				jq('#sviv_vati').val(""); jq('#sviv_vati').val(data[i].sviv_vati); 
				jq('#sviv_vat4').val(""); jq('#sviv_vat4').val(data[i].sviv_vat4);
				
				jq('#sviv_eup1').val(""); jq('#sviv_eup1').val(data[i].sviv_eup1); 
				jq('#sviv_eup2').val(""); jq('#sviv_eup2').val(data[i].sviv_eup2); 
				jq('#sviv_brut').val(""); jq('#sviv_brut').val(data[i].sviv_brut); 
				jq('#sviv_neto').val(""); jq('#sviv_neto').val(data[i].sviv_neto); 
				jq('#sviv_ankv').val(""); jq('#sviv_ankv').val(data[i].sviv_ankv); 
				jq('#sviv_vasl').val(""); jq('#sviv_vasl').val(data[i].sviv_vasl);
				jq('#sviv_vas2').val(""); jq('#sviv_vas2').val(data[i].sviv_vas2); 
				jq('#sviv_vas3').val(""); jq('#sviv_vas3').val(data[i].sviv_vas3);
				jq('#sviv_vas4').val(""); jq('#sviv_vas4').val(data[i].sviv_vas4);
				jq('#sviv_vas5').val(""); jq('#sviv_vas5').val(data[i].sviv_vas5);
				jq('#sviv_fokd').val(""); jq('#sviv_fokd').val(data[i].sviv_fokd); 
				jq('#sviv_fabl').val(""); jq('#sviv_fabl').val(data[i].sviv_fabl); 
				
				//Avgiftsberäkningar section
				jq('#sviva_abk1').val("");jq('#sviva_abk1').val(data[i].sviva_abk1);
				jq('#sviva_abg1').val("");jq('#sviva_abg1').val(data[i].sviva_abg1);
				jq('#sviva_abs1').val("");jq('#sviva_abs1').val(data[i].sviva_abs1);
				jq('#sviva_abx1').val("");jq('#sviva_abx1').val(data[i].sviva_abx1);
				jq('#sviva_abb1').val("");jq('#sviva_abb1').val(data[i].sviva_abb1);
				
				jq('#sviva_abk2').val("");jq('#sviva_abk2').val(data[i].sviva_abk2);
				jq('#sviva_abg2').val("");jq('#sviva_abg2').val(data[i].sviva_abg2);
				jq('#sviva_abs2').val("");jq('#sviva_abs2').val(data[i].sviva_abs2);
				jq('#sviva_abx2').val("");jq('#sviva_abx2').val(data[i].sviva_abx2);
				jq('#sviva_abb2').val("");jq('#sviva_abb2').val(data[i].sviva_abb2);
				
				jq('#sviva_abk3').val("");jq('#sviva_abk3').val(data[i].sviva_abk3);
				jq('#sviva_abg3').val("");jq('#sviva_abg3').val(data[i].sviva_abg3);
				jq('#sviva_abs3').val("");jq('#sviva_abs3').val(data[i].sviva_abs3);
				jq('#sviva_abx3').val("");jq('#sviva_abx3').val(data[i].sviva_abx3);
				jq('#sviva_abb3').val("");jq('#sviva_abb3').val(data[i].sviva_abb3);
				
				jq('#sviva_abk4').val("");jq('#sviva_abk4').val(data[i].sviva_abk4);
				jq('#sviva_abg4').val("");jq('#sviva_abg4').val(data[i].sviva_abg4);
				jq('#sviva_abs4').val("");jq('#sviva_abs4').val(data[i].sviva_abs4);
				jq('#sviva_abx4').val("");jq('#sviva_abx4').val(data[i].sviva_abx4);
				jq('#sviva_abb4').val("");jq('#sviva_abb4').val(data[i].sviva_abb4);
				
				jq('#sviva_abk5').val("");jq('#sviva_abk5').val(data[i].sviva_abk5);
				jq('#sviva_abg5').val("");jq('#sviva_abg5').val(data[i].sviva_abg5);
				jq('#sviva_abs5').val("");jq('#sviva_abs5').val(data[i].sviva_abs5);
				jq('#sviva_abx5').val("");jq('#sviva_abx5').val(data[i].sviva_abx5);
				jq('#sviva_abb5').val("");jq('#sviva_abb5').val(data[i].sviva_abb5);
				
				//Extra section
				jq('#sviv_suko').val("");jq('#sviv_suko').val(data[i].sviv_suko); 
				jq('#sviv_sutx').val("");jq('#sviv_sutx').val(data[i].sviv_sutx);  
				jq('#sviv_sut2').val("");jq('#sviv_sut2').val(data[i].sviv_sut2); 
				jq('#sviv_sut3').val("");jq('#sviv_sut3').val(data[i].sviv_sut3);
				jq('#sviv_sut4').val("");jq('#sviv_sut4').val(data[i].sviv_sut4); 
				jq('#sviv_sut5').val("");jq('#sviv_sut5').val(data[i].sviv_sut5);
				//
				jq('#sviv_suk6').val("");jq('#sviv_suk6').val(data[i].sviv_suk6); 
				jq('#sviv_sut6').val("");jq('#sviv_sut6').val(data[i].sviv_sut6);  
				jq('#sviv_sut7').val("");jq('#sviv_sut7').val(data[i].sviv_sut7); 
				jq('#sviv_sut8').val("");jq('#sviv_sut8').val(data[i].sviv_sut8);
				jq('#sviv_sut9').val("");jq('#sviv_sut9').val(data[i].sviv_sut9); 
				jq('#sviv_suta').val("");jq('#sviv_suta').val(data[i].sviv_suta);
				//
				jq('#sviv_sukb').val("");jq('#sviv_sukb').val(data[i].sviv_sukb); 
				jq('#sviv_sutb').val("");jq('#sviv_sutb').val(data[i].sviv_sutb);  
				jq('#sviv_sutc').val("");jq('#sviv_sutc').val(data[i].sviv_sutc); 
				jq('#sviv_sutd').val("");jq('#sviv_sutd').val(data[i].sviv_sutd);
				jq('#sviv_sute').val("");jq('#sviv_sute').val(data[i].sviv_sute); 
				jq('#sviv_sutf').val("");jq('#sviv_sutf').val(data[i].sviv_sutf);
				
				jq('#sviv_tik1').val("");jq('#sviv_tik1').val(data[i].sviv_tik1); 
				jq('#sviv_tit1').val("");jq('#sviv_tit1').val(data[i].sviv_tit1); 
				jq('#sviv_tix1').val("");jq('#sviv_tix1').val(data[i].sviv_tix1);
				jq('#sviv_tik2').val("");jq('#sviv_tik2').val(data[i].sviv_tik2); 
				jq('#sviv_tit2').val("");jq('#sviv_tit2').val(data[i].sviv_tit2); 
				jq('#sviv_tix2').val("");jq('#sviv_tix2').val(data[i].sviv_tix2);
				jq('#sviv_tik3').val("");jq('#sviv_tik3').val(data[i].sviv_tik3); 
				jq('#sviv_tit3').val("");jq('#sviv_tit3').val(data[i].sviv_tit3); 
				jq('#sviv_tix3').val("");jq('#sviv_tix3').val(data[i].sviv_tix3);
				jq('#sviv_tik4').val("");jq('#sviv_tik4').val(data[i].sviv_tik4); 
				jq('#sviv_tit4').val("");jq('#sviv_tit4').val(data[i].sviv_tit4); 
				jq('#sviv_tix4').val("");jq('#sviv_tix4').val(data[i].sviv_tix4);
				jq('#sviv_tik5').val("");jq('#sviv_tik5').val(data[i].sviv_tik5); 
				jq('#sviv_tit5').val("");jq('#sviv_tit5').val(data[i].sviv_tit5); 
				jq('#sviv_tix5').val("");jq('#sviv_tix5').val(data[i].sviv_tix5);
				jq('#sviv_tik6').val("");jq('#sviv_tik6').val(data[i].sviv_tik6); 
				jq('#sviv_tit6').val("");jq('#sviv_tit6').val(data[i].sviv_tit6); 
				jq('#sviv_tix6').val("");jq('#sviv_tix6').val(data[i].sviv_tix6);
				
				jq('#sviv_godm').val("");jq('#sviv_godm').val(data[i].sviv_godm); 
				jq('#sviv_kota').val("");jq('#sviv_kota').val(data[i].sviv_kota); 
				jq('#sviv_kosl').val("");jq('#sviv_kosl').val(data[i].sviv_kosl);
				jq('#sviv_god2').val("");jq('#sviv_god2').val(data[i].sviv_god2); 
				jq('#sviv_kot2').val("");jq('#sviv_kot2').val(data[i].sviv_kot2); 
				jq('#sviv_kos2').val("");jq('#sviv_kos2').val(data[i].sviv_kos2);
				jq('#sviv_god3').val("");jq('#sviv_god3').val(data[i].sviv_god3); 
				jq('#sviv_kot3').val("");jq('#sviv_kot3').val(data[i].sviv_kot3); 
				jq('#sviv_kos3').val("");jq('#sviv_kos3').val(data[i].sviv_kos3);
				jq('#sviv_god4').val("");jq('#sviv_god4').val(data[i].sviv_god4); 
				jq('#sviv_kot4').val("");jq('#sviv_kot4').val(data[i].sviv_kot4); 
				jq('#sviv_kos4').val("");jq('#sviv_kos4').val(data[i].sviv_kos4);
				jq('#sviv_god5').val("");jq('#sviv_god5').val(data[i].sviv_god5); 
				jq('#sviv_kot5').val("");jq('#sviv_kot5').val(data[i].sviv_kot5); 
				jq('#sviv_kos5').val("");jq('#sviv_kos5').val(data[i].sviv_kos5);
				
				jq('#sviv_co01').val("");jq('#sviv_co01').val(data[i].sviv_co01); 
				jq('#sviv_co02').val("");jq('#sviv_co02').val(data[i].sviv_co02); 
				jq('#sviv_co03').val("");jq('#sviv_co03').val(data[i].sviv_co03);
				jq('#sviv_co04').val("");jq('#sviv_co04').val(data[i].sviv_co04); 
				jq('#sviv_co05').val("");jq('#sviv_co05').val(data[i].sviv_co05); 
				jq('#sviv_co06').val("");jq('#sviv_co06').val(data[i].sviv_co06);
				jq('#sviv_co07').val("");jq('#sviv_co07').val(data[i].sviv_co07); 
				jq('#sviv_co08').val("");jq('#sviv_co08').val(data[i].sviv_co08); 
				jq('#sviv_co09').val("");jq('#sviv_co09').val(data[i].sviv_co09);
				jq('#sviv_co10').val("");jq('#sviv_co10').val(data[i].sviv_co10); 
				jq('#sviv_co11').val("");jq('#sviv_co11').val(data[i].sviv_co11); 
				jq('#sviv_co12').val("");jq('#sviv_co12').val(data[i].sviv_co12);
				jq('#sviv_co13').val("");jq('#sviv_co13').val(data[i].sviv_co13); 
				jq('#sviv_co14').val("");jq('#sviv_co14').val(data[i].sviv_co14); 
				jq('#sviv_co15').val("");jq('#sviv_co15').val(data[i].sviv_co15);
				jq('#sviv_co16').val("");jq('#sviv_co16').val(data[i].sviv_co16); 
				jq('#sviv_co17').val("");jq('#sviv_co17').val(data[i].sviv_co17); 
				jq('#sviv_co18').val("");jq('#sviv_co18').val(data[i].sviv_co18);
				jq('#sviv_co19').val("");jq('#sviv_co19').val(data[i].sviv_co19); 
				jq('#sviv_co20').val("");jq('#sviv_co20').val(data[i].sviv_co20); 
				
				jq('#sviv_stva').val("");jq('#sviv_stva').val(data[i].sviv_stva);
				jq('#sviv_stva2').val("");jq('#sviv_stva2').val(data[i].sviv_stva2); 
				jq('#sviv_fnkd').val("");jq('#sviv_fnkd').val(data[i].sviv_fnkd); 
				jq('#sviv_lagi').val("");jq('#sviv_lagi').val(data[i].sviv_lagi); 
				jq('#sviv_komr').val("");jq('#sviv_komr').val(data[i].sviv_komr);
				//drop downs
				jq('#sviv_lagl').val("");jq('#sviv_lagl').val(data[i].sviv_lagl); 
				jq('#sviv_lagt').val("");jq('#sviv_lagt').val(data[i].sviv_lagt);
				jq('#sviv_call').val("");jq('#sviv_call').val(data[i].sviv_call);
				jq('#sviv_betk').val("");jq('#sviv_betk').val(data[i].sviv_betk);
				//bilagda handligar
				jq('#sviv_bit1').val("");jq('#sviv_bit1').val(data[i].sviv_bit1); 
				jq('#sviv_bii1').val("");jq('#sviv_bii1').val(data[i].sviv_bii1);
				jq('#sviv_bit2').val("");jq('#sviv_bit2').val(data[i].sviv_bit2); 
				jq('#sviv_bii2').val("");jq('#sviv_bii2').val(data[i].sviv_bii2);
				jq('#sviv_bit3').val("");jq('#sviv_bit3').val(data[i].sviv_bit3); 
				jq('#sviv_bii3').val("");jq('#sviv_bii3').val(data[i].sviv_bii3);
				jq('#sviv_bit4').val("");jq('#sviv_bit4').val(data[i].sviv_bit4); 
				jq('#sviv_bii4').val("");jq('#sviv_bii4').val(data[i].sviv_bii4);
				jq('#sviv_bit5').val("");jq('#sviv_bit5').val(data[i].sviv_bit5); 
				jq('#sviv_bii5').val("");jq('#sviv_bii5').val(data[i].sviv_bii5);
				jq('#sviv_bit6').val("");jq('#sviv_bit6').val(data[i].sviv_bit6); 
				jq('#sviv_bii6').val("");jq('#sviv_bii6').val(data[i].sviv_bii6);
				jq('#sviv_bit7').val("");jq('#sviv_bit7').val(data[i].sviv_bit7); 
				jq('#sviv_bii7').val("");jq('#sviv_bii7').val(data[i].sviv_bii7);
				jq('#sviv_bit8').val("");jq('#sviv_bit8').val(data[i].sviv_bit8); 
				jq('#sviv_bii8').val("");jq('#sviv_bii8').val(data[i].sviv_bii8);
				jq('#sviv_bit9').val("");jq('#sviv_bit9').val(data[i].sviv_bit9); 
				jq('#sviv_bii9').val("");jq('#sviv_bii9').val(data[i].sviv_bii9);
				
				//some aspects
				//1. We will show the user that there is more kolli antal in rubrik 31. (extraordinära uppgifter)
				if(jq('#sviv_kot2').val() != "" || jq('#sviv_kot3').val() != "" || jq('#sviv_kot4').val() != "" || jq('#sviv_kot5').val() != ""){
					jq('#kotaRubrik').css("color", "green");
				}
				//extra behavior in other fields
				//jq('#calcAvgifterAjax').show();
				
				jq('#sviv_vata').focus();
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
			jq.getJSON('searchTaricVarukod_TdsImport.do', {
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
		  jq('#sviv_vata').val("");
		  //and populate (if applicable)
		  var key = jq('#taricVarukodList').val();
		  jq('#sviv_vata').val(key);
		  			  
		});
	});
  	
  	
  	function deleteAvgifter() {
		//Avgiftsberäkningar section erase values
		jq('#sviva_abk1').val('');jq('#sviva_abg1').val('');
		jq('#sviva_abs1').val('');jq('#sviva_abx1').val('');
		jq('#sviva_abb1').val('');
		
		jq('#sviva_abk2').val('');jq('#sviva_abg2').val('');
		jq('#sviva_abs2').val('');jq('#sviva_abx2').val('');
		jq('#sviva_abb2').val('');
		
		jq('#sviva_abk3').val('');jq('#sviva_abg3').val('');
		jq('#sviva_abs3').val('');jq('#sviva_abx3').val('');
		jq('#sviva_abb3').val('');
		
		jq('#sviva_abk4').val('');jq('#sviva_abg4').val('');
		jq('#sviva_abs4').val('');jq('#sviva_abx4').val('');
		jq('#sviva_abb4').val('');
		
		jq('#sviva_abk5').val('');jq('#sviva_abg5').val('');
		jq('#sviva_abs5').val('');jq('#sviva_abx5').val('');
		jq('#sviva_abb5').val('');
  	}
  	
  	//CALCULATE Avgifter via AJAX Controller here
  	function calculateAvgifter() {
  		var svihStrParam = "svih_vufr=" + jq('#session_svih_vufr').val() + "&" + "svih_vuva=" + jq('#session_svih_vuva').val() + "&" + 
						"svih_vuku=" + jq('#session_svih_vuku').val() + "&" + "svih_vufo=" + jq('#session_svih_vufo').val() + "&" + 	
						"svih_ovko=" + jq('#session_svih_ovko').val() + "&" + "svih_kara=" + jq('#session_svih_kara').val() + "&" + 	
						"svih_anra=" + jq('#session_svih_anra').val() + "&" + "svih_lekd=" + jq('#session_svih_lekd').val() + "&"	+
						"svih_vakd=" + jq('#session_svih_vakd').val() + "&" + "svih_vaku=" + jq('#session_svih_vaku').val() + "&" + 	
						"svih_fabl=" + jq('#session_svih_fabl').val() + "&"; 

		var svivStrParam = "sviv_vata=" + jq('#sviv_vata').val() + "&" + "sviv_ulkd=" + jq('#sviv_ulkd').val() + "&" + 	
						"sviv_fokd=" + jq('#sviv_fokd').val() + "&" + "sviv_eup1=" + jq('#sviv_eup1').val() + "&" + 	
						"sviv_ankv=" + jq('#sviv_ankv').val() + "&" + "sviv_stva=" + jq('#sviv_stva').val() + "&" + 	
						"sviv_stva2=" + jq('#sviv_stva2').val() + "&" + "sviv_fabl=" + jq('#sviv_fabl').val();	
		
		jq.ajax({
			type: 'GET',
			url: 'calculateAvgifter_TdsImport.do',
			data: { 	applicationUser : jq('#applicationUser').val(),
					svihStr : svihStrParam, 
				  	svivStr : svivStrParam },
			dataType: 'json',
			success: function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					
					//alert(data[i].debugPrintlnAjax);
					//debug info
					jq('#debugPrintlnAjaxInfo').text(data[i].debugPrintlnAjax);
					
					//tull och statistiskt värde
					jq('#sviv_stva').val(data[i].sviv_stva);jq('#sviv_stva2').val(data[i].sviv_stva2);
					//alert(callerType);
					
					//Avgiftsberäkningar section
					jq('#sviva_abk1').val(data[i].sviva_abk1);jq('#sviva_abg1').val(data[i].sviva_abg1);
					jq('#sviva_abs1').val(data[i].sviva_abs1);jq('#sviva_abx1').val(data[i].sviva_abx1);
					jq('#sviva_abb1').val(data[i].sviva_abb1);
					
					jq('#sviva_abk2').val(data[i].sviva_abk2);jq('#sviva_abg2').val(data[i].sviva_abg2);
					jq('#sviva_abs2').val(data[i].sviva_abs2);jq('#sviva_abx2').val(data[i].sviva_abx2);
					jq('#sviva_abb2').val(data[i].sviva_abb2);
					
					jq('#sviva_abk3').val(data[i].sviva_abk3);jq('#sviva_abg3').val(data[i].sviva_abg3);
					jq('#sviva_abs3').val(data[i].sviva_abs3);jq('#sviva_abx3').val(data[i].sviva_abx3);
					jq('#sviva_abb3').val(data[i].sviva_abb3);
					
					jq('#sviva_abk4').val(data[i].sviva_abk4);jq('#sviva_abg4').val(data[i].sviva_abg4);
					jq('#sviva_abs4').val(data[i].sviva_abs4);jq('#sviva_abx4').val(data[i].sviva_abx4);
					jq('#sviva_abb4').val(data[i].sviva_abb4);
					
					jq('#sviva_abk5').val(data[i].sviva_abk5);jq('#sviva_abg5').val(data[i].sviva_abg5);
					jq('#sviva_abs5').val(data[i].sviva_abs5);jq('#sviva_abx5').val(data[i].sviva_abx5);
					jq('#sviva_abb5').val(data[i].sviva_abb5);
					
				}
			}
		});
  	}
  	
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
			    	jq('#sviv_vata').val(""); 
				//now populate (if applicable)
			    	var key = jq('#taricVarukodList').val();
			    	jq('#sviv_vata').val(key); 
		    	}
	    });
	    
	});
		
	
	//----------------------
	//get bilagda handlingar
	// (new item line)
	//----------------------
  	jq(function() {
  		jq('#sviv_eup1').change(function() {
  			//only with create-new record
  			//alert(jq('#lineNr').val());
  			if(jq('#lineNr').val()==null || jq('#lineNr').val()=='' ){
  				//only with sviv_eup1=4200 (momsfria varor = vat free items)
  				if(jq('#sviv_eup1').val()=='4200' ){
  					//set some default related fields GUI values here
  					//now call the ajax main function 
			    		jq.ajax({
			  	  	  type: 'GET',
			  	  	  url: 'getBilagdaHandlingar_TdsImport.do',
			  	  	  data: { 	applicationUser : jq('#applicationUser').val(),
			  	  		  		avd : jq('#model_avd').val(), 
			  	  		  	  	opd : jq('#model_opd').val() },
			  	  	  dataType: 'json',
			  	  	  success: function(data) {
			  	  		var len = data.length;
			  			for ( var i = 0; i < len; i++) {
			  				//Bilagda handlingar
			  				jq('#sviv_bit2').val(data[i].bit1); //sviv_bit1 and sviv_bii1 are reserved for [svih_fatx and svih_faty from header]
			  				jq('#sviv_bit3').val(data[i].bit2);jq('#sviv_bit4').val(data[i].bit3);
			  				jq('#sviv_bit5').val(data[i].bit4);jq('#sviv_bit6').val(data[i].bit5);
			  				jq('#sviv_bit7').val(data[i].bit6);jq('#sviv_bit8').val(data[i].bit7);
			  				jq('#sviv_bit9').val(data[i].bit8);
			  				
			  				jq('#sviv_bii2').val(data[i].bii1);
			  				jq('#sviv_bii3').val(data[i].bii2);jq('#sviv_bii4').val(data[i].bii3);
			  				jq('#sviv_bii5').val(data[i].bii4);jq('#sviv_bii5').val(data[i].bii5);
			  				jq('#sviv_bii7').val(data[i].bii6);jq('#sviv_bii7').val(data[i].bii7);
			  				jq('#sviv_bii9').val(data[i].bii8);
			  			}
			  	  	  }
			    		});
  				}else{
  					jq('#sviv_bit2').val('');jq('#sviv_bii2').val('');
  					jq('#sviv_bit3').val('');jq('#sviv_bii3').val('');
  					jq('#sviv_bit4').val('');jq('#sviv_bii4').val('');
  					jq('#sviv_bit5').val('');jq('#sviv_bii5').val('');
  					jq('#sviv_bit6').val('');jq('#sviv_bii6').val('');
  					jq('#sviv_bit7').val('');jq('#sviv_bii7').val('');
  					jq('#sviv_bit8').val('');jq('#sviv_bii8').val('');
  					jq('#sviv_bit9').val('');jq('#sviv_bii9').val('');
  					//some GUI elements
  					if(jq('#sviv_suko').val()=='SE200' ){
  						jq('#sviv_suko').val("");
  					}
  				}
  			}else{
  				if(jq('#sviv_eup1').val()=='4200' ){
  					//set some default related fields GUI values here
  					//jq('#sviv_suko').val("SE200"); //SE200=momsfritt //...bortkommenterad pga DHL Norges Anders Martin önskemål... 16.Mar.2015
  				}else{
  					if(jq('#sviv_suko').val()=='SE200' ){
  						jq('#sviv_suko').val("");
  					}
  				}
  			}
		});
	});
	
  	//validate gross and net weight. Calculate net weight also
  	jq(function() { 
	    jq('#sviv_brut').blur(function() {
		  //init field(s)
    	  var grossWeight = jq('#sviv_brut').val();
    	  if(grossWeight!=''){ grossWeight = grossWeight.replace(",","."); }
    	  grossWeight = Number(grossWeight);
    	  if(grossWeight < 1){
    		  if(jq('#sviv_neto').val()==''){
				  var net = grossWeight * 0.9;
				  net = net.toString();
				  jq('#sviv_neto').val(net.replace(".", ","));
			  }
    	  }else{
    		  if(jq('#sviv_neto').val()==''){
				  var net = grossWeight * 0.9;
				  if(net>1 && Math.floor(net) != net){
					//Round the value in order to eliminate the decimal part
					net = Math.round(net);  
				  }	  
				  net = net.toString();
				  jq('#sviv_neto').val(net.replace(".", ","));
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
    			            jq('#sviv_brut').focus();
    			        }
    		        }
    		        
    			  });  //end dialog
    			  
    			  
	    	  }else{
	    		  if(jq('#sviv_neto').val()==''){
					  var net = grossWeight * 0.9;
					  if(net>1 && Math.floor(net) != net){
						//Round the value in order to eliminate the decimal part
						net = Math.round(net);  
					  }	  
					  net = net.toString();
					  jq('#sviv_neto').val(net.replace(".", ","));
				  }
	    	  }
	    	  */
    	  }
		});
	    jq('#sviv_neto').blur(function() {
	    	  
	    	  /*OBSOLETE after DHL's meeting Larvik
	    	  //init field(s)
	    	  var netWeight = jq('#sviv_neto').val();
	    	  if(netWeight!=''){ netWeight = netWeight.replace(",","."); }
	    	  
	    	  netWeight = Number(netWeight);
	    	  if(netWeight < 1){
	    		  jq('#sviv_neto').val(net.replace(".", ","));
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
	    			            jq('#sviv_neto').focus();
	    			        }
	    		        }
	    			  });  //end dialog
		    	  }
	    	  }
	    	  */
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
	  	  url: 'getSpecificTopicItemChosenFromGuiElement_TdsImport.do',
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
				jq('#sviw_knnr').val(jq('#receiverId').val());
				jq('#sviw_ulkd').val(data[i].sviv_ulkd);
				jq('#sviw_vata').val(data[i].sviv_vata);
				jq('#sviw_fokd').val(data[i].sviv_fokd);
				jq('#sviw_eup1').val(data[i].sviv_eup1);
				jq('#description_sviw').text(data[i].sviv_vasl);
				//hidden fields
				jq('#sviw_vasl').val(data[i].sviv_vasl);
				jq('#sviw_kota').val(data[i].sviv_kota);
				jq('#sviw_kosl').val(data[i].sviv_kosl);
				jq('#sviw_godm').val(data[i].sviv_godm);
				jq('#sviw_brut').val(data[i].sviv_brut);
				jq('#sviw_neto').val(data[i].sviv_neto);
				jq('#sviw_kono').val(data[i].sviv_kono);
				jq('#sviw_ankv').val(data[i].sviv_ankv);
				jq('#sviw_suko').val(data[i].sviv_suko);
				jq('#sviw_sutx').val(data[i].sviv_sutx);
				jq('#sviw_atin').val(data[i].sviv_atin);
				jq('#sviw_fabl').val(data[i].sviv_fabl);
				jq('#sviw_bit1').val(data[i].sviv_bit1);
				jq('#sviw_bii1').val(data[i].sviv_bii1);
				jq('#sviw_call').val(data[i].sviv_call);
				
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
  	  jq("#sviw_knso").blur(function() {
  		  if(jq("#sviw_knso").val()!='' && jq("#sviw_knnr").val()!=''){
  			  jq("#dialogSaveTU").button("option", "disabled", false);
  		  }else{
  			  jq("#dialogSaveTU").button("option", "disabled", true);
  		  }
  	  });
  	  jq("#sviw_knnr").blur(function() {
		  if(jq("#sviw_knnr").val()!='' && jq("#sviw_knso").val()!=''){
			  jq("#dialogSaveTU").button("option", "disabled", false);
		  }else{
			  jq("#dialogSaveTU").button("option", "disabled", true);
		  }
	  });
  	  
    });
  	
  	
  	
  	
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
	  		  "scrollCollapse":  true,
	  		  "order": [[ 11, "desc" ], [ 0, 'asc' ]],
	  		  "lengthMenu": [ 75, 100, 300, 400, 900]
	  	  });
	      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
	      jq('#tblItemLines').dataTable( {
	    	  "tabIndex": -1,
	    	  "dom": '<"top">t<"bottom"fip><"clear">',
	    	  "scrollY":    "180px",
	  		  "scrollCollapse":  true,
	  		  "order": [[ 12, "desc" ], [ 1, 'asc' ]],
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
	      
	      
	      //
	  	 jq('#sviv_ulkd').focus();
	  	
  		});
  	
	