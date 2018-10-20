	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//--------
  	//Koder
  	//--------
	jq(function() {
		jq('#customerList').on('click', 'td', function(){
			  var id = this.id;
			  var record = id.split('@');
			 
			  var knr = record[0].replace("knr", "");
			  var knavn = record[1].replace("knavn", "");
			  var adr1 = record[2].replace("kadr1", "");
			  var adr3 = record[3].replace("kadr3", "");
			  var postnr = record[4].replace("kpostnr", "");
			  var land = record[5].replace("kland", "");
			  var eori = record[6].replace("keori", "");
			  var callerType = record[7].replace("ctype", "");
			  var adr2 = record[8].replace("kadr2", "");
			  var tlf = record[9].replace("tlf", "");
			  
			  //addressing a parent field from this child window
			  
			  //=========================
			  //TDS Export Module 
			  //=========================
			  //AVS
			  if(callerType == 'sveh_avna'){
				  opener.jq('#sveh_avkn').val(knr);
				  opener.jq('#sveh_avna').val(knavn);
				  opener.jq('#sveh_aveo').val(eori);
				  opener.jq('#sveh_ava1').val(adr1);
				  opener.jq('#sveh_ava2').val(adr2);
				  opener.jq('#sveh_avpa').val(adr3);
				  opener.jq('#sveh_avpn').val(postnr);
				  opener.jq('#sveh_avlk').val(land);
				  opener.jq('#sveh_avha').val("");
				  opener.jq('#sveh_avtl').val(tlf);
				  //focus
				  opener.jq('#sveh_avna').focus();
			  //MOTTAGARE		 
			  }else if(callerType == 'sveh_mona'){
				  opener.jq('#sveh_mokn').val(knr);
				  opener.jq('#sveh_mona').val(knavn);
				  opener.jq('#sveh_moeo').val(eori);
				  opener.jq('#sveh_moa1').val(adr1);
				  opener.jq('#sveh_moa2').val(adr2);
				  opener.jq('#sveh_mopa').val(adr3);
				  opener.jq('#sveh_mopn').val(postnr);
				  opener.jq('#sveh_molk').val(land);
				  //focus
				  opener.jq('#sveh_mona').focus();
				  
			  //Deklarant
			  }else if(callerType == 'sveh_dkna'){
				  opener.jq('#sveh_dkkn').val(knr);
				  opener.jq('#sveh_dkna').val(knavn);
				  opener.jq('#sveh_dkeo').val(eori);
				  opener.jq('#sveh_dka1').val(adr1);
				  opener.jq('#sveh_dka2').val(adr2);
				  opener.jq('#sveh_dkpa').val(adr3);
				  opener.jq('#sveh_dkpn').val(postnr);
				  opener.jq('#sveh_dklk').val(land);
				  //focus
				  opener.jq('#sveh_dkna').focus();

			  //=========================
			  //TDS Import Module 
			  //=========================
		      //AVS		  
			  }else if(callerType == 'svih_avna'){
				  opener.jq('#svih_avkn').val(knr);
				  opener.jq('#svih_avna').val(knavn);
				  opener.jq('#svih_aveo').val(eori);
				  opener.jq('#svih_ava1').val(adr1);
				  opener.jq('#svih_ava2').val(adr2);
				  opener.jq('#svih_avpa').val(adr3);
				  opener.jq('#svih_avpn').val(postnr);
				  opener.jq('#svih_avlk').val(land);
				  
				  //focus
				  opener.jq('#svih_avna').focus();
			  //MOTTAGARE		 
			  }else if(callerType == 'svih_mona'){
				  opener.jq('#svih_mokn').val(knr);
				  opener.jq('#svih_mona').val(knavn);
				  opener.jq('#svih_moeo').val(eori);
				  opener.jq('#svih_moa1').val(adr1);
				  opener.jq('#svih_moa2').val(adr2);
				  opener.jq('#svih_mopa').val(adr3);
				  opener.jq('#svih_mopn').val(postnr);
				  opener.jq('#svih_molk').val(land);
				  opener.jq('#svih_moha').val("");
				  opener.jq('#svih_motl').val(tlf);
				  //focus
				  opener.jq('#svih_mona').focus();
				  
			  //Deklarant
			  }else if(callerType == 'svih_dkna'){
				  opener.jq('#svih_dkkn').val(knr);
				  opener.jq('#svih_dkna').val(knavn);
				  opener.jq('#svih_dkeo').val(eori);
				  opener.jq('#svih_dka1').val(adr1);
				  opener.jq('#svih_dka2').val(adr2);
				  opener.jq('#svih_dkpa').val(adr3);
				  opener.jq('#svih_dkpn').val(postnr);
				  opener.jq('#svih_dklk').val(land);
				  opener.jq('#svih_dkha').val("");
				  opener.jq('#svih_dktl').val(tlf);
				  //focus
				  opener.jq('#svih_dkna').focus();
			  //=========================
			  //NCTS Export Module 
			  //=========================
		      //AVS		  
			  }else if(callerType == 'thnas'){
				  opener.jq('#thkns').val(knr);
				  opener.jq('#thnas').val(knavn);
				  opener.jq('#thtins').val(eori);
				  opener.jq('#thads1').val(adr1);
				  //opener.jq('#?').val(adr2);
				  opener.jq('#thpss').val(adr3);
				  opener.jq('#thpns').val(postnr);
				  opener.jq('#thlks').val(land);
				  //focus
				  opener.jq('#thnas').focus();
				  
			  //MOTTAGARE		 
			  }else if(callerType == 'thnak'){
				  opener.jq('#thknk').val(knr);
				  opener.jq('#thnak').val(knavn);
				  opener.jq('#thtink').val(eori);
				  opener.jq('#thadk1').val(adr1);
				  //opener.jq('#?').val(adr2);
				  opener.jq('#thpsk').val(adr3);
				  opener.jq('#thpnk').val(postnr);
				  opener.jq('#thlkk').val(land);
				  //focus
				  opener.jq('#thnak').focus();
				  
			  //Ansvarig
			  }else if(callerType == 'thnaa'){
				  //opener.jq('#th...').val(knr);
				  opener.jq('#thnaa').val(knavn);
				  opener.jq('#thtina').val(eori);
				  opener.jq('#thada1').val(adr1);
				  //opener.jq('#?').val(adr2);
				  opener.jq('#thpsa').val(adr3);
				  opener.jq('#thpna').val(postnr);
				  opener.jq('#thlka').val(land);
				  //focus
				  opener.jq('#thnaa').focus();
	  
				//AVS - SÄKERHET	  
			  }else if(callerType == 'thnass'){
				  opener.jq('#thknss').val(knr);
				  opener.jq('#thnass').val(knavn);
				  opener.jq('#thtinss').val(eori);
				  opener.jq('#thadss1').val(adr1);
				  //opener.jq('#?').val(adr2);
				  opener.jq('#thpsss').val(adr3);
				  opener.jq('#thpnss').val(postnr);
				  opener.jq('#thlkss').val(land);
				  //focus
				  opener.jq('#thnass').focus();
				  
			  //MOTTAGARE - SÄKERHET		 
			  }else if(callerType == 'thnaks'){
				  opener.jq('#thknks').val(knr);
				  opener.jq('#thnaks').val(knavn);
				  opener.jq('#thtinks').val(eori);
				  opener.jq('#thadks1').val(adr1);
				  //opener.jq('#?').val(adr2);
				  opener.jq('#thpsks').val(adr3);
				  opener.jq('#thpnks').val(postnr);
				  opener.jq('#thlkks').val(land);
				  //focus
				  opener.jq('#thnaks').focus();
				  
			  //Transportör - SÄKERHET
			  }else if(callerType == 'thnat'){
				  opener.jq('#thknt').val(knr);
				  opener.jq('#thnat').val(knavn);
				  opener.jq('#thtint').val(eori);
				  opener.jq('#thadt1').val(adr1);
				  //opener.jq('#?').val(adr2);
				  opener.jq('#thpst').val(adr3);
				  opener.jq('#thpnt').val(postnr);
				  opener.jq('#thlkt').val(land);
				  //focus
				  opener.jq('#thnat').focus();
			 	 
				//ITEM lines  
			  }else if(callerType == 'tvnas'){
				  opener.jq('#tvkns').val(knr);
				  opener.jq('#tvnas').val(knavn);
				  opener.jq('#tvtins').val(eori);
				  opener.jq('#tvads1').val(adr1);
				  //opener.jq('#?').val(adr2);
				  opener.jq('#tvpss').val(adr3);
				  opener.jq('#tvpns').val(postnr);
				  opener.jq('#tvlks').val(land);
				  //focus
				  opener.jq('#tvnas').focus();
			 	 
			  }else if(callerType == 'tvnak'){
				  opener.jq('#tvknk').val(knr);
				  opener.jq('#tvnak').val(knavn);
				  opener.jq('#tvtink').val(eori);
				  opener.jq('#tvadk1').val(adr1);
				  //opener.jq('#?').val(adr2);
				  opener.jq('#tvpsk').val(adr3);
				  opener.jq('#tvpnk').val(postnr);
				  opener.jq('#tvlkk').val(land);
				  //focus
				  opener.jq('#tvnak').focus();
			 	 
			  //=========================
			  //NCTS Import Module 
			  //=========================
		      //AVS		  
			  }else if(callerType == 'tina'){
				  opener.jq('#tikn').val(knr);
				  opener.jq('#tina').val(knavn);
				  opener.jq('#titin').val(eori);
				  opener.jq('#tiad1').val(adr1);
				  //opener.jq('#?').val(adr2);
				  opener.jq('#tips').val(adr3);
				  opener.jq('#tipn').val(postnr);
				  opener.jq('#tilk').val(land);
				  //focus
				  opener.jq('#tina').focus();
				  
			  //MOTTAGARE		 
			  }
			  
			  //close child window
			  window.close();
		  });
	});
	
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterGeneralCode () {
    		jq('#customerList').DataTable().search(
      		jq('#customerList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#customerList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.customerList_filter').on( 'keyup click', function () {
      		filterGeneralCode();
      });
      
    });   
  	
  	
	