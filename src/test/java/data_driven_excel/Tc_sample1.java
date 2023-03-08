package data_driven_excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Tc_sample1 {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		String expTestScriptName="CheckListOfOwner";
		String expkey="OwnerName";
		DataFormatter df=new DataFormatter();
		FileInputStream fis=new FileInputStream("./src/test/resources/exceldata/eg.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet("Owner");
		int rowCount = sheet.getLastRowNum();
		String value="";
		for(int i=0; i<=rowCount; i++)
		{
			String testScriptName = df.formatCellValue(sheet.getRow(i).getCell(0));
//			System.out.println(testScriptName);
			if(testScriptName.equalsIgnoreCase(expTestScriptName))
			{
				for(int j=0;j<sheet.getRow(i).getLastCellNum(); j++)
				{
					String key = df.formatCellValue(sheet.getRow(i).getCell(j));
					if(key.equalsIgnoreCase(expkey))
					{
						value=df.formatCellValue(sheet.getRow(i+1).getCell(j));
						break;
					}
					
				}
				break;
			}
			
		}
		System.out.println(value);
	}
}
