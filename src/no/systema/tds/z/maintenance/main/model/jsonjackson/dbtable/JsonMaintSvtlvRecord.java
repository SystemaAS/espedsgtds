package no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.main.util.StringManager;

/**
 * 
 *@author oscardelatorre
 *@date Jun 3 2017
 */
public class JsonMaintSvtlvRecord extends JsonAbstractGrandFatherRecord  {
	private StringManager strMgr = new StringManager();
	private final String CODE_N = "N";
	private final String CODE_J = "J";
	private final String CODE_P = "P";
	
	private final String CODE_NEJ = "Nej";
	private final String CODE_JA = "Ja";
	private final String CODE_PROCENT = "Procent";
	
	
	private String svlv_kd = null;    //key                        
	public void setSvlv_kd (String value){ this.svlv_kd = value;   }   
	public String getSvlv_kd (){ return this.svlv_kd;   }  
	
	private String svlv_tr = null;                                
	public void setSvlv_tr (String value){ this.svlv_tr = value;   }   
	public String getSvlv_tr (){ return this.svlv_tr;   }
	//for gui purposes
	private String svlv_trText = null;
	public String getSvlv_trText (){
		if(strMgr.isNotNull(this.svlv_tr)){
			if(this.CODE_N.equals(svlv_tr)){
				this.svlv_trText = this.CODE_NEJ;
			}else if(this.CODE_J.equals(svlv_tr)){
				this.svlv_trText = this.CODE_JA;
			}
		}
		return this.svlv_trText;   
	}  
	
	private String svlv_tr2 = null;                                
	public void setSvlv_tr2 (String value){ this.svlv_tr2 = value;   }   
	public String getSvlv_tr2 (){ return this.svlv_tr2;   } 
	//for gui purposes
	private String svlv_tr2Text = null;
	public String getSvlv_tr2Text (){
		if(strMgr.isNotNull(this.svlv_tr2)){
			if(this.CODE_N.equals(svlv_tr2)){
				this.svlv_tr2Text = this.CODE_NEJ;
			}else if(this.CODE_J.equals(svlv_tr2)){
				this.svlv_tr2Text = this.CODE_JA;
			}
		}
		return this.svlv_tr2Text;   
	}  
	
	private String svlv_fs = null;                                
	public void setSvlv_fs (String value){ this.svlv_fs = value;   }   
	public String getSvlv_fs (){ return this.svlv_fs;   }  
	//for gui purposes
	private String svlv_fsText = null;
	public String getSvlv_fsText (){
		if(strMgr.isNotNull(this.svlv_fs)){
			if(this.CODE_N.equals(svlv_fs)){
				this.svlv_fsText = this.CODE_NEJ;
			}else if(this.CODE_J.equals(svlv_fs)){
				this.svlv_fsText = this.CODE_JA;
			}else if(this.CODE_P.equals(svlv_fs)){
				this.svlv_fsText = this.CODE_PROCENT;
			}
		}
		return this.svlv_fsText;   
	}  
	
	private String svlv_fsp = null;                                
	public void setSvlv_fsp (String value){ this.svlv_fsp = value;   }   
	public String getSvlv_fsp (){ return this.svlv_fsp;   }
	public String getSvlv_fspFormatted (){ 
		if(this.svlv_fsp!=null){
			this.svlv_fsp = this.svlv_fsp.replace(".", ",");
		}
		return this.svlv_fsp;  
	}
	
	private String svlv_fs2 = null;                                
	public void setSvlv_fs2 (String value){ this.svlv_fs2 = value;   }   
	public String getSvlv_fs2 (){ return this.svlv_fs2;   }  
	//for gui purposes
	private String svlv_fs2Text = null;
	public String getSvlv_fs2Text (){
		if(strMgr.isNotNull(this.svlv_fs2)){
			if(this.CODE_N.equals(svlv_fs2)){
				this.svlv_fs2Text = this.CODE_NEJ;
			}else if(this.CODE_J.equals(svlv_fs2)){
				this.svlv_fs2Text = this.CODE_JA;
			}else if(this.CODE_P.equals(svlv_fs2)){
				this.svlv_fs2Text = this.CODE_PROCENT;
			}
		}
		return this.svlv_fs2Text;   
	} 
	
	private String svlv_fs2p = null;                                
	public void setSvlv_fs2p (String value){ this.svlv_fs2p = value;   }   
	public String getSvlv_fs2p (){ return this.svlv_fs2p;   }  
	public String getSvlv_fs2pFormatted (){ 
		if(this.svlv_fs2p!=null){
			this.svlv_fs2p = this.svlv_fs2p.replace(".", ",");
		}
		return this.svlv_fs2p;  
	}
	
	private String svlv_ok = null;                                
	public void setSvlv_ok (String value){ this.svlv_ok = value;   }   
	public String getSvlv_ok (){ return this.svlv_ok;   }  
	//for gui purposes
	private String svlv_okText = null;
	public String getSvlv_okText (){
		if(strMgr.isNotNull(this.svlv_ok)){
			if(this.CODE_N.equals(svlv_ok)){
				this.svlv_okText = this.CODE_NEJ;
			}else if(this.CODE_J.equals(svlv_ok)){
				this.svlv_okText = this.CODE_JA;
			}
		}
		return this.svlv_okText;   
	}  
	
	private String svlv_kr = null;                                
	public void setSvlv_kr (String value){ this.svlv_kr = value;   }   
	public String getSvlv_kr (){ return this.svlv_kr;   } 
	//for gui purposes
	private String svlv_krText = null;
	public String getSvlv_krText (){
		if(strMgr.isNotNull(this.svlv_kr)){
			if(this.CODE_N.equals(svlv_kr)){
				this.svlv_krText = this.CODE_NEJ;
			}else if(this.CODE_J.equals(svlv_kr)){
				this.svlv_krText = this.CODE_JA;
			}
		}
		return this.svlv_krText;   
	}  
	
	private String svlv_ar = null;                                
	public void setSvlv_ar (String value){ this.svlv_ar = value;   }   
	public String getSvlv_ar (){ return this.svlv_ar;   } 
	//for gui purposes
	private String svlv_arText = null;
	public String getSvlv_arText (){
		if(strMgr.isNotNull(this.svlv_ar)){
			if(this.CODE_N.equals(svlv_ar)){
				this.svlv_arText = this.CODE_NEJ;
			}else if(this.CODE_J.equals(svlv_ar)){
				this.svlv_arText = this.CODE_JA;
			}
		}
		return this.svlv_arText;   
	}  
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
