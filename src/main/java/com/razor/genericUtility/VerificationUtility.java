package com.razor.genericUtility;
/**
 * 
 * @author Vishwanath-Naik
 *
 */
public class VerificationUtility {
	/**
	 * 
	 * @param actual
	 * @param expected
	 */
	public void exactVerify(String actual, String expected) {
		if(actual.equals(expected))
		{
			System.out.println("TC is Pass");
		}
		else {
			System.out.println("TC is Fail");
		}
	}
	public void partialVerify(String actual, String expected) {
		if(actual.contains(expected))
		{
			System.out.println("TC is Pass");
		}
		else {
			System.out.println("TC is Fail");
		}
	}
}
