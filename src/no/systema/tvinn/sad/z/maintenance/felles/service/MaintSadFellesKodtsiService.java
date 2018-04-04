/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.felles.service;

import no.systema.tvinn.sad.z.maintenance.felles.model.JsonMaintSadFellesKodtsiContainer;

/**
 * 
 * @author oscardelatorre
 * @date Apr 4, 2018
 * 
 *
 */
public interface MaintSadFellesKodtsiService {
	public JsonMaintSadFellesKodtsiContainer getList(String utfPayload);
	public JsonMaintSadFellesKodtsiContainer doUpdate(String utfPayload);
	
}
