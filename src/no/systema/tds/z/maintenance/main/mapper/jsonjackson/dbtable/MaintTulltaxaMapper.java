/**
 * 
 */
package no.systema.tds.z.maintenance.main.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.tds.z.maintenance.main.model.MaintenanceMainTulltaxaObject;

/**
 * @author oscardelatorre
 * @date Okt 2019
 * 
 */
public class MaintTulltaxaMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(MaintTulltaxaMapper.class.getName());
	
	public MaintenanceMainTulltaxaObject getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		MaintenanceMainTulltaxaObject container = super.getObjectMapper().readValue(utfPayload.getBytes(), MaintenanceMainTulltaxaObject.class); 
		
		return container;
	}
}
