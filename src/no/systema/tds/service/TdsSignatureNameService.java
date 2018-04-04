/**
 * 
 */
package no.systema.tds.service;

import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureNameContainer;

/**
 * @author oscardelatorre
 * @date Nov 6, 2013
 * 
 */
public interface TdsSignatureNameService {
	public JsonTdsSignatureNameContainer getContainer(String utfPayload) throws Exception;
}
