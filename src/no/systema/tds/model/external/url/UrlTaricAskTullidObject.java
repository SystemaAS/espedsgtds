/**
 * 
 */
package no.systema.tds.model.external.url;

/**
 * @author oscardelatorre
 * @date Sep 26, 2013
 * 
 */
public final class UrlTaricAskTullidObject {
	public static final String value = "https://tid.tullverket.se/FT/VisaArendestatus.aspx";
	//public static final String windowOpenDimensions = "window.open(this.href,\'Taric\',\'width=750,height=500,left=5,top=20, scrollbars, resizable'); return false";
	public static final String windowOpenDimensions = "window.open(this.href,\'Taric\'); return false";
	public String getValue(){return UrlTaricAskTullidObject.value; }
	public String getWindowOpenDimensions(){return UrlTaricAskTullidObject.windowOpenDimensions; }
	
}
