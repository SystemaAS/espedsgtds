package no.systema.tds.z.maintenance.tdsncts.controller;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import no.systema.main.model.SystemaWebUser;
import no.systema.tds.z.maintenance.main.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tds.z.maintenance.main.url.store.MaintenanceUrlDataStore;
import no.systema.tds.z.maintenance.main.util.TdsMaintenanceConstants;
//
import no.systema.tds.z.maintenance.tdsncts.service.MaintSvxkodfService;
import no.systema.tds.z.maintenance.tdsncts.model.jsonjackson.dbtable.JsonMaintSvxkodfContainer;
import no.systema.tds.z.maintenance.tdsncts.model.jsonjackson.dbtable.JsonMaintSvxkodfRecord;
import no.systema.tds.z.maintenance.tdsncts.validator.MaintTdsMainSvx001rValidator;



/**
 * TDS Maintenance NCTS Export Topic Controller 
 * 
 * @author oscardelatorre
 * @date Jun 2, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintTdsNctsKoderSvx001rController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintTdsNctsKoderSvx001rController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsmaintenancenctsexport_svx001r.do", method=RequestMethod.GET)
	public ModelAndView doTdsMaintNctsExportList_svx001r(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tdsmaintenancenctsexport_svx001r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		String dbTable = request.getParameter("id");
		String tkunik = request.getParameter("tkunik");
		String legend = request.getParameter("legend");
		
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_MAINTENANCE_NCTS_EXPORT);
			session.setAttribute(TdsMaintenanceConstants.ACTIVE_URL_RPG_TDS_MAINTENANCE, TdsMaintenanceConstants.ACTIVE_URL_RPG_INITVALUE); 
		
			//lists
			List list = this.fetchList(appUser.getUser(), tkunik);
			//set domain objects
			model.put("dbTable", dbTable);
			model.put("legend", legend);
			model.put("tkunik", tkunik);
			model.put("list", list);
			successView.addObject(TdsMaintenanceConstants.DOMAIN_MODEL , model);
			//successView.addObject(TvinnSadConstants.DOMAIN_LIST,new ArrayList());
			
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
	
	@RequestMapping(value="tdsmaintenancenctsexport_svx001r_edit.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doTdsMaintNctsExportList_svx001r_edit(@ModelAttribute ("record") JsonMaintSvxkodfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tdsmaintenancenctsexport_svx001r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String dbTable = request.getParameter("id");
		String updateId = request.getParameter("updateId");
		String action = request.getParameter("action");
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//Move on
			MaintTdsMainSvx001rValidator validator = new MaintTdsMainSvx001rValidator();
			if(TdsMaintenanceConstants.ACTION_DELETE.equals(action)){
				validator.validateDelete(recordToValidate, bindingResult);
			}else{
				validator.validate(recordToValidate, bindingResult);
			}
			if(bindingResult.hasErrors()){
				//ERRORS
				logger.info("[ERROR Validation] Record does not validate)");
				model.put("dbTable", dbTable);
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
				//this in order to present the complete list to the end user after a DML-operation
				//recordToValidate.setDkvk_kd(null);
			}
			List<JsonMaintSvxkodfRecord> list = this.fetchList(appUser.getUser(), recordToValidate.getTkunik());
	    	//set domain objets
	    	model.put("dbTable", dbTable);
	    	model.put("tkunik", recordToValidate.getTkunik());
	    	model.put(TdsMaintenanceConstants.DOMAIN_LIST, list);
			successView.addObject(TdsMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	
	}
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsmaintenancenctsimport_svx001r.do", method=RequestMethod.GET)
	public ModelAndView doTdsMaintNctsImportList_svx001r(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tdsmaintenancenctsimport_svx001r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		String dbTable = request.getParameter("id");
		String tkunik = request.getParameter("tkunik");
		String legend = request.getParameter("legend");
		
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_MAINTENANCE_NCTS_IMPORT);
			session.setAttribute(TdsMaintenanceConstants.ACTIVE_URL_RPG_TDS_MAINTENANCE, TdsMaintenanceConstants.ACTIVE_URL_RPG_INITVALUE); 
		
			//lists
			List list = this.fetchList(appUser.getUser(), tkunik);
			//set domain objects
			model.put("dbTable", dbTable);
			model.put("legend", legend);
			model.put("tkunik", tkunik);
			model.put("list", list);
			successView.addObject(TdsMaintenanceConstants.DOMAIN_MODEL , model);
			//successView.addObject(TvinnSadConstants.DOMAIN_LIST,new ArrayList());
			
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
	
	@RequestMapping(value="tdsmaintenancenctsimport_svx001r_edit.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doTdsMaintNctsImportList_svx001r_edit(@ModelAttribute ("record") JsonMaintSvxkodfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tdsmaintenancenctsimport_svx001r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String dbTable = request.getParameter("id");
		String updateId = request.getParameter("updateId");
		String action = request.getParameter("action");
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//Move on
			MaintTdsMainSvx001rValidator validator = new MaintTdsMainSvx001rValidator();
			if(TdsMaintenanceConstants.ACTION_DELETE.equals(action)){
				validator.validateDelete(recordToValidate, bindingResult);
			}else{
				validator.validate(recordToValidate, bindingResult);
			}
			if(bindingResult.hasErrors()){
				//ERRORS
				logger.info("[ERROR Validation] Record does not validate)");
				model.put("dbTable", dbTable);
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
				//this in order to present the complete list to the end user after a DML-operation
				//recordToValidate.setDkvk_kd(null);
			}
			List<JsonMaintSvxkodfRecord> list = this.fetchList(appUser.getUser(), recordToValidate.getTkunik());
	    	//set domain objets
	    	model.put("dbTable", dbTable);
	    	model.put("tkunik", recordToValidate.getTkunik());
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
	private List<JsonMaintSvxkodfRecord> fetchList(String applicationUser, String tkunik){
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_SVX001R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		//mandatory params
		urlRequestParams.append("user="+ applicationUser);
		urlRequestParams.append("&tkunik=" + tkunik);
		
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintSvxkodfRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSvxkodfContainer container = this.maintSvxkodfService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSvxkodfRecord record : list){
	        		//logger.info("TGGNR:" + record.getTggnr());
	        	}
	        }
    	}
    	return list;
    	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param record
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private int updateRecord(String applicationUser, JsonMaintSvxkodfRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_SVX001R_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&mode=" + mode;
		String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((record));
		//put the final valid param. string
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	logger.info(jsonPayload);
    	
    	//extract
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSvxkodfContainer container = this.maintSvxkodfService.doUpdate(jsonPayload);
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
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("maintSvxkodfService")
	private MaintSvxkodfService maintSvxkodfService;
	@Autowired
	@Required
	public void setMaintSvxkodfService (MaintSvxkodfService value){ this.maintSvxkodfService = value; }
	public MaintSvxkodfService getMaintSvxkodfService(){ return this.maintSvxkodfService; }
	
	

}

