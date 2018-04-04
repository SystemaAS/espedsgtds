/**
 * 
 */
package no.systema.tds.tdsexport.model.jsonjackson.topic.logging;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author oscardelatorre
 * @date Aug 6, 2013
 *
 */
public class JsonTdsExportSpecificTopicLoggingLargeTextRecord {
	
	
	private String f0078a = null;
	public void setF0078a(String value) {  this.f0078a = value; }
	public String getF0078a() {return this.f0078a;}
	
	private String f0078b = null;
	public void setF0078b(String value) {  this.f0078b = value; }
	public String getF0078b() {return this.f0078b;}
	
	
	/**
	 * Used for java reflection in other classes
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
