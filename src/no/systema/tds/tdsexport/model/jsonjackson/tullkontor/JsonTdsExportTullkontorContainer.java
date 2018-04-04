/**
 * 
 */
package no.systema.tds.tdsexport.model.jsonjackson.tullkontor;

import java.util.Collection;
/**
 * @author oscardelatorre
 * @date Jan 28, 2013
 */
public class JsonTdsExportTullkontorContainer {
	
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String sonavn = null;
	public void setSonavn(String value){ this.sonavn = value;}
	public String getSonavn(){ return this.sonavn; }
	
	private String kod = null;
	public void setKod(String value){ this.kod = value;}
	public String getKod(){ return this.kod; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTdsExportTullkontorRecord> customslist = null;
	public void setCustomslist(Collection<JsonTdsExportTullkontorRecord> value){ this.customslist = value;}
	public Collection<JsonTdsExportTullkontorRecord> getCustomslist(){ return this.customslist; }
	
}
