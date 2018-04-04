/**
 * 
 */
package no.systema.tds.tdsexport.service;

import no.systema.tds.tdsexport.model.jsonjackson.customer.JsonTdsExportCustomerContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicInvoiceContainer;

/**
 * @author oscardelatorre
 * @date Feb 16, 201
 *
 */
public interface TdsExportCustomerService {
	public JsonTdsExportCustomerContainer getTdsExportCustomerContainer(String utfPayload);
	
	
}
