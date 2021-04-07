package no.systema.main.service;

import java.io.File;
import java.util.Calendar;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import no.systema.jservices.common.dao.SvlthDao;
import no.systema.main.model.SystemaWebUser;
import no.systema.tds.nctsimport.model.jsonjackson.topic.JsonNctsImportTopicListContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.JsonNctsImportTopicListRecord;
import no.systema.tds.nctsimport.model.jsonjackson.topic.unloading.JsonNctsImportSpecificTopicUnloadingContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.unloading.JsonNctsImportSpecificTopicUnloadingRecord;
import no.systema.tds.nctsimport.service.NctsImportSpecificTopicUnloadingService;
import no.systema.tds.nctsimport.service.NctsImportTopicListService;
import no.systema.tds.nctsimport.url.store.UrlDataStore;

/**
 * This class is the manager class dealing with the Temporary Solution for Tillfällig Lagring.
 * The temporary solution consists in sending a PDF by email. The permanent solution will be full EDI (xml or other) and by means of FTP.
 * 
 * This service orchestrates the sending of a PDF by email. It consits of an atomic-execution of the following steps
 * (1) Manufacture the PDF (with the correct name convention)
 * (2) Archive the PDF (in the db-table ARKIVP)
 * (3) Send the email with the attachment (either a full DTL-file or an Avvikelse-file)
 *  
 * @author oscardelatorre
 * @date Nov 2020
 */
@Service
public class TillfalligLagringTempSolutionService {

	@Autowired
	EmailService emailService;
	
	@Autowired
	PdfiTextService pdfService;
	
	@Autowired
	ArchiveService archiveService;
	
	@Autowired	
	UrlCgiProxyService urlCgiProxyService;
	
	@Autowired
	NctsImportSpecificTopicUnloadingService nctsImportSpecificTopicUnloadingService;
	
	@Autowired
	NctsImportTopicListService nctsImportTopicListService;
	
	
	@Value("${tlagring.activate.usecase.send.file.dtl:1}")
    private Integer sendFileDtlActivated;
	
	
	@Value("${tlagring.activate.usecase.send.file.avvikelse:1}")
    private Integer sendFileAvvikelseActivated;
	
	private static final Logger logger = Logger.getLogger(TillfalligLagringTempSolutionService.class.getName());
	private static final String EMAIL_TEXT_TILLFALLIGLAGRING = "Tillfällig lagring";
	/**
	 * Entry point for the entire Use-Case
	 * 
	 * @param dao
	 * @param appUser
	 */
	public void execute(SvlthDao dao, SystemaWebUser appUser){
		try{
	    	//Only inlägg(I) or rättelse(R) and only when their are activated. We can deactivate one or both.
	    	if(isActiveDtlUseCase(dao) || isActiveAvvikelseUseCase(dao)){
		    	//init directory for file on disk
	    		pdfService.setFileBasePath(appUser.getArchiveRootPath() + PdfiTextService.BASE_DIR_TILLFALLIG_LAGRING_ARCHIVE);
		    	File fbp = new File(pdfService.getFileBasePath());
		    	
		    	if(fbp.exists()){
		    		//---------------------
		    		//STEP (1) create PDF
		    		//---------------------
		    		JsonNctsImportTopicListRecord daoTmp = this.getAvdOpdTNCI000R(appUser.getUser(), dao.getSvlth_irn());
		    		JsonNctsImportSpecificTopicUnloadingRecord auxDao = this.getAuxDaoTNCI005R(appUser.getUser(), daoTmp);
		    		//logger.warn(auxDao);
		    		Map<String, String> map = pdfService.createPdf(dao, auxDao);
		    		//---------------------
		    		//STEP (2) archive PDF
		    		//---------------------
		    		String absoluteFileName = map.get(PdfiTextService.MAP_KEY_FILE_NAME);
		    		String emailSubject = map.get(PdfiTextService.MAP_KEY_EMAIL_SUBJECT);
		    		if(StringUtils.isNotEmpty(FilenameUtils.getName(absoluteFileName)) && FilenameUtils.getName(absoluteFileName).length()<=40){
		    			archiveService.save(appUser, absoluteFileName, dao);
		    		}else{
		    			logger.error("ERROR: NULL or invalid length(>40 chars):" + absoluteFileName);
		    		}
		    		//---------------------------------
		    		//STEP (3) send mail to tullverket
		    		//---------------------------------
		    		boolean avvikelseFlag = false;
		    		if(FilenameUtils.getName(absoluteFileName).toUpperCase().contains("AVV")){
		    			//avvikelse type has a different target email address
		    			avvikelseFlag = true;
		    		}
		    		emailService.sendMail(emailSubject, EMAIL_TEXT_TILLFALLIGLAGRING, avvikelseFlag, absoluteFileName);
		    	}else{
		    		logger.error("ERROR:" + pdfService.getFileBasePath() + " does not exist");
		    	}
	    	}
	    }catch(Exception e){
	    	logger.error("ERROR:" + e.toString());
	    	e.printStackTrace();
	    }
		
	}
	
	/**
	 * 
	 * @param dao
	 * @return
	 */
	private boolean isActiveDtlUseCase(SvlthDao dao){
		boolean retval = false;
		if(PdfiTextService.TYPE_H_INLAGG.equals(dao.getSvlth_h()) && this.sendFileDtlActivated == 1){
			retval = true;
		}
		return retval;
	}
	/**
	 * 
	 * @param dao
	 * @return
	 */
	private boolean isActiveAvvikelseUseCase(SvlthDao dao){
		boolean retval = false;
		if(PdfiTextService.TYPE_H_RATTELSE.equals(dao.getSvlth_h()) && this.sendFileAvvikelseActivated == 1){
			retval = true;
		}
		return retval;
	}
	
	/**
	 * Get auxiliary fields not found in main svlth-dao
	 * 
	 * @param applicationUser
	 * @param avd
	 * @param opd
	 * @return
	 */
	private JsonNctsImportSpecificTopicUnloadingRecord getAuxDaoTNCI005R(String applicationUser, JsonNctsImportTopicListRecord dto) throws Exception{
		JsonNctsImportSpecificTopicUnloadingRecord dao = new JsonNctsImportSpecificTopicUnloadingRecord() ;
		if(dto!=null){
			String BASE_URL = UrlDataStore.NCTS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_UNLOADING_URL;
			//url params
			String urlRequestParamsKeys = "user=" + applicationUser + "&avd=" + dto.getAvd() + "&opd=" + dto.getOpd();
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.warn("URL: " + BASE_URL);
	    	logger.warn("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
	    	logger.debug(jsonPayload);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		JsonNctsImportSpecificTopicUnloadingContainer container = this.nctsImportSpecificTopicUnloadingService.getNctsImportSpecificTopicUnloadingContainer(jsonPayload);
	    		if(container!=null && container.getOneorder()!=null){
	    			for(JsonNctsImportSpecificTopicUnloadingRecord record: container.getOneorder() ){
	    				dao = record;
	    			}
	    		}
	    	}
		}else{
			logger.error("ERROR MRNnr does not exists in NCTS-Import. It is mandatory for DTL-PDF");
		}
    	return dao;
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param mrn
	 * @return
	 */
	private JsonNctsImportTopicListRecord getAvdOpdTNCI000R(String applicationUser, String mrn) throws Exception{
		JsonNctsImportTopicListRecord daoAvdOpd = null;
		//get BASE URL
		final String BASE_URL = UrlDataStore.NCTS_IMPORT_BASE_TOPICLIST_URL;
		//add URL-parameters
		String urlRequestParamsKeys = "user=" + applicationUser + "&mrn=" + mrn;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_URL);
    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
    	//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	
		if(jsonPayload!=null){
			JsonNctsImportTopicListContainer container = this.nctsImportTopicListService.getNctsImportTopicListContainer(jsonPayload);
			if(container!=null && container.getOrderList()!=null){
				for(JsonNctsImportTopicListRecord record: container.getOrderList()){
					daoAvdOpd = record;
				}
			}
		}
		return daoAvdOpd;
	}
	
}
