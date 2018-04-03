  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  jq(function() {
	  jq("#formRecord").submit(function() {
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT}); 
	  });
	  
  });
  
  jq(function() {
  	  
  	  //custom validity
	  jq('#dktard02').blur(function() {
	  		if(jq('#dktard02').val()!=''){
	    		refreshCustomValidity(jq('#dktard02')[0]);
	  		}
	  });
	  
	  //UNB Sender 
	  jq('#svtf_0004IdLink').click(function() {
		  jq('#svtf_0004IdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_edi.do?action=doFind&id=' + jq('#svtf_0004').val() + '&ctype=svtf_0004', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#svtf_0004IdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#svtf_0004IdLink').click();
  		  }
  	  });
	  //UNB Receiver 
	  jq('#svtf_0010IdLink').click(function() {
		  jq('#svtf_0010IdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_edi.do?action=doFind&id=' + jq('#svtf_0010').val() + '&ctype=svtf_0010', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#svtf_0010IdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#svtf_0010IdLink').click();
  		  }
  	  });
	  //SMS Sender
	  jq('#svtf_usriIdLink').click(function() {
		  jq('#svtf_usriIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_osusers.do?action=doFind&id=' + jq('#svtf_usri').val() + '&ctype=svtf_usri', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#svtf_usriIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#svtf_usriIdLink').click();
  		  }
  	  });
	  
	  
	  //START CUSTOMER VALIDITY refreshes
	    //must be done since CustomValidity is HTML 5 and not jQuery
	    //otherwise the validation is never removed (when the value was setted via jQuery in some event)
	    jq('#svtf_0004').focus(function() {
	    	if(jq('#svtf_0004').val()!=''){
	  			refreshCustomValidity(jq('#svtf_0004')[0]);
	  		}
	  	});
	    jq('#svtf_0010').focus(function() {
	    	if(jq('#svtf_0010').val()!=''){
	  			refreshCustomValidity(jq('#svtf_0010')[0]);
	  		}
	  	});
	    jq('#svtf_0022').focus(function() {
	    	if(jq('#svtf_0022').val()!=''){
	  			refreshCustomValidity(jq('#svtf_0022')[0]);
	  		}
	  	});
	    jq('#svtf_pref').focus(function() {
	    	if(jq('#svtf_pref').val()!=''){
	  			refreshCustomValidity(jq('#svtf_pref')[0]);
	  		}
	  	});
	    jq('#svtf_numb').focus(function() {
	    	if(jq('#svtf_numb').val()!=''){
	  			refreshCustomValidity(jq('#svtf_numb')[0]);
	  		}
	  	});
	    jq('#svtf_usri').focus(function() {
	    	if(jq('#svtf_usri').val()!=''){
	  			refreshCustomValidity(jq('#svtf_usri')[0]);
	  		}
	  	});
	    jq('#svtf_usra').focus(function() {
	    	if(jq('#svtf_usra').val()!=''){
	  			refreshCustomValidity(jq('#svtf_usra')[0]);
	  		}
	  	});
	    jq('#svtf_sec1').focus(function() {
	    	if(jq('#svtf_sec1').val()!=''){
	  			refreshCustomValidity(jq('#svtf_sec1')[0]);
	  		}
	  	});
	    jq('#svtf_sec2').focus(function() {
	    	if(jq('#svtf_sec2').val()!=''){
	  			refreshCustomValidity(jq('#svtf_sec2')[0]);
	  		}
	  	});
	    jq('#svtf_cer1').focus(function() {
	    	if(jq('#svtf_cer1').val()!=''){
	  			refreshCustomValidity(jq('#svtf_cer1')[0]);
	  		}
	  	});
	    jq('#svtf_cer3').focus(function() {
	    	if(jq('#svtf_cer3').val()!=''){
	  			refreshCustomValidity(jq('#svtf_cer3')[0]);
	  		}
	  	});
	  
  });