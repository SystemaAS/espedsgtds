/**
 * 
 */
package no.systema.tds.tdsexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicInvoiceContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicInvoiceExternalContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicInvoiceExternalForUpdateContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicInvoiceExternalRecord;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicInvoiceRecord;


/**
 * 
 * @author oscardelatorre
 * @date Oct 26, 2015
 * 
 * 
 */
public class TdsExportTopicInvoiceMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsExportTopicInvoiceMapper.class.getName());
	
	public JsonTdsExportTopicInvoiceContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsExportTopicInvoiceContainer topicListContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsExportTopicInvoiceContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("User:" + topicListContainer.getUser());
		for (JsonTdsExportTopicInvoiceRecord record : topicListContainer.getInvList()){
			//DEBUG
			//logger.info(record.getSftxt());
		}
		return topicListContainer;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsExportTopicInvoiceContainer getContainerOneInvoice(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsExportTopicInvoiceContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsExportTopicInvoiceContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("User:" + container.getUser());
		//logger.info("oneInvoice:" + container.getOneInvoice());
		for (JsonTdsExportTopicInvoiceRecord record : container.getOneInvoice()){
			//DEBUG
			//logger.info(record.getSftxt());
		}
		return container;
	}
	
	/**
	 * External invoice
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsExportTopicInvoiceExternalContainer getContainerInvoiceExternal(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsExportTopicInvoiceExternalContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsExportTopicInvoiceExternalContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("User:" + container.getUser());
		for (JsonTdsExportTopicInvoiceExternalRecord record : container.getListexternfakt()){
			//DEBUG
			//logger.info(record.getSftxt());
		}
		return container;
	}
	
	/**
	 * Get one specific external invoice
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsExportTopicInvoiceExternalContainer getContainerOneInvoiceInvoiceExternal(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsExportTopicInvoiceExternalContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsExportTopicInvoiceExternalContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("User:" + container.getUser());
		for (JsonTdsExportTopicInvoiceExternalRecord record : container.getGetexternfakt()){
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
	public JsonTdsExportTopicInvoiceExternalForUpdateContainer getContainerOneInvoiceInvoiceExternalForUpdate(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsExportTopicInvoiceExternalForUpdateContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsExportTopicInvoiceExternalForUpdateContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("User:" + container.getUser());
		
		return container;
	}
}


