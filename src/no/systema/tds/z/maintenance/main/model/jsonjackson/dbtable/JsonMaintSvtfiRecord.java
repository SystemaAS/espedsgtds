package no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date May 02, 2017
 * 
 */
public class JsonMaintSvtfiRecord extends JsonAbstractGrandFatherRecord  {
	
	private boolean validSenderId = false;                                
	public void setValidSenderId (boolean value){ this.validSenderId = value;   }   
	public boolean isValidSenderId (){ return this.validSenderId;   }  
	
	private boolean validReceiverId = false;                                
	public void setValidReceiverId (boolean value){ this.validReceiverId = value;   }   
	public boolean isValidReceiverId (){ return this.validReceiverId;   }  
	
	private boolean validSmsUserId = false;                                
	public void setValidSmsUserId (boolean value){ this.validSmsUserId = value;   }   
	public boolean isValidSmsUserId (){ return this.validSmsUserId;   }  
	
	private String svtf_sigm = null;                                
	public void setSvtf_sigm (String value){ this.svtf_sigm = value;   }   
	public String getSvtf_sigm (){ return this.svtf_sigm;   }  
	
	private String svtf_0004 = null;                                
	public void setSvtf_0004 (String value){ this.svtf_0004 = value;   }   
	public String getSvtf_0004 (){ return this.svtf_0004;   }  
	
	private String svtf_0008 = null;                                
	public void setSvtf_0008 (String value){ this.svtf_0008 = value;   }   
	public String getSvtf_0008 (){ return this.svtf_0008;   }  
	
	private String svtf_0010 = null;                                
	public void setSvtf_0010 (String value){ this.svtf_0010 = value;   }   
	public String getSvtf_0010 (){ return this.svtf_0010;   }  
	
	private String svtf_0014 = null;                                
	public void setSvtf_0014 (String value){ this.svtf_0014 = value;   }   
	public String getSvtf_0014 (){ return this.svtf_0014;   }  
	
	private String svtf_0022 = null;                                
	public void setSvtf_0022 (String value){ this.svtf_0022 = value;   }   
	public String getSvtf_0022 (){ return this.svtf_0022;   }  
	
	private String svtf_0035 = null;                                
	public void setSvtf_0035 (String value){ this.svtf_0035 = value;   }   
	public String getSvtf_0035 (){ return this.svtf_0035;   }  
	
	private String svtf_pref = null;                                
	public void setSvtf_pref (String value){ this.svtf_pref = value;   }   
	public String getSvtf_pref (){ return this.svtf_pref;   }  
	
	private String svtf_numb = null;                                
	public void setSvtf_numb (String value){ this.svtf_numb = value;   }   
	public String getSvtf_numb (){ return this.svtf_numb;   }  
	
	private String svtf_kval = null;                                
	public void setSvtf_kval (String value){ this.svtf_kval = value;   }   
	public String getSvtf_kval (){ return this.svtf_kval;   }  
	
	private String svtf_sec1 = null;                                
	public void setSvtf_sec1 (String value){ this.svtf_sec1 = value;   }   
	public String getSvtf_sec1 (){ return this.svtf_sec1;   }  
	
	private String svtf_sec2 = null;                                
	public void setSvtf_sec2 (String value){ this.svtf_sec2 = value;   }   
	public String getSvtf_sec2 (){ return this.svtf_sec2;   }  
	
	
	private String svtf_cer1 = null;                                
	public void setSvtf_cer1 (String value){ this.svtf_cer1 = value;   }   
	public String getSvtf_cer1 (){ return this.svtf_cer1;   }  
	
	private String svtf_cer2 = null;                                
	public void setSvtf_cer2 (String value){ this.svtf_cer2 = value;   }   
	public String getSvtf_cer2 (){ return this.svtf_cer2;   }  
	
	private String svtf_cer3 = null;                                
	public void setSvtf_cer3 (String value){ this.svtf_cer3 = value;   }   
	public String getSvtf_cer3 (){ return this.svtf_cer3;   }  
	
	private String svtf_cer4 = null;                                
	public void setSvtf_cer4 (String value){ this.svtf_cer4 = value;   }   
	public String getSvtf_cer4 (){ return this.svtf_cer4;   } 
	
	private String svtf_usri = null;                                
	public void setSvtf_usri (String value){ this.svtf_usri = value;   }   
	public String getSvtf_usri (){ return this.svtf_usri;   }  
	
	private String svtf_usra = null;                                
	public void setSvtf_usra (String value){ this.svtf_usra = value;   }   
	public String getSvtf_usra (){ return this.svtf_usra;   }  
	
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}
	
	
}
