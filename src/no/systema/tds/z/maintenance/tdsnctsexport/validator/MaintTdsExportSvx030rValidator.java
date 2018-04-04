package no.systema.tds.z.maintenance.tdsnctsexport.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tds.z.maintenance.tdsnctsexport.model.jsonjackson.dbtable.JsonMaintSvxghRecord;

/**
 * 
 * @author oscardelatorre
 * @date Jun 23, 2017
 * 
 *
 */
public class MaintTdsExportSvx030rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintTdsExportSvx030rValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSvxghRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSvxghRecord record = (JsonMaintSvxghRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tggnr", "", "Garantinr är obligatoriskt"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tgtina", "", "Foretagsnr är obligatoriskt"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tgnaa", "", "Namn är obligatoriskt"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tgpna", "", "Postnr. är obligatoriskt"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tgpsa", "", "Postadr. är obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tglka", "", "Landkode är obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tgtsd", "", "Garantitullkont. är obligatoriskt"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tggty", "", "Garantityp är obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tgakny", "", "Ny tillg.kode är obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tgakgm", "", "Gml. tillg.kode är obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tggbl", "", "Garantibelopp är obligatoriskt"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tggvk", "", "Valuta är obligatorisk"); 
		
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//TODO
			}
		}
	}
	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	
	public void validateDelete(Object obj, Errors errors) { 
		
		JsonMaintSvxghRecord record = (JsonMaintSvxghRecord)obj;
		//logger.info(record.getTariff());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tggnr", "", "Garantinr är obligatoriskt"); 
		
	}
	
	
}
