/**
 * 
 */
package no.systema.tds.tdsexport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author oscardelatorre
 * @date Jul 05, 2017
 *
 */
public class JsonTdsExportTopicZemListRecord extends JsonAbstractGrandFatherRecord {
	
	private String svzh_mrnn = null;
	public void setSvzh_mrnn(String value) {  this.svzh_mrnn = value; }
	public String getSvzh_mrnn() { return this.svzh_mrnn;}
	
	private String svzh_tuid = null;
	public void setSvzh_tuid(String value) {  this.svzh_tuid = value; }
	public String getSvzh_tuid() { return this.svzh_tuid;}
	
	private String svzh_utdt = null;
	public void setSvzh_utdt(String value) {  this.svzh_utdt = value; }
	public String getSvzh_utdt() { return this.svzh_utdt;}
	
	private String svzh_avna = null;
	public void setSvzh_avna(String value) {  this.svzh_avna = value; }
	public String getSvzh_avna() { return this.svzh_avna;}
	
	private String svzh_mona = null;
	public void setSvzh_mona(String value) {  this.svzh_mona = value; }
	public String getSvzh_mona() { return this.svzh_mona;}
	
	private String svzh_molk = null;
	public void setSvzh_molk(String value) {  this.svzh_molk = value; }
	public String getSvzh_molk() { return this.svzh_molk;}

	private String pdfh = null;
	public void setPdfh(String value) {  this.pdfh = value; }
	public String getPdfh() { return this.pdfh;}
	
	//gets the file name without any path
	private String pdfhName = null;
	public String getPdfhName() {
		this.pdfhName = this.pdfh; //default
		if(this.pdfh!=null){
			int i = this.pdfh.lastIndexOf("/");
			this.pdfhName = this.pdfh.substring( i+1 );
		}
		return this.pdfhName;
	}

	private String pdfv = null;
	public void setPdfv(String value) {  this.pdfv = value; }
	public String getPdfv() { return this.pdfv;}
	
	//gets the file name without any path
	private String pdfvName = null;
	public String getPdfvName() {
		this.pdfvName = this.pdfv; //default
		if(this.pdfv!=null){
			int i = this.pdfv.lastIndexOf("/");
			this.pdfvName = this.pdfv.substring( i+1 );
		}
		return this.pdfvName;
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
