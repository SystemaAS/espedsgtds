package no.systema.tds.controller;

import java.util.*;
import java.text.DecimalFormat;
			
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;

import no.systema.main.model.SystemaWebUser;
import no.systema.tds.url.store.TdsUrlDataStore;
import no.systema.tds.util.TdsConstants;

import no.systema.tds.model.jsonjackson.authorization.JsonTdsAuthorizationContainer;
import no.systema.tds.model.jsonjackson.authorization.JsonTdsAuthorizationRecord;
import no.systema.tds.model.jsonjackson.authorization.JsonTdsPkiSmsCodeContainer;
import no.systema.tds.model.jsonjackson.authorization.JsonTdsPkiSmsCodeRecord;
//topic list
import no.systema.tds.model.jsonjackson.authorization.topic.JsonTdsAuthorizationTopicListContainer;
import no.systema.tds.model.jsonjackson.authorization.topic.JsonTdsAuthorizationSignSpecificTopicContainer;
import no.systema.tds.model.signpki.ApprovedRevokedDataCarrierObject;

import no.systema.tds.service.TdsAuthorizationService;


/**
 * TDS sign PKI Controller 
 * 
 * @author oscardelatorre
 * @date June 18, 2013
 * 
 */

@Controller
//@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
//@Scope("session")
public class TdsSignPkiController {
	
	private static final Logger logger = Logger.getLogger(TdsSignPkiController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private final long EXPIRATION_LIMIT_FOR_SMS_CODE_IN_MILLISECONDS = 120000; //120 seconds (2 minutes)
	private final long EXPIRATION_LIMIT_FOR_VALID_PKI_SESSION_IN_MILLISECONDS = 1800000; //30 minutes = 30x60 = 1800 seconds = 1800,000 milliseconds
	
	
	/**
	 * The method approves or revokes specific sign-topics
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tds_sign_pki_approve_revoke.do", method=RequestMethod.POST)
	public ModelAndView doTdsSignPkiApproveRevokeTopics(HttpSession session, HttpServletRequest request){
		//we must issue a redirect since we are timing a time-out (30 minutes). Otherwise the view will not emulate a full Http request as in redirect.
		ModelAndView successView = new ModelAndView("redirect:tds_sign_pki_list.do");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		Collection outputList = new ArrayList();
		
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_SIGN_PKI);
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, TdsConstants.ACTIVE_URL_RPG_INITVALUE); 
			//lists
			String BASE_URL_APPROVE = TdsUrlDataStore.TDS_SIGNED_TOPIC_APPROVE;
			String BASE_URL_REVOKE = TdsUrlDataStore.TDS_SIGNED_TOPIC_REVOKE;
			String baseUrl = null;
			
			//iterate through the list
			String nrOfRecordsInPage = request.getParameter("nrOfRecordsInPage");
			int nrOfRecords = Integer.parseInt(nrOfRecordsInPage);
			StringBuffer errorMessageBuffer = null;
			//Get user selections
			List<ApprovedRevokedDataCarrierObject> mapOfApprovedAndRevokedRecords = this.getApprovedAndRevokedRecords(request, appUser.getUser());
			String urlRequestParamsKeys = null;
			//start iterating on selected topics (whether they have been approved or revoked)
			for(ApprovedRevokedDataCarrierObject dataCarrier : mapOfApprovedAndRevokedRecords){
				urlRequestParamsKeys = this.getRequestUrlKeyParametersForApproveOrRevokeSign(appUser.getUser(),dataCarrier);
				logger.info("looping on data carrier -->" + dataCarrier.getOpd() + " XX " + dataCarrier.getUrsprung());
				if(ApprovedRevokedDataCarrierObject.ACTION_APPROVED.equals(dataCarrier.getActionType()) ){
					baseUrl = BASE_URL_APPROVE;
				}else if(ApprovedRevokedDataCarrierObject.ACTION_REVOKED.equals(dataCarrier.getActionType()) ){
					baseUrl = BASE_URL_REVOKE;
				}
				logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + baseUrl);
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(baseUrl, urlRequestParamsKeys);
		    	logger.info("jsonPayload on request: " + jsonPayload);
		    	
		    	JsonTdsAuthorizationSignSpecificTopicContainer jsonTdsAuthorizationSignSpecificTopicContainer = null;
		    	if(jsonPayload!=null){
		    		try{
		    			//now issue back-end request
			    		logger.info("Before service request...");
			    		jsonTdsAuthorizationSignSpecificTopicContainer = this.tdsAuthorizationService.getContainerApproveRevokeTopicToSign(jsonPayload);
			    		logger.info("After service request...");
			    		if(jsonTdsAuthorizationSignSpecificTopicContainer!=null){
			    			if(jsonTdsAuthorizationSignSpecificTopicContainer.getErrMsg()!=null && !"".equals(jsonTdsAuthorizationSignSpecificTopicContainer.getErrMsg()) ){
			    				if(errorMessageBuffer==null){
			    					errorMessageBuffer = new StringBuffer();
			    				}
			    				errorMessageBuffer.append("[ERROR] " + jsonTdsAuthorizationSignSpecificTopicContainer.getErrMsg());
			    				errorMessageBuffer.append("[On: opd="+jsonTdsAuthorizationSignSpecificTopicContainer.getOpd() + "avd="+jsonTdsAuthorizationSignSpecificTopicContainer.getAvd() + "ursp="+jsonTdsAuthorizationSignSpecificTopicContainer.getUrsp());
			    				errorMessageBuffer.append("\n");
			    			}	
			    		}
			    		
		    		}catch(Exception e){
		    			e.printStackTrace();
		    		}
		    	}
		    	
		    	//Debug --> 
		    	//logger.info(" --> jsonPayload:" + jsonPayload);
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			}
			//for debug purposes in GUI
			if(urlRequestParamsKeys!=null){
				session.setAttribute(TdsConstants.ACTIVE_URL_RPG, baseUrl  + "==>params: " + urlRequestParamsKeys.toString());
			}
			
			//Catch any errors that might have happened
			if(errorMessageBuffer!=null && !"".equals(errorMessageBuffer)){
				session.setAttribute(AppConstants.ASPECT_ERROR_MESSAGE, errorMessageBuffer.toString());
			}
			
			return successView;
	    	
		}
		
	}	
	
	/**
	 * Gets the end user selections
	 * 
	 * @param request
	 * @param userName
	 * 
	 * @return
	 * 
	 * 
	 */
	
	private List<ApprovedRevokedDataCarrierObject> getApprovedAndRevokedRecords(HttpServletRequest request, String userName) {
        List<ApprovedRevokedDataCarrierObject> list = new ArrayList<ApprovedRevokedDataCarrierObject> ();
        int nrOfRecordsInPage = 0;
        
        try {
            String records = request.getParameter("nrOfRecordsInPage");
            if (records != null) {
                nrOfRecordsInPage = Integer.parseInt(records);
                logger.info("Records in page: " + nrOfRecordsInPage);
                //retrieve the checkboxes and fill in the list as applicable
                ApprovedRevokedDataCarrierObject dataCarrier = null;
                for (int i = 1; i <= nrOfRecordsInPage; i++) {
                    String status = request.getParameter("signGrp_" + i);
                    String opd = request.getParameter("syop_" + i);
                    String avd = request.getParameter("syavd_" + i);
    				String ursp = request.getParameter("ursp_" + i);
    				
                    if (opd != null) {
                        if (status != null && !status.equalsIgnoreCase("")) {
                        	dataCarrier = new ApprovedRevokedDataCarrierObject();
                        	dataCarrier.setUser(userName);
                        	dataCarrier.setAvd(avd);
                        	dataCarrier.setOpd(opd);
                        	dataCarrier.setUrsprung(ursp);
                            
                            if (status.equalsIgnoreCase("approved")) {
                                logger.info("APPROVED opd: " + opd + " avd:" + avd );
                                dataCarrier.setActionType(ApprovedRevokedDataCarrierObject.ACTION_APPROVED);
                                list.add(dataCarrier);
                               
                                
                            }else if (status.equalsIgnoreCase("revoked")) {
                            	logger.info("REVOKED opd: " + opd + " avd:" + avd );
                            	dataCarrier.setActionType(ApprovedRevokedDataCarrierObject.ACTION_REVOKED);
                                list.add(dataCarrier);
                               
                            }
                        }
                    }

                }
                
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return list;

    }
	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tds_sign_pki.do", method={ RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doTdsSignPki(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tds_sign_pki");
		//we must issue a redirect since we are timing a time-out (30 minutes). Otherwise the view will not emulate a full Http request as in redirect.
		ModelAndView alreadyAuthorizedView = new ModelAndView("redirect:tds_sign_pki_list.do");
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		session.removeAttribute(AppConstants.ASPECT_ERROR_MESSAGE);
		
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_SIGN_PKI);
			
			long timeElapsedOnValidPkiSessionTimeout = this.getTimeElapsedOnValidPkiSessionTimeout(session);
			String timeElapsedInMinutes = this.getTimeElapsedInMinutes(timeElapsedOnValidPkiSessionTimeout);
			logger.info("PKI-valid-session time elapsed so far [minutes / milliseconds]: " + timeElapsedInMinutes + "/" + timeElapsedOnValidPkiSessionTimeout);
			logger.info("PKI-valid-session time limit = 30 minutes [18000000]");
			if(!"0x".equals(timeElapsedInMinutes)){
				session.setAttribute("timeElapsedInMinutes", timeElapsedInMinutes);
			}
			//-------------------------------------------------------------
			//Check if the user is already in a PKI-authorized mode or not
			//-------------------------------------------------------------
			if(timeElapsedOnValidPkiSessionTimeout <= this.EXPIRATION_LIMIT_FOR_VALID_PKI_SESSION_IN_MILLISECONDS){
				return alreadyAuthorizedView;
				
			}else{
				//-------------------------------------------------------------
				//In this case the user MUST apply for permission all the way
				//-------------------------------------------------------------
					
				//lend the user password from the GUI
				appUser.setPwAS400(request.getParameter("pwAS400"));
				int tdsAuthenticationNumberOfTries = 0;
				
				//check for GUI required element (if any...)
				if( (appUser.getPwAS400()!=null && !"".equals(appUser.getPwAS400())) &&  (appUser.getAuthorizedTdsUserAS400()!=null && "Y".equals(appUser.getAuthorizedTdsUserAS400()))  ){
					
					if("Y".equals(appUser.getAuthenticatedTdsSignPkiUserAS400())){
						//do nothing. The user has already been authenticated
						
					}else{
						tdsAuthenticationNumberOfTries = Integer.valueOf((Integer)session.getAttribute(AppConstants.TDS_AUTHENTICATION_NUMBER_OF_TRIES));
						tdsAuthenticationNumberOfTries++;
						logger.info("Number of tries [authentication]: " + tdsAuthenticationNumberOfTries);
						
						String BASE_URL = TdsUrlDataStore.TDS_GET_AUTHORIZATION_CODE;
						//url params
						String urlRequestParamsKeys = this.getRequestUrlKeyParameters(appUser, tdsAuthenticationNumberOfTries);
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
					    				session.setAttribute(AppConstants.ASPECT_ERROR_MESSAGE, jsonTdsAuthorizationContainer.getErrMsg());
					    				
					    			}else{
					    				//get now the authentication result flag for Tds-Pki permission to sign
					    				this.updateAppUser(jsonTdsAuthorizationContainer, appUser);
					    				
					    				//if the login is valid we populate this value that will enable valid TDS-menus at the JSP (headerTds.jsp)
					    				if("Y".equals(appUser.getAuthenticatedTdsSignPkiUserAS400())){
					    					session.setAttribute(AppConstants.SYSTEMA_WEB_USER_KEY, appUser);
					    					//start counting from this time-stamp (you have now 120 sec. to submit your SMS-code before the PKI-authentication expires)
					    					session.setAttribute(AppConstants.TDS_TIMESTAMP_BEFORE_SUBMITTING_VALID_SMSCODE, new Date());
					    					
					    					//finally we send the request for getting the SMS code and update the appUser with
					    					this.setPkiSmsCodeOnAppUser(appUser, session);
					    					logger.info("Sms CODE on APP-USER: " + appUser.getAuthenticatedTdsSignPkiSmsCode());
					    					
					    				}else{
					    					String unauthorizedErrorMessage = this.getAuthenticationTdsErrorMessage(appUser);
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
					
					if("POST".equals(request.getMethod())){
						String requiredValuesMissingErrorMessage = this.getRequiredValuesMissingErrorMessage();
						session.setAttribute(AppConstants.ASPECT_ERROR_MESSAGE, requiredValuesMissingErrorMessage);
					}
				}
		    	
				logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
			    session.setAttribute(AppConstants.ACTIVE_URL_RPG, AppConstants.ACTIVE_URL_RPG_INITVALUE);
			    
			    //Accumulate the number of login tries since the user has only max 3 tries before the login is locked.
			    session.setAttribute(AppConstants.TDS_AUTHENTICATION_NUMBER_OF_TRIES, tdsAuthenticationNumberOfTries);
				
				return successView;
			}
		}
	}
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tds_sign_pki_submit_smscode.do", method={ RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doTdsSignPkiSubmitSmsCode(HttpSession session, HttpServletRequest request){
		//we must issue a redirect since we are timing a time-out (30 minutes). Otherwise the view will not emulate a full Http request as in redirect.
		final String SUCCESS_VIEW = "redirect:tds_sign_pki_list.do"; 
		final String UNSUCCESS_VIEW = "tds_sign_pki";
		
		ModelAndView theView = null;
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		session.removeAttribute(AppConstants.ASPECT_ERROR_MESSAGE);
		
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_SIGN_PKI);
			
			//check the sms code timeout now
			String smsCode = request.getParameter("signSmsCode");
			long timeElapsedBeforeSubmittingSmsCode = getTimeElapsedOnSmsCodeTimeout(smsCode, session);
			logger.info("SMS-code time elapsed so far [milliseconds]: " + timeElapsedBeforeSubmittingSmsCode);
			
			//start checking now
			if(timeElapsedBeforeSubmittingSmsCode <= this.EXPIRATION_LIMIT_FOR_SMS_CODE_IN_MILLISECONDS){
				if( smsCode!=null && "Y".equals(appUser.getAuthenticatedTdsSignPkiUserAS400()) ){
					if(this.isValidSmsCode(smsCode, appUser.getAuthenticatedTdsSignPkiSmsCode())){
						//remove privileges
						appUser.setAuthenticatedTdsSignPkiSmsCode(null);
						appUser.setAuthenticatedTdsSignPkiUserAS400(null);
						session.setAttribute(AppConstants.SYSTEMA_WEB_USER_KEY, appUser);
						//start counting time elapsed (seed) = valid 30 minutes from now
						session.setAttribute(AppConstants.TDS_TIMESTAMP_AFTER_SUBMITTING_VALID_SMSCODE, new Date());
						theView = new ModelAndView(SUCCESS_VIEW); 
					
					}else{
						//Invalid Sms value
						if("POST".equals(request.getMethod())){
							String requiredSmsCodeInvalidValue = this.getRequiredSmsCodeInvalidValue();
							session.setAttribute(AppConstants.ASPECT_ERROR_MESSAGE, requiredSmsCodeInvalidValue);
							theView = new ModelAndView(UNSUCCESS_VIEW);
						}
					}
					
				}else{
					if("POST".equals(request.getMethod())){
						String requiredSmsCodeValueMissingErrorMessage = this.getRequiredSmsCodeValueMissingErrorMessage();
						session.setAttribute(AppConstants.ASPECT_ERROR_MESSAGE, requiredSmsCodeValueMissingErrorMessage);
						theView = new ModelAndView(UNSUCCESS_VIEW);
					}
				}
			}else{
				String smsCodeExpirationErrorMessage = "SMS-code valid registration time has expired! <br/>Please start with the PKI-authentication process again";
				session.setAttribute(AppConstants.ASPECT_ERROR_MESSAGE, smsCodeExpirationErrorMessage);
				logger.error(smsCodeExpirationErrorMessage);
				//erase the valid authentication Pki-flag
				appUser.setAuthenticatedTdsSignPkiUserAS400(null);
				session.setAttribute(AppConstants.SYSTEMA_WEB_USER_KEY, appUser);
				theView = new ModelAndView(UNSUCCESS_VIEW);
			}
		}
		
		logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
	    session.setAttribute(AppConstants.ACTIVE_URL_RPG, AppConstants.ACTIVE_URL_RPG_INITVALUE);
	    
		return theView;
	}	

	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tds_sign_pki_list.do", method={ RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doTdsSignPkiShowList(HttpSession session, HttpServletRequest request){
		
		ModelAndView successView = new ModelAndView("tds_sign_pki_list");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		Collection outputList = new ArrayList();
		
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_SIGN_PKI);
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, TdsConstants.ACTIVE_URL_RPG_INITVALUE); 
			//lists
			String BASE_URL = TdsUrlDataStore.TDS_GET_PKI_TOPIC_LIST;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForPkiTopicList(appUser);
			//for debug purposes in GUI
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    		//--------------------------------------
	    		//EXECUTE the FETCH (RPG program) here
	    		//--------------------------------------
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		JsonTdsAuthorizationTopicListContainer jsonTdsAuthorizationTopicListContainer = null;
		    	if(jsonPayload!=null){
		    		try{
		    			jsonTdsAuthorizationTopicListContainer = this.tdsAuthorizationService.getContainerTopicList(jsonPayload);
		    			outputList = jsonTdsAuthorizationTopicListContainer.getSigneringslista();
		    			
		    		}catch(Exception e){
		    			e.printStackTrace();
		    		}
		    	}
		    	//Debug --> 
		    	logger.info(" --> jsonPayload:" + jsonPayload);
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	
			successView.addObject("model" , model);
			successView.addObject("list", outputList);
	    	return successView;
		}
		
	}	

	
	/**
	 * Validates the Sms code from GUI and in addition, towards the previously system generated sms-code
	 * 
	 * @param smsCodeGui
	 * @param generatedOriginalSmsCode
	 * @return
	 */
	private boolean isValidSmsCode(String smsCodeGui, String generatedOriginalSmsCode){
		boolean retval = false;
		logger.info("SMS-CODE-GUI: " + smsCodeGui);
		logger.info("SMS-CODE-GENERATED: " + generatedOriginalSmsCode);
		
		if( (smsCodeGui!=null) && (!"".equals(smsCodeGui)) && (smsCodeGui.length()==7) ){
			if(smsCodeGui.equals(generatedOriginalSmsCode)){
				retval = true;
			}
			
		}
		
		return retval;
	}
	
	
	/**
	 * This method updates the AppUser with the Sms-code (one-time code) 
	 * This code will be used later (within 120 seconds) by the end-user when he/she submits the value in the GUI...
	 * 
	 * @param appUser
	 * @param session
	 */
	private void setPkiSmsCodeOnAppUser(SystemaWebUser appUser, HttpSession session){
		String BASE_URL = TdsUrlDataStore.TDS_GET_PKI_SMS_CODE;
		//url params
		String urlRequestParamsKeys = this.getRequestUrlKeyParametersForPkiSmsCode(appUser);
		//for debug purposes in GUI
		session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    	JsonTdsPkiSmsCodeContainer jsonTdsPkiSmsCodeContainer = null;
	    	if(jsonPayload!=null){
	    		try{
	    			jsonTdsPkiSmsCodeContainer = this.tdsAuthorizationService.getContainerPkiSmsCode(jsonPayload);
	    			if(jsonTdsPkiSmsCodeContainer.getErrMsg()!=null && !"".equals(jsonTdsPkiSmsCodeContainer.getErrMsg())){
	    				session.setAttribute(AppConstants.ASPECT_ERROR_MESSAGE, jsonTdsPkiSmsCodeContainer.getErrMsg());
	    			}else{
	    				//get now the one-time Sms code that was produced by the back-end
	    				this.updateAppUserWithSmsCode(jsonTdsPkiSmsCodeContainer, appUser);
	    			}
	    		}catch(Exception e){
	    			e.printStackTrace();
	    		}
	    	}
	}
	
	/**
	 * This method checks the time elapsed before the user has been able to enter and submit (on GUI) his/her one-time code 
	 * 
	 * @param smsCode
	 * @param session
	 * @return
	 */
	private long getTimeElapsedOnSmsCodeTimeout(String smsCode, HttpSession session){
		long timeElapsedBeforeSubmittingSmsCode = 1000000000L;
		//check the sms code now
		if(smsCode!=null && !"".equals(smsCode)){
			//get the start time-stamp registered when the user was authenticated and the SMS-code input field presented in the GUI...
			Date startTimeStamp = (Date)session.getAttribute(AppConstants.TDS_TIMESTAMP_BEFORE_SUBMITTING_VALID_SMSCODE);
			//now get the time-stamp at this specific moment.
			Date now = new Date();
			//compare and check for expiration or not
			if(startTimeStamp!=null){
				timeElapsedBeforeSubmittingSmsCode = now.getTime() - startTimeStamp.getTime();
			}
		}
		
		return timeElapsedBeforeSubmittingSmsCode;
	}
	
	/**
	 * This method checks the time elapsed from within the valid authorized framework within PKI-session (30 minutes)
	 * After this 30 minutes the user MUST re-enter his/her password and received a one-time code via SMS
	 * 
	 * @param session
	 * @return
	 */
	
	private long getTimeElapsedOnValidPkiSessionTimeout(HttpSession session){
		long timeElapsed = 1000000000L;
		
		//get the start time-stamp registered when the user was authenticated and the SMS-code input field presented in the GUI...
		Date startTimeStamp = (Date)session.getAttribute(AppConstants.TDS_TIMESTAMP_AFTER_SUBMITTING_VALID_SMSCODE);
		//now get the time-stamp at this specific moment.
		Date now = new Date();
		//compare and check for expiration or not
		if(startTimeStamp!=null){
			timeElapsed = now.getTime() - startTimeStamp.getTime();
		}
		
		return timeElapsed;
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private String getTimeElapsedInMinutes(long value){
		//conversion
		double valueDbl = 0.00D;
		valueDbl = (double)value;
		valueDbl = (valueDbl / 1000) /60;
		DecimalFormat df = new DecimalFormat("0.00");
		//formatting
		String retval = "0x";
		if(valueDbl<100){
			retval = df.format(valueDbl);
		}
		return retval;
	}
	/**
	 * 
	 * @return
	 */
	private String getRequiredSmsCodeValueMissingErrorMessage(){
		StringBuffer sb = new StringBuffer();
		sb.append("Sms-kod är obligatorisk");
		return sb.toString();
	}

	/**
	 * 
	 * @return
	 */
	private String getRequiredSmsCodeInvalidValue(){
		StringBuffer sb = new StringBuffer();
		sb.append("Sms-kod stämmer inte men tidigare genererad kod. Du måste börja om på nytt.");
		return sb.toString();
	}

	
	
	/**
	 * 
	 * @return
	 */
	private String getRequiredValuesMissingErrorMessage(){
		StringBuffer sb = new StringBuffer();
		sb.append("Användarid och/eller lösenord är obligatoriska");
		return sb.toString();
	}

	
	/**
	 * Invalid authentication
	 * 
	 * @param appUser
	 * @return
	 */
	private String getAuthenticationTdsErrorMessage(SystemaWebUser appUser){
		
		StringBuffer sb = new StringBuffer();
		sb.append("Användaren: " + appUser.getUserAS400() + " kan inte autentiseras (kontrollera id/lösenord)./n");
		sb.append("Kontakta din TDS System Administrator för vidare upplysningar.");
		
		return sb.toString();
	}
	
	
	/**
	 * Authenticates the end user (in order to be able to send an SMS code to the end user)
	 * 
	 * @param jsonTdsAuthorizationContainer
	 * @param appUser
	 */
	private void updateAppUser(JsonTdsAuthorizationContainer jsonTdsAuthorizationContainer, SystemaWebUser appUser){
		for(JsonTdsAuthorizationRecord record : jsonTdsAuthorizationContainer.getTdsBehorigKontroll()){
			appUser.setAuthenticatedTdsSignPkiUserAS400(record.getBpki());
		}
	}
	
	/**
	 * Sets the Sms Pki Code 
	 * @param jsonTdsPkiSmsCodeContainer
	 * @param appUser
	 */
	private void updateAppUserWithSmsCode(JsonTdsPkiSmsCodeContainer jsonTdsPkiSmsCodeContainer, SystemaWebUser appUser){
		for(JsonTdsPkiSmsCodeRecord record : jsonTdsPkiSmsCodeContainer.getTdsSkapaengangskod()){
			appUser.setAuthenticatedTdsSignPkiSmsCode(record.getSmsKod());
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
	
	/**
	 * 
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForPkiSmsCode(SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "usrAS400=" + appUser.getUserAS400());
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForPkiTopicList(SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "usrAS400=" + appUser.getUserAS400());
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param userName
	 * @param opd
	 * @param avd
	 * @param ursp
	 * @return
	 */
	private String getRequestUrlKeyParametersForApproveOrRevokeSign(String userName, ApprovedRevokedDataCarrierObject dataCarrier){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		urlRequestParamsKeys.append("user=" + userName);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + dataCarrier.getAvd());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + dataCarrier.getOpd());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ursp=" + dataCarrier.getUrsprung());
		
		
		return urlRequestParamsKeys.toString();
	}
	
	
	//Services
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

