  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  jq(function() {
	  jq("#formRecord").submit(function() {
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT}); 
	  });
  });
  
  jq(function() {
	  /*
	  jq("#svvk_dts").datepicker({ 
		  dateFormat: 'yymmdd',
		  onSelect: function() {
			    $(this).change();
			  }
	  });
	  jq("#svvk_dte").datepicker({ 
		  dateFormat: 'yymmdd'	  
	  });
	  */
  });
  
  
  jq(function() {
		//Clean values for createing new record
		jq('#newRecordButton').click(function() {
			jq('#svlv_kd').val("");
			jq("#svlv_kd").prop("readonly", false);
			jq("#svlv_kd").removeClass("inputTextReadOnly");
			jq("#svlv_kd").addClass("inputTextMediumBlueMandatoryField");
			
			//rest of the gang
			jq('#svlv_ok').val("");
  			jq('#svlv_tr').val("");
  			jq('#svlv_tr2').val("");
  			jq('#svlv_fs').val("");
  			jq('#svlv_fsp').val("");
  			jq('#svlv_fs2').val("");
  			jq('#svlv_fs2p').val("");
  			
  			jq('#svlv_ok').val("");
  			jq('#svlv_kr').val("");
  			jq('#svlv_ar').val("");
			
			//for update
			jq('#updateId').val("");
		});
  }); 
  
  //-----------------------
  //GET specific db-record
  //-----------------------
  function getRecord(record){
	var rawId = record.id;
  	var applicationUserParam = jq('#applicationUser').val();
  	
  	rawId = rawId.replace("recordUpdate_", "");
  	//var record = rawId.split('_');
	//var id = record[0];
	var id = rawId;
	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_svt058r.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  id : id },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			jq('#svlv_kd').val("");jq('#svlv_kd').val(data[i].svlv_kd);
  			jq("#svlv_kd").prop("readonly", true);
  			jq("#svlv_kd").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#svlv_kd").addClass("inputTextReadOnly");
  			
  			
  			//rest of the gang
  			jq('#svlv_ok').val("");jq('#svlv_ok').val(data[i].svlv_ok);
  			jq('#svlv_tr').val("");jq('#svlv_tr').val(data[i].svlv_tr);
  			jq('#svlv_tr2').val("");jq('#svlv_tr2').val(data[i].svlv_tr2);
  			jq('#svlv_fs').val("");jq('#svlv_fs').val(data[i].svlv_fs);
  			jq('#svlv_fsp').val("");jq('#svlv_fsp').val(data[i].svlv_fspFormatted);
  			jq('#svlv_fs2').val("");jq('#svlv_fs2').val(data[i].svlv_fs2);
  			jq('#svlv_fs2p').val("");jq('#svlv_fs2p').val(data[i].svlv_fs2pFormatted);
  			
  			jq('#svlv_ok').val("");jq('#svlv_ok').val(data[i].svlv_ok);
  			jq('#svlv_kr').val("");jq('#svlv_kr').val(data[i].svlv_kr);
  			jq('#svlv_ar').val("");jq('#svlv_ar').val(data[i].svlv_ar);
  			
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].svlv_kd);
  			
  		}
  	  }, 
  	  error: function() {
  		  alert('Error loading ...');
  	  }
	});
		
  }
  
			
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
    	  "scrollY": "250px",
    	  "scrollCollapse":  false,
    	  "order": [[ 1, "asc" ]],
    	  "lengthMenu": [ 75, 100]
  	  });
      
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
  });
  