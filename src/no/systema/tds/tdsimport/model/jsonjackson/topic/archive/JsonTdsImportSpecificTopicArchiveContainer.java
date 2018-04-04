/**
 * 
 */
package no.systema.tds.tdsimport.model.jsonjackson.topic.archive;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Sep 2, 2013
 *
 */
public class JsonTdsImportSpecificTopicArchiveContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTdsImportSpecificTopicArchiveRecord> archiveElements;
	public void setArchiveElements(Collection<JsonTdsImportSpecificTopicArchiveRecord> value){ this.archiveElements = value; }
	public Collection<JsonTdsImportSpecificTopicArchiveRecord> getArchiveElements(){ return archiveElements; }
	
	
}
