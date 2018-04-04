package no.systema.tds.z.maintenance.tdsnctsexport.model.jsonjackson.dbtable;
import java.io.Serializable;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Jun 23, 2017
 * 
 */
public class JsonMaintSvxghRecord extends JsonAbstractGrandFatherRecord {
	
	private String tgst = null;                                
	public void setTgst (String value){ this.tgst = value;   }   
	public String getTgst (){ return this.tgst;   }  
	
	private String tggnr = null; 
	public void setTggnr (String value){ this.tggnr = value;   }   
	public String getTggnr (){ return this.tggnr;   }              

	private String tggty = "0";
	public void setTggty (String value){ this.tggty = value;   }   
	public String getTggty (){ return this.tggty;   }              

	private String tggvk = null;
	public void setTggvk (String value){ this.tggvk = value;   }   
	public String getTggvk (){ return this.tggvk;   }              

	private String tggbl = "0"; 
	public void setTggbl (String value){ this.tggbl = value;   }   
	public String getTggbl (){ return this.tggbl;   }              

	private String tggblb = "0"; 
	public void setTggblb (String value){ this.tggblb = value;   }   
	public String getTggblb (){ return this.tggblb;   }              

	private String tgkna = "0"; 
	public void setTgkna (String value){ this.tgkna = value;   }   
	public String getTgkna (){ return this.tgkna;   }              

	private String tgtina = null; 
	public void setTgtina (String value){ this.tgtina = value;   }   
	public String getTgtina (){ return this.tgtina;   }              

	private String tgnaa = null; 
	public void setTgnaa (String value){ this.tgnaa = value;   }   
	public String getTgnaa (){ return this.tgnaa;   }              

	private String tgada1 = null; 
	public void setTgada1 (String value){ this.tgada1 = value;   }   
	public String getTgada1 (){ return this.tgada1;   }              

	private String tgpna = null; 
	public void setTgpna (String value){ this.tgpna = value;   }   
	public String getTgpna (){ return this.tgpna;   }              
	
	private String tgpsa = null; 
	public void setTgpsa (String value){ this.tgpsa = value;   }   
	public String getTgpsa (){ return this.tgpsa;   }              
	
	private String tglka = null; 
	public void setTglka (String value){ this.tglka = value;   }   
	public String getTglka (){ return this.tglka;   }              
	
	private String tgtsd = null; 
	public void setTgtsd (String value){ this.tgtsd = value;   }   
	public String getTgtsd (){ return this.tgtsd;   }              
	
	private String tgakny = null; 
	public void setTgakny (String value){ this.tgakny = value;   }   
	public String getTgakny (){ return this.tgakny;   }              
	
	private String tgakgm = null; 
	public void setTgakgm (String value){ this.tgakgm = value;   }   
	public String getTgakgm (){ return this.tgakgm;   }              
	
	private String tgusr = null; 
	public void setTgusr (String value){ this.tgusr = value;   }   
	public String getTgusr (){ return this.tgusr;   }              
	
	
	private String tgdt = "0"; 
	public void setTgdt (String value){ this.tgdt = value;   }   
	public String getTgdt (){ return this.tgdt;   }              
	
	private String tgdtr = "0"; 
	public void setTgdtr (String value){ this.tgdtr = value;   }   
	public String getTgdtr (){ return this.tgdtr;   }              
	
	private String tgprm = "0"; 
	public void setTgprm (String value){ this.tgprm = value;   }   
	public String getTgprm (){ return this.tgprm;   }              
	
	private String tgrn = "0"; 
	public void setTgrn (String value){ this.tgrn = value;   }   
	public String getTgrn (){ return this.tgrn;   }              
	
	private String tggfv = null; 
	public void setTggfv (String value){ this.tggfv = value;   }   
	public String getTggfv (){ return this.tggfv;   }              
		
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
