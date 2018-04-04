/**
 * 
 */
package no.systema.tds.z.maintenance.main.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtlvContainer;

/**
 * @author oscardelatorre
 * @date Jun 03, 2017
 * 
 */
public class MaintSvtlvMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(MaintSvtlvMapper.class.getName());
	
	public JsonMaintSvtlvContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonMaintSvtlvContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintSvtlvContainer.class); 
		
		return container;
	}
}
