/**
 * 
 */
package no.systema.tds.tdsimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicListContainer;

/**
 * @author oscardelatorre
 * @date Sep 2, 2013
 * 
 */
public class TdsImportTopicListMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsImportTopicListMapper.class.getName());
	
	public JsonTdsImportTopicListContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsImportTopicListContainer topicListContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsImportTopicListContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		//DEBUG 
		/*
		Collection<JsonTdsImportTopicListRecord> list = topicListContainer.getOrderList();
		for(JsonTdsImportTopicListRecord record : list){
			logger.info("TullId: " + record.getTullId());
			logger.info("Avs�ndare: " + record.getAvsNavn());
			
		}*/
		
		
		return topicListContainer;
	}
}
