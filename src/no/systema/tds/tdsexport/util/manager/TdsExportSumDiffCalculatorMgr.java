package no.systema.tds.tdsexport.util.manager;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicFaktTotalContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicFaktTotalRecord;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicRecord;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemRecord;
import no.systema.tds.tdsexport.service.TdsExportSpecificTopicItemService;
import no.systema.tds.tdsexport.service.TdsExportSpecificTopicService;
import no.systema.tds.tdsexport.url.store.TdsExportUrlDataStore;


@Service
public class TdsExportSumDiffCalculatorMgr {
	private static final Logger logger = Logger.getLogger(TdsExportSumDiffCalculatorMgr.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();

	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	
	@Autowired
	private TdsExportSpecificTopicItemService tdsExportSpecificTopicItemService;
	
	@Autowired
	private TdsExportSpecificTopicService tdsExportSpecificTopicService;
	
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	public JsonTdsExportSpecificTopicRecord getSumOfSpecificFieldsInItemLines(String avd, String opd, SystemaWebUser appUser){
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
		logger.info(jsonPayloadFetch);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	JsonTdsExportSpecificTopicItemContainer jsonTdsExportSpecificTopicItemContainer = this.tdsExportSpecificTopicItemService.getTdsExportSpecificTopicItemContainer(jsonPayloadFetch);
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
    	topicRecord.setSumOfGrossWeightInItemLines(this.numberFormatter.getDouble(grossWeight, 3));
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
	public JsonTdsExportSpecificTopicFaktTotalRecord getInvoiceTotalFromInvoices( String avd, String opd, SystemaWebUser appUser){
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
		logger.info(jsonPayload);
		
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	JsonTdsExportSpecificTopicFaktTotalContainer container = this.tdsExportSpecificTopicService.getTdsExportSpecificTopicFaktTotalContainer(jsonPayload);
    	if(container!=null){
	    	for(JsonTdsExportSpecificTopicFaktTotalRecord record : container.getInvTot()){
				returnRecord = record;
	    	}
    	}
		
		return returnRecord;
	}
	
	
	
}
