/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.model.JsonMaintMainQaokp08aContainer;
import no.systema.z.main.maintenance.mapper.MaintMainQaokp08aMapper;

/**
 * 
 * @author oscardelatorre
 * @date Apr 03, 2018
 * 
 * 
 */
public class MaintMainQaokp08aServiceImpl implements MaintMainQaokp08aService {
	/**
	 * 
	 */
	public JsonMaintMainQaokp08aContainer getList(String utfPayload) {
		JsonMaintMainQaokp08aContainer container = null;
		try{
			MaintMainQaokp08aMapper mapper = new MaintMainQaokp08aMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
