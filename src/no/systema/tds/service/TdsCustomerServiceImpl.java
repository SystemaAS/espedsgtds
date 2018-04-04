/**
 * 
 */
package no.systema.tds.service;

import no.systema.tds.mapper.jsonjackson.TdsCustomerMapper;
import no.systema.tds.model.jsonjackson.customer.JsonTdsCustomerContainer;

/**
 * @author oscardelatorre
 *
 */
public class TdsCustomerServiceImpl implements TdsCustomerService{
	public JsonTdsCustomerContainer getTdsExportCustomerContainer(String utfPayload) {
		JsonTdsCustomerContainer container = null;
		try{
			TdsCustomerMapper mapper = new TdsCustomerMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
}
