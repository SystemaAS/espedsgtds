/**
 * 
 */
package no.systema.tds.nctsimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.tds.nctsimport.model.jsonjackson.topic.JsonNctsImportTopicListContainer;

/**
 * @author oscardelatorre
 * 
 */
public class NctsImportTopicListMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(NctsImportTopicListMapper.class.getName());
	
	public JsonNctsImportTopicListContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonNctsImportTopicListContainer topicListContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonNctsImportTopicListContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		//DEBUG 
		/*
		Collection<JsonTdsExportTopicListRecord> list = topicListContainer.getOrderList();
		for(JsonTdsExportTopicListRecord record : list){
			logger.info("TullId: " + record.getTullId());
			logger.info("Avs.: " + record.getAvsNavn());
			
		}*/
		
		
		return topicListContainer;
	}
}
