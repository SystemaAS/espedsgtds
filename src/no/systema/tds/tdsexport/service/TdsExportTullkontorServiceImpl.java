/**
 * 
 */
package no.systema.tds.tdsexport.service;

import no.systema.tds.tdsexport.mapper.jsonjackson.TdsExportTullkontorMapper;
import no.systema.tds.tdsexport.model.jsonjackson.tullkontor.JsonTdsExportTullkontorContainer;

/**
 * @author oscardelatorre
 *
 */
public class TdsExportTullkontorServiceImpl implements TdsExportTullkontorService{
	public JsonTdsExportTullkontorContainer getTdsExportTullkontorContainer(String utfPayload) {
		JsonTdsExportTullkontorContainer jsonTdsExportTullkontorContainer = null;
		try{
			TdsExportTullkontorMapper tdsExportTullkontorMapper = new TdsExportTullkontorMapper();
			jsonTdsExportTullkontorContainer = tdsExportTullkontorMapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonTdsExportTullkontorContainer;
		
	}
}
