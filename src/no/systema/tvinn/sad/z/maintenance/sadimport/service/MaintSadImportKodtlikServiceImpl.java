/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.JsonMaintSadImportKodtlikContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.MaintSadImportKodtlikMapper;

/**
 * 
 * @author oscardelatorre
 * @date Mar 22, 2016
 * 
 * 
 */
public class MaintSadImportKodtlikServiceImpl implements MaintSadImportKodtlikService {
	/**
	 * 
	 */
	public JsonMaintSadImportKodtlikContainer getList(String utfPayload) {
		JsonMaintSadImportKodtlikContainer container = null;
		try{
			MaintSadImportKodtlikMapper mapper = new MaintSadImportKodtlikMapper();
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
	public JsonMaintSadImportKodtlikContainer doUpdate(String utfPayload) {
		JsonMaintSadImportKodtlikContainer container = null;
		try{
			MaintSadImportKodtlikMapper mapper = new MaintSadImportKodtlikMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
