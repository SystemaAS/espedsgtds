/**
 * 
 */
package no.systema.tds.nctsimport.model.jsonjackson.topic;

import java.util.Collection;


/**
 * @author oscardelatorre
 * @date Dec 3, 2013
 *
 */
public class JsonNctsImportTopicListContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String sign = null;
	public void setSign(String value) {  this.sign = value; }
	public String getSign() { return this.sign;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonNctsImportTopicListRecord> orderList;
	public void setOrderList(Collection<JsonNctsImportTopicListRecord> value){ this.orderList = value; }
	public Collection<JsonNctsImportTopicListRecord> getOrderList(){ return orderList; }
	
}
