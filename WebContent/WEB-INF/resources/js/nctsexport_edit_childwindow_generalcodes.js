	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//--------
  	//Koder
  	//--------
	jq(function() {
		jq('#generalCodeList').on('click', 'td', function(){
			  var id = this.id;
			  var record = id.split('@');
			  var kod = record[0].replace("kod", "");
			  var callerType = record[1].replace("ctype", "");
			  //alert(kod + " " + callerType);
			  
			  if(callerType == 'thalk'){
				  opener.jq('#thalk').val(kod);
				  opener.jq('#thalk').focus();
				  
			  }else if(callerType == 'thblk'){
				  opener.jq('#thblk').val(kod);
				  opener.jq('#thblk').focus();
				  
			  }else if(callerType == 'thtalk'){
				  opener.jq('#thtalk').val(kod);
				  opener.jq('#thtalk').focus();
				  
			  }else if(callerType == 'thtask'){
				  opener.jq('#thtask').val(kod);
				  opener.jq('#thtask').focus();
				  
			  }else if(callerType == 'thtgsk'){
				  opener.jq('#thtgsk').val(kod);
				  opener.jq('#thtgsk').focus();
				  
			  }else if(callerType == 'thskfd'){
				  opener.jq('#thskfd').val(kod);
				  opener.jq('#thskfd').focus();
				  
			  }else if(callerType == 'thdsk'){
				  opener.jq('#thdsk').val(kod);
				  opener.jq('#thdsk').focus();
				  
			  }else if(callerType == 'thsks'){
				  opener.jq('#thsks').val(kod);
				  opener.jq('#thsks').focus();
				  
			  }else if(callerType == 'thskk'){
				  opener.jq('#thskk').val(kod);
				  opener.jq('#thskk').focus();
				  
			  }else if(callerType == 'thska'){
				  opener.jq('#thska').val(kod);
				  opener.jq('#thska').focus();
				  
			  }else if(callerType == 'thskss'){
				  opener.jq('#thskss').val(kod);
				  opener.jq('#thskss').focus();
				  
			  }else if(callerType == 'thskks'){
				  opener.jq('#thskks').val(kod);
				  opener.jq('#thskks').focus();
				  
			  }else if(callerType == 'thskt'){
				  opener.jq('#thskt').val(kod);
				  opener.jq('#thskt').focus();
				  
			  }else if(callerType == 'thlosdsk'){
				  opener.jq('#thlosdsk').val(kod);
				  opener.jq('#thlosdsk').focus();
				  
			  }else if(callerType == 'thlks'){
				  opener.jq('#thlks').val(kod);
				  opener.jq('#thlks').focus();
				  
			  }else if(callerType == 'thlkss'){
				  opener.jq('#thlkss').val(kod);
				  opener.jq('#thlkss').focus();
				  
			  }else if(callerType == 'thlkks'){
				  opener.jq('#thlkks').val(kod);
				  opener.jq('#thlkks').focus();
				  
			  }else if(callerType == 'thlkt'){
				  opener.jq('#thlkt').val(kod);
				  opener.jq('#thlkt').focus();
				  
			  }else if(callerType == 'thlkk'){
				  opener.jq('#thlkk').val(kod);
				  opener.jq('#thlkk').focus();
				  
			  }else if(callerType == 'thgbgu'){
				  opener.jq('#thgbgu').val(kod);
				  opener.jq('#thgbgu').focus();
				  
			  }else if(callerType == 'thtglk'){
				  opener.jq('#thtglk').val(kod);
				  opener.jq('#thtglk').focus();
				  
			  }else if(callerType == 'thlka'){
				  opener.jq('#thlka').val(kod);
				  opener.jq('#thlka').focus();
				
			  //valutakode	  
			  }else if(callerType == 'thgvk'){
				  opener.jq('#thgvk').val(kod);
				  opener.jq('#thgvk').focus();
			  }

			  
			  //close child window
			  window.close();
		  });
	});
	
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterGeneralCode () {
    		jq('#generalCodeList').DataTable().search(
      		jq('#generalCodeList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#generalCodeList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "scrollCollapse":  true,
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.generalCodeList_filter').on( 'keyup click', function () {
      		filterGeneralCode();
      });
      
    });   
  	
  	
	