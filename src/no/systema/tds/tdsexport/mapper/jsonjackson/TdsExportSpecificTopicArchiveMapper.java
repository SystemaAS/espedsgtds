/**
 * 
 */
package no.systema.tds.tdsexport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.tdsexport.model.jsonjackson.topic.archive.JsonTdsExportSpecificTopicArchiveContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.archive.JsonTdsExportSpecificTopicArchiveRecord;

/**
 * @author oscardelatorre
 * @date June 28, 2013
 * 
 */
public class TdsExportSpecificTopicArchiveMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsExportSpecificTopicArchiveMapper.class.getName());
	
	public JsonTdsExportSpecificTopicArchiveContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsExportSpecificTopicArchiveContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsExportSpecificTopicArchiveContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonTdsExportSpecificTopicArchiveRecord> list = container.getArchiveElements();
		for(JsonTdsExportSpecificTopicArchiveRecord record : list){
			logger.info("Url (archive): " + record.getUrl());
			logger.info("Subject (archive): " + record.getSubject());
			
		}
		return container;
	}
}
