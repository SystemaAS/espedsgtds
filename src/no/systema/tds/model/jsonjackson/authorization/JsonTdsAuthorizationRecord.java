/**
 * 
 */
package no.systema.tds.model.jsonjackson.authorization;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * @author oscardelatorre
 * @date June 17, 2013
 * 
 */
public class JsonTdsAuthorizationRecord extends JsonAbstractGrandFatherRecord  {
	private String btds = null;
	public void setBtds(String value){ this.btds = value;}
	public String getBtds(){ return this.btds; }
	
	private String bpki = null;
	public void setBpki(String value){ this.bpki = value;}
	public String getBpki(){ return this.bpki; }
	
	private String sign = null;
	public void setSign(String value){ this.sign = value;}
	public String getSign(){ return this.sign; }
	
	private String ttax = null;
	public void setTtax(String value){ this.ttax = value;}
	public String getTtax(){ return this.ttax; }
	
	
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
