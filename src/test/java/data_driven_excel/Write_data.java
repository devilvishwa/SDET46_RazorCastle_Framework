package data_driven_excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Write_data {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/exceldata/eg.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet("Sheet3");
		//Row row = sheet.createRow(1);  -------for creating new row
		Row row = sheet.getRow(1);			//second time writing in same row
		Cell cell = row.createCell(1);
		cell.setCellValue("hello");
		FileOutputStream fos=new FileOutputStream("./src/test/resources/exceldata/eg.xlsx");
		wb.write(fos);
		wb.close();
		fis.close();
	}
}
