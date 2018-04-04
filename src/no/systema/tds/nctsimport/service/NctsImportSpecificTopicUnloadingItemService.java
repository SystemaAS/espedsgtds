/**
 * 
 */
package no.systema.tds.nctsimport.service;

import no.systema.tds.nctsimport.model.jsonjackson.topic.unloading.items.JsonNctsImportSpecificTopicUnloadingItemContainer;


/**
 * @author oscardelatorre
 * @date Dec 20, 2013
 */
public interface NctsImportSpecificTopicUnloadingItemService {
	public JsonNctsImportSpecificTopicUnloadingItemContainer getNctsImportSpecificTopicUnloadingItemContainer(String utfPayload);
	
}
