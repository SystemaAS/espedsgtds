package no.systema.tds.nctsexport.controller;

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
import no.systema.main.util.StringManager;

import no.systema.main.model.SystemaWebUser;

import no.systema.tds.model.jsonjackson.codes.JsonTdsNctsCodeContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsNctsCodeRecord;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTaricVarukodContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTaricVarukodRecord;
import no.systema.tds.tdsexport.model.jsonjackson.tullkontor.JsonTdsExportTullkontorContainer;
import no.systema.tds.tdsexport.model.jsonjackson.tullkontor.JsonTdsExportTullkontorRecord;

import no.systema.tds.tdsexport.service.TdsExportTullkontorService;
import no.systema.tds.service.TdsGeneralCodesChildWindowService;
import no.systema.tds.service.TdsTaricVarukodService;
import no.systema.tds.service.TdsTaricVarukodServiceImpl;

import no.systema.tds.url.store.TdsUrlDataStore;
import no.systema.tds.util.TdsConstants;




/**
 * Ncts Export Items Controller - child windows popup
 * 
 * @author oscardelatorre
 * @date Okt, 2018
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class NctsExportHeaderControllerChildWindow {
	private final StringManager strMgr = new StringManager();
	private static final Logger logger = Logger.getLogger(NctsExportHeaderControllerChildWindow.class.getName());
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
	@RequestMapping(value="nctsexport_edit_invoice_childwindow_generalcodes.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitInvoiceGeneralCodes(@ModelAttribute ("record") JsonTdsNctsCodeContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitGeneralCodes");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		String typeCode = request.getParameter("type");
		String ie = this.getIeMode(typeCode);
		
		ModelAndView successView = new ModelAndView("nctsexport_edit_invoice_childwindow_generalcodes");
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

	@RequestMapping(value="nctsexport_edit_childwindow_generalcodes.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitGeneralCodes(@ModelAttribute ("record") JsonTdsNctsCodeContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitGeneralCodes");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		String typeCode = request.getParameter("type");
		String ie = this.getIeMode(typeCode);
		
		ModelAndView successView = new ModelAndView("nctsexport_edit_childwindow_generalcodes");
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
	@RequestMapping(value="nctsexport_edit_childwindow_tullkontor", params="action=doInit",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doInitTullkontor(@ModelAttribute ("record") JsonTdsExportTullkontorRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitGeneralCodes");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		String code = request.getParameter("tkkode");
		String soName = request.getParameter("tktxtn");
		
		ModelAndView successView = new ModelAndView("nctsexport_edit_childwindow_tullkontor");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			  
			List list = this.getTullkontorList(appUser, soName, code);
			model.put("tullkontorList", list);
			model.put("callerType", callerType);
			model.put("tkkode", code);
			model.put("tktxtn", soName);
			
			successView.addObject(TdsConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	/**
	 * 
	 * @param appUser
	 * @param soName
	 * @param code
	 * @return
	 */
	private List<JsonTdsExportTullkontorRecord> getTullkontorList(SystemaWebUser appUser, String soName, String code){
		List<JsonTdsExportTullkontorRecord> list = new ArrayList<JsonTdsExportTullkontorRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = TdsUrlDataStore.TDS_FETCH_UTFARTS_TULLKONTOR_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		if(strMgr.isNotNull(soName) && strMgr.isNotNull(code)){
			urlRequestParams.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + soName );
			urlRequestParams.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kod=" + code );
		}else if (strMgr.isNotNull(soName)){
			urlRequestParams.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + soName );
		}else if (strMgr.isNotNull(code)){
			urlRequestParams.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kod=" + code );
		}
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams);
		
		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		JsonTdsExportTullkontorContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.tdsExportTullkontorService.getTdsExportTullkontorContainer(jsonPayload);
				if(container!=null){
					for(JsonTdsExportTullkontorRecord  record : container.getCustomslist()){
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
	private List<JsonTdsNctsCodeRecord> getCodeList(SystemaWebUser appUser, String typeCode, String ieMode){
		List<JsonTdsNctsCodeRecord> list = new ArrayList<JsonTdsNctsCodeRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		//Default
		String BASE_URL = TdsUrlDataStore.TDS_CODES_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser() + "&ie=" + ieMode);
		urlRequestParams.append("&typ=" + typeCode);
	
		if("UNKNOWN".equals(ieMode)){
			//NCTS-specific URL
			BASE_URL = TdsUrlDataStore.TDS_NCTS_CODES_URL;
			urlRequestParams = new StringBuffer();
			urlRequestParams.append("user=" + appUser.getUser());
			urlRequestParams.append("&typ=" + typeCode);
		}
		logger.info(BASE_URL);
		logger.info(urlRequestParams);
		
		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		JsonTdsNctsCodeContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.tdsGeneralCodesChildWindowService.getNctsCodeContainer(jsonPayload);
				if(container!=null){
					for(JsonTdsNctsCodeRecord  record : container.getKodlista()){
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
		final String IE_MODE = "E"; //Export
		
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
	
	
	@Qualifier 
	private TdsExportTullkontorService tdsExportTullkontorService;
	@Autowired
	@Required	
	public void setTdsExportTullkontorService(TdsExportTullkontorService value){this.tdsExportTullkontorService = value;}
	public TdsExportTullkontorService getTdsExportTullkontorService(){ return this.tdsExportTullkontorService; }
	
}

