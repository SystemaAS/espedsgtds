/**
 * 
 */
package no.systema.tds.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.model.jsonjackson.JsonTdsGodsnrContainer;

/**
 * @author oscardelatorre
 * 
 */
public class TdsGodsnrMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsGodsnrMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsGodsnrContainer getContainer(String utfPayload) throws Exception{
		JsonTdsGodsnrContainer container = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsGodsnrContainer.class); 
		}	
		return container;
	}
	
	
}
