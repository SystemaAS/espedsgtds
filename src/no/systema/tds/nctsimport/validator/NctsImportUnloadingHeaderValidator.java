package no.systema.tds.nctsimport.validator;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import no.systema.main.util.StringManager;

import no.systema.tds.nctsimport.model.jsonjackson.topic.unloading.JsonNctsImportSpecificTopicUnloadingRecord;


/**
 * 
 * @author oscardelatorre
 * @date Mar, 2020
 * 
 */
public class NctsImportUnloadingHeaderValidator implements Validator {
	private static final Logger logger = Logger.getLogger(NctsImportUnloadingHeaderValidator.class.getName());
	private StringManager strMgr = new StringManager();	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonNctsImportSpecificTopicUnloadingRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonNctsImportSpecificTopicUnloadingRecord record = (JsonNctsImportSpecificTopicUnloadingRecord)obj;

			if(record!=null){
				String KONFORM_JA = "1";
				if(record.getNikonf()!=null && KONFORM_JA.equals(record.getNikonf()) ){
					if(strMgr.isNull(record.getNidtl())){
						errors.rejectValue("nidtl", "systema.ncts.import.header.error.null.unloading.header.lossningsdate.nidtl");
					}
				}
			
			}
			
	}
	
	
	
}
