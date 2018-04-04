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
import no.systema.tds.nctsexport.model.jsonjackson.topic.logging.JsonNctsExportSpecificTopicLoggingContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.logging.JsonNctsExportSpecificTopicLoggingRecord;

/**
 * @author oscardelatorre
 * @date Aug 7, 2013
 * 
 */
public class NctsExportSpecificTopicLoggingMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(NctsExportSpecificTopicLoggingMapper.class.getName());
	
	public JsonNctsExportSpecificTopicLoggingContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonNctsExportSpecificTopicLoggingContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonNctsExportSpecificTopicLoggingContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonNctsExportSpecificTopicLoggingRecord> list = container.getLogg();
		for(JsonNctsExportSpecificTopicLoggingRecord record : list){
			logger.info("Message nr (topic logging): " + record.getMmn());
		}
		return container;
	}
}
