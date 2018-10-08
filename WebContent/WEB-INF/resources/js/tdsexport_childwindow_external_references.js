	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//--------
  	//Koder
  	//--------
	jq(function() {
		jq('#tblList').on('click', 'td', function(){
			  var id = this.id;
			  var refnr = id.replace("ref", "");
			  //var callerType = record[1].replace("ctype", "");
			  //alert(refnr);
			  
			  opener.jq('#selectedExtRefNr').val(refnr);
			  opener.jq('#selectedExtRefNr').focus();
				  
			  //close child window
			  window.close();
		  });
	});
	
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filter () {
    		jq('#tblList').DataTable().search(
      		jq('#tblList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#tblList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.tblList_filter').on( 'keyup click', function () {
      		filter();
      });
      
    });   
  	
  	
	