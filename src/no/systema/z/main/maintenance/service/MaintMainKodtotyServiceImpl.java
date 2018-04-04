/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.mapper.MaintMainGenericMapper;
import no.systema.z.main.maintenance.model.JsonMaintMainKodtotyContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Apr 4, 2018
 * 
 * 
 */
public class MaintMainKodtotyServiceImpl implements MaintMainKodtotyService {
	
	@Override
	public JsonMaintMainKodtotyContainer getContainer(String utfPayload) {
		JsonMaintMainKodtotyContainer container = null;
		try {
			MaintMainGenericMapper mapper = new MaintMainGenericMapper(new JsonMaintMainKodtotyContainer());
			container = (JsonMaintMainKodtotyContainer)mapper.getContainer(utfPayload);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return container;

	}

}
