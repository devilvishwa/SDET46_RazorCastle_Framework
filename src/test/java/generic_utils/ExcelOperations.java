package generic_utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class contains reusable methods to handle Excel Operations
 * 
 * @author Rakesh K G
 *
 */
public class ExcelOperations {

	private DataFormatter df;
	private Workbook wb;

	/**
	 * default constructor for deprecated method createWorkBook
	 */
	public ExcelOperations() {

	}

	/**
	 * This constructor is used to initialize the ExcelOperations object
	 */
	public ExcelOperations(String filePath) {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			df = new DataFormatter();
			wb = WorkbookFactory.create(fis);
			fis.close();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will initialize the property file
	 * 
	 * @deprecated new ExcelOperations(String filePath)
	 * @param filePath
	 */
	@Deprecated
	public void createWorkBook(String filePath) {

		try {
			FileInputStream fis = new FileInputStream(filePath);
			df = new DataFormatter();
			wb = WorkbookFactory.create(fis);
			fis.close();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to get the test data for the given test script name
	 * 
	 * @param expTestScriptName
	 * @param sheetName
	 * @return
	 */
	public Map<String, String> getExcelData(String expTestScriptName, String sheetName) {

		Map<String, String> data = new HashMap<String, String>();
		Sheet sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();

		for (int i = 1; i <= rowCount; i++) {
			String testScriptName = df.formatCellValue(sheet.getRow(i).getCell(0));
			if (testScriptName.equalsIgnoreCase(expTestScriptName)) {
				for (int j = 1; j < sheet.getRow(i).getLastCellNum(); j++) {
					String key = df.formatCellValue(sheet.getRow(i).getCell(j)).trim();
					String value = df.formatCellValue(sheet.getRow(i + 1).getCell(j)).trim();
					data.put(key, value);
				}
				break;
			}
		}
		// System.out.println(data+data.get(key));
		return data;
	}

	/**
	 * This method is used to get the test data for the given test script name based
	 * on key
	 * 
	 * @param expTestScriptName
	 * @param expKey
	 * @param sheetName
	 * @return
	 */
	public String getExcelData(String expTestScriptName, String expKey, String sheetName) {

		Sheet sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();

		String value = "";
		for (int i = 1; i <= rowCount; i++) {
			String testScriptName = df.formatCellValue(sheet.getRow(i).getCell(0));
			if (testScriptName.equalsIgnoreCase(expTestScriptName)) {
				for (int j = 1; j < sheet.getRow(i).getLastCellNum(); j++) {
					String key = df.formatCellValue(sheet.getRow(i).getCell(j));
					if (key.equalsIgnoreCase(expKey)) {
						value = df.formatCellValue(sheet.getRow(i + 1).getCell(j));
						break;
					}
				}
				break;
			}
		}
		System.out.println(value);
		return value;
	}

	/**
	 * This method is used to set the test data in the excel
	 * 
	 * @param sheetName
	 * @param rowNum
	 * @param colnum
	 */
	public void setData(String sheetName, int rowNum, int colnum, String value,String fileOutputPath) {
		wb.getSheet(sheetName).getRow(rowNum).createCell(colnum).setCellValue(value);
		save(fileOutputPath);
	}

	/**
	 * 
	 */
	private void save(String fileOutputPath) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fileOutputPath);
			wb.write(fos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to close the workbook
	 * 
	 * @throws IOException
	 */
	public void closeWorkBook() throws IOException {
		wb.close();
	}

}
