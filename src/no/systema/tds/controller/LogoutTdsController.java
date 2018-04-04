package no.systema.tds.controller;

import java.util.Calendar;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;


@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")

/**
 * OBSOLETE class since espedsg2-porting (apr 4th, 2018)
 * 
 * @author oscardelatorre
 *
 */
public class LogoutTdsController {
	private static final Logger logger = Logger.getLogger(LogoutTdsController.class.getName());
	private ModelAndView successView = new ModelAndView("dashboard");
	private ModelAndView loginView = new ModelAndView("login");
	
	
	@RequestMapping(value="logoutTds.do", method=RequestMethod.GET)
	public ModelAndView logoutTds(HttpSession session, HttpServletResponse response){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView view = null;
		
		if(appUser==null){
			 view = this.loginView;
		}else{
			logger.info("Logging out from Systema TDS...");
			session.removeAttribute(AppConstants.ASPECT_ERROR_MESSAGE);
			
			view = this.successView;
		}
		return view;
	}

	
    
}

