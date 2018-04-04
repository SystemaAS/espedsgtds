/**
 * 
 */
package no.systema.tds.model.jsonjackson.validation;

import java.util.Collection;


/**
 * 
 * @author oscardelatorre
 * @date Sep 26, 2013
 *
 */
public class JsonTdsMangdEnhetContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String ie = null;
	public void setIe(String value){ this.ie = value;}
	public String getIe(){ return this.ie; }
	
	private String kod = null;
	public void setKod(String value){ this.kod = value;}
	public String getKod(){ return this.kod; }
	
	private String lk = null;
	public void setLk(String value){ this.lk = value;}
	public String getLk(){ return this.lk; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTdsMangdEnhetRecord> xtramangdenhet = null;
	public void setXtramangdenhet(Collection<JsonTdsMangdEnhetRecord> value){ this.xtramangdenhet = value;}
	public Collection<JsonTdsMangdEnhetRecord> getXtramangdenhet(){ return this.xtramangdenhet; }
	
}
