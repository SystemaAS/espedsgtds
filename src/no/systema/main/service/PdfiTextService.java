package no.systema.main.service;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import lombok.Data;
import no.systema.jservices.common.dao.SvlthDao;
import no.systema.main.util.AppConstants;
import no.systema.tds.model.jsonjackson.JsonTdsFirmArcContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.unloading.JsonNctsImportSpecificTopicUnloadingRecord;

@Data
@Service
public class PdfiTextService {
	
	//Wired - SERVICES
	@Autowired	
	private UrlCgiProxyService urlCgiProxyService;
	
	@Autowired
	private FirmArcService firmArcService;
		
	
	@Value("${tlagring.dao.responsible.contact.person.name}")
    private String contactPersonName;
	@Value("${tlagring.dao.responsible.contact.person.email}")
    private String contactPersonEmail;
	@Value("${tlagring.dao.responsible.contact.person.mobile}")
    private String contactPersonMobile;
	
	@Value("${tlagring.dao.warehouse.id}")
    private String warehouseId;
	@Value("${tlagring.dao.ombud.status}")
    private String ombudStatus;
	
	
	
	private static final Logger logger = Logger.getLogger(PdfiTextService.class.getName());
	private static final String BASE_DIR_TILLFALLIG_LAGRING_ARCHIVE = File.separator  + "tillflag" + File.separator;
	private static final String PDF_TYPE_DTL = "DTL";
	private static final String PDF_TYPE_AVVIKELSE = "Avvikelse";
	private static final String PDF_FILE_SUFFIX = ".pdf";
	public static final String TYPE_H_INLAGG = "I";
	public static final String TYPE_H_RATTELSE = "R";
	public static final String EMPTY_PLACEHOLDER = "Värdet var inte tillgängligt vid denna tidpunkt. Se vidare på NCTS (MRN)";
	private String fileBasePath = "";
	//
	public static final String MAP_KEY_FILE_NAME = "file";
	public static final String MAP_KEY_EMAIL_SUBJECT = "subject";
	
	/**
	 * Creates the Pdf on disk
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> createPdf(SvlthDao dao, JsonNctsImportSpecificTopicUnloadingRecord auxDao) throws Exception {
        HashMap<String, String> map = new HashMap<String, String>();
		String plainFileName = this.getPlainFileName(dao);
        String emailSubject = this.getEmailSubject(dao);
        String absoluteFileName = this.fileBasePath + plainFileName;
        logger.warn(absoluteFileName);
        
        map.put(MAP_KEY_FILE_NAME, absoluteFileName);
        map.put(MAP_KEY_EMAIL_SUBJECT, emailSubject);
        
		//Initialize PDF writer
        PdfWriter writer = new PdfWriter(absoluteFileName);
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);
        // Initialize document
        Document document = new Document(pdf);

		
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String now = formatter.format(time);
       
        //Add paragraph to the document
        String declarationType = "DTL";
        
        Table tableAvvikelse = null; 
        if(TYPE_H_RATTELSE.equals(dao.getSvlth_h())){
        	declarationType = "Avvikelse";
        	
        	this.getDocumentHeader(document, declarationType, now);
        	//tableAvv
            tableAvvikelse = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
            //record
            tableAvvikelse.addCell(addCell("MRN"));
            tableAvvikelse.addCell(addCell(dao.getSvlth_irn()));
            //record
            tableAvvikelse.addCell(addCell("Uppläggningsdatum"));
            tableAvvikelse.addCell(addCell( setStringValue(dao.getSvlth_id2())) );
            //record
            tableAvvikelse.addCell(addCell("Kontaktuppgifter"));
            tableAvvikelse.addCell(addCell( contactPersonName + ",  epost:" + contactPersonEmail + ",  mobil:" + contactPersonMobile));
            
            //record - Identifiering av lager
            //Ange lämplig unionskod till exempel SE, 
            //typ av lagringsanläggning följt av tillståndsnr. för anläggningen för tillfällig lagring i fråga)
            tableAvvikelse.addCell(addCell("Identifiering av lager"));
            tableAvvikelse.addCell(addCell(warehouseId));
            //record
            tableAvvikelse.addCell(addCell("Godslokalkod - (Varornas förvaringsplats)"));
            tableAvvikelse.addCell(addCell(dao.getSvlth_igl()));
            tableAvvikelse.addCell(addCell("LRN - Godsnummer"));
            tableAvvikelse.addCell(addCell(dao.getSvlth_ign()));
            
            //record
            tableAvvikelse.addCell(addCell("Bruttovikt(kg)"));
            if(dao.getSvlth_ibr()!=null){
            	tableAvvikelse.addCell(addCell(setStringValue(dao.getSvlth_ibr())) );
            }else{
            	tableAvvikelse.addCell(addCell(""));
            }
            //record
            tableAvvikelse.addCell(addCell("Kollislag"));
            tableAvvikelse.addCell(addCell(dao.getSvlth_isl()));
            //record
            tableAvvikelse.addCell(addCell("Antal kollin"));
            tableAvvikelse.addCell(addCell( setStringValue(dao.getSvlth_int())) );
            //record
            tableAvvikelse.addCell(addCell("Extrainformation"));
            StringBuffer sb = new StringBuffer(dao.getSvlth_ivb());
            if(StringUtils.isNotEmpty(dao.getSvlth_ivb2())){ sb.append("," + dao.getSvlth_ivb2()); }
            if(StringUtils.isNotEmpty(dao.getSvlth_ivb3())){ sb.append("," + dao.getSvlth_ivb3()); }
            if(StringUtils.isNotEmpty(dao.getSvlth_ivb4())){ sb.append("," + dao.getSvlth_ivb4()); }
            tableAvvikelse.addCell(addCell(sb.toString()));
            //add table
            document.add(tableAvvikelse);
            
        }else{
        	this.getDocumentHeader(document, declarationType, now);
	        //table 
	        Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
	        //===============
	        //SVLTH db-table
	        //===============
	        //record
	        table.addCell(addCell("MRN"));
	        table.addCell(addCell(dao.getSvlth_irn()));
	        //record
	        table.addCell(addCell("Uppläggningsdatum"));
	        table.addCell(addCell( setStringValue(dao.getSvlth_id2())) );
	        //record
	        table.addCell(addCell("Varupostnr (antal varuposter)"));
	        if(auxDao!=null && StringUtils.isNotEmpty(auxDao.getTivpos())){ 
	        	table.addCell(addCell(auxDao.getTivpos())); 
	        }else{
	        	table.addCell(addCell(EMPTY_PLACEHOLDER));
	        }
	        //record
	        table.addCell(addCell("Underskrift/Bestyrkande"));
	        table.addCell(addCell( contactPersonName + ",  epost:" + contactPersonEmail + ",  mobil:" + contactPersonMobile));
	        //record
	        table.addCell(addCell("Tidigare dokument"));
	        table.addCell(addCell(getTidigareDocument(dao.getSvlth_ih1(), dao.getSvlth_ih2(), dao.getSvlth_ih3())));
	        //record
	        table.addCell(addCell("Ytterligare uppgifter"));
	        table.addCell(addCell(dao.getSvlth_pos()) );
	        //record
	        table.addCell(addCell("Framlagda dokument"));
	        table.addCell(addCell(dao.getSvlth_iex()) );
	        //record
	        table.addCell(addCell("LRN - Godsnummer"));
	        table.addCell(addCell(dao.getSvlth_ign()));
	        
	        //record - Identifiering av lager
	        //Ange lämplig unionskod till exempel SE, 
	        //typ av lagringsanläggning följt av tillståndsnr. för anläggningen för tillfällig lagring i fråga)
	        table.addCell(addCell("Identifiering av lager"));
	        table.addCell(addCell(warehouseId));
	        //record
	        table.addCell(addCell("Eori-nr - Deklaranten"));
	        if(auxDao!=null && StringUtils.isNotEmpty(auxDao.getTitina())){ 
	        	table.addCell(addCell(auxDao.getTitina())); 
	        }else{
	        	table.addCell(addCell(EMPTY_PLACEHOLDER));
	        }
	        //record
	        table.addCell(addCell("Eori-nr - Ombud"));
	        if(auxDao!=null && StringUtils.isNotEmpty(auxDao.getTitin())){ 
	        	table.addCell(addCell(auxDao.getTitin())); 
	        }else{
	        	table.addCell(addCell(EMPTY_PLACEHOLDER));
	        }
	        //record
	        table.addCell(addCell("Kod för ombudsstatus (T eller O)"));
	        table.addCell(addCell(ombudStatus));
	        
	        //record
	        table.addCell(addCell("Godslokalkod - (Varornas förvaringsplats)"));
	        table.addCell(addCell(dao.getSvlth_igl()));
	        //record
	        table.addCell(addCell("Bruttovikt(kg)"));
	        if(dao.getSvlth_ibr()!=null){
	        	table.addCell(addCell(setStringValue(dao.getSvlth_ibr())) );
	        }else{
	        	table.addCell(addCell(""));
	        }
	        //record
	        table.addCell(addCell("Varubeskrivning"));
	        table.addCell(addCell(dao.getSvlth_ivb()));
	        //record
	        table.addCell(addCell("Kollislag"));
	        table.addCell(addCell(dao.getSvlth_isl()));
	        //record
	        table.addCell(addCell("Antal kollin"));
	        table.addCell(addCell( setStringValue(dao.getSvlth_int())) );
	        //record
	        table.addCell(addCell("Godsmärkning"));
	        table.addCell(addCell(this.getGodsmarkning(auxDao.getNimn1(), auxDao.getNimn2())));
	        //record (ej obligatoriskt om det finns varubeskr.)
	        //table.addCell(addCell("Varukod"));
	        //table.addCell(addCell("-"));
	        //record
	        table.addCell(addCell("Transp.medlets ID vid ankomsten"));
	        table.addCell(addCell(""));
	        //record
	        table.addCell(addCell("Containernr."));
	        table.addCell(addCell(""));
	        //record
	        table.addCell(addCell("Förseglingsnr"));
	        table.addCell(addCell(auxDao.getNidfkd()));	        
	        
	        document.add(table);
        }
        
        
        document.close();
        
        return map;
    }
	
	private String getTidigareDocument(String ih1, String ih2 , String ih3){
		StringBuffer retval = new StringBuffer();
		if(StringUtils.isNotEmpty(ih1)){
			retval.append(ih1);
		}
		if(StringUtils.isNotEmpty(ih2)){
			retval.append("," + ih2);
		}
		if(StringUtils.isNotEmpty(ih3)){
			retval.append("," + ih3);
		}
		
		if(retval.length()==0){
			retval.append("");
		}
		
		return retval.toString();
	}
	
	private String getGodsmarkning(String nimn1, String nimn2){
		StringBuffer retval = new StringBuffer();
		if(StringUtils.isNotEmpty(nimn1)){
			retval.append(nimn1);
		}
		if(StringUtils.isNotEmpty(nimn2)){
			retval.append("," + nimn2);
		}
		
		if(retval.length()==0){
			retval.append("");
		}
		
		return retval.toString();
	}
	
	private Cell addCell(String str){
		Cell newCell = new Cell().add(new Paragraph(""));
		if(StringUtils.isNotEmpty(str)){
			newCell = new Cell().add(new Paragraph(str));			
		}
		return newCell;
	}
	
	private String setStringValue(Integer value){
		String retval = "";
		try{
			retval = String.valueOf(value);
		}catch(Exception e){
			e.toString();
		}
		return retval;	
	}
	
	private String setStringValue(BigDecimal value){
		String retval = "";
		try{
			retval = value.toString();
		}catch(Exception e){
			e.toString();
		}
		return retval;	
	}
		
	
	/**
	 * 
	 * @param dto
	 * @param appUser
	 */
	public void setFileBasePath(String applicationUser){
		
		try{
			String BASE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFIRMARC.do";
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + applicationUser);
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("AVD BASE_URL:" + BASE_URL);
			logger.info("AVD BASE_PARAMS:" + urlRequestParamsKeys.toString());
			JsonTdsFirmArcContainer container = this.firmArcService.getContainer(url);
			
			this.fileBasePath = File.separator + container.getArcane() + this.BASE_DIR_TILLFALLIG_LAGRING_ARCHIVE;
			logger.warn("path:" + this.fileBasePath);
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * File name must be unique. We use the archive time-stamp of the dao
	 * It must be <= 40 chars
	 * 
	 * @param dao
	 * @return
	 */
	private String getPlainFileName(SvlthDao dao){
		String SEPARATOR = "_";
		StringBuffer name = null;
		
		if(dao!=null){
			name = new StringBuffer();
			name.append(dao.getSvlth_ign() + SEPARATOR + dao.getSvlth_id1() + dao.getSvlth_im1());
			if(TYPE_H_INLAGG.equals(dao.getSvlth_h())){
				name.append(SEPARATOR + PDF_TYPE_DTL);
			}else if(TYPE_H_RATTELSE.equals(dao.getSvlth_h())){
				name.append(SEPARATOR + PDF_TYPE_AVVIKELSE.toUpperCase().substring(0,5));
			}
			name.append(PDF_FILE_SUFFIX);
		}
		return name.toString();
	}
	
	/**
	 * Tullverket requires 2 name conventions:
	 * (1) Godslokalkod_YYYYMMDD_DTL (inlägg)
	 * (2) Godslokalkod_YYYYMMDD_Avvikelse (rättelse)
	 * (Lägg därtill Godsnr ifall filen inte är unik för denna dag (ref email från tullverket))
	 * 
	 * @param dao
	 * @return
	 */
	private String getEmailSubject(SvlthDao dao){
		String SEPARATOR = "_";
		StringBuffer name = null;
		
		if(dao!=null){
			name = new StringBuffer();
			name.append(dao.getSvlth_igl() + SEPARATOR + dao.getSvlth_id2() + SEPARATOR);
			if(TYPE_H_INLAGG.equals(dao.getSvlth_h())){
				name.append(PDF_TYPE_DTL);
				name.append("_" + dao.getSvlth_ign());
			}else if(TYPE_H_RATTELSE.equals(dao.getSvlth_h())){
				name.append(PDF_TYPE_AVVIKELSE);
				name.append("_" + dao.getSvlth_ign());
			}
			name.append(PDF_FILE_SUFFIX);
		}
		return name.toString();
	}
	
	private void getDocumentHeader(Document document, String declarationType, String now){
		document.add(new Paragraph(declarationType + " - Deklaration for Tillfällig lagring "));
    	document.add(new Paragraph(now));
    	document.add(new Paragraph(""));
	}
}
