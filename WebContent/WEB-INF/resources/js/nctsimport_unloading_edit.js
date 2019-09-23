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
  	
	
  //ChildWindow Country Codes
    function triggerChildWindowCountryCodes(record){
    	var idLink = record.id;
    	var id = idLink.replace("IdLink", "");
    	jq(idLink).attr('target','_blank');
    	window.open('nctsimport_edit_items_childwindow_generalcodes.do?action=doInit&type=GCY&ctype=' + id , "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    }
    
  //ChildWindow Language Codes
    function triggerChildWindowLanguageCodes(record){
    	var idLink = record.id;
    	var id = idLink.replace("IdLink", "");
    	jq(idLink).attr('target','_blank');
    	window.open('nctsimport_edit_items_childwindow_generalcodes.do?action=doInit&type=012&ctype=' + id , "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    }	
	
    
    jq(document).ready(function() {
  	  //to prevent hiding datepicker behind the autocomplete function
  	  jq('.datepicker').on('click', function(e) {
  		   e.preventDefault();
  		   jq(this).attr("autocomplete", "off");  
  	  });
    });
	
	
	
	

