package no.systema.tds.tdsexport.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicRecord;
import no.systema.main.util.StringManager;

/**
 * 
 * @author oscardelatorre
 *
 */
public class TdsExportHeaderValidator implements Validator {
	private StringManager strMgr = new StringManager();

	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonTdsExportSpecificTopicRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonTdsExportSpecificTopicRecord record = (JsonTdsExportSpecificTopicRecord)obj;
				
		//Check for Mandatory fields first
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_avna", "systema.tds.export.header.error.null.sveh_avna"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_aveo", "systema.tds.export.header.error.null.sveh_aveo"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_ava1", "systema.tds.export.header.error.null.sveh_ava1"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_avpn", "systema.tds.export.header.error.null.sveh_avpn"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_avpa", "systema.tds.export.header.error.null.sveh_avpa"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_avlk", "systema.tds.export.header.error.null.sveh_avlk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_avha", "systema.tds.export.header.error.null.sveh_avha"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_avtl", "systema.tds.export.header.error.null.sveh_avtl"); 
		//receiver
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_mona", "systema.tds.export.header.error.null.sveh_mona"); 
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_moeo", "systema.tds.export.header.error.null.sveh_moeo"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_moa1", "systema.tds.export.header.error.null.sveh_moa1"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_mopn", "systema.tds.export.header.error.null.sveh_mopn"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_mopa", "systema.tds.export.header.error.null.sveh_mopa"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_molk", "systema.tds.export.header.error.null.sveh_molk"); 
		//deklarant
		/* If null then we copy from SENDER
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_dkna", "systema.tds.export.header.error.null.sveh_dkna"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_dkeo", "systema.tds.export.header.error.null.sveh_dkeo"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_dka1", "systema.tds.export.header.error.null.sveh_dka1"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_dkpn", "systema.tds.export.header.error.null.sveh_dkpn"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_dkpa", "systema.tds.export.header.error.null.sveh_dkpa"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_dklk", "systema.tds.export.header.error.null.sveh_dklk"); 
		*/
		//ombud
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_omeo", "systema.tds.export.header.error.null.sveh_omeo"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_omty", "systema.tds.export.header.error.null.sveh_omty"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_omha", "systema.tds.export.header.error.null.sveh_omha"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_omtl", "systema.tds.export.header.error.null.sveh_omtl"); 
		//header
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_syav", "systema.tds.export.header.error.null.sveh_syav"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_sysg", "systema.tds.export.header.error.null.sveh_sysg"); 
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_dek1", "systema.tds.export.header.error.null.sveh_dek1"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_dek2", "systema.tds.export.header.error.null.sveh_dek2"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_upps", "systema.tds.export.header.error.null.sveh_upps"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_tart", "systema.tds.export.header.error.null.sveh_tart"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_mtyp", "systema.tds.export.header.error.null.sveh_mtyp");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_fatx", "systema.tds.export.header.error.null.sveh_fatx");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_faty", "systema.tds.export.header.error.null.sveh_faty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_avut", "systema.tds.export.header.error.null.sveh_avut");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_aube", "systema.tds.export.header.error.null.sveh_aube");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_trin", "systema.tds.export.header.error.null.sveh_trin");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_trgr", "systema.tds.export.header.error.null.sveh_trgr");
		if( !"1".equals(record.getSveh_0035()) ){
			if( !"1".equals(record.getTestAvdFlag()) ){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_trai", "systema.tds.export.header.error.null.sveh_trai");
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_utfa", "systema.tds.export.header.error.null.sveh_utfa");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_kota", "systema.tds.export.header.error.null.sveh_kota");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_brut", "systema.tds.export.header.error.null.sveh_brut");
		//
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sveh_fabl", "systema.tds.export.header.error.null.sveh_fabl");
		
		
		//Logical controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			
			if(record!=null){
				//EORI nissarna
				if( record.getSveh_aveo()!=null && !"".equals(record.getSveh_aveo()) ){
					if(record.getSveh_aveo().length() < 3){
						errors.rejectValue("sveh_aveo", "systema.tds.export.header.error.rule.sveh_aveo.eoriInvalid");
					}
				}
				if( record.getSveh_moeo()!=null && !"".equals(record.getSveh_moeo()) ){
					if(record.getSveh_moeo().length() < 3){
						errors.rejectValue("sveh_moeo", "systema.tds.export.header.error.rule.sveh_moeo.eoriInvalid");
					}
				}
				if( record.getSveh_dkeo()!=null && !"".equals(record.getSveh_dkeo()) ){
					if(record.getSveh_dkeo().length() < 3){
						errors.rejectValue("sveh_dkeo", "systema.tds.export.header.error.rule.sveh_dkeo.eoriInvalid");
					}
				}
				
				//Meddelandytyp = sveh_mtyp
				if(record.getSveh_mtyp().equalsIgnoreCase("UGE") || record.getSveh_mtyp().equalsIgnoreCase("UGO")){
					if(!record.getSveh_dek2().equalsIgnoreCase("Z")){
						errors.rejectValue("sveh_mtyp", "systema.tds.export.header.error.rule.sveh_mtyp.ugeUgo");
					}
				}
				
				//Fakt.belopp, kurs och valuta = sveh_vakd - sveh_fabl - sveh_vaku
				if(record.getSveh_fabl()!=null && !record.getSveh_fabl().equalsIgnoreCase("") || 
				   record.getSveh_vakd()!=null && !record.getSveh_vakd().equalsIgnoreCase("") ||
				   record.getSveh_vaku()!=null && !record.getSveh_vaku().equalsIgnoreCase("")){
						
					if(record.getSveh_vakd()==null || record.getSveh_vakd().equalsIgnoreCase("")){
						errors.rejectValue("sveh_fabl", "systema.tds.export.header.error.rule.sveh_fabl.totalValidity");
					}else if (record.getSveh_vaku()==null || record.getSveh_vaku().equalsIgnoreCase("")){
						errors.rejectValue("sveh_fabl", "systema.tds.export.header.error.rule.sveh_fabl.totalValidity");
					}else if (record.getSveh_fabl()==null || record.getSveh_fabl().equalsIgnoreCase("")){
						errors.rejectValue("sveh_fabl", "systema.tds.export.header.error.rule.sveh_fabl.totalValidity");
					}
				}
				
				//Ber.Avg.Tid
				if(record.getSveh_beat()!=null && !record.getSveh_beat().equals("")){
					if(!"UGE".equalsIgnoreCase(record.getSveh_mtyp())){
						errors.rejectValue("sveh_beat", "systema.tds.export.header.error.rule.sveh_beat.onlyUgeMessageType");
					}
				}
				//Taxdeb.Dag
				if(record.getSveh_taxd()!=null && !record.getSveh_taxd().equals("")){
					if(!"UFF".equalsIgnoreCase(record.getSveh_mtyp())){
						errors.rejectValue("sveh_taxd", "systema.tds.export.header.error.rule.sveh_taxd.onlyUffMessageType");
					}
				}
				//Transportmedlets id (avg�ng)
				if(record.getSveh_trid()!=null && !record.getSveh_trid().equals("")){
					if("5".equalsIgnoreCase(record.getSveh_trin()) || "7".equalsIgnoreCase(record.getSveh_trin()) ){
						errors.rejectValue("sveh_trid", "systema.tds.export.header.error.rule.sveh_trid.invalidWith5Or7TransportInrikes");
					}
				}
				
				//Transportmedlets id (gr�nsspassagen)
				if(record.getSveh_tral()!=null && !record.getSveh_tral().equals("")){
					if("5".equalsIgnoreCase(record.getSveh_trgr()) || "7".equalsIgnoreCase(record.getSveh_trgr()) ){
						errors.rejectValue("sveh_tral", "systema.tds.export.header.error.rule.sveh_tral.optionalWith5Or7TransportVidGransen");
					}
				}
				
				//check frakt section
				if( strMgr.isNotNull(record.getSveh_vufr()) || strMgr.isNotNull(record.getSveh_vuva()) || strMgr.isNotNull(record.getSveh_vuku()) ){
					if( strMgr.isNotNull(record.getSveh_vufr()) && strMgr.isNotNull(record.getSveh_vuva()) && strMgr.isNotNull(record.getSveh_vuku()) ){
						//OK - since all must exits
					}else{
						errors.rejectValue("sveh_vuva", "systema.tds.export.header.error.rule.sveh_vuva.totalValidity");
					}
				}
				
				
				
			}
			
		}
			
		

	}
	
}
