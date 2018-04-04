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
 * TDS Maintenance NCTS Export Topic Controller 
 * 
 * @author oscardelatorre
 * @date Jun 22, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintTdsNctsKoderGateController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintTdsNctsKoderGateController.class.getName());
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
	@RequestMapping(value="tdsmaintenancenctsexport_kodergate.do", method=RequestMethod.GET)
	public ModelAndView doKoderGateExportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tdsmaintenancenctsexport_kodergate");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_MAINTENANCE_NCTS_EXPORT);
			session.setAttribute(TdsMaintenanceConstants.ACTIVE_URL_RPG_TDS_MAINTENANCE, TdsMaintenanceConstants.ACTIVE_URL_RPG_INITVALUE); 
		
			//lists
			List list = this.populateMaintenanceMainList();
			model.put("list", list);
			successView.addObject(TdsMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsmaintenancenctsimport_kodergate.do", method=RequestMethod.GET)
	public ModelAndView doKoderGateImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tdsmaintenancenctsimport_kodergate");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_MAINTENANCE_NCTS_IMPORT);
			session.setAttribute(TdsMaintenanceConstants.ACTIVE_URL_RPG_TDS_MAINTENANCE, TdsMaintenanceConstants.ACTIVE_URL_RPG_INITVALUE); 
		
			//lists
			List list = this.populateMaintenanceMainList();
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
		object.setSubject("Språkkod");
		object.setCode("012");
		object.setText("SVX001R / SVXKODF");
		object.setDbTable("SVXKODF");
		object.setPgm("svx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("2");
		object.setSubject("Dokumentkod");
		object.setCode("013");
		object.setText("SVX001R / SVXKODF");
		object.setDbTable("SVXKODF");
		object.setPgm("svx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("3");
		object.setSubject("Tidigare dokumnet");
		object.setCode("014");
		object.setText("SVX001R / SVXKODF");
		object.setDbTable("SVXKODF");
		object.setPgm("svx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("4");
		object.setSubject("Kollityp");
		object.setCode("017");
		object.setText("SVX001R / SVXKODF");
		object.setDbTable("SVXKODF");
		object.setPgm("svx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("5");
		object.setSubject("Deklarationstyp");
		object.setCode("031");
		object.setText("SVX001R / SVXKODF");
		object.setDbTable("SVXKODF");
		object.setPgm("svx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("6");
		object.setSubject("Tilläggsupplysningar");
		object.setCode("039");
		object.setText("SVX001R / SVXKODF");
		object.setDbTable("SVXKODF");
		object.setPgm("svx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("7");
		object.setSubject("Kontrollresultat");
		object.setCode("047");
		object.setText("SVX001R / SVXKODF");
		object.setDbTable("SVXKODF");
		object.setPgm("svx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("8");
		object.setSubject("Känsliga varor");
		object.setCode("064");
		object.setText("DKX001R / DKXKODF");
		object.setDbTable("DKXKODF");
		object.setPgm("dkx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("9");
		object.setSubject("Särskilda omständigheter ");
		object.setCode("096");
		object.setText("SVX001R / SVXKODF");
		object.setDbTable("SVXKODF");
		object.setPgm("svx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("10");
		object.setSubject("Tillgångskod för garanti");
		object.setCode("105");
		object.setText("SVX001R / SVXKODF");
		object.setDbTable("SVXKODF");
		object.setPgm("svx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("11");
		object.setSubject("Tullkontor ref.nr");
		object.setCode("106");
		object.setText("SVX001R / SVXKODF");
		object.setDbTable("SVXKODF");
		object.setPgm("svx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("12");
		object.setSubject("Betalningssätt transportkostnad");
		object.setCode("116");
		object.setText("SVX001R / SVXKODF");
		object.setDbTable("SVXKODF");
		object.setPgm("svx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("14");
		object.setSubject("Felkoder i CONTROL");
		object.setCode("023");
		object.setText("SVX001R / SVXKODF");
		object.setDbTable("SVXKODF");
		object.setPgm("svx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("15");
		object.setSubject("Kontrollindikator varupost");
		object.setCode("041");
		object.setText("SVX001R / SVXKODF");
		object.setDbTable("SVXKODF");
		object.setPgm("svx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("16");
		object.setSubject("Kontrollindikator transit");
		object.setCode("042");
		object.setText("SVX001R / SVXKODF");
		object.setDbTable("SVXKODF");
		object.setPgm("svx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("17");
		object.setSubject("Felkoder i CUSRES felmeddelande");
		object.setCode("049");
		object.setText("SVX001R / SVXKODF");
		object.setDbTable("SVXKODF");
		object.setPgm("svx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("18");
		object.setSubject("Felkoder för datagrupp i CUSRES");
		object.setCode("149");
		object.setText("DKX001R / DKXKODF");
		object.setDbTable("DKXKODF");
		object.setPgm("dkx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("19");
		object.setSubject("Felkoder för garanti");
		object.setCode("209");
		object.setText("SVX001R / SVXKODF");
		object.setDbTable("SVXKODF");
		object.setPgm("svx001r");
		object.setStatus("G");
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

