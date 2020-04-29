/**
 * 
 */
package no.systema.tds.url.store;
import no.systema.main.util.AppConstants;

/**
 * Static URLs
 * @author oscardelatorre
 *
 */
public final class TdsUrlDataStore {
	
	
	//----------------------------------
	//[1] FETCH HEADER FUNCTIONS GENERAL
	//----------------------------------
	
	//FETCH CUSTOMER(S)
	static public String TDS_FETCH_CUSTOMER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG003R.pgm";
	//FETCH UTFARTSTULL KONTOR
	static public String TDS_FETCH_UTFARTS_TULLKONTOR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG004R.pgm";//?user=OSCAR&sonavn=SVINESUND&kod=SE...";
	// GENERAL CODES - TDS - TDS 
	// (Kollislag (KLI), Tidigare handlingar (THO), etc)
	static public String TDS_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG005R.pgm"; 
	//http://gw.systema.no/sycgip/TSVG005R.pgm?user=OSCAR&ie=A&typ=KLI
	
	//FETCH VARUKODER-TARIC (ITEM LINES)
	static public String TDS_FETCH_TARIC_VARUKODER_ITEMS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG006R.pgm";//?user=OSCAR&ie=I&kod=8514
	static public String TDS_FETCH_TARIC_VARUKODER_ITEMS_FROM_DESC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG023R.pgm";//?user=OSCAR&ie=I&sok=Cirrhin
	
	
	//CURRENCY RATES
	static public String TDS_FETCH_CURRENCY_RATE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG007R.pgm"; //?user=OSCAR&kod=SEK...		
	
	// GENERAL CODES - TDS - NCTS (Since we discovered discrepancies towards TSVG005R.pgm (above).
	// A special function is needed for the NCTS domain
	// (Kollislag (017), Dokumentkod (013), etc)
	/* typ kan ha följande värde:
		012=Språkkod                      
		013=Dokumentkod                  
		014=Tidigare dokument              
		017=Kollityp                      
		031=Deklarationstyp              
		039=Tilläggsupplysning            
		047=Kontrollresultat              
		064=Känslig vara                  
		096=Speciella omständigheter      
		105=Tillgångskod för garanti      
		106=Tullkontor referansenr        
		116=Betalningssätt transportkostnad */
	static public String TDS_NCTS_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNCG005R.pgm"; 
	//http://gw.systema.no/sycgip/TNCG005R.pgm?user=OSCAR&typ=013 
	
	//----------------------------------
	//FETCH AVDELNING AND SIGNATURE
	//----------------------------------
    //ie=E	TDS export
    //ie=I	TDS import
    //ie=X	NCTS export
    //ie=N	NCTS import
	//ie=A	Transportuppdrag
	//ie=B	Norsk Import (SAD-import)
	//ie=C	Norsk Export (SAD-export)
	//ie=D	Dansk Import (Dansk-SKAT-Told)
	static public String TDS_FETCH_AVDELNINGAR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG008R.pgm"; //?user=OSCAR&ie=E
	
	//ie=F	TDS import/export
	//ie=N	NCTS import/export	
	//TODO!! ...more parameters for the ADMIN-module
	static public String TDS_FETCH_SIGNATURE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG009R.pgm"; //?user=OSCAR&ie=F
	
	
	//-------------------------
	//[2] Authorization on TDS
	//-------------------------
	static public String TDS_GET_AUTHORIZATION_CODE = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG010R.pgm"; //user=OSCAR&usrAS400=CB&pwAS400=Before9&ant=3
	//Sms code
	static public String TDS_GET_PKI_SMS_CODE = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG011R.pgm"; //user=OSCAR&usrAS400=CB
	//Signerings ärendelista
	static public String TDS_GET_PKI_TOPIC_LIST = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG012R.pgm"; 
	//
	static public String TDS_SIGNED_TOPIC_APPROVE = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG013R.pgm"; //user=OSCAR&avd=1&opd=129&ursp=TI
	//http://gw.systema.no/sycgip/TSVG013R.pgm?user=OSCAR&avd=1&opd=129&ursp=TI 
	
	static public String TDS_SIGNED_TOPIC_REVOKE = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG014R.pgm"; //user=OSCAR&avd=1&opd=129&ursp=TI
	//http://gw.systema.no/sycgip/TSVG014R.pgm?user=OSCAR&avd=1&opd=129&ursp=TI 

	//-----------------------------------------------------
	//[2.1] Authorization on TDS - change password routine
	//-----------------------------------------------------
	static public String TDS_CHANGE_PASSWORD = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG015R.pgm"; 
	//http://gw.systema.no/sycgip/TSVG015R.pgm?user=CB&usrAS400=CBX&pwAS400=After8&pwAS400N1=Straffe12&pwAS400N=Straffe12 	
	/*user=SYSPED WEB användarid 
	usrAS400=Användarid till iSeries 
	pwAS400=Nuvarande lösenord till iSeries 
	pwAS400N1=Nytt lösenord till iSeries, repetition-1 
	pwAS400N2=Nytt lösenord till iSeries, repetition-2 
	*/
	
	//-----------------------------
	//Validation routines (general)
	//-----------------------------
	//http://gw.systema.no/sycgip/TSVG016R.pgm?user=OSCAR&ie=I&kod=9404300000&lk=CA 
	//lk = landkod, kod=Varukod 
	//Use case triggering this pgm. TDS Import - Item lines
	static public String TDS_CHECK_EXTRA_MANGDENHET = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG016R.pgm"; 
	
	
	//-------------------------------
	//Name for a given SIGN (general)
	//-------------------------------
	//http://gw.systema.no/sycgip/TSVG017R.pgm?user=OSCAR&avd=1&sign=OT
	static public String TDS_FETCH_SIGNATURE_NAME_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG017R.pgm"; 
	
	//--------------------------------------------------------
	//[11] Bilagda handlingar-Ykoder 
	//--------------------------------------------------------
	static public String TDS_FETCH_BILAGDA_HANDLIGAR_YKODER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG021R.pgm";
	//http://gw.systema.no/sycgip/tsvg021r.pgm?user=OSCAR&ie=E&vata=38089990&lk=NO
	
	//------------------------------------------------------
	//[12] Tilläggskoder Rubrik 33 - Items (Export/Import)
	//------------------------------------------------------
	//http://gw.systema.no/sycgip/tsvg022r.pgm?user=OSCAR&ie=I&vata=8708299000&lk=NO
	static public String TDS_FETCH_TILLAGSKODER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG022R.pgm";
	
	//--------------
	//PRINT special
	//--------------
	//http://gw.systema.no/sycgip/TARC000R.pgm?user=OSCAR&avd=1&opd=218&type=Z
	static public String TDS_BASE_PRINT_FORSATTSBLAD_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TARC000R.pgm";	
	//http://gw.systema.no/sycgip/TARC001R.pgm?user=OSCAR&type=Z..
	static public String TDS_CODES2_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TARC001R.pgm"; 
	
	
	//-------------
	//Upload CSV
	//-------------
	static public String SYSTEMA_UPLOAD_FILE_CSV_INVOICE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG020R.pgm";	
	//http://gw.systema.no:65209/sycgip/TSVG020R.pgm?user=OSCAR&wsdokn=tarzan.jpg
	//{ "user": "OSCAR", "wsdokn": "tarzan.jpg","valids": "Y", "tmpdir": "/pdf/tmp/", "errMsg": "", "uploadcsv": [] } 
		

}
