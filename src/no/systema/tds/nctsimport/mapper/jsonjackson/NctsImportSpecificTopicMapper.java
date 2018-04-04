/**
 * 
 */
package no.systema.tds.nctsimport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.nctsimport.model.jsonjackson.topic.JsonNctsImportSpecificTopicContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.JsonNctsImportSpecificTopicRecord;

/**
 * 
 * @author oscardelatorre
 * @date Dec 4, 2013
 * 
 */
public class NctsImportSpecificTopicMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(NctsImportSpecificTopicMapper.class.getName());
	
	public JsonNctsImportSpecificTopicContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonNctsImportSpecificTopicContainer topicListContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonNctsImportSpecificTopicContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		//DEBUG
		
		Collection<JsonNctsImportSpecificTopicRecord> fields = topicListContainer.getOneorder();
		for(JsonNctsImportSpecificTopicRecord record : fields){
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
