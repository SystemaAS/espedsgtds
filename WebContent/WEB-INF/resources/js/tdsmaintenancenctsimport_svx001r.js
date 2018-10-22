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
		//Clean values for createing new record
		jq('#newRecordButton').click(function() {
			jq('#tkkode').val("");
			jq("#tkkode").prop("readonly", false);
			jq("#tkkode").removeClass("inputTextReadOnly");
			jq("#tkkode").addClass("inputTextMediumBlueMandatoryField");
			
			//rest of the gang
			jq('#tktxtn').val("");
			jq('#tktxte').val("");
			
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
	var tkunik = record[0];
	var tkkode = record[1];
	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_svx001r.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		tkunik : tkunik,
  		tkkode : tkkode},
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			jq('#tkkode').val("");jq('#tkkode').val(data[i].tkkode);
  			jq("#tkkode").prop("readonly", true);
  			jq("#tkkode").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#tkkode").addClass("inputTextReadOnly");
  			
  			//rest of the gang
  			jq('#tkkode').val("");jq('#tkkode').val(data[i].tkkode);
  			jq('#tktxtn').val("");jq('#tktxtn').val(data[i].tktxtn);
  			jq('#tktxte').val("");jq('#tktxte').val(data[i].tktxte);
  			
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].tkunik);
  			
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
    	  "order": [[ 1, "asc" ]],
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
  