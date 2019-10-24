  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  var BLOCKUI_OVERLAY_MESSAGE_LONG = "Please wait. The operation can take 1-5 min";
  
  jq(function() {
		jq('#alinkRecordId_tulltaxa').click(function(){
			jq('#alinkRecordId_tulltaxa').attr('target','_blank');
	    	window.open('tdsmaintenancefelles_tulltaxa.do?action=doInit', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    	
		});
  });
  //-------------------
  //Datatables jquery
  //-------------------
  //private function
  function filterGlobal () {
    jq('#mainList').dataTable().search(
    	jq('#mainList_filter').val()
    ).draw();
  }
  
  jq(document).ready(function() {
      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
      jq('#mainList').dataTable( {
    	  "dom": '<"top">t<"bottom"flip><"clear">',
          "paging":   false,
          "ordering": false,
          "info":     false,
          "searching":     false,
  	  });
      
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
  });
  