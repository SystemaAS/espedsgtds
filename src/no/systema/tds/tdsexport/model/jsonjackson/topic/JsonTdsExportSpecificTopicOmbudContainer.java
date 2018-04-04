/**
 * 
 */
package no.systema.tds.tdsexport.model.jsonjackson.topic;
import java.util.Collection;


/**
 * 
 * @author oscardelatorre
 * @date Nov 5, 2013
 * 
 */
public class JsonTdsExportSpecificTopicOmbudContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() {return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() {return this.avd;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() {return this.errMsg;}
	
	private Collection<JsonTdsExportSpecificTopicOmbudRecord> getGetdepinf = null;
	public void setGetdepinf(Collection<JsonTdsExportSpecificTopicOmbudRecord> value) {  this.getGetdepinf = value; }
	public Collection<JsonTdsExportSpecificTopicOmbudRecord> getGetdepinf() {return this.getGetdepinf;}
	
	
}
