/**
 * 
 */
package no.systema.tds.tdsimport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.tdsimport.model.jsonjackson.topic.logging.JsonTdsImportSpecificTopicLoggingContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.logging.JsonTdsImportSpecificTopicLoggingRecord;

/**
 * @author oscardelatorre
 * @date Sep 2, 2013
 * 
 */
public class TdsImportSpecificTopicLoggingMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsImportSpecificTopicLoggingMapper.class.getName());
	
	public JsonTdsImportSpecificTopicLoggingContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsImportSpecificTopicLoggingContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsImportSpecificTopicLoggingContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonTdsImportSpecificTopicLoggingRecord> list = container.getLogg();
		for(JsonTdsImportSpecificTopicLoggingRecord record : list){
			logger.info("Message nr (topic logging): " + record.getMmn());
		}
		return container;
	}
}
