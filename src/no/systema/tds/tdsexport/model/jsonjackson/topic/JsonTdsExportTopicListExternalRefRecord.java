/**
 * 
 */
package no.systema.tds.tdsexport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author oscardelatorre
 * @date Oct, 2016
 *
 */
public class JsonTdsExportTopicListExternalRefRecord extends JsonAbstractGrandFatherRecord {
	private String fssok = null;
	public void setFssok(String value) {  this.fssok = value; }
	public String getFssok() { return this.fssok;}
	
	private String fsavd = null;
	public void setFsavd(String value) {  this.fsavd = value; }
	public String getFsavd() { return this.fsavd;}
	
	private String fsopd = null;
	public void setFsopd(String value) {  this.fsopd = value; }
	public String getFsopd() { return this.fsopd;}
	
	private String fsdtop = null;
	public void setFsdtop(String value) {  this.fsdtop = value; }
	public String getFsdtop() { return this.fsdtop;}
	
	private String henas = null;
	public void setHenas(String value) {  this.henas = value; }
	public String getHenas() {return this.henas;}
	
	private String henak = null;
	public void setHenak(String value) {  this.henak = value; }
	public String getHenak() { return this.henak;}

	
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
