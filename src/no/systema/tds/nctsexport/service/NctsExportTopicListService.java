/**
 * 
 */
package no.systema.tds.nctsexport.service;

import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportTopicListContainer;

/**
 * @author oscardelatorre
 * @date May 17, 2013
 *
 */
public interface NctsExportTopicListService {
	public JsonNctsExportTopicListContainer getNctsExportTopicListContainer(String utfPayload);
}
