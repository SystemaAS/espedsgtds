package no.systema.tds.admin.controller;

import java.lang.reflect.Field;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;


//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.validator.UserValidator;
import no.systema.main.util.AppConstants;


import no.systema.tds.service.html.dropdown.TdsDropDownListPopulationService;

import no.systema.main.model.SystemaWebUser;
import no.systema.tds.admin.filter.SearchFilterTdsAdminTransportList;
import no.systema.tds.admin.validator.TdsAdminTransportListValidator;
import no.systema.tds.admin.service.TdsAdminTransportService;
import no.systema.tds.admin.model.jsonjackson.topic.JsonTdsAdminTransportListContainer;
import no.systema.tds.admin.url.store.TdsAdminUrlDataStore;

import no.systema.tds.util.TdsConstants;
import no.systema.tds.url.store.TdsUrlDataStore;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsAvdelningContainer;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsAvdelningRecord;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureContainer;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureRecord;



/**
 * TDS Admin Transport Controller 
 * 
 * @author oscardelatorre
 * @date Jan 14, 2014
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TdsAdminTransportController {
	
	private static final Logger logger = Logger.getLogger(TdsAdminTransportController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsadmin_transport.do", method=RequestMethod.GET)
	public ModelAndView doTdsExportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tdsadmin_transport");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		SearchFilterTdsAdminTransportList searchFilter = new SearchFilterTdsAdminTransportList();
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_ADMIN);
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, TdsConstants.ACTIVE_URL_RPG_INITVALUE); 
			//lists
			this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
			this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			//init filter with users signature (for starters)
			searchFilter.setSign(appUser.getTdsSign());
			successView.addObject("searchFilter" , searchFilter);
			//init the rest
			successView.addObject(TdsConstants.DOMAIN_MODEL , model);
			successView.addObject(TdsConstants.DOMAIN_LIST,new ArrayList());
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsadmin_transport", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFind(@ModelAttribute ("record") SearchFilterTdsAdminTransportList recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		
		ModelAndView successView = new ModelAndView("tdsadmin_transport");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
	    	
			//-----------
			//Validation
			//-----------
			TdsAdminTransportListValidator validator = new TdsAdminTransportListValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    
		    //check for ERRORS
			if(bindingResult.hasErrors()){
		    		logger.info("[ERROR Validation] search-filter does not validate)");
		    		//put domain objects and do go back to the successView from here
		    		//drop downs
				this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
				this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
				successView.addObject(TdsConstants.DOMAIN_MODEL, model);
		    	
				successView.addObject(TdsConstants.DOMAIN_LIST, new ArrayList());
				successView.addObject("searchFilter", recordToValidate);
				return successView;
	    		
		    }else{
				//----------------------------------------------
				//get Search Filter and populate (bind) it here
				//----------------------------------------------
		    		SearchFilterTdsAdminTransportList searchFilter = new SearchFilterTdsAdminTransportList();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(searchFilter);
	            //binder.registerCustomEditor(...); // if needed
	            binder.bind(request);
	            
	            //get BASE URL
		    		final String BASE_URL = TdsAdminUrlDataStore.TDS_ADMIN_BASE_TRANSPORT_LIST_URL;
		    		//add URL-parameters
		    		String urlRequestParams = this.getRequestUrlKeyParameters(searchFilter, appUser);
				session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL + "==>params: " + urlRequestParams.toString()); 
			    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			    	logger.info("URL: " + BASE_URL);
			    	logger.info("URL PARAMS: " + urlRequestParams);
			    	
			    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

				//Debug --> 
				logger.info(jsonPayload);
			    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			    	if(jsonPayload!=null){
			    		JsonTdsAdminTransportListContainer jsonTdsAdminTransportListContainer = this.tdsAdminTransportService.getTdsAdminTransportListContainer(jsonPayload);
					//-----------------------------------------------------------
					//now filter the topic list with the search filter (if applicable)
					//-----------------------------------------------------------
					outputList = jsonTdsAdminTransportListContainer.getOrderList();

					
					//--------------------------------------
					//Final successView with domain objects
					//--------------------------------------
					//drop downs
					this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
					this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
					successView.addObject(TdsConstants.DOMAIN_MODEL , model);
			    		//domain and search filter
					successView.addObject(TdsConstants.DOMAIN_LIST,outputList);
					successView.addObject("searchFilter", searchFilter);
					logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
			    	
					return successView;
					
				}else{
					logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
					return loginView;
				}
		    }
		}
		
	}

	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	
	private void populateAvdelningHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser){
		//fill in html lists here
		String UPPDRAG_TYPE = "A"; //All avd in the company (for Transportuppdrag)
		try{
			String BASE_URL = TdsUrlDataStore.TDS_FETCH_AVDELNINGAR_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + UPPDRAG_TYPE);
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("AVD BASE_URL:" + BASE_URL);
			logger.info("AVD BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonTdsAvdelningContainer container = this.tdsDropDownListPopulationService.getAvdelningContainer(url);
			List<JsonTdsAvdelningRecord> list = new ArrayList();
			for(JsonTdsAvdelningRecord record: container.getAvdelningar()){
				list.add(record);
			}
			model.put(TdsConstants.RESOURCE_MODEL_KEY_AVD_LIST, list);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	
	private void populateSignatureHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser){
		//fill in html lists here
		String UPPDRAG_TYPE = "F"; //TDS (TODO for Transportuppdrag ?) 
		
		try{
			String BASE_URL = TdsUrlDataStore.TDS_FETCH_SIGNATURE_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + UPPDRAG_TYPE);
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("SIGN BASE_URL:" + BASE_URL);
			logger.info("SIGN BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonTdsSignatureContainer container = this.tdsDropDownListPopulationService.getSignatureContainer(url);
			List<JsonTdsSignatureRecord> list = new ArrayList();
			for(JsonTdsSignatureRecord record: container.getSignaturer()){
				list.add(record);
			}
			model.put(TdsConstants.RESOURCE_MODEL_KEY_SIGN_LIST, list);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(SearchFilterTdsAdminTransportList searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		if(searchFilter.getAvd()!=null && !"".equals(searchFilter.getAvd())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + searchFilter.getAvd());
		}
		if(searchFilter.getSign()!=null && !"".equals(searchFilter.getSign())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + searchFilter.getSign());
		}
		if(searchFilter.getOpd()!=null && !"".equals(searchFilter.getOpd())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + searchFilter.getOpd());
		}
		if(searchFilter.getAvsNavn()!=null && !"".equals(searchFilter.getAvsNavn())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avsNavn=" + searchFilter.getAvsNavn());
		}
		if(searchFilter.getMotNavn()!=null && !"".equals(searchFilter.getMotNavn())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "motNavn=" + searchFilter.getMotNavn());
		}
		if(searchFilter.getDatum()!=null && !"".equals(searchFilter.getDatum())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "datum=" + searchFilter.getDatum());
		}
		
		return urlRequestParamsKeys.toString();
	}
	
	//SERVICES
	
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("tdsDropDownListPopulationService")
	private TdsDropDownListPopulationService tdsDropDownListPopulationService;
	@Autowired
	public void setTdsDropDownPopulationService (TdsDropDownListPopulationService value){ this.tdsDropDownListPopulationService=value; }
	public TdsDropDownListPopulationService getTdsDropDownListPopulationService(){return this.tdsDropDownListPopulationService;}
	
	
	@Qualifier ("tdsAdminTransportService")
	private TdsAdminTransportService tdsAdminTransportService;
	@Autowired
	@Required
	public void setTdsAdminTransportService (TdsAdminTransportService value){ this.tdsAdminTransportService = value; }
	public TdsAdminTransportService getTdsAdminTransportService(){ return this.tdsAdminTransportService; }
	
	
}

