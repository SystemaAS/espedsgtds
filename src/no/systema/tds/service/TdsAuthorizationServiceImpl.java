/**
 * 
 */
package no.systema.tds.service;

import no.systema.tds.mapper.jsonjackson.authorization.TdsAuthorizationMapper;
import no.systema.tds.model.jsonjackson.authorization.JsonTdsAuthorizationContainer;
import no.systema.tds.model.jsonjackson.authorization.JsonTdsPkiSmsCodeContainer;
//Topic list
import no.systema.tds.mapper.jsonjackson.authorization.topic.TdsAuthorizationTopicListMapper;
import no.systema.tds.model.jsonjackson.authorization.topic.JsonTdsAuthorizationTopicListContainer;
//Specific Topic to sign
import no.systema.tds.mapper.jsonjackson.authorization.topic.TdsAuthorizationSpecificTopicToSignMapper;
import no.systema.tds.model.jsonjackson.authorization.topic.JsonTdsAuthorizationSignSpecificTopicContainer;


/**
 * 
 * @author oscardelatorre
 * @date June 17, 2013
 */
public class TdsAuthorizationServiceImpl implements TdsAuthorizationService {
	private TdsAuthorizationMapper mapper = new TdsAuthorizationMapper();
	private TdsAuthorizationTopicListMapper topicListMapper = new TdsAuthorizationTopicListMapper();
	private TdsAuthorizationSpecificTopicToSignMapper specificTopicMapper = new TdsAuthorizationSpecificTopicToSignMapper();
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsAuthorizationContainer getContainer(String utfPayload) throws Exception{
		return this.mapper.getContainer(utfPayload);
	}
	
	/**
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsPkiSmsCodeContainer getContainerPkiSmsCode(String utfPayload) throws Exception{
		return this.mapper.getCodeContainerPkiSmsCode(utfPayload);
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsAuthorizationTopicListContainer getContainerTopicList(String utfPayload) throws Exception{
		return this.topicListMapper.getContainer(utfPayload);
	}
	
	public JsonTdsAuthorizationSignSpecificTopicContainer getContainerApproveRevokeTopicToSign(String utfPayload) throws Exception{
		return this.specificTopicMapper.getContainer(utfPayload);
	}
}
