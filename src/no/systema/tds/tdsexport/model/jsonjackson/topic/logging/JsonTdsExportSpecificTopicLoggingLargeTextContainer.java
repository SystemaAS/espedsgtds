/**
 * 
 */
package no.systema.tds.tdsexport.model.jsonjackson.topic.logging;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Aug 6, 2013
 *
 */
public class JsonTdsExportSpecificTopicLoggingLargeTextContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String fmn = null;
	public void setFmn(String value) {  this.fmn = value; }
	public String getFmn() { return this.fmn;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTdsExportSpecificTopicLoggingLargeTextRecord> loggtext;
	public void setLoggtext(Collection<JsonTdsExportSpecificTopicLoggingLargeTextRecord> value){ this.loggtext = value; }
	public Collection<JsonTdsExportSpecificTopicLoggingLargeTextRecord> getLoggtext(){ return loggtext; }
	
	
}
