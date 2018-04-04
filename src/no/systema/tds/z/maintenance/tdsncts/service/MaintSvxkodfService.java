/**
 * 
 */
package no.systema.tds.z.maintenance.tdsncts.service;

import no.systema.tds.z.maintenance.tdsncts.model.jsonjackson.dbtable.JsonMaintSvxkodfContainer;;

/**
 * 
 * @author oscardelatorre
 * @date Jun 22, 2016
 * 
 *
 */
public interface MaintSvxkodfService {
	public JsonMaintSvxkodfContainer getList(String utfPayload);
	public JsonMaintSvxkodfContainer doUpdate(String utfPayload);
	
}
