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
			  var tullid = record[2].replace("tullid", "");
			  var faktValuta = record[3].replace("valuta", "");
			  var faktBelopp = record[4].replace("blp", "");
			  var xref = record[5].replace("xref", "");
			  //Used in item lines
			  opener.jq('#tvavd2').val(avd);
			  opener.jq('#tvtdn2').val(opd);
			  opener.jq('#xref').val(xref);
			  
			  if(opener.jq('#tvdref').val()==''){
				  opener.jq('#tvdref').val(tullid);
				 
			  }
			  opener.jq('#tvtdn2').focus();
			  
			  
			  //Used in Create new header (if applicable)
			  opener.jq('#selectedAvd').val(avd);
			  opener.jq('#selectedOpd').val(opd);
			  opener.jq('#selectedExtRefNr').val(xref);
			  opener.jq('#selectedExtRefNr').focus();
			  
			  
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
  	
  	
	