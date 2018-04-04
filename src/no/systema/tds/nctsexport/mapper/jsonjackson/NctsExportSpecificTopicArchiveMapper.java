/**
 * 
 */
package no.systema.tds.nctsexport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.nctsexport.model.jsonjackson.topic.archive.JsonNctsExportSpecificTopicArchiveContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.archive.JsonNctsExportSpecificTopicArchiveRecord;

/**
 * @author oscardelatorre
 * @date Aug 7, 2013
 * 
 */
public class NctsExportSpecificTopicArchiveMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(NctsExportSpecificTopicArchiveMapper.class.getName());
	
	public JsonNctsExportSpecificTopicArchiveContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonNctsExportSpecificTopicArchiveContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonNctsExportSpecificTopicArchiveContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonNctsExportSpecificTopicArchiveRecord> list = container.getArchiveElements();
		for(JsonNctsExportSpecificTopicArchiveRecord record : list){
			logger.info("Url (archive): " + record.getUrl());
			logger.info("Subject (archive): " + record.getSubject());
			
		}
		return container;
	}
}
