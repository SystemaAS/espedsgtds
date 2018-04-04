/**
 * 
 */
package no.systema.tds.tdsimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicUtlamListContainer;

/**
 * @author oscardelatorre
 * 
 */
public class TdsImportTopicUtlamListMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsImportTopicUtlamListMapper.class.getName());
	
	public JsonTdsImportTopicUtlamListContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsImportTopicUtlamListContainer topicListContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsImportTopicUtlamListContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		//logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		return topicListContainer;
	}
}
