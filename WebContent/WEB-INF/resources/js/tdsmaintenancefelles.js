  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  var BLOCKUI_OVERLAY_MESSAGE_LONG = "Please wait. The operation can take 1-5 min";
  
  jq(function() {
		jq('#alinkRecordId_tulltaxa').click(function(){
			doTulltaxa();
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
  
//---------------------------------------
  //DELETE Order
  //This is done in order to present a jquery
  //Alert modal pop-up
  //----------------------------------------
  function doTulltaxa(){
	  	//Start dialog
	  	jq('<div></div>').dialog({
	        modal: true,
	        title: "Hämta filer från Tulltaxa fildistribution ",
	        width: 420,
	        buttons: {
		        Fortsätt: function() {
	        		jq( this ).dialog( "close" );
		            //do delete
		            jq('#alinkRecordId_tulltaxa').attr('target','_blank');
			    	window.open('tdsmaintenancefelles_tulltaxa.do?action=doInit', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
		        },
		        Avbryt: function() {
		            jq( this ).dialog( "close" );
		        }
	        },
	        open: function() {
		  		  var markup = "Är du säkert på att du vill hämta filerna nu?<p>Operationen kan ta upp till 1 min. Tryck på 'Fortsätt'";
		          jq(this).html(markup);
		          //make Cancel the default button
		          jq(this).siblings('.ui-dialog-buttonpane').find('button:eq(1)').focus();
		     }
		});  //end dialog
  }
  