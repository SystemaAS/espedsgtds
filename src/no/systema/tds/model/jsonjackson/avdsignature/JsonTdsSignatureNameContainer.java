/**
 * 
 */
package no.systema.tds.model.jsonjackson.avdsignature;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Nov 6, 2013
 */
public class JsonTdsSignatureNameContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String sign = null;
	public void setSign(String value){ this.sign = value;}
	public String getSign(){ return this.sign; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTdsSignatureNameRecord> getsignname = null;
	public void setGetsignname(Collection<JsonTdsSignatureNameRecord> value){ this.getsignname = value;}
	public Collection<JsonTdsSignatureNameRecord> getGetsignname(){ return this.getsignname; }
}
