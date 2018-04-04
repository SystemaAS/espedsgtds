/**
 * 
 */
package no.systema.tds.nctsimport.service;

import no.systema.tds.nctsimport.mapper.jsonjackson.NctsImportSpecificTopicUnloadingItemMapper;
import no.systema.tds.nctsimport.model.jsonjackson.topic.unloading.items.JsonNctsImportSpecificTopicUnloadingItemContainer;

/**
 * @author oscardelatorre
 * @date Dec 20, 2013
 */
public class NctsImportSpecificTopicUnloadingItemServiceImpl implements NctsImportSpecificTopicUnloadingItemService{
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 * 
	 */
	public JsonNctsImportSpecificTopicUnloadingItemContainer getNctsImportSpecificTopicUnloadingItemContainer(String utfPayload) {
		JsonNctsImportSpecificTopicUnloadingItemContainer container = null;
		try{
			NctsImportSpecificTopicUnloadingItemMapper mapper = new NctsImportSpecificTopicUnloadingItemMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	
}
