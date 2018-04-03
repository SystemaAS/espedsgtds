  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  
  jq(function() {
	  if(jq("#userAS400").val()!=""){
		  jq("#pwAS400").focus();
	  }else{
		  jq("#userAS400").focus();
	  }
  });
  
  
