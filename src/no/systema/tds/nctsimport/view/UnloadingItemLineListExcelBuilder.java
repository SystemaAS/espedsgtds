/**
 * 
 */
package no.systema.tds.nctsimport.view;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import no.systema.tds.nctsimport.model.jsonjackson.topic.unloading.items.JsonNctsImportSpecificTopicUnloadingItemRecord;
import no.systema.tds.util.TdsConstants;
import no.systema.main.context.TdsAppContext;
/**
 * 
 * @author oscardelatorre
 * @date Dec 5, 2014
 * 
 */
public class UnloadingItemLineListExcelBuilder extends AbstractXlsView {
	private ApplicationContext context;
	
	public UnloadingItemLineListExcelBuilder(){
		this.context = TdsAppContext.getApplicationContext();
	}
	
	protected void buildExcelDocument(Map<String, Object> model,
        Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get data model which is passed by the Spring Container via our own Controller implementation
        List<JsonNctsImportSpecificTopicUnloadingItemRecord> itemList = (List<JsonNctsImportSpecificTopicUnloadingItemRecord>) model.get(TdsConstants.ITEM_LIST);
         
        // create a new Excel sheet
        Sheet sheet = workbook.createSheet("TDS NCTS-Import Item list");
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

        header.createCell(0).setCellValue(this.context.getMessage("systema.ncts.import.unloading.item.list.label.tvavd.avdelning", new Object[0], request.getLocale()));
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue(this.context.getMessage("systema.ncts.import.unloading.item.list.label.tvtdn.topicNr", new Object[0], request.getLocale()));
        header.getCell(1).setCellStyle(style);
        
        header.createCell(2).setCellValue(this.context.getMessage("systema.ncts.import.unloading.item.list.label.tvli.linjeNr", new Object[0], request.getLocale()));
        header.getCell(2).setCellStyle(style);

        header.createCell(3).setCellValue(this.context.getMessage("systema.ncts.import.unloading.item.list.label.nvvnt.varukod", new Object[0], request.getLocale()));
        header.getCell(3).setCellStyle(style);
        
        header.createCell(4).setCellValue(this.context.getMessage("systema.ncts.import.unloading.item.list.label.nvdty.dokTyp", new Object[0], request.getLocale()));
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue(this.context.getMessage("systema.ncts.import.unloading.item.list.label.nvdref.dokRef", new Object[0], request.getLocale()));
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue(this.context.getMessage("systema.ncts.import.unloading.item.list.label.nvvktb.bruttoVikt", new Object[0], request.getLocale()));
        header.getCell(6).setCellStyle(style);
        
        header.createCell(7).setCellValue(this.context.getMessage("systema.ncts.import.unloading.item.list.label.nvvktn.nettoVikt", new Object[0], request.getLocale()));
        header.getCell(7).setCellStyle(style);
        
        header.createCell(8).setCellValue(this.context.getMessage("systema.ncts.import.unloading.item.list.label.nveh.kolliSlag", new Object[0], request.getLocale()));
        header.getCell(8).setCellStyle(style);
        
        header.createCell(9).setCellValue(this.context.getMessage("systema.ncts.import.unloading.item.list.label.nvnt.kolliAnt", new Object[0], request.getLocale()));
        header.getCell(9).setCellStyle(style);
        
        header.createCell(10).setCellValue(this.context.getMessage("systema.ncts.import.unloading.item.list.label.nvvt.varuBeskrivning", new Object[0], request.getLocale()));
        header.getCell(10).setCellStyle(style);
        
        
        // create data rows
        int rowCount = 1;
         
        for (JsonNctsImportSpecificTopicUnloadingItemRecord record : itemList) {
            Row aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(record.getTvavd());
            aRow.createCell(1).setCellValue(record.getTvtdn());
            aRow.createCell(2).setCellValue(record.getTvli());
            aRow.createCell(3).setCellValue(record.getNvvnt());
            aRow.createCell(4).setCellValue(record.getNvdty());
            aRow.createCell(5).setCellValue(record.getNvdref());
            aRow.createCell(6).setCellValue(record.getNvvktb());
            aRow.createCell(7).setCellValue(record.getNvvktn());
            aRow.createCell(8).setCellValue(record.getNveh());
            aRow.createCell(9).setCellValue(record.getNvnt());
            aRow.createCell(10).setCellValue(record.getNvvt());
        }
    }
	
}
