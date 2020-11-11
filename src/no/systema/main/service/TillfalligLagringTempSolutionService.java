package no.systema.main.service;

import java.io.File;

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
	
	@Value("${tlagring.activate.usecase.send.file.dtl:1}")
    private Integer sendFileDtlActivated;
	
	
	@Value("${tlagring.activate.usecase.send.file.avvikelse:1}")
    private Integer sendFileAvvikelseActivated;
	

	
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
		    	pdfService.setFileBasePath(appUser.getUser());
		    	File fbp = new File(pdfService.getFileBasePath());
		    	
		    	if(fbp.exists()){
		    		pdfService.createPdf(dao);
		    		//emailService.sendMail("SimpleEmail Testing Subject", "Tillfällig lagring", false, "/ownfiles/hello_world.pdf");
		    	}
	    	}
	    }catch(Exception e){
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
	
}
