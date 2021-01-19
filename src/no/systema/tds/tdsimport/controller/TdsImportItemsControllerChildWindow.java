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
import no.systema.tds.tdsimport.service.TdsImportSpecificTopicItemService;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportTulltaxaKundensRegisterVarukodContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportTulltaxaKundensRegisterVarukodRecord;
import no.systema.tds.tdsimport.url.store.TdsImportUrlDataStore;

import no.systema.tds.url.store.TdsUrlDataStore;
import no.systema.tds.util.TdsConstants;
import no.systema.tds.util.manager.CodeDropDownMgr;




/**
 * Tds Import Items Controller - child windows popup
 * 
 * @author oscardelatorre
 * @date Jan 15, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TdsImportItemsControllerChildWindow {
	
	private static final Logger logger = Logger.getLogger(TdsImportItemsControllerChildWindow.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	//customer
	private final String DATATABLE_LIST = "list";

	
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
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
	@RequestMapping(value="tdsimport_edit_items_childwindow_generalcodes", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitGeneralCodes(@ModelAttribute ("record") JsonTdsCodeContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitCodes");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		String typeCode = request.getParameter("type");
		String ie = this.getIeMode(typeCode);
		
		ModelAndView successView = new ModelAndView("tdsimport_edit_items_childwindow_generalcodes");
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
	@RequestMapping(value="tdsimport_edit_items_childwindow_tulltaxa", params="action=doInit",  method={RequestMethod.GET, RequestMethod.POST } )
	public ModelAndView doInitTulltaxa(@ModelAttribute ("record") JsonTdsTaricVarukodContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitTulltaxa");
		Map model = new HashMap();
		String varuKod = request.getParameter("vkod");
		String text = request.getParameter("tekst");
		String caller = request.getParameter("caller");  //Field in jsp
		
		String ieMode = "I";
		
		ModelAndView successView = new ModelAndView("tdsimport_edit_items_childwindow_tulltaxa");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			  
			List<JsonTdsTaricVarukodRecord> list = new ArrayList();
			if(text!=null && !"".equals(text)){
				list = this.getTulltaxaListFromDesc(appUser, text, ieMode);
				model.put("tekst", text);
			}else{
				list = this.getTulltaxaList(appUser, varuKod, ieMode);
				model.put("vkod", varuKod);
			}
			model.put("tullTaxaList", list);
			model.put("caller", caller);
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
	@RequestMapping(value="tdsimport_edit_items_childwindow_kundensvarereg", params="action=doInit",  method={RequestMethod.GET, RequestMethod.POST } )
	public ModelAndView doInitKundVareReg(@ModelAttribute ("record") JsonTdsImportTulltaxaKundensRegisterVarukodContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitKundVareReg");
		Map model = new HashMap();
		String receiverId = request.getParameter("recId");
		
		ModelAndView successView = new ModelAndView("tdsimport_edit_items_childwindow_kundensvarereg");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			List<JsonTdsImportTulltaxaKundensRegisterVarukodRecord> list = new ArrayList();
			list = this.getKundVareRegList(appUser, receiverId);
			
			model.put("kundensVareRegList", list);
			successView.addObject(TdsConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	/**
	 * Get varukoder from tulltaxa
	 * 
	 * @param appUser
	 * @param tulltaxaVarukod
	 * @param ieMode
	 * @return
	 */
	private List<JsonTdsTaricVarukodRecord> getTulltaxaList(SystemaWebUser appUser, String tulltaxaVarukod, String ieMode){
		List<JsonTdsTaricVarukodRecord> list = new ArrayList<JsonTdsTaricVarukodRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = TdsUrlDataStore.TDS_FETCH_TARIC_VARUKODER_ITEMS_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser() + "&ie=" + ieMode);
		urlRequestParams.append("&kod=" + tulltaxaVarukod);
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams);
		
		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info(jsonPayload);
		JsonTdsTaricVarukodContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.tdsTaricVarukodService.getContainer(jsonPayload);
				if(container!=null){
					for(JsonTdsTaricVarukodRecord  record : container.getTullTaxalist()){
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
	 * 
	 * @param appUser
	 * @param description
	 * @param ieMode
	 * @return
	 */
	private List<JsonTdsTaricVarukodRecord> getTulltaxaListFromDesc(SystemaWebUser appUser, String description, String ieMode){
		List<JsonTdsTaricVarukodRecord> list = new ArrayList<JsonTdsTaricVarukodRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = TdsUrlDataStore.TDS_FETCH_TARIC_VARUKODER_ITEMS_FROM_DESC_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser() + "&ie=" + ieMode);
		urlRequestParams.append("&sok=" + description);
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams);
		
		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info(jsonPayload);
		JsonTdsTaricVarukodContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.tdsTaricVarukodService.getContainer(jsonPayload);
				if(container!=null){
					for(JsonTdsTaricVarukodRecord  record : container.getTtaxalfasok()){
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
	 * 
	 * @param appUser
	 * @param receiverId
	 * @return
	 */
	private List<JsonTdsImportTulltaxaKundensRegisterVarukodRecord> getKundVareRegList(SystemaWebUser appUser, String receiverId){
		List<JsonTdsImportTulltaxaKundensRegisterVarukodRecord> list = new ArrayList<JsonTdsImportTulltaxaKundensRegisterVarukodRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_FETCH_TULLTAXA_KUNDENSVAREREG_VARUKODER_ITEMS_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&levenr=" + receiverId);
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams);
		
		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info(jsonPayload);
		JsonTdsImportTulltaxaKundensRegisterVarukodContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.tdsImportSpecificTopicItemService.getKundRegisterVarukodContainer(jsonPayload);
				if(container!=null){
					for(JsonTdsImportTulltaxaKundensRegisterVarukodRecord  record : container.getKundvarlist()){
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
		//remove duplicates if applicable
		if("FOR".equals(typeCode) || "FF1".equals(typeCode) ){
			list = this.codeDropDownMgr.getNoneDuplicatesList(list);
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
		final String IE_MODE_E = "E"; //Export
		final String IE_MODE_I = "I"; //Import
		
		String retval = "UNKNOWN";
		if(typeCode!=null){
			if("KLI".equals(typeCode) || "GCY".equals(typeCode) || "MDX".equals(typeCode) || "MCF".equals(typeCode) || "ADD".equals(typeCode) || "FFK".equals(typeCode) ||  
				"THO".equals(typeCode) ||	"CHN".equals(typeCode) || "CHA".equals(typeCode)){
					retval = IE_MODE_A;
			}else if("FF1".equals(typeCode) || "SAL".equals(typeCode) || "FOR".equals(typeCode)){
					retval = IE_MODE_I;
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
	
	
	private TdsImportSpecificTopicItemService tdsImportSpecificTopicItemService;
	@Autowired
	@Required	
	public void setTdsImportSpecificTopicItemService(TdsImportSpecificTopicItemService value){this.tdsImportSpecificTopicItemService = value;}
	public TdsImportSpecificTopicItemService getTdsImportSpecificTopicItemService(){ return this.tdsImportSpecificTopicItemService; }
	
	
}

