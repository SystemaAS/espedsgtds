/**
 * 
 */
package no.systema.tds.tdsimport.service;

import org.apache.log4j.Logger;

import no.systema.tds.tdsimport.mapper.jsonjackson.TdsImportSpecificTopicMapper;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicCheckItemErrorContainer;
import no.systema.tds.tdsimport.controller.ajax.TdsImportAjaxHandlerController;
import no.systema.tds.tdsimport.mapper.jsonjackson.TdsImportTopicCopiedFromTransportUppdragMapper;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicCopiedFromTransportUppdragContainer;
import no.systema.tds.tdsimport.mapper.jsonjackson.TdsImportSpecificTopicMapper;
import no.systema.tds.tdsimport.mapper.jsonjackson.TdsImportTopicCopiedMapper;
import no.systema.tds.tdsimport.mapper.jsonjackson.TdsImportSpecificTopicLoggingMapper;
import no.systema.tds.tdsimport.mapper.jsonjackson.TdsImportSpecificTopicLoggingLargeTextMapper;
import no.systema.tds.tdsimport.mapper.jsonjackson.TdsImportSpecificTopicArchiveMapper;
import no.systema.tds.tdsimport.mapper.jsonjackson.TdsImportTopicInvoiceMapper;



import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicFaktTotalContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicCopiedContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicOmbudContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicInvoiceContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicInvoiceExternalForUpdateContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicInvoiceRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicInvoiceExternalContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicInvoiceExternalRecord;

import no.systema.tds.tdsimport.model.jsonjackson.topic.logging.JsonTdsImportSpecificTopicLoggingContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.logging.JsonTdsImportSpecificTopicLoggingLargeTextContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.archive.JsonTdsImportSpecificTopicArchiveContainer;

/**
 * @author oscardelatorre
 *
 */
public class TdsImportSpecificTopicServiceImpl implements TdsImportSpecificTopicService{
	private static final Logger logger = Logger.getLogger(TdsImportSpecificTopicServiceImpl.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 *  
	 */
	public JsonTdsImportSpecificTopicContainer getTdsImportSpecificTopicContainer(String utfPayload) {
		JsonTdsImportSpecificTopicContainer container = null;
		try{
			TdsImportSpecificTopicMapper mapper = new TdsImportSpecificTopicMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonTdsImportSpecificTopicOmbudContainer getTdsImportSpecificTopicOmbudContainer(String utfPayload) {
		JsonTdsImportSpecificTopicOmbudContainer container = null;
		try{
			TdsImportSpecificTopicMapper mapper = new TdsImportSpecificTopicMapper();
			container = mapper.getOmbudContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	/**
	 * 
	 */
	public JsonTdsImportSpecificTopicFaktTotalContainer getTdsImportSpecificTopicFaktTotalContainer (String utfPayload){
		JsonTdsImportSpecificTopicFaktTotalContainer container = null;
		try{
			TdsImportSpecificTopicMapper mapper = new TdsImportSpecificTopicMapper();
			container = mapper.getFaktTotalContainer(utfPayload);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
		
	}
	
	
	/**
	 * Archive
	 * @param utfPayload
	 * @return
	 */
	public JsonTdsImportSpecificTopicArchiveContainer getTdsImportSpecificTopicArchiveContainer(String utfPayload) {
		JsonTdsImportSpecificTopicArchiveContainer container = null;
		try{
			TdsImportSpecificTopicArchiveMapper mapper = new TdsImportSpecificTopicArchiveMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

	/**
	 * Logging
	 * @param utfPayload
	 * @return
	 */
	public JsonTdsImportSpecificTopicLoggingContainer getTdsImportSpecificTopicLoggingContainer(String utfPayload) {
		JsonTdsImportSpecificTopicLoggingContainer container = null;
		try{
			TdsImportSpecificTopicLoggingMapper mapper = new TdsImportSpecificTopicLoggingMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	/**
	 * Show large text
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonTdsImportSpecificTopicLoggingLargeTextContainer getTdsImportSpecificTopicLoggingLargeTextContainer(String utfPayload){
		JsonTdsImportSpecificTopicLoggingLargeTextContainer container = null;
		try{
			TdsImportSpecificTopicLoggingLargeTextMapper mapper = new TdsImportSpecificTopicLoggingLargeTextMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
	/**
	 * Cloned record
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonTdsImportTopicCopiedContainer getTdsImportTopicCopiedContainer(String utfPayload){
		JsonTdsImportTopicCopiedContainer container = null;
		try{
			TdsImportTopicCopiedMapper mapper = new TdsImportTopicCopiedMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
	/**
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonTdsImportTopicCopiedFromTransportUppdragContainer getTdsImportTopicCopiedFromTransportUppdragContainer(String utfPayload){
		JsonTdsImportTopicCopiedFromTransportUppdragContainer container = null;
		try{
			TdsImportTopicCopiedFromTransportUppdragMapper mapper = new TdsImportTopicCopiedFromTransportUppdragMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;	
	}
	
	/**
	 * 
	 */
	public JsonTdsImportTopicInvoiceContainer getTdsImportTopicInvoiceContainerContainer (String utfPayload){
		JsonTdsImportTopicInvoiceContainer container = null;
		try{
			TdsImportTopicInvoiceMapper mapper = new TdsImportTopicInvoiceMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
		
	}
	/**
	 * 
	 */
	public JsonTdsImportTopicInvoiceContainer getTdsImportTopicInvoiceContainerOneInvoice (String utfPayload){
		JsonTdsImportTopicInvoiceContainer container = null;
		try{
			TdsImportTopicInvoiceMapper mapper = new TdsImportTopicInvoiceMapper();
			container = mapper.getContainerOneInvoice(utfPayload);
			for(JsonTdsImportTopicInvoiceRecord record : container.getOneInvoice()){
				//DEBUG
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
		
	}
	/**
	 * External invoices
	 * 
	 */
	public JsonTdsImportTopicInvoiceExternalContainer getTdsImportTopicInvoiceContainerContainerExternal (String utfPayload){
		JsonTdsImportTopicInvoiceExternalContainer container = null;
		try{
			TdsImportTopicInvoiceMapper mapper = new TdsImportTopicInvoiceMapper();
			container = mapper.getContainerInvoiceExternal(utfPayload);
			for(JsonTdsImportTopicInvoiceExternalRecord record : container.getListexternfakt()){
				//DEBUG
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	/**
	 * External invoices
	 * 
	 */
	public JsonTdsImportTopicInvoiceExternalContainer getTdsImportTopicInvoiceContainerOneInvoiceExternal (String utfPayload){
		JsonTdsImportTopicInvoiceExternalContainer container = null;
		try{
			TdsImportTopicInvoiceMapper mapper = new TdsImportTopicInvoiceMapper();
			container = mapper.getContainerInvoiceExternal(utfPayload);
			for(JsonTdsImportTopicInvoiceExternalRecord record : container.getGetexternfakt()){
				//DEBUG
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * For update
	 */
	public JsonTdsImportTopicInvoiceExternalForUpdateContainer getTdsImportTopicInvoiceContainerOneInvoiceExternalForUpdate (String utfPayload){
		JsonTdsImportTopicInvoiceExternalForUpdateContainer container = null;
		try{
			TdsImportTopicInvoiceMapper mapper = new TdsImportTopicInvoiceMapper();
			container = mapper.getContainerOneInvoiceInvoiceExternalForUpdate(utfPayload);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
		
	}
	/**
	 * 
	 */
	public JsonTdsImportSpecificTopicCheckItemErrorContainer getCheckItemErrorContainer(String utfPayload){
		JsonTdsImportSpecificTopicCheckItemErrorContainer container = null;
		try{
			TdsImportSpecificTopicMapper mapper = new TdsImportSpecificTopicMapper();
			container = mapper.getCheckItemErrorContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	
	
}
