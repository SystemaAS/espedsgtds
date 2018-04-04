/**
 * 
 */
package no.systema.tds.mapper.jsonjackson;

import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.model.jsonjackson.codes.JsonTdsCodeContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsCodeRecord;
import no.systema.tds.model.jsonjackson.codes.JsonTdsNctsCodeContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsNctsCodeRecord;

/**
 * @author oscardelatorre
 * 
 */
public class TdsCodeMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsCodeMapper.class.getName());
	
	public JsonTdsCodeContainer getContainer(String utfPayload) throws Exception{
		
		JsonTdsCodeContainer codeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			codeContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsCodeContainer.class); 
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + codeContainer.getUser());
			
			//DEBUG
			Collection<JsonTdsCodeRecord> fields = codeContainer.getKodlista();
			for(JsonTdsCodeRecord record : fields){
				/*logger.info("Code: " + record.getSvkd_kd());
				logger.info("Value: " + record.getSvkd_kbs());
				*/
			}
		}	
		return codeContainer;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsNctsCodeContainer getNctsContainer(String utfPayload) throws Exception{
		JsonTdsNctsCodeContainer codeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			codeContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsNctsCodeContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			logger.info("Mapping Code object from JSON payload...");
			logger.info("[JSON-String payload status=OK]  " + codeContainer.getUser());
			
			//DEBUG
			Collection<JsonTdsNctsCodeRecord> fields = codeContainer.getKodlista();
			for(JsonTdsNctsCodeRecord record : fields){
				//logger.info("Code: " + record.getTkkode());
			}
		}	
		return codeContainer;
	}
}
