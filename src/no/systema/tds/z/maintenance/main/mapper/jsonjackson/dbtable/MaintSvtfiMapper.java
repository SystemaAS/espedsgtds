/**
 * 
 */
package no.systema.tds.z.maintenance.main.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtfiContainer;

/**
 * @author oscardelatorre
 * @date May 03, 2017
 * 
 */
public class MaintSvtfiMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(MaintSvtfiMapper.class.getName());
	
	public JsonMaintSvtfiContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonMaintSvtfiContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintSvtfiContainer.class); 
		
		return container;
	}
}
