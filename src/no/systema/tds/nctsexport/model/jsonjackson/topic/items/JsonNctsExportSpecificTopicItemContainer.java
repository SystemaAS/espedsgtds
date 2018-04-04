/**
 * 
 */
package no.systema.tds.nctsexport.model.jsonjackson.topic.items;

import java.util.Collection;


/**
 * @author oscardelatorre
 * @date June 7, 2013
 *
 */
public class JsonNctsExportSpecificTopicItemContainer {
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
		
	private Collection<JsonNctsExportSpecificTopicItemRecord> orderList;
	public void setOrderList(Collection<JsonNctsExportSpecificTopicItemRecord> value){ this.orderList = value; }
	public Collection<JsonNctsExportSpecificTopicItemRecord> getOrderList(){ return orderList; }
	
}
