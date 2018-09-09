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
				for ( var i = 0; i < len; i++) {
					jq('#svef_vaku').val(data[i].svvk_krs);
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
  	  	  url: 'getInvoiceLine_TdsExport.do',
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
  	
    });

  	
  	
	