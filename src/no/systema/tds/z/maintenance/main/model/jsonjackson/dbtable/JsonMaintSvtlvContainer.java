/**
 * 
 */
package no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Jun 03, 2017
 *
 */
public class JsonMaintSvtlvContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintSvtlvRecord> dtoList;
	public void setDtoList(Collection<JsonMaintSvtlvRecord> value){ this.dtoList = value; }
	public Collection<JsonMaintSvtlvRecord> getDtoList(){ return dtoList; }
	
}
