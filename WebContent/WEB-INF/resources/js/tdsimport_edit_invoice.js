	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
    
  	//Overlay on tab (to mark visually a delay...)
    jq(function() {
  	  jq('#alinkHeader').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });
  	  jq('#alinkItemLines').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });
  	  jq('#alinkLogging').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
  	  jq('#alinkArchive').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
  	  
    });
    
  	
  	jq(function() {
  	  	jq('#importInvoicesButton').click(function() {
  	  		window.open('tdsimport_edit_childwindow_external_invoices.do?avd=' + jq("#avd").val() + "&opd=" + jq("#opd").val(), 'importInvoicesWin','top=120px,left=100px,height=600px,width=800px,scrollbars=no,status=no,location=no');
  	  	});
  	  
  	  	//Typ ( same as Bilagda Handlingar on item level)
	    jq('#bilagdaHandIdLink').click(function() {
	    	jq('#bilagdaHandIdLink').attr('target','_blank');
	    	window.open('tdsimport_edit_invoice_childwindow_generalcodes.do?action=doInit&type=MCF&ctype=svif_faty', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#bilagdaHandIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#bilagdaHandIdLink').click();
			}
	    });
	    //Valuta
	    jq('#valutaIdLink').click(function() {
	    	jq('#valutaIdLink').attr('target','_blank');
	    	window.open('tdsimport_edit_invoice_childwindow_generalcodes.do?action=doInit&type=MDX&ctype=svif_vakd', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#valutaIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#valutaIdLink').click();
			}
	    });
  	  	
  	  	
  	});
  	
  	//Currency AJAX fetch
	jq(function() { 
	    jq('#svif_vakd').change(function() {
	    	//alert('Hej');
	    	//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('getCurrencyRate_TdsImport.do', {
				applicationUser : jq('#applicationUser').val(),
				currencyCode : jq('#svif_vakd').val(),
				ajax : 'true'
			}, function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#svif_vaku').val(data[i].svvk_krs);
					jq('#factor').val(data[i].svvs_omr);
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
  	  var invoiceNr = record[1];
  	  jq.ajax({
  	  	  type: 'GET',
  	  	  url: 'getInvoiceLine_TdsImport.do',
  	  	  data: { applicationUser : jq('#applicationUser').val(),
  		  		  avd : jq('#avd').val(),
  		  		  opd : jq('#opd').val(),
  		  		  invoiceNr : invoiceNr },
  	  	  dataType: 'json',
  	  	  cache: false,
  	  	  contentType: 'application/json',
  	  	  success: function(data) {
  	  		var len = data.length;
  	  		for ( var i = 0; i < len; i++) {
  	  			jq("#isModeUpdate").val("true");
  	  			jq("#svif_fatx").val(data[i].svif_fatx);
  	  			jq("#svif_fabl" ).val(data[i].svif_fabl);
  	  			jq("#svif_vakd").val(data[i].svif_vakd);
  	  			jq("#svif_vaku").val(data[i].svif_vaku);
  	  			jq("#svif_faty").val(data[i].svif_faty);
  	  		}
  	  		jq("#svif_fatx").focus();
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
  	
    });

  	
  	
	