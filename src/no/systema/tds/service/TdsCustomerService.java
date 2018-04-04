/**
 * 
 */
package no.systema.tds.service;

import no.systema.tds.model.jsonjackson.customer.JsonTdsCustomerContainer;

/**
 * @author oscardelatorre
 * @date Jun 07, 2017
 *
 */
public interface TdsCustomerService {
	public JsonTdsCustomerContainer getTdsExportCustomerContainer(String utfPayload);
	
	
}
