package no.systema.tds.controller;

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

//import no.systema.tds.service.MainHdTopicService;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.tds.url.store.TdsUrlDataStore;
import no.systema.tds.util.TdsConstants;
import no.systema.tds.model.jsonjackson.authorization.JsonTdsAuthorizationContainer;
import no.systema.tds.model.jsonjackson.authorization.JsonTdsAuthorizationRecord;
import no.systema.tds.service.TdsAuthorizationService;

//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
//models


/**
 * Gateway to the TDS Application
 * 
 * The user will either have access to TDS and NCTS or not. With no access then only the Dashboard will be available
 * With valid access the user will be able to use TDS and NCTS
 * 
 * TDS has an extra layer of authorization (in addition of the general SYSPED authentication process)
 * 
 * @author oscardelatorre
 *
 */

@Controller
public class GateController {
	private static final Logger logger = Logger.getLogger(GateController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsgate.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView tdsgate(HttpSession session, HttpServletRequest request){
		
		ModelAndView SUCCESS_VIEW = new ModelAndView("tdsgate");
		ModelAndView TOO_OLD_PASSWORD_VIEW = new ModelAndView("tds_change_password_too_old");
		//init
		ModelAndView theView = SUCCESS_VIEW;
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu("INIT");
			logger.info("Inside method: tdsgate");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			

			String tmp = appUser.getPwAS400();
			if(tmp!=null && !"".equals(tmp)){
				//nothing since user has previously been granted permission.
			}else{
				//otherwise it is first time authorization
				//lend the user password from the GUI
				appUser.setPwAS400(request.getParameter("pwAS400"));
			}
			//init the required counter for the TDS authorization (max 3 tries)
			int tdsAuthorizationNumberOfTries = 0;
			
			//check for GUI required element (if any...)
			if( (appUser.getPwAS400()!=null && !"".equals(appUser.getPwAS400())) &&  (appUser.getUserAS400()!=null && !"".equals(appUser.getUserAS400()))  ){
				if(appUser.getAuthorizedTdsUserAS400()!=null && !"Y".equals(appUser.getAuthorizedTdsUserAS400())){
					//do nothing meaning:
					//that the user has already been previously authorized in a TDS-general
				}else{
					logger.info("SESSION:" + session.getId());
					logger.info("INTEGER:" + (Integer)session.getAttribute(AppConstants.TDS_AUTHORIZATION_NUMBER_OF_TRIES));
					
					Integer nrTriesTmp = (Integer)session.getAttribute(AppConstants.TDS_AUTHORIZATION_NUMBER_OF_TRIES);
					if(nrTriesTmp != null){
						tdsAuthorizationNumberOfTries = nrTriesTmp;
						tdsAuthorizationNumberOfTries++;
						logger.info("Number of tries [authorization]: " + tdsAuthorizationNumberOfTries);
						
					}else{
						//the first try
						tdsAuthorizationNumberOfTries++;
						logger.info("Number of tries [authorization]: " + tdsAuthorizationNumberOfTries);
					}
					String BASE_URL = TdsUrlDataStore.TDS_GET_AUTHORIZATION_CODE;
					//url params
					String urlRequestParamsKeys = this.getRequestUrlKeyParameters(appUser, tdsAuthorizationNumberOfTries);
					//for debug purposes in GUI
					session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
					
					logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
				    	logger.info("URL: " + BASE_URL);
				    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
				    	//--------------------------------------
				    	//EXECUTE the FETCH (RPG program) here
				    	//--------------------------------------
				    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				    	JsonTdsAuthorizationContainer jsonTdsAuthorizationContainer = null;
				    	if(jsonPayload!=null){
				    		try{
				    			jsonTdsAuthorizationContainer = this.tdsAuthorizationService.getContainer(jsonPayload);
				    			if(jsonTdsAuthorizationContainer.getErrMsg()!=null && !"".equals(jsonTdsAuthorizationContainer.getErrMsg())){
				    				//Different types of errors
				    				if(jsonTdsAuthorizationContainer.getErrMsg().contains("forsok")){
				    					String suffixInfo = ". Gå tillbaka till Dashboard och prova på nytt. Du har max. 3 försök.";
				    					session.setAttribute(AppConstants.ASPECT_ERROR_MESSAGE, jsonTdsAuthorizationContainer.getErrMsg() + suffixInfo );
				    					//init password to NULL upon error
					    				appUser.setPwAS400(null);
					    				session.setAttribute(AppConstants.SYSTEMA_WEB_USER_KEY, appUser);
					    				
				    				}else if(jsonTdsAuthorizationContainer.getErrMsg().contains("gammalt lösenord")){
				    					//we must redirect to the new jsp in order to keep clean the "change password when too old" activity
				    					theView = TOO_OLD_PASSWORD_VIEW;
				    					session.removeAttribute(AppConstants.ASPECT_ERROR_MESSAGE);
				    					//put user in session since we will need the original password for the "change password too old" routine
					    				session.setAttribute(AppConstants.SYSTEMA_WEB_USER_KEY, appUser);
					    				
				    				}else{
				    					session.setAttribute(AppConstants.ASPECT_ERROR_MESSAGE, jsonTdsAuthorizationContainer.getErrMsg());
				    					//init password to NULL upon error
					    				appUser.setPwAS400(null);
					    				session.setAttribute(AppConstants.SYSTEMA_WEB_USER_KEY, appUser);
					    				
				    				}
				    			}else{
				    				//get now the authorization flags for both: Tds-general and Tds-Pki
				    				this.updateAppUser(jsonTdsAuthorizationContainer, appUser);
				    				
				    				//if the login is valid we populate this value that will enable valid TDS-menus at the JSP (headerTds.jsp)
				    				if("Y".equals(appUser.getAuthorizedTdsUserAS400())){
				    					session.setAttribute(AppConstants.SYSTEMA_WEB_USER_KEY, appUser);
				    					logger.info("[After returning AS400]sign: " + appUser.getTdsSign());
				    				}else{
				    					String unauthorizedErrorMessage = this.getUnauthorizedTdsErrorMessage(appUser);
				    					session.setAttribute(AppConstants.ASPECT_ERROR_MESSAGE, unauthorizedErrorMessage);
				    				}
				    			}
				    		}catch(Exception e){
				    			e.printStackTrace();
				    		}
				    	}
						//Debug --> 
				    	logger.info(" --> jsonPayload:" + jsonPayload);
				    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
				}
			}else{
				//Only when the user has submit the form-Post. With a general GET (coming from the Dashboard) no error message should be presented.
				if("POST".equals(request.getMethod())){
					String requiredValuesMissingErrorMessage = this.getRequiredValuesMissingErrorMessage();
					session.setAttribute(AppConstants.ASPECT_ERROR_MESSAGE, requiredValuesMissingErrorMessage);
				}
			}
	    	
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    session.setAttribute(AppConstants.ACTIVE_URL_RPG, AppConstants.ACTIVE_URL_RPG_INITVALUE);
		    
		    //Accumulate the number of login tries since the user has only max 3 tries before the login is locked.
		    session.setAttribute(AppConstants.TDS_AUTHORIZATION_NUMBER_OF_TRIES, tdsAuthorizationNumberOfTries);
			return theView;
		}
	}
	
	/**
	 * The method gets an the error message for invalid required fields for authorization of TDS
	 * 
	 * @return
	 */
	private String getRequiredValuesMissingErrorMessage(){
		
		StringBuffer sb = new StringBuffer();
		sb.append("Användarid och/eller lösenord är obligatoriska");
		return sb.toString();
	}
	
	/**
	 * The method gets an unauthorized TDS error message
	 * @param appUser
	 * @return
	 */
	private String getUnauthorizedTdsErrorMessage(SystemaWebUser appUser){
		
		StringBuffer sb = new StringBuffer();
		sb.append("Användaren: " + appUser.getUserAS400() + " har inte allmän behörighet för TDS./n");
		sb.append("Kontakta din TDS System Administrator för vidare upplysningar.");
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @param jsonTdsAuthorizationContainer
	 * @param appUser
	 */
	private void updateAppUser(JsonTdsAuthorizationContainer jsonTdsAuthorizationContainer, SystemaWebUser appUser){
		for(JsonTdsAuthorizationRecord record : jsonTdsAuthorizationContainer.getTdsBehorigKontroll()){
			appUser.setAuthorizedTdsSignPkiUserAS400(record.getBpki());
			appUser.setAuthorizedTdsUserAS400(record.getBtds());
			appUser.setTdsSign(record.getSign());
		}
	}
	
	/**
	 * 
	 * @param appUser
	 * @param numberOfTries
	 * @return
	 */
	private String getRequestUrlKeyParameters(SystemaWebUser appUser, int numberOfTries){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "usrAS400=" + appUser.getUserAS400());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "pwAS400=" + appUser.getPwAS400());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ant=" + numberOfTries);
		
		return urlRequestParamsKeys.toString();
	}
	
	
	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
		
	
	@Qualifier ("tdsAuthorizationService")
	private TdsAuthorizationService tdsAuthorizationService;
	@Autowired
	@Required
	public void setTdsAuthorizationService (TdsAuthorizationService value) { this.tdsAuthorizationService = value; }
	public TdsAuthorizationService getTdsAuthorizationService(){ return this.tdsAuthorizationService; }
}

