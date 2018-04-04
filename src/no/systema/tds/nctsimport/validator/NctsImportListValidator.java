package no.systema.tds.nctsimport.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.tds.nctsimport.filter.SearchFilterNctsImportTopicList;

/**
 * 
 * @author oscardelatorre
 * @date Dec 3, 2013
 *
 */
public class NctsImportListValidator implements Validator {

	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return SearchFilterNctsImportTopicList.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		SearchFilterNctsImportTopicList record = (SearchFilterNctsImportTopicList)obj;
		
		//Check for Mandatory fields first
		if(record!=null){
			
			if( !this.valueExists(record.getAvd()) && !this.valueExists(record.getSign()) ){
				if( this.valueExists(record.getOpd()) ||  this.valueExists(record.getStatus()) || 
					this.valueExists(record.getForenklad()) ||  this.valueExists(record.getForenklad()) ||	
					this.valueExists(record.getGodsNr()) ||  this.valueExists(record.getDatumFr())	 ||
					this.valueExists(record.getMrnNr()) ||  this.valueExists(record.getDatum())	 ){
					//do nothing. Validation test passed!
				}else{
					//at least avd or sign must exist IF everything else is empty... 
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "avd", "systema.ncts.import.list.error.null.avdOrSign"); 
				}
			}
			
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
