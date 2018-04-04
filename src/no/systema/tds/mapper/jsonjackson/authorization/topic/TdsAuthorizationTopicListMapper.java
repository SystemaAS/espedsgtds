/**
 * 
 */
package no.systema.tds.mapper.jsonjackson.authorization.topic;

import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.model.jsonjackson.authorization.topic.JsonTdsAuthorizationTopicListContainer;
import no.systema.tds.model.jsonjackson.authorization.topic.JsonTdsAuthorizationTopicListRecord;

/**
 * @author oscardelatorre
 * 
 */
public class TdsAuthorizationTopicListMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsAuthorizationTopicListMapper.class.getName());
	
	public JsonTdsAuthorizationTopicListContainer getContainer(String utfPayload) throws Exception{
		JsonTdsAuthorizationTopicListContainer container = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsAuthorizationTopicListContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			logger.info("Mapping object from JSON payload...");
			logger.info("[JSON-String payload status=OK]  " + container.getUser());
			
			//DEBUG
			Collection<JsonTdsAuthorizationTopicListRecord> fields = container.getSigneringslista();
			for(JsonTdsAuthorizationTopicListRecord record : fields){
				logger.info("Syav: " + record.getSyav());
				
			}
		}	
		return container;
	}
	
	

}
