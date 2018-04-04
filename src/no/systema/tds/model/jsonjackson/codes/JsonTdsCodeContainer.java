/**
 * 
 */
package no.systema.tds.model.jsonjackson.codes;

import java.util.Collection;

import no.systema.tds.model.jsonjackson.codes.JsonTdsCodeRecord;

/**
 * General Code Container for TDS general codes
 * 
 * Example
 * http://gw.systema.no/sycgip/TSVG005R.pgm?user=OSCAR&ie=A&typ=THO 
 * 
 * KLI = Kollislag           IE = A
 * GCY = Landkod      		 IE = A
 * MDX = Valutakod			IE = A
 * MCF = Bilagda Handlingar  IE = A
 * ADD = Tilläggskod exp     IE = E
 * ADD = Tilläggskod imp     IE = A
 * FFK = Förfarande :2 exp   IE = E
 * FFK = Förfarande :2 imp   IE = I
 * SAL = Särsk. uppl kod     IE = A
 * FOR = Förmånskod imp      IE = I
 * THO = Tid.handl. TYP      IE = A
 *
 * @author oscardelatorre
 * @date Feb 24, 2013
 *
 */
public class JsonTdsCodeContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String ie = null;
	public void setIe(String value){ this.ie = value;}
	public String getIe(){ return this.ie; }
	
	private String typ = null;
	public void setTyp(String value){ this.typ = value;}
	public String getTyp(){ return this.typ; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTdsCodeRecord> kodlista = null;
	public void setKodlista(Collection<JsonTdsCodeRecord> value){ this.kodlista = value;}
	public Collection<JsonTdsCodeRecord> getKodlista(){ return this.kodlista; }
	
}
