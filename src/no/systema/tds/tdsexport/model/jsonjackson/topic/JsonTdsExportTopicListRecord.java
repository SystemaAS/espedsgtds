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
 * @date Nov 25, 2012
 *
 */
public class JsonTdsExportTopicListRecord extends JsonAbstractGrandFatherRecord {
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String h_xref = null;
	public void setH_xref(String value) {  this.h_xref = value; }
	public String getH_xref() { return this.h_xref;}
	
	private String tullId = null;
	public void setTullId(String value) {  this.tullId = value; }
	public String getTullId() { return this.tullId;}
	
	private String tullid = null;
	public void setTullid(String value) {  this.tullid = value; }
	public String getTullid() { return this.tullid;}
	
	private String mrn = null;
	public void setMrn(String value) {  this.mrn = value; }
	public String getMrn() { return this.mrn;}
	
	private String sign = null;
	public void setSign(String value) {  this.sign = value; }
	public String getSign() { return this.sign;}

	private String datum = null;
	public void setDatum(String value) {  this.datum = value; }
	public String getDatum() { return this.datum;}
	
	private String status = null;
	public void setStatus(String value) {  this.status = value; }
	public String getStatus() { return this.status;}

	private String avsNavn = null;
	public void setAvsNavn(String value) {  this.avsNavn = value; }
	public String getAvsNavn() { return this.avsNavn;}
	
	private String motNavn = null;
	public void setMotNavn(String value) {  this.motNavn = value; }
	public String getMotNavn() { return this.motNavn;}
	
	private String mtyp = null;
	public void setMtyp(String value) {  this.mtyp = value; }
	public String getMtyp() { return this.mtyp;}
	
	private String sveh_prof = null;
	public void setSveh_prof(String value) {  this.sveh_prof = value; }
	public String getSveh_prof() {return this.sveh_prof;}
	
	private String dokref = null;
	public void setDokref(String value) {  this.dokref = value; }
	public String getDokref() {return this.dokref;}
	
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
