package no.systema.tds.accounting.controller;

import java.io.File;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import lombok.NonNull;
import no.systema.jservices.common.dao.ArkivpDao;
import no.systema.jservices.common.dao.SvlthDao;
import no.systema.jservices.common.dao.SvltuDao;
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
import no.systema.main.service.ArchiveService;
import no.systema.main.service.FirmArcService;
import no.systema.main.service.PdfiTextService;
import no.systema.main.service.TillfalligLagringTempSolutionService;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.EncodingTransformer;
import no.systema.main.util.io.PayloadContentFlusher;
import no.systema.main.validator.IPAddressValidator;
import no.systema.main.validator.LoginValidator;
import no.systema.tds.model.jsonjackson.JsonTdsFirmArcContainer;
import no.systema.tds.util.TdsConstants;

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
	private PayloadContentFlusher payloadContentFlusher = new PayloadContentFlusher();
	
	@Autowired
	WTF wtf;
	
	@Autowired	
	private UrlCgiProxyService urlCgiProxyService;
	
	@Autowired
	private ArchiveService archiveService;
	
	@Autowired
	private FirmArcService firmArcService;
	
	@Autowired
	TillfalligLagringTempSolutionService tlagringTempSolutionService;
	
	
	
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
	public ModelAndView doList(@RequestParam(value = "selectGodslokalkod", required = false) String godslokalkod,
							   HttpSession session, HttpServletRequest request) {
		ModelAndView successView = new ModelAndView("accounting_list");
		ModelAndView modalView = new ModelAndView("accounting_modal_godslokalkod");
		ModelAndView returnView;
		
		SystemaWebUser appUser = loginValidator.getValidUser(session);
		
		
		logger.info("accounting_list.do....");
		logger.info("selectGodslokalkod="+godslokalkod);
		
		
		if (godslokalkod != null) {
			setGodslokalkod(session, godslokalkod);
		}		
		
		if (appUser == null) {
			return loginView;
		} else {
			if (hasGodslokalkod(session)) {
				logger.info("hasGodslokalkod->successView");
				returnView = successView;
				appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_ACCOUNTING);
				
			} else {
				logger.info("NOT hasGodslokalkod->modalView");
				returnView = modalView;	
			}
				
			return returnView;
		}

	}
	/**
	 * Show Archive (Tullverkets Övergångslösning)
	 * @param record
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="accounting_archive.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doListArchive(@ModelAttribute ("record") ArkivpDao record, BindingResult bindingResult, HttpSession session, HttpServletRequest request) {
		ModelAndView successView = new ModelAndView("accounting_archive");
		
		SystemaWebUser appUser = loginValidator.getValidUser(session);
		logger.warn("accounting_archive.do....");
		logger.warn(record.getArrfk());
		if (appUser == null) {
			return loginView;
		} else {
			Collection<ArkivpDao> list = this.archiveService.getList(appUser, record);
			String pdfDir = this.getFileBasePath(appUser.getUser());
			successView.addObject("pdfDir", pdfDir);
			successView.addObject("list", list);
			return successView;
		}

	}
	/**
	 * Render Pdf on Archive (Tullverkets Övergångslösning)
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="accounting_renderArchive.do", method={ RequestMethod.GET })
	public ModelAndView doAccountingRenderArchive(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("Inside doAccountingRenderArchive...");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SIGN_PKI);
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, TdsConstants.ACTIVE_URL_RPG_INITVALUE); 
			String filePath = request.getParameter("fp");
			
			if(filePath!=null && !"".equals(filePath)){
				
                String absoluteFilePath = filePath;
                if(!new IPAddressValidator().isValidAbsoluteFilePathFor_RenderFile(absoluteFilePath)){
                	return (null);
                }else{
	                //must know the file type in order to put the correct content type on the Servlet response.
	                String fileType = this.payloadContentFlusher.getFileType(filePath);
	                if(AppConstants.DOCUMENTTYPE_PDF.equals(fileType)){
	                		response.setContentType(AppConstants.HTML_CONTENTTYPE_PDF);
	                }else if(AppConstants.DOCUMENTTYPE_TIFF.equals(fileType) || AppConstants.DOCUMENTTYPE_TIF.equals(fileType)){
	            			response.setContentType(AppConstants.HTML_CONTENTTYPE_TIFF);
	                }else if(AppConstants.DOCUMENTTYPE_JPEG.equals(fileType) || AppConstants.DOCUMENTTYPE_JPG.equals(fileType)){
	                		response.setContentType(AppConstants.HTML_CONTENTTYPE_JPEG);
	                }else if(AppConstants.DOCUMENTTYPE_TXT.equals(fileType)){
	            			response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTHTML);
	                }else if(AppConstants.DOCUMENTTYPE_DOC.equals(fileType)){
	            			response.setContentType(AppConstants.HTML_CONTENTTYPE_WORD);
	                }else if(AppConstants.DOCUMENTTYPE_XLS.equals(fileType)){
	            			response.setContentType(AppConstants.HTML_CONTENTTYPE_EXCEL);
	                }
	                //--> with browser dialogbox: response.setHeader ("Content-disposition", "attachment; filename=\"edifactPayload.txt\"");
	                response.setHeader ("Content-disposition", "filename=\"archiveDocument." + fileType + "\"");
	                
	                logger.info("Start flushing file payload...");
	                //send the file output to the ServletOutputStream
	                try{
	                		this.payloadContentFlusher.flushServletOutput(response, absoluteFilePath);
	                		//payloadContentFlusher.flushServletOutput(response, "plain text test...".getBytes());
	                	
	                }catch (Exception e){
	                		e.printStackTrace();
	                }
                }
            }
			//this to present the output in an independent window
            return(null);
			
		}
			
	}	
	

	@RequestMapping(value="accounting_inlagg.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doInlagg(@ModelAttribute ("record") SvlthDao record, 
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
		
		String enteredGodsNummer = null;
		
		boolean crap = false;
		
		
		if (action.equals(CRUDEnum.CREATE.getValue()) && record.getSvlth_h() == null) {
			logger.info("Init...");
			successView.addObject("action", CRUDEnum.CREATE.getValue());
			successView.addObject("saldo", null);
			
			return successView;

		} 		
		
		try {

			if (action.equals(CRUDEnum.CREATE.getValue())) {
				logger.info("Create INLAGG...");

				Long millis =  wtf.getGodsNrMap().get(record.getSvlth_ign() );
				if ( millis != null) {
					logger.info("millis != null, record.getSvlth_ign() ="+record.getSvlth_ign());
					wtf.getGodsNrMap().remove(record.getSvlth_ign());
					logger.error("!!!!!!!!!!WTF, this is the crap that needs to go away!!!!!!!!!!");
					crap = true;
				} else {
					logger.info("millis = null, put record.getSvlth_ign()="+record.getSvlth_ign());				
					wtf.getGodsNrMap().put(record.getSvlth_ign(), System.currentTimeMillis());
					logger.info(" wtf.getGodsNrMap().values()"+ wtf.getGodsNrMap().values());
				}
				
				setDate(EventTypeEnum.INLAGG, record);

				if (StringUtils.hasValue(record.getSvlth_ign())){
					enteredGodsNummer = record.getSvlth_ign(); //use if error
				}
				if (!crap) {
					SvlthDao savedDao = saveRecord(appUser, record, "A");
					if(savedDao!=null && org.apache.commons.lang3.StringUtils.isNotEmpty(savedDao.getSvlth_ign())){
						this.tlagringTempSolutionService.execute(savedDao, appUser);
					}
				}
				wtf.getGodsNrMap().remove(record.getSvlth_ign());
				logger.info(" wtf.getGodsNrMap().values()"+ wtf.getGodsNrMap().values());

//				returnDto = fetchRecord(appUser, saved);
//				successView.addObject("record", returnDto);
//				successView.addObject("saldo", returnDto.getSaldo());

				successView.addObject("action", CRUDEnum.READ.getValue());
				
				successView.addObject("info", "Inlägg skapat.");
				

			} else if (action.equals(CRUDEnum.UPDATE.getValue())) {
				throw new IllegalAccessError("Updates not allowed!");

			} else if (action.equals(CRUDEnum.READ.getValue())) {
				logger.info("Read...");
				record.setSvlth_h(EventTypeEnum.INLAGG.getValue());
				returnDto = fetchRecord(appUser, record);
				successView.addObject("record", returnDto);
				successView.addObject("saldo", returnDto.getSaldo());

				successView.addObject("action", CRUDEnum.READ.getValue());
					
				
			} else if (action.equals(CRUDEnum.DELETE.getValue())) {
				throw new IllegalAccessError("Delete not allowed!");

			}
			
			return successView;			
			

		} catch (Throwable e) {
			logger.error("ERROR:", e);
			successView.addObject("action", action);
			successView.addObject("error", e.getMessage());
			successView.addObject("saldo", null);
			
			record.setSvlth_ign(enteredGodsNummer);
			
			wtf.getGodsNrMap().remove(record.getSvlth_ign());


			return successView;			

		}

	}

	@RequestMapping(value = "accounting_uttag_list.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doUttagList(@ModelAttribute("record") SvlthDto record, HttpSession session, HttpServletRequest request) {

		ModelAndView successView = new ModelAndView("accounting_uttag");
		SystemaWebUser appUser = loginValidator.getValidUser(session);
		SvlthDto headDto;

		if (appUser == null) {
			return loginView;
		} else {

			try {
				headDto = fetchRecord(appUser, record.getSvlth_ign(), record.getSvlth_pos(), EventTypeEnum.INLAGG.getValue(), record.getSvlth_id1(), record.getSvlth_im1());

				successView.addObject("headRecord", headDto);
				successView.addObject("action", CRUDEnum.CREATE.getValue());
				appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_ACCOUNTING);

				successView.addObject("record", record);

				return successView;

			} catch (Throwable e) {
				logger.error("ERROR:", e);
				successView.addObject("error", e.getMessage());
				successView.addObject("record", record);

				return successView;
			}

		}

	}
	
	@RequestMapping(value = "accounting_uttag.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doUttag(@ModelAttribute("record") SvlthDto record, 
								@RequestParam(value = "h_svlth_ign", required = true) String h_svlth_ign, 
								@RequestParam(value = "h_svlth_pos", required = true) String h_svlth_pos,
								@RequestParam(value = "h_svlth_id1", required = true) Integer h_svlth_id1,
								@RequestParam(value = "h_svlth_im1", required = true) Integer h_svlth_im1,
								@RequestParam(value = "action", required = true) Integer action, HttpSession session, HttpServletRequest request) {

		SystemaWebUser appUser = loginValidator.getValidUser(session);
		if (appUser == null) {
			return loginView;
		}

		logger.info("accounting_uttag.do, record=" + ReflectionToStringBuilder.reflectionToString(record));
		logger.info("action=" + action);

		ModelAndView successView = new ModelAndView("accounting_uttag");
		SvlthDto returnDto = new SvlthDto();
		
		SvlthDao recordDao = SvlthDto.get(record);
		List<SvltuDao> uhDaoList = SvlthDto.transform(record);
		
		boolean crap = false;

//		uhDaoList.forEach(dao -> {
//			logger.info("dao="+ReflectionToStringBuilder.toString(dao, ToStringStyle.MULTI_LINE_STYLE));
//		});
		
		
		try {

			if (action.equals(CRUDEnum.CREATE.getValue())) {
				logger.info("Create UTTAG...");
				Long millis =  wtf.getGodsNrMap().get(record.getSvlth_ign() );
	
				if ( millis != null) {
					logger.info("millis != null, record.getSvlth_ign() ="+record.getSvlth_ign());
					wtf.getGodsNrMap().remove(record.getSvlth_ign());
					logger.error("WTF, this is the crap that needs to go away! Now handled with logic.");
					crap = true;
				} else {
					logger.info("millis = null, put record.getSvlth_ign()="+record.getSvlth_ign());				
					wtf.getGodsNrMap().put(record.getSvlth_ign(), System.currentTimeMillis());
					logger.info(" wtf.getGodsNrMap().values()"+ wtf.getGodsNrMap().values());
				}
				setDate(EventTypeEnum.UTTAG, recordDao);
				
				if (!crap) {
					saveRecord(appUser, recordDao, "A");
					saveRecords(appUser, uhDaoList, "A");
				}
				wtf.getGodsNrMap().remove(record.getSvlth_ign());
				logger.info(" wtf.getGodsNrMap().values()"+ wtf.getGodsNrMap().values());
				
				successView.addObject("action", CRUDEnum.READ.getValue());
				
				successView.addObject("info", "Uttag skapat.");

			} else if (action.equals(CRUDEnum.UPDATE.getValue())) {
				throw new IllegalAccessError("Updates not allowed!");

			} else if (action.equals(CRUDEnum.READ.getValue())) {
				logger.info("Read...");
				returnDto = fetchRecord(appUser, recordDao);
				successView.addObject("record", returnDto);
				successView.addObject("action", CRUDEnum.READ.getValue());

			} else if (action.equals(CRUDEnum.DELETE.getValue())) {
				throw new IllegalAccessError("Delete not allowed!");

			}

			SvlthDto headDto = fetchRecord(appUser, h_svlth_ign, h_svlth_pos, EventTypeEnum.INLAGG.getValue(), h_svlth_id1, h_svlth_im1);
			successView.addObject("headRecord", headDto);

			return successView;

		} catch (Throwable e) {
			logger.error("ERROR:", e);
			successView.addObject("action", action);
			successView.addObject("error", e.getMessage().replace("\"", ""));
			successView.addObject("record", record);
			SvlthDto headDto = fetchRecord(appUser, h_svlth_ign, h_svlth_pos, EventTypeEnum.INLAGG.getValue(), h_svlth_id1, h_svlth_im1);
			successView.addObject("headRecord", headDto);

			return successView;

		}

	}
	

	@RequestMapping(value = "accounting_rattelse.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doRattelse(@ModelAttribute("record") SvlthDto record, @RequestParam(value = "action", required = true) Integer action,
			@RequestParam(value = "h_svlth_h", required = true) String h_svlth_h, 
			@RequestParam(value = "h_svlth_ign", required = true) String h_svlth_ign, 
			@RequestParam(value = "h_svlth_pos", required = true) String h_svlth_pos,
			@RequestParam(value = "h_svlth_id1", required = true) Integer h_svlth_id1,
			@RequestParam(value = "h_svlth_im1", required = true) Integer h_svlth_im1,
			HttpSession session, HttpServletRequest request) {

		SystemaWebUser appUser = loginValidator.getValidUser(session);
		if (appUser == null) {
			return loginView;
		}

		logger.info("accounting_rattelse.do, record=" + ReflectionToStringBuilder.reflectionToString(record));
		logger.info("action=" + action);
		
		logger.info("h_svlth_h="+h_svlth_h);
		logger.info("h_svlth_ign="+h_svlth_ign);
		logger.info("h_svlth_pos="+h_svlth_pos);
		logger.info("h_svlth_id1="+h_svlth_id1);
		logger.info("h_svlth_im1="+h_svlth_im1);
		
		

		ModelAndView successView = new ModelAndView("accounting_rattelse");
		SvlthDto returnDto = new SvlthDto();

		if (action.equals(CRUDEnum.CREATE.getValue()) && record.getSvlth_rty() == null) {
			logger.info("Init...");
			successView.addObject("action", CRUDEnum.CREATE.getValue());
			SvlthDto headDto = fetchRecord(appUser, h_svlth_ign, h_svlth_pos, h_svlth_h, h_svlth_id1, h_svlth_im1);
	
			logger.info("headRecord="+ReflectionToStringBuilder.toString(headDto, ToStringStyle.MULTI_LINE_STYLE));
			
			successView.addObject("headRecord", headDto);

			return successView;
		}

		try {
			if (action.equals(CRUDEnum.CREATE.getValue())) {
				logger.info("Create...");
	
				SvlthDao recordDao = SvlthDto.get(record);
	
				adjustIntoDao(h_svlth_h, record,recordDao);
	
				setDate(EventTypeEnum.RATTELSE, recordDao);
				SvlthDao savedDao = saveRecord(appUser, recordDao, "A");
				if(savedDao!=null && org.apache.commons.lang3.StringUtils.isNotEmpty(savedDao.getSvlth_ign())){
					this.tlagringTempSolutionService.execute(savedDao, appUser);
				}
				successView.addObject("action", CRUDEnum.READ.getValue());
				
				successView.addObject("info", "Rättelse skapat.");

			} else if (action.equals(CRUDEnum.UPDATE.getValue())) {
				throw new IllegalAccessError("Updates not allowed!");

			} else if (action.equals(CRUDEnum.READ.getValue())) {
				logger.info("Read...");

				SvlthDao recordDao = SvlthDto.get(record);

				returnDto = fetchRecord(appUser, recordDao);
				successView.addObject("record", returnDto);
				successView.addObject("action", CRUDEnum.READ.getValue());

			} else if (action.equals(CRUDEnum.DELETE.getValue())) {
				throw new IllegalAccessError("Delete not allowed!");

			}

			SvlthDto headDto = fetchRecord(appUser, h_svlth_ign, h_svlth_pos, h_svlth_h, h_svlth_id1, h_svlth_im1);
			successView.addObject("headRecord", headDto);

			return successView;

		} catch (Throwable e) {
			logger.error("ERROR:", e);
			successView.addObject("action", action);
			successView.addObject("error", e.getMessage().replace("\"", ""));
			successView.addObject("svlth_ign", h_svlth_ign);
			SvlthDto headDto = fetchRecord(appUser, h_svlth_ign, h_svlth_pos, h_svlth_h, h_svlth_id1, h_svlth_im1);
			successView.addObject("headRecord", headDto);

			return successView;

		}

	}

	private void adjustIntoDao(String h_svlth_h, SvlthDto record, SvlthDao recordDao) {
		logger.info("::adjustIntoDao::");
		logger.info("h_svlth_h="+h_svlth_h);
		if (h_svlth_h.equals(EventTypeEnum.UTTAG.getValue())) {
			logger.info("record.getSvlth_rntU()="+record.getSvlth_rntU());
			if (StringUtils.hasValue(record.getSvlth_rexU())) {
				recordDao.setSvlth_rex(record.getSvlth_rexU());
			}
			if (StringUtils.hasValue(record.getSvlth_rntU())) {
				recordDao.setSvlth_rnt(Integer.parseInt(record.getSvlth_rntU()));
			}
			if (StringUtils.hasValue(record.getSvlth_rtxU())) {
				recordDao.setSvlth_rtx(record.getSvlth_rtxU());
			}
		}
	}


	private SvlthDto fetchRecord(SystemaWebUser appUser, SvlthDao record) {
		SvlthDto dto = getHeadDto(appUser.getUser(), record.getSvlth_ign(), record.getSvlth_pos(), record.getSvlth_h(), record.getSvlth_id1(), record.getSvlth_im1());
		
		return dto;
	}	

	private SvlthDto fetchRecord(SystemaWebUser appUser, String svlth_ign, String svlth_pos, String svlth_h, Integer svlth_id1, Integer svlth_im1) {
		SvlthDto dto = getHeadDto(appUser.getUser(), svlth_ign,svlth_pos, svlth_h, svlth_id1, svlth_im1);
		
		return dto;
	}	
	
	private SvlthDto getHeadDto(@NonNull String user, @NonNull String svlth_ign, String svlth_pos, @NonNull String svlth_h, @NonNull Integer svlth_id1, @NonNull Integer svlth_im1)  {
		EncodingTransformer transformer = new EncodingTransformer();
		JsonReader<JsonDtoContainer<SvlthDto>> jsonReader = new JsonReader<JsonDtoContainer<SvlthDto>>();
		jsonReader.set(new JsonDtoContainer<SvlthDto>());
		SvlthDto dto;

		String BASE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSVLTH.do";	
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("?user=" + user);
		urlRequestParams.append("&svlth_h=" + svlth_h);
		urlRequestParams.append("&svlth_ign=" + svlth_ign);
		urlRequestParams.append("&svlth_pos=" + svlth_pos);
		urlRequestParams.append("&svlth_id1=" + svlth_id1);
		urlRequestParams.append("&svlth_im1=" + svlth_im1);
		
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
//			logger.info("jsonPayload="+jsonPayload);		
		} catch (Exception e) {
			logger.error("Transforming did not work on "+urlRequestParams.toString());
			throw new RuntimeException("Transforming did not work, e", e);
		}
		
		JsonDtoContainer<SvlthDto> container = (JsonDtoContainer<SvlthDto>) jsonReader.get(jsonPayload);
		if (container != null) {
			if (StringUtils.hasValue(container.getErrMsg())) {
				String errMsg = String.format("READ-error on inlagg, svlth_ign: %s. Error message: %s", svlth_ign, container.getErrMsg()) ;
				logger.error(errMsg);
				throw new RuntimeException(container.getErrMsg());
			}		
			List<SvlthDto> list = container.getDtoList();
			if (list.isEmpty() || list.size() != 1){
				String errMsg = String.format("Expecting SvlthDao in return! DML-error on bilag.") ;
				String inParams = String.format(" svlth_h: %s ,  svlth_ign: %s , svlth_pos: %s , svlth_id1: %s, svlth_im1: %s", svlth_h, svlth_ign,svlth_pos, svlth_id1, svlth_im1 ) ;
				logger.error(errMsg + "list.size="+list.size());
				throw new RuntimeException(errMsg + inParams);
			} else {
				dto = list.get(0);
				return  dto;
			}
		} else {
			String errMsg = String.format("Container is null. Could not found SvlthDto on godsnummer: %s , jsonPayload %s", svlth_ign, jsonPayload) ;
			throw new RuntimeException(errMsg);
		}
		
	}
	

	private SvlthDao saveRecord(SystemaWebUser appUser, SvlthDao record, String mode) {
		logger.info("saveRecord::record::"+ReflectionToStringBuilder.toString(record));
		SvlthDao dao = null;
		setUser(appUser.getUser(), record);
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
//		logger.info("jsonPayloadResponse="+jsonPayloadResponse);
		
		String jsonPayload = null;
		try {
			jsonPayload = transformer.transformToJSONTargetEncoding(jsonPayloadResponse, "UTF8");
//			logger.info("jsonPayload="+jsonPayload);		
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
			List<SvlthDao> list = container.getDtoList();
			if (list.isEmpty() || list.size() != 1){
				String errMsg = String.format("Expecting SvlthDao in return! DML-error on bilag.") ;
				String inParams = String.format(" Record to save: %s ", ReflectionToStringBuilder.toString(record,ToStringStyle.MULTI_LINE_STYLE));
				logger.error(errMsg + "list.size="+list.size());
				throw new RuntimeException(errMsg + inParams);
			} else {
				dao = list.get(0);
				
			}
		}
		
		return  dao;
		
	}	
	
	
	private void saveRecords(SystemaWebUser appUser, List<SvltuDao> recordList, String mode) {
		recordList.forEach(dao -> {
			saveRecord(appUser, dao, mode);
		});
	}

	private void saveRecord(SystemaWebUser appUser, SvltuDao record, String mode) {
		logger.info("saveRecord::record::"+ReflectionToStringBuilder.toString(record));
		EncodingTransformer transformer = new EncodingTransformer();
		String SVLTU_DML_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSVLTU_U.do";

		MultiValueMap<String, String> recordParams = UrlRequestParameterMapper.getUriParameter(record);
	    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(SVLTU_DML_URL)
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
		
		JsonReader<JsonDtoContainer<SvltuDao>> jsonReader = new JsonReader<JsonDtoContainer<SvltuDao>>();
		jsonReader.set(new JsonDtoContainer<SvltuDao>());
		JsonDtoContainer<SvltuDao> container = (JsonDtoContainer<SvltuDao>) jsonReader.get(jsonPayload);
		if (container != null) {
			if (StringUtils.hasValue(container.getErrMsg())) {
				String errMsg = String.format("SAVE-error on utgående handlingar, godsnummer: %s. Error message: %s", record.getSvltu_ign(), container.getErrMsg()) ;
				logger.error(errMsg);
				throw new IllegalArgumentException(container.getErrMsg());
			}		
		}
		
	}	

	
	private void setUser(String user, SvlthDao record) {
		record.setSvlth_ius(user);
	}

	private void setDate(EventTypeEnum eventType, SvlthDao record) {
		int[] dato = DateTimeManager.getNowDato();		
		record.setSvlth_id1(dato[0]);
		record.setSvlth_im1(dato[1]);
		
		if (eventType == EventTypeEnum.UTTAG) {
			record.setSvlth_um1(dato[1]);
		}
		
	}

	private boolean hasGodslokalkod(HttpSession session) {
		String glk = getGodslokalkod(session); 
		if (glk != null) {
			return true;
		} else {
			return false;
		}
	}
	
	private String getGodslokalkod(HttpSession session) {
		return (String)session.getAttribute(AccountingSession.GODS_LOKAL_KOD);
	}

	private void setGodslokalkod(HttpSession session, String godslokalkod) {
		session.setAttribute(AccountingSession.GODS_LOKAL_KOD, godslokalkod);
		
	}	
	
	
	
	public String getFileBasePath(String applicationUser){	
		String retval = "";
		try{
			String BASE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFIRMARC.do";
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + applicationUser);
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("AVD BASE_URL:" + BASE_URL);
			logger.info("AVD BASE_PARAMS:" + urlRequestParamsKeys.toString());
			JsonTdsFirmArcContainer container = this.firmArcService.getContainer(url);
			
			retval = File.separator + container.getArcane() + PdfiTextService.BASE_DIR_TILLFALLIG_LAGRING_ARCHIVE;
			logger.warn("path:" + retval);
			
		}catch(Exception e){
			logger.error(e.toString());
		}
		
		return retval;
		
	}
	
	
	
	
}

