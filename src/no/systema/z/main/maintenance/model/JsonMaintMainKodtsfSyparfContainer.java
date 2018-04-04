/**
 * 
 */
package no.systema.z.main.maintenance.model;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Apr 03, 2018
 *
 */
public class JsonMaintMainKodtsfSyparfContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonMaintMainKodtsfSyparfRecord> list;
	public void setList(Collection<JsonMaintMainKodtsfSyparfRecord> value){ this.list = value; }
	public Collection<JsonMaintMainKodtsfSyparfRecord> getList(){ return list; }
	
}
