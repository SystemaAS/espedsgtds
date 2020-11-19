package no.systema.main.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.systema.jservices.common.dao.ArkivpDao;
import no.systema.jservices.common.dao.SvlthDao;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.tds.model.jsonjackson.JsonTdsArkivpContainer;
import no.systema.tds.tdsexport.mapper.url.request.UrlRequestParameterMapper;

/**
 * This class is used for Tillfallig lagring övergångslösning
 * The archive service deals with the meta data of a given pdf-file on disk. This in order to trace the file when sending it
 * to tullverket via an email
 * 
 * @author oscardelatorre
 * Nov 2020
 */
@Service
public class ArchiveService {
	
	@Autowired	
	private UrlCgiProxyService urlCgiProxyService;
	
	@Autowired
	private FirmArcService firmArcService;
	
	private static final Logger logger = Logger.getLogger(ArchiveService.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	/**
	 * 
	 * @param appUser
	 * @param absoluteFileName
	 * @param dao
	 */
	public void save(SystemaWebUser appUser, String absoluteFileName, SvlthDao dao){
		try{
			File tmp = new File(absoluteFileName);
			logger.warn("Absolute file name:" + absoluteFileName);
			logger.warn("SvlthDao:" + dao);
			
			if(tmp.exists() && dao!=null){
				//handover from dao to dto(arkivp)
				ArkivpDao dto = handover(appUser, absoluteFileName, dao);
				logger.warn(dto.toString());
				String BASE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsARKIVP_U.do";
				
				String urlRequestParamsKey = "user=" + appUser.getUser() + "&mode=A";
				String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((dto));
				String urlParams = urlRequestParamsKey + urlRequestParams;
				logger.warn("URL" + BASE_URL);
				logger.warn("PARAMs:" + urlParams);
				//Execute rest-service
				String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlParams);
				logger.info("jsonPayload on request: " + jsonPayload);
	    		if(jsonPayload!=null){
	    			JsonTdsArkivpContainer container = this.firmArcService.getArkivpContainer(jsonPayload);
	    			if(container!=null){
	    				if(StringUtils.isNotEmpty(container.getErrMsg())){
	    					logger.error("ERROR<insert>: " + container.getErrMsg());
	    				}else{
	    					//OK
	    				}
	    			}
	    		}
			}else{
				logger.error("ERROR:" + absoluteFileName + " not found or dao = NULL ?" );
			}
		}catch(Exception e){
			logger.error("ERROR:" + e.toString());
			e.printStackTrace();
			
		}
		
	}
	/**
	 * Populate dto to send to REST url
	 * @param appUser
	 * @param absoluteFileName
	 * @param dao
	 * @return
	 */
	private ArkivpDao handover(SystemaWebUser appUser, String absoluteFileName, SvlthDao dao) throws Exception{
		ArkivpDao dto = new ArkivpDao();
		DateTimeManager dateTimeMgr = new DateTimeManager();
		
		dto.setArlink(FilenameUtils.getName(absoluteFileName));
		dto.setArdate(dateTimeMgr.getCurrentDate_ISO());
		dto.setArtime(dateTimeMgr.getCurrentDate_ISO("HHmmss"));
		dto.setArrfk(dao.getSvlth_ign());//Godsnr
		dto.setArunde(dao.getSvlth_pos());
		dto.setArbhis(dao.getSvlth_irn());//MRN
		String companyCode = appUser.getCompanyCode();
		if(StringUtils.isEmpty(companyCode)){ companyCode = appUser.getFallbackCompanyCode(); }
		dto.setArfirm(companyCode);
		dto.setAruser(appUser.getUser());
		
		return dto;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param dto
	 * @return
	 */
	public Collection<ArkivpDao> getList(SystemaWebUser appUser, ArkivpDao dto){
		Collection<ArkivpDao> result = new ArrayList<ArkivpDao>();
		if(dto!=null){
			//handover from dao to dto(arkivp)
			logger.warn(dto.toString());
			String BASE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsARKIVP.do";
			
			String urlRequestParamsKey = "user=" + appUser.getUser();
			String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((dto));
			String urlParams = urlRequestParamsKey + urlRequestParams;
			logger.warn("URL" + BASE_URL);
			logger.warn("PARAMs:" + urlParams);
			//Execute rest-service
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlParams);
			logger.info("jsonPayload on request: " + jsonPayload);
    		if(jsonPayload!=null){
    			JsonTdsArkivpContainer container = this.firmArcService.getArkivpContainer(jsonPayload);
    			if(container!=null){
    				if(StringUtils.isNotEmpty(container.getErrMsg())){
    					logger.error("ERROR<insert>: " + container.getErrMsg());
    				}else{
    					result = container.getList();
    				}
    			}
    		}
		}else{
			logger.error("ERROR:dao = NULL ?" );
		}
		logger.warn(result);
		return result;
		
	}
}
