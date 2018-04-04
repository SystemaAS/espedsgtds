/**
 * 
 */
package no.systema.tds.nctsimport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.nctsimport.model.jsonjackson.topic.items.JsonNctsImportSpecificTopicItemContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.items.JsonNctsImportSpecificTopicItemRecord;

/**
 * @author oscardelatorre
 * @date Dec 16, 2013
 * 
 */
public class NctsImportSpecificTopicItemMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(NctsImportSpecificTopicItemMapper.class.getName());
	
	public JsonNctsImportSpecificTopicItemContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonNctsImportSpecificTopicItemContainer topicItemContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonNctsImportSpecificTopicItemContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicItemContainer.getUser());
		//DEBUG
		Collection<JsonNctsImportSpecificTopicItemRecord> list = topicItemContainer.getOrderList();
		for(JsonNctsImportSpecificTopicItemRecord record : list){
			logger.info("Item description (event desc): " + record.getTvinf1());
			logger.info("Place name: " + record.getTvst());
			logger.info("Transport Id: " + record.getTvtaid());
			
			
		}
		return topicItemContainer;
	}
}
