  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  jq(function() {
	  jq("#formRecord").submit(function() {
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT}); 
	  });
  });
  
  jq(function() {
	  jq("#svvk_dts").datepicker({ 
		  dateFormat: 'yymmdd',
		  onSelect: function() {
			    $(this).change();
			  }
	  });
	  jq("#svvk_dte").datepicker({ 
		  dateFormat: 'yymmdd'	  
	  });
  });
  
  
  jq(function() {
		//Clean values for createing new record
		jq('#newRecordButton').click(function() {
			jq('#svvk_kd').val("");
			jq("#svvk_kd").prop("readonly", false);
			jq("#svvk_kd").removeClass("inputTextReadOnly");
			jq("#svvk_kd").addClass("inputTextMediumBlueMandatoryField");
			//
			jq('#svvk_dts').val("");
			jq("#svvk_dts").prop("readonly", false);
			jq("#svvk_dts").removeClass("inputTextReadOnly");
			jq("#svvk_dts").addClass("inputTextMediumBlueMandatoryField");
			
			//rest of the gang
			jq('#svvk_krs').val("");
			jq('#svvk_omr').val("");
			jq('#svvk_dte').val("");
			
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
  	var record = rawId.split('_');
	var currencyCode = record[0];
	var startDate = record[1];
	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_svt057r.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  id : currencyCode,
  		  	  date : startDate},
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			jq('#svvk_kd').val("");jq('#svvk_kd').val(data[i].svvk_kd);
  			jq("#svvk_kd").prop("readonly", true);
  			jq("#svvk_kd").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#svvk_kd").addClass("inputTextReadOnly");
  			
  			jq('#svvk_dts').val("");jq('#svvk_dts').val(data[i].svvk_dts);
  			jq("#svvk_dts").prop("readonly", true);
  			jq("#svvk_dts").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#svvk_dts").addClass("inputTextReadOnly");
  			
  			//rest of the gang
  			jq('#svvk_krs').val("");jq('#svvk_krs').val(data[i].svvk_krs);
  			jq('#svvk_omr').val("");jq('#svvk_omr').val(data[i].svvk_omr);
  			jq('#svvk_dte').val("");jq('#svvk_dte').val(data[i].svvk_dte);
  			
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].svvk_kd);
  			
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
	  var lang = jq('#language').val();
	  jq('#mainList').dataTable( {
    	  "dom": '<"top">t<"bottom"flip><"clear">',
    	  "scrollY": "250px",
    	  "scrollCollapse":  false,
    	  "lengthMenu": [ 75, 100],
		  "language": {
				"url": getLanguage(lang)
	      }     	  
  	  });
      
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
  });
  