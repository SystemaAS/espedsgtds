/**
 * 
 */
package no.systema.tds.tdsexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicCopiedFromTransportUppdragContainer;

/**
 * 
 * @author oscardelatorre
 * @date Sep 19, 2013
 * 
 */
public class TdsExportTopicCopiedFromTransportUppdragMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsExportTopicCopiedFromTransportUppdragMapper.class.getName());
	
	public JsonTdsExportTopicCopiedFromTransportUppdragContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsExportTopicCopiedFromTransportUppdragContainer topicListContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsExportTopicCopiedFromTransportUppdragContainer.class); 
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
