package no.systema.tds.z.maintenance.main.controller;

import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

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

import no.systema.pgpengine.*;
import no.systema.pgpengine.sub.DecryptMain;
import no.systema.tds.z.maintenance.main.model.MaintenanceMainTulltaxaObject;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtlvContainer;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtlvRecord;
import no.systema.tds.z.maintenance.main.service.MaintTulltaxaService;
import no.systema.tds.z.maintenance.main.url.store.MaintenanceUrlDataStore;
import no.systema.tds.z.maintenance.main.util.TdsMaintenanceConstants;
import no.systema.main.service.UrlCgiProxyService;
//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.ApplicationPropertiesUtil;
import no.systema.main.util.StringManager;
//models


/**
 * Class for the TDS Maintenance Application for Tulltaxa Fildistribution PGP
 * 
 * 
 * @author oscardelatorre
 * @date Okt 23, 2019
 * 
 * 	
 */

@Controller
public class TdsMaintenanceTulltaxaPgpController {
	private static final Logger logger = Logger.getLogger(TdsMaintenanceTulltaxaPgpController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	
	private StringManager strMgr = new StringManager();
	
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="tdsmaintenancefelles_tulltaxa_pgp.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView runTulltaxa_pgp(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenance_childwindow_tulltaxa_result");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//appUser.setActiveMenu("INIT");
			logger.warn("Inside method: runTulltaxa_pgp");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			
			final String PUBLIC_KEY_RING = ApplicationPropertiesUtil.getProperty("pgp.tulltaxa.cert.pubkey");
			final String PRIVATE_KEY_RING = ApplicationPropertiesUtil.getProperty("pgp.tulltaxa.cert.privkey");
			final String SECRET_KEY_RING_PASS = ApplicationPropertiesUtil.getProperty("pgp.tulltaxa.cert.seckey");
			
			final String _1Dir = request.getParameter("lnkpgp");
			final String _2Dir = request.getParameter("lnkzip");
			final String _3Dir = request.getParameter("lnkunzip");
			
			DecryptMain engineDecrypt = new DecryptMain();
			PgpDecompressor decompressor = new PgpDecompressor();
			if(Files.exists(Paths.get(_1Dir)) && Files.exists(Paths.get(_2Dir)) && Files.exists(Paths.get(_3Dir))){
				//------
				//STEP 1 - Decrypt (we decrypt all first in order to have bulks in the pipe process)
				//------
				try (Stream<Path> paths = Files.walk(Paths.get(_1Dir))) {
					logger.warn("Starting decryption ...");
		    	    paths
		    	        .filter(Files::isRegularFile)
		    	        .forEach( e ->{
		    	        		if(!e.getFileName().toString().startsWith(".")){
		    	        			//System.out.println(e);
		        	        		engineDecrypt.runDecryptMain(e, _2Dir, PUBLIC_KEY_RING, PRIVATE_KEY_RING, SECRET_KEY_RING_PASS );
		    	        		}
		    	        	});
		    	 
		    	   //------- 
		    	   //STEP 2 - Decompress (decompress in a bulk: after all decrypted files have been processed)
		    	   //------- 
		    	  
		    	   logger.warn("Start decompression ..."); 
		    	   decompressor.decompress(Paths.get(_2Dir), _3Dir);
		    	   logger.warn("End decompression ...");
		    	   
				}catch (Exception e) { 
				   logger.warn("Exception: " + e); 
				   model.put("error", "Exception: " + e);
			    } 
			}else{
			   logger.warn("Exception: All directories MUST exist.");	
			   model.put("error", "Exception: All directories MUST exist.");
			}
	    	
			successView.addObject(TdsMaintenanceConstants.DOMAIN_MODEL , model);
			return successView;
			
		}
	}
	/**
	 * The method starts the back-end FTP process that fetches all .pgp files from Tullverket Fildistribution
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsmaintenancefelles_tulltaxa.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView initTulltaxaFtp(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenance_childwindow_tulltaxa");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		final List<String> outputList = new ArrayList<String>();
		
		if(appUser==null){
			return this.loginView;
		}else{
			//appUser.setActiveMenu("INIT");
			logger.warn("Inside method: initTulltaxaFtp");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			
			//START Back-end FTP fetch of files is fired here
			MaintenanceMainTulltaxaObject container = null;
			String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_TULLTAXA_GET_LIST_URL;
			String urlRequestParams = "user=" + appUser.getUser() ;
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	    	//END FTP 
	    	
	    	//At this point we should have Tullverket's pgp files in the SYSPED (AS400) system
	    	//extract
	    	if(jsonPayload!=null){
				//lists
	    		container = this.maintTulltaxaService.getList(jsonPayload);
	    		 
			}
			logger.info(container);
			//String _1Dir = "/ownfiles/tullverketCert/encrypted/";
			if(container!=null && strMgr.isNull(container.getErrMsg()) &&  Files.exists(Paths.get(container.getLnkpgp()))){
				try (Stream<Path> paths = Files.walk(Paths.get(container.getLnkpgp()))) {
					logger.warn("Listing FTP files for decryption ...");
		    	    paths
		    	        .filter(Files::isRegularFile)
		    	        .forEach( e ->{
		    	        		if(!e.getFileName().toString().startsWith(".")){
		    	        			logger.info(e);
		        	        		outputList.add(e.getFileName().toString());
		    	        		}
		    	        	});
			    
				}catch(Exception e){
					logger.warn("Exception: " + e);
				}
				Map model = new HashMap();
				model.put("listpgp", outputList);
				model.put("tulltaxaObject", container);
				successView.addObject(TdsMaintenanceConstants.DOMAIN_MODEL , model);
				
			}else{
				
				if(container!=null){
					logger.warn("errMsg: " + container.getErrMsg());
					logger.warn("File not found:" + container.getLnkpgp());
				}else{
					logger.warn("FATAL File not found: container=null");
				}
			}
			
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
	
	@Qualifier ("maintTulltaxaService")
	private MaintTulltaxaService maintTulltaxaService;
	@Autowired
	@Required
	public void setMaintTulltaxaService (MaintTulltaxaService value){ this.maintTulltaxaService = value; }
	public MaintTulltaxaService getMaintTulltaxaService(){ return this.maintTulltaxaService; }
	
		
}

