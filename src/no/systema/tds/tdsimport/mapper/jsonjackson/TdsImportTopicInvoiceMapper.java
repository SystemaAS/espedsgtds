/**
 * 
 */
package no.systema.tds.tdsimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicInvoiceContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicInvoiceExternalContainer;
//application library
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicInvoiceExternalForUpdateContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicInvoiceExternalRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicInvoiceRecord;


/**
 * 
 * @author oscardelatorre
 * @date Oct 23, 2015
 * 
 * 
 */
public class TdsImportTopicInvoiceMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsImportTopicInvoiceMapper.class.getName());
	
	public JsonTdsImportTopicInvoiceContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsImportTopicInvoiceContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsImportTopicInvoiceContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonTdsImportTopicInvoiceRecord record : container.getInvList()){
			//DEBUG
			//logger.info(record.getSftxt());
		}
		return container;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsImportTopicInvoiceContainer getContainerOneInvoice(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsImportTopicInvoiceContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsImportTopicInvoiceContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("USER:" + container.getUser());
		for (JsonTdsImportTopicInvoiceRecord record : container.getOneInvoice()){
			//DEBUG
			//logger.info(record.getSvif_fatx());
		}
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsImportTopicInvoiceExternalContainer getContainerInvoiceExternal(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsImportTopicInvoiceExternalContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsImportTopicInvoiceExternalContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonTdsImportTopicInvoiceExternalRecord record : container.getListexternfakt()){
			//DEBUG
			//logger.info(record.getSftxt());
		}
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsImportTopicInvoiceExternalContainer getContainerOneInvoiceInvoiceExternal(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsImportTopicInvoiceExternalContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsImportTopicInvoiceExternalContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonTdsImportTopicInvoiceExternalRecord record : container.getGetexternfakt()){
			//DEBUG
			//logger.info(record.getSftxt());
		}
		return container;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsImportTopicInvoiceExternalForUpdateContainer getContainerOneInvoiceInvoiceExternalForUpdate(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsImportTopicInvoiceExternalForUpdateContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsImportTopicInvoiceExternalForUpdateContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("User:" + container.getUser());
		
		return container;
	}
}


