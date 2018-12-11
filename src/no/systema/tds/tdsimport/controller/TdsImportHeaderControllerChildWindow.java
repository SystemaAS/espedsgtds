package no.systema.tds.tdsimport.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javawebparts.core.org.apache.commons.lang.StringUtils;

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
import org.springframework.web.bind.ServletRequestDataBinder;

//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.EncodingTransformer;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;

import no.systema.tds.model.jsonjackson.codes.JsonTdsCodeContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsCodeRecord;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTaricVarukodContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTaricVarukodRecord;

import no.systema.tds.service.TdsGeneralCodesChildWindowService;
import no.systema.tds.service.TdsTaricVarukodService;
import no.systema.tds.service.TdsTaricVarukodServiceImpl;
import no.systema.tds.tdsimport.service.TdsImportTopicListService;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicListExternalRefContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicListExternalRefRecord;
import no.systema.tds.tdsimport.url.store.TdsImportUrlDataStore;
import no.systema.tds.url.store.TdsUrlDataStore;
import no.systema.tds.util.TdsConstants;




/**
 * Tds Import Header Controller - child windows popup
 * 
 * @author oscardelatorre
 * @date Jan 18, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TdsImportHeaderControllerChildWindow {
	
	private static final Logger logger = Logger.getLogger(TdsImportHeaderControllerChildWindow.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	//customer
	private final String DATATABLE_LIST = "list";

	
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
	@RequestMapping(value="tdsimport_edit_invoice_childwindow_generalcodes", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitInvoiceGeneralCodes(@ModelAttribute ("record") JsonTdsCodeContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitInvoiceGeneralCodes");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		String typeCode = request.getParameter("type");
		String ie = this.getIeMode(typeCode);
		
		ModelAndView successView = new ModelAndView("tdsimport_edit_invoice_childwindow_generalcodes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			  
			List list = this.getCodeList(appUser, typeCode, ie);
			
			model.put("generalCodeList", list);
			model.put("callerType", callerType);
			
			successView.addObject(TdsConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsimport_edit_childwindow_generalcodes", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitGeneralCodes(@ModelAttribute ("record") JsonTdsCodeContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitGeneralCodes");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		String typeCode = request.getParameter("type");
		String ie = this.getIeMode(typeCode);
		
		ModelAndView successView = new ModelAndView("tdsimport_edit_childwindow_generalcodes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			  
			List list = this.getCodeList(appUser, typeCode, ie);
			
			model.put("generalCodeList", list);
			model.put("callerType", callerType);
			
			successView.addObject(TdsConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	@RequestMapping(value="tdsimport_childwindow_external_references.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView tdsImportExternalReferences(@ModelAttribute ("record") JsonTdsImportTopicListExternalRefContainer recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("Inside: tdsImportExternalReferences");
		
		ModelAndView successView = new ModelAndView("tdsimport_childwindow_external_references");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		Map model = new HashMap();
		
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//Catch required action (doFetch or doUpdate)
		
		if(appUser==null){
			return this.loginView;
		}else{
			
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String avd = request.getParameter("avd");
			//logger.info("AVD:" + avd);
			String BASE_URL_FETCH = TdsImportUrlDataStore.TDS_IMPORT_BASE_FETCH_TOPIC_LIST_EXTERNAL_REFERENCES_URL;
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			if(StringUtils.isNotEmpty(avd)){
				urlRequestParamsKeys.append("&avd=" + avd);
				model.put("avd", avd);
			}
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("FETCH av list... ");
	    	logger.info("URL: " + BASE_URL_FETCH);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
			String jsonPayloadFetch = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys.toString());
			
			//Debug --> 
	    	//logger.info(jsonPayloadFetch);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	JsonTdsImportTopicListExternalRefContainer container = this.tdsImportTopicListService.getTdsImportTopicListExternalRefContainer(jsonPayloadFetch);
	    	//drop downs populated from back-end
	    	this.setDomainObjectsForListExternalRefInView(model, container);
			
	    	successView.addObject("model",model);
			
	    	logger.info("END of method");
	    	return successView;
		}
		
	}
	/**
	 * 
	 * @param model
	 * @param container
	 */
	private void setDomainObjectsForListExternalRefInView(Map model, JsonTdsImportTopicListExternalRefContainer container){
		List list = new ArrayList();
		if(container!=null){
			for (JsonTdsImportTopicListExternalRefRecord record : container.getExtList()){
				//this.adjustDatesOnFetch(record);
				list.add(record);
			}
		}
		model.put(TdsConstants.DOMAIN_LIST_EXTERNAL_REF, list);
		
	}
	
	/**
	 * 
	 * @param appUser
	 * @param codeType
	 * @return
	 */
	private List<JsonTdsCodeRecord> getCodeList(SystemaWebUser appUser, String typeCode, String ieMode){
		List<JsonTdsCodeRecord> list = new ArrayList<JsonTdsCodeRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = TdsUrlDataStore.TDS_CODES_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser() + "&ie=" + ieMode);
		urlRequestParams.append("&typ=" + typeCode);
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams);
		
		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		JsonTdsCodeContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.tdsGeneralCodesChildWindowService.getCodeContainer(jsonPayload);
				if(container!=null){
					for(JsonTdsCodeRecord  record : container.getKodlista()){
						list.add(record);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * KLI = Kollislag           IE = A
	 * GCY = Landkod 			 IE = A
	 * MDX = Valutakod			 IE = A
	 * MCF = Bilagda Handlingar  IE = A
	 * ADD = Tilläggskod imp     IE = A
	 * FFK = Förfarande :2 exp   IE = A
	 * FF1 = Förfarande :1 imp	 IE = I 
	 * SAL = Särsk. uppl kod     IE = I,E
	 * FOR = Förmånskod imp      IE = I
	 * THO = Tid.handl. TYP       IE = A
	 * CHN = Avgiftskoder 		 IE = A
	 * 
	 * @param typeCode
	 * @return
	 */
	private String getIeMode(String typeCode){
		final String IE_MODE_A = "A"; //ALL
		final String IE_MODE = "I"; //Import
		
		String retval = "UNKNOWN";
		if(typeCode!=null){
			if("KLI".equals(typeCode) || "GCY".equals(typeCode) || "MDX".equals(typeCode) || "MCF".equals(typeCode) || "ADD".equals(typeCode) || "FFK".equals(typeCode) ||  
				"THO".equals(typeCode) ||	"CHN".equals(typeCode) || "CHA".equals(typeCode)){
					retval = IE_MODE_A;
			}else if("FF1".equals(typeCode) || "SAL".equals(typeCode) || "FOR".equals(typeCode)){
					retval = IE_MODE;
			}
		}
		
		return  retval;
	}
	

	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	
	@Qualifier 
	private TdsGeneralCodesChildWindowService tdsGeneralCodesChildWindowService;
	@Autowired
	@Required	
	public void setTdsGeneralCodesChildWindowService(TdsGeneralCodesChildWindowService value){this.tdsGeneralCodesChildWindowService = value;}
	public TdsGeneralCodesChildWindowService getTdsGeneralCodesChildWindowService(){ return this.tdsGeneralCodesChildWindowService; }
	
	
	@Qualifier 
	private TdsTaricVarukodService tdsTaricVarukodService;
	@Autowired
	@Required	
	public void setTdsTaricVarukodService(TdsTaricVarukodService value){this.tdsTaricVarukodService = value;}
	public TdsTaricVarukodService getTdsTaricVarukodService(){ return this.tdsTaricVarukodService; }
	
	
	@Qualifier ("tdsImportTopicListService")
	private TdsImportTopicListService tdsImportTopicListService;
	@Autowired
	@Required
	public void setTdsImportTopicListService (TdsImportTopicListService value){ this.tdsImportTopicListService = value; }
	public TdsImportTopicListService getTdsImportTopicListService(){ return this.tdsImportTopicListService; }
	
	
}

