package no.systema.tds.z.maintenance.felles.controller.ajax;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;

//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;

import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtvkContainer;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtvkRecord;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvthaContainer;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvthaRecord;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtlvContainer;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtlvRecord;

import no.systema.tds.z.maintenance.main.service.MaintSvthaService;
import no.systema.tds.z.maintenance.main.service.MaintSvtvkService;
import no.systema.tds.z.maintenance.main.service.MaintSvtlvService;

import no.systema.tds.z.maintenance.main.url.store.MaintenanceUrlDataStore;



/**
 * Maintenance Ajax Controller 
 * 
 * @author oscardelatorre
 * @date May 09, 2017 
 * 
 */


@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintTdsFellesAjaxHandlerController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintTdsFellesAjaxHandlerController.class.getName());
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_svt057r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSvtvkRecord> getRecordDkt057
	  	(@RequestParam String applicationUser, @RequestParam String id, @RequestParam String date) {
		final String METHOD = "[DEBUG] getSpecificRecord_dkt057r";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSvtvkRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListSvt057(applicationUser, id, date);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_svt058r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSvtlvRecord> getRecordSvt058
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getSpecificRecord_svt058r";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSvtlvRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListSvt058(applicationUser, id);
    	
    	return result;
	
	}
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_svt056r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSvthaRecord> getRecordDkt056r
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getSpecificRecord_dktard";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSvthaRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListSvt056(applicationUser, id);
    	
    	return result;
	
	}

	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @param startDate
	 * @return
	 */
	private Collection<JsonMaintSvtvkRecord> fetchListSvt057(String applicationUser, String id, String startDate ){
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_SVT057R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&svvk_kd=" + id + "&svvk_dts=" + startDate;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSvtvkRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSvtvkContainer container = this.maintSvtvkService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getDtoList();
	        	for(JsonMaintSvtvkRecord record: list){
	        		//logger.info(record.getDkth_sysg());
	        	}
	        }
    	}
    	return list;
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	private Collection<JsonMaintSvthaRecord> fetchListSvt056(String applicationUser, String id){
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_SVT056R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&svth_sysg=" + id ;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSvthaRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSvthaContainer container = this.maintSvthaService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getDtoList();
	        	for(JsonMaintSvthaRecord record: list){
	        		//logger.info(record.getDkth_sysg());
	        	}
	        }
    	}
    	return list;
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	private Collection<JsonMaintSvtlvRecord> fetchListSvt058(String applicationUser, String id){
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_SVT058R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&svlv_kd=" + id ;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSvtlvRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSvtlvContainer container = this.maintSvtlvService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getDtoList();
	        	for(JsonMaintSvtlvRecord record: list){
	        		//logger.info(record.getSvlv_kd());
	        	}
	        }
    	}
    	return list;
	}
	

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
	
	
	@Qualifier ("maintSvtlvService")
	private MaintSvtlvService maintSvtlvService;
	@Autowired
	@Required
	public void setMaintSvtlvService (MaintSvtlvService value){ this.maintSvtlvService = value; }
	public MaintSvtlvService getMaintSvtlvService(){ return this.maintSvtlvService; }
	
	
	@Qualifier ("maintSvthaService")
	private MaintSvthaService maintSvthaService;
	@Autowired
	@Required
	public void setMaintSvthaService (MaintSvthaService value){ this.maintSvthaService = value; }
	public MaintSvthaService getMaintSvthaService(){ return this.maintSvthaService; }
	
	
	
}

