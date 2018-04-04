/**
 * 
 */
package no.systema.tds.tdsimport.controller.ajax;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.main.model.jsonjackson.general.JsonCurrencyRateContainer;
import no.systema.main.model.jsonjackson.general.JsonCurrencyRateRecord;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.service.general.CurrencyRateService;
import no.systema.tds.tdsimport.util.manager.TdsImportControllerAjaxCommonFunctionsMgr;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicInvoiceExternalForUpdateContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicInvoiceExternalContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicInvoiceExternalRecord;
import no.systema.tds.tdsimport.url.store.TdsImportUrlDataStore;
import no.systema.tds.tdsimport.model.jsonjackson.customer.JsonTdsImportCustomerContainer;
import no.systema.tds.tdsimport.model.jsonjackson.customer.JsonTdsImportCustomerRecord;
import no.systema.tds.tdsimport.model.jsonjackson.tullkontor.JsonTdsImportTullkontorContainer;
import no.systema.tds.tdsimport.model.jsonjackson.tullkontor.JsonTdsImportTullkontorRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemAvgifterRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemBilagdaHandlingarContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemBilagdaHandlingarRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemFormanskoderContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemFormanskoderRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicOmbudContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicOmbudRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicInvoiceContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicInvoiceRecord;

import no.systema.tds.model.jsonjackson.JsonTdsGodsnrContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTillaggskodContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsBilagdaHandlingarYKoderContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsBilagdaHandlingarYKoderRecord;


import no.systema.tds.model.jsonjackson.codes.JsonTdsTaricVarukodContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTaricVarukodRecord;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTillaggskodRecord;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureNameContainer;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureNameRecord;

import no.systema.tds.service.TdsGodsnrService;
import no.systema.tds.service.TdsTillaggskoderService;
import no.systema.tds.service.TdsBilagdaHandlingarYKoderService;

import no.systema.tds.service.TdsTaricVarukodService;
import no.systema.tds.tdsimport.service.TdsImportCustomerService;
import no.systema.tds.tdsimport.service.TdsImportSpecificTopicService;
import no.systema.tds.tdsimport.service.TdsImportTullkontorService;
import no.systema.tds.tdsimport.service.TdsImportSpecificTopicItemService;
import no.systema.tds.service.TdsSignatureNameService;

import no.systema.tds.tdsimport.url.store.TdsImportUrlDataStore;
import no.systema.tds.tdsimport.util.manager.TdsImportAvgiftsberakningenMgr;
import no.systema.tds.url.store.TdsUrlDataStore;

import no.systema.tds.util.TdsConstants;

/**
 * This Ajax handler is the class handling ajax request in TdsImport. 
 * It will usually be called from within a jQuery function or other javascript alike... 
 * 
 * @author oscardelatorre
 * @date Sep 2, 2013
 * 
 */

@Controller

public class TdsImportAjaxHandlerController {
	private static final Logger logger = Logger.getLogger(TdsImportAjaxHandlerController.class.getName());
	 
	/**
	 * Gets the signature name (person)
	 * 
	 * @param applicationUser
	 * @param avd
	 * @param sign
	 * @return
	 */
	@RequestMapping(value = "getSignatureName_TdsImport.do", method = RequestMethod.GET)
     public @ResponseBody Set<JsonTdsSignatureNameRecord> getSignatureName
	  						(@RequestParam String applicationUser, @RequestParam String avd, 
	  						 @RequestParam String sign) {
		 logger.info("Inside: getSignatureName_TdsImport.do");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = TdsUrlDataStore.TDS_FETCH_SIGNATURE_NAME_URL;
		 String urlRequestParamsKeys = "user=" + applicationUser + "&avd=" + avd + "&sign=" + sign;
		 logger.info("URL: " + BASE_URL);
		 logger.info("PARAMS: " + urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 logger.info(jsonPayload);
		 if(jsonPayload!=null){
			 	try{
			 		JsonTdsSignatureNameContainer container = this.tdsSignatureNameService.getContainer(jsonPayload);
					if(container!=null){
						 for(JsonTdsSignatureNameRecord  record : container.getGetsignname()){
							 logger.info("#### SVTH_NAMN:" + record.getSvth_namn());
							 logger.info("#### SVTH_TLF:" + record.getSvth_tlf());
							 result.add(record);
						 }
					}
			 	}catch(Exception e){
			 		e.printStackTrace();
			 	}
			 }
		 
		 return result;
	}
	  
	/**
	 * Fetches the dep. information (Ombud)
	 * 
	 * @param applicationUser
	 * @param avd
	 * @return
	 */
	 @RequestMapping(value = "getSpecificTopicOmbud_TdsImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTdsImportSpecificTopicOmbudRecord> getSpecificTopicOmbud
	  						(@RequestParam String applicationUser, @RequestParam String avd) {
		 logger.info("Inside: getSpecificTopicOmbud_TdsImport.do");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_FETCH_OMBUD_DEFAULT_DATA_URL;
		 String urlRequestParamsKeys = "user=" + applicationUser + "&avd=" + avd;
		 logger.info("URL: " + BASE_URL);
		 logger.info("PARAMS: " + urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 logger.info(jsonPayload);
		 if(jsonPayload!=null){
				 JsonTdsImportSpecificTopicOmbudContainer container = this.tdsImportSpecificTopicService.getTdsImportSpecificTopicOmbudContainer(jsonPayload);
				 if(container!=null){
					 for(JsonTdsImportSpecificTopicOmbudRecord  record : container.getGetdepinf()){
						 logger.info("#### SVIA_OMHA:" + record.getSvia_omha());
						 result.add(record);
					 }
				 }
			 }
		 
		 return result;
	 }
	  
	
	/**
	 * 
	 * @param applicationUser
	 * @param elementValue
	 * @param avd
	 * @param opd
	 * @return
	 */
	@RequestMapping(value = "getSpecificTopicItemChosenFromGuiElement_TdsImport.do", method = RequestMethod.GET)
	public @ResponseBody Set<JsonTdsImportSpecificTopicItemRecord> getSpecificTopicItemChosenFromHtmlList
	  						(@RequestParam String applicationUser, @RequestParam String elementValue, 
	  						 @RequestParam String avd, @RequestParam String opd ) {
		 logger.info("Inside: getSpecificTopicItemChosenFromGuiElement_TdsImport()");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL;
		 String[] fields = elementValue.split("_");
		 String lineNr = null;
		 String lineId = null;
		 if(fields!=null && fields.length==3){
			 logger.info("FIELD: " + fields[0] + " " + fields[1] + " " + fields[2]);
			 lineId = fields[1];
			 lineNr = fields[2];
			 //When lineId != lineNr use lineId (line-counter)
			 if(!lineId.equals(lineNr)){lineNr = lineId;}
			 logger.info(applicationUser + "-" + elementValue + "-" + avd + "-" + opd + "- lineNr:" + lineNr);
			 
			 String urlRequestParamsKeys = this.getRequestUrlKeyParametersForItem(applicationUser, avd, opd, lineNr);
			 logger.info("URL: " + BASE_URL);
			 logger.info("PARAMS: " + urlRequestParamsKeys);
			 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");		
			 if(jsonPayload!=null){
				 //we must replace wrong name in order to use the same JSON item record. The RPG name "oneline" should be replaced (at the back end)
				 //in the future by orderList... We do that here and now
				 jsonPayload = jsonPayload.replaceFirst("oneline", "orderList");
				 logger.info(jsonPayload);
				 JsonTdsImportSpecificTopicItemContainer container = tdsImportSpecificTopicItemService.getTdsImportSpecificTopicItemContainer(jsonPayload);
				 if(container!=null){
					 for(JsonTdsImportSpecificTopicItemRecord  record : container.getOrderList()){
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
	   * Populates the GUI element with a list of customers fulfilling the search condition
	   * @param customerName
	   * @param customerNumber
	   * @return
	   */
	  @RequestMapping(value = "searchCustomer_TdsImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTdsImportCustomerRecord> searchCustomer(@RequestParam String applicationUser, @RequestParam String customerName, @RequestParam String customerNumber) {
		  logger.info("Inside TdsImportAjaxHandlerController.searchCustomer()");
		  Set result = new HashSet();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = TdsUrlDataStore.TDS_FETCH_CUSTOMER_URL;
		  String urlRequestParamsKeys = this.getRequestUrlKeyParametersForSearchCustomer(applicationUser, customerName, customerNumber);
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  //Should be removed as soon as RPG return the correct container name = customerlist (not capitalized in the first letter)
		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		jsonPayload = jsonPayload.replaceFirst("Customerlist", "customerlist");
	  		  	JsonTdsImportCustomerContainer container = this.tdsImportCustomerService.getTdsImportCustomerContainer(jsonPayload);
	    		if(container!=null){
	    			for(JsonTdsImportCustomerRecord  record : container.getCustomerlist()){
	    				logger.info("CUSTOMER via AJAX: " + record.getKnavn() + " NUMBER:" + record.getKundnr());
	    				logger.info("KPERS: " + record.getKpers() + " TLF:" + record.getTlf());
	    				result.add(record);
	    			}
	    		}
	    	}
		  return result;
		  
	  }
	  /**
	   * Populates the GUI element with a list of tullkontor-offices fulfilling the search condition
	   * 
	   * @param applicationUser
	   * @param soName
	   * @param code
	   * @return
	   */
	  
	  @RequestMapping(value = "searchUtfartsTullkontor_TdsImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTdsImportTullkontorRecord> searchUtfartsTullkontor(@RequestParam String applicationUser, @RequestParam String tullkontorName, @RequestParam String tullkontorCode) {
		  logger.info("Inside searchUtfartsTullkontor()");
		  Set result = new HashSet();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = TdsUrlDataStore.TDS_FETCH_UTFARTS_TULLKONTOR_URL;
		  String urlRequestParamsKeys = this.getRequestUrlKeyParametersForSearchUtfartsTullkontor(applicationUser, tullkontorName, tullkontorCode);
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		  if(jsonPayload!=null){
			  JsonTdsImportTullkontorContainer container = this.tdsImportTullkontorService.getTdsImportTullkontorContainer(jsonPayload);
			  if(container!=null){
				  for(JsonTdsImportTullkontorRecord  record : container.getCustomslist()){
					  //logger.info("Kontorsnamn via AJAX: " + record.getTktxtn() + " Code:" + record.getTkkode());
					  result.add(record);
				  }
			  }
		  }
		  return result;
		  
	  }
	  
	  
	  @RequestMapping(value = "getCurrencyRate_TdsImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTdsImportTullkontorRecord> getCurrencyRate(@RequestParam String applicationUser, @RequestParam String currencyCode) {
		  logger.info("Inside getCurrencyRate()");
		  Set result = new HashSet();
		  
		  String BASE_URL_CURRENCY_RATE = TdsUrlDataStore.TDS_FETCH_CURRENCY_RATE_URL;
		  StringBuffer urlRequestParamsCurrencyRate = new StringBuffer();
		  urlRequestParamsCurrencyRate.append("user=" + applicationUser);
		  urlRequestParamsCurrencyRate.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kod=" + currencyCode);
		  String jsonPayloadCurrencyRate = this.urlCgiProxyService.getJsonContent(BASE_URL_CURRENCY_RATE, urlRequestParamsCurrencyRate.toString());
		  logger.info("CURRENCY URL:" + BASE_URL_CURRENCY_RATE);
		  logger.info("CURRENCY PARAMS:" + urlRequestParamsCurrencyRate.toString());
		  logger.info(jsonPayloadCurrencyRate);
		  //now map and process json
		  if(jsonPayloadCurrencyRate!=null){
			  JsonCurrencyRateContainer jsonCurrencyRateContainer = this.currencyRateService.getCurrencyRateContainer(jsonPayloadCurrencyRate);
			  for(JsonCurrencyRateRecord record : jsonCurrencyRateContainer.getValutakurs()){
				  //Debug
				  logger.info("Currency RATE: " + record.getSvvk_krs());
				  logger.info("Currency FACTOR: " + record.getSvvs_omr());
				  result.add(record);
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
	  @RequestMapping(value = "searchTaricVarukod_TdsImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTdsTaricVarukodRecord> getTaricVarukod(@RequestParam String applicationUser, @RequestParam String taricVarukod) {
		  logger.info("Inside getTaricVarukod()");
		  Set result = new HashSet();
		  String TDS_IMPORT_IE = "I";//always E 
		  
		  String lastValueDEBUG = null;
		  try{
			  String BASE_URL = TdsUrlDataStore.TDS_FETCH_TARIC_VARUKODER_ITEMS_URL;
			  String urlRequestParamsKeys = "user=" + applicationUser + "&ie=" + TDS_IMPORT_IE + "&kod=" + taricVarukod;
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			  logger.info(jsonPayload);
			  JsonTdsTaricVarukodContainer container = this.tdsTaricVarukodService.getContainer(jsonPayload);
			  for(JsonTdsTaricVarukodRecord record : container.getTullTaxalist()){
				  lastValueDEBUG = record.getSvvs_vatak() + " " + record.getSvvs_vata() + " " + record.getSvvs_txtk();
				  result.add(record);
			  }	
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  //DEBUG
		  if(result!=null && result.size()>0){ logger.info("Last record value for this service:" + lastValueDEBUG); }
		  return result;
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param svihStr
	   * @param svivStr
	   * @return
	   */
	  @RequestMapping(value = "calculateAvgifter_TdsImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTdsImportSpecificTopicItemRecord> calculateAvgifter(@RequestParam String applicationUser, @RequestParam String svihStr, @RequestParam String svivStr) {
		  logger.info("Inside: calculateAvgifter()");
		  Set result = new HashSet();
		  try{
			  TdsImportAvgiftsberakningenMgr avgiftsMgr = new TdsImportAvgiftsberakningenMgr(this.tdsImportSpecificTopicItemService, this.urlCgiProxyService);
			  String urlRequestParams = svihStr + svivStr;
			  JsonTdsImportSpecificTopicItemAvgifterRecord itemAvgiftsRecord = avgiftsMgr.calculateChargesOnItem(applicationUser, urlRequestParams);
			  if(itemAvgiftsRecord!=null){
				  result.add(itemAvgiftsRecord);
			  }else{
				  logger.info("[ERROR] NULL object on return from avgiftsMgr... ? " );
				  
			  }
		  }catch(Exception e){
			  e.printStackTrace();
			  
		  }
		  
		  return result;
	  }

	  /**
	   * Item lines level. Get Bilagda handlingar ...
	   * 
	   * @param applicationUser
	   * @param avd
	   * @param opd
	   * @return
	   */
	  @RequestMapping(value = "getBilagdaHandlingar_TdsImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTdsImportSpecificTopicItemBilagdaHandlingarRecord> getBilagdaHandlingar(@RequestParam String applicationUser, @RequestParam String avd, @RequestParam String opd) {
		  logger.info("Inside: getBilagdaHandlingar()");
		  Set result = new HashSet();
		  try{
			  
			  String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_GET_BILAGDA_HANDLIGAR_URL;
			  String urlRequestParams = "user=" + applicationUser + "&avd=" + avd + "&opd=" + opd;
			  logger.info(BASE_URL);
			  logger.info(urlRequestParams);
			  
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			  JsonTdsImportSpecificTopicItemBilagdaHandlingarContainer container = this.tdsImportSpecificTopicItemService.getTdsBilagdaHandlingarContainer(jsonPayload);
			  
			  for(JsonTdsImportSpecificTopicItemBilagdaHandlingarRecord record : container.getBilhand()){
				  logger.info(record.getBit1() + "##" + record.getBii1()) ;
				  logger.info(record.getBit2() + "##" + record.getBii2()) ;
				  result.add(record);
			  }	
			  
		  }catch(Exception e){
			  e.printStackTrace();
			  
		  }
		  
		  return result;
	  }
	  
	  /**
	   * Förmånskoder
	   * @param applicationUser
	   * @param countryCode
	   * @param itemCode
	   * @return
	   */
	  @RequestMapping(value = "getFormanskod_TdsImport.do", method = RequestMethod.GET)
	  public @ResponseBody List<JsonTdsImportSpecificTopicItemFormanskoderRecord> getFormanskod(@RequestParam String applicationUser, 
			  														@RequestParam String countryCode, @RequestParam String itemCode) {
		  logger.info("Inside: getFormanskod()");
		  TdsImportControllerAjaxCommonFunctionsMgr ajaxMgr = new TdsImportControllerAjaxCommonFunctionsMgr();
		  return ajaxMgr.fetchFormanskod(this.tdsImportSpecificTopicItemService, applicationUser, countryCode, itemCode);
		  
	  }
	  
	  /**
	   * Tilläggskoder
	   * @param applicationUser
	   * @param countryCode
	   * @param itemCode
	   * @return
	   */
	  @RequestMapping(value = "getTillagskoder_TdsImport.do", method = RequestMethod.GET)
	  public @ResponseBody List<JsonTdsTillaggskodRecord> getTillagskoder(@RequestParam String applicationUser, 
			  														@RequestParam String countryCode, @RequestParam String itemCode) {
		  logger.info("Inside: getTillagskoder()");
		  TdsImportControllerAjaxCommonFunctionsMgr ajaxMgr = new TdsImportControllerAjaxCommonFunctionsMgr();
		  return ajaxMgr.fetchTillagskoder(this.tdsTillaggskoderService, applicationUser, countryCode, itemCode);
		  
	  }
	  /**
	   * 
	   * @param applicationUser
	   * @param countryCode
	   * @param itemCode
	   * @param formansCode
	   * @return
	   */
	  @RequestMapping(value = "getBilagdaHandlingarYkoder_TdsImport.do", method = RequestMethod.GET)
	  public @ResponseBody List<JsonTdsBilagdaHandlingarYKoderRecord> getBilagdaHandlingarYkoder(@RequestParam String applicationUser, 
			  					@RequestParam String countryCode, @RequestParam String itemCode, @RequestParam String formansCode) {
		  
		  logger.info("Inside: getBilagdaHandlingarYkoder()");
		  TdsImportControllerAjaxCommonFunctionsMgr ajaxMgr = new TdsImportControllerAjaxCommonFunctionsMgr();
		  return ajaxMgr.fetchBilagdaHandlingar(this.tdsBilagdaHandlingarYKoderService, applicationUser, countryCode, itemCode, formansCode);
		  
	  }
	  
	  /**
	   * Get invoice line
	   * 
	   * @param applicationUser
	   * @param invoiceNr
	   * @param avd
	   * @param opd
	   * @return
	   */
	  @RequestMapping(value = "getInvoiceLine_TdsImport.do", method = RequestMethod.GET)
	  public @ResponseBody List<JsonTdsImportTopicInvoiceRecord> getInvoiceLine(@RequestParam String applicationUser, 
								@RequestParam String avd,	@RequestParam String opd, @RequestParam String invoiceNr) {
		  logger.info("Inside: getInvoiceLine()");
		  logger.info("InvoiceNr:" + invoiceNr);
		  List<JsonTdsImportTopicInvoiceRecord> list = new ArrayList<JsonTdsImportTopicInvoiceRecord>();
		  try{
			  String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_INVOICE_URL;
			  String urlRequestParams = "user=" + applicationUser + "&avd=" + avd +   "&opd=" + opd + "&fak=" + invoiceNr;
			  
			  logger.info(BASE_URL);
			  logger.info(urlRequestParams);
			  
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			  JsonTdsImportTopicInvoiceContainer container = null;
			  
			  try{
				  if(jsonPayload!=null){
					container = this.tdsImportSpecificTopicService.getTdsImportTopicInvoiceContainerOneInvoice(jsonPayload);
					if(container!=null){
						//logger.info("LIST oneInvoice:" + container.getOneInvoice());
						for(JsonTdsImportTopicInvoiceRecord  record : container.getOneInvoice()){
							//logger.info(record.getSvif_fatx());
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
	   * @param applicationUser
	   * @param requestParams
	   * @return
	   */
	  @RequestMapping(value = "updateGodsnrOnItemLinesImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTdsGodsnrContainer> updateGodsnrOnItemLinesImport(@RequestParam String applicationUser, @RequestParam String requestParams) {
		  logger.info("Inside updateGodsnrOnItemLinesImport()");
		  Set result = new HashSet();
		  try{
			  String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_UPDATE_GODSNR_ON_ALL_ITEMS_URL;
			  
			  String urlRequestParamsKeys = "user=" + applicationUser + requestParams;
			  logger.info("URL:" + BASE_URL);
			  logger.info("PARAMS:" + urlRequestParamsKeys);
			  
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			  
			  JsonTdsGodsnrContainer container = this.tdsGodsnrService.getContainer(jsonPayload);
			  if(container!=null && ( container.getErrMsg()!=null && !"".equals(container.getErrMsg())) ){
				  logger.info("[ERROR] " + container.getErrMsg());
			  }
			  result.add(container);
			  	
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  
		  return result;
	  }
	  /**
	   * 
	   * @param applicationUser
	   * @param requestParams
	   * @return
	   */
	  @RequestMapping(value = "updateExternalInvoiceLine_TdsImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTdsImportTopicInvoiceExternalForUpdateContainer> updateExternalInvoiceLineExport(@RequestParam String applicationUser, @RequestParam String requestParams) {
		  logger.info("Inside updateExternalInvoiceLineExport");
		  
		  Set<JsonTdsImportTopicInvoiceExternalForUpdateContainer> result = new HashSet<JsonTdsImportTopicInvoiceExternalForUpdateContainer>();
		  
		  try{
			  String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_EXTERNAL_URL;
			  String urlRequestParamsKeys = "user=" + applicationUser + requestParams;
			  logger.info("URL:" + BASE_URL);
			  logger.info("PARAMS:" + urlRequestParamsKeys);
			  
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			  
			  JsonTdsImportTopicInvoiceExternalForUpdateContainer container = this.tdsImportSpecificTopicService.getTdsImportTopicInvoiceContainerOneInvoiceExternalForUpdate(jsonPayload);
			  if(container!=null && ( container.getErrmsg()!=null && !"".equals(container.getErrmsg())) ){
				  logger.info("[ERROR] " + container.getErrmsg());
			  }else{
				  logger.info("[INFO]" + " Update successfully done!");
				  result.add(container);
				  
			  }
			  	
		  }catch(Exception e){
			  //e.printStackTrace();
		  }
		  
		  return result;
	  }
	  
	  /**
	   * Forms the correct parameter list according to a correct html POST
	   * @param applicationUser
	   * @param customerName
	   * @param customerNumber
	   * @return
	   */
	  private String getRequestUrlKeyParametersForSearchCustomer(String applicationUser, String customerName, String customerNumber){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  if(customerName!=null && !"".equals(customerName) && customerNumber!=null && !"".equals(customerNumber)){
			  sb.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + customerName );
			  sb.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "knr=" + customerNumber );
		  }else if (customerName!=null && !"".equals(customerName)){
			  sb.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + customerName );
		  }else if (customerNumber!=null && !"".equals(customerNumber)){
			  sb.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "knr=" + customerNumber );
		  }
		  
		  return sb.toString();
	  }
	  /**
	   * 
	   * @param applicationUser
	   * @param soName
	   * @param code
	   * @return
	   */
	  private String getRequestUrlKeyParametersForSearchUtfartsTullkontor(String applicationUser, String soName, String code){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  if(soName!=null && !"".equals(soName) && code!=null && !"".equals(code)){
			  sb.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + soName );
			  sb.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kod=" + code );
		  }else if (soName!=null && !"".equals(soName)){
			  sb.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + soName );
		  }else if (code!=null && !"".equals(code)){
			  sb.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kod=" + code );
		  }
		  
		  return sb.toString();
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
	  	
	  //SERVICES
	  @Qualifier ("urlCgiProxyService")
	  private UrlCgiProxyService urlCgiProxyService;
	  @Autowired
	  @Required
	  public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	  public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }

	  
	  @Qualifier 
	  private TdsImportCustomerService tdsImportCustomerService;
	  @Autowired
	  @Required	
	  public void setTdsImportCustomerService(TdsImportCustomerService value){this.tdsImportCustomerService = value;}
	  public TdsImportCustomerService getTdsImportCustomerService(){ return this.tdsImportCustomerService; }
	  
	  
	  @Qualifier 
	  private TdsImportTullkontorService tdsImportTullkontorService;
	  @Autowired
	  @Required	
	  public void setTdsImportTullkontorService(TdsImportTullkontorService value){this.tdsImportTullkontorService = value;}
	  public TdsImportTullkontorService getTdsImportTullkontorService(){ return this.tdsImportTullkontorService; }
	  
	  @Qualifier ("tdsImportSpecificTopicService")
	  private TdsImportSpecificTopicService tdsImportSpecificTopicService;
	  @Autowired
	  @Required
	  public void setTdsImportSpecificTopicService (TdsImportSpecificTopicService value){ this.tdsImportSpecificTopicService = value; }
	  public TdsImportSpecificTopicService getTdsImportSpecificTopicService(){ return this.tdsImportSpecificTopicService; }
	  
	  @Qualifier 
	  private TdsImportSpecificTopicItemService tdsImportSpecificTopicItemService;
	  @Autowired
	  @Required	
	  public void setTdsImportSpecificTopicItemService(TdsImportSpecificTopicItemService value){this.tdsImportSpecificTopicItemService = value;}
	  public TdsImportSpecificTopicItemService getTdsImportSpecificTopicItemService(){ return this.tdsImportSpecificTopicItemService; }
	  
	  @Qualifier ("currencyRateService")
	  private CurrencyRateService currencyRateService;
	  @Autowired
	  public void setCurrencyRateService (CurrencyRateService value){ this.currencyRateService=value; }
	  public CurrencyRateService getCurrencyRateService(){return this.currencyRateService;}
	  
	  @Qualifier 
	  private TdsTaricVarukodService tdsTaricVarukodService;
	  @Autowired
	  @Required	
	  public void setTdsTaricVarukodService(TdsTaricVarukodService value){this.tdsTaricVarukodService = value;}
	  public TdsTaricVarukodService getTdsTaricVarukodService(){ return this.tdsTaricVarukodService; }
	  
	  @Qualifier 
	  private TdsSignatureNameService tdsSignatureNameService;
	  @Autowired
	  @Required	
	  public void setTdsSignatureNameService(TdsSignatureNameService value){this.tdsSignatureNameService = value;}
	  public TdsSignatureNameService getTdsSignatureNameService(){ return this.tdsSignatureNameService; }
	  
	  @Qualifier 
	  private TdsGodsnrService tdsGodsnrService;
	  @Autowired
	  @Required	
	  public void setTdsGodsnrService(TdsGodsnrService value){this.tdsGodsnrService = value;}
	  public TdsGodsnrService getTdsGodsnrService(){ return this.tdsGodsnrService; }
	
	  
	  @Qualifier 
	  private TdsTillaggskoderService tdsTillaggskoderService;
	  @Autowired
	  @Required	
	  public void setTdsTillaggskoderService(TdsTillaggskoderService value){this.tdsTillaggskoderService = value;}
	  public TdsTillaggskoderService getTdsTillaggskoderService(){ return this.tdsTillaggskoderService; }
	
	  
	  @Qualifier 
	  private TdsBilagdaHandlingarYKoderService tdsBilagdaHandlingarYKoderService;
	  @Autowired
	  @Required	
	  public void setTdsBilagdaHandlingarYKoderService(TdsBilagdaHandlingarYKoderService value){this.tdsBilagdaHandlingarYKoderService = value;}
	  public TdsBilagdaHandlingarYKoderService getTdsBilagdaHandlingarYKoderService(){ return this.tdsBilagdaHandlingarYKoderService; }
	
	  
	  
		
}
