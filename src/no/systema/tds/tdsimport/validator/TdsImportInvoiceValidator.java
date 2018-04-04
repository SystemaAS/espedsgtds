package no.systema.tds.tdsimport.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicInvoiceRecord;

/**
 * 
 * @author oscardelatorre
 * @date Oct 27, 2015
 * 
 */
public class TdsImportInvoiceValidator implements Validator {

	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonTdsImportTopicInvoiceRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonTdsImportTopicInvoiceRecord record = (JsonTdsImportTopicInvoiceRecord)obj;
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svif_fatx", "systema.tds.import.header.error.null.invoice.invnr.svif_fatx");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svif_faty", "systema.tds.import.header.error.null.invoice.typ.svif_faty"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svif_fabl", "systema.tds.import.header.error.null.invoice.belopp.svif_fabl"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svif_vakd", "systema.tds.import.header.error.null.invoice.valuta.svif_vakd"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svif_vaku", "systema.tds.import.header.error.null.invoice.valutaKurs.svif_vaku"); 
		
		
		
		/*
		if(record!=null){
			if("true".equals(record.getIsModeUpdate())){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svif_fatx", "systema.tds.import.header.error.null.invoice.invnr.svif_fatx"); 
			}else{
				if(this.atLeastOneValueExists(record)){
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svif_fatx", "systema.tds.import.header.error.null.invoice.invnr.svif_fatx"); 
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
	private boolean atLeastOneValueExists(JsonTdsImportTopicInvoiceRecord record){
		boolean retval = false;
		if(record!=null){
			if(!"".equals(record.getSvif_faty()) || !"".equals(record.getSvif_fabl()) || 
			   !"".equals(record.getSvif_vakd()) || !"".equals(record.getSvif_vaku()) ){
				retval = true;
			}
		}
		
		return retval;
	}
}
