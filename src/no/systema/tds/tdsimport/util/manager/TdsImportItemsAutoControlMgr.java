package no.systema.tds.tdsimport.util.manager;

import java.util.*;

import org.apache.log4j.Logger;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.StringManager;
import no.systema.tds.service.TdsBilagdaHandlingarYKoderService;
import no.systema.tds.service.TdsTillaggskoderService;
import no.systema.tds.model.jsonjackson.codes.JsonTdsBilagdaHandlingarYKoderRecord;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTillaggskodRecord;
import no.systema.tds.model.jsonjackson.validation.JsonTdsMangdEnhetContainer;
import no.systema.tds.model.jsonjackson.validation.JsonTdsMangdEnhetRecord;
import no.systema.tds.tdsexport.util.RpgReturnResponseHandler;
import no.systema.tds.tdsimport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicRecord;
import no.systema.tds.tdsimport.service.TdsImportSpecificTopicItemService;
import no.systema.tds.tdsimport.url.store.TdsImportUrlDataStore;
import no.systema.tds.tdsimport.util.manager.TdsImportControllerAjaxCommonFunctionsMgr;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemAvgifterRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemFormanskoderRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemRecord;
import no.systema.tds.url.store.TdsUrlDataStore;
import no.systema.tds.util.TdsConstants;
import no.systema.tds.model.jsonjackson.JsonTdsAutoControlErrorContainer;



public class TdsImportItemsAutoControlMgr {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	private static final Logger logger = Logger.getLogger(TdsImportItemsAutoControlMgr.class.getName());
	private final StringManager strMgr = new StringManager();
	
	private UrlCgiProxyService urlCgiProxyService = null;
	private TdsImportSpecificTopicItemService tdsImportSpecificTopicItemService = null;
	
	//Should be set after the constructor call
	private JsonTdsImportSpecificTopicItemRecord record = null;
	public void setRecord (JsonTdsImportSpecificTopicItemRecord recordToValidate){ 
		this.record = recordToValidate;
		this.validRecord = true;
	}
	
	private JsonTdsImportSpecificTopicRecord headerRecord = null;
	public void setHeaderRecord (JsonTdsImportSpecificTopicRecord headerRecord){ 
		this.headerRecord = headerRecord;
	}
	
	//this is the global validRecord that will decide if the control passes
	private boolean validRecord = true;
	public boolean isValidRecord (){return this.validRecord;}
	
	public TdsImportItemsAutoControlMgr(UrlCgiProxyService urlCgiProxyService, TdsImportSpecificTopicItemService tdsImportSpecificTopicItemService){
		this.urlCgiProxyService = urlCgiProxyService;
		this.tdsImportSpecificTopicItemService = tdsImportSpecificTopicItemService;
	}
	/**
	 * 
	 * @param headerRecord
	 * @param applicationUser
	 */
	public void calculateChargesOnItem(JsonTdsImportSpecificTopicRecord headerRecord, String applicationUser){
		TdsImportAvgiftsberakningenMgr avgiftsMgr = new TdsImportAvgiftsberakningenMgr(this.tdsImportSpecificTopicItemService, this.urlCgiProxyService);
		if( (this.record.getSviv_stva()!=null && !"".equals(this.record.getSviv_stva()) ) && (this.record.getSviv_stva2()!=null && !"".equals(this.record.getSviv_stva2())) ){
			//nothing... We should expect all the charges be there as well...
		}else{	
			String svihStrParam = "svih_vufr=" + headerRecord.getSvih_vufr() + "&svih_vuva=" + headerRecord.getSvih_vuva() + 
			"&svih_vuku=" + headerRecord.getSvih_vuku() + "&svih_vufo=" + headerRecord.getSvih_vufo() + 	
			"&svih_ovko=" + headerRecord.getSvih_ovko() + "&svih_kara=" + headerRecord.getSvih_kara() + 	
			"&svih_anra=" + headerRecord.getSvih_anra() + "&svih_lekd=" + headerRecord.getSvih_lekd() + 
			"&svih_vakd=" + headerRecord.getSvih_vakd() + "&svih_vaku=" + headerRecord.getSvih_vaku() + 	
			"&svih_fabl=" + headerRecord.getSvih_fabl(); 
	
			String svivStrParam = "&sviv_vata=" + this.record.getSviv_vata() + "&sviv_ulkd=" + this.record.getSviv_ulkd() +  	
			"&sviv_fokd=" + this.record.getSviv_fokd() + "&sviv_eup1=" + this.record.getSviv_eup1() + 	
			"&sviv_ankv=" + this.record.getSviv_ankv() + "&sviv_stva=" + this.record.getSviv_stva() + 	
			"&sviv_stva2=" + this.record.getSviv_stva2() + "&sviv_fabl=" + this.record.getSviv_fabl();	
			
			String urlRequestParams = svihStrParam + svivStrParam;
			
			JsonTdsImportSpecificTopicItemAvgifterRecord chargeRecord = avgiftsMgr.calculateChargesOnItem(applicationUser, urlRequestParams);
			if(chargeRecord!=null){
				this.record.setSviv_stva(chargeRecord.getSviv_stva());this.record.setSviv_stva2(chargeRecord.getSviv_stva2());
				//
				this.record.setSviva_abk1(chargeRecord.getSviva_abk1());this.record.setSviva_abg1(chargeRecord.getSviva_abg1());
				this.record.setSviva_abs1(chargeRecord.getSviva_abs1());this.record.setSviva_abx1(chargeRecord.getSviva_abx1());
				this.record.setSviva_abb1(chargeRecord.getSviva_abb1());
				//
				this.record.setSviva_abk2(chargeRecord.getSviva_abk2());this.record.setSviva_abg2(chargeRecord.getSviva_abg2());
				this.record.setSviva_abs2(chargeRecord.getSviva_abs2());this.record.setSviva_abx2(chargeRecord.getSviva_abx2());
				this.record.setSviva_abb2(chargeRecord.getSviva_abb2());
				//
				this.record.setSviva_abk3(chargeRecord.getSviva_abk3());this.record.setSviva_abg3(chargeRecord.getSviva_abg3());
				this.record.setSviva_abs3(chargeRecord.getSviva_abs3());this.record.setSviva_abx3(chargeRecord.getSviva_abx3());
				this.record.setSviva_abb3(chargeRecord.getSviva_abb3());
				//
				this.record.setSviva_abk4(chargeRecord.getSviva_abk4());this.record.setSviva_abg4(chargeRecord.getSviva_abg4());
				this.record.setSviva_abs4(chargeRecord.getSviva_abs4());this.record.setSviva_abx4(chargeRecord.getSviva_abx4());
				this.record.setSviva_abb4(chargeRecord.getSviva_abb4());
				//
				this.record.setSviva_abk5(chargeRecord.getSviva_abk5());this.record.setSviva_abg5(chargeRecord.getSviva_abg5());
				this.record.setSviva_abs5(chargeRecord.getSviva_abs5());this.record.setSviva_abx5(chargeRecord.getSviva_abx5());
				this.record.setSviva_abb5(chargeRecord.getSviva_abb5());
				
			}
		}	  
	}
	/**
	 * Mandatory fields
	 */
	public void checkValidMandatoryFields(){
		logger.info("Inside: checkValidMandatoryFields()");
		if( this.isNotNull(record.getSviv_ulkd()) && this.isNotNull(record.getSviv_vata()) && this.isNotNull(record.getSviv_fokd()) && 
			this.isNotNull(record.getSviv_eup1()) && this.isNotNull(record.getSviv_brut()) && this.isNotNull(record.getSviv_neto()) && 
			this.isNotNull(record.getSviv_kosl()) && this.isNotNull(record.getSviv_vasl()) && this.isNotNull(record.getSviv_fabl()) && 
			this.isNotNull(record.getSviv_call()) && this.isNotNull(record.getSviv_stva()) && this.isNotNull(record.getSviv_stva2()) ){
			//nothing
		}else{
			//logger.info("FALSE");
			this.validRecord = false;
		}
	}
	
	/**
	 * 
	 * @param tdsTillaggskoderService
	 * @param applicationUser
	 * @param countryCode
	 * @param itemCode
	 */
	public void checkValidTillaggsKoder(TdsTillaggskoderService tdsTillaggskoderService, String applicationUser){
		TdsImportControllerAjaxCommonFunctionsMgr commonMgr = new TdsImportControllerAjaxCommonFunctionsMgr();
		List<JsonTdsTillaggskodRecord> list = commonMgr.fetchTillagskoder(tdsTillaggskoderService, applicationUser, this.headerRecord.getSvih_avut(), this.record.getSviv_vata());
		if(list!=null && list.size()>0){
			if(this.isNotNull(this.record.getSviv_vati()) ){
				//nothing
			}else{
				for(JsonTdsTillaggskodRecord record: list){
					this.record.setSviv_vati(record.getKod());
				}
			}
		}
	}
	
	/**
	 * Formånskoder
	 * 
	 * @param itemService
	 * @param applicationUser
	 */
	public void checkValidFormansKod(TdsImportSpecificTopicItemService itemService, String applicationUser){
		TdsImportControllerAjaxCommonFunctionsMgr commonMgr = new TdsImportControllerAjaxCommonFunctionsMgr();
		List<JsonTdsImportSpecificTopicItemFormanskoderRecord> list = commonMgr.fetchFormanskod(itemService, applicationUser, this.record.getSviv_ulkd(), this.record.getSviv_vata());
		if(list!=null && list.size()>0){
			if(this.isNotNull(this.record.getSviv_fokd()) ){
				//nothing
			}else{
				for(JsonTdsImportSpecificTopicItemFormanskoderRecord record : list){
					this.record.setSviv_fokd(record.getFokd());
				}
			}
		}
	}
	
	/**
	 * The gross and net weight ARE NOT ALLOWED to have decimals if the value is >=1
	 * @return
	 */
	public void checkValidGrossAndNetWeight(){
		try{
			double grossWeight = Double.valueOf(this.record.getSviv_brut().replace(",", "."));
			double netWeight = Double.valueOf(this.record.getSviv_neto().replace(",", "."));
			//check gross
			/*OBSOLETE -- DHL requirement 
			if(grossWeight>=1){
				if(Math.floor(grossWeight)!= grossWeight){
					this.validRecord = false;
				}
			}
			//check net
			if(netWeight>=1){
				if(Math.floor(netWeight)!= netWeight){
					this.validRecord = false;
				}
			}
			*/
			//compare gross vs net
			if(netWeight>grossWeight){
				this.validRecord = false;
			}
		}catch(Exception e){
			this.validRecord = false;
			
		}
		
	}
	
	/**
	 * Bilagda handlingar
	 * @param tdsBilagdaHandlingarYKoderService
	 * @param applicationUser
	 */
	
	public void checkValidBilagdaHandlingar(TdsBilagdaHandlingarYKoderService tdsBilagdaHandlingarYKoderService, String applicationUser){
		TdsImportControllerAjaxCommonFunctionsMgr commonMgr = new TdsImportControllerAjaxCommonFunctionsMgr();
		List<JsonTdsBilagdaHandlingarYKoderRecord> list = commonMgr.fetchBilagdaHandlingar(tdsBilagdaHandlingarYKoderService, applicationUser, this.headerRecord.getSvih_avut(), 
																							this.record.getSviv_vata(), this.record.getSviv_fokd());
		if(list!=null && list.size()>0){
			/* OBSOLETE after DHL's requirement (does not exist in Validator)
			if(this.isNotNull(this.record.getSviv_bit1()) ){
				
				if(this.record.getSviv_bit1().startsWith("Y")){
				//nothing
					
				}else{
					if(this.isNotNull(this.record.getSviv_bit2()) ){
						if(this.record.getSviv_bit2().startsWith("Y")){
							//nothing
						}else{
							if(this.isNotNull(this.record.getSviv_bit3()) ){
								if(this.record.getSviv_bit3().startsWith("Y")){
									//nothing
								}
							}else{
								this.validRecord = false;
							}
						}
						
					}else{
						this.validRecord = false;
					}
				}
			}else{
				this.validRecord = false;
			}*/
		}
	}
	/**
	 * Antal kolli rules
	 */
	public void checkValidAntalKolli(){
		logger.info("Inside: checkValidAntalKolli()");
		if(this.record.getSviv_kota()!=null && !"".equals(this.record.getSviv_kota())){
			if(this.isBulkGods(this.record.getSviv_kosl())){
				//bulkvaror should be empty (or Zero)
				if(this.isZero(this.record.getSviv_kota())){
					//valid
				}else{
					this.validRecord = false;
				}
			}else if(this.isOemballeratGods(this.record.getSviv_kosl())){
				//oemballerat gods should never be Zero
				if(this.isZero(this.record.getSviv_kota())){
					this.validRecord = false;
				}
			}else{
				//only with the first item line, otherwise it is valid to send kota=0 with all others kosl (units) and item lines
				if(this.record.getSviv_syli().equals("1")){
					if(this.isZero(this.record.getSviv_kota())){
						this.validRecord = false;
					}
				}
			}
		}else{
			if(this.record.getSviv_kosl()!=null && !"".equals(this.record.getSviv_kosl())){
				if(this.isBulkGods(this.record.getSviv_kosl())){
					//valid
				}else{
					this.validRecord = false;
				}
			}
		}
			
	}
	/***
	 * 
	 * @param applicationUser
	 */
	public void checkValidExtraMangdEnhet(String applicationUser){
		if("Y".equals(this.record.getExtraMangdEnhet())){
			if(this.record.getSviv_ankv()!=null && !"".equals(this.record.getSviv_ankv())){
				//valid
			}else{
				this.validRecord = false;
			}
		}else{
			if(this.record.getSviv_ankv()!=null && !"".equals(this.record.getSviv_ankv())){
				this.validRecord = false;
			}
		}
	}
	/**
	 * 
	 * @param applicationUser
	 */
	public void checkValidUrsprungslandKod(String applicationUser){
		if("SE".equals(this.record.getSviv_ulkd())){
			this.validRecord = false;
		}else if("EU".equals(this.record.getSviv_ulkd())){
			if( (this.record.getSviv_eup1()!=null && !"".equals(this.record.getSviv_eup1())) &&  (this.record.getSviv_fokd()!=null && !"".equals(this.record.getSviv_fokd()))	){
				//now to the final check
				if(this.isValidUrsprungsland_EU()){
					//valid
				}else{
					this.validRecord = false;
				}
			}else{
				this.validRecord = false;
			}
		}
	}
	
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean isValidUrsprungsland_EU(){
		logger.info("Inside isValidUrsprungsland_EU()");
		boolean retval = false;
		
		String eup1 = this.record.getSviv_eup1();
		String fokd = this.record.getSviv_fokd();
		boolean eup1Condition = false;
		boolean fokdCondition = false;
		
		if( ( eup1.startsWith("6")|| (eup1.equals("4010")||eup1.equals("4071")) ) ){
			eup1Condition = true;
		}
		if( fokd.startsWith("2")|| fokd.equals("3")){
			fokdCondition = true;
		}
		//now to the final check
		if(eup1Condition && fokdCondition){
			retval = true;
		}	
		
		return retval;
	}
	/**
	 * 
	 * @param applicationUser
	 * @param headerRecord
	 */
	public void getMandatoryMangdEnhetDirective(String applicationUser){
		String TDS_IE = "I";
		
		String BASE_URL_FETCH = TdsUrlDataStore.TDS_CHECK_EXTRA_MANGDENHET;
		String urlRequestParamsKeys = "user="+ applicationUser + "&ie=" + TDS_IE + "&kod=" + this.record.getSviv_vata() + "&lk=" + this.record.getSviv_ulkd();

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
		for(JsonTdsMangdEnhetRecord enhetRecord: container.getXtramangdenhet()){
			if(enhetRecord.getXtra()!=null && enhetRecord.getXtra().toUpperCase().equals("Y")){
				//Set all values
				this.record.setExtraMangdEnhet(enhetRecord.getXtra().toUpperCase());
				this.record.setExtraMangdEnhetCode(enhetRecord.getSvtx15_33());
				this.record.setExtraMangdEnhetDescription(enhetRecord.getSvtx03_04());
				//------------------------------------------
				//Rules of engagement to help the end-user
				//(Validation will not fire then ...)
				//------------------------------------------
				//RULE 1: NAR
				if("NAR".equals(this.record.getExtraMangdEnhetCode())){
					if(strMgr.isNotNull(this.record.getSviv_ankv())){
					 //Nothing	
					}else{
						if(strMgr.isNotNull(this.record.getSviv_kota()) && !"0".equals(this.record.getSviv_kota()) ){
							this.record.setSviv_ankv(this.record.getSviv_kota());
							//logger.info("YES!!!:" + recordToValidate.getSvev_ankv());
						}
					}
				}
				//RULE 2: TODO
				//here ...
			}
		}
		
	}
	
	/**Check for bulk goods
	 * 
	 * @param value
	 * @return
	 */
	private boolean isBulkGods(String value){
		boolean retval = false;
		if(value!=null){
			if("VR".equals(value)){
				retval = true;
			}else if("VY".equals(value)){
				retval = true;
			}else if("VO".equals(value)){
				retval = true;
			}else if("VL".equals(value)){
				retval = true;
			}else if("VG".equals(value)){
				retval = true;
			}else if("VQ".equals(value)){
				retval = true;
			} 
		}
		
		return retval;
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean isOemballeratGods(String value){
		boolean retval = false;
		if(value!=null){
			if("NE".equals(value)){
				retval = true;
			}else if("NF".equals(value)){
				retval = true;
			}else if("NG".equals(value)){
				retval = true;
			} 
		}
		
		return retval;
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean isZero(String value){
		Double db = 0.00D;
		boolean retval = true;
		try{
			db = Double.parseDouble(value);
			if(db!=0){
				retval = false;
			}
		}catch(Exception e){
			//nothing
		}
		
		return retval;
	}
	/**
	 * Light update on record in order to edit the original record with the least common denominator
	 * 
	 * @param applicationUser
	 * @return
	 */
	public boolean updateItemRecord(String applicationUser){
		boolean retval = true;
		UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		String BASE_URL_UPDATE = TdsImportUrlDataStore.TDS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
		logger.info("[INFO] UPDATE (light) to be done with lineNr [sviv_syli]:" + this.record.getSviv_syli());
		
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + applicationUser);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + this.record.getSviv_syav());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + this.record.getSviv_syop());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + this.record.getSviv_syli());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=U");
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_ligh=X"); //light update flag
		
		
		String urlRequestParamsItem = urlRequestParameterMapper.getUrlParameterValidString((this.record));
		//put the final valid param. string
		String urlRequestParams = urlRequestParamsKeys.toString() + urlRequestParamsItem;
		/* DEBUG
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_URL_UPDATE);
    	logger.info("URL PARAMS: " + urlRequestParams);
    	*/
    	//----------------------------------------------------------------------------
    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
    	//----------------------------------------------------------------------------
		String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_UPDATE, urlRequestParams);
		//Debug --> 
    	//logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
    	//we must evaluate a return RPG code in order to know if the Update was OK or not
    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgReturnPayload);
    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
    		retval = false;
    	}else{
    		//Update succefully done!
    		logger.info("[INFO] Valid light Update -- Record successfully updated, OK ");    		
    	}
    	return retval;
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param errorFlag
	 */
	public void updateItemWithAutoControlError(String applicationUser, String errorFlag){
		String BASE_URL_UPDATE_ERR = TdsImportUrlDataStore.TDS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_AUTOCONTROL_ERROR_URL;
		StringBuffer urlRequestParamsKeysAutoControl = new StringBuffer();
		urlRequestParamsKeysAutoControl.append("user=" + applicationUser);
		urlRequestParamsKeysAutoControl.append("&avd=" + this.record.getSviv_syav());
		urlRequestParamsKeysAutoControl.append("&opd=" + this.record.getSviv_syop());
		urlRequestParamsKeysAutoControl.append("&lin=" + this.record.getSviv_syli());
		if(errorFlag!=null){
			urlRequestParamsKeysAutoControl.append("&sviv_err=" + errorFlag);
		}else{
			urlRequestParamsKeysAutoControl.append("&sviv_err=");
		}
		/* DEBUG
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("FETCH item list... ");
    	logger.info("URL: " + BASE_URL_UPDATE_ERR);
    	logger.info("URL PARAMS: " + urlRequestParamsKeysAutoControl);
    	*/
    	//--------------------------------------
    	//EXECUTE the FETCH (RPG program) here
    	//--------------------------------------
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_UPDATE_ERR, urlRequestParamsKeysAutoControl.toString());
		JsonTdsAutoControlErrorContainer container = this.tdsImportSpecificTopicItemService.getTdsImportSpecificTopicItemAutoControlErrorContainer(jsonPayload);
    	if(container!=null){
    		if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
    			logger.info(container.getErrMsg());
    		}else{
    			logger.info("[OK] Update successfully done on AutoControl");
    		}
    	}
    }
	/**
	 * 
	 * @param applicationUser
	 */
	public void updateItemWithExgraMangdEnhet(String applicationUser){
		String BASE_URL_UPDATE_ERR = TdsImportUrlDataStore.TDS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_EXTRAMANGD_ENHET_URL;
		StringBuffer urlRequestParamsKeysAutoControl = new StringBuffer();
		urlRequestParamsKeysAutoControl.append("user=" + applicationUser);
		urlRequestParamsKeysAutoControl.append("&avd=" + this.record.getSviv_syav());
		urlRequestParamsKeysAutoControl.append("&opd=" + this.record.getSviv_syop());
		urlRequestParamsKeysAutoControl.append("&lin=" + this.record.getSviv_syli());
		//new value
		urlRequestParamsKeysAutoControl.append("&sviv_ankv=" + this.record.getSviv_ankv());
		
		/* DEBUG
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("FETCH item list... ");
    	logger.info("URL: " + BASE_URL_UPDATE_ERR);
    	logger.info("URL PARAMS: " + urlRequestParamsKeysAutoControl);
    	*/
    	//--------------------------------------
    	//EXECUTE the FETCH (RPG program) here
    	//--------------------------------------
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_UPDATE_ERR, urlRequestParamsKeysAutoControl.toString());
		JsonTdsAutoControlErrorContainer container = this.tdsImportSpecificTopicItemService.getTdsImportSpecificTopicItemAutoControlErrorContainer(jsonPayload);
    	if(container!=null){
    		if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
    			logger.info(container.getErrMsg());
    		}else{
    			logger.info("[OK] Update successfully done on AutoControl");
    		}
    	}
    }
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean isNotNull(String value){
		boolean retval = false;
		if(value!=null && !"".equals(value)){
			retval = true;
		}
		return retval;
	}
	
}
