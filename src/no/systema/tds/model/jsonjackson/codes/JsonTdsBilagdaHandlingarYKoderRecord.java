/**
 * 
 */
package no.systema.tds.model.jsonjackson.codes;

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
public class JsonTdsBilagdaHandlingarYKoderRecord extends JsonAbstractGrandFatherRecord {
	
	private String kod = null;
	public void setKod(String value) {  this.kod = value; }
	public String getKod() {return this.kod;}
	
	private String txt = null;
	public void setTxt(String value) {  this.txt = value; }
	public String getTxt() {return this.txt;}
	
	
	
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
