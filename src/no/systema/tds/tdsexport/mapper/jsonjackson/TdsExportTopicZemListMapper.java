/**
 * 
 */
package no.systema.tds.tdsexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicZemListContainer;

/**
 * @author oscardelatorre
 * 
 */
public class TdsExportTopicZemListMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsExportTopicZemListMapper.class.getName());
	
	public JsonTdsExportTopicZemListContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsExportTopicZemListContainer topicListContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsExportTopicZemListContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		//logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		return topicListContainer;
	}
}
