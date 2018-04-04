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
import no.systema.tds.nctsimport.model.jsonjackson.topic.unloading.JsonNctsImportSpecificTopicUnloadingContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.unloading.JsonNctsImportSpecificTopicUnloadingRecord;

/**
 * 
 * @author oscardelatorre
 * @date Dec 19, 2013
 * 
 */
public class NctsImportSpecificTopicUnloadingMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(NctsImportSpecificTopicUnloadingMapper.class.getName());
	
	public JsonNctsImportSpecificTopicUnloadingContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonNctsImportSpecificTopicUnloadingContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonNctsImportSpecificTopicUnloadingContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		//DEBUG
		
		Collection<JsonNctsImportSpecificTopicUnloadingRecord> fields = container.getOneorder();
		for(JsonNctsImportSpecificTopicUnloadingRecord record : fields){
			
			/*logger.info("Bruttovikt: " + record.getTivkb());*/
		}
			
		return container;
	}
}
