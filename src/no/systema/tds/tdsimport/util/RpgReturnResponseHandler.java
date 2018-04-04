/**
 * 
 */
package no.systema.tds.tdsimport.util;

import java.util.*;


import org.apache.log4j.Logger;

/**
 * The class evaluates return codes from RPG operations.
 * 
 * @author oscardelatorre
 * @date Sep 2, 2013
 */
public class RpgReturnResponseHandler {
	private static final Logger logger = Logger.getLogger(RpgReturnResponseHandler.class.getName());
	
	
	private String errorMessage = null;
	public void setErrorMessage(String value){ this.errorMessage=value;  }
	public String getErrorMessage(){ return this.errorMessage;  }
	
	private String user = null;
	public void setUser(String value){ this.user=value;  }
	public String getUser(){ return this.user;  }
	
	private String svih_syop = null;
	public void setSvih_syop(String value){ this.svih_syop=value;  }
	public String getSvih_syop(){ return this.svih_syop;  }
	
	private String svih_tuid = null;
	public void setSvih_tuid(String value){ this.svih_tuid=value;  }
	public String getSvih_tuid(){ return this.svih_tuid;  }
	
	//Ombud fields
	private String svih_omeo = null;
	public void setSvih_omeo(String value){ this.svih_omeo=value;  }
	public String getSvih_omeo(){ return this.svih_omeo;  }
	
	private String svih_omha = null;	
	public void setSvih_omha(String value){ this.svih_omha=value;  }
	public String getSvih_omha(){ return this.svih_omha;  }
	
	private String svih_omty = null;
	public void setSvih_omty(String value){ this.svih_omty=value;  }
	public String getSvih_omty(){ return this.svih_omty;  }
	
	private String svih_omtl = null;
	public void setSvih_omtl(String value){ this.svih_omtl=value;  }
	public String getSvih_omtl(){ return this.svih_omtl;  }
	
	//Item lines key records (line nr, opd, etc)
	private String sviv_syli = null;
	public void setSviv_syli(String value){ this.sviv_syli=value;  }
	public String getSviv_syli(){ return this.sviv_syli;  }
	
	private String sviv_syop = null;
	public void setSviv_syop(String value){ this.sviv_syop=value;  }
	public String getSviv_syop(){ return this.sviv_syop;  }
	
	
	
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
					}else if(keyValuePair[0].trim().equalsIgnoreCase("svih_syop")){
						this.svih_syop = keyValuePair[0] + ":" + keyValuePair[1]+ ",";
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
					if(keyValuePair[0].trim().equalsIgnoreCase("SVIH_SYOP")){
						if(keyValuePair.length>1){
							if(keyValuePair[1]!=null && !"".equals(keyValuePair[1])){
								this.svih_syop = keyValuePair[1];
								
							}
						}
					}
					if(keyValuePair[0].trim().equalsIgnoreCase("SVIH_TUID")){
						if(keyValuePair.length>1){
							if(keyValuePair[1]!=null && !"".equals(keyValuePair[1])){
								this.svih_tuid = keyValuePair[1];
								
							}
						}
					}
					if(keyValuePair[0].trim().equalsIgnoreCase("SVIH_OMEO")){
						if(keyValuePair.length>1){
							if(keyValuePair[1]!=null && !"".equals(keyValuePair[1])){
								this.svih_omeo = keyValuePair[1];
								
							}
						}
					}
					if(keyValuePair[0].trim().equalsIgnoreCase("SVTH_NAMN")){
						if(keyValuePair.length>1){
							if(keyValuePair[1]!=null && !"".equals(keyValuePair[1])){
								this.svih_omha = keyValuePair[1];
								
							}
						}
					}
					if(keyValuePair[0].trim().equalsIgnoreCase("SVIA_OMTL")){
						if(keyValuePair.length>1){
							if(keyValuePair[1]!=null && !"".equals(keyValuePair[1])){
								this.svih_omtl = keyValuePair[1];
								
							}
						}
					}
					if(keyValuePair[0].trim().equalsIgnoreCase("SVIA_OMTY")){
						if(keyValuePair.length>1){
							if(keyValuePair[1]!=null && !"".equals(keyValuePair[1])){
								this.svih_omty = keyValuePair[1];
								
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
					}else if(keyValuePair[0].trim().equalsIgnoreCase("sviv_syop")){
						this.sviv_syop = keyValuePair[1];
					}else if(keyValuePair[0].trim().equalsIgnoreCase("sviv_syli")){
						this.sviv_syli = keyValuePair[1];
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
