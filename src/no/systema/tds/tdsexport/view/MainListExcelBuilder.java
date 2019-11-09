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
import org.springframework.web.servlet.view.document.AbstractExcelView;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicListRecord;
import no.systema.tds.util.TdsConstants;
import no.systema.main.context.TdsAppContext;
/**
 * 
 * @author oscardelatorre
 * @date Nov 09, 2019
 * 
 */
public class MainListExcelBuilder extends AbstractExcelView {
	private ApplicationContext context;
	
	public MainListExcelBuilder(){
		this.context = TdsAppContext.getApplicationContext();
	}
	
	protected void buildExcelDocument(Map<String, Object> model,
        HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get data model which is passed by the Spring Container via our own Controller implementation
        List<JsonTdsExportTopicListRecord> itemList = (List<JsonTdsExportTopicListRecord>) model.get(TdsConstants.MAIN_TOPIC_LIST);
         
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("TDS-Export Main list");
        sheet.setDefaultColumnWidth(30);
         
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
         
        // create header row
        HSSFRow header = sheet.createRow(0);

        header.createCell(0).setCellValue(this.context.getMessage("systema.tds.export.list.search.label.avd", new Object[0], request.getLocale()));
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue(this.context.getMessage("systema.tds.export.list.search.label.signatur", new Object[0], request.getLocale()));
        header.getCell(1).setCellStyle(style);
        
        header.createCell(2).setCellValue(this.context.getMessage("systema.tds.export.list.search.label.arende", new Object[0], request.getLocale()));
        header.getCell(2).setCellStyle(style);
        
        
        header.createCell(3).setCellValue(this.context.getMessage("systema.tds.export.list.search.label.extRef", new Object[0], request.getLocale()));
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue(this.context.getMessage("systema.tds.export.list.search.label.tullid", new Object[0], request.getLocale()));
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue(this.context.getMessage("systema.tds.export.list.search.label.mtyp", new Object[0], request.getLocale()));
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue(this.context.getMessage("systema.tds.export.list.search.label.datum", new Object[0], request.getLocale()));
        header.getCell(6).setCellStyle(style);
        
        header.createCell(7).setCellValue(this.context.getMessage("systema.tds.export.list.search.label.status", new Object[0], request.getLocale()));
        header.getCell(7).setCellStyle(style);
        
        header.createCell(8).setCellValue(this.context.getMessage("systema.tds.export.list.search.label.proforma", new Object[0], request.getLocale()));
        header.getCell(8).setCellStyle(style);
        
        header.createCell(9).setCellValue(this.context.getMessage("systema.tds.export.list.search.label.avsandare", new Object[0], request.getLocale()));
        header.getCell(9).setCellStyle(style);
        
        header.createCell(10).setCellValue(this.context.getMessage("systema.tds.export.list.search.label.mottagare", new Object[0], request.getLocale()));
        header.getCell(10).setCellStyle(style);
        
        
        // create data rows
        int rowCount = 1;
         
        for (JsonTdsExportTopicListRecord record : itemList) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(record.getAvd());
            aRow.createCell(1).setCellValue(record.getSign());
            aRow.createCell(2).setCellValue(record.getOpd());
            
            aRow.createCell(3).setCellValue(record.getH_xref());
            aRow.createCell(4).setCellValue(record.getTullid());
            aRow.createCell(5).setCellValue(record.getMtyp());
            aRow.createCell(6).setCellValue(record.getDatum());
            aRow.createCell(7).setCellValue(record.getStatus());
            aRow.createCell(8).setCellValue(record.getSveh_prof());
            aRow.createCell(9).setCellValue(record.getAvsNavn());
            aRow.createCell(10).setCellValue(record.getMotNavn());
            
        }
    }
	
}
