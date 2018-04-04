/**
 * 
 */
package no.systema.tds.z.maintenance.main.service;

import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtfiContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 03, 2017
 * 
 *
 */
public interface MaintSvtfiService {
	public JsonMaintSvtfiContainer getList(String utfPayload);
	public JsonMaintSvtfiContainer doUpdate(String utfPayload);
	
}
