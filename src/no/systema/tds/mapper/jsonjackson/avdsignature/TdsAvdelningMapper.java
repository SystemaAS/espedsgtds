/**
 * 
 */
package no.systema.tds.mapper.jsonjackson.avdsignature;

import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsAvdelningContainer;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsAvdelningRecord;

/**
 * @author oscardelatorre
 * 
 */
public class TdsAvdelningMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(TdsAvdelningMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsAvdelningContainer getContainer(String utfPayload) throws Exception{
		JsonTdsAvdelningContainer container = null;
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsAvdelningContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + taricCodeContainer.getUser());
			
			//DEBUG
			Collection<JsonTdsAvdelningRecord> fields = container.getAvdelningar();
			for(JsonTdsAvdelningRecord record : fields){
				/*logger.info("Vata: " + record.getSvvs_vata());
				logger.info("VataK: " + record.getSvvs_vatak());
				logger.info("Txt: " + record.getSvvs_txtk());
				*/
			}
		}
			
		return container;
	}
}
