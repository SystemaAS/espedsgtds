/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.model.JsonMaintMainCundfContainer;
import no.systema.z.main.maintenance.mapper.MaintMainCundfMapper;

/**
 * 
 * @author oscardelatorre
 * @date Apr 03, 2018
 * 
 * 
 */
public class MaintMainCundfServiceImpl implements MaintMainCundfService {
	/**
	 * 
	 */
	public JsonMaintMainCundfContainer getList(String utfPayload) {
		JsonMaintMainCundfContainer container = null;
		try{
			MaintMainCundfMapper mapper = new MaintMainCundfMapper();
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
	public JsonMaintMainCundfContainer doUpdate(String utfPayload) {
		JsonMaintMainCundfContainer container = null;
		try{
			MaintMainCundfMapper mapper = new MaintMainCundfMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
