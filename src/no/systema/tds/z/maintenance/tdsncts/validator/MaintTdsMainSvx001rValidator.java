package no.systema.tds.z.maintenance.tdsncts.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tds.z.maintenance.tdsncts.model.jsonjackson.dbtable.JsonMaintSvxkodfRecord;


/**
 * 
 * @author oscardelatorre
 * @date Jun 22, 2017
 * 
 *
 */
public class MaintTdsMainSvx001rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintTdsMainSvx001rValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSvxkodfRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSvxkodfRecord record = (JsonMaintSvxkodfRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tkunik", "", "Id-Key er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tkkode", "", "Kode er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tktxtn", "", "Tekst (dansk) er obligatorisk"); 
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//N/A
			}
		}
	}
	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	
	public void validateDelete(Object obj, Errors errors) { 
		
		JsonMaintSvxkodfRecord record = (JsonMaintSvxkodfRecord)obj;
		//logger.info(record.getTariff());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tkunik", "", "Id-Key er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tkkode", "", "Kode er obligatorisk"); 

	}

}
