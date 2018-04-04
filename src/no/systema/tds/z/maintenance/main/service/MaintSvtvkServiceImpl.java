/**
 * 
 */
package no.systema.tds.z.maintenance.main.service;

import no.systema.tds.z.maintenance.main.mapper.jsonjackson.dbtable.MaintSvtvkMapper;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtvkContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 19, 2017
 * 
 * 
 */
public class MaintSvtvkServiceImpl implements MaintSvtvkService {
	/**
	 * 
	 */
	public JsonMaintSvtvkContainer getList(String utfPayload) {
		JsonMaintSvtvkContainer container = null;
		try{
			MaintSvtvkMapper mapper = new MaintSvtvkMapper();
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
	public JsonMaintSvtvkContainer doUpdate(String utfPayload) {
		JsonMaintSvtvkContainer container = null;
		try{
			MaintSvtvkMapper mapper = new MaintSvtvkMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
