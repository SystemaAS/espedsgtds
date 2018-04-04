/**
 * 
 */
package no.systema.tds.nctsexport.service;

import no.systema.tds.nctsexport.mapper.jsonjackson.NctsExportSpecificTopicItemMapper;
import no.systema.tds.nctsexport.mapper.jsonjackson.NctsExportSpecificTopicItemSensitiveGoodsValidatorMapper;
import no.systema.tds.nctsexport.model.jsonjackson.topic.items.JsonNctsExportSpecificTopicItemContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.items.validation.JsonNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer;

/**
 * @author oscardelatorre
 * @date June 6, 2013
 *
 */
public class NctsExportSpecificTopicItemServiceImpl implements NctsExportSpecificTopicItemService{
	/**
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonNctsExportSpecificTopicItemContainer getNctsExportSpecificTopicItemContainer(String utfPayload) {
		JsonNctsExportSpecificTopicItemContainer jsonNctsExportSpecificTopicItemContainer = null;
		try{
			NctsExportSpecificTopicItemMapper nctsExportSpecificTopicItemMapper = new NctsExportSpecificTopicItemMapper();
			jsonNctsExportSpecificTopicItemContainer = nctsExportSpecificTopicItemMapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonNctsExportSpecificTopicItemContainer;
		
	}

	/**
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer getJsonNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer(String utfPayload){
		JsonNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer container = null;
		try{
			NctsExportSpecificTopicItemSensitiveGoodsValidatorMapper mapper = new NctsExportSpecificTopicItemSensitiveGoodsValidatorMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
}
