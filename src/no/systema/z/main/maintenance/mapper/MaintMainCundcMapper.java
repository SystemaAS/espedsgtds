/**
 * 
 */
package no.systema.z.main.maintenance.mapper;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.z.main.maintenance.model.JsonMaintMainCundcContainer;

/**
 * @author Fredrik Möller
 * @date Apr 3, 2018
 * 
 */
public class MaintMainCundcMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(MaintMainCundcMapper.class.getName());
	
	public JsonMaintMainCundcContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonMaintMainCundcContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintMainCundcContainer.class); 
		
		return container;
	}
}
