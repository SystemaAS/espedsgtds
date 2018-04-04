/**
 * 
 */
package no.systema.tds.nctsexport.service;

import no.systema.tds.nctsexport.mapper.jsonjackson.NctsExportTopicListMapper;
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportTopicListContainer;

/**
 * @author oscardelatorre
 * @date May 17, 2013
 */
public class NctsExportTopicListServiceImpl implements NctsExportTopicListService {

	public JsonNctsExportTopicListContainer getNctsExportTopicListContainer(String utfPayload) {
		JsonNctsExportTopicListContainer jsonNctsExportTopicListContainer = null;
		try{
			NctsExportTopicListMapper nctsExportTopicListMapper = new NctsExportTopicListMapper();
			jsonNctsExportTopicListContainer = nctsExportTopicListMapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonNctsExportTopicListContainer;
		
	}

}
