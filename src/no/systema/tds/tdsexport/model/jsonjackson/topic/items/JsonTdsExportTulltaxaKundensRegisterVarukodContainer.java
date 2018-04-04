/**
 * 
 */
package no.systema.tds.tdsexport.model.jsonjackson.topic.items;

import java.util.Collection;

/**
 * Kundens varuregister (to allow for the search of tulltaxa varukod via the customer's item data)
 * 
 * @author oscardelatorre
 * @date Jan 27, 2016
 */
public class JsonTdsExportTulltaxaKundensRegisterVarukodContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String levenr = null;
	public void setLevenr(String value){ this.levenr = value;}
	public String getLevenr(){ return this.levenr; }
	
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTdsExportTulltaxaKundensRegisterVarukodRecord> kundvarlist = null;
	public void setKundvarlist(Collection<JsonTdsExportTulltaxaKundensRegisterVarukodRecord> value){ this.kundvarlist = value;}
	public Collection<JsonTdsExportTulltaxaKundensRegisterVarukodRecord> getKundvarlist(){ return this.kundvarlist; }
}
