package no.systema.tds.tdsimport.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicRecord;
import no.systema.tds.tdsimport.util.TdsImportCalculator;

/**
 * 
 * @author oscardelatorre
 *
 */
public class TdsImportHeaderValidator implements Validator {
	private static final Logger logger = Logger.getLogger(TdsImportHeaderValidator.class.getName());
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonTdsImportSpecificTopicRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonTdsImportSpecificTopicRecord record = (JsonTdsImportSpecificTopicRecord)obj;
		//Check for Mandatory fields first
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_avna", "systema.tds.import.header.error.null.svih_avna"); 
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_aveo", "systema.tds.import.header.error.null.svih_aveo"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_ava1", "systema.tds.import.header.error.null.svih_ava1"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_avpn", "systema.tds.import.header.error.null.svih_avpn"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_avpa", "systema.tds.import.header.error.null.svih_avpa"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_avlk", "systema.tds.import.header.error.null.svih_avlk"); 
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_avha", "systema.tds.import.header.error.null.svih_avha"); 
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_avtl", "systema.tds.import.header.error.null.svih_avtl"); 
		//receiver
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_mona", "systema.tds.import.header.error.null.svih_mona"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_moeo", "systema.tds.import.header.error.null.svih_moeo"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_moa1", "systema.tds.import.header.error.null.svih_moa1"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_mopn", "systema.tds.import.header.error.null.svih_mopn"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_mopa", "systema.tds.import.header.error.null.svih_mopa"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_molk", "systema.tds.import.header.error.null.svih_molk");
		if( !record.getSvih_mtyp().startsWith("H")){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_moha", "systema.tds.import.header.error.null.svih_moha"); 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_motl", "systema.tds.import.header.error.null.svih_motl"); 
		}
		//deklarant
		/* if null then we copy from RECEIVER
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_dkna", "systema.tds.import.header.error.null.svih_dkna"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_dkeo", "systema.tds.import.header.error.null.svih_dkeo"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_dka1", "systema.tds.import.header.error.null.svih_dka1"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_dkpn", "systema.tds.import.header.error.null.svih_dkpn"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_dkpa", "systema.tds.import.header.error.null.svih_dkpa"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_dklk", "systema.tds.import.header.error.null.svih_dklk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_dkha", "systema.tds.import.header.error.null.svih_dkha"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_dktl", "systema.tds.import.header.error.null.svih_dktl"); 
		*/
		//ombud
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_omeo", "systema.tds.import.header.error.null.svih_omeo"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_omty", "systema.tds.import.header.error.null.svih_omty"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_omha", "systema.tds.import.header.error.null.svih_omha"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_omtl", "systema.tds.import.header.error.null.svih_omtl"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_upps", "systema.tds.import.header.error.null.svih_upps");
		//header
		//all messages like HNU, HNK, etc (with "H") have optional fields (ref. to the import matrix spec . CB.)
		if( !record.getSvih_mtyp().startsWith("H")){
			//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_upps", "systema.tds.import.header.error.null.svih_upps");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_tart", "systema.tds.import.header.error.null.svih_tart"); 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_fabl", "systema.tds.import.header.error.null.svih_fabl"); 
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_syav", "systema.tds.import.header.error.null.svih_syav"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_sysg", "systema.tds.import.header.error.null.svih_sysg"); 
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_dek1", "systema.tds.import.header.error.null.svih_dek1"); 
		if( !record.getSvih_mtyp().startsWith("OMP")){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_dek2", "systema.tds.import.header.error.null.svih_dek2"); 
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_mtyp", "systema.tds.import.header.error.null.svih_mtyp");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_fatx", "systema.tds.import.header.error.null.svih_fatx");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_faty", "systema.tds.import.header.error.null.svih_faty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_avut", "systema.tds.import.header.error.null.svih_avut");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_aube", "systema.tds.import.header.error.null.svih_aube");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_trin", "systema.tds.import.header.error.null.svih_trin");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_trgr", "systema.tds.import.header.error.null.svih_trgr");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_utfa", "systema.tds.import.header.error.null.svih_utfa");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_kota", "systema.tds.import.header.error.null.svih_kota");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svih_brut", "systema.tds.import.header.error.null.svih_brut");
		
		
		
		//Logical controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			
			if(record!=null){
				//EORI nissarna
				if( record.getSvih_aveo()!=null && !"".equals(record.getSvih_aveo()) ){
					if(record.getSvih_aveo().length() < 3){
						errors.rejectValue("svih_aveo", "systema.tds.import.header.error.rule.svih_aveo.eoriInvalid");
					}
				}
				if( record.getSvih_moeo()!=null && !"".equals(record.getSvih_moeo()) ){
					if(record.getSvih_moeo().length() < 3){
						errors.rejectValue("svih_moeo", "systema.tds.import.header.error.rule.svih_moeo.eoriInvalid");
					}
				}
				if( record.getSvih_dkeo()!=null && !"".equals(record.getSvih_dkeo()) ){
					if(record.getSvih_dkeo().length() < 3){
						errors.rejectValue("svih_dkeo", "systema.tds.import.header.error.rule.svih_dkeo.eoriInvalid");
					}
				}
				if( record.getSvih_omeo()!=null && !"".equals(record.getSvih_omeo()) ){
					if(record.getSvih_omeo().length() < 3){
						errors.rejectValue("svih_omeo", "systema.tds.import.header.error.rule.svih_omeo.eoriInvalid");
					}
				}
				
				//Meddelandytyp = svih_mtyp
				if(record.getSvih_mtyp().equalsIgnoreCase("HNU") || record.getSvih_mtyp().equalsIgnoreCase("HRT") ||
						 record.getSvih_mtyp().equalsIgnoreCase("HNK")){
					if(!record.getSvih_dek2().equalsIgnoreCase("C")){
						errors.rejectValue("svih_mtyp", "systema.tds.import.header.error.rule.svih_mtyp.deklaration.C");
					}
				}else if(record.getSvih_mtyp().equalsIgnoreCase("DNU") || record.getSvih_mtyp().equalsIgnoreCase("DNK")){
					if(record.getSvih_dek2().equalsIgnoreCase("A") || record.getSvih_dek2().equalsIgnoreCase("B")){
						//correct
					}else{
						errors.rejectValue("svih_mtyp", "systema.tds.import.header.error.rule.svih_mtyp.deklaration.combination");
					}
				}else if(record.getSvih_mtyp().equalsIgnoreCase("DRT")){
					if(record.getSvih_dek2().equalsIgnoreCase("A") || record.getSvih_dek2().equalsIgnoreCase("X")){
						//correct
					}else{
						errors.rejectValue("svih_mtyp", "systema.tds.import.header.error.rule.svih_mtyp.deklaration.combination");
					}
				}else if(record.getSvih_mtyp().equalsIgnoreCase("UNU") || record.getSvih_mtyp().equalsIgnoreCase("URT")){
					if(!record.getSvih_dek2().equalsIgnoreCase("A")){
						errors.rejectValue("svih_mtyp", "systema.tds.import.header.error.rule.svih_mtyp.deklaration.A");
					}
				}else if(record.getSvih_mtyp().equalsIgnoreCase("TNU") || record.getSvih_mtyp().equalsIgnoreCase("TRT")){
					if(!record.getSvih_dek2().equalsIgnoreCase("Y")){
						errors.rejectValue("svih_mtyp", "systema.tds.import.header.error.rule.svih_mtyp.deklaration.Y");
					}
				}else if(record.getSvih_mtyp().equalsIgnoreCase("UFF")){
					if(record.getSvih_dek2().equalsIgnoreCase("Y") || record.getSvih_dek2().equalsIgnoreCase("Z")){
						//valid
					}else{
						errors.rejectValue("svih_mtyp", "systema.tds.import.header.error.rule.svih_mtyp.deklaration.combination");
					}
				}else if(record.getSvih_mtyp().equalsIgnoreCase("TQN") || record.getSvih_mtyp().equalsIgnoreCase("TQR") ||
						record.getSvih_mtyp().equalsIgnoreCase("UGE") ){
					if(!record.getSvih_dek2().equalsIgnoreCase("Z")){
						errors.rejectValue("svih_mtyp", "systema.tds.import.header.error.rule.svih_mtyp.deklaration.Z");
					}
				}else if(record.getSvih_mtyp().equalsIgnoreCase("OMP")){
					if(record.getSvih_dek2()==null || "".equalsIgnoreCase(record.getSvih_dek2())){
						//valid
					}else{
						errors.rejectValue("svih_mtyp", "systema.tds.import.header.error.rule.svih_mtyp.omp.noDeklaration");
					}
				}
				
				//Fakt.belopp, kurs och valuta = svih_vakd - svih_fabl - svih_vaku
				if(record.getSvih_fabl()!=null && !record.getSvih_fabl().equalsIgnoreCase("") || 
				   record.getSvih_vakd()!=null && !record.getSvih_vakd().equalsIgnoreCase("") ||
				   record.getSvih_vaku()!=null && !record.getSvih_vaku().equalsIgnoreCase("")){
						
					if(record.getSvih_vakd()==null || record.getSvih_vakd().equalsIgnoreCase("")){
						errors.rejectValue("svih_fabl", "systema.tds.import.header.error.rule.svih_fabl.totalValidity");
					}else if (record.getSvih_vaku()==null || record.getSvih_vaku().equalsIgnoreCase("")){
						errors.rejectValue("svih_fabl", "systema.tds.import.header.error.rule.svih_fabl.totalValidity");
					}else if (record.getSvih_fabl()==null || record.getSvih_fabl().equalsIgnoreCase("")){
						errors.rejectValue("svih_fabl", "systema.tds.import.header.error.rule.svih_fabl.totalValidity");
					}
				}
				
				//Transportmedlets nationalitet (gränsspassagen)
				if(record.getSvih_tral()!=null && !record.getSvih_tral().equals("")){
					//Ok
				}else{
					if("1".equalsIgnoreCase(record.getSvih_trgr()) || "3".equalsIgnoreCase(record.getSvih_trgr()) ||
					   "4".equalsIgnoreCase(record.getSvih_trgr()) || "8".equalsIgnoreCase(record.getSvih_trgr()) ||	
					   "9".equalsIgnoreCase(record.getSvih_trgr()) ){
						errors.rejectValue("svih_tral", "systema.tds.import.header.error.rule.svih_tral.mandatoryWith1_3_4_8_9");
					}
				}
				
				//Vardeuppgifter
				TdsImportCalculator calculator = new TdsImportCalculator();
				boolean vardeUppgifterIsValid = calculator.isValidVardeUppgifter(record.getSvih_vufr(), record.getSvih_vufo(), record.getSvih_ovko(), record.getSvih_kara(), record.getSvih_anra());
				if(!vardeUppgifterIsValid){
					errors.rejectValue("svih_vufr", "systema.tds.import.header.error.rule.vardeuppgifter.isInvalid");
				}
				
				//Exportland invalid country codes
				if("EU".equals(record.getSvih_avut()) || "SE".equals(record.getSvih_avut()) ){
					errors.rejectValue("svih_avut", "systema.tds.import.header.error.rule.svih_avut.invalid.countryCodes");
				}
				
				//Lev.villkor - ort
				if(!"".equals(record.getSvih_lekd())){
					if(!"".equals(record.getSvih_leor())){
						//valid
					}else{
						errors.rejectValue("svih_leor", "systema.tds.import.header.error.rule.svih_leor.leveransOrt");
					}
				}
				//-------------
				//KOLLI ANTAL
				//-------------
				if("0".equals(record.getSvih_kota())){
					errors.rejectValue("svih_kota", "systema.tds.import.header.error.rule.svih_kota.invalid.mustBeBiggerThanZero");
				}else{
					//Kolli antal in header vs kolli antal on item lines (sum)
					int antalKolliInHeader = Integer.parseInt(record.getSvih_kota());
					//logger.info("ANTAL KOLLI on HEADER:" + antalKolliInHeader);
					//logger.info("ANTAL KOLLI on ITEMS:" + record.getSumOfAntalKolliInItemLines());
					//The validation should be triggered ONLY when there are one-or-more item lines .
					
					/*OBSOLETE...At the moment we are deactivating this validation and instead leave the responsibility to the end user.
					  This in order to avoid lock-edits in case of value=-1 when copying...
					if(record.getSumOfAntalKolliInItemLines()>0){
						if(antalKolliInHeader != record.getSumOfAntalKolliInItemLines()){
							errors.rejectValue("svih_kota", "systema.tds.import.header.error.rule.svih_kota.invalid.itemLinesSum");
						}
					}else{
						if (record.getSumOfAntalKolliInItemLines()==-1){
							errors.rejectValue("svih_kota", "systema.tds.import.header.error.rule.svih_kota.invalid.itemLinesSum.discrepant");
						}
					}*/
				}
				
				//Klareringsbehörig EORI
				//Only valid input with mtyp = DNU
				/*
				if(record.getSvih_kleo()!=null && !"".equals(record.getSvih_kleo().trim()) ){
					if(!"DNU".equals(record.getSvih_mtyp()) ){
						errors.rejectValue("svih_kleo", "systema.tds.import.header.error.rule.item.svih_kleo.onlyDNU");
					}
				}
				*/
				
			}
			
		}
			
		

	}
	
}
