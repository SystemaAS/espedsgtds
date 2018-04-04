/**
 * 
 */
package no.systema.tds.nctsexport.model.jsonjackson.topic.logging;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Aug 7, 2013
 *
 */
public class JsonNctsExportSpecificTopicLoggingLargeTextContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String fmn = null;
	public void setFmn(String value) {  this.fmn = value; }
	public String getFmn() { return this.fmn;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonNctsExportSpecificTopicLoggingLargeTextRecord> loggtext;
	public void setLoggtext(Collection<JsonNctsExportSpecificTopicLoggingLargeTextRecord> value){ this.loggtext = value; }
	public Collection<JsonNctsExportSpecificTopicLoggingLargeTextRecord> getLoggtext(){ return loggtext; }
	
	
}
