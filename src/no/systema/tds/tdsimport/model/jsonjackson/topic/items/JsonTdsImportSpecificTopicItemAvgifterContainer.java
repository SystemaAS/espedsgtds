/**
 * 
 */
package no.systema.tds.tdsimport.model.jsonjackson.topic.items;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Sep 10, 2103
 * 
 */
public class JsonTdsImportSpecificTopicItemAvgifterContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonTdsImportSpecificTopicItemAvgifterRecord> taxcalc;
	public void setTaxcalc(Collection<JsonTdsImportSpecificTopicItemAvgifterRecord> value){ this.taxcalc = value; }
	public Collection<JsonTdsImportSpecificTopicItemAvgifterRecord> getTaxcalc(){ return taxcalc; }
	
	
	
}
