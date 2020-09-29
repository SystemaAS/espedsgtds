package no.systema.tds.tdsimport.controller;

import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;


//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.StringManager;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsAvdelningContainer;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsAvdelningRecord;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureContainer;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureRecord;
import no.systema.tds.service.html.dropdown.TdsDropDownListPopulationService;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicCheckItemErrorContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicRecord;
import no.systema.tds.tdsimport.url.store.TdsImportUrlDataStore;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicFaktTotalContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicFaktTotalRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicOmbudContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicOmbudRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicCopiedFromTransportUppdragContainer;
import no.systema.tds.tdsimport.validator.TdsImportHeaderValidator;

import no.systema.main.model.SystemaWebUser;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicCopiedContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemRecord;

import no.systema.tds.tdsimport.service.TdsImportSpecificTopicItemService;
import no.systema.tds.tdsimport.service.TdsImportSpecificTopicService;
import no.systema.tds.tdsimport.service.html.dropdown.DropDownListPopulationService;
import no.systema.tds.tdsimport.url.store.TdsImportUrlDataStore;
import no.systema.tds.tdsimport.util.RpgReturnResponseHandler;
import no.systema.tds.tdsimport.util.manager.TdsImportSumDiffCalculatorMgr;
import no.systema.tds.tdsimport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tds.url.store.TdsUrlDataStore;
import no.systema.tds.util.TdsConstants;
import no.systema.tds.util.manager.CodeDropDownMgr;
import no.systema.tds.util.manager.TaricDirectAccessorMgr;


/**
 * TDS Import Topic Controller 
 * 
 * @author oscardelatorre
 * @date Sep 2, 2013
 * 
 */

@Controller
@Scope("session")
public class TdsImportHeaderController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(TdsImportHeaderController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private TaricDirectAccessorMgr taricDirectAccessorMgr = new TaricDirectAccessorMgr();
	private StringManager strMgr = new StringManager();
	
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	
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
	@RequestMapping(value="tdsimport_edit.do",  params="action=doPrepareCreate", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doPrepareCreate(HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		String sign = request.getParameter("sign");
		String avd = request.getParameter("avd");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		ModelAndView successView = new ModelAndView("tdsimport_edit");
		logger.info("Method: doPrepareCreate [RequestMapping-->tdsimport_edit.do]");
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			
    		this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
    		this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
    		//drop down to print skilleark (must be Z type)
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString2(this.urlCgiProxyService, this.tdsDropDownListPopulationService,model, appUser, "Z");
			//add gui lists here
    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","GCY");
    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","MDX");
    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"I","FF1");
    		//domain
    		model.put("sign", sign);
    		model.put("avd", avd);
    		JsonTdsImportSpecificTopicRecord record = this.initCreateNewTopic(appUser.getUser(), avd);
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
	@RequestMapping(value="tdsimport_edit.do")
	public ModelAndView doTdsImportEdit(@ModelAttribute ("record") JsonTdsImportSpecificTopicRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tdsimport_edit");
		String method = "doTdsImportEdit [RequestMapping-->tdsimport_edit.do]";
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
		String svih_0035 = request.getParameter("svih_0035"); //test indicator
		
		logger.info("TEST flagga:<" + svih_0035 +">");
		
		//Action (doFetch, doUpdate, doCreate)
		logger.info("Action:" + action);
		logger.info("Opd:" + opd);
		logger.info("Avd:" + avd);
		logger.info("Sign:" + sign);
		logger.info("FABL:" + recordToValidate.getSvih_fabl());
		
		Map model = new HashMap();
		model.put("sign", sign);
		
		
		if(appUser==null){
			return this.loginView;
		}else{
			if(action!=null){
				//Get sums
				JsonTdsImportSpecificTopicRecord sumTopicRecord = this.tdsImportSumDiffCalculatorMgr.getSumOfSpecificFieldsInItemLines(avd, opd, appUser);
				JsonTdsImportSpecificTopicFaktTotalRecord sumFaktTotalRecord = this.tdsImportSumDiffCalculatorMgr.getInvoiceTotalFromInvoices(avd, opd, appUser);
				
				//we do save the sum of antalKolliInItemLines.
				//this is the only place of initialization of this variable
				//session.setAttribute("sumOfAntalKolliInItemLines", sumOfAntalKolliInItemLines);
				
				//add gui lists here (common for FETCH, UPDATE, etc)
	    		this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
				this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
				//drop down to print skilleark (must be Z type)
				this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString2(this.urlCgiProxyService, this.tdsDropDownListPopulationService,model, appUser, "Z");
				
				//-------------
				//FETCH RECORD
				//-------------
				if(TdsConstants.ACTION_FETCH.equals(action)){
					logger.info("FETCH record transaction...");
					//---------------------------
					//get BASE URL = RPG-PROGRAM
		            //---------------------------
					String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
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
			    		JsonTdsImportSpecificTopicContainer jsonTdsImportSpecificTopicContainer = this.tdsImportSpecificTopicService.getTdsImportSpecificTopicContainer(jsonPayload);
			    		//add gui lists here
			    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","GCY");
		    			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","MDX");
		    			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"I","FF1");
		    			this.taricDirectAccessorMgr.askTullid(model);
		    			
			    		this.setDomainObjectsInView(session, model, jsonTdsImportSpecificTopicContainer, sumTopicRecord, sumFaktTotalRecord);
			    		
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
					//this validation alternative is only used in a pure update.
					if(opd!=null && !"".equals(opd)){
						logger.info("PURE UPDATE validation...");
						//We do fetch this number here in order to update the recordToValidate (for validation purposes) 
						//Get sums
						recordToValidate.setSumOfAntalKolliInItemLines(sumTopicRecord.getSumOfAntalKolliInItemLines());
						recordToValidate.setSumOfGrossWeightInItemLines(sumTopicRecord.getSumOfGrossWeightInItemLines());
						recordToValidate.setSumOfInvoiceAmountInItemLines(sumTopicRecord.getSumOfInvoiceAmountInItemLines());
						
						
					}else{
						recordToValidate.setSvih_syav(avd);
						recordToValidate.setSvih_sysg(sign);
					}
					TdsImportHeaderValidator validator = new TdsImportHeaderValidator();
					validator.validate(recordToValidate, bindingResult);
				    recordToValidate.setSvih_0035(svih_0035);
					//----------------------------
				    //check for validation ERRORS
					//----------------------------
					if(bindingResult.hasErrors()){
				    	logger.info("[ERROR Validation] Record does not validate)");
				    	//put domain objects and do go back to the original view...
				    	recordToValidate.setSvih_syav(avd);
				    	recordToValidate.setSvih_sysg(sign);
				    	this.setDomainObjectsInView(session, model, recordToValidate, sumTopicRecord);
				    	isValidCreatedRecordTransactionOnRPG = false;
				    	if(opd==null || "".equals(opd)){
				    		action = TdsConstants.ACTION_CREATE;
				    	}
				    }else{
			    		JsonTdsImportSpecificTopicRecord jsonTdsImportSpecificTopicRecord = null;
						String tuid = null;
						
						if(opd!=null && !"".equals(opd)){
							logger.info("PURE UPDATE transaction..."); 
							//PURE UDPATE transaction
							//this means that the update is an update of an existing record
							jsonTdsImportSpecificTopicRecord = new JsonTdsImportSpecificTopicRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonTdsImportSpecificTopicRecord);
				            //binder.registerCustomEditor(...); // if needed
							logger.info("SVIH_AVNA STEP 1: " + request.getParameter("svih_avna"));
				            binder.bind(request);
				            jsonTdsImportSpecificTopicRecord.setSvih_0035(svih_0035);
				            
				            
						}else{
							logger.info("CREATE NEW follow by UDATE transaction...");
							//CREATE AND UPDATE transaction
							//This means that the update will be done AFTER a creation of an empty record. All this in the same transaction. 2 STEPS involved: (1)create and (2)update
							//---------------------------------------------------------------------------------------------
							//STEP[1] Generate new Topic key seeds (avd,opd,sign,tullid) by creating an empty new record. 
							//---------------------------------------------------------------------------------------------
							jsonTdsImportSpecificTopicRecord = this.createNewTopicHeaderKeySeeds(session, request, appUser, avd, sign);
							if(jsonTdsImportSpecificTopicRecord!=null){
								opd = jsonTdsImportSpecificTopicRecord.getSvih_syop();
								tuid = jsonTdsImportSpecificTopicRecord.getSvih_tuid();
								//
								jsonTdsImportSpecificTopicRecord.setSvih_syav(avd);
								jsonTdsImportSpecificTopicRecord.setSvih_sysg(sign);
								
								//take the rest from GUI.
								jsonTdsImportSpecificTopicRecord = new JsonTdsImportSpecificTopicRecord();
								ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonTdsImportSpecificTopicRecord);
					            //binder.registerCustomEditor(...); // if needed
					            binder.bind(request);
					            
					            //Now set back with the generated values since the bind method above erases them...
					            jsonTdsImportSpecificTopicRecord.setSvih_syav(avd);
					            jsonTdsImportSpecificTopicRecord.setSvih_syop(opd);
					            jsonTdsImportSpecificTopicRecord.setSvih_sysg(sign);
					            jsonTdsImportSpecificTopicRecord.setSvih_tuid(tuid);
					            //more completions
					            jsonTdsImportSpecificTopicRecord.setSumOfAntalKolliInItemLines(sumTopicRecord.getSumOfAntalKolliInItemLines());
					            jsonTdsImportSpecificTopicRecord.setSumOfGrossWeightInItemLines(sumTopicRecord.getSumOfGrossWeightInItemLines());
					            jsonTdsImportSpecificTopicRecord.setSumOfInvoiceAmountInItemLines(sumTopicRecord.getSumOfInvoiceAmountInItemLines());
					            
					            
								//test indicator
					            jsonTdsImportSpecificTopicRecord.setSvih_0035(svih_0035);
								
							}else{
								//Some kind of error occurred. Set the transaction as invalid...
								isValidCreatedRecordTransactionOnRPG = false;
							}
						}
						//--------------------------------------------------
						//At this point we are ready to do an update
						//--------------------------------------------------
						if(isValidCreatedRecordTransactionOnRPG){
							
							//Last adjustment of some fields
							this.adjustFields( jsonTdsImportSpecificTopicRecord);
							
				            //---------------------------
							//get BASE URL = RPG-PROGRAM
				            //---------------------------
							String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
							
							//-----------------------------------
							//add URL-parameter "mode=U" (Update)
							//-----------------------------------
							String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
							//Set fall-back fields if applicable. Usually deklarant-info. When deklarant = null then it will be = mottagare (receiver)
							//We check this right here though the jsonRecord (if applicable)
							this.setDeklarantFieldsIfApplicable(jsonTdsImportSpecificTopicRecord);
							
							//build the url parameters (from GUI-form) to send on a GET/POST method AFTER the keyParameters
							String urlRequestParamsTopic = this.urlRequestParameterMapper.getUrlParameterValidString((jsonTdsImportSpecificTopicRecord));
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
					    		this.setFatalError(model, rpgReturnResponseHandler, jsonTdsImportSpecificTopicRecord);
					    		isValidCreatedRecordTransactionOnRPG = false;
					    	}else{
					    		
					    		if(this.validateItemLines(appUser, avd, opd)){
					    			//Update succefully done!
					    			logger.info("[INFO] Record successfully updated, OK ");
					    			//put domain objects
					    			this.setDomainObjectsInView(session, model, jsonTdsImportSpecificTopicRecord, sumTopicRecord );
					    			this.adjustValidUpdateFlag(model, jsonTdsImportSpecificTopicRecord);
					    		}else{
					    			rpgReturnResponseHandler.setErrorMessage("Varning: Ärendet har sparats. Det finns varuposter med felstatus som måste korrigeras.  ");
						    		this.setFatalError(model, rpgReturnResponseHandler, jsonTdsImportSpecificTopicRecord);
						    		isValidCreatedRecordTransactionOnRPG = false;
					    		}
					    	}
						}else{
							rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tuid, syop generation : " + rpgReturnResponseHandler.getErrorMessage());
							this.setFatalError(model, rpgReturnResponseHandler, jsonTdsImportSpecificTopicRecord);
							isValidCreatedRecordTransactionOnRPG = false;
						}
				    }
					
					//add gui lists here
	    			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService,model,appUser,"A","GCY");
	    			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService,model,appUser,"A","MDX");
	    			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"I","FF1");
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
	 * 
	 * @param applicationUser
	 * @param avd
	 * @return
	 */
	public JsonTdsImportSpecificTopicRecord initCreateNewTopic (String applicationUser, String avd) {
		 logger.info("Inside: initCreateNewTopic.do");
		 JsonTdsImportSpecificTopicRecord result = new JsonTdsImportSpecificTopicRecord();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_FETCH_OMBUD_DEFAULT_DATA_URL;
		 String urlRequestParamsKeys = "user=" + applicationUser + "&avd=" + avd;
		 logger.info("URL: " + BASE_URL);
		 logger.info("PARAMS: " + urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 logger.info(jsonPayload);
		 if(jsonPayload!=null){
				 JsonTdsImportSpecificTopicOmbudContainer container = this.tdsImportSpecificTopicService.getTdsImportSpecificTopicOmbudContainer(jsonPayload);
				 if(container!=null){
					 for(JsonTdsImportSpecificTopicOmbudRecord  record : container.getGetdepinf()){
						 result.setSvih_omeo(record.getSvia_omeo());
						 result.setSvih_omty(record.getSvia_omty());
						 result.setSvih_omha(record.getSvia_omha());
						 result.setSvih_dek1(record.getSvih_dek1());
						 result.setSvih_dek2(record.getSvih_dek2());
						 result.setSvih_tart(record.getSvih_tart());
						 result.setSvih_mtyp(record.getSvih_mtyp());
					 }
				 }
			 }
		 
		 return result;
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
		String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_FETCH_TOPIC_ITEMLIST_VALIDATION_URL;
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
    		JsonTdsImportSpecificTopicCheckItemErrorContainer container = this.tdsImportSpecificTopicService.getCheckItemErrorContainer(jsonPayload);
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
	private void adjustValidUpdateFlag(Map model, JsonTdsImportSpecificTopicRecord record){
		record.setValidUpdate(true);
		model.put(TdsConstants.DOMAIN_RECORD, record);
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsimport_send.do")
	public ModelAndView doTdsImportSend(@ModelAttribute ("record") JsonTdsImportSpecificTopicRecord recordToValidate, BindingResult bindingResult,  HttpSession session, HttpServletRequest request){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_IMPORT);
		ModelAndView successView = new ModelAndView("redirect:tdsimport.do?action=doFind&sign=" + appUser.getTdsSign());
		
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
			String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
			
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
	    		//TODO ERROR HANDLING HERE... stay in the same page ?
	    	}else{
	    		//Update succefully done!
	    		logger.info("[INFO] Record successfully sent [changed status], OK ");
	    		//put domain objects
	    		//this.setDomainObjectsInView(session, model, jsonTdsImportSpecificTopicRecord);
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
	@RequestMapping(value="tdsimport_edit_printTopic.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doTdsImportEditPrintTopic(HttpSession session, HttpServletRequest request){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView successView = new ModelAndView("redirect:tdsimport.do?action=doFind&sign=" + appUser.getTdsSign());
		Map model = new HashMap();
		
		String method = "doTdsImportEditPrintTopic [RequestMapping-->tdsimport_edit_printTopic.do]";
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
			String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_PRINT_FOR_SPECIFIC_TOPIC_URL;
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
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsimport_edit_printSkilleArkTopic.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doTdsImportEditPrintSkilleArkTopic(HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView successView = new ModelAndView("redirect:tdsimport.do?action=doFind&sign=" + appUser.getTdsSign());
		
		String method = "doTdsImportEditPrintSkilleArkTopic [RequestMapping-->tdsimport_edit_printSkilleArkTopic.do]";
		logger.info("Method: " + method);
		String opd = request.getParameter("currentOpd");
		String avd = request.getParameter("currentAvd");
		String archiveType = request.getParameter("selectedType");
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			//-------------------------------------
			//get BASE URL = RPG-PROGRAM for PRINT
            //-------------------------------------
			String BASE_URL = TdsUrlDataStore.TDS_BASE_PRINT_FORSATTSBLAD_FOR_SPECIFIC_TOPIC_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForPrintSkilleArk( avd, opd, appUser, archiveType);
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the Print (RPG program) here
	    	//--------------------------------------
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
	    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    //END of PRINT here and now
	    	logger.info("Method PRINT END - " + method);	
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
	@RequestMapping(value="tdsimport_copyTopic.do", method={RequestMethod.POST} )
	public ModelAndView doCopyTopic( HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tdsimport_edit");
		ModelAndView fallbackOnErrorView = new ModelAndView("tdsimport");
		
		JsonTdsImportTopicCopiedContainer jsonTdsImportTopicCopiedContainer = null;
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		String method = "doCopyTopic [RequestMapping-->tdsimport_copyTopic.do]";
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
			String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
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
	    		jsonTdsImportTopicCopiedContainer = this.tdsImportSpecificTopicService.getTdsImportTopicCopiedContainer(jsonPayload);
	    		if(jsonTdsImportTopicCopiedContainer!=null){
	    			//Check for errors
	    			if(jsonTdsImportTopicCopiedContainer.getErrMsg()!=null && !"".equals(jsonTdsImportTopicCopiedContainer.getErrMsg())){
	    				logger.fatal("[ERROR FATAL] errMsg containing: " + jsonTdsImportTopicCopiedContainer.getErrMsg());
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
			BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
			//url params
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, jsonTdsImportTopicCopiedContainer.getNewavd(), jsonTdsImportTopicCopiedContainer.getNewopd(), jsonTdsImportTopicCopiedContainer.getNewsign(), appUser);
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
	    		JsonTdsImportSpecificTopicContainer jsonTdsImportSpecificTopicContainer = this.tdsImportSpecificTopicService.getTdsImportSpecificTopicContainer(jsonPayload);
	    		
	    		//add gui lists here (common for FETCH, UPDATE, etc)
	    		this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
				this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
				//drop down to print skilleark (must be Z type)
				this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString2(this.urlCgiProxyService, this.tdsDropDownListPopulationService,model, appUser, "Z");
				
	    		//add gui lists here
	    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService,model,appUser,"A","GCY");
	    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService,model,appUser,"A","MDX");
	    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService,model,appUser,"I","FF1");
	    		
	    		this.taricDirectAccessorMgr.askTullid(model);
	    		
	    		this.setDomainObjectsInView(session, model, jsonTdsImportSpecificTopicContainer);
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
	 * Copies one topic(ärende) to a new one, from (1) a Norsk Export or (2) Transport Uppdrag (order)
	 * STEP 1: Copy
	 * STEP 2: Fetch the record as if it was a selection of a topic in a list (Update mode)
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsimport_doFetchTopicFromTransportUppdrag.do", method={RequestMethod.POST} )
	public ModelAndView doFetchTopicFromTransportUppdrag( HttpSession session, HttpServletRequest request){
		JsonTdsImportTopicCopiedFromTransportUppdragContainer jsonContainer = null;
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);

		ModelAndView successView = new ModelAndView("tdsimport_edit");
		//fallback view (usually on errors)
		ModelAndView fallbackView = new ModelAndView("tdsimport_edit");
		fallbackView.addObject("action", "doPrepareCreate");
		//ModelAndView fallbackView = new ModelAndView("redirect:tdsimport_edit.do?action=doPrepareCreate&user="+appUser.getUser());
		
		String method = "[RequestMapping-->tdsimport_doFetchTopicFromTransportUppdrag.do]";
		logger.info("Method: " + method);
		Map model = new HashMap();
		
		//We must get all parameters from the enumeration since all have sequence counter number
		String action=request.getParameter("actionGS");;
		String avd=request.getParameter("selectedAvd");
		String opd=request.getParameter("selectedOpd");
		String extRefNr=request.getParameter("selectedExtRefNr"); //Domino ref in Dachser Norway AS
		String sign = request.getParameter("sign");
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			if( (extRefNr!=null && !"".equals(extRefNr)) || ( (opd!=null && !"".equals(opd)) && (avd!=null && !"".equals(avd))) ){
				//--------------------
				//STEP 1: COPY record
				//--------------------
				logger.info("starting PROCESS record transaction...");
				String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
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
		    		jsonPayload = jsonPayload.replaceAll("SVIH", "svih");//AS must change so this step is removed!
		    		jsonContainer = this.tdsImportSpecificTopicService.getTdsImportTopicCopiedFromTransportUppdragContainer(jsonPayload);
		    		if(jsonContainer!=null){
		    			//Check for errors
		    			if(jsonContainer.getErrMsg()!=null && !"".equals(jsonContainer.getErrMsg())){
		    				logger.info("[WARN] errMsg containing: " + jsonContainer.getErrMsg());
		    				logger.info("[WARN] redirecting to doPrepareCreate");
		    				//Send the error message to the redirect view.
		    				//request.setAttribute("errorMessageOnCopyFromTransportOppdrag", jsonContainer.getErrMsg());
		    				model.put(TdsConstants.ASPECT_ERROR_MESSAGE, jsonContainer.getErrMsg());
		    				model.put(TdsConstants.ASPECT_ERROR_META_INFO, "Vid kopiering av TransportUppdrag...");
		    				fallbackView.addObject(TdsConstants.DOMAIN_MODEL, model);
		    				//
		    				
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
			BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
			//url params
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, jsonContainer.getSvih_syav(), jsonContainer.getSvih_syop(), jsonContainer.getSign(), appUser);
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
	    		JsonTdsImportSpecificTopicContainer jsonTdsImportSpecificTopicContainer = this.tdsImportSpecificTopicService.getTdsImportSpecificTopicContainer(jsonPayload);
	    		
	    		//add gui lists here (common for FETCH, UPDATE, etc)
	    		this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
				this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
				//drop down to print skilleark (must be Z type)
				this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString2(this.urlCgiProxyService, this.tdsDropDownListPopulationService,model, appUser, "Z");
				//land and currency codes
	    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService,model,appUser,"A","GCY");
    			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService,model,appUser,"A","MDX");
    			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"I","FF1");
    			this.taricDirectAccessorMgr.askTullid(model);
    			
	    		this.setDomainObjectsInView(session, model, jsonTdsImportSpecificTopicContainer);
	    		successView.addObject(TdsConstants.DOMAIN_MODEL, model);
			//put the doUpdate action since we are preparing the record for an update (when saving)
			successView.addObject(TdsConstants.EDIT_ACTION_ON_TOPIC, TdsConstants.ACTION_UPDATE);
	    		
	    	}else{
	    		logger.fatal("[ERROR fatal] NO CONTENT on jsonPayload from URL... ??? <Null>");
	    		return loginView;
			}
		}else{
			logger.warn("[INFO] Opdnr is NULL. Redirecting to: tdsimport_edit.do?action=doPrepareCreate... ");
			return new ModelAndView("redirect:tdsimport_edit.do?action=doPrepareCreate&user=" + appUser.getUser() + "&sign=" + sign + "&avd=" + avd);
		}
		
		return successView;
		}
		
	}
	
	/**
	 * The method requests a clearance on a given topic (Begäran om klarering)
	 * 
	 * The procedure is done in 2 STEPs
	 * (1) Change the message type (meddelande typ) as ref. in tullverkets documentation (from: DNU/DRT to DBK or from: HNU/HRT, all with status="O"--> to HBK)
	 * (2) Send to signering. (Emulates exactly the same behavior as in method: doTdsImportSend, in this same class 
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsimport_requestClearanceOnTopic.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doTdsImportRequestClearanceOnTopic(HttpSession session, HttpServletRequest request){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView returnView = null;
		ModelAndView successView = new ModelAndView("redirect:tds_sign_pki.do");
		ModelAndView errorView = new ModelAndView("redirect:tdsimport.do?action=doFind&sign=" + appUser.getTdsSign());

		Map model = new HashMap();
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		String method = "doTdsImportRequestClearanceOnTopic [RequestMapping-->tdsimport_requestClearanceOnTopic.do]";
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
			String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_UPDATE_MESSAGETYPE_URL;
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
	    		BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
			
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
	 * This method manages several types of events such as:(A) Rättelse, (B) Omprövning, (C) Kompletterande tulldeklaration
	 * 
	 * 
	 * (A)Rättelse av ej klarerat ärende (DNU, HNU, TNU, TQN --> alla med status="O")
	 * The procedure is done in 1 STEP
	 * (1) Change the message type (meddelande typ) as ref. in tullverkets documentation:
	 * 	   from: (1)DNU to DRT, (2)HNU to HRT, (3)TNU to TRT and (4)TQN to TQR
	 * 
	 * (B)Omprövning av klarerat ärende (DNU, DNK, TNU, TKN --> alla med status="G" [klarerat] )
	 * The procedure is done in 1 STEP
	 * (1) Change the message type (meddelande typ) as ref. in tullverkets documentation:
	 * 	   from: (1)DNU to DRT, (2)HNU to HRT, (3)TNU to TRT and (4)TQN to TQR
	 * 
	 * (C)Kompletterande tulldeklaration (alla som starta på "H" alla med status="G" [klarerat] )
	 * 
	 * 
	 * 
	 * After the update is done the end-user is redirected to the usual update (now with the new message type and with the status = "" (NULL)
	 * 
	 *  
	 * The method
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsimport_completionEventsOnTopic.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doTdsImportCompletionEventsOnTopic(HttpSession session, HttpServletRequest request){

		ModelAndView returnView = null;
		ModelAndView successView = new ModelAndView("tdsimport_edit");
		ModelAndView errorView = new ModelAndView("tdsimport_edit");
		errorView.addObject("action", "doPrepareCreate");

		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();

		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		String method = "[RequestMapping-->tdsimport_completionEventsOnTopic.do]";
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
			String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_UPDATE_MESSAGETYPE_URL;
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
				JsonTdsImportSpecificTopicRecord sumTopicRecord = this.tdsImportSumDiffCalculatorMgr.getSumOfSpecificFieldsInItemLines(avd, opd, appUser);
				JsonTdsImportSpecificTopicFaktTotalRecord sumFaktTotalRecord = this.tdsImportSumDiffCalculatorMgr.getInvoiceTotalFromInvoices(avd, opd, appUser);
				
				
				//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
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
		    		JsonTdsImportSpecificTopicContainer jsonTdsImportSpecificTopicContainer = this.tdsImportSpecificTopicService.getTdsImportSpecificTopicContainer(jsonPayload);
		    		
		    		//add gui lists here (common for FETCH, UPDATE, etc)
		    		this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
					this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
					//drop down to print skilleark (must be Z type)
					this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString2(this.urlCgiProxyService, this.tdsDropDownListPopulationService,model, appUser, "Z");
					
		    		//add gui lists here
		    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","GCY");
	    			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","MDX");
	    			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"I","FF1");
	    			this.taricDirectAccessorMgr.askTullid(model);
	    			
		    		this.setDomainObjectsInView(session, model, jsonTdsImportSpecificTopicContainer, sumTopicRecord, sumFaktTotalRecord);
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
	@RequestMapping(value="tdsimport_updateStatus.do")
	public ModelAndView doUpdateStatus(HttpSession session, HttpServletRequest request){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_IMPORT);
		ModelAndView successView = new ModelAndView("redirect:tdsimport.do?action=doFind&sign=" + appUser.getTdsSign());
		
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
			String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_UPDATE_STATUS_URL;
			
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
	 * Completes deklarant values (when applicable)
	 * @param jsonTdsImportSpecificTopicRecord
	 * 
	 */
	private void setDeklarantFieldsIfApplicable(JsonTdsImportSpecificTopicRecord record){
		if(record.getSvih_dkkn()==null || "".equals(record.getSvih_dkkn())){
			record.setSvih_dkkn(record.getSvih_mokn());
		}
		if(record.getSvih_dkna()==null || "".equals(record.getSvih_dkna())){
			record.setSvih_dkna(record.getSvih_mona());
		}
		if(record.getSvih_dkeo()==null || "".equals(record.getSvih_dkeo())){
			record.setSvih_dkeo(record.getSvih_moeo());			
		}
		if(record.getSvih_dka1()==null || "".equals(record.getSvih_dka1())){
			record.setSvih_dka1(record.getSvih_moa1());			
		}
		if(record.getSvih_dka2()==null || "".equals(record.getSvih_dka2())){
			record.setSvih_dka2(record.getSvih_moa2());
		}
		if(record.getSvih_dkpa()==null || "".equals(record.getSvih_dkpa())){
			record.setSvih_dkpa(record.getSvih_mopa());
		}
		if(record.getSvih_dkpn()==null || "".equals(record.getSvih_dkpn())){
			record.setSvih_dkpn(record.getSvih_mopn());
		}
		if(record.getSvih_dklk()==null || "".equals(record.getSvih_dklk())){
			record.setSvih_dklk(record.getSvih_molk());
		}
		if(record.getSvih_dkha()==null || "".equals(record.getSvih_dkha())){
			record.setSvih_dkha(record.getSvih_moha());
		}
		if(record.getSvih_dktl()==null || "".equals(record.getSvih_dktl())){
			record.setSvih_dktl(record.getSvih_motl());
		}
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
	private JsonTdsImportSpecificTopicRecord createNewTopicHeaderKeySeeds(HttpSession session, HttpServletRequest request, SystemaWebUser user,
																		 String avd, String sign){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonTdsImportSpecificTopicRecord record = new JsonTdsImportSpecificTopicRecord();
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
		
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
		logger.info("### rpgSeedNumberPayload: " + rpgSeedNumberPayload);
		
		rpgReturnResponseHandler.getNewSeedsOpdAndTuidRequiredForCreateNewTopic(rpgSeedNumberPayload);
		logger.info("### syop from RPG PROGRAM: " + rpgReturnResponseHandler.getSvih_syop());
		logger.info("### tuid from RPG PROGRAM: " + rpgReturnResponseHandler.getSvih_tuid());
		
		//we must complete the GUI-json sypo and tuid with the value from a seedTuid here
		if(rpgReturnResponseHandler.getSvih_syop()!=null && rpgReturnResponseHandler.getSvih_tuid()!=null){
			record.setSvih_syop(rpgReturnResponseHandler.getSvih_syop().trim());
			record.setSvih_tuid(rpgReturnResponseHandler.getSvih_tuid().trim());
			
		}else{
			logger.info("[ERROR] No mandatory seeds (tuid, opd) were generated correctly)! look at std output log. [errMsg]" + rpgReturnResponseHandler.getErrorMessage());
			record = null;
		}
        
		return record;
	}
	
	/**
	 * log Errors in Aspects and Domain objects in order to render on GUI
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param jsonTdsImportSpecificTopicRecord
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonTdsImportSpecificTopicRecord record){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, record);
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
	 * @param sign
	 * 
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForCopyTopicFromTransportUppdrag(String avd, String opd, String extRefNr, SystemaWebUser appUser){
		//user=OSCAR&avd=1&opd=53452&sign=CB&mode=GS 
		final String MODE = "GS";
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		if(opd!=null && !"".equals(opd)){
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		}else if (extRefNr!=null && !"".equals(extRefNr)){
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_xref=" + extRefNr);
		}
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + appUser.getTdsSign());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + MODE);
		
		return urlRequestParamsKeys.toString();	
	}

	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForCopy(String avd, String newAvd, String newSign, String opd, SystemaWebUser appUser){
		//user=OSCAR&avd=1&newavd=2&opd=218&mode=C&newsign=OT 
		final String MODE_COPY = "C";
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "newavd=" + newAvd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + MODE_COPY);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "newsign=" + newSign);
		
		
		return urlRequestParamsKeys.toString();	
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
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_mtyp=" + mtyp);
		
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
	private void setDomainObjectsInView(HttpSession session, Map model, JsonTdsImportSpecificTopicContainer container){
		//SET HEADER RECORDS  (from RPG)
		for (JsonTdsImportSpecificTopicRecord record : container.getOneorder()){
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
	 * @param sumOfAntalKolliInItemLines
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonTdsImportSpecificTopicContainer container, JsonTdsImportSpecificTopicRecord sumTopicRecord, JsonTdsImportSpecificTopicFaktTotalRecord sumFaktTotalRecord){
		//SET HEADER RECORDS  (from RPG)
		for (JsonTdsImportSpecificTopicRecord record : container.getOneorder()){
			//(1) handover to final object
			record.setSumOfAntalKolliInItemLines(sumTopicRecord.getSumOfAntalKolliInItemLines());
			record.setSumOfGrossWeightInItemLines(sumTopicRecord.getSumOfGrossWeightInItemLines());
			record.setSumOfInvoiceAmountInItemLines(sumTopicRecord.getSumOfInvoiceAmountInItemLines());
			record.setInvoiceListTotValidCurrency(sumFaktTotalRecord.getTot_vakd());
			record.setInvoiceListTotSum(sumFaktTotalRecord.getTot_fabl());
			record.setInvoiceListTotKurs(sumFaktTotalRecord.getTot_vaku());
			logger.info("invoiceListTotSum:" + record.getInvoiceListTotSum());
            //(2) now check if the Invoice Total amount is NULL. If it is then we should check if the list of invoices gives something
			if(record.getSvih_fabl()!=null && !"".equals(record.getSvih_fabl())){
				//nothing
			}else{
				if(sumFaktTotalRecord!=null){
					if(sumFaktTotalRecord.getTot_fabl()!=null && !"".equals(sumFaktTotalRecord.getTot_fabl())){
						record.setSvih_fabl(sumFaktTotalRecord.getTot_fabl());
						record.setSvih_vakd(sumFaktTotalRecord.getTot_vakd());
						record.setSvih_vaku(sumFaktTotalRecord.getTot_vaku());
						record.setSvih_vaom(sumFaktTotalRecord.getTot_omr());
					}
				}
			}
			
			//logger.info("Bruttovikt Str:" + record.getSumOfGrossWeightInItemLinesStr());
			//logger.info("Bruttovikt dbl:" + record.getSumOfGrossWeightInItemLines());
			//logger.info("svih_brut_dbl:" + record.getSvih_brut_dbl());
			
			//logger.info("###SVIH_AVNA:" + record.getSvih_avna());
			//logger.info("###SVIH_:" + record.getSvih_avna());
			model.put(TdsConstants.DOMAIN_RECORD, record);
			//put the header topic in session for the coming item lines
			session.setAttribute(TdsConstants.DOMAIN_RECORD_TOPIC, record);
		}

	}
	/**
	 * Populates the html object (model map for the JSTL)
	 * 
	 * @param model
	 * @param record
	 * @return
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonTdsImportSpecificTopicRecord record, JsonTdsImportSpecificTopicRecord sumTopicRecord){
		//SET HEADER RECORDS  (from RPG)
		record.setSumOfAntalKolliInItemLines(sumTopicRecord.getSumOfAntalKolliInItemLines());
		record.setSumOfGrossWeightInItemLines(sumTopicRecord.getSumOfGrossWeightInItemLines());
		record.setSumOfInvoiceAmountInItemLines(sumTopicRecord.getSumOfInvoiceAmountInItemLines());
		
		//fill in dbl value (if applicable)
        if( strMgr.isNotNull(record.getSvih_brut()) ){
        	record.setSvih_brut_dbl(Double.parseDouble(record.getSvih_brut().replace(",", ".")));
    		//logger.info("SVIH_BRUT_DBL:" + record.getSvih_brut_dbl());
    	}
        //logger.info("Bruttovikt Str:" + record.getSumOfGrossWeightInItemLinesStr());
		//logger.info("Bruttovikt dbl:" + record.getSumOfGrossWeightInItemLines());
		//logger.info("svih_brut_dbl:" + record.getSvih_brut_dbl());
		
		model.put(TdsConstants.DOMAIN_RECORD, record);
		//put the header topic in session for the coming item lines
		session.setAttribute(TdsConstants.DOMAIN_RECORD_TOPIC, record);
	}
	/**
	 * 
	 * 
	 * @param model
	 * @param jsonTdsImportSpecificTopicRecord
	 */
	private void setDomainObjectsInView(Map model, JsonTdsImportSpecificTopicRecord record){
		//SET HEADER RECORDS  (from RPG)
		model.put(TdsConstants.DOMAIN_RECORD, record);
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
		errorMetaInformation.append(rpgReturnResponseHandler.getSvih_syop());
		//model.put(TdsConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
			
	
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	private void populateAvdelningHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser){
		//fill in html lists here
		String TDS_IMPORT_IE = "I"; //Import
		try{
			String BASE_URL = TdsUrlDataStore.TDS_FETCH_AVDELNINGAR_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + TDS_IMPORT_IE);
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
		String TDS_IE = "F"; //Fortullning. The other one is: NCTS = ie=N 
		
		try{
			String BASE_URL = TdsUrlDataStore.TDS_FETCH_SIGNATURE_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + TDS_IE);
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("SIGN BASE_URL:" + BASE_URL);
			logger.info("SIGN BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonTdsSignatureContainer container = this.tdsDropDownListPopulationService.getSignatureContainer(url);
			List<JsonTdsSignatureRecord> list = new ArrayList();
			for(JsonTdsSignatureRecord record: container.getSignaturer()){
				//logger.info("SIGN:" + record.getSign());
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
	 * @param type
	 * @return
	 */
	private String getRequestUrlKeyParametersForPrintSkilleArk(String avd, String opd, SystemaWebUser appUser, String type){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "type=" + type);
		
		return urlRequestParamsKeys.toString();	
	}
	
	
	/**
	 * 
	 * @param recordToValidate
	 */
	private void adjustFields(JsonTdsImportSpecificTopicRecord recordToValidate){
		//Godsmärkning
		if(strMgr.isNotNull(recordToValidate.getSvih_golk())){
			recordToValidate.setSvih_golk(recordToValidate.getSvih_golk().toUpperCase());
		}
		
	}
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("tdsImportSpecificTopicService")
	private TdsImportSpecificTopicService tdsImportSpecificTopicService;
	@Autowired
	@Required
	public void setTdsImportSpecificTopicService (TdsImportSpecificTopicService value){ this.tdsImportSpecificTopicService = value; }
	public TdsImportSpecificTopicService getTdsImportSpecificTopicService(){ return this.tdsImportSpecificTopicService; }
	
	@Qualifier ("tdsImportSpecificTopicItemService")
	private TdsImportSpecificTopicItemService tdsImportSpecificTopicItemService;
	@Autowired
	@Required
	public void setTdsImportSpecificTopicItemService (TdsImportSpecificTopicItemService value){ this.tdsImportSpecificTopicItemService = value; }
	public TdsImportSpecificTopicItemService getTdsImportSpecificTopicItemService(){ return this.tdsImportSpecificTopicItemService; }
	
	@Qualifier ("tdsImportDropDownListPopulationService")
	private DropDownListPopulationService tdsImportDropDownListPopulationService;
	@Autowired
	public void setTdsImportDropDownPopulationService (DropDownListPopulationService value){ this.tdsImportDropDownListPopulationService=value; }
	public DropDownListPopulationService getTdsImportDropDownListPopulationService(){return this.tdsImportDropDownListPopulationService;}
	
	@Qualifier ("tdsDropDownListPopulationService")
	private TdsDropDownListPopulationService tdsDropDownListPopulationService;
	@Autowired
	public void setTdsDropDownPopulationService (TdsDropDownListPopulationService value){ this.tdsDropDownListPopulationService=value; }
	public TdsDropDownListPopulationService getTdsDropDownListPopulationService(){return this.tdsDropDownListPopulationService;}
	
	@Autowired
	TdsImportSumDiffCalculatorMgr tdsImportSumDiffCalculatorMgr;
}

