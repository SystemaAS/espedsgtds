/**
 * 
 */
package no.systema.tds.z.maintenance.main.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtvkContainer;

/**
 * @author oscardelatorre
 * @date May 19, 2017
 * 
 */
public class MaintSvtvkMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(MaintSvtvkMapper.class.getName());
	
	public JsonMaintSvtvkContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonMaintSvtvkContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintSvtvkContainer.class); 
		
		return container;
	}
}
