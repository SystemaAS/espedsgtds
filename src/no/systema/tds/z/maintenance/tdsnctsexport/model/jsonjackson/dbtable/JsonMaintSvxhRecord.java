package no.systema.tds.z.maintenance.tdsnctsexport.model.jsonjackson.dbtable;
import java.io.Serializable;
import java.lang.reflect.Field;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import lombok.Data;
/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Jul, 2021
 * 
 */
@Data 
public class JsonMaintSvxhRecord extends JsonAbstractGrandFatherRecord {

	private String thst = "";  //Status 
	private String thavd = ""; //avd
	private String thtdn = ""; //oppd
	private String thsg = ""; //sign
	private String thdt = "0"; //date YMD
	private String thgft1 = ""; //garanti
	private String thgadk = ""; //Adgangskode
	private String thgbl = "0"; //Garantibelopp
	private String thgvk = ""; //Valuta
	
	private String thddt = "0"; //Fristdatum
	
	
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
