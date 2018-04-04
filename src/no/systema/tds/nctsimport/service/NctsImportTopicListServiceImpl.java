/**
 * 
 */
package no.systema.tds.nctsimport.service;

import no.systema.tds.nctsimport.mapper.jsonjackson.NctsImportTopicListMapper;
import no.systema.tds.nctsimport.model.jsonjackson.topic.JsonNctsImportTopicListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Dec 3, 2013
 * 
 */
public class NctsImportTopicListServiceImpl implements NctsImportTopicListService {

	public JsonNctsImportTopicListContainer getNctsImportTopicListContainer(String utfPayload) {
		JsonNctsImportTopicListContainer container = null;
		try{
			NctsImportTopicListMapper mapper = new NctsImportTopicListMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
