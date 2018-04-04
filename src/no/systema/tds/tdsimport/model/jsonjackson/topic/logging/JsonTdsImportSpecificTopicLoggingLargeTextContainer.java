/**
 * 
 */
package no.systema.tds.tdsimport.model.jsonjackson.topic.logging;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Sep 2, 2013
 *
 */
public class JsonTdsImportSpecificTopicLoggingLargeTextContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String fmn = null;
	public void setFmn(String value) {  this.fmn = value; }
	public String getFmn() { return this.fmn;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTdsImportSpecificTopicLoggingLargeTextRecord> loggtext;
	public void setLoggtext(Collection<JsonTdsImportSpecificTopicLoggingLargeTextRecord> value){ this.loggtext = value; }
	public Collection<JsonTdsImportSpecificTopicLoggingLargeTextRecord> getLoggtext(){ return loggtext; }
	
	
}
