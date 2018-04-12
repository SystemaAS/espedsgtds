/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.mapper.jsonjackson.dbtable.MaintMainGenericMapper;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtlikContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Apr 4, 2018
 * 
 * 
 */
public class MaintMainKodtlikServiceImpl implements MaintMainKodtlikService {
	
	@Override
	public JsonMaintMainKodtlikContainer getContainer(String utfPayload) {
		JsonMaintMainKodtlikContainer container = null;
		try {
			MaintMainGenericMapper mapper = new MaintMainGenericMapper(new JsonMaintMainKodtlikContainer());
			container = (JsonMaintMainKodtlikContainer)mapper.getContainer(utfPayload);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return container;

	}

}
