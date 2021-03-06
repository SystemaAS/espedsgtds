/**
 * 
 */
package no.systema.tds.tdsexport.view;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemRecord;
import no.systema.tds.util.TdsConstants;
import no.systema.main.context.TdsAppContext;
/**
 * 
 * @author oscardelatorre
 * @date Dec 5, 2014
 * 
 */
public class ItemLineListExcelBuilder extends AbstractXlsView {
	private ApplicationContext context;
	
	public ItemLineListExcelBuilder(){
		this.context = TdsAppContext.getApplicationContext();
	}
	
	protected void buildExcelDocument(Map<String, Object> model,
        Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get data model which is passed by the Spring Container via our own Controller implementation
        List<JsonTdsExportSpecificTopicItemRecord> itemList = (List<JsonTdsExportSpecificTopicItemRecord>) model.get(TdsConstants.ITEM_LIST);
         
        // create a new Excel sheet
        Sheet sheet = workbook.createSheet("TDS-Export Item list");
        sheet.setDefaultColumnWidth(30);
         
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        style.setFont(font);
         
        // create header row
        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue(this.context.getMessage("systema.tds.import.item.list.label.sviv_syav.avdelning", new Object[0], request.getLocale()));
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue(this.context.getMessage("systema.tds.import.item.list.label.sviv_syop.topicNr", new Object[0], request.getLocale()));
        header.getCell(1).setCellStyle(style);
        
        header.createCell(2).setCellValue(this.context.getMessage("systema.tds.import.item.list.label.sviv_syli.linjeNr", new Object[0], request.getLocale()));
        header.getCell(2).setCellStyle(style);
        
        
        header.createCell(3).setCellValue(this.context.getMessage("systema.tds.import.item.list.label.sviv_vano.varupostNr", new Object[0], request.getLocale()));
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue(this.context.getMessage("systema.tds.import.item.list.label.sviv_ulkd.urspLand", new Object[0], request.getLocale()));
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue(this.context.getMessage("systema.tds.import.item.list.label.sviv_vata.varukod", new Object[0], request.getLocale()));
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue(this.context.getMessage("systema.tds.import.item.list.label.sviv_fokd.formanskod", new Object[0], request.getLocale()));
        header.getCell(6).setCellStyle(style);
        
        header.createCell(7).setCellValue(this.context.getMessage("systema.tds.import.item.list.label.sviv_eup1.forfarande1", new Object[0], request.getLocale()));
        header.getCell(7).setCellStyle(style);
        
        header.createCell(8).setCellValue(this.context.getMessage("systema.tds.import.item.list.label.sviv_brut.bruttoVikt", new Object[0], request.getLocale()));
        header.getCell(8).setCellStyle(style);
        
        header.createCell(9).setCellValue(this.context.getMessage("systema.tds.import.item.list.label.sviv_neto.nettoVikt", new Object[0], request.getLocale()));
        header.getCell(9).setCellStyle(style);
        
        header.createCell(10).setCellValue(this.context.getMessage("systema.tds.import.item.list.label.sviv_ankv.extraMangd", new Object[0], request.getLocale()));
        header.getCell(10).setCellStyle(style);
        
        header.createCell(11).setCellValue(this.context.getMessage("systema.tds.import.item.list.label.sum_of_sviv_kotas.kolliAnt", new Object[0], request.getLocale()));
        header.getCell(11).setCellStyle(style);
        
        header.createCell(12).setCellValue(this.context.getMessage("systema.tds.import.item.list.label.sviv_vasl.varuBeskrivning", new Object[0], request.getLocale()));
        header.getCell(12).setCellStyle(style);
        
        header.createCell(13).setCellValue(this.context.getMessage("systema.tds.import.item.list.label.sviv_fabl.fbelopp", new Object[0], request.getLocale()));
        header.getCell(13).setCellStyle(style);
        
        
        // create data rows
        int rowCount = 1;
         
        for (JsonTdsExportSpecificTopicItemRecord record : itemList) {
            Row aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(record.getSvev_syav());
            aRow.createCell(1).setCellValue(record.getSvev_syop());
            aRow.createCell(2).setCellValue(record.getSvev_syli());
            
            aRow.createCell(3).setCellValue(record.getSvev_vano());
            aRow.createCell(4).setCellValue(record.getSvev_ulkd());
            aRow.createCell(5).setCellValue(record.getSvev_vata());
            aRow.createCell(6).setCellValue(record.getSvev_vati());
            aRow.createCell(7).setCellValue(record.getSvev_eup1());
            aRow.createCell(8).setCellValue(record.getSvev_brut());
            aRow.createCell(9).setCellValue(record.getSvev_neto());
            aRow.createCell(10).setCellValue(record.getSvev_ankv());
            aRow.createCell(11).setCellValue(record.getSum_of_svev_kotas());
            aRow.createCell(12).setCellValue(record.getSvev_vasl());
            aRow.createCell(13).setCellValue(record.getSvev_fabl());
        }
    }
	
}
