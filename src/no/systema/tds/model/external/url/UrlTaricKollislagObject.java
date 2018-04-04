/**
 * 
 */
package no.systema.tds.model.external.url;

/**
 * @author oscardelatorre
 * @date Sep 25, 2013
 * 
 */
public final class UrlTaricKollislagObject {
	public static final String value = "http://taric.tullverket.se/taric/bin/tagKodforteckning.cgi?kodtyp=M&lang=SV&sok=S%F6k";
	//not working in Chrome
	//public static final String windowOpenDimensions = "window.open(this.href,\'Taric\',\'width=750,height=500,left=5,top=20, scrollbars, resizable'); return false";
	public static final String windowOpenDimensions = "window.open(this.href,\'Taric\'); return false";
	public String getValue(){return UrlTaricKollislagObject.value; }
	public String getWindowOpenDimensions(){return UrlTaricKollislagObject.windowOpenDimensions; }
	
}
