/**
 * 
 */
package no.systema.tds.mapper.jsonjackson;

import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.tds.model.jsonjackson.codes.JsonTdsTaricVarukodContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTaricVarukodRecord;

/**
 * @author oscardelatorre
 * 
 */
public class TdsTaricVarukodMapper {
	private static final Logger logger = Logger.getLogger(TdsTaricVarukodMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsTaricVarukodContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		mapper.configure(DeserializationFeature.WRAP_EXCEPTIONS,true);
		
		JsonTdsTaricVarukodContainer taricCodeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			taricCodeContainer = mapper.readValue(utfPayload.getBytes(), JsonTdsTaricVarukodContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + taricCodeContainer.getUser());
			
			//DEBUG
			Collection<JsonTdsTaricVarukodRecord> fields = null;
			if(taricCodeContainer.getTullTaxalist()!=null){
				//when searching from varukod
				fields = taricCodeContainer.getTullTaxalist();
			}else if(taricCodeContainer.getTtaxalfasok()!=null){
				//when searching from varukod description (differente back-end routine)
				fields = taricCodeContainer.getTtaxalfasok();
			}
			//iterate now
			for(JsonTdsTaricVarukodRecord record : fields){
				/*logger.info("Vata: " + record.getSvvs_vata());
				logger.info("VataK: " + record.getSvvs_vatak());
				logger.info("Txt: " + record.getSvvs_txtk());
				*/
			}
		}	
		return taricCodeContainer;
	}
}
