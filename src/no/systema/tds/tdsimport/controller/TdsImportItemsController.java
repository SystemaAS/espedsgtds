package no.systema.tds.tdsimport.controller;

import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
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
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.StringManager;


import no.systema.tds.tdsimport.util.manager.TdsImportItemsAutoControlMgr;
import no.systema.tds.tdsimport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tds.tdsimport.model.KundensVaruRegisterUpdateItemRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemAvgifterRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicRecord;

import no.systema.tds.tdsimport.service.TdsImportSpecificTopicItemService;
import no.systema.tds.tdsimport.service.html.dropdown.DropDownListPopulationService;
import no.systema.tds.tdsimport.url.store.TdsImportUrlDataStore;
import no.systema.tds.tdsimport.util.RpgReturnResponseHandler;
import no.systema.tds.tdsimport.util.TdsImportCalculator;
import no.systema.tds.tdsimport.util.manager.TdsImportAvgiftsberakningenMgr;
import no.systema.tds.tdsimport.validator.TdsImportItemsValidator;
import no.systema.tds.util.TdsConstants;
import no.systema.tds.service.TdsBilagdaHandlingarYKoderService;
import no.systema.tds.service.TdsTaricVarukodService;
import no.systema.tds.service.TdsTillaggskoderService;
import no.systema.tds.service.html.dropdown.TdsDropDownListPopulationService;

import no.systema.tds.util.manager.CodeDropDownMgr;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTaricVarukodContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTaricVarukodRecord;
import no.systema.tds.url.store.TdsUrlDataStore;
import no.systema.tds.model.jsonjackson.validation.JsonTdsMangdEnhetContainer;
import no.systema.tds.model.jsonjackson.validation.JsonTdsMangdEnhetRecord;


/**
 * TDS Import create items gateway
 * 
 * @author oscardelatorre
 * @date Sep 2, 2103
 * 
 */

@Controller
//@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TdsImportItemsController {
	private static final Logger logger = Logger.getLogger(TdsImportItemsController.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(1500);
	private final StringManager strMgr = new StringManager();
	
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private TdsImportCalculator tdsImportCalculator = new TdsImportCalculator();
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private final String NOT_FOUND = "NOT FOUND";
	private final String MATCH = "MATCH";
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {

    }
	
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsimport_edit_items.do")
	public ModelAndView tdsImportEditItem(@ModelAttribute ("record") JsonTdsImportSpecificTopicItemRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("Inside: tdsImportEditItem");
		ModelAndView successView = new ModelAndView("tdsimport_edit_items");
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonTdsImportSpecificTopicItemRecord jsonTdsImportSpecificTopicItemRecord = null;
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		Map model = new HashMap();
		String urlRequestParamsKeys = null;
		//Catch required action (doFetch or doUpdate)
		String action = request.getParameter("action");
		logger.info("ACTION: " + action);
		
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_IMPORT);
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, TdsConstants.ACTIVE_URL_RPG_INITVALUE); 
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG_UPDATE, TdsConstants.ACTIVE_URL_RPG_INITVALUE);

			
			boolean isValidCreatedRecordTransactionOnRPG = true;
			//Keys and header information
			String opd = request.getParameter("opd");
			String avd = request.getParameter("avd");
			String sign = request.getParameter("sign");
			String tullId = request.getParameter("tullId");
			String status = request.getParameter("status");
			String datum = request.getParameter("datum");
			String invoiceTotalAmount = request.getParameter("fabl");
			//this fragment gets some header fields needed for the validator
			JsonTdsImportSpecificTopicRecord headerRecord = (JsonTdsImportSpecificTopicRecord)session.getAttribute(TdsConstants.DOMAIN_RECORD_TOPIC);
			String header_svih_cont = null;
			String header_svih_mtyp = null;
			if(headerRecord!=null){
				header_svih_cont = headerRecord.getSvih_cont();
				header_svih_mtyp = headerRecord.getSvih_mtyp();
			}
			
			//this key is only used with a real Update. When empty it will be a signal for a CREATE NEW (Add)
			String lineNr = request.getParameter("sviv_syli");
			if(lineNr!=null && !"".equals(lineNr)){
				//nothing
			}else{
				//this branch is necessary in order to get the line Nr after a validation error (ref. below att bindingResult.hasErrors in this same method)
				lineNr = (String)session.getAttribute("sviv_syli_SESSION");
			}
			model.put("avd", avd);
			model.put("opd", opd);
			model.put("sign", sign);
			model.put("tullId", tullId);
			model.put("status", status);
			model.put("datum", datum);
			
			if(TdsConstants.ACTION_UPDATE.equals(action)){
				//-----------
				//Validation
				//-----------
				//we must validate towards the back-end here in order to avoid injection problems in Validator
				//The validation routine for Taric Varukod pinpoints those input values in which the user HAVE NOT used the search routine
				String varukodValidNumber = this.getTaricVarukod(appUser.getUser(), recordToValidate.getSviv_vata());
				if(!this.MATCH.equals(varukodValidNumber)){
					//REMOVED - DHL req. 1.Jun.2017
					//recordToValidate.setSviv_vata(null); 
					recordToValidate.setValidNumberVata(false);
				}
				
				//put some header records in aux.attributes (in order to send to validator)... Add more if applicable
				recordToValidate.setHeader_svih_cont(header_svih_cont);
				recordToValidate.setHeader_svih_mtyp(header_svih_mtyp);
				//set MangdEnhet Directives if applicable
				this.getMandatoryMangdEnhetDirective(appUser.getUser(), recordToValidate);
				
				TdsImportItemsValidator validator = new TdsImportItemsValidator();
				logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
			    validator.validate(recordToValidate, bindingResult);
			    //check for ERRORS
				if(bindingResult.hasErrors()){
				    	logger.info("[ERROR Validation] Item Record does not validate)");
				    	logger.info("[INFO lineNr] " + lineNr);
				    	model.put("sign", sign);
				    	model.put("record", recordToValidate);
				    	if(lineNr!=null && !"".equals(lineNr)){
				    		logger.info("[INFO lineNr] ... filling old value: lineNr:" + lineNr);
				    		session.setAttribute("sviv_syli_SESSION", lineNr);
					    	recordToValidate.setSviv_syop(opd);
				    		recordToValidate.setSviv_syav(avd);
				    	}
			    }else{
					if(lineNr!=null && !"".equals(lineNr)){
						//clean
						session.removeAttribute("sviv_syli_SESSION");
				    	
						//-------
						//UPDATE
						//-------
						logger.info("UPDATE(only) ITEM (existent item) on process...");
						//take the rest from GUI.
						jsonTdsImportSpecificTopicItemRecord = new JsonTdsImportSpecificTopicItemRecord();
						ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonTdsImportSpecificTopicItemRecord);
			            //binder.registerCustomEditor(...); // if needed
			            binder.bind(request);
			            
			            jsonTdsImportSpecificTopicItemRecord.setSviv_syli(lineNr);
			            jsonTdsImportSpecificTopicItemRecord.setSviv_vano(lineNr);
			            jsonTdsImportSpecificTopicItemRecord.setSviv_syop(opd);
			            jsonTdsImportSpecificTopicItemRecord.setSviv_syav(avd);
			            //Extra Mängd completion after validation
			            jsonTdsImportSpecificTopicItemRecord.setSviv_ankv(recordToValidate.getSviv_ankv());
			            
					}else{
						//-------
						//CREATE
						//-------
						//clean
						session.removeAttribute("sviv_syli_SESSION");
				    	
						logger.info("CREATE and UPDATE on ITEM (new fresh item) on process...");
						//This means that the update will be done AFTER a creation of an empty record. All this in the same transaction. 2 STEPS involved: (1)create and (2)update
						//-----------------------------------------------------------------------------------------
						//STEP[1] Generate new Item line key seeds (avd,opd,syli) by creating an empty new record. 
						//		  This step is ONLY applicable for new item lines 
						//-------------------------------------------------------------------------------------------
						jsonTdsImportSpecificTopicItemRecord  = this.createNewItemKeySeeds(session, request, appUser);
						if(jsonTdsImportSpecificTopicItemRecord!=null){
							String newLineNr = jsonTdsImportSpecificTopicItemRecord.getSviv_syli();
							
							//take the rest from GUI.
							jsonTdsImportSpecificTopicItemRecord = new JsonTdsImportSpecificTopicItemRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonTdsImportSpecificTopicItemRecord);
				            //binder.registerCustomEditor(...); // if needed
				            binder.bind(request);
				            
				            logger.info("[INFO] populate the sviv_syli:" + newLineNr);
				            //put back the generated seed and the header keys (syop, syavd)
				            jsonTdsImportSpecificTopicItemRecord.setSviv_syli(newLineNr);
				            jsonTdsImportSpecificTopicItemRecord.setSviv_vano(newLineNr);
				            jsonTdsImportSpecificTopicItemRecord.setSviv_syop(opd);
				            jsonTdsImportSpecificTopicItemRecord.setSviv_syav(avd);
				            //Extra Mängd completion after validation
				            jsonTdsImportSpecificTopicItemRecord.setSviv_ankv(recordToValidate.getSviv_ankv());
				            
				            //-----------------------------------------------------------------------
				            //Now calculate the charges (Avgiftsberäkningen) always with CREATE NEW
				            //-----------------------------------------------------------------------
				            TdsImportAvgiftsberakningenMgr avgifterMgr = new TdsImportAvgiftsberakningenMgr(this.tdsImportSpecificTopicItemService, this.urlCgiProxyService);
				            JsonTdsImportSpecificTopicItemAvgifterRecord avgifterRecord = avgifterMgr.calculateChargesOnItem(session, jsonTdsImportSpecificTopicItemRecord, appUser);
				            if(avgifterRecord!=null){
			            			avgifterMgr.handOverAttributes(avgifterRecord, jsonTdsImportSpecificTopicItemRecord);
			            			logger.info("stva: " + jsonTdsImportSpecificTopicItemRecord.getSviv_stva());
			            			logger.info("stva2: " + jsonTdsImportSpecificTopicItemRecord.getSviv_stva2());
			            			logger.info("abb1: " + jsonTdsImportSpecificTopicItemRecord.getSviva_abb1());
			            			logger.info("abg1: " + jsonTdsImportSpecificTopicItemRecord.getSviva_abg1());
				            }
				            
						}else{
							isValidCreatedRecordTransactionOnRPG = false;
						}
					}
					//--------------------------------------------------
					//At this point we are ready to do an update
					//--------------------------------------------------
					if(isValidCreatedRecordTransactionOnRPG){
						
						//Extra check on [Invoice Due Amount (svev_fabl)] vs [Statistiskt varde (svev_stva)]
			            //if svev_stva = null then copy the svev_fabl (fakturabelopp)
			            //this.updateStatisticalValue(jsonTdsImportSpecificTopicItemRecord);
			            //this.updateTullvardeValue(jsonTdsImportSpecificTopicItemRecord);
			            
			            logger.info("[INFO] Valid STEP[1] Add Record successfully created, OK ");
						//---------------------------
						//get BASE URL = RPG-PROGRAM
			            //---------------------------
						String BASE_URL_UPDATE = TdsImportUrlDataStore.TDS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
						logger.info("[INFO] UPDATE to be done with lineNr [sviv_syli]:" + jsonTdsImportSpecificTopicItemRecord.getSviv_syli());
						
						urlRequestParamsKeys = this.getRequestUrlKeyParametersUpdate(jsonTdsImportSpecificTopicItemRecord.getSviv_syli(), avd, opd, appUser, TdsConstants.MODE_UPDATE);
						String urlRequestParamsTopicItem = this.urlRequestParameterMapper.getUrlParameterValidString((jsonTdsImportSpecificTopicItemRecord));
						//put the final valid param. string
						String urlRequestParams = urlRequestParamsKeys + urlRequestParamsTopicItem;
						//for debug purposes in GUI
						session.setAttribute(TdsConstants.ACTIVE_URL_RPG_UPDATE, BASE_URL_UPDATE + "==>params: " + urlRequestParams.toString()); 
						
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
				    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgReturnPayload);
				    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
				    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
				    		this.setFatalError(model, rpgReturnResponseHandler, jsonTdsImportSpecificTopicItemRecord);
				    		
				    	}else{
				    		//Update succefully done!
				    		logger.info("[INFO] Valid STEP[2] Update -- Record successfully updated, OK ");
				    		//put domain objects (it is not necessary since the fetch below (onFetch) will clean this up anyway)
				    	}
					}else{
						rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tuid, syop generation : " + rpgReturnResponseHandler.getErrorMessage());
						this.setFatalError(model, rpgReturnResponseHandler, jsonTdsImportSpecificTopicItemRecord);
					}
			    }
				
			}else if(TdsConstants.ACTION_DELETE.equals(action)){
				logger.info("[INFO] Delete record start process... ");
				String lineNrToDelete = request.getParameter("lin");
				
				//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				String BASE_URL_DELETE = TdsImportUrlDataStore.TDS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
				String urlRequestParams = this.getRequestUrlKeyParametersUpdate(lineNrToDelete, avd, opd, appUser,TdsConstants.MODE_DELETE );
				
				logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			    	logger.info("URL: " + BASE_URL_DELETE);
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
			    		this.setFatalError(model, rpgReturnResponseHandler, jsonTdsImportSpecificTopicItemRecord);
			    		
			    	}else{
			    		//Delete succefully done!
			    		logger.info("[INFO] Valid Delete -- Record successfully deleted, OK ");
			    	}
			
			}
			
			//FETCH the ITEM LIST of existent ITEMs for this TOPIC
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL_FETCH = TdsImportUrlDataStore.TDS_IMPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(request, avd, opd, appUser);
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("FETCH av item list... ");
	    	logger.info("URL: " + BASE_URL_FETCH);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
			String jsonPayloadFetch = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
			//for debug purposes in GUI
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL_FETCH + "==>params: " + urlRequestParamsKeys.toString()  + " " + "(fetched item list):" + jsonPayloadFetch);  
			
			//Debug --> 
	    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayloadFetch));
	    	
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	JsonTdsImportSpecificTopicItemContainer jsonTdsImportSpecificTopicItemContainer = this.getTdsImportSpecificTopicItemService().getTdsImportSpecificTopicItemContainer(jsonPayloadFetch);
	    	if(jsonTdsImportSpecificTopicItemContainer!=null){
		    	Double calculatedItemLinesTotalAmount = this.tdsImportCalculator.getItemLinesTotalAmount(jsonTdsImportSpecificTopicItemContainer);
		    	Double diffItemLinesTotalAmountWithInvoiceTotalAmount = this.tdsImportCalculator.getDiffBetweenCalculatedTotalAmountAndTotalAmount(invoiceTotalAmount, calculatedItemLinesTotalAmount);
		    	jsonTdsImportSpecificTopicItemContainer.setCalculatedItemLinesTotalAmount(calculatedItemLinesTotalAmount);
		    	jsonTdsImportSpecificTopicItemContainer.setDiffItemLinesTotalAmountWithInvoiceTotalAmount(diffItemLinesTotalAmountWithInvoiceTotalAmount);
	    	}
	    	//general code population
	    	this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","KLI");
    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","THO");
    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","CHN");
    		//country and currency codes
    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","GCY");
    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","MDX");
    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","MCF");
    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"I","SAL");
    		
    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"I","FOR");
    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"I","FF1");
    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","FFK");
    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","MDM");
    		
    		//drop downs populated from a txt file
    		this.setDomainObjectsForListInView(session, model, jsonTdsImportSpecificTopicItemContainer);
			
    		successView.addObject("model",model);
    		//successView.addObject(Constants.EDIT_ACTION_ON_TOPIC, Constants.ACTION_FETCH);
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsimport_edit_items_autocontrol.do")
	public ModelAndView tdsImportEditItemAutoControl(@ModelAttribute ("record") JsonTdsImportSpecificTopicItemRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("Inside: tdsImportEditItemAutoControl");
		final String AUTO_CONTROL_ERROR_FLAG_VALUE = "X";
		final String ERROR_FRAME_STD_OUTPUT = "-------------------------------------------------------------";
		
		ModelAndView successView = null;
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		TdsImportAvgiftsberakningenMgr avgifterMgr = new TdsImportAvgiftsberakningenMgr(this.tdsImportSpecificTopicItemService, this.urlCgiProxyService);
        
		JsonTdsImportSpecificTopicItemRecord jsonTdsImportSpecificTopicItemRecord = null;
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//this fragment gets some header fields needed for the validator
		JsonTdsImportSpecificTopicRecord headerRecord = (JsonTdsImportSpecificTopicRecord)session.getAttribute(TdsConstants.DOMAIN_RECORD_TOPIC);
		
		Map model = new HashMap();
		String urlRequestParamsKeys = null;
		//
		String fabl = request.getParameter("fablAutoControl");
		
		if(appUser==null){
			return this.loginView;
		}else{
			StringBuffer params = new StringBuffer();
			params.append("user=" + appUser.getUser() + "&avd=" + recordToValidate.getSviv_syav() + "&opd=" + recordToValidate.getSviv_syop() + "&fabl=" + fabl);
			successView = new ModelAndView("redirect:tdsimport_edit_items.do?" + params);
			//FETCH the ITEM LIST of existent ITEMs for this TOPIC
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL_FETCH = TdsImportUrlDataStore.TDS_IMPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(request, recordToValidate.getSviv_syav(), recordToValidate.getSviv_syop(), appUser);
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("FETCH item list... ");
	    	logger.info("URL: " + BASE_URL_FETCH);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
			String jsonPayloadFetch = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
			//for debug purposes in GUI
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL_FETCH + "==>params: " + urlRequestParamsKeys.toString() + " " + "(fetched item list):" + jsonPayloadFetch);   
			
			//Debug --> 
			logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayloadFetch));
	    	
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			//logger.info("AA");
	    	JsonTdsImportSpecificTopicItemContainer container = this.tdsImportSpecificTopicItemService.getTdsImportSpecificTopicItemContainer(jsonPayloadFetch);
	    	if(container!=null){
	    		//INIT Autocontrol Manager...
	    		TdsImportItemsAutoControlMgr autoControlMgr = new TdsImportItemsAutoControlMgr(this.urlCgiProxyService, this.tdsImportSpecificTopicItemService);
	    		//Iterate on every line to control the requirements
	    		for(JsonTdsImportSpecificTopicItemRecord record: container.getOrderList()){
	    			//Init record
	    			autoControlMgr.setRecord(record);
	    			autoControlMgr.setHeaderRecord(headerRecord);
	    			//Begin with the validity checks
	    			String idDebug = record.getSviv_syli() + "-" + record.getSviv_vata();
	    			//----------------------------------------------
	    			//Check varukod and stop everything if invalid
	    			//----------------------------------------------
	    			String varukodValidNumber = this.getTaricVarukod(appUser.getUser(), record.getSviv_vata());
	    			if(!this.MATCH.equals(varukodValidNumber)){
	    				//Set error
						logger.info(ERROR_FRAME_STD_OUTPUT);
						logger.info("ERROR level (0) - Varukod invalid:" + idDebug);
						logger.info(ERROR_FRAME_STD_OUTPUT);
						autoControlMgr.updateItemWithAutoControlError(appUser.getUser(), AUTO_CONTROL_ERROR_FLAG_VALUE);
	    			}else{
		    			//---------------------------
		    			//START with calculations
		    			//---------------------------
		    			//logger.info("Check Calculations " + idDebug);
		    			autoControlMgr.calculateChargesOnItem(headerRecord, appUser.getUser());
		    			autoControlMgr.checkValidTillaggsKoder(this.tdsTillaggskoderService, appUser.getUser());
		    			autoControlMgr.checkValidFormansKod(this.tdsImportSpecificTopicItemService, appUser.getUser());
		    			
		    			//------------------------------------------------
			            //Now calculate the charges (Avgiftsberäkningen) 
			            //------------------------------------------------
		    			String urlRequestParamsCharges = avgifterMgr.getRequestUrlKeyParametersAvgiftsberakning(headerRecord, record, appUser);
			            JsonTdsImportSpecificTopicItemAvgifterRecord itemAvgiftsRecord = avgifterMgr.calculateChargesOnItem(appUser.getUser(), urlRequestParamsCharges);
			            if(itemAvgiftsRecord!=null){
		            		avgifterMgr.handOverAttributesOnUpdate(itemAvgiftsRecord, record);
	            			//logger.info("stva: " + record.getSviv_stva());logger.info("stva2: " + record.getSviv_stva2());
	            			//logger.info("abb1: " + record.getSviva_abb1());logger.info("abg1: " + record.getSviva_abg1());
			            }
		    			
		    			//Update (back-end) the record after the above calculations
		    			autoControlMgr.updateItemRecord(appUser.getUser());
						//---------------------------
		    			//START with validations now
		    			//---------------------------
						//Begin with the validity checks
		    			idDebug = record.getSviv_syli() + "-" + record.getSviv_vata();
		    			//logger.info("level check (1) " + idDebug);
						autoControlMgr.checkValidGrossAndNetWeight();
						if(autoControlMgr.isValidRecord()){
							//Go to level 2
							//logger.info("level check (2) " + idDebug);
	    					autoControlMgr.checkValidBilagdaHandlingar(this.tdsBilagdaHandlingarYKoderService, appUser.getUser());
	    					if(autoControlMgr.isValidRecord()){
	    						//Go to level 3
	    						//logger.info("level check (3) " + idDebug);
	    						autoControlMgr.checkValidAntalKolli();
	    						if(autoControlMgr.isValidRecord()){
	    							//Go to level 4
		    						//logger.info("level check (4) " + idDebug);
	    							autoControlMgr.getMandatoryMangdEnhetDirective(appUser.getUser());
	    							autoControlMgr.checkValidExtraMangdEnhet(appUser.getUser());
	    							if(autoControlMgr.isValidRecord()){
	    								//Update with Extramangd
		    		    				autoControlMgr.updateItemWithExgraMangdEnhet(appUser.getUser());
		    		    				
	    								//Go to level 5
			    						//logger.info("level check (5) " + idDebug);
	    								autoControlMgr.checkValidUrsprungslandKod(appUser.getUser());
	    								if(autoControlMgr.isValidRecord()){
	    									//Go to level FINAL MandatoryFields (must be the last check)
	    		    						//Nothing more below this level. New requirements must be insert between previous level and this FINAL level!
	    		    						autoControlMgr.checkValidMandatoryFields();
	    									if(autoControlMgr.isValidRecord()){
	    										//Update in order to remove previous error flags, if any...
	    		    		    				autoControlMgr.updateItemWithAutoControlError(appUser.getUser(), null );
	    		    		    			}else{
	    		    		    				//Set error
	    		    		    				logger.info(ERROR_FRAME_STD_OUTPUT);
	    			    						logger.info("ERROR level (FINAL) - Mandatory Fields" + idDebug);
	    			    						logger.info(ERROR_FRAME_STD_OUTPUT);
	    			    						autoControlMgr.updateItemWithAutoControlError(appUser.getUser(), AUTO_CONTROL_ERROR_FLAG_VALUE);
	    		    		    			}
	    					    			
	    								}else{
	    									//Set error
	    									logger.info(ERROR_FRAME_STD_OUTPUT);
	    		    						logger.info("ERROR level (5) - UrsprungslandKod" + idDebug);
	    		    						logger.info(ERROR_FRAME_STD_OUTPUT);
	    		    						autoControlMgr.updateItemWithAutoControlError(appUser.getUser(), AUTO_CONTROL_ERROR_FLAG_VALUE);
	    								}
	    							}else{
	    								//Set error
	    								logger.info(ERROR_FRAME_STD_OUTPUT);
	    	    						logger.info("ERROR level (4) - Extra Mangd Enhet" + idDebug);
	    	    						logger.info(ERROR_FRAME_STD_OUTPUT);
	    	    						autoControlMgr.updateItemWithAutoControlError(appUser.getUser(), AUTO_CONTROL_ERROR_FLAG_VALUE);
	    							}
	    							
	    						}else{
	    							//Set error
	    							logger.info(ERROR_FRAME_STD_OUTPUT);
		    						logger.info("ERROR level (3) - Antal kolli" + idDebug);
		    						logger.info(ERROR_FRAME_STD_OUTPUT);
		    						autoControlMgr.updateItemWithAutoControlError(appUser.getUser(), AUTO_CONTROL_ERROR_FLAG_VALUE);
	    						}
	    					}else{
	    						//Set error
	    						logger.info(ERROR_FRAME_STD_OUTPUT);
	    						logger.info("ERROR level (2) - Bilagda handlingar" + idDebug);
	    						logger.info(ERROR_FRAME_STD_OUTPUT);
	    						autoControlMgr.updateItemWithAutoControlError(appUser.getUser(), AUTO_CONTROL_ERROR_FLAG_VALUE);
	    					}
						}else{
							//Set error
							logger.info(ERROR_FRAME_STD_OUTPUT);
							logger.info("ERROR level (1) - Weights" + idDebug);
							logger.info(ERROR_FRAME_STD_OUTPUT);
							autoControlMgr.updateItemWithAutoControlError(appUser.getUser(), AUTO_CONTROL_ERROR_FLAG_VALUE);
						}
	    			}
	    		}
	    	}
			
		}
		return successView;
	}
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsimport_edit_items_doUpdateKundensVaruregister.do")
	public ModelAndView tdsImportEditItemUpdateKundensVaruregister(@ModelAttribute ("record") KundensVaruRegisterUpdateItemRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("Inside: tdsImportEditItemUpdateKundensVaruregister");
		
		ModelAndView successView = null;
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//this fragment gets some header fields needed for the validator
		JsonTdsImportSpecificTopicRecord headerRecord = (JsonTdsImportSpecificTopicRecord)session.getAttribute(TdsConstants.DOMAIN_RECORD_TOPIC);
		Map model = new HashMap();
		
		
		if(appUser==null){
			return this.loginView;
		}else{
			StringBuffer params = new StringBuffer();
			params.append("user=" + appUser.getUser() + "&avd=" + headerRecord.getSvih_syav() + "&opd=" + headerRecord.getSvih_syop());
			successView = new ModelAndView("redirect:tdsimport_edit_items.do?" + params);
			
			//UDPATE kundensvaruregister
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_UPDATE_TULLTAXA_KUNDENSVAREREG_VARUKODER_ITEMS_URL;
			String urlRequestParamsTopicItem = "user=" + appUser.getUser() + this.urlRequestParameterMapper.getUrlParameterValidString((recordToValidate));
			logger.info("URL:" + BASE_URL );
			logger.info("PARAMS:" + urlRequestParamsTopicItem);
			String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsTopicItem);
			
			logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
	    	//we must evaluate a return RPG code in order to know if the Update was OK or not
	    	rpgReturnResponseHandler.evaluateRpgResponseOnItemKundensVarRegisterUpdate(rpgReturnPayload);
	    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
	    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
	    		this.setFatalError(model, rpgReturnResponseHandler, new JsonTdsImportSpecificTopicItemRecord());
	    	}else{
	    		//Delete succefully done!
	    		logger.info("[INFO] Valid Insert -- Record successfully inserted, OK ");
	    	}	
			
		}
		return successView;
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param recordToValidate
	 * @param headerRecord
	 * @return
	 */
	private void getMandatoryMangdEnhetDirective(String applicationUser, JsonTdsImportSpecificTopicItemRecord recordToValidate){
		String TDS_IE = "I";
		
		String BASE_URL_FETCH = TdsUrlDataStore.TDS_CHECK_EXTRA_MANGDENHET;
		String urlRequestParamsKeys = "user="+ applicationUser + "&ie=" + TDS_IE + "&kod=" + recordToValidate.getSviv_vata() + "&lk=" + recordToValidate.getSviv_ulkd();

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("FETCH av mangdenhet... ");
    	logger.info("URL: " + BASE_URL_FETCH);
    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
    	//-----------------
    	//Json and execute 
    	//-----------------
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		JsonTdsMangdEnhetContainer container = this.tdsImportSpecificTopicItemService.getTdsMangdEnhetContainer(jsonPayload);
		for(JsonTdsMangdEnhetRecord record: container.getXtramangdenhet()){
			if(record.getXtra()!=null && record.getXtra().toUpperCase().equals("Y")){
				//Set all values
				recordToValidate.setExtraMangdEnhet(record.getXtra().toUpperCase());
				recordToValidate.setExtraMangdEnhetCode(record.getSvtx15_33());
				recordToValidate.setExtraMangdEnhetDescription(record.getSvtx03_04());
				//------------------------------------------
				//Rules of engagement to help the end-user
				//(Validation will not fire then ...)
				//------------------------------------------
				//RULE 1: NAR
				if("NAR".equals(recordToValidate.getExtraMangdEnhetCode())){
					if(strMgr.isNotNull(recordToValidate.getSviv_ankv())){
					 //Nothing	
					}else{
						if(strMgr.isNotNull(recordToValidate.getSviv_kota()) && !"0".equals(recordToValidate.getSviv_kota()) ){
							recordToValidate.setSviv_ankv(recordToValidate.getSviv_kota());
							logger.info("YES!!!:" + recordToValidate.getSviv_ankv());
						}
					}
				}
				//RULE 2: TODO
				//here ...
			}
		}
		
	}
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param varukod
	 * @return
	 */
	private String getTaricVarukod(String applicationUser, String taricVarukod) {
		logger.info("Inside getTaricVarukod()");
		String retval = null;
		
		String TDS_IE = "I";//always E 
		try{
		  String BASE_URL = TdsUrlDataStore.TDS_FETCH_TARIC_VARUKODER_ITEMS_URL;
		  String urlRequestParamsKeys = "user=" + applicationUser + "&ie=" + TDS_IE + "&kod=" + taricVarukod;
		  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  JsonTdsTaricVarukodContainer container = this.tdsTaricVarukodService.getContainer(jsonPayload);
		  if(container!=null){
			  for(JsonTdsTaricVarukodRecord record : container.getTullTaxalist()){
				  if(taricVarukod!=null && taricVarukod.equals(record.getSvvs_vata())){
					  logger.info("MATCH on VARUKOD !!!!: " + record.getSvvs_vata());
					  retval = this.MATCH;
				  }
			  }	
		  }
		}catch(Exception e){
			e.printStackTrace();
		}
	  
		return retval;
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
		errorMetaInformation.append(rpgReturnResponseHandler.getSviv_syop());
		model.put(TdsConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
	/**
	 * Sets domain objects
	 * 
	 * @param session
	 * @param model
	 * @param container
	 * 
	 */
	private void setDomainObjectsForListInView(HttpSession session, Map model, JsonTdsImportSpecificTopicItemContainer container){
		List list = new ArrayList();
		if(container!=null){
			for (JsonTdsImportSpecificTopicItemRecord record : container.getOrderList()){
				list.add(record);
				//DEBUG -- logger.info(record.getSviv_vasl());
			}
		}
		model.put(TdsConstants.DOMAIN_LIST, list);
		model.put(TdsConstants.DOMAIN_RECORD_ITEM_CONTAINER_TOPIC, container);
		//set a session variable in order to make the list available to an external view controller (Excel/PDF- Controller)
		session.setAttribute(session.getId() + TdsConstants.SESSION_LIST, list);
	}
	/**
	 * Sets domain objects
	 * @param model
	 * @param record
	 */
	private void setDomainObjectsInView(Map model, JsonTdsImportSpecificTopicItemRecord record){
		model.put(TdsConstants.DOMAIN_RECORD, record);
	}
	
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param record
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonTdsImportSpecificTopicItemRecord record){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, record);
	}
	
	/**
	 * 
	 * Creates the record (Add) for a later update in the same transaction
	 * @param session
	 * @param request
	 * @param appUser
	 * @return
	 */
	private JsonTdsImportSpecificTopicItemRecord createNewItemKeySeeds(HttpSession session, HttpServletRequest request, SystemaWebUser appUser){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		//request variables
		String numberOfItemLinesInTopicStr = request.getParameter("numberOfItemLinesInTopic");
		if(numberOfItemLinesInTopicStr==null || "".equals(numberOfItemLinesInTopicStr)){
			numberOfItemLinesInTopicStr = "0";
		}
			
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		
		JsonTdsImportSpecificTopicItemRecord jsonTdsImportSpecificTopicItemRecord = new JsonTdsImportSpecificTopicItemRecord();
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = TdsImportUrlDataStore.TDS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
		
		//-------------------------------------------------------------------------------------------
		// STEP[PREPARE CREATION] --> generate new opd and tuid (if applicable) in order to be able to Add (Create)
		//-------------------------------------------------------------------------------------------
		logger.info("STEP[1] GET SEEDS and CREATE RECORD...");
		logger.info("STEP[1] numberOfItemLinesInTopicStr: " + numberOfItemLinesInTopicStr);
		StringBuffer urlRequestParamsForSeed = new StringBuffer();
		urlRequestParamsForSeed.append("user=" + appUser.getUser());
		urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		Integer numberOfItemLinesInTopic = -99;
		try{
			numberOfItemLinesInTopic = Integer.valueOf(numberOfItemLinesInTopicStr);
			//add one
			numberOfItemLinesInTopic++;
			logger.info("New item line nr:" + numberOfItemLinesInTopic);
		}catch(Exception e){
			//nothing
		}
		urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + numberOfItemLinesInTopic);
		urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TdsConstants.MODE_ADD);
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
			logger.info("[ERROR] No mandatory seeds (syli, opd) were generated correctly)! look at std output log. [errMsg]" + rpgReturnResponseHandler.getErrorMessage());
			jsonTdsImportSpecificTopicItemRecord = null;
			
		}else{
			jsonTdsImportSpecificTopicItemRecord.setSviv_syop(rpgReturnResponseHandler.getSviv_syop().trim());
			jsonTdsImportSpecificTopicItemRecord.setSviv_syli(rpgReturnResponseHandler.getSviv_syli().trim());
		}
        
		return jsonTdsImportSpecificTopicItemRecord;
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
	 * @param numberOfItemLinesInTopic
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersUpdate(String lineNumber, String avd, String opd, SystemaWebUser appUser, String mode){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + lineNumber);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + mode);
		
		return urlRequestParamsKeys.toString();
	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
		
	@Qualifier 
	private TdsTaricVarukodService tdsTaricVarukodService;
	@Autowired
	@Required	
	public void setTdsTaricVarukodService(TdsTaricVarukodService value){this.tdsTaricVarukodService = value;}
	public TdsTaricVarukodService getTdsTaricVarukodService(){ return this.tdsTaricVarukodService; }
	
	@Qualifier ("tdsImportSpecificTopicItemService")
	private TdsImportSpecificTopicItemService tdsImportSpecificTopicItemService;
	@Autowired
	@Required
	public void setTdsImportSpecificTopicItemService (TdsImportSpecificTopicItemService value){ this.tdsImportSpecificTopicItemService = value; }
	public TdsImportSpecificTopicItemService getTdsImportSpecificTopicItemService(){ return this.tdsImportSpecificTopicItemService; }
	 
	@Qualifier ("tdsImportDropDownListPopulationService")
	private DropDownListPopulationService tdsImportDropDownListPopulationService;
	@Autowired
	public void setTdsImportDropDownListPopulationService (DropDownListPopulationService value){ this.tdsImportDropDownListPopulationService=value; }
	public DropDownListPopulationService getTdsImportDropDownListPopulationService(){return this.tdsImportDropDownListPopulationService;}
	 
	@Qualifier ("tdsDropDownListPopulationService")
	private TdsDropDownListPopulationService tdsDropDownListPopulationService;
	@Autowired
	public void setTdsDropDownPopulationService (TdsDropDownListPopulationService value){ this.tdsDropDownListPopulationService=value; }
	public TdsDropDownListPopulationService getTdsDropDownListPopulationService(){return this.tdsDropDownListPopulationService;}
	 
	@Qualifier 
	private TdsTillaggskoderService tdsTillaggskoderService;
	@Autowired
	@Required	
	public void setTdsTillaggskoderService(TdsTillaggskoderService value){this.tdsTillaggskoderService = value;}
	public TdsTillaggskoderService getTdsTillaggskoderService(){ return this.tdsTillaggskoderService; }
	
	@Qualifier 
	private TdsBilagdaHandlingarYKoderService tdsBilagdaHandlingarYKoderService;
	@Autowired
	@Required	
	public void setTdsBilagdaHandlingarYKoderService(TdsBilagdaHandlingarYKoderService value){this.tdsBilagdaHandlingarYKoderService = value;}
	public TdsBilagdaHandlingarYKoderService getTdsBilagdaHandlingarYKoderService(){ return this.tdsBilagdaHandlingarYKoderService; }
	
	
	 
}

