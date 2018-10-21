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
			  
			  if(callerType == 'tvdsk'){
				  opener.jq('#tvdsk').val(kod);
				  opener.jq('#tvdsk').focus();
				  
			  }else if(callerType == 'tvvtsk'){
				  opener.jq('#tvvtsk').val(kod);
				  opener.jq('#tvvtsk').focus();
				  
			  }else if(callerType == 'tvmnsk'){
				  opener.jq('#tvmnsk').val(kod);
				  opener.jq('#tvmnsk').focus();
				  
			  }else if(callerType == 'tvdty'){
				  opener.jq('#tvdty').val(kod);
				  opener.jq('#tvdty').focus();
				  
			  }else if(callerType == 'tveh'){
				  opener.jq('#tveh').val(kod);
				  opener.jq('#tveh').focus();
				  
			  }else if(callerType == 'tvskss'){
				  opener.jq('#tvskss').val(kod);
				  opener.jq('#tvskss').focus();
				  
			  }else if(callerType == 'tvlkss'){
				  opener.jq('#tvlkss').val(kod);
				  opener.jq('#tvlkss').focus();
				  
			  }else if(callerType == 'tvskks'){
				  opener.jq('#tvskks').val(kod);
				  opener.jq('#tvskks').focus();
				  
			  }else if(callerType == 'tvlkks'){
				  opener.jq('#tvlkks').val(kod);
				  opener.jq('#tvlkks').focus();
				  
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
  	
  	
	