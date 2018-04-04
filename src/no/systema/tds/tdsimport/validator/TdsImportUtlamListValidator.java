package no.systema.tds.tdsimport.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.tds.tdsimport.filter.SearchFilterTdsImportTopicUtlamList;

/**
 * 
 * @author oscardelatorre
 *
 */
public class TdsImportUtlamListValidator implements Validator {

	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return SearchFilterTdsImportTopicUtlamList.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		SearchFilterTdsImportTopicUtlamList record = (SearchFilterTdsImportTopicUtlamList)obj;
		
		//Check for Mandatory fields first
		if(record!=null){
			/* TODO ?
			if( !this.valueExists(record.getAvd()) && !this.valueExists(record.getSign()) ){
				if( this.valueExists(record.getOpd()) || this.valueExists(record.getTullId()) || 
					this.valueExists(record.getDatum()) ||  this.valueExists(record.getAvsNavn()) ||	
					this.valueExists(record.getMotNavn()) ||  this.valueExists(record.getStatus()) ){
					//do nothing. Validation test passed!
				}else{
					//at least avd or sign must exist IF everything else is empty... 
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "avd", "systema.tds.import.list.error.null.avdOrSign"); 
				}
			}
			*/
		}
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean valueExists(String value){
		boolean retval = false;
		if(value!=null){
			if(!"".equals(value)){
				retval = true;
			}
		}
		
		return retval;
	}
}
