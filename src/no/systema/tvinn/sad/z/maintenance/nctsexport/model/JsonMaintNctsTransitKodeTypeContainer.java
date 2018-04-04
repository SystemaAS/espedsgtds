/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.nctsexport.model;

import java.util.Collection;

/**
 * @author Fredrik Möller
 * @date Apr 4, 2018
 *
 */
public class JsonMaintNctsTransitKodeTypeContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintNctsTransitKodeTypeRecord> list;
	public void setList(Collection<JsonMaintNctsTransitKodeTypeRecord> value){ this.list = value; }
	public Collection<JsonMaintNctsTransitKodeTypeRecord> getList(){ return list; }
	
}
