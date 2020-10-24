package no.systema.tds.nctsexport.controller;

import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;

//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.BigDecimalFormatter;
import no.systema.main.util.JsonDebugger;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemRecord;
import no.systema.tds.tdsexport.url.store.TdsExportUrlDataStore;
import no.systema.tds.tdsexport.service.TdsExportSpecificTopicService;
import no.systema.tds.tdsexport.service.TdsExportSpecificTopicItemService;

import no.systema.tds.nctsexport.model.jsonjackson.topic.items.JsonNctsExportSpecificTopicItemContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.items.JsonNctsExportSpecificTopicItemRecord;
import no.systema.tds.nctsexport.model.topic.NctsExportSpecificTopicTotalItemLinesObject;
import no.systema.tds.model.external.url.UrlISOLanguageObject;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsAvdelningContainer;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsAvdelningRecord;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureContainer;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureRecord;
import no.systema.tds.model.jsonjackson.codes.JsonTdsNctsCodeContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsNctsCodeRecord;
import no.systema.tds.service.html.dropdown.TdsDropDownListPopulationService;
import no.systema.tds.tdsexport.model.jsonjackson.customer.JsonTdsExportCustomerRecord;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicRecord;

import no.systema.tds.nctsexport.validator.NctsExportHeaderValidator;

import no.systema.main.model.SystemaWebUser;
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportSpecificTopicContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportSpecificTopicRecord;
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportTopicCopiedContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportTopicCopiedFromTransportUppdragContainer;

import no.systema.tds.nctsexport.service.NctsExportSpecificTopicService;
import no.systema.tds.nctsexport.service.NctsExportSpecificTopicItemService;
import no.systema.tds.nctsexport.service.html.dropdown.DropDownListPopulationService;
import no.systema.tds.nctsexport.url.store.UrlDataStore;
import no.systema.tds.nctsexport.util.RpgReturnResponseHandler;
import no.systema.tds.nctsexport.util.manager.CodeDropDownMgr;
import no.systema.tds.nctsexport.util.manager.NctsExportControllerAjaxCommonFunctionsMgr;
import no.systema.tds.url.store.TdsUrlDataStore;
import no.systema.tds.util.TdsConstants;

import no.systema.tds.nctsexport.mapper.url.request.UrlRequestParameterMapper;


/**
 * NCTS Export Topic Controller 
 * 
 * @author oscardelatorre
 * @date June, 2103
 */

@Controller
@Scope("session")
public class NctsExportHeaderController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(NctsExportHeaderController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private BigDecimalFormatter bigDecimalFormatter = new BigDecimalFormatter();
	private ApplicationContext context;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
		//binder.setValidator(new NctsExportValidator()); //it must have spring form tags in the html otherwise = meaningless
    }
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}
	
	
	/**
	 * Renders the create GUI view (without any logic)
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="nctsexport_edit.do",  params="action=doPrepareCreate", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doPrepareCreate(HttpSession session, HttpServletRequest request){
		
		Map model = new HashMap();
		String sign = request.getParameter("sign");
		String avd = request.getParameter("avd");
		//logger.info("sign:" + sign);
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		ModelAndView successView = new ModelAndView("nctsexport_edit");
		logger.info("Method: doPrepareCreate [RequestMapping-->nctsexport_edit.do]");
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			
			//add gui lists here
    		this.setCodeDropDownMgr(appUser, model);
			this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
			this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
    		//domain
			model.put("sign", sign);
			model.put("avd", avd);
			JsonNctsExportSpecificTopicRecord record = this.initCreateNewTopic(appUser.getUser(),avd);
			model.put("record", record);
			
			successView.addObject("model", model);
    		successView.addObject(TdsConstants.EDIT_ACTION_ON_TOPIC, TdsConstants.ACTION_CREATE);

		}
		
		return successView;
	}
	
	
	
	/**
	 * Creates or Updates a new Topic (Arende)
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	
	
	@RequestMapping(value="nctsexport_edit.do")
	public ModelAndView doNctsExportEdit(@ModelAttribute ("record") JsonNctsExportSpecificTopicRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		
		ModelAndView successView = new ModelAndView("nctsexport_edit");
		String method = "doNctsExportEdit [RequestMapping-->nctsexport_edit.do]";
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		logger.info("Method: " + method);
		NctsExportSpecificTopicTotalItemLinesObject totalItemLinesObject = new NctsExportSpecificTopicTotalItemLinesObject();
		
		//---------------------------------
		//Crucial request parameters (Keys
		//---------------------------------
		String action = request.getParameter("action");
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		String th0035 = request.getParameter("th0035"); //test indicator
		
		logger.info("TEST flagga:<" + th0035 +">");
		
		//Action (doFetch, doUpdate, doCreate)
		logger.info("Action:" + action);
		logger.info("Opd:" + opd);
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;
		}else{
			if(action!=null){
				//get some item lines total fields (∑)
				totalItemLinesObject = this.getRequiredSumsInItemLines(avd, opd, appUser);
				//-------------
				//FETCH RECORD
				//-------------
				if(TdsConstants.ACTION_FETCH.equals(action)){
					logger.info("FETCH record transaction...");
					//---------------------------
					//get BASE URL = RPG-PROGRAM
		            //---------------------------
					String BASE_URL = UrlDataStore.NCTS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
					//url params
					String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
					//for debug purposes in GUI
					session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
					
					logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
				    	logger.warn("URL: " + BASE_URL);
				    	logger.warn("URL PARAMS: " + urlRequestParamsKeys);
				    	//--------------------------------------
				    	//EXECUTE the FETCH (RPG program) here
				    	//--------------------------------------
				    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
						//Debug --> 
				    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
				    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
				    	if(jsonPayload!=null){
				    		JsonNctsExportSpecificTopicContainer jsonNctsExportSpecificTopicContainer = this.nctsExportSpecificTopicService.getNctsExportSpecificTopicContainer(jsonPayload);
				    		//add gui lists here
				    		this.setCodeDropDownMgr(appUser, model);
				    		this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
							this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
				    		this.setDomainObjectsInView(session, model, jsonNctsExportSpecificTopicContainer, totalItemLinesObject);
				    		
				    		successView.addObject(TdsConstants.DOMAIN_MODEL, model);
				    		//put the doUpdate action since we are preparing the record for an update (when saving)
				    		successView.addObject(TdsConstants.EDIT_ACTION_ON_TOPIC, TdsConstants.ACTION_UPDATE);
				    		
				    	}else{
						logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
						return loginView;
					}
			    	
			    //----------------------------
				//CREATE and/or UPDATE RECORD
				//----------------------------	
				}else if(TdsConstants.ACTION_UPDATE.equals(action)){
					boolean isValidCreatedRecordTransactionOnRPG = true;
					//---------------------
					//Validation Light GUI
					//---------------------
					NctsExportHeaderValidator validator = new NctsExportHeaderValidator();
					logger.info("VALIDATING...");
					if(opd!=null && !"".equals(opd)){
						//Update...
						//nothing
					}else{
						logger.info("avdXX: " + avd);
						logger.info("signXX: " + sign);
						
						//Create
						//we must lend these dropdown variables to the validation object
						recordToValidate.setThavd(avd);
						recordToValidate.setThsg(sign);
						
					}
					//get guarantee in order to validate with end-user guarantee in GUI
					JsonNctsExportSpecificTopicContainer guaranteeContainer = nctsExportControllerAjaxCommonFunctionsMgr
						      .calculateGuaranteeAmount(appUser.getUser(), recordToValidate.getThavd(), recordToValidate.getThtdn());
					int comparedGuaranteeValue = bigDecimalFormatter.compareBigDecimals(recordToValidate.getThgbl(), guaranteeContainer.getAmount(), 0);
					recordToValidate.setComparedGuaranteeValue(comparedGuaranteeValue);
					recordToValidate.setCalculatedGuaranteeAmount(bigDecimalFormatter.getBigDecimalIntegerPart(guaranteeContainer.getAmount()));
					
					validator.validate(recordToValidate, bindingResult);
					recordToValidate.setTh0035(th0035);
					
					
				    //check for ERRORS
					if(bindingResult.hasErrors()){
					    	logger.info("[ERROR Validation] Record does not validate)");
					    	//put domain objects and do go back to the original view...
					    	recordToValidate.setThavd(avd);
					    	//recordToValidate.setThsg(sign);
					    	this.setCodeDropDownMgr(appUser, model);
				    		this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
							this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
					    	this.setDomainObjectsInView(session, model, recordToValidate);
					    	isValidCreatedRecordTransactionOnRPG = false;
					    	if(opd==null || "".equals(opd)){
					    		action = TdsConstants.ACTION_CREATE;
					    	}
				    }else{
				    	
				    		JsonNctsExportSpecificTopicRecord jsonNctsExportSpecificTopicRecord = null;
						String tuid = null;
						
						if(opd!=null && !"".equals(opd)){
							logger.info("PURE UPDATE transaction...");
							//PURE UDPATE transaction
							//this means that the update is an update of an existing record
							jsonNctsExportSpecificTopicRecord = new JsonNctsExportSpecificTopicRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonNctsExportSpecificTopicRecord);
							
				            binder.bind(request);
				            //binder.registerCustomEditor(...); // if needed
							logger.info("THNAS [after bind]: " + jsonNctsExportSpecificTopicRecord.getThnas());
							logger.info("THTUID [after bind]: " + jsonNctsExportSpecificTopicRecord.getThtuid());
							//test indicator
							jsonNctsExportSpecificTopicRecord.setTh0035(th0035);
				            
						}else{
							logger.info("CREATE NEW follow by UDATE transaction...");
							//CREATE AND UPDATE transaction
							//This means that the update will be done AFTER a creation of an empty record. All this in the same transaction. 2 STEPS involved: (1)create and (2)update
							//---------------------------------------------------------------------------------------------
							//STEP[1] Generate new Topic key seeds (avd,opd,sign,tullid) by creating an empty new record. 
							//---------------------------------------------------------------------------------------------
							jsonNctsExportSpecificTopicRecord = this.createNewTopicHeaderKeySeeds(session, request, appUser, avd, sign);
							if(jsonNctsExportSpecificTopicRecord!=null){
								//avd = jsonNctsExportSpecificTopicRecord.getThavd();
								//sign = jsonNctsExportSpecificTopicRecord.getThsg();
								opd = jsonNctsExportSpecificTopicRecord.getThtdn();
								tuid = jsonNctsExportSpecificTopicRecord.getThtuid();
								
								//take the rest from GUI.
								jsonNctsExportSpecificTopicRecord = new JsonNctsExportSpecificTopicRecord();
								ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonNctsExportSpecificTopicRecord);
					            //binder.registerCustomEditor(...); // if needed
					            binder.bind(request);
					            
					            //Now set back with the generated values since the bind method above erases them...
					            jsonNctsExportSpecificTopicRecord.setThavd(avd);
					            jsonNctsExportSpecificTopicRecord.setThtdn(opd);
					            jsonNctsExportSpecificTopicRecord.setThsg(sign);
					            jsonNctsExportSpecificTopicRecord.setThtuid(tuid);
					            //test indicator
					            jsonNctsExportSpecificTopicRecord.setTh0035(th0035);
							}else{
								//Some kind of error occurred. Set the transaction as invalid...
								isValidCreatedRecordTransactionOnRPG = false;
							}
						}
						//--------------------------------------------------
						//At this point we are ready to do an update
						//--------------------------------------------------
						if(isValidCreatedRecordTransactionOnRPG){
				            //---------------------------
							//get BASE URL = RPG-PROGRAM
				            //---------------------------
							String BASE_URL = UrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
							
							//-----------------------------------
							//add URL-parameter "mode=U" (Update)
							//-----------------------------------
							String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
							//build the url parameters (from GUI-form) to send on a GET/POST method AFTER the keyParameters
							String urlRequestParamsTopic = this.urlRequestParameterMapper.getUrlParameterValidString((jsonNctsExportSpecificTopicRecord));
							//put the final valid param. string
							String urlRequestParams = urlRequestParamsKeys + urlRequestParamsTopic;
							//for debug purposes in GUI
							session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL); 
					    	
							logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
						    	logger.info("URL: " + BASE_URL);
						    	logger.info("URL PARAMS: " + urlRequestParams);
						    	//----------------------------------------------------------------------------
						    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
						    	//----------------------------------------------------------------------------
						    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
								//Debug --> 
						    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
						    	//we must evaluate a return RPG code in order to know if the Update was OK or not
						    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicUpdate(rpgReturnPayload);
						    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
						    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
						    		this.setFatalError(model, rpgReturnResponseHandler, jsonNctsExportSpecificTopicRecord);
						    		isValidCreatedRecordTransactionOnRPG = false;
						    	}else{
						    		//Update succefully done!
						    		logger.info("[INFO] Record successfully updated, OK ");
						    		//put domain objects
						    		this.setDomainObjectsInView(session, model, jsonNctsExportSpecificTopicRecord);
						    		this.adjustValidUpdateFlag(model, jsonNctsExportSpecificTopicRecord);
						    	}
							}else{
								rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tuid, syop generation : " + rpgReturnResponseHandler.getErrorMessage());
								this.setFatalError(model, rpgReturnResponseHandler, jsonNctsExportSpecificTopicRecord);
								isValidCreatedRecordTransactionOnRPG = false;
							}
				    }
					
		            //add gui lists here
					this.setCodeDropDownMgr(appUser, model);
					this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
					this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
					
					successView.addObject("model" , model);
					successView.addObject(TdsConstants.DOMAIN_MODEL, model);
		            //Edit or Create offset
				    	if(isValidCreatedRecordTransactionOnRPG){
			            	successView.addObject(TdsConstants.EDIT_ACTION_ON_TOPIC, TdsConstants.ACTION_UPDATE);
		            }else{
		            		//Validation errors have been generated and we must offset to some state (set or changed above in some flow)
			            	successView.addObject(TdsConstants.EDIT_ACTION_ON_TOPIC, action);
		            }
					
				//------------------------
				//CREATE-ADD [PURE] RECORD
				//-------------------------
				}else if(TdsConstants.ACTION_CREATE.equals(action)){
					//OBSOLETE
					//This action is not used as an isolated create. It is realized in the UPDATE above in 2 transactions
					//Could be needed in the future.
					
				//------------------
				//REMOVE RECORD
				//------------------	
				}else if(TdsConstants.ACTION_DELETE.equals(action)){
					//Remove Topic
					//Could be delete OR set a remove status...(no physical delete)
					//TODO
				}
			}
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param avd
	 * @return
	 */
	public JsonNctsExportSpecificTopicRecord initCreateNewTopic(String applicationUser, String avd) {
	 	String method = "initCreateNewTopic";
	 	logger.info("Inside " + method);
	 	JsonNctsExportSpecificTopicRecord result = new JsonNctsExportSpecificTopicRecord();
	 	
	 	logger.info("FETCH record transaction...");
		//---------------------------
		//get BASE URL = RPG-PROGRAM
		//---------------------------
		String BASE_URL = UrlDataStore.NCTS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
		//url params
		String urlRequestParamsKeys = "user=" + applicationUser + "&avd=" + avd;
		//for debug purposes in GUI
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + BASE_URL);
		logger.info("URL PARAMS: " + urlRequestParamsKeys);
		//--------------------------------------
		//EXECUTE the FETCH (RPG program) here
		//--------------------------------------
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//Debug --> 
		logger.info(method + " --> jsonPayload:" + jsonPayload);
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");

		if(jsonPayload!=null){
    		JsonNctsExportSpecificTopicContainer container = this.nctsExportSpecificTopicService.getNctsExportSpecificTopicContainer(jsonPayload);
    		if(container!=null){
    			for(JsonNctsExportSpecificTopicRecord  record : container.getOneorder()){
    				result = record;
    			}
    		}
    	}
		return result;
  }
	/**
	 * 
	 * Aux method to prevent an end-user for sending the declaration without having saved it first.
	 * The end-user must save a topic before issuing a further "send". Sort of a validation routine to ensure validity of all fields.
	 * 
	 * @param model
	 * @param record
	 */
	private void adjustValidUpdateFlag(Map model, JsonNctsExportSpecificTopicRecord record){
		record.setValidUpdate(true);
		model.put(TdsConstants.DOMAIN_RECORD, record);
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="nctsexport_send.do")
	public ModelAndView doNctsExportSend(HttpSession session, HttpServletRequest request){

		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_NCTS_EXPORT);
		ModelAndView successView = new ModelAndView("redirect:nctsexport.do?action=doFind&sign=" + appUser.getTdsSign());
		
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		//---------------------------------
		//Crucial request parameters (Keys
		//---------------------------------
		String action = TdsConstants.ACTION_SEND;
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		
		//Action (doFetch, doUpdate, doCreate)
		logger.info("Action:" + action);
		Map model = new HashMap();
		
		
		if(appUser==null){
			return this.loginView;
		}else{
		    //---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = UrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
			
			//-----------------------------------
			//add URL-parameter "mode=S" (Send)
			//-----------------------------------
			String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
			//there is only key parameters in doSend. No other topic (record) specific parameters from GUI or such
			String urlRequestParams = urlRequestParamsKeys;
			//for debugging purposes
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	//----------------------------------------------------------------------------
		    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
		    	//----------------------------------------------------------------------------
		    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
				//Debug --> 
		    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
		    	//we must evaluate a return RPG code in order to know if the Update was OK or not
		    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicUpdate(rpgReturnPayload);
		    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
		    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
		    		//this.setFatalError(model, rpgReturnResponseHandler, jsonTdsExportSpecificTopicRecord);
		    		//TODO ERROR HANDLING HERE... stay in the same page ?
		    	}else{
		    		//Update succefully done!
		    		logger.info("[INFO] Record successfully sent [changed status], OK ");
		    		//put domain objects
		    		//this.setDomainObjectsInView(session, model, jsonTdsExportSpecificTopicRecord);
		    		//TODO SUCCESS should stay at the same side or not? Right now we go to the list of topics
		    	}
			
		}
		return successView;
	}
	
	/**
	 * Prints a specific topic
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="ncts_export_edit_printTopic.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doNctsExportEditPrintTopic(HttpSession session, HttpServletRequest request){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView successView = new ModelAndView("redirect:nctsexport.do?action=doFind&sign=" + appUser.getTdsSign());

		Map model = new HashMap();
		
		String method = "doNctsExportEditPrintTopic [RequestMapping-->ncts_export_edit_printTopic.do]";
		logger.info("Method: [RequestMapping-->ncts_export_edit_printTopic.do]");
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			//-------------------------------------
			//get BASE URL = RPG-PROGRAM for PRINT
            //-------------------------------------
			String BASE_URL = UrlDataStore.NCTS_EXPORT_BASE_PRINT_FOR_SPECIFIC_TOPIC_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForPrint( avd, opd, appUser);
			//for debug purposes in GUI
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the Print (RPG program) here
	    	//--------------------------------------
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
	    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    //END of PRINT here and now
		    		
		}
		
		return successView;
	}
	
	/**
	 *
	 * Copies one topic(ärende) to a new one (clones the source topic)
	 * STEP 1: Copy by getting JSON with the new record (new opd, new avd, new sign)
	 * STEP 2: Fetch the record as if it was a selection of a topic in a list
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="nctsexport_copyTopic.do", method={RequestMethod.POST} )
	public ModelAndView doCopyTopic( HttpSession session, HttpServletRequest request){
		
		ModelAndView successView = new ModelAndView("nctsexport_edit");
		ModelAndView fallbackOnErrorView = new ModelAndView("nctsexport");
		
		JsonNctsExportTopicCopiedContainer jsonNctsExportTopicCopiedContainer = null;
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		String method = "doCopyTopic [RequestMapping-->nctsexport_copyTopic.do]";
		logger.info("Method: " + method);
		Map model = new HashMap();
		
		//We must get all parameters from the enumeration since all have sequence counter number
		String action=null;
		String avd=null;
		String opd=null;
		String newAvd=null;
		String newSign=null;
		
		Enumeration requestParameters = request.getParameterNames();
	    while (requestParameters.hasMoreElements()) {
	        String element = (String) requestParameters.nextElement();
	        String value = request.getParameter(element);

	        if (element != null && value != null) {
	        		logger.info("####################################################");
        			logger.info("param Name : " + element + " value: " + value);
        			if(element.startsWith("originalAvd")){
        				avd = value;
        			}else if(element.startsWith("originalOpd")){
        				opd = value;
        			}else if(element.startsWith("newAvd")){
        				newAvd = value;
        			}else if(element.startsWith("newSign")){
        				newSign = value;
        			}else if(element.startsWith("action")){
        				action = value;
        			}
        		}
	    	}
	    
	
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			//--------------------
			//STEP 1: COPY record
			//--------------------
			logger.info("starting COPY record transaction...");
			String BASE_URL = UrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForCopy(avd, newAvd, newSign, opd, appUser);
			//for debug purposes in GUI
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE (RPG program) here
		    	//--------------------------------------
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		jsonNctsExportTopicCopiedContainer = this.nctsExportSpecificTopicService.getNctsExportTopicCopiedContainer(jsonPayload);
		    		if(jsonNctsExportTopicCopiedContainer!=null){
		    			//Check for errors
		    			if(jsonNctsExportTopicCopiedContainer.getErrMsg()!=null && !"".equals(jsonNctsExportTopicCopiedContainer.getErrMsg())){
		    				logger.fatal("[ERROR FATAL] errMsg containing: " + jsonNctsExportTopicCopiedContainer.getErrMsg());
		    				return fallbackOnErrorView;
		    			}
		    		}
		    	}else{
				logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
				return loginView;
			}
		    
			
	    	//At this point we do now have a cloned record with its own data. The only thing left is to present it in edit mode
	    	//--------------------
			//STEP 2: FETCH record
			//--------------------
			logger.info("starting FETCH record transaction...");
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			BASE_URL = UrlDataStore.NCTS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
			//url params
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, jsonNctsExportTopicCopiedContainer.getNewavd(), jsonNctsExportTopicCopiedContainer.getNewopd(), jsonNctsExportTopicCopiedContainer.getNewsign(), appUser);
			//for debug purposes in GUI
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE the FETCH (RPG program) here
		    	//--------------------------------------
		    	jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		JsonNctsExportSpecificTopicContainer jsonNctsExportSpecificTopicContainer = this.nctsExportSpecificTopicService.getNctsExportSpecificTopicContainer(jsonPayload);
		    		//add gui lists here
		    		this.setCodeDropDownMgr(appUser, model);
		    		this.setDomainObjectsInView(session, model, jsonNctsExportSpecificTopicContainer, null);
		    		successView.addObject(TdsConstants.DOMAIN_MODEL, model);
				//put the doUpdate action since we are preparing the record for an update (when saving)
				successView.addObject(TdsConstants.EDIT_ACTION_ON_TOPIC, TdsConstants.ACTION_UPDATE);
		    		
		    	}else{
				logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
				return loginView;
			}
			
			
			return successView;
		}
		
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="nctsexport_doFetchTopicFromTransportUppdrag.do", method={RequestMethod.POST} )
	public ModelAndView doFetchTopicFromTransportUppdrag( HttpSession session, HttpServletRequest request){
		JsonNctsExportTopicCopiedFromTransportUppdragContainer jsonContainer = null;
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		//We must get all parameters from the enumeration since all have sequence counter number
		String action=request.getParameter("actionGS");
		String sign=request.getParameter("sign");
		String avd=request.getParameter("selectedAvd");
		String opd=request.getParameter("selectedOpd");
		String extRefNr=request.getParameter("selectedExtRefNr"); //Domino ref in Dachser DK/SE/NO				
		
		ModelAndView successView = new ModelAndView("nctsexport_edit");
		//fallback view (usually on errors)
		ModelAndView fallbackView = new ModelAndView("nctsexport_edit");
		fallbackView.addObject("action", "doPrepareCreate");
		//this view is when the end user choose not to copy at all. He/She will start from scratch (empty form (header))
		ModelAndView cleanNewView = new ModelAndView("redirect:nctsexport_edit.do?action=doPrepareCreate&sign=" + sign + "&avd=" + avd );
		
		String method = "[RequestMapping-->nctsexport_doFetchTopicFromTransportUppdrag.do]";
		logger.info("Method: " + method);
		Map model = new HashMap();
		
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			
			if( (extRefNr!=null && !"".equals(extRefNr)) || ( (opd!=null && !"".equals(opd)) && (avd!=null && !"".equals(avd))) ){
				//--------------------
				//STEP 1: CLONE record
				//--------------------
				logger.info("starting PROCESS record transaction...");
				String BASE_URL = UrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
				String urlRequestParamsKeys = this.getRequestUrlKeyParametersForCopyTopicFromTransportUppdrag(avd, opd, extRefNr, appUser);
				//for debug purposes in GUI
				session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
				
				logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE (RPG program) here
		    	//--------------------------------------
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		    	//Debug --> 
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		jsonContainer = this.nctsExportSpecificTopicService.getNctsExportTopicCopiedFromTransportUppdragContainer(jsonPayload);
		    		if(jsonContainer!=null){
		    			//Check for errors
		    			if(jsonContainer.getErrMsg()!=null && !"".equals(jsonContainer.getErrMsg())){
		    				logger.info("[WARN] errMsg containing: " + jsonContainer.getErrMsg());
		    				logger.info("[WARN] redirecting to doPrepareCreate");
		    				//Send the error message to the redirect view.
		    				//request.setAttribute("errorMessageOnCopyFromTransportOppdrag", jsonContainer.getErrMsg());
		    				model.put(TdsConstants.ASPECT_ERROR_MESSAGE, jsonContainer.getErrMsg());
		    				model.put(TdsConstants.ASPECT_ERROR_META_INFO, "Vid kopiering av TransportUppdrag...");
		    				model.put("sign", sign);
		    				fallbackView.addObject(TdsConstants.DOMAIN_MODEL, model);
		    				
		    				return fallbackView;
		    			}else{
		    				//At this point we do have a cloned record ready to be presented.
		    				//Before we do present this record, we must create a default-item line such as the requirement demands
		    				JsonNctsExportSpecificTopicItemRecord nctsExportTargetItemRecord = new JsonNctsExportSpecificTopicItemRecord();
		    				this.initDefaultFirstItemLine(nctsExportTargetItemRecord, appUser.getUser(),jsonContainer.getThavd(), jsonContainer.getThtdn());
		    				this.createDefaultFirstItemLine(nctsExportTargetItemRecord, appUser.getUser(),jsonContainer.getThavd(), jsonContainer.getThtdn());
		    			}
		    		}
		    	}else{
		    		logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
					return loginView;
				}    
				
		    	//At this point we do now have a cloned record with its own data. The only thing left is to present it in edit mode
		    	//--------------------
				//STEP 2: FETCH record
				//--------------------
				logger.info("starting FETCH record transaction...");
				//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				BASE_URL = UrlDataStore.NCTS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
				//url params
				urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, jsonContainer.getThavd(), jsonContainer.getThtdn(), jsonContainer.getThsg(), appUser);
				//for debug purposes in GUI
				session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
				
				logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE the FETCH (RPG program) here
		    	//--------------------------------------
		    	jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				//Debug --> 
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		JsonNctsExportSpecificTopicContainer jsonSpecificTopicContainer = this.nctsExportSpecificTopicService.getNctsExportSpecificTopicContainer(jsonPayload);
	    			//populate gui
		    		this.setCodeDropDownMgr(appUser, model);	
		    		this.setDomainObjectsInView(session, model, jsonSpecificTopicContainer);
		    		successView.addObject(TdsConstants.DOMAIN_MODEL, model);
		    		//put the doUpdate action since we are preparing the record for an update (when saving)
		    		successView.addObject(TdsConstants.EDIT_ACTION_ON_TOPIC, TdsConstants.ACTION_UPDATE);	
		    	}else{
		    		logger.fatal("[ERROR fatal] NO CONTENT on jsonPayload from URL... ??? <Null>");
		    		return loginView;
		    	}
			}else{
				logger.warn("[INFO] Opdnr is NULL. Redirecting to: nctsexport_edit.do?action=doPrepareCreate... ");
				//return new ModelAndView("redirect:tdsimport_edit.do?action=doPrepareCreate");
				return cleanNewView;
			}			
			return successView;
		}
	}
	 
	/**
	 * 
	 * Admin purposes. Updates a status in order to enable the administrator with this task
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="nctsexport_updateStatus.do")
	public ModelAndView doUpdateStatus(HttpSession session, HttpServletRequest request){
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_NCTS_EXPORT);
		ModelAndView successView = new ModelAndView("redirect:nctsexport.do?action=doFind&sign=" + appUser.getTdsSign());
		
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		//---------------------------------
		//Crucial request parameters (Keys
		//---------------------------------
		String opd = request.getParameter("currentOpd");
		String avd = request.getParameter("currentAvd");
		String newStatus = request.getParameter("selectedStatus");
		
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;
		}else{
			
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = UrlDataStore.NCTS_EXPORT_BASE_UPDATE_STATUS_URL;
			
			//-------------------
			//add URL-parameter 
			//-------------------
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForUpdateStatus(avd, opd, newStatus, appUser);
			//there are only key parameters in doSend. No other topic (record) specific parameters from GUI or such
			String urlRequestParams = urlRequestParamsKeys;
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	//----------------------------------------------------------------------------
		    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
		    	//----------------------------------------------------------------------------
		    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			//Debug --> 
		    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
		    	//we must evaluate a return RPG code in order to know if the Update was OK or not
		    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicUpdate(rpgReturnPayload);
		    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
		    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
		    		//TODO ERROR HANDLING HERE... stay in the same page ?
		    	}else{
		    		//Update succefully done!
		    		logger.info("[INFO] Status successfully updated [changed status], OK ");
		    		//put domain objects
		    		//this.setDomainObjectsInView(session, model, jsonTdsImportSpecificTopicRecord);
		    		//TODO SUCCESS should stay at the same side or not? Right now we go to the list of topics
		    	}
			
				
		}
		return successView;
	}
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param extRefNr
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForCopyTopicFromTransportUppdrag(String avd, String opd, String extRefNr, SystemaWebUser appUser){
		final String MODE = "GS";
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thavd=" + avd);
		if(opd!=null && !"".equals(opd)){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thtdn=" + opd);
		}else if (extRefNr!=null && !"".equals(extRefNr)){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thxref=" + extRefNr);
		}
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + appUser.getTdsSign());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + MODE);
		
		return urlRequestParamsKeys.toString();	
	}
	/**
	 * 
	 * @param nctsExportTargetItemRecord
	 * @param applicationUser
	 * @param avd
	 * @param opd
	 */
	private void initDefaultFirstItemLine(JsonNctsExportSpecificTopicItemRecord nctsExportTargetItemRecord, String applicationUser, String avd, String opd){
		//------------------------
		//STEP(1) - Header values
		//------------------------
		String BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
		//url params
		String urlRequestParamsKeys = "user=" + applicationUser + "&avd=" + avd + "&opd=" + opd; // + "&xref=" + xref;
		//for debug purposes in GUI
		 
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + BASE_URL);
		logger.info("URL PARAMS: " + urlRequestParamsKeys);
   	 	
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//Debug --> 
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		if(jsonPayload!=null){
			JsonTdsExportSpecificTopicContainer container = this.tdsExportSpecificTopicService.getTdsExportSpecificTopicContainer(jsonPayload);
			if(container!=null && container.getOneorder()!=null){
				for(JsonTdsExportSpecificTopicRecord record : container.getOneorder()){
					//Debug
					//logger.info(record.getDkeh_17a());
					nctsExportTargetItemRecord.setTvalk("SE");
					nctsExportTargetItemRecord.setTvdty("830");
					nctsExportTargetItemRecord.setTvblk(record.getSveh_aube());
					
 			  	}
	   		}
	   	 }
		//---------------------------
		//STEP(2) - Item line values
		//---------------------------
		BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
			
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("FETCH av item list... ");
		logger.info("URL: " + BASE_URL);
		logger.info("URL PARAMS: " + urlRequestParamsKeys);
		//--------------------------------------
		//EXECUTE the FETCH (RPG program) here
		//--------------------------------------
		String jsonPayloadFetch = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 //Debug --> 
		 //logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayloadFetch));
		 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 if(jsonPayloadFetch!=null){
			 JsonTdsExportSpecificTopicItemContainer container = this.tdsExportSpecificTopicItemService.getTdsExportSpecificTopicItemContainer(jsonPayloadFetch);
			 if(container!=null){
				 for(JsonTdsExportSpecificTopicItemRecord record : container.getOrderList()){
					 //Debug
					 nctsExportTargetItemRecord.setTvvnt(record.getSvev_vata());
					 nctsExportTargetItemRecord.setTvvt(record.getSvev_vasl());
					 //logger.info("Varenr:" + nctsExportTargetItemRecord.getTvvnt());
					 //logger.info("Varebesk.:" + nctsExportTargetItemRecord.getTvvt());
					 nctsExportTargetItemRecord.setTvvktb(record.getSvev_brut());
					 nctsExportTargetItemRecord.setTvvktn(record.getSvev_neto());
					 break; //only the first item line (if any)
				 }
			 }
		 }	
	}
	/**
	 * 
	 * @param nctsExportTargetItemRecord
	 * @param applicationUser
	 * @param avd
	 * @param opd
	 */
	private void createDefaultFirstItemLine(JsonNctsExportSpecificTopicItemRecord nctsExportTargetItemRecord, String applicationUser, String avd, String opd){
		
		JsonNctsExportSpecificTopicItemRecord tmpRecord  = this.createNewItemKeySeeds(applicationUser, avd, opd);
		if(tmpRecord!=null){
			String newLineNr = tmpRecord.getTvli();
			logger.info("[INFO] New LineNr:" + newLineNr);
			
			//take the rest from GUI.
			nctsExportTargetItemRecord.setTvli(newLineNr);
			nctsExportTargetItemRecord.setTvtdn(opd);
			nctsExportTargetItemRecord.setTvavd(avd);
			logger.info("[INFO] New line number (on item record):" + nctsExportTargetItemRecord.getTvli());
            //some mandatory validation
			this.validateDefaultNctsItemNr(nctsExportTargetItemRecord);
            logger.info("[INFO] Varubeskrivning (on item record):" + nctsExportTargetItemRecord.getTvvt());
            logger.info("[INFO] Varekode (on item record):" + nctsExportTargetItemRecord.getTvvnt());
            logger.info("[INFO] Bruttov. (on item record):" + nctsExportTargetItemRecord.getTvvktb());
			logger.info("[INFO] Nettov. (on item record):" + nctsExportTargetItemRecord.getTvvktn());
			logger.info("[INFO] Avs.land (on item record):" + nctsExportTargetItemRecord.getTvalk());
			logger.info("[INFO] Best.land (on item record):" + nctsExportTargetItemRecord.getTvblk());
			logger.info("[INFO] 44.Doc.type (on item record):" + nctsExportTargetItemRecord.getTvdty());
			//
			String BASE_URL_UPDATE = UrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + applicationUser);
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + nctsExportTargetItemRecord.getTvavd().trim());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + nctsExportTargetItemRecord.getTvtdn().trim());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + nctsExportTargetItemRecord.getTvli().trim());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TdsConstants.MODE_UPDATE);
			//execute
			String urlRequestParamsTopicItem = this.urlRequestParameterMapper.getUrlParameterValidString((nctsExportTargetItemRecord));
			//put the final valid param. string
			String urlRequestParams = urlRequestParamsKeys + urlRequestParamsTopicItem;
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL_UPDATE);
	    	logger.info("URL PARAMS: " + urlRequestParams);
	    	//----------------------------------------------------------------------------
	    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
	    	//----------------------------------------------------------------------------
			String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_UPDATE, urlRequestParams);
			//Debug --> 
	    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
	    	//we must evaluate a return RPG code in order to know if the Update was OK or not
	    	RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
	    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgReturnPayload);
	    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
	    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
	    		//this.setFatalError(modelTODO, rpgReturnResponseHandler, nctsExportTargetItemRecord);
	    		
	    	}else{
	    		//Update succefully done!
	    		logger.info("[INFO] Valid default Item Line CREATE -- Record successfully updated, OK ");
	    	}
			
		}else{
			//Error here
		}
	}
	
	
	/**
	 * 
	 * @param nctsExportTargetItemRecord
	 */
	private void validateDefaultNctsItemNr(JsonNctsExportSpecificTopicItemRecord nctsExportTargetItemRecord){
		//Commodity code
		if(nctsExportTargetItemRecord.getTvvnt()!=null && !"".equals(nctsExportTargetItemRecord.getTvvnt())){
			String tmp = nctsExportTargetItemRecord.getTvvnt();
			if(tmp.length()>6){
				nctsExportTargetItemRecord.setTvvnt(tmp.substring(0,6));
			}
			//Description
			if(nctsExportTargetItemRecord.getTvvt()!=null && !"".equals(nctsExportTargetItemRecord.getTvvt())){
				//OK
			}else{
				nctsExportTargetItemRecord.setTvvt("CLONE DEFAULT");
			}
		}else{
			nctsExportTargetItemRecord.setTvvnt("660000");
			nctsExportTargetItemRecord.setTvvt("CLONE DEFAULT");
		}
		
	}
    
	/**
	 * 
	 * @param applicationUser
	 * @param avd
	 * @param opd
	 * @return
	 */
	private JsonNctsExportSpecificTopicItemRecord createNewItemKeySeeds(String applicationUser, String avd, String opd){
		
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		//request variables
		JsonNctsExportSpecificTopicItemRecord jsonSkatNctsExportSpecificTopicItemRecord = new JsonNctsExportSpecificTopicItemRecord();
		
		try{
			//---------------------------
			//get BASE URL = RPG-PROGRAM
	        //---------------------------
			String BASE_URL = UrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
			
			//-------------------------------------------------------------------------------------------
			// STEP[PREPARE CREATION] --> generate new opd and tuid (if applicable) in order to be able to Add (Create)
			//-------------------------------------------------------------------------------------------
			logger.info("STEP[1] GET SEEDS and CREATE RECORD...");
			String numberOfItemLinesInTopicStr = "1";
			StringBuffer urlRequestParamsForSeed = new StringBuffer();
			//
			urlRequestParamsForSeed.append("user=" + applicationUser);
			urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
			urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
			urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + numberOfItemLinesInTopicStr);
			urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TdsConstants.MODE_ADD);
			logger.info("New SEEDs URL: " + BASE_URL);
			logger.info("PARAMS for SEED: " + urlRequestParamsForSeed.toString());
					
			//Get the counter from RPG (new opd Id)
			String rpgSeedNumberPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsForSeed.toString());
			// Map the JSON response to the new seeds (thtdn,tvli)
			// We are not using std JSON conversion since the RPGs strings are not the same. Should be the same as
			// the header fields. The RPG output should be changed in order to comply to the field specification...
			rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgSeedNumberPayload);
			
			//we must complete the GUI-json with the value from a line nr seed here
			if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage()) ){
				logger.info("[ERROR] No mandatory seeds (tvli, opd) were generated correctly)! look at std output log. [errMsg]" + rpgReturnResponseHandler.getErrorMessage());
				jsonSkatNctsExportSpecificTopicItemRecord = null;
				
			}else{
				jsonSkatNctsExportSpecificTopicItemRecord.setTvtdn(rpgReturnResponseHandler.getThtdn());
				jsonSkatNctsExportSpecificTopicItemRecord.setTvli(rpgReturnResponseHandler.getTvli());
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return jsonSkatNctsExportSpecificTopicItemRecord;
	}
	
	
	
	
	/**
	 * Generates key seeds for an upcoming update (the generation of this keys creates also a new record ready to be updated)
	 * The method must be seen as STEP ONE in an upcoming update [same transaction].
	 * 
	 * @param session
	 * @param request
	 * @param user
	 * @param avd
	 * @param sign
	 * 
	 * @return 
	 */
	private JsonNctsExportSpecificTopicRecord createNewTopicHeaderKeySeeds(HttpSession session, HttpServletRequest request, SystemaWebUser user, String avd, String sign){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonNctsExportSpecificTopicRecord jsonNctsExportSpecificTopicRecord = new JsonNctsExportSpecificTopicRecord();
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = UrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
		
		//----------------------------------------------------------------------------------------------------------
		// STEP[PREPARE CREATION] --> generate new opd and tuid (if applicable) in order to be able to Add (Create)
		//----------------------------------------------------------------------------------------------------------
		logger.info("STEP[1] GET SEEDS and CREATE RECORD...");
		StringBuffer urlRequestParamsForSeed = new StringBuffer();
		urlRequestParamsForSeed.append("user=" + user.getUser());
		//for debug purposes in GUI
		session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL);
				
		String avdIdForCreate = avd;
		String signatureForCreate = sign;
		urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thavd=" + avdIdForCreate);
		urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + signatureForCreate);
		urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TdsConstants.MODE_ADD);
		logger.info("BASE_URL: " + BASE_URL);
		logger.info("PARAMS for SEED: " + urlRequestParamsForSeed.toString());
		
		//Get the counter from RPG (new thtdn (opd) Id)
		String rpgSeedNumberPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsForSeed.toString());
		logger.info("RpgSeedNumberPayload: " + rpgSeedNumberPayload);
		
		// Map the JSON response to the new seeds (syop,tuid and ombud fields)
		// We are not using std JSON conversion since the RPGs strings are not the same. Should be the same as
		// the header fields. The RPG output should be changed in order to comply to the field specification...
		rpgReturnResponseHandler.getNewSeedsOpdAndTuidRequiredForCreateNewTopic(rpgSeedNumberPayload);
		logger.info("### thtdn from RPG PROGRAM: " + rpgReturnResponseHandler.getThtdn());
		logger.info("### thtuid from RPG PROGRAM: " + rpgReturnResponseHandler.getThtuid());
		
		//we must complete the GUI-json sypo and tuid with the value from a seedTuid here
		if(rpgReturnResponseHandler.getThtdn()!=null && rpgReturnResponseHandler.getThtuid()!=null){
			jsonNctsExportSpecificTopicRecord.setThtdn(rpgReturnResponseHandler.getThtdn().trim());
			jsonNctsExportSpecificTopicRecord.setThtuid(rpgReturnResponseHandler.getThtuid().trim());
			jsonNctsExportSpecificTopicRecord.setLrnNr(rpgReturnResponseHandler.getThtuid().trim());
			jsonNctsExportSpecificTopicRecord.setThavd(avdIdForCreate);
			jsonNctsExportSpecificTopicRecord.setThsg(signatureForCreate);
			
			
		}else{
			logger.info("[ERROR] No mandatory seeds (lrn/mrn, opd) were generated correctly)! look at std output log. [errMsg]" + rpgReturnResponseHandler.getErrorMessage());
			jsonNctsExportSpecificTopicRecord = null;
		}
        
		return jsonNctsExportSpecificTopicRecord;
	}
	
	/**
	 * log Errors in Aspects and Domain objects in order to render on GUI
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param jsonTdsExportSpecificTopicRecord
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonNctsExportSpecificTopicRecord jsonNctsExportSpecificTopicRecord){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, jsonNctsExportSpecificTopicRecord);
	}
	
	/**
	 * Gets the key parameter string (such as avd, opd, user, mode)
	 * @param action
	 * @param avd
	 * @param opd
	 * @param sign
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(String action, String avd, String opd, String sign, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		
		if(TdsConstants.ACTION_FETCH.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
			
		}else if(TdsConstants.ACTION_UPDATE.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd); //thavd is the one used in the AS400 pgm (sends in the jsonRecord bean but this must be sent, in addition)
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd); //thtdn is the one used in the AS400 pgm (sends in the jsonRecord bean but this must be sent, in addition)
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TdsConstants.MODE_UPDATE);
			
		}else if(TdsConstants.ACTION_CREATE.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd); //thavd
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd); //thtdn
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + sign);
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TdsConstants.MODE_ADD);
			
		}else if(TdsConstants.ACTION_SEND.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thavd=" + avd);
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thtdn=" + opd);
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TdsConstants.MODE_SEND);
			
		}
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * Change the status in a specific topic
	 * 
	 * @param avd
	 * @param opd
	 * @param newStatus
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForUpdateStatus(String avd, String opd, String newStatus, SystemaWebUser appUser){
		
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		if(newStatus != null & !"".equals(newStatus)){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "status=" + newStatus);
		}
		return urlRequestParamsKeys.toString();	
	}
	
	/**
	 * Print parameters
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForPrint(String avd, String opd, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		return urlRequestParamsKeys.toString();	
	}
	
	/**
	 * 
	 * @param avd
	 * @param newAvd
	 * @param newSign
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForCopy(String avd, String newAvd, String newSign, String opd, SystemaWebUser appUser){
		//user=OSCAR&avd=1&newavd=2&opd=218&mode=C&newsign=OT 
		final String MODE_COPY = "C";
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thavd=" + avd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "newavd=" + newAvd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thtdn=" + opd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + MODE_COPY);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "newsign=" + newSign);
		
		
		return urlRequestParamsKeys.toString();	
	}
	/**
	 * Populates the html object (model map for the JSTL)
	 * 
	 * @param session
	 * @param model
	 * @param jsonNctsExportSpecificTopicContainer
	 * @return
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonNctsExportSpecificTopicContainer jsonNctsExportSpecificTopicContainer, NctsExportSpecificTopicTotalItemLinesObject totalItemLinesObject){
		//SET HEADER RECORDS  (from RPG)
		for (JsonNctsExportSpecificTopicRecord record : jsonNctsExportSpecificTopicContainer.getOneorder()){
			if(totalItemLinesObject!=null){
				record.setSumOfAntalKolliInItemLines(totalItemLinesObject.getSumOfAntalKolliInItemLines());
				record.setSumOfAntalItemLines(totalItemLinesObject.getSumOfAntalItemLines());
			}
			model.put(TdsConstants.DOMAIN_RECORD, record);
			//put the header topic in session for the coming item lines
			session.setAttribute(TdsConstants.DOMAIN_RECORD_TOPIC, record);
		}

	}
	/**
	 * 
	 * @param session
	 * @param model
	 * @param jsonNctsExportSpecificTopicContainer
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonNctsExportSpecificTopicContainer jsonNctsExportSpecificTopicContainer){
		//SET HEADER RECORDS  (from RPG)
		for (JsonNctsExportSpecificTopicRecord record : jsonNctsExportSpecificTopicContainer.getOneorder()){
			model.put(TdsConstants.DOMAIN_RECORD, record);
			//put the header topic in session for the coming item lines
			session.setAttribute(TdsConstants.DOMAIN_RECORD_TOPIC_TDS_NCTS_EXPORT, record);
		}

	}
	
	/**
	 * Populates the html object (model map for the JSTL)
	 * 
	 * @param model
	 * @param jsonTdsExportSpecificTopicRecord
	 * @return
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonNctsExportSpecificTopicRecord jsonNctsExportSpecificTopicRecord){
		//SET HEADER RECORDS  (from RPG)
		model.put(TdsConstants.DOMAIN_RECORD, jsonNctsExportSpecificTopicRecord);
		//put the header topic in session for the coming item lines
		session.setAttribute(TdsConstants.DOMAIN_RECORD_TOPIC, jsonNctsExportSpecificTopicRecord);
	}
	/**
	 * 
	 * 
	 * @param model
	 * @param jsonTdsExportSpecificTopicRecord
	 */
	private void setDomainObjectsInView(Map model, JsonNctsExportSpecificTopicRecord jsonNctsExportSpecificTopicRecord){
		//SET HEADER RECORDS  (from RPG)
		model.put(TdsConstants.DOMAIN_RECORD, jsonNctsExportSpecificTopicRecord);
	}
	/**
	 * Sets aspects 
	 * Usually error objects, log objects, other non-domain related objects
	 * @param model
	 */
	
	private void setAspectsInView (Map model, RpgReturnResponseHandler rpgReturnResponseHandler){
		model.put(TdsConstants.ASPECT_ERROR_MESSAGE, rpgReturnResponseHandler.getErrorMessage());
		//extra error information
		StringBuffer errorMetaInformation = new StringBuffer();
		errorMetaInformation.append(rpgReturnResponseHandler.getUser());
		errorMetaInformation.append(rpgReturnResponseHandler.getThtdn());
		model.put(TdsConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
	
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	private void populateAvdelningHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser){
		//fill in html lists here
		String NCTS_EXPORT_IE = "X"; //Export
		try{
			String BASE_URL = TdsUrlDataStore.TDS_FETCH_AVDELNINGAR_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + NCTS_EXPORT_IE);
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("AVD BASE_URL:" + BASE_URL);
			logger.info("AVD BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonTdsAvdelningContainer container = this.tdsDropDownListPopulationService.getAvdelningContainer(url);
			List<JsonTdsAvdelningRecord> list = new ArrayList();
			for(JsonTdsAvdelningRecord record: container.getAvdelningar()){
				list.add(record);
			}
			model.put(TdsConstants.RESOURCE_MODEL_KEY_AVD_LIST, list);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	private void populateSignatureHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser){
		//fill in html lists here
		String NCTS_EXPORT_IMPORT_IE = "N"; //NCTS = ie=N 
		
		try{
			String BASE_URL = TdsUrlDataStore.TDS_FETCH_SIGNATURE_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + NCTS_EXPORT_IMPORT_IE);
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("SIGN BASE_URL:" + BASE_URL);
			logger.info("SIGN BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonTdsSignatureContainer container = this.tdsDropDownListPopulationService.getSignatureContainer(url);
			List<JsonTdsSignatureRecord> list = new ArrayList();
			for(JsonTdsSignatureRecord record: container.getSignaturer()){
				list.add(record);
			}
			model.put(TdsConstants.RESOURCE_MODEL_KEY_SIGN_LIST, list);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private NctsExportSpecificTopicTotalItemLinesObject getRequiredSumsInItemLines(String avd, String opd, SystemaWebUser appUser){
		NctsExportSpecificTopicTotalItemLinesObject totalItemLinesObject = new NctsExportSpecificTopicTotalItemLinesObject();
		//-----------------------------------------------------
		//FETCH the ITEM LIST of existent ITEMs for this TOPIC
		//-----------------------------------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL_FETCH = UrlDataStore.NCTS_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&avd=" + avd + "&opd=" + opd;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("FETCH av item list... ");
	    	logger.info("URL: " + BASE_URL_FETCH);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
		String jsonPayloadFetch = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
		//Debug --> 
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayloadFetch));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	JsonNctsExportSpecificTopicItemContainer jsonNctsExportSpecificTopicItemContainer = this.getNctsExportSpecificTopicItemService().getNctsExportSpecificTopicItemContainer(jsonPayloadFetch);
	    	//now to the real logic
	    	int antalKolli = 0;
	    	double grossWeight = 0.0D;
	    	int numberOfItemLines = 0;
	    	
	    	if(jsonNctsExportSpecificTopicItemContainer!=null){
		    	for(JsonNctsExportSpecificTopicItemRecord record : jsonNctsExportSpecificTopicItemContainer.getOrderList()){
		    		numberOfItemLines++;
		    		
		    		if(record.getTvnt()!=null && !"".equals(record.getTvnt())){
		    			try{
		    				antalKolli += Integer.parseInt(record.getTvnt());
		    			}catch(Exception e){
		    				//logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    		if(record.getTvnt2()!=null && !"".equals(record.getTvnt2())){
		    			try{
		    				antalKolli += Integer.parseInt(record.getTvnt2());
		    			}catch(Exception e){
		    				//logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    		if(record.getTvnt3()!=null && !"".equals(record.getTvnt3())){
		    			try{
		    				antalKolli += Integer.parseInt(record.getTvnt3());
		    			}catch(Exception e){
		    				//logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    		if(record.getTvnt4()!=null && !"".equals(record.getTvnt4())){
		    			try{
		    				antalKolli += Integer.parseInt(record.getTvnt4());
		    			}catch(Exception e){
		    				//logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    		if(record.getTvnt5()!=null && !"".equals(record.getTvnt5())){
		    			try{
		    				antalKolli += Integer.parseInt(record.getTvnt5());
		    			}catch(Exception e){
		    				//logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    		//bruttovikt
		    		if(record.getTvvktb()!=null && !"".equals(record.getTvvktb())){
		    			try{
		    				grossWeight += Double.parseDouble(record.getTvvktb());
		    			}catch(Exception e){
		    				//logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    	}
	    	}
	    	//This is to flag the fact that no antal kolli exist DESPITE the fact that there is 1 or more item lines
	    	//to be used in validation...
	    	if(numberOfItemLines>0 && antalKolli==0){
	    		antalKolli = -1;
	    	}
	    	totalItemLinesObject.setSumOfAntalItemLines(numberOfItemLines);
	    	totalItemLinesObject.setSumOfAntalKolliInItemLines(antalKolli);
	    	
	    	//DEBUG
	    	logger.info("AntalKolli: " + totalItemLinesObject.getSumOfAntalKolliInItemLines());
	    	logger.info("AntalItems: " + totalItemLinesObject.getSumOfAntalItemLines());
	    	
	    
	    	return totalItemLinesObject;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 *
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_MDX_CURRENCY, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_GCY_COUNTRY, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_012_SPRAK, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_031_DEKLTYPE, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_096_SPEC_OMST, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_116_BETALNING_TRANSPORT, null, null);
		
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param avd
	 * @param opd
	 * @return
	 */
	public String calculateGuaranteeAmount(String applicationUser, String avd, String opd){
		String retval = "";
		
		logger.info("FETCH calculation...");
		//---------------------------
		//get BASE URL = RPG-PROGRAM
		//---------------------------
		String BASE_URL = UrlDataStore.NCTS_EXPORT_BASE_CALCULATE_SPECIFIC_TOPIC_GUARRANTEE_URL;
		//url params
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + applicationUser + "&avd=" + avd + "&opd=" + opd);
		//for debug purposes in GUI
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.warn("URL: " + BASE_URL);
		logger.warn("URL PARAMS: " + urlRequestParamsKeys.toString());
		//--------------------------------------
		//EXECUTE the FETCH (RPG program) here
		//--------------------------------------
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
		//Debug --> 
		logger.info(" --> jsonPayload:" + jsonPayload);
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");

		if(jsonPayload!=null){
    		JsonNctsExportSpecificTopicContainer container = this.nctsExportSpecificTopicService.getNctsExportSpecificTopicContainer(jsonPayload);
    		if(container!=null){
    			logger.warn("Guarantee amount via AJAX: " + container.getAmount() );
    			retval = container.getAmount();
    		}
    	}
		return retval;
		
	}
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("nctsExportSpecificTopicService")
	private NctsExportSpecificTopicService nctsExportSpecificTopicService;
	@Autowired
	@Required
	public void setNctsExportSpecificTopicService (NctsExportSpecificTopicService value){ this.nctsExportSpecificTopicService = value; }
	public NctsExportSpecificTopicService getNctsExportSpecificTopicService(){ return this.nctsExportSpecificTopicService; }
	
	@Qualifier ("nctsExportSpecificTopicItemService")
	private NctsExportSpecificTopicItemService nctsExportSpecificTopicItemService;
	@Autowired
	@Required
	public void setNctsExportSpecificTopicItemService (NctsExportSpecificTopicItemService value){ this.nctsExportSpecificTopicItemService = value; }
	public NctsExportSpecificTopicItemService getNctsExportSpecificTopicItemService(){ return this.nctsExportSpecificTopicItemService; }
	
	
	@Qualifier ("tdsExportSpecificTopicItemService")
	private TdsExportSpecificTopicItemService tdsExportSpecificTopicItemService;
	@Autowired
	@Required
	public void setTdsExportSpecificTopicItemService (TdsExportSpecificTopicItemService value){ this.tdsExportSpecificTopicItemService = value; }
	public TdsExportSpecificTopicItemService getTdsExportSpecificTopicItemService(){ return this.tdsExportSpecificTopicItemService; }
	
	
	@Qualifier ("tdsExportSpecificTopicService")
	private TdsExportSpecificTopicService tdsExportSpecificTopicService;
	@Autowired
	@Required
	public void setTdsExportSpecificTopicService (TdsExportSpecificTopicService value){ this.tdsExportSpecificTopicService = value; }
	public TdsExportSpecificTopicService getTdsExportSpecificTopicService(){ return this.tdsExportSpecificTopicService; }
	
	
	@Qualifier ("dropDownPopulationService")
	private DropDownListPopulationService dropDownListPopulationService;
	@Autowired
	public void setDropDownPopulationService (DropDownListPopulationService value){ this.dropDownListPopulationService=value; }
	public DropDownListPopulationService getDropDownListPopulationService(){return this.dropDownListPopulationService;}
	
	@Qualifier ("tdsDropDownListPopulationService")
	private TdsDropDownListPopulationService tdsDropDownListPopulationService;
	@Autowired
	public void setTdsDropDownPopulationService (TdsDropDownListPopulationService value){ this.tdsDropDownListPopulationService=value; }
	public TdsDropDownListPopulationService getTdsDropDownListPopulationService(){return this.tdsDropDownListPopulationService;}
	
	@Autowired
	NctsExportControllerAjaxCommonFunctionsMgr nctsExportControllerAjaxCommonFunctionsMgr;
}

