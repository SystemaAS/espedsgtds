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
 * @date Oct 26, 2015
 *
 */
public class JsonTdsImportTopicInvoiceExternalRecord extends JsonAbstractGrandFatherRecord {
	private MainDateFormatter dateFormatter = new MainDateFormatter();
	
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

	private String svif_fabl = null;
	public void setSvif_fabl(String value) {  this.svif_fabl = value; }
	public String getSvif_fabl() { return this.svif_fabl;}
	
	private Double svif_fablDbl = 0.00D;
	public Double getSvif_fablDbl() { 
		if(this.svif_fabl!=null){
			try{
				this.svif_fablDbl = Double.parseDouble(this.svif_fabl.replace(",", "."));
			}catch(Exception e){
				//nothing
			}
		}
		return this.svif_fablDbl;
	}
	
	
	private String svif_unik = null;
	public void setSvif_unik(String value) {  this.svif_unik = value; }
	public String getSvif_unik() { return this.svif_unik;}
	
	private String svif_reff = null;
	public void setSvif_reff(String value) {  this.svif_reff = value; }
	public String getSvif_reff() { return this.svif_reff;}
	
	
	
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
