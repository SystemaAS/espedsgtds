/**
 * 
 */
package no.systema.tds.mapper.jsonjackson.avdsignature;

import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureContainer;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureNameContainer;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureNameRecord;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureRecord;

/**
 * @author oscardelatorre
 * 
 */
public class TdsSignatureMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsSignatureMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsSignatureContainer getContainer(String utfPayload) throws Exception{
		JsonTdsSignatureContainer container = null;
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsSignatureContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + taricCodeContainer.getUser());
			
			//DEBUG
			Collection<JsonTdsSignatureRecord> fields = container.getSignaturer();
			for(JsonTdsSignatureRecord record : fields){
				/*logger.info("Vata: " + record.getSvvs_vata());
				logger.info("VataK: " + record.getSvvs_vatak());
				logger.info("Txt: " + record.getSvvs_txtk());
				*/
			}
		}
			
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsSignatureNameContainer getSignatureNameContainer(String utfPayload) throws Exception{
		JsonTdsSignatureNameContainer container = null;
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsSignatureNameContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + taricCodeContainer.getUser());
			
			//DEBUG
			Collection<JsonTdsSignatureNameRecord> fields = container.getGetsignname();
			for(JsonTdsSignatureNameRecord record : fields){
				/*logger.info("Vata: " + record.getSvvs_vata());
				logger.info("VataK: " + record.getSvvs_vatak());
				logger.info("Txt: " + record.getSvvs_txtk());
				*/
			}
		}
			
		return container;
	}
}
