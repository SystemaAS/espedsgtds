/**
 * 
 */
package no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date May 19, 2017
 *
 */
public class JsonMaintSvtvkContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintSvtvkRecord> dtoList;
	public void setDtoList(Collection<JsonMaintSvtvkRecord> value){ this.dtoList = value; }
	public Collection<JsonMaintSvtvkRecord> getDtoList(){ return dtoList; }
	
}
