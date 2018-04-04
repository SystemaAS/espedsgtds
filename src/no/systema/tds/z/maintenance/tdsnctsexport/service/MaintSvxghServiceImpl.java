/**
 * 
 */
package no.systema.tds.z.maintenance.tdsnctsexport.service;

import no.systema.tds.z.maintenance.tdsnctsexport.mapper.jsonjackson.dbtable.MaintSvxghMapper;
import no.systema.tds.z.maintenance.tdsnctsexport.model.jsonjackson.dbtable.JsonMaintSvxghContainer;

/**
 * 
 * @author oscardelatorre
 * @date Jun 13, 2016
 * 
 * 
 */
public class MaintSvxghServiceImpl implements MaintSvxghService {
	/**
	 * 
	 */
	public JsonMaintSvxghContainer getList(String utfPayload) {
		JsonMaintSvxghContainer container = null;
		try{
			MaintSvxghMapper mapper = new MaintSvxghMapper();
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
	public JsonMaintSvxghContainer doUpdate(String utfPayload) {
		JsonMaintSvxghContainer container = null;
		try{
			MaintSvxghMapper mapper = new MaintSvxghMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
