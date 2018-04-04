/**
 * 
 */
package no.systema.tds.z.maintenance.main.service;

import no.systema.tds.z.maintenance.main.mapper.jsonjackson.dbtable.MaintSvtlvMapper;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtlvContainer;

/**
 * 
 * @author oscardelatorre
 * @date Jun 03, 2017
 * 
 * 
 */
public class MaintSvtlvServiceImpl implements MaintSvtlvService {
	/**
	 * 
	 */
	public JsonMaintSvtlvContainer getList(String utfPayload) {
		JsonMaintSvtlvContainer container = null;
		try{
			MaintSvtlvMapper mapper = new MaintSvtlvMapper();
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
	public JsonMaintSvtlvContainer doUpdate(String utfPayload) {
		JsonMaintSvtlvContainer container = null;
		try{
			MaintSvtlvMapper mapper = new MaintSvtlvMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
