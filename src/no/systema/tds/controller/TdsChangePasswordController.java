/**
 * 
 */
package no.systema.tds.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.model.jsonjackson.JsonSystemaUserContainer;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.login.SystemaWebLoginService;
import no.systema.main.util.AppConstants;
import no.systema.tds.model.jsonjackson.authorization.JsonTdsAuthorizationContainer;
import no.systema.tds.model.jsonjackson.authorization.JsonTdsAuthorizationRecord;
import no.systema.tds.model.signpki.ApprovedRevokedDataCarrierObject;
import no.systema.tds.url.store.TdsUrlDataStore;
import no.systema.tds.util.TdsConstants;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author oscardelatorre
 * @date Aug 22, 2013
 * 
 */
@Controller

public class TdsChangePasswordController {
	private static final Logger logger = Logger.getLogger(TdsChangePasswordController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");

	
	/**
	 * Init gateway for the "change password" activity
	 * Menu choice
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tds_change_password.do", method={ RequestMethod.GET })
	public ModelAndView doTdsChangePassword(HttpSession session, HttpServletRequest request){
		//we must issue a redirect since we are timing a time-out (30 minutes). Otherwise the view will not emulate a full Http request as in redirect.
		ModelAndView successView = new ModelAndView("tds_change_password");
		logger.info("[INFO] Init ...change pw");
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		session.removeAttribute(AppConstants.ASPECT_ERROR_MESSAGE);
		session.removeAttribute(AppConstants.ASPECT_SUCCESS_MESSAGE);
		
		
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_CHANGE_PASSWORD);
			
		}
		return successView;
	}
	
	/**
	 * Used when the user wishes to change his/her password (from the "Change password" menu)
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tds_change_password_submit.do", method={ RequestMethod.POST })
	public ModelAndView doTdsChangePasswordSubmit(HttpSession session, HttpServletRequest request){
		//we must issue a redirect since we are timing a time-out (30 minutes). Otherwise the view will not emulate a full Http request as in redirect.
		ModelAndView successView = new ModelAndView("tds_change_password");
		StringBuffer errorMessageBuffer = null;
		StringBuffer successMessageBuffer = null;
		//Init
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		session.removeAttribute(AppConstants.ASPECT_ERROR_MESSAGE);
		session.removeAttribute(AppConstants.ASPECT_SUCCESS_MESSAGE);
		
		String password = request.getParameter("pwAS400N1");
		String repeatPassord = request.getParameter("pwAS400N2");
		
		
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_CHANGE_PASSWORD);

			String BASE_URL = TdsUrlDataStore.TDS_CHANGE_PASSWORD;
			String urlRequestParamsKeys = this.getRequestUrlKeyParameters(appUser, password, repeatPassord);
			logger.info(urlRequestParamsKeys);
			
			//Execute rpg-program
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			logger.info("jsonPayload on request: " + jsonPayload);
	    		if(jsonPayload!=null){
	    			JsonSystemaUserContainer container = this.systemaWebLoginService.getSystemaUserContainerForPassword(jsonPayload);
	    			if(container!=null){
	    				if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
	    					errorMessageBuffer = new StringBuffer("[ERROR] " + container.getErrMsg());
	    					logger.error(errorMessageBuffer);
	    				}else{
	    					successMessageBuffer = new StringBuffer("Lösenordet har ändrats");
	    					session.setAttribute(AppConstants.ASPECT_SUCCESS_MESSAGE, successMessageBuffer.toString());
	    					//update user
	    					appUser.setPwAS400(password);
	    					session.setAttribute(AppConstants.SYSTEMA_WEB_USER_KEY,appUser);
	    					logger.error("[INFO change pw] " + successMessageBuffer);
	    					logger.error("[INFO change pw] " + appUser.getUser() + "@" + appUser.getPwAS400());
	    					
	    				}
	    			}else{
	    				errorMessageBuffer = new StringBuffer("[ERROR - fatal] JsonPayload returned a null Container. Contact your System Administrator...");
	    				logger.error(errorMessageBuffer);
	    			}
	    		}else{
				errorMessageBuffer = new StringBuffer("[ERROR - fatal] JsonPayload returned NULL. Contact your System Administrator...");
				logger.error(errorMessageBuffer);
	    		}
	    		
			//if errors
			if(errorMessageBuffer!=null && !"".equals(errorMessageBuffer)){
				session.setAttribute(AppConstants.ASPECT_ERROR_MESSAGE, errorMessageBuffer.toString());
			}
			
		}
		return successView;
	}
	
	
	/**
	 * Used to be able to continue within TDS.
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tds_change_password_too_old_submit.do", method={ RequestMethod.POST })
	public ModelAndView doTdsChangePasswordSubmitTooOld(HttpSession session, HttpServletRequest request){
		//we must issue a redirect since we are timing a time-out (30 minutes). Otherwise the view will not emulate a full Http request as in redirect.
		ModelAndView SUCCESS_VIEW = new ModelAndView("tdsgate");
		ModelAndView FALLBACK_VIEW = new ModelAndView("tds_change_password_too_old");
		//init to fall-back view in case of errors
		ModelAndView theView = FALLBACK_VIEW;
		
		StringBuffer errorMessageBuffer = null;
		StringBuffer successMessageBuffer = null;
		//Init
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		session.removeAttribute(AppConstants.ASPECT_ERROR_MESSAGE);
		session.removeAttribute(AppConstants.ASPECT_SUCCESS_MESSAGE);
		
		String password = request.getParameter("pwAS400N1");
		String repeatPassord = request.getParameter("pwAS400N2");
		
		
		if(appUser==null){
			return this.loginView;
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_CHANGE_PASSWORD);

			String BASE_URL = TdsUrlDataStore.TDS_CHANGE_PASSWORD;
			String urlRequestParamsKeys = this.getRequestUrlKeyParameters(appUser, password, repeatPassord);
			logger.info(urlRequestParamsKeys);
			
			//Execute rpg-program
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			logger.info("jsonPayload on request: " + jsonPayload);
	    		if(jsonPayload!=null){
	    			JsonSystemaUserContainer container = this.systemaWebLoginService.getSystemaUserContainerForPassword(jsonPayload);
	    			if(container!=null){
	    				if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
	    					errorMessageBuffer = new StringBuffer("[ERROR] " + container.getErrMsg());
	    					logger.error(errorMessageBuffer);
	    				}else{
	    					//redirection to the tdsgate in order to see the TDS-header menu
	    					theView = SUCCESS_VIEW;
	    					//update user
	    					appUser.setPwAS400(password);
	    					session.setAttribute(AppConstants.SYSTEMA_WEB_USER_KEY, appUser);
	    					logger.error("[INFO change pw] " + successMessageBuffer);
	    					logger.error("[INFO change pw] " + appUser.getUser() + "@" + appUser.getPwAS400());
	    					
	    				}
	    			}else{
	    				errorMessageBuffer = new StringBuffer("[ERROR - fatal] JsonPayload returned a null Container. Contact your System Administrator...");
	    				logger.error(errorMessageBuffer);
	    			}
	    		}else{
				errorMessageBuffer = new StringBuffer("[ERROR - fatal] JsonPayload returned NULL. Contact your System Administrator...");
				logger.error(errorMessageBuffer);
	    		}
	    		
			//if errors
			if(errorMessageBuffer!=null && !"".equals(errorMessageBuffer)){
				session.setAttribute(AppConstants.ASPECT_ERROR_MESSAGE, errorMessageBuffer.toString());
			}
			
		}
		return theView;
	}
	
	
	/**
	 * 
	 * @param userName
	 * @param dataCarrier
	 * @return
	 */
	private String getRequestUrlKeyParameters(SystemaWebUser appUser, String pwAS400N1, String pwAS400N2){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "usrAS400=" + appUser.getUserAS400());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "pwAS400=" + appUser.getPwAS400());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "pwAS400N1=" + pwAS400N1);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "pwAS400N2=" + pwAS400N2);
		//user=CB&usrAS400=CBX&pwAS400=After8&pwAS400N1=Straffe12&pwAS400N=Straffe12
		
		return urlRequestParamsKeys.toString();
	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("systemaWebLoginService")
	private SystemaWebLoginService systemaWebLoginService;
	@Autowired
	@Required
	public void setSystemaWebLoginService (SystemaWebLoginService value){ this.systemaWebLoginService = value; }
	public SystemaWebLoginService getSystemaWebLoginService(){ return this.systemaWebLoginService; }
		
	
}
