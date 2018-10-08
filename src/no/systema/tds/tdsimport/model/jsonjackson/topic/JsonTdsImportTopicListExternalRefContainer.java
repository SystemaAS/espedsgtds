/**
 * 
 */
package no.systema.tds.tdsimport.model.jsonjackson.topic;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Oct 2018
 *
 */
public class JsonTdsImportTopicListExternalRefContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonTdsImportTopicListExternalRefRecord> extList;
	public void setExtList(Collection<JsonTdsImportTopicListExternalRefRecord> value){ this.extList = value; }
	public Collection<JsonTdsImportTopicListExternalRefRecord> getExtList(){ return extList; }
	
}
