/**
 * 
 */
package no.systema.tds.tdsimport.service;

import no.systema.tds.tdsimport.mapper.jsonjackson.TdsImportCustomerMapper;
import no.systema.tds.tdsimport.model.jsonjackson.customer.JsonTdsImportCustomerContainer;

/**
 * @author oscardelatorre
 * @date Sep 2, 2013
 * 
 *
 */
public class TdsImportCustomerServiceImpl implements TdsImportCustomerService{
	public JsonTdsImportCustomerContainer getTdsImportCustomerContainer(String utfPayload) {
		JsonTdsImportCustomerContainer container = null;
		try{
			TdsImportCustomerMapper mapper = new TdsImportCustomerMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
}
