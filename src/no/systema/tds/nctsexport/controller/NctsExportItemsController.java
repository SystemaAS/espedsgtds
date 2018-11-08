package no.systema.tds.nctsexport.controller;

import java.util.*;

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


import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.ServletRequestDataBinder;
//import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

//import no.systema.tds.service.MainHdTopicService;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.StringManager;
import no.systema.tds.nctsexport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportSpecificTopicRecord;
import no.systema.tds.nctsexport.model.jsonjackson.topic.items.JsonNctsExportSpecificTopicItemContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.items.JsonNctsExportSpecificTopicItemRecord;

import no.systema.tds.nctsexport.service.NctsExportSpecificTopicItemService;
import no.systema.tds.nctsexport.service.html.dropdown.DropDownListPopulationService;
import no.systema.tds.nctsexport.url.store.UrlDataStore;
import no.systema.tds.nctsexport.util.RpgReturnResponseHandler;
import no.systema.tds.nctsexport.validator.NctsExportItemsValidator;
import no.systema.tds.nctsexport.util.manager.CodeDropDownMgr;
//application imports
import no.systema.tds.util.TdsConstants;
import no.systema.tds.service.html.dropdown.TdsDropDownListPopulationService;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicRecord;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemRecord;


/**
 * TDS Export create items gateway
 * 
 * @author oscardelatorre
 *
 */

@Controller
//@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class NctsExportItemsController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(NctsExportItemsController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private StringManager strMgr = new StringManager();
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        //binder.setValidator(new TdsExportItemsValidator());
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
	@RequestMapping(value="nctsexport_edit_items.do")
	public ModelAndView nctsExportEditItem(@ModelAttribute ("record") JsonNctsExportSpecificTopicItemRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("Inside: nctsExportEditItem");
		ModelAndView successView = new ModelAndView("nctsexport_edit_items");
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonNctsExportSpecificTopicItemRecord jsonNctsExportSpecificTopicItemRecord = null;
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		Map model = new HashMap();
		String urlRequestParamsKeys = null;
		//Catch required action (doFetch or doUpdate)
		String action = request.getParameter("action");
		logger.info("ACTION: " + action);
		
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_NCTS_EXPORT);
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, TdsConstants.ACTIVE_URL_RPG_INITVALUE); 
			
			boolean isValidCreatedRecordTransactionOnRPG = true;
			//Keys and header information
			String opd = request.getParameter("opd");
			String avd = request.getParameter("avd");
			String sign = request.getParameter("sign");
			String tullId = request.getParameter("tullId");
			String mrnNr = request.getParameter("mrnNr");
			String status = request.getParameter("status");
			String datum = request.getParameter("datum");
			//this fragment gets some header fields needed for the validator
			JsonNctsExportSpecificTopicRecord headerRecord = (JsonNctsExportSpecificTopicRecord)session.getAttribute(TdsConstants.DOMAIN_RECORD_TOPIC);
			
			//this key is only used with a real Update. When empty it will be a signal for a CREATE NEW (Add)
			String lineNr = request.getParameter("tvli");
			if(lineNr!=null && !"".equals(lineNr)){
				//nothing
			}else{
				//this branch is necessary in order to get the line Nr after a validation error (ref. below att bindingResult.hasErrors in this same method)
				lineNr = (String)session.getAttribute("tvli_SESSION");
			}
			model.put("avd", avd);
			model.put("opd", opd);
			model.put("sign", sign);
			model.put("tullId", tullId);
			model.put("status", status);
			model.put("datum", datum);
			model.put("mrnNr", mrnNr);
			
			
			if(TdsConstants.ACTION_UPDATE.equals(action)){
				//-----------
				//Validation
				//-----------
				NctsExportItemsValidator validator = new NctsExportItemsValidator();
				logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
				//-------------------------------------------------------
				//this is only for validation of gross weight 
				//in first item line (manadatory), this is the only way
				//-------------------------------------------------------
				String numberOfItemLinesInTopicStr = request.getParameter("numberOfItemLinesInTopic");
				if(numberOfItemLinesInTopicStr==null || "".equals(numberOfItemLinesInTopicStr)){
					numberOfItemLinesInTopicStr = "0";
				}
				recordToValidate.setNumberOfItemLinesInTopicStr(numberOfItemLinesInTopicStr);
				//in order to flag an update one-line item
				if(lineNr!=null && !"".equals(lineNr)){
					if("1".equals(numberOfItemLinesInTopicStr)){
						recordToValidate.setNumberOfItemLinesInTopicStr("-99");
					}
				}
				validator.validate(recordToValidate, bindingResult);
			    
				
			    //check for ERRORS
				if(bindingResult.hasErrors()){
					recordToValidate.setTvli(null);
				    	logger.info("[ERROR Validation] Item Record does not validate)");
				    	logger.info("[INFO lineNr] " + lineNr);
				    	
				    	model.put("record", recordToValidate);
				    	if(lineNr!=null && !"".equals(lineNr)){
				    		logger.info("[INFO lineNr] ... filling old value: lineNr:" + lineNr);
				    		session.setAttribute("tvli_SESSION", lineNr);
				    		recordToValidate.setTvli(lineNr);
					    	recordToValidate.setTvtdn(opd);
				    		recordToValidate.setTvavd(avd);
				    	}
			    }else{
					if(lineNr!=null && !"".equals(lineNr)){
						//clean
						session.removeAttribute("tvli_SESSION");
				    	
						//-------
						//UPDATE
						//-------
						logger.info("UPDATE(only) ITEM (existent item) in process...");
						//take the rest from GUI.
						jsonNctsExportSpecificTopicItemRecord = new JsonNctsExportSpecificTopicItemRecord();
						ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonNctsExportSpecificTopicItemRecord);
			            //binder.registerCustomEditor(...); // if needed
			            binder.bind(request);
			            //put back the generated seed and the header keys (avd,opd)
			            jsonNctsExportSpecificTopicItemRecord.setTvavd(avd);
			            jsonNctsExportSpecificTopicItemRecord.setTvtdn(opd);
			            //put back the values of sensitive goods
			            jsonNctsExportSpecificTopicItemRecord.setTvfv(recordToValidate.getTvfv());
			            jsonNctsExportSpecificTopicItemRecord.setTvfvnt(recordToValidate.getTvfvnt());
			            
			            logger.info("[DEBUG] UPDATE on Line nr: " + jsonNctsExportSpecificTopicItemRecord.getTvli().trim());
			            logger.info("[DEBUG] UPDATE on Opd: " + jsonNctsExportSpecificTopicItemRecord.getTvtdn());
			            logger.info("[DEBUG] UPDATE on Avd: " + jsonNctsExportSpecificTopicItemRecord.getTvavd());
			            logger.info("[DEBUG] UPDATE on tvtdsk: " + jsonNctsExportSpecificTopicItemRecord.getTvtdsk());
			            logger.info("[DEBUG] UPDATE on tvtdo: " + jsonNctsExportSpecificTopicItemRecord.getTvtdo());
						
					}else{
						//-------
						//CREATE
						//-------
						logger.info("CREATE and UPDATE on ITEM (new fresh item) on process...");
						//This means that the update will be done AFTER a creation of an empty record. All this in the same transaction. 2 STEPS involved: (1)create and (2)update
						//-----------------------------------------------------------------------------------------
						//STEP[1] Generate new Item line key seeds (avd,opd,syli) by creating an empty new record. 
						//		  This step is ONLY applicable for new item lines 
						//-------------------------------------------------------------------------------------------
						jsonNctsExportSpecificTopicItemRecord  = this.createNewItemKeySeeds(session, request, appUser);
						if(jsonNctsExportSpecificTopicItemRecord!=null){
							String newLineNr = jsonNctsExportSpecificTopicItemRecord.getTvli();
							logger.info("[INFO] New LineNr:" + newLineNr);
							//take the rest from GUI.
							jsonNctsExportSpecificTopicItemRecord = new JsonNctsExportSpecificTopicItemRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonNctsExportSpecificTopicItemRecord);
				            //binder.registerCustomEditor(...); // if needed
				            binder.bind(request);
				            
				            jsonNctsExportSpecificTopicItemRecord.setTvli(newLineNr);
				            //put back the generated seed and the header keys (avd,opd)
				            jsonNctsExportSpecificTopicItemRecord.setTvtdn(opd);
				            jsonNctsExportSpecificTopicItemRecord.setTvavd(avd);
				            logger.info("[INFO] Varubeskrivning (on item record):" + jsonNctsExportSpecificTopicItemRecord.getTvvt());
				            logger.info("[INFO] New line number (on item record):" + jsonNctsExportSpecificTopicItemRecord.getTvli());
				            //put back the values of sensitive goods
				            jsonNctsExportSpecificTopicItemRecord.setTvfv(recordToValidate.getTvfv());
				            jsonNctsExportSpecificTopicItemRecord.setTvfvnt(recordToValidate.getTvfvnt());
				            
						}else{
							isValidCreatedRecordTransactionOnRPG = false;
						}
					}
					//--------------------------------------------------
					//At this point we are ready to do an update
					//--------------------------------------------------
					if(isValidCreatedRecordTransactionOnRPG){
						
						//Last adjustment of some fields
						this.adjustFields( jsonNctsExportSpecificTopicItemRecord);
						
			            logger.info("[INFO] Valid previous step successfully processed, OK ");
			            logger.info("[INFO] Ready to move forward to do the UPDATE");
			            
						//---------------------------
						//get BASE URL = RPG-PROGRAM
			            //---------------------------
						String BASE_URL_UPDATE = UrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
						urlRequestParamsKeys = this.getRequestUrlKeyParametersUpdate(jsonNctsExportSpecificTopicItemRecord, appUser, TdsConstants.MODE_UPDATE);
						String urlRequestParamsTopicItem = this.urlRequestParameterMapper.getUrlParameterValidString((jsonNctsExportSpecificTopicItemRecord));
						//put the final valid param. string
						String urlRequestParams = urlRequestParamsKeys + urlRequestParamsTopicItem;
						//for debug purposes in GUI
						session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL_UPDATE + "==>params: " + urlRequestParams.toString()); 
						
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
					    		this.setFatalError(model, rpgReturnResponseHandler, jsonNctsExportSpecificTopicItemRecord);
					    		
					    	}else{
					    		//Update succefully done!
					    		logger.info("[INFO] Valid STEP[2] Update -- Record successfully updated, OK ");
					    		//---------------------------
					    		//REFRESH ON SOME VARIABLES
					    		//---------------------------
					    		//Update some variables on header such as (1)Number of item lines, (2)Kolliantal and (3)Gross weight-Bruttovikt
					    		this.refreshHeaderVariables(appUser.getUser(), avd, opd);
					    	}
					}else{
						rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tuid, syop generation : " + rpgReturnResponseHandler.getErrorMessage());
						this.setFatalError(model, rpgReturnResponseHandler, jsonNctsExportSpecificTopicItemRecord);
					}
			    }
				
			}else if(TdsConstants.ACTION_DELETE.equals(action)){
				logger.info("[INFO] Delete record start process... ");
				String lineNrToDelete = request.getParameter("lin");
				
				//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				String BASE_URL_DELETE = UrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
				jsonNctsExportSpecificTopicItemRecord = new JsonNctsExportSpecificTopicItemRecord();
				jsonNctsExportSpecificTopicItemRecord.setTvli(lineNrToDelete);
				jsonNctsExportSpecificTopicItemRecord.setTvavd(avd);
				jsonNctsExportSpecificTopicItemRecord.setTvtdn(opd);
				String urlRequestParams = this.getRequestUrlKeyParametersUpdate(jsonNctsExportSpecificTopicItemRecord, appUser,TdsConstants.MODE_DELETE );
				
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
			    		this.setFatalError(model, rpgReturnResponseHandler, jsonNctsExportSpecificTopicItemRecord);
			    		
			    	}else{
			    		//Delete successfully done!
			    		logger.info("[INFO] Valid Delete -- Record successfully deleted, OK ");
			    		//---------------------------
			    		//REFRESH ON SOME VARIABLES
			    		//---------------------------
			    		//Update some variables on header such as (1)Number of item lines, (2)Kolliantal and (3)Gross weight-Bruttovikt
			    		this.refreshHeaderVariables(appUser.getUser(), avd, opd);
			    	}
			
			}
			
	    	
			//FETCH the ITEM LIST of existent ITEMs for this TOPIC
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL_FETCH = UrlDataStore.NCTS_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
			
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(request, avd, opd, appUser);
			//for debug purposes in GUI
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL_FETCH + "==>params: " + urlRequestParamsKeys.toString()); 
			
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
		    	JsonNctsExportSpecificTopicItemContainer jsonNctsExportSpecificTopicItemContainer = this.nctsExportSpecificTopicItemService.getNctsExportSpecificTopicItemContainer(jsonPayloadFetch);
		    	
		    
		    	this.setCodeDropDownMgr(appUser, model);
		    	this.setDomainObjectsForListInView(session, model, jsonNctsExportSpecificTopicItemContainer);
			
			successView.addObject("model",model);
			//successView.addObject(Constants.EDIT_ACTION_ON_TOPIC, Constants.ACTION_FETCH);
			
			return successView;
		}
	}
	
	
	/**
	 * 
	 * @param appUser
	 * @param avd
	 * @param opd
	 */
	private void refreshHeaderVariables(String appUser, String avd, String opd){
		//---------------------------
		//REFRESH ON SOME VARIABLES
		//---------------------------
		//Update some variables on header such as (1)Number of item lines, (2)Kolliantal and (3)Gross weight-Bruttovikt
		logger.info("[INFO] REFRESH of topic ... starting now...");
		String BASE_URL_REFRESH = UrlDataStore.NCTS_EXPORT_BASE_REFRESH_SPECIFIC_TOPIC_URL;
		String urlRequestParams = "user=" + appUser + "&avd=" + avd + "&opd=" + opd; 
		
		logger.info("URL: " + BASE_URL_REFRESH);
		logger.info("URL PARAMS: " + urlRequestParams);
		String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_REFRESH, urlRequestParams);
		logger.info(rpgReturnPayload);	
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
		errorMetaInformation.append(rpgReturnResponseHandler.getThtdn());
		model.put(TdsConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
	/**
	 * Sets domain objects
	 * 
	 * @param session
	 * @param model
	 * @param jsonNctsExportSpecificTopicItemContainer
	 * 
	 */
	private void setDomainObjectsForListInView(HttpSession session, Map model, JsonNctsExportSpecificTopicItemContainer jsonNctsExportSpecificTopicItemContainer){
		List list = new ArrayList();
		if(jsonNctsExportSpecificTopicItemContainer!=null){
			for (JsonNctsExportSpecificTopicItemRecord record : jsonNctsExportSpecificTopicItemContainer.getOrderList()){
				list.add(record);
				logger.info(record.getTvvt());
			}
		}
		model.put(TdsConstants.DOMAIN_LIST, list);
		//set a session variable in order to make the list available to an external view controller (Excel/PDF- Controller)
		session.setAttribute(session.getId() + TdsConstants.SESSION_LIST, list);
	}
	/**
	 * Sets domain objects
	 * @param model
	 * @param jsonNctsExportSpecificTopicItemRecord
	 */
	private void setDomainObjectsInView(Map model, JsonNctsExportSpecificTopicItemRecord jsonNctsExportSpecificTopicItemRecord){
		model.put(TdsConstants.DOMAIN_RECORD, jsonNctsExportSpecificTopicItemRecord);
	}
	
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param jsonNctsExportSpecificTopicItemRecord
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonNctsExportSpecificTopicItemRecord jsonNctsExportSpecificTopicItemRecord){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, jsonNctsExportSpecificTopicItemRecord);
	}
	
	/**
	 * 
	 * Creates the record (Add) for a later update in the same transaction
	 * @param session
	 * @param request
	 * @param appUser
	 * @return
	 */
	private JsonNctsExportSpecificTopicItemRecord createNewItemKeySeeds(HttpSession session, HttpServletRequest request, SystemaWebUser appUser){
		
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		//request variables
		String numberOfItemLinesInTopicStr = request.getParameter("numberOfItemLinesInTopic");
		if(numberOfItemLinesInTopicStr==null || "".equals(numberOfItemLinesInTopicStr)){
			numberOfItemLinesInTopicStr = "0";
		}else{
			numberOfItemLinesInTopicStr = numberOfItemLinesInTopicStr.trim();
		}
			
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		
		JsonNctsExportSpecificTopicItemRecord jsonNctsExportSpecificTopicItemRecord = new JsonNctsExportSpecificTopicItemRecord();
		
		try{
			//---------------------------
			//get BASE URL = RPG-PROGRAM
	        //---------------------------
			String BASE_URL = UrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
			
			//-------------------------------------------------------------------------------------------
			// STEP[PREPARE CREATION] --> generate new opd and tuid (if applicable) in order to be able to Add (Create)
			//-------------------------------------------------------------------------------------------
			logger.info("STEP[1] GET SEEDS and CREATE RECORD...");
			StringBuffer urlRequestParamsForSeed = new StringBuffer();
			urlRequestParamsForSeed.append("user=" + appUser.getUser());
			urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
			urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
			Integer numberOfItemLinesInTopic = -99;
			
			numberOfItemLinesInTopic = Integer.valueOf(numberOfItemLinesInTopicStr);
			//add one
			numberOfItemLinesInTopic++;
			logger.info("New item line nr: " + numberOfItemLinesInTopic);
			
			urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + numberOfItemLinesInTopic);
			urlRequestParamsForSeed.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + TdsConstants.MODE_ADD);
			logger.info("New SEEDs URL: " + BASE_URL);
			logger.info("PARAMS for SEED: " + urlRequestParamsForSeed.toString());
			//for debug purposes in GUI
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL + " ==>params: " + urlRequestParamsForSeed.toString() );
					
			//Get the counter from RPG (new opd Id)
			String rpgSeedNumberPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsForSeed.toString());
			// Map the JSON response to the new seeds (thtdn,tvli)
			// We are not using std JSON conversion since the RPGs strings are not the same. Should be the same as
			// the header fields. The RPG output should be changed in order to comply to the field specification...
			rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgSeedNumberPayload);
			
			//we must complete the GUI-json with the value from a line nr seed here
			if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage()) ){
				logger.info("[ERROR] No mandatory seeds (tvli, opd) were generated correctly)! look at std output log. [errMsg]" + rpgReturnResponseHandler.getErrorMessage());
				jsonNctsExportSpecificTopicItemRecord = null;
				
			}else{
				jsonNctsExportSpecificTopicItemRecord.setTvtdn(rpgReturnResponseHandler.getThtdn());
				jsonNctsExportSpecificTopicItemRecord.setTvli(rpgReturnResponseHandler.getTvli());
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}

		return jsonNctsExportSpecificTopicItemRecord;
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
	private String getRequestUrlKeyParametersUpdate(JsonNctsExportSpecificTopicItemRecord record, SystemaWebUser appUser, String mode){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + record.getTvavd().trim());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + record.getTvtdn().trim());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + record.getTvli().trim());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + mode.trim());
		
		return urlRequestParamsKeys.toString();
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
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_017_KOLLI, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tdsDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_013_DOKTYPE, null, null);
	}
	
	/**
	 * 
	 * @param recordToValidate
	 */
	private void adjustFields(JsonNctsExportSpecificTopicItemRecord recordToValidate){
		//Godsmärkning
		if(strMgr.isNotNull(recordToValidate.getTvmn())){
			recordToValidate.setTvmn(recordToValidate.getTvmn().toUpperCase());
		}
		//Godsmärkning - Extraordinära uppg
		if(strMgr.isNotNull(recordToValidate.getTvmn2())){
			recordToValidate.setTvmn2(recordToValidate.getTvmn2().toUpperCase());
		}
		if(strMgr.isNotNull(recordToValidate.getTvmn3())){
			recordToValidate.setTvmn3(recordToValidate.getTvmn3().toUpperCase());
		}
		if(strMgr.isNotNull(recordToValidate.getTvmn4())){
			recordToValidate.setTvmn4(recordToValidate.getTvmn4().toUpperCase());
		}
		if(strMgr.isNotNull(recordToValidate.getTvmn5())){
			recordToValidate.setTvmn5(recordToValidate.getTvmn5().toUpperCase());
		}
	}
	/**
	 * 
	 * @param jsonNctsExportSpecificTopicItemRecord
	 * @param model
	 * @param container
	 * @param headerRecord
	 */
	/* NOT NEEDED as in Export/Import= ? CB/THOMAS ... to be continued
	private void setBruttoViktRestValue(JsonNctsExportSpecificTopicItemRecord jsonNctsExportSpecificTopicItemRecord, Map model, JsonNctsExportSpecificTopicItemContainer container, JsonNctsExportSpecificTopicRecord headerRecord){
		if(container!=null){
			try{
				Double sumBrut = 0.00D;
				for (JsonNctsExportSpecificTopicItemRecord record : container.getOrderList()){
					if(strMgr.isNotNull(record.getTvvktb()) ){
						sumBrut += Double.parseDouble(record.getTvvktb().replace(",", "."));
					}
				}
				Double headerBrutVal = 0.00D;
				if(strMgr.isNotNull(headerRecord.getThvkb())){
					headerBrutVal = Double.valueOf(headerRecord.getThvkb().replace(",", "."));
				}
				Double result = headerBrutVal - sumBrut;
				String resultStr = String.valueOf(result);
				jsonNctsExportSpecificTopicItemRecord.setTvvktb(resultStr.replace(".", ","));
				logger.info("final rest value:" + jsonNctsExportSpecificTopicItemRecord.getTvvktb());
			}catch(Exception e){
				logger.info("ERROR on default Gross weight:" + e.toString());
			}
		}
		
		
	}
	*/
	
	//SERVICES
	
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
		
	
	@Qualifier ("nctsExportSpecificTopicItemService")
	private NctsExportSpecificTopicItemService nctsExportSpecificTopicItemService;
	@Autowired
	@Required
	public void setNctsExportSpecificTopicItemService (NctsExportSpecificTopicItemService value){ this.nctsExportSpecificTopicItemService = value; }
	public NctsExportSpecificTopicItemService getNctsExportSpecificTopicItemService(){ return this.nctsExportSpecificTopicItemService; }
	 
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
	 
	 
}

