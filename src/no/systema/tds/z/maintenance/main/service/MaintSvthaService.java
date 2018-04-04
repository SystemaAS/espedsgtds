/**
 * 
 */
package no.systema.tds.z.maintenance.main.service;

import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvthaContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 08, 2017
 * 
 *
 */
public interface MaintSvthaService {
	public JsonMaintSvthaContainer getList(String utfPayload);
	public JsonMaintSvthaContainer doUpdate(String utfPayload);
	
}
