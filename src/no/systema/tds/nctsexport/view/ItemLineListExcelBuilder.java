/**
 * 
 */
package no.systema.tds.nctsexport.view;

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

import no.systema.tds.nctsexport.model.jsonjackson.topic.items.JsonNctsExportSpecificTopicItemRecord;
import no.systema.tds.util.TdsConstants;
import no.systema.main.context.TdsAppContext;
/**
 * 
 * @author oscardelatorre
 * @date Dec 5, 2014
 * 
 */
public class ItemLineListExcelBuilder extends AbstractExcelView {
	private ApplicationContext context;
	
	public ItemLineListExcelBuilder(){
		this.context = TdsAppContext.getApplicationContext();
	}
	
	protected void buildExcelDocument(Map<String, Object> model,
        HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get data model which is passed by the Spring Container via our own Controller implementation
        List<JsonNctsExportSpecificTopicItemRecord> itemList = (List<JsonNctsExportSpecificTopicItemRecord>) model.get(TdsConstants.ITEM_LIST);
         
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("TDS-Import Item list");
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

        header.createCell(0).setCellValue(this.context.getMessage("systema.ncts.export.item.list.label.tvavd.avdelning", new Object[0], request.getLocale()));
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue(this.context.getMessage("systema.ncts.export.item.list.label.tvtdn.topicNr", new Object[0], request.getLocale()));
        header.getCell(1).setCellStyle(style);
        
        header.createCell(2).setCellValue(this.context.getMessage("systema.ncts.export.item.list.label.tvli.linjeNr", new Object[0], request.getLocale()));
        header.getCell(2).setCellStyle(style);
        
        
        header.createCell(3).setCellValue(this.context.getMessage("systema.ncts.export.item.list.label.tvvnt.varukod", new Object[0], request.getLocale()));
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue(this.context.getMessage("systema.ncts.export.item.list.label.tvdk.deklTyp", new Object[0], request.getLocale()));
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue(this.context.getMessage("systema.ncts.export.item.list.label.tvalk.avsLand", new Object[0], request.getLocale()));
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue(this.context.getMessage("systema.ncts.export.item.list.label.tvblk.bestLand", new Object[0], request.getLocale()));
        header.getCell(6).setCellStyle(style);
        
        header.createCell(7).setCellValue(this.context.getMessage("systema.ncts.export.item.list.label.tvdty.dokTyp", new Object[0], request.getLocale()));
        header.getCell(7).setCellStyle(style);
        
        header.createCell(8).setCellValue(this.context.getMessage("systema.ncts.export.item.list.label.tvvktb.bruttoVikt", new Object[0], request.getLocale()));
        header.getCell(8).setCellStyle(style);
        
        header.createCell(9).setCellValue(this.context.getMessage("systema.ncts.export.item.list.label.tvvktn.nettoVikt", new Object[0], request.getLocale()));
        header.getCell(9).setCellStyle(style);
        
        header.createCell(10).setCellValue(this.context.getMessage("systema.ncts.export.item.list.label.sum_of_tvnt.kolliAnt", new Object[0], request.getLocale()));
        header.getCell(10).setCellStyle(style);
        
        header.createCell(11).setCellValue(this.context.getMessage("systema.ncts.export.item.list.label.tvvt.varuBeskrivning", new Object[0], request.getLocale()));
        header.getCell(11).setCellStyle(style);
        
        
        
        // create data rows
        int rowCount = 1;
         
        for (JsonNctsExportSpecificTopicItemRecord record : itemList) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(record.getTvavd());
            aRow.createCell(1).setCellValue(record.getTvtdn());
            aRow.createCell(2).setCellValue(record.getTvli());
            
            aRow.createCell(3).setCellValue(record.getTvvnt());
            aRow.createCell(4).setCellValue(record.getTvdk());
            aRow.createCell(5).setCellValue(record.getTvalk());
            aRow.createCell(6).setCellValue(record.getTvblk());
            aRow.createCell(7).setCellValue(record.getTvdty());
            aRow.createCell(8).setCellValue(record.getTvvktb());
            aRow.createCell(9).setCellValue(record.getTvvktn());
            aRow.createCell(10).setCellValue(record.getSum_of_tvnt());
            aRow.createCell(11).setCellValue(record.getTvvt());
        }
    }
	
}
