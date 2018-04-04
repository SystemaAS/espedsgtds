/**
 * 
 */
package no.systema.tds.tdsimport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.tdsimport.model.jsonjackson.customer.JsonTdsImportCustomerContainer;
import no.systema.tds.tdsimport.model.jsonjackson.customer.JsonTdsImportCustomerRecord;

/**
 * @author oscardelatorre
 * @date Sep 2, 2103
 * 
 */
public class TdsImportCustomerMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsImportCustomerMapper.class.getName());
	
	public JsonTdsImportCustomerContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsImportCustomerContainer customerListContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsImportCustomerContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("Mapping Customer object from JSON payload...");
		logger.info("[JSON-String payload status=OK]  " + customerListContainer.getUser());
		
		//DEBUG
		Collection<JsonTdsImportCustomerRecord> fields = customerListContainer.getCustomerlist();
		for(JsonTdsImportCustomerRecord record : fields){
			logger.info("knavn: " + record.getKnavn());
			logger.info("kundnr: " + record.getKundnr());
			
		}
			
		return customerListContainer;
	}
}
