package no.systema.tds.tdsimport.util.manager;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tds.tdsexport.util.manager.TdsExportSumDiffCalculatorMgr;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicFaktTotalContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicFaktTotalRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemRecord;
import no.systema.tds.tdsimport.service.TdsImportSpecificTopicItemService;
import no.systema.tds.tdsimport.service.TdsImportSpecificTopicService;
import no.systema.tds.tdsimport.url.store.TdsImportUrlDataStore;


/**
 * 
 * @author oscardelatorre
 * @date Sep 2020
 */
@Service
public class TdsImportSumDiffCalculatorMgr {
	private static final Logger logger = Logger.getLogger(TdsImportSumDiffCalculatorMgr.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();

	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	
	
	/**
	 * Gets sum fields needed for mathematical checks (between header values and sum(item values).
	 * E.g. sumOfAntallKolli, sumFaktbelopp, sumBruttovikt, 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	public JsonTdsImportSpecificTopicRecord getSumOfSpecificFieldsInItemLines(String avd, String opd, SystemaWebUser appUser){
		//-----------------------------------------------------
		//FETCH the ITEM LIST of existent ITEMs for this TOPIC
		//-----------------------------------------------------
		JsonTdsImportSpecificTopicRecord topicRecord = new JsonTdsImportSpecificTopicRecord();
		
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL_FETCH = TdsImportUrlDataStore.TDS_IMPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
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
		logger.debug(jsonPayloadFetch);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	JsonTdsImportSpecificTopicItemContainer jsonTdsImportSpecificTopicItemContainer = this.tdsImportSpecificTopicItemService.getTdsImportSpecificTopicItemContainer(jsonPayloadFetch);
    	//now to the real logic
    	int antalKolli = 0;
    	double grossWeight = 0.00D;
    	double invoiceAmount = 0.00D;
    	int numberOfItemLines = 0;
    	if(jsonTdsImportSpecificTopicItemContainer!=null){
	    	for(JsonTdsImportSpecificTopicItemRecord record : jsonTdsImportSpecificTopicItemContainer.getOrderList()){
	    		numberOfItemLines++;
	    		//logger.info("ANTAL KOLLI Inside FOR--svivKota: " + record.getSviv_kota());
	    		if(record.getSviv_kota()!=null && !"".equals(record.getSviv_kota())){
	    			try{
	    				antalKolli += Integer.parseInt(record.getSviv_kota());
	    			}catch(Exception e){
	    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
	    			}
	    		}
	    		if(record.getSviv_kot2()!=null && !"".equals(record.getSviv_kot2())){
	    			try{
	    				antalKolli += Integer.parseInt(record.getSviv_kot2());
	    			}catch(Exception e){
	    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
	    			}
	    		}
	    		if(record.getSviv_kot3()!=null && !"".equals(record.getSviv_kot3())){
	    			try{
	    				antalKolli += Integer.parseInt(record.getSviv_kot3());
	    			}catch(Exception e){
	    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
	    			}
	    		}
	    		if(record.getSviv_kot4()!=null && !"".equals(record.getSviv_kot4())){
	    			try{
	    				antalKolli += Integer.parseInt(record.getSviv_kot4());
	    			}catch(Exception e){
	    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
	    			}
	    		}
	    		if(record.getSviv_kot5()!=null && !"".equals(record.getSviv_kot5())){
	    			try{
	    				antalKolli += Integer.parseInt(record.getSviv_kot5());
	    			}catch(Exception e){
	    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
	    			}
	    		}
	    		//gross weight
	    		if(record.getSviv_brut()!=null && !"".equals(record.getSviv_brut())){
	    			try{
	    				grossWeight += Double.parseDouble(record.getSviv_brut().replace(",", "."));
	    			}catch(Exception e){
	    				logger.info("[ERROR] on GROSS WEIGHT CATCH");
	    			}
	    		}
	    		//invoice amount
	    		if(record.getSviv_fabl()!=null && !"".equals(record.getSviv_fabl())){
	    			try{
	    				invoiceAmount += Double.parseDouble(record.getSviv_fabl().replace(",", "."));
	    			}catch(Exception e){
	    				logger.info("[ERROR] on INVOICE AMOUNT CATCH");
	    			}
	    		}
	    		
	    	}
    	}
    	//This is to flag the fact that no antal kolli exist DESPITE the fact that there is 1 or more item lines
    	//to be used in validation...
    	if(numberOfItemLines>0 && antalKolli==0){ antalKolli = -1; }
    	//DEBUG
    	//logger.info("AntalKolli(sum): " + antalKolli);
    	logger.info("Bruttovikt(sum): " + grossWeight);
    	//logger.info("Fakt.belopp(sum): " + invoiceAmount);
    
    	topicRecord.setSumOfAntalKolliInItemLines(antalKolli);
    	topicRecord.setSumOfGrossWeightInItemLines(grossWeight);
    	topicRecord.setSumOfInvoiceAmountInItemLines(invoiceAmount);
    	
    	return topicRecord;
	}
	
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	public JsonTdsImportSpecificTopicFaktTotalRecord getInvoiceTotalFromInvoices( String avd, String opd, SystemaWebUser appUser){
		//--------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		JsonTdsImportSpecificTopicFaktTotalRecord returnRecord = null;
		
		String BASE_URL_FETCH = TdsImportUrlDataStore.TDS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_FAKT_TOTAL_URL;
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
		logger.debug(jsonPayload);
		
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	JsonTdsImportSpecificTopicFaktTotalContainer container = this.tdsImportSpecificTopicService.getTdsImportSpecificTopicFaktTotalContainer(jsonPayload);
    	if(container!=null){
	    	for(JsonTdsImportSpecificTopicFaktTotalRecord record : container.getInvTot()){
				 returnRecord = record;
	    	}
    	}
		
		return returnRecord;
	}
	
	
	@Autowired
	private TdsImportSpecificTopicService tdsImportSpecificTopicService;
	
	@Autowired 
	private TdsImportSpecificTopicItemService tdsImportSpecificTopicItemService;
	
	
}
