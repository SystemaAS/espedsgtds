/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.model;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Apr 4, 2018
 *
 */
public class JsonMaintSadImportKodtlikContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintSadImportKodtlikRecord> list;
	public void setList(Collection<JsonMaintSadImportKodtlikRecord> value){ this.list = value; }
	public Collection<JsonMaintSadImportKodtlikRecord> getList(){ return list; }
	
}
