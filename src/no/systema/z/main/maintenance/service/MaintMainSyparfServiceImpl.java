/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.mapper.jsonjackson.dbtable.MaintMainGenericMapper;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainSyparfContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Apr 3, 2018
 * 
 * 
 */
public class MaintMainSyparfServiceImpl implements MaintMainSyparfService {
	
	@Override
	public JsonMaintMainSyparfContainer getContainer(String utfPayload) {
		JsonMaintMainSyparfContainer container = null;
		try {
			MaintMainGenericMapper mapper = new MaintMainGenericMapper(new JsonMaintMainSyparfContainer());
			container = (JsonMaintMainSyparfContainer)mapper.getContainer(utfPayload);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return container;

	}

}
