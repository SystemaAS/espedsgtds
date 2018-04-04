/**
 * 
 */
package no.systema.tds.tdsexport.util;

import java.util.*;

import no.systema.tds.tdsexport.controller.TdsExportController;

import org.apache.log4j.Logger;

/**
 * The class evaluates return codes from RPG operations.
 * 
 * @author oscardelatorre
 * @date Jan 18, 2013
 */
public class RpgReturnResponseHandler {
	private static final Logger logger = Logger.getLogger(RpgReturnResponseHandler.class.getName());
	
	
	private String errorMessage = null;
	public void setErrorMessage(String value){ this.errorMessage=value;  }
	public String getErrorMessage(){ return this.errorMessage;  }
	
	private String user = null;
	public void setUser(String value){ this.user=value;  }
	public String getUser(){ return this.user;  }
	
	private String sveh_syop = null;
	public void setSveh_syop(String value){ this.sveh_syop=value;  }
	public String getSveh_syop(){ return this.sveh_syop;  }
	
	private String sveh_tuid = null;
	public void setSveh_tuid(String value){ this.sveh_tuid=value;  }
	public String getSveh_tuid(){ return this.sveh_tuid;  }
	
	//Ombud fields
	private String sveh_omeo = null;
	public void setSveh_omeo(String value){ this.sveh_omeo=value;  }
	public String getSveh_omeo(){ return this.sveh_omeo;  }
	
	private String sveh_omha = null;	
	public void setSveh_omha(String value){ this.sveh_omha=value;  }
	public String getSveh_omha(){ return this.sveh_omha;  }
	
	private String sveh_omty = null;
	public void setSveh_omty(String value){ this.sveh_omty=value;  }
	public String getSveh_omty(){ return this.sveh_omty;  }
	
	private String sveh_omtl = null;
	public void setSveh_omtl(String value){ this.sveh_omtl=value;  }
	public String getSveh_omtl(){ return this.sveh_omtl;  }
	
	//Item lines key records (line nr, opd, etc)
	private String svev_syli = null;
	public void setSvev_syli(String value){ this.svev_syli=value;  }
	public String getSvev_syli(){ return this.svev_syli;  }
	
	private String svev_syop = null;
	public void setSvev_syop(String value){ this.svev_syop=value;  }
	public String getSvev_syop(){ return this.svev_syop;  }
	
	
	
	/**
	 * Sets the error message code after an RPG-call been made by an HTML-POST request on a topic (arende) update
	 * 
	 * @param rpgRawReturnPayload
	 * @return
	 */
	public void evaluateRpgResponseOnTopicUpdate(String rpgRawResponsePayload){
		
		if(rpgRawResponsePayload!=null){
			String tmp = rpgRawResponsePayload.replaceAll("\"", "");
			String tmp2 = tmp.replace("{", "");
			String cleanRawPayload = tmp2.replace("}", "");
			
			String[] record = cleanRawPayload.split(",");
			List <String>list = Arrays.asList(record);
			for(String field: list){
				//logger.info(field);
				String[] keyValuePair = field.split(":");
				if(keyValuePair[0]!=null){
					if(keyValuePair[0].trim().equalsIgnoreCase("errMsg")){
						if(keyValuePair.length>1){
							String errorCode = keyValuePair[1];
							if(errorCode!=null && !"".equals(errorCode.trim())){
								this.errorMessage = errorCode ;
								logger.info(this.errorMessage);
							}
						}
					}else if(keyValuePair[0].trim().equalsIgnoreCase("user")){
						this.user = keyValuePair[0] + ":" + keyValuePair[1] + ",";
					}else if(keyValuePair[0].trim().equalsIgnoreCase("sveh_syop")){
						this.sveh_syop = keyValuePair[0] + ":" + keyValuePair[1]+ ",";
					}
				}
			}
		}
		
		
	}
	
	/**
	 * Gets a seed (counter) required for an INSERT
	 * 
	 * @param rpgRawResponsePayload
	 */
	public void getNewSeedsOpdAndTuidRequiredForCreateNewTopic(String rpgRawResponsePayload){
		Map map = new HashMap(); 
		if(rpgRawResponsePayload!=null){
			String tmp = rpgRawResponsePayload.replaceAll("\"", "");
			String tmp2 = tmp.replace("{", "");
			String cleanRawPayload = tmp2.replace("}", "");
			
			String[] record = cleanRawPayload.split(",");
			List <String>list = Arrays.asList(record);
			for(String field: list){
				//logger.info(field);
				String[] keyValuePair = field.split(":");
				if(keyValuePair[0]!=null){
					if(keyValuePair[0].trim().equalsIgnoreCase("SVEH_SYOP")){
						if(keyValuePair.length>1){
							if(keyValuePair[1]!=null && !"".equals(keyValuePair[1])){
								this.sveh_syop = keyValuePair[1];
								
							}
						}
					}
					if(keyValuePair[0].trim().equalsIgnoreCase("SVEH_TUID")){
						if(keyValuePair.length>1){
							if(keyValuePair[1]!=null && !"".equals(keyValuePair[1])){
								this.sveh_tuid = keyValuePair[1];
								
							}
						}
					}
					if(keyValuePair[0].trim().equalsIgnoreCase("SVEA_OMEO")){
						if(keyValuePair.length>1){
							if(keyValuePair[1]!=null && !"".equals(keyValuePair[1])){
								this.sveh_omeo = keyValuePair[1];
								
							}
						}
					}
					if(keyValuePair[0].trim().equalsIgnoreCase("SVTH_NAMN")){
						if(keyValuePair.length>1){
							if(keyValuePair[1]!=null && !"".equals(keyValuePair[1])){
								this.sveh_omha = keyValuePair[1];
								
							}
						}
					}
					if(keyValuePair[0].trim().equalsIgnoreCase("SVEA_OMTL")){
						if(keyValuePair.length>1){
							if(keyValuePair[1]!=null && !"".equals(keyValuePair[1])){
								this.sveh_omtl = keyValuePair[1];
								
							}
						}
					}
					if(keyValuePair[0].trim().equalsIgnoreCase("SVEA_OMTY")){
						if(keyValuePair.length>1){
							if(keyValuePair[1]!=null && !"".equals(keyValuePair[1])){
								this.sveh_omty = keyValuePair[1];
								
							}
						}
					}
					if(keyValuePair[0].trim().equalsIgnoreCase("errMsg")){
						if(keyValuePair.length>1){
							String errorCode = keyValuePair[1];
							if(errorCode!=null && !"".equals(errorCode.trim())){
								this.errorMessage = errorCode ;
								logger.info(this.errorMessage);
							}
						}
					}
				}
			}
		}	
	}
	
	/**
	 * Evaluates the creation of a topic item line (add)
	 * @param rpgRawResponsePayload
	 */
	public void evaluateRpgResponseOnTopicItemCreateOrUpdate(String rpgRawResponsePayload){
		
		if(rpgRawResponsePayload!=null){
			String tmp = rpgRawResponsePayload.replaceAll("\"", "");
			String tmp2 = tmp.replace("{", "");
			String cleanRawPayload = tmp2.replace("}", "");
			
			String[] record = cleanRawPayload.split(",");
			List <String>list = Arrays.asList(record);
			for(String field: list){
				//logger.info(field);
				String[] keyValuePair = field.split(":");
				if(keyValuePair[0]!=null){
					if(keyValuePair[0].trim().equalsIgnoreCase("errMsg")){
						if(keyValuePair.length>1){
							String errorCode = keyValuePair[1];
							if(errorCode!=null && !"".equals(errorCode.trim())){
								this.errorMessage = errorCode ;
								logger.info(this.errorMessage);
							}
						}
					}else if(keyValuePair[0].trim().equalsIgnoreCase("user")){
						this.user = keyValuePair[1];
					}else if(keyValuePair[0].trim().equalsIgnoreCase("svev_syop")){
						this.svev_syop = keyValuePair[1];
					}else if(keyValuePair[0].trim().equalsIgnoreCase("svev_syli")){
						this.svev_syli = keyValuePair[1];
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param rpgRawResponsePayload
	 */
	public void evaluateRpgResponseOnTopicInvoiceCreateOrUpdate(String rpgRawResponsePayload){
		
		if(rpgRawResponsePayload!=null){
			String tmp = rpgRawResponsePayload.replaceAll("\"", "");
			String tmp2 = tmp.replace("{", "");
			String cleanRawPayload = tmp2.replace("}", "");
			
			String[] record = cleanRawPayload.split(",");
			List <String>list = Arrays.asList(record);
			for(String field: list){
				//logger.info(field);
				String[] keyValuePair = field.split(":");
				if(keyValuePair[0]!=null){
					if(keyValuePair[0].trim().equalsIgnoreCase("errMsg")){
						if(keyValuePair.length>1){
							String errorCode = keyValuePair[1];
							if(errorCode!=null && !"".equals(errorCode.trim())){
								this.errorMessage = errorCode ;
								logger.info(this.errorMessage);
							}
						}
					}else if(keyValuePair[0].trim().equalsIgnoreCase("user")){
						this.user = keyValuePair[1];
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param rpgRawResponsePayload
	 */
	public void evaluateRpgResponseOnItemKundensVarRegisterUpdate(String rpgRawResponsePayload){
		
		if(rpgRawResponsePayload!=null){
			String tmp = rpgRawResponsePayload.replaceAll("\"", "");
			String tmp2 = tmp.replace("{", "");
			String cleanRawPayload = tmp2.replace("}", "");
			
			String[] record = cleanRawPayload.split(",");
			List <String>list = Arrays.asList(record);
			for(String field: list){
				//logger.info(field);
				String[] keyValuePair = field.split(":");
				if(keyValuePair[0]!=null){
					if(keyValuePair[0].trim().equalsIgnoreCase("errMsg")){
						if(keyValuePair.length>1){
							String errorCode = keyValuePair[1];
							if(errorCode!=null && !"".equals(errorCode.trim())){
								this.errorMessage = errorCode ;
								logger.info(this.errorMessage);
							}
						}
					}else if(keyValuePair[0].trim().equalsIgnoreCase("user")){
						this.user = keyValuePair[1];
					}
				}
			}
		}
	}
}
