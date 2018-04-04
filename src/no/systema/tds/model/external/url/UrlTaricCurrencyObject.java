/**
 * 
 */
package no.systema.tds.model.external.url;

/**
 * 
 * @author oscardelatorre
 * @date Sep 15, 2013
 * 
 * 
 */
public final class UrlTaricCurrencyObject {
	//Obsolete --> public static final String value = "http://taric.tullverket.se/taric/bin/tagValuta.cgi##_top";
	public static final String value = "http://tulltaxan.tullverket.se/tariff/uc/qry/er/queryExchangeRate.jsf?unit&frDate&toDate=2020-09-01";
	public static final String windowOpenDimensions = "window.open(this.href,\'Taric\'); return false";
	public String getValue(){return UrlTaricCurrencyObject.value; }
	public String getWindowOpenDimensions(){return UrlTaricCurrencyObject.windowOpenDimensions; }
	
}
