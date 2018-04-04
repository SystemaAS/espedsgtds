/**
 * 
 */
package no.systema.tds.nctsimport.model.jsonjackson.topic.unloading.items;

import java.util.Collection;


/**
 * 
 * @author oscardelatorre
 * @date Dec 20, 2013
 *
 */
public class JsonNctsImportSpecificTopicUnloadingItemContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String tvavd = null;
	public void setTvavd(String value) {  this.tvavd = value; }
	public String getTvavd() { return this.tvavd;}
	
	private String tvtdn = null;
	public void setTvtdn(String value) {  this.tvtdn = value; }
	public String getTvtdn() { return this.tvtdn;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
		
	private Collection<JsonNctsImportSpecificTopicUnloadingItemRecord> orderList;
	public void setOrderList(Collection<JsonNctsImportSpecificTopicUnloadingItemRecord> value){ this.orderList = value; }
	public Collection<JsonNctsImportSpecificTopicUnloadingItemRecord> getOrderList(){ return orderList; }
	
}
