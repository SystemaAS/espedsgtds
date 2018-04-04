/**
 * 
 */
package no.systema.tds.z.maintenance.main.service;

import no.systema.tds.z.maintenance.main.mapper.jsonjackson.dbtable.MaintSvtfiMapper;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtfiContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 03, 2017
 * 
 * 
 */
public class MaintSvtfiServiceImpl implements MaintSvtfiService {
	/**
	 * 
	 */
	public JsonMaintSvtfiContainer getList(String utfPayload) {
		JsonMaintSvtfiContainer container = null;
		try{
			MaintSvtfiMapper mapper = new MaintSvtfiMapper();
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
	public JsonMaintSvtfiContainer doUpdate(String utfPayload) {
		JsonMaintSvtfiContainer container = null;
		try{
			MaintSvtfiMapper mapper = new MaintSvtfiMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
