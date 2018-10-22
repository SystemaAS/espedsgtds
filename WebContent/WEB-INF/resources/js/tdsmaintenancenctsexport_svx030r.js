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
			jq('#tggnr').val("");
			jq("#tggnr").prop("readonly", false);
			jq("#tggnr").removeClass("inputTextReadOnly");
			jq("#tggnr").addClass("inputTextMediumBlueMandatoryField");
			
			//rest of the gang
			jq('#tgkna').val("");
			jq('#tgtina').val("");
			jq('#tgnaa').val("");
			jq('#tgada1').val("");
			jq('#tgpna').val("");
			jq('#tgpsa').val("");
			jq('#tglka').val("");
			//Garanti
			jq('#tgtsd').val("");
			jq('#tggty').val("");
			jq('#tggfv').val("");
			jq('#tgakny').val("");
			jq('#tgakgm').val("");
			jq('#tggbl').val("");
			jq('#tggblb').val("");
			jq('#tggvk').val("");
			jq('#tgprm').val("");
			
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
	var tggnr = record[0];
	var moreToCome = record[1];
	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_svx030r.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  id : tggnr },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			
  			jq('#tggnr').val("");jq('#tggnr').val(data[i].tggnr);
  			jq("#tggnr").prop("readonly", true);
  			jq("#tggnr").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#tggnr").addClass("inputTextReadOnly");
  			
  			
  			//rest of the gang
  			jq('#tgkna').val("");jq('#tgkna').val(data[i].tgkna);
			jq('#tgtina').val("");jq('#tgtina').val(data[i].tgtina);
			jq('#tgnaa').val("");jq('#tgnaa').val(data[i].tgnaa);
			jq('#tgada1').val("");jq('#tgada1').val(data[i].tgada1);
			jq('#tgpna').val("");jq('#tgpna').val(data[i].tgpna);
			jq('#tgpsa').val("");jq('#tgpsa').val(data[i].tgpsa);
			jq('#tglka').val("");jq('#tglka').val(data[i].tglka);
			//Garanti
			jq('#tgtsd').val("");jq('#tgtsd').val(data[i].tgtsd);
			jq('#tggty').val("");jq('#tggty').val(data[i].tggty);
			jq('#tggfv').val("");jq('#tggfv').val(data[i].tggfv);
			jq('#tgakny').val("");jq('#tgakny').val(data[i].tgakny);
			jq('#tgakgm').val("");jq('#tgakgm').val(data[i].tgakgm);
			jq('#tggbl').val("");jq('#tggbl').val(data[i].tggbl);
			jq('#tggblb').val("");jq('#tggblb').val(data[i].tggblb);
			jq('#tggvk').val("");jq('#tggvk').val(data[i].tggvk);
			jq('#tgprm').val("");jq('#tgprm').val(data[i].tgprm);
			
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].tggnr);

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
  