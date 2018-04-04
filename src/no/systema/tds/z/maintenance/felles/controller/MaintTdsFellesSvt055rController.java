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

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;

//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.StringManager;
import no.systema.main.model.SystemaWebUser;
import no.systema.z.main.maintenance.model.JsonMaintMainEdiiContainer;
import no.systema.z.main.maintenance.model.JsonMaintMainEdiiRecord;
import no.systema.z.main.maintenance.model.JsonMaintMainQaokp08aContainer;
import no.systema.z.main.maintenance.model.JsonMaintMainQaokp08aRecord;
import no.systema.z.main.maintenance.service.MaintMainEdiiService;
import no.systema.z.main.maintenance.service.MaintMainQaokp08aService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.tds.z.maintenance.main.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtfiContainer;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtfiRecord;
import no.systema.tds.z.maintenance.main.service.MaintSvtfiService;
import no.systema.tds.z.maintenance.main.service.html.dropdown.TdsMaintMainDropDownListPopulationService;
import no.systema.tds.z.maintenance.main.url.store.MaintenanceUrlDataStore;
import no.systema.tds.z.maintenance.main.util.TdsMaintenanceConstants;
import no.systema.tds.z.maintenance.felles.validator.MaintTdsFellesSvt055rValidator;


/**
 *  TDS Maintenance Felles Svt055 Controller 
 * 
 * @author oscardelatorre
 * @date Maj 03, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintTdsFellesSvt055rController {
	
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintTdsFellesSvt055rController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
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
	@RequestMapping(value="tdsmaintenancefelles_svt055r.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doSadMaintImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tdsmaintenancefelles_svt055r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		String dbTable = request.getParameter("id");
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//get table
	    	List<JsonMaintSvtfiRecord> list = new ArrayList();
	    	list = this.fetchList(appUser.getUser(), model);
	    	//drop downs
	    	//List codeList022 = this.fetchListKoder(appUser.getUser(), this.SKAT_IMPORT_SUPPL_ENHETER_KODE);
	    	//List codeListToldsatstype = this.skatMaintMainDropDownListPopulationService.getToldsatstypeList();
	    	
	    	//set domain objets
	    	model.put("dbTable", dbTable);
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
	
	@RequestMapping(value="tdsmaintenancefelles_svt055r_edit.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doTdsmaintenanceFellesEdit(@ModelAttribute ("record") JsonMaintSvtfiRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tdsmaintenancefelles_svt055r");
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
			MaintTdsFellesSvt055rValidator validator = new MaintTdsFellesSvt055rValidator();
			if(TdsMaintenanceConstants.ACTION_DELETE.equals(action)){
				validator.validateDelete(recordToValidate, bindingResult);
			}else{
				//extra criteria
				this.validateEdiiChildren (recordToValidate, appUser.getUser());
				this.validateOsUsers(recordToValidate, appUser.getUser());
				//validate
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
				logger.info(recordToValidate.getSvtf_0004());
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
				//TODO
			}
			//fetch the newly updated record if valid
			List<JsonMaintSvtfiRecord> list = null;
			if(isValidRecord){
				list = this.fetchList(appUser.getUser(), model);
			}
			//drop downs TODO
			logger.info(recordToValidate.getSvtf_0004());
	    	//set domain objets
	    	model.put("dbTable", dbTable);
	    	//model.put(SkatMaintenanceConstants.DOMAIN_LIST, list);
			successView.addObject(TdsMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}

	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @param startDato
	 * @return
	 */
	private List<JsonMaintSvtfiRecord> fetchList(String applicationUser, Map model){
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_SVT055R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser);

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintSvtfiRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSvtfiContainer container = this.maintSvtfiService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getDtoList();
	        	for(JsonMaintSvtfiRecord record : list){
	        		//put record since this is the only one in whole table
	        		model.put(TdsMaintenanceConstants.DOMAIN_RECORD, record);
	        		model.put("updateId", record.getSvtf_0004());
	        	}
	        }
    	}
    	return list;
    	
	}
	
	
	
	
	/**
	 * UPDATE/CREATE/DELETE
	 * 
	 * @param applicationUser
	 * @param record
	 * @param mode
	 * @return
	 */
	private int updateRecord(String applicationUser, JsonMaintSvtfiRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_SVT055R_DML_UPDATE_URL;
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
    		JsonMaintSvtfiContainer container = this.maintSvtfiService.doUpdate(jsonPayload);
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
	private void validateEdiiChildren(JsonMaintSvtfiRecord recordToValidate, String applicationUser){
		this.getEdiiList(recordToValidate, applicationUser, recordToValidate.getSvtf_0004(), this.ID_INTERNAL, ID_INFO_SENDER);
		this.getEdiiList(recordToValidate, applicationUser, recordToValidate.getSvtf_0010(), this.ID_EXTERNAL, ID_INFO_RECEIVER);
		
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param applicationUser
	 * @param id
	 * @param offset
	 * @param unbParty
	 */
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
	
	/**
	 * 
	 * @param recordToValidate
	 * @param applicationUser
	 */
	private void validateOsUsers(JsonMaintSvtfiRecord recordToValidate, String applicationUser){
		
		Collection<JsonMaintMainQaokp08aRecord> list = new ArrayList<JsonMaintMainQaokp08aRecord>();
		//prepare the access CGI with RPG back-end
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYQAOKP08A_GET_LIST_URL;
		String urlRequestParamsKeys = this.getRequestUrlKeyParametersForSearchOsUsers(applicationUser, recordToValidate.getSvtf_usri());
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//debugger
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonMaintMainQaokp08aContainer container = this.maintMainQaokp08aService.getList(jsonPayload);
    		if(container!=null){
    			list = container.getList();
    			for(JsonMaintMainQaokp08aRecord  record : list){
    				if( record.getWos8dden().equals(recordToValidate.getSvtf_usri()) && 
						record.getWos8ddgn().equals(recordToValidate.getSvtf_usra()) 	){
    					//set flag
    					recordToValidate.setValidSmsUserId(true);
    					
    				}
    			}
    		}
    	}
			
	}
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	private String getRequestUrlKeyParametersForSearchOsUsers(String applicationUser, String id){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  if(id!=null && !"".equals(id) ){
			  sb.append( TdsMaintenanceConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wos8dden=" + id.toUpperCase() );
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
	
	
	@Qualifier ("maintSvtfiService")
	private MaintSvtfiService maintSvtfiService;
	@Autowired
	@Required
	public void setMaintSvtfiService (MaintSvtfiService value){ this.maintSvtfiService = value; }
	public MaintSvtfiService getMaintSvtfiService(){ return this.maintSvtfiService; }
	
	
	@Qualifier ("tdsMaintMainDropDownListPopulationService")
	private TdsMaintMainDropDownListPopulationService tdsMaintMainDropDownListPopulationService;
	@Autowired
	@Required
	public void setTdsMaintMainDropDownListPopulationService (TdsMaintMainDropDownListPopulationService value){ this.tdsMaintMainDropDownListPopulationService = value; }
	public TdsMaintMainDropDownListPopulationService getTdsMaintMainDropDownListPopulationService(){ return this.tdsMaintMainDropDownListPopulationService; }
	
	
	@Qualifier 
	private MaintMainEdiiService maintMainEdiiService;
	@Autowired
	@Required	
	public void setMaintMainEdiiService(MaintMainEdiiService value){this.maintMainEdiiService = value;}
	public MaintMainEdiiService getMaintMainEdiiService(){ return this.maintMainEdiiService; }
	
	
	@Qualifier ("maintMainQaokp08aService")
	private MaintMainQaokp08aService maintMainQaokp08aService;
	@Autowired
	@Required
	public void setMaintMainQaokp08aService (MaintMainQaokp08aService value){ this.maintMainQaokp08aService = value; }
	public MaintMainQaokp08aService getMaintMainQaokp08aService(){ return this.maintMainQaokp08aService; }
	
	
	
}

