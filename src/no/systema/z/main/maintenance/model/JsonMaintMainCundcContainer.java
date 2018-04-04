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
public class JsonMaintMainCundcContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintMainCundcRecord> list;
	public void setList(Collection<JsonMaintMainCundcRecord> value){ this.list = value; }
	public Collection<JsonMaintMainCundcRecord> getList(){ return list; }
	
}
