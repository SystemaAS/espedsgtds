/**
 * 
 */
package no.systema.tds.nctsimport.service;

import no.systema.tds.nctsimport.mapper.jsonjackson.NctsImportSpecificTopicUnloadingMapper;
import no.systema.tds.nctsimport.model.jsonjackson.topic.unloading.JsonNctsImportSpecificTopicUnloadingContainer;

/**
 * @author oscardelatorre
 * @date Dec 4, 2013
 */
public class NctsImportSpecificTopicUnloadingServiceImpl implements NctsImportSpecificTopicUnloadingService{
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 * 
	 */
	public JsonNctsImportSpecificTopicUnloadingContainer getNctsImportSpecificTopicUnloadingContainer(String utfPayload) {
		JsonNctsImportSpecificTopicUnloadingContainer container = null;
		try{
			NctsImportSpecificTopicUnloadingMapper mapper = new NctsImportSpecificTopicUnloadingMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	
}
