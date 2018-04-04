/**
 * 
 */
package no.systema.tds.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author oscardelatorre
 * @date Oct 28, 2015
 */
public class JsonTdsAutoControlErrorContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	//Export
	private String svev_syav = null;
	public void setSvev_syav(String value){ this.svev_syav = value;}
	public String getSvev_syav(){ return this.svev_syav; }
	
	private String svev_syop = null;
	public void setSvev_syop(String value){ this.svev_syop = value;}
	public String getSvev_syop(){ return this.svev_syop; }
	
	private String svev_syli = null;
	public void setSvev_syli(String value){ this.svev_syli = value;}
	public String getSvev_syli(){ return this.svev_syli; }
	
	//Import
	private String sviv_syav = null;
	public void setSviv_syav(String value){ this.sviv_syav = value;}
	public String getSviv_syav(){ return this.sviv_syav; }
	
	private String sviv_syop = null;
	public void setSviv_syop(String value){ this.sviv_syop = value;}
	public String getSviv_syop(){ return this.sviv_syop; }
	
	private String sviv_syli = null;
	public void setSviv_syli(String value){ this.sviv_syli = value;}
	public String getSviv_syli(){ return this.sviv_syli; }
	
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}
	
}
