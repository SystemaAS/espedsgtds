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
import no.systema.tds.tdsimport.model.jsonjackson.topic.logging.JsonTdsImportSpecificTopicLoggingLargeTextContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.logging.JsonTdsImportSpecificTopicLoggingLargeTextRecord;

/**
 * @author oscardelatorre
 * @date Sep 2, 2013
 * 
 */
public class TdsImportSpecificTopicLoggingLargeTextMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsImportSpecificTopicLoggingLargeTextMapper.class.getName());
	
	public JsonTdsImportSpecificTopicLoggingLargeTextContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsImportSpecificTopicLoggingLargeTextContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsImportSpecificTopicLoggingLargeTextContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonTdsImportSpecificTopicLoggingLargeTextRecord> list = container.getLoggtext();
		for(JsonTdsImportSpecificTopicLoggingLargeTextRecord record : list){
			logger.info("Message nr (topic logging): " + record.getF0078a());
		}
		return container;
	}
}
