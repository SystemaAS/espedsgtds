/**
 * 
 */
package no.systema.tds.nctsimport.mapper.url.request;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.*;

import org.apache.log4j.Logger;

import no.systema.main.util.io.TextFileReaderService;
import no.systema.tds.nctsimport.model.jsonjackson.topic.JsonNctsImportSpecificTopicRecord;
import no.systema.tds.nctsimport.model.jsonjackson.topic.items.JsonNctsImportSpecificTopicItemRecord;
import no.systema.tds.nctsimport.model.jsonjackson.topic.unloading.JsonNctsImportSpecificTopicUnloadingRecord;
import no.systema.tds.nctsimport.model.jsonjackson.topic.unloading.items.JsonNctsImportSpecificTopicUnloadingItemRecord;

import no.systema.tds.util.TdsConstants;

/**
 * @author oscardelatorre
 * @param Dec 4, 2013
 */
public class UrlRequestParameterMapper {
	private static final Logger logger = Logger.getLogger(UrlRequestParameterMapper.class.getName());
	
	/**
	 * Builds the final url parameter list (to send with a GET or POST form method)
	 * @param object
	 * @return
	 * 
	 */
	public String getUrlParameterValidString(JsonNctsImportSpecificTopicRecord object){
		StringBuffer sb = new StringBuffer();
		try{
			for(Field field: object.getFields()){
				try{
					field.setAccessible(true);//we must do this in order to access private fields
					String value = (String)field.get(object); 
					if(value==null){
						sb.append("");
					}else{
						//CRUCIAL! to encode the value in order to handle all special characters (%,&,",',()...) before JSON-call
						//& will be converted into "%26", %="%25", etc. 
						//Refer to URLEncode special characters for further info)
						value = URLEncoder.encode(value, "UTF-8");
						
						sb.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
						sb.append(value.trim());
					}
				}catch(Exception e){
					//Try Integer
					if(field.get(object) instanceof Integer){
						Integer value = (Integer)field.get(object); 
						sb.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
						sb.append(value);
					}else if(field.get(object) instanceof Double){
						Double value = (Double)field.get(object); 
						sb.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
						sb.append(value);
					}else{
						logger.info(" [INFO]data type not yet supported...");
					}
					//add more instances if you need...
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
	
	public String getUrlParameterValidString(JsonNctsImportSpecificTopicItemRecord object){
		StringBuffer sb = new StringBuffer();
		try{
			for(Field field: object.getFields()){
				try{
				field.setAccessible(true);//we must do this in order to access private fields
				String value = (String)field.get(object); 
				if(value==null){
					sb.append("");
				}else{
					//CRUCIAL! to encode the value in order to handle all special characters (%,&,",',()...) before JSON-call
					//& will be converted into "%26", %="%25", etc. 
					//Refer to URLEncode special characters for further info)
					value = URLEncoder.encode(value, "UTF-8");
					
					sb.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
					sb.append(value.trim());
				}
				}catch(Exception e){
					//Try Integer
					if(field.get(object) instanceof Integer){
						Integer value = (Integer)field.get(object); 
						sb.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
						sb.append(value);
					}else if(field.get(object) instanceof Double){
						Double value = (Double)field.get(object); 
						sb.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
						sb.append(value);
					}else{
						logger.info(" [INFO]data type not yet supported...");
					}
					//add more instances if you need...					
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	//-------------------------------
	//Unloading - (Lossning) SECTION
	//-------------------------------
	public String getUrlParameterValidString(JsonNctsImportSpecificTopicUnloadingRecord object){
		StringBuffer sb = new StringBuffer();
		try{
			for(Field field: object.getFields()){
				try{
					field.setAccessible(true);//we must do this in order to access private fields
					String value = (String)field.get(object); 
					if(value==null){
						sb.append("");
					}else{
						//only editable fields
						if(field.getName().startsWith("ni")){
							//CRUCIAL! to encode the value in order to handle all special characters (%,&,",',()...) before JSON-call
							//& will be converted into "%26", %="%25", etc. 
							//Refer to URLEncode special characters for further info)
							value = URLEncoder.encode(value, "UTF-8");
							
							sb.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
							sb.append(value.trim());
						}
					}
				}catch(Exception e){
					if(field.getName().startsWith("ni")){
						//Try Integer
						if(field.get(object) instanceof Integer){
							Integer value = (Integer)field.get(object); 
							sb.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
							sb.append(value);
						}else if(field.get(object) instanceof Double){
							Double value = (Double)field.get(object); 
							sb.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
							sb.append(value);
						}else{
							logger.info(" [INFO]data type not yet supported...");
						}
						//add more instances if you need...
					}else{
						//NOTHING...continue
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @param object
	 * @return
	 */
	public String getUrlParameterValidString(JsonNctsImportSpecificTopicUnloadingItemRecord object){
		StringBuffer sb = new StringBuffer();
		try{
			for(Field field: object.getFields()){
				try{
					field.setAccessible(true);//we must do this in order to access private fields
					String value = (String)field.get(object); 
					if(value==null){
						sb.append("");
					}else{
						//only editable fields
						if(field.getName().startsWith("nv")){
							//CRUCIAL! to encode the value in order to handle all special characters (%,&,",',()...) before JSON-call
							//& will be converted into "%26", %="%25", etc. 
							//Refer to URLEncode special characters for further info)
							value = URLEncoder.encode(value, "UTF-8");
							
							sb.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
							sb.append(value.trim());
						}
					}
				}catch(Exception e){
					if(field.getName().startsWith("nv")){
						//Try Integer
						if(field.get(object) instanceof Integer){
							Integer value = (Integer)field.get(object); 
							sb.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
							sb.append(value);
						}else if(field.get(object) instanceof Double){
							Double value = (Double)field.get(object); 
							sb.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
							sb.append(value);
						}else{
							logger.info(" [INFO]data type not yet supported...");
						}
						//add more instances if you need...
					}else{
						//NOTHING...continue
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
}
