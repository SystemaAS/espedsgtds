package no.systema.tds.nctsimport.controller;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
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
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;
import no.systema.tds.url.store.TdsUrlDataStore;
import no.systema.tds.util.TdsConstants;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsAvdelningContainer;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsAvdelningRecord;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureContainer;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureRecord;
import no.systema.tds.service.html.dropdown.TdsDropDownListPopulationService;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicRecord;
import no.systema.tds.tdsimport.model.jsonjackson.customer.JsonTdsImportCustomerRecord;
import no.systema.tds.nctsimport.model.jsonjackson.topic.JsonNctsImportSpecificTopicContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.JsonNctsImportSpecificTopicRecord;
import no.systema.tds.nctsimport.validator.NctsImportHeaderValidator;
import no.systema.tds.nctsimport.service.NctsImportSpecificTopicService;
import no.systema.tds.nctsimport.service.html.dropdown.DropDownListPopulationService;
import no.systema.tds.nctsimport.url.store.UrlDataStore;
import no.systema.tds.nctsimport.util.RpgReturnResponseHandler;
import no.systema.tds.nctsimport.util.manager.CodeDropDownMgr;
import no.systema.tds.nctsimport.mapper.url.request.UrlRequestParameterMapper;


/**
 * NCTS Import Topic Controller 
 * 
 * @author oscardelatorre
 * @date Dec 4, 2103
 */

@Controller
@Scope("session")
public class NctsImportHeaderController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(NctsImportHeaderController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();

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
	@RequestMapping(value="nctsimport_edit.do",  params="action=doPrepareCreate", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doPrepareCreate(HttpSession session, HttpServletRequest request){
		
		Map model = new HashMap();
		String sign = request.getParameter("sign");
		String avd = request.getParameter("avd");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		ModelAndView successView = new ModelAndView("nctsimport_edit");
		logger.info("Method: doPrepareCreate [RequestMapping-->nctsimport_edit.do]");
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{

			this.setCodeDropDownMgr(appUser, model);
			this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
    		this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
    		//domain
    		model.put("sign", sign);
    		model.put("avd", avd);
    		JsonNctsImportSpecificTopicRecord record = this.initCreateNewTopic(appUser.getUser(), avd);
    		model.put("record", record);
    		successView.addObject("model", model);
    		successView.addObject(TdsConstants.EDIT_ACTION_ON_TOPIC, TdsConstants.ACTION_CREATE);

		}
		
		return successView;
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param avd
	 * @return
	 */
	 public JsonNctsImportSpecificTopicRecord initCreateNewTopic(String applicationUser, String avd) {
		 
		 	String method = "initCreateNewTopic";
		 	logger.info("Inside " + method);
		 	JsonNctsImportSpecificTopicRecord result = new JsonNctsImportSpecificTopicRecord();
		 	
		 	logger.info("FETCH record transaction...");
			//---------------------------
			//get BASE URL = RPG-PROGRAM
			//---------------------------
			String BASE_URL = UrlDataStore.NCTS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
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
	    		JsonNctsImportSpecificTopicContainer container = this.nctsImportSpecificTopicService.getNctsImportSpecificTopicContainer(jsonPayload);
	    		if(container!=null){
	    			for(JsonNctsImportSpecificTopicRecord  record : container.getOneorder()){
	    				result = record;
	    			}
	    		}
	    	}
			return result;
		  
	  }
	/**
	 * Creates or Updates a new Topic (Arende)
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="nctsimport_edit.do")
	public ModelAndView doNctsImportEdit(@ModelAttribute ("record") JsonNctsImportSpecificTopicRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		
		ModelAndView successView = new ModelAndView("nctsimport_edit");
		String method = "doNctsImportEdit [RequestMapping-->nctsimport_edit.do]";
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		logger.info("Method: " + method);
		
		//---------------------------------
		//Crucial request parameters (Keys
		//---------------------------------
		String action = request.getParameter("action");
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		String status = request.getParameter("status");
		String datum = request.getParameter("datum");
		
		String ti0035 = request.getParameter("ti0035"); //test indicator
		logger.info("TEST flagga:<" + ti0035 +">");
		
		
		//Action (doFetch, doUpdate, doCreate)
		logger.info("Action:" + action);
		logger.info("Opd:" + opd);
		logger.info("Status:" + status);
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;
		}else{
			
			if(action!=null){
				//-------------
				//FETCH RECORD
				//-------------
				
				if(TdsConstants.ACTION_FETCH.equals(action)){
					logger.info("FETCH record transaction...");
					
					//---------------------------
					//get BASE URL = RPG-PROGRAM
		            //---------------------------
					String BASE_URL = UrlDataStore.NCTS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
					//url params
					String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
					//for debug purposes in GUI
					session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
					
					logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
				    	logger.info("URL: " + BASE_URL);
				    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
				    	//--------------------------------------
				    	//EXECUTE the FETCH (RPG program) here
				    	//--------------------------------------
				    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
						//Debug --> 
				    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
				    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
				    	if(jsonPayload!=null){
				    		JsonNctsImportSpecificTopicContainer jsonNctsImportSpecificTopicContainer = this.nctsImportSpecificTopicService.getNctsImportSpecificTopicContainer(jsonPayload);
				    		//add gui lists here
						this.setCodeDropDownMgr(appUser, model);
						this.setDomainObjectsInView(session, model, jsonNctsImportSpecificTopicContainer);
				    		
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
					
					NctsImportHeaderValidator validator = new NctsImportHeaderValidator();
					logger.info("VALIDATING...");
					if(opd!=null && !"".equals(opd)){
						//Update...
						//nothing
					}else{
						logger.info("avdXX: " + avd);
						logger.info("signXX: " + sign);
						
						//Create
						//we must lend these dropdown variables to the validation object
						recordToValidate.setTiavd(avd);
						recordToValidate.setTisg(sign);
						
					}
					validator.validate(recordToValidate, bindingResult);
					recordToValidate.setTi0035(ti0035);
					
				    //check for ERRORS
					if(bindingResult.hasErrors()){
					    	logger.info("[ERROR Validation] Record does not validate)");
					    	//put domain objects and do go back to the original view...
					    	recordToValidate.setTiavd(avd);
					    	recordToValidate.setTisg(sign);
					    	this.setDomainObjectsInView(session, model, recordToValidate);
					    	isValidCreatedRecordTransactionOnRPG = false;
					    	if(opd==null || "".equals(opd)){
					    		action = TdsConstants.ACTION_CREATE;
					    	}
				    }else{
				    		JsonNctsImportSpecificTopicRecord jsonNctsImportSpecificTopicRecord = null;
						String tuid = null;
						
						if(opd!=null && !"".equals(opd)){
							logger.info("PURE UPDATE transaction...");
							//PURE UDPATE transaction
							//this means that the update is an update of an existing record
							jsonNctsImportSpecificTopicRecord = new JsonNctsImportSpecificTopicRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonNctsImportSpecificTopicRecord);
							
				            binder.bind(request);
				            //binder.registerCustomEditor(...); // if needed
							logger.info("TIENKL [after bind]: " + jsonNctsImportSpecificTopicRecord.getTienkl());
							if(StringUtils.isNotEmpty(jsonNctsImportSpecificTopicRecord.getTitrnr())){
								jsonNctsImportSpecificTopicRecord.setTitrnr(jsonNctsImportSpecificTopicRecord.getTitrnr().toUpperCase());
							}
							//test indicator
							jsonNctsImportSpecificTopicRecord.setTi0035(ti0035);	
				            
						}else{
							logger.info("CREATE NEW follow by UDATE transaction...");
							//CREATE AND UPDATE transaction
							//This means that the update will be done AFTER a creation of an empty record. All this in the same transaction. 2 STEPS involved: (1)create and (2)update
							//---------------------------------------------------------------------------------------------
							//STEP[1] Generate new Topic key seeds (avd,opd,sign,tullid) by creating an empty new record. 
							//---------------------------------------------------------------------------------------------
							jsonNctsImportSpecificTopicRecord = this.createNewTopicHeaderKeySeeds(session, request, appUser, avd, sign);
							if(jsonNctsImportSpecificTopicRecord!=null){
								avd = jsonNctsImportSpecificTopicRecord.getTiavd();
								sign = jsonNctsImportSpecificTopicRecord.getTisg();
								opd = jsonNctsImportSpecificTopicRecord.getTitdn();
								
								//take the rest from GUI.
								jsonNctsImportSpecificTopicRecord = new JsonNctsImportSpecificTopicRecord();
								ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonNctsImportSpecificTopicRecord);
					            //binder.registerCustomEditor(...); // if needed
					            binder.bind(request);
					            
					            //Now set back with the generated values since the bind method above erases them...
					            jsonNctsImportSpecificTopicRecord.setTiavd(avd);
					            jsonNctsImportSpecificTopicRecord.setTitdn(opd);
					            jsonNctsImportSpecificTopicRecord.setTisg(sign);
					            if(StringUtils.isNotEmpty(jsonNctsImportSpecificTopicRecord.getTitrnr())){
									jsonNctsImportSpecificTopicRecord.setTitrnr(jsonNctsImportSpecificTopicRecord.getTitrnr().toUpperCase());
								}
					            //test indicator
								jsonNctsImportSpecificTopicRecord.setTi0035(ti0035);
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
							String BASE_URL = UrlDataStore.NCTS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
							
							//-----------------------------------
							//add URL-parameter "mode=U" (Update)
							//-----------------------------------
							String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
							//build the url parameters (from GUI-form) to send on a GET/POST method AFTER the keyParameters
							String urlRequestParamsTopic = this.urlRequestParameterMapper.getUrlParameterValidString((jsonNctsImportSpecificTopicRecord));
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
						    		this.setFatalError(model, rpgReturnResponseHandler, jsonNctsImportSpecificTopicRecord);
						    		isValidCreatedRecordTransactionOnRPG = false;
						    	}else{
						    		//Update succefully done!
						    		logger.info("[INFO] Record successfully updated, OK ");
						    		//Godsnr. must be updated from the rpgReturn since there is a suffix added that must update the domain object
						    		jsonNctsImportSpecificTopicRecord.setTign(rpgReturnResponseHandler.getTign());
						    		//put domain objects
						    		this.setDomainObjectsInView(session, model, jsonNctsImportSpecificTopicRecord);
						    		this.adjustValidUpdateFlag(model, jsonNctsImportSpecificTopicRecord);
						    	}
							}else{
								rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tuid, syop generation : " + rpgReturnResponseHandler.getErrorMessage());
								this.setFatalError(model, rpgReturnResponseHandler, jsonNctsImportSpecificTopicRecord);
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
	
	
	private void adjustValidUpdateFlag(Map model, JsonNctsImportSpecificTopicRecord record){
		record.setValidUpdate(true);
		model.put(TdsConstants.DOMAIN_RECORD, record);
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="nctsimport_send.do")
	public ModelAndView doNctsImportSend(HttpSession session, HttpServletRequest request){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_NCTS_IMPORT);
		ModelAndView successView = new ModelAndView("redirect:nctsimport.do?action=doFind&sign=" + appUser.getTdsSign());
		
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
			String BASE_URL = UrlDataStore.NCTS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
			
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
		    		//TODO ERROR HANDLING HERE... stay in the same page ?
		    	}else{
		    		//Update succefully done!
		    		logger.info("[INFO] Record successfully sent [changed status], OK ");
		    		//put domain objects
		    		//this.setDomainObjectsInView(session, model, jsonNctsImportSpecificTopicRecord);
		    		//TODO SUCCESS should stay at the same side or not? Right now we go to the list of topics
		    	}
			
		}
		return successView;
	}
	
	/**
	 * 
	 * Admin purposes. Updates a status in order to enable the administrator with this task
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="nctsimport_updateStatus.do")
	public ModelAndView doUpdateStatus(HttpSession session, HttpServletRequest request){

		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_NCTS_IMPORT);
		ModelAndView successView = new ModelAndView("redirect:nctsimport.do?action=doFind&sign=" + appUser.getTdsSign());
		
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
			String BASE_URL = UrlDataStore.NCTS_IMPORT_BASE_UPDATE_STATUS_URL;
			
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
	private JsonNctsImportSpecificTopicRecord createNewTopicHeaderKeySeeds(HttpSession session, HttpServletRequest request, SystemaWebUser user, String avd, String sign){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonNctsImportSpecificTopicRecord jsonNctsImportSpecificTopicRecord = new JsonNctsImportSpecificTopicRecord();
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = UrlDataStore.NCTS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
		
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
		urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tiavd=" + avdIdForCreate);
		urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + signatureForCreate);
		urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TdsConstants.MODE_ADD);
		logger.info("BASE_URL: " + BASE_URL);
		logger.info("PARAMS for SEED: " + urlRequestParamsForSeed.toString());
		
		//Get the counter from RPG (new titdn (opd) Id)
		String rpgSeedNumberPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsForSeed.toString());
		logger.info("RpgSeedNumberPayload: " + rpgSeedNumberPayload);
		
		// Map the JSON response to the new seeds (syop,tuid and ombud fields)
		// We are not using std JSON conversion since the RPGs strings are not the same. Should be the same as
		// the header fields. The RPG output should be changed in order to comply to the field specification...
		rpgReturnResponseHandler.getNewSeedsOpdAndTuidRequiredForCreateNewTopic(rpgSeedNumberPayload);
		logger.info("### titdn from RPG PROGRAM: " + rpgReturnResponseHandler.getTitdn());
		
		//we must complete the GUI-json sypo and tuid with the value from a seedTuid here
		if(rpgReturnResponseHandler.getTitdn()!=null ){
			jsonNctsImportSpecificTopicRecord.setTitdn(rpgReturnResponseHandler.getTitdn().trim());
			jsonNctsImportSpecificTopicRecord.setTiavd(avdIdForCreate);
			jsonNctsImportSpecificTopicRecord.setTisg(signatureForCreate);
			
		}else{
			logger.info("[ERROR] No mandatory seeds (lrn/mrn, opd) were generated correctly)! look at std output log. [errMsg]" + rpgReturnResponseHandler.getErrorMessage());
			jsonNctsImportSpecificTopicRecord = null;
		}
        
		return jsonNctsImportSpecificTopicRecord;
	}
	
	/**
	 * log Errors in Aspects and Domain objects in order to render on GUI
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param jsonTdsExportSpecificTopicRecord
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonNctsImportSpecificTopicRecord jsonNctsImportSpecificTopicRecord){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, jsonNctsImportSpecificTopicRecord);
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
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tiavd=" + avd);
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "titdn=" + opd);
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TdsConstants.MODE_SEND);
			
		}
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
	private void setDomainObjectsInView(HttpSession session, Map model, JsonNctsImportSpecificTopicContainer jsonNctsImportSpecificTopicContainer){
		//SET HEADER RECORDS  (from RPG)
		for (JsonNctsImportSpecificTopicRecord record : jsonNctsImportSpecificTopicContainer.getOneorder()){
			model.put(TdsConstants.DOMAIN_RECORD, record);
			//put the header topic in session for the coming item lines
			session.setAttribute(TdsConstants.DOMAIN_RECORD_TOPIC, record);
		}

	}
	
	/**
	 * Populates the html object (model map for the JSTL)
	 * 
	 * @param model
	 * @param jsonTdsExportSpecificTopicRecord
	 * @return
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonNctsImportSpecificTopicRecord jsonNctsImportSpecificTopicRecord){
		//SET HEADER RECORDS  (from RPG)
		model.put(TdsConstants.DOMAIN_RECORD, jsonNctsImportSpecificTopicRecord);
		//put the header topic in session for the coming item lines
		session.setAttribute(TdsConstants.DOMAIN_RECORD_TOPIC, jsonNctsImportSpecificTopicRecord);
	}
	/**
	 * 
	 * 
	 * @param model
	 * @param jsonTdsExportSpecificTopicRecord
	 */
	private void setDomainObjectsInView(Map model, JsonNctsImportSpecificTopicRecord jsonNctsImportSpecificTopicRecord){
		//SET HEADER RECORDS  (from RPG)
		model.put(TdsConstants.DOMAIN_RECORD, jsonNctsImportSpecificTopicRecord);
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
		errorMetaInformation.append(rpgReturnResponseHandler.getTitdn());
		model.put(TdsConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
			
	
	
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	private void populateAvdelningHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser){
		//fill in html lists here
		String NCTS_IMPORT_IE = "N"; //Import
		try{
			String BASE_URL = TdsUrlDataStore.TDS_FETCH_AVDELNINGAR_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + NCTS_IMPORT_IE);
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
		String NCTS_IMPORT_IE = "N"; //NCTS = ie=N 
		
		try{
			String BASE_URL = TdsUrlDataStore.TDS_FETCH_SIGNATURE_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + NCTS_IMPORT_IE);
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
	 * @param appUser
	 * @param model
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
	}
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("nctsImportSpecificTopicService")
	private NctsImportSpecificTopicService nctsImportSpecificTopicService;
	@Autowired
	@Required
	public void setNctsImportSpecificTopicService (NctsImportSpecificTopicService value){ this.nctsImportSpecificTopicService = value; }
	public NctsImportSpecificTopicService getNctsImportSpecificTopicService(){ return this.nctsImportSpecificTopicService; }
	
	
	@Qualifier ("nctsImportDropDownListPopulationService")
	private DropDownListPopulationService nctsImportDropDownListPopulationService;
	@Autowired
	public void setNctsImportDropDownListPopulationService (DropDownListPopulationService value){ this.nctsImportDropDownListPopulationService=value; }
	public DropDownListPopulationService getNctsImportDropDownListPopulationService(){return this.nctsImportDropDownListPopulationService;}
	
	@Qualifier ("tdsDropDownListPopulationService")
	private TdsDropDownListPopulationService tdsDropDownListPopulationService;
	@Autowired
	public void setTdsDropDownPopulationService (TdsDropDownListPopulationService value){ this.tdsDropDownListPopulationService=value; }
	public TdsDropDownListPopulationService getTdsDropDownListPopulationService(){return this.tdsDropDownListPopulationService;}
	
	
}

