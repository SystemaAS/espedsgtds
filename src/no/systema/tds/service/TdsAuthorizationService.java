/**
 * 
 */
package no.systema.tds.service;

import no.systema.tds.model.jsonjackson.authorization.JsonTdsAuthorizationContainer;
import no.systema.tds.model.jsonjackson.authorization.JsonTdsPkiSmsCodeContainer;
import no.systema.tds.model.jsonjackson.authorization.topic.JsonTdsAuthorizationSignSpecificTopicContainer;
import no.systema.tds.model.jsonjackson.authorization.topic.JsonTdsAuthorizationTopicListContainer;



/**
 * @author oscardelatorre
 * @date June 2013
 * 
 */
public interface TdsAuthorizationService {
	public JsonTdsAuthorizationContainer getContainer(String utfPayload) throws Exception;
	public JsonTdsPkiSmsCodeContainer getContainerPkiSmsCode(String utfPayload) throws Exception;
	public JsonTdsAuthorizationTopicListContainer getContainerTopicList(String utfPayload) throws Exception;
	public JsonTdsAuthorizationSignSpecificTopicContainer getContainerApproveRevokeTopicToSign(String utfPayload) throws Exception;
	
	
	
}
