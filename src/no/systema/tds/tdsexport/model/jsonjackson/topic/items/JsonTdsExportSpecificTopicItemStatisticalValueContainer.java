/**
 * 
 */
package no.systema.tds.tdsexport.model.jsonjackson.topic.items;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Oct 28, 2015
 * 
 */
public class JsonTdsExportSpecificTopicItemStatisticalValueContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonTdsExportSpecificTopicItemStatisticalValueRecord> taxcalc;
	public void setTaxcalc(Collection<JsonTdsExportSpecificTopicItemStatisticalValueRecord> value){ this.taxcalc = value; }
	public Collection<JsonTdsExportSpecificTopicItemStatisticalValueRecord> getTaxcalc(){ return taxcalc; }
	
	
	
}
