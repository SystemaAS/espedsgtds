/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.model.JsonMaintMainSyparfContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Apr 3, 2018
 * 
 *
 */
public interface MaintMainSyparfService {
	/**
	 * Get JsonMaintMainSyparfContainer
	 * 
	 * @param utfPayload
	 * @return JsonMaintMainSyparfContainer
	 */
	public JsonMaintMainSyparfContainer getContainer(String utfPayload);
	
}
