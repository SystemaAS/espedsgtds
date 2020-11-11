package no.systema.main.service;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import no.systema.jservices.common.dao.SvlthDao;
import no.systema.main.model.SystemaWebUser;

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
	    		pdfService.setFileBasePath(appUser.getUser());
		    	File fbp = new File(pdfService.getFileBasePath());
		    	
		    	if(fbp.exists()){
		    		//---------------------
		    		//STEP (1) create PDF
		    		//---------------------		    		
		    		String absoluteFileName = pdfService.createPdf(dao);
		    		//---------------------
		    		//STEP (2) archive PDF
		    		//---------------------
		    		if(StringUtils.isNotEmpty(FilenameUtils.getName(absoluteFileName)) && FilenameUtils.getName(absoluteFileName).length()<=40){
		    			archiveService.save(appUser, absoluteFileName, dao);
		    		}else{
		    			logger.error("ERROR: NULL or invalid length(>40 chars):" + absoluteFileName );
		    		}
		    		//---------------------------------
		    		//STEP (3) send mail to tullverket
		    		//---------------------------------
		    		boolean avvikelseFlag = false;
		    		if(FilenameUtils.getName(absoluteFileName).toLowerCase().contains("avvikelse")){
		    			//avvikelse type has a different target email address
		    			avvikelseFlag = true;
		    		}
		    		emailService.sendMail(getEmailSubject(absoluteFileName), EMAIL_TEXT_TILLFALLIGLAGRING, avvikelseFlag, absoluteFileName);
		    	}else{
		    		logger.error("ERROR:" + pdfService.getFileBasePath() + " does not exist");
		    	}
	    	}
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		
	}
	private String getEmailSubject(String absoluteFileName){
		String fileName = FilenameUtils.getName(absoluteFileName);
		int i = fileName.indexOf(".");
		
		return fileName.substring(0,i);
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
	
}
