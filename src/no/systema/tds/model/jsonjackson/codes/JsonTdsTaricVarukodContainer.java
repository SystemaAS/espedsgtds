/**
 * 
 */
package no.systema.tds.model.jsonjackson.codes;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @Mar 9, 2013
 */
public class JsonTdsTaricVarukodContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String ie = null;
	public void setIe(String value){ this.ie = value;}
	public String getIe(){ return this.ie; }
	
	private String kod 	 = null;
	public void setKod(String value){ this.kod = value;}
	public String getKod(){ return this.kod; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTdsTaricVarukodRecord> tullTaxalist = null;
	public void setTullTaxalist(Collection<JsonTdsTaricVarukodRecord> value){ this.tullTaxalist = value;}
	public Collection<JsonTdsTaricVarukodRecord> getTullTaxalist(){ return this.tullTaxalist; }
	
	private Collection<JsonTdsTaricVarukodRecord> ttaxalfasok = null;
	public void setTtaxalfasok(Collection<JsonTdsTaricVarukodRecord> value){ this.ttaxalfasok = value;}
	public Collection<JsonTdsTaricVarukodRecord> getTtaxalfasok(){ return this.ttaxalfasok; }
	
}
