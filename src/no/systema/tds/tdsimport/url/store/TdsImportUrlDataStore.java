/**
 * 
 */
package no.systema.tds.tdsimport.url.store;
import no.systema.main.util.AppConstants;
/**
 * Static URLs
 * @author oscardelatorre
 *
 */
public final class TdsImportUrlDataStore {
	
	
	//----------------------------
	//[1] FETCH ARENDE LIST
	//----------------------------
	//TEST static public String TDS_EXPORT_BASE_TOPICLIST_URL = "http://gw.systema.no/sycgip/TSVI000R.pgm?user=OSCAR&avd=1&sign=CB";
	static public String TDS_IMPORT_BASE_TOPICLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI000R.pgm";
	//http://gw.systema.no/sycgip/TSVU000R.pgm?user=OSCAR...;
	static public String TDS_IMPORT_BASE_TOPICLIST_UTLAM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVU000R.pgm";
	//fetch external references
	//http://10.13.1.22/sycgip/tsvi004r.pgm?user=A25DEMO
	static public String TDS_IMPORT_BASE_FETCH_TOPIC_LIST_EXTERNAL_REFERENCES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI004R.pgm";
	//http://gw.systema.no/sycgip/tsvi005r.pgm?user=OSCAR&avd=1&opd=91127
	static public String TDS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_EXTERNAL_REFERENCES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI005R.pgm";		
	
	//----------------------------
	//[2] FETCH A SPECIFIC ARENDE
	//----------------------------
	//TEST static public String TDS_EXPORT_BASE_SPECIFIC_TOPIC_URL = "http://gw.systema.no/sycgip/TSVI001R.pgm?user=OSCAR&avd=1&opd=139";
	static public String TDS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI001R.pgm";
	
	//[2.1] FETCH Fakt.total from Invoices
	//http://gw.systema.no/sycgip/tsvi033r.pgm?user=OSCAR&avd=1&opd=211
	static public String TDS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_FAKT_TOTAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI033R.pgm";
	
	//------------------------------
	//[3] EDIT A SPECIFIC ARENDE
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)	
	//mode=C (Copy selected topic)... from Norskexport or fallback to the origin: transportuppdrag
	//mode=S (Send topic)
	//------------------------------		
	static public String TDS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI002R.pgm";
	
	
	//-----------------------------
	//[5] FETCH ITEM RECORDS (LIST)
	//-----------------------------
	static public String TDS_IMPORT_BASE_FETCH_TOPIC_ITEMLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI010R.pgm";
	//[5.1] FETCH SPECIFIC ITEM (for an update)
	static public String TDS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI011R.pgm";
	
	//FETCH VARUKODER-KUNDENS Vareregister 
	static public String TDS_IMPORT_BASE_FETCH_TULLTAXA_KUNDENSVAREREG_VARUKODER_ITEMS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI041R.pgm";
	//http://gw.systema.no/sycgip/tsvi041r.pgm?user=OSCAR&levenr=1
	static public String TDS_IMPORT_BASE_UPDATE_TULLTAXA_KUNDENSVAREREG_VARUKODER_ITEMS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI042R.pgm";
	//http://gw.systema.no/sycgip/tsvi042r.pgm?user=OSCAR&sviw_knnr=XXXX&sviw_knso=YYY&etc.....
	
	//In order to validate item lines with save header. Only with OK the user will be allowed to send the declaration
	//http://gw.systema.no/sycgip/tsvi025r.pgm?user=OSCAR&avd=1&opd=216
	static public String TDS_IMPORT_BASE_FETCH_TOPIC_ITEMLIST_VALIDATION_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI025R.pgm";
		
	
	//-----------------------------------
	//[6] EDIT A SPECIFIC ITEM RECORD
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)
	//-----------------------------------
	static public String TDS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI012R.pgm";	

	//Change Godsnr from header to all item lines
	static public String TDS_IMPORT_BASE_UPDATE_GODSNR_ON_ALL_ITEMS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI014GR.pgm";
	//http://10.13.1.22/sycgip/TSVI014gr.pgm?user=OSCAR&avd=1&opd=900077&godn=tarzan
	
	//----------------------------------
	//[6.1] EDIT/SAVE Avgiftsberäkningen  
	//----------------------------------
	//http://gw.systema.no/sycgip/TSVI017R.pgm?user=OSCAR&svih_vufr=123&svih_vuva=USD&svih_vuku=8&svih_vufo=123&svih_ovko=123&svih_kara=123&svih_anra=123&svih_lekd=DDP&svih_vakd=USD&svih_vaku=8&svih_fabl=123&sviv_vata=4601219000&sviv_ulkd=US&sviv_fokd=100&sviv_eup1=4000&sviv_ankv=1&sviv_stva=0&sviv_stva2=0&sviv_fabl=100 
	static public String TDS_IMPORT_BASE_AVGIFTS_CALCULATION_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI017R.pgm";	
	
	//--------------------------------------
	//[6.1.1] Update AutoControl error line  
	//--------------------------------------
	static public String TDS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_AUTOCONTROL_ERROR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI022R.pgm";
	//http://gw.systema.no/sycgip/tsvi022r.pgm?user=OSCAR&avd=1&opd=900077&lin=1&sviv_err=X
	//--------------------------------------
	//[6.1.2] Update AutoControl error line  
	//--------------------------------------
	static public String TDS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_EXTRAMANGD_ENHET_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI023R.pgm";
	//http://gw.systema.no/sycgip/tsvi023r.pgm?user=OSCAR&avd=1&opd=155216&lin=1&svev_ankv=XXX
		
	
	//-----------------------------------
	//[6.2] LIST/EDIT  INVOICE RECORD(s)
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)
	//-----------------------------------
	static public String TDS_IMPORT_BASE_FETCH_TOPIC_INVOICELIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI030R.pgm";
	//http://gw.systema.no/sycgip/tsvi030r.pgm?user=OSCAR&avd=1&opd=52919
	static public String TDS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_INVOICE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI031R.pgm";
	//http://gw.systema.no/sycgip/tsvi031r.pgm?user=OSCAR&avd=1&opd=52919&fak=SE444197610900
	static public String TDS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI032R.pgm";	
	//http://gw.systema.no/sycgip/tsvi032r.pgm?user=OSCAR&mode=U&avd=1&opd=52919&fak=SE444197610900..................................
	
	//------------------------------------------
	//[6.3] LIST/Get  External INVOICE RECORD(s)
	//------------------------------------------
	static public String TDS_IMPORT_BASE_FETCH_TOPIC_INVOICELIST_EXTERNAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI060R.pgm";
	//http://gw.systema.no/sycgip/tsvi060r.pgm?user=OSCAR
	static public String TDS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_INVOICE_EXTERNAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI061R.pgm";	
	//http://gw.systema.no/sycgip/tsvi061r.pgm?user=OSCAR&reff=6441&unik=4009282
	static public String TDS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_EXTERNAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI062R.pgm";	
	//http://gw.systema.no/sycgip/tsvi062r.pgm?user=OSCAR&avd=1&opd=91127&mode=U&reff=6445&unik=4012087
	
	
	//------------------------------------------
	//[7] LOG and ARCHIVE for a SPECIFIC ARENDE
	//------------------------------------------
	//This section contains external functions such as LOGGING, ARCHIVE, etc, for a specific topic within TDS EXPORT	
	static public String TDS_IMPORT_BASE_LOG_LIST_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI015R.pgm";	
	//http://gw.systema.no/sycgip/TSVE015R.pgm?user=OSCAR&avd=1&opd=218&typ=SVE 
	
	static public String TDS_IMPORT_BASE_LOG_LARGE_TEXT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI016R.pgm";	
	//http://gw.systema.no/sycgip/TSVE016R.pgm?user=OSCAR&fmn=84278
	
	static public String TDS_IMPORT_BASE_ARCHIVE_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE001.pgm";	//this function is actually general for all modules byt
	//http://gw.systema.no/sycgip/TJGE001.pgm?user=JOVO&avd=1&opd=52919
		
	//------------------------------------------
	//[8] PRINT document for a SPECIFIC ARENDE
	//------------------------------------------
	static public String TDS_IMPORT_BASE_PRINT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI014R.pgm";	//this function is actually general for all modules byt
	//http://gw.systema.no/sycgip/TSVE014R.pgm?user=OSCAR&avd=1&opd=218	

	//----------------------------------------------
	//[9] OMBUD default values for every new record
	//----------------------------------------------
	//http://gw.systema.no/sycgip/TSVI018R.pgm?user=OSCAR&avd=1
	static public String TDS_IMPORT_BASE_FETCH_OMBUD_DEFAULT_DATA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI018R.pgm";	
	
	//--------------------------------------------------
	//[10] Updates a message type (DNU, HNU) to another
	// Used in Use Case: Begäran om klarering...
	//--------------------------------------------------
	//http://gw.systema.no/sycgip/TSVI019R.pgm?user=OSCAR&avd=1&opd=156&svih_mtyp=HRT 
	static public String TDS_IMPORT_BASE_UPDATE_MESSAGETYPE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI019R.pgm";	
		
	//--------------------------------------------------------
	//[11] Bilagda handlingar (item lines AJAX help function)
	//--------------------------------------------------------
	//http://gw.systema.no/sycgip/TSVI020R.pgm?user=CB&avd=1&opd=171  
	static public String TDS_IMPORT_BASE_GET_BILAGDA_HANDLIGAR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI020R.pgm";	
	
	//--------------------------------------------------------
	//[12] Förmånskoder 
	//--------------------------------------------------------
	//http://gw.systema.no/sycgip/tsvi021r.pgm?user=OSCAR&lk=NO&vata=3808999000
	static public String TDS_IMPORT_BASE_GET_FORMANSKOD_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI021R.pgm";
	
	//-----------------------------
	// Change status (Admin Role)
	//-----------------------------
	static public String TDS_IMPORT_BASE_UPDATE_STATUS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI051R.pgm";
		
	
	
}
