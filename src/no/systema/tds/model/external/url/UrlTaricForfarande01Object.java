/**
 * 
 */
package no.systema.tds.model.external.url;

/**
 * @author oscardelatorre
 * @date Sep 26, 2013
 * 
 */
public final class UrlTaricForfarande01Object {
	//Obsolete->> public static final String value = "http://taric.tullverket.se/taric/bin/tagKodforteckning.cgi?kodtyp=P&lang=SV&sok=S%F6k";
	public static final String value = "http://tulltaxan.tullverket.se/tariff/uc/qry/cl/search.jsf";
	
	public static final String windowOpenDimensions = "window.open(this.href,\'Taric\'); return false";
	public String getValue(){return UrlTaricForfarande01Object.value; }
	public String getWindowOpenDimensions(){return UrlTaricForfarande01Object.windowOpenDimensions; }
	
}
