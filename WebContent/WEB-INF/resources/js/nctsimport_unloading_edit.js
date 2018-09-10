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
  	
  //date fields
    jq(function() {
  	  jq("#nidtl").datepicker({ 
  		  dateFormat: 'yymmdd'
  	  });
  	  
    });
  	
	
	
	
	
	
	
	

