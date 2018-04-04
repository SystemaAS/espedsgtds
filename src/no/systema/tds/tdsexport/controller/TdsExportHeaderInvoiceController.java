package no.systema.tds.tdsexport.controller;

import java.util.*;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
//import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

//import no.systema.tds.service.MainHdTopicService;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.context.TdsServletContext;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;

import no.systema.tds.service.html.dropdown.TdsDropDownListPopulationService;
import no.systema.tds.util.TdsConstants;
import no.systema.tds.service.TdsTaricVarukodService;
import no.systema.tds.util.manager.CodeDropDownMgr;
import no.systema.tds.tdsexport.service.TdsExportSpecificTopicService;
import no.systema.tds.tdsexport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tds.tdsexport.url.store.TdsExportUrlDataStore;
import no.systema.tds.tdsexport.util.RpgReturnResponseHandler;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicInvoiceContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicInvoiceRecord;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicRecord;
import no.systema.tds.tdsexport.util.TdsExportCalculator;
import no.systema.tds.tdsexport.service.html.dropdown.DropDownListPopulationService;
import no.systema.tds.tdsexport.validator.TdsExportInvoiceValidator;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicFaktTotalContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicFaktTotalRecord;
import no.systema.tds.tdsimport.url.store.TdsImportUrlDataStore;



/**
 * TDS Export create invoice gateway
 * 
 * @author oscardelatorre
 * @date Oct 23, 2015
 * 
 */

@Controller
//@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TdsExportHeaderInvoiceController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(TdsExportHeaderInvoiceController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private TdsExportCalculator tdsExportCalculator = new TdsExportCalculator();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private final String NOT_FOUND = "NOT FOUND";
	private final String MATCH = "MATCH";
	
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {

    }
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsexport_edit_invoice.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView tdsExportListEditInvoice(@ModelAttribute ("record") JsonTdsExportTopicInvoiceRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		boolean bindingErrorsExist = false;
		logger.info("Inside: tdsExportListEditInvoice");
		
		ModelAndView successView = new ModelAndView("tdsexport_edit_invoice");
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonTdsExportTopicInvoiceRecord jsonTdsExportTopicInvoiceRecord = recordToValidate;
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		Map model = new HashMap();
		String urlRequestParamsKeys = null;
		//Catch required action (doFetch or doUpdate)
		String action = request.getParameter("action");
		logger.info("ACTION: " + action);
		
		if(appUser==null){
			return this.loginView;
		}else{
			
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_IMPORT);
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, TdsConstants.ACTIVE_URL_RPG_INITVALUE);
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG_UPDATE, TdsConstants.ACTIVE_URL_RPG_INITVALUE);
			
			boolean isValidCreatedRecordTransactionOnRPG = true;
			//Keys and header information
			String opd = request.getParameter("opd");
			String avd = request.getParameter("avd");
			String sign = request.getParameter("sign");
			String status = request.getParameter("status");
			String datum = request.getParameter("datum");
			
			//this fragment gets some header fields needed for the validator
			JsonTdsExportSpecificTopicRecord headerRecord = (JsonTdsExportSpecificTopicRecord)session.getAttribute(TdsConstants.DOMAIN_RECORD_TOPIC);
			String invoiceTotalAmount = headerRecord.getSveh_fabl();
			
			
			//this key is only used with a real Update. When empty it will be a signal for a CREATE NEW (Add)
			if(recordToValidate.getIsModeUpdate()!=null && "true".equals(recordToValidate.getIsModeUpdate())){
				logger.info("[INFO isModeUdate] " + recordToValidate.getIsModeUpdate());
			}
			model.put("avd", avd);
			model.put("opd", opd);
			model.put("sign", sign);
			model.put("status", status);
			model.put("datum", datum);
			
			
			if(TdsConstants.ACTION_UPDATE.equals(action)){
				
				TdsExportInvoiceValidator validator = new TdsExportInvoiceValidator();
				logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
			    validator.validate(recordToValidate, bindingResult);
			    //check for ERRORS
				if(bindingResult.hasErrors()){
				    	logger.info("[ERROR Validation] Record does not validate)");
				    	logger.info("[INFO faktnr] " + recordToValidate.getSvef_fatx());
				    	bindingErrorsExist = true;
				    	model.put("record", recordToValidate);
				    	
			    }else{
			    	if(recordToValidate.getIsModeUpdate()!=null && "true".equals(recordToValidate.getIsModeUpdate())){
						//-------
						//UPDATE
						//-------
						logger.info("UPDATE(only) ITEM (existent invoice) on process...");
					}else{
						//Minimum
						if(recordToValidate.getSvef_fatx()!=null && !"".equals(recordToValidate.getSvef_fatx())){
							//-------
							//CREATE
							//-------
							logger.info("CREATE and UPDATE on ITEM (new fresh invoice) on process...");
							//This means that the update will be done AFTER a creation of an empty record. All this in the same transaction. 2 STEPS involved: (1)create and (2)update
							//-----------------------------------------------------------------------------------------
							//STEP[1] Generate new Item line key seeds (avd,opd,sftxt) by creating an empty new record. 
							//		  This step is ONLY applicable for new item lines 
							//-------------------------------------------------------------------------------------------
							jsonTdsExportTopicInvoiceRecord  = this.createNewItemKeySeeds(recordToValidate, avd, opd, session, request, appUser);
							if(jsonTdsExportTopicInvoiceRecord!=null){
								String newId = jsonTdsExportTopicInvoiceRecord.getSvef_fatx();
								//take the rest from GUI.
								jsonTdsExportTopicInvoiceRecord = new JsonTdsExportTopicInvoiceRecord();
								ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonTdsExportTopicInvoiceRecord);
					            //binder.registerCustomEditor(...); // if needed
					            binder.bind(request);
					            logger.info("[INFO] populate svef_fatx:" + newId);
							}else{
								isValidCreatedRecordTransactionOnRPG = false;
							}
						}else{
							isValidCreatedRecordTransactionOnRPG = false;
						}
					}
					//--------------------------------------------------
					//At this point we are ready to do an update
					//--------------------------------------------------
					if(isValidCreatedRecordTransactionOnRPG){
						logger.info("[INFO] Valid STEP[1] Add Record(if applicable) successfully created, OK ");
						//adjust after bind (both UPDATE or CREATE)
						this.adjustFieldsAfterBind(request, jsonTdsExportTopicInvoiceRecord);
						
						//---------------------------
						//get BASE URL = RPG-PROGRAM
			            //---------------------------
						String BASE_URL_UPDATE = TdsExportUrlDataStore.TDS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_URL;
						urlRequestParamsKeys = this.getRequestUrlKeyParametersUpdate(jsonTdsExportTopicInvoiceRecord.getSvef_fatx(), avd, opd, appUser, TdsConstants.MODE_UPDATE);
						String urlRequestParamsTopicItem = this.urlRequestParameterMapper.getUrlParameterValidString((jsonTdsExportTopicInvoiceRecord));
						//put the final valid param. string
						String urlRequestParams = urlRequestParamsKeys + urlRequestParamsTopicItem;
						//for debug purposes in GUI
						session.setAttribute(TdsConstants.ACTIVE_URL_RPG_UPDATE, BASE_URL_UPDATE + "==>params: " + urlRequestParams.toString()); 
						
						logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
				    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL_UPDATE));
				    	logger.info("URL PARAMS: " + urlRequestParams);
				    	//----------------------------------------------------------------------------
				    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
				    	//----------------------------------------------------------------------------
						String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_UPDATE, urlRequestParams);
					    	
						//Debug --> 
				    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
				    	//we must evaluate a return RPG code in order to know if the Update was OK or not
				    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicInvoiceCreateOrUpdate(rpgReturnPayload);
				    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
				    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
				    		this.setFatalError(model, rpgReturnResponseHandler, jsonTdsExportTopicInvoiceRecord);
				    		
				    	}else{
				    		//Update succefully done!
				    		logger.info("[INFO] Valid STEP[2] Update -- Record successfully updated, OK ");
				    		//put domain objects (it is not necessary since the fetch below (onFetch) will clean this up anyway)
				    	}
					}else{
						if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage()) ){
							rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tuid, syop generation : " + rpgReturnResponseHandler.getErrorMessage());
							this.setFatalError(model, rpgReturnResponseHandler, jsonTdsExportTopicInvoiceRecord);
						}
					}
			    }
				
			}else if(TdsConstants.ACTION_DELETE.equals(action)){
				
				logger.info("[INFO] Delete record start process... ");
				String lineIdToDelete = request.getParameter("fak");
				
				//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				String BASE_URL_DELETE = TdsExportUrlDataStore.TDS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_URL;
				String urlRequestParams = this.getRequestUrlKeyParametersUpdate(lineIdToDelete, avd, opd, appUser,TdsConstants.MODE_DELETE );
				
				logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL_DELETE));
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	//----------------------------------------------------------------------------
		    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
		    	//----------------------------------------------------------------------------
		    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_DELETE, urlRequestParams);
			    	
				//Debug --> 
		    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
		    	//we must evaluate a return RPG code in order to know if the Update was OK or not
		    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgReturnPayload);
		    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
		    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
		    		this.setFatalError(model, rpgReturnResponseHandler, jsonTdsExportTopicInvoiceRecord);
		    	}else{
		    		//Delete succefully done!
		    		logger.info("[INFO] Valid Delete -- Record successfully deleted, OK ");
		    	}
				
			}
			
			
			//FETCH the ITEM LIST of existent ITEMs for this TOPIC
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL_FETCH = TdsExportUrlDataStore.TDS_EXPORT_BASE_FETCH_TOPIC_INVOICELIST_URL;
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(request, avd, opd, appUser);
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("FETCH av item list... ");
	    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL_FETCH));
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
			String jsonPayloadFetch = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
			//for debug purposes in GUI
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL_FETCH + "==>params: " + urlRequestParamsKeys.toString() + 
					" " + "(fetched list):" + jsonPayloadFetch); 
			
			//Debug --> 
			logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayloadFetch));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	JsonTdsExportTopicInvoiceContainer jsonTdsExportTopicInvoiceContainer = this.tdsExportSpecificTopicService.getTdsExportTopicInvoiceContainerContainer(jsonPayloadFetch);
	    	if(jsonTdsExportTopicInvoiceContainer!=null){
	    		//get totals from AS400
	    		JsonTdsExportSpecificTopicFaktTotalRecord sumFaktTotalRecord = this.getInvoiceTotalFromInvoices(avd, opd, appUser);
	    		jsonTdsExportTopicInvoiceContainer.setCalculatedValidCurrency(sumFaktTotalRecord.getTot_vakd());
	    		jsonTdsExportTopicInvoiceContainer.setCalculatedItemLinesTotalAmount(sumFaktTotalRecord.getTot_fabl());
	    		logger.info("CalculatedItemLinesTotalAmount:" + jsonTdsExportTopicInvoiceContainer.getCalculatedItemLinesTotalAmount());
	    		
	    		/*OBSOLETE SECTION. Has been repalced by service AS400 above: this.getInvoiceTotalFromInvoices...
	    		//Set the common currency code for all invoices (if more than one)
	    		jsonTdsExportTopicInvoiceContainer.setCalculatedValidCurrency(this.tdsExportCalculator.getFinalCurrency(jsonTdsExportTopicInvoiceContainer));
	    		
	    		Double calculatedItemLinesTotalAmount = this.tdsExportCalculator.getItemLinesTotalAmountInvoice(jsonTdsExportTopicInvoiceContainer);
	    		Double diffItemLinesTotalAmountWithInvoiceTotalAmount = this.tdsExportCalculator.getDiffBetweenCalculatedTotalAmountAndTotalAmount(invoiceTotalAmount, calculatedItemLinesTotalAmount);
	    		logger.info("CalculatedItemLinesTotalAmount:" + calculatedItemLinesTotalAmount);
	    		logger.info("diffItemLinesTotalAmountWithInvoiceTotalAmount:" + diffItemLinesTotalAmountWithInvoiceTotalAmount);
	    		jsonTdsExportTopicInvoiceContainer.setCalculatedItemLinesTotalAmount(calculatedItemLinesTotalAmount);
	    		jsonTdsExportTopicInvoiceContainer.setDiffItemLinesTotalAmountWithInvoiceTotalAmount(diffItemLinesTotalAmountWithInvoiceTotalAmount);
				 */   
	    	} 
			
	    	//drop downs populated from back-end
	    	//this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","GCY");
    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","MDX");
    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","MCF");
	    	
    		this.setDomainObjectsForListInView(model, jsonTdsExportTopicInvoiceContainer);
			
    		
    		//this next step is necessary for the default values on "create new" record
    		if(bindingErrorsExist){
    			this.setDefaultDomainItemRecordInView(model, jsonTdsExportTopicInvoiceContainer, recordToValidate);
    		}else{
    			this.setDefaultDomainItemRecordInView(model, jsonTdsExportTopicInvoiceContainer, null);
    		}
    		
	    	successView.addObject("model",model);
			//successView.addObject(Constants.EDIT_ACTION_ON_TOPIC, Constants.ACTION_FETCH);
	    	logger.info("END of method");
	    	return successView;
		}
		
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
	 * Set aspects  objects
	 * @param model
	 * @param rpgReturnResponseHandler
	 */
	private void setAspectsInView (Map model, RpgReturnResponseHandler rpgReturnResponseHandler){
		model.put(TdsConstants.ASPECT_ERROR_MESSAGE, rpgReturnResponseHandler.getErrorMessage());
		//extra error information
		StringBuffer errorMetaInformation = new StringBuffer();
		errorMetaInformation.append(rpgReturnResponseHandler.getUser());
		//errorMetaInformation.append(rpgReturnResponseHandler.getOpd());
		model.put(TdsConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
	/**
	 * Sets domain objects
	 * @param model
	 * @param container
	 * 
	 */
	private void setDomainObjectsForListInView(Map model, JsonTdsExportTopicInvoiceContainer container){
		List list = new ArrayList();
		if(container!=null){
			for (JsonTdsExportTopicInvoiceRecord record : container.getInvList()){
				//this.adjustDatesOnFetch(record);
				list.add(record);
			}
		}
		model.put(TdsConstants.DOMAIN_LIST, list);
		model.put(TdsConstants.DOMAIN_RECORD_ITEM_CONTAINER_INVOICE_TOPIC, container);
	}
	/**
	 * Sets domain objects
	 * @param model
	 * @param record
	 */
	private void setDomainObjectsInView(Map model, JsonTdsExportTopicInvoiceRecord record){
		//this.adjustDatesOnFetch(record);
		model.put(TdsConstants.DOMAIN_RECORD, record);
	}
	
	/**
	 * The method populates a default record (including original records before a validation error)
	 * The requirement here is to propose some predefined default values to the end-user.
	 *  
	 * @param model
	 * @param container
	 * @param recordToValidate
	 * 
	 */
	private void setDefaultDomainItemRecordInView(Map model, JsonTdsExportTopicInvoiceContainer container, JsonTdsExportTopicInvoiceRecord recordToValidate){
		List list = new ArrayList();
		JsonTdsExportTopicInvoiceRecord defaultRecord = new JsonTdsExportTopicInvoiceRecord();
		
		if(container!=null){
			
			//meaning that there were validation errors
			if(recordToValidate!=null){
				defaultRecord = recordToValidate;//in order to retain the original values before the validation errors
				model.put(TdsConstants.DOMAIN_RECORD, defaultRecord);
				
			}else{
				model.put(TdsConstants.DOMAIN_RECORD, defaultRecord);				
			}
		}
	}
	
	
	
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param record
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonTdsExportTopicInvoiceRecord record){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, record);
	}
	
	/**
	 * 
	 * @param jsonTdsExportTopicInvoiceRecord
	 * @param avd
	 * @param opd
	 * @param session
	 * @param request
	 * @param appUser
	 * @return
	 */
	private JsonTdsExportTopicInvoiceRecord createNewItemKeySeeds(JsonTdsExportTopicInvoiceRecord record, String avd, String opd, HttpSession session, HttpServletRequest request, SystemaWebUser appUser){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_URL;
		
		//-------------------------------------------------------------------------------------------
		// STEP[PREPARE CREATION] --> generate new opd and tuid (if applicable) in order to be able to Add (Create)
		//-------------------------------------------------------------------------------------------
		logger.info("STEP[1] GET SEEDS and CREATE RECORD...");
		//logger.info("STEP[1] numberOfItemLinesInTopicStr: " + numberOfItemLinesInTopicStr);
		StringBuffer urlRequestParamsForSeed = new StringBuffer();
		urlRequestParamsForSeed.append("user=" + appUser.getUser());
		urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fak=" + record.getSvef_fatx());
		urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TdsConstants.MODE_ADD);
		logger.info("URL for SEED: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("PARAMS for SEED: " + urlRequestParamsForSeed.toString());
		//for debug purposes in GUI
		session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL + " ==>params: " + urlRequestParamsForSeed.toString() );
				
		//Get the counter from RPG (new opd Id)
		String rpgSeedNumberPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsForSeed.toString());
		
		// Map the JSON response to the new seeds (syop,syli)
		// We are not using std JSON conversion since the RPGs strings are not the same. Should be the same as
		// the header fields. The RPG output should be changed in order to comply to the field specification...
		rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgSeedNumberPayload);
		
		//we must complete the GUI-json with the value from a line nr seed here
		if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage()) ){
			logger.info("[ERROR] No mandatory seeds were generated correctly)! look at std output log. [errMsg]" + rpgReturnResponseHandler.getErrorMessage());
			record = null;
		}
        
		return record;
	}
	/**
	 * 
	 * @param request
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(HttpServletRequest request, String avd, String opd, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		
		return urlRequestParamsKeys.toString();
	}
	/**
	 * 
	 * Parameters for a creation of a "next" item line
	 * 
	 * @param lineId
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersUpdate(String invoiceId, String avd, String opd, SystemaWebUser appUser, String mode){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fak=" + invoiceId);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + mode);
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * We must adjust some fields that require it (presentation requirements)
	 * @param record
	 */
	/*
	private void adjustDatesOnFetch(JsonTdsImportTopicInvoiceRecord record){
		String dateSfdtNO = this.dateFormatter.convertToDate_NO(record.getSfdt());
		//fields
		record.setSfdt(dateSfdtNO);
	}
	*/
	/**
	 * 
	 * @param request
	 * @param record
	 */
	
	private void adjustFieldsAfterBind(HttpServletRequest request, JsonTdsExportTopicInvoiceRecord record){
		String factor = request.getParameter("factor");
		//fields
		record.setSvef_omr(factor);
	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	
	@Qualifier 
	private TdsTaricVarukodService tvinnSadTaricVarukodService;
	@Autowired
	@Required	
	public void setTdsTaricVarukodService(TdsTaricVarukodService value){this.tvinnSadTaricVarukodService = value;}
	public TdsTaricVarukodService getTdsTaricVarukodService(){ return this.tvinnSadTaricVarukodService; }
	
	
	
	@Qualifier ("dropDownListPopulationService")
	private DropDownListPopulationService dropDownListPopulationService;
	@Autowired
	public void setSadImportDropDownListPopulationService (DropDownListPopulationService value){ this.dropDownListPopulationService=value; }
	public DropDownListPopulationService getDropDownListPopulationService(){return this.dropDownListPopulationService;}
	
	
	@Qualifier ("tdsDropDownListPopulationService")
	private TdsDropDownListPopulationService tdsDropDownListPopulationService;
	@Autowired
	public void setTdsDropDownListPopulationService (TdsDropDownListPopulationService value){ this.tdsDropDownListPopulationService=value; }
	public TdsDropDownListPopulationService getTdsDropDownListPopulationService(){return this.tdsDropDownListPopulationService;}
	
	
	
	@Qualifier ("tdsExportSpecificTopicService")
	private TdsExportSpecificTopicService tdsExportSpecificTopicService;
	@Autowired
	@Required
	public void setTdsExportSpecificTopicService (TdsExportSpecificTopicService value){ this.tdsExportSpecificTopicService = value; }
	public TdsExportSpecificTopicService getTdsExportSpecificTopicService(){ return this.tdsExportSpecificTopicService; }
	
	
	 
}

