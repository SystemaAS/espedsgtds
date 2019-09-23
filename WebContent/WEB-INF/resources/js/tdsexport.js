  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  
  jq(function() {
	  jq('#alinkZem').click(function() { 
  		setBlockUI();
  	  });	
  });
  
  jq(function() {
	  jq("#opd").focus();
  });
  
  jq(function() {
	  jq("#datum").datepicker({ 
		  dateFormat: 'yymmdd'
		  
	  });
	  jq("#datumt").datepicker({ 
		  dateFormat: 'yymmdd'  
	  });
	  jq( "#submit" ).click(function( event ) {
		  setBlockUI();
  	  });
	  
	//External references in model Dialog
	  	jq('#extRefIdLink').click(function() {
	    	jq('#extRefIdLink').attr('target','_blank');
	    	window.open('tdsexport_childwindow_external_references.do?avd=' + jq("#selectedAvd").val(), "codeWin", "top=300px,left=750px,height=500px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#extRefIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#extRefIdLink').click();
			}
	    });
	  
  });
  
  
  
  
  
  //-----------------------------------
  //START Model dialog "Kopiera Ärende
  //-----------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq( ".clazz_dialog" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			modal: true,
			width: 350,
			maxWidth:500
		});
	  });
  });
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".copyLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("copyLink","");
		  //setters (add more if needed)
		  jq('#dialog'+counterIndex).dialog( "option", "title", "Kopiera Ärende " + jq('#originalOpd'+counterIndex).val() );
		  
		  //deal with buttons for this modal window
		  jq('#dialog'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSave"+counterIndex,	
				 text: "Gå vidare",
				 click: function(){
					 		jq('#copyForm'+counterIndex).submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancel"+counterIndex,
			 	 text: "Avbryt", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSave"+counterIndex).button("option", "disabled", true);
					 		jq("#newAvd"+counterIndex).val("");
					 		jq("#newSign"+counterIndex).val("");
							jq( this ).dialog( "close" ); 
					 		  
				 		} 
	 	 		 } ] 
			  
		  });
		  //init values
		  jq("#dialogSave"+counterIndex).button("option", "disabled", true);
		  
		  //open now
		  jq('#dialog'+counterIndex).dialog('open');
		 
	  });
  });
  //Events for the drop downs (some kind of "implicit validation" since all drop downs are mandatory)
  jq(function() {
	  jq(".newAvd").change(function() {
		  //console.log("newAvd CHANGE !!!!!");
		  if(jq("#dialog"+counterIndex).find('.newAvd').val()!='' && jq("#dialog"+counterIndex).find('.newSign').val()!=''){
			  jq("#dialogSave"+counterIndex).button("option", "disabled", false);
			  
		  }else{
			  jq("#dialogSave"+counterIndex).button("option", "disabled", true);
		  }
	  });
	  jq(".newSign").change(function() {
		  //console.log("newSign CHANGE !!!!");
		  if(jq("#dialog"+counterIndex).find('.newAvd').val()!='' && jq("#dialog"+counterIndex).find('.newSign').val()!=''){
			  jq("#dialogSave"+counterIndex).button("option", "disabled", false);
			  
		  }else{
			  jq("#dialogSave"+counterIndex).button("option", "disabled", true);
		  }
	  });
	  
	  
  });
  //---------------------------------
  //END Model dialog "Kopiera Ärende
  //---------------------------------
	  
 
//-----------------------------------------------------------------------------
  //START Model dialog "Kopiera Ärende från mall (norsk import/transport order)
  //---------------------------------------------------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq("#dialogCopyFromTransportUppdrag").dialog({
		  autoOpen: false,
		  maxWidth:500,
          maxHeight: 600,
          width: 500,
          height: 500,
		  modal: true
	  });
  });
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq("#copyFromTransportUppdragLink").click(function() {
		  //setters (add more if needed)
		  //jq('#dialogCopyFromTransportUppdrag').dialog( "option", "title", "Hämta ärende från Norsk Importförtullning eller från Transportuppdrag" );
		  jq('#dialogCopyFromTransportUppdrag').dialog( "option", "title", "Hämta ärende från SYSPED" );
		  
		  
		  //deal with buttons for this modal window
		  jq('#dialogCopyFromTransportUppdrag').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Gå vidare",
				 click: function(){
					 		jq('#copyFromTransportUppdragForm').submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU",
			 	 text: "Avbryt", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSaveSU").button("option", "disabled", true);
					 		jq("#selectedAvd").val("");
					 		jq("#selectedOpd").val("");
					 		jq("#selectedExtRefNr").val("");
							jq( this ).dialog( "close" ); 
					 		
							jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  jq("#dialogSaveSU").button("option", "disabled", false);
		  //open now
		  jq('#dialogCopyFromTransportUppdrag').dialog('open');
	  });
  });
  //Events for the drop downs (some kind of "implicit validation" since all drop downs are mandatory)
  /*
  jq(function() {
	  jq("#selectedAvd").change(function() {
		  if(jq("#dialog").find('#selectedAvd').val()!='' && jq("#dialog").find('#selectedSign').val()!=''){
			  jq("#dialogSave").button("option", "disabled", false);
		  }else{
			  jq("#dialogSave").button("option", "disabled", true);
		  }
	  });
	  jq("#selectedSign").change(function() {
		  if(jq("#dialog").find('#selectedAvd').val()!='' && jq("#dialog").find('#selectedSign').val()!=''){
			  jq("#dialogSave").button("option", "disabled", false);
		  }else{
			  jq("#dialogSave").button("option", "disabled", true);
		  }
	  });
	  
  });
  */
  function applyRuleOnDialogTranspUppdragNullAvd(){
	  if(jq('#selectedOpd').val()=='' && jq('#selectedExtRefNr').val()==''){
		  jq("#dialogSaveTU").button("option", "disabled", false);
	  }else{
		  jq("#dialogSaveTU").button("option", "disabled", true);
	  }
  }
  function applyRuleOnDialogTranspUppdragExtRef(){
	  if(jq('#selectedExtRefNr').val()!=''){
		  jq("#dialogSaveTU").button("option", "disabled", false);
	  }else{
		  jq("#dialogSaveTU").button("option", "disabled", true);
	  }
  }
  //------------------------------------------------
  //END Model dialog "Kopiera Ärende from template" 
  //------------------------------------------------
	  
  
  //-----------------------------------------
  //START Model dialog "Begäran om klarering"
  //-----------------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq( ".clazz_dialogRequestClearance" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			modal: true
		});
	  });
  });
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".clearanceLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("clearanceLink","");
		  //setters (add more if needed)
		  jq('#dialogRequestClearance'+counterIndex).dialog( "option", "title", "Begäran om klarering" );
		  
		  //deal with buttons for this modal window
		  jq('#dialogRequestClearance'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveRequestClearance"+counterIndex,	
				 text: "Gå vidare",
				 click: function(){
					 		jq('#requestClearanceForm'+counterIndex).submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelRequestClearance"+counterIndex,
			 	 text: "Avbryt", 
				 click: function(){
				 		//back to initial state of form elements on modal dialog
			 		 	jq('#dialogRequestClearance'+counterIndex).dialog('close');
				 		jq("#dialogSaveRequestClearance"+counterIndex).button("option", "disabled", true);
				 		
			 		} 
	 	 		 } ] 
		  });
		  
		  //open now
		  jq('#dialogRequestClearance'+counterIndex).dialog('open');
		 
	  });
  });
  
  //----------------------------------------
  //END Model dialog "Begäran om Klarering"
  //----------------------------------------

  //----------------------------------------------------
  //START Model dialog "Rättelse av ej klarerad ärende"
  //----------------------------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq( ".clazz_dialogCorrectionOnNonClearedTopic" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			modal: true
		});
	  });
  });
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".correctionOnNonClearedLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("correctionOnNonClearedLink","");
		  //setters (add more if needed)
		  jq('#dialogCorrectionOnNonClearedTopic'+counterIndex).dialog( "option", "title", "Rättelse av ej klarerat ärende" );
		  
		  //deal with buttons for this modal window
		  jq('#dialogCorrectionOnNonClearedTopic'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveCorrectNonClearedTopic"+counterIndex,	
				 text: "Gå vidare",
				 click: function(){
					 		jq('#correctNonClearedForm'+counterIndex).submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelCorrectNonClearedTopic"+counterIndex,
			 	 text: "Avbryt", 
				 click: function(){
				 		//back to initial state of form elements on modal dialog
			 		 	jq('#dialogCorrectionOnNonClearedTopic'+counterIndex).dialog('close');
				 		jq("#dialogCorrectionOnNonClearedTopic"+counterIndex).button("option", "disabled", true);
			 		} 
	 	 		 } ] 
		  });
		  
		  //open now
		  jq('#dialogCorrectionOnNonClearedTopic'+counterIndex).dialog('open');
		 
	  });
  });
  
  //-------------------------------------------------
  //END Model dialog "Rättelse av ej klarerad ärende"
  //-------------------------------------------------

  
  //----------------------------------------------------
  //START Model dialog "Kompletterande tulldeklaration"
  //----------------------------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq( ".clazz_dialogCompletionOnTopic" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			modal: true
		});
	  });
  });
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".completionLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("completionLink","");
		  //setters (add more if needed)
		  jq('#dialogCompletionOnTopic'+counterIndex).dialog( "option", "title", "Kompletterande tulldeklaration" );
		  
		  //deal with buttons for this modal window
		  jq('#dialogCompletionOnTopic'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveCompletionOnTopic"+counterIndex,	
				 text: "Gå vidare",
				 click: function(){
					 		jq('#completionForm'+counterIndex).submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelCompletionOnTopic"+counterIndex,
			 	 text: "Avbryt", 
				 click: function(){
				 		//back to initial state of form elements on modal dialog
			 		 	jq('#dialogCompletionOnTopic'+counterIndex).dialog('close');	
				 		jq("#dialogCompletionOnTopic"+counterIndex).button("option", "disabled", true);
				 		
			 		} 
	 	 		 } ] 
		  });
		  
		  //open now
		  jq('#dialogCompletionOnTopic'+counterIndex).dialog('open');
		 
	  });
  });
  //--------------------------------------------------
  //END Model dialog "Kompletterande tulldeklaration"
  //--------------------------------------------------
  

  //---------------------------------------
  //DELETE Order
  //This is done in order to present a jquery
  //Alert modal pop-up
  //----------------------------------------
  function doPermanentlyDeleteOrder(element){
	  //start
	  var record = element.id.split('@');
	  var avd = record[0];
	  var opd = record[1];
	  avd= avd.replace("avd_","");
	  opd= opd.replace("opd_","");
	  	//Start dialog
	  	jq('<div></div>').dialog({
	        modal: true,
	        title: "Ta bort ärende " + opd,
	        buttons: {
		        Fortsett: function() {
	        		jq( this ).dialog( "close" );
		            //do delete
		            jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
		            window.location = "tdsexport.do?action=doDelete" + "&avd=" + avd + "&opd=" + opd;
		        },
		        Avbryt: function() {
		            jq( this ).dialog( "close" );
		        }
	        },
	        open: function() {
		  		  var markup = "Är du säkert på att du vill radera denna?";
		          jq(this).html(markup);
		          //make Cancel the default button
		          jq(this).siblings('.ui-dialog-buttonpane').find('button:eq(1)').focus();
		     }
		});  //end dialog
  }
  
  
  jq(document).ready(function() {
	  //to prevent hiding datepicker behind the autocomplete function
	  jq('.datepicker').on('click', function(e) {
		   e.preventDefault();
		   jq(this).attr("autocomplete", "off");  
	  });
  });