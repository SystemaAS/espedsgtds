/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.model.JsonMaintMainEdiiContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 30, 2018
 * 
 *
 */
public interface MaintMainEdiiService {
	public JsonMaintMainEdiiContainer getList(String utfPayload);
	public JsonMaintMainEdiiContainer doUpdate(String utfPayload);
	
}
