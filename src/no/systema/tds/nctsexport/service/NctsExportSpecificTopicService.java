/**
 * 
 */
package no.systema.tds.nctsexport.service;

import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportTopicCopiedFromTransportUppdragContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportSpecificTopicContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.archive.JsonNctsExportSpecificTopicArchiveContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.logging.JsonNctsExportSpecificTopicLoggingContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.logging.JsonNctsExportSpecificTopicLoggingLargeTextContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.validation.JsonNctsExportSpecificTopicGuaranteeValidatorContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportTopicCopiedContainer;

/**
 * @author oscardelatorre
 *
 */
public interface NctsExportSpecificTopicService {
	public JsonNctsExportSpecificTopicContainer getNctsExportSpecificTopicContainer(String utfPayload);
	public JsonNctsExportSpecificTopicArchiveContainer getNctsExportSpecificTopicArchiveContainer(String utfPayload);
	public JsonNctsExportSpecificTopicLoggingContainer getNctsExportSpecificTopicLoggingContainer(String utfPayload);
	public JsonNctsExportSpecificTopicLoggingLargeTextContainer getNctsExportSpecificTopicLoggingLargeTextContainer(String utfPayload);
	public JsonNctsExportSpecificTopicGuaranteeValidatorContainer getNctsExportSpecificTopicGuaranteeValidatorContainer(String utfPayload);
	public JsonNctsExportTopicCopiedContainer getNctsExportTopicCopiedContainer(String utfPayload);
	public JsonNctsExportTopicCopiedFromTransportUppdragContainer getNctsExportTopicCopiedFromTransportUppdragContainer(String utfPayload);
}
