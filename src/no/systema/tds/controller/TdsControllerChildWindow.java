package no.systema.tds.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;

import no.systema.tds.model.jsonjackson.customer.JsonTdsCustomerContainer;
import no.systema.tds.model.jsonjackson.customer.JsonTdsCustomerRecord;
import no.systema.tds.service.TdsCustomerService;
import no.systema.tds.url.store.TdsUrlDataStore;
import no.systema.tds.util.TdsConstants;


/**
 * TDS  Controller - Child Window popup
 * 
 * @author oscardelatorre
 * @date Jun 07, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TdsControllerChildWindow {
	
	private static final Logger logger = Logger.getLogger(TdsControllerChildWindow.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(800);
	//customer
	
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	//private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tds_childwindow_customer.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView searchCustomer(HttpSession session, HttpServletRequest request){
		logger.info("Inside searchCustomer");
		
		ModelAndView successView = new ModelAndView("tds_childwindow_customer");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		logger.info(callerType);
		String customerName = request.getParameter("sonavn");
		String customerNr = request.getParameter("knr");
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		if(appUser==null){
			return this.loginView;
				
		}else{
			Collection<JsonTdsCustomerRecord> list = new ArrayList<JsonTdsCustomerRecord>();
			//prepare the access CGI with RPG back-end
			if( (customerNr!=null && !"".equals(customerNr)) || (customerName!=null && !"".equals(customerName)) ){
				
				String BASE_URL = TdsUrlDataStore.TDS_FETCH_CUSTOMER_URL;
				String urlRequestParamsKeys = this.getRequestUrlKeyParametersForSearchCustomer(appUser.getUser(), customerName, customerNr);
				logger.info("URL: " + BASE_URL);
				logger.info("PARAMS: " + urlRequestParamsKeys);
				logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
				String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				//Should be removed as soon as RPG return the correct container name = customerlist (not capitalized in the first letter)
				logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
				logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		jsonPayload = jsonPayload.replaceFirst("Customerlist", "customerlist");
		    		JsonTdsCustomerContainer container = this.tdsCustomerService.getTdsExportCustomerContainer(jsonPayload);
		    		if(container!=null){
		    			list = container.getCustomerlist();
		    			for(JsonTdsCustomerRecord  record : container.getCustomerlist()){
		    				//logger.info("CUSTOMER via AJAX: " + record.getKnavn() + " NUMBER:" + record.getKundnr());
		    				//logger.info("KPERS: " + record.getKpers() + " TLF:" + record.getTlf());
		    			}
		    		}
		    	}
			}
			
			model.put("customerList", list);
			model.put("sonavn", customerName);
			model.put("knr", customerNr);
			model.put("ctype", callerType);
			
			successView.addObject(TdsConstants.DOMAIN_MODEL , model);
			
	    	return successView;	
		  	
		}
		
	}
 	
	/**
	 * 
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
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier 
	private TdsCustomerService tdsCustomerService;
	@Autowired
	@Required	
	public void setTdsCustomerService(TdsCustomerService value){this.tdsCustomerService = value;}
	public TdsCustomerService getTdsCustomerService(){ return this.tdsCustomerService; }
	
}

