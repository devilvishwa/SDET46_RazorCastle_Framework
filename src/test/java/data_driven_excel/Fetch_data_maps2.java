package data_driven_excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Fetch_data_maps2 {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
//		String expTestScriptName="AdminTest";
//		
//		DataFormatter df=new DataFormatter();
//		FileInputStream fis=new FileInputStream("./src/test/resources/exceldata/ProjectData.xlsx");
//		Workbook wb = WorkbookFactory.create(fis);
//		Sheet sheet = wb.getSheet("Tc");
//		int rowCount = sheet.getLastRowNum(); //index
//		HashMap<String, String> map = new HashMap<>();
//		for(int i=1; i<=rowCount; i++)
//		{
//			String testScriptName = df.formatCellValue(sheet.getRow(i).getCell(0));
//			
//			if(testScriptName.equalsIgnoreCase(expTestScriptName))
//			{
//				
//				for(int j=1;j<sheet.getRow(i).getLastCellNum(); j++)
//				{
//						String key = df.formatCellValue(sheet.getRow(i+1).getCell(j));
//						String value = df.formatCellValue(sheet.getRow(i+2).getCell(j));
//						map.put(key, value);
//						
//						
//				}
//				break;
//			}
//				
//		}
 		String expTestScriptName="OwnerTest";
		String tcData="Tc";
		//use excel data
		DataFormatter df=new DataFormatter();
		FileInputStream fis1=new FileInputStream("./src/test/resources/exceldata/ProjectData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sheet = wb.getSheet(tcData);
		int rowCount = sheet.getLastRowNum(); //index
		HashMap<String, String> map = new HashMap<>();
		for(int i=9; i<=rowCount; i++)
		{
			String testScriptName = df.formatCellValue(sheet.getRow(i).getCell(0));
			
			if(testScriptName.equalsIgnoreCase(expTestScriptName))
			{
				
				for(int j=1;j<sheet.getRow(i).getLastCellNum(); j++)
				{
						String key = df.formatCellValue(sheet.getRow(i+1).getCell(j));
						String value = df.formatCellValue(sheet.getRow(i+2).getCell(j));
						map.put(key, value);
						
						
				}
				break;
			}
				
		}	
	    System.out.println(map.get("Other")+"     "+map.get("UpdatedSale"));
	}
}
