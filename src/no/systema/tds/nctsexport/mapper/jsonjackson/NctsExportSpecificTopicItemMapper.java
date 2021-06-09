/**
 * 
 */
package no.systema.tds.nctsexport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.nctsexport.model.jsonjackson.topic.items.JsonNctsExportSpecificTopicItemContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.items.JsonNctsExportSpecificTopicItemRecord;

/**
 * @author oscardelatorre
 * 
 */
public class NctsExportSpecificTopicItemMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(NctsExportSpecificTopicItemMapper.class.getName());
	
	public JsonNctsExportSpecificTopicItemContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonNctsExportSpecificTopicItemContainer topicItemContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonNctsExportSpecificTopicItemContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicItemContainer.getUser());
		//DEBUG
		Collection<JsonNctsExportSpecificTopicItemRecord> list = topicItemContainer.getOrderList();
		//DEBUG
		/*
		if(list!=null) {
			for(JsonNctsExportSpecificTopicItemRecord record : list){
				logger.info("Item description: " + record.getTvvt());
				logger.info("Sender name: " + record.getTvnas());
				logger.info("Receiver name: " + record.getTvnak());
				
				
			}
		}
		*/
		return topicItemContainer;
	}
}
