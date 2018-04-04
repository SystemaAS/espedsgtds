/**
 * 
 */
package no.systema.tds.tdsexport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicCheckItemErrorContainer;
//application library
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicFaktTotalContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicOmbudContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicOmbudRecord;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicRecord;

/**
 * @author oscardelatorre
 * 
 */
public class TdsExportSpecificTopicMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsExportSpecificTopicMapper.class.getName());
	
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsExportSpecificTopicContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsExportSpecificTopicContainer topicListContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsExportSpecificTopicContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		//DEBUG
		
		Collection<JsonTdsExportSpecificTopicRecord> fields = topicListContainer.getOneorder();
		for(JsonTdsExportSpecificTopicRecord record : fields){
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
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsExportSpecificTopicCheckItemErrorContainer getCheckItemErrorContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsExportSpecificTopicCheckItemErrorContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsExportSpecificTopicCheckItemErrorContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
	
			
		return container;
	}
	
	
	
	
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsExportSpecificTopicOmbudContainer getOmbudContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsExportSpecificTopicOmbudContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsExportSpecificTopicOmbudContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		//DEBUG
		
		Collection<JsonTdsExportSpecificTopicOmbudRecord> fields = container.getGetdepinf();
		for(JsonTdsExportSpecificTopicOmbudRecord record : fields){
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
			
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsExportSpecificTopicFaktTotalContainer getFaktTotalContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsExportSpecificTopicFaktTotalContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsExportSpecificTopicFaktTotalContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		return container;
	}
}
