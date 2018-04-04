package no.systema.tds.z.maintenance.tdsexport.controller;

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
import no.systema.main.model.SystemaWebUser;

import no.systema.tds.z.maintenance.main.model.MaintenanceMainListObject;
import no.systema.tds.z.maintenance.main.util.TdsMaintenanceConstants;

/**
 * TDS Maintenance NCTS Import Topic Controller 
 * 
 * @author oscardelatorre
 * @date Juni 22, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintTdsExportController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintTdsExportController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsmaintenanceexport.do", method=RequestMethod.GET)
	public ModelAndView doSkatImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tdsmaintenanceexport");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_MAINTENANCE_EXPORT);
			session.setAttribute(TdsMaintenanceConstants.ACTIVE_URL_RPG_TDS_MAINTENANCE, TdsMaintenanceConstants.ACTIVE_URL_RPG_INITVALUE); 
		
			//lists
			List list = this.populateMaintenanceMainList();
			//init the rest
			model.put("list", list);
			successView.addObject(TdsMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	/**
	 * 
	 * @return
	 */
	private List<MaintenanceMainListObject> populateMaintenanceMainList(){
		List<MaintenanceMainListObject> listObject = new ArrayList<MaintenanceMainListObject>();
		MaintenanceMainListObject object = new  MaintenanceMainListObject();
	
		object.setId("1");
		object.setSubject("Underhåll av kunders varuregister");
		object.setCode("mainmaintenancecundf");
		object.setText("VKUND / CUNDF, CUNDC, FRATXT, SYPARF, CUNDMAF,.... ");
		object.setDbTable("CUNDF...");
		object.setStatus("G");
		object.setPgm("tillGeneralKundersvareregExport_SE_GUI");
		listObject.add(object);
		//
		
		return listObject;
	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	

}

