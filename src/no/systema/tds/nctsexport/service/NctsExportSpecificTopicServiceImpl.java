/**
 * 
 */
package no.systema.tds.nctsexport.service;

import no.systema.tds.nctsexport.mapper.jsonjackson.NctsExportSpecificTopicMapper;
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportSpecificTopicContainer;
import no.systema.tds.nctsexport.mapper.jsonjackson.NctsExportSpecificTopicGuaranteeValidatorMapper;
import no.systema.tds.nctsexport.mapper.jsonjackson.NctsExportSpecificTopicArchiveMapper;
import no.systema.tds.nctsexport.mapper.jsonjackson.NctsExportSpecificTopicLoggingLargeTextMapper;
import no.systema.tds.nctsexport.mapper.jsonjackson.NctsExportSpecificTopicLoggingMapper;
import no.systema.tds.nctsexport.mapper.jsonjackson.NctsExportTopicCopiedMapper;
import no.systema.tds.nctsexport.mapper.jsonjackson.NctsExportTopicCopiedFromTransportUppdragMapper;

import no.systema.tds.nctsexport.model.jsonjackson.topic.archive.JsonNctsExportSpecificTopicArchiveContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.logging.JsonNctsExportSpecificTopicLoggingContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.logging.JsonNctsExportSpecificTopicLoggingLargeTextContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.validation.JsonNctsExportSpecificTopicGuaranteeValidatorContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportTopicCopiedContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportTopicCopiedFromTransportUppdragContainer;


/**
 * @author oscardelatorre
 * @date Aug 7, 2013
 */
public class NctsExportSpecificTopicServiceImpl implements NctsExportSpecificTopicService{
	public JsonNctsExportSpecificTopicContainer getNctsExportSpecificTopicContainer(String utfPayload) {
		JsonNctsExportSpecificTopicContainer jsonNctsExportSpecificTopicContainer = null;
		try{
			NctsExportSpecificTopicMapper nctsExportSpecificTopicMapper = new NctsExportSpecificTopicMapper();
			jsonNctsExportSpecificTopicContainer = nctsExportSpecificTopicMapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonNctsExportSpecificTopicContainer;
		
	}
	
	/**
	 * Archive
	 * @param utfPayload
	 * @return
	 */
	public JsonNctsExportSpecificTopicArchiveContainer getNctsExportSpecificTopicArchiveContainer(String utfPayload) {
		JsonNctsExportSpecificTopicArchiveContainer container = null;
		try{
			NctsExportSpecificTopicArchiveMapper mapper = new NctsExportSpecificTopicArchiveMapper();
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
	public JsonNctsExportSpecificTopicLoggingContainer getNctsExportSpecificTopicLoggingContainer(String utfPayload) {
		JsonNctsExportSpecificTopicLoggingContainer container = null;
		try{
			NctsExportSpecificTopicLoggingMapper mapper = new NctsExportSpecificTopicLoggingMapper();
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
	public JsonNctsExportSpecificTopicLoggingLargeTextContainer getNctsExportSpecificTopicLoggingLargeTextContainer(String utfPayload){
		JsonNctsExportSpecificTopicLoggingLargeTextContainer container = null;
		try{
			NctsExportSpecificTopicLoggingLargeTextMapper mapper = new NctsExportSpecificTopicLoggingLargeTextMapper();
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
	public JsonNctsExportSpecificTopicGuaranteeValidatorContainer getNctsExportSpecificTopicGuaranteeValidatorContainer(String utfPayload){
		JsonNctsExportSpecificTopicGuaranteeValidatorContainer container = null;
		try{
			NctsExportSpecificTopicGuaranteeValidatorMapper mapper = new NctsExportSpecificTopicGuaranteeValidatorMapper();
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
	public JsonNctsExportTopicCopiedContainer getNctsExportTopicCopiedContainer(String utfPayload){
		JsonNctsExportTopicCopiedContainer container = null;
		try{
			NctsExportTopicCopiedMapper mapper = new NctsExportTopicCopiedMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
	public JsonNctsExportTopicCopiedFromTransportUppdragContainer getNctsExportTopicCopiedFromTransportUppdragContainer(String utfPayload){
		JsonNctsExportTopicCopiedFromTransportUppdragContainer container = null;
		try{
			NctsExportTopicCopiedFromTransportUppdragMapper mapper = new NctsExportTopicCopiedFromTransportUppdragMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
	
	
	
}
