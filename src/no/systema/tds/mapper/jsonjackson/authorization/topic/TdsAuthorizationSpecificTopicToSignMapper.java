/**
 * 
 */
package no.systema.tds.mapper.jsonjackson.authorization.topic;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.tds.model.jsonjackson.authorization.topic.JsonTdsAuthorizationSignSpecificTopicContainer;

/**
 * @author oscardelatorre
 * 
 */
public class TdsAuthorizationSpecificTopicToSignMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(TdsAuthorizationSpecificTopicToSignMapper.class.getName());
	
	public JsonTdsAuthorizationSignSpecificTopicContainer getContainer(String utfPayload) throws Exception{
		JsonTdsAuthorizationSignSpecificTopicContainer container = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsAuthorizationSignSpecificTopicContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			logger.info("Mapping object from JSON payload...");
			logger.info("[JSON-String payload status=OK]  " + container.getUser());
			
			//DEBUG - No Record type
			/*Collection<JsonTdsAuthorizationSignSpecificTopicRecord> fields = container.getSigneringslista();
			for(JsonTdsAuthorizationTopicListRecord record : fields){
				logger.info("Syav: " + record.getSyav());
				
			}*/
		}
			
		return container;
	}
	
	

}
