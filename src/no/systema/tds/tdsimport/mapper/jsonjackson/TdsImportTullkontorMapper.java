/**
 * 
 */
package no.systema.tds.tdsimport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.tdsimport.model.jsonjackson.tullkontor.JsonTdsImportTullkontorContainer;
import no.systema.tds.tdsimport.model.jsonjackson.tullkontor.JsonTdsImportTullkontorRecord;

/**
 * @author oscardelatorre
 * @date Sep 2, 2103
 * 
 */
public class TdsImportTullkontorMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsImportTullkontorMapper.class.getName());
	
	public JsonTdsImportTullkontorContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonTdsImportTullkontorContainer listContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsImportTullkontorContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("Mapping Customer object from JSON payload...");
		logger.info("[JSON-String payload status=OK]  " + listContainer.getUser());
		
		//DEBUG
		Collection<JsonTdsImportTullkontorRecord> fields = listContainer.getCustomslist();
		for(JsonTdsImportTullkontorRecord record : fields){
			logger.info("tullkontor-txt: " + record.getTktxtn());
			logger.info("tullkontor-code: " + record.getTkkode());
			
		}
			
		return listContainer;
	}
}
