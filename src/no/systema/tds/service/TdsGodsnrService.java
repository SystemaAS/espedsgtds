/**
 * 
 */
package no.systema.tds.service;

import no.systema.tds.model.jsonjackson.JsonTdsGodsnrContainer;

/**
 * @author oscardelatorre
 * @date Oct 14, 2015
 * 
 */
public interface TdsGodsnrService {
	public JsonTdsGodsnrContainer getContainer(String utfPayload) throws Exception;
}
