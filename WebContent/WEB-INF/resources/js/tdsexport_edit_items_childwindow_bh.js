	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//--------
  	//Koder
  	//--------
	jq(function() {
		
		jq('#buttonCodesOk').click(function(){
			
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
					  //svev_bit1 is reserved for N380 (invoice). so we start from svev_bit2 and forward
					  if(checkedCounterYkoder==1){
						  opener.jq('#svev_bit2').val(kod);
					  }else if(checkedCounterYkoder==2){
						  opener.jq('#svev_bit3').val(kod);
					  }else if(checkedCounterYkoder==3){
						  opener.jq('#svev_bit4').val(kod);
					  }else if(checkedCounterYkoder==4){
						  opener.jq('#svev_bit5').val(kod);
					  }
					  
			  	  }
			 });
			 //GUI adapt
			  if( opener.jq('#svev_bit2').val()=='' && opener.jq('#svev_bit3').val()=='' && opener.jq('#svev_bit4').val()=='' && opener.jq('#svev_bit5').val()==''){
				  opener.jq('#warningCodesFlagDivBh').show();
			  }else{
				  opener.jq('#warningCodesFlagDivBh').hide();
			  }
			opener.jq('#submit').focus();
			//close child window
			window.close();
		});
		
		
	});
	
  	
  	
	