/**
 * 
 */
package no.systema.tds.tdsexport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.tdsexport.model.jsonjackson.customer.JsonTdsExportCustomerContainer;
import no.systema.tds.tdsexport.model.jsonjackson.customer.JsonTdsExportCustomerRecord;

/**
 * @author oscardelatorre
 * 
 */
public class TdsExportCustomerMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsExportCustomerMapper.class.getName());
	
	public JsonTdsExportCustomerContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsExportCustomerContainer customerListContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsExportCustomerContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("Mapping Customer object from JSON payload...");
		logger.info("[JSON-String payload status=OK]  " + customerListContainer.getUser());
		
		//DEBUG
		Collection<JsonTdsExportCustomerRecord> fields = customerListContainer.getCustomerlist();
		for(JsonTdsExportCustomerRecord record : fields){
			logger.info("knavn: " + record.getKnavn());
			logger.info("kundnr: " + record.getKundnr());
			
		}
			
		return customerListContainer;
	}
}
