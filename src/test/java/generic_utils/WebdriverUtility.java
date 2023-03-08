package generic_utils;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.mifmif.common.regex.util.Iterator;

public class WebdriverUtility {
public void maximizeWindow(WebDriver driver)
	
	{
		driver.manage().window().maximize();
	}
	
	public void minimizeWindow(WebDriver driver)
	
	{
		driver.manage().window().maximize();
	}
	public String getWindowhandle(WebDriver driver)
	{
		String wh = driver.getWindowHandle();
		return wh;
	}
	public Set<String> getWindowhandles(WebDriver driver)
	{
	 Set<String> allwh = driver.getWindowHandles();
		return allwh;
	}
	
	
	public void switchWindow(WebDriver driver,String PartialWindowTitle)
	{
		Set<String> allid = driver.getWindowHandles();
		Iterator it=(Iterator) allid.iterator();
		while(it.hasNext())
		{
			String wid=it.next();
			driver.switchTo().window(wid);
			if(driver.getTitle().contains(PartialWindowTitle))
			{
				break;
			}
		}
		
	}
	public void alertSwitchPopUpAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}

	public void alertSwitchPopUpCancel(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();;
	}

	public void alertSwitchPopUpgetText(WebDriver driver)
	{
		driver.switchTo().alert().getText();
	}
	
	public void getTitle(WebDriver driver)
	{
		driver.getTitle();
	}
	public void getclass(WebDriver driver)
	{
		driver.getClass();
	}
	public void getPageSource(WebDriver driver)
	{
		driver.getPageSource();
	}
	public void getCurrentURL(WebDriver driver)
	{
		driver.getCurrentUrl();
	}
	public void navigateBack(WebDriver driver)
	{
		driver.navigate().back();
	}
	public void navigateForward(WebDriver driver)
	{
		driver.navigate().forward();
	}
	public void navigateRefresh(WebDriver driver)
	{
		driver.navigate().refresh();
	}
	public void closeBrowser(WebDriver driver)
	{
		driver.close();
	}
	public void closeAllBrowser(WebDriver driver)
	{
		driver.quit();
	}
	public void lunchBrowser(WebDriver driver,String url)
	{
		driver.get(url);
	}
	public void switchFrame(WebDriver driver,int i)
	{
		driver.switchTo().frame(i);
	}
	
	public void switchFrame(WebDriver driver, String frameNameOrId)
	{
		driver.switchTo().frame(frameNameOrId);
	}
	public void switchFrame(WebDriver driver, WebElement frameElement)
	{
		driver.switchTo().frame(frameElement);
	}
}
