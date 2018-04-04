/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.nctsexport.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 

import no.systema.tvinn.sad.z.maintenance.nctsexport.model.JsonMaintNctsTransitKodeTypeContainer;

/**
 * @author Fredrik Möller
 * @date Apr 4, 2018
 * 
 */
public class MaintNctsExportTransitKodeTypeMapper {
	
	public JsonMaintNctsTransitKodeTypeContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintNctsTransitKodeTypeContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintNctsTransitKodeTypeContainer.class); 
		return container;
	}
}
