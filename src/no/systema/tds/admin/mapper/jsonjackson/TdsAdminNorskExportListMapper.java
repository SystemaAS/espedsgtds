/**
 * 
 */
package no.systema.tds.admin.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.tds.admin.model.jsonjackson.topic.JsonTdsAdminNorskExportListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Jan 14, 2014
 * 
 */
public class TdsAdminNorskExportListMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(TdsAdminNorskExportListMapper.class.getName());
	
	public JsonTdsAdminNorskExportListContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsAdminNorskExportListContainer topicListContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsAdminNorskExportListContainer.class); 
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
