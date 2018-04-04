/**
 * 
 */
package no.systema.tds.tdsexport.service;

import no.systema.tds.model.jsonjackson.JsonTdsAutoControlErrorContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsBilagdaHandlingarYKoderContainer;
import no.systema.tds.model.jsonjackson.validation.JsonTdsMangdEnhetContainer;
import no.systema.tds.mapper.jsonjackson.TdsAutoControlErrorMapper;
import no.systema.tds.mapper.jsonjackson.validation.TdsMangdEnhetMapper;
import no.systema.tds.tdsexport.mapper.jsonjackson.TdsExportSpecificTopicItemMapper;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemStatisticalValueContainer;
import no.systema.tds.tdsexport.mapper.jsonjackson.TdsExportTulltaxaKundensRegisterVarukodMapper;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportTulltaxaKundensRegisterVarukodContainer;

/**
 * @author oscardelatorre
 *
 */
public class TdsExportSpecificTopicItemServiceImpl implements TdsExportSpecificTopicItemService{
	/**
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonTdsExportSpecificTopicItemContainer getTdsExportSpecificTopicItemContainer(String utfPayload) {
		JsonTdsExportSpecificTopicItemContainer jsonTdsExportSpecificTopicItemContainer = null;
		try{
			TdsExportSpecificTopicItemMapper tdsExportSpecificTopicItemMapper = new TdsExportSpecificTopicItemMapper();
			jsonTdsExportSpecificTopicItemContainer = tdsExportSpecificTopicItemMapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonTdsExportSpecificTopicItemContainer;
		
	}
	
	/**
	 * 
	 */
	public JsonTdsExportSpecificTopicItemStatisticalValueContainer getTdsExportSpecificTopicItemStatisticalValueContainer(String utfPayload){
		JsonTdsExportSpecificTopicItemStatisticalValueContainer container = null;
		try{
			TdsExportSpecificTopicItemMapper tdsExportSpecificTopicItemMapper = new TdsExportSpecificTopicItemMapper();
			container = tdsExportSpecificTopicItemMapper.getStatisticalValueContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

	/**
	 * AutoControlError (used when update of error lines in auto-control item lines
	 */
	public JsonTdsAutoControlErrorContainer getTdsExportSpecificTopicItemAutoControlErrorContainer(String utfPayload){
		JsonTdsAutoControlErrorContainer container = null;
		try{
			TdsAutoControlErrorMapper mapper = new TdsAutoControlErrorMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
	/**
	 * 
	 */
	public JsonTdsMangdEnhetContainer getTdsMangdEnhetContainer (String utfPayload){
		JsonTdsMangdEnhetContainer container = null;
		try{
			TdsMangdEnhetMapper mapper = new TdsMangdEnhetMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsExportTulltaxaKundensRegisterVarukodContainer getKundRegisterVarukodContainer(String utfPayload) throws Exception{
		JsonTdsExportTulltaxaKundensRegisterVarukodContainer container= null;
		try{
			TdsExportTulltaxaKundensRegisterVarukodMapper mapper = new TdsExportTulltaxaKundensRegisterVarukodMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
}
