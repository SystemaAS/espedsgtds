/**
 * 
 */
package no.systema.tds.nctsimport.service;

import no.systema.tds.nctsimport.model.jsonjackson.topic.archive.JsonNctsImportSpecificTopicArchiveContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.logging.JsonNctsImportSpecificTopicLoggingContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.logging.JsonNctsImportSpecificTopicLoggingLargeTextContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.JsonNctsImportSpecificTopicContainer;


/**
 * @author oscardelatorre
 * @date Dec 4, 2013
 */
public interface NctsImportSpecificTopicService {
	public JsonNctsImportSpecificTopicContainer getNctsImportSpecificTopicContainer(String utfPayload);
	public JsonNctsImportSpecificTopicArchiveContainer getNctsImportSpecificTopicArchiveContainer(String utfPayload);
	public JsonNctsImportSpecificTopicLoggingContainer getNctsImportSpecificTopicLoggingContainer(String utfPayload);
	public JsonNctsImportSpecificTopicLoggingLargeTextContainer getNctsImportSpecificTopicLoggingLargeTextContainer(String utfPayload);
	
}
