package no.systema.tds.tdsexport.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemRecord;

/**
 * 
 * @author oscardelatorre
 *
 */
public class TdsExportItemsValidator implements Validator {

	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonTdsExportSpecificTopicItemRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonTdsExportSpecificTopicItemRecord record = (JsonTdsExportSpecificTopicItemRecord)obj;
		
		//Check for Mandatory fields first
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svev_ulkd", "systema.tds.export.header.error.null.item.svev_ulkd"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svev_vata", "systema.tds.export.header.error.null.item.svev_vata"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svev_eup1", "systema.tds.export.header.error.null.item.svev_eup1");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svev_brut", "systema.tds.export.header.error.null.item.svev_brut");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svev_neto", "systema.tds.export.header.error.null.item.svev_neto");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svev_kosl", "systema.tds.export.header.error.null.item.svev_kosl");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svev_vasl", "systema.tds.export.header.error.null.item.svev_vasl");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svev_godm", "systema.tds.export.header.error.null.item.svev_godm");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svev_fabl", "systema.tds.export.header.error.null.item.svev_fabl");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svev_call", "systema.tds.export.header.error.null.item.svev_call");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svev_kota", "systema.tds.export.header.error.null.item.svev_kota");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svev_stva", "systema.tds.export.header.error.null.item.svev_stva");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svev_stva2", "systema.tds.export.header.error.null.item.svev_stva2");
		

		//Logical controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//varukodkontroll
				if(record.isValidNumberVata()){
					//Statvärde
					if("0".equals(record.getSvev_stva()) ){
						errors.rejectValue("svev_stva", "systema.tds.export.header.error.null.item.svev_stva");
					}
					//Tullvärde
					if("0".equals(record.getSvev_stva2()) ){
						errors.rejectValue("svev_stva2", "systema.tds.export.header.error.null.item.svev_stva2");
					}
					
					//Bruttovikt
					if(record.getSvev_brut()!=null && !"".equals(record.getSvev_brut())){
						Double grossWeight = Double.parseDouble(record.getSvev_brut().replace(",", "."));
						//Check on decimals (requirement)
						if(grossWeight>=1){
							/*OBSOLETE after DHL's meeting Larvik 21.Maj.2017 (CB/OT/OLA plus avd)
							//check if there are decimals (not valid when bruto >=1)
							if(grossWeight % 1 == 0){
								//OK
							}else{
								errors.rejectValue("svev_brut", "systema.tds.export.header.error.rule.item.svev_brut.invalidDecimals");
							}*/
						}
					}
					
					//Nettovikt
					if(record.getSvev_neto()!=null && !"".equals(record.getSvev_neto())){
						try{
							Double grossWeight = Double.parseDouble(record.getSvev_brut().replace(",", "."));
							Double netWeight = Double.parseDouble(record.getSvev_neto().replace(",", "."));
							if(netWeight==0.00D){
								errors.rejectValue("svev_neto", "systema.tds.export.header.error.rule.item.svev_neto.biggerThanZero");
							}else{
								if(netWeight>grossWeight){
									errors.rejectValue("svev_neto", "systema.tds.export.header.error.rule.item.svev_neto.notBiggerThanGross");
									
								}
							}
							/*OBSOLETE after DHL's meeting Larvik 21.Maj.2017 (CB/OT/OLA plus avd)
							//Check on decimals (requirement)
							if(netoWeight>=1){
								//check if there are decimals (not valid when bruto >=1)
								if(netoWeight % 1 == 0){
									//OK
								}else{
									errors.rejectValue("svev_neto", "systema.tds.export.header.error.rule.item.svev_neto.invalidDecimals");
								}
							}
							*/
						}catch(Exception e){
							errors.rejectValue("svev_neto", "systema.tds.export.header.error.rule.item.svev_neto.biggerThanZero");
						}
					}
					//Forfarande 1 (37.1)
					if(record.getSvev_eup1()!=null && !"".equals(record.getSvev_eup1())){
						String tmp = record.getSvev_eup1();
						if(tmp.endsWith("71")){
							if( (record.getSvev_lagt()!=null && !"".equals(record.getSvev_lagt())) && 
								(record.getSvev_lagi()!=null && !"".equals(record.getSvev_lagi())) && 
								(record.getSvev_lagl()!=null && !"".equals(record.getSvev_lagl()))	){
								//ok... pass the test. Do nothing here...
								
							}else{
								//did not pass the test, therefore = error
								errors.rejectValue("svev_lagt", "systema.tds.export.header.error.rule.item.svev_lagt.mustExists");
							}
						}
					}
					
					//Validate extra - mangdenhet is mandatory in some combinations whereas must not exist at all with other combinations
					if("Y".equals(record.getExtraMangdEnhet())){
						if(record.getSvev_ankv()!=null && !"".equals(record.getSvev_ankv())){
							//valid
						}else{
							errors.rejectValue("svev_ankv", "systema.tds.export.header.error.rule.item.svev_ankv.extraMangd.mustExist");
							errors.rejectValue("svev_ankv", "", "(Extra mängdenhet: antal " + record.getExtraMangdEnhetDescription() + ")");
						}
					}else{
						if(record.getSvev_ankv()!=null && !"".equals(record.getSvev_ankv())){
							errors.rejectValue("svev_ankv", "systema.tds.export.header.error.rule.item.svev_ankv.extraMangd.mustNotExist");
						}
					}
					
					
					//-------------------
					//Bilagda Handlingar
					//-------------------
					if(this.isNotNull(record.getSvev_bit1()) ){
						if(record.getSvev_bit1().startsWith("Y") || record.getSvev_bit1().startsWith("X") ){
							//nothing
						}else{
							/*REVISE it ? ... with CB
							if(this.isNotNull(record.getSvev_bit2()) ){
								if(record.getSvev_bit2().startsWith("Y") || record.getSvev_bit2().startsWith("X")){
									//nothing
								}else{
									if(this.isNotNull(record.getSvev_bit3()) ){
										if(record.getSvev_bit3().startsWith("Y") || record.getSvev_bit3().startsWith("X")){
											//nothing
										}else{
											if(this.isNotNull(record.getSvev_bit4()) ){
												if(record.getSvev_bit4().startsWith("Y") || record.getSvev_bit4().startsWith("X")){
													//nothing
												}else{
													errors.rejectValue("svev_bit4", "TODO ?");
												}
											}else{
												errors.rejectValue("svev_bit4", "TODO ?");
											}
										}
									}else{
										errors.rejectValue("svev_bit3", "TODO ?");
									}
								}
							}else{
								errors.rejectValue("svev_bit2", "TODO ?");
							}*/
						}
					}else{
						errors.rejectValue("svev_bit1", "systema.tds.export.header.error.rule.item.svev_bit1.mustExist");
					}
	
					
					//-------------------
					//Tidigare Handlingar
					//-------------------
					//(1)
					if(record.getSvev_tik1()!=null && !"".equals(record.getSvev_tik1())){
						if( (record.getSvev_tit1()==null || "".equals(record.getSvev_tit1()) ) || (record.getSvev_tix1()==null || "".equals(record.getSvev_tix1())) ){
							errors.rejectValue("svev_tik1", "systema.tds.export.header.error.rule.item.svev_tik.allOrNone");	
						}
					}else if(record.getSvev_tit1()!=null && !"".equals(record.getSvev_tit1())){
						if( (record.getSvev_tik1()==null || "".equals(record.getSvev_tik1()) ) || (record.getSvev_tix1()==null || "".equals(record.getSvev_tix1())) ){
							errors.rejectValue("svev_tik1", "systema.tds.export.header.error.rule.item.svev_tik.allOrNone");	
						}
					}else if(record.getSvev_tix1()!=null && !"".equals(record.getSvev_tix1())){
						if( (record.getSvev_tik1()==null || "".equals(record.getSvev_tik1()) ) || (record.getSvev_tit1()==null  || "".equals(record.getSvev_tit1())) ){
							errors.rejectValue("svev_tik1", "systema.tds.export.header.error.rule.item.svev_tik.allOrNone");	
						}
					}
					//(2)
					if(record.getSvev_tik2()!=null && !"".equals(record.getSvev_tik2())){
						if( (record.getSvev_tit2()==null || "".equals(record.getSvev_tit2()) ) || (record.getSvev_tix2()==null || "".equals(record.getSvev_tix2())) ){
							errors.rejectValue("svev_tik2", "systema.tds.export.header.error.rule.item.svev_tik.allOrNone");	
						}
					}else if(record.getSvev_tit2()!=null && !"".equals(record.getSvev_tit2())){
						if( (record.getSvev_tik2()==null || "".equals(record.getSvev_tik2()) ) || (record.getSvev_tix2()==null || "".equals(record.getSvev_tix2())) ){
							errors.rejectValue("svev_tik2", "systema.tds.export.header.error.rule.item.svev_tik.allOrNone");	
						}
					}else if(record.getSvev_tix2()!=null && !"".equals(record.getSvev_tix2())){
						if( (record.getSvev_tik2()==null || "".equals(record.getSvev_tik2()) ) || (record.getSvev_tit2()==null  || "".equals(record.getSvev_tit2())) ){
							errors.rejectValue("svev_tik2", "systema.tds.export.header.error.rule.item.svev_tik.allOrNone");	
						}
					}
					//(3)
					if(record.getSvev_tik3()!=null && !"".equals(record.getSvev_tik3())){
						if( (record.getSvev_tit3()==null || "".equals(record.getSvev_tit3()) ) || (record.getSvev_tix3()==null || "".equals(record.getSvev_tix3())) ){
							errors.rejectValue("svev_tik3", "systema.tds.export.header.error.rule.item.svev_tik.allOrNone");	
						}
					}else if(record.getSvev_tit3()!=null && !"".equals(record.getSvev_tit3())){
						if( (record.getSvev_tik3()==null || "".equals(record.getSvev_tik3()) ) || (record.getSvev_tix3()==null || "".equals(record.getSvev_tix3())) ){
							errors.rejectValue("svev_tik3", "systema.tds.export.header.error.rule.item.svev_tik.allOrNone");	
						}
					}else if(record.getSvev_tix3()!=null && !"".equals(record.getSvev_tix3())){
						if( (record.getSvev_tik3()==null || "".equals(record.getSvev_tik3()) ) || (record.getSvev_tit3()==null  || "".equals(record.getSvev_tit3())) ){
							errors.rejectValue("svev_tik3", "systema.tds.export.header.error.rule.item.svev_tik.allOrNone");	
						}
					}
					//(4)
					if(record.getSvev_tik4()!=null && !"".equals(record.getSvev_tik4())){
						if( (record.getSvev_tit4()==null || "".equals(record.getSvev_tit4()) ) || (record.getSvev_tix4()==null || "".equals(record.getSvev_tix4())) ){
							errors.rejectValue("svev_tik4", "systema.tds.export.header.error.rule.item.svev_tik.allOrNone");	
						}
					}else if(record.getSvev_tit4()!=null && !"".equals(record.getSvev_tit4())){
						if( (record.getSvev_tik4()==null || "".equals(record.getSvev_tik4()) ) || (record.getSvev_tix4()==null || "".equals(record.getSvev_tix4())) ){
							errors.rejectValue("svev_tik4", "systema.tds.export.header.error.rule.item.svev_tik.allOrNone");	
						}
					}else if(record.getSvev_tix4()!=null && !"".equals(record.getSvev_tix4())){
						if( (record.getSvev_tik4()==null || "".equals(record.getSvev_tik4()) ) || (record.getSvev_tit4()==null  || "".equals(record.getSvev_tit4())) ){
							errors.rejectValue("svev_tik4", "systema.tds.export.header.error.rule.item.svev_tik.allOrNone");	
						}
					}
					//(5)
					if(record.getSvev_tik5()!=null && !"".equals(record.getSvev_tik5())){
						if( (record.getSvev_tit5()==null || "".equals(record.getSvev_tit5()) ) || (record.getSvev_tix5()==null || "".equals(record.getSvev_tix5())) ){
							errors.rejectValue("svev_tik5", "systema.tds.export.header.error.rule.item.svev_tik.allOrNone");	
						}
					}else if(record.getSvev_tit5()!=null && !"".equals(record.getSvev_tit5())){
						if( (record.getSvev_tik5()==null || "".equals(record.getSvev_tik5()) ) || (record.getSvev_tix5()==null || "".equals(record.getSvev_tix5())) ){
							errors.rejectValue("svev_tik5", "systema.tds.export.header.error.rule.item.svev_tik.allOrNone");	
						}
					}else if(record.getSvev_tix5()!=null && !"".equals(record.getSvev_tix5())){
						if( (record.getSvev_tik5()==null || "".equals(record.getSvev_tik5()) ) || (record.getSvev_tit3()==null  || "".equals(record.getSvev_tit5())) ){
							errors.rejectValue("svev_tik5", "systema.tds.export.header.error.rule.item.svev_tik.allOrNone");	
						}
					}
					//(6)
					if(record.getSvev_tik6()!=null && !"".equals(record.getSvev_tik6())){
						if( (record.getSvev_tit6()==null || "".equals(record.getSvev_tit6()) ) || (record.getSvev_tix6()==null || "".equals(record.getSvev_tix6())) ){
							errors.rejectValue("svev_tik6", "systema.tds.export.header.error.rule.item.svev_tik.allOrNone");	
						}
					}else if(record.getSvev_tit6()!=null && !"".equals(record.getSvev_tit6())){
						if( (record.getSvev_tik6()==null || "".equals(record.getSvev_tik6()) ) || (record.getSvev_tix6()==null || "".equals(record.getSvev_tix6())) ){
							errors.rejectValue("svev_tik6", "systema.tds.export.header.error.rule.item.svev_tik.allOrNone");	
						}
					}else if(record.getSvev_tix6()!=null && !"".equals(record.getSvev_tix6())){
						if( (record.getSvev_tik6()==null || "".equals(record.getSvev_tik6()) ) || (record.getSvev_tit6()==null  || "".equals(record.getSvev_tit6())) ){
							errors.rejectValue("svev_tik6", "systema.tds.export.header.error.rule.item.svev_tik.allOrNone");	
						}
					}
				}else{
					errors.rejectValue("svev_vata", "systema.tds.export.header.error.rule.item.svev_vata.invalidNumber");
				}
				
			}
			
		}
			
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean isNotNull(String value){
		boolean retval = false;
		if(value!=null && !"".equals(value)){
			retval = true;
		}
		return retval;
	}
}
