/**
 * 
 */
package no.systema.tds.tdsexport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.tdsexport.model.jsonjackson.topic.logging.JsonTdsExportSpecificTopicLoggingLargeTextContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.logging.JsonTdsExportSpecificTopicLoggingLargeTextRecord;

/**
 * @author oscardelatorre
 * @date Aug 6, 2013
 * 
 */
public class TdsExportSpecificTopicLoggingLargeTextMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsExportSpecificTopicLoggingLargeTextMapper.class.getName());
	
	public JsonTdsExportSpecificTopicLoggingLargeTextContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsExportSpecificTopicLoggingLargeTextContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsExportSpecificTopicLoggingLargeTextContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonTdsExportSpecificTopicLoggingLargeTextRecord> list = container.getLoggtext();
		for(JsonTdsExportSpecificTopicLoggingLargeTextRecord record : list){
			logger.info("Message nr (topic logging): " + record.getF0078a());
		}
		return container;
	}
}
