package no.systema.tds.admin.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.tds.admin.filter.SearchFilterTdsAdminTransportList;

/**
 * 
 * @author oscardelatorre
 * @date Jan 21, 2014
 * 
 */
public class TdsAdminTransportListValidator implements Validator {

	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return SearchFilterTdsAdminTransportList.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		SearchFilterTdsAdminTransportList record = (SearchFilterTdsAdminTransportList)obj;
		
		//Check for Mandatory fields first
		if(record!=null){
			
			if( !this.valueExists(record.getAvd()) && !this.valueExists(record.getSign()) ){
				if( this.valueExists(record.getOpd()) ||  
					this.valueExists(record.getDatum()) ||  this.valueExists(record.getAvsNavn()) ||	
					this.valueExists(record.getMotNavn()) ||  this.valueExists(record.getStatus()) ){
					//do nothing. Validation test passed!
				}else{
					//at least avd or sign must exist IF everything else is empty... 
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "avd", "systema.tds.admin.transport.list.error.null.avdOrSign"); 
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
