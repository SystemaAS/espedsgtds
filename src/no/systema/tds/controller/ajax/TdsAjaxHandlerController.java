/**
 * 
 */
package no.systema.tds.controller.ajax;

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
import no.systema.main.service.general.CurrencyRateService;
import no.systema.tds.tdsexport.model.jsonjackson.customer.JsonTdsExportCustomerContainer;
import no.systema.tds.tdsexport.model.jsonjackson.customer.JsonTdsExportCustomerRecord;
import no.systema.tds.tdsexport.model.jsonjackson.tullkontor.JsonTdsExportTullkontorContainer;
import no.systema.tds.tdsexport.model.jsonjackson.tullkontor.JsonTdsExportTullkontorRecord;

import no.systema.tds.service.TdsSignatureNameService;
import no.systema.tds.service.TdsTaricVarukodService;
import no.systema.tds.tdsexport.service.TdsExportCustomerService;
import no.systema.tds.tdsexport.service.TdsExportSpecificTopicService;
import no.systema.tds.tdsexport.service.TdsExportTullkontorService;
import no.systema.tds.tdsexport.service.TdsExportSpecificTopicItemService;
import no.systema.tds.url.store.TdsUrlDataStore;

import no.systema.tds.util.TdsConstants;

/**
 * This Ajax handler is the class handling ajax request in Tds. 
 * It will usually be called from within a jQuery function or other javascript alike... 
 * 
 * @author oscardelatorre
 * @date Dec 20, 2013
 * 
 */

@Controller
public class TdsAjaxHandlerController {
	private static final Logger logger = Logger.getLogger(TdsAjaxHandlerController.class.getName());
	
	
	
	  /**
	   * Populates the GUI element with a list of customers fulfilling the search condition
	   * @param customerName
	   * @param customerNumber
	   * @return
	   */
	  @RequestMapping(value = "searchCustomer.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTdsExportCustomerRecord> searchCustomer(@RequestParam String applicationUser, @RequestParam String customerName, @RequestParam String customerNumber) {
		  logger.info("Inside TdsExportAjaxHandler.searchCustomer()");
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
	  		  	JsonTdsExportCustomerContainer container = this.tdsExportCustomerService.getTdsExportCustomerContainer(jsonPayload);
	    		if(container!=null){
	    			for(JsonTdsExportCustomerRecord  record : container.getCustomerlist()){
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
	  
	  @RequestMapping(value = "searchUtfartsTullkontor.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTdsExportTullkontorRecord> searchUtfartsTullkontor(@RequestParam String applicationUser, @RequestParam String tullkontorName, @RequestParam String tullkontorCode) {
		  logger.info("Inside TdsAjaxHandler.searchUtfartsTullkontor()");
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
			  JsonTdsExportTullkontorContainer container = this.tdsExportTullkontorService.getTdsExportTullkontorContainer(jsonPayload);
			  if(container!=null){
				  for(JsonTdsExportTullkontorRecord  record : container.getCustomslist()){
					  //logger.info("Kontorsnamn via AJAX: " + record.getTktxtn() + " Code:" + record.getTkkode());
					  result.add(record);
				  }
			  }
		  }
		  return result;
		  
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param currencyCode
	   * @return
	   */
	  @RequestMapping(value = "getCurrencyRate.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonTdsExportTullkontorRecord> getCurrencyRate(@RequestParam String applicationUser, @RequestParam String currencyCode) {
		  logger.info("Inside TdsExportAjaxHandler.getCurrencyRate()");
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
		
}
