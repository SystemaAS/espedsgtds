/**
 * 
 */
package no.systema.tds.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.model.jsonjackson.customer.JsonTdsCustomerContainer;
import no.systema.tds.model.jsonjackson.customer.JsonTdsCustomerRecord;

/**
 * @author oscardelatorre
 * 
 */
public class TdsCustomerMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(TdsCustomerMapper.class.getName());
	
	public JsonTdsCustomerContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonTdsCustomerContainer customerListContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsCustomerContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("Mapping Customer object from JSON payload...");
		logger.info("[JSON-String payload status=OK]  " + customerListContainer.getUser());
		
		//DEBUG
		Collection<JsonTdsCustomerRecord> fields = customerListContainer.getCustomerlist();
		for(JsonTdsCustomerRecord record : fields){
			logger.info("knavn: " + record.getKnavn());
			logger.info("kundnr: " + record.getKundnr());
			
		}
			
		return customerListContainer;
	}
}
