/**
 * 
 */
package no.systema.tds.nctsimport.service;

import no.systema.tds.nctsimport.model.jsonjackson.topic.unloading.JsonNctsImportSpecificTopicUnloadingContainer;


/**
 * @author oscardelatorre
 * @date Dec 19, 2013
 */
public interface NctsImportSpecificTopicUnloadingService {
	public JsonNctsImportSpecificTopicUnloadingContainer getNctsImportSpecificTopicUnloadingContainer(String utfPayload);
	
}
