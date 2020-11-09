/**
 * 
 */
package no.systema.main.service;

import no.systema.tds.model.jsonjackson.JsonTdsFirmArcContainer;

import org.springframework.stereotype.Service;
import no.systema.tds.mapper.jsonjackson.TdsFirmArcMapper;

/**
 * 
 * @author oscardelatorre
 * Nov 2020
 * 
 */
@Service
public class FirmArcService{
	public JsonTdsFirmArcContainer getContainer(String utfPayload) {
		JsonTdsFirmArcContainer container = null;
		try{
			TdsFirmArcMapper mapper = new TdsFirmArcMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
}
