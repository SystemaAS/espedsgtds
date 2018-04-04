/**
 * 
 */
package no.systema.tds.tdsimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicCopiedFromTransportUppdragContainer;

/**
 * 
 * @author oscardelatorre
 * @date Sep 19, 2013
 * 
 */
public class TdsImportTopicCopiedFromTransportUppdragMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsImportTopicCopiedFromTransportUppdragMapper.class.getName());
	
	public JsonTdsImportTopicCopiedFromTransportUppdragContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsImportTopicCopiedFromTransportUppdragContainer topicListContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsImportTopicCopiedFromTransportUppdragContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		//DEBUG 
		/*
		Collection<JsonTdsExportTopicListRecord> list = topicListContainer.getOrderList();
		for(JsonTdsExportTopicListRecord record : list){
			logger.info("TullId: " + record.getTullId());
			logger.info("Avs�ndare: " + record.getAvsNavn());
			
		}*/
		
		
		return topicListContainer;
	}
}
