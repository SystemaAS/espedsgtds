/**
 * 
 */
package no.systema.tds.util.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.tds.model.external.url.*;

import no.systema.tds.model.jsonjackson.codes.JsonTdsCodeContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsCodeRecord;
import no.systema.tds.service.html.dropdown.TdsDropDownListPopulationService;
import no.systema.tds.url.store.TdsUrlDataStore;
import no.systema.tds.util.TdsConstants;


/**
 * The class handles general gui drop downs aspect population
 *
 * This Manager is not instantiated by the Spring Container at start up. 
 * Instead, it is instantiated by a controller when needed.
 * 
 * 
 * 
 * @author oscardelatorre
 * @date Sep 15, 2013
 * 
 */

public class CodeDropDownMgr {
	private static final Logger logger = Logger.getLogger(CodeDropDownMgr.class.getName());
	
	
	/**
	 * AS400 codes for the general routine
	 * 
	 * KLI = Kollislag           IE = A
	 * GCY = Landkod 			 IE = A
	 * MDX = Valutakod			 IE = A
	 * MCF = Bilagda Handlingar  IE = A
	 * ADD = Till�ggskod exp     IE = E
	 * ADD = Till�ggskod imp     IE = A
	 * FFK = F�rfarande :2 exp   IE = E
	 * FFK = F�rfarande :2 imp   IE = I
	 * FF1 = Förfarande :1 imp	 IE = I 
	 * SAL = S�rsk. uppl kod     IE = A
	 * FOR = F�rm�nskod imp      IE = I
	 * THO = Tid.handl. TYP       IE = A
	 * CHA = Avgiftskoder 		 IE = A
	 * MDM = Enhetslist			 IE = A
	 * 
	 * @param model
	 * @param utfPayload
	 */
	public void populateCodesHtmlDropDownsFromJsonString(UrlCgiProxyService urlCgiProxyService, TdsDropDownListPopulationService tdsDropDownListPopulationService,
														Map model, SystemaWebUser appUser, String paramIE, String paramTYP){
		//fill in html lists here
		try{
			
			String CODES_URL = TdsUrlDataStore.TDS_CODES_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + paramIE);
			urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "typ=" + paramTYP);
			//Now build the payload and send to the back end via the drop down service
			String utfPayload = urlCgiProxyService.getJsonContent(CODES_URL, urlRequestParamsKeys.toString());
			logger.info("CODES_URL:" + CODES_URL);
			logger.info("CODES PARAMS:" + urlRequestParamsKeys.toString());
			//logger.info(utfPayload);
			
			
			JsonTdsCodeContainer codeContainer = tdsDropDownListPopulationService.getCodeContainer(utfPayload);
			List<JsonTdsCodeRecord> list = new ArrayList();
			
			for(JsonTdsCodeRecord codeRecord: codeContainer.getKodlista()){
				list.add(codeRecord);
			}
			//if(list!=null){ logger.info("CODE LIST exists. Size:" + list.size()); }
			
			if("KLI".equalsIgnoreCase(paramTYP)){
				model.put(TdsConstants.RESOURCE_MODEL_KEY_CODE_KLI_LIST, list);
				model.put(TdsConstants.URL_EXTERNAL_KOLLISLAGCODES_TARIC_CODE, new UrlTaricKollislagObject());
		    	
			}else if("GCY".equalsIgnoreCase(paramTYP)){
				model.put(TdsConstants.RESOURCE_MODEL_KEY_CODE_GCY_LIST,list);
				model.put(TdsConstants.URL_EXTERNAL_LANDCODES_TARIC_CODE, new UrlTaricCountryObject());
		    	
			}else if("MDX".equalsIgnoreCase(paramTYP)){
				model.put(TdsConstants.RESOURCE_MODEL_KEY_CODE_MDX_LIST,list);
				model.put(TdsConstants.URL_EXTERNAL_CURRENCYCODES_TARIC_CODE, new UrlTaricCurrencyObject());

			}else if("MCF".equalsIgnoreCase(paramTYP)){
				model.put(TdsConstants.RESOURCE_MODEL_KEY_CODE_MCF_LIST,list);
				model.put(TdsConstants.URL_EXTERNAL_MCFCODES_TARIC_CODE, new UrlTaricBilagdaHandlingarObject());

			}else if("ADD".equalsIgnoreCase(paramTYP)){
				//not yet implemented
			}else if("FF1".equalsIgnoreCase(paramTYP)){
				model.put(TdsConstants.RESOURCE_MODEL_KEY_CODE_FORFARANDE01_LIST, list);
				model.put(TdsConstants.URL_EXTERNAL_FORFARANDE01_CODES_TARIC_CODE, new UrlTaricForfarande01Object());

			}else if("FFK".equalsIgnoreCase(paramTYP)){
				model.put(TdsConstants.RESOURCE_MODEL_KEY_CODE_FORFARANDE02_LIST, list);
				model.put(TdsConstants.URL_EXTERNAL_FORFARANDE02_CODES_TARIC_CODE, new UrlTaricForfarande02Object());

			}else if("SAL".equalsIgnoreCase(paramTYP)){
				model.put(TdsConstants.RESOURCE_MODEL_KEY_CODE_SAL_LIST,list);
				model.put(TdsConstants.URL_EXTERNAL_SALCODES_TARIC_CODE, new UrlTaricSarskildaUpplysningarObject());
				
			}else if("FOR".equalsIgnoreCase(paramTYP)){
				model.put(TdsConstants.RESOURCE_MODEL_KEY_CODE_FORMAN_LIST, list);
				model.put(TdsConstants.URL_EXTERNAL_FORMANSKODCODES_TARIC_CODE, new UrlTaricFormanskodObject());

			}else if("THO".equalsIgnoreCase(paramTYP)){
				model.put(TdsConstants.RESOURCE_MODEL_KEY_CODE_THO_LIST, list);
			
			}else if("CHN".equalsIgnoreCase(paramTYP)){
				model.put(TdsConstants.RESOURCE_MODEL_KEY_CODE_CHA_LIST, list);
				model.put(TdsConstants.URL_EXTERNAL_AVGIFTSBERAKNINGAR_CODES_TARIC_CODE, new UrlTaricAvgiftskodObject());

			}else if("MDM".equalsIgnoreCase(paramTYP)){
				model.put(TdsConstants.RESOURCE_MODEL_KEY_CODE_MDM_LIST, list);
				model.put(TdsConstants.URL_EXTERNAL_AVGIFTSBERAKNINGAR_CODES_TARIC_CODE, new UrlTaricAvgiftskodObject());

			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
