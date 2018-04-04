/**
 * 
 */
package no.systema.tds.tdsexport.service;

import no.systema.tds.tdsexport.model.jsonjackson.tullkontor.JsonTdsExportTullkontorContainer;

/**
 * @author oscardelatorre
 * @date Feb 16, 201
 *
 */
public interface TdsExportTullkontorService {
	public JsonTdsExportTullkontorContainer getTdsExportTullkontorContainer(String utfPayload);
}
