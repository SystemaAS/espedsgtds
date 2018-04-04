/**
 * 
 */
package no.systema.tds.tdsimport.service;

import no.systema.tds.tdsimport.mapper.jsonjackson.TdsImportSpecificTopicItemMapper;
import no.systema.tds.mapper.jsonjackson.validation.TdsMangdEnhetMapper;
import no.systema.tds.mapper.jsonjackson.TdsAutoControlErrorMapper;
import no.systema.tds.tdsimport.mapper.jsonjackson.TdsImportTulltaxaKundensRegisterVarukodMapper;

import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemAvgifterContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemBilagdaHandlingarContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemFormanskoderContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportTulltaxaKundensRegisterVarukodContainer;

import no.systema.tds.model.jsonjackson.JsonTdsAutoControlErrorContainer;
import no.systema.tds.model.jsonjackson.validation.JsonTdsMangdEnhetContainer;

/**
 * @author oscardelatorre
 * @date Sep 2, 2013
 * 
 *
 */
public class TdsImportSpecificTopicItemServiceImpl implements TdsImportSpecificTopicItemService{
	/**
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonTdsImportSpecificTopicItemContainer getTdsImportSpecificTopicItemContainer(String utfPayload) {
		JsonTdsImportSpecificTopicItemContainer jsonTdsExportSpecificTopicItemContainer = null;
		try{
			TdsImportSpecificTopicItemMapper tdsExportSpecificTopicItemMapper = new TdsImportSpecificTopicItemMapper();
			jsonTdsExportSpecificTopicItemContainer = tdsExportSpecificTopicItemMapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonTdsExportSpecificTopicItemContainer;
		
	}
	
	/**
	 * Maps the avgifter
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonTdsImportSpecificTopicItemAvgifterContainer getTdsImportSpecificTopicItemAvgifterContainer(String utfPayload) {
		JsonTdsImportSpecificTopicItemAvgifterContainer jsonTdsExportSpecificTopicItemAvgifterContainer = null;
		try{
			TdsImportSpecificTopicItemMapper tdsExportSpecificTopicItemMapper = new TdsImportSpecificTopicItemMapper();
			jsonTdsExportSpecificTopicItemAvgifterContainer = tdsExportSpecificTopicItemMapper.getAvgifterContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonTdsExportSpecificTopicItemAvgifterContainer;
		
	}
	
	/**
	 * Maps the mangdenhet (Yes or No)
	 * 
	 * @param utfPayload
	 * @return
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
	 * 
	 * 
	 */
	public JsonTdsImportSpecificTopicItemBilagdaHandlingarContainer getTdsBilagdaHandlingarContainer (String utfPayload){
		JsonTdsImportSpecificTopicItemBilagdaHandlingarContainer container= null;
		try{
			TdsImportSpecificTopicItemMapper mapper = new TdsImportSpecificTopicItemMapper();
			container = mapper.getBilagdaHandlingarContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	/**
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonTdsImportSpecificTopicItemFormanskoderContainer getTdsFormanskoderContainer (String utfPayload){
		JsonTdsImportSpecificTopicItemFormanskoderContainer container= null;
		try{
			TdsImportSpecificTopicItemMapper mapper = new TdsImportSpecificTopicItemMapper();
			container = mapper.getFormanskoderContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
	/**
	 * 
	 */
	public JsonTdsAutoControlErrorContainer getTdsImportSpecificTopicItemAutoControlErrorContainer(String utfPayload){
		JsonTdsAutoControlErrorContainer container= null;
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
	public JsonTdsImportTulltaxaKundensRegisterVarukodContainer getKundRegisterVarukodContainer(String utfPayload) throws Exception{
		JsonTdsImportTulltaxaKundensRegisterVarukodContainer container= null;
		try{
			TdsImportTulltaxaKundensRegisterVarukodMapper mapper = new TdsImportTulltaxaKundensRegisterVarukodMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
}
