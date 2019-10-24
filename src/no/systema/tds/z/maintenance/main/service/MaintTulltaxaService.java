/**
 * 
 */
package no.systema.tds.z.maintenance.main.service;

import no.systema.tds.z.maintenance.main.model.MaintenanceMainTulltaxaObject;

/**
 * 
 * @author oscardelatorre
 * @date Oct 2019
 * 
 *
 */
public interface MaintTulltaxaService {
	public MaintenanceMainTulltaxaObject getList(String utfPayload);
	
}
