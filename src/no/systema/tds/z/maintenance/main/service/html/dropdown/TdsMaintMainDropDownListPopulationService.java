/**
 * 
 */
package no.systema.tds.z.maintenance.main.service.html.dropdown;

import java.util.List;

import org.apache.log4j.Logger;

import no.systema.main.context.TdsServletContext;
import no.systema.main.util.io.TextFileReaderService;
import no.systema.tds.mapper.url.request.UrlRequestParameterMapper;
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
 * @date May 03, 2017
 * 
 */
public class TdsMaintMainDropDownListPopulationService {
	private static final Logger logger = Logger.getLogger(TdsMaintMainDropDownListPopulationService.class.getName());
	
	private final String FILE_RESOURCE_PATH = TdsConstants.RESOURCE_FILES_PATH;
	private TextFileReaderService textFileReaderService = new TextFileReaderService();
	
	/**
	 * List of currencies
	 * @return
	 */
	public List<String> getToldsatstypeList(){
		String LIST_FILE = "TOCHANGE_skatMaintenanceToldsatstypeList.txt";
		List<String> list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + LIST_FILE));
		//Debug
		/*
		for(String record : list){
			logger.info(record);
		}*/
		return list;
	}
}
