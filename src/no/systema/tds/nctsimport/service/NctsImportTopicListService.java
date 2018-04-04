/**
 * 
 */
package no.systema.tds.nctsimport.service;

import no.systema.tds.nctsimport.model.jsonjackson.topic.JsonNctsImportTopicListContainer;

/**
 * @author oscardelatorre
 * @date Dec 3, 2013
 *
 */
public interface NctsImportTopicListService {
	public JsonNctsImportTopicListContainer getNctsImportTopicListContainer(String utfPayload);
}
