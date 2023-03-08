package data_driven_excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Tc_multiple_sample {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		String expTestScriptName="CheckListOfOwner";
		String expkey="OwnerName";
		DataFormatter df=new DataFormatter();
		FileInputStream fis=new FileInputStream("./src/test/resources/exceldata/eg.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet("Owner");
		int rowCount = sheet.getLastRowNum();
		ArrayList<String> dataList = new ArrayList<>();
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
						for(int k=i+1;;k++)
						{
							String data = df.formatCellValue(sheet.getRow(k).getCell(j));
							if(data.equals(""))
							{
							break;
							}
							else {
								dataList.add(data);
							}
						}
						
					}
					
				}
				
			}
			
		}
		System.out.println(dataList);
		
	}
}
