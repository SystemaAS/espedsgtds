/**
 * 
 */
package no.systema.tds.service;

import no.systema.tds.mapper.jsonjackson.TdsTaricVarukodMapper;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTaricVarukodContainer;

/**
 * This service could be moved to the service.html.dropdown package but we do not know if the Taric varukod
 * will be render as list or maybe as a tree. Could be a table. We do separate this service outside the html.dropdown package...
 * 
 * @author oscardelatorre
 * @date Mar 9, 2013
 */
public class TdsTaricVarukodServiceImpl implements TdsTaricVarukodService {
	private TdsTaricVarukodMapper taricMapper = new TdsTaricVarukodMapper();
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsTaricVarukodContainer getContainer(String utfPayload) throws Exception{
		return this.taricMapper.getContainer(utfPayload);
	}
}
