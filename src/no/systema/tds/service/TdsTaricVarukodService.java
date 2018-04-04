/**
 * 
 */
package no.systema.tds.service;

import no.systema.tds.model.jsonjackson.codes.JsonTdsTaricVarukodContainer;

/**
 * @author oscardelatorre
 *
 */
public interface TdsTaricVarukodService {
	public JsonTdsTaricVarukodContainer getContainer(String utfPayload) throws Exception;
}
