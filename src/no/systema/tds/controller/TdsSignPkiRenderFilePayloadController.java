package no.systema.tds.controller;

import java.io.*;
import java.util.*;
import java.net.*;

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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.io.PayloadContentFlusher;
import no.systema.main.validator.IPAddressValidator;
import no.systema.main.model.SystemaWebUser;
import no.systema.tds.util.TdsConstants;

import no.systema.tds.service.TdsAuthorizationService;


/**
 * TDS File Payload Renderer for PKI Topics - Controller 
 * 
 * This class is in charge of a rendering process for any given file type (PDF, TIF, TXT, etc)
 * The rendering process is usually implemented in a separate window.
 * 
 * @author oscardelatorre
 * @date June 21, 2013
 * 
 */

@Controller
public class TdsSignPkiRenderFilePayloadController {
	
	private static final Logger logger = Logger.getLogger(TdsSignPkiRenderFilePayloadController.class.getName());
	private PayloadContentFlusher payloadContentFlusher = new PayloadContentFlusher();
	
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private static final int BYTE_BLOCK_SIZE = 1024;
	
	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tds_sign_pki_renderEdifact.do", method={ RequestMethod.GET })
	public ModelAndView doTdsSignPkiRenderPdf(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("Inside doTdsSignPkiRenderPdf...");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_SIGN_PKI);
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, TdsConstants.ACTIVE_URL_RPG_INITVALUE); 
			String filePath = request.getParameter("fp");
			
			if(filePath!=null && !"".equals(filePath)){
                String absoluteFilePath = filePath;
                if(!new IPAddressValidator().isValidAbsoluteFilePathFor_RenderFile(absoluteFilePath)){
                	return (null);
                }else{
	                response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTHTML);
	                //--> with browser dialogbox: response.setHeader ("Content-disposition", "attachment; filename=\"edifactPayload.txt\"");
	                response.setHeader ("Content-disposition", "filename=\"edifactPayload.txt\"");
	                
	                logger.info("Start flushing file payload...");
	                //send the file output to the ServletOutputStream
	                try{
	                	payloadContentFlusher.flushServletOutput(response, absoluteFilePath);
	                }catch (Exception e){
	                	e.printStackTrace();
	                }
                }
            }
        	//this to present the output in an independent window
            return(null);
			
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

