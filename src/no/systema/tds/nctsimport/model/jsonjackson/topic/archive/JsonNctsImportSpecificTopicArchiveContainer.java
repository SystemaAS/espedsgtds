/**
 * 
 */
package no.systema.tds.nctsimport.model.jsonjackson.topic.archive;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Apr 4, 2014
 *
 */
public class JsonNctsImportSpecificTopicArchiveContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonNctsImportSpecificTopicArchiveRecord> archiveElements;
	public void setArchiveElements(Collection<JsonNctsImportSpecificTopicArchiveRecord> value){ this.archiveElements = value; }
	public Collection<JsonNctsImportSpecificTopicArchiveRecord> getArchiveElements(){ return archiveElements; }
	
	
}
