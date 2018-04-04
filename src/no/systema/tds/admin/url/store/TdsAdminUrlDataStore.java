/**
 * 
 */
package no.systema.tds.admin.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Jan 14, 2014
 * 
 * 
 */
public final class TdsAdminUrlDataStore {
	
	//--------------------------------
	//[1] FETCH Transportuppdrag LIST
	//--------------------------------
	static public String TDS_ADMIN_BASE_TRANSPORT_LIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG018R.pgm";
	//http://gw.systema.no/sycgip/TSVG018R.pgm?user=OSCAR&avd=1&sign=JOVO&DATUM=20131001 
	
	//----------------------------
	//[1] FETCH Norsk Import LIST
	//----------------------------
	static public String TDS_ADMIN_BASE_NORSKIMPORT_LIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG019R.pgm";
	//http://gw.systema.no/sycgip/TSVG019R.pgm?user=OSCAR&avd=1&sign=JOVO&DATUM=20131001
		
	//----------------------------
	//[1] FETCH Norsk Export LIST
	//----------------------------
	static public String TDS_ADMIN_BASE_NORSKEXPORT_LIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG020R.pgm"; 
	//http://gw.systema.no/sycgip/TSVG020R.pgm?user=OSCAR&DATUM=20101001
		
	
}
