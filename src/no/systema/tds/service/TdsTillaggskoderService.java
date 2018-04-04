/**
 * 
 */
package no.systema.tds.service;

import no.systema.tds.model.jsonjackson.codes.JsonTdsTillaggskodContainer;

/**
 * @author oscardelatorre
 * @date Oct 19, 2015
 * 
 */
public interface TdsTillaggskoderService {
	public JsonTdsTillaggskodContainer getContainer(String utfPayload) throws Exception;
}
