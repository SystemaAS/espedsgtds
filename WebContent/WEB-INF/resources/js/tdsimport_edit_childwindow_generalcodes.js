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
			  
			  //addressing a parent field from this child window
			  if(callerType == 'svih_avlk'){
				  //Typ	
				  opener.jq('#svih_avlk').val(kod);
				  opener.jq('#svih_avlk').focus();
				  
			  }else if(callerType == 'svih_molk'){
				  //Valuta
				  opener.jq('#svih_molk').val(kod);
				  opener.jq('#svih_molk').focus();
				  
			  }else if(callerType == 'svih_vakd'){
				  //Valuta
				  opener.jq('#svih_vakd').val(kod);
				  opener.jq('#svih_vakd').change();
				  opener.jq('#svih_vaku').focus();
				  
			  }else if(callerType == 'svih_dklk'){
				  //Valuta
				  opener.jq('#svih_dklk').val(kod);
				  opener.jq('#svih_dklk').focus();
				  
			  }else if(callerType == 'svih_avut'){
				  //Valuta
				  opener.jq('#svih_avut').val(kod);
				  opener.jq('#svih_trin').focus();
				  
			  }else if(callerType == 'svih_tral'){
				  //Valuta
				  opener.jq('#svih_tral').val(kod);
				  opener.jq('#svih_golk').focus();
				  
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
  	
  	
	