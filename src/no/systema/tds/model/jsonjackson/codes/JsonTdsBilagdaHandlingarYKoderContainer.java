/**
 * 
 */
package no.systema.tds.model.jsonjackson.codes;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Oct 19, 2015
 * 
 */
public class JsonTdsBilagdaHandlingarYKoderContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}

	private String ie = null;
	public void setIe(String value) {  this.ie = value; }
	public String getIe() { return this.ie;}

	private String vata = null;
	public void setVata(String value) {  this.vata = value; }
	public String getVata() { return this.vata;}

	private String lk = null;
	public void setLk(String value) {  this.lk = value; }
	public String getLk() { return this.lk;}

	private String fokd = null;
	public void setFokd(String value) {  this.fokd = value; }
	public String getFokd() { return this.fokd;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonTdsBilagdaHandlingarYKoderRecord> bilhandykoder;
	public void setBilhandykoder(Collection<JsonTdsBilagdaHandlingarYKoderRecord> value){ this.bilhandykoder = value; }
	public Collection<JsonTdsBilagdaHandlingarYKoderRecord> getBilhandykoder(){ return bilhandykoder; }
	
	
	
}
