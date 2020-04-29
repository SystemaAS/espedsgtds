	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	
  	//Overlay on tab (to mark visually a delay...)
    jq(function() {
    	jq('#alinkMainList').click(function() { 
      		setBlockUI();
      	  });		
  	  jq('#alinkHeader').click(function() { 
  		setBlockUI();
  	  });
  	  jq('#alinkItemLines').click(function() { 
  		setBlockUI();
  	  });
  	  jq('#alinkLogging').click(function() { 
  		setBlockUI();
  	  });
  	  jq('#alinkArchive').click(function() { 
  		setBlockUI();
  	  });
  	  
    });
    
  	jq(function() {
  	  	jq('#importInvoicesButton').click(function() {
  	  		window.open('tdsexport_edit_childwindow_external_invoices.do?avd=' + jq("#avd").val() + "&opd=" + jq("#opd").val(), 'importInvoicesWin','top=120px,left=100px,height=600px,width=800px,scrollbars=no,status=no,location=no');
  	  	});
  	  	
  	  	//Typ ( same as Bilagda Handlingar on item level)
	    jq('#bilagdaHandIdLink').click(function() {
	    	jq('#bilagdaHandIdLink').attr('target','_blank');
	    	window.open('tdsexport_edit_invoice_childwindow_generalcodes.do?action=doInit&type=MCF&ctype=svef_faty', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#bilagdaHandIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#bilagdaHandIdLink').click();
			}
	    });
	    //Valuta
	    jq('#valutaIdLink').click(function() {
	    	jq('#valutaIdLink').attr('target','_blank');
	    	window.open('tdsexport_edit_invoice_childwindow_generalcodes.do?action=doInit&type=MDX&ctype=svef_vakd', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#valutaIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#valutaIdLink').click();
			}
	    });
  	});
  	
  	//Currency AJAX fetch
	jq(function() { 
	    jq('#svef_vakd').change(function() {
	    	//alert('Hej');
	    	//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('getCurrencyRate_TdsImport.do', {
				applicationUser : jq('#applicationUser').val(),
				currencyCode : jq('#svef_vakd').val(),
				ajax : 'true'
			}, function(data) {
				var len = data.length;
				if(len>0){
					for ( var i = 0; i < len; i++) {
						jq('#svef_vaku').val(data[i].svvk_krs);
						jq('#factor').val(data[i].svvs_omr);
					}
				}else{
					jq('#svef_vaku').val("1");
					jq('#factor').val("1");
				}
				
			});
	    });
	});
  	
  	//-----------------------------------------
  	//Get the item line for a potential Update
  	//-----------------------------------------
  	function getItemData(element) {
  	  var id = element.id;
  	  var record = id.split('_');
  	  var idFak = record[1];
  	  var idTyp = record[2];
  	  
  	  jq.ajax({
  	  	  type: 'GET',
  	  	  url: 'getInvoiceLine_TdsExport.do',
  	  	  data: { applicationUser : jq('#applicationUser').val(),
  		  		  avd : jq('#avd').val(),
  		  		  opd : jq('#opd').val(),
  		  		  idFak : idFak,
  		  		  idTyp : idTyp },
  	  	  dataType: 'json',
  	  	  cache: false,
  	  	  contentType: 'application/json',
  	  	  success: function(data) {
  	  		var len = data.length;
  	  		for ( var i = 0; i < len; i++) {
  	  			jq("#isModeUpdate").val("true");
  	  			jq("#svef_fatx").val(data[i].svef_fatx);
  	  			jq("#svef_fabl" ).val(data[i].svef_fabl);
  	  			jq("#svef_vakd").val(data[i].svef_vakd);
  	  			jq("#svef_vaku").val(data[i].svef_vaku);
  	  			jq("#svef_faty").val(data[i].svef_faty);
  	  		}
  	  		
  	  		jq("#svef_fabl").focus();
	  		//read only
	  		jq("#svef_faty").addClass("inputTextReadOnly");
	  		jq('#svef_faty').find(':not(:selected)').prop('disabled',true);
	  		//read only
	  		jq("#svef_fatx").addClass("inputTextReadOnly");
	  		jq("#svef_fatx").prop("readonly", true);
  	  		
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
      jq('#tblInvoices').DataTable().search(
      	jq('#tblInvoices_filter').val()
      ).draw();
    }

    jq(document).ready(function() {
      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
      jq('#tblInvoices').DataTable( {
    	  "dom": '<"top">t<"bottom"f><"clear">',
  		  "scrollY":    "110px",
  		  "scrollCollapse":  true,
  		  "lengthMenu": [ 25, 50]
  	  });
      //event on input field for search
      jq('input.tblInvoices_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
      
     
	  //to prevent hiding datepicker behind the autocomplete function
	  jq('.datepicker').on('click', function(e) {
		   e.preventDefault();
		   jq(this).attr("autocomplete", "off");  
	  });

  	
    });
    
    
    
    //-------------------------------------------
	  //START Model dialog: "File upload"
	  //-------------------------------------------
	  //Initialize <div> here
	  jq(function() { 
		  jq("#dialogUploadArchiveDocument").dialog({
			  autoOpen: false,
			  maxWidth:400,
	          maxHeight: 300,
	          width: 400,
	          height: 300,
			  modal: true
		  });
	  });
	  //----------------------------
	  //Present dialog box onClick 
	  //----------------------------
	  jq(function() {
		  jq("#uploadFileImg").click(function() {
			  presentUploadFileDialog();
		  });
		  
	  });
	  function presentUploadFileDialog(){
		//setters (add more if needed)
		  jq('#dialogUploadArchiveDocument').dialog( "option", "title", "Upload dokument" );
		  //deal with buttons for this modal window
		  jq('#dialogUploadArchiveDocument').dialog({
			 buttons: [ 
			     /* N/A (look at file-change event instead     
	            {
	             	
				 id: "dialogSaveTU",	
				 text: "Ok",
				 click: function(){
					 		jq('#uploadFileForm').submit();
				 		}
			 	 },*/
	 	 		{
			 	 id: "dialogCancelTU",
			 	 text: "Avbryt", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		//jq("#dialogSaveTU").button("option", "disabled", true);
					 		//jq("#wstype").val("");
					 		jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  //jq("#dialogSaveTU").button("option", "disabled", false);
		  //open now
		  jq('#dialogUploadArchiveDocument').dialog('open');
	  }
	  
	 //----------------
	 // UPLOAD FILE 
	 //----------------
	  function myFileUploadDragEnter(e){
		  jq("#fileUpload").addClass( "isa_blue" );
	  }
	  function myFileUploadDragLeave(e){
		  jq("#fileUpload").removeClass( "isa_blue" );
	  }
	  
	  //Events for the drop downs (some kind of "implicit validation" since all drop downs are mandatory)
	  jq(function() {
		//Triggers drag-and-drop
		  jq('#fileUpload').hover(function(){
			  jq("#fileUpload").removeClass( "isa_success" );
			  jq("#fileUpload").removeClass( "isa_error" );
		  });  
		  
		  jq("#fileUpload").change(function() {
			  jq("#fileUpload").removeClass( "isa_blue" );
			  uploadFile();
		  });
		  
	  });
	  //Upload file
	  function uploadFile(){
			//grab all form data  
			  var form = new FormData(document.getElementById('uploadFileForm'));
			  setBlockUI();
			  
			  jq.ajax({
			  	  type: 'POST',
			  	  url: 'uploadFileToArchiveInvoice.do',
			  	  data: form,  
			  	  dataType: 'text',
			  	  cache: false,
			  	  processData: false,
			  	  contentType: false,
		  		  success: function(data) {
				  	  var len = data.length;
			  		  if(len>0){
			  			jq("#fileUpload").val("");
					  	//Check for errors or successfully processed
					  	var exists = data.indexOf("ERROR");
					  	if(exists>0){
					  		//ERROR on back-end
					  		jq("#fileUpload").addClass( "isa_error" );
					  		jq("#fileUpload").removeClass( "isa_success" );
					  	}else{
					  		//OK
					  		jq("#fileUpload").addClass( "isa_success" );
					  		jq("#fileUpload").removeClass( "isa_error" );
					  	}
					  	//response to end user 
					  	alert(data);
					  	if(data.indexOf('[OK') == 0) {
						  	var trip = '';
						  	var avd = jq("#avd").val();
						  	var opd = jq("#opd").val();
						  	var sign = jq("#sign").val();
						  	//reload
						  	window.location = "tdsexport_edit_invoice.do?action=doFetch&avd=" + avd + "&opd=" + opd + "&sign=" +  sign;
					  	}
					  	//unblock
					  	jq.unblockUI();
			  		  }
			  	  }, 
			  	  error: function() {
			  		  jq.unblockUI();
			  		  alert('Error loading ...');
			  		  jq("#fileUpload").val("");
			  		  //cosmetics
			  		  jq("#fileUpload").addClass( "isa_error" );
			  		  jq("#fileUpload").removeClass( "isa_success" );
				  }
			  });
			      
		  }
	  
	  //-------------------------------------------
	  //END UPLOAD --> Model dialog: "File upload"
	  //-------------------------------------------
	
  	
  	
	