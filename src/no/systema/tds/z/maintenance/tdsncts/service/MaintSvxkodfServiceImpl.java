/**
 * 
 */
package no.systema.tds.z.maintenance.tdsncts.service;

import no.systema.tds.z.maintenance.tdsncts.mapper.jsonjackson.dbtable.MaintSvxkodfMapper;
import no.systema.tds.z.maintenance.tdsncts.model.jsonjackson.dbtable.JsonMaintSvxkodfContainer;


/**
 * 
 * @author oscardelatorre
 * @date Apr 10, 2017
 * 
 * 
 */
public class MaintSvxkodfServiceImpl implements MaintSvxkodfService {
	/**
	 * 
	 */
	public JsonMaintSvxkodfContainer getList(String utfPayload) {
		JsonMaintSvxkodfContainer container = null;
		try{
			MaintSvxkodfMapper mapper = new MaintSvxkodfMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 */
	public JsonMaintSvxkodfContainer doUpdate(String utfPayload) {
		JsonMaintSvxkodfContainer container = null;
		try{
			MaintSvxkodfMapper mapper = new MaintSvxkodfMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
