/**
 * 
 */
package no.systema.tds.model.jsonjackson.avdsignature;

/**
 * @author oscardelatorre
 * @date Mar 9, 2013
 * 
 */
public class JsonTdsAvdelningRecord {
	private String avd = null;
	public void setAvd(String value){ this.avd = value;}
	public String getAvd(){ return this.avd; }
	
	private String namn = null;
	public void setNamn(String value){ this.namn = value;}
	public String getNamn(){ return this.namn; }
	
	private String tst = null;
	public void setTst(String value){ this.tst = value;}
	public String getTst(){ return this.tst; }
}
