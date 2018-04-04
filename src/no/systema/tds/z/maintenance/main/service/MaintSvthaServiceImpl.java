/**
 * 
 */
package no.systema.tds.z.maintenance.main.service;

import no.systema.tds.z.maintenance.main.mapper.jsonjackson.dbtable.MaintSvthaMapper;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvthaContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 03, 2017
 * 
 * 
 */
public class MaintSvthaServiceImpl implements MaintSvthaService {
	/**
	 * 
	 */
	public JsonMaintSvthaContainer getList(String utfPayload) {
		JsonMaintSvthaContainer container = null;
		try{
			MaintSvthaMapper mapper = new MaintSvthaMapper();
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
	public JsonMaintSvthaContainer doUpdate(String utfPayload) {
		JsonMaintSvthaContainer container = null;
		try{
			MaintSvthaMapper mapper = new MaintSvthaMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
