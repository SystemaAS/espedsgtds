/**
 * 
 */
package no.systema.tds.tdsexport.url.store;
import no.systema.main.util.AppConstants;
/**
 * Static URLs
 * @author oscardelatorre
 *
 */
public final class TdsExportUrlDataStore {
	
	
	//----------------------------
	//[1] FETCH ARENDE LIST
	//----------------------------
	//TEST static public String TDS_EXPORT_BASE_TOPICLIST_URL = "http://gw.systema.no/sycgip/TSVE000R.pgm?user=OSCAR&avd=1&sign=CB";
	static public String TDS_EXPORT_BASE_TOPICLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE000R.pgm";
	
	//http://10.13.3.22/sycgip/tsvz000r.pgm?user=CB&datum=20011001
	static public String TDS_EXPORT_BASE_TOPICLIST_ZEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVZ000R.pgm";
	
	//fetch external references
	//http://10.13.1.22/sycgip/tdke004r.pgm?user=A25DEMO
	static public String TDS_EXPORT_BASE_FETCH_TOPIC_LIST_EXTERNAL_REFERENCES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE004R.pgm";
		
	
	//----------------------------
	//[2] FETCH A SPECIFIC ARENDE
	//----------------------------
	//TEST static public String TDS_EXPORT_BASE_SPECIFIC_TOPIC_URL = "http://gw.systema.no/sycgip/TSVE001R.pgm?user=OSCAR&avd=1&opd=139";
	//TEST LOCAL FILE -->static public String TDS_EXPORT_GET_SPECIFIC_TOPIC_URL = "file:///Users/oscardelatorre/Documents/workspace/espedsg/test/diverseListPayload.txt";
	static public String TDS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE001R.pgm";
	//[2.1 FETCH Fakt.total from Invoices]
	static public String TDS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_FAKT_TOTAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE033R.pgm";
	
	//------------------------------
	//[3] EDIT A SPECIFIC ARENDE
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)	
	//mode=C (Copy selected topic)
	//mode=S (Send topic)
	//------------------------------		
	static public String TDS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE002R.pgm";
	
	
	//-----------------------------
	//[5] FETCH ITEM RECORDS (LIST)
	//-----------------------------
	//[5.1] FETCH SPECIFIC ITEM (for an update)
	static public String TDS_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE010R.pgm";
	//FETCH VARUKODER-KUNDENS Vareregister 
	static public String TDS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE011R.pgm";
	//http://gw.systema.no/sycgip/tsve041r.pgm?user=OSCAR&levenr=1
	static public String TDS_EXPORT_BASE_FETCH_TULLTAXA_KUNDENSVAREREG_VARUKODER_ITEMS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE041R.pgm";
	//http://gw.systema.no/sycgip/tsve042r.pgm?user=OSCAR&sviw_knnr=XXXX&sviw_knso=YYY&etc.....
	static public String TDS_EXPORT_BASE_UPDATE_TULLTAXA_KUNDENSVAREREG_VARUKODER_ITEMS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE042R.pgm";
	
	//In order to validate item lines with save header. Only with OK the user will be allowed to send the declaration
	//http://gw.systema.no/sycgip/tsve025r.pgm?user=OSCAR&avd=1&opd=267
	static public String TDS_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_VALIDATION_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE025R.pgm";
	
	
	
	//-----------------------------------
	//[6] EDIT A SPECIFIC ITEM RECORD
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)
	//-----------------------------------
	static public String TDS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE012R.pgm";	

	//Change Godsnr from header to all item lines
	static public String TDS_EXPORT_BASE_UPDATE_GODSNR_ON_ALL_ITEMS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE014R.pgm";
	//http://10.13.1.22/sycgip/TSVE014r.pgm?user=OSCAR&avd=1&opd=900077&godn=tarzan
	
	//----------------------------------
	//[6.0.1] Calculate Stat.values  
	//----------------------------------
	//http://gw.systema.no/sycgip/tsve017r.pgm?user=OSCAR&sveh_vakd=USD&sveh_vaku=8,4511&sveh_fabl=600&svev_stva=0&svev_stva2=0&svev_fabl=100 
	static public String TDS_EXPORT_BASE_STATISTICAL_VALUES_CALCULATION_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE017R.pgm";	
	//http://10.13.3.22/sycgip/tsve024r.pgm?user=CB&avd=1&opd=259
	static public String TDS_EXPORT_BASE_STATISTICAL_VALUES_CLEAR_TO_NULL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE024R.pgm";	
		
	//--------------------------------------
	//[6.0.2] Update AutoControl error line  
	//--------------------------------------
	static public String TDS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_AUTOCONTROL_ERROR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE022R.pgm";
	//http://gw.systema.no/sycgip/tsve022r.pgm?user=OSCAR&avd=1&opd=155216&lin=1&svev_err=X
	//--------------------------------------
	//[6.0.3] Update AutoControl error line  
	//--------------------------------------
	static public String TDS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_EXTRAMANGD_ENHET_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE023R.pgm";
	//http://gw.systema.no/sycgip/tsve023r.pgm?user=OSCAR&avd=1&opd=155216&lin=1&svev_err=X
		
	//-----------------------------------
	//[6.1] LIST/EDIT  INVOICE RECORD(s)
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)
	//-----------------------------------
	static public String TDS_EXPORT_BASE_FETCH_TOPIC_INVOICELIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE030R.pgm";
	//http://gw.systema.no/sycgip/tsve030r.pgm?user=OSCAR&avd=1&opd=52919
	static public String TDS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_INVOICE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE031R.pgm";
	//http://gw.systema.no/sycgip/tsve031r.pgm?user=OSCAR&avd=1&opd=52919&fak=SE444197610900
	static public String TDS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE032R.pgm";	
	//http://gw.systema.no/sycgip/tsve032r.pgm?user=OSCAR&mode=U&avd=1&opd=52919&fak=SE444197610900..................................
	
	
	//------------------------------------------
	//[6.2] LIST/Get  External INVOICE RECORD(s)
	//------------------------------------------
	static public String TDS_EXPORT_BASE_FETCH_TOPIC_INVOICELIST_EXTERNAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE060R.pgm";
	//http://gw.systema.no/sycgip/tsve060r.pgm?user=OSCAR
	static public String TDS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_INVOICE_EXTERNAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE061R.pgm";	
	//http://gw.systema.no/sycgip/tsve061r.pgm?user=OSCAR&reff=6441&unik=4009282
	static public String TDS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_EXTERNAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE062R.pgm";	
	//http://gw.systema.no/sycgip/tsve062r.pgm?user=OSCAR&avd=1&opd=91127&mode=U&reff=6445&unik=4012087
	
	//------------------------------------------
	//[7] LOG and ARCHIVE for a SPECIFIC ARENDE
	//------------------------------------------
	//This section contains external functions such as LOGGING, ARCHIVE, etc, for a specific topic within TDS EXPORT	
	static public String TDS_EXPORT_BASE_LOG_LIST_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE015R.pgm";	
	//http://gw.systema.no/sycgip/TSVE015R.pgm?user=OSCAR&avd=1&opd=218&typ=SVE 
	
	static public String TDS_EXPORT_BASE_LOG_LARGE_TEXT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE016R.pgm";	
	//http://gw.systema.no/sycgip/TSVE016R.pgm?user=OSCAR&fmn=84278
	
	static public String TDS_EXPORT_BASE_ARCHIVE_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE001.pgm";	//this function is actually general for all modules byt
	//http://gw.systema.no/sycgip/TJGE001.pgm?user=JOVO&avd=1&opd=52919
		
	//------------------------------------------
	//[8] PRINT document for a SPECIFIC ARENDE
	//------------------------------------------
	static public String TDS_EXPORT_BASE_PRINT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE014R.pgm";	//this function is actually general for all modules byt
	//http://gw.systema.no/sycgip/TSVE014R.pgm?user=OSCAR&avd=1&opd=218	

	//----------------------------------------------
	//[9] OMBUD default values for every new record
	//----------------------------------------------
	//http://gw.systema.no/sycgip/TSVI018R.pgm?user=OSCAR&avd=2
	static public String TDS_EXPORT_BASE_FETCH_OMBUD_DEFAULT_DATA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE018R.pgm";	
	
	//--------------------------------------------------
	//[10] Updates a message type (UNU, URT, UKO) to another
	// Used in Use Case: Begäran om klarering...
	//--------------------------------------------------
	//http://gw.systema.no/sycgip/TSVE019R.pgm?user=OSCAR&avd=1&opd=237&sveh_mtyp=URT  
	static public String TDS_EXPORT_BASE_UPDATE_MESSAGETYPE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE019R.pgm";	
		
	
	//-----------------------------
	// Change status (Admin Role)
	//-----------------------------
	static public String TDS_EXPORT_BASE_UPDATE_STATUS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE051R.pgm";
		
	//-----------------------------
	// Update proformaärende
	//-----------------------------
	static public String TDS_EXPORT_BASE_UPDATE_PROFORMA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVE053R.pgm";
		
}
