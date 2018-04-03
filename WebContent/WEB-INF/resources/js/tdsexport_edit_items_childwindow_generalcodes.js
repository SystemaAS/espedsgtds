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
			  var text = record[1].replace("text", "");
			  var callerType = record[2].replace("ctype", "");
			  //alert(kod + " " + text + " " + callerType);
			  
			  //addressing a parent field from this child window
			  if(callerType == 'svev_ulkd'){
				  //Ursp.land 	
				  opener.jq('#svev_ulkd').val(kod);
				  opener.jq('#svev_vata').focus();
			  
			  }else if(callerType == 'svev_eup1'){
				  //Ursp.land 	
				  opener.jq('#svev_eup1').val(kod);
				  opener.jq('#svev_eup2').focus();
			  
			  }else if(callerType == 'svev_eup2'){
				  //Ursp.land 	
				  opener.jq('#svev_eup2').val(kod);
				  opener.jq('#svev_brut').focus();
				  
			  }else if(callerType == 'svev_kosl'){
				  //Ursp.land 	
				  opener.jq('#svev_kosl').val(kod);
				  opener.jq('#svev_vasl').focus();
			  
			  }else if(callerType == 'svev_lagl'){
				  //Ursp.land 	
				  opener.jq('#svev_lagl').val(kod);
				  opener.jq('#svev_lagl').focus();
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
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.generalCodeList_filter').on( 'keyup click', function () {
      		filterGeneralCode();
      });
      
    });   
  	
  	
	