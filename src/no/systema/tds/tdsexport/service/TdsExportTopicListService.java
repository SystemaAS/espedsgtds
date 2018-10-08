/**
 * 
 */
package no.systema.tds.tdsexport.service;

import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicListExternalRefContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicListContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicZemListContainer;

/**
 * @author oscardelatorre
 * @date Nov 25, 2012
 *
 */
public interface TdsExportTopicListService {
	public JsonTdsExportTopicListContainer getTdsExportTopicListContainer(String utfPayload);
	public JsonTdsExportTopicZemListContainer getTdsExportTopicZemListContainer(String utfPayload);
	public JsonTdsExportTopicListExternalRefContainer getTdsExportTopicListExternalRefContainer(String utfPayload);
}
