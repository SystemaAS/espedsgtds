	/**
 * 
 */
package no.systema.tds.nctsexport.util.manager;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesContainer;
import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesRecord;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.util.JsonDebugger;

import no.systema.tds.url.store.TdsUrlDataStore;
import no.systema.tds.util.TdsConstants;
import no.systema.tds.service.TdsBilagdaHandlingarYKoderService;
import no.systema.tds.service.TdsTillaggskoderService;
import no.systema.tds.model.jsonjackson.codes.JsonTdsBilagdaHandlingarYKoderContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsBilagdaHandlingarYKoderRecord;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTillaggskodContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTillaggskodRecord;
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportSpecificTopicContainer;
import no.systema.tds.nctsexport.service.NctsExportSpecificTopicService;
import no.systema.tds.nctsexport.url.store.UrlDataStore;
import no.systema.tds.tdsexport.service.TdsExportSpecificTopicItemService;
import no.systema.tds.tdsexport.url.store.TdsExportUrlDataStore;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemStatisticalValueContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemStatisticalValueRecord;
import no.systema.tds.tdsexport.url.store.TdsExportUrlDataStore;



/**
 * TDS export
 *
 * This Manager is not instantiated by the Spring Container at start up. 
 * Instead, it is instantiated by a caller when needed.
 * 
 * It aims to provide a single-point-of-entry for common methods found from within an MVC-Controller or an Ajax-Controller
 *  
 * 
 * @author oscardelatorre
 * @date Oct 2020
 * 
 * 
 */
@Service
public class NctsExportControllerAjaxCommonFunctionsMgr {
	private static final Logger logger = Logger.getLogger(NctsExportControllerAjaxCommonFunctionsMgr.class.getName());
	private UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
	  
	/**
	 * 
	 * @param service
	 * @param applicationUser
	 * @param countryCode
	 * @param itemCode
	 * @return
	 */
	public JsonNctsExportSpecificTopicContainer calculateGuaranteeAmount(String applicationUser, String avd, String opd){
		JsonNctsExportSpecificTopicContainer result = null;
		
		logger.info("FETCH calculation...");
		//---------------------------
		//get BASE URL = RPG-PROGRAM
		//---------------------------
		String BASE_URL = UrlDataStore.NCTS_EXPORT_BASE_CALCULATE_SPECIFIC_TOPIC_GUARRANTEE_URL;
		//url params
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + applicationUser + "&avd=" + avd + "&opd=" + opd);
		//for debug purposes in GUI
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.warn("URL: " + BASE_URL);
		logger.warn("URL PARAMS: " + urlRequestParamsKeys.toString());
		//--------------------------------------
		//EXECUTE the FETCH (RPG program) here
		//--------------------------------------
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
		//Debug --> 
		logger.info(" --> jsonPayload:" + jsonPayload);
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");

		if(jsonPayload!=null){
    		JsonNctsExportSpecificTopicContainer container = this.nctsExportSpecificTopicService.getNctsExportSpecificTopicContainer(jsonPayload);
    		if(container!=null){
    			logger.warn("Guarantee amount via AJAX: " + container.getAmount() );
    			result = container;
    		}
    	}
		return result;
		
	}
	
	@Autowired
	NctsExportSpecificTopicService nctsExportSpecificTopicService;
	
}
