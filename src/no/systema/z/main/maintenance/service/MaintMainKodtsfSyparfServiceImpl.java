/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtsfSyparfContainer;
import no.systema.z.main.maintenance.mapper.jsonjackson.dbtable.MaintMainKodtsfSyparfMapper;

/**
 * 
 * @author oscardelatorre
 * @date Apr 03, 2018
 * 
 * 
 */
public class MaintMainKodtsfSyparfServiceImpl implements MaintMainKodtsfSyparfService {
	/**
	 * 
	 */
	public JsonMaintMainKodtsfSyparfContainer getList(String utfPayload) {
		JsonMaintMainKodtsfSyparfContainer container = null;
		try{
			MaintMainKodtsfSyparfMapper mapper = new MaintMainKodtsfSyparfMapper();
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
	public JsonMaintMainKodtsfSyparfContainer doUpdate(String utfPayload) {
		JsonMaintMainKodtsfSyparfContainer container = null;
		try{
			MaintMainKodtsfSyparfMapper mapper = new MaintMainKodtsfSyparfMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
