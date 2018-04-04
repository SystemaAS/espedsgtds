/**
 * 
 */
package no.systema.tds.tdsimport.mapper.url.request;

import java.lang.reflect.Field;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import no.systema.main.util.io.TextFileReaderService;
import no.systema.tds.tdsimport.model.KundensVaruRegisterUpdateItemRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicInvoiceRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemRecord;
import no.systema.tds.util.TdsConstants;


/**
 * @author oscardelatorre
 * @param Sep 2, 2013
 */
public class UrlRequestParameterMapper {
	private static final Logger logger = Logger.getLogger(UrlRequestParameterMapper.class.getName());
	/**
	 * Builds the final url parameter list (to send with a GET or POST form method)
	 * @param object
	 * @return
	 * 
	 */
	public String getUrlParameterValidString(JsonTdsImportSpecificTopicRecord object){
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
	public String getUrlParameterValidString(JsonTdsImportSpecificTopicItemRecord object){
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
						//& will be converted inte %26, %=%25, etc (refer to URLEncode special characters for further info)
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
	 * 
	 * @param object
	 * @return
	 */
	public String getUrlParameterValidString(JsonTdsImportTopicInvoiceRecord object){
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
	 * 
	 * @param object
	 * @return
	 */
	public String getUrlParameterValidString(KundensVaruRegisterUpdateItemRecord object){
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
						//& will be converted inte %26, %=%25, etc (refer to URLEncode special characters for further info)
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
	
	
}
