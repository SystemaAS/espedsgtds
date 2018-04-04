/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.service;

import no.systema.tvinn.sad.z.maintenance.sadexport.model.JsonMaintSadExportKodts6Container;

/**
 * 
 * @author Fredrik Möller
 * @date Apr 3, 2018
 * 
 *
 */
public interface MaintSadExportKodts6Service {
	public JsonMaintSadExportKodts6Container getList(String utfPayload);
	public JsonMaintSadExportKodts6Container doUpdate(String utfPayload);
}
