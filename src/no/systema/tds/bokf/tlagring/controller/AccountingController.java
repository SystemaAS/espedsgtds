package no.systema.tds.bokf.tlagring.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import no.systema.jservices.common.dao.SvlthDao;
import no.systema.jservices.common.dto.SvlthDto;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
import no.systema.jservices.common.util.DateTimeManager;
import no.systema.jservices.common.util.StringUtils;
import no.systema.jservices.common.values.CRUDEnum;
import no.systema.jservices.common.values.EventTypeEnum;
import no.systema.main.mapper.url.request.UrlRequestParameterMapper;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.validator.LoginValidator;

/**
 * Controller  for Tillfällig lagring.
 * 
 * @author Fredrik Möller
 * @date Mar 29 , 2019
 * 
 */
@Controller
public class AccountingController {
	private static Logger logger = Logger.getLogger(AccountingController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private LoginValidator loginValidator = new LoginValidator();
	
	@Autowired
	RestTemplate restTemplate;
	

	@RequestMapping(value="accounting_list.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doList(HttpSession session, HttpServletRequest request) {
		ModelAndView successView = new ModelAndView("accounting_list");
		SystemaWebUser appUser = loginValidator.getValidUser(session);

		if (appUser == null) {
			return loginView;
		} else {
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_ACCOUNTING);

			return successView;
		}

	}

	
	@RequestMapping(value="accounting_inlagg.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doInlagg(@RequestParam(value = "svlth_irn", required = false) String svlth_irn,
								@ModelAttribute ("record") SvlthDao record, 
								@RequestParam(value = "action", required = true) Integer action,
								BindingResult bindingResult, HttpSession session, HttpServletRequest request){

		SystemaWebUser appUser = loginValidator.getValidUser(session);		
		if (appUser == null) {
			return loginView;
		}		
		
		logger.info("accounting_inlagg.do, record="+ReflectionToStringBuilder.reflectionToString(record, ToStringStyle.MULTI_LINE_STYLE));
		logger.info("action="+action);
		
		ModelAndView successView =  new ModelAndView("accounting_inlagg");
		SvlthDto returnDto = new SvlthDto();

		if (action.equals(CRUDEnum.CREATE.getValue()) && record.getSvlth_h() == null) {
			logger.info("Init...");
			successView.addObject("action", CRUDEnum.CREATE.getValue());
			
			return successView;
		} 		
		
		try {

			if (action.equals(CRUDEnum.CREATE.getValue())) {
				logger.info("Create...");

				setDate(EventTypeEnum.INLAGG, record);
				saveRecord(appUser, record, "A");

				returnDto = fetchRecord(appUser, record);
				successView.addObject("record", returnDto);
				successView.addObject("action", CRUDEnum.READ.getValue());

			} else if (action.equals(CRUDEnum.UPDATE.getValue())) {
				throw new IllegalAccessError("Updates not allowed!");

			} else if (action.equals(CRUDEnum.READ.getValue())) {
				logger.info("Read...");
				returnDto = fetchRecord(appUser, record);
				successView.addObject("record", returnDto);
				successView.addObject("action", CRUDEnum.READ.getValue());

			} else if (action.equals(CRUDEnum.DELETE.getValue())) {
				throw new IllegalAccessError("Delete not allowed!");

			}
			
			successView.addObject("svlth_irn", svlth_irn);
			return successView;			
			

		} catch (Throwable e) {
			logger.error("ERROR:", e);
			successView.addObject("action", action);
			successView.addObject("error", e.getMessage());
			successView.addObject("svlth_irn", svlth_irn);
			return successView;			

		}

	}

	@RequestMapping(value="accounting_uttag_list.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doUttagList(@RequestParam(value = "svlth_irn", required = true) String svlth_irn,
									HttpSession session, HttpServletRequest request) {
		ModelAndView successView = new ModelAndView("accounting_uttag");
		SystemaWebUser appUser = loginValidator.getValidUser(session);
		SvlthDto headDto;
		
		if (appUser == null) {
			return loginView;
		} else {

			try {
				headDto = fetchRecord(appUser, svlth_irn);
				successView.addObject("headRecord", headDto);
				successView.addObject("action", CRUDEnum.CREATE.getValue());
				appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_ACCOUNTING);

				successView.addObject("svlth_irn", svlth_irn);
				return successView;
				
			} catch (Throwable e) {
				logger.error("ERROR:", e);
				successView.addObject("error", e.getMessage());
				successView.addObject("svlth_irn", svlth_irn);
				return successView;
			}


		}

	}
	
	@RequestMapping(value="accounting_uttag.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doUttag( @ModelAttribute ("record") SvlthDao record, 
								@RequestParam(value = "action", required = true) Integer action,
								@RequestParam(value = "svlth_irn", required = false) String svlth_irn,
								HttpSession session, HttpServletRequest request){

		SystemaWebUser appUser = loginValidator.getValidUser(session);		
		if (appUser == null) {
			return loginView;
		}		
		
		logger.info("accounting_uttag.do, record="+ReflectionToStringBuilder.reflectionToString(record));
		logger.info("action="+action);

		ModelAndView successView =  new ModelAndView("accounting_uttag");
		SvlthDto returnDto = new SvlthDto();

		try {

			if (action.equals(CRUDEnum.CREATE.getValue())) {
				logger.info("Create...");

				setDate(EventTypeEnum.UTTAG, record);
				saveRecord(appUser, record, "A");

				successView.addObject("action", CRUDEnum.READ.getValue());

			} else if (action.equals(CRUDEnum.UPDATE.getValue())) {
				throw new IllegalAccessError("Updates not allowed!");

			} else if (action.equals(CRUDEnum.READ.getValue())) {
				logger.info("Read...");
				returnDto = fetchRecord(appUser, record);
				successView.addObject("svlth_irn", svlth_irn);
				successView.addObject("record", returnDto);

				successView.addObject("action", CRUDEnum.READ.getValue());

			} else if (action.equals(CRUDEnum.DELETE.getValue())) {
				throw new IllegalAccessError("Delete not allowed!");

			}

			successView.addObject("svlth_irn", svlth_irn);
			return successView;			
			
		} catch (Throwable e) {
			logger.error("ERROR:", e);
			successView.addObject("action", action);
			successView.addObject("error", e.getMessage());
			successView.addObject("svlth_irn", svlth_irn);
			return successView;

		}

	}
	
	private SvlthDto fetchRecord(SystemaWebUser appUser, SvlthDao record) {
		logger.info("::fetchRecord::");
		SvlthDto dao = getHeadDto(appUser.getUser(), record.getSvlth_irn());
		
		return dao;
		
	}	

	private SvlthDto fetchRecord(SystemaWebUser appUser, String Svlth_irn) {
		logger.info("::fetchRecord::");
		SvlthDto dto = getHeadDto(appUser.getUser(), Svlth_irn);
		
		return dto;
		
	}	
	
	
	private SvlthDto getHeadDto(String user, String mrn) {
		JsonReader<JsonDtoContainer<SvlthDto>> jsonReader = new JsonReader<JsonDtoContainer<SvlthDto>>();
		jsonReader.set(new JsonDtoContainer<SvlthDto>());
		SvlthDto dto;

		String BASE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSVLTH.do";	
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("?user=" + user);
		urlRequestParams.append("&svlth_h=" + EventTypeEnum.INLAGG.getValue());
		urlRequestParams.append("&svlth_irn=" + mrn);
		logger.info("Full url: " + BASE_URL +urlRequestParams.toString());

		ResponseEntity<String> response = restTemplate.exchange(BASE_URL + urlRequestParams.toString(), HttpMethod.GET, null, String.class);
		String jsonPayload = response.getBody();		
		logger.info("jsonPayload="+jsonPayload);
		
		JsonDtoContainer<SvlthDto> container = (JsonDtoContainer<SvlthDto>) jsonReader.get(jsonPayload);
		if (container != null) {
			if (StringUtils.hasValue(container.getErrMsg())) {
				String errMsg = String.format("DML-error on inlagg, mrn: %s. Error message: %s", mrn, container.getErrMsg()) ;
				logger.error(errMsg);
				throw new RuntimeException(container.getErrMsg());
			}		
			List<SvlthDto> list = container.getDtoList();
			if (list.isEmpty() || list.size() != 1){
				String errMsg = String.format("Expecting SvlthDao in return! DML-error on bilag, mrn: %s. Error message: %s", mrn, container.getErrMsg()) ;
				logger.error(errMsg);
				throw new RuntimeException(errMsg);
			} else {
				dto = list.get(0);
				return  dto;

			}
		}
		
		return null;
		
	}
	

	private void saveRecord(SystemaWebUser appUser, SvlthDao record, String mode) {
		logger.info("saveRecord::record::"+ReflectionToStringBuilder.toString(record));
		String SVLTH_DML_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSVLTH_U.do";

		MultiValueMap<String, String> recordParams = UrlRequestParameterMapper.getUriParameter(record);
	    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(SVLTH_DML_URL)
		        .queryParam("user", appUser.getUser())
		        .queryParam("mode", mode)
		        .queryParam("lang", appUser.getUsrLang())
		        .queryParams(recordParams);
		URI uri = builder.buildAndExpand().toUri();
		logger.info("Full uri="+uri);
		
		ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
		String body = response.getBody();		
		logger.info("body="+body);

		JsonReader<JsonDtoContainer<SvlthDao>> jsonReader = new JsonReader<JsonDtoContainer<SvlthDao>>();
		jsonReader.set(new JsonDtoContainer<SvlthDao>());
		JsonDtoContainer<SvlthDao> container = (JsonDtoContainer<SvlthDao>) jsonReader.get(body);
		if (container != null) {
			if (StringUtils.hasValue(container.getErrMsg())) {
				String errMsg = String.format("DML-error on inlagg, mrn: %s. Error message: %s", record.getSvlth_irn(), container.getErrMsg()) ;
				logger.info(errMsg);
				throw new RuntimeException(container.getErrMsg());
			}		
		}
		
	}	

	private void setDate(EventTypeEnum eventType, SvlthDao record) {
		int[] dato = DateTimeManager.getNowDato();		

		if (eventType == EventTypeEnum.INLAGG) {
			record.setSvlth_id1(dato[0]);
			record.setSvlth_im1(dato[1]);
		} else if (eventType == EventTypeEnum.UTTAG) {
			record.setSvlth_ud1(dato[0]);
			record.setSvlth_um1(dato[1]);
		}
		
	}

}

