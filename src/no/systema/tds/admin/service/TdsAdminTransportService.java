/**
 * 
 */
package no.systema.tds.admin.service;

import no.systema.tds.admin.model.jsonjackson.topic.JsonTdsAdminTransportListContainer;
import no.systema.tds.admin.model.jsonjackson.topic.JsonTdsAdminNorskImportListContainer;
import no.systema.tds.admin.model.jsonjackson.topic.JsonTdsAdminNorskExportListContainer;


/**
 * @author oscardelatorre
 *
 */
public interface TdsAdminTransportService {
	public JsonTdsAdminTransportListContainer getTdsAdminTransportListContainer(String utfPayload);
	public JsonTdsAdminNorskImportListContainer getTdsAdminNorskImportListContainer(String utfPayload);
	public JsonTdsAdminNorskExportListContainer getTdsAdminNorskExportListContainer(String utfPayload);
	
}
