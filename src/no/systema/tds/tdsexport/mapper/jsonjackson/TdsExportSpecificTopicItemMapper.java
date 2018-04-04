/**
 * 
 */
package no.systema.tds.tdsexport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemRecord;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemStatisticalValueContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemStatisticalValueRecord;

/**
 * @author oscardelatorre
 * 
 */
public class TdsExportSpecificTopicItemMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsExportSpecificTopicItemMapper.class.getName());
	
	public JsonTdsExportSpecificTopicItemContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsExportSpecificTopicItemContainer topicItemContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsExportSpecificTopicItemContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		//logger.info("[JSON-String payload status=OK]  " + topicItemContainer.getUser());
		//DEBUG
		Collection<JsonTdsExportSpecificTopicItemRecord> list = topicItemContainer.getOrderList();
		for(JsonTdsExportSpecificTopicItemRecord record : list){
			//logger.info("Item description: " + record.getSvev_vasl());
		}
		return topicItemContainer;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsExportSpecificTopicItemStatisticalValueContainer getStatisticalValueContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsExportSpecificTopicItemStatisticalValueContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsExportSpecificTopicItemStatisticalValueContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		//logger.info("[JSON-String payload status=OK]  " + topicItemContainer.getUser());
		//DEBUG
		Collection<JsonTdsExportSpecificTopicItemStatisticalValueRecord> list = container.getTaxcalc();
		for(JsonTdsExportSpecificTopicItemStatisticalValueRecord record : list){
			//logger.info("Item description: " + record.getSvev_vasl());
		}
		return container;
	}
	
	
}
