/**
 * 
 */
package no.systema.tds.service;

import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureNameContainer;
import no.systema.tds.mapper.jsonjackson.avdsignature.TdsSignatureMapper;


/**
 * 
 * @author oscardelatorre
 * @date Nov 6, 2013
 * 
 */
public class TdsSignatureNameServiceImpl implements TdsSignatureNameService {
	private TdsSignatureMapper tdsSignatureMapper = new TdsSignatureMapper();
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsSignatureNameContainer getContainer(String utfPayload) throws Exception{
		return this.tdsSignatureMapper.getSignatureNameContainer(utfPayload);
	}
	
}
