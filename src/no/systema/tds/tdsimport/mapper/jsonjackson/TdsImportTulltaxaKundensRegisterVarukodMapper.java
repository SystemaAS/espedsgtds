/**
 * 
 */
package no.systema.tds.tdsimport.mapper.jsonjackson;

import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportTulltaxaKundensRegisterVarukodContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportTulltaxaKundensRegisterVarukodRecord;

/**
 * 
 * @author oscardelatorre
 * @date Jan 25, 2016
 * 
 * 
 */
public class TdsImportTulltaxaKundensRegisterVarukodMapper {
	private static final Logger logger = Logger.getLogger(TdsImportTulltaxaKundensRegisterVarukodMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsImportTulltaxaKundensRegisterVarukodContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		mapper.configure(DeserializationFeature.WRAP_EXCEPTIONS,true);
		
		JsonTdsImportTulltaxaKundensRegisterVarukodContainer taricCodeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			taricCodeContainer = mapper.readValue(utfPayload.getBytes(), JsonTdsImportTulltaxaKundensRegisterVarukodContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + taricCodeContainer.getUser());
			
			//DEBUG
			Collection<JsonTdsImportTulltaxaKundensRegisterVarukodRecord> fields = taricCodeContainer.getKundvarlist();
			for(JsonTdsImportTulltaxaKundensRegisterVarukodRecord record : fields){
				//logger.info("Varukod: " + record.getTatanr());
			}
		}	
		return taricCodeContainer;
	}
}
