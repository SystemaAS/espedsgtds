package no.systema.tds.z.maintenance.tdsncts.controller.ajax;

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
import no.systema.tds.z.maintenance.main.url.store.MaintenanceUrlDataStore;
import no.systema.tds.z.maintenance.tdsncts.service.MaintSvxkodfService;
import no.systema.tds.z.maintenance.tdsnctsexport.model.jsonjackson.dbtable.JsonMaintSvxghContainer;
import no.systema.tds.z.maintenance.tdsnctsexport.model.jsonjackson.dbtable.JsonMaintSvxghRecord;
import no.systema.tds.z.maintenance.tdsnctsexport.service.MaintSvxghService;
import no.systema.tds.z.maintenance.tdsncts.model.jsonjackson.dbtable.JsonMaintSvxkodfContainer;
import no.systema.tds.z.maintenance.tdsncts.model.jsonjackson.dbtable.JsonMaintSvxkodfRecord;


/**
 * Maintenance Ajax Controller 
 * 
 * @author oscardelatorre
 * @date Jun 22, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintTdsNctsAjaxHandlerController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintTdsNctsAjaxHandlerController.class.getName());
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="getSpecificRecord_svx030r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSvxghRecord> getRecordSvx030
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getRecordSvx030r";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSvxghRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListSvx030(applicationUser, id);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param tkunik
	 * @param tkkode
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_svx001r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSvxkodfRecord> getRecordSvx001r
	  	(@RequestParam String applicationUser, @RequestParam String tkunik, @RequestParam String tkkode ) {
		final String METHOD = "[DEBUG] getRecordSvx001r";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSvxkodfRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListSvx001r(applicationUser, tkunik, tkkode);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	
	private Collection<JsonMaintSvxghRecord> fetchListSvx030(String applicationUser, String id){
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_SVX030R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&tggnr=" + id + "&om=1" ; //OneMatch ...
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSvxghRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSvxghContainer container = this.maintSvxghService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSvxghRecord record: list){
	        		//logger.info(record.getTggnr());
	        	}
	        }
    	}
    	
    	return list;
    	
	}
	/**
	 * 
	 * @param applicationUser
	 * @param tkunik
	 * @param tkkode
	 * @return
	 */
	private Collection<JsonMaintSvxkodfRecord> fetchListSvx001r(String applicationUser, String tkunik, String tkkode){
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_SVX001R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&tkunik=" + tkunik + "&tkkode=" + tkkode ; 
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSvxkodfRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSvxkodfContainer container = this.maintSvxkodfService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSvxkodfRecord record: list){
	        		//logger.info(record.getTggnr());
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
	
	
	@Qualifier ("maintSvxghService")
	private MaintSvxghService maintSvxghService;
	@Autowired
	@Required
	public void setMaintSvxghService (MaintSvxghService value){ this.maintSvxghService = value; }
	public MaintSvxghService getMaintSvxghService(){ return this.maintSvxghService; }
	
	
	@Qualifier ("maintSvxkodfService")
	private MaintSvxkodfService maintSvxkodfService;
	@Autowired
	@Required
	public void setMaintSvxkodfService (MaintSvxkodfService value){ this.maintSvxkodfService = value; }
	public MaintSvxkodfService getMaintSvxkodfService(){ return this.maintSvxkodfService; }
	
	
}

