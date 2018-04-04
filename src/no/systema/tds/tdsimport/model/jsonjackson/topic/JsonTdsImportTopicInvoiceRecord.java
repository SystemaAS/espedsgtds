/**
 * 
 */
package no.systema.tds.tdsimport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.main.util.MainDateFormatter;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep 24, 2014
 *
 */
public class JsonTdsImportTopicInvoiceRecord extends JsonAbstractGrandFatherRecord {
	private MainDateFormatter dateFormatter = new MainDateFormatter();
	
	//This is to know if the invoice line is new or existent (update). We don't have any other line nr. or id to know whether it is an
	//update or a create new
	private String isModeUpdate = null;
	public void setIsModeUpdate(String value) {  this.isModeUpdate = value; }
	public String getIsModeUpdate() {return this.isModeUpdate;}
	
	private String debugPrintlnAjax = null;
	public void setDebugPrintlnAjax(String value) {  this.debugPrintlnAjax = value; }
	public String getDebugPrintlnAjax() {return this.debugPrintlnAjax;}
	
	private String svif_syav = null;
	public void setSvif_syav(String value) {  this.svif_syav = value; }
	public String getSvif_syav() { return this.svif_syav;}
	
	private String svif_syop = null;
	public void setSvif_syop(String value) {  this.svif_syop = value; }
	public String getSvif_syop() { return this.svif_syop;}
	
	private String svif_faty = null;
	public void setSvif_faty(String value) {  this.svif_faty = value; }
	public String getSvif_faty() { return this.svif_faty;}
	
	private String svif_fatx = null;
	public void setSvif_fatx(String value) {  this.svif_fatx = value; }
	public String getSvif_fatx() { return this.svif_fatx;}
	
	private String svif_vakd = null;
	public void setSvif_vakd(String value) { this.svif_vakd = value; }
	public String getSvif_vakd() { return this.svif_vakd; }
	
	private String svif_vaku = null;
	public void setSvif_vaku(String value) {  this.svif_vaku = value; }
	public String getSvif_vaku() { return this.svif_vaku;}

	private Double svif_vakuDbl = 0.00D;
	public Double getSvif_vakuDbl() { 
		if(this.svif_vaku!=null){
			try{
				this.svif_vakuDbl = Double.parseDouble(this.svif_vaku.replace(",", "."));
			}catch(Exception e){
				//nothing
			}
		}
		return this.svif_vakuDbl;
	}
	private String svif_fabl = null;
	public void setSvif_fabl(String value) {  this.svif_fabl = value; }
	public String getSvif_fabl() { return this.svif_fabl;}
	
	private Double svif_fablDbl = 0.00D;
	public Double getSfkr28Dbl() { 
		if(this.svif_fabl!=null){
			try{
				this.svif_fablDbl = Double.parseDouble(this.svif_fabl.replace(",", "."));
			}catch(Exception e){
				//nothing
			}
		}
		return this.svif_fablDbl;
	}
	
	private String svif_omr = null;
	public void setSvif_omr(String value) { this.svif_omr = value; }
	public String getSvif_omr() { return this.svif_omr;}
	
	
	private Integer svif_omrInt = 1;
	public Integer getSvif_omrInt() { 
		if(this.svif_omr!= null){
			try{
				svif_omrInt = Integer.parseInt(this.svif_omr);
			}catch(Exception e){
				//nothing
			}
		}
		return this.svif_omrInt;
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
