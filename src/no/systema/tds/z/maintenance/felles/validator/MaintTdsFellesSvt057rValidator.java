package no.systema.tds.z.maintenance.felles.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.validator.DateValidator;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtvkRecord;

/**
 * 
 * @author oscardelatorre
 * @date May 19, 2017
 * 
 *
 */
public class MaintTdsFellesSvt057rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintTdsFellesSvt057rValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSvtvkRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		
		JsonMaintSvtvkRecord record = (JsonMaintSvtvkRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svvk_kd", "", " Kod är obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svvk_krs", "", " Kurs är obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svvk_omr", "", " Faktor är obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svvk_dts", "", " Från är obligatorisk"); 
		
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//validation of parent Sign
				/*if (!record.isValidSignature()){
					errors.rejectValue("validSignature", " ", "Signatur är ogiltig"); 
				}else{
					//check for duplicate
					if (record.getDuplicateSignature()){
						errors.rejectValue("duplicateSignature", " ", "Signatur är ogiltig. Den existerar redan. ");
					}
				}*/
			}
		}
	}
	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	public void validateDelete(Object obj, Errors errors) { 
		// N/A
		
	}
}
