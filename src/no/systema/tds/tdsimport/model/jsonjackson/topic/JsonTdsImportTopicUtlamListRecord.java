/**
 * 
 */
package no.systema.tds.tdsimport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author oscardelatorre
 * @date Jul 06, 2017
 *
 */
public class JsonTdsImportTopicUtlamListRecord extends JsonAbstractGrandFatherRecord {
	
	private String doc_1004 = null; //tullid
	public void setDoc_1004(String value) {  this.doc_1004 = value; }
	public String getDoc_1004() { return this.doc_1004;}
	
	private String doc_1001 = null; //typ
	public void setDoc_1001(String value) {  this.doc_1001 = value; }
	public String getDoc_1001() { return this.doc_1001;}
	
	
	private String dtm_2380b = null; //utlämningsdatum
	public void setDtm_2380b(String value) {  this.dtm_2380b = value; }
	public String getDtm_2380b() { return this.dtm_2380b;}
	
	
	
	private String nad_3036d = null; //avsändare
	public void setNad_3036d(String value) {  this.nad_3036d = value; }
	public String getNad_3036d() { return this.nad_3036d;}
	
	private String nad_3036e = null; //mottagare
	public void setNad_3036e(String value) {  this.nad_3036e = value; }
	public String getNad_3036e() { return this.nad_3036e;}
	
	private String nad_3207d = null; //lk
	public void setNad_3207d(String value) {  this.nad_3207d = value; }
	public String getNad_3207d() { return this.nad_3207d;}
	
	
	
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
