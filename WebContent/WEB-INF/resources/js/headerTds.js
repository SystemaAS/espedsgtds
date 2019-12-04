  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  
  jq(function() {
	  if(jq("#userAS400").val()!=""){
		  jq("#pwAS400").focus();
	  }else{
		  jq("#userAS400").focus();
	  }
  });
  
  
  //---------------------------------
  //START Model dialog: "Logger"
  //------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq("#dialogLogger").dialog({
		  autoOpen: false,
		  maxWidth:500,
          maxHeight: 400,
          width: 230,
          height: 250,
		  modal: true
	  });
  });

  jq(function() {
	  jq("#alinkLog4jLogger").click(function() {
		  presentPwdDialog();
	  });
  });
  
  
  //---------------------
  //PRESENT PWD DIALOG
  //---------------------
  function presentPwdDialog(){
	  jq('#dialogLogger').dialog( "option", "title", "Log4j logger" );
	  //deal with buttons for this modal window
	  jq('#dialogLogger').dialog({
		 buttons: [ 
            {
			 id: "dialogSaveTU",	
			 text: "Show",
			 click: function(){
				 		if(jq("#pwd").val() == "straffe12"){
				 			window.open('renderLocalLog4j.do?logLevel=' + jq("#logLevel").val(), '_blank');
				 			jq("#loggerStatus").removeClass( "isa_error" );
			  				jq("#loggerStatus").addClass( "isa_success" );
			  				jq("#loggerStatus").text("");
			  				jq("#pwd").val("");
			  				jq("#logLevel").val("");
			  				jq( this ).dialog( "close" );
				 		}else{
				 			jq("#loggerStatus").removeClass( "isa_success" );
			  				jq("#loggerStatus").addClass( "isa_error" );
			  				jq("#loggerStatus").text("...");
			  				jq("#pwd").val("");
			  				jq("#logLevel").val("");
				 		}
		 			}
		 	 },
  			{
		 	 id: "dialogCancelTU",
		 	 text: "Cancel", 
			 click: function(){
				 		jq("#loggerStatus").removeClass( "isa_success" );
				 		jq("#loggerStatus").removeClass( "isa_error" );
				 		jq("#loggerStatus").text("");
				 		jq("#pwd").val("");
				 		jq("#logLevel").val("");
		  				jq( this ).dialog( "close" ); 
			 		} 
 	 		 } ] 
	  });
	  jq('#dialogLogger').dialog('open');
  }
	  
  
  
