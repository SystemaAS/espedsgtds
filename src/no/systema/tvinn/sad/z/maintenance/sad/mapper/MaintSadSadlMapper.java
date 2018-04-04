/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sad.mapper;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 

import no.systema.tvinn.sad.z.maintenance.sad.model.JsonMaintSadSadlContainer;
import no.systema.tvinn.sad.z.maintenance.sad.model.JsonMaintSadSadlRecord;

//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Apr 3, 2018
 * 
 */
public class MaintSadSadlMapper {
	private static final Logger logger = Logger.getLogger(MaintSadSadlMapper.class.getName());
	
	public JsonMaintSadSadlContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadSadlContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadSadlContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadSadlRecord> list = container.getList();
		for(JsonMaintSadSadlRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
