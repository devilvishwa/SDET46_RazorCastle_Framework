package com.Razor.Owner;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.ExcelSheet.EnumUtility.ExcelSheet;
import com.ExcelSheet.EnumUtility.PropertyKeyData;
import com.razor.genericUtility.ExcelUtility;
import com.razor.genericUtility.FrameworkConstants;
import com.razor.genericUtility.JavaUtility;
import com.razor.genericUtility.PropertyUtility;
import com.razor.genericUtility.VerificationUtility;
import com.razor.genericUtility.WebAction.InteractionUtility;
import com.razor.genericUtility.WebAction.SeleniumUtility;
import com.razor.genericUtility.WebAction.WaitUtility;

public class owner_edit {
	public static void main(String[] args) throws IOException {

		PropertyUtility propertyUtils=new PropertyUtility(FrameworkConstants.TEST_PROPERTY_FILE_PATH);
		ExcelUtility excelUtils= new ExcelUtility(FrameworkConstants.TEST_EXCEL_FILE_PATH);
		JavaUtility javaUtility=new JavaUtility();
		SeleniumUtility seleniumUtility=new SeleniumUtility();
		VerificationUtility verificationUtility=new VerificationUtility();
		WaitUtility waitUtility=new WaitUtility();

		// common data
		String url=propertyUtils.getPropertyData(PropertyKeyData.URL);
		String browser = propertyUtils.getPropertyData(PropertyKeyData.BROWSER);
		String username = propertyUtils.getPropertyData(PropertyKeyData.USERNAME);
		String password = propertyUtils.getPropertyData(PropertyKeyData.PASSWORD);
		long timeout = Long.parseLong(propertyUtils.getPropertyData(PropertyKeyData.TIMEOUT));

		//test data
		String sheetName= ExcelSheet.SHEET1.getSheetName();
		String expModuleName="OwnerTest";
		String expTestCaseName="RegisterNewOwner";
		Map<String, String> map = excelUtils.getData(sheetName, expModuleName, expTestCaseName);
		System.out.println(map);

		String expSale=map.get("UpdatedSale")+javaUtility.getRandomNumber(100);

		WebDriver driver = seleniumUtility.launchBrowser(browser);
		InteractionUtility interactionUtility=new InteractionUtility(driver);

		//pre setting the browser
		seleniumUtility.maximizeBrowser();
		waitUtility.WaitForElementLoad(driver, timeout);

		//navigating the application
		seleniumUtility.launchApplication(url);
		String homePage = driver.getCurrentUrl();
		if(homePage.equals("http://rmgtestingserver/domain/House_Rental_Application/"))
		{
			System.out.println("home page is displayed");
		}

		//owner login
		driver.findElement(By.xpath("//a[.='Login']")).click();
		String loginPage= driver.getCurrentUrl();
		if(loginPage.equals("http://rmgtestingserver/domain/House_Rental_Application/auth/login.php"))
		{
			System.out.println("login page is displayed");
		}
		driver.findElement(By.xpath("//input[@id='exampleInputEmail1']")).sendKeys(map.get("User Name"));
		driver.findElement(By.xpath("//input[@id='exampleInputPassword1']")).sendKeys(map.get("Password"));
		driver.findElement(By.xpath("//button[.='Submit']")).click();
		String ownerPage = driver.getCurrentUrl();
		if(ownerPage.equals("http://rmgtestingserver/domain/House_Rental_Application/auth/dashboard.php"))
		{                    
			System.out.println("owner page is displayed");
		}

		//owner update
		driver.findElement(By.xpath("//a[.='Details/Update']")).click();
		driver.findElement(By.xpath("//a[.='Edit']")).click();
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='sale']")).clear();
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='sale']")).sendKeys(expSale);
		driver.findElement(By.xpath("//input[@type='other']")).sendKeys(map.get("Other"));
		WebElement ele = driver.findElement(By.xpath("//select[@id='vacant']"));
		Select s=new Select(ele);
		s.selectByValue("0");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//a[.='Logout']")).click();
		String homePage2=driver.getCurrentUrl();
		if(homePage2.equals("http://rmgtestingserver/domain/House_Rental_Application/index.php"))
		{
			System.out.println("home page is displayed after owner logout");
		}
		//admin login
		driver.findElement(By.xpath("//a[.='Login']")).click();
		driver.findElement(By.xpath("//input[@id='exampleInputEmail1']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='exampleInputPassword1']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		String adminPage = driver.getCurrentUrl();
		if(adminPage.equals("http://rmgtestingserver/domain/House_Rental_Application/auth/dashboard.php"))
		{                    
			System.out.println("admin page is displayed");
		}
		driver.findElement(By.xpath("//a[.='Details/Update']")).click();
		String detailsPage= driver.getCurrentUrl();
		if(detailsPage.equals("http://rmgtestingserver/domain/House_Rental_Application/app/list.php"))
		{                    
			System.out.println("details page is displayed");
		}
		//verify
		List<WebElement> list = driver.findElements(By.xpath("//h4[.='Room Details']/..//p[2]"));
		String actualSale = "";
		for(WebElement ele1:list)
		{
			actualSale = ele1.getText();
		}
		verificationUtility.partialVerify(actualSale, expSale);
		driver.findElement(By.xpath("//a[.='Logout']")).click();
		String successLogout = driver.getCurrentUrl();
		if(successLogout.equals("http://rmgtestingserver/domain/House_Rental_Application/index.php"))
		{
			System.out.println("admin successfully logged out ");
		}

		seleniumUtility.closeBrowser();

	}
}








	//		driver.findElement(By.xpath("//div/h4[.='Other Details']/../div//b"));
	//		WebElement text = driver.findElement(By.xpath("//div/h4[.='Other Details']/../div//b"));
	//		System.out.println(text);
	//		driver.findElement(By.xpath("//a[.='Edit']")).click();


