package data_driven_excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Fetch_data {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		DataFormatter df=new DataFormatter();
		
		//step 1--- convert the physical file into java readable object
		FileInputStream fis=new FileInputStream("./src/test/resources/exceldata/eg.xlsx");
		
		//step 2--- open excel
		Workbook wb = WorkbookFactory.create(fis);
		
		//step 3--- get control on sheet
		Sheet sheet = wb.getSheet("Sheet1");
		
		// step 4-- get control on row
		Row row = sheet.getRow(1);
		
		// step 5--- get control on cell
		Cell cell = row.getCell(1);
		
		//step 6 --- fetch data
		String data = df.formatCellValue(cell);
		
		//step 7--- utilize data
		System.out.println(data);
		
		//step 8--- close stream workbook
		wb.close();
		fis.close();	
		
	}
}
