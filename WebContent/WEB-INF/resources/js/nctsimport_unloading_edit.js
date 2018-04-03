	//===========================================
	//General functions for this JSP side - AJAX
	//===========================================
	
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	
  //date fields
    jq(function() {
  	  jq("#nidtl").datepicker({ 
  		  dateFormat: 'yymmdd'
  	  });
  	  
    });
  	
	
	
	
	
	
	
	

