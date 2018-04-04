/**
 * 
 */
package no.systema.tds.tdsimport.model.jsonjackson.topic;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Nov 1, 2017
 *
 */
public class JsonTdsImportSpecificTopicCheckItemErrorContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String ok = null;
	public void setOk(String value) {  this.ok = value; }
	public String getOk() { return this.ok;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonTdsImportSpecificTopicCheckItemErrorRecord> checkitemerror;
	public void setCheckitemerror(Collection<JsonTdsImportSpecificTopicCheckItemErrorRecord> value){ this.checkitemerror = value; }
	public Collection<JsonTdsImportSpecificTopicCheckItemErrorRecord> getCheckitemerror(){ return checkitemerror; }
	
	
}
