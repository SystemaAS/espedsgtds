package no.systema.tds.accounting.controller;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import no.systema.jservices.common.dao.SvlthDao;
import no.systema.jservices.common.dto.SvlthDto;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
import no.systema.jservices.common.util.CommonResponseErrorHandler;
import no.systema.jservices.common.util.DateTimeManager;
import no.systema.jservices.common.util.StringUtils;
import no.systema.jservices.common.values.CRUDEnum;
import no.systema.jservices.common.values.EventTypeEnum;
import no.systema.main.mapper.url.request.UrlRequestParameterMapper;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.EncodingTransformer;
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


	/**
	 * Explicit settings of Jackson ObjectMapper.
	 * <li> Support Java 8 LocalDateTime.</li>
	 * <li> Indent output </li>
	 * <li> Exlude properties with null vales</li>
	 * 
	 * @return ObjectMapper
	 */
    @Bean
    @Primary
    public ObjectMapper objectMapper() {
    	return Jackson2ObjectMapperBuilder.json()
	            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) //ISODate
	            .createXmlMapper(false)
	            .featuresToEnable(SerializationFeature.INDENT_OUTPUT) //nicer output
	            .serializationInclusion(Include.NON_NULL) //exclude null values
	            .build();
    }	
	
	
	/**
	 * Initialize  {@linkplain RestTemplate} with {@linkplain StringHttpMessageConverter(Charset.forName("UTF-8"))}
	 * 
	 * @return RestTemplate
	 */
    @Bean
	public RestTemplate restTemplate(){
    	RestTemplate restTemplate = new RestTemplate(Arrays.asList(
    			new StringHttpMessageConverter(StandardCharsets.UTF_8),
    			new MappingJackson2HttpMessageConverter(objectMapper()))
    			);
		restTemplate.setErrorHandler(new CommonResponseErrorHandler());

		return restTemplate;  
	}	
	
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
				setGodsnummer(EventTypeEnum.INLAGG, record);
				saveRecord(appUser, record, "A");

				returnDto = fetchRecord(appUser, record);
				successView.addObject("record", returnDto);
				successView.addObject("action", CRUDEnum.READ.getValue());

			} else if (action.equals(CRUDEnum.UPDATE.getValue())) {
				throw new IllegalAccessError("Updates not allowed!");

			} else if (action.equals(CRUDEnum.READ.getValue())) {
				logger.info("Read...");
				record.setSvlth_h(EventTypeEnum.INLAGG.getValue());
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
				headDto = fetchRecord(appUser, svlth_irn, EventTypeEnum.INLAGG.getValue(), null, null);
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
			SvlthDto headDto = fetchRecord(appUser, svlth_irn, EventTypeEnum.INLAGG.getValue(), null, null);
			successView.addObject("headRecord", headDto);

			return successView;			
			
		} catch (Throwable e) {
			logger.error("ERROR:", e);
			successView.addObject("action", action);
			successView.addObject("error", e.getMessage());
			successView.addObject("svlth_irn", svlth_irn);
			SvlthDto headDto = fetchRecord(appUser, svlth_irn, EventTypeEnum.INLAGG.getValue(), null, null);
			successView.addObject("headRecord", headDto);

			return successView;

		}

	}
	

	@RequestMapping(value="accounting_rattelse.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doRattelse( @ModelAttribute ("record") SvlthDao record, 
									@RequestParam(value = "action", required = true) Integer action,
									@RequestParam(value = "h_svlth_irn", required = true) String h_svlth_irn,
									@RequestParam(value = "h_svlth_h", required = true) String h_svlth_h,
									@RequestParam(value = "h_svlth_id1", required = true) String h_svlth_id1,
									@RequestParam(value = "h_svlth_ud1", required = false) Integer h_svlth_ud1,
									@RequestParam(value = "h_svlth_um1", required = false) Integer h_svlth_um1,
									HttpSession session, HttpServletRequest request){

		SystemaWebUser appUser = loginValidator.getValidUser(session);		
		if (appUser == null) {
			return loginView;
		}		
		
		logger.info("accounting_rattelse.do, record="+ReflectionToStringBuilder.reflectionToString(record));
		logger.info("action="+action);

		
		ModelAndView successView =  new ModelAndView("accounting_rattelse");
		SvlthDto returnDto = new SvlthDto();
		
		
		if (action.equals(CRUDEnum.CREATE.getValue()) && record.getSvlth_rty() == null) {
			logger.info("Init...");
			successView.addObject("action", CRUDEnum.CREATE.getValue());
			
			SvlthDto headDto = fetchRecord(appUser, h_svlth_irn, h_svlth_h, h_svlth_ud1, h_svlth_um1);
			successView.addObject("headRecord", headDto);
			
			return successView;
		} 		
		
		try {

			if (action.equals(CRUDEnum.CREATE.getValue())) {
				logger.info("Create...");
				saveRecord(appUser, record, "A");

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

			SvlthDto headDto = fetchRecord(appUser, h_svlth_irn, h_svlth_h, h_svlth_ud1, h_svlth_um1);
			successView.addObject("headRecord", headDto);

			return successView;			
			
		} catch (Throwable e) {
			logger.error("ERROR:", e);
			successView.addObject("action", action);
			successView.addObject("error", e.getMessage());
			successView.addObject("svlth_irn", h_svlth_irn);
			SvlthDto headDto = fetchRecord(appUser, h_svlth_irn, h_svlth_h, h_svlth_ud1, h_svlth_um1);
			successView.addObject("headRecord", headDto);

			return successView;

		}

	}
	
	private SvlthDto fetchRecord(SystemaWebUser appUser, SvlthDao record) {
		logger.info("::fetchRecord::");
		SvlthDto dao = getHeadDto(appUser.getUser(), record.getSvlth_irn(), record.getSvlth_h(), record.getSvlth_ud1(), record.getSvlth_um1());
		
		return dao;
		
	}	

	private SvlthDto fetchRecord(SystemaWebUser appUser, String svlth_irn, String svlth_h, Integer svlth_ud1, Integer svlth_um1) {
		logger.info("::fetchRecord::");
		SvlthDto dto = getHeadDto(appUser.getUser(), svlth_irn, svlth_h, svlth_ud1, svlth_um1);
		
		return dto;
		
	}	
	
	private SvlthDto getHeadDto(String user, String mrn, String svlth_h, Integer svlth_ud1, Integer svlth_um1)  {
		EncodingTransformer transformer = new EncodingTransformer();
		JsonReader<JsonDtoContainer<SvlthDto>> jsonReader = new JsonReader<JsonDtoContainer<SvlthDto>>();
		jsonReader.set(new JsonDtoContainer<SvlthDto>());
		SvlthDto dto;

		String BASE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSVLTH.do";	
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("?user=" + user);
		urlRequestParams.append("&svlth_h=" + svlth_h);
		urlRequestParams.append("&svlth_irn=" + mrn);
		if (svlth_h.equals(EventTypeEnum.UTTAG.getValue())) {
			urlRequestParams.append("&svlth_ud1=" + svlth_ud1);
			urlRequestParams.append("&svlth_um1=" + svlth_um1);
		}
		
		logger.info("Full url: " + BASE_URL +urlRequestParams.toString());
		
		ResponseEntity<String> response = null;
		try {
			response = restTemplate().exchange(BASE_URL + transformer.transformToJSONTargetEncoding(urlRequestParams.toString(), "UTF8"), HttpMethod.GET, null, String.class);
		}  catch (Exception e) {
			logger.error("Transforming did not work on "+urlRequestParams.toString());
			throw new RuntimeException("Transforming did not work, e", e);
		}
		String jsonPayloadResponse = response.getBody().toString();		
		logger.info("jsonPayloadResponse="+jsonPayloadResponse);

		String jsonPayload = null;
		try {
			jsonPayload = transformer.transformToJSONTargetEncoding(jsonPayloadResponse, "UTF8");
			logger.info("jsonPayload="+jsonPayload);		
		} catch (Exception e) {
			logger.error("Transforming did not work on "+urlRequestParams.toString());
			throw new RuntimeException("Transforming did not work, e", e);
		}
		
		JsonDtoContainer<SvlthDto> container = (JsonDtoContainer<SvlthDto>) jsonReader.get(jsonPayload);
		if (container != null) {
			if (StringUtils.hasValue(container.getErrMsg())) {
				String errMsg = String.format("READ-error on inlagg, mrn: %s. Error message: %s", mrn, container.getErrMsg()) ;
				logger.error(errMsg);
				throw new RuntimeException(container.getErrMsg());
			}		
			List<SvlthDto> list = container.getDtoList();
			if (list.isEmpty() || list.size() != 1){
				String errMsg = String.format("Expecting SvlthDao in return! DML-error on bilag") ;
				String inParams = String.format("mrn: %s ,  svlth_h: %s , svlth_ud1: %s , svlth_um1: %s", mrn, svlth_h, svlth_ud1, svlth_um1 ) ;
				logger.error(errMsg + "list.size="+list.size());
				throw new RuntimeException(errMsg + inParams);
			} else {
				dto = list.get(0);
				return  dto;
			}
		} else {
			String errMsg = String.format("Container is null. Could not found SvlthDto on mrn: %s , jsonPayload %s", mrn, jsonPayload) ;
			throw new RuntimeException(errMsg);
		}
		
	}
	

	private void saveRecord(SystemaWebUser appUser, SvlthDao record, String mode) {
		logger.info("saveRecord::record::"+ReflectionToStringBuilder.toString(record));
		EncodingTransformer transformer = new EncodingTransformer();
		String SVLTH_DML_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSVLTH_U.do";

		MultiValueMap<String, String> recordParams = UrlRequestParameterMapper.getUriParameter(record);
	    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(SVLTH_DML_URL)
		        .queryParam("user", appUser.getUser())
		        .queryParam("mode", mode)
		        .queryParam("lang", appUser.getUsrLang())
		        .queryParams(recordParams);
		URI uri = builder.buildAndExpand().encode().toUri();
		logger.info("Full uri="+uri);
		
		ResponseEntity<String> response = restTemplate().exchange(uri, HttpMethod.GET, null, String.class);
		String jsonPayloadResponse = response.getBody();		
		logger.info("jsonPayloadResponse="+jsonPayloadResponse);
		
		String jsonPayload = null;
		try {
			jsonPayload = transformer.transformToJSONTargetEncoding(jsonPayloadResponse, "UTF8");
			logger.info("jsonPayload="+jsonPayload);		
		} catch (Exception e) {
			logger.error("Transforming did not work on "+jsonPayloadResponse);
			throw new RuntimeException("Transforming did not work, e", e);
		}		
		
		JsonReader<JsonDtoContainer<SvlthDao>> jsonReader = new JsonReader<JsonDtoContainer<SvlthDao>>();
		jsonReader.set(new JsonDtoContainer<SvlthDao>());
		JsonDtoContainer<SvlthDao> container = (JsonDtoContainer<SvlthDao>) jsonReader.get(jsonPayload);
		if (container != null) {
			if (StringUtils.hasValue(container.getErrMsg())) {
				String errMsg = String.format("SAVE-error on inlagg, mrn: %s. Error message: %s", record.getSvlth_irn(), container.getErrMsg()) ;
				logger.error(errMsg);
				throw new IllegalArgumentException(container.getErrMsg());
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

	private void setGodsnummer(EventTypeEnum eventType, SvlthDao record) {
		if (eventType == EventTypeEnum.INLAGG) {
			record.setSvlth_ign(record.getSvlth_igl() + record.getSvlth_ign());
		}
	}	
	
	
	
}

