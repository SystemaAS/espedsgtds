/**
 * 
 */
package no.systema.tds.tdsimport.model.jsonjackson.topic;

import java.util.Collection;

import no.systema.main.util.NumberFormatterLocaleAware;

/**
 * @author oscardelatorre
 * @date Oct 24, 2015
 *
 */
public class JsonTdsImportTopicInvoiceContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String fak = null;
	public void setFak(String value) {  this.fak = value; }
	public String getFak() { return this.fak;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	/*
	private Double calculatedItemLinesTotalAmount = 0.000D;
	public void setCalculatedItemLinesTotalAmount(Double value) {  this.calculatedItemLinesTotalAmount = value; }
	public String getCalculatedItemLinesTotalAmount() {
		NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
		return formatter.getDoubleEuropeanFormat(this.calculatedItemLinesTotalAmount, 3, false);
	}*/
	private String calculatedItemLinesTotalAmount = null;
	public void setCalculatedItemLinesTotalAmount(String value) {  this.calculatedItemLinesTotalAmount = value; }
	public String getCalculatedItemLinesTotalAmount() { return this.calculatedItemLinesTotalAmount; }
	
	private Double diffItemLinesTotalAmountWithInvoiceTotalAmount = 0.000D;
	public void setDiffItemLinesTotalAmountWithInvoiceTotalAmount(Double value) {  this.diffItemLinesTotalAmountWithInvoiceTotalAmount = value; }
	public String getDiffItemLinesTotalAmountWithInvoiceTotalAmount() {
		NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
		return formatter.getDoubleEuropeanFormat(this.diffItemLinesTotalAmountWithInvoiceTotalAmount, 3, false);
	}
	
	//Used when different currencies exist. The main currency must be = SEK
	private String calculatedValidCurrency = null;
	public void setCalculatedValidCurrency(String value) {  this.calculatedValidCurrency = value; }
	public String getCalculatedValidCurrency() { return this.calculatedValidCurrency; }
	
	
	/**
	 * This is usually done in the JSP but in this case (Finans.Opplysn.) we do it right here since there are some
	 * presentation issues that unable the counter on the JSP to work in the correct time line.
	 * Here we just send the size of the item list.
	 * 
	 */
	private String totalNumberOfItemLines = "";
	public String getTotalNumberOfItemLines() {
		String retval = "";
		if(this.invList!=null){
			retval = String.valueOf(this.invList.size());
		}
		return retval;
	}
	
	
	/**
	 * 
	 */
	private Collection<JsonTdsImportTopicInvoiceRecord> invList;
	public void setInvList(Collection<JsonTdsImportTopicInvoiceRecord> value){ this.invList = value; }
	public Collection<JsonTdsImportTopicInvoiceRecord> getInvList(){ return invList; }
	
	private Collection<JsonTdsImportTopicInvoiceRecord> oneInvoice;
	public void setOneInvoice(Collection<JsonTdsImportTopicInvoiceRecord> value){ this.oneInvoice = value; }
	public Collection<JsonTdsImportTopicInvoiceRecord> getOneInvoice(){ return oneInvoice; }
	
	
}
