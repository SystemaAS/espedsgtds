/**
 * 
 */
package no.systema.tds.nctsexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportTopicCopiedContainer;

/**
 * @author oscardelatorre
 * 
 */
public class NctsExportTopicCopiedMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(NctsExportTopicCopiedMapper.class.getName());
	
	public JsonNctsExportTopicCopiedContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonNctsExportTopicCopiedContainer topicListContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonNctsExportTopicCopiedContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		
		return topicListContainer;
	}
}
