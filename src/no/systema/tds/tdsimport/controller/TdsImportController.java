package no.systema.tds.tdsimport.controller;

import java.lang.reflect.Field;
import java.util.*;

import org.apache.log4j.Level;
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

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;


//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;

import no.systema.tds.tdsimport.util.RpgReturnResponseHandler;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsAvdelningContainer;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsAvdelningRecord;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureContainer;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureRecord;

import no.systema.tds.service.html.dropdown.TdsDropDownListPopulationService;
import no.systema.tds.tdsimport.validator.TdsImportListValidator;
import no.systema.tds.tdsimport.validator.TdsImportUtlamListValidator;


import no.systema.main.model.SystemaWebUser;
import no.systema.tds.tdsimport.filter.SearchFilterTdsImportTopicList;
import no.systema.tds.tdsimport.filter.SearchFilterTdsImportTopicUtlamList;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicListContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicListRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicUtlamListContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicUtlamListRecord;
import no.systema.tds.tdsimport.service.TdsImportTopicListService;
import no.systema.tds.tdsimport.url.store.TdsImportUrlDataStore;
import no.systema.tds.url.store.TdsUrlDataStore;
import no.systema.tds.util.TdsConstants;
import no.systema.tds.util.manager.TaricDirectAccessorMgr;


/**
 * TDS Import Topic Controller 
 * 
 * @author oscardelatorre
 *
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TdsImportController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(TdsImportController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private TaricDirectAccessorMgr taricDirectAccessorMgr = new TaricDirectAccessorMgr();
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	/*OBSOLETE. Replaced by doFind()
	@RequestMapping(value="tdsimport.do", method=RequestMethod.GET)
	public ModelAndView doTdsImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tdsimport");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		SearchFilterTdsImportTopicList searchFilter = new SearchFilterTdsImportTopicList();
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_IMPORT);
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, TdsConstants.ACTIVE_URL_RPG_INITVALUE); 
		
			//lists
			this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
			this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			//init filter with users signature (for starters)
			searchFilter.setSign(appUser.getTdsSign());
			successView.addObject("searchFilter" , searchFilter);
			//init the rest
			this.taricDirectAccessorMgr.askTullid(model);
			successView.addObject(TdsConstants.DOMAIN_MODEL , model);
			successView.addObject(TdsConstants.DOMAIN_LIST,new ArrayList());
			
	    	return successView;
		}
	}
	*/
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsimport", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFind(@ModelAttribute ("record") SearchFilterTdsImportTopicList recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Collection<JsonTdsImportTopicListRecord> outputList = new ArrayList();
		Map model = new HashMap();
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		
		ModelAndView successView = new ModelAndView("tdsimport");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_IMPORT);
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, TdsConstants.ACTIVE_URL_RPG_INITVALUE); 

			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//-----------
			//Validation
			//-----------
			TdsImportListValidator validator = new TdsImportListValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//drop downs
				this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
				this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
				this.taricDirectAccessorMgr.askTullid(model);
				successView.addObject(TdsConstants.DOMAIN_MODEL, model);
		    	
				successView.addObject(TdsConstants.DOMAIN_LIST, new ArrayList());
				successView.addObject(TdsConstants.DOMAIN_SEARCH_FILTER_TDSIMPORT, recordToValidate);
				return successView;
	    		
		    }else{
				//----------------------------------------------
				//get Search Filter and populate (bind) it here
				//----------------------------------------------
	    		SearchFilterTdsImportTopicList searchFilter = new SearchFilterTdsImportTopicList();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(searchFilter);
	            //binder.registerCustomEditor(...); // if needed
	            binder.bind(request);
	            
	            if(request.getMethod().equalsIgnoreCase(RequestMethod.POST.toString())){
	            	session.setAttribute(TdsConstants.SESSION_SEARCH_FILTER_TDSIMPORT, searchFilter);
	            }else{
	            	SearchFilterTdsImportTopicList sessionFilter = (SearchFilterTdsImportTopicList)session.getAttribute(TdsConstants.SESSION_SEARCH_FILTER_TDSIMPORT);
	            	if(sessionFilter!=null){
	            		//Use the session filter when applicable
	            		searchFilter = sessionFilter;
	            	}
	            }
	            
	            //populate list
	            outputList = this.getTopicList(searchFilter, appUser);
	            				    	
				//--------------------------------------
				//Final successView with domain objects
				//--------------------------------------
				//drop downs
				this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
				this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
				this.taricDirectAccessorMgr.askTullid(model);
				successView.addObject(TdsConstants.DOMAIN_MODEL , model);
	    		//domain and search filter
				successView.addObject(TdsConstants.DOMAIN_LIST,outputList);
				
				if(session.getAttribute(TdsConstants.SESSION_SEARCH_FILTER_TDSIMPORT) ==null || "".equals(session.getAttribute(TdsConstants.SESSION_SEARCH_FILTER_TDSIMPORT)) ){
					successView.addObject(TdsConstants.DOMAIN_SEARCH_FILTER_TDSIMPORT, searchFilter);
				}
				
				logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
		    	
				return successView;
		    }
		}
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsimport.do", params="action=doDelete",  method={RequestMethod.GET} )
	public ModelAndView doDelete(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		ModelAndView successView = new ModelAndView("redirect:tdsimport.do?action=doFind&sign=" + appUser.getTdsSign());
		Collection<JsonTdsImportTopicListRecord> outputList = new ArrayList<JsonTdsImportTopicListRecord>();
		Map model = new HashMap();
		
		logger.info("Inside doDelete");
		
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
    		final String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
    		//add URL-parameters
    		String urlRequestParams = this.getRequestUrlKeyParametersDoDelete(avd,opd, appUser);
    		session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL + "==>params: " + urlRequestParams.toString()); 
	    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParams);
	    	
	    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	    	
	    	//Debug --> 
	    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
	    	//we must evaluate a return RPG code in order to know if the Update was OK or not
	    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicUpdate(rpgReturnPayload);
	    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
	    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on DELETE: " + rpgReturnResponseHandler.getErrorMessage());
	    		this.setFatalError(model, rpgReturnResponseHandler);
	    	}else{
	    		//Update succefully done!
	    		logger.info("[INFO] Record successfully updated, OK ");
	    		//put domain objects
	    		//this.setDomainObjectsInView(session, model, jsonSkatImportSpecificTopicRecord, sumOfAntalKolliInItemLines, sumOfAntalItemLines );
	    	}
			//--------------------------------------
			//Final successView with domain objects
			//--------------------------------------
	    	SearchFilterTdsImportTopicList searchFilter = new SearchFilterTdsImportTopicList();
			//drop downs
			this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
			this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			
			this.taricDirectAccessorMgr.askTullid(model);
			successView.addObject(TdsConstants.DOMAIN_MODEL , model);
    		//get list
			outputList = this.getTopicList(searchFilter, appUser);
			successView.addObject(TdsConstants.DOMAIN_LIST, outputList);
			//init filter with users signature (for starters)
			searchFilter.setSign(appUser.getTdsSign());
			successView.addObject("searchFilter", searchFilter);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
	    	
			return successView;
		}
		
	}
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsimportutlam", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindUtlam(@ModelAttribute ("record") SearchFilterTdsImportTopicUtlamList recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Collection<JsonTdsImportTopicUtlamListRecord> outputList = new ArrayList();
		Map model = new HashMap();
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		
		ModelAndView successView = new ModelAndView("tdsimportutlam");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_IMPORT);
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, TdsConstants.ACTIVE_URL_RPG_INITVALUE); 

			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//-----------
			//Validation
			//-----------
			TdsImportUtlamListValidator validator = new TdsImportUtlamListValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//drop downs
				this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
				this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
				this.taricDirectAccessorMgr.askTullid(model);
				successView.addObject(TdsConstants.DOMAIN_MODEL, model);
		    	
				successView.addObject(TdsConstants.DOMAIN_LIST, new ArrayList());
				successView.addObject("searchFilter", recordToValidate);
				return successView;
	    		
		    }else{
				//----------------------------------------------
				//get Search Filter and populate (bind) it here
				//----------------------------------------------
	    		SearchFilterTdsImportTopicUtlamList searchFilter = new SearchFilterTdsImportTopicUtlamList();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(searchFilter);
	            //binder.registerCustomEditor(...); // if needed
	            binder.bind(request);
	            //populate list
	            outputList = this.getTopicUtlamList(searchFilter, appUser);
	            				    	
				//--------------------------------------
				//Final successView with domain objects
				//--------------------------------------
				//drop downs
				this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
				this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
				this.taricDirectAccessorMgr.askTullid(model);
				successView.addObject(TdsConstants.DOMAIN_MODEL , model);
	    		//domain and search filter
				successView.addObject(TdsConstants.DOMAIN_LIST,outputList);
				successView.addObject("searchFilter", searchFilter);
				logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
		    	
				return successView;
		    }
		}
	}
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private Collection<JsonTdsImportTopicListRecord> getTopicList(SearchFilterTdsImportTopicList searchFilter, SystemaWebUser appUser){
		Collection<JsonTdsImportTopicListRecord> outputList = new ArrayList();	
		
		//get BASE URL
		final String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_TOPICLIST_URL;
		//add URL-parameters
		String urlRequestParams = this.getRequestUrlKeyParameters(searchFilter, appUser);
		//session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL + "==>params: " + urlRequestParams.toString()); 
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + BASE_URL);
		logger.info("URL PARAMS: " + urlRequestParams);
		
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	
		//Debug --> 
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		if(jsonPayload!=null){
			JsonTdsImportTopicListContainer jsonTdsImportTopicListContainer = this.tdsImportTopicListService.getTdsImportTopicListContainer(jsonPayload);
			//-----------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//-----------------------------------------------------------
			outputList = jsonTdsImportTopicListContainer.getOrderList();				    	
		}
		return outputList;
	}
	
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private Collection<JsonTdsImportTopicUtlamListRecord> getTopicUtlamList(SearchFilterTdsImportTopicUtlamList searchFilter, SystemaWebUser appUser){
		Collection<JsonTdsImportTopicUtlamListRecord> outputList = new ArrayList();	
		
		//get BASE URL
		final String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_TOPICLIST_UTLAM_URL;
		//add URL-parameters
		String urlRequestParams = this.getRequestUrlKeyParameters(searchFilter, appUser);
		//session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL + "==>params: " + urlRequestParams.toString()); 
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + BASE_URL);
		logger.info("URL PARAMS: " + urlRequestParams);
		
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	
		//Debug --> 
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		if(jsonPayload!=null){
			JsonTdsImportTopicUtlamListContainer container = this.tdsImportTopicListService.getTdsImportTopicUtlamListContainer(jsonPayload);
			//-----------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//-----------------------------------------------------------
			outputList = container.getUtlList();				    	
		}
		return outputList;
	}
	/**
	 * log Errors in Aspects and Domain objects in order to render on GUI
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param jsonTdsImportSpecificTopicRecord
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        //this.setDomainObjectsInView(model, record);
	}
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 */
	private void setAspectsInView (Map model, RpgReturnResponseHandler rpgReturnResponseHandler){
		model.put(TdsConstants.ASPECT_ERROR_MESSAGE, rpgReturnResponseHandler.getErrorMessage());
		//extra error information
		StringBuffer errorMetaInformation = new StringBuffer();
		errorMetaInformation.append(rpgReturnResponseHandler.getUser());
		errorMetaInformation.append(rpgReturnResponseHandler.getSvih_syop());
		model.put(TdsConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersDoDelete(String avd, String opd, SystemaWebUser appUser){
		final String MODE_DELETE = "D";
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + MODE_DELETE);
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param model
	 * @param appUser
	 * @param session
	 * 
	 */
	private void populateAvdelningHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser, HttpSession session){
		//fill in html lists here
		String TDS_IMPORT_IE = "I"; //Import
		try{
			String BASE_URL = TdsUrlDataStore.TDS_FETCH_AVDELNINGAR_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + TDS_IMPORT_IE);
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
			session.setAttribute(TdsConstants.RESOURCE_MODEL_KEY_AVD_LIST_SESSION_TEST_FLAG, list);
			
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
		String TDS_EXPORT_IMPORT_IE = "F"; //Fortullning. The other one is: NCTS = ie=N 
		
		try{
			String BASE_URL = TdsUrlDataStore.TDS_FETCH_SIGNATURE_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + TDS_EXPORT_IMPORT_IE);
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
	private String getRequestUrlKeyParameters(SearchFilterTdsImportTopicList searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		if(searchFilter.getAvd()!=null && !"".equals(searchFilter.getAvd())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + searchFilter.getAvd());
		}
		if(searchFilter.getOpd()!=null && !"".equals(searchFilter.getOpd())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + searchFilter.getOpd());
		}
		if(searchFilter.getXref()!=null && !"".equals(searchFilter.getXref())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "xref=" + searchFilter.getXref());
		}
		if(searchFilter.getSign()!=null && !"".equals(searchFilter.getSign())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + searchFilter.getSign());
		}
		if(searchFilter.getTullId()!=null && !"".equals(searchFilter.getTullId())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tullId=" + searchFilter.getTullId());
		}
		if(searchFilter.getDatum()!=null && !"".equals(searchFilter.getDatum())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "datum=" + searchFilter.getDatum());
		}
		if(searchFilter.getStatus()!=null && !"".equals(searchFilter.getStatus())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "status=" + searchFilter.getStatus());
		}
		if(searchFilter.getAvsNavn()!=null && !"".equals(searchFilter.getAvsNavn())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avsNavn=" + searchFilter.getAvsNavn());
		}
		if(searchFilter.getMotNavn()!=null && !"".equals(searchFilter.getMotNavn())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "motNavn=" + searchFilter.getMotNavn());
		}
		if(searchFilter.getMtyp()!=null && !"".equals(searchFilter.getMtyp())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mtyp=" + searchFilter.getMtyp());
		}
		
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(SearchFilterTdsImportTopicUtlamList searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		if(searchFilter.getTullid()!=null && !"".equals(searchFilter.getTullid())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tullid=" + searchFilter.getTullid());
		}
		if(searchFilter.getDatum()!=null && !"".equals(searchFilter.getDatum())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "datum=" + searchFilter.getDatum());
		}
		if(searchFilter.getDatumt()!=null && !"".equals(searchFilter.getDatumt())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "datumt=" + searchFilter.getDatumt());
		}
		if(searchFilter.getTyp()!=null && !"".equals(searchFilter.getTyp())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "typ=" + searchFilter.getTyp());
		}
		if(searchFilter.getAvsNavn()!=null && !"".equals(searchFilter.getAvsNavn())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avsnavn=" + searchFilter.getAvsNavn());
		}
		if(searchFilter.getMotNavn()!=null && !"".equals(searchFilter.getMotNavn())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "motnavn=" + searchFilter.getMotNavn());
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
	
	@Qualifier ("tdsImportTopicListService")
	private TdsImportTopicListService tdsImportTopicListService;
	@Autowired
	@Required
	public void setTdsExportTopicListService (TdsImportTopicListService value){ this.tdsImportTopicListService = value; }
	public TdsImportTopicListService getTdsExportTopicListService(){ return this.tdsImportTopicListService; }
	
	
	@Qualifier ("tdsDropDownListPopulationService")
	private TdsDropDownListPopulationService tdsDropDownListPopulationService;
	@Autowired
	public void setTdsDropDownPopulationService (TdsDropDownListPopulationService value){ this.tdsDropDownListPopulationService=value; }
	public TdsDropDownListPopulationService getTdsDropDownListPopulationService(){return this.tdsDropDownListPopulationService;}
	
	
}

