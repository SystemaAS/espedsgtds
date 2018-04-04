/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.mapper.MaintMainGenericMapper;
import no.systema.z.main.maintenance.model.JsonMaintMainFratxtContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Apr 03, 2018
 * 
 * 
 */
public class MaintMainFratxtServiceImpl implements MaintMainFratxtService {
	
	@Override
	public JsonMaintMainFratxtContainer getContainer(String utfPayload) {
		JsonMaintMainFratxtContainer container = null;
		try {
			MaintMainGenericMapper mapper = new MaintMainGenericMapper(new JsonMaintMainFratxtContainer());
			container = (JsonMaintMainFratxtContainer)mapper.getContainer(utfPayload);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return container;

	}

}
