/**
 * 
 */
package no.systema.tds.service;

import no.systema.tds.model.jsonjackson.codes.JsonTdsBilagdaHandlingarYKoderContainer;

/**
 * @author oscardelatorre
 * @date Oct 19, 2015
 * 
 */
public interface TdsBilagdaHandlingarYKoderService {
	public JsonTdsBilagdaHandlingarYKoderContainer getContainer(String utfPayload) throws Exception;
}
