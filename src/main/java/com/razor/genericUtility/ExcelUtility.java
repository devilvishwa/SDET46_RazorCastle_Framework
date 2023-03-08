package com.razor.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * 
 * @author Vishwanath-Naik
 *
 */
public class ExcelUtility {
	DataFormatter df;
	Workbook wb;
	/**
	 * 
	 */
	public ExcelUtility() {}
	/**
	 * @param fileName
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public ExcelUtility(String fileName) throws EncryptedDocumentException, IOException
	{
		initialize(fileName);
	}

	private void initialize(String fileName) throws EncryptedDocumentException, IOException {
		df= new DataFormatter();
		FileInputStream fisData=new FileInputStream(fileName);
		wb = WorkbookFactory.create(fisData);
	}
	/**
	 * @param filePath
	 * @param sheetName
	 * @param expTestCaseName
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	//	public Map<String, String> getData(String sheetName, String expTestCaseName) 
	//	{
	//		Sheet sheet = wb.getSheet(sheetName);
	//		int rowCount = sheet.getLastRowNum();  //index
	//		Map<String, String> map = new HashedMap<String,String>();
	//		for(int i=1; i<=rowCount; i++)
	//		{
	//			String testScriptName = df.formatCellValue(sheet.getRow(i).getCell(0));
	//			
	//			if(testScriptName.equalsIgnoreCase(expTestCaseName))
	//			{
	//				for(int j=1;j<sheet.getRow(i).getLastCellNum(); j++)
	//				{
	//					
	//					String key = df.formatCellValue(sheet.getRow(i+1).getCell(j));
	//					String value = df.formatCellValue(sheet.getRow(i+2).getCell(j));
	//					map.put(key, value);
	//				}
	//				break;
	//			}
	//			
	//		}
	//		return map;
	//	}
	public Map<String, String> getData(String sheetName, String expModuleName, String expTestCaseName) 
	{
		Sheet sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();  //index
		Map<String, String> map = new HashedMap<String,String>();
		if(expModuleName.equalsIgnoreCase("AdminTest")) 
		{
			for(int i=1; i<=rowCount; i++)
			{
				String testScriptName = df.formatCellValue(sheet.getRow(i).getCell(0));

				if(testScriptName.equalsIgnoreCase(expModuleName))
				{
					String cell = df.formatCellValue(sheet.getRow(i).getCell(1));
					String cell2 = df.formatCellValue(sheet.getRow(i+4).getCell(1));
					if(cell.equalsIgnoreCase(expTestCaseName))
					{
						for(int j=1;j<sheet.getRow(i).getLastCellNum(); j++)
						{
							String key = df.formatCellValue(sheet.getRow(i+1).getCell(j));
							String value = df.formatCellValue(sheet.getRow(i+2).getCell(j));
							map.put(key, value);
						}

					}
					else if(cell2.equalsIgnoreCase(expTestCaseName))
					{
						for(int j=1;j<sheet.getRow(i).getLastCellNum(); j++)
						{
							String key = df.formatCellValue(sheet.getRow(i+5).getCell(j));
							String value = df.formatCellValue(sheet.getRow(i+6).getCell(j));
							map.put(key, value);
						}

					}
					else {
						System.out.println("no matching data");
					}
					break;
				}
			}
		}
		else if(expModuleName.equalsIgnoreCase("OwnerTest"))

		{
			for(int i=9; i<=rowCount; i++)
			{
				String testScriptName = df.formatCellValue(sheet.getRow(i).getCell(0));

				if(testScriptName.equalsIgnoreCase(expModuleName))
				{
					String cell3 = df.formatCellValue(sheet.getRow(i).getCell(1));
					String cell4 = df.formatCellValue(sheet.getRow(i+8).getCell(1));
					
					if(cell3.equalsIgnoreCase(expTestCaseName))
					{
						for(int j=1;j<sheet.getRow(i).getLastCellNum(); j++)
						{
							String key = df.formatCellValue(sheet.getRow(i+1).getCell(j));
							String value = df.formatCellValue(sheet.getRow(i+2).getCell(j));
							map.put(key, value);		
						}
					}
					else if(cell4.equalsIgnoreCase(expTestCaseName))
					{
						for(int j=1;j<sheet.getRow(i).getLastCellNum(); j++)
						{
							String key = df.formatCellValue(sheet.getRow(i+9).getCell(j));
							String value = df.formatCellValue(sheet.getRow(i+10).getCell(j));
							map.put(key, value);		
						}
					}
					else {
						System.out.println("no matching data");
					}
					break;
				}

			}
		}


		return map;
	}
	/**
	 * 
	 * @param sheetName
	 * @param expTestCaseName
	 * @param expectedKey
	 * @return
	 */

	//	public String getData(String sheetName, String expTestCaseName, String expectedKey) {
	//		Sheet sheet = wb.getSheet(sheetName);
	//		int rowCount = sheet.getLastRowNum();  //index
	//		String value="";
	//		int testScriptCounter = 0;
	//		int keyCounter = 0;
	//		Map<String, String> map = new HashedMap<>();
	//		for(int i=1; i<=rowCount; i++)
	//		{
	//			String testScriptName = df.formatCellValue(sheet.getRow(i).getCell(0));
	//			
	//			if(testScriptName.equalsIgnoreCase(expTestCaseName))
	//			{
	//				testScriptCounter++;
	//				for(int j=1;j<sheet.getRow(i).getLastCellNum(); j++)
	//				{
	//					String key = df.formatCellValue(sheet.getRow(i+1).getCell(j));
	//					if(key.equalsIgnoreCase(expectedKey))
	//					{
	//					keyCounter++;
	//					value = df.formatCellValue(sheet.getRow(i+2).getCell(j));
	//					break;
	//					}
	//				}
	//				break;
	//		}
	//			
	//	}
	//		if(keyCounter==0)
	//		{
	//			if(testScriptCounter==0)
	//			{
	//				value="please give proper testScript key "+expTestCaseName+"'";
	//			}
	//			else
	//			{
	//				value="please give proper testScript key '"+expectedKey+"'";
	//			}
	//		}
	//		System.out.println("value fetched from excel ==>"+value);
	//		return value;
	//	}
	/**
	 * 
	 * @param sheetName
	 * @param rowIndex
	 * @param cellIndex
	 * @return
	 */
	public String getData(String sheetName, int rowIndex, int cellIndex)
	{
		String value = df.formatCellValue(wb.getSheet(sheetName).getRow(rowIndex).getCell(cellIndex));
		return value;
	}
	/**
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException
	{
		wb.close();
	}
	/**
	 * 
	 * @param sheetName
	 * @param rowIndex
	 * @param cellIndex
	 */
	public void setData(String sheetName, int rowIndex, int cellIndex)
	{
		wb.getSheet(sheetName).getRow(rowIndex).createCell(cellIndex);
	}
	/**
	 * 
	 * @param fileOutputPath
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void setData(String fileOutputPath) throws FileNotFoundException, IOException
	{
		wb.write(new FileOutputStream(fileOutputPath));
	}
}


