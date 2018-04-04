/**
 * 
 */
package no.systema.tds.tdsimport.model.jsonjackson.topic.items;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * 
 * @author oscardelatorre
 * @date Oct 19, 2015
 * 
 */
public class JsonTdsImportSpecificTopicItemFormanskoderRecord extends JsonAbstractGrandFatherRecord {

	private String fokd = null;
	public void setFokd(String value) {  this.fokd = value; }
	public String getFokd() {return this.fokd;}
	
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}
	
	
}
