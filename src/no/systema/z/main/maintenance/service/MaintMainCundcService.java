/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.model.JsonMaintMainCundcContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Apr 3, 2018
 * 
 *
 */
public interface MaintMainCundcService {
	public JsonMaintMainCundcContainer getList(String utfPayload);
	public JsonMaintMainCundcContainer doUpdate(String utfPayload);
	
}
