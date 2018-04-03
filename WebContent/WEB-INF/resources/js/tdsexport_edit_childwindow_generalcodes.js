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
			  if(callerType == 'sveh_avlk'){
				  //Typ	
				  opener.jq('#sveh_avlk').val(kod);
				  opener.jq('#sveh_avlk').focus();
				  
			  }else if(callerType == 'sveh_molk'){
				  //Valuta
				  opener.jq('#sveh_molk').val(kod);
				  opener.jq('#sveh_molk').focus();
				  
			  }else if(callerType == 'sveh_vakd'){
				  //Valuta
				  opener.jq('#sveh_vakd').val(kod);
				  opener.jq('#sveh_vakd').change();
				  opener.jq('#sveh_vakd').focus();
				  
			  }else if(callerType == 'sveh_dklk'){
				  //Valuta
				  opener.jq('#sveh_dklk').val(kod);
				  opener.jq('#sveh_dklk').focus();
				  
			  }else if(callerType == 'sveh_avut'){
				  //Valuta
				  opener.jq('#sveh_avut').val(kod);
				  opener.jq('#sveh_aube').focus();
				  
			  }else if(callerType == 'sveh_aube'){
				  //Valuta
				  opener.jq('#sveh_aube').val(kod);
				  opener.jq('#sveh_trin').focus();
				  
			  }else if(callerType == 'sveh_trlk'){
				  //Valuta
				  opener.jq('#sveh_trlk').val(kod);
				  opener.jq('#sveh_trgr').focus();
				  
			  }else if(callerType == 'sveh_tral'){
				  //Valuta
				  opener.jq('#sveh_tral').val(kod);
				  opener.jq('#sveh_golk').focus();
				  
			  }else if(callerType == 'sveh_vuva'){
				  //Valuta
				  opener.jq('#sveh_vuva').val(kod);
				  opener.jq('#sveh_vuva').change();
				  opener.jq('#sveh_vuva').focus();
				  
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
  	
  	
	