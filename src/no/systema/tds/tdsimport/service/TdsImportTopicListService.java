/**
 * 
 */
package no.systema.tds.tdsimport.service;

import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicUtlamListContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicListExternalRefContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicListContainer;

/**
 * @author oscardelatorre
 * @date Sep 2, 2013
 *
 */
public interface TdsImportTopicListService {
	public JsonTdsImportTopicListContainer getTdsImportTopicListContainer(String utfPayload);
	public JsonTdsImportTopicUtlamListContainer getTdsImportTopicUtlamListContainer(String utfPayload);
	public JsonTdsImportTopicListExternalRefContainer getTdsImportTopicListExternalRefContainer(String utfPayload);
}
