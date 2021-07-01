/**
 * 
 */
package no.systema.tds.z.maintenance.tdsnctsexport.service;

import no.systema.tds.z.maintenance.tdsnctsexport.mapper.jsonjackson.dbtable.MaintSvxghMapper;
import no.systema.tds.z.maintenance.tdsnctsexport.mapper.jsonjackson.dbtable.MaintSvxhMapper;
import no.systema.tds.z.maintenance.tdsnctsexport.model.jsonjackson.dbtable.JsonMaintSvxghContainer;
import no.systema.tds.z.maintenance.tdsnctsexport.model.jsonjackson.dbtable.JsonMaintSvxhContainer;

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
	
	public JsonMaintSvxhContainer getListReservedGuaranty(String utfPayload) {
		JsonMaintSvxhContainer container = null;
		try{
			MaintSvxhMapper mapper = new MaintSvxhMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
	public JsonMaintSvxhContainer doReleaseGuarantee(String utfPayload) {
		JsonMaintSvxhContainer container = null;
		try{
			MaintSvxhMapper mapper = new MaintSvxhMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}

}
