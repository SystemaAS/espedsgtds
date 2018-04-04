/**
 * 
 */
package no.systema.tds.nctsimport.service;

import no.systema.tds.nctsimport.mapper.jsonjackson.NctsImportSpecificTopicItemMapper;
import no.systema.tds.nctsimport.model.jsonjackson.topic.items.JsonNctsImportSpecificTopicItemContainer;

/**
 * @author oscardelatorre
 * @date June 6, 2013
 *
 */
public class NctsImportSpecificTopicItemServiceImpl implements NctsImportSpecificTopicItemService{
	/**
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonNctsImportSpecificTopicItemContainer getNctsImportSpecificTopicItemContainer(String utfPayload) {
		JsonNctsImportSpecificTopicItemContainer container = null;
		try{
			NctsImportSpecificTopicItemMapper mapper = new NctsImportSpecificTopicItemMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
}
