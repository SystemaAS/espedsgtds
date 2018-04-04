/**
 * 
 */
package no.systema.tds.model.jsonjackson.authorization.topic;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date June 19, 2013
 */
public class JsonTdsAuthorizationTopicListContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String sign = null;
	public void setSign(String value){ this.sign = value;}
	public String getSign(){ return this.sign; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTdsAuthorizationTopicListRecord> signeringslista = null;
	public void setSigneringslista(Collection<JsonTdsAuthorizationTopicListRecord> value){ this.signeringslista = value;}
	public Collection<JsonTdsAuthorizationTopicListRecord> getSigneringslista(){ return this.signeringslista; }
}
