/**
 * 
 */
package no.systema.tds.tdsimport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicCheckItemErrorContainer;
//application library
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicFaktTotalContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicOmbudContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicOmbudRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicRecord;

/**
 * @author oscardelatorre
 * @date Sep 2, 2013
 * 
 */
public class TdsImportSpecificTopicMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsImportSpecificTopicMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsImportSpecificTopicContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsImportSpecificTopicContainer topicListContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsImportSpecificTopicContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		//DEBUG
		
		Collection<JsonTdsImportSpecificTopicRecord> fields = topicListContainer.getOneorder();
		for(JsonTdsImportSpecificTopicRecord record : fields){
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
	public JsonTdsImportSpecificTopicOmbudContainer getOmbudContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsImportSpecificTopicOmbudContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsImportSpecificTopicOmbudContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		//DEBUG
		
		Collection<JsonTdsImportSpecificTopicOmbudRecord> fields = container.getGetdepinf();
		for(JsonTdsImportSpecificTopicOmbudRecord record : fields){
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
	public JsonTdsImportSpecificTopicFaktTotalContainer getFaktTotalContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsImportSpecificTopicFaktTotalContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsImportSpecificTopicFaktTotalContainer.class); 
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
	public JsonTdsImportSpecificTopicCheckItemErrorContainer getCheckItemErrorContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsImportSpecificTopicCheckItemErrorContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsImportSpecificTopicCheckItemErrorContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
	
			
		return container;
	}
}
