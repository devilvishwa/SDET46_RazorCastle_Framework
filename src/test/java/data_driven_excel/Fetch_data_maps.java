package data_driven_excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Fetch_data_maps {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		String expTestScriptName="CreateUser";
		
		DataFormatter df=new DataFormatter();
		FileInputStream fis=new FileInputStream("./src/test/resources/exceldata/eg.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet("Sample");
		int rowCount = sheet.getLastRowNum(); //index
		HashMap<String, String> map = new HashMap<>();
		for(int i=0; i<=rowCount; i++)
		{
			String testScriptName = df.formatCellValue(sheet.getRow(i).getCell(0));
			
			if(testScriptName.equalsIgnoreCase(expTestScriptName))
			{
				for(int j=0;j<sheet.getRow(i).getLastCellNum(); j++)
				{
					String key = df.formatCellValue(sheet.getRow(i).getCell(j));
					String value = df.formatCellValue(sheet.getRow(i+1).getCell(j));
					map.put(key, value);
				}
				break;
			}
			
		}
		System.out.println(map.get("FirstName"));
	}
}
