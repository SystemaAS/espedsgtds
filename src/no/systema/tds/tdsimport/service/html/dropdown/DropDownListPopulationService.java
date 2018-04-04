/**
 * 
 */
package no.systema.tds.tdsimport.service.html.dropdown;

import java.util.List;

import org.apache.log4j.Logger;

import no.systema.main.context.TdsServletContext;
import no.systema.main.util.io.TextFileReaderService;
import no.systema.main.util.DateTimeManager;
import no.systema.tds.util.TdsConstants;


/**
 * This service fetches values into drop downs in HTML
 * Criteria is based upon whether the drop down list is static or dynamic.
 * 
 * This class is used ONLY for STATIC lists (e.g. currency codes, country codes, etc)
 * 
 * The servlet context is necessary in order to get a text-xml file within the webb-application path...
 * All static lists are retrieved from a file on disk.
 *  
 * @author oscardelatorre
 *
 */
public class DropDownListPopulationService {
	private final String FILE_RESOURCE_PATH = TdsConstants.RESOURCE_FILES_PATH;
	private TextFileReaderService textFileReaderService = new TextFileReaderService();
	private static final Logger logger = Logger.getLogger(DropDownListPopulationService.class.getName());
	private DateTimeManager mgr = new DateTimeManager();
	
	/**
	 * List of currencies
	 * @return
	 */
	public List<String> getCurrencyList(){
		String LIST_FILE = "currencyList.txt";
		List<String> list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + LIST_FILE));
		//Debug
		/*
		for(String record : list){
			logger.info(record);
		}*/
		return list;
	}
	
	/**
	 * List of countries
	 * @return
	 */
	public List<String> getCountryList(){
		String LIST_FILE = "countryList.txt";
		List<String> list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + LIST_FILE));
		//Debug
		/*
		for(String record : list){
			logger.info(record + "X");
		}*/
		return list;
	}
	/**
	 * List of Berakningsenheter on Avgiftsberkningarna (TDS Import- Item lines)
	 * 
	 * @return
	 * @OBSOLETE Currently replaced by service: TSVG005R.pgm?user=OSCAR&typ=MDM&IE=A
	 * 
	 */
	public List<String> getBerakningsEnheterList(){
		String LIST_FILE = "berakningsEnheterList.txt";
		String LIST_FILE_NEW = "berakningsEnheterList_Tulltaxa20160201.txt";
		String NEW_TULLTAXA_DATE_LIMIT = "20160201";
		List<String> list = null;
		
		boolean isValid = mgr.currentDayBeforeUserDate(NEW_TULLTAXA_DATE_LIMIT, "yyyyMMdd");
		if(isValid){
			list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + LIST_FILE));
		}else{
			list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + LIST_FILE_NEW));
		}
		
		
		//Debug
		/*
		for(String record : list){
			logger.info(record + "X");
		}*/
		return list;
	}
}
