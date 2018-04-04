/**
 * 
 */
package no.systema.tds.nctsexport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportSpecificTopicContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportSpecificTopicRecord;

/**
 * @author oscardelatorre
 * 
 */
public class NctsExportSpecificTopicMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(NctsExportSpecificTopicMapper.class.getName());
	
	public JsonNctsExportSpecificTopicContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonNctsExportSpecificTopicContainer topicListContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonNctsExportSpecificTopicContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		//DEBUG
		
		Collection<JsonNctsExportSpecificTopicRecord> fields = topicListContainer.getOneorder();
		for(JsonNctsExportSpecificTopicRecord record : fields){
			/*logger.info("SVEH_SYAV: " + record.getSveh_syav());
			logger.info("SVEH_SYOP: " + record.getSveh_syop());
			logger.info("SVEH_AVTL: " + record.getSveh_avtl());
			logger.info("SVEH_MOPA: " + record.getSveh_mopa());
			logger.info("SVEH_MOLK: " + record.getSveh_molk());
			logger.info("SVEH_FABL: " + record.getSveh_fabl());
			logger.info("SVEH_FATX: " + record.getSveh_fatx());
			logger.info("SVEH_VAKD: " + record.getSveh_vakd());
			*/
		}
			
		return topicListContainer;
	}
}
