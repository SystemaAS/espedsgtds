/**
 * 
 */
package no.systema.tds.tdsexport.filter;

import java.lang.reflect.Field;
import java.util.*;

import org.apache.log4j.Logger;

/**
 * This search class is used at the GUI search behaviour
 * It is MANDATORY to have the same attribute name convention as the JSON-object fetched from the JSON-payload at the back-end.
 * The reason for this is the java-reflection mechanism used when searching (since no SQL or other mechanism is used)
 * By using java reflection to match the object fields, these 2 (the JSON object and its SearchFilter object) must have the same attribute name 
 * 
 * @author oscardelatorre
 * @date 	Nov 25, 2012
 */
public class SearchFilterTdsExportTopicList {
	private static final Logger logger = Logger.getLogger(SearchFilterTdsExportTopicList.class.getName());
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String xref = null;
	public void setXref(String value) {  this.xref = value; }
	public String getXref() { return this.xref;}
	
	private String tullId = null;
	public void setTullId(String value) {  this.tullId = value; }
	public String getTullId() { return this.tullId;}
	
	private String sign = null;
	public void setSign(String value) { this.sign = value;}
	public String getSign() { return this.sign;}
	
	//från och med
	private String datum = null;
	public void setDatum(String value) {  this.datum = value; }
	public String getDatum() { return this.datum;}
	
	private String status = null;
	public void setStatus(String value) {  this.status = value; }
	public String getStatus() { return this.status;}
	
	private String avsNavn = null;
	public void setAvsNavn(String value) {  this.avsNavn = value; }
	public String getAvsNavn() { return this.avsNavn;}
	
	private String motNavn = null;
	public void setMotNavn(String value) {  this.motNavn = value; }
	public String getMotNavn() { return this.motNavn;}
	
	private String mtyp = null;
	public void setMtyp(String value) {  this.mtyp = value; }
	public String getMtyp() { return this.mtyp;}
	
	
	/**
	 * Gets the populated values by reflection
	 * @param searchFilter
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getPopulatedFields() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		for(Field field : list){
			field.setAccessible(true);
			logger.info("FIELD NAME: " + field.getName() + "VALUE:" + (String)field.get(this));
			String value = (String)field.get(this);
			if(value!=null && !"".equals(value)){
				logger.info(field.getName() + " Value:" + value);
				map.put(field.getName(), value);
			}
		}
		
		return map;
	}
}
