/**
 * 
 */
package no.systema.tds.tdsexport.model.jsonjackson.customer;

import java.util.Collection;
/**
 * @author oscardelatorre
 * @date Jan 28, 2013
 */
public class JsonTdsExportCustomerContainer {
	
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
	
	private Collection<JsonTdsExportCustomerRecord> customerlist = null;
	public void setCustomerlist(Collection<JsonTdsExportCustomerRecord> value){ this.customerlist = value;}
	public Collection<JsonTdsExportCustomerRecord> getCustomerlist(){ return this.customerlist; }
	
}
