/**
 * 
 */
package no.systema.tds.z.maintenance.main.service;

import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtvkContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 19, 2017
 * 
 *
 */
public interface MaintSvtvkService {
	public JsonMaintSvtvkContainer getList(String utfPayload);
	public JsonMaintSvtvkContainer doUpdate(String utfPayload);
	
}
