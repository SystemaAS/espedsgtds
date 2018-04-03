	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//--------
  	//Koder
  	//--------
	jq(function() {
		jq('#kundensVareRegList').on('click', 'td', function(){
			  var id = this.id;
			  var record = id.split('@');
			  var sviv_knso = record[0].replace("sviw_knso", "");var sviv_vasl = record[1].replace("sviw_vasl", "");
			  var sviv_vata = record[2].replace("sviw_vata", "");var sviv_brut = record[3].replace("sviw_brut", "");
			  var sviv_neto = record[4].replace("sviw_neto", "");var sviv_ulkd = record[5].replace("sviw_ulkd", "");
			  var sviv_fokd = record[6].replace("sviw_fokd", "");var sviv_eup1 = record[7].replace("sviw_eup1", "");
			  var sviv_kota = record[8].replace("sviw_kota", "");var sviv_kosl = record[9].replace("sviw_kosl", "");
			  var sviv_godm = record[10].replace("sviw_godm", "");var sviv_kono = record[11].replace("sviw_kono", "");
			  var sviv_ankv = record[12].replace("sviw_ankv", "");var sviv_suko = record[13].replace("sviw_suko", "");
			  var sviv_sutx = record[14].replace("sviw_sutx", "");var sviv_atin = record[15].replace("sviw_atin", "");
			  var sviv_fabl = record[16].replace("sviw_fabl", "");var sviv_betk = record[17].replace("sviw_betk", "");
			  var sviv_komr = record[18].replace("sviw_komr", "");var sviv_fnkd = record[19].replace("sviw_fnkd", "");
			  var sviv_bit1 = record[20].replace("sviw_bit1", "");var sviv_bii1 = record[21].replace("sviw_bii1", "");
			  var sviv_call = record[22].replace("sviw_call", "");
			  
			  //alert("bit1:" + sviv_bit1);
			  
			  opener.jq('#sviv_vata').val(sviv_vata);opener.jq('#sviv_vasl').val(sviv_vasl); opener.jq('#sviv_brut').val(sviv_brut);opener.jq('#sviv_neto').val(sviv_neto);
			  opener.jq('#sviv_ulkd').val(sviv_ulkd);opener.jq('#sviv_fokd').val(sviv_fokd); opener.jq('#sviv_eup1').val(sviv_eup1);opener.jq('#sviv_kota').val(sviv_kota);
			  opener.jq('#sviv_kosl').val(sviv_kosl);opener.jq('#sviv_godm').val(sviv_godm); opener.jq('#sviv_kono').val(sviv_kono);opener.jq('#sviv_ankv').val(sviv_ankv);
			  opener.jq('#sviv_suko').val(sviv_suko);opener.jq('#sviv_sutx').val(sviv_sutx); opener.jq('#sviv_atin').val(sviv_atin);opener.jq('#sviv_fabl').val(sviv_fabl);
			  opener.jq('#sviv_betk').val(sviv_betk);opener.jq('#sviv_komr').val(sviv_komr); opener.jq('#sviv_fnkd').val(sviv_fnkd);opener.jq('#sviv_bit1').val(sviv_bit1);
			  opener.jq('#sviv_bii1').val(sviv_bii1);opener.jq('#sviv_call').val(sviv_call);
			  
			  //focus on	
			  opener.jq('#sviv_vata').focus();
			  
			  //close child window
			  window.close();
		  });
	});
	
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterGeneralCode () {
    		jq('#kundensVareRegList').DataTable().search(
      		jq('#kundensVareRegList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#kundensVareRegList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.kundensVareRegList_filter').on( 'keyup click', function () {
      		filterGeneralCode();
      });
      
    });   
  	
  	
	