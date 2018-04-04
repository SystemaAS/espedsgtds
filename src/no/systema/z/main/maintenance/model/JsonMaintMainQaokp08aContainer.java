/**
 * 
 */
package no.systema.z.main.maintenance.model;

import java.util.Collection;

/**
 * Copy of JsonMaintSadImportSadvareContainer
 * 
 * @author oscardelatorre
 * @date Apr 3, 2018
 *
 */
public class JsonMaintMainQaokp08aContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintMainQaokp08aRecord> list;
	public void setList(Collection<JsonMaintMainQaokp08aRecord> value){ this.list = value; }
	public Collection<JsonMaintMainQaokp08aRecord> getList(){ return list; }
	
}
