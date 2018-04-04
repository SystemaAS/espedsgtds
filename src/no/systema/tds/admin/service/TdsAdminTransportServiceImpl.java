/**
 * 
 */
package no.systema.tds.admin.service;

import no.systema.tds.admin.model.jsonjackson.topic.JsonTdsAdminNorskExportListContainer;
import no.systema.tds.admin.model.jsonjackson.topic.JsonTdsAdminNorskImportListContainer;
import no.systema.tds.admin.model.jsonjackson.topic.JsonTdsAdminTransportListContainer;

import no.systema.tds.admin.mapper.jsonjackson.TdsAdminTransportListMapper;
import no.systema.tds.admin.mapper.jsonjackson.TdsAdminNorskImportListMapper;
import no.systema.tds.admin.mapper.jsonjackson.TdsAdminNorskExportListMapper;

/**
 * @author oscardelatorre
 * @date Jan 14, 2014
 * 
 */
public class TdsAdminTransportServiceImpl implements TdsAdminTransportService {
	
	/**
	 * Transportuppdrag
	 * 
	 * @param utfPayload
	 * @return
	 */
	public JsonTdsAdminTransportListContainer getTdsAdminTransportListContainer(String utfPayload){
		JsonTdsAdminTransportListContainer container = null;
		try{
			TdsAdminTransportListMapper mapper = new TdsAdminTransportListMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
	/**
	 * Norsk Import
	 * 
	 * @param utfPayload
	 * @return
	 */
	public JsonTdsAdminNorskImportListContainer getTdsAdminNorskImportListContainer(String utfPayload){
		JsonTdsAdminNorskImportListContainer container = null;
		try{
			TdsAdminNorskImportListMapper mapper = new TdsAdminNorskImportListMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
	/**
	 * Norsk Export
	 * 
	 * @param utfPayload
	 * @return
	 */
	public JsonTdsAdminNorskExportListContainer getTdsAdminNorskExportListContainer(String utfPayload){
		JsonTdsAdminNorskExportListContainer container = null;
		try{
			TdsAdminNorskExportListMapper mapper = new TdsAdminNorskExportListMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
}
