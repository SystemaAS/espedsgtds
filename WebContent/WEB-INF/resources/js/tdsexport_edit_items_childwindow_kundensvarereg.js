	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//--------
  	//Koder
  	//--------
	jq(function() {
		
		jq('#kundensVareRegList').on('click', 'td', function(){
			  var id = this.id;
			  var record = id.split('@');
			  var svev_knso = record[0].replace("svew_knso", "");var svev_vasl = record[1].replace("svew_vasl", "");
			  var svev_vata = record[2].replace("svew_vata", "");var svev_brut = record[3].replace("svew_brut", "");
			  var svev_neto = record[4].replace("svew_neto", "");var svev_ulkd = record[5].replace("svew_ulkd", "");
			  var svev_fokd = record[6].replace("svew_fokd", "");var svev_eup1 = record[7].replace("svew_eup1", "");
			  var svev_kota = record[8].replace("svew_kota", "");var svev_kosl = record[9].replace("svew_kosl", "");
			  var svev_godm = record[10].replace("svew_godm", "");var svev_kono = record[11].replace("svew_kono", "");
			  var svev_ankv = record[12].replace("svew_ankv", "");var svev_suko = record[13].replace("svew_suko", "");
			  var svev_sutx = record[14].replace("svew_sutx", "");var svev_atin = record[15].replace("svew_atin", "");
			  var svev_fabl = record[16].replace("svew_fabl", "");var svev_betk = record[17].replace("svew_betk", "");
			  var svev_komr = record[18].replace("svew_komr", "");var svev_fnkd = record[19].replace("svew_fnkd", "");
			  var svev_bit1 = record[20].replace("svew_bit1", "");var svev_bii1 = record[21].replace("svew_bii1", "");
			  var svev_call = record[22].replace("svew_call", "");
			  
			  //alert("bit1:" + svev_bit1);
			  
			  opener.jq('#svev_vata').val(svev_vata);opener.jq('#svev_vasl').val(svev_vasl); opener.jq('#svev_brut').val(svev_brut);opener.jq('#svev_neto').val(svev_neto);
			  opener.jq('#svev_ulkd').val(svev_ulkd);opener.jq('#svev_fokd').val(svev_fokd); opener.jq('#svev_eup1').val(svev_eup1);opener.jq('#svev_kota').val(svev_kota);
			  opener.jq('#svev_kosl').val(svev_kosl);opener.jq('#svev_godm').val(svev_godm); opener.jq('#svev_kono').val(svev_kono);opener.jq('#svev_ankv').val(svev_ankv);
			  opener.jq('#svev_suko').val(svev_suko);opener.jq('#svev_sutx').val(svev_sutx); opener.jq('#svev_atin').val(svev_atin);opener.jq('#svev_fabl').val(svev_fabl);
			  opener.jq('#svev_betk').val(svev_betk);opener.jq('#svev_komr').val(svev_komr); opener.jq('#svev_fnkd').val(svev_fnkd);opener.jq('#svev_bit1').val(svev_bit1);
			  opener.jq('#svev_bii1').val(svev_bii1);opener.jq('#svev_call').val(svev_call);
			  
			  //focus on	
			  opener.jq('#svev_vata').focus();
			  
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
  	
  	
	