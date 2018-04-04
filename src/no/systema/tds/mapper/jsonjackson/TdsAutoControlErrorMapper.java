/**
 * 
 */
package no.systema.tds.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.model.jsonjackson.JsonTdsAutoControlErrorContainer;

/**
 * @author oscardelatorre
 * @date Oct 28, 2015
 * 
 */
public class TdsAutoControlErrorMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsAutoControlErrorMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsAutoControlErrorContainer getContainer(String utfPayload) throws Exception{
		JsonTdsAutoControlErrorContainer container = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsAutoControlErrorContainer.class); 
		}	
		return container;
	}
	
	
}
