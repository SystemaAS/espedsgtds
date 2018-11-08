/**
 * 
 */
package no.systema.tds.mapper.jsonjackson;

import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;


import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.model.jsonjackson.codes.JsonTdsCodeContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsCodeRecord;
import no.systema.tds.model.jsonjackson.codes.JsonTdsCode2Container;
import no.systema.tds.model.jsonjackson.codes.JsonTdsCode2Record;

import no.systema.tds.model.jsonjackson.codes.JsonTdsNctsCodeContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsNctsCodeRecord;

/**
 * @author oscardelatorre
 * 
 */
public class TdsCodeMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsCodeMapper.class.getName());
	
	public JsonTdsCodeContainer getContainer(String utfPayload) throws Exception{
		
		JsonTdsCodeContainer codeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			codeContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsCodeContainer.class); 
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + codeContainer.getUser());
			
			//DEBUG
			Collection<JsonTdsCodeRecord> fields = codeContainer.getKodlista();
			for(JsonTdsCodeRecord record : fields){
				/*logger.info("Code: " + record.getSvkd_kd());
				logger.info("Value: " + record.getSvkd_kbs());
				*/
			}
		}	
		return codeContainer;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsCode2Container getContainer2(String utfPayload) throws Exception{
		
		JsonTdsCode2Container codeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			codeContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsCode2Container.class); 
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + codeContainer.getUser());
			
			//DEBUG
			Collection<JsonTdsCode2Record> fields = codeContainer.getArkivkodelist();
			for(JsonTdsCode2Record record : fields){
				/*logger.info("Code: " + record.getSvkd_kd());
				logger.info("Value: " + record.getSvkd_kbs());
				*/
			}
		}	
		return codeContainer;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsNctsCodeContainer getNctsContainer(String utfPayload) throws Exception{
		JsonTdsNctsCodeContainer codeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			codeContainer = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsNctsCodeContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + codeContainer.getUser());
			
			//DEBUG
			Collection<JsonTdsNctsCodeRecord> fields = codeContainer.getKodlista();
			for(JsonTdsNctsCodeRecord record : fields){
				//logger.info("Code: " + record.getTkkode());
			}
		}	
		return codeContainer;
	}
}
