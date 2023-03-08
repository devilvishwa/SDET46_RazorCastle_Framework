package data_driven_excel;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Fetch_mutiple_data {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		DataFormatter df=new DataFormatter();
		FileInputStream fis=new FileInputStream("./src/test/resources/exceldata/ProjectData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet("Sheet1");
		int rowCount = sheet.getLastRowNum(); //index
		System.out.println(rowCount);
		for(int i=0; i<=rowCount; i++)
		{
			short cellCount = sheet.getRow(i).getLastCellNum(); //count
			System.out.println(cellCount);
			for(int j=0; j<cellCount; j++)
			{
				System.out.println(df.formatCellValue(sheet.getRow(i).getCell(j)));
			}
		}
	}
}
