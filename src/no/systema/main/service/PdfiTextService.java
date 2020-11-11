package no.systema.main.service;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.tds.controller.GateController;
import no.systema.tds.model.jsonjackson.JsonTdsFirmArcContainer;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsAvdelningContainer;
import no.systema.tds.model.jsonjackson.avdsignature.JsonTdsAvdelningRecord;
import no.systema.tds.url.store.TdsUrlDataStore;
import no.systema.tds.util.TdsConstants;

@Data
@Service
public class PdfiTextService {
	
	//Wired - SERVICES
	@Autowired	
	private UrlCgiProxyService urlCgiProxyService;
	
	@Autowired
	private FirmArcService firmArcService;
		
	private static final Logger logger = Logger.getLogger(PdfiTextService.class.getName());
	private static final String BASE_DIR_TILLFALLIG_LAGRING_ARCHIVE = File.separator  + "tillflag" + File.separator;
	private static final String PDF_TYPE_DTL = "DTL";
	private static final String PDF_TYPE_AVVIKELSE = "Avvikelse";
	private static final String PDF_FILE_SUFFIX = ".pdf";
	public static final String TYPE_H_INLAGG = "I";
	public static final String TYPE_H_RATTELSE = "R";
	public static final String EMPTY_PLACEHOLDER = "Värdet var inte tillgängligt vid denna tidpunkt. Se vidare på NCTS (MRN)";
	private String fileBasePath = "";
	
	/**
	 * Creates the Pdf on disk
	 * @param fileName
	 * @throws Exception
	 */
	public void createPdf(SvlthDao dao) throws Exception {
        String plainFileName = this.getPlainFileName(dao);
        String absoluteFileName = this.fileBasePath + plainFileName;
        logger.warn(absoluteFileName);
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
        if(TYPE_H_RATTELSE.equals(dao.getSvlth_h())){
        	declarationType = "Avvikelse";
        }
        document.add(new Paragraph(declarationType + " - Deklaration for Tillfällig lagring "));
        document.add(new Paragraph(now));
        document.add(new Paragraph(""));
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
        table.addCell(addCell( EMPTY_PLACEHOLDER ));
        //record
        table.addCell(addCell("Underskrift/Bestyrkande"));
        table.addCell(addCell( "Namn och email-> todo" ));
        //record
        table.addCell(addCell("Tidigare dokument"));
        table.addCell(addCell(dao.getSvlth_ih1()) + " " + dao.getSvlth_ih2() + " " + dao.getSvlth_ih3());
        //record
        table.addCell(addCell("Ytterligare uppgifter"));
        table.addCell(addCell(dao.getSvlth_iex()) );
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
        table.addCell(addCell(EMPTY_PLACEHOLDER));
        //record
        table.addCell(addCell("Eori-nr - Deklaranten"));
        table.addCell(addCell(EMPTY_PLACEHOLDER));
        //record
        table.addCell(addCell("Eori-nr - Ombud"));
        table.addCell(addCell(EMPTY_PLACEHOLDER));
        //record
        table.addCell(addCell("Kod för ombudsstatus (T eller O)"));
        table.addCell(addCell(EMPTY_PLACEHOLDER));
        
        //record
        table.addCell(addCell("Godslokalkod - (Varornas förvaringsplats)"));
        table.addCell(addCell(dao.getSvlth_ign()));
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
        table.addCell(addCell(EMPTY_PLACEHOLDER));
        //record (ej obligatoriskt om det finns varubeskr.)
        //table.addCell(addCell("Varukod"));
        //table.addCell(addCell("-"));
        //record
        table.addCell(addCell("Transp.medlets ID vid ankomsten"));
        table.addCell(addCell(EMPTY_PLACEHOLDER));
        //record
        table.addCell(addCell("Containernr."));
        table.addCell(addCell(EMPTY_PLACEHOLDER));
        //record
        table.addCell(addCell("Förseglingsnr"));
        table.addCell(addCell(EMPTY_PLACEHOLDER));
        
        /*
        Table table = new Table(UnitValue.createPercentArray(8)).useAllAvailableWidth();
        
        List<List<String>> dataset = getData();
        for (List<String> record : dataset) {
            for (String field : record) {
                table.addCell(new Cell().add(new Paragraph(field)));
            }
        }
        */
        
        document.add(table);
        document.close();
        
        
    }
	
	private static Cell addCell(String str){
		Cell newCell = new Cell().add(new Paragraph(""));
		if(StringUtils.isNotEmpty(str)){
			newCell = new Cell().add(new Paragraph(str));			
		}
		return newCell;
	}
	
	private static String setStringValue(Integer value){
		String retval = "";
		try{
			retval = String.valueOf(value);
		}catch(Exception e){
			e.toString();
		}
		return retval;	
	}
	
	private static String setStringValue(BigDecimal value){
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
	 * Tullverket requires 2 name conventions:
	 * (1) Godslokalkod_YYYYMMDD_DTL (inlägg)
	 * (2) Godslokalkod_YYYYMMDD_Avvikelse (rättelse)
	 * (Lägg därtill Godsnr ifall filen inte är unik för denna dag (ref email från tullverket))
	 * 
	 * @param dao
	 * @return
	 */
	private String getPlainFileName(SvlthDao dao){
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
}
