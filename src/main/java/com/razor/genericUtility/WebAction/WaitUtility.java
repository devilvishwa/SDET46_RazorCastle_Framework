package com.razor.genericUtility.WebAction;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
/**
 * 
 * @author Vishwanath-Naik
 *
 */
public class WaitUtility {
	/**
	 * 
	 * @param milisecond
	 */
	public void pause(long milisecond)
	{
		try {
			Thread.sleep(milisecond);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param driver
	 * @param timeout
	 */
	public void WaitForElementLoad(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
}
