/**
 * 
 */
package no.systema.tds.service;

import no.systema.tds.model.jsonjackson.codes.JsonTdsCodeContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsNctsCodeContainer;


/**
 * @author oscardelatorre
 * @date Jan 15, 2016
 * 
 */
public interface TdsGeneralCodesChildWindowService {
	public JsonTdsCodeContainer getCodeContainer(String utfPayload) throws Exception;
	public JsonTdsNctsCodeContainer getNctsCodeContainer(String utfPayload) throws Exception;
}
