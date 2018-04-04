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
import no.systema.tds.tdsexport.model.jsonjackson.topic.logging.JsonTdsExportSpecificTopicLoggingContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.logging.JsonTdsExportSpecificTopicLoggingRecord;

/**
 * @author oscardelatorre
 * @date June 28, 2013
 * 
 */
public class TdsExportSpecificTopicLoggingMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsExportSpecificTopicLoggingMapper.class.getName());
	
	public JsonTdsExportSpecificTopicLoggingContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsExportSpecificTopicLoggingContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsExportSpecificTopicLoggingContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonTdsExportSpecificTopicLoggingRecord> list = container.getLogg();
		for(JsonTdsExportSpecificTopicLoggingRecord record : list){
			logger.info("Message nr (topic logging): " + record.getMmn());
		}
		return container;
	}
}
