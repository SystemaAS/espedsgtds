/**
 * 
 */
package no.systema.z.main.maintenance.model;

import java.util.Collection;

/**
 * @author Fredrik Möller
 * @date Apr 3, 2018
 *
 */
public class JsonMaintMainSyparfContainer implements IJsonMaintMainContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintMainSyparfRecord> dtoList;
	public void setList(Collection<JsonMaintMainSyparfRecord> value){ this.dtoList = value; }
	public Collection<JsonMaintMainSyparfRecord> getDtoList(){ return dtoList; }
	
}
