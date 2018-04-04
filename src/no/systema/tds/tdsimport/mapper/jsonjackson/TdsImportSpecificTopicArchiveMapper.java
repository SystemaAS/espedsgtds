/**
 * 
 */
package no.systema.tds.tdsimport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.tdsimport.model.jsonjackson.topic.archive.JsonTdsImportSpecificTopicArchiveContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.archive.JsonTdsImportSpecificTopicArchiveRecord;

/**
 * @author oscardelatorre
 * @date Sep 2, 2013
 * 
 */
public class TdsImportSpecificTopicArchiveMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsImportSpecificTopicArchiveMapper.class.getName());
	
	public JsonTdsImportSpecificTopicArchiveContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsImportSpecificTopicArchiveContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsImportSpecificTopicArchiveContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonTdsImportSpecificTopicArchiveRecord> list = container.getArchiveElements();
		for(JsonTdsImportSpecificTopicArchiveRecord record : list){
			logger.info("Url (archive): " + record.getUrl());
			logger.info("Subject (archive): " + record.getSubject());
			
		}
		return container;
	}
}
