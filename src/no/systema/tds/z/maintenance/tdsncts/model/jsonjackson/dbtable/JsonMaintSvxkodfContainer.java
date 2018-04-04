/**
 * 
 */
package no.systema.tds.z.maintenance.tdsncts.model.jsonjackson.dbtable;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Jun 22, 2017
 *
 */
public class JsonMaintSvxkodfContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintSvxkodfRecord> list;
	public void setList(Collection<JsonMaintSvxkodfRecord> value){ this.list = value; }
	public Collection<JsonMaintSvxkodfRecord> getList(){ return list; }
	
}
