/**
 * 
 */
package no.systema.tds.tdsimport.service;

import no.systema.tds.tdsimport.model.jsonjackson.customer.JsonTdsImportCustomerContainer;

/**
 * @author oscardelatorre
 * @date Sep 2, 2013
 *
 */
public interface TdsImportCustomerService {
	public JsonTdsImportCustomerContainer getTdsImportCustomerContainer(String utfPayload);
}
