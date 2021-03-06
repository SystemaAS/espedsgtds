	/**
 * 
 */
package no.systema.tds.tdsexport.util.manager;

import java.util.*;

import org.apache.log4j.Logger;

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
 * @date Oct 27, 2015
 * 
 * 
 */
public class TdsExportControllerAjaxCommonFunctionsMgr {
	private static final Logger logger = Logger.getLogger(TdsExportControllerAjaxCommonFunctionsMgr.class.getName());
	private UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
	  
	/**
	 * 
	 * @param service
	 * @param applicationUser
	 * @param countryCode
	 * @param itemCode
	 * @return
	 */
	public List<JsonTdsTillaggskodRecord> fetchTillagskoder(TdsTillaggskoderService service, String applicationUser, String countryCode, String itemCode){
		//===========
		//FETCH LIST
		//===========
		logger.info("Inside: fetchTillagskoder()");
		List<JsonTdsTillaggskodRecord> list = new ArrayList<JsonTdsTillaggskodRecord>();
		  
		  try{
			  String IE_MODE = "E";
			  String BASE_URL = TdsUrlDataStore.TDS_FETCH_TILLAGSKODER_URL;
			  String urlRequestParams = "user=" + applicationUser + "&ie=" + IE_MODE +  "&lk=" + countryCode + "&vata=" + itemCode;
			  logger.info(BASE_URL);
			  logger.info(urlRequestParams);
				
			  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			  JsonTdsTillaggskodContainer container = null;
			  try{
					if(jsonPayload!=null){
						container = service.getContainer(jsonPayload);
						if(container!=null){
							//logger.info("AAAAAAAA");
							for(JsonTdsTillaggskodRecord  record : container.getR33tillkoder()){
								//logger.info("BBBBBBBB" + record.getKod());
								list.add(record);
			    			}
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			  
		  }catch(Exception e){
			  e.printStackTrace();
			  
		  }
		  
		  return list;
		
	}
	/**
	 * 
	 * @param tdsBilagdaHandlingarYKoderService
	 * @param applicationUser
	 * @param countryCode
	 * @param itemCode
	 * @return
	 */
	public List<JsonTdsBilagdaHandlingarYKoderRecord> fetchBilagdaHandlingar(TdsBilagdaHandlingarYKoderService tdsBilagdaHandlingarYKoderService, String applicationUser, String countryCode, String itemCode){
		//===========
		//FETCH LIST
		//===========
		logger.info("Inside: fetchBilagdaHandlingar()");
		List<JsonTdsBilagdaHandlingarYKoderRecord> list = new ArrayList<JsonTdsBilagdaHandlingarYKoderRecord>();
		try{
		  String IE_MODE = "E";
		  String BASE_URL = TdsUrlDataStore.TDS_FETCH_BILAGDA_HANDLIGAR_YKODER_URL;
		  String urlRequestParams = "user=" + applicationUser + "&ie=" + IE_MODE +   "&lk=" + countryCode + "&vata=" + itemCode;
		  logger.info(BASE_URL);
		  logger.info(urlRequestParams);
		  
		  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		  JsonTdsBilagdaHandlingarYKoderContainer container = null;
		  logger.info(jsonPayload);
		  try{
			  if(jsonPayload!=null){
				container = tdsBilagdaHandlingarYKoderService.getContainer(jsonPayload);
				if(container!=null){
					for(JsonTdsBilagdaHandlingarYKoderRecord  record : container.getBilhandykoder()){
						list.add(record);
	    			}
				}
			  }
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		}catch(Exception e){
		  e.printStackTrace();
		  
		}
		return list;
	}
	/**
	 * Gets the statistical values (stat. value and tillvärde)
	 * @param tdsExportSpecificTopicItemService
	 * @param appUser
	 * @param urlRequestParams
	 * @return
	 */
	public JsonTdsExportSpecificTopicItemStatisticalValueRecord calculateStatisticalValuesOnItem(TdsExportSpecificTopicItemService tdsExportSpecificTopicItemService, String appUser, String urlRequestParams){
		JsonTdsExportSpecificTopicItemStatisticalValueRecord retval = null;
		//-----------------------------------------------
        //Now calculate the charges (Statistical values) 
        //-----------------------------------------------
        String BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_STATISTICAL_VALUES_CALCULATION_URL;
		logger.info("[INFO] Statistical values calculation process START");
		String urlRequestParamsKeys = "user=" + appUser + TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + urlRequestParams;
		//put the final valid param. string
		//for debug purposes in GUI
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + BASE_URL);
		logger.info("URL PARAMS: " + urlRequestParamsKeys);
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		logger.info(jsonPayload);
		
		JsonTdsExportSpecificTopicItemStatisticalValueContainer container = tdsExportSpecificTopicItemService.getTdsExportSpecificTopicItemStatisticalValueContainer(jsonPayload);
		logger.info("[INFO] Statistical values calculation process END");
		for(JsonTdsExportSpecificTopicItemStatisticalValueRecord record : container.getTaxcalc()){
			//logger.info("[DEBUG] svev_stva: " + record.getSvev_stva());
			//store debug std output
			record.setDebugPrintlnAjax(BASE_URL + "?" + urlRequestParamsKeys + " <JSON> " + jsonPayload + "</JSON>");
			retval = record;
		}
		
		return retval;
	}
	
	
	
}
