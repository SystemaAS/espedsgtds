/**
 * 
 */
package no.systema.tds.z.maintenance.main.service;

import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtlvContainer;

/**
 * 
 * @author oscardelatorre
 * @date Jun 03, 2017
 * 
 *
 */
public interface MaintSvtlvService {
	public JsonMaintSvtlvContainer getList(String utfPayload);
	public JsonMaintSvtlvContainer doUpdate(String utfPayload);
	
}
