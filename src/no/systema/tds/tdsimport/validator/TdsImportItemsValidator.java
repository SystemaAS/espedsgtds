package no.systema.tds.tdsimport.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemRecord;

/**
 * 
 * 
 * @author oscardelatorre
 *
 */
public class TdsImportItemsValidator implements Validator {
	private final String NOT_FOUND = "NOT FOUND";

	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonTdsImportSpecificTopicItemRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * 
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonTdsImportSpecificTopicItemRecord record = (JsonTdsImportSpecificTopicItemRecord)obj;
		
		//Check for Mandatory fields first
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sviv_ulkd", "systema.tds.import.header.error.null.item.sviv_ulkd");
		
		//Only for some types of message types on header (meddelandetyp: sveh_mtyp)
		if(!record.getHeader_svih_mtyp().startsWith("H")){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sviv_vata", "systema.tds.import.header.error.null.item.sviv_vata"); 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sviv_fokd", "systema.tds.import.header.error.null.item.sviv_fokd");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sviv_neto", "systema.tds.import.header.error.null.item.sviv_neto");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sviv_fabl", "systema.tds.import.header.error.null.item.sviv_fabl");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sviv_stva", "systema.tds.import.header.error.null.item.sviv_stva");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sviv_stva2", "systema.tds.import.header.error.null.item.sviv_stva2");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sviv_eup1", "systema.tds.import.header.error.null.item.sviv_eup1");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sviv_brut", "systema.tds.import.header.error.null.item.sviv_brut");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sviv_vasl", "systema.tds.import.header.error.null.item.sviv_vasl");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sviv_kota", "systema.tds.import.header.error.null.item.sviv_kota");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sviv_kosl", "systema.tds.import.header.error.null.item.sviv_kosl");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sviv_godm", "systema.tds.import.header.error.null.item.sviv_godm");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sviv_call", "systema.tds.import.header.error.null.item.sviv_call");
		
		
		//Logical controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				if(record.isValidNumberVata()){
					
					//Bruttovikt
					if(record.getSviv_brut()!=null && !"".equals(record.getSviv_brut())){
						Double grossWeight = Double.parseDouble(record.getSviv_brut().replace(",", "."));
						//Check on decimals (requirement)
						/*OBSOLETE after DHL's meeting Larvik 21.Maj.2017 (CB/OT/OLA plus avd)
						//check if there are decimals (not valid when bruto >=1)
						if(grossWeight>=1){
							if(grossWeight % 1 == 0){
								//OK
							}else{
								errors.rejectValue("sviv_brut", "systema.tds.import.header.error.rule.item.sviv_brut.invalidDecimals");
							}
							
						}*/
					}
					//Nettovikt
					if(record.getSviv_neto()!=null && !"".equals(record.getSviv_neto())){
						try{
							Double grossWeight = Double.parseDouble(record.getSviv_brut().replace(",", "."));
							Double netWeight = Double.parseDouble(record.getSviv_neto().replace(",", "."));
							if(netWeight==0.00D){
								errors.rejectValue("sviv_neto", "systema.tds.import.header.error.rule.item.sviv_neto.biggerThanZero");
							}else{
								if(netWeight>grossWeight){
									errors.rejectValue("sviv_neto", "systema.tds.import.header.error.rule.item.sviv_neto.notBiggerThanGross");
								}
							}
							/*OBSOLETE after DHL's meeting Larvik 21.Maj.2017 (CB/OT/OLA plus avd)
							//Check on decimals (requirement)
							if(netoWeight>=1){
								//check if there are decimals (not valid when bruto >=1)
								if(netoWeight % 1 == 0){
									//OK
								}else{
									errors.rejectValue("sviv_neto", "systema.tds.import.header.error.rule.item.sviv_neto.invalidDecimals");
								}
							}
							*/
						}catch(Exception e){
							errors.rejectValue("sviv_neto", "systema.tds.import.header.error.rule.item.sviv_neto.biggerThanZero");
						}
					}
					//Forfarande 1 (37.1)
					if(record.getSviv_eup1()!=null && !"".equals(record.getSviv_eup1())){
						String tmp = record.getSviv_eup1();
						if(tmp.endsWith("71")){
							if( (record.getSviv_lagt()!=null && !"".equals(record.getSviv_lagt())) && 
								(record.getSviv_lagi()!=null && !"".equals(record.getSviv_lagi())) && 
								(record.getSviv_lagl()!=null && !"".equals(record.getSviv_lagl()))	){
								//ok... pass the test. Do nothing here...
								
							}else{
								//did not pass the test, therefore = error
								errors.rejectValue("sviv_lagt", "systema.tds.import.header.error.rule.item.sviv_lagt.mustExists");
							}
						}
					}
					
					//Varukod
					if(!record.getHeader_svih_mtyp().startsWith("H")){
						if(record.getSviv_vata()!=null && !"".equals(record.getSviv_vata())){
							if(this.NOT_FOUND.equals(record.getSviv_vata())){
								errors.rejectValue("sviv_vata", "systema.tds.import.header.error.rule.item.sviv_vata.notFound");
							}else{
								int VARUKOD_IMPORT_VALID_LENGTH = 10;
								int validLength = record.getSviv_vata().length();
								if(validLength!=VARUKOD_IMPORT_VALID_LENGTH){
									errors.rejectValue("sviv_vata", "systema.tds.import.header.error.rule.item.sviv_vata.tenNumbers");
								}
							}
						}
					}
					//----------------------------
					//Rubrik 31 (many rules) KOTA
					//----------------------------
					//(a) Antal kolli
					//Antal delar för oemballerat gods kan aldrig vara noll eller tomt. Om kollislag avser bulkvaror skall fältet
					//lämnas tomt. Varje deklarerad bulkpost räknas dock som ett vid summering av antal kollin i fält 6.
					if(record.getSviv_kota()!=null && !"".equals(record.getSviv_kota())){
						if(this.isBulkGods(record.getSviv_kosl())){
							//bulkvaror should be empty (or Zero)
							if(this.isZero(record.getSviv_kota())){
								//valid
							}else{
								errors.rejectValue("sviv_kota", "systema.tds.import.header.error.rule.item.sviv_kota.mustBeEmpty");
							}
						}else if(this.isOemballeratGods(record.getSviv_kosl())){
							//oemballerat gods should never be Zero
							if(this.isZero(record.getSviv_kota())){
								errors.rejectValue("sviv_kota", "systema.tds.import.header.error.rule.item.sviv_kota.otherThanZero");
							}
						}else{
							//only with the first item line, otherwise it is valid to send kota=0 with all others kosl (units) and item lines
							if(record.getSviv_syli().equals("1")){
								if(this.isZero(record.getSviv_kota())){
									errors.rejectValue("sviv_kota", "systema.tds.import.header.error.rule.item.sviv_kota.otherThanZero");
								}
							}
						}
					}else{
						if(record.getSviv_kosl()!=null && !"".equals(record.getSviv_kosl())){
							if(this.isBulkGods(record.getSviv_kosl())){
								//valid
							}else{
								errors.rejectValue("sviv_kota", "systema.tds.import.header.error.rule.item.sviv_kota.mustHaveValidValue");
							}
						}
					}
					//(b) Godsmarkning special rule
					if(record.getSviv_godm()!=null && !"".equals(record.getSviv_godm())){
						//valid
					}else{
						//there are exceptions that allow Godsmarke to be null
						if(record.getSviv_kosl()!=null && !"".equals(record.getSviv_kosl())){
							if(this.isOemballeratGods(record.getSviv_kosl())){
								//valid
							}else if(this.isBulkGods(record.getSviv_kosl())){
								//valid
							}else{
								errors.rejectValue("sviv_godm", "systema.tds.import.header.error.null.item.sviv_godm");
							}
						}
					}
					//(c) Container flag from header (Rubrik 19)
					if(record.getHeader_svih_cont()!=null && record.getHeader_svih_cont().equals("1")){
						if(record.getSviv_co01()!=null && !"".equals(record.getSviv_co01())){						
							//valid
						}else{
							errors.rejectValue("sviv_co01", "systema.tds.import.header.error.rule.item.sviv_co01.mustExists");
						}
					}
					//---------------
					//End Rubrik 31 KOTA
					//---------------	
					
					
					//----------------------------
					//Rubrik 31 (many rules) KOT2
					//----------------------------
					//(a) Antal kolli
					//Antal delar för oemballerat gods kan aldrig vara noll eller tomt. Om kollislag avser bulkvaror skall fältet
					//lämnas tomt. Varje deklarerad bulkpost räknas dock som ett vid summering av antal kollin i fält 6.
					if(record.getSviv_kot2()!=null && !"".equals(record.getSviv_kot2())){
						if(this.isBulkGods(record.getSviv_kos2())){
							//bulkvaror should be empty (or Zero)
							if(this.isZero(record.getSviv_kot2())){
								//valid
							}else{
								errors.rejectValue("sviv_kot2", "systema.tds.import.header.error.rule.item.sviv_kot2.mustBeEmpty");
							}
						}else if(this.isOemballeratGods(record.getSviv_kos2())){
							//oemballerat gods should never be Zero
							if(this.isZero(record.getSviv_kot2())){
								errors.rejectValue("sviv_kot2", "systema.tds.import.header.error.rule.item.sviv_kot2.otherThanZero");
							}
						}else{
							//only with the first item line, otherwise it is valid to send kota=0 with all others kosl (units) and item lines
							if(record.getSviv_syli().equals("1")){
								if(this.isZero(record.getSviv_kot2())){
									errors.rejectValue("sviv_kot2", "systema.tds.import.header.error.rule.item.sviv_kot2.otherThanZero");
								}
							}
							if(record.getSviv_kos2()==null || "".equals(record.getSviv_kos2()) ){
								errors.rejectValue("sviv_kos2", "systema.tds.import.header.error.rule.item.sviv_kos2.mustHaveValidValue");
							}
						}
					}else{
						if(record.getSviv_kos2()!=null && !"".equals(record.getSviv_kos2())){
							if(this.isBulkGods(record.getSviv_kos2())){
								//valid
							}else{
								errors.rejectValue("sviv_kos2", "systema.tds.import.header.error.rule.item.sviv_kot2.mustHaveValidValue");
							}
						}
					}
					//(b) Godsmarkning special rule
					if(record.getSviv_god2()!=null && !"".equals(record.getSviv_god2())){
						//valid
					}else{
						//there are exceptions that allow Godsmarke to be null
						if(record.getSviv_kos2()!=null && !"".equals(record.getSviv_kos2())){
							if(this.isOemballeratGods(record.getSviv_kos2())){
								//valid
							}else if(this.isBulkGods(record.getSviv_kos2())){
								//valid
							}else{
								errors.rejectValue("sviv_god2", "systema.tds.import.header.error.null.item.sviv_god2");
							}
						}
					}
					//------------------
					//End Rubrik 31 KOT2
					//------------------
					
	
					//----------------------------
					//Rubrik 31 (many rules) KOT3
					//----------------------------
					//(a) Antal kolli
					//Antal delar för oemballerat gods kan aldrig vara noll eller tomt. Om kollislag avser bulkvaror skall fältet
					//lämnas tomt. Varje deklarerad bulkpost räknas dock som ett vid summering av antal kollin i fält 6.
					if(record.getSviv_kot3()!=null && !"".equals(record.getSviv_kot3())){
						if(this.isBulkGods(record.getSviv_kos3())){
							//bulkvaror should be empty (or Zero)
							if(this.isZero(record.getSviv_kot3())){
								//valid
							}else{
								errors.rejectValue("sviv_kot3", "systema.tds.import.header.error.rule.item.sviv_kot3.mustBeEmpty");
							}
						}else if(this.isOemballeratGods(record.getSviv_kos3())){
							//oemballerat gods should never be Zero
							if(this.isZero(record.getSviv_kot3())){
								errors.rejectValue("sviv_kot3", "systema.tds.import.header.error.rule.item.sviv_kot3.otherThanZero");
							}
						}else{
							//only with the first item line, otherwise it is valid to send kota=0 with all others kosl (units) and item lines
							if(record.getSviv_syli().equals("1")){
								if(this.isZero(record.getSviv_kot3())){
									errors.rejectValue("sviv_kot3", "systema.tds.import.header.error.rule.item.sviv_kot3.otherThanZero");
								}
							}
						}
					}else{
						if(record.getSviv_kos3()!=null && !"".equals(record.getSviv_kos3())){
							if(this.isBulkGods(record.getSviv_kos3())){
								//valid
							}else{
								errors.rejectValue("sviv_kos3", "systema.tds.import.header.error.rule.item.sviv_kot3.mustHaveValidValue");
							}
						}
					}
					//(b) Godsmarkning special rule
					if(record.getSviv_god3()!=null && !"".equals(record.getSviv_god3())){
						//valid
					}else{
						//there are exceptions that allow Godsmarke to be null
						if(record.getSviv_kos3()!=null && !"".equals(record.getSviv_kos3())){
							if(this.isOemballeratGods(record.getSviv_kos3())){
								//valid
							}else if(this.isBulkGods(record.getSviv_kos3())){
								//valid
							}else{
								errors.rejectValue("sviv_god3", "systema.tds.import.header.error.null.item.sviv_god3");
							}
						}
					}
					//------------------
					//End Rubrik 31 KOT3
					//------------------
					
					
					//----------------------------
					//Rubrik 31 (many rules) KOT4
					//----------------------------
					//(a) Antal kolli
					//Antal delar för oemballerat gods kan aldrig vara noll eller tomt. Om kollislag avser bulkvaror skall fältet
					//lämnas tomt. Varje deklarerad bulkpost räknas dock som ett vid summering av antal kollin i fält 6.
					if(record.getSviv_kot4()!=null && !"".equals(record.getSviv_kot4())){
						if(this.isBulkGods(record.getSviv_kos4())){
							//bulkvaror should be empty (or Zero)
							if(this.isZero(record.getSviv_kot4())){
								//valid
							}else{
								errors.rejectValue("sviv_kot4", "systema.tds.import.header.error.rule.item.sviv_kot4.mustBeEmpty");
							}
						}else if(this.isOemballeratGods(record.getSviv_kos4())){
							//oemballerat gods should never be Zero
							if(this.isZero(record.getSviv_kot4())){
								errors.rejectValue("sviv_kot4", "systema.tds.import.header.error.rule.item.sviv_kot4.otherThanZero");
							}
						}else{
							//only with the first item line, otherwise it is valid to send kota=0 with all others kosl (units) and item lines
							if(record.getSviv_syli().equals("1")){
								if(this.isZero(record.getSviv_kot4())){
									errors.rejectValue("sviv_kot4", "systema.tds.import.header.error.rule.item.sviv_kot4.otherThanZero");
								}
							}
						}
					}else{
						if(record.getSviv_kos4()!=null && !"".equals(record.getSviv_kos4())){
							if(this.isBulkGods(record.getSviv_kos4())){
								//valid
							}else{
								errors.rejectValue("sviv_kos4", "systema.tds.import.header.error.rule.item.sviv_kot4.mustHaveValidValue");
							}
						}
					}
					//(b) Godsmarkning special rule
					if(record.getSviv_god4()!=null && !"".equals(record.getSviv_god4())){
						//valid
					}else{
						//there are exceptions that allow Godsmarke to be null
						if(record.getSviv_kos4()!=null && !"".equals(record.getSviv_kos4())){
							if(this.isOemballeratGods(record.getSviv_kos4())){
								//valid
							}else if(this.isBulkGods(record.getSviv_kos4())){
								//valid
							}else{
								errors.rejectValue("sviv_god4", "systema.tds.import.header.error.null.item.sviv_god4");
							}
						}
					}
					//------------------
					//End Rubrik 31 KOT4
					//------------------
					
					
					//----------------------------
					//Rubrik 31 (many rules) KOT5
					//----------------------------
					//(a) Antal kolli
					//Antal delar för oemballerat gods kan aldrig vara noll eller tomt. Om kollislag avser bulkvaror skall fältet
					//lämnas tomt. Varje deklarerad bulkpost räknas dock som ett vid summering av antal kollin i fält 6.
					if(record.getSviv_kot5()!=null && !"".equals(record.getSviv_kot5())){
						if(this.isBulkGods(record.getSviv_kos5())){
							//bulkvaror should be empty (or Zero)
							if(this.isZero(record.getSviv_kot5())){
								//valid
							}else{
								errors.rejectValue("sviv_kot5", "systema.tds.import.header.error.rule.item.sviv_kot5.mustBeEmpty");
							}
						}else if(this.isOemballeratGods(record.getSviv_kos5())){
							//oemballerat gods should never be Zero
							if(this.isZero(record.getSviv_kot5())){
								errors.rejectValue("sviv_kot5", "systema.tds.import.header.error.rule.item.sviv_kot5.otherThanZero");
							}
						}else{
							//only with the first item line, otherwise it is valid to send kota=0 with all others kosl (units) and item lines
							if(record.getSviv_syli().equals("1")){
								if(this.isZero(record.getSviv_kot5())){
									errors.rejectValue("sviv_kot5", "systema.tds.import.header.error.rule.item.sviv_kot5.otherThanZero");
								}
							}
						}
					}else{
						if(record.getSviv_kos5()!=null && !"".equals(record.getSviv_kos5())){
							if(this.isBulkGods(record.getSviv_kos5())){
								//valid
							}else{
								errors.rejectValue("sviv_kos5", "systema.tds.import.header.error.rule.item.sviv_kot5.mustHaveValidValue");
							}
						}
					}
					//(b) Godsmarkning special rule
					if(record.getSviv_god5()!=null && !"".equals(record.getSviv_god5())){
						//valid
					}else{
						//there are exceptions that allow Godsmarke to be null
						if(record.getSviv_kos5()!=null && !"".equals(record.getSviv_kos5())){
							if(this.isOemballeratGods(record.getSviv_kos5())){
								//valid
							}else if(this.isBulkGods(record.getSviv_kos5())){
								//valid
							}else{
								errors.rejectValue("sviv_god5", "systema.tds.import.header.error.null.item.sviv_god5");
							}
						}
					}
					//------------------
					//End Rubrik 31 KOT5
					//------------------
					
					
					
					//Varukod 33.4
					if(record.getSviv_vat4()!=null && !"".equals(record.getSviv_vat4())){
						if(record.getSviv_vati()!=null && !"".equals(record.getSviv_vati())){
							//valid
						}else{
							errors.rejectValue("sviv_vat4", "systema.tds.import.header.error.rule.item.sviv_vati.mustExists");
						}
					}
					
					//Ursrpungslandkod
					//EU får endast användas vid återimport och när du samtidigt yrkar på preferenstull, det vill säga då du i fält 37 (sviv_eup1)
					//anger en förfarandekod som börjar på 6 eller koderna 4010 och 4071 samt en förmånskod (sviv_fokd) i fält 36 som börjar
					//på 2 eller 3
					if("SE".equals(record.getSviv_ulkd())){
						errors.rejectValue("sviv_ulkd", "systema.tds.import.header.error.rule.item.sviv_ulkd.invalid.countryCodes");
						
					}else if("EU".equals(record.getSviv_ulkd())){
						if( (record.getSviv_eup1()!=null && !"".equals(record.getSviv_eup1())) && 
							(record.getSviv_fokd()!=null && !"".equals(record.getSviv_fokd()))	){
							//now to the final check
							if(this.isValidUrsprungsland_EU(record)){
								//valid
							}else{
								errors.rejectValue("sviv_ulkd", "systema.tds.import.header.error.rule.item.sviv_ulkd.invalid.countryCodes");
							}
							
						}else{
							errors.rejectValue("sviv_ulkd", "systema.tds.import.header.error.rule.item.sviv_ulkd.invalid.countryCodes");
						}
					}
					
					//Validate extra - mangdenhet is mandatory in some combinations whereas must not exist at all with other combinations
					if("Y".equals(record.getExtraMangdEnhet())){
						if(record.getSviv_ankv()!=null && !"".equals(record.getSviv_ankv())){
							//valid
						}else{
							errors.rejectValue("sviv_ankv", "systema.tds.import.header.error.rule.item.sviv_ankv.extraMangd.mustExist");
							errors.rejectValue("sviv_ankv", "", "(Extra mängdenhet: antal " + record.getExtraMangdEnhetDescription() + ")");
						}
					}else{
						if(record.getSviv_ankv()!=null && !"".equals(record.getSviv_ankv())){
							errors.rejectValue("sviv_ankv", "systema.tds.import.header.error.rule.item.sviv_ankv.extraMangd.mustNotExist");
						}
					}
					//-------------------
					//Tidigare Handlingar
					//-------------------
					//(1)
					if(record.getSviv_tik1()!=null && !"".equals(record.getSviv_tik1())){
						if( (record.getSviv_tit1()==null || "".equals(record.getSviv_tit1()) ) || (record.getSviv_tix1()==null || "".equals(record.getSviv_tix1())) ){
							errors.rejectValue("sviv_tik1", "systema.tds.import.header.error.rule.item.sviv_tik.allOrNone");	
						}
					}else if(record.getSviv_tit1()!=null && !"".equals(record.getSviv_tit1())){
						if( (record.getSviv_tik1()==null || "".equals(record.getSviv_tik1()) ) || (record.getSviv_tix1()==null || "".equals(record.getSviv_tix1())) ){
							errors.rejectValue("sviv_tik1", "systema.tds.import.header.error.rule.item.sviv_tik.allOrNone");	
						}
					}else if(record.getSviv_tix1()!=null && !"".equals(record.getSviv_tix1())){
						if( (record.getSviv_tik1()==null || "".equals(record.getSviv_tik1()) ) || (record.getSviv_tit1()==null  || "".equals(record.getSviv_tit1())) ){
							errors.rejectValue("sviv_tik1", "systema.tds.import.header.error.rule.item.sviv_tik.allOrNone");	
						}
					}
					//(2)
					if(record.getSviv_tik2()!=null && !"".equals(record.getSviv_tik2())){
						if( (record.getSviv_tit2()==null || "".equals(record.getSviv_tit2()) ) || (record.getSviv_tix2()==null || "".equals(record.getSviv_tix2())) ){
							errors.rejectValue("sviv_tik2", "systema.tds.import.header.error.rule.item.sviv_tik.allOrNone");	
						}
					}else if(record.getSviv_tit2()!=null && !"".equals(record.getSviv_tit2())){
						if( (record.getSviv_tik2()==null || "".equals(record.getSviv_tik2()) ) || (record.getSviv_tix2()==null || "".equals(record.getSviv_tix2())) ){
							errors.rejectValue("sviv_tik2", "systema.tds.import.header.error.rule.item.sviv_tik.allOrNone");	
						}
					}else if(record.getSviv_tix2()!=null && !"".equals(record.getSviv_tix2())){
						if( (record.getSviv_tik2()==null || "".equals(record.getSviv_tik2()) ) || (record.getSviv_tit2()==null  || "".equals(record.getSviv_tit2())) ){
							errors.rejectValue("sviv_tik2", "systema.tds.import.header.error.rule.item.sviv_tik.allOrNone");	
						}
					}
					//(3)
					if(record.getSviv_tik3()!=null && !"".equals(record.getSviv_tik3())){
						if( (record.getSviv_tit3()==null || "".equals(record.getSviv_tit3()) ) || (record.getSviv_tix3()==null || "".equals(record.getSviv_tix3())) ){
							errors.rejectValue("sviv_tik3", "systema.tds.import.header.error.rule.item.sviv_tik.allOrNone");	
						}
					}else if(record.getSviv_tit3()!=null && !"".equals(record.getSviv_tit3())){
						if( (record.getSviv_tik3()==null || "".equals(record.getSviv_tik3()) ) || (record.getSviv_tix3()==null || "".equals(record.getSviv_tix3())) ){
							errors.rejectValue("sviv_tik3", "systema.tds.import.header.error.rule.item.sviv_tik.allOrNone");	
						}
					}else if(record.getSviv_tix3()!=null && !"".equals(record.getSviv_tix3())){
						if( (record.getSviv_tik3()==null || "".equals(record.getSviv_tik3()) ) || (record.getSviv_tit3()==null  || "".equals(record.getSviv_tit3())) ){
							errors.rejectValue("sviv_tik3", "systema.tds.import.header.error.rule.item.sviv_tik.allOrNone");	
						}
					}
					//(4)
					if(record.getSviv_tik4()!=null && !"".equals(record.getSviv_tik4())){
						if( (record.getSviv_tit4()==null || "".equals(record.getSviv_tit4()) ) || (record.getSviv_tix4()==null || "".equals(record.getSviv_tix4())) ){
							errors.rejectValue("sviv_tik4", "systema.tds.import.header.error.rule.item.sviv_tik.allOrNone");	
						}
					}else if(record.getSviv_tit4()!=null && !"".equals(record.getSviv_tit4())){
						if( (record.getSviv_tik4()==null || "".equals(record.getSviv_tik4()) ) || (record.getSviv_tix4()==null || "".equals(record.getSviv_tix4())) ){
							errors.rejectValue("sviv_tik4", "systema.tds.import.header.error.rule.item.sviv_tik.allOrNone");	
						}
					}else if(record.getSviv_tix4()!=null && !"".equals(record.getSviv_tix4())){
						if( (record.getSviv_tik4()==null || "".equals(record.getSviv_tik4()) ) || (record.getSviv_tit4()==null  || "".equals(record.getSviv_tit4())) ){
							errors.rejectValue("sviv_tik4", "systema.tds.import.header.error.rule.item.sviv_tik.allOrNone");	
						}
					}
					//(5)
					if(record.getSviv_tik5()!=null && !"".equals(record.getSviv_tik5())){
						if( (record.getSviv_tit5()==null || "".equals(record.getSviv_tit5()) ) || (record.getSviv_tix5()==null || "".equals(record.getSviv_tix5())) ){
							errors.rejectValue("sviv_tik5", "systema.tds.import.header.error.rule.item.sviv_tik.allOrNone");	
						}
					}else if(record.getSviv_tit5()!=null && !"".equals(record.getSviv_tit5())){
						if( (record.getSviv_tik5()==null || "".equals(record.getSviv_tik5()) ) || (record.getSviv_tix5()==null || "".equals(record.getSviv_tix5())) ){
							errors.rejectValue("sviv_tik5", "systema.tds.import.header.error.rule.item.sviv_tik.allOrNone");	
						}
					}else if(record.getSviv_tix5()!=null && !"".equals(record.getSviv_tix5())){
						if( (record.getSviv_tik5()==null || "".equals(record.getSviv_tik5()) ) || (record.getSviv_tit3()==null  || "".equals(record.getSviv_tit5())) ){
							errors.rejectValue("sviv_tik5", "systema.tds.import.header.error.rule.item.sviv_tik.allOrNone");	
						}
					}
					//(6)
					if(record.getSviv_tik6()!=null && !"".equals(record.getSviv_tik6())){
						if( (record.getSviv_tit6()==null || "".equals(record.getSviv_tit6()) ) || (record.getSviv_tix6()==null || "".equals(record.getSviv_tix6())) ){
							errors.rejectValue("sviv_tik6", "systema.tds.import.header.error.rule.item.sviv_tik.allOrNone");	
						}
					}else if(record.getSviv_tit6()!=null && !"".equals(record.getSviv_tit6())){
						if( (record.getSviv_tik6()==null || "".equals(record.getSviv_tik6()) ) || (record.getSviv_tix6()==null || "".equals(record.getSviv_tix6())) ){
							errors.rejectValue("sviv_tik6", "systema.tds.import.header.error.rule.item.sviv_tik.allOrNone");	
						}
					}else if(record.getSviv_tix6()!=null && !"".equals(record.getSviv_tix6())){
						if( (record.getSviv_tik6()==null || "".equals(record.getSviv_tik6()) ) || (record.getSviv_tit6()==null  || "".equals(record.getSviv_tit6())) ){
							errors.rejectValue("sviv_tik6", "systema.tds.import.header.error.rule.item.sviv_tik.allOrNone");	
						}
					}
				}else{
					errors.rejectValue("sviv_vata", "systema.tds.import.header.error.rule.item.svev_vata.invalidNumber");
				}
				
			}
		}
	}
	
	/**
	 * Nested conditions with the EU code
	 * 
	 * @param record
	 * @return
	 */
	private boolean isValidUrsprungsland_EU(JsonTdsImportSpecificTopicItemRecord record){
		boolean retval = false;
		
		String eup1 = record.getSviv_eup1();
		String fokd = record.getSviv_fokd();
		boolean eup1Condition = false;
		boolean fokdCondition = false;
		
		if( ( eup1.startsWith("6")|| (eup1.equals("4010")||eup1.equals("4071")) ) ){
			eup1Condition = true;
		}
		if( fokd.startsWith("2")|| fokd.equals("3")){
			fokdCondition = true;
		}
		//now to the final check
		if(eup1Condition && fokdCondition){
			retval = true;
		}	
		
		return retval;
	}
	
	/**
	 * Oemballerat gods 
	 * 
	 * @param value
	 * @return
	 */
	private boolean isOemballeratGods(String value){
		boolean retval = false;
		if(value!=null){
			if("NE".equals(value)){
				retval = true;
			}else if("NF".equals(value)){
				retval = true;
			}else if("NG".equals(value)){
				retval = true;
			} 
		}
		
		return retval;
	}
	
	/**
	 * Bulkvaror
	 * 
	 * @param value
	 * @return
	 */
	private boolean isBulkGods(String value){
		boolean retval = false;
		if(value!=null){
			if("VR".equals(value)){
				retval = true;
			}else if("VY".equals(value)){
				retval = true;
			}else if("VO".equals(value)){
				retval = true;
			}else if("VL".equals(value)){
				retval = true;
			}else if("VG".equals(value)){
				retval = true;
			}else if("VQ".equals(value)){
				retval = true;
			} 
		}
		
		return retval;
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean isZero(String value){
		Double db = 0.00D;
		boolean retval = true;
		try{
			db = Double.parseDouble(value);
			if(db!=0){
				retval = false;
			}
		}catch(Exception e){
			//nothing
		}
		
		return retval;
	}
	
}
