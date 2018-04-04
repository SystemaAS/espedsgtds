/**
 * 
 */
package no.systema.tds.service;

import no.systema.tds.model.jsonjackson.codes.JsonTdsBilagdaHandlingarYKoderContainer;
import no.systema.tds.mapper.jsonjackson.TdsBilagdaHandligarYKoderMapper;


/**
 * 
 * @author oscardelatorre
 * @date Oct 14, 2015
 * 
 */
public class TdsBilagdaHandlingarYKoderServiceImpl implements TdsBilagdaHandlingarYKoderService {
	private TdsBilagdaHandligarYKoderMapper mapper = new TdsBilagdaHandligarYKoderMapper();
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsBilagdaHandlingarYKoderContainer getContainer(String utfPayload) throws Exception{
		return this.mapper.getContainer(utfPayload);
	}
	
}
