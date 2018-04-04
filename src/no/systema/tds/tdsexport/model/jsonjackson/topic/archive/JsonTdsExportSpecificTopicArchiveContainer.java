/**
 * 
 */
package no.systema.tds.tdsexport.model.jsonjackson.topic.archive;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Aug 6, 2013
 *
 */
public class JsonTdsExportSpecificTopicArchiveContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTdsExportSpecificTopicArchiveRecord> archiveElements;
	public void setArchiveElements(Collection<JsonTdsExportSpecificTopicArchiveRecord> value){ this.archiveElements = value; }
	public Collection<JsonTdsExportSpecificTopicArchiveRecord> getArchiveElements(){ return archiveElements; }
	
	
}
