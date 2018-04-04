package no.systema.tds.nctsimport.validator;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;

import no.systema.tds.nctsimport.model.jsonjackson.topic.unloading.items.JsonNctsImportSpecificTopicUnloadingItemRecord;

import no.systema.tds.nctsimport.service.NctsImportSpecificTopicItemService;
import no.systema.tds.nctsimport.service.NctsImportSpecificTopicItemServiceImpl;
import no.systema.tds.nctsimport.service.NctsImportSpecificTopicUnloadingItemService;
import no.systema.tds.nctsimport.service.NctsImportSpecificTopicUnloadingItemServiceImpl;

import no.systema.tds.nctsimport.url.store.UrlDataStore;
/**
 * 
 * @author oscardelatorre
 * @date Jan 17, 2014
 * 
 */
public class NctsImportUnloadingItemsValidator implements Validator {
	private static final Logger logger = Logger.getLogger(NctsImportUnloadingItemsValidator.class.getName());
	//Intantiate services here since we are not capable to configure injection with Autowired. Check that further...
	private UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
	private NctsImportSpecificTopicUnloadingItemService nctsImportSpecificTopicUnloadingItemServiceImpl = new NctsImportSpecificTopicUnloadingItemServiceImpl();
		
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonNctsImportSpecificTopicUnloadingItemRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonNctsImportSpecificTopicUnloadingItemRecord record = (JsonNctsImportSpecificTopicUnloadingItemRecord)obj;

		//Check for Mandatory fields first
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nvvt", "systema.ncts.import.header.error.null.unloading.item.description.nvvt"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nvmn", "systema.ncts.import.header.error.null.unloading.item.godsmarkning.nvmn");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nveh", "systema.ncts.import.header.error.null.unloading.item.kollislag.nveh");
		
		//Logical controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				
				//------------------------------------------------------------
				//Känsliga varor must always check for mandatory input or none
				//-------------------------------------------------------------
				/*if(record.getTvvnt()!=null && !"".equals(record.getTvvnt()) ){
					if(this.isVarukodWithSensitiveGoodsFlag(record)){
						if(record.getTvfvnt()!=null && !"".equals(record.getTvfvnt())){
							//nothing since it has been filled in
						}else{
							errors.rejectValue("tvfvnt", "systema.ncts.export.header.error.rule.item.tvfvnt.mustExist");
						}
					}else{
						//back to default values
						record.setTvfv(null);
						record.setTvfvnt(null);
					}
				}*/
				
			}
			
		}
			
	}
	
	
	
}
