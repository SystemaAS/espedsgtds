/**
 * 
 */
package no.systema.tds.nctsexport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.nctsexport.model.jsonjackson.topic.logging.JsonNctsExportSpecificTopicLoggingLargeTextContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.logging.JsonNctsExportSpecificTopicLoggingLargeTextRecord;

/**
 * @author oscardelatorre
 * @date Aug 7, 2013
 * 
 */
public class NctsExportSpecificTopicLoggingLargeTextMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(NctsExportSpecificTopicLoggingLargeTextMapper.class.getName());
	
	public JsonNctsExportSpecificTopicLoggingLargeTextContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonNctsExportSpecificTopicLoggingLargeTextContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonNctsExportSpecificTopicLoggingLargeTextContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonNctsExportSpecificTopicLoggingLargeTextRecord> list = container.getLoggtext();
		for(JsonNctsExportSpecificTopicLoggingLargeTextRecord record : list){
			logger.info("Message nr (topic logging): " + record.getF0078a());
		}
		return container;
	}
}
