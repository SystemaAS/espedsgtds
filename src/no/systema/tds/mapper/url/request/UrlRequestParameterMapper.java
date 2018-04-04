/**
 * 
 */
package no.systema.tds.mapper.url.request;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.*;

import no.systema.tds.model.jsonjackson.authorization.topic.JsonTdsAuthorizationSignSpecificTopicContainer;
import no.systema.tds.util.TdsConstants;

/**
 * @author oscardelatorre
 * @param June 24, 2013
 */
public class UrlRequestParameterMapper {
	
	/**
	 * Builds the final url parameter list (to send with a GET or POST form method)
	 * @param object
	 * @return
	 * 
	 */
	public String getUrlParameterValidString(JsonTdsAuthorizationSignSpecificTopicContainer object){
		StringBuffer sb = new StringBuffer();
		try{
			for(Field field: object.getFields()){
				field.setAccessible(true);//we must do this in order to access private fields
				sb.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
				String value = (String)field.get(object); 
				if(value==null){
					sb.append("");
				}else{
					//CRUCIAL! to encode the value in order to handle all special characters (%,&,",',()...) before JSON-call
					//& will be converted into "%26", %="%25", etc. 
					//Refer to URLEncode special characters for further info)
					value = URLEncoder.encode(value, "UTF-8");
					
					sb.append(value.trim());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * Builds the final url parameter list (to send with a GET or POST form method)
	 * @param object
	 * @return
	 */
	/*public String getUrlParameterValidString(JsonTdsExportSpecificTopicItemRecord object){
		StringBuffer sb = new StringBuffer();
		try{
			for(Field field: object.getFields()){
				field.setAccessible(true);//we must do this in order to access private fields
				sb.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
				String value = (String)field.get(object); 
				if(value==null){
					sb.append("");
				}else{
					sb.append(value);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	*/
	
}
