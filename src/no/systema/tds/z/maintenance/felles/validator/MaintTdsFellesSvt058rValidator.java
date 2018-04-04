package no.systema.tds.z.maintenance.felles.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.validator.DateValidator;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtlvRecord;

/**
 * 
 * @author oscardelatorre
 * @date Jun 03, 2017
 * 
 *
 */
public class MaintTdsFellesSvt058rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintTdsFellesSvt058rValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSvtlvRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		
		JsonMaintSvtlvRecord record = (JsonMaintSvtlvRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svlv_kd", "", " Kod är obligatorisk"); 
		
		
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
