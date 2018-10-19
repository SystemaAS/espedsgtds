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
  		  dateFormat: 'yymmdd' 
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
					
					
					
				}
				
			});
	    });
	});
	
	
	//-------------------------------------------
	//Avgångs-Tullkontor AJAX [NCTS Export]
	//-------------------------------------------
	//FETCH Tullkontor
	function searchAvgangTullkontorOwnWindow(){
		//init the tullkontor object in javascript (will be put into a map)
	  	var tullkontor = new Object();
	  	//fields
	  	tullkontor.tkkode = "";tullkontor.tktxtn = "";
	  	
		jq(function(){
			//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('searchUtfartsTullkontor.do', {
				applicationUser : jq('#applicationUser').val(),
				tullkontorName : jq('#search_sveh_utfa').val(),
				tullkontorCode : jq('#search_sveh_utfa_Code').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].tkkode + '">' + data[i].tkkode + '&nbsp;&nbsp;' + data[i].tktxtn + '</option>';
					tullkontor = new Object();
					tullkontor.tkkode = data[i].tkkode;
					tullkontor.tktxtn = data[i].tktxtn;
					//put the object in map now with customerNumber as key
					map[tullkontor.tkkode] = tullkontor;
				}
				//now that we have our options, give them to our select
				jq('#tullkontorListAvgang').html(html);
			});
		})
		
	}
	//BestämmelseTullkontor list
	jq(function() { 
	    jq('#tullkontorListAvgang').change(function() {
	    	jq('#thcats').val(""); //ncts export
	    	
		//now populate (if applicable)
	    	var key = jq('#tullkontorListAvgang').val();
	    	jq('#thcats').val(key); //ncts export
	    	
	    });
	});
	
	
	//----------------------------------
	//Events Avgångstullkontor (SEARCH window)
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
				jq(searchAvgangTullkontorOwnWindow);
			}			
    		});
		jq('#search_sveh_utfa_Code').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchAvgangTullkontorOwnWindow);
			}			
    		});
	});

	
	//-------------------------------------------
	//Bestämmelses-Tullkontor AJAX [NCTS Export]
	//-------------------------------------------
	//FETCH Tullkontor
	function searchBestamTullkontorOwnWindow(){
		//init the tullkontor object in javascript (will be put into a map)
	  	var tullkontor = new Object();
	  	//fields
	  	tullkontor.tkkode = "";tullkontor.tktxtn = "";
	  	
		jq(function(){
			//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('searchUtfartsTullkontor.do', {
				applicationUser : jq('#applicationUser').val(),
				tullkontorName : jq('#search_sveh_utfa_bestam').val(),
				tullkontorCode : jq('#search_sveh_utfa_Code_bestam').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].tkkode + '">' + data[i].tkkode + '&nbsp;&nbsp;' + data[i].tktxtn + '</option>';
					tullkontor = new Object();
					tullkontor.tkkode = data[i].tkkode;
					tullkontor.tktxtn = data[i].tktxtn;
					//put the object in map now with customerNumber as key
					map[tullkontor.tkkode] = tullkontor;
				}
				//now that we have our options, give them to our select
				jq('#tullkontorListBestam').html(html);
			});
		})
		
	}
	//BestämmelseTullkontor list
	jq(function() { 
	    jq('#tullkontorListBestam').change(function() {
	    	jq('#thtsb').val(""); //ncts export
	    	
		//now populate (if applicable)
	    	var key = jq('#tullkontorListBestam').val();
	    	jq('#thtsb').val(key); //ncts export
	    	
	    });
	});
	
	
	//----------------------------------
	//Events TullkontorTransit (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgBestamstullKontor').click(function(){
    			jq("#search_sveh_utfa_bestam").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_utfa_bestam').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchBestamTullkontorOwnWindow);
			}			
    		});
		jq('#search_sveh_utfa_Code_bestam').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchBestamTullkontorOwnWindow);
			}			
    		});
	});
	


	//--------------------------------------------------------------------------
	//Transit-Tullkontor AJAX [NCTS Export] - Can be up to 8 transittullkontor
	//--------------------------------------------------------------------------
	//FETCH Transittullkontor 01
	function searchTransitTullkontor01_OwnWindow(){
		//init the tullkontor object in javascript (will be put into a map)
	  	var tullkontor = new Object();
	  	//fields
	  	tullkontor.tkkode = "";tullkontor.tktxtn = "";
	  	
		jq(function(){
			//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('searchUtfartsTullkontor.do', {
				applicationUser : jq('#applicationUser').val(),
				tullkontorName : jq('#search_sveh_utfa_transit01').val(),
				tullkontorCode : jq('#search_sveh_utfa_Code_transit01').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].tkkode + '">' + data[i].tkkode + '&nbsp;&nbsp;' + data[i].tktxtn + '</option>';
					tullkontor = new Object();
					tullkontor.tkkode = data[i].tkkode;
					tullkontor.tktxtn = data[i].tktxtn;
					//put the object in map now with customerNumber as key
					map[tullkontor.tkkode] = tullkontor;
				}
				//now that we have our options, give them to our select
				jq('#tullkontorListTransit01').html(html);
			});
		})
		
	}
	//Transittullkontor 01 list
	jq(function() { 
	    jq('#tullkontorListTransit01').change(function() {
	    	jq('#thtsd1').val(""); //ncts export
	    	
		//now populate (if applicable)
	    	var key = jq('#tullkontorListTransit01').val();
	    	jq('#thtsd1').val(key); //ncts export
	    	
	    });
	});
	
	//On Keypress (13)
	jq(function() { 
	    jq('#tullkontorListTransit01').keypress(function() {
		    	if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#thtsd1').val(""); //ncts export
				//now populate (if applicable)
			    	var key = jq('#tullkontorListTransit01').val();
			    	jq('#thtsd1').val(key); //ncts export
		    	}
	    });
	    
	});
	
	//----------------------------------
	//Events TullkontorTransit (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgTullkontorListTransit01').click(function(){
    			jq("#search_sveh_utfa_transit01").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_utfa_transit01').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor01_OwnWindow);
			}			
    		});
		jq('#search_sveh_utfa_Code_transit01').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor01_OwnWindow);
			}			
    		});
	});
	
	
	//--------------------------------------------------------------------------
	//Transit-Tullkontor AJAX [NCTS Export] - Can be up to 8 transittullkontor
	//--------------------------------------------------------------------------
	//FETCH Transittullkontor 02
	function searchTransitTullkontor02_OwnWindow(){
		//init the tullkontor object in javascript (will be put into a map)
	  	var tullkontor = new Object();
	  	//fields
	  	tullkontor.tkkode = "";tullkontor.tktxtn = "";
	  	
		jq(function(){
			//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('searchUtfartsTullkontor.do', {
				applicationUser : jq('#applicationUser').val(),
				tullkontorName : jq('#search_sveh_utfa_transit02').val(),
				tullkontorCode : jq('#search_sveh_utfa_Code_transit02').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].tkkode + '">' + data[i].tkkode + '&nbsp;&nbsp;' + data[i].tktxtn + '</option>';
					tullkontor = new Object();
					tullkontor.tkkode = data[i].tkkode;
					tullkontor.tktxtn = data[i].tktxtn;
					//put the object in map now with customerNumber as key
					map[tullkontor.tkkode] = tullkontor;
				}
				//now that we have our options, give them to our select
				jq('#tullkontorListTransit02').html(html);
			});
		})
		
	}
	//Transittullkontor 02 list
	jq(function() { 
	    jq('#tullkontorListTransit02').change(function() {
	    	jq('#thtsd2').val(""); //ncts export
	    	
			//now populate (if applicable)
	    	var key = jq('#tullkontorListTransit02').val();
	    	jq('#thtsd2').val(key); //ncts export
	    	
	    });
	});
	
	//On Keypress (13)
	jq(function() { 
	    jq('#tullkontorListTransit02').keypress(function() {
		    	if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#thtsd2').val(""); //ncts export
				//now populate (if applicable)
			    	var key = jq('#tullkontorListTransit02').val();
			    	jq('#thtsd2').val(key); //ncts export
		    	}
	    });
	    
	});
	
	//----------------------------------
	//Events TullkontorTransit (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgTullkontorListTransit02').click(function(){
    			jq("#search_sveh_utfa_transit02").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_utfa_transit02').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor02_OwnWindow);
			}			
    		});
		jq('#search_sveh_utfa_Code_transit02').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor02_OwnWindow);
			}			
    		});
	});
	
	

	
	
	//--------------------------------------------------------------------------
	//Transit-Tullkontor AJAX [NCTS Export] - Can be up to 8 transittullkontor
	//--------------------------------------------------------------------------
	//FETCH Transittullkontor 03
	function searchTransitTullkontor03_OwnWindow(){
		//init the tullkontor object in javascript (will be put into a map)
	  	var tullkontor = new Object();
	  	//fields
	  	tullkontor.tkkode = "";tullkontor.tktxtn = "";
	  	
		jq(function(){
			//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('searchUtfartsTullkontor.do', {
				applicationUser : jq('#applicationUser').val(),
				tullkontorName : jq('#search_sveh_utfa_transit03').val(),
				tullkontorCode : jq('#search_sveh_utfa_Code_transit03').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].tkkode + '">' + data[i].tkkode + '&nbsp;&nbsp;' + data[i].tktxtn + '</option>';
					tullkontor = new Object();
					tullkontor.tkkode = data[i].tkkode;
					tullkontor.tktxtn = data[i].tktxtn;
					//put the object in map now with customerNumber as key
					map[tullkontor.tkkode] = tullkontor;
				}
				//now that we have our options, give them to our select
				jq('#tullkontorListTransit03').html(html);
			});
		})
		
	}
	//Transittullkontor 03 list
	jq(function() { 
	    jq('#tullkontorListTransit03').change(function() {
	    	jq('#thtsd3').val(""); //ncts export
	    	
			//now populate (if applicable)
	    	var key = jq('#tullkontorListTransit03').val();
	    	jq('#thtsd3').val(key); //ncts export
	    	
	    });
	});
	
	//On Keypress (13)
	jq(function() { 
	    jq('#tullkontorListTransit03').keypress(function() {
		    	if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#thtsd3').val(""); //ncts export
				//now populate (if applicable)
			    	var key = jq('#tullkontorListTransit03').val();
			    	jq('#thtsd3').val(key); //ncts export
		    	}
	    });
	    
	});
	
	//----------------------------------
	//Events TullkontorTransit (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgTullkontorListTransit03').click(function(){
    			jq("#search_sveh_utfa_transit03").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_utfa_transit03').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor03_OwnWindow);
			}			
    		});
		jq('#search_sveh_utfa_Code_transit03').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor03_OwnWindow);
			}			
    		});
	});
	

	
	//--------------------------------------------------------------------------
	//Transit-Tullkontor AJAX [NCTS Export] - Can be up to 8 transittullkontor
	//--------------------------------------------------------------------------
	//FETCH Transittullkontor 04
	function searchTransitTullkontor04_OwnWindow(){
		//init the tullkontor object in javascript (will be put into a map)
	  	var tullkontor = new Object();
	  	//fields
	  	tullkontor.tkkode = "";tullkontor.tktxtn = "";
	  	
		jq(function(){
			//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('searchUtfartsTullkontor.do', {
				applicationUser : jq('#applicationUser').val(),
				tullkontorName : jq('#search_sveh_utfa_transit04').val(),
				tullkontorCode : jq('#search_sveh_utfa_Code_transit04').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].tkkode + '">' + data[i].tkkode + '&nbsp;&nbsp;' + data[i].tktxtn + '</option>';
					tullkontor = new Object();
					tullkontor.tkkode = data[i].tkkode;
					tullkontor.tktxtn = data[i].tktxtn;
					//put the object in map now with customerNumber as key
					map[tullkontor.tkkode] = tullkontor;
				}
				//now that we have our options, give them to our select
				jq('#tullkontorListTransit04').html(html);
			});
		})
		
	}
	//Transittullkontor 04 list
	jq(function() { 
	    jq('#tullkontorListTransit04').change(function() {
	    	jq('#thtsd4').val(""); //ncts export
	    	
			//now populate (if applicable)
	    	var key = jq('#tullkontorListTransit04').val();
	    	jq('#thtsd4').val(key); //ncts export
	    	
	    });
	});
	
	
	//On Keypress (13)
	jq(function() { 
	    jq('#tullkontorListTransit04').keypress(function() {
		    	if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#thtsd4').val(""); //ncts export
				//now populate (if applicable)
			    	var key = jq('#tullkontorListTransit04').val();
			    	jq('#thtsd4').val(key); //ncts export
		    	}
	    });
	    
	});
	
	//----------------------------------
	//Events TullkontorTransit (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgTullkontorListTransit04').click(function(){
    			jq("#search_sveh_utfa_transit04").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_utfa_transit04').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor04_OwnWindow);
			}			
    		});
		jq('#search_sveh_utfa_Code_transit04').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor04_OwnWindow);
			}			
    		});
	});
	

	
	//--------------------------------------------------------------------------
	//Transit-Tullkontor AJAX [NCTS Export] - Can be up to 8 transittullkontor
	//--------------------------------------------------------------------------
	//FETCH Transittullkontor 05
	function searchTransitTullkontor05_OwnWindow(){
		//init the tullkontor object in javascript (will be put into a map)
	  	var tullkontor = new Object();
	  	//fields
	  	tullkontor.tkkode = "";tullkontor.tktxtn = "";
	  	
		jq(function(){
			//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('searchUtfartsTullkontor.do', {
				applicationUser : jq('#applicationUser').val(),
				tullkontorName : jq('#search_sveh_utfa_transit05').val(),
				tullkontorCode : jq('#search_sveh_utfa_Code_transit05').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].tkkode + '">' + data[i].tkkode + '&nbsp;&nbsp;' + data[i].tktxtn + '</option>';
					tullkontor = new Object();
					tullkontor.tkkode = data[i].tkkode;
					tullkontor.tktxtn = data[i].tktxtn;
					//put the object in map now with customerNumber as key
					map[tullkontor.tkkode] = tullkontor;
				}
				//now that we have our options, give them to our select
				jq('#tullkontorListTransit05').html(html);
			});
		})
		
	}
	//Transittullkontor 05 list
	jq(function() { 
	    jq('#tullkontorListTransit05').change(function() {
	    	jq('#thtsd5').val(""); //ncts export
	    	
			//now populate (if applicable)
	    	var key = jq('#tullkontorListTransit05').val();
	    	jq('#thtsd5').val(key); //ncts export
	    	
	    });
	});
	
	
	//On Keypress (13)
	jq(function() { 
	    jq('#tullkontorListTransit05').keypress(function() {
		    	if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#thtsd5').val(""); //ncts export
				//now populate (if applicable)
			    	var key = jq('#tullkontorListTransit05').val();
			    	jq('#thtsd5').val(key); //ncts export
		    	}
	    });
	    
	});
	
	//----------------------------------
	//Events TullkontorTransit (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgTullkontorListTransit05').click(function(){
    			jq("#search_sveh_utfa_transit05").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_utfa_transit05').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor05_OwnWindow);
			}			
    		});
		jq('#search_sveh_utfa_Code_transit05').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor05_OwnWindow);
			}			
    		});
	});
	

	
	//--------------------------------------------------------------------------
	//Transit-Tullkontor AJAX [NCTS Export] - Can be up to 8 transittullkontor
	//--------------------------------------------------------------------------
	//FETCH Transittullkontor 06
	function searchTransitTullkontor06_OwnWindow(){
		//init the tullkontor object in javascript (will be put into a map)
	  	var tullkontor = new Object();
	  	//fields
	  	tullkontor.tkkode = "";tullkontor.tktxtn = "";
	  	
		jq(function(){
			//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('searchUtfartsTullkontor.do', {
				applicationUser : jq('#applicationUser').val(),
				tullkontorName : jq('#search_sveh_utfa_transit06').val(),
				tullkontorCode : jq('#search_sveh_utfa_Code_transit06').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].tkkode + '">' + data[i].tkkode + '&nbsp;&nbsp;' + data[i].tktxtn + '</option>';
					tullkontor = new Object();
					tullkontor.tkkode = data[i].tkkode;
					tullkontor.tktxtn = data[i].tktxtn;
					//put the object in map now with customerNumber as key
					map[tullkontor.tkkode] = tullkontor;
				}
				//now that we have our options, give them to our select
				jq('#tullkontorListTransit06').html(html);
			});
		})
		
	}
	//Transittullkontor 06 list
	jq(function() { 
	    jq('#tullkontorListTransit06').change(function() {
	    	jq('#thtsd6').val(""); //ncts export
	    	
			//now populate (if applicable)
	    	var key = jq('#tullkontorListTransit06').val();
	    	jq('#thtsd6').val(key); //ncts export
	    	
	    });
	});
	
	
	//On Keypress (13)
	jq(function() { 
	    jq('#tullkontorListTransit06').keypress(function() {
		    	if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#thtsd6').val(""); //ncts export
				//now populate (if applicable)
			    	var key = jq('#tullkontorListTransit06').val();
			    	jq('#thtsd6').val(key); //ncts export
		    	}
	    });
	    
	});
	
	//----------------------------------
	//Events TullkontorTransit (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgTullkontorListTransit06').click(function(){
    			jq("#search_sveh_utfa_transit06").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_utfa_transit06').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor06_OwnWindow);
			}			
    		});
		jq('#search_sveh_utfa_Code_transit06').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor06_OwnWindow);
			}			
    		});
	});
	

	
	//--------------------------------------------------------------------------
	//Transit-Tullkontor AJAX [NCTS Export] - Can be up to 8 transittullkontor
	//--------------------------------------------------------------------------
	//FETCH Transittullkontor 07
	function searchTransitTullkontor07_OwnWindow(){
		//init the tullkontor object in javascript (will be put into a map)
	  	var tullkontor = new Object();
	  	//fields
	  	tullkontor.tkkode = "";tullkontor.tktxtn = "";
	  	
		jq(function(){
			//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('searchUtfartsTullkontor.do', {
				applicationUser : jq('#applicationUser').val(),
				tullkontorName : jq('#search_sveh_utfa_transit07').val(),
				tullkontorCode : jq('#search_sveh_utfa_Code_transit07').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].tkkode + '">' + data[i].tkkode + '&nbsp;&nbsp;' + data[i].tktxtn + '</option>';
					tullkontor = new Object();
					tullkontor.tkkode = data[i].tkkode;
					tullkontor.tktxtn = data[i].tktxtn;
					//put the object in map now with customerNumber as key
					map[tullkontor.tkkode] = tullkontor;
				}
				//now that we have our options, give them to our select
				jq('#tullkontorListTransit07').html(html);
			});
		})
		
	}
	//Transittullkontor 07 list
	jq(function() { 
	    jq('#tullkontorListTransit07').change(function() {
	    	jq('#thtsd7').val(""); //ncts export
	    	
			//now populate (if applicable)
	    	var key = jq('#tullkontorListTransit07').val();
	    	jq('#thtsd7').val(key); //ncts export
	    	
	    });
	});
	
	
	//On Keypress (13)
	jq(function() { 
	    jq('#tullkontorListTransit07').keypress(function() {
		    	if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#thtsd7').val(""); //ncts export
				//now populate (if applicable)
			    	var key = jq('#tullkontorListTransit07').val();
			    	jq('#thtsd7').val(key); //ncts export
		    	}
	    });
	    
	});
	
	//----------------------------------
	//Events TullkontorTransit (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgTullkontorListTransit07').click(function(){
    			jq("#search_sveh_utfa_transit07").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_utfa_transit07').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor07_OwnWindow);
			}			
    		});
		jq('#search_sveh_utfa_Code_transit07').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor07_OwnWindow);
			}			
    		});
	});
	

	
	//--------------------------------------------------------------------------
	//Transit-Tullkontor AJAX [NCTS Export] - Can be up to 8 transittullkontor
	//--------------------------------------------------------------------------
	//FETCH Transittullkontor 08
	function searchTransitTullkontor08_OwnWindow(){
		//init the tullkontor object in javascript (will be put into a map)
	  	var tullkontor = new Object();
	  	//fields
	  	tullkontor.tkkode = "";tullkontor.tktxtn = "";
	  	
		jq(function(){
			//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('searchUtfartsTullkontor.do', {
				applicationUser : jq('#applicationUser').val(),
				tullkontorName : jq('#search_sveh_utfa_transit08').val(),
				tullkontorCode : jq('#search_sveh_utfa_Code_transit08').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].tkkode + '">' + data[i].tkkode + '&nbsp;&nbsp;' + data[i].tktxtn + '</option>';
					tullkontor = new Object();
					tullkontor.tkkode = data[i].tkkode;
					tullkontor.tktxtn = data[i].tktxtn;
					//put the object in map now with customerNumber as key
					map[tullkontor.tkkode] = tullkontor;
				}
				//now that we have our options, give them to our select
				jq('#tullkontorListTransit08').html(html);
			});
		})
		
	}
	//Transittullkontor 08 list
	jq(function() { 
	    jq('#tullkontorListTransit08').change(function() {
	    	jq('#thtsd8').val(""); //ncts export
	    	
			//now populate (if applicable)
	    	var key = jq('#tullkontorListTransit08').val();
	    	jq('#thtsd8').val(key); //ncts export
	    	
	    });
	});
	
	//On Keypress (13)
	jq(function() { 
	    jq('#tullkontorListTransit08').keypress(function() {
		    	if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#thtsd8').val(""); //ncts export
				//now populate (if applicable)
			    	var key = jq('#tullkontorListTransit08').val();
			    	jq('#thtsd8').val(key); //ncts export
		    	}
	    });
	    
	});
	
	//----------------------------------
	//Events TullkontorTransit (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgTullkontorListTransit08').click(function(){
    			jq("#search_sveh_utfa_transit08").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_utfa_transit08').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor08_OwnWindow);
			}			
    		});
		jq('#search_sveh_utfa_Code_transit08').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor08_OwnWindow);
			}			
    		});
	});
	

	
	//--------------------------------------------------------
	//VALIDATION before submit
	//Some fields must be validated upon "change" on the spot
	//E.g. Guarantee 
	//--------------------------------------------------------
	/*
	//Guarantee nr and code validation
	jq(function() { 
	    jq('#thgft1').blur(function() {
	    		var original_guaranteeNumber = jq('#thgft1').val();
	    		var original_guaranteeCode = jq('#thgadk').val();
	    		
	    		//alert('Hej');
	    		//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('validateGuaranteeNrAndCode.do', {
				applicationUser : jq('#applicationUser').val(),
				guaranteeNumber : jq('#thgft1').val(),
				guaranteeCode : jq('#thgadk').val(),
				ajax : 'true'
			}, 
			function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					var errMsg = data[i].errMsg;
					alert(errMsg);
					/*
					var errMsg = data[i].errMsg;
					if(errMsg!=null && !"".equals(errMsg)){
						alert('NOK');
						jq('#thgft1').val(errMsg);
						jq('#thgadk').val(original_guaranteeCode);
					}else{
						alert('No errors');
						jq('#thgft1').val(data[i].thgft1);
						jq('#thgadk').val(data[i].thgadk);
					}
					
				}
				
			});
		
	    });
	    
	});
	*/
	
  	//---------------
	//Tullkontor list
	//---------------
	//onChange
	jq(function() { 
	    jq('#tullkontorList').change(function() {
		    	jq('#thcats').val(""); //ncts export
		    	//now populate (if applicable)
		    	var key = jq('#tullkontorList').val();
		    	jq('#thcats').val(key); //ncts export
	    });
	});
	//On Keypress (13)
	jq(function() { 
	    jq('#tullkontorList').keypress(function() {
		    	if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#thcats').val(""); //ncts export
				//now populate (if applicable)
			    	var key = jq('#tullkontorList').val();
			    	jq('#thcats').val(key); //ncts export
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
	
	
	
	
	
	

