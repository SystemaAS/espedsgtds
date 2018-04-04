/**
 * 
 */
package no.systema.tds.model.jsonjackson.authorization.topic;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * This class is used when the specific topic will be signed (send to the PKI-infrastructure)
 * @author oscardelatorre
 * @date June 21, 2013
 */
public class JsonTdsAuthorizationSignSpecificTopicContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String ursp = null;
	public void setUrsp(String value){ this.ursp = value;}
	public String getUrsp(){ return this.ursp; }
	
	private String avd = null;
	public void setAvd(String value){ this.avd = value;}
	public String getAvd(){ return this.avd; }
	
	private String opd = null;
	public void setOpd(String value){ this.opd = value;}
	public String getOpd(){ return this.opd; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	/**
	 * Used for java reflection in other classes
	 * @return
	 * @throws Exception
	 */
	
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}
	
	/*
	private Collection<Todo> signeringslista = null;
	public void setSigneringslista(Collection<Todo> value){ this.signeringslista = value;}
	public Collection<Todo> getSigneringslista(){ return this.signeringslista; }
	*/
	/*
	private Collection<Todo> aterkall = null;
	public void setAterkall(Collection<Todo> value){ this.aterkall = value;}
	public Collection<Todo> getAterkall(){ return this.aterkall; }
	*/
}
