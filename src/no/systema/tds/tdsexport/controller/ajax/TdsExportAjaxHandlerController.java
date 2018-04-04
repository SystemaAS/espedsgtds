/**
 * 
 */
package no.systema.tds.tdsexport.controller.ajax;

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
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemRecord;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemStatisticalValueRecord;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureNameContainer;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureNameRecord;
import no.systema.tds.model.jsonjackson.codes.JsonTdsBilagdaHandlingarYKoderContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsBilagdaHandlingarYKoderRecord;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTaricVarukodContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTaricVarukodRecord;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTillaggskodContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTillaggskodRecord;
import no.systema.tds.model.jsonjackson.JsonTdsGodsnrContainer;


import no.systema.tds.service.TdsBilagdaHandlingarYKoderService;
import no.systema.tds.service.TdsSignatureNameService;
import no.systema.tds.service.TdsTaricVarukodService;
import no.systema.tds.service.TdsGodsnrService;
import no.systema.tds.service.TdsTillaggskoderService;

import no.systema.tds.tdsexport.service.TdsExportCustomerService;
import no.systema.tds.tdsexport.service.TdsExportSpecificTopicService;
import no.systema.tds.tdsexport.service.TdsExportTullkontorService;
import no.systema.tds.tdsexport.service.TdsExportSpecificTopicItemService;
import no.systema.tds.tdsexport.url.store.TdsExportUrlDataStore;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicOmbudContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicOmbudRecord;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicInvoiceContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicInvoiceExternalForUpdateContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicInvoiceRecord;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicInvoiceExternalContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicInvoiceExternalRecord;
import no.systema.tds.url.store.TdsUrlDataStore;

import no.systema.tds.util.TdsConstants;
import no.systema.tds.tdsexport.util.manager.TdsExportControllerAjaxCommonFunctionsMgr;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemAvgifterRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemRecord;
import no.systema.tds.tdsimport.util.manager.TdsImportAvgiftsberakningenMgr;


/**
 * This Ajax handler is the class handling ajax request in TdsExport. 
 * It will usually be called from within a jQuery function or other javascript alike... 
 * 
 * @author oscardelatorre
 * @date Jan 28, 2013
 * 
 */

@Controller
public class TdsExportAjaxHandlerController {
	private static final Logger logger = Logger.getLogger(TdsExportAjaxHandlerController.class.getName());
	
	
	@RequestMapping(value = "getSignatureName_TdsExport.do", method = RequestMethod.GET)
    public @ResponseBody Set<JsonTdsSignatureNameRecord> getSignatureName
	  						(@RequestParam String applicationUser, @RequestParam String avd, 
	  						 @RequestParam String sign) {
		 logger.info("Inside: getSignatureName_TdsExport.do");
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
	 @RequestMapping(value = "getSpecificTopicOmbud_TdsExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTdsExportSpecificTopicOmbudRecord> getSpecificTopicOmbud
	  						(@RequestParam String applicationUser, @RequestParam String avd) {
		 logger.info("Inside: getSpecificTopicOmbud_TdsExport.do");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_FETCH_OMBUD_DEFAULT_DATA_URL;
		 String urlRequestParamsKeys = "user=" + applicationUser + "&avd=" + avd;
		 logger.info("URL: " + BASE_URL);
		 logger.info("PARAMS: " + urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 logger.info(jsonPayload);
		 if(jsonPayload!=null){
				 JsonTdsExportSpecificTopicOmbudContainer container = this.tdsExportSpecificTopicService.getTdsExportSpecificTopicOmbudContainer(jsonPayload);
				 if(container!=null){
					 for(JsonTdsExportSpecificTopicOmbudRecord  record : container.getGetdepinf()){
						 logger.info("#### SVEA_OMHA:" + record.getSvea_omha());
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
	  * @param user
	  * @param avd
	  * @param opd
	  * @return
	  */
	 @RequestMapping(value = "getSpecificTopicItemChosenFromGuiElement.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTdsExportSpecificTopicItemRecord> getSpecificTopicItemChosenFromHtmlList
	  						(@RequestParam String applicationUser, @RequestParam String elementValue, 
	  						 @RequestParam String avd, @RequestParam String opd ) {
		 logger.info("Inside: getSpecificTopicItemChosenFromGuiElement()");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL;
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
				 JsonTdsExportSpecificTopicItemContainer container = tdsExportSpecificTopicItemService.getTdsExportSpecificTopicItemContainer(jsonPayload);
				 if(container!=null){
					 for(JsonTdsExportSpecificTopicItemRecord  record : container.getOrderList()){
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
	   * @param taricVarukod
	   * @return
	   */
	  @RequestMapping(value = "searchTaricVarukod.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTdsTaricVarukodRecord> getTaricVarukod(@RequestParam String applicationUser, @RequestParam String taricVarukod) {
		  logger.info("Inside TdsExportAjaxHandler.getTaricVarukod()");
		  Set result = new HashSet();
		  String TDS_EXPORT_IE = "E";//always E since we are in TDS-EXPORT
		  
		  try{
			  String BASE_URL = TdsUrlDataStore.TDS_FETCH_TARIC_VARUKODER_ITEMS_URL;
			  String urlRequestParamsKeys = "user=" + applicationUser + "&ie=" + TDS_EXPORT_IE + "&kod=" + taricVarukod;
			  logger.info("URL:" + BASE_URL);
			  logger.info("PARAMS:" + urlRequestParamsKeys);
			  
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			  
			  JsonTdsTaricVarukodContainer container = this.tdsTaricVarukodService.getContainer(jsonPayload);
			  for(JsonTdsTaricVarukodRecord record : container.getTullTaxalist()){
				  //System.out.print(record.getSvvs_vatak() + " " + record.getSvvs_vata() + " ");
				  //logger.info(record.getSvvs_txtk());
				  result.add(record);
			  }	
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  
		  return result;
	  }
	  
	  /**
	   * Tilläggskoder
	   * @param applicationUser
	   * @param countryCode
	   * @param itemCode
	   * @return
	   */
	  @RequestMapping(value = "getTillagskoder_TdsExport.do", method = RequestMethod.GET)
	  public @ResponseBody List<JsonTdsTillaggskodRecord> getTillagskoder(@RequestParam String applicationUser, 
			  														@RequestParam String countryCode, @RequestParam String itemCode) {
		  logger.info("Inside: getTillagskoder()");
		  TdsExportControllerAjaxCommonFunctionsMgr ajaxMgr = new TdsExportControllerAjaxCommonFunctionsMgr();
		  return ajaxMgr.fetchTillagskoder(this.tdsTillaggskoderService, applicationUser, countryCode, itemCode);
		  
	  }
	  /**
	   * Bilagda handlingar
	   * @param applicationUser
	   * @param countryCode
	   * @param itemCode
	   * @param formansCode
	   * @return
	   */
	  @RequestMapping(value = "getBilagdaHandlingarYkoder_TdsExport.do", method = RequestMethod.GET)
	  public @ResponseBody List<JsonTdsBilagdaHandlingarYKoderRecord> getBilagdaHandlingarYkoder(@RequestParam String applicationUser, 
			  					@RequestParam String countryCode, @RequestParam String itemCode) {
		  logger.info("Inside: getBilagdaHandlingarYkoder()");
		  TdsExportControllerAjaxCommonFunctionsMgr ajaxMgr = new TdsExportControllerAjaxCommonFunctionsMgr();
		  return ajaxMgr.fetchBilagdaHandlingar(this.tdsBilagdaHandlingarYKoderService, applicationUser, countryCode, itemCode);
		  
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param requestParams
	   * @return
	   */
	  @RequestMapping(value = "updateGodsnrOnItemLinesExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTdsGodsnrContainer> updateGodsnrOnItemLinesExport(@RequestParam String applicationUser, @RequestParam String requestParams) {
		  logger.info("Inside updateGodsnrOnItemLinesExport()");
		  Set result = new HashSet();
		  try{
			  String BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_UPDATE_GODSNR_ON_ALL_ITEMS_URL;
			  
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
	   * Get invoice line
	   * 
	   * @param applicationUser
	   * @param invoiceNr
	   * @param avd
	   * @param opd
	   * @return
	   */
	  @RequestMapping(value = "getInvoiceLine_TdsExport.do", method = RequestMethod.GET)
	  public @ResponseBody List<JsonTdsExportTopicInvoiceRecord> getInvoiceLine(@RequestParam String applicationUser, 
								@RequestParam String avd,	@RequestParam String opd, @RequestParam String invoiceNr) {
		  logger.info("Inside: getInvoiceLine()");
		  logger.info("InvoiceNr:" + invoiceNr);
		  List<JsonTdsExportTopicInvoiceRecord> list = new ArrayList<JsonTdsExportTopicInvoiceRecord>();
		  try{
			  String BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_INVOICE_URL;
			  String urlRequestParams = "user=" + applicationUser + "&avd=" + avd +   "&opd=" + opd + "&fak=" + invoiceNr;
			  
			  logger.info(BASE_URL);
			  logger.info(urlRequestParams);
			  
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			  JsonTdsExportTopicInvoiceContainer container = null;
			  
			  try{
				  if(jsonPayload!=null){
					container = this.tdsExportSpecificTopicService.getTdsExportTopicInvoiceContainerOneInvoice(jsonPayload);
					if(container!=null){
						for(JsonTdsExportTopicInvoiceRecord  record : container.getOneInvoice()){
							//logger.info(record.getSvef_fatx());
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
	   * Moves the specific invoice to the internal list of invoices (imported invoices)
	   * 
	   * @param applicationUser
	   * @param requestParams
	   * @return
	   */
	  @RequestMapping(value = "updateExternalInvoiceLine_TdsExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTdsExportTopicInvoiceExternalForUpdateContainer> updateExternalInvoiceLineExport(@RequestParam String applicationUser, @RequestParam String requestParams) {
		  logger.info("Inside updateExternalInvoiceLineExport");
		  
		  Set<JsonTdsExportTopicInvoiceExternalForUpdateContainer> result = new HashSet<JsonTdsExportTopicInvoiceExternalForUpdateContainer>();
		  
		  try{
			  String BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_EXTERNAL_URL;
			  String urlRequestParamsKeys = "user=" + applicationUser + requestParams;
			  logger.info("URL:" + BASE_URL);
			  logger.info("PARAMS:" + urlRequestParamsKeys);
			  
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			  
			  JsonTdsExportTopicInvoiceExternalForUpdateContainer container = this.tdsExportSpecificTopicService.getTdsExportTopicInvoiceContainerOneInvoiceExternalForUpdate(jsonPayload);
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
	   * Calculates Statistical values
	   * 
	   * @param applicationUser
	   * @param svihStr
	   * @param svivStr
	   * @return
	   */
	  @RequestMapping(value = "calculateStatisticalValues_TdsExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTdsExportSpecificTopicItemStatisticalValueRecord> calculateStatisticalValues(@RequestParam String applicationUser, @RequestParam String svehStr, @RequestParam String svevStr) {
		  logger.info("Inside: calculateStatisticalValues()");
		  Set result = new HashSet();
		  try{
			  TdsExportControllerAjaxCommonFunctionsMgr commonMgr = new TdsExportControllerAjaxCommonFunctionsMgr();
			  String urlRequestParams = svehStr + svevStr;
			  JsonTdsExportSpecificTopicItemStatisticalValueRecord statRecord = commonMgr.calculateStatisticalValuesOnItem(this.tdsExportSpecificTopicItemService, applicationUser, urlRequestParams);
			  if(statRecord!=null){
				  result.add(statRecord);
			  }else{
				  logger.info("[ERROR] NULL object on return from avgiftsMgr... ? " );
			  }
		  }catch(Exception e){
			  e.printStackTrace();
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
	  private TdsExportCustomerService tdsExportCustomerService;
	  @Autowired
	  @Required	
	  public void setTdsExportCustomerService(TdsExportCustomerService value){this.tdsExportCustomerService = value;}
	  public TdsExportCustomerService getTdsExportCustomerService(){ return this.tdsExportCustomerService; }
	  
	  
	  @Qualifier 
	  private TdsExportTullkontorService tdsExportTullkontorService;
	  @Autowired
	  @Required	
	  public void setTdsExportTullkontorService(TdsExportTullkontorService value){this.tdsExportTullkontorService = value;}
	  public TdsExportTullkontorService getTdsExportTullkontorService(){ return this.tdsExportTullkontorService; }
	  
	  @Qualifier ("tdsExportSpecificTopicService")
	  private TdsExportSpecificTopicService tdsExportSpecificTopicService;
	  @Autowired
	  @Required
	  public void setTdsExportSpecificTopicService (TdsExportSpecificTopicService value){ this.tdsExportSpecificTopicService = value; }
	  public TdsExportSpecificTopicService getTdsExportSpecificTopicService(){ return this.tdsExportSpecificTopicService; }
	  
	  @Qualifier 
	  private TdsExportSpecificTopicItemService tdsExportSpecificTopicItemService;
	  @Autowired
	  @Required	
	  public void setTdsExportSpecificTopicItemService(TdsExportSpecificTopicItemService value){this.tdsExportSpecificTopicItemService = value;}
	  public TdsExportSpecificTopicItemService getTdsExportSpecificTopicItemService(){ return this.tdsExportSpecificTopicItemService; }
	  
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
