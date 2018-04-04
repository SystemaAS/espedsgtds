/**
 * 
 */
package no.systema.tds.tdsimport.model.jsonjackson.customer;

import java.util.Collection;
/**
 * @author oscardelatorre
 * @date Sep 2, 2013
 */
public class JsonTdsImportCustomerContainer {
	
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String sonavn = null;
	public void setSonavn(String value){ this.sonavn = value;}
	public String getSonavn(){ return this.sonavn; }
	
	private String knr = null;
	public void setKnr(String value){ this.knr = value;}
	public String getKnr(){ return this.knr; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTdsImportCustomerRecord> customerlist = null;
	public void setCustomerlist(Collection<JsonTdsImportCustomerRecord> value){ this.customerlist = value;}
	public Collection<JsonTdsImportCustomerRecord> getCustomerlist(){ return this.customerlist; }
	
}
