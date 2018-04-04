/**
 * 
 */
package no.systema.tds.nctsimport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.nctsimport.model.jsonjackson.topic.archive.JsonNctsImportSpecificTopicArchiveContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.archive.JsonNctsImportSpecificTopicArchiveRecord;

/**
 * @author oscardelatorre
 * @date Apr 4, 2014
 * 
 */
public class NctsImportSpecificTopicArchiveMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(NctsImportSpecificTopicArchiveMapper.class.getName());
	
	public JsonNctsImportSpecificTopicArchiveContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonNctsImportSpecificTopicArchiveContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonNctsImportSpecificTopicArchiveContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonNctsImportSpecificTopicArchiveRecord> list = container.getArchiveElements();
		for(JsonNctsImportSpecificTopicArchiveRecord record : list){
			logger.info("Url (archive): " + record.getUrl());
			logger.info("Subject (archive): " + record.getSubject());
			
		}
		return container;
	}
}
