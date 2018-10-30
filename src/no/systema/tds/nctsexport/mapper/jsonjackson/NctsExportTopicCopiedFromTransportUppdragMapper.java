/**
 * 
 */
package no.systema.tds.nctsexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportTopicCopiedFromTransportUppdragContainer;

/**
 * 
 * @author oscardelatorre
 * @date Okt 2018
 * 
 */
public class NctsExportTopicCopiedFromTransportUppdragMapper {
	private static final Logger logger = Logger.getLogger(NctsExportTopicCopiedFromTransportUppdragMapper.class.getName());
	
	public JsonNctsExportTopicCopiedFromTransportUppdragContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonNctsExportTopicCopiedFromTransportUppdragContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonNctsExportTopicCopiedFromTransportUppdragContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		return topicListContainer;
	}
}
