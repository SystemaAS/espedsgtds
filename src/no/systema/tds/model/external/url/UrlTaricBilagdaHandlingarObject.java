/**
 * 
 */
package no.systema.tds.model.external.url;

/**
 * 
 * @author oscardelatorre
 * @date Oct 14, 2013
 * 
 */
public final class UrlTaricBilagdaHandlingarObject {
	//OBSOLETE -->public static final String value = "http://taric.tullverket.se/taric/bin/tagKodforteckning.cgi?kodtyp=D&lang=SV&sok=S%F6k";
	public static final String value = "http://tulltaxan.tullverket.se/tariff/uc/qry/cl/search.jsf";
	
	public static final String windowOpenDimensions = "window.open(this.href,\'Taric\'); return false";
	public String getValue(){return UrlTaricBilagdaHandlingarObject.value; }
	public String getWindowOpenDimensions(){return UrlTaricBilagdaHandlingarObject.windowOpenDimensions; }
	
}
