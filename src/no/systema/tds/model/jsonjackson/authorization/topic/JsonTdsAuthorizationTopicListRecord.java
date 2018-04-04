/**
 * 
 */
package no.systema.tds.model.jsonjackson.authorization.topic;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author oscardelatorre
 * @date June 19, 2013
 * 
 */

public class JsonTdsAuthorizationTopicListRecord {
	private final String TDS_EXPORT = "TDS Export";
	private final String TDS_IMPORT = "TDS Import";
	private final String NCTS_EXPORT = "NCTS Export";
	private final String NCTS_IMPORT = "NCTS Import";
	
	private String syav = null;
	public void setSyav(String value){ this.syav = value;}
	public String getSyav(){ return this.syav; }
	
	private String syop = null;
	public void setSyop(String value){ this.syop = value;}
	public String getSyop(){ return this.syop; }
	
	private String sysg = null;
	public void setSysg(String value){ this.sysg = value;}
	public String getSysg(){ return this.sysg; }
	
	private String urspDecoded = null;
	public String getUrspDecoded(){ 
		String retval = this.ursp;
		if("TE".equals(this.ursp)){
			retval = this.TDS_EXPORT;
		}else if("TI".equals(this.ursp)){
			retval = this.TDS_IMPORT;			
		}else if("NE".equals(this.ursp)){
			retval = this.NCTS_EXPORT;
		}else if("NI".equals(this.ursp)){
			retval = this.NCTS_IMPORT;
		}
			
		return retval; 
	}
	
	private String ursp = null;
	public void setUrsp(String value){ this.ursp = value;}
	public String getUrsp(){ return  this.ursp; }
		
	private String syst = null;
	public void setSyst(String value){ this.syst = value;}
	public String getSyst(){ return this.syst; }
	
	private String refid = null;
	public void setRefid(String value){ this.refid = value;}
	public String getRefid(){ return this.refid; }
	
	private String url = null;
	public void setUrl(String value){ this.url = value;}
	public String getUrl(){ return this.url; }
	
	private String sydt = null;
	public void setSydt(String value){ this.sydt = value;}
	public String getSydt(){ return this.sydt; }
	
	private String mtyp = null;
	public void setMtyp(String value){ this.mtyp = value;}
	public String getMtyp(){ return this.mtyp; }
	
	private String namn = null;
	public void setNamn(String value){ this.namn = value;}
	public String getNamn(){ return this.namn; }
	
	/**
	 * Used for java reflection in other classes
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
