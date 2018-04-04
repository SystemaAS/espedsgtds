/**
 * 
 */
package no.systema.tds.model.signpki;

/**
 * This object is the data carrier used inside a controller instead of a map.
 * Usually in order to map some request parameters outside any back-end (JSON) interaction.
 * 
 * In other words: this class will only map front-end values 
 * 
 *
 * @author oscardelatorre
 * @date 24.Jun.2013
 *
 */
public class ApprovedRevokedDataCarrierObject {
	public final static String ACTION_APPROVED = "A"; //Approved
	public final static String ACTION_REVOKED = "R"; //Revoked
	

	private String avd = null;
	public void setAvd(String value){ this.avd = value;}
	public String getAvd(){ return this.avd; }

	private String opd = null;
	public void setOpd(String value){ this.opd = value;}
	public String getOpd(){ return this.opd; }
	
	private String ursprung = null;
	public void setUrsprung(String value){ this.ursprung = value;}
	public String getUrsprung(){ return this.ursprung; }
	
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	//set approved or revoked
	private String actionType = null;
	public void setActionType(String value){ this.actionType = value;}
	public String getActionType(){ return this.actionType; }
	
	

}
