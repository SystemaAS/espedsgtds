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
			  if(callerType == 'sviv_ulkd'){
				  //Ursp.land 	
				  opener.jq('#sviv_ulkd').val(kod);
				  opener.jq('#sviv_vata').focus();
				  
			  }else if(callerType == 'sviv_fokd'){
				  //förmånskod
				  opener.jq('#sviv_fokd').val(kod);
				  opener.jq('#sviv_eup1').focus();
				  
			  }else if(callerType == 'sviv_eup1'){
				  //Förf.1
				  opener.jq('#sviv_eup1').val(kod);
				  opener.jq('#sviv_eup2').focus();
				  
			  }else if(callerType == 'sviv_eup2'){
				  //Förf.2
				  opener.jq('#sviv_eup2').val(kod);
				  opener.jq('#sviv_brut').focus();
				  
			  }else if(callerType == 'sviv_kosl'){
				  //Kollislag
				  opener.jq('#sviv_kosl').val(kod);
				  opener.jq('#sviv_vasl').focus();
			  
			  }else if(callerType == 'sviv_lagl'){
				  //Kollislag
				  opener.jq('#sviv_lagl').val(kod);
				  opener.jq('#sviv_lagl').focus();
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
  	
  	
	