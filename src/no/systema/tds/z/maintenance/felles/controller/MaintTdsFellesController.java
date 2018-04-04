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
import no.systema.main.model.SystemaWebUser;

import no.systema.tds.z.maintenance.main.model.MaintenanceMainListObject;
import no.systema.tds.z.maintenance.main.util.TdsMaintenanceConstants;

/**
 * TDS Maintenance Felles gate Controller 
 * 
 * @author oscardelatorre
 * @date Maj 02, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintTdsFellesController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintTdsFellesController.class.getName());
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
	@RequestMapping(value="tdsmaintenancefelles.do", method=RequestMethod.GET)
	public ModelAndView doSkatImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tdsmaintenancefelles");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_MAINTENANCE_FELLES);
			session.setAttribute(TdsMaintenanceConstants.ACTIVE_URL_RPG_TDS_MAINTENANCE, TdsMaintenanceConstants.ACTIVE_URL_RPG_INITVALUE); 
		
			//lists
			List list = this.populateMaintenanceMainList();
			//this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
			//this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			//this.setCodeDropDownMgr(appUser, model);
			//init filter with users signature (for starters)
			//searchFilter.setSg(appUser.getTvinnSadSign());
			//successView.addObject("searchFilter" , searchFilter);
			//init the rest
			model.put("list", list);
			successView.addObject(TdsMaintenanceConstants.DOMAIN_MODEL , model);
			//successView.addObject(TvinnSadConstants.DOMAIN_LIST,new ArrayList());
			
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
		object.setSubject("Underhåll av firmaupplysningar");
		object.setCode("TDS_FIRMUPPL");
		object.setText("SVT055 / SVTFI");
		object.setDbTable("SVTFI");
		object.setStatus("G");
		object.setPgm("svt055r");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("2");
		object.setSubject("Underhåll av handläggare");
		object.setCode("TDS_HANDL");
		object.setText("SVT056 / SVTHA");
		object.setDbTable("SVTHA");
		object.setStatus("G");
		object.setPgm("svt056r");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("3");
		object.setSubject("Underhåll av valutakurser");
		object.setCode("TDS_VALUTA");
		object.setText("SVT057 / SVTVK");
		object.setDbTable("SVTVK");
		object.setStatus("G");
		object.setPgm("svt057r");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("4");
		object.setSubject("Underhåll av leveransvillkår");
		object.setCode("TDS_LEVVILLKAR");
		object.setText("SVT058 / SVTLV");
		object.setDbTable("SVTLV");
		object.setStatus("G");
		object.setPgm("svt058r");
		listObject.add(object);
		
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

