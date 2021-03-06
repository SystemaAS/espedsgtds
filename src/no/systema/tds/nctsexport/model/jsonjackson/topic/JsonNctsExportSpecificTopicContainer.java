/**
 * 
 */
package no.systema.tds.nctsexport.model.jsonjackson.topic;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Nov 25, 2012
 *
 */
public class JsonNctsExportSpecificTopicContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String amount = null;
	public void setAmount(String value) {  this.amount = value; }
	public String getAmount() { return this.amount;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonNctsExportSpecificTopicRecord> oneorder;
	public void setOneorder(Collection<JsonNctsExportSpecificTopicRecord> value){ this.oneorder = value; }
	public Collection<JsonNctsExportSpecificTopicRecord> getOneorder(){ return oneorder; }
	
	
}
