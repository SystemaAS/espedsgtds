/**
 * 
 */
package no.systema.tds.tdsexport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.main.util.MainDateFormatter;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author oscardelatorre
 * @date Oct 26, 2015
 *
 */
public class JsonTdsExportTopicInvoiceRecord extends JsonAbstractGrandFatherRecord {
	private MainDateFormatter dateFormatter = new MainDateFormatter();
	
	//This is to know if the invoice line is new or existent (update). We don't have any other line nr. or id to know whether it is an
	//update or a create new
	private String isModeUpdate = null;
	public void setIsModeUpdate(String value) {  this.isModeUpdate = value; }
	public String getIsModeUpdate() {return this.isModeUpdate;}
	
	private String debugPrintlnAjax = null;
	public void setDebugPrintlnAjax(String value) {  this.debugPrintlnAjax = value; }
	public String getDebugPrintlnAjax() {return this.debugPrintlnAjax;}
	
	private String svef_syav = null;
	public void setSvef_syav(String value) {  this.svef_syav = value; }
	public String getSvef_syav() { return this.svef_syav;}
	
	private String svef_syop = null;
	public void setSvef_syop(String value) {  this.svef_syop = value; }
	public String getSvef_syop() { return this.svef_syop;}
	
	private String svef_faty = null;
	public void setSvef_faty(String value) {  this.svef_faty = value; }
	public String getSvef_faty() { return this.svef_faty;}
	
	private String svef_fatx = null;
	public void setSvef_fatx(String value) {  this.svef_fatx = value; }
	public String getSvef_fatx() { return this.svef_fatx;}
	
	private String svef_vakd = null;
	public void setSvef_vakd(String value) { this.svef_vakd = value; }
	public String getSvef_vakd() { return this.svef_vakd; }
	
	private String svef_vaku = null;
	public void setSvef_vaku(String value) {  this.svef_vaku = value; }
	public String getSvef_vaku() { return this.svef_vaku;}

	private Double svef_vakuDbl = 0.00D;
	public Double getSvef_vakuDbl() { 
		if(this.svef_vaku!=null){
			try{
				this.svef_vakuDbl = Double.parseDouble(this.svef_vaku.replace(",", "."));
			}catch(Exception e){
				//nothing
			}
		}
		return this.svef_vakuDbl;
	}
	
	private String svef_fabl = null;
	public void setSvef_fabl(String value) {  this.svef_fabl = value; }
	public String getSvef_fabl() { return this.svef_fabl;}
	
	private Double svef_fablDbl = 0.00D;
	public Double getSfkr28Dbl() { 
		if(this.svef_fabl!=null){
			try{
				this.svef_fablDbl = Double.parseDouble(this.svef_fabl.replace(",", "."));
			}catch(Exception e){
				//nothing
			}
		}
		return this.svef_fablDbl;
	}
	
	//None database field (internal)
	private String svef_omr = null;
	public void setSvef_omr(String value) {  this.svef_omr = value; }
	public String getSvef_omr() { return this.svef_omr;}
	
	private Integer svef_omrInt = 1;
	public Integer getSvef_omrInt() { 
		if(this.svef_omr!= null){
			try{
				svef_omrInt = Integer.parseInt(this.svef_omr);
			}catch(Exception e){
				//nothing
			}
		}
		return this.svef_omrInt;
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
