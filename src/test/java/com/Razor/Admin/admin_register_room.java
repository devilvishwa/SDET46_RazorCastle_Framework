package com.Razor.Admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

public class admin_register_room {
	public static void main(String[] args) throws InterruptedException, IOException {
		
		PropertyUtility propertyUtils=new PropertyUtility(FrameworkConstants.TEST_PROPERTY_FILE_PATH);
		ExcelUtility excelUtils= new ExcelUtility(FrameworkConstants.TEST_EXCEL_FILE_PATH);
		JavaUtility javaUtility=new JavaUtility();
		SeleniumUtility seleniumUtility=new SeleniumUtility();
		VerificationUtility verificationUtility=new VerificationUtility();
		WaitUtility waitUtility=new WaitUtility();
		
		// common data
		String url=propertyUtils.getPropertyData(PropertyKeyData.URL);
		String browser = propertyUtils.getPropertyData(PropertyKeyData.BROWSER);
		String username = javaUtility.decode(propertyUtils.getPropertyData(PropertyKeyData.USERNAME));
		String password = javaUtility.decode(propertyUtils.getPropertyData(PropertyKeyData.PASSWORD));
		long timeout = Long.parseLong(propertyUtils.getPropertyData(PropertyKeyData.TIMEOUT));
		
		//test data
		String sheetName= ExcelSheet.SHEET1.getSheetName();
		String expModuleName="AdminTest";
		String expTestCaseName="IndividualHomeRegistration";
		Map<String, String> map = excelUtils.getData(sheetName, expModuleName, expTestCaseName);
		System.out.println(map);
		
		String expFullName=map.get("Full Name")+" "+javaUtility.getRandomNumber(100);
		
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
		
		//admin register room
		driver.findElement(By.xpath("//a[.='Login']")).click();
		driver.findElement(By.xpath("//input[@id='exampleInputEmail1']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='exampleInputPassword1']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		String adminPage = driver.getCurrentUrl();
		if(adminPage.equals("http://rmgtestingserver/domain/House_Rental_Application/auth/dashboard.php"))
		{                    
			System.out.println("admin page is displayed");
		}
		driver.findElement(By.xpath("//a[.='Register']")).click();
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='fullname']")).sendKeys(expFullName);
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='mobile']")).sendKeys(map.get("Mobile"));
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='alternat_mobile']")).sendKeys(map.get("Alternat Mobile"));
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='email']")).sendKeys(map.get("Email"));
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='plot_number']")).sendKeys(map.get("Plot Number/Home Number"));
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='rooms']")).sendKeys(map.get("Available Rooms"));
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='country']")).sendKeys(map.get("Country"));
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='state']")).sendKeys(map.get("State"));
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='city']")).sendKeys(map.get("City"));
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='rent']")).sendKeys(map.get("Rent"));
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='sale']")).sendKeys(map.get("Sale"));
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='deposit']")).sendKeys(map.get("Deposit"));
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='landmark']")).sendKeys(map.get("Landmark"));
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='address']")).sendKeys(map.get("Address"));
		WebElement ele = driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='image']"));
		ele.sendKeys("C:\\MANUAL ASSIGN\\WB.png");
		driver.findElement(By.xpath("//h2[.='Register Room']/../form/div[6]/following-sibling::button")).click();

		WebElement success = driver.findElement(By.xpath("//h2[.='Register Room']/preceding-sibling::div[1]"));
		System.out.println(success.getText());
		
		driver.findElement(By.xpath("//a[.='Home']")).click();
		driver.findElement(By.xpath("//b[text()='Rooms for Rent: ']")).click();
		String roomsPage= driver.getCurrentUrl();
		if(roomsPage.equals("http://rmgtestingserver/domain/House_Rental_Application/app/list.php"))
		{                    
			System.out.println("registered rooms page is displayed");
		}
		List<WebElement> list = driver.findElements(By.xpath("//h4[.='Owner Details']/../p[1]"));
		String actualFullName = "";
		for(WebElement ele1:list)
		{
			actualFullName = ele1.getText();	
		}
		verificationUtility.partialVerify(actualFullName, expFullName);
		driver.findElement(By.xpath("//a[.='Logout']")).click();
		
		seleniumUtility.closeBrowser();
	}
}
		
		
		
		
//		if(success.getText().equals("Registration successfull. Thank you"))
//		{
//			System.out.println("message : "+ success.getText());
//			System.out.println("verification successful");
//			driver.close();
//		}
//		else
//		{
//			System.out.println("defect");
//		}








/*
 * driver.findElement(By.xpath("(//button[.='Submit'])[1]")).click();
 * driver.findElement(By.xpath("//input[@id='fullname']")).sendKeys("tommy");
 * driver.findElement(By.xpath("(//input[@title='10 digit mobile number'])[1]"))
 * .sendKeys("8974561250");
 * driver.findElement(By.xpath("(//input[@title='10 digit mobile number'])[2]"))
 * .sendKeys("3698521470");
 * driver.findElement(By.xpath("(//input[@id='email'])[1]")).sendKeys(
 * "tommy@razor.com");
 * driver.findElement(By.xpath("(//input[@id='plot_number'])[1]")).sendKeys(
 * "3781");
 * driver.findElement(By.xpath("//input[@id='rooms']")).sendKeys("4bhk");
 * driver.findElement(By.xpath("(//input[@id='country'])[1]")).sendKeys("India")
 * ; driver.findElement(By.xpath("(//input[@id='state'])[1]")).sendKeys("goa");
 * driver.findElement(By.xpath("(//input[@id='city'])[1]")).sendKeys("panaji");
 * driver.findElement(By.xpath("//input[@id='rent']")).sendKeys("8000");
 * driver.findElement(By.xpath("//input[@id='sale']")).sendKeys("7880000");
 * driver.findElement(By.xpath("//input[@id='deposit']")).sendKeys("45000");
 * driver.findElement(By.xpath("(//input[@id='landmark'])[1]")).sendKeys(
 * "forest"); driver.findElement(By.xpath("(//input[@id='address'])[1]")).
 * sendKeys("123 stone park"); WebElement ele =
 * driver.findElement(By.xpath("(//input[@id='image'])[1]"));
 * ele.sendKeys("C:\\MANUAL ASSIGN\\WB.png");
 * driver.findElement(By.xpath("(//button[.='Submit'])[1]")).click();
 */