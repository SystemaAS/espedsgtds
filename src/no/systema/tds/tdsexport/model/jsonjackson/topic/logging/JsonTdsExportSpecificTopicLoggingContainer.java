/**
 * 
 */
package no.systema.tds.tdsexport.model.jsonjackson.topic.logging;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date June 28, 2013
 *
 */
public class JsonTdsExportSpecificTopicLoggingContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String typ = null;
	public void setTyp(String value) {  this.typ = value; }
	public String getTyp() { return this.typ;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTdsExportSpecificTopicLoggingRecord> logg;
	public void setLogg(Collection<JsonTdsExportSpecificTopicLoggingRecord> value){ this.logg = value; }
	public Collection<JsonTdsExportSpecificTopicLoggingRecord> getLogg(){ return logg; }
	
	
}
