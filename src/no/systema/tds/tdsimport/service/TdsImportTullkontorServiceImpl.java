/**
 * 
 */
package no.systema.tds.tdsimport.service;

import no.systema.tds.tdsimport.mapper.jsonjackson.TdsImportTullkontorMapper;
import no.systema.tds.tdsimport.model.jsonjackson.tullkontor.JsonTdsImportTullkontorContainer;

/**
 * @author oscardelatorre
 * @date Sep 2, 2013
 * 
 *
 */
public class TdsImportTullkontorServiceImpl implements TdsImportTullkontorService{
	public JsonTdsImportTullkontorContainer getTdsImportTullkontorContainer(String utfPayload) {
		JsonTdsImportTullkontorContainer jsonTdsImportTullkontorContainer = null;
		try{
			TdsImportTullkontorMapper mapper = new TdsImportTullkontorMapper();
			jsonTdsImportTullkontorContainer = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonTdsImportTullkontorContainer;
		
	}
}
