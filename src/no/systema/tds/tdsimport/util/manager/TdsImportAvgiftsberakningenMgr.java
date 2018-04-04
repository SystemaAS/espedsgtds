/**
 * 
 */
package no.systema.tds.tdsimport.util.manager;

import java.util.Calendar;

import no.systema.tds.tdsimport.controller.TdsImportItemsController;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemContainer;
import no.systema.tds.tdsimport.service.TdsImportSpecificTopicItemService;
import no.systema.tds.tdsimport.url.store.TdsImportUrlDataStore;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemAvgifterContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemAvgifterRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemRecord;

import no.systema.tds.util.TdsConstants;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


/**
 * This class calculates the charges (Avgifter) in a TDS item scenario.
 * 
 * @author oscardelatorre
 * @date Sep 10, 2013
 * 
 */
public class TdsImportAvgiftsberakningenMgr {
	private static final Logger logger = Logger.getLogger(TdsImportAvgiftsberakningenMgr.class.getName());
	private TdsImportSpecificTopicItemService tdsImportSpecificTopicItemService;
	private UrlCgiProxyService urlCgiProxyService;
	
	/**
	 * Constructor
	 * @param itemService
	 * @param urlProxyService
	 */
	public TdsImportAvgiftsberakningenMgr(TdsImportSpecificTopicItemService itemService, UrlCgiProxyService urlProxyService){
		this.tdsImportSpecificTopicItemService = itemService;
		this.urlCgiProxyService = urlProxyService;
		
	}
	
	
	/**
	 * 
	 * @param session
	 * @param jsonTdsImportSpecificTopicItemRecord
	 * @param appUser
	 */
	public JsonTdsImportSpecificTopicItemAvgifterRecord calculateChargesOnItem(HttpSession session, JsonTdsImportSpecificTopicItemRecord jsonTdsImportSpecificTopicItemRecord, SystemaWebUser appUser ){
		JsonTdsImportSpecificTopicItemAvgifterRecord retval = null;
		//-----------------------------------------------------------------------
        //Now calculate the charges (Avgiftsberäkningen) always with CREATE NEW
        //-----------------------------------------------------------------------
        String BASE_URL_CALCULATION_AVGIFTER = TdsImportUrlDataStore.TDS_IMPORT_BASE_AVGIFTS_CALCULATION_URL;
		logger.info("[INFO] Avgifts calculation process START");
		String urlRequestParamsKeys = this.getRequestUrlKeyParametersAvgiftsberakning(session, jsonTdsImportSpecificTopicItemRecord, appUser);
		//put the final valid param. string
		//for debug purposes in GUI
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + BASE_URL_CALCULATION_AVGIFTER);
    		logger.info("URL PARAMS: " + urlRequestParamsKeys);
    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_CALCULATION_AVGIFTER, urlRequestParamsKeys);
			
    		JsonTdsImportSpecificTopicItemAvgifterContainer container = this.tdsImportSpecificTopicItemService.getTdsImportSpecificTopicItemAvgifterContainer(jsonPayload);
    		logger.info("[INFO] Avgifts calculation process END");
		for(JsonTdsImportSpecificTopicItemAvgifterRecord record : container.getTaxcalc()){
			logger.info("[DEBUG] sviv_stva: " + record.getSviv_stva());
			logger.info("[DEBUG] sviv_abb1: " + record.getSviva_abb1());
			retval = record;
		}
		return retval;
	}
	
	/**
	 * 
	 * @param session
	 * @param jsonTdsImportSpecificTopicItemRecord
	 * @param appUser
	 * @return
	 */
	public JsonTdsImportSpecificTopicItemAvgifterRecord calculateChargesOnItem(String appUser, String urlRequestParams){
		JsonTdsImportSpecificTopicItemAvgifterRecord retval = null;
		//-----------------------------------------------------------------------
        //Now calculate the charges (Avgiftsberäkningen) always with CREATE NEW
        //-----------------------------------------------------------------------
        String BASE_URL_CALCULATION_AVGIFTER = TdsImportUrlDataStore.TDS_IMPORT_BASE_AVGIFTS_CALCULATION_URL;
		logger.info("[INFO] Avgifts calculation process START");
		String urlRequestParamsKeys = "user=" + appUser + TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + urlRequestParams;
		//put the final valid param. string
		//for debug purposes in GUI
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + BASE_URL_CALCULATION_AVGIFTER);
		logger.info("URL PARAMS: " + urlRequestParamsKeys);
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_CALCULATION_AVGIFTER, urlRequestParamsKeys);
		logger.info(jsonPayload);
		
		JsonTdsImportSpecificTopicItemAvgifterContainer container = this.tdsImportSpecificTopicItemService.getTdsImportSpecificTopicItemAvgifterContainer(jsonPayload);
		logger.info("[INFO] Avgifts calculation process END");
		for(JsonTdsImportSpecificTopicItemAvgifterRecord record : container.getTaxcalc()){
			//logger.info("[DEBUG] sviv_stva: " + record.getSviv_stva());
			//logger.info("[DEBUG] sviv_abb1: " + record.getSviva_abb1());
			//store debug std output
			record.setDebugPrintlnAjax(BASE_URL_CALCULATION_AVGIFTER + "?" + urlRequestParamsKeys + " <JSON> " + jsonPayload + "</JSON>");
			retval = record;
		}
		
		return retval;
	}
	
	/**
	 * 
	 * @param session
	 * @param itemRecord
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersAvgiftsberakning(HttpSession session, JsonTdsImportSpecificTopicItemRecord itemRecord, SystemaWebUser appUser ){
		JsonTdsImportSpecificTopicRecord topicHeaderRecord = (JsonTdsImportSpecificTopicRecord)session.getAttribute(TdsConstants.DOMAIN_RECORD_TOPIC);
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_vufr=" + topicHeaderRecord.getSvih_vufr());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_vuva=" + topicHeaderRecord.getSvih_vuva());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_vuku=" + topicHeaderRecord.getSvih_vuku());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_vufo=" + topicHeaderRecord.getSvih_vufo());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_ovko=" + topicHeaderRecord.getSvih_ovko());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_kara=" + topicHeaderRecord.getSvih_kara());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_anra=" + topicHeaderRecord.getSvih_anra());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_lekd=" + topicHeaderRecord.getSvih_lekd());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_vakd=" + topicHeaderRecord.getSvih_vakd());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_vaku=" + topicHeaderRecord.getSvih_vaku());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_fabl=" + topicHeaderRecord.getSvih_fabl());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_vata=" + itemRecord.getSviv_vata());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_ulkd=" + itemRecord.getSviv_ulkd());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_fokd=" + itemRecord.getSviv_fokd());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_eup1=" + itemRecord.getSviv_eup1());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_ankv=" + itemRecord.getSviv_ankv());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_stva=" + itemRecord.getSviv_stva());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_stva2=" + itemRecord.getSviv_stva2());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_fabl=" + itemRecord.getSviv_fabl());
		
		return urlRequestParamsKeys.toString();
	}
	/**
	 * 
	 * @param topicHeaderRecord
	 * @param itemRecord
	 * @param appUser
	 * @return
	 */
	public String getRequestUrlKeyParametersAvgiftsberakning(JsonTdsImportSpecificTopicRecord topicHeaderRecord, JsonTdsImportSpecificTopicItemRecord itemRecord, SystemaWebUser appUser ){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_vufr=" + topicHeaderRecord.getSvih_vufr());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_vuva=" + topicHeaderRecord.getSvih_vuva());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_vuku=" + topicHeaderRecord.getSvih_vuku());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_vufo=" + topicHeaderRecord.getSvih_vufo());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_ovko=" + topicHeaderRecord.getSvih_ovko());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_kara=" + topicHeaderRecord.getSvih_kara());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_anra=" + topicHeaderRecord.getSvih_anra());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_lekd=" + topicHeaderRecord.getSvih_lekd());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_vakd=" + topicHeaderRecord.getSvih_vakd());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_vaku=" + topicHeaderRecord.getSvih_vaku());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_fabl=" + topicHeaderRecord.getSvih_fabl());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_vata=" + itemRecord.getSviv_vata());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_ulkd=" + itemRecord.getSviv_ulkd());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_fokd=" + itemRecord.getSviv_fokd());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_eup1=" + itemRecord.getSviv_eup1());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_ankv=" + itemRecord.getSviv_ankv());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_stva=" + itemRecord.getSviv_stva());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_stva2=" + itemRecord.getSviv_stva2());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_fabl=" + itemRecord.getSviv_fabl());
		
		return urlRequestParamsKeys.toString();
	}
	/**
	 * 
	 * Hand over of attributes from the source record (JSON) to the target record (JSON)
	 * @param sourceRecord
	 * @param targetRecord
	 * 
	 */
	public void handOverAttributes(JsonTdsImportSpecificTopicItemAvgifterRecord sourceRecord, JsonTdsImportSpecificTopicItemRecord targetRecord){
		//Statistiskt värde
		targetRecord.setSviv_stva(sourceRecord.getSviv_stva());
		targetRecord.setSviv_stva2(sourceRecord.getSviv_stva2());
		
		//Slag (kod)
		targetRecord.setSviva_abk1(sourceRecord.getSviva_abk1());
		targetRecord.setSviva_abk2(sourceRecord.getSviva_abk2());
		targetRecord.setSviva_abk3(sourceRecord.getSviva_abk3());
		targetRecord.setSviva_abk4(sourceRecord.getSviva_abk4());
		targetRecord.setSviva_abk5(sourceRecord.getSviva_abk5());
		//Grund
		targetRecord.setSviva_abg1(sourceRecord.getSviva_abg1());
		targetRecord.setSviva_abg2(sourceRecord.getSviva_abg2());
		targetRecord.setSviva_abg3(sourceRecord.getSviva_abg3());
		targetRecord.setSviva_abg4(sourceRecord.getSviva_abg4());
		targetRecord.setSviva_abg5(sourceRecord.getSviva_abg5());
		//Sats
		targetRecord.setSviva_abs1(sourceRecord.getSviva_abs1());
		targetRecord.setSviva_abs2(sourceRecord.getSviva_abs2());
		targetRecord.setSviva_abs3(sourceRecord.getSviva_abs3());
		targetRecord.setSviva_abs4(sourceRecord.getSviva_abs4());
		targetRecord.setSviva_abs5(sourceRecord.getSviva_abs5());
		//Enhet
		targetRecord.setSviva_abx1(sourceRecord.getSviva_abx1());
		targetRecord.setSviva_abx2(sourceRecord.getSviva_abx2());
		targetRecord.setSviva_abx3(sourceRecord.getSviva_abx3());
		targetRecord.setSviva_abx4(sourceRecord.getSviva_abx4());
		targetRecord.setSviva_abx5(sourceRecord.getSviva_abx5());
		//Belopp
		targetRecord.setSviva_abb1(sourceRecord.getSviva_abb1());
		targetRecord.setSviva_abb2(sourceRecord.getSviva_abb2());
		targetRecord.setSviva_abb3(sourceRecord.getSviva_abb3());
		targetRecord.setSviva_abb4(sourceRecord.getSviva_abb4());
		targetRecord.setSviva_abb5(sourceRecord.getSviva_abb5());
		
	}
	/**
	 * 
	 * @param sourceRecord
	 * @param targetRecord
	 */
	public void handOverAttributesOnUpdate(JsonTdsImportSpecificTopicItemAvgifterRecord sourceRecord, JsonTdsImportSpecificTopicItemRecord targetRecord){
		//Statistiskt värde
		if(targetRecord.getSviv_stva().isEmpty()){ targetRecord.setSviv_stva(sourceRecord.getSviv_stva()); }
		if(targetRecord.getSviv_stva2().isEmpty()){ targetRecord.setSviv_stva2(sourceRecord.getSviv_stva2()); }
		
		//Slag (kod)
		if(this.isEmpty(targetRecord.getSviva_abk1()) ){ targetRecord.setSviva_abk1(sourceRecord.getSviva_abk1()); }
		if(this.isEmpty(targetRecord.getSviva_abk2()) ){ targetRecord.setSviva_abk2(sourceRecord.getSviva_abk2()); }
		if(this.isEmpty(targetRecord.getSviva_abk3()) ){ targetRecord.setSviva_abk3(sourceRecord.getSviva_abk3()); }
		if(this.isEmpty(targetRecord.getSviva_abk4()) ){ targetRecord.setSviva_abk4(sourceRecord.getSviva_abk4()); }
		if(this.isEmpty(targetRecord.getSviva_abk5()) ){ targetRecord.setSviva_abk5(sourceRecord.getSviva_abk5()); }
		//Grund
		if(this.isEmpty(targetRecord.getSviva_abg1()) ){ targetRecord.setSviva_abg1(sourceRecord.getSviva_abg1()); }
		if(this.isEmpty(targetRecord.getSviva_abg2()) ){ targetRecord.setSviva_abg2(sourceRecord.getSviva_abg2()); }
		if(this.isEmpty(targetRecord.getSviva_abg3()) ){ targetRecord.setSviva_abg3(sourceRecord.getSviva_abg3()); }
		if(this.isEmpty(targetRecord.getSviva_abg4()) ){ targetRecord.setSviva_abg4(sourceRecord.getSviva_abg4()); }
		if(this.isEmpty(targetRecord.getSviva_abg5()) ){ targetRecord.setSviva_abg5(sourceRecord.getSviva_abg5()); }
		//Sats
		if(this.isEmpty(targetRecord.getSviva_abs1()) ){ targetRecord.setSviva_abs1(sourceRecord.getSviva_abs1()); }
		if(this.isEmpty(targetRecord.getSviva_abs2()) ){ targetRecord.setSviva_abs2(sourceRecord.getSviva_abs2()); }
		if(this.isEmpty(targetRecord.getSviva_abs3()) ){ targetRecord.setSviva_abs3(sourceRecord.getSviva_abs3()); }
		if(this.isEmpty(targetRecord.getSviva_abs4()) ){ targetRecord.setSviva_abs4(sourceRecord.getSviva_abs4()); }
		if(this.isEmpty(targetRecord.getSviva_abs5()) ){ targetRecord.setSviva_abs5(sourceRecord.getSviva_abs5()); }
		//Enhet
		if(this.isEmpty(targetRecord.getSviva_abx1()) ){ targetRecord.setSviva_abx1(sourceRecord.getSviva_abx1()); }
		if(this.isEmpty(targetRecord.getSviva_abx2()) ){ targetRecord.setSviva_abx2(sourceRecord.getSviva_abx2()); }
		if(this.isEmpty(targetRecord.getSviva_abx3()) ){ targetRecord.setSviva_abx3(sourceRecord.getSviva_abx3()); }
		if(this.isEmpty(targetRecord.getSviva_abx4()) ){ targetRecord.setSviva_abx4(sourceRecord.getSviva_abx4()); }
		if(this.isEmpty(targetRecord.getSviva_abx5()) ){ targetRecord.setSviva_abx5(sourceRecord.getSviva_abx5()); }
		//Belopp
		if(this.isEmpty(targetRecord.getSviva_abb1()) ){ targetRecord.setSviva_abb1(sourceRecord.getSviva_abb1()); }
		if(this.isEmpty(targetRecord.getSviva_abb2()) ){ targetRecord.setSviva_abb2(sourceRecord.getSviva_abb2()); }
		if(this.isEmpty(targetRecord.getSviva_abb3()) ){ targetRecord.setSviva_abb3(sourceRecord.getSviva_abb3()); }
		if(this.isEmpty(targetRecord.getSviva_abb4()) ){ targetRecord.setSviva_abb4(sourceRecord.getSviva_abb4()); }
		if(this.isEmpty(targetRecord.getSviva_abb5()) ){ targetRecord.setSviva_abb5(sourceRecord.getSviva_abb5()); }
		
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean isEmpty(String value){
		boolean retval = true;
		if(value!=null && !"".equals(value)){
			retval = false;
		}
		
		return retval;
	}
	
}
