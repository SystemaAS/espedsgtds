/**
 * 
 */
package no.systema.tds.nctsimport.service;

import no.systema.tds.nctsimport.mapper.jsonjackson.NctsImportSpecificTopicArchiveMapper;
import no.systema.tds.nctsimport.mapper.jsonjackson.NctsImportSpecificTopicLoggingLargeTextMapper;
import no.systema.tds.nctsimport.mapper.jsonjackson.NctsImportSpecificTopicLoggingMapper;
import no.systema.tds.nctsimport.mapper.jsonjackson.NctsImportSpecificTopicMapper;
import no.systema.tds.nctsimport.model.jsonjackson.topic.archive.JsonNctsImportSpecificTopicArchiveContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.logging.JsonNctsImportSpecificTopicLoggingContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.logging.JsonNctsImportSpecificTopicLoggingLargeTextContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.JsonNctsImportSpecificTopicContainer;

/**
 * @author oscardelatorre
 * @date Dec 4, 2013
 */
public class NctsImportSpecificTopicServiceImpl implements NctsImportSpecificTopicService{
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 * 
	 */
	public JsonNctsImportSpecificTopicContainer getNctsImportSpecificTopicContainer(String utfPayload) {
		JsonNctsImportSpecificTopicContainer container = null;
		try{
			NctsImportSpecificTopicMapper mapper = new NctsImportSpecificTopicMapper();
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
	public JsonNctsImportSpecificTopicLoggingContainer getNctsImportSpecificTopicLoggingContainer(String utfPayload) {
		JsonNctsImportSpecificTopicLoggingContainer container = null;
		try{
			NctsImportSpecificTopicLoggingMapper mapper = new NctsImportSpecificTopicLoggingMapper();
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
	public JsonNctsImportSpecificTopicLoggingLargeTextContainer getNctsImportSpecificTopicLoggingLargeTextContainer(String utfPayload){
		JsonNctsImportSpecificTopicLoggingLargeTextContainer container = null;
		try{
			NctsImportSpecificTopicLoggingLargeTextMapper mapper = new NctsImportSpecificTopicLoggingLargeTextMapper();
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
	public JsonNctsImportSpecificTopicArchiveContainer getNctsImportSpecificTopicArchiveContainer(String utfPayload) {
		JsonNctsImportSpecificTopicArchiveContainer container = null;
		try{
			NctsImportSpecificTopicArchiveMapper mapper = new NctsImportSpecificTopicArchiveMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}	

	
}
