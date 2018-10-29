	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  	
  	//--------
  	//Koder
  	//--------
	jq(function() {
		jq("#datum").datepicker({ 
			  dateFormat: 'yymmdd' 
			  //defaultDate: "-6m"	  
		});
		
		
		//put values from childwindow into opener fields
		jq('#uppdragsList').on('click', 'td', function(){
			  var id = this.id;
			  var record = id.split('@');
			  var avd = record[0].replace("avd", "");
			  var opd = record[1].replace("opd", "");
			  var tullid = record[1].replace("tullid", "");
			  var faktValuta = record[2].replace("valuta", "");
			  var faktBelopp = record[3].replace("blp", "");
			  /* from SKAT ...
			  var xref = record[2].replace("xref", "");
			  var refnr = record[3].replace("refnr", "");
			  var mrn = record[4].replace("mrn", "");
			  */
			  opener.jq('#tvavd2').val(avd);
			  opener.jq('#tvtdn2').val(opd);
			  //opener.jq('#xref').val(xref);
			  //opener.jq('#svxv_221').val(faktValuta);
			  //opener.jq('#svxv_222').val(faktBelopp);
			  
			  if(opener.jq('#tvdref').val()==''){
				  opener.jq('#tvdref').val(tullid);
				  /* FROM SKAT ...
				  if(mrn!=''){
					  opener.jq('#tvdref').val(mrn);
				  }else{	  
					  opener.jq('#tvdref').val(refnr);
				  }*/
			  }
			  
			  opener.jq('#tvtdn2').focus();
			  //close child window
			  window.close();
			  
	    });
		
	});
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterGeneralCode () {
    		jq('#uppdragsList').DataTable().search(
      		jq('#uppdragsList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#uppdragsList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.uppdragsList_filter').on( 'keyup click', function () {
      		filterGeneralCode();
      });
      
    });   
  	
  	
	