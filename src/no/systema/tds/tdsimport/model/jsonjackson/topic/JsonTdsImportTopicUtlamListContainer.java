/**
 * 
 */
package no.systema.tds.tdsimport.model.jsonjackson.topic;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Jul 06, 2017
 *
 */
public class JsonTdsImportTopicUtlamListContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String tullid = null;
	public void setTullid(String value) {  this.tullid = value; }
	public String getTullid() { return this.tullid;}
	
	private String datum = null;
	public void setDatum(String value) {  this.datum = value; }
	public String getDatum() { return this.datum;}
	
	private String datumt = null;
	public void setDatumt(String value) {  this.datumt = value; }
	public String getDatumt() { return this.datumt;}
	
	private String errmsg = null;
	public void setErrmsg(String value) {  this.errmsg = value; }
	public String getErrmsg() { return this.errmsg;}
	
	
	private Collection<JsonTdsImportTopicUtlamListRecord> utlList;
	public void setUtlList(Collection<JsonTdsImportTopicUtlamListRecord> value){ this.utlList = value; }
	public Collection<JsonTdsImportTopicUtlamListRecord> getUtlList(){ return utlList; }
	
}
