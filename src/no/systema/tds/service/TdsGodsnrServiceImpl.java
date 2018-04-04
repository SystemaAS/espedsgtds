/**
 * 
 */
package no.systema.tds.service;

import no.systema.tds.model.jsonjackson.JsonTdsGodsnrContainer;
import no.systema.tds.mapper.jsonjackson.TdsGodsnrMapper;


/**
 * 
 * @author oscardelatorre
 * @date Oct 14, 2015
 * 
 */
public class TdsGodsnrServiceImpl implements TdsGodsnrService {
	private TdsGodsnrMapper mapper = new TdsGodsnrMapper();
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsGodsnrContainer getContainer(String utfPayload) throws Exception{
		return this.mapper.getContainer(utfPayload);
	}
	
}
