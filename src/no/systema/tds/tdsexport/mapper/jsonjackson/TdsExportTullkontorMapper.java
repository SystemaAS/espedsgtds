/**
 * 
 */
package no.systema.tds.tdsexport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.tdsexport.model.jsonjackson.tullkontor.JsonTdsExportTullkontorContainer;
import no.systema.tds.tdsexport.model.jsonjackson.tullkontor.JsonTdsExportTullkontorRecord;

/**
 * @author oscardelatorre
 * 
 */
public class TdsExportTullkontorMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsExportTullkontorMapper.class.getName());
	
	public JsonTdsExportTullkontorContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonTdsExportTullkontorContainer listContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsExportTullkontorContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("Mapping Customer object from JSON payload...");
		logger.info("[JSON-String payload status=OK]  " + listContainer.getUser());
		
		//DEBUG
		Collection<JsonTdsExportTullkontorRecord> fields = listContainer.getCustomslist();
		for(JsonTdsExportTullkontorRecord record : fields){
			//logger.info("tullkontor-txt: " + record.getTktxtn());
			//logger.info("tullkontor-code: " + record.getTkkode());
			
		}
			
		return listContainer;
	}
}
