/**
 * 
 */
package no.systema.tds.nctsimport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.nctsimport.model.jsonjackson.topic.logging.JsonNctsImportSpecificTopicLoggingContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.logging.JsonNctsImportSpecificTopicLoggingRecord;

/**
 * @author oscardelatorre
 * @date Apr 4, 2014
 * 
 */
public class NctsImportSpecificTopicLoggingMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(NctsImportSpecificTopicLoggingMapper.class.getName());
	
	public JsonNctsImportSpecificTopicLoggingContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonNctsImportSpecificTopicLoggingContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonNctsImportSpecificTopicLoggingContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonNctsImportSpecificTopicLoggingRecord> list = container.getLogg();
		for(JsonNctsImportSpecificTopicLoggingRecord record : list){
			logger.info("Message nr (topic logging): " + record.getMmn());
		}
		return container;
	}
}
