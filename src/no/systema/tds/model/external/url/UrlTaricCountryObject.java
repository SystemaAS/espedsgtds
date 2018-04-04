/**
 * 
 */
package no.systema.tds.model.external.url;

/**
 * @author oscardelatorre
 * @date Sep 13, 2013
 * 
 */
public final class UrlTaricCountryObject {
	//Obsolete --> public static final String value = "http://taric.tullverket.se/taric/bin/tagKodforteckning.cgi?kodtyp=L&lang=SV";
	public static final String value = "http://tulltaxan.tullverket.se/tariff/uc/qry/cl/search.jsf";
	
	public static final String windowOpenDimensions = "window.open(this.href,\'Taric\'); return false";
	public String getValue(){return UrlTaricCountryObject.value; }
	public String getWindowOpenDimensions(){return UrlTaricCountryObject.windowOpenDimensions; }
	
}
