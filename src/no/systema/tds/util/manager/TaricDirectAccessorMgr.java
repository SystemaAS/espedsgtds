/**
 * 
 */
package no.systema.tds.util.manager;

import no.systema.tds.model.external.url.UrlTaricAskTullidObject;
import no.systema.tds.util.TdsConstants;

import java.util.*;

/**
 * The class has direct access to an external Taric url address
 * 
 * @author oscardelatorre
 * @date Nov 12, 2013
 * 
 *
 */
public class TaricDirectAccessorMgr {
	
	/**
	 * 
	 * @param model
	 */
	public void askTullid(Map model){
		model.put(TdsConstants.URL_EXTERNAL_FRAGA_TULLID_TARIC_CODE, new UrlTaricAskTullidObject());
	}
	
	
}
