package no.systema.tds.tdsexport.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicInvoiceRecord;

/**
 * 
 * @author oscardelatorre
 * @date Oct 27, 2015
 * 
 */
public class TdsExportInvoiceValidator implements Validator {

	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonTdsExportTopicInvoiceRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonTdsExportTopicInvoiceRecord record = (JsonTdsExportTopicInvoiceRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svef_faty", "systema.tds.export.header.error.null.invoice.typ.svef_faty"); 
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svef_fatx", "systema.tds.export.header.error.null.invoice.invnr.svef_fatx");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svef_fabl", "systema.tds.export.header.error.null.invoice.belopp.svef_fabl"); 
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svef_vakd", "systema.tds.export.header.error.null.invoice.valuta.svef_vakd"); 
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svef_vaku", "systema.tds.export.header.error.null.invoice.valutaKurs.svef_vaku"); 
		
		
		/*
		if(record!=null){
			if("true".equals(record.getIsModeUpdate())){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svef_fatx", "systema.tds.export.header.error.null.invoice.invnr.svef_fatx"); 
			}else{
				if(this.atLeastOneValueExists(record)){
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svef_fatx", "systema.tds.export.header.error.null.invoice.invnr.svef_fatx");
				}
				//TODO
			}
			
			
		}*/
	}
	/**
	 * Validate if this is an attempt for create new without invoice nr.
	 * @param record
	 * @return
	 */
	private boolean atLeastOneValueExists(JsonTdsExportTopicInvoiceRecord record){
		boolean retval = false;
		if(record!=null){
			if(!"".equals(record.getSvef_faty()) || !"".equals(record.getSvef_fabl()) || 
			   !"".equals(record.getSvef_vakd()) || !"".equals(record.getSvef_vaku()) ){
				retval = true;
			}
		}
		
		return retval;
	}
}
