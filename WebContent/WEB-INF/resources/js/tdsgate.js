//General functions for this JSP side - AJAX
var jq = jQuery.noConflict();

//pre-run some services
jq(document).ready(function(){
	jq.getJSON('getCurrencyRate.do', {
		applicationUser : "SYSTEMA",
		currencyCode : "SEK",
		ajax : 'true'
	}, function(data) {
		var len = data.length;
		for ( var i = 0; i < len; i++) {
			//data[i].svvk_krs;
			//data[i].svvs_omr;
			//q('#sveh_vaku').val(data[i].svvk_krs);
			//jq('#sveh_vaom').val(data[i].svvs_omr);
		}
		
	});
});

  	