/**
 * 
 */
package no.systema.tds.mapper.jsonjackson.authorization;

import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.model.jsonjackson.authorization.JsonTdsAuthorizationContainer;
import no.systema.tds.model.jsonjackson.authorization.JsonTdsAuthorizationRecord;
import no.systema.tds.model.jsonjackson.authorization.JsonTdsPkiSmsCodeContainer;
import no.systema.tds.model.jsonjackson.authorization.JsonTdsPkiSmsCodeRecord;

/**
 * @author oscardelatorre
 * 
 */
public class TdsAuthorizationMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsAuthorizationMapper.class.getName());
	
	public JsonTdsAuthorizationContainer getContainer(String utfPayload) throws Exception{
		JsonTdsAuthorizationContainer container = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsAuthorizationContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			logger.info("Mapping object from JSON payload...");
			logger.info("[JSON-String payload status=OK]  " + container.getUser());
			
			//DEBUG
			Collection<JsonTdsAuthorizationRecord> fields = container.getTdsBehorigKontroll();
			for(JsonTdsAuthorizationRecord record : fields){
				//logger.info("bpki: " + record.getBpki());
				//logger.info("btds: " + record.getBtds());
				//logger.info("sign: " + record.getSign());
			}
		}
		return container;
	}
	
	
	/**
	 * PKI sms code mapper
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsPkiSmsCodeContainer getCodeContainerPkiSmsCode(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonTdsPkiSmsCodeContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsPkiSmsCodeContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("Mapping object from JSON payload...");
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		//DEBUG
		Collection<JsonTdsPkiSmsCodeRecord> fields = container.getTdsSkapaengangskod();
		for(JsonTdsPkiSmsCodeRecord record : fields){
			logger.info("smsCode: " + record.getSmsKod());
		}
			
		return container;
	}
	
}
