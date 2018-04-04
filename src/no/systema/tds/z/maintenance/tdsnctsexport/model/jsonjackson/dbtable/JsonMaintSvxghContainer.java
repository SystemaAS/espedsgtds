/**
 * 
 */
package no.systema.tds.z.maintenance.tdsnctsexport.model.jsonjackson.dbtable;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Jun 23, 2017
 *
 */
public class JsonMaintSvxghContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintSvxghRecord> list;
	public void setList(Collection<JsonMaintSvxghRecord> value){ this.list = value; }
	public Collection<JsonMaintSvxghRecord> getList(){ return list; }
	
}
