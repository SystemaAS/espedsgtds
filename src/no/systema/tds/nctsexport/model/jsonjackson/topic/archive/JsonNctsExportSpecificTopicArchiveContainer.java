/**
 * 
 */
package no.systema.tds.nctsexport.model.jsonjackson.topic.archive;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Aug 7, 2013
 *
 */
public class JsonNctsExportSpecificTopicArchiveContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonNctsExportSpecificTopicArchiveRecord> archiveElements;
	public void setArchiveElements(Collection<JsonNctsExportSpecificTopicArchiveRecord> value){ this.archiveElements = value; }
	public Collection<JsonNctsExportSpecificTopicArchiveRecord> getArchiveElements(){ return archiveElements; }
	
	
}
