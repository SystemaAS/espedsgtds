/**
 * 
 */
package no.systema.tds.tdsimport.model.jsonjackson.topic;
import java.util.Collection;
/**
 * 
 * @author oscardelatorre
 * @date Nov 5, 2013
 * 
 */
public class JsonTdsImportSpecificTopicOmbudContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() {return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() {return this.avd;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() {return this.errMsg;}
	
	private Collection<JsonTdsImportSpecificTopicOmbudRecord> getGetdepinf = null;
	public void setGetdepinf(Collection<JsonTdsImportSpecificTopicOmbudRecord> value) {  this.getGetdepinf = value; }
	public Collection<JsonTdsImportSpecificTopicOmbudRecord> getGetdepinf() {return this.getGetdepinf;}
	
	
}
