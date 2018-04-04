/**
 * 
 */
package no.systema.tds.tdsexport.model.jsonjackson.topic;
import java.util.Collection;

/**
 * 
 * @author oscardelatorre
 * @date Dec 2, 2015
 * 
 */
public class JsonTdsExportSpecificTopicFaktTotalContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() {return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() {return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() {return this.opd;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() {return this.errMsg;}
	
	private Collection<JsonTdsExportSpecificTopicFaktTotalRecord> invTot = null;
	public void setInvTot(Collection<JsonTdsExportSpecificTopicFaktTotalRecord> value) {  this.invTot = value; }
	public Collection<JsonTdsExportSpecificTopicFaktTotalRecord> getInvTot() {return this.invTot;}
	
	
}
