package no.systema.tds.nctsimport.controller;

import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
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
import no.systema.tds.nctsimport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tds.nctsimport.model.jsonjackson.topic.items.JsonNctsImportSpecificTopicItemContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.items.JsonNctsImportSpecificTopicItemRecord;
import no.systema.tds.nctsimport.service.NctsImportSpecificTopicItemService;

import no.systema.tds.nctsimport.service.html.dropdown.DropDownListPopulationService;
import no.systema.tds.nctsimport.url.store.UrlDataStore;
import no.systema.tds.nctsimport.util.manager.CodeDropDownMgr;
import no.systema.tds.nctsimport.util.RpgReturnResponseHandler;
import no.systema.tds.nctsimport.validator.NctsImportItemsValidator;
//application imports
import no.systema.tds.util.TdsConstants;
import no.systema.tds.service.html.dropdown.TdsDropDownListPopulationService;
import no.systema.tds.model.external.url.UrlISOLanguageObject;





/**
 * NCTS Import create items gateway
 * 
 * @author oscardelatorre
 *
 */

@Controller
//@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class NctsImportItemsController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(NctsImportItemsController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();

	
	
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
	@RequestMapping(value="nctsimport_edit_items.do")
	public ModelAndView nctsImportEditItem(@ModelAttribute ("record") JsonNctsImportSpecificTopicItemRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("Inside: nctsImportEditItem");
		ModelAndView successView = new ModelAndView("nctsimport_edit_items");
		
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonNctsImportSpecificTopicItemRecord jsonNctsImportSpecificTopicItemRecord = null;
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		Map model = new HashMap();
		String urlRequestParamsKeys = null;
		//Catch required action (doFetch or doUpdate)
		String action = request.getParameter("action");
		logger.info("ACTION: " + action);
		
		if(appUser==null){
			return this.loginView;
		}else{
		  
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_NCTS_IMPORT);
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, TdsConstants.ACTIVE_URL_RPG_INITVALUE); 
			
			boolean isValidCreatedRecordTransactionOnRPG = true;
			//Keys and header information
			String opd = request.getParameter("opd");
			String avd = request.getParameter("avd");
			String sign = request.getParameter("sign");
			String mrnNr = request.getParameter("mrnNr");
			String godsNr = request.getParameter("godsNr");
			String status = request.getParameter("status");
			String datum = request.getParameter("datum");
			//this key is only used with a real Update. When empty it will be a signal for a CREATE NEW (Add)
			String lineNr = request.getParameter("lineNr");
			if(lineNr!=null && !"".equals(lineNr)){
				//nothing
			}else{
				//this branch is necessary in order to get the line Nr after a validation error (ref. below att bindingResult.hasErrors in this same method)
				lineNr = (String)session.getAttribute("tvli_SESSION");
			}
			model.put("opd", opd);
			model.put("avd", avd);
			model.put("sign", sign);
			model.put("mrnNr", mrnNr);
			model.put("godsNr", godsNr);
			model.put("status", status);
			model.put("datum", datum);
			
			
			if(TdsConstants.ACTION_UPDATE.equals(action)){
				//-----------
				//Validation
				//-----------
				NctsImportItemsValidator validator = new NctsImportItemsValidator();
				logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
				
				//-------------------------------------------------------
				//this is only for validation of gross weight 
				//in first item line (manadatory), this is the only way
				//-------------------------------------------------------
				/*
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
				}*/
				
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
				    		recordToValidate.setTvavd(avd);
					    	recordToValidate.setTvtdn(opd);
					    	recordToValidate.setTvli(lineNr);
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
						jsonNctsImportSpecificTopicItemRecord = new JsonNctsImportSpecificTopicItemRecord();
						ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonNctsImportSpecificTopicItemRecord);
			            //binder.registerCustomEditor(...); // if needed
			            binder.bind(request);
			            //put back the generated seed and the header keys (avd,opd)
			            jsonNctsImportSpecificTopicItemRecord.setTvavd(avd);
			            jsonNctsImportSpecificTopicItemRecord.setTvtdn(opd);
			            jsonNctsImportSpecificTopicItemRecord.setTvli(lineNr);
			            //info fields
			            this.setInformationFields(jsonNctsImportSpecificTopicItemRecord);
			            
			            logger.info("[DEBUG] UPDATE on Avd: " + jsonNctsImportSpecificTopicItemRecord.getTvavd());
			            logger.info("[DEBUG] UPDATE on Opd: " + jsonNctsImportSpecificTopicItemRecord.getTvtdn());
			            logger.info("[DEBUG] UPDATE on Line nr: " + jsonNctsImportSpecificTopicItemRecord.getTvli());
			            
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
						jsonNctsImportSpecificTopicItemRecord  = this.createNewItemKeySeeds(session, request, appUser);
						if(jsonNctsImportSpecificTopicItemRecord!=null){
							String newLineNr = jsonNctsImportSpecificTopicItemRecord.getTvli();
							logger.info("[INFO] New LineNr:" + newLineNr);
							//take the rest from GUI.
							jsonNctsImportSpecificTopicItemRecord = new JsonNctsImportSpecificTopicItemRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonNctsImportSpecificTopicItemRecord);
				            //binder.registerCustomEditor(...); // if needed
				            binder.bind(request);
				            
				            jsonNctsImportSpecificTopicItemRecord.setTvli(newLineNr);
				            //put back the generated seed and the header keys (avd,opd)
				            jsonNctsImportSpecificTopicItemRecord.setTvtdn(opd);
				            jsonNctsImportSpecificTopicItemRecord.setTvavd(avd);
				            //info fields
				            this.setInformationFields(jsonNctsImportSpecificTopicItemRecord);
				            
				            //logger.info("[INFO] Händelser (on item-event record):" + jsonNctsImportSpecificTopicItemRecord.getTvinf1());
				            //logger.info("[INFO] New line number (on item record):" + jsonNctsImportSpecificTopicItemRecord.getTvli());
				            
						}else{
							isValidCreatedRecordTransactionOnRPG = false;
						}
					}
					//--------------------------------------------------
					//At this point we are ready to do an update
					//--------------------------------------------------
					if(isValidCreatedRecordTransactionOnRPG){
						
						
			            logger.info("[INFO] Valid previous step successfully processed, OK ");
			            logger.info("[INFO] Ready to move forward to do the UPDATE");
			            
						//---------------------------
						//get BASE URL = RPG-PROGRAM
			            //---------------------------
						String BASE_URL_UPDATE = UrlDataStore.NCTS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
						urlRequestParamsKeys = this.getRequestUrlKeyParametersUpdate(jsonNctsImportSpecificTopicItemRecord, appUser, TdsConstants.MODE_UPDATE);
						String urlRequestParamsTopicItem = this.urlRequestParameterMapper.getUrlParameterValidString((jsonNctsImportSpecificTopicItemRecord));
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
					    		this.setFatalError(model, rpgReturnResponseHandler, jsonNctsImportSpecificTopicItemRecord);
					    		
					    	}else{
					    		//Update succefully done!
					    		logger.info("[INFO] Valid STEP[2] Update -- Record successfully updated, OK ");
					    		//---------------------------
					    		//REFRESH ON SOME VARIABLES
					    		//---------------------------
					    		//Update some variables on header such as (1)Number of item lines, (2)Kolliantal and (3)Gross weight-Bruttovikt
					    		//TO USE??? -->this.refreshHeaderVariables(appUser.getUser(), avd, opd);
					    	}
					}else{
						rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tuid, syop generation : " + rpgReturnResponseHandler.getErrorMessage());
						this.setFatalError(model, rpgReturnResponseHandler, jsonNctsImportSpecificTopicItemRecord);
					}
			    }
				
			}else if(TdsConstants.ACTION_DELETE.equals(action)){
				logger.info("[INFO] Delete record start process... ");
				String lineNrToDelete = request.getParameter("lin");
				
				//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				String BASE_URL_DELETE = UrlDataStore.NCTS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
				jsonNctsImportSpecificTopicItemRecord = new JsonNctsImportSpecificTopicItemRecord();
				jsonNctsImportSpecificTopicItemRecord.setTvli(lineNrToDelete);
				jsonNctsImportSpecificTopicItemRecord.setTvavd(avd);
				jsonNctsImportSpecificTopicItemRecord.setTvtdn(opd);
				String urlRequestParams = this.getRequestUrlKeyParametersUpdate(jsonNctsImportSpecificTopicItemRecord, appUser,TdsConstants.MODE_DELETE );
				
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
			    		this.setFatalError(model, rpgReturnResponseHandler, jsonNctsImportSpecificTopicItemRecord);
			    		
			    	}else{
			    		//Delete successfully done!
			    		logger.info("[INFO] Valid Delete -- Record successfully deleted, OK ");
			    		//---------------------------
			    		//REFRESH ON SOME VARIABLES
			    		//---------------------------
			    		//Update some variables on header such as (1)Number of item lines, (2)Kolliantal and (3)Gross weight-Bruttovikt
			    		//TO USE? --> this.refreshHeaderVariables(appUser.getUser(), avd, opd);
			    	}
			
			}
			
	    	
			//FETCH the ITEM LIST of existent ITEMs for this TOPIC
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL_FETCH = UrlDataStore.NCTS_IMPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
			
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
		    	JsonNctsImportSpecificTopicItemContainer jsonNctsImportSpecificTopicItemContainer = this.nctsImportSpecificTopicItemService.getNctsImportSpecificTopicItemContainer(jsonPayloadFetch);
		    	
		    	//general code population
		    this.setCodeDropDownMgr(appUser, model);
		    	this.setDomainObjectsForListInView(session, model, jsonNctsImportSpecificTopicItemContainer);
			
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
		String BASE_URL_REFRESH = UrlDataStore.NCTS_IMPORT_BASE_REFRESH_SPECIFIC_TOPIC_URL;
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
		errorMetaInformation.append(rpgReturnResponseHandler.getTitdn());
		model.put(TdsConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
	/**
	 * Sets domain objects
	 * @param session
	 * @param model
	 * @param jsonNctsImportSpecificTopicItemContainer
	 */
	private void setDomainObjectsForListInView(HttpSession session, Map model, JsonNctsImportSpecificTopicItemContainer itemContainer){
		List list = new ArrayList();
		if(itemContainer!=null){
			for (JsonNctsImportSpecificTopicItemRecord record : itemContainer.getOrderList()){
				list.add(record);
				logger.info(record.getTvst());
			}
		}
		model.put(TdsConstants.DOMAIN_LIST, list);
		//set a session variable in order to make the list available to an external view controller (Excel/PDF- Controller)
		session.setAttribute(session.getId() + TdsConstants.SESSION_LIST, list);
	}
	/**
	 * Sets domain objects
	 * @param model
	 * @param jsonNctsImportSpecificTopicItemRecord
	 */
	private void setDomainObjectsInView(Map model, JsonNctsImportSpecificTopicItemRecord jsonNctsImportSpecificTopicItemRecord){
		model.put(TdsConstants.DOMAIN_RECORD, jsonNctsImportSpecificTopicItemRecord);
	}
	
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param jsonNctsImportSpecificTopicItemRecord
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonNctsImportSpecificTopicItemRecord jsonNctsImportSpecificTopicItemRecord){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, jsonNctsImportSpecificTopicItemRecord);
	}
	
	/**
	 * 
	 * Creates the record (Add) for a later update in the same transaction
	 * @param session
	 * @param request
	 * @param appUser
	 * @return
	 */
	private JsonNctsImportSpecificTopicItemRecord createNewItemKeySeeds(HttpSession session, HttpServletRequest request, SystemaWebUser appUser){
		
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
		
		JsonNctsImportSpecificTopicItemRecord jsonNctsImportSpecificTopicItemRecord = new JsonNctsImportSpecificTopicItemRecord();
		
		try{
			//---------------------------
			//get BASE URL = RPG-PROGRAM
	        //---------------------------
			String BASE_URL = UrlDataStore.NCTS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
			
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
			// Map the JSON response to the new seeds (tvtdn,tvli)
			// We are not using std JSON conversion since the RPGs strings are not the same. Should be the same as
			// the header fields. The RPG output should be changed in order to comply to the field specification...
			rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgSeedNumberPayload);
			
			//we must complete the GUI-json with the value from a line nr seed here
			if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage()) ){
				logger.info("[ERROR] No mandatory seeds (tvli, opd) were generated correctly)! look at std output log. [errMsg]" + rpgReturnResponseHandler.getErrorMessage());
				jsonNctsImportSpecificTopicItemRecord = null;
				
			}else{
				jsonNctsImportSpecificTopicItemRecord.setTvtdn(opd);
				jsonNctsImportSpecificTopicItemRecord.setTvli(String.valueOf(numberOfItemLinesInTopic));
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}

		return jsonNctsImportSpecificTopicItemRecord;
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
	private String getRequestUrlKeyParametersUpdate(JsonNctsImportSpecificTopicItemRecord record, SystemaWebUser appUser, String mode){
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
	 * @param model
	 */
	private void populateHtmlDropDownsFromFile(Map model){
		//fill in html lists here
		//model.put(TdsConstants.RESOURCE_MODEL_KEY_CURRENCY_LIST, this.dropDownListPopulationService.getCurrencyList());
		//model.put(TdsConstants.RESOURCE_MODEL_KEY_COUNTRY_LIST, this.dropDownListPopulationService.getCountryList());
		model.put(TdsConstants.RESOURCE_MODEL_KEY_LANGUAGE_LIST, this.dropDownListPopulationService.getLanguageList());
		model.put(TdsConstants.URL_EXTERNAL_LANGUAGECODES_TARIC_CODE, new UrlISOLanguageObject());
		
	}
	
	/**
	 * AS400 codes for the general routine
	 * 
	 * 012=Språkkod                      
	 * 013=Dokumentkod                  
	 * 014=Tidigare dokument              
	 * 017=Kollityp                      
	 * 031=Deklarationstyp              
	 * 039=Tilläggsupplysning            
	 * 047=Kontrollresultat              
	 * 064=Känslig vara                  
	 * 096=Speciella omständigheter      
	 * 105=Tillgångskod för garanti      
	 * 106=Tullkontor referansenr        
	 * 116=Betalningssätt transportkostnad
	 *
	 * 
	 * @param model
	 * @param utfPayload
	 */
	/*
	private void populateCodesHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser, String paramTYP){
		//fill in html lists here
		try{
			String CODES_URL = TdsUrlDataStore.TDS_NCTS_CODES_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "typ=" + paramTYP);
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(CODES_URL, urlRequestParamsKeys.toString());
			logger.info("CODES_URL:" + CODES_URL);
			logger.info("CODES PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonTdsNctsCodeContainer codeContainer = this.tdsDropDownListPopulationService.getNctsCodeContainer(url);
			List<JsonTdsNctsCodeRecord> list = new ArrayList();
			for(JsonTdsNctsCodeRecord codeRecord: codeContainer.getKodlista()){
				//logger.info(codeRecord.getSvkd_kd());
				list.add(codeRecord);
			}
			if(JsonTdsNctsCodeContainer.KOD_DOK.equalsIgnoreCase(paramTYP)){
				logger.info(JsonTdsNctsCodeContainer.KOD_DOK + " (Dokumentkod) - branch...");
				model.put(TdsConstants.RESOURCE_MODEL_KEY_CODE_NCTS_DOKUMENT_LIST, list);
				
			}else if(JsonTdsNctsCodeContainer.KOD_KOLLI_TYP.equalsIgnoreCase(paramTYP)){
				logger.info(JsonTdsNctsCodeContainer.KOD_KOLLI_TYP + " (Kollislag) - branch...");
				model.put(TdsConstants.RESOURCE_MODEL_KEY_CODE_NCTS_KOLLI_LIST, list);
				
			}else if(JsonTdsNctsCodeContainer.KOD_DEKL_TYP.equalsIgnoreCase(paramTYP)){
				logger.info(JsonTdsNctsCodeContainer.KOD_DEKL_TYP + " (Dekl.typ) - branch...");
				model.put(TdsConstants.RESOURCE_MODEL_KEY_CODE_NCTS_DEKLTYP_LIST, list);
			}//more implementations here...
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	*/
	
	/**
	 * 
	 * Populates all the information fields since there is only one field (text area) in the GUI
	 * 
	 * @param itemRecord
	 * 
	 */
	private void setInformationFields(JsonNctsImportSpecificTopicItemRecord itemRecord){
		int FIELD_LENGTH = 70;
		String tvinf = itemRecord.getTvinf();
		
		if(tvinf!=null && !"".equals(tvinf)){
			if(tvinf.trim().length()>=FIELD_LENGTH){
				itemRecord.setTvinf1(tvinf.substring(0,FIELD_LENGTH));
				itemRecord.setTvinf2(tvinf.substring(FIELD_LENGTH));
				//logger.info("tvinf2:" + itemRecord.getTvinf2());
				
				if(itemRecord.getTvinf2().length()>=FIELD_LENGTH){
					//logger.info("B");
					String tmp2 = itemRecord.getTvinf2();
					itemRecord.setTvinf2(tmp2.substring(0,FIELD_LENGTH));
					itemRecord.setTvinf3(tmp2.substring(FIELD_LENGTH));
					
					if(itemRecord.getTvinf3().length()>=FIELD_LENGTH){
						//logger.info("C");
						String tmp3 = itemRecord.getTvinf3();
						itemRecord.setTvinf3(tmp3.substring(0,FIELD_LENGTH));
						itemRecord.setTvinf4(tmp3.substring(FIELD_LENGTH));
					}
				}
			}else{
				itemRecord.setTvinf1(tvinf);
			}
		}
		//clear the temp place holder.
		itemRecord.setTvinf(null);
		
		logger.debug("tvinf1:" + itemRecord.getTvinf1());
		logger.info("tvinf2:" + itemRecord.getTvinf2());
		logger.info("tvinf3:" + itemRecord.getTvinf3());
		logger.info("tvinf4:" + itemRecord.getTvinf4());
		
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
		
	
	@Qualifier ("nctsImportSpecificTopicItemService")
	private NctsImportSpecificTopicItemService nctsImportSpecificTopicItemService;
	@Autowired
	@Required
	public void setNctsImportSpecificTopicItemService (NctsImportSpecificTopicItemService value){ this.nctsImportSpecificTopicItemService = value; }
	public NctsImportSpecificTopicItemService getNctsImportSpecificTopicItemService(){ return this.nctsImportSpecificTopicItemService; }
	 
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

