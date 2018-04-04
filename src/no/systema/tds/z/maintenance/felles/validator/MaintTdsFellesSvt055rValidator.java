package no.systema.tds.z.maintenance.felles.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.validator.DateValidator;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtfiRecord;

/**
 * 
 * @author oscardelatorre
 * @date May 03, 2017
 * 
 *
 */
public class MaintTdsFellesSvt055rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintTdsFellesSvt055rValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSvtfiRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSvtfiRecord record = (JsonMaintSvtfiRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svtf_0004", "", " UNB - Avsändarid  är obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svtf_0010", "", " UNB - Mottag.id  är obligatorisk"); 
		//
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svtf_0022", "", " Lösenord är obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svtf_pref", "", " Tullid prefix är obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svtf_numb", "", " Sist använda nr. är obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svtf_usri", "", " SMS-sender Userid är obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svtf_usra", "", " SMS-sender Adress är obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svtf_sec1", "", " Security party id - Egen är obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svtf_sec2", "", " Security party id - Tullverket är obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svtf_cer1", "", " Certifikatserienr. - Egen är obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svtf_cer2", "", " Certifikatserienr. - Tullverket är obligatorisk"); 
		
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//UNB validity
				if (!record.isValidSenderId()){
					errors.rejectValue("validSenderId", " ", " UNB - Avsändarid är ogiltigt"); 
				}
				if (!record.isValidReceiverId()){
					errors.rejectValue("validReceiverId", " ", " UNB - Mottag.id är ogiltigt"); 
				}
				if (!record.isValidSmsUserId()){
					errors.rejectValue("validSmsUserId", " ", " SMS-sender Userid/Adress är ogiltigt"); 
				}
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
