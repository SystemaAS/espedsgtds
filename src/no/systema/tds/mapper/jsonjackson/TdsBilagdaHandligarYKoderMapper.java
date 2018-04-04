/**
 * 
 */
package no.systema.tds.mapper.jsonjackson;

import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.tds.model.jsonjackson.codes.JsonTdsBilagdaHandlingarYKoderContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsBilagdaHandlingarYKoderRecord;

/**
 * @author oscardelatorre
 * 
 */
public class TdsBilagdaHandligarYKoderMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(TdsBilagdaHandligarYKoderMapper.class.getName());
	
	
	/**
	 * Bilagda handlingar mapper
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsBilagdaHandlingarYKoderContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsBilagdaHandlingarYKoderContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsBilagdaHandlingarYKoderContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonTdsBilagdaHandlingarYKoderRecord> list = container.getBilhandykoder();
		for(JsonTdsBilagdaHandlingarYKoderRecord record : list){
			//logger.info("Item description: " + record.getSviv_stva());
		}
		return container;
	}		
	
}
