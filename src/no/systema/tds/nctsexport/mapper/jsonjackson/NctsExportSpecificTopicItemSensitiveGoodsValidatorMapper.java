/**
 * 
 */
package no.systema.tds.nctsexport.mapper.jsonjackson;



import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.tds.nctsexport.model.jsonjackson.topic.items.validation.JsonNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer;
import no.systema.tds.tdsexport.mapper.jsonjackson.TdsExportCustomerMapper;


/**
 * @author oscardelatorre
 * @date Nov 26, 2013
 *
 */
public class NctsExportSpecificTopicItemSensitiveGoodsValidatorMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsExportCustomerMapper.class.getName());
		
	public JsonNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("Mapping Customer object from JSON payload...");
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
			
		return container;
	}
}


