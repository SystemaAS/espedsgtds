/**
 * 
 */
package no.systema.tds.tdsimport.service;

import no.systema.tds.tdsimport.mapper.jsonjackson.TdsImportTopicListMapper;
import no.systema.tds.tdsimport.mapper.jsonjackson.TdsImportTopicUtlamListMapper;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicListContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicUtlamListContainer;

/**
 * @author oscardelatorre
 * @date Sep 2, 2013
 */
public class TdsImportTopicListServiceImpl implements TdsImportTopicListService {

	public JsonTdsImportTopicListContainer getTdsImportTopicListContainer(String utfPayload) {
		JsonTdsImportTopicListContainer listContainer = null;
		try{
			TdsImportTopicListMapper tdsExportTopicListMapper = new TdsImportTopicListMapper();
			listContainer = tdsExportTopicListMapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return listContainer;
		
	}
	/**
	 * 
	 */
	public JsonTdsImportTopicUtlamListContainer getTdsImportTopicUtlamListContainer(String utfPayload){
		JsonTdsImportTopicUtlamListContainer listContainer = null;
		try{
			TdsImportTopicUtlamListMapper mapper = new TdsImportTopicUtlamListMapper();
			listContainer = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return listContainer;
		
	}

}
