/**
 * 
 */
package no.systema.tds.tdsimport.model.jsonjackson.topic;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Sep 2, 2013
 *
 */
public class JsonTdsImportTopicListContainer {
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
	
	
	private Collection<JsonTdsImportTopicListRecord> orderList;
	public void setOrderList(Collection<JsonTdsImportTopicListRecord> value){ this.orderList = value; }
	public Collection<JsonTdsImportTopicListRecord> getOrderList(){ return orderList; }
	
}
