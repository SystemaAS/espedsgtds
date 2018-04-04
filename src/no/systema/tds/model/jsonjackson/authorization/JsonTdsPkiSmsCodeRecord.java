/**
 * 
 */
package no.systema.tds.model.jsonjackson.authorization;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author oscardelatorre
 * @date June 19, 2013
 * 
 */
public class JsonTdsPkiSmsCodeRecord {
	private String smsKod = null;
	public void setSmsKod(String value){ this.smsKod = value;}
	public String getSmsKod(){ return this.smsKod; }
		
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
