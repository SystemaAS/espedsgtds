/**
 * 
 */
package no.systema.tds.tdsimport.model.jsonjackson.topic.items;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Oct 19, 2015
 * 
 */
public class JsonTdsImportSpecificTopicItemFormanskoderContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String vata = null;
	public void setVata(String value) {  this.vata = value; }
	public String getVata() { return this.vata;}
	
	private String lk = null;
	public void setLk(String value) {  this.lk = value; }
	public String getLk() { return this.lk;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonTdsImportSpecificTopicItemFormanskoderRecord> foreslagenformanskod;
	public void setForeslagenformanskod(Collection<JsonTdsImportSpecificTopicItemFormanskoderRecord> value){ this.foreslagenformanskod = value; }
	public Collection<JsonTdsImportSpecificTopicItemFormanskoderRecord> getForeslagenformanskod(){ return foreslagenformanskod; }
	
	
	
}
