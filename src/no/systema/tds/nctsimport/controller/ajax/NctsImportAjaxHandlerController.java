/**
 * 
 */
package no.systema.tds.nctsimport.controller.ajax;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;

import no.systema.main.service.general.CurrencyRateService;
import no.systema.tds.tdsimport.model.jsonjackson.customer.JsonTdsImportCustomerRecord;
import no.systema.tds.nctsimport.model.jsonjackson.topic.JsonNctsImportSpecificTopicContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.JsonNctsImportSpecificTopicRecord;


import no.systema.tds.nctsimport.model.jsonjackson.topic.items.JsonNctsImportSpecificTopicItemContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.items.JsonNctsImportSpecificTopicItemRecord;
import no.systema.tds.nctsimport.model.jsonjackson.topic.unloading.items.JsonNctsImportSpecificTopicUnloadingItemContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.unloading.items.JsonNctsImportSpecificTopicUnloadingItemRecord;
import no.systema.tds.url.store.TdsUrlDataStore;

import no.systema.tds.service.TdsTaricVarukodService;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTaricVarukodContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTaricVarukodRecord;
import no.systema.tds.tdsimport.service.TdsImportCustomerService;
import no.systema.tds.nctsimport.service.NctsImportSpecificTopicService;
import no.systema.tds.nctsimport.service.NctsImportSpecificTopicItemService;
import no.systema.tds.nctsimport.service.NctsImportSpecificTopicUnloadingItemService;
import no.systema.tds.nctsimport.url.store.UrlDataStore;
import no.systema.tds.util.TdsConstants;

/**
 * This Ajax handler is the class handling ajax request in NctsImport. 
 * It will usually be called from within a jQuery function or other javascript alike... 
 * 
 * @author oscardelatorre
 * @date Dec 16, 2013
 * 
 */

@Controller
public class NctsImportAjaxHandlerController {
	private static final Logger logger = Logger.getLogger(NctsImportAjaxHandlerController.class.getName());
	 
	
	
	@RequestMapping(value = "getNctsImportSpecificTopicUnloadingItemChosenFromGuiElement.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonNctsImportSpecificTopicUnloadingItemRecord> getNctsImportSpecificTopicUnloadingItemRecord
	  						(@RequestParam String applicationUser, @RequestParam String elementValue, 
	  						 @RequestParam String avd, @RequestParam String opd ) {
		 logger.info("Inside: getNctsImportSpecificTopicUnloadingItemChosenFromGuiElement()");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = UrlDataStore.NCTS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_UNLOADING_ITEM_URL;
		 String[] fields = elementValue.split("_");
		 
		 String lineCounter = null;
		 String lineNr = null;
		 if(fields!=null && fields.length==3){
			 logger.info("FIELD: " + fields[0] + " " + fields[1] + " " + fields[2]);
			 lineCounter = fields[1];
			 lineNr = fields[2];
			 
			 logger.info(applicationUser + "-" + elementValue + "-" + avd + "-" + opd + "- linenr:" + lineNr);
			 String urlRequestParamsKeys = this.getRequestUrlKeyParametersForItem(applicationUser, avd, opd, lineNr);
			 logger.info("URL: " + BASE_URL);
			 logger.info("PARAMS: " + urlRequestParamsKeys);
			 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			 
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");		
			 if(jsonPayload!=null){
				 //we must replace wrong name in order to use the same JSON item record. The RPG name "oneline" should be replaced (at the back end)
				 //in the future by orderList... We do that here and now. Same as for avd and opd (in order to comply with the model record attributes)
				 jsonPayload = jsonPayload.replaceFirst("avd", "tvavd");
				 jsonPayload = jsonPayload.replaceFirst("opd", "tvtdn");
				 jsonPayload = jsonPayload.replaceFirst("oneline", "orderList");
				 
				 logger.info(jsonPayload);
				 JsonNctsImportSpecificTopicUnloadingItemContainer container = this.nctsImportSpecificTopicUnloadingItemService.getNctsImportSpecificTopicUnloadingItemContainer(jsonPayload);
				 if(container!=null){
					 for(JsonNctsImportSpecificTopicUnloadingItemRecord  record : container.getOrderList()){
						 logger.info("Item Description: " + record.getNvvt());
						 logger.info("Item line nr: " + record.getTvli());
						 
						 //this.concatenateInformationFields(record);
						 result.add(record);
					 }
				 }
			 }
			 
		 }else{
			 logger.error("[ERROR] on fields[]...null or incorrect length???...");
		 }
		 
		 return result;
	 }	
	
	 
	 /**
	  * 
	  * @param applicationUser
	  * @param elementValue
	  * @param user
	  * @param avd
	  * @param opd
	  * @return
	  */
	 
	 @RequestMapping(value = "getNctsImportSpecificTopicItemChosenFromGuiElement.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonNctsImportSpecificTopicItemRecord> getNctsImportSpecificTopicItemChosenFromGuiElement
	  						(@RequestParam String applicationUser, @RequestParam String elementValue, 
	  						 @RequestParam String avd, @RequestParam String opd ) {
		 logger.info("Inside: getNctsImportSpecificTopicItemChosenFromGuiElement()");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = UrlDataStore.NCTS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL;
		 String[] fields = elementValue.split("_");
		 String lineNr = null;
		 String varupostNr = null;
		 if(fields!=null && fields.length==3){
			 logger.info("FIELD: " + fields[0] + " " + fields[1] + " " + fields[2]);
			 lineNr = fields[1];
			 varupostNr = fields[2];
			 logger.info(applicationUser + "-" + elementValue + "-" + avd + "-" + opd + "- linenr:" + lineNr);
			 String urlRequestParamsKeys = this.getRequestUrlKeyParametersForItem(applicationUser, avd, opd, lineNr);
			 logger.info("URL: " + BASE_URL);
			 logger.info("PARAMS: " + urlRequestParamsKeys);
			 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");		
			 if(jsonPayload!=null){
				 //we must replace wrong name in order to use the same JSON item record. The RPG name "oneline" should be replaced (at the back end)
				 //in the future by orderList... We do that here and now. Same as for avd and opd (in order to comply with the model record attributes)
				 jsonPayload = jsonPayload.replaceFirst("avd", "tvavd");
				 jsonPayload = jsonPayload.replaceFirst("opd", "tvtdn");
				 jsonPayload = jsonPayload.replaceFirst("oneline", "orderList");
				 
				 logger.info(jsonPayload);
				 JsonNctsImportSpecificTopicItemContainer container = this.nctsImportSpecificTopicItemService.getNctsImportSpecificTopicItemContainer(jsonPayload);
				 if(container!=null){
					 for(JsonNctsImportSpecificTopicItemRecord  record : container.getOrderList()){
						 logger.info("Event Description: " + record.getTvinf1());
						 logger.info("Plats: " + record.getTvst());
						 this.concatenateInformationFields(record);
						 result.add(record);
					 }
				 }
			 }
		 }else{
			 logger.error("[ERROR] on fields[]...null or incorrect length???...");
		 }
		 
		 return result;
	 }
	 
	 /**
	  * 
	  * @param record
	  */
	 private void concatenateInformationFields(JsonNctsImportSpecificTopicItemRecord record){
		 String inf = null;
		 
		 //record.setTvinf(value)
		 if(record.getTvinf1()!=null && !"".equals(record.getTvinf1())){
			 inf = record.getTvinf1();
		 }
		 if(record.getTvinf2()!=null && !"".equals(record.getTvinf2())){
			 inf += " " + record.getTvinf2();
		 }
		 if(record.getTvinf3()!=null && !"".equals(record.getTvinf3())){
			 inf += " " + record.getTvinf3();
		 }
		 if(record.getTvinf4()!=null && !"".equals(record.getTvinf4())){
			 inf += " " + record.getTvinf4();
		 }
		 
		 record.setTvinf(inf);
	 }
	 /**
	  * The method is used for fetching default values on "Create new"
	  * It is triggered when the end user chooses a department (Avdelning)
	  * 
	  * @param applicationUser
	  * @param customerName
	  * @param customerNumber
	  * @return
	  */
	 
	 @RequestMapping(value = "initCreateNewTopicNctsImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTdsImportCustomerRecord> initCreateNewTopic(@RequestParam String applicationUser, @RequestParam String avd) {
		 
		 	String method = "NctsImportAjaxHandlerController.initCreateNewTopicNctsImport()";
		 	logger.info("Inside " + method);
		 	Set result = new HashSet();
		 	
		 	logger.info("FETCH record transaction...");
			//---------------------------
			//get BASE URL = RPG-PROGRAM
			//---------------------------
			String BASE_URL = UrlDataStore.NCTS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForInitCreateNewTopic(applicationUser, avd);
			//for debug purposes in GUI
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("URL: " + BASE_URL);
			logger.info("URL PARAMS: " + urlRequestParamsKeys);
			//--------------------------------------
			//EXECUTE the FETCH (RPG program) here
			//--------------------------------------
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
			logger.info(method + " --> jsonPayload:" + jsonPayload);
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");

			if(jsonPayload!=null){
	    		JsonNctsImportSpecificTopicContainer container = this.nctsImportSpecificTopicService.getNctsImportSpecificTopicContainer(jsonPayload);
	    		if(container!=null){
	    			for(JsonNctsImportSpecificTopicRecord  record : container.getOneorder()){
	    				logger.info("Registreringsdatum: " + record.getTidt() );
	    				result.add(record);
	    			}
	    		}
	    	}
			return result;
		  
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param taricVarukod
	   * @return
	   */
	  
	  @RequestMapping(value = "searchTaricVarukodNcts_TdsNctsImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTdsTaricVarukodRecord> getTaricVarukodNcts(@RequestParam String applicationUser, @RequestParam String taricVarukod) {
		  logger.info("Inside NctsExportAjaxHandler.getTaricVarukodNcts()");
		  Set result = new HashSet();
		  String EXPORT_IE = "I";//always I since we are in IMPORT mode
		  
		  try{
			  String BASE_URL = TdsUrlDataStore.TDS_FETCH_TARIC_VARUKODER_ITEMS_URL;
			  String urlRequestParamsKeys = "user=" + applicationUser + "&ie=" + EXPORT_IE + "&kod=" + taricVarukod;
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			  JsonTdsTaricVarukodContainer container = this.tdsTaricVarukodService.getContainer(jsonPayload);
			  for(JsonTdsTaricVarukodRecord record : container.getTullTaxalist()){
				  
				  result.add(record);
			  }	
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  
		  return result;
	  }


	  
	  
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param avd
	   * @param opd
	   * @param lineNr
	   * @return
	   */
	  private String getRequestUrlKeyParametersForItem(String applicationUser, String avd, String opd,  String lineNr){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  if(avd!=null && !"".equals(avd) && opd!=null && !"".equals(opd) && lineNr!=null && !"".equals(lineNr)){
			  sb.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd );
			  sb.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd );
			  sb.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + lineNr );
		  }
		  return sb.toString();
	  }
	  /**
	   * 
	   * @param applicationUser
	   * @param avd
	   * @return
	   */
	  private String getRequestUrlKeyParametersForInitCreateNewTopic(String applicationUser, String avd){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  sb.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd );
		  return sb.toString();
	  }
	  	
	  //SERVICES
	  @Qualifier ("urlCgiProxyService")
	  private UrlCgiProxyService urlCgiProxyService;
	  @Autowired
	  @Required
	  public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	  public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }

	  
	  @Qualifier ("nctsImportSpecificTopicService")
	  private NctsImportSpecificTopicService nctsImportSpecificTopicService;
	  @Autowired
	  @Required
	  public void setNctsImportSpecificTopicService (NctsImportSpecificTopicService value){ this.nctsImportSpecificTopicService = value; }
	  public NctsImportSpecificTopicService getNctsImportSpecificTopicService(){ return this.nctsImportSpecificTopicService; }
	  
	  
	  @Qualifier ("nctsImportSpecificTopicItemService")
	  private NctsImportSpecificTopicItemService nctsImportSpecificTopicItemService;
	  @Autowired
	  @Required	
	  public void setNctsImportSpecificTopicItemService(NctsImportSpecificTopicItemService value){this.nctsImportSpecificTopicItemService = value;}
	  public NctsImportSpecificTopicItemService getNctsExportSpecificTopicItemService(){ return this.nctsImportSpecificTopicItemService; }
	  
	  
	  @Qualifier ("nctsImportSpecificTopicUnloadingItemService")
	  private NctsImportSpecificTopicUnloadingItemService nctsImportSpecificTopicUnloadingItemService;
	  @Autowired
	  @Required	
	  public void setNctsImportSpecificTopicUnloadingItemService(NctsImportSpecificTopicUnloadingItemService value){this.nctsImportSpecificTopicUnloadingItemService = value;}
	  public NctsImportSpecificTopicUnloadingItemService getNctsImportSpecificTopicUnloadingItemService(){ return this.nctsImportSpecificTopicUnloadingItemService; }
	  
	  
	  @Qualifier 
	  private TdsTaricVarukodService tdsTaricVarukodService;
	  @Autowired
	  @Required	
	  public void setTdsTaricVarukodService(TdsTaricVarukodService value){this.tdsTaricVarukodService = value;}
	  public TdsTaricVarukodService getTdsTaricVarukodService(){ return this.tdsTaricVarukodService; }
	  
	  
		
}
