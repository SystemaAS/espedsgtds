/**
 * 
 */
package no.systema.tds.model.jsonjackson.authorization;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date June 17, 2013
 */
public class JsonTdsPkiSmsCodeContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String usrAS400 = null;
	public void setUsrAS400(String value){ this.usrAS400 = value;}
	public String getUsrAS400(){ return this.usrAS400; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTdsPkiSmsCodeRecord> tdsSkapaengangskod = null;
	public void setTdsSkapaengangskod(Collection<JsonTdsPkiSmsCodeRecord> value){ this.tdsSkapaengangskod = value;}
	public Collection<JsonTdsPkiSmsCodeRecord> getTdsSkapaengangskod(){ return this.tdsSkapaengangskod; }
}
