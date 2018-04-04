/**
 * 
 */
package no.systema.tds.nctsimport.service;

import no.systema.tds.nctsimport.model.jsonjackson.topic.items.JsonNctsImportSpecificTopicItemContainer;


/**
 * @author oscardelatorre
 * @date Dec 16, 2013
 * 
 */
public interface NctsImportSpecificTopicItemService {
	public JsonNctsImportSpecificTopicItemContainer getNctsImportSpecificTopicItemContainer(String utfPayload);
}
