/**
 * 
 */
package no.systema.tds.model.jsonjackson.codes;

import java.util.Collection;

/**
 * 
 * @author oscardelatorre
 * @date Oct 19, 2015
 * 
 */
public class JsonTdsTillaggskodContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String ie = null;
	public void setIe(String value){ this.ie = value;}
	public String getIe(){ return this.ie; }
	
	private String vata 	 = null;
	public void setVata(String value){ this.vata = value;}
	public String getVata(){ return this.vata; }
	
	private String lk 	 = null;
	public void setLk(String value){ this.lk = value;}
	public String getLk(){ return this.lk; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTdsTillaggskodRecord> r33tillkoder = null;
	public void setR33tillkoder(Collection<JsonTdsTillaggskodRecord> value){ this.r33tillkoder = value;}
	public Collection<JsonTdsTillaggskodRecord> getR33tillkoder(){ return this.r33tillkoder; }
}
