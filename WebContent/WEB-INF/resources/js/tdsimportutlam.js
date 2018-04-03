  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
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
  });
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  //-----------------------------------
  //START Model dialog "Kopiera Ärende
  //-----------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq( ".clazz_dialog" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			modal: true
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
		  if(jq("#dialog"+counterIndex).find('.newAvd').val()!='' && jq("#dialog"+counterIndex).find('.newSign').val()!=''){
			  jq("#dialogSave"+counterIndex).button("option", "disabled", false);
			  
		  }else{
			  jq("#dialogSave"+counterIndex).button("option", "disabled", true);
		  }
	  });
	  jq(".newSign").change(function() {
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
  //START Model dialog "Kopiera Ärende from template (norsk export/transport order)
  //---------------------------------------------------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq("#dialogCopyFromTransportUppdrag").dialog({
		  autoOpen: false,
		  maxWidth:500,
          maxHeight: 400,
          width: 500,
          height: 400,
		  modal: true
	  });
  });
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq("#copyFromTransportUppdragLink").click(function() {
		  //setters (add more if needed)
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
					 		jq("#selectedSign").val("");
							jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  jq("#dialogSaveSU").button("option", "disabled", true);
		  //open now
		  jq('#dialogCopyFromTransportUppdrag').dialog('open');
	  });
  });
  //Events for the drop downs (some kind of "implicit validation" since all drop downs are mandatory)
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
  //-----------------------------------------------
  //END Model dialog "Kopiera Ärende from template
  //-----------------------------------------------

  
  
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
			 		 	jq('#dialogSaveRequestClearance'+counterIndex).dialog('close');
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
			 		jq('#dialogCorrectionOnNonClearedTopic'+counterIndex).dialog('close');
			 		//back to initial state of form elements on modal dialog
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

  
  //--------------------------------
  //START Model dialog "Omprövning"
  //--------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq( ".clazz_dialogReconsiderationOnTopic" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			modal: true
		});
	  });
  });
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".reconsiderationLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("reconsiderationLink","");
		  //setters (add more if needed)
		  jq('#dialogReconsiderationOnTopic'+counterIndex).dialog( "option", "title", "Omprövning" );
		  
		  //deal with buttons for this modal window
		  jq('#dialogReconsiderationOnTopic'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveReconsiderationOnTopic"+counterIndex,	
				 text: "Gå vidare",
				 click: function(){
					 		jq('#reconsiderationForm'+counterIndex).submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelReconsiderationOnTopic"+counterIndex,
			 	 text: "Avbryt", 
				 click: function(){
				 		//back to initial state of form elements on modal dialog
			 		 	jq('#dialogReconsiderationOnTopic'+counterIndex).dialog('close');
				 		jq("#dialogReconsiderationOnTopic"+counterIndex).button("option", "disabled", true);
			 		} 
	 	 		 } ] 
		  });
		  
		  //open now
		  jq('#dialogReconsiderationOnTopic'+counterIndex).dialog('open');
		 
	  });
  });
  //-------------------------------
  //END Model dialog "Omprövning"
  //-------------------------------
  
  
  

  
  
  