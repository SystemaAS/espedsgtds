	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	
  	jq(document).ready(function() {
  		//TODO
  	});
  	
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
  	  jq('#alinkItemLines').click(function() { 
  		setBlockUI();
  	  });
  	  jq('#alinkArchive').click(function() { 
  		setBlockUI();
	  });
    });
  	
  	