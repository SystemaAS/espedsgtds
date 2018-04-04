/**
 * 
 */
package no.systema.tds.service;

import no.systema.tds.mapper.jsonjackson.TdsCodeMapper;


import no.systema.tds.model.jsonjackson.codes.JsonTdsCodeContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsNctsCodeContainer;



/**
 * Accesses the mapper (for codes) general routine from AS400
 * 
 * @author oscardelatorre
 * @date Jan 15, 2016
 * 
 */
public class TdsGeneralCodesChildWindowServiceImpl implements TdsGeneralCodesChildWindowService {
	private TdsCodeMapper codeMapper = new TdsCodeMapper();
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsCodeContainer getCodeContainer(String utfPayload) throws Exception{
		return this.codeMapper.getContainer(utfPayload);
	}
	/*
	public JsonTdsNctsCodeContainer getNctsCodeContainer(String utfPayload) throws Exception{
		return this.codeMapper.getNctsContainer(utfPayload);
	}*/
	
}
