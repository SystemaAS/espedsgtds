/**
 * 
 */
package no.systema.tds.tdsexport.model.jsonjackson.topic.items;

import java.util.Collection;

import no.systema.main.util.NumberFormatterLocaleAware;



/**
 * @author oscardelatorre
 * 
 */
public class JsonTdsExportSpecificTopicItemContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String svev_syav = null;
	public void setSvev_syav(String value) {  this.svev_syav = value; }
	public String getSvev_syav() { return this.svev_syav;}
	
	private String svev_syop = null;
	public void setSvev_syop(String value) {  this.svev_syop = value; }
	public String getSvev_syop() { return this.svev_syop;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Double calculatedItemLinesTotalAmount = 0.000D;
	public void setCalculatedItemLinesTotalAmount(Double value) {  this.calculatedItemLinesTotalAmount = value; }
	public String getCalculatedItemLinesTotalAmount() {
		NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
		return formatter.getDoubleEuropeanFormat(this.calculatedItemLinesTotalAmount, 3, false);	}

	private Double diffItemLinesTotalAmountWithInvoiceTotalAmount = 0.000D;
	public void setDiffItemLinesTotalAmountWithInvoiceTotalAmount(Double value) {  this.diffItemLinesTotalAmountWithInvoiceTotalAmount = value; }
	public String getDiffItemLinesTotalAmountWithInvoiceTotalAmount() {
		NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
		return formatter.getDoubleEuropeanFormat(this.diffItemLinesTotalAmountWithInvoiceTotalAmount, 3, false);	}
	public Double getDiffItemLinesTotalAmountWithInvoiceTotalAmountDbl() { return this.diffItemLinesTotalAmountWithInvoiceTotalAmount;	}
	
	
	
	private int calculatedItemLinesTotalKolli = 0;
	public void setCalculatedItemLinesTotalKolli(int value) {  this.calculatedItemLinesTotalKolli = value; }
	public String getCalculatedItemLinesTotalKolli() { return String.valueOf(this.calculatedItemLinesTotalKolli); }

	private int diffItemLinesTotalKolliWithInvoiceTotalKolli = 0;
	public void setDiffItemLinesTotalKolliWithInvoiceTotalKolli(int value) {  this.diffItemLinesTotalKolliWithInvoiceTotalKolli = value; }
	public String getDiffItemLinesTotalKolliWithInvoiceTotalKolli() { return String.valueOf(this.diffItemLinesTotalKolliWithInvoiceTotalKolli);	}
	public Integer getDiffItemLinesTotalKolliWithInvoiceTotalKolliInt() { return this.diffItemLinesTotalKolliWithInvoiceTotalKolli;	}
	
	
	private Double calculatedItemLinesTotalGrossWeight = 0.000D;
	public void setCalculatedItemLinesTotalGrossWeight(Double value) {  this.calculatedItemLinesTotalGrossWeight = value; }
	public String getCalculatedItemLinesTotalGrossWeight() {
		NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
		return formatter.getDoubleEuropeanFormat(this.calculatedItemLinesTotalGrossWeight, 3, false);	}
	public Double getCalculatedItemLinesTotalGrossWeightDbl() { return this.calculatedItemLinesTotalGrossWeight;	}

	private Double diffItemLinesTotalGrossWeightWithInvoiceTotalGrossWeight = 0.000D;
	public void setDiffItemLinesTotalGrossWeightWithInvoiceTotalGrossWeight(Double value) {  this.diffItemLinesTotalGrossWeightWithInvoiceTotalGrossWeight = value; }
	public String getDiffItemLinesTotalGrossWeightWithInvoiceTotalGrossWeight() {
		NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
		return formatter.getDoubleEuropeanFormat(this.diffItemLinesTotalGrossWeightWithInvoiceTotalGrossWeight, 3, false);	}
	public Double getDiffItemLinesTotalGrossWeightWithInvoiceTotalGrossWeightDbl() { return this.diffItemLinesTotalGrossWeightWithInvoiceTotalGrossWeight;	}
	
	
	private Collection<JsonTdsExportSpecificTopicItemRecord> orderList;
	public void setOrderList(Collection<JsonTdsExportSpecificTopicItemRecord> value){ this.orderList = value; }
	public Collection<JsonTdsExportSpecificTopicItemRecord> getOrderList(){ return orderList; }
	
	
	
}
