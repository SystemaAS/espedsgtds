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
 * http://gw.systema.no/sycgip/TNCG005R.pgm?user=OSCAR&typ=013
 *
 *	012=Språkkod                      
 *	013=Dokumentkod                  
 *	014=Tidigare dokument              
 *	017=Kollityp                      
 *	031=Deklarationstyp              
 *	039=Tilläggsupplysning            
 *	047=Kontrollresultat              
 *	064=Känslig vara                  
 *	096=Speciella omständigheter      
 *	105=Tillgångskod för garanti      
 *	106=Tullkontor referansenr        
 *	116=Betalningssätt transportkostnad  
 *	
 * @author oscardelatorre
 * @date June 28, 2013
 *
 */
public class JsonTdsNctsCodeContainer {
	
	public static final String KOD_SPRAK = "012";
	public static final String KOD_DOK = "013";
	public static final String KOD_TIDIGARE_DOK = "014";
	public static final String KOD_KOLLI_TYP = "017";
	public static final String KOD_DEKL_TYP = "031";
	public static final String KOD_TILLAGSUPP = "039";
	public static final String KOD_KONTROLLRESULTAT = "047";
	public static final String KOD_KANSLIGVARA = "064";
	public static final String KOD_SPEC_OMST = "096";
	public static final String KOD_TILLGANGASKOD_GARANTI = "105";
	public static final String KOD_TULLKONTOR_REF = "106";
	public static final String KOD_BETALNINGSSATT_TRANSPORTKOSTNAD = "116";
	
			
	
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String typ = null;
	public void setTyp(String value){ this.typ = value;}
	public String getTyp(){ return this.typ; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTdsNctsCodeRecord> kodlista = null;
	public void setKodlista(Collection<JsonTdsNctsCodeRecord> value){ this.kodlista = value;}
	public Collection<JsonTdsNctsCodeRecord> getKodlista(){ return this.kodlista; }
	
}
