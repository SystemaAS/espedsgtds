/**
 * 
 */
package no.systema.tds.service;

import no.systema.tds.model.jsonjackson.codes.JsonTdsTillaggskodContainer;
import no.systema.tds.mapper.jsonjackson.TdsTillaggskoderMapper;


/**
 * 
 * @author oscardelatorre
 * @date Oct 14, 2015
 * 
 */
public class TdsTillaggskoderServiceImpl implements TdsTillaggskoderService {
	private TdsTillaggskoderMapper mapper = new TdsTillaggskoderMapper();
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsTillaggskodContainer getContainer(String utfPayload) throws Exception{
		return this.mapper.getContainer(utfPayload);
	}
	
}
