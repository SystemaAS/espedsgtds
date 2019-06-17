	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//--------
  	//Koder
  	//--------
	jq(function() {
		
		jq('#buttonCodesOk').click(function(){
			//Rubrik 33 (tilläggskoder)
			var checkedCounterTillaggs = 0; //since the opener ONLY can receive max: 2 alternatives. This is the ultimate counter for knowing which receiver (1,2 or 3)
			jq( ".clazzTillaggskodAware" ).each(function( i ) {
				  var id = this.id;
				  var record = id.split('_');
				  var kod = record[0].replace("kod", "");
				  var dtId = record[1].replace("dt", "");
				  var counter = i + 1;
				  //alert("Code:" + kod + "XX" + jq('#kod' + kod + '_' + 'dt' + dtId).prop('checked'));
				  if(jq('#kod' + kod + '_' + 'dt' + dtId).prop('checked')){
					  checkedCounterTillaggs++;
					  //at the moment only 2 alternatives are accountable
					  if(checkedCounterTillaggs==1){
						  opener.jq('#sviv_vati').val(kod);
					  }else if(checkedCounterTillaggs==2){
						  opener.jq('#sviv_vat4').val(kod);
					  }
					  //GUI adapt
					  if(opener.jq('#sviv_vati').val()==''){
						  opener.jq('#warningCodesFlagDiv').show();
					  }else{
						  opener.jq('#warningCodesFlagDiv').hide();
					  }
			  	  }
			});
			//Bilagda handlingar Y-koder
			var checkedCounterYkoder = 0; 
			jq( ".clazzYkodAware" ).each(function( i ) {
				  var id = this.id;
				  var record = id.split('_');
				  var kod = record[0].replace("kod", "");
				  var dtId = record[1].replace("dt", "");
				  var counter = i + 1;
				  
				  //alert("Code:" + kod + "XX" + jq('#kod' + kod + '_' + 'dt' + dtId).prop('checked'));
				  if(jq('#kod' + kod + '_' + 'dt' + dtId).prop('checked')){
					  checkedCounterYkoder++;
					  //sviv_bit1 is reserved for N380 (invoice). so we start from sviv_bit2 and forward
					  if(checkedCounterYkoder==1){
						  opener.jq('#sviv_bit2').val(kod);
					  }else if(checkedCounterYkoder==2){
						  opener.jq('#sviv_bit3').val(kod);
					  }else if(checkedCounterYkoder==3){
						  opener.jq('#sviv_bit4').val(kod);
					  }else if(checkedCounterYkoder==4){
						  opener.jq('#sviv_bit5').val(kod);
					  }
					  
			  	  }
			 });
			opener.jq('#submit').focus();
			//close child window
			window.close();
		});
		
		
	});
	
  	
  	
	