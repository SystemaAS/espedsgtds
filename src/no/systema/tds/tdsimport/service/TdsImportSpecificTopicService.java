/**
 * 
 */
package no.systema.tds.tdsimport.service;

import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicInvoiceExternalForUpdateContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicInvoiceExternalContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicCopiedFromTransportUppdragContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicCheckItemErrorContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicCopiedContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicInvoiceContainer;


import no.systema.tds.tdsimport.model.jsonjackson.topic.archive.JsonTdsImportSpecificTopicArchiveContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.logging.JsonTdsImportSpecificTopicLoggingContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.logging.JsonTdsImportSpecificTopicLoggingLargeTextContainer;

import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicOmbudContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicFaktTotalContainer;




/**
 * @author oscardelatorre
 * @date Sep 2, 2013
 * 
 */
public interface TdsImportSpecificTopicService {
	public JsonTdsImportSpecificTopicContainer getTdsImportSpecificTopicContainer(String utfPayload);
	public JsonTdsImportSpecificTopicArchiveContainer getTdsImportSpecificTopicArchiveContainer(String utfPayload);
	public JsonTdsImportSpecificTopicLoggingContainer getTdsImportSpecificTopicLoggingContainer(String utfPayload);
	public JsonTdsImportSpecificTopicLoggingLargeTextContainer getTdsImportSpecificTopicLoggingLargeTextContainer(String utfPayload);
	public JsonTdsImportTopicCopiedContainer getTdsImportTopicCopiedContainer(String utfPayload);
	public JsonTdsImportTopicCopiedFromTransportUppdragContainer getTdsImportTopicCopiedFromTransportUppdragContainer(String utfPayload);
	public JsonTdsImportSpecificTopicOmbudContainer getTdsImportSpecificTopicOmbudContainer (String utfPayload);
	public JsonTdsImportSpecificTopicFaktTotalContainer getTdsImportSpecificTopicFaktTotalContainer (String utfPayload);
	//error validation at item level
	public JsonTdsImportSpecificTopicCheckItemErrorContainer getCheckItemErrorContainer(String utfPayload);
	//Invoices
	public JsonTdsImportTopicInvoiceContainer getTdsImportTopicInvoiceContainerContainer (String utfPayload);
	public JsonTdsImportTopicInvoiceContainer getTdsImportTopicInvoiceContainerOneInvoice (String utfPayload);
	//External invoices
	public JsonTdsImportTopicInvoiceExternalContainer getTdsImportTopicInvoiceContainerContainerExternal (String utfPayload);
	public JsonTdsImportTopicInvoiceExternalContainer getTdsImportTopicInvoiceContainerOneInvoiceExternal (String utfPayload);
	public JsonTdsImportTopicInvoiceExternalForUpdateContainer getTdsImportTopicInvoiceContainerOneInvoiceExternalForUpdate (String utfPayload);
	
	
}
