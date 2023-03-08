package com.ExcelSheet.EnumUtility;
/**
 * This enum contains sheet name of excel
 * @author Vishwanath-Naik
 *
 */
public enum ExcelSheet {
	
	SHEET1("Sheet1");
	String key;
	/**
	 * 
	 * @param string
	 */
	private ExcelSheet(String key) {
		this.key=key;
	}
	/**
	 * 
	 * @return
	 */
	public String getSheetName()
	{
		return key;
	}

	
	
}
