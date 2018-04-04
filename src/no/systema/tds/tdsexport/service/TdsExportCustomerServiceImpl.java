/**
 * 
 */
package no.systema.tds.tdsexport.service;

import no.systema.tds.tdsexport.mapper.jsonjackson.TdsExportCustomerMapper;
import no.systema.tds.tdsexport.model.jsonjackson.customer.JsonTdsExportCustomerContainer;

/**
 * @author oscardelatorre
 *
 */
public class TdsExportCustomerServiceImpl implements TdsExportCustomerService{
	public JsonTdsExportCustomerContainer getTdsExportCustomerContainer(String utfPayload) {
		JsonTdsExportCustomerContainer jsonTdsExportCustomerContainer = null;
		try{
			TdsExportCustomerMapper tdsExportCustomerMapper = new TdsExportCustomerMapper();
			jsonTdsExportCustomerContainer = tdsExportCustomerMapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonTdsExportCustomerContainer;
		
	}
}
