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
import no.systema.tds.nctsimport.model.jsonjackson.topic.logging.JsonNctsImportSpecificTopicLoggingLargeTextContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.logging.JsonNctsImportSpecificTopicLoggingLargeTextRecord;

/**
 * @author oscardelatorre
 * @date Apr 4, 2014
 * 
 */
public class NctsImportSpecificTopicLoggingLargeTextMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(NctsImportSpecificTopicLoggingLargeTextMapper.class.getName());
	
	public JsonNctsImportSpecificTopicLoggingLargeTextContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonNctsImportSpecificTopicLoggingLargeTextContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonNctsImportSpecificTopicLoggingLargeTextContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonNctsImportSpecificTopicLoggingLargeTextRecord> list = container.getLoggtext();
		for(JsonNctsImportSpecificTopicLoggingLargeTextRecord record : list){
			logger.info("Message nr (topic logging): " + record.getF0078a());
		}
		return container;
	}
}
