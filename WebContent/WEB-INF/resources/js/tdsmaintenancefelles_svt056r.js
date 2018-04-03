  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
//called from form-submit, otherwise the select-disabled won't send the value
  function enableDisabledFields() {
	    document.getElementById('svth_sysg').disabled= "";
  }
  
  
  jq(function() { 
	  //Childwindow on parent signatures
	  jq('#svth_sysgIdLink').click(function() {
		  jq('#svth_sysgIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_signatures.do?action=doFind&id=' + jq('#svth_sysg').val() + '&ctype=svth_sysg', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#svth_sysgIdLink').keypress(function(e){ //extra feature for the end user
		  if(e.which == 13) {
			  jq('#svth_sysgIdLink').click();
		  }
	  });
  });
  
  jq(function() {
		//Clean values for createing new record
		jq('#newRecordButton').click(function() {
			jq('#svth_sysg').val("");
			jq('#svth_sysg').attr('disabled', false);
			jq('#svth_sysg').attr('readOnly', false);
			jq("#svth_sysg").removeClass("inputTextReadOnly");
			jq("#svth_sysg").addClass("inputTextMediumBlueMandatoryField");
			//
			jq('#svth_namn').val("");
			jq('#svth_usid').val("");
			
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
  	var svth_sysg = rawId;
	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_svt056r.do',
  	  data: { applicationUser : applicationUserParam, 
  		  	  id : svth_sysg },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {

  			if(data[i].svth_sysg!=null && data[i].svth_sysg != ''){
  				jq('#svth_sysg').val("");jq('#svth_sysg').val(data[i].svth_sysg);
  				//editable fields
  				//Special treatment for honet when empty since we must map to space-Html representation: meaning = '+'
  				
	  			jq('#svth_sysg').val("");jq('#svth_sysg').val(data[i].svth_sysg);
	  			jq('#svth_sysg').attr('disabled', true);
	  			jq('#svth_sysg').attr('readOnly', true);
				jq("#svth_sysg").removeClass("inputTextMediumBlueMandatoryField");
				jq("#svth_sysg").addClass("inputTextReadOnly");
				//rest of the fields
				jq('#svth_namn').val("");jq('#svth_namn').val(data[i].svth_namn);
  				jq('#svth_usid').val("");jq('#svth_usid').val(data[i].svth_usid);
  				
	  			//for a future update
	  			jq('#updateId').val("");jq('#updateId').val(data[i].svth_sysg);

  			}else{
  				//DEBUG->
  				alert("svth_sysg = NULL ?");
  			}
  			
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
    	  "scrollY": "350px",
    	  "scrollCollapse":  true,
    	  //"columnDefs": [{ "type": "num", "targets": 1 }],
    	  "order": [[ 1, "asc" ]],
    	  "lengthMenu": [ 75, 100]
  	  });
      
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
  });
  