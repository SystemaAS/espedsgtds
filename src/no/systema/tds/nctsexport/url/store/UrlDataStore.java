/**
 * 
 */
package no.systema.tds.nctsexport.url.store;
import no.systema.main.util.AppConstants;
/**
 * Static URLs
 * @author oscardelatorre
 *
 */
public final class UrlDataStore {
	
	//----------------------------
	//[1] FETCH ARENDE LIST
	//----------------------------
	//http://gw.systema.no/sycgip/TNCE000R.pgm?user=OSCAR&avd=1&sign=CB";
	static public String NCTS_EXPORT_BASE_TOPICLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNCE000R.pgm"; 
	//http://gw.systema.no/sycgip/TNCE000R.pgm?user=OSCAR&tvdref=tarzan
	static public String NCTS_EXPORT_BASE_TOPICLIST_DOCREF_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNCE000R2.pgm"; 
	
	//---------------------------------------------------------------
	//[2] FETCH A SPECIFIC ARENDE or Default values for a NEW ARENDE
	//---------------------------------------------------------------
	static public String NCTS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNCE001R.pgm";
	//http://gw.systema.no/sycgip/TNCE001R.pgm?user=OSCAR&avd=1&opd=50013
	//http://gw.systema.no/sycgip/TNCE001R.pgm?user=OSCAR&avd=1 (for default values with CREATE NEW)
		
	//------------------------------
	//[3] EDIT A SPECIFIC ARENDE
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)	
	//mode=C (Copy existing topic) from Norskimport or fallback to the origin: transportuppdrag
	//mode=S (Send topic)
	//------------------------------		
	static public String NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNCE002R.pgm";
	//http://gw.systema.no/sycgip/TNCE002R.pgm?user=OSCAR&thavd=1&sign=CB&mode=A
	//http://gw.systema.no/sycgip/TNCE002R.pgm?user=OSCAR&thavd=1&newavd=1&thtdn=50013&mode=C&newsign=OT
	
	//Validate Guarantee
	static public String NCTS_EXPORT_BASE_VALIDATE_SPECIFIC_TOPIC_GUARRANTEE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNCE003R.pgm";
	//http://gw.systema.no/sycgip/TNCE003R.pgm?user=YBC&thgft1=09SE00005000000W7&thgadk=2222
	
	//Validate Sensitive goods
	//http://gw.systema.no/sycgip/TNCE017R.pgm?user=OSCAR&tftanr=170199(valid varukod) --->010290 (valid varukod)
	static public String NCTS_EXPORT_BASE_VALIDATE_SPECIFIC_TOPIC_ITEM_SENSITIVE_GOODS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNCE017R.pgm";
	
	
	//Refresh ARENDE
	static public String NCTS_EXPORT_BASE_REFRESH_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNCE004R.pgm";
	
	
	//-----------------------------
	//[5] FETCH ITEM RECORDS (LIST)
	//-----------------------------
	static public String NCTS_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNCE010R.pgm";
	//[5.1] FETCH SPECIFIC ITEM (for an update)
	static public String NCTS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNCE011R.pgm";
	
	
	//-----------------------------------
	//[6] EDIT A SPECIFIC ITEM RECORD
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)
	//-----------------------------------
	static public String NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNCE012R.pgm";	
	//http://gw.systema.no/sycgip/TNCE012R.pgm?user=OSCAR&avd=1&opd=50079&lin=3&mode=D	
	
	
	//---------------------------------------------
	//[6.1] IMPORT into item lines
	//avd = avdelningen till NCTS export
	//opd = angivelsenr till NCTS export
	//sveh_syav = avdelningen till exportangivelsen
	//sveh_syop = angivelsenr till exportangivelsen
	//----------------------------------------------
	static public String NCTS_EXPORT_BASE_IMPORT_EXPORT_AS_ITEMLINE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNCE091R.pgm";	
	//http://gw.systema.no/sycgip/tnce091r.pgm?user=OSCAR&avd=1&opd=188888&sveh_syav=2&sveh_syop=152221
	
	
	//------------------------------------------
	//[7] LOG and ARCHIVE for a SPECIFIC ARENDE
	//------------------------------------------
	//This section contains external functions such as LOGGING, ARCHIVE, etc, for a specific topic within TDS EXPORT	
	static public String NCTS_EXPORT_BASE_LOG_LIST_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNCE015R.pgm";	
	//http://gw.systema.no/sycgip/TNCE015R.pgm?user=OSCAR&avd=1&opd=50113&typ=5
	/* Typ kan vara:
		0 = Norsk SAD Import  
		1 = Norsk SAD Eksport
		2 = Norsk NCTS Eksport      
		3 = Norsk NCTS Import      
		4 = All edifact
		5 = Svensk NCTS Export  
		6 = Svensk NCTS Import */	
	
	static public String NCTS_EXPORT_BASE_LOG_LARGE_TEXT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNCE016R.pgm";	
	//http://gw.systema.no/sycgip/TNCE016R.pgm?user=OSCAR&fmn=85847 
	
	static public String NCTS_EXPORT_BASE_ARCHIVE_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE001.pgm";	 
	//http://gw.systema.no/sycgip/TJGE001.pgm?user=JOVO&avd=1&opd=???
			
	//------------------------------------------
	//[8] PRINT document for a SPECIFIC ARENDE
	//------------------------------------------
	static public String NCTS_EXPORT_BASE_PRINT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNCE014R.pgm";	
	//http://gw.systema.no/sycgip/TNCE014R.pgm?user=OSCAR&avd=1&opd=218	

	//-----------------------------
	// Change status (Admin Role)
	//-----------------------------
	static public String NCTS_EXPORT_BASE_UPDATE_STATUS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNCE051R.pgm";
		
	
	
}
