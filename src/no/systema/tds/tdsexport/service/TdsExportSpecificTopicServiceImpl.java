/**
 * 
 */
package no.systema.tds.tdsexport.service;

import no.systema.tds.nctsexport.mapper.jsonjackson.NctsExportSpecificTopicGuaranteeValidatorMapper;
import no.systema.tds.nctsexport.model.jsonjackson.topic.validation.JsonNctsExportSpecificTopicGuaranteeValidatorContainer;
import no.systema.tds.tdsexport.mapper.jsonjackson.TdsExportSpecificTopicMapper;
import no.systema.tds.tdsexport.mapper.jsonjackson.TdsExportTopicCopiedMapper;
import no.systema.tds.tdsexport.mapper.jsonjackson.TdsExportTopicCopiedFromTransportUppdragMapper;

import no.systema.tds.tdsexport.mapper.jsonjackson.TdsExportSpecificTopicLoggingMapper;
import no.systema.tds.tdsexport.mapper.jsonjackson.TdsExportSpecificTopicLoggingLargeTextMapper;
import no.systema.tds.tdsexport.mapper.jsonjackson.TdsExportSpecificTopicArchiveMapper;
import no.systema.tds.tdsexport.mapper.jsonjackson.TdsExportTopicInvoiceMapper;


import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicFaktTotalContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicCopiedContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicCopiedFromTransportUppdragContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicInvoiceContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicInvoiceExternalContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicInvoiceExternalForUpdateContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicCheckItemErrorContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicOmbudContainer;

import no.systema.tds.tdsexport.model.jsonjackson.topic.logging.JsonTdsExportSpecificTopicLoggingContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.logging.JsonTdsExportSpecificTopicLoggingLargeTextContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.archive.JsonTdsExportSpecificTopicArchiveContainer;

/**
 * @author oscardelatorre
 *
 */
public class TdsExportSpecificTopicServiceImpl implements TdsExportSpecificTopicService{
	/**
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonTdsExportSpecificTopicContainer getTdsExportSpecificTopicContainer(String utfPayload) {
		JsonTdsExportSpecificTopicContainer jsonTdsExportSpecificTopicContainer = null;
		try{
			TdsExportSpecificTopicMapper tdsExportSpecificTopicMapper = new TdsExportSpecificTopicMapper();
			jsonTdsExportSpecificTopicContainer = tdsExportSpecificTopicMapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonTdsExportSpecificTopicContainer;
		
	}
	
	/**
	 * @param utfPayload
	 * @return
	 */
	public JsonTdsExportSpecificTopicOmbudContainer getTdsExportSpecificTopicOmbudContainer(String utfPayload) {
		JsonTdsExportSpecificTopicOmbudContainer jsonTdsExportSpecificTopicOmbudContainer = null;
		try{
			TdsExportSpecificTopicMapper tdsExportSpecificTopicMapper = new TdsExportSpecificTopicMapper();
			jsonTdsExportSpecificTopicOmbudContainer = tdsExportSpecificTopicMapper.getOmbudContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonTdsExportSpecificTopicOmbudContainer;
		
	}
	/**
	 * 
	 */
	public JsonTdsExportSpecificTopicFaktTotalContainer getTdsExportSpecificTopicFaktTotalContainer (String utfPayload){
		JsonTdsExportSpecificTopicFaktTotalContainer container = null;
		try{
			TdsExportSpecificTopicMapper mapper = new TdsExportSpecificTopicMapper();
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
	public JsonTdsExportSpecificTopicArchiveContainer getTdsExportSpecificTopicArchiveContainer(String utfPayload) {
		JsonTdsExportSpecificTopicArchiveContainer container = null;
		try{
			TdsExportSpecificTopicArchiveMapper mapper = new TdsExportSpecificTopicArchiveMapper();
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
	public JsonTdsExportSpecificTopicLoggingContainer getTdsExportSpecificTopicLoggingContainer(String utfPayload) {
		JsonTdsExportSpecificTopicLoggingContainer container = null;
		try{
			TdsExportSpecificTopicLoggingMapper mapper = new TdsExportSpecificTopicLoggingMapper();
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
	public JsonTdsExportSpecificTopicLoggingLargeTextContainer getTdsExportSpecificTopicLoggingLargeTextContainer(String utfPayload){
		JsonTdsExportSpecificTopicLoggingLargeTextContainer container = null;
		try{
			TdsExportSpecificTopicLoggingLargeTextMapper mapper = new TdsExportSpecificTopicLoggingLargeTextMapper();
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
	public JsonTdsExportTopicCopiedContainer getTdsExportTopicCopiedContainer(String utfPayload){
		JsonTdsExportTopicCopiedContainer container = null;
		try{
			TdsExportTopicCopiedMapper mapper = new TdsExportTopicCopiedMapper();
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
	public JsonTdsExportTopicCopiedFromTransportUppdragContainer getTdsExportTopicCopiedFromTransportUppdragContainer(String utfPayload){
		JsonTdsExportTopicCopiedFromTransportUppdragContainer container = null;
		try{
			TdsExportTopicCopiedFromTransportUppdragMapper mapper = new TdsExportTopicCopiedFromTransportUppdragMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	/**
	 * 
	 */
	public JsonTdsExportTopicInvoiceContainer getTdsExportTopicInvoiceContainerContainer (String utfPayload){
		JsonTdsExportTopicInvoiceContainer container = null;
		try{
			TdsExportTopicInvoiceMapper mapper = new TdsExportTopicInvoiceMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	/**
	 * 
	 */
	public JsonTdsExportTopicInvoiceContainer getTdsExportTopicInvoiceContainerOneInvoice (String utfPayload){
		JsonTdsExportTopicInvoiceContainer container = null;
		try{
			TdsExportTopicInvoiceMapper mapper = new TdsExportTopicInvoiceMapper();
			container = mapper.getContainerOneInvoice(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * External invoices
	 */
	public JsonTdsExportTopicInvoiceExternalContainer getTdsExportTopicInvoiceContainerContainerExternal (String utfPayload){
		JsonTdsExportTopicInvoiceExternalContainer container = null;
		try{
			TdsExportTopicInvoiceMapper mapper = new TdsExportTopicInvoiceMapper();
			container = mapper.getContainerInvoiceExternal(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	/**
	 * External invoices
	 * 
	 */
	public JsonTdsExportTopicInvoiceExternalContainer getTdsExportTopicInvoiceContainerOneInvoiceExternal (String utfPayload){
		JsonTdsExportTopicInvoiceExternalContainer container = null;
		try{
			TdsExportTopicInvoiceMapper mapper = new TdsExportTopicInvoiceMapper();
			container = mapper.getContainerOneInvoiceInvoiceExternal(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
		
	}
	
	/**
	 * Update of external invoice (import it to normal invoice list)
	 * @param utfPayload
	 * 
	 */
	public JsonTdsExportTopicInvoiceExternalForUpdateContainer getTdsExportTopicInvoiceContainerOneInvoiceExternalForUpdate (String utfPayload){
		JsonTdsExportTopicInvoiceExternalForUpdateContainer container = null;
		try{
			TdsExportTopicInvoiceMapper mapper = new TdsExportTopicInvoiceMapper();
			container = mapper.getContainerOneInvoiceInvoiceExternalForUpdate(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * 
	 */
	public JsonTdsExportSpecificTopicCheckItemErrorContainer getCheckItemErrorContainer(String utfPayload){
		JsonTdsExportSpecificTopicCheckItemErrorContainer container = null;
		try{
			TdsExportSpecificTopicMapper mapper = new TdsExportSpecificTopicMapper();
			container = mapper.getCheckItemErrorContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	
}
