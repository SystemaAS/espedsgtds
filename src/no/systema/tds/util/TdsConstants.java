/**
 * 
 */
package no.systema.tds.util;

/**
 * All type of system constants for TDS in general
 * 
 * @author oscardelatorre
 *
 */
public final class TdsConstants {
	
	
	//session constants
	public static final String ACTIVE_URL_RPG = "activeUrlRPG";
	public static final String ACTIVE_URL_RPG_UPDATE = "activeUrlRPGUpdate";
	public static final String ACTIVE_URL_RPG_INITVALUE = "=)";
	
	//actions
	public static final String EDIT_ACTION_ON_TOPIC = "editActionOnTopic";
	public static final String EDIT_ACTION_ON_TOPIC_ITEM = "editActionOnTopicItem";
	
	public static final String ACTION_FETCH = "doFetch";
	public static final String ACTION_UPDATE = "doUpdate";
	public static final String ACTION_CREATE = "doCreate";
	public static final String ACTION_DELETE = "doDelete";
	public static final String ACTION_SEND = "doSend";
	
	//update modes
	public static final String MODE_UPDATE = "U";
	public static final String MODE_ADD = "A";
	public static final String MODE_DELETE = "D";
	public static final String MODE_SEND = "S";
	
	
	//url
	public static final String URL_CHAR_DELIMETER_FOR_URL_WITH_HTML_REQUEST_GET = "?";
	public static final String URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST = "&"; //Used for GET and POST
	//base path for resource files (for drop-downs or other convenient files
	public static final String RESOURCE_FILES_PATH = "/WEB-INF/resources/files/";
	public static final String RESOURCE_MODEL_KEY_CURRENCY_LIST = "currencyList";
	public static final String RESOURCE_MODEL_KEY_COUNTRY_LIST = "countryList";
	public static final String RESOURCE_MODEL_KEY_LANGUAGE_LIST = "languageList";
	public static final String RESOURCE_MODEL_KEY_BERAKNINGSENHET_LIST = "berakningsEnhetList";
	
	
	//code lists (Kolli(KLI), Tidigare handlingar(THO), Dokumenttyp(MCF), etc.
	public static final String RESOURCE_MODEL_KEY_CODE_KLI_LIST = "kolliCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_THO_LIST = "thoCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_CHA_LIST = "chaCodeList"; //Avgiftskoder
	public static final String RESOURCE_MODEL_KEY_CODE_GCY_LIST = "gcyCodeList"; //Landkoder
	public static final String RESOURCE_MODEL_KEY_CODE_MDX_LIST = "mdxCodeList"; //valutakoder
	public static final String RESOURCE_MODEL_KEY_CODE_MCF_LIST = "mcfCodeList"; //Bilagdahandlingarkoder
	public static final String RESOURCE_MODEL_KEY_CODE_SAL_LIST = "salCodeList"; //Särskilda upplysningar
	public static final String RESOURCE_MODEL_KEY_CODE_MDM_LIST = "mdmCodeList"; //Enhetslist (beräkning)
	
	
	public static final String RESOURCE_MODEL_KEY_CODE_FORMAN_LIST = "formanCodeList"; //förmånskoder
	public static final String RESOURCE_MODEL_KEY_CODE_FORFARANDE01_LIST = "forfarande01CodeList"; //förfarande 1
	public static final String RESOURCE_MODEL_KEY_CODE_FORFARANDE02_LIST = "forfarande02CodeList"; //förfarande 1
	
	
	//external URLs (if applicable)
	//public static final String URL_EXTERNAL_LANDCODES_TARIC_VALUE = "http://taric.tullverket.se/taric/bin/tagKodforteckning.cgi?kodtyp=L&lang=SV";
	public static final String URL_EXTERNAL_LANDCODES_TARIC_CODE = "taricLandCodesURL";
	public static final String URL_EXTERNAL_CURRENCYCODES_TARIC_CODE = "taricCurrencyCodesURL";
	public static final String URL_EXTERNAL_LANGUAGECODES_TARIC_CODE = "isoLanguageCodesURL";
	public static final String URL_EXTERNAL_KOLLISLAGCODES_TARIC_CODE = "taricKollislagCodesURL";
	public static final String URL_EXTERNAL_FORMANSKODCODES_TARIC_CODE = "taricFormanskodCodesURL";
	public static final String URL_EXTERNAL_FORFARANDE01_CODES_TARIC_CODE = "taricForfarande01CodesURL";
	public static final String URL_EXTERNAL_FORFARANDE02_CODES_TARIC_CODE = "taricForfarande02CodesURL";
	public static final String URL_EXTERNAL_AVGIFTSBERAKNINGAR_CODES_TARIC_CODE = "taricAvgiftsberakningarCodesURL";
	public static final String URL_EXTERNAL_MCFCODES_TARIC_CODE = "taricBilagdaHandlingarCodesURL";
	public static final String URL_EXTERNAL_SALCODES_TARIC_CODE = "taricSarskildaUpplysningarCodesURL";
	public static final String URL_EXTERNAL_FRAGA_TULLID_TARIC_CODE = "taricFragaTullidURL";
	
	
	
	
	
	//code lists (Kolli(017), Dokumentkod(013), etc.
	public static final String RESOURCE_MODEL_KEY_CODE_NCTS_DOKUMENT_LIST = "ncts013_DocType_CodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_NCTS_KOLLI_LIST = "ncts017_Kolli_CodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_NCTS_DEKLTYP_LIST = "ncts031_DeklType_CodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_NCTS_KANSLIGVARA_LIST = "ncts_KansligVara_CodeList";
	
	
	
	//avd and signature lists
	public static final String RESOURCE_MODEL_KEY_AVD_LIST = "avdList";
	public static final String RESOURCE_MODEL_KEY_SIGN_LIST = "signList";
	public static final String RESOURCE_MODEL_KEY_AVD_LIST_SESSION_TEST_FLAG = "avdListSessionTestFlag";
	
	
	//domain objects for model-view passing values
	public static final String DOMAIN_MODEL = "model";
	public static final String DOMAIN_RECORD = "record";
	public static final String DOMAIN_SEARCH_FILTER = "searchFilter";
	public static final String DOMAIN_RECORD_TOPIC = "recordTopic";
	public static final String DOMAIN_RECORD_TOPIC_UNLOADING = "recordTopicUnloading";
	
	public static final String DOMAIN_LIST = "list";
	public static final String DOMAIN_RECORD_ITEM_CONTAINER_TOPIC = "recordItemContainerTopic";
	public static final String DOMAIN_RECORD_ITEM_CONTAINER_INVOICE_TOPIC = "recordItemContainerInvoiceTopic";
	public static final String DOMAIN_SEARCH_FILTER_TDSIMPORT = "searchFilterTdsImport";
	public static final String DOMAIN_SEARCH_FILTER_TDSEXPORT = "searchFilterTdsExport";
	public static final String DOMAIN_SEARCH_FILTER_TDSIMPORT_NCTS = "searchFilterTdsImportNcts";
	public static final String DOMAIN_SEARCH_FILTER_TDSEXPORT_NCTS = "searchFilterTdsExportNcts";
	
	public static final String SESSION_LIST = "sessionList";
	public static final String SESSION_SEARCH_FILTER_TDSIMPORT = "searchFilterTdsImport";
	public static final String SESSION_SEARCH_FILTER_TDSEXPORT = "searchFilterTdsExport";
	public static final String SESSION_SEARCH_FILTER_TDSIMPORT_NCTS = "searchFilterTdsImportNcts";
	public static final String SESSION_SEARCH_FILTER_TDSEXPORT_NCTS = "searchFilterTdsExportNcts";
	
	public static final String ITEM_LIST = "itemList";	
	
	
	//aspects in view (sucha as errors, logs, other
	public static final String ASPECT_ERROR_MESSAGE = "errorMessage";
	public static final String ASPECT_ERROR_META_INFO = "errorInfo";
	
	
	
	
	   
}
