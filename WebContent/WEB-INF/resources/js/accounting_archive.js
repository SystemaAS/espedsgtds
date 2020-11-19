var jq = jQuery.noConflict();
var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Vennligst vent...";



jq(function() {
	jq('#submitBtn').click(function() {
		setBlockUI();
		jq('#searchForm').submit();
	});
	
	jq("#own_ardateTo").datepicker({ 
		dateFormat: 'yymmdd'  
	});
	jq("#ardate").datepicker({ 
		dateFormat: 'yymmdd'  
	});
	
});

//-------------------
//Datatables jquery
//-------------------
//private function
function filter () {
  jq('#mainList').DataTable().search(
  	jq('#mainList_filter').val()
  ).draw();
}

jq(document).ready(function() {
  var lang = jq("#language").val();	
  //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
  jq('#mainList').dataTable( {
	  "searchHighlight": true,
	  //"dom": '<"top"fli>rt<"bottom"p><"clear">',
	  "dom": '<"top"f>t<"bottom"lip><"clear">',
	  "scrollY":    "580px",
	  "scrollCollapse":  true,
	  "order": [[ 0, "desc" ], [ 1, "asc" ]],
	  "lengthMenu": [ 75, 100],
	  "language": { "url": getLanguage(lang)},
	  "fnDrawCallback": function( oSettings ) {
          jq('.dataTables_filter input').addClass("inputText12LightYellow");
	  	}
	  });
  //event on input field for search
  jq('input.mainList_filter').on( 'keyup click', function () {
  		filter();
  });
	
});


