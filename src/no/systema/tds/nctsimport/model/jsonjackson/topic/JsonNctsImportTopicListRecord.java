/**
 * 
 */
package no.systema.tds.nctsimport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author oscardelatorre
 * @date Dec 3, 2013
 *
 */
public class JsonNctsImportTopicListRecord extends JsonAbstractGrandFatherRecord {
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String sign = null;
	public void setSign(String value) {  this.sign = value; }
	public String getSign() { return this.sign;}

	private String datum = null;
	public void setDatum(String value) {  this.datum = value; }
	public String getDatum() { return this.datum;}

	private String mrnNr = null;
	public void setMrnNr(String value) {  this.mrnNr = value; }
	public String getMrnNr() { return this.mrnNr;}
	
	private String status = null;
	public void setStatus(String value) {  this.status = value; }
	public String getStatus() { return this.status;}

	private String forenklad = null;
	public void setForenklad(String value) {  this.forenklad = value; }
	public String getForenklad() { return this.forenklad;}

	private String ansNavn = null;
	public void setAnsNavn(String value) {  this.ansNavn = value; }
	public String getAnsNavn() { return this.ansNavn;}

	private String godsNr = null;
	public void setGodsNr(String value) {  this.godsNr = value; }
	public String getGodsNr() { return this.godsNr;}

	private String datumFr = null;
	public void setDatumFr(String value) {  this.datumFr = value; }
	public String getDatumFr() { return this.datumFr;}


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
