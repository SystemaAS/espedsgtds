	package no.systema.tds.tdsexport.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
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
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.util.StringManager;

import no.systema.tds.tdsexport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicFaktTotalRecord;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicRecord;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemRecord;

import no.systema.tds.tdsexport.service.TdsExportSpecificTopicItemService;
import no.systema.tds.tdsexport.service.html.dropdown.DropDownListPopulationService;
import no.systema.tds.tdsexport.url.store.TdsExportUrlDataStore;
import no.systema.tds.tdsexport.util.RpgReturnResponseHandler;
import no.systema.tds.tdsexport.util.TdsExportCalculator;
import no.systema.tds.tdsexport.util.manager.TdsExportItemsAutoControlMgr;
import no.systema.tds.tdsexport.util.manager.TdsExportSumDiffCalculatorMgr;
import no.systema.tds.tdsexport.validator.TdsExportItemsValidator;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemRecord;
import no.systema.tds.tdsexport.model.KundensVaruRegisterUpdateItemRecord;

//application imports
import no.systema.tds.util.TdsConstants;
import no.systema.tds.service.TdsBilagdaHandlingarYKoderService;
import no.systema.tds.service.TdsTaricVarukodService;
import no.systema.tds.service.TdsTillaggskoderService;

import no.systema.tds.util.manager.CodeDropDownMgr;
import no.systema.tds.service.html.dropdown.TdsDropDownListPopulationService;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTaricVarukodContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTaricVarukodRecord;
import no.systema.tds.model.jsonjackson.validation.JsonTdsMangdEnhetContainer;
import no.systema.tds.model.jsonjackson.validation.JsonTdsMangdEnhetRecord;
import no.systema.tds.url.store.TdsUrlDataStore;

//models




/**
 * TDS Export create items gateway
 * 
 * @author oscardelatorre
 *
 */

@Controller
//@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TdsExportItemsController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	private static final Logger logger = Logger.getLogger(TdsExportItemsController.class.getName());
	private final StringManager strMgr = new StringManager();
	private final NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private TdsExportCalculator tdsExportCalculator = new TdsExportCalculator();
	
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private final String MATCH = "MATCH";

	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        //binder.setValidator(new TdsExportItemsValidator());
    }
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsexport_edit_items.do")
	public ModelAndView tdsExportEditItem(@ModelAttribute ("record") JsonTdsExportSpecificTopicItemRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("Inside: tdsExportEditItem");
		ModelAndView successView = new ModelAndView("tdsexport_edit_items");
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonTdsExportSpecificTopicItemRecord jsonTdsExportSpecificTopicItemRecord = null;
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		Map model = new HashMap();
		String urlRequestParamsKeys = null;
		//Catch required action (doFetch or doUpdate)
		String action = request.getParameter("action");
		logger.info("ACTION: " + action);
		
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TDS_EXPORT);
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
			JsonTdsExportSpecificTopicRecord headerRecord = (JsonTdsExportSpecificTopicRecord)session.getAttribute(TdsConstants.DOMAIN_RECORD_TOPIC);
			
			//this key is only used with a real Update. When empty it will be a signal for a CREATE NEW (Add)
			String lineNr = request.getParameter("svev_syli");
			if(lineNr!=null && !"".equals(lineNr)){
				//nothing
			}else{
				//this branch is necessary in order to get the line Nr after a validation error (ref. below att bindingResult.hasErrors in this same method)
				lineNr = (String)session.getAttribute("svev_syli_SESSION");
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
				String varukodValidNumber = this.getTaricVarukod(appUser.getUser(), recordToValidate.getSvev_vata());
				if(!this.MATCH.equals(varukodValidNumber)){
					//REMOVED - DHL req. 1.Jun.2017
					//recordToValidate.setSvev_vata(null); 
					recordToValidate.setValidNumberVata(false);
				}
				//set MangdEnhet Directives if applicable
				this.getMandatoryMangdEnhetDirective(appUser.getUser(), recordToValidate, headerRecord);
				
				TdsExportItemsValidator validator = new TdsExportItemsValidator();
				logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
			    validator.validate(headerRecord, recordToValidate, bindingResult);
			    //check for ERRORS
				if(bindingResult.hasErrors()){
					isValidCreatedRecordTransactionOnRPG = false;
			    	logger.info("[ERROR Validation] Item Record does not validate)");
			    	logger.info("[INFO lineNr] " + lineNr);
			    	model.put("sign", sign);
			    	model.put("record", recordToValidate);
			    	if(lineNr!=null && !"".equals(lineNr)){
			    		logger.info("[INFO lineNr] ... filling old value: lineNr:" + lineNr);
			    		session.setAttribute("svev_syli_SESSION", lineNr);
				    	recordToValidate.setSvev_syop(opd);
			    		recordToValidate.setSvev_syav(avd);
			    		
			    	}
			    	
			    }else{
					if(lineNr!=null && !"".equals(lineNr)){
						//clean
						session.removeAttribute("svev_syli_SESSION");
				    	
						//-------
						//UPDATE
						//-------
						logger.info("UPDATE(only) ITEM (existent item) on process...");
						//take the rest from GUI.
						jsonTdsExportSpecificTopicItemRecord = new JsonTdsExportSpecificTopicItemRecord();
						ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonTdsExportSpecificTopicItemRecord);
			            //binder.registerCustomEditor(...); // if needed
			            binder.bind(request);
			            
			            jsonTdsExportSpecificTopicItemRecord.setSvev_syli(lineNr);
			            jsonTdsExportSpecificTopicItemRecord.setSvev_vano(lineNr);
			            jsonTdsExportSpecificTopicItemRecord.setSvev_syop(opd);
			            jsonTdsExportSpecificTopicItemRecord.setSvev_syav(avd);
			            //Extra Mängd completion after validation
			            jsonTdsExportSpecificTopicItemRecord.setSvev_ankv(recordToValidate.getSvev_ankv());
			            
					}else{
						//-------
						//CREATE
						//-------
						//clean
						session.removeAttribute("svev_syli_SESSION");
				    	
						logger.info("CREATE and UPDATE on ITEM (new fresh item) on process...");
						//This means that the update will be done AFTER a creation of an empty record. All this in the same transaction. 2 STEPS involved: (1)create and (2)update
						//-----------------------------------------------------------------------------------------
						//STEP[1] Generate new Item line key seeds (avd,opd,syli) by creating an empty new record. 
						//		  This step is ONLY applicable for new item lines 
						//-------------------------------------------------------------------------------------------
						jsonTdsExportSpecificTopicItemRecord  = this.createNewItemKeySeeds(session, request, appUser);
						if(jsonTdsExportSpecificTopicItemRecord!=null){
							String newLineNr = jsonTdsExportSpecificTopicItemRecord.getSvev_syli();
							
							//take the rest from GUI.
							jsonTdsExportSpecificTopicItemRecord = new JsonTdsExportSpecificTopicItemRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonTdsExportSpecificTopicItemRecord);
				            //binder.registerCustomEditor(...); // if needed
				            binder.bind(request);
				            
				            //put back the generated seed and the header keys (syop, syavd)
				            jsonTdsExportSpecificTopicItemRecord.setSvev_syli(newLineNr);
				            jsonTdsExportSpecificTopicItemRecord.setSvev_vano(newLineNr);
				            jsonTdsExportSpecificTopicItemRecord.setSvev_syop(opd);
				            jsonTdsExportSpecificTopicItemRecord.setSvev_syav(avd);
				            //Extra Mängd completion after validation
				            jsonTdsExportSpecificTopicItemRecord.setSvev_ankv(recordToValidate.getSvev_ankv());
				            
						}else{
							isValidCreatedRecordTransactionOnRPG = false;
						}
					}
					//--------------------------------------------------
					//At this point we are ready to do an update
					//--------------------------------------------------
					if(isValidCreatedRecordTransactionOnRPG){
						
						//Last adjustment of some fields
						this.adjustFields( jsonTdsExportSpecificTopicItemRecord);
						
						//Extra check on [Invoice Due Amount (svev_fabl)] vs [Statistiskt varde (svev_stva)]
			            //if svev_stva = null then copy the svev_fabl (fakturabelopp)
			            this.updateStatisticalValue(jsonTdsExportSpecificTopicItemRecord);
			            this.updateTullvardeValue(jsonTdsExportSpecificTopicItemRecord);
			            
			            logger.info("[INFO] Valid STEP[1] Add Record successfully created, OK ");
						//---------------------------
						//get BASE URL = RPG-PROGRAM
			            //---------------------------
						String BASE_URL_UPDATE = TdsExportUrlDataStore.TDS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
						urlRequestParamsKeys = this.getRequestUrlKeyParametersUpdate(jsonTdsExportSpecificTopicItemRecord.getSvev_syli(), avd, opd, appUser, TdsConstants.MODE_UPDATE);
						String urlRequestParamsTopicItem = this.urlRequestParameterMapper.getUrlParameterValidString((jsonTdsExportSpecificTopicItemRecord));
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
				    		this.setFatalError(model, rpgReturnResponseHandler, jsonTdsExportSpecificTopicItemRecord);
					    		
				    	}else{
				    		//Update succefully done!
				    		logger.info("[INFO] Valid STEP[2] Update -- Record successfully updated, OK ");
				    		//put domain objects (it is not necessary since the fetch below (onFetch) will clean this up anyway)
				    		//this.setDomainObjectsInView(model, jsonTdsExportSpecificTopicItemRecord);
				    	}
					}else{
						rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tuid, syop generation : " + rpgReturnResponseHandler.getErrorMessage());
						this.setFatalError(model, rpgReturnResponseHandler, jsonTdsExportSpecificTopicItemRecord);
					}
			    }
				
			}else if(TdsConstants.ACTION_DELETE.equals(action)){
				logger.info("[INFO] Delete record start process... ");
				String lineNrToDelete = request.getParameter("lin");
				
				//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				String BASE_URL_DELETE = TdsExportUrlDataStore.TDS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
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
		    		this.setFatalError(model, rpgReturnResponseHandler, jsonTdsExportSpecificTopicItemRecord);
			    		
		    	}else{
		    		//Delete succefully done!
		    		logger.info("[INFO] Valid Delete -- Record successfully deleted, OK ");
		    	}
			}
	    
			//FETCH the ITEM LIST of existent ITEMs for this TOPIC
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL_FETCH = TdsExportUrlDataStore.TDS_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
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
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL_FETCH + "==>params: " + urlRequestParamsKeys.toString() + " " + "(fetched item list):" + jsonPayloadFetch);   
			//Debug --> 
			logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayloadFetch));
			
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	
			JsonTdsExportSpecificTopicItemContainer jsonTdsExportSpecificTopicItemContainer = this.getTdsExportSpecificTopicItemService().getTdsExportSpecificTopicItemContainer(jsonPayloadFetch);
	    	if(jsonTdsExportSpecificTopicItemContainer!=null){
	    		Double calculatedItemLinesTotalAmount = this.tdsExportCalculator.getItemLinesTotalAmount(jsonTdsExportSpecificTopicItemContainer);
	    		Double diffItemLinesTotalAmountWithInvoiceTotalAmount = this.tdsExportCalculator.getDiffBetweenCalculatedTotalAmountAndTotalAmount(invoiceTotalAmount, calculatedItemLinesTotalAmount);
	    		jsonTdsExportSpecificTopicItemContainer.setCalculatedItemLinesTotalAmount(calculatedItemLinesTotalAmount);
	    		jsonTdsExportSpecificTopicItemContainer.setDiffItemLinesTotalAmountWithInvoiceTotalAmount(diffItemLinesTotalAmountWithInvoiceTotalAmount);
	    		
	    		//New elements on matrix
	    		//Antal and diff with total antal on header(sveh_kota)
	    		JsonTdsExportSpecificTopicRecord sumTopicRecord = this.tdsExportSumDiffCalculatorMgr.getSumOfSpecificFieldsInItemLines(avd, opd, appUser);
				jsonTdsExportSpecificTopicItemContainer.setCalculatedItemLinesTotalKolli(sumTopicRecord.getSumOfAntalKolliInItemLines());
				if(StringUtils.isNotEmpty(headerRecord.getSveh_kota())){
					jsonTdsExportSpecificTopicItemContainer.setDiffItemLinesTotalKolliWithInvoiceTotalKolli(Integer.parseInt(headerRecord.getSveh_kota()) - sumTopicRecord.getSumOfAntalKolliInItemLines() );
				}else{
					jsonTdsExportSpecificTopicItemContainer.setDiffItemLinesTotalKolliWithInvoiceTotalKolli(0 - sumTopicRecord.getSumOfAntalKolliInItemLines() );
				}
				
				//Gross weight and diff with total Gross weight on header(sveh_brut)
				jsonTdsExportSpecificTopicItemContainer.setCalculatedItemLinesTotalGrossWeight(sumTopicRecord.getSumOfGrossWeightInItemLines());
				if(StringUtils.isNotEmpty(headerRecord.getSveh_brut())){
					Double diffGrossWeight = headerRecord.getSveh_brut_dbl() - sumTopicRecord.getSumOfGrossWeightInItemLines();
					jsonTdsExportSpecificTopicItemContainer.setDiffItemLinesTotalGrossWeightWithInvoiceTotalGrossWeight(diffGrossWeight);
				}else{
					Double diffGrossWeight = 0.00D - sumTopicRecord.getSumOfGrossWeightInItemLines();
					jsonTdsExportSpecificTopicItemContainer.setDiffItemLinesTotalGrossWeightWithInvoiceTotalGrossWeight(diffGrossWeight);
				}
	    	}
	    	
	    	//general code population
		    this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","KLI");
		    this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","THO");
		    this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","GCY");
    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","MDX");
    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"A","MCF");
    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"E","SAL");
    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"E","FF1");
    		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService, model,appUser,"E","FFK");
    		
    		//Default values for a new line
    		if(isValidCreatedRecordTransactionOnRPG){
    			jsonTdsExportSpecificTopicItemRecord = new JsonTdsExportSpecificTopicItemRecord();
    			this.setBruttoViktRestValue(jsonTdsExportSpecificTopicItemRecord, model, jsonTdsExportSpecificTopicItemContainer, headerRecord);
    			this.setAntalKolliRestValue(jsonTdsExportSpecificTopicItemRecord, model, jsonTdsExportSpecificTopicItemContainer, headerRecord);
    			this.setDomainObjectsInView(model, jsonTdsExportSpecificTopicItemRecord);
    		}
    		//list of items
    		this.setDomainObjectsForListInView(session, model, jsonTdsExportSpecificTopicItemContainer);
			
    		successView.addObject("model",model);
    		//successView.addObject(Constants.EDIT_ACTION_ON_TOPIC, Constants.ACTION_FETCH);
	    	return successView;
		}
	}
	
	/**
	 * Used for autofortollning - control av every line
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsexport_edit_items_autocontrol.do")
	public ModelAndView tdsExportEditItemAutoControl(@ModelAttribute ("record") JsonTdsExportSpecificTopicItemRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("Inside: tdsExportEditItemAutoControl");
		final String AUTO_CONTROL_ERROR_FLAG_VALUE = "X";
		final String ERROR_FRAME_STD_OUTPUT = "-------------------------------------------------------------";
		
		ModelAndView successView = null;
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonTdsExportSpecificTopicItemRecord jsonTdsExportSpecificTopicItemRecord = null;
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//this fragment gets some header fields needed for the validator
		JsonTdsExportSpecificTopicRecord headerRecord = (JsonTdsExportSpecificTopicRecord)session.getAttribute(TdsConstants.DOMAIN_RECORD_TOPIC);
		Map model = new HashMap();
		String urlRequestParamsKeys = null;
		//
		String fabl = request.getParameter("fablAutoControl");
		String sign = request.getParameter("sign");
		
		
		if(appUser==null){
			return this.loginView;
		}else{
			
			//STEP [1] check if you must clear all statistical values (upon user choice in modal dialog...)
			if(strMgr.isNotNull(recordToValidate.getStatValueNullIt())){
				this.clearAllStatisticalValuesBeforeVarupostcontrol(appUser, recordToValidate);
			}
			
			//STEP [2] now we go on with the usual autokontroll routine
			StringBuffer params = new StringBuffer();
			params.append("user=" + appUser.getUser() + "&avd=" + recordToValidate.getSvev_syav() + "&sign=" + sign + "&opd=" + recordToValidate.getSvev_syop() + "&fabl=" + fabl);
			successView = new ModelAndView("redirect:tdsexport_edit_items.do?" + params);
			//FETCH the ITEM LIST of existent ITEMs for this TOPIC
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL_FETCH = TdsExportUrlDataStore.TDS_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(request, recordToValidate.getSvev_syav(), recordToValidate.getSvev_syop(), appUser);
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("FETCH item list... ");
	    	logger.info("URL: " + BASE_URL_FETCH);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	logger.info(headerRecord.getSveh_fabl());
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
	    	JsonTdsExportSpecificTopicItemContainer container = this.tdsExportSpecificTopicItemService.getTdsExportSpecificTopicItemContainer(jsonPayloadFetch);
	    	
	    	if(container!=null){
	    		
	    		//INIT Autocontrol Manager...
	    		TdsExportItemsAutoControlMgr autoControlMgr = new TdsExportItemsAutoControlMgr(this.urlCgiProxyService, this.tdsExportSpecificTopicItemService);
	    		//Iterate on every line to control the requirements
	    		for(JsonTdsExportSpecificTopicItemRecord record: container.getOrderList()){
	    			//Init record
	    			autoControlMgr.setRecord(record);
	    			autoControlMgr.setHeaderRecord(headerRecord);
	    			//Begin with the validity checks
	    			String idDebug = record.getSvev_syli() + "-" + record.getSvev_vata();
	    			//----------------------------------------------
	    			//Check varukod and stop everything if invalid
	    			//----------------------------------------------
	    			String varukodValidNumber = this.getTaricVarukod(appUser.getUser(), record.getSvev_vata());
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
		    			autoControlMgr.calculateStatisticalValuesOnItem(headerRecord, appUser.getUser());
		    			autoControlMgr.checkValidTillaggsKoder(this.tdsTillaggskoderService, appUser.getUser());
		    			//Update (back-end) the record after the above calculations
		    			autoControlMgr.updateItemRecord(appUser.getUser());
						//---------------------------
		    			//START with validations now
		    			//---------------------------
						//Go to level 1
						//logger.info("level check (1) " + idDebug);
						autoControlMgr.checkValidGrossAndNetWeight();
						if(autoControlMgr.isValidRecord()){
							//Go to level 2
							//logger.info("level check (2) " + idDebug);
	    					autoControlMgr.checkValidBilagdaHandlingar(this.tdsBilagdaHandlingarYKoderService, appUser.getUser());
	    					if(autoControlMgr.isValidRecord()){
	    						//Go to level 3
	    						//logger.info("level check (3) " + idDebug);
	    						autoControlMgr.getMandatoryMangdEnhetDirective(appUser.getUser(), headerRecord);
								autoControlMgr.checkValidExtraMangdEnhet(appUser.getUser());
								if(autoControlMgr.isValidRecord()){
		    						//Go to level FINAL MandatoryFields (must be the last check)
		    						//Nothing more below this level. New requirements must be insert between previous level and this FINAL level!
		    						autoControlMgr.checkValidMandatoryFields();
		    						if(autoControlMgr.isValidRecord()){
		    		    				//Update in order to remove previous error flags, if any...
		    		    				autoControlMgr.updateItemWithAutoControlError(appUser.getUser(), null );
		    		    				//Update with Extramangd
		    		    				autoControlMgr.updateItemWithExgraMangdEnhet(appUser.getUser());
		    		    				
		    		    			}else{
		    		    				//Set error
		    		    				logger.info(ERROR_FRAME_STD_OUTPUT);
			    						logger.info("ERROR level (FINAL) - Mandatory Fields:" + idDebug);
			    						logger.info(ERROR_FRAME_STD_OUTPUT);
			    						autoControlMgr.updateItemWithAutoControlError(appUser.getUser(), AUTO_CONTROL_ERROR_FLAG_VALUE );
		    		    			}
								}else{
	    		    				//Set error
	    		    				logger.info(ERROR_FRAME_STD_OUTPUT);
		    						logger.info("ERROR level (3) - Extra Mangd Enhet" + idDebug);
		    						logger.info(ERROR_FRAME_STD_OUTPUT);
		    						autoControlMgr.updateItemWithAutoControlError(appUser.getUser(), AUTO_CONTROL_ERROR_FLAG_VALUE );
	    		    			}
	    					}else{
	    						//Set error
	    						logger.info(ERROR_FRAME_STD_OUTPUT);
	    						logger.info("ERROR level (2) - Bilagda handlingar:" + idDebug);
	    						logger.info(ERROR_FRAME_STD_OUTPUT);
	    						autoControlMgr.updateItemWithAutoControlError(appUser.getUser(), AUTO_CONTROL_ERROR_FLAG_VALUE);
	    					}
						}else{
							//Set error
							logger.info(ERROR_FRAME_STD_OUTPUT);
							logger.info("ERROR level (1) - Weights:" + idDebug);
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
	 * @param record
	 */
	private void clearAllStatisticalValuesBeforeVarupostcontrol(SystemaWebUser appUser, JsonTdsExportSpecificTopicItemRecord recordToValidate){
		
		//---------------------------
		//get BASE URL = RPG-PROGRAM
	    //---------------------------
		String BASE_URL_FETCH = TdsExportUrlDataStore.TDS_EXPORT_BASE_STATISTICAL_VALUES_CLEAR_TO_NULL_URL;
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser() + "&avd=" + recordToValidate.getSvev_syav() + "&opd=" + recordToValidate.getSvev_syop());
		
		//--------------------------------------
		//EXECUTE (RPG program) here
		//--------------------------------------
		String jsonPayloadFetch = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys.toString());
		
		//Debug --> 
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayloadFetch));
		
		//we should implement a boolean return in case of error but today is Friday and we assume that this call will never fail
		//...TODO
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tdsexport_edit_items_doUpdateKundensVaruregister.do")
	public ModelAndView tdsExportEditItemUpdateKundensVaruregister(@ModelAttribute ("record") KundensVaruRegisterUpdateItemRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("Inside: tdsExportEditItemUpdateKundensVaruregister");
		
		ModelAndView successView = null;
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//this fragment gets some header fields needed for the validator
		JsonTdsExportSpecificTopicRecord headerRecord = (JsonTdsExportSpecificTopicRecord)session.getAttribute(TdsConstants.DOMAIN_RECORD_TOPIC);
		Map model = new HashMap();
		
		
		if(appUser==null){
			return this.loginView;
		}else{
			StringBuffer params = new StringBuffer();
			params.append("user=" + appUser.getUser() + "&avd=" + headerRecord.getSveh_syav() + "&opd=" + headerRecord.getSveh_syop());
			successView = new ModelAndView("redirect:tdsexport_edit_items.do?" + params);
			//UDPATE kundensvaruregister
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_UPDATE_TULLTAXA_KUNDENSVAREREG_VARUKODER_ITEMS_URL;
			String urlRequestParamsTopicItem = "user=" + appUser.getUser() + this.urlRequestParameterMapper.getUrlParameterValidString((recordToValidate));
			
			logger.info("URL:" + BASE_URL );
			logger.info("PARAMS:" + urlRequestParamsTopicItem);
			String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsTopicItem);
			
			logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
	    	//we must evaluate a return RPG code in order to know if the Update was OK or not
	    	rpgReturnResponseHandler.evaluateRpgResponseOnItemKundensVarRegisterUpdate(rpgReturnPayload);
	    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
	    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
	    		this.setFatalError(model, rpgReturnResponseHandler, new JsonTdsExportSpecificTopicItemRecord());
	    	}else{
	    		//Delete succefully done!
	    		logger.info("[INFO] Valid Insert -- Record successfully inserted, OK ");
	    	}
			
		}
		return successView;
	}
	
	/**
	 * Checks and updates the statistical value (which is mandatory.. but not in main section) when needed.
	 * 
	 * @param record
	 */
	private void updateStatisticalValue(JsonTdsExportSpecificTopicItemRecord record){
		String statisticalValue = record.getSvev_stva();
		
		if(statisticalValue!=null && !"".equals(statisticalValue)){
			//Nothing. The user has a value which might be different than the total invoice due value.
		}else{
			//copy invoice amount to the statistical value
			record.setSvev_stva(record.getSvev_fabl());
		}
		
	}
	
	/**
	 * Checks and updates the tullvarde value when needed.
	 * @param record
	 */
	private void updateTullvardeValue(JsonTdsExportSpecificTopicItemRecord record){
		String tullvardeValue = record.getSvev_stva2();
		
		if(tullvardeValue!=null && !"".equals(tullvardeValue)){
			//Nothing. The user has a value which might be different than the total invoice due value.
		}else{
			//copy invoice amount to the statistical value
			record.setSvev_stva2(record.getSvev_fabl());
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
		
		String TDS_IMPORT_IE = "E";//always E 
		try{
		  String BASE_URL = TdsUrlDataStore.TDS_FETCH_TARIC_VARUKODER_ITEMS_URL;
		  String urlRequestParamsKeys = "user=" + applicationUser + "&ie=" + TDS_IMPORT_IE + "&kod=" + taricVarukod;
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
		errorMetaInformation.append(rpgReturnResponseHandler.getSvev_syop());
		model.put(TdsConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
	/**
	 * Sets domain objects
	 * @param model
	 * @param jsonTdsExportSpecificTopicItemContainer
	 */
	private void setDomainObjectsForListInView(HttpSession session, Map model, JsonTdsExportSpecificTopicItemContainer container){
		List list = new ArrayList();
		if(container!=null){
			for (JsonTdsExportSpecificTopicItemRecord record : container.getOrderList()){
				list.add(record);
				//logger.info(record.getSvev_vasl());
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
	 * @param jsonTdsExportSpecificTopicItemRecord
	 */
	private void setDomainObjectsInView(Map model, JsonTdsExportSpecificTopicItemRecord jsonTdsExportSpecificTopicItemRecord){
		model.put(TdsConstants.DOMAIN_RECORD, jsonTdsExportSpecificTopicItemRecord);
	}
	
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param jsonTdsExportSpecificTopicItemRecord
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonTdsExportSpecificTopicItemRecord jsonTdsExportSpecificTopicItemRecord){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, jsonTdsExportSpecificTopicItemRecord);
	}
	
	/**
	 * 
	 * Creates the record (Add) for a later update in the same transaction
	 * @param session
	 * @param request
	 * @param appUser
	 * @return
	 */
	private JsonTdsExportSpecificTopicItemRecord createNewItemKeySeeds(HttpSession session, HttpServletRequest request, SystemaWebUser appUser){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		//request variables
		String numberOfItemLinesInTopicStr = request.getParameter("numberOfItemLinesInTopic");
		if(numberOfItemLinesInTopicStr==null || "".equals(numberOfItemLinesInTopicStr)){
			numberOfItemLinesInTopicStr = "0";
		}
			
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		
		JsonTdsExportSpecificTopicItemRecord jsonTdsExportSpecificTopicItemRecord = new JsonTdsExportSpecificTopicItemRecord();
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = TdsExportUrlDataStore.TDS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
		
		//-------------------------------------------------------------------------------------------
		// STEP[PREPARE CREATION] --> generate new opd and tuid (if applicable) in order to be able to Add (Create)
		//-------------------------------------------------------------------------------------------
		logger.info("STEP[1] GET SEEDS and CREATE RECORD...");
		StringBuffer urlRequestParamsForSeed = new StringBuffer();
		urlRequestParamsForSeed.append("user=" + appUser.getUser());
		urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		Integer numberOfItemLinesInTopic = -99;
		try{
			numberOfItemLinesInTopic = Integer.valueOf(numberOfItemLinesInTopicStr);
			//add one
			numberOfItemLinesInTopic++;
			logger.info("New item line nr: " + numberOfItemLinesInTopic);
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
			jsonTdsExportSpecificTopicItemRecord = null;
			
		}else{
			jsonTdsExportSpecificTopicItemRecord.setSvev_syop(rpgReturnResponseHandler.getSvev_syop());
			jsonTdsExportSpecificTopicItemRecord.setSvev_syli(rpgReturnResponseHandler.getSvev_syli());
		}
        
		return jsonTdsExportSpecificTopicItemRecord;
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
	
	/**
	 * 
	 * @param applicationUser
	 * @param recordToValidate
	 * @param headerRecord
	 */
	public void getMandatoryMangdEnhetDirective(String applicationUser, JsonTdsExportSpecificTopicItemRecord recordToValidate, JsonTdsExportSpecificTopicRecord headerRecord){
		String TDS_IE = "E";
		
		String BASE_URL_FETCH = TdsUrlDataStore.TDS_CHECK_EXTRA_MANGDENHET;
		
		//Changed 03.jan.2017--> DHL discover this error: String urlRequestParamsKeys = "user="+ applicationUser + "&ie=" + TDS_IE + "&kod=" + recordToValidate.getSvev_vata() + "&lk=" + recordToValidate.getSvev_ulkd();
		String urlRequestParamsKeys = "user="+ applicationUser + "&ie=" + TDS_IE + "&kod=" + recordToValidate.getSvev_vata() + "&lk=" + headerRecord.getSveh_aube();

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.warn("FETCH av mangdenhet... ");
    	logger.warn("URL: " + BASE_URL_FETCH);
    	logger.warn("URL PARAMS: " + urlRequestParamsKeys);
    	//-----------------
    	//Json and execute 
    	//-----------------
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		JsonTdsMangdEnhetContainer container = this.tdsExportSpecificTopicItemService.getTdsMangdEnhetContainer(jsonPayload);
		for(JsonTdsMangdEnhetRecord record: container.getXtramangdenhet()){
			logger.warn("A");
			if(record.getXtra()!=null && record.getXtra().toUpperCase().equals("Y")){
				logger.warn("B -> Xtramangdenhet:" + record.getXtra());
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
					if(strMgr.isNotNull(recordToValidate.getSvev_ankv())){
					 //Nothing	
					}else{
						if(strMgr.isNotNull(recordToValidate.getSvev_kota()) && !"0".equals(recordToValidate.getSvev_kota()) ){
							recordToValidate.setSvev_ankv(recordToValidate.getSvev_kota());
							//logger.info("YES!!!:" + recordToValidate.getSvev_ankv());
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
	 * @param recordToValidate
	 */
	private void adjustFields(JsonTdsExportSpecificTopicItemRecord recordToValidate){
		//Bruttovikt
		recordToValidate.setSvev_brut(numberFormatter.formatBigDecimal(3, recordToValidate.getSvev_brut()));
		//Nettovikt
		recordToValidate.setSvev_neto(numberFormatter.formatBigDecimal(3, recordToValidate.getSvev_neto()));
				
		//Godsmärkning
		if(strMgr.isNotNull(recordToValidate.getSvev_godm())){
			recordToValidate.setSvev_godm(recordToValidate.getSvev_godm().toUpperCase());
		}
		//Godsmärkning - Extraordinära uppg
		if(strMgr.isNotNull(recordToValidate.getSvev_god2())){
			recordToValidate.setSvev_god2(recordToValidate.getSvev_god2().toUpperCase());
		}
		if(strMgr.isNotNull(recordToValidate.getSvev_god3())){
			recordToValidate.setSvev_god3(recordToValidate.getSvev_god3().toUpperCase());
		}
		if(strMgr.isNotNull(recordToValidate.getSvev_god4())){
			recordToValidate.setSvev_god4(recordToValidate.getSvev_god4().toUpperCase());
		}
		if(strMgr.isNotNull(recordToValidate.getSvev_god5())){
			recordToValidate.setSvev_god5(recordToValidate.getSvev_god5().toUpperCase());
		}
		
	}
	
	/**
	 * 
	 * @param model
	 * @param container
	 * @param headerRecord
	 */
	private void setBruttoViktRestValue(JsonTdsExportSpecificTopicItemRecord jsonTdsExportSpecificTopicItemRecord, Map model, JsonTdsExportSpecificTopicItemContainer container, JsonTdsExportSpecificTopicRecord headerRecord){
		if(container!=null){
			try{
				Double sumBrut = 0.00D;
				for (JsonTdsExportSpecificTopicItemRecord record : container.getOrderList()){
					if(strMgr.isNotNull(record.getSvev_brut()) ){
						sumBrut += Double.parseDouble(record.getSvev_brut().replace(",", "."));
					}
				}
				Double headerBrutVal = headerRecord.getSveh_brut_dbl();
				Double result = headerBrutVal - sumBrut;
				String resultStr = String.valueOf(result);
				jsonTdsExportSpecificTopicItemRecord.setSvev_brut(resultStr.replace(".", ","));
				logger.info("final rest value:" + jsonTdsExportSpecificTopicItemRecord.getSvev_brut());
			}catch(Exception e){
				logger.info("ERROR on default Gross weight:" + e.toString());
			}
		}
		
		
	}
	/**
	 * 
	 * @param jsonTdsExportSpecificTopicItemRecord
	 * @param model
	 * @param container
	 * @param headerRecord
	 */
	private void setAntalKolliRestValue(JsonTdsExportSpecificTopicItemRecord jsonTdsExportSpecificTopicItemRecord, Map model, JsonTdsExportSpecificTopicItemContainer container, JsonTdsExportSpecificTopicRecord headerRecord){
		if(container!=null){
			try{
				Integer sumKolli = 0;
				for (JsonTdsExportSpecificTopicItemRecord record : container.getOrderList()){
					if(strMgr.isNotNull(record.getSvev_kota()) ){
						sumKolli += Integer.parseInt(record.getSvev_kota());
					}
				}
				Integer headerSumKolliVal =0;
				if(strMgr.isNotNull(headerRecord.getSveh_kota())){
					headerSumKolliVal = Integer.valueOf(headerRecord.getSveh_kota());
				}
				
				Integer result = headerSumKolliVal - sumKolli;
				String resultStr = String.valueOf(result);
				jsonTdsExportSpecificTopicItemRecord.setSvev_kota(resultStr);
				logger.info("final rest value Kolli:" + jsonTdsExportSpecificTopicItemRecord.getSvev_kota());
			}catch(Exception e){
				logger.info("ERROR on default Antal Kolli:" + e.toString());
			}
		}
		
		
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
	
	
	@Qualifier ("tdsExportSpecificTopicItemService")
	private TdsExportSpecificTopicItemService tdsExportSpecificTopicItemService;
	@Autowired
	@Required
	public void setTdsExportSpecificTopicItemService (TdsExportSpecificTopicItemService value){ this.tdsExportSpecificTopicItemService = value; }
	public TdsExportSpecificTopicItemService getTdsExportSpecificTopicItemService(){ return this.tdsExportSpecificTopicItemService; }
	 
	@Qualifier ("dropDownListPopulationService")
	private DropDownListPopulationService dropDownListPopulationService;
	@Autowired
	public void setDropDownListPopulationService (DropDownListPopulationService value){ this.dropDownListPopulationService=value; }
	public DropDownListPopulationService getDropDownListPopulationService(){return this.dropDownListPopulationService;}
	 
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
	
	@Autowired
	TdsExportSumDiffCalculatorMgr tdsExportSumDiffCalculatorMgr;
}

