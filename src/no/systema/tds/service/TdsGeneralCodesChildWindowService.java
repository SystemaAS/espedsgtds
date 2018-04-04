/**
 * 
 */
package no.systema.tds.service;

import no.systema.tds.model.jsonjackson.codes.JsonTdsCodeContainer;

/**
 * @author oscardelatorre
 * @date Jan 15, 2016
 * 
 */
public interface TdsGeneralCodesChildWindowService {
	public JsonTdsCodeContainer getCodeContainer(String utfPayload) throws Exception;
}
