/**
 * 
 */
package no.systema.tds.tdsimport.model.jsonjackson.topic.items;

import java.util.Collection;

/**
 * Kundens varuregister (to allow for the search of tulltaxa nr via the customer's item data)
 * 
 * @author oscardelatorre
 * @date Jan 27, 2016
 */
public class JsonTdsImportTulltaxaKundensRegisterVarukodContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String levenr = null;
	public void setLevenr(String value){ this.levenr = value;}
	public String getLevenr(){ return this.levenr; }
	
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTdsImportTulltaxaKundensRegisterVarukodRecord> kundvarlist = null;
	public void setKundvarlist(Collection<JsonTdsImportTulltaxaKundensRegisterVarukodRecord> value){ this.kundvarlist = value;}
	public Collection<JsonTdsImportTulltaxaKundensRegisterVarukodRecord> getKundvarlist(){ return this.kundvarlist; }
}
