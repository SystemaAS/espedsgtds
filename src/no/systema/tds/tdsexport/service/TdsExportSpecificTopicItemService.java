/**
 * 
 */
package no.systema.tds.tdsexport.service;

import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemStatisticalValueContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportTulltaxaKundensRegisterVarukodContainer;
import no.systema.tds.model.jsonjackson.JsonTdsAutoControlErrorContainer;
import no.systema.tds.model.jsonjackson.validation.JsonTdsMangdEnhetContainer;



/**
 * @author oscardelatorre
 *
 */
public interface TdsExportSpecificTopicItemService {
	public JsonTdsExportSpecificTopicItemContainer getTdsExportSpecificTopicItemContainer(String utfPayload);
	public JsonTdsExportSpecificTopicItemStatisticalValueContainer getTdsExportSpecificTopicItemStatisticalValueContainer(String utfPayload);
	public JsonTdsAutoControlErrorContainer getTdsExportSpecificTopicItemAutoControlErrorContainer(String utfPayload);
	public JsonTdsMangdEnhetContainer getTdsMangdEnhetContainer (String utfPayload);
	
	public JsonTdsExportTulltaxaKundensRegisterVarukodContainer getKundRegisterVarukodContainer(String utfPayload) throws Exception;
	
}
