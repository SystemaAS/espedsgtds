/**
 * 
 */
package no.systema.tds.tdsexport.model.jsonjackson.topic;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Oct 2018
 *
 */
public class JsonTdsExportTopicListExternalRefContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonTdsExportTopicListExternalRefRecord> extList;
	public void setExtList(Collection<JsonTdsExportTopicListExternalRefRecord> value){ this.extList = value; }
	public Collection<JsonTdsExportTopicListExternalRefRecord> getExtList(){ return extList; }
	
}
