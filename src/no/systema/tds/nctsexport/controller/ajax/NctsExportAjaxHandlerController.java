/**
 * 
 */
package no.systema.tds.nctsexport.controller.ajax;


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

import no.systema.main.model.jsonjackson.general.JsonCurrencyRateContainer;
import no.systema.main.model.jsonjackson.general.JsonCurrencyRateRecord;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;

import no.systema.main.service.general.CurrencyRateService;
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tds.nctsexport.url.store.UrlDataStore;
import no.systema.tds.nctsexport.util.manager.NctsExportControllerAjaxCommonFunctionsMgr;
import no.systema.tds.tdsexport.model.jsonjackson.customer.JsonTdsExportCustomerRecord;
import no.systema.tds.nctsexport.model.jsonjackson.topic.items.JsonNctsExportSpecificTopicItemContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.items.JsonNctsExportSpecificTopicItemRecord;
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportSpecificTopicContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportSpecificTopicRecord;
import no.systema.tds.nctsexport.model.jsonjackson.topic.validation.JsonNctsExportSpecificTopicGuaranteeValidatorContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTaricVarukodContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTaricVarukodRecord;
import no.systema.tds.url.store.TdsUrlDataStore;



import no.systema.tds.service.TdsTaricVarukodService;
import no.systema.tds.tdsexport.service.TdsExportCustomerService;
import no.systema.tds.tdsexport.service.TdsExportTullkontorService;
import no.systema.tds.nctsexport.service.NctsExportSpecificTopicItemService;
import no.systema.tds.nctsexport.service.NctsExportSpecificTopicService;
import no.systema.tds.nctsexport.url.store.UrlDataStore;

import no.systema.tds.util.TdsConstants;

/**
 * This Ajax handler is the class handling ajax request in NctsExport. 
 * It will usually be called from within a jQuery function or other javascript alike... 
 * 
 * @author oscardelatorre
 * @date June 06, 2013
 * 
 */

@Controller
public class NctsExportAjaxHandlerController {
	private static final Logger logger = Logger.getLogger(NctsExportAjaxHandlerController.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware(); 
	 /**
	  * 
	  * @param applicationUser
	  * @param elementValue
	  * @param user
	  * @param avd
	  * @param opd
	  * @return
	  */
	 
	 @RequestMapping(value = "getNctsExportSpecificTopicItemChosenFromGuiElement.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonNctsExportSpecificTopicItemRecord> getSpecificTopicItemChosenFromHtmlList
	  						(@RequestParam String applicationUser, @RequestParam String elementValue, 
	  						 @RequestParam String avd, @RequestParam String opd ) {
		 logger.info("Inside: getNctsExportSpecificTopicItemChosenFromGuiElement()");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = UrlDataStore.NCTS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL;
		 String[] fields = elementValue.split("_");
		 String lineNr = null;
		 String lineCounter = null;
		 if(fields!=null && fields.length==3){
			 logger.info("FIELD: " + fields[0] + " " + fields[1] + " " + fields[2]);
			 lineCounter = fields[1]; //counter
			 lineNr = fields[2]; //tvli
			 
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
				 JsonNctsExportSpecificTopicItemContainer container = this.nctsExportSpecificTopicItemService.getNctsExportSpecificTopicItemContainer(jsonPayload);
				 if(container!=null){
					 for(JsonNctsExportSpecificTopicItemRecord  record : container.getOrderList()){
						 logger.info("Item Description: " + record.getTvvt());
						 logger.info("Receiver name: " + record.getTvnak());
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
	  * @param currencyCode
	  * @param isoDate
	  * @param invoiceAmount
	  * @param toldsats
	  * @return
	  */
	 @RequestMapping(value = "getCurrencyRate_NctsExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set getCurrencyRate(@RequestParam String applicationUser, @RequestParam String currencyCode, 
			  @RequestParam String isoDate, @RequestParam String invoiceAmount, @RequestParam String toldsats) {
		  final String METHOD = "[DEBUG] getCurrencyRate_NctsExport.do "; 
		  logger.info("Inside " + METHOD);
		  Set result = new HashSet();
		  //String validDate = this.getValidCurrencyDate(isoDate);
		  String validDate = isoDate;
		  if(toldsats==null || "".equals(toldsats)){
			  toldsats = "0.00";
		  }
		  //build
		  String BASE_URL_CURRENCY_RATE = TdsUrlDataStore.TDS_FETCH_CURRENCY_RATE_URL;
		  StringBuffer urlRequestParamsCurrencyRate = new StringBuffer();
		  urlRequestParamsCurrencyRate.append("user=" + applicationUser);
		  urlRequestParamsCurrencyRate.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kod=" + currencyCode);
		  urlRequestParamsCurrencyRate.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "datum=" + validDate);
		  //execute
		  String jsonPayloadCurrencyRate = this.urlCgiProxyService.getJsonContent(BASE_URL_CURRENCY_RATE, urlRequestParamsCurrencyRate.toString());
		  logger.info(METHOD + "CURRENCY URL:" + BASE_URL_CURRENCY_RATE);
		  logger.info(METHOD + "CURRENCY PARAMS:" + urlRequestParamsCurrencyRate.toString());
		  logger.info(METHOD + jsonPayloadCurrencyRate);
		  //now map and process json
		  if(jsonPayloadCurrencyRate!=null){
			  JsonCurrencyRateContainer jsonCurrencyRateContainer = this.currencyRateService.getCurrencyRateContainer(jsonPayloadCurrencyRate);
			  for(JsonCurrencyRateRecord record : jsonCurrencyRateContainer.getValutakurs()){
				  //Debug
				  logger.info(METHOD + "Currency RATE: " + record.getSvvk_krs());
				  logger.info(METHOD + "Currency FACTOR: " + record.getSvvs_omr());
				  //extra fields
				  logger.info("InvoiceAmount:" + invoiceAmount);
				  if(invoiceAmount!=null && !"".equals(invoiceAmount)){
					  logger.info(METHOD + "Inside... ");
					  Double dblAmount = Double.parseDouble(invoiceAmount.replace(",", "."));
					  Double dblToldsats = Double.parseDouble(toldsats.replace(",", "."));
					  Double dblKurs = Double.parseDouble(record.getSvvk_krs().replace(",", "."));
					  Integer intFactor = Integer.parseInt(record.getSvvs_omr());
					  logger.info(METHOD + "Before math... ");
					  //(1) do the math
					  Double dblAmountSEK = (dblAmount * dblKurs) / intFactor;
					  Double dblTollvSEK = (dblAmountSEK * dblToldsats);
					  Double dblSubTotalExklMoms = (dblAmountSEK + dblTollvSEK);
					  Double dblMomsSEK = (dblSubTotalExklMoms) * 0.25;
					  Double dblGrandTotalSEK = dblSubTotalExklMoms + dblMomsSEK;
					  logger.info(METHOD + "After math... " + dblAmountSEK + "-"+dblTollvSEK + "-" + dblSubTotalExklMoms + "-" + dblMomsSEK + "-" + dblGrandTotalSEK);
					  //format decimals numbers
					  //dblAmountDKK = this.numberFormatter.getDouble(dblAmountDKK, 2);
					  //dblTollvDKK = this.numberFormatter.getDouble(dblTollvDKK, 2);
					  //dblSubTotalExklMoms = this.numberFormatter.getDouble(dblSubTotalExklMoms, 2);
					  //dblMomsDKK = this.numberFormatter.getDouble(dblMomsDKK, 2);
					  //dblGrandTotalDKK = this.numberFormatter.getDouble(dblGrandTotalDKK, 2);
					  //logger.info(METHOD + "After format... " + dblAmountDKK + "-"+dblTollvDKK + "-" + dblSubTotalExklMoms + "-" + dblMomsDKK + "-" + dblGrandTotalDKK);
					  ///(2) setters
					  //--String strAmountDKK = String.valueOf(dblAmountDKK);
					  //--String strAmountDKK = this.numberFormatter.getDoubleEuropeanFormat(dblAmountDKK, 2);
					  String strAmountSEK = String.valueOf(this.numberFormatter.getDoubleEuropeanFormat(dblAmountSEK, 2, false));
					  record.setOwn_blpSEK(strAmountSEK);
					  //Tollverdi
					  //--String strTollvDKK = String.valueOf(dblTollvDKK);
					  //--record.setOwn_tollvDKK(strTollvDKK.replace(".", ","));
					  String strTollvSEK = String.valueOf(this.numberFormatter.getDoubleEuropeanFormat(dblTollvSEK, 2, false));
					  record.setOwn_tollvSEK(strTollvSEK);
					  //Moms
					  //--String strMomsDKK = String.valueOf(dblMomsDKK);
					  //--record.setOwn_momsDKK(strMomsDKK.replace(".", ","));
					  String strMomsDKK = String.valueOf(this.numberFormatter.getDoubleEuropeanFormat(dblMomsSEK, 2, false));
					  record.setOwn_momsSEK(strMomsDKK);
					  //Grand total
					  //--String strGrandTotalDKK = String.valueOf(dblGrandTotalDKK);
					  //--record.setOwn_grandTotalDKK(strGrandTotalDKK.replace(".", ","));
					  String setOwn_grandTotalSEK = String.valueOf(this.numberFormatter.getDoubleEuropeanFormat(dblGrandTotalSEK, 2, false));
					  record.setOwn_grandTotalSEK(setOwn_grandTotalSEK);
					  logger.info(METHOD + "Grand Total: " + record.getOwn_grandTotalSEK());
					  
				  }
				  result.add(record);
			  }
		  } 
		  return result;
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
	 @RequestMapping(value = "initCreateNewTopic.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTdsExportCustomerRecord> initCreateNewTopic(@RequestParam String applicationUser, @RequestParam String avd) {
		 
		 	String method = "NctsExportAjaxHandlerController.initCreateNewTopic()";
		 	logger.info("Inside " + method);
		 	Set result = new HashSet();
		 	
		 	logger.info("FETCH record transaction...");
			//---------------------------
			//get BASE URL = RPG-PROGRAM
			//---------------------------
			String BASE_URL = UrlDataStore.NCTS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
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
	    		JsonNctsExportSpecificTopicContainer container = this.nctsExportSpecificTopicService.getNctsExportSpecificTopicContainer(jsonPayload);
	    		if(container!=null){
	    			for(JsonNctsExportSpecificTopicRecord  record : container.getOneorder()){
	    				logger.info("Deklarantens plats via AJAX: " + record.getThdst() );
	    				result.add(record);
	    			}
	    		}
	    	}
			return result;
		  
	  }
	 
	 /**
	  * Calculates the guarantee amount for an end-user event (usually a button)
	  * @param applicationUser
	  * @param avd
	  * @param opd
	  * @return
	  */
	 @RequestMapping(value = "calculateGuaranteeAmount.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonNctsExportSpecificTopicContainer> calculateGuarantee(@RequestParam String applicationUser, 
			  								@RequestParam String avd, @RequestParam String opd) {
		 
		 	String method = "NctsExportAjaxHandlerController.calculateGuarantee";
		 	logger.info("Inside " + method);
		 	Set result = new HashSet();
		 	
		 	logger.info("FETCH calculation...");
		 	JsonNctsExportSpecificTopicContainer container = this.nctsExportControllerAjaxCommonFunctionsMgr.calculateGuaranteeAmount(applicationUser, avd, opd);
			if(container!=null){
    			logger.warn("Guarantee amount via AJAX: " + container.getAmount() );
    			result.add(container);
			}
			return result;			
		 	
	  }
	  
	  /**
	   * 	
	   * @param applicationUser
	   * @param avd
	   * @return
	   */
	  @RequestMapping(value = "validateGuaranteeNrAndCode.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTdsExportCustomerRecord> validateGuaranteeNrAndCode(@RequestParam String applicationUser, @RequestParam String guaranteeNumber, @RequestParam String guaranteeCode ) {
		 
		 	String method = "TdsExportAjaxHandler.validateGuaranteeNrAndCode()";
		 	logger.info("Inside " + method);
		 	Set result = new HashSet();
		 	
		 	logger.info("VALIDATION of Guarantee...");
			//---------------------------
			//get BASE URL = RPG-PROGRAM
			//---------------------------
			String BASE_URL = UrlDataStore.NCTS_EXPORT_BASE_VALIDATE_SPECIFIC_TOPIC_GUARRANTEE_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForGuaranteeValidation(applicationUser,guaranteeNumber,guaranteeCode );
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
				JsonNctsExportSpecificTopicGuaranteeValidatorContainer container = this.nctsExportSpecificTopicService.getNctsExportSpecificTopicGuaranteeValidatorContainer(jsonPayload);
				logger.info("errMsg on Guarantee: " + container.getErrMsg());
	    			if(container!=null){
	    				result.add(container);
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
	  
	  @RequestMapping(value = "searchTaricVarukodNcts.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTdsTaricVarukodRecord> getTaricVarukodNcts(@RequestParam String applicationUser, @RequestParam String taricVarukod) {
		  logger.info("Inside NctsExportAjaxHandler.getTaricVarukodNcts()");
		  Set result = new HashSet();
		  String EXPORT_IE = "E";//always E since we are in EXPORT mode
		  
		  try{
			  String BASE_URL = TdsUrlDataStore.TDS_FETCH_TARIC_VARUKODER_ITEMS_URL;
			  String urlRequestParamsKeys = "user=" + applicationUser + "&ie=" + EXPORT_IE + "&kod=" + taricVarukod;
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
	   * Validate guarantee dependency fields
	   * 
	   * @param applicationUser
	   * @param guaranteeNumber
	   * @param guaranteeCode
	   * @return
	   */
	  private String getRequestUrlKeyParametersForGuaranteeValidation(String applicationUser,String guaranteeNumber,String guaranteeCode){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  sb.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thgft1=" + guaranteeNumber );
		  sb.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thgadk=" + guaranteeCode );
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
	  	
	  /**
	   * 
	   * @param applicationUser
	   * @param requestParams
	   * @return
	   */
	  @RequestMapping(value = "importTdsExportAsNctsExportItemLine_TdsNctsExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonNctsExportSpecificTopicContainer> importTdsExportAsSkatNctsExportItemLine(@RequestParam String applicationUser, @RequestParam String requestParams) {
		 
		 	String method = "importTdsExportAsNctsExportItemLine_TdsNctsExport.do";
		 	logger.info("Inside " + method);
		 	Set result = new HashSet();
		 	
		 	if (requestParams!=null && !"".equals(requestParams)){
			 	String[] params = requestParams.split(";");
			 	List <String>list = Arrays.asList(params);
			 	
			 	for (String record : list){
				 	logger.info("update record transaction started");
					//---------------------------
					//get BASE URL = RPG-PROGRAM
					//---------------------------
					String BASE_URL = UrlDataStore.NCTS_EXPORT_BASE_IMPORT_EXPORT_AS_ITEMLINE_URL;
					//url params
					String urlRequestParamsKeys = "user=" + applicationUser + record;
					//for debug purposes in GUI
					logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
					logger.info("URL: " + BASE_URL);
					logger.info("URL PARAMS: " + urlRequestParamsKeys);
					//--------------------------------------
					//EXECUTE RPG program here
					//--------------------------------------
					String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
					//Debug --> 
					logger.info(method + " --> jsonPayload:" + jsonPayload);
					logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		
					if(jsonPayload!=null){
					JsonNctsExportSpecificTopicContainer container = this.nctsExportSpecificTopicService.getNctsExportSpecificTopicContainer(jsonPayload);
			    		if(container!=null){
			    			logger.info("container errMsg (if any): " + "avd:" + container.getAvd() + " opd:" + container.getOpd() + 
			    						" errMsg:" + container.getErrMsg() );
			    					result.add(container);
			    		}
			    	}
			    	
			 	}
		 	}
		return result;  
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
	  
	  @Qualifier ("nctsExportSpecificTopicService")
	  private NctsExportSpecificTopicService nctsExportSpecificTopicService;
	  @Autowired
	  @Required
	  public void setTdsExportSpecificTopicService (NctsExportSpecificTopicService value){ this.nctsExportSpecificTopicService = value; }
	  public NctsExportSpecificTopicService getTdsExportSpecificTopicService(){ return this.nctsExportSpecificTopicService; }
		
	  @Qualifier 
	  private NctsExportSpecificTopicItemService nctsExportSpecificTopicItemService;
	  @Autowired
	  @Required	
	  public void setNctsExportSpecificTopicItemService(NctsExportSpecificTopicItemService value){this.nctsExportSpecificTopicItemService = value;}
	  public NctsExportSpecificTopicItemService getNctsExportSpecificTopicItemService(){ return this.nctsExportSpecificTopicItemService; }
	  
	  
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
	   
	  
	  @Autowired
	  NctsExportControllerAjaxCommonFunctionsMgr nctsExportControllerAjaxCommonFunctionsMgr;
		
}
