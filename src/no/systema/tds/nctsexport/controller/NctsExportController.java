package no.systema.tds.nctsexport.controller;

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
import no.systema.main.util.StringManager;

import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsAvdelningContainer;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsAvdelningRecord;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureContainer;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureRecord;

import no.systema.tds.service.html.dropdown.TdsDropDownListPopulationService;
import no.systema.tds.nctsexport.validator.NctsExportListValidator;

import no.systema.main.model.SystemaWebUser;
import no.systema.tds.nctsexport.filter.SearchFilterNctsExportTopicList;
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportTopicListContainer;
import no.systema.tds.nctsexport.service.NctsExportTopicListService;
import no.systema.tds.nctsexport.url.store.UrlDataStore;
import no.systema.tds.url.store.TdsUrlDataStore;
import no.systema.tds.util.TdsConstants;


/**
 * NCTS Export Topic Controller 
 * 
 * @author oscardelatorre
 *
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class NctsExportController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(NctsExportController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private StringManager strMgr = new StringManager();
	
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
	/* OBSOLETE. Replace by doFind
	@RequestMapping(value="nctsexport.do", method=RequestMethod.GET)
	public ModelAndView doNctsExportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("nctsexport");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		SearchFilterNctsExportTopicList searchFilter = new SearchFilterNctsExportTopicList();
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_NCTS_EXPORT);
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, TdsConstants.ACTIVE_URL_RPG_INITVALUE); 
			//lists
			this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
			this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			//init filter with users signature (for starters)
			searchFilter.setSign(appUser.getTdsSign());
			successView.addObject("searchFilter" , searchFilter);
			
			successView.addObject("model" , model);
			successView.addObject("list",new ArrayList());
	    	return successView;
		}
	}
	*/
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="nctsexport", params="action=doFind", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doFind(@ModelAttribute ("record") SearchFilterNctsExportTopicList recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		ModelAndView successView = new ModelAndView("nctsexport");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_NCTS_EXPORT);
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, TdsConstants.ACTIVE_URL_RPG_INITVALUE); 
			
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//-----------
			//Validation
			//-----------
			NctsExportListValidator validator = new NctsExportListValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    logger.info("NCTS - After Validator!");
		    
		    //check for ERRORS
			if(bindingResult.hasErrors()){
		    	logger.info("[ERROR Validation] search-filter does not validate)");
		    	//put domain objects and do go back to the successView from here
		    	//drop downs
				this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
				this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
				successView.addObject(TdsConstants.DOMAIN_MODEL , model);
		    	successView.addObject(TdsConstants.DOMAIN_LIST,new ArrayList());
		    	successView.addObject(TdsConstants.DOMAIN_SEARCH_FILTER_TDSEXPORT_NCTS, recordToValidate);
				
				return successView;
	    		
		    }else{
				//----------------------------------------------
				//get Search Filter and populate (bind) it here
				//----------------------------------------------
				SearchFilterNctsExportTopicList searchFilter = new SearchFilterNctsExportTopicList();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(searchFilter);
	            //binder.registerCustomEditor(...); // if needed
	            binder.bind(request);
	            
	            //Put in session for further use (within this module) ONLY with: POST method = doFind on search fields
	            if(request.getMethod().equalsIgnoreCase(RequestMethod.POST.toString())){
	            	session.setAttribute(TdsConstants.SESSION_SEARCH_FILTER_TDSEXPORT_NCTS, searchFilter);
	            }else{
	            	SearchFilterNctsExportTopicList sessionFilter = (SearchFilterNctsExportTopicList)session.getAttribute(TdsConstants.SESSION_SEARCH_FILTER_TDSEXPORT_NCTS);
	            	if(sessionFilter!=null){
	            		//Use the session filter when applicable
	            		searchFilter = sessionFilter;
	            	}
	            }
	            //get BASE URL
	    		String BASE_URL = UrlDataStore.NCTS_EXPORT_BASE_TOPICLIST_URL;
	    		//only when docRef exists
	    		if(searchFilter!=null && strMgr.isNotNull(searchFilter.getDocRef())){
	    			BASE_URL = UrlDataStore.NCTS_EXPORT_BASE_TOPICLIST_DOCREF_URL;
	    		}
	    		//add URL-parameters
				String urlRequestParams = this.getRequestUrlKeyParameters(searchFilter, appUser);
				logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams.toString());
		    	
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
				//Debug --> 
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	
				if(jsonPayload!=null){
					JsonNctsExportTopicListContainer jsonNctsExportTopicListContainer = this.nctsExportTopicListService.getNctsExportTopicListContainer(jsonPayload);
					//-----------------------------------------------------------------
					//now filter the topic list with the search filter (if applicable)
					//-----------------------------------------------------------------
					outputList = jsonNctsExportTopicListContainer.getOrderList();

					//--------------------------------------
					//Final successView with domain objects
					//--------------------------------------
					//drop downs
					this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
					this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
					successView.addObject(TdsConstants.DOMAIN_MODEL , model);
			    		//domain and search filter
					successView.addObject(TdsConstants.DOMAIN_LIST,outputList);
					//set a session variable in order to make the list available to an external view controller (Excel/PDF- Controller)
					session.setAttribute(session.getId() + TdsConstants.SESSION_LIST, outputList);
					
					if(session.getAttribute(TdsConstants.SESSION_SEARCH_FILTER_TDSEXPORT_NCTS) ==null || "".equals(session.getAttribute(TdsConstants.SESSION_SEARCH_FILTER_TDSEXPORT_NCTS)) ){
						successView.addObject(TdsConstants.SESSION_SEARCH_FILTER_TDSEXPORT_NCTS, searchFilter);
					}
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
	 * @param session
	 * 
	 */
	private void populateAvdelningHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser, HttpSession session){
		//fill in html lists here
		String NCTS_EXPORT_IE = "X"; //NCTS Export ... ie=N (for NCTS Import)
		
		try{
			String BASE_URL = TdsUrlDataStore.TDS_FETCH_AVDELNINGAR_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + NCTS_EXPORT_IE);
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
		String TDS_EXPORT_IMPORT_IE = "N"; //NCTS ==> ie=N 
		
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
	private String getRequestUrlKeyParameters(SearchFilterNctsExportTopicList searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		if(searchFilter.getAvd()!=null && !"".equals(searchFilter.getAvd())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + searchFilter.getAvd());
		}
		if(searchFilter.getOpd()!=null && !"".equals(searchFilter.getOpd())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + searchFilter.getOpd());
		}
		if(searchFilter.getSign()!=null && !"".equals(searchFilter.getSign())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + searchFilter.getSign());
		}
		
		if(searchFilter.getLrnNr()!=null && !"".equals(searchFilter.getLrnNr())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lrn=" + searchFilter.getLrnNr());
		}
		
		if(searchFilter.getMrnNr()!=null && !"".equals(searchFilter.getMrnNr())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mrn=" + searchFilter.getMrnNr());
		}
		
		if(searchFilter.getDatum()!=null && !"".equals(searchFilter.getDatum())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "datum=" + searchFilter.getDatum());
		}
		if(searchFilter.getDatumt()!=null && !"".equals(searchFilter.getDatumt())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "datumt=" + searchFilter.getDatumt());
		}
		if(searchFilter.getStatus()!=null && !"".equals(searchFilter.getStatus())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "status=" + searchFilter.getStatus());
		}
		
		if(searchFilter.getBruttoVikt()!=null && !"".equals(searchFilter.getBruttoVikt())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "bvikt=" + searchFilter.getBruttoVikt());
		}
		if(searchFilter.getDocRef()!=null && !"".equals(searchFilter.getDocRef())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tvdref=" + searchFilter.getDocRef());
		}
		
		if(searchFilter.getMotNavn()!=null && !"".equals(searchFilter.getMotNavn())){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "motNavn=" + searchFilter.getMotNavn());
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
	
	
	
	@Qualifier ("nctsExportTopicListService")
	private NctsExportTopicListService nctsExportTopicListService;
	@Autowired
	@Required
	public void setNctsExportTopicListService (NctsExportTopicListService value){ this.nctsExportTopicListService = value; }
	public NctsExportTopicListService getTdsExportTopicListService(){ return this.nctsExportTopicListService; }
	
	
	@Qualifier ("tdsDropDownListPopulationService")
	private TdsDropDownListPopulationService tdsDropDownListPopulationService;
	@Autowired
	public void setTdsDropDownPopulationService (TdsDropDownListPopulationService value){ this.tdsDropDownListPopulationService=value; }
	public TdsDropDownListPopulationService getTdsDropDownListPopulationService(){return this.tdsDropDownListPopulationService;}
	
	
}

