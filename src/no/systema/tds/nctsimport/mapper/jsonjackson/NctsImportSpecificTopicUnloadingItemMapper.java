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
import no.systema.tds.nctsimport.model.jsonjackson.topic.unloading.items.JsonNctsImportSpecificTopicUnloadingItemContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.unloading.items.JsonNctsImportSpecificTopicUnloadingItemRecord;

/**
 * 
 * @author oscardelatorre
 * @date Dec 19, 2013
 * 
 */
public class NctsImportSpecificTopicUnloadingItemMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(NctsImportSpecificTopicUnloadingItemMapper.class.getName());
	
	public JsonNctsImportSpecificTopicUnloadingItemContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonNctsImportSpecificTopicUnloadingItemContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonNctsImportSpecificTopicUnloadingItemContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		//DEBUG
		
		Collection<JsonNctsImportSpecificTopicUnloadingItemRecord> fields = container.getOrderList();
		for(JsonNctsImportSpecificTopicUnloadingItemRecord record : fields){
			
			/*logger.info("Bruttovikt: " + record.getTivkb());*/
		}
			
		return container;
	}
}
