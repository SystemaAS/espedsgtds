/**
 * 
 */
package no.systema.tds.tdsimport.model.jsonjackson.topic.items;

import java.util.Collection;
import java.util.Locale;
import java.text.NumberFormat;
import no.systema.main.util.NumberFormatterLocaleAware;

/**
 * @author oscardelatorre
 * @date Sep 2, 2103
 * 
 */
public class JsonTdsImportSpecificTopicItemContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String sviv_syav = null;
	public void setSviv_syav(String value) {  this.sviv_syav = value; }
	public String getSviv_syav() { return this.sviv_syav;}
	
	private String sviv_syop = null;
	public void setSviv_syop(String value) {  this.sviv_syop = value; }
	public String getSviv_syop() { return this.sviv_syop;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Double calculatedItemLinesTotalAmount = 0.000D;
	public void setCalculatedItemLinesTotalAmount(Double value) {  this.calculatedItemLinesTotalAmount = value; }
	public String getCalculatedItemLinesTotalAmount() {
		NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
		return formatter.getDoubleEuropeanFormat(this.calculatedItemLinesTotalAmount, 3, false);
	}

	private Double diffItemLinesTotalAmountWithInvoiceTotalAmount = 0.000D;
	public void setDiffItemLinesTotalAmountWithInvoiceTotalAmount(Double value) {  this.diffItemLinesTotalAmountWithInvoiceTotalAmount = value; }
	public String getDiffItemLinesTotalAmountWithInvoiceTotalAmount() {
		NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
		return formatter.getDoubleEuropeanFormat(this.diffItemLinesTotalAmountWithInvoiceTotalAmount, 3, false);
	}
	
	private Collection<JsonTdsImportSpecificTopicItemRecord> orderList;
	public void setOrderList(Collection<JsonTdsImportSpecificTopicItemRecord> value){ this.orderList = value; }
	public Collection<JsonTdsImportSpecificTopicItemRecord> getOrderList(){ return orderList; }
	
	
	
}
