/**
 * 
 */
package no.systema.tds.z.maintenance.tdsnctsexport.service;

import no.systema.tds.z.maintenance.tdsnctsexport.model.jsonjackson.dbtable.JsonMaintSvxghContainer;;

/**
 * 
 * @author oscardelatorre
 * @date Jun 23, 2017
 * 
 *
 */
public interface MaintSvxghService {
	public JsonMaintSvxghContainer getList(String utfPayload);
	public JsonMaintSvxghContainer doUpdate(String utfPayload);
	
}
