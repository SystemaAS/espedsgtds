/**
 * 
 */
package no.systema.tds.tdsexport.mapper.jsonjackson;

import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportTulltaxaKundensRegisterVarukodContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportTulltaxaKundensRegisterVarukodRecord;

/**
 * 
 * @author oscardelatorre
 * @date Jan 28, 2016
 * 
 * 
 */
public class TdsExportTulltaxaKundensRegisterVarukodMapper {
	private static final Logger logger = Logger.getLogger(TdsExportTulltaxaKundensRegisterVarukodMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsExportTulltaxaKundensRegisterVarukodContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		mapper.configure(DeserializationFeature.WRAP_EXCEPTIONS,true);
		
		JsonTdsExportTulltaxaKundensRegisterVarukodContainer taricCodeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			taricCodeContainer = mapper.readValue(utfPayload.getBytes(), JsonTdsExportTulltaxaKundensRegisterVarukodContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + taricCodeContainer.getUser());
			
			//DEBUG
			Collection<JsonTdsExportTulltaxaKundensRegisterVarukodRecord> fields = taricCodeContainer.getKundvarlist();
			for(JsonTdsExportTulltaxaKundensRegisterVarukodRecord record : fields){
				//logger.info("Varukod: " + record.getTatanr());
			}
		}	
		return taricCodeContainer;
	}
}
