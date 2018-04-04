/**
 * 
 */
package no.systema.tds.nctsexport.service;

import no.systema.tds.nctsexport.model.jsonjackson.topic.items.JsonNctsExportSpecificTopicItemContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.items.validation.JsonNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer;


/**
 * @author oscardelatorre
 *
 */
public interface NctsExportSpecificTopicItemService {
	public JsonNctsExportSpecificTopicItemContainer getNctsExportSpecificTopicItemContainer(String utfPayload);
	public JsonNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer getJsonNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer(String utfPayload);
}
