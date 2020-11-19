package no.systema.tds.nctsexport.validator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.util.BigDecimalFormatter;
import no.systema.tds.nctsexport.service.NctsExportSpecificTopicService;
import no.systema.tds.nctsexport.service.NctsExportSpecificTopicServiceImpl;

import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportSpecificTopicRecord;
import no.systema.tds.nctsexport.model.jsonjackson.topic.validation.JsonNctsExportSpecificTopicGuaranteeValidatorContainer;

import no.systema.tds.nctsexport.url.store.UrlDataStore;
import no.systema.tds.util.TdsConstants;

/**
 * 
 * @author oscardelatorre
 *
 */
public class NctsExportHeaderValidator implements Validator {
	private static final Logger logger = Logger.getLogger(NctsExportHeaderValidator.class.getName());
	//Intantiate services here since we are not capable to configure injection with Autowired. Check that further...
	private UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
	private NctsExportSpecificTopicService nctsExportSpecificTopicService = new NctsExportSpecificTopicServiceImpl();
	private BigDecimalFormatter bigDecimalFormatter = new BigDecimalFormatter();   
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonNctsExportSpecificTopicRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonNctsExportSpecificTopicRecord record = (JsonNctsExportSpecificTopicRecord)obj;
		System.out.print("Inside NctsExportHeaderValidator");
		
		//Check for Mandatory fields first
		
		//sender
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thnas", "systema.ncts.export.header.error.null.thnas"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtins", "systema.ncts.export.header.error.null.thtins"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thads1", "systema.ncts.export.header.error.null.thads1"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thpns", "systema.ncts.export.header.error.null.thpns"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thpss", "systema.ncts.export.header.error.null.thpss"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thlks", "systema.ncts.export.header.error.null.thlks"); 
		
		//receiver (there could be several receivers at an item line level and therefore it should be optional)
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thnak", "systema.ncts.export.header.error.null.thnak"); 
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtink", "systema.ncts.export.header.error.null.thtink"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thadk1", "systema.ncts.export.header.error.null.thadk1"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thpnk", "systema.ncts.export.header.error.null.thpnk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thpsk", "systema.ncts.export.header.error.null.thpsk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thlkk", "systema.ncts.export.header.error.null.thlkk"); 
		
		//ansvarig
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thnaa", "systema.ncts.export.header.error.null.thnaa"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtina", "systema.ncts.export.header.error.null.thtina"); 
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thada1", "systema.ncts.export.header.error.null.thada1"); 
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thpna", "systema.ncts.export.header.error.null.thpna"); 
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thpsa", "systema.ncts.export.header.error.null.thpsa"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thlka", "systema.ncts.export.header.error.null.thlka"); 
		
		//header
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thdk", "systema.ncts.export.header.error.null.thdk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thavd", "systema.ncts.export.header.error.null.thavd"); 
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtdn", "systema.ncts.export.header.error.null.thtdn");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thsg", "systema.ncts.export.header.error.null.thsg");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thdst", "systema.ncts.export.header.error.null.thdst");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thdsk", "systema.ncts.export.header.error.null.thdsk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtsb", "systema.ncts.export.header.error.null.thtsb");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thalk", "systema.ncts.export.header.error.null.thalk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thblk", "systema.ncts.export.header.error.null.thblk");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtrm", "systema.ncts.export.header.error.null.thtrm");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtrmi", "systema.ncts.export.header.error.null.thtrmi");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtaid", "systema.ncts.export.header.error.null.thtaid");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtalk", "systema.ncts.export.header.error.null.thtalk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtgid", "systema.ncts.export.header.error.null.thtgid");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtglk", "systema.ncts.export.header.error.null.thtglk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thskfd", "systema.ncts.export.header.error.null.thskfd");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thcats", "systema.ncts.export.header.error.null.thcats");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thgkd", "systema.ncts.export.header.error.null.thgkd");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thlsd", "systema.ncts.export.header.error.null.thlsd");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thgadk", "systema.ncts.export.header.error.null.thgadk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thkdc", "systema.ncts.export.header.error.null.thkdc");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thgbl", "systema.ncts.export.header.error.null.thgbl");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thgvk", "systema.ncts.export.header.error.null.thgvk");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thddt", "systema.ncts.export.header.error.null.thddt");
		
		
		
		//Logical controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//-----------------
				//Date validations
				//-----------------
				if(record.getThtrdt()!=null && !"".equals(record.getThtrdt())){
					if(!this.isValidDate(record.getThtrdt())){
						errors.rejectValue("thtrdt", "systema.ncts.export.header.error.rule.thtrdt.invalid.date.format");
						logger.info("ERROR thtrdt");
					}
				}
				if(record.getThddtk()!=null && !"".equals(record.getThddtk())){
					if(!this.isValidDate(record.getThddtk())){
						errors.rejectValue("thddtk", "systema.ncts.export.header.error.rule.thddtk.invalid.date.format");
						logger.info("ERROR thddtk");

					}
				}
				if(record.getThddt()!=null && !"".equals(record.getThddt())){
					if(!this.isValidDate(record.getThddt())){
						errors.rejectValue("thddt", "systema.ncts.export.header.error.rule.thddt.invalid.date.format");
						logger.info("ERROR thddt");
					}
				}
				
				//------------------------------------------------------------------
				//Förseglingsrelaterade fält (all 3 fields must be filled or empty)
				//------------------------------------------------------------------				
				if(record.getThdfkd()!=null && !"".equals(record.getThdfkd())){
					if(record.getThdfsk()==null || "".equals(record.getThdfsk())){
						errors.rejectValue("thdfsk", "systema.ncts.export.header.error.rule.thdfkd.threerelatedfields");
						logger.info("ERROR thdfsk");
					}else if (record.getThdant()==null || "".equals(record.getThdant())){
						errors.rejectValue("thdant", "systema.ncts.export.header.error.rule.thdfkd.threerelatedfields");
						logger.info("ERROR thdant");
					}
				}
				if(record.getThdfsk()!=null && !"".equals(record.getThdfsk())){
					if(record.getThdfkd()==null || "".equals(record.getThdfkd())){
						errors.rejectValue("thdfkd", "systema.ncts.export.header.error.rule.thdfkd.threerelatedfields");
						logger.info("ERROR thdfkd");
					}else if (record.getThdant()==null || "".equals(record.getThdant())){
						errors.rejectValue("thdant", "systema.ncts.export.header.error.rule.thdfkd.threerelatedfields");
						logger.info("ERROR thdant");
					}
				}
				if(record.getThdant()!=null && !"".equals(record.getThdant())){
					if(record.getThdfkd()==null || "".equals(record.getThdfkd())){
						errors.rejectValue("thdfkd", "systema.ncts.export.header.error.rule.thdfkd.threerelatedfields");
						logger.info("ERROR thdfkd");
					}else if (record.getThdfsk()==null || "".equals(record.getThdfsk())){
						errors.rejectValue("thdfsk", "systema.ncts.export.header.error.rule.thdfkd.threerelatedfields");
						logger.info("ERROR thdfsk");
					}
				}
				
				//-----------------
				//Kontrollresultat
				//-----------------
				if(record.getThenkl()!=null && "J".equals(record.getThenkl())){
					if(record.getThdkr()==null || "".equals (record.getThdkr())){
						errors.rejectValue("thdkr", "systema.ncts.export.header.error.null.thdkr");
						logger.info("ERROR thdkr");
					}
				}
				
				//Transitkontor
				if(record.getThdkr()!=null && "A3".equals(record.getThdkr())){
					if( record.getThalk()!=null && record.getThblk()!=null){
						if(record.getThalk().equals(record.getThblk())){
							//nothing
						}else{
							if(record.getThtsd1()==null || "".equals (record.getThtsd1())){
								errors.rejectValue("thtsd1", "systema.ncts.export.header.error.rule.thtsd1.atleastone");
								logger.info("ERROR thtsd1");
							}
						}
					}
				}
				
				//--------
				//Garanti
				//--------
				if(record.getThgft1()==null || "".equals(record.getThgft1())){
					if(record.getThgft2()==null || "".equals (record.getThgft2())){
						errors.rejectValue("thgft1", "systema.ncts.export.header.error.rule.thgft1.atleastone");
						logger.info("ERROR thgft1");
					}
				}
				
				//------------------------------------------------------------------------------------------
				//Garantee calculation between end-user (thgbl vs the actual calculation in all item lines) 
				//------------------------------------------------------------------------------------------
				logger.warn("comparedValue:" + record.getComparedGuaranteeValue());
				logger.warn("guiAmount:"+ bigDecimalFormatter.getBigDecimalIntegerPart(record.getThgbl()));
				logger.warn("calculatedAmount:"+ record.getCalculatedGuaranteeAmount());
				
				if(record.getComparedGuaranteeValue()==0){
					//OK
				}else{
					Integer guiAmount = bigDecimalFormatter.getBigDecimalIntegerPart(record.getThgbl());
					Integer calculatedAmount = record.getCalculatedGuaranteeAmount();
					
					if(calculatedAmount == 0){
						if(guiAmount > -5 && guiAmount < 5){
							//OK meaning that this is newly created and the user has not fetched the correct calculation. Usually when the oppdrag has been newly created
						}	
					}else{
						if(guiAmount > -5 && guiAmount < 5){
							errors.rejectValue("thgbl", "systema.ncts.export.header.error.rule.thgbl.invalid");
						}
					}
				}
				
				//Garantikoder
				String errMsg = this.isValidGuarantee(record);
				if(errMsg!=null){
					errors.reject("thgft1", "Garantifel: " + errMsg);
				}
				
				//Begränsnings land
				if("1".equals(record.getThgbgi())){
					if(record.getThgbgu()==null || "".equals(record.getThgbgu())){
						errors.rejectValue("thgbgu", "systema.ncts.export.header.error.rule.thgbgu.countrycode");
					}
				}
				
				//Godslokalkod
				if(!"".equals(record.getThlsd())){
					if(!"".equals(record.getThlasd())){
						//OK
					}else{
						errors.rejectValue("thlasd", "systema.ncts.export.header.error.rule.thlasd");
					}
				}
			
			

				
			}
		}
		
	}
	
	/**
	 * 
	 * @param rawValue
	 * @return
	 */
	private boolean isValidDate(String rawValue){
		boolean retval = false;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		formatter.setLenient(false); //in order to put logical control for month
		try{
			Date tmp = formatter.parse(rawValue);
			retval = true;
		}catch(Exception e){
			//nothing
		}
		return retval;
	}
	
	/**
	 * Checks if the Guarantee number (thgft1) is ok together with its Guarantee code (thgadk)
	 * 
	 * @param record
	 * @return
	 */
	private String isValidGuarantee(JsonNctsExportSpecificTopicRecord record){
		String method = "isValidGuarantee";
	 	logger.info("Inside " + method);
	 	String retval = null;
	 	
	 	logger.info("VALIDATION of Guarantee...");
		//---------------------------
		//get BASE URL = RPG-PROGRAM
		//---------------------------
		String BASE_URL = UrlDataStore.NCTS_EXPORT_BASE_VALIDATE_SPECIFIC_TOPIC_GUARRANTEE_URL;
		//url params
		String urlRequestParamsKeys = this.getRequestUrlKeyParametersForGuaranteeValidation(record.getApplicationUser(),record.getThgft1(),record.getThgadk() );
		//for debug purposes in GUI
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + BASE_URL);
		logger.info("URL PARAMS: " + urlRequestParamsKeys);
		//--------------------------------------
		//EXECUTE the FETCH (RPG program) here
		//--------------------------------------
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//Debug --> 
		logger.info(method + " --> jsonPayload:" + jsonPayload);
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	
		if(jsonPayload!=null){
			JsonNctsExportSpecificTopicGuaranteeValidatorContainer container = this.nctsExportSpecificTopicService.getNctsExportSpecificTopicGuaranteeValidatorContainer(jsonPayload);
			if(container!=null){
				if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
					logger.info("######################################");
					logger.info("[ERROR] on Guarantee: " + container.getErrMsg());
					logger.info("######################################");
					retval = container.getErrMsg();
				}
			}
		}
		return retval;
	}
	/**
	   * Validate guarantee dependency fields
	   * 
	   * @param applicationUser
	   * @param guaranteeNumber
	   * @param guaranteeCode
	   * @return
	   */
	  private String getRequestUrlKeyParametersForGuaranteeValidation(String applicationUser,String guaranteeNumber,String guaranteeCode){
		  StringBuffer sb = new StringBuffer();
		  final String USER_TEST = "OSCAR"; //Special for test
		  if("Oscar".equalsIgnoreCase(applicationUser)){
			  sb.append("user=" + USER_TEST);
		  }else{
			  sb.append("user=" + applicationUser);
		  }
		  sb.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thgft1=" + guaranteeNumber );
		  sb.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thgadk=" + guaranteeCode );
		  return sb.toString();
	  }
	  
}
