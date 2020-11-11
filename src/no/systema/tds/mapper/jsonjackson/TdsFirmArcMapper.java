/**
 * 
 */
package no.systema.tds.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.tds.model.jsonjackson.JsonTdsArkivpContainer;
import no.systema.tds.model.jsonjackson.JsonTdsFirmArcContainer;


/**
 * @author oscardelatorre
 * Nov 2020
 * 
 */
public class TdsFirmArcMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(TdsFirmArcMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsFirmArcContainer getContainer(String utfPayload) throws Exception{
		JsonTdsFirmArcContainer container = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsFirmArcContainer.class); 
		}	
		return container;
	}
	
	
	public JsonTdsArkivpContainer getArkivpContainer(String utfPayload) throws Exception{
		JsonTdsArkivpContainer container = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTdsArkivpContainer.class); 
		}	
		return container;
	}
	
}
