/**
 * 
 */
package no.systema.tds.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.model.jsonjackson.codes.JsonTdsTillaggskodContainer;

/**
 * @author oscardelatorre
 * 
 */
public class TdsTillaggskoderMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsTillaggskoderMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsTillaggskodContainer getContainer(String utfPayload) throws Exception{
		JsonTdsTillaggskodContainer container = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsTillaggskodContainer.class); 
		}	
		return container;
	}
	
	
}
