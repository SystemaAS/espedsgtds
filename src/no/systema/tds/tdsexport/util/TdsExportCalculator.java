/**
 * 
 */
package no.systema.tds.tdsexport.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.log4j.Logger;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemRecord;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicInvoiceContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicInvoiceRecord;



/**
 * @author oscardelatorre
 * @date Sep 16, 2013
 * 
 */
public class TdsExportCalculator {
	private static final Logger logger = Logger.getLogger(TdsExportCalculator.class.getName());
	
	/**
	 * 
	 * @param jsonTdsExportSpecificTopicItemContainer
	 * @return
	 */
	public Double getItemLinesTotalAmount(JsonTdsExportSpecificTopicItemContainer jsonTdsExportSpecificTopicItemContainer){
		Double retval = 0.000D;
		for (JsonTdsExportSpecificTopicItemRecord jsonTdsExportSpecificTopicItemRecord : jsonTdsExportSpecificTopicItemContainer.getOrderList()){
			try{
				String rawValue =  jsonTdsExportSpecificTopicItemRecord.getSvev_fabl();
				if(rawValue==null || "".equals(rawValue)){
					rawValue = "0.00";
				}else{
					NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
				    try {
				        Number parsed = nf.parse(rawValue);
				        BigDecimal bd = new BigDecimal(parsed.toString());
				        //logger.info(bd.toString());
				        retval += bd.doubleValue();
				        
				    } catch (Exception e) {
				        e.printStackTrace();
				    }

				}
				//logger.info("################### FINAL CONVERSION IMPORT FABL (calculated): " + retval);
				
			}catch (Exception e){
				e.printStackTrace();
				
			}
		}
		
		return retval;
		
	}
	/**
	 * 
	 * @param invoiceAmountParam
	 * @param calculatedItemLinesTotalAmount
	 * @return
	 */
	public Double getDiffBetweenCalculatedTotalAmountAndTotalAmount(String invoiceAmountParam, Double calculatedItemLinesTotalAmount){
		Double retval = 0.000D;
		Double tmp = 0.000D;
		try{
			String rawValue =  invoiceAmountParam;
			if(rawValue==null || "".equals(rawValue)){
				rawValue = "0.00";
			}else{
				//rawValue = rawValue.replace(",", ".");
				NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
			    try {
			        Number parsed = nf.parse(rawValue);
			        BigDecimal bd = new BigDecimal(parsed.toString());
			        logger.info(bd.toString());
			        tmp = bd.doubleValue();

			    } catch (Exception e) {
			        e.printStackTrace();
			    }

			}
			
			Double invoiceAmount = tmp;
			retval = invoiceAmount - calculatedItemLinesTotalAmount;
			//logger.info("################### 'FABL vs itemLinesTotal: " + invoiceAmount + "X" + calculatedItemLinesTotalAmount);
			//logger.info("################### FINAL CONVERSION IMPORT FABL (diff): " + retval);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return retval;
	}
	
	/**
	 * 
	 * @param container
	 * @return
	 */
	public Double getItemLinesTotalAmountInvoice(JsonTdsExportTopicInvoiceContainer container){
		Double retval = 0.000D;
		String  SWEDEN_CURRENCY_CODE = "SEK";
		for (JsonTdsExportTopicInvoiceRecord record : container.getInvList()){
			try{
				String rawValue =  record.getSvef_fabl();
				if(rawValue==null || "".equals(rawValue)){
					rawValue = "0.00";
				}else{
					NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
				    try {
				        Number parsed = nf.parse(rawValue);
				        BigDecimal bd = new BigDecimal(parsed.toString());
				        //logger.info("TOTAL AMOUNT:" + bd);
				        //check if there is a valid currency = NOK (meaning implicitly that there is several currencies)
				        
				        if(SWEDEN_CURRENCY_CODE.equals(container.getCalculatedValidCurrency())){
				        	    retval += bd.doubleValue() * (record.getSvef_vakuDbl() / record.getSvef_omrInt());
				        }else{
			        			retval += bd.doubleValue();
				        }

				        //retval += bd.doubleValue();
				        
				    } catch (Exception e) {
				        e.printStackTrace();
				    }

				}
				//logger.info("################### FINAL CONVERSION IMPORT FABL (calculated): " + retval);
				
			}catch (Exception e){
				e.printStackTrace();
				
			}
		}
		
		return retval;
		
	}
	
	/**
	 * 
	 * @param container
	 * @return
	 */
	public String getFinalCurrency(JsonTdsExportTopicInvoiceContainer container){
		String  SWEDEN_CURRENCY_CODE = "SEK";
		String currencyCode = null;
		int counter = 1;
		if(container!=null){
			outer :for (JsonTdsExportTopicInvoiceRecord record : container.getInvList()){
				if(counter==1){
					currencyCode = record.getSvef_vakd();
				}else{
					if(!currencyCode.equals(record.getSvef_vaku())){
						currencyCode = SWEDEN_CURRENCY_CODE;
						break outer;
					}
				}
				counter++;
			}
		}
		return currencyCode;
	}
	
}
