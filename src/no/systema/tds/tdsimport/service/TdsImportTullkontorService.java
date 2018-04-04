/**
 * 
 */
package no.systema.tds.tdsimport.service;

import no.systema.tds.tdsimport.model.jsonjackson.tullkontor.JsonTdsImportTullkontorContainer;

/**
 * @author oscardelatorre
 * @date Feb 16, 201
 *
 */
public interface TdsImportTullkontorService {
	public JsonTdsImportTullkontorContainer getTdsImportTullkontorContainer(String utfPayload);
}
