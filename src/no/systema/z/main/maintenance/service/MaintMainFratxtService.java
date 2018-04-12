/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainFratxtContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Apr 03, 2018
 * 
 *
 */
public interface MaintMainFratxtService {

	public JsonMaintMainFratxtContainer getContainer(String utfPayload);
	
}
