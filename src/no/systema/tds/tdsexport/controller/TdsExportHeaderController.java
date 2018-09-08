package no.systema.tds.tdsexport.controller;

import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
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
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.util.StringManager;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsAvdelningContainer;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsAvdelningRecord;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureContainer;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureRecord;

import no.systema.tds.service.html.dropdown.TdsDropDownListPopulationService;
import no.systema.tds.tdsexport.validator.TdsExportHeaderValidator;


import no.systema.main.model.SystemaWebUser;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicCheckItemErrorContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicRecord;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicCopiedContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicCopiedFromTransportUppdragContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicFaktTotalContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicFaktTotalRecord;


import no.systema.tds.tdsexport.service.TdsExportSpecificTopicService;
import no.systema.tds.tdsexport.service.TdsExportSpecificTopicItemService;

import no.systema.tds.tdsexport.service.html.dropdown.DropDownListPopulationService;
import no.systema.tds.tdsexport.url.store.TdsExportUrlDataStore;
import no.systema.tds.tdsexport.util.RpgReturnResponseHandler;
import no.systema.tds.url.store.TdsUrlDataStore;
import no.systema.tds.util.TdsConstants;
import no.systema.tds.util.manager.CodeDropDownMgr;
import no.systema.tds.util.manager.TaricDirectAccessorMgr;

import no.systema.tds.tdsexport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemRecord;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicFaktTotalContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicFaktTotalRecord;
import no.systema.tds.tdsimport.url.store.TdsImportUrlDataStore;



/**
 * TDS Export Topic Controller 
 * 
 * @author oscardelatorre
 *
 */

@Controller
@Scope("session")
public class TdsExportHeaderController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	private static final Logger logger = Logger.getLogger(TdsExportHeaderController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private TaricDirectAccessorMgr taricDirectAccessorMgr = new TaricDirectAccessorMgr();
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();

	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private StringManager strMgr = new StringManager();
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
		//binder.setValidator(new TdsExportValidator()); //it must have spring form tags in the html otherwise = meaningless
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
	@RequestMapping(value="tdsexport_edit.do",  params="action=doPrepareCreate", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doPrepareCreate(HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		String sign = request.getParameter("sign");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView successView = new ModelAndView("tdsexport_edit");
		logger.info("Method: doPrepareCreate [RequestMapping-->tdsexport_edit.do]");
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//add gui lists here
    		this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
    		this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
    		//land & currency codes
    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","GCY");
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","MDX");
    		//domain
			model.put("sign", sign);
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
	@RequestMapping(value="tdsexport_edit.do")
	public ModelAndView doTdsExportEdit(@ModelAttribute ("record") JsonTdsExportSpecificTopicRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tdsexport_edit");
		String method = "doTdsExportEdit [RequestMapping-->tdsexport_edit.do]";
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		logger.info("Method: " + method);
		//---------------------------------
		//Crucial request parameters (Keys)
		//---------------------------------
		String action = request.getParameter("action");
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		String sveh_0035 = request.getParameter("sveh_0035"); //test indicator
		
		logger.info("Testflagga i PROD:<" + sveh_0035 +">");
		//logger.info("Testavd. flagga:<" + recordToValidate.getTestAvdFlag() +">");
		
		//Action (doFetch, doUpdate, doCreate)
		logger.info("Action:" + action);
		logger.info("Opd:" + opd);
		Map model = new HashMap();
		
		
		if(appUser==null){
			return this.loginView;
		}else{
			if(action!=null){
				//This number must be > 0. We fetch this sum of kolliInItemLines in order to have it, first thing.
				JsonTdsExportSpecificTopicRecord sumTopicRecord = this.getSumOfSpecificFieldsInItemLines(avd, opd, appUser);
				JsonTdsExportSpecificTopicFaktTotalRecord sumFaktTotalRecord = this.getInvoiceTotalFromInvoices(avd, opd, appUser);
				
				
				//add gui lists here (common for FETCH, UPDATE, etc)
	    		this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
				this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
					
				//-------------
				//FETCH RECORD
				//-------------
				if(TdsConstants.ACTION_FETCH.equals(action)){
					logger.info("FETCH record transaction...");
					//---------------------------
					//get BASE URL = RPG-PROGRAM
		            //---------------------------
					String BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
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
			    		JsonTdsExportSpecificTopicContainer jsonTdsExportSpecificTopicContainer = this.tdsExportSpecificTopicService.getTdsExportSpecificTopicContainer(jsonPayload);
			    		//land and currency codes
			    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","GCY");
	    				this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","MDX");
	    				this.taricDirectAccessorMgr.askTullid(model);
	    				
			    		this.setDomainObjectsInView(session, model, jsonTdsExportSpecificTopicContainer,sumTopicRecord, sumFaktTotalRecord);
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
					//-----------
					//Validation
					//-----------
					logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
					if(opd!=null && !"".equals(opd)){
						logger.info("PURE UPDATE validation...");
						//We do fetch this number here in order to update the recordToValidate (for validation purposes) 
						recordToValidate.setSumOfAntalKolliInItemLines(sumTopicRecord.getSumOfAntalKolliInItemLines());
						recordToValidate.setSumOfGrossWeightInItemLines(sumTopicRecord.getSumOfGrossWeightInItemLines());
						recordToValidate.setSumOfInvoiceAmountInItemLines(sumTopicRecord.getSumOfInvoiceAmountInItemLines());
			            
					}else{
						recordToValidate.setSveh_syav(avd);
						recordToValidate.setSveh_sysg(sign);
					}
					TdsExportHeaderValidator validator = new TdsExportHeaderValidator();
					validator.validate(recordToValidate, bindingResult);
					recordToValidate.setSveh_0035(sveh_0035);
					
				    //check for ERRORS
					if(bindingResult.hasErrors()){
				    	logger.info("[ERROR Validation] Record does not validate)");
				    	//put domain objects and do go back to the original view...
				    	recordToValidate.setSveh_syav(avd);
				    	recordToValidate.setSveh_sysg(sign);
				    	this.setDomainObjectsInView(session, model, recordToValidate, sumTopicRecord);
				    	isValidCreatedRecordTransactionOnRPG = false;
				    	if(opd==null || "".equals(opd)){
				    		action = TdsConstants.ACTION_CREATE;
				    	}
				    }else{
			    		JsonTdsExportSpecificTopicRecord jsonTdsExportSpecificTopicRecord = null;
						String tuid = null;
						
						if(opd!=null && !"".equals(opd)){
							logger.info("PURE UPDATE transaction..."); 
							//PURE UDPATE transaction
							//this means that the update is an update of an existing record
							jsonTdsExportSpecificTopicRecord = new JsonTdsExportSpecificTopicRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonTdsExportSpecificTopicRecord);
				            //binder.registerCustomEditor(...); // if needed
							logger.info("SVEH_AVNA STEP 1: " + request.getParameter("sveh_avna"));
				            binder.bind(request);
				            //test indicator
				            jsonTdsExportSpecificTopicRecord.setSveh_0035(sveh_0035);
				            
						}else{
							logger.info("CREATE NEW follow by UDATE transaction...");
							//CREATE AND UPDATE transaction
							//This means that the update will be done AFTER a creation of an empty record. All this in the same transaction. 2 STEPS involved: (1)create and (2)update
							//---------------------------------------------------------------------------------------------
							//STEP[1] Generate new Topic key seeds (avd,opd,sign,tullid) by creating an empty new record. 
							//---------------------------------------------------------------------------------------------
							jsonTdsExportSpecificTopicRecord = this.createNewTopicHeaderKeySeeds(session, request, appUser, avd, sign);
							if(jsonTdsExportSpecificTopicRecord!=null){
								opd = jsonTdsExportSpecificTopicRecord.getSveh_syop();
								tuid = jsonTdsExportSpecificTopicRecord.getSveh_tuid();
								//
								jsonTdsExportSpecificTopicRecord.setSveh_syav(avd);
								jsonTdsExportSpecificTopicRecord.setSveh_sysg(sign);
								
								//take the rest from GUI.
								jsonTdsExportSpecificTopicRecord = new JsonTdsExportSpecificTopicRecord();
								ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonTdsExportSpecificTopicRecord);
					            //binder.registerCustomEditor(...); // if needed
					            binder.bind(request);
					            
					            //Now set back with the generated values since the bind method above erases them...
					            jsonTdsExportSpecificTopicRecord.setSveh_syav(avd);
								jsonTdsExportSpecificTopicRecord.setSveh_syop(opd);
								jsonTdsExportSpecificTopicRecord.setSveh_sysg(sign);
								jsonTdsExportSpecificTopicRecord.setSveh_tuid(tuid);
								//more completions
					            jsonTdsExportSpecificTopicRecord.setSumOfAntalKolliInItemLines(sumTopicRecord.getSumOfAntalKolliInItemLines());
					            jsonTdsExportSpecificTopicRecord.setSumOfGrossWeightInItemLines(sumTopicRecord.getSumOfGrossWeightInItemLines());
					            jsonTdsExportSpecificTopicRecord.setSumOfInvoiceAmountInItemLines(sumTopicRecord.getSumOfInvoiceAmountInItemLines());
					            
					            //test indicator
					            jsonTdsExportSpecificTopicRecord.setSveh_0035(sveh_0035);
								
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
							String BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
							
							//-----------------------------------
							//add URL-parameter "mode=U" (Update)
							//-----------------------------------
							String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
							//build the url parameters (from GUI-form) to send on a GET/POST method AFTER the keyParameters
							
							//Set fall-back fields if applicable. Usually deklarant-info. When deklarant = null then it will be = mottagare (receiver)
							//We check this right here though the jsonRecord (if applicable)
							this.setDeklarantFieldsIfApplicable(jsonTdsExportSpecificTopicRecord);
							
							String urlRequestParamsTopic = this.urlRequestParameterMapper.getUrlParameterValidString((jsonTdsExportSpecificTopicRecord));
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
					    		this.setFatalError(model, rpgReturnResponseHandler, jsonTdsExportSpecificTopicRecord);
					    		isValidCreatedRecordTransactionOnRPG = false;
					    	}else{
					    		if(this.validateItemLines(appUser, avd, opd)){
						    		//Update successfully done!
						    		logger.info("[INFO] Record successfully updated, OK ");
						    		//put domain objects
						    		this.setDomainObjectsInView(session, model, jsonTdsExportSpecificTopicRecord, sumTopicRecord);
						    		this.adjustValidUpdateFlag(model, jsonTdsExportSpecificTopicRecord);
					    		}else{
					    			rpgReturnResponseHandler.setErrorMessage("Varning: Ärendet har sparats. Det finns varuposter med felstatus som måste korrigeras. ");
						    		this.setFatalError(model, rpgReturnResponseHandler, jsonTdsExportSpecificTopicRecord);
						    		isValidCreatedRecordTransactionOnRPG = false;
					    		}
					    	}
						}else{
							rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tuid, syop generation : " + rpgReturnResponseHandler.getErrorMessage());
							this.setFatalError(model, rpgReturnResponseHandler, jsonTdsExportSpecificTopicRecord);
							isValidCreatedRecordTransactionOnRPG = false;
						}
				    }
					//land and currency codes
	    			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService,model,appUser,"A","GCY");
	    			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService,model,appUser,"A","MDX");
	    			this.taricDirectAccessorMgr.askTullid(model);
	    			
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
	 * Check if item lines = OK
	 * 
	 * @param appUser
	 * @param avd
	 * @param opd
	 * @return
	 */
	private boolean validateItemLines(SystemaWebUser appUser, String avd, String opd){
		boolean retval = true;
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_VALIDATION_URL;
		//url params
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&avd=" + avd + "&opd=" + opd;
		
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
    		JsonTdsExportSpecificTopicCheckItemErrorContainer container = this.tdsExportSpecificTopicService.getCheckItemErrorContainer(jsonPayload);
    		if(strMgr.isNotNull(container.getOk()) && "N".equals(container.getOk()) ){
    			retval = false;
    		}
    	}
    	
    	return retval;
	}
	
	
	/**
	 * 
	 * Aux method to prevent an end-user for sending the declaration without having saved it first.
	 * The end-user must save a topic before issuing a further "send". Sort of a validation routine to ensure validity of all fields.
	 * 
	 * @param model
	 * @param record
	 * 
	 */
	private void adjustValidUpdateFlag(Map model, JsonTdsExportSpecificTopicRecord record){
		record.setValidUpdate(true);
		model.put(TdsConstants.DOMAIN_RECORD, record);
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsexport_send.do")
	public ModelAndView doTdsExportSend(HttpSession session, HttpServletRequest request){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_EXPORT);
		ModelAndView successView = new ModelAndView("redirect:tdsexport.do?action=doFind&sign=" + appUser.getTdsSign());
		
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
			String BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
			
			//-----------------------------------
			//add URL-parameter "mode=S" (Send)
			//-----------------------------------
			String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
			//there are only key parameters in doSend. No other topic (record) specific parameters from GUI or such
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
	@RequestMapping(value="tdsexport_edit_printTopic.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doTdsExportEditPrintTopic(HttpSession session, HttpServletRequest request){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);

		ModelAndView successView = new ModelAndView("redirect:tdsexport.do?action=doFind&sign=" + appUser.getTdsSign());

		Map model = new HashMap();
		String method = "doTdsExportEditPrintTopic [RequestMapping-->tdsexport_edit_printTopic.do]";
		logger.info("Method: " + method);
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			//-------------------------------------
			//get BASE URL = RPG-PROGRAM for PRINT
            //-------------------------------------
			String BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_PRINT_FOR_SPECIFIC_TOPIC_URL;
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
	 * Copies one topic(ärende) to a new one (clones the source topic)
	 * STEP 1: Copy by getting JSON with the new record (new tuid, new opd, new avd, new sign)
	 * STEP 2: Fetch the record as if it was a selection of a topic in a list
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsexport_copyTopic.do", method={RequestMethod.POST} )
	public ModelAndView doCopyTopic( HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tdsexport_edit");
		ModelAndView fallbackOnErrorView = new ModelAndView("tdsexport");
		
		JsonTdsExportTopicCopiedContainer jsonTdsExportTopicCopiedContainer = null;
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		String method = "doCopyTopic [RequestMapping-->tdsexport_copyTopic.do]";
		logger.info("Method: " + method);
		Map model = new HashMap();
		
		//We must get all parameters from the enumeration since all have sequence counter number
		String action=null;
		String avd=null;
		String opd=null;
		String newAvd=null;
		String newSign=null;
		String fullCopy = null;
		
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
    			}else if(element.startsWith("fullCopy")){
    				fullCopy = value;
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
			String BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForCopy(avd, newAvd, newSign, opd, appUser, fullCopy);
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
	    		jsonTdsExportTopicCopiedContainer = this.tdsExportSpecificTopicService.getTdsExportTopicCopiedContainer(jsonPayload);
	    		if(jsonTdsExportTopicCopiedContainer!=null){
	    			//Check for errors
	    			if(jsonTdsExportTopicCopiedContainer.getErrMsg()!=null && !"".equals(jsonTdsExportTopicCopiedContainer.getErrMsg())){
	    				logger.fatal("[ERROR FATAL] errMsg containing: " + jsonTdsExportTopicCopiedContainer.getErrMsg());
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
			BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
			//url params
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, jsonTdsExportTopicCopiedContainer.getNewavd(), jsonTdsExportTopicCopiedContainer.getNewopd(), jsonTdsExportTopicCopiedContainer.getNewsign(), appUser);
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
	    		JsonTdsExportSpecificTopicContainer jsonTdsExportSpecificTopicContainer = this.tdsExportSpecificTopicService.getTdsExportSpecificTopicContainer(jsonPayload);
	    		//add gui lists here (common for FETCH, UPDATE, etc)
	    		this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
				this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
				
	    		//land and currency codes
	    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","GCY");
    			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","MDX");
    			this.taricDirectAccessorMgr.askTullid(model);
    			
	    		this.setDomainObjectsInView(session, model, jsonTdsExportSpecificTopicContainer);
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
	 * Copies one topic(ärende) to a new one, from (1) a Norsk Import or (2) Transport Uppdrag (order)
	 * STEP 1: Copy
	 * STEP 2: Fetch the record as if it was a selection of a topic in a list (Update mode)
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsexport_doFetchTopicFromTransportUppdrag.do", method={RequestMethod.POST} )
	public ModelAndView doFetchTopicFromTransportUppdrag( HttpSession session, HttpServletRequest request){
		JsonTdsExportTopicCopiedFromTransportUppdragContainer jsonContainer = null;
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String method = "[RequestMapping-->tdsexport_doFetchTopicFromTransportUppdrag.do]";
		logger.info("Method: " + method);
		Map model = new HashMap();
		
		//We must get all parameters from the enumeration since all have sequence counter number
		String action=request.getParameter("actionGS");;
		String avd=request.getParameter("selectedAvd");
		String opd=request.getParameter("selectedOpd");
		String sign = request.getParameter("sign");
		
		ModelAndView successView = new ModelAndView("tdsexport_edit");
		ModelAndView fallbackView = new ModelAndView("redirect:tdsexport_edit.do?action=doPrepareCreate&user="+appUser.getUser()+"&sign=" + sign);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			if( (opd!=null && !"".equals(opd)) && (avd!=null && !"".equals(avd))){
				//--------------------
				//STEP 1: COPY record
				//--------------------
				logger.info("starting PROCESS record transaction...");
				String BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
				String urlRequestParamsKeys = this.getRequestUrlKeyParametersForCopyTopicFromTransportUppdrag(avd, opd, appUser);
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
			    		jsonContainer = this.tdsExportSpecificTopicService.getTdsExportTopicCopiedFromTransportUppdragContainer(jsonPayload);
			    		if(jsonContainer!=null){
			    			//Check for errors
			    			if(jsonContainer.getErrMsg()!=null && !"".equals(jsonContainer.getErrMsg())){
			    				logger.info("[WARN] errMsg containing: " + jsonContainer.getErrMsg());
			    				logger.info("[WARN] redirecting to doPrepareCreate");
			    				
			    				return fallbackView;
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
				BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
				//url params
				urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, jsonContainer.getSveh_syav(), jsonContainer.getSveh_syop(), jsonContainer.getSign(), appUser);
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
		    		JsonTdsExportSpecificTopicContainer jsonTdsExportSpecificTopicContainer = this.tdsExportSpecificTopicService.getTdsExportSpecificTopicContainer(jsonPayload);
		    		//add gui lists here (common for FETCH, UPDATE, etc)
		    		this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
					this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
					
		    		//land and currency codes
		    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","GCY");
	    			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","MDX");
	    			this.taricDirectAccessorMgr.askTullid(model);
	    			
		    		this.setDomainObjectsInView(session, model, jsonTdsExportSpecificTopicContainer);
		    		successView.addObject(TdsConstants.DOMAIN_MODEL, model);
		    		//put the doUpdate action since we are preparing the record for an update (when saving)
		    		successView.addObject(TdsConstants.EDIT_ACTION_ON_TOPIC, TdsConstants.ACTION_UPDATE);
		    		
		    	}else{
		    		logger.fatal("[ERROR fatal] NO CONTENT on jsonPayload from URL... ??? <Null>");
		    		return loginView;
				}
			}else{
				logger.fatal("[INFO] Opdnr is NULL ");
				return fallbackView;
			}
			
			return successView;
		}
		
	}
	
	/**
	 * The method requests a clearance on a given topic (Begäran om klarering)
	 * 
	 * The procedure is done in 2 STEPs
	 * (1) Change the message type (meddelande typ) as ref. in tullverkets documentation (from: UNU, URT, UKO to UBK all with status="O"--> to UBK)
	 * (2) Send to signering. (Emulates exactly the same behavior as in method: doTdsExportSend, in this same class 
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsexport_requestClearanceOnTopic.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doTdsExportRequestClearanceOnTopic(HttpSession session, HttpServletRequest request){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView returnView = null;
		ModelAndView successView = new ModelAndView("redirect:tds_sign_pki.do");
		ModelAndView errorView = new ModelAndView("redirect:tdsexport.do?action=doFind&sign=" + appUser.getTdsSign());
		Map model = new HashMap();
		
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		String method = "doTdsExportRequestClearanceOnTopic [RequestMapping-->tdsexport_requestClearanceOnTopic.do]";
		logger.info("Method: " + method);
		String mtyp = null;
		String opd = null;
		String avd = null;
		
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
        			}else if(element.startsWith("originalMtyp")){
        				mtyp = value;
        			}
        		}
	    	}
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			//--------------
			//get BASE URL 
            //--------------
			String BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_UPDATE_MESSAGETYPE_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForCompletionEventsOnTopic( avd, opd, mtyp, appUser);
			//for debug purposes in GUI
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//-------------------------------------------------------
	    	//STEP [1] EXECUTE (RPG program) (Update message type)
	    	//-------------------------------------------------------
	    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
	    	logger.info(method + " --> rpgReturnPayload:" + rpgReturnPayload);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
	    	//we must evaluate a return RPG code in order to know if the Update was OK or not
	    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicUpdate(rpgReturnPayload);
	    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
	    		String errorPrefix = "[ERROR] FATAL on UPDATE: ";
	    		rpgReturnResponseHandler.setErrorMessage(errorPrefix + rpgReturnResponseHandler.getErrorMessage());
	    		logger.fatal(rpgReturnResponseHandler.getErrorMessage());
	    		this.setAspectsInView(model, rpgReturnResponseHandler);
	    		returnView = errorView;
	    		
	    	}else{
	    		//Update succefully done!
	    		logger.info("[INFO] Record successfully updated, OK ");
	    		//-------------------------------------------------------
		    	//STEP [2]  EXECUTE (RPG program) (Send to Signering)
		    	//-------------------------------------------------------
	    		BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
			
				//-----------------------------------
				//add URL-parameter "mode=S" (Send)
				//-----------------------------------
				urlRequestParamsKeys = this.getRequestUrlKeyParameters(TdsConstants.ACTION_SEND, avd, opd, null, appUser);
				//there are only key parameters in doSend. No other topic (record) specific parameters from GUI or such
				String urlRequestParams = urlRequestParamsKeys;
				//for debugging purposes
				session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL); 
				
				logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	//----------------------------------------------------------------------------
		    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
		    	//----------------------------------------------------------------------------
		    	rpgReturnPayload = null;
	    		rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
				//Debug --> 
		    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
		    	//we must evaluate a return RPG code in order to know if the Update was OK or not
		    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicUpdate(rpgReturnPayload);
		    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
		    		String errorPrefix = "[ERROR] FATAL on UPDATE: ";
		    		rpgReturnResponseHandler.setErrorMessage(errorPrefix + rpgReturnResponseHandler.getErrorMessage());
		    		logger.fatal(rpgReturnResponseHandler.getErrorMessage());
		    		this.setAspectsInView(model, rpgReturnResponseHandler);
		    		returnView = errorView;
		    		
		    	}else{
		    		//Update succefully done!
		    		logger.info("[INFO] Record successfully sent to Signering [changed status], OK ");
		    		returnView = successView;
		    		
		    	}
		    		
	    	}
		    		
		}
		
		return returnView;
	}

	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsexport_completionEventsOnTopic.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doTdsExportCompletionEventsOnTopic(HttpSession session, HttpServletRequest request){

		ModelAndView returnView = null;
		ModelAndView successView = new ModelAndView("tdsexport_edit");
		ModelAndView errorView = new ModelAndView("tdsexport_edit");
		errorView.addObject("action", "doPrepareCreate");

		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();

		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		String method = "[RequestMapping-->tdsexport_completionEventsOnTopic.do]";
		logger.info("Method: " + method);
		Map model = new HashMap();
		
		String mtyp = null;
		String opd = null;
		String avd = null;
		
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
        			}else if(element.startsWith("originalMtyp")){
        				mtyp = value;
        			}
        		}
	    	}
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			
			//--------------
			//get BASE URL 
            //--------------
			String BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_UPDATE_MESSAGETYPE_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForCompletionEventsOnTopic( avd, opd, mtyp, appUser);
			//for debug purposes in GUI
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//-------------------------------------------------------
	    	//STEP [1] EXECUTE (RPG program) (Update message type)
	    	//-------------------------------------------------------
	    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
	    	logger.info(method + " --> rpgReturnPayload:" + rpgReturnPayload);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    
	    	
	    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
	    	//we must evaluate a return RPG code in order to know if the Update was OK or not
	    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicUpdate(rpgReturnPayload);
	    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
	    		String errorPrefix = "[ERROR] FATAL on UPDATE: ";
	    		rpgReturnResponseHandler.setErrorMessage(errorPrefix + rpgReturnResponseHandler.getErrorMessage());
	    		logger.fatal(rpgReturnResponseHandler.getErrorMessage());
	    		this.setAspectsInView(model, rpgReturnResponseHandler);
	    		returnView = errorView;
	    		
	    	}else{
	    		//Update succefully done!
	    		logger.info("[INFO] Record successfully updated, OK ");
	    		//At this point we do now have an updated record. The only thing left is to present it in edit mode
	    		//-----------------------------------------------
	    		// FETCH record (in order to redirect to update)
		    	//-----------------------------------------------
				logger.info("FETCH record transaction...");
				//for Sums
				JsonTdsExportSpecificTopicRecord sumTopicRecord = this.getSumOfSpecificFieldsInItemLines(avd, opd, appUser);
				JsonTdsExportSpecificTopicFaktTotalRecord sumFaktTotalRecord = this.getInvoiceTotalFromInvoices(avd, opd, appUser);
				
				
				//---------------------------
				//get BASE URL = RPG-PROGRAM
		        //---------------------------
				BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
				//url params
				urlRequestParamsKeys = this.getRequestUrlKeyParameters(TdsConstants.ACTION_UPDATE, avd, opd, null, appUser);
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
		    		JsonTdsExportSpecificTopicContainer jsonTdsExportSpecificTopicContainer = this.tdsExportSpecificTopicService.getTdsExportSpecificTopicContainer(jsonPayload);
		    		//add gui lists here (common for FETCH, UPDATE, etc)
		    		this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
					this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
					
		    		//add gui lists here
		    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","GCY");
	    			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","MDX");
	    			this.taricDirectAccessorMgr.askTullid(model);
	    			
		    		this.setDomainObjectsInView(session, model, jsonTdsExportSpecificTopicContainer, sumTopicRecord, sumFaktTotalRecord);
		    		successView.addObject(TdsConstants.DOMAIN_MODEL, model);
		    		//put the doUpdate action since we are preparing the record for an update (when saving)
		    		successView.addObject(TdsConstants.EDIT_ACTION_ON_TOPIC, TdsConstants.ACTION_UPDATE);
		    	}else{
		    		logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
		    		return loginView;
		    	}
	    		returnView = successView;
	    	}
		}
		return returnView;
	}
	
	/**
	 * 
	 * Admin purposes. Updates a status in order to enable the administrator with this task
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="tdsexport_updateStatus.do")
	public ModelAndView doUpdateStatus(HttpSession session, HttpServletRequest request){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_EXPORT);
		ModelAndView successView = new ModelAndView("redirect:tdsexport.do?action=doFind&sign=" + appUser.getTdsSign());
		
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
			String BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_UPDATE_STATUS_URL;
			
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
	 * @param mtyp
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForCompletionEventsOnTopic(String avd, String opd, String mtyp,  SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sveh_mtyp=" + mtyp);
		
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
	 * 
	 * @param record
	 */
	private void setDeklarantFieldsIfApplicable(JsonTdsExportSpecificTopicRecord record){
		if(record.getSveh_dkkn()==null || "".equals(record.getSveh_dkkn())){
			record.setSveh_dkkn(record.getSveh_avkn());
		}
		if(record.getSveh_dkna()==null || "".equals(record.getSveh_dkna())){
			record.setSveh_dkna(record.getSveh_avna());
		}
		if(record.getSveh_dkeo()==null || "".equals(record.getSveh_dkeo())){
			record.setSveh_dkeo(record.getSveh_aveo());			
		}
		if(record.getSveh_dka1()==null || "".equals(record.getSveh_dka1())){
			record.setSveh_dka1(record.getSveh_ava1());			
		}
		if(record.getSveh_dka2()==null || "".equals(record.getSveh_dka2())){
			record.setSveh_dka2(record.getSveh_ava2());
		}
		if(record.getSveh_dkpa()==null || "".equals(record.getSveh_dkpa())){
			record.setSveh_dkpa(record.getSveh_avpa());
		}
		if(record.getSveh_dkpn()==null || "".equals(record.getSveh_dkpn())){
			record.setSveh_dkpn(record.getSveh_avpn());
		}
		if(record.getSveh_dklk()==null || "".equals(record.getSveh_dklk())){
			record.setSveh_dklk(record.getSveh_avlk());
		}
		
	}

	/**
	 * Sum of all item lines kolliantal
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private JsonTdsExportSpecificTopicRecord getSumOfSpecificFieldsInItemLines(String avd, String opd, SystemaWebUser appUser){
		//-----------------------------------------------------
		//FETCH the ITEM LIST of existent ITEMs for this TOPIC
		//-----------------------------------------------------
		JsonTdsExportSpecificTopicRecord topicRecord = new JsonTdsExportSpecificTopicRecord();
		
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL_FETCH = TdsExportUrlDataStore.TDS_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
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
    	JsonTdsExportSpecificTopicItemContainer jsonTdsExportSpecificTopicItemContainer = this.getTdsExportSpecificTopicItemService().getTdsExportSpecificTopicItemContainer(jsonPayloadFetch);
    	//now to the real logic
    	int antalKolli = 0;
    	double grossWeight = 0.00D;
    	double invoiceAmount = 0.00D;
    	int numberOfItemLines = 0;
    	if(jsonTdsExportSpecificTopicItemContainer!=null){
	    	for(JsonTdsExportSpecificTopicItemRecord record : jsonTdsExportSpecificTopicItemContainer.getOrderList()){
	    		numberOfItemLines++;
	    		//logger.info("ANTAL KOLLI Inside FOR--svevKota: " + record.getSvev_kota());
	    		if(record.getSvev_kota()!=null && !"".equals(record.getSvev_kota())){
	    			try{
	    				antalKolli += Integer.parseInt(record.getSvev_kota());
	    			}catch(Exception e){
	    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
	    			}
	    		}
	    		if(record.getSvev_kot2()!=null && !"".equals(record.getSvev_kot2())){
	    			try{
	    				antalKolli += Integer.parseInt(record.getSvev_kot2());
	    			}catch(Exception e){
	    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
	    			}
	    		}
	    		if(record.getSvev_kot3()!=null && !"".equals(record.getSvev_kot3())){
	    			try{
	    				antalKolli += Integer.parseInt(record.getSvev_kot3());
	    			}catch(Exception e){
	    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
	    			}
	    		}
	    		if(record.getSvev_kot4()!=null && !"".equals(record.getSvev_kot4())){
	    			try{
	    				antalKolli += Integer.parseInt(record.getSvev_kot4());
	    			}catch(Exception e){
	    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
	    			}
	    		}
	    		if(record.getSvev_kot5()!=null && !"".equals(record.getSvev_kot5())){
	    			try{
	    				antalKolli += Integer.parseInt(record.getSvev_kot5());
	    			}catch(Exception e){
	    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
	    			}
	    		}
	    		
	    		//gross weight
	    		if(record.getSvev_brut()!=null && !"".equals(record.getSvev_brut())){
	    			try{
	    				grossWeight += Double.parseDouble(record.getSvev_brut().replace(",", "."));
	    			}catch(Exception e){
	    				logger.info("[ERROR] on GROSS WEIGHT CATCH");
	    			}
	    		}
	    		//invoice amount
	    		if(record.getSvev_fabl()!=null && !"".equals(record.getSvev_fabl())){
	    			try{
	    				invoiceAmount += Double.parseDouble(record.getSvev_fabl().replace(",", "."));
	    			}catch(Exception e){
	    				logger.info("[ERROR] on INVOICE AMOUNT CATCH");
	    			}
	    		}
	    	}
    	}
    	//This is to flag the fact that no antal kolli exist DESPITE the fact that there is 1 or more item lines
    	//to be used in validation...
    	if(numberOfItemLines>0 && antalKolli==0){ antalKolli = -1; }
    	/*DEBUG
    	logger.info("AntalKolli(sum): " + antalKolli);
    	logger.info("Bruttovikt(sum): " + grossWeight);
    	logger.info("Fakt.belopp(sum): " + invoiceAmount);
    	*/
    	topicRecord.setSumOfAntalKolliInItemLines(antalKolli);
    	topicRecord.setSumOfGrossWeightInItemLines(grossWeight);
    	topicRecord.setSumOfInvoiceAmountInItemLines(this.numberFormatter.getDouble(invoiceAmount, 3));
    	
    	return topicRecord;
	}
	
	
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private JsonTdsExportSpecificTopicFaktTotalRecord getInvoiceTotalFromInvoices( String avd, String opd, SystemaWebUser appUser){
		//--------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		JsonTdsExportSpecificTopicFaktTotalRecord returnRecord = null;
		
		String BASE_URL_FETCH = TdsExportUrlDataStore.TDS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_FAKT_TOTAL_URL;
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&avd=" + avd + "&opd=" + opd;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("FETCH av item list... ");
    	logger.info("URL: " + BASE_URL_FETCH);
    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
    	//--------------------------------------
    	//EXECUTE the FETCH (RPG program) here
    	//--------------------------------------
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
		//Debug --> 
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	JsonTdsExportSpecificTopicFaktTotalContainer container = this.tdsExportSpecificTopicService.getTdsExportSpecificTopicFaktTotalContainer(jsonPayload);
    	if(container!=null){
	    	for(JsonTdsExportSpecificTopicFaktTotalRecord record : container.getInvTot()){
				returnRecord = record;
	    	}
    	}
		
		return returnRecord;
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
	private JsonTdsExportSpecificTopicRecord createNewTopicHeaderKeySeeds(HttpSession session, HttpServletRequest request, SystemaWebUser user,
																		 String avd, String sign){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonTdsExportSpecificTopicRecord jsonTdsExportSpecificTopicRecord = new JsonTdsExportSpecificTopicRecord();
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
		
		//----------------------------------------------------------------------------------------------------------
		// STEP[PREPARE CREATION] --> generate new opd and tuid (if applicable) in order to be able to Add (Create)
		//----------------------------------------------------------------------------------------------------------
		logger.info("STEP[1] GET SEEDS and CREATE RECORD...");
		StringBuffer urlRequestParamsForSeed = new StringBuffer();
		urlRequestParamsForSeed.append("user=" + user.getUser());
		//for debug purposes in GUI
		session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL);
				
		urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + sign);
		urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TdsConstants.MODE_ADD);
		logger.info("PARAMS for SEED: " + urlRequestParamsForSeed.toString());
		
		//Get the counter from RPG (new opd Id)
		String rpgSeedNumberPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsForSeed.toString());
		// Map the JSON response to the new seeds (syop,tuid and ombud fields)
		// We are not using std JSON conversion since the RPGs strings are not the same. Should be the same as
		// the header fields. The RPG output should be changed in order to comply to the field specification...
		rpgReturnResponseHandler.getNewSeedsOpdAndTuidRequiredForCreateNewTopic(rpgSeedNumberPayload);
		logger.info("### syop from RPG PROGRAM: " + rpgReturnResponseHandler.getSveh_syop());
		logger.info("### tuid from RPG PROGRAM: " + rpgReturnResponseHandler.getSveh_tuid());
		
		//we must complete the GUI-json sypo and tuid with the value from a seedTuid here
		if(rpgReturnResponseHandler.getSveh_syop()!=null && rpgReturnResponseHandler.getSveh_tuid()!=null){
			jsonTdsExportSpecificTopicRecord.setSveh_syop(rpgReturnResponseHandler.getSveh_syop().trim());
			jsonTdsExportSpecificTopicRecord.setSveh_tuid(rpgReturnResponseHandler.getSveh_tuid().trim());
			
		}else{
			logger.info("[ERROR] No mandatory seeds (tuid, opd) were generated correctly)! look at std output log. [errMsg]" + rpgReturnResponseHandler.getErrorMessage());
			jsonTdsExportSpecificTopicRecord = null;
		}
        
		return jsonTdsExportSpecificTopicRecord;
	}
	
	/**
	 * log Errors in Aspects and Domain objects in order to render on GUI
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param jsonTdsExportSpecificTopicRecord
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonTdsExportSpecificTopicRecord jsonTdsExportSpecificTopicRecord){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, jsonTdsExportSpecificTopicRecord);
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
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForCopyTopicFromTransportUppdrag(String avd, String opd, SystemaWebUser appUser){
		//user=OSCAR&avd=1&opd=53452&sign=CB&mode=GS 
		final String MODE = "GS";
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + appUser.getTdsSign());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + MODE);
		
		return urlRequestParamsKeys.toString();	
	}
	
	/**
	 * 
	 * @param avd
	 * @param newAvd
	 * @param newSign
	 * @param opd
	 * @param appUser
	 * @param fullCopy
	 * @return
	 */
	private String getRequestUrlKeyParametersForCopy(String avd, String newAvd, String newSign, String opd, SystemaWebUser appUser, String fullCopy){
		//user=OSCAR&avd=1&newavd=2&opd=218&mode=C&newsign=OT&f=1 
		final String MODE_COPY = "C";
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "newavd=" + newAvd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + MODE_COPY);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "newsign=" + newSign);
		if(strMgr.isNotNull(fullCopy)){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "full=" + fullCopy);
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
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TdsConstants.MODE_UPDATE);
			
		}else if(TdsConstants.ACTION_CREATE.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + sign);
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TdsConstants.MODE_ADD);
			
		}else if(TdsConstants.ACTION_SEND.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TdsConstants.MODE_SEND);
			
		}
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param session
	 * @param model
	 * @param container
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonTdsExportSpecificTopicContainer container){
		//SET HEADER RECORDS  (from RPG)
		for (JsonTdsExportSpecificTopicRecord record : container.getOneorder()){
			model.put(TdsConstants.DOMAIN_RECORD, record);
			//put the header topic in session for the coming item lines
			session.setAttribute(TdsConstants.DOMAIN_RECORD_TOPIC, record);
		}

	}
	
	/**
	 * 
	 * @param session
	 * @param model
	 * @param container
	 * @param sumTopicRecord
	 * 
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonTdsExportSpecificTopicContainer container, JsonTdsExportSpecificTopicRecord sumTopicRecord,JsonTdsExportSpecificTopicFaktTotalRecord sumFaktTotalRecord ){
		//SET HEADER RECORDS  (from RPG)
		for (JsonTdsExportSpecificTopicRecord record : container.getOneorder()){
			record.setSumOfAntalKolliInItemLines(sumTopicRecord.getSumOfAntalKolliInItemLines());
			record.setSumOfGrossWeightInItemLines(sumTopicRecord.getSumOfGrossWeightInItemLines());
			record.setSumOfInvoiceAmountInItemLines(sumTopicRecord.getSumOfInvoiceAmountInItemLines());
			
			record.setInvoiceListTotSum(sumFaktTotalRecord.getTot_fabl());
			record.setInvoiceListTotValidCurrency(sumFaktTotalRecord.getTot_vakd());
			record.setInvoiceListTotKurs(sumFaktTotalRecord.getTot_vaku());
			
			//(2) now check if the Invoice Total amount is NULL. If it is then we should check if the list of invoices gives something
			if(record.getSveh_fabl()!=null && !"".equals(record.getSveh_fabl())){
				//nothing
			}else{
				if(sumFaktTotalRecord!=null){
					if(sumFaktTotalRecord.getTot_fabl()!=null && !"".equals(sumFaktTotalRecord.getTot_fabl())){
						record.setSveh_fabl(sumFaktTotalRecord.getTot_fabl());
						record.setSveh_vakd(sumFaktTotalRecord.getTot_vakd());
						record.setSveh_vaku(sumFaktTotalRecord.getTot_vaku());
						record.setSveh_vaom(sumFaktTotalRecord.getTot_omr());
						
					}
				}
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
	 * @param record
	 * @param sumTopicRecord
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonTdsExportSpecificTopicRecord record, JsonTdsExportSpecificTopicRecord sumTopicRecord){
		//SET HEADER RECORDS  (from RPG)
		record.setSumOfAntalKolliInItemLines(sumTopicRecord.getSumOfAntalKolliInItemLines());
		record.setSumOfGrossWeightInItemLines((sumTopicRecord.getSumOfGrossWeightInItemLines()));
		record.setSumOfInvoiceAmountInItemLines((sumTopicRecord.getSumOfInvoiceAmountInItemLines()));
		
		model.put(TdsConstants.DOMAIN_RECORD, record);
		//put the header topic in session for the coming item lines
		session.setAttribute(TdsConstants.DOMAIN_RECORD_TOPIC, record);
	}
	/**
	 * 
	 * 
	 * @param model
	 * @param jsonTdsExportSpecificTopicRecord
	 */
	private void setDomainObjectsInView(Map model, JsonTdsExportSpecificTopicRecord jsonTdsExportSpecificTopicRecord){
		//SET HEADER RECORDS  (from RPG)
		model.put(TdsConstants.DOMAIN_RECORD, jsonTdsExportSpecificTopicRecord);
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
		errorMetaInformation.append(rpgReturnResponseHandler.getSveh_syop());
		//model.put(TdsConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
	
	
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	private void populateAvdelningHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser){
		//fill in html lists here
		String TDS_EXPORT_IE = "E"; //Export
		try{
			String BASE_URL = TdsUrlDataStore.TDS_FETCH_AVDELNINGAR_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + TDS_EXPORT_IE);
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
		String TDS_EXPORT_IMPORT_IE = "F"; //Fortullning. The other one is: NCTS = ie=N 
		
		try{
			String BASE_URL = TdsUrlDataStore.TDS_FETCH_SIGNATURE_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + TDS_EXPORT_IMPORT_IE);
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
	
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("tdsExportSpecificTopicService")
	private TdsExportSpecificTopicService tdsExportSpecificTopicService;
	@Autowired
	@Required
	public void setTdsExportSpecificTopicService (TdsExportSpecificTopicService value){ this.tdsExportSpecificTopicService = value; }
	public TdsExportSpecificTopicService getTdsExportSpecificTopicService(){ return this.tdsExportSpecificTopicService; }
	
	
	@Qualifier ("tdsExportSpecificTopicItemService")
	private TdsExportSpecificTopicItemService tdsExportSpecificTopicItemService;
	@Autowired
	@Required
	public void setTdsExportSpecificTopicItemService (TdsExportSpecificTopicItemService value){ this.tdsExportSpecificTopicItemService = value; }
	public TdsExportSpecificTopicItemService getTdsExportSpecificTopicItemService(){ return this.tdsExportSpecificTopicItemService; }
	
	
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
	
	
}

