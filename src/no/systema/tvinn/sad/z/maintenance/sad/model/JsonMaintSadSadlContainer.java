/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sad.model;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Apr 3, 2018
 *
 */
public class JsonMaintSadSadlContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintSadSadlRecord> list;
	public void setList(Collection<JsonMaintSadSadlRecord> value){ this.list = value; }
	public Collection<JsonMaintSadSadlRecord> getList(){ return list; }
	
}
