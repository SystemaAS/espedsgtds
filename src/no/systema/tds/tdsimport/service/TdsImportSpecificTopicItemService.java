/**
 * 
 */
package no.systema.tds.tdsimport.service;

import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemAvgifterContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemBilagdaHandlingarContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemFormanskoderContainer;


import no.systema.tds.model.jsonjackson.JsonTdsAutoControlErrorContainer;
import no.systema.tds.model.jsonjackson.validation.JsonTdsMangdEnhetContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportTulltaxaKundensRegisterVarukodContainer;
/**
 * 
 * @author oscardelatorre
 * @date Sep 2, 2103
 * 
 *
 */
public interface TdsImportSpecificTopicItemService {
	
	public JsonTdsImportSpecificTopicItemContainer getTdsImportSpecificTopicItemContainer(String utfPayload);
	public JsonTdsImportSpecificTopicItemAvgifterContainer getTdsImportSpecificTopicItemAvgifterContainer(String utfPayload);
	public JsonTdsImportSpecificTopicItemBilagdaHandlingarContainer getTdsBilagdaHandlingarContainer (String utfPayload);
	public JsonTdsImportSpecificTopicItemFormanskoderContainer getTdsFormanskoderContainer (String utfPayload);
	public JsonTdsMangdEnhetContainer getTdsMangdEnhetContainer (String utfPayload);
	public JsonTdsAutoControlErrorContainer getTdsImportSpecificTopicItemAutoControlErrorContainer(String utfPayload);
	
	public JsonTdsImportTulltaxaKundensRegisterVarukodContainer getKundRegisterVarukodContainer(String utfPayload) throws Exception;
	
}
