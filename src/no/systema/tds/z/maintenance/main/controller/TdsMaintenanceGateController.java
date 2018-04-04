package no.systema.tds.z.maintenance.main.controller;

import java.net.InetAddress;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.systema.main.service.UrlCgiProxyService;

//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
//models


/**
 * Gateway to the TDS Maintenance Application
 * 
 * 
 * @author oscardelatorre
 * @date Maj 02, 2017
 * 
 * 	
 */

@Controller
public class TdsMaintenanceGateController {
	private static final Logger logger = Logger.getLogger(TdsMaintenanceGateController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="tdsmaintenancegate.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView skatmaintenancegate(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tdsmaintenancegate");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu("INIT");
			logger.info("Inside method: tdsmaintenancegate");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			
			
	    	logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    session.setAttribute(AppConstants.ACTIVE_URL_RPG, AppConstants.ACTIVE_URL_RPG_INITVALUE);
		    
			return successView;
			
		}
	}
	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
		
}

