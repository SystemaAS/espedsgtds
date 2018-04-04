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
public final class UrlTaricSarskildaUpplysningarObject {
	//OBSOLETE --> public static final String value = "http://taric.tullverket.se/taric/bin/tagKodforteckning.cgi?kodtyp=S&lang=SV&sok=S%C3%B6k";
	public static final String value = "http://tulltaxan.tullverket.se/tariff/uc/qry/cl/search.jsf";
	public static final String windowOpenDimensions = "window.open(this.href,\'Taric\'); return false";
	public String getValue(){return UrlTaricSarskildaUpplysningarObject.value; }
	public String getWindowOpenDimensions(){return UrlTaricSarskildaUpplysningarObject.windowOpenDimensions; }
	
}
