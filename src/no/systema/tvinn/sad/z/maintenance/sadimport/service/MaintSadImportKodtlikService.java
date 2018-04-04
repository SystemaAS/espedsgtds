/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.JsonMaintSadImportKodtlikContainer;

/**
 * 
 * @author oscardelatorre
 * @date Apr 4, 2018
 * 
 *
 */
public interface MaintSadImportKodtlikService {
	public JsonMaintSadImportKodtlikContainer getList(String utfPayload);
	public JsonMaintSadImportKodtlikContainer doUpdate(String utfPayload);
	
}
