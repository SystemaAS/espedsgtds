	//===========================================
	//General functions for this JSP side - AJAX
	//===========================================
	
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var map = {};
  	
  	jq(function() {
        jq('#alinkMainList').click(function() { 
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
  	
  	jq(function() {
    	//Customer ANSVARIGE
	  	jq('#tinaIdLink').click(function() {
	  		jq('#tinaIdLink').attr('target','_blank');
	  		window.open('tds_childwindow_customer.do?action=doFind&sonavn=' + jq('#tina').val() + '&ctype=tina', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  	});
	  	jq('#tinaIdLink').keypress(function(e){ //extra feature for the end user
	  		if(e.which == 13) {
	  			jq('#tinaIdLink').click();
	  		}
	  	});
  	});

	
	//------------------------------------------------------------------------------------------
	//Init Topic with CREATE NEW TOPIC since some values are fetched depending on the Avdelning
	//------------------------------------------------------------------------------------------	
	jq(function() { 
	    jq('#avd').change(function() {
	    		//alert('Hej');
	    		//this parameters must match the AJAX controller parameter names in Spring exactly...
	    		jq.getJSON('initCreateNewTopicNctsImport.do', {
	    			applicationUser : jq('#applicationUser').val(),
	    			avd : jq('#avd').val(),
	    			ajax : 'true'
			}, 
			function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#tienkl').val(data[i].tienkl);
					//jq('#sign').val(data[i].tisg);
					//jq('#tisg').val(data[i].tisg);
					jq('#tist').val(data[i].tist);
					jq('#tidt').val(data[i].tidt);
					
					jq('#tikn').val(data[i].tikn);
					jq('#tina').val(data[i].tina);
					jq('#tiad1').val(data[i].tiad1);
					jq('#tips').val(data[i].tips);
					jq('#tipn').val(data[i].tipn);
					jq('#tilk').val(data[i].tilk);
					jq('#tisk').val(data[i].tisk);
					jq('#titin').val(data[i].titin);
					
					jq('#tign').val(data[i].tign);
					jq('#titrnr').val(data[i].titrnr);
					jq('#tignsk').val(data[i].tignsk);
					jq('#tialk').val(data[i].tialk);
					//jq('#tignsk').val("SV");//default
					//jq('#tialk').val("SE");//default
					jq('#tialsk').val(data[i].tialsk);
					
					jq('#tials').val(data[i].tials);
					jq('#tialss').val(data[i].tialss);
					
					jq('#tiglsk').val(data[i].tiglsk);
					jq('#tiacts').val(data[i].tiacts);
					jq('#tiskb').val(data[i].tiskb);
					jq('#titsb').val(data[i].titsb);
					jq('#tidtf').val(data[i].tidtf);
					
				}
				
			});
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

	
	
	  //ChildWindow Country Codes
	    function triggerChildWindowCountryCodes(record){
	    	var idLink = record.id;
	    	var id = idLink.replace("IdLink", "");
	    	jq(idLink).attr('target','_blank');
	    	window.open('nctsimport_edit_childwindow_generalcodes.do?action=doInit&type=GCY&ctype=' + id , "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    }
	    
	  //ChildWindow Language Codes
	    function triggerChildWindowLanguageCodes(record){
	    	var idLink = record.id;
	    	var id = idLink.replace("IdLink", "");
	    	jq(idLink).attr('target','_blank');
	    	window.open('nctsimport_edit_childwindow_generalcodes.do?action=doInit&type=012&ctype=' + id , "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    }
	  //ChildWindow Tullkontor Codes
	    function triggerChildWindowTullkontorCodes(record){
	    	var idLink = record.id;
	    	var id = idLink.replace("IdLink", "");
	    	//alert(idLink + "XX" + id);
	    	//alert(jq("#"+id).val());
	    	jq(idLink).attr('target','_blank');
	    	window.open('nctsimport_edit_childwindow_tullkontor.do?action=doInit&tkkode=' + jq("#"+id).val()+ '&ctype=' + id, "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    }
	
    
	
	

