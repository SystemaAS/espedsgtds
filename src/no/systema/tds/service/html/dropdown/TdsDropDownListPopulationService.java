/**
 * 
 */
package no.systema.tds.service.html.dropdown;

import no.systema.tds.mapper.jsonjackson.TdsCodeMapper;
import no.systema.tds.mapper.jsonjackson.avdsignature.TdsAvdelningMapper;
import no.systema.tds.mapper.jsonjackson.avdsignature.TdsSignatureMapper;


import no.systema.tds.model.jsonjackson.codes.JsonTdsCodeContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsNctsCodeContainer;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsAvdelningContainer;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsSignatureContainer;



/**
 * Accesses the mapper (for codes) general routine from AS400
 * 
 * @author oscardelatorre
 * @date Feb 24, 2013
 * 
 */
public class TdsDropDownListPopulationService {
	private TdsCodeMapper codeMapper = new TdsCodeMapper();
	private TdsAvdelningMapper avdMapper = new TdsAvdelningMapper();
	private TdsSignatureMapper signMapper = new TdsSignatureMapper();
	
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsCodeContainer getCodeContainer(String utfPayload) throws Exception{
		return this.codeMapper.getContainer(utfPayload);
	}
	
	public JsonTdsNctsCodeContainer getNctsCodeContainer(String utfPayload) throws Exception{
		return this.codeMapper.getNctsContainer(utfPayload);
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsAvdelningContainer getAvdelningContainer(String utfPayload) throws Exception{
		return this.avdMapper.getContainer(utfPayload);
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsSignatureContainer getSignatureContainer(String utfPayload) throws Exception{
		return this.signMapper.getContainer(utfPayload);
	}
}
