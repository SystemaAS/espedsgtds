/**
 * 
 */
package no.systema.tds.tdsexport.service;

import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicOmbudContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicInvoiceContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicInvoiceExternalContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicInvoiceExternalForUpdateContainer;

import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicCopiedContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicCopiedFromTransportUppdragContainer;

import no.systema.tds.tdsexport.model.jsonjackson.topic.archive.JsonTdsExportSpecificTopicArchiveContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.logging.JsonTdsExportSpecificTopicLoggingContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.logging.JsonTdsExportSpecificTopicLoggingLargeTextContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicFaktTotalContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicCheckItemErrorContainer;




/**
 * @author oscardelatorre
 *
 */
public interface TdsExportSpecificTopicService {
	public JsonTdsExportSpecificTopicContainer getTdsExportSpecificTopicContainer(String utfPayload);
	public JsonTdsExportSpecificTopicArchiveContainer getTdsExportSpecificTopicArchiveContainer(String utfPayload);
	public JsonTdsExportSpecificTopicLoggingContainer getTdsExportSpecificTopicLoggingContainer(String utfPayload);
	public JsonTdsExportSpecificTopicLoggingLargeTextContainer getTdsExportSpecificTopicLoggingLargeTextContainer(String utfPayload);
	public JsonTdsExportTopicCopiedContainer getTdsExportTopicCopiedContainer(String utfPayload);
	public JsonTdsExportTopicCopiedFromTransportUppdragContainer getTdsExportTopicCopiedFromTransportUppdragContainer(String utfPayload);
	public JsonTdsExportSpecificTopicOmbudContainer getTdsExportSpecificTopicOmbudContainer(String utfPayload);
	public JsonTdsExportSpecificTopicFaktTotalContainer getTdsExportSpecificTopicFaktTotalContainer (String utfPayload);
	//error validation at item level
	public JsonTdsExportSpecificTopicCheckItemErrorContainer getCheckItemErrorContainer(String utfPayload);
	
	//Invoices
	public JsonTdsExportTopicInvoiceContainer getTdsExportTopicInvoiceContainerContainer (String utfPayload);
	public JsonTdsExportTopicInvoiceContainer getTdsExportTopicInvoiceContainerOneInvoice (String utfPayload);
	//External invoices
	public JsonTdsExportTopicInvoiceExternalContainer getTdsExportTopicInvoiceContainerContainerExternal (String utfPayload);
	public JsonTdsExportTopicInvoiceExternalContainer getTdsExportTopicInvoiceContainerOneInvoiceExternal (String utfPayload);
	public JsonTdsExportTopicInvoiceExternalForUpdateContainer getTdsExportTopicInvoiceContainerOneInvoiceExternalForUpdate (String utfPayload);
	
	
}
