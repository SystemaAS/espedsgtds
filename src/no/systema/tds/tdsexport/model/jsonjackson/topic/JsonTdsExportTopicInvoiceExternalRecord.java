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
public class JsonTdsExportTopicInvoiceExternalRecord extends JsonAbstractGrandFatherRecord {
	private MainDateFormatter dateFormatter = new MainDateFormatter();
	
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

	private String svef_fabl = null;
	public void setSvef_fabl(String value) {  this.svef_fabl = value; }
	public String getSvef_fabl() { return this.svef_fabl;}
	
	private Double svef_fablDbl = 0.00D;
	public Double getSvef_fablDbl() { 
		if(this.svef_fabl!=null){
			try{
				this.svef_fablDbl = Double.parseDouble(this.svef_fabl.replace(",", "."));
			}catch(Exception e){
				//nothing
			}
		}
		return this.svef_fablDbl;
	}
	
	
	private String svef_unik = null;
	public void setSvef_unik(String value) {  this.svef_unik = value; }
	public String getSvef_unik() { return this.svef_unik;}
	
	private String svef_reff = null;
	public void setSvef_reff(String value) {  this.svef_reff = value; }
	public String getSvef_reff() { return this.svef_reff;}
	
	//Tuid for Proforma
	private String svef_tuid = null;
	public void setSvef_tuid(String value) {  this.svef_tuid = value; }
	public String getSvef_tuid() { return this.svef_tuid;}
		
	
	
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
