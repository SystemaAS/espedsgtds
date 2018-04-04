/**
 * 
 */
package no.systema.tds.tdsimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicCopiedContainer;

/**
 * @author oscardelatorre
 * 
 */
public class TdsImportTopicCopiedMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsImportTopicCopiedMapper.class.getName());
	
	public JsonTdsImportTopicCopiedContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsImportTopicCopiedContainer topicListContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsImportTopicCopiedContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		
		
		return topicListContainer;
	}
}
