package no.systema.tds.z.maintenance.felles.controller;

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

//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.StringManager;

import no.systema.main.model.SystemaWebUser;
import no.systema.z.main.maintenance.service.MaintMainKodtsfSyparfService;
import no.systema.tds.z.maintenance.main.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtvkContainer;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtvkRecord;
import no.systema.tds.z.maintenance.main.service.MaintSvtvkService;
import no.systema.tds.z.maintenance.main.service.html.dropdown.TdsMaintMainDropDownListPopulationService;
import no.systema.tds.z.maintenance.main.url.store.MaintenanceUrlDataStore;
import no.systema.tds.z.maintenance.main.util.TdsMaintenanceConstants;
import no.systema.tds.z.maintenance.felles.validator.MaintTdsFellesSvt057rValidator;



/**
 *  TDS Maintenance Felles Svt057 - Valutakurser Controller 
 * 
 * @author oscardelatorre
 * @date Maj 19, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintTdsFellesSvt057rController {
	
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintTdsFellesSvt057rController.class.getName());
	private final StringManager strMgr = new StringManager();
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private String SKAT_IMPORT_SUPPL_ENHETER_KODE = "022"; //We use the ones in Skat-Import
	private final String ID_INTERNAL = "I";
	private final String ID_EXTERNAL = "E";
	private final String ID_INFO_SENDER = "senderId";
	private final String ID_INFO_RECEIVER = "receiverId";
	
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsmaintenancefelles_svt057r.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tdsmaintenancefelles_svt057r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		String dbTable = request.getParameter("id");
		String id = request.getParameter("searchKode");
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//get table
	    	List<JsonMaintSvtvkRecord> list = new ArrayList();
	    	list = this.fetchList(appUser.getUser(), id);
	    	//set domain objets
	    	model.put("dbTable", dbTable);
	    	model.put("searchKode", id);
	    	model.put(TdsMaintenanceConstants.DOMAIN_LIST, list);
	    	successView.addObject(TdsMaintenanceConstants.DOMAIN_MODEL , model);
			
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
	@RequestMapping(value="tdsmaintenancefelles_svt057r_edit.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doTdsmaintenanceFellesEdit(@ModelAttribute ("record") JsonMaintSvtvkRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tdsmaintenancefelles_svt057r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		boolean isValidRecord = true;
		String dbTable = request.getParameter("id");
		String updateId = request.getParameter("updateId");
		String action = request.getParameter("action");
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			
			//Move on
			MaintTdsFellesSvt057rValidator validator = new MaintTdsFellesSvt057rValidator();
			if(TdsMaintenanceConstants.ACTION_DELETE.equals(action)){
				validator.validateDelete(recordToValidate, bindingResult);
			}else{
				//check validity of signature
				//this.validateSignature(appUser.getUser(), recordToValidate);
				//only with create new
				if(TdsMaintenanceConstants.ACTION_UPDATE.equals(action)){
					if (updateId==null || "".equals(updateId)){
						//this.isDuplicateSignature(appUser.getUser(), recordToValidate);
					}
				}
				//now validate
				validator.validate(recordToValidate, bindingResult);
			}
			
			if(bindingResult.hasErrors()){
				//ERRORS
				logger.info("[ERROR Validation] Record does not validate)");
				model.put("dbTable", dbTable);
				isValidRecord = false;
				if(updateId!=null && !"".equals(updateId)){
					//meaning bounced in an Update and not a Create new
					model.put("updateId", updateId);
					
				}
				model.put(TdsMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
			}else{
				
				//------------
				//UPDATE table
				//------------
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				//UPDATE
				if (TdsMaintenanceConstants.ACTION_UPDATE.equals(action) ){
					if(updateId!=null && !"".equals(updateId)){
						//update
						logger.info(TdsMaintenanceConstants.ACTION_UPDATE);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, TdsMaintenanceConstants.MODE_UPDATE, errMsg);
						
					//CREATE
					}else{
						//create new
						logger.info(TdsMaintenanceConstants.ACTION_CREATE);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, TdsMaintenanceConstants.MODE_ADD, errMsg);
					}
				}else if(TdsMaintenanceConstants.ACTION_DELETE.equals(action) ){
					//delete
					logger.info(TdsMaintenanceConstants.ACTION_DELETE);
					dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, TdsMaintenanceConstants.MODE_DELETE, errMsg);
				}
				//check for Update errors
				if( dmlRetval < 0){
					logger.info("[ERROR Validation] Record does not validate)");
					model.put("dbTable", dbTable);
					model.put(TdsMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					model.put(TdsMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				}
				
			}
			//------------
			//FETCH table
			//------------
			if(TdsMaintenanceConstants.ACTION_DELETE.equals(action) || TdsMaintenanceConstants.ACTION_UPDATE.equals(action) ){
				//TODO ?
			}
			//fetch if valid
			List<JsonMaintSvtvkRecord> list = this.fetchList(appUser.getUser(), null);
			
			//set domain objects
	    	model.put("dbTable", dbTable);
	    	
	    	model.put(TdsMaintenanceConstants.DOMAIN_LIST, list);
			successView.addObject(TdsMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}

	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	private List<JsonMaintSvtvkRecord> fetchList(String applicationUser, String id){
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_SVT057R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser);
		if(strMgr.isNotNull(id)){
			urlRequestParams.append("&svvk_kd=" + id);
		}

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintSvtvkRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSvtvkContainer container = this.maintSvtvkService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getDtoList();
	        	for(JsonMaintSvtvkRecord record : list){
	        		//logger.info("KOD:" + record.getSvvk_kd() + "END");
	        	}
	        }
    	}
    	return list;
    	
	}
	/**
	 * 
	 * @param applicationUser
	 * @return
	 */
	/*
	private void isDuplicateSignature(String applicationUser, JsonMaintSvtvkRecord recordToValidate){
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_SVT056R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser + "&svth_sysg=" + recordToValidate.getSvth_sysg());
	
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintSvtvkRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSvthaContainer container = this.maintSvthaService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getDtoList();
	        	for(JsonMaintSvtvkRecord record : list){
	        		if(record.getSvth_sysg().equals(recordToValidate.getSvth_sysg())){
	        			recordToValidate.setDuplicateSignature(true);
	        		}
	        	}
	        }
    	}
	}
	*/
	
	/**
	 * UPDATE/CREATE/DELETE
	 * 
	 * @param applicationUser
	 * @param record
	 * @param mode
	 * @return
	 */
	private int updateRecord(String applicationUser, JsonMaintSvtvkRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_SVT057R_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&mode=" + mode;
		String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((record));
		//put the final valid param. string
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	
    	//extract
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSvtvkContainer container = this.maintSvtvkService.doUpdate(jsonPayload);
	        if(container!=null){
	        	if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
	        		if(container.getErrMsg().toUpperCase().startsWith("ERROR")){
	        			errMsg.append(container.getErrMsg());
	        			retval = TdsMaintenanceConstants.ERROR_CODE;
	        		}
	        	}
	        }
    	}
    	return retval;
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param applicationUser
	 */
	/*
	private void validateEdiiChildren(JsonMaintSvtfiRecord recordToValidate, String applicationUser){
		this.getEdiiList(recordToValidate, applicationUser, recordToValidate.getSvtf_0004(), this.ID_INTERNAL, ID_INFO_SENDER);
		this.getEdiiList(recordToValidate, applicationUser, recordToValidate.getSvtf_0010(), this.ID_EXTERNAL, ID_INFO_RECEIVER);
		
	}
	
	
	private void getEdiiList(JsonMaintSvtfiRecord recordToValidate, String applicationUser, String id, String offset, String unbParty){
		
		Collection<JsonMaintMainEdiiRecord> list = new ArrayList<JsonMaintMainEdiiRecord>();
		//prepare the access CGI with RPG back-end
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYEDIIR_GET_LIST_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&inid=" + id;
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//debugger
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		if(jsonPayload!=null){
			JsonMaintMainEdiiContainer container = this.maintMainEdiiService.getList(jsonPayload);
			if(container!=null){
				list = container.getList();
				for(JsonMaintMainEdiiRecord  result : list){
					if(offset.equals(result.getInex()) ){
						if(ID_INFO_SENDER.equals(unbParty)){ recordToValidate.setValidSenderId(true); }
						if(ID_INFO_RECEIVER.equals(unbParty)){ recordToValidate.setValidReceiverId(true); }
					}
				}
			}
		}
	}
	
	*/
	
	/**
	 * 
	 * @param applicationUser
	 * @param recordToValidate
	 * @param duplicateControl
	 */
	/*
	private void validateSignature(String applicationUser, JsonMaintSvtvkRecord recordToValidate ){
		
		if( (applicationUser!=null && !"".equals(applicationUser)) && (recordToValidate!=null) ){
			
			String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA60R_GET_LIST_URL;
			String urlRequestParams = "user=" + applicationUser;
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
	    	logger.info("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	    	//DEBUG
	    	//this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
	    	//extract
	    	List<JsonMaintMainKodtsfSyparfRecord> list = new ArrayList();
	    	if(jsonPayload!=null){
				//lists
	    		JsonMaintMainKodtsfSyparfContainer container = this.maintMainKodtsfSyparfService.getList(jsonPayload);
		        if(container!=null){
		        	list = (List)container.getList();
		        	if (list!=null){
						for(JsonMaintMainKodtsfSyparfRecord record : list){
							/*if( recordToValidate.getSvth_sy().equals(record.getKosfsi()) ){
								recordToValidate.setValidSignature(true);
								//logger.info("AAAAAA");
							
						}
					}
		        }
	    	}
	    	
		}
	}
*/
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("maintSvtvkService")
	private MaintSvtvkService maintSvtvkService;
	@Autowired
	@Required
	public void setMaintSvtvkService (MaintSvtvkService value){ this.maintSvtvkService = value; }
	public MaintSvtvkService getMaintSvtvkService(){ return this.maintSvtvkService; }
	
	
	@Qualifier ("tdsMaintMainDropDownListPopulationService")
	private TdsMaintMainDropDownListPopulationService tdsMaintMainDropDownListPopulationService;
	@Autowired
	@Required
	public void setTdsMaintMainDropDownListPopulationService (TdsMaintMainDropDownListPopulationService value){ this.tdsMaintMainDropDownListPopulationService = value; }
	public TdsMaintMainDropDownListPopulationService getTdsMaintMainDropDownListPopulationService(){ return this.tdsMaintMainDropDownListPopulationService; }
	
	
	@Qualifier ("maintMainKodtsfSyparfService")
	private MaintMainKodtsfSyparfService maintMainKodtsfSyparfService;
	@Autowired
	@Required
	public void setMaintMainKodtsfSyparfService (MaintMainKodtsfSyparfService value){ this.maintMainKodtsfSyparfService = value; }
	public MaintMainKodtsfSyparfService getMaintMainKodtsfSyparfService(){ return this.maintMainKodtsfSyparfService; }
	
	
	
}

