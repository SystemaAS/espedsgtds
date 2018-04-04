/**
 * 
 */
package no.systema.tds.mapper.jsonjackson.validation;

import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.model.jsonjackson.validation.JsonTdsMangdEnhetContainer;
import no.systema.tds.model.jsonjackson.validation.JsonTdsMangdEnhetRecord;

/**
 * @author oscardelatorre
 * @date Sep 26, 2013
 */
public class TdsMangdEnhetMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(TdsMangdEnhetMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsMangdEnhetContainer getContainer(String utfPayload) throws Exception{
		JsonTdsMangdEnhetContainer container = null;
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsMangdEnhetContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + taricCodeContainer.getUser());
			
			//DEBUG
			Collection<JsonTdsMangdEnhetRecord> fields = container.getXtramangdenhet();
			for(JsonTdsMangdEnhetRecord record : fields){
				/*logger.info("Vata: " + record.getSvvs_vata());
				logger.info("VataK: " + record.getSvvs_vatak());
				logger.info("Txt: " + record.getSvvs_txtk());
				*/
			}
		}
			
		return container;
	}
}
