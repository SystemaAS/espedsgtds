/**
 * 
 */
package no.systema.tds.z.maintenance.main.service;

import no.systema.tds.z.maintenance.main.mapper.jsonjackson.dbtable.MaintTulltaxaMapper;
import no.systema.tds.z.maintenance.main.model.MaintenanceMainTulltaxaObject;

/**
 * 
 * @author oscardelatorre
 * @date Oct 2019
 * 
 * 
 */
public class MaintTulltaxaServiceImpl implements MaintTulltaxaService {
	/**
	 * 
	 */
	public MaintenanceMainTulltaxaObject getList(String utfPayload) {
		MaintenanceMainTulltaxaObject container = null;
		try{
			MaintTulltaxaMapper mapper = new MaintTulltaxaMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	

}
