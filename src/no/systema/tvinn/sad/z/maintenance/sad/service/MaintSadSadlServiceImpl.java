/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sad.service;

import no.systema.tvinn.sad.z.maintenance.sad.mapper.MaintSadSadlMapper;
import no.systema.tvinn.sad.z.maintenance.sad.model.JsonMaintSadSadlContainer;

/**
 * 
 * @author oscardelatorre
 * @date Apr 3, 2018
 * 
 * 
 */
public class MaintSadSadlServiceImpl implements MaintSadSadlService {
	/**
	 * 
	 */
	public JsonMaintSadSadlContainer getList(String utfPayload) {
		JsonMaintSadSadlContainer container = null;
		try{
			MaintSadSadlMapper mapper = new MaintSadSadlMapper();
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
	public JsonMaintSadSadlContainer doUpdate(String utfPayload) {
		JsonMaintSadSadlContainer container = null;
		try{
			MaintSadSadlMapper mapper = new MaintSadSadlMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
