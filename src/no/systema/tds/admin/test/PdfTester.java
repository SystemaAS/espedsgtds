package no.systema.tds.admin.test;



import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.itextpdf.io.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

public class PdfTester {
	public static final String DEST = "/ownfiles/godsnummer_yyyyMMdd_DTL.pdf";
	
	public static void main(String[] args) {
		File file = new File(DEST);
        file.getParentFile().mkdirs();
        
        try{
        	new PdfTester().createPdf(DEST);
        	System.out.println("OK");
        }catch(Exception e){
        	e.toString();
        }
	}
	
	public void createPdf(String dest) throws Exception {
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);
        
        // Initialize document
        Document document = new Document(pdf);

        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String now = formatter.format(time);
       
        //Add paragraph to the document
        document.add(new Paragraph("DTL - Deklaration for Tillfällig lagring "));
        document.add(new Paragraph(now));
        document.add(new Paragraph(""));
        //table
        Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
        //record
        table.addCell(addCell("MRN"));
        table.addCell(addCell("NO10ER2342342ER"));
        //record
        table.addCell(addCell("Godsnummer"));
        table.addCell(addCell("BXU-20-199"));
        //record
        table.addCell(addCell("Uppläggningsdatum"));
        table.addCell(addCell("20201105"));
        //record
        table.addCell(addCell("Tidigare dokument"));
        table.addCell(addCell("-"));
        //
        
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
		return new Cell().add(new Paragraph(str));
	}
	
	private static List<List<String>> getData() {
        List<List<String>> data = new ArrayList<>();
        String[] tableTitleList = {" Title", " (Re)set", " Obs", " Mean", " Std.Dev", " Min", " Max", "Unit"};
        data.add(Arrays.asList(tableTitleList));

        for (int i = 0; i < 10; i++) {
            List<String> dataLine = new ArrayList<>();
            for (int j = 0; j < tableTitleList.length; j++) {
                dataLine.add(tableTitleList[j] + " " + (i + 1));
            }
            data.add(dataLine);
        }

        return data;
    }

}
