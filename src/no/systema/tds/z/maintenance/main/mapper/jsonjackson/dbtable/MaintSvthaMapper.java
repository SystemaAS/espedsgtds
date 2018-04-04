/**
 * 
 */
package no.systema.tds.z.maintenance.main.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvthaContainer;

/**
 * @author oscardelatorre
 * @date May 08, 2017
 * 
 */
public class MaintSvthaMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(MaintSvthaMapper.class.getName());
	
	public JsonMaintSvthaContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonMaintSvthaContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintSvthaContainer.class); 
		
		return container;
	}
}
