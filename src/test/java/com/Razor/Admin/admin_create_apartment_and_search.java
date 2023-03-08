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

public class admin_create_apartment_and_search {
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
		String expModuleName="AdminTest";
		String expTestCaseName="Apartment Registration";
		Map<String, String> map = excelUtils.getData(sheetName, expModuleName, expTestCaseName);
		System.out.println(map);
		
		String expApartmentName=map.get("Apartment Name")+" "+javaUtility.getRandomNumber(100);
		
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
		//admin register apartment
		driver.findElement(By.xpath("//a[.='Register']")).click();
		driver.findElement(By.xpath("//a[.='Apartment Registration']")).click();
		driver.findElement(By.xpath("//h2[.='Apartment Room']/..//div/input[@id='apartment_name']")).sendKeys(expApartmentName);
		driver.findElement(By.xpath("//h2[.='Apartment Room']/..//div/input[@id='mobile']")).sendKeys(map.get("Mobile"));
		driver.findElement(By.xpath("//h2[.='Apartment Room']/..//div/input[@id='alternat_mobile']")).sendKeys(map.get("Alternat Mobile"));
		driver.findElement(By.xpath("//h2[.='Apartment Room']/..//div/input[@id='email']")).sendKeys(map.get("Email"));
		driver.findElement(By.xpath("//h2[.='Apartment Room']/..//div/input[@id='plot_number']")).sendKeys(map.get("Plot Number/Home Number"));
		driver.findElement(By.xpath("//h2[.='Apartment Room']/..//div/input[@id='country']")).sendKeys(map.get("Country"));
		driver.findElement(By.xpath("//h2[.='Apartment Room']/..//div/input[@id='state']")).sendKeys(map.get("State"));
		driver.findElement(By.xpath("//h2[.='Apartment Room']/..//div/input[@id='city']")).sendKeys(map.get("City"));
		driver.findElement(By.xpath("//h2[.='Apartment Room']/..//div/input[@id='landmark']")).sendKeys(map.get("Landmark"));
		driver.findElement(By.xpath("//h2[.='Apartment Room']/..//div/input[@id='address']")).sendKeys(map.get("Address"));
		WebElement image = driver.findElement(By.xpath("//h2[.='Apartment Room']/..//div/input[@id='image']"));
		image.sendKeys("C:\\MANUAL ASSIGN\\KR.png");
		driver.findElement(By.xpath("//a[.='Add More(Plat Number/Description)']")).click();
		driver.findElement(By.xpath("//h2[.='Apartment Room']/..//div/input[@id='fullname']")).sendKeys(map.get("Fullname"));
		driver.findElement(By.xpath("//h2[.='Apartment Room']/..//div/input[@id='ap_number_of_plats']")).sendKeys(map.get("Flat number"));
		driver.findElement(By.xpath("//h2[.='Apartment Room']/..//div/input[@id='rooms']")).sendKeys(map.get("Room"));
		driver.findElement(By.xpath("//h2[.='Apartment Room']/..//div/input[@id='area']")).sendKeys(map.get("Area"));
		driver.findElement(By.xpath("//h2[.='Apartment Room']/..//div/input[@id='rent']")).sendKeys(map.get("Rent"));
		driver.findElement(By.xpath("//h2[.='Apartment Room']/..//div/input[@id='deposit']")).sendKeys(map.get("Deposit"));
		driver.findElement(By.xpath("//h2[.='Apartment Room']/..//div/input[@id='accommodation']")).sendKeys(map.get("Accomodation"));
		driver.findElement(By.xpath("//h2[.='Apartment Room']/..//div/input[@id='description']")).sendKeys(map.get("Description"));
		driver.findElement(By.xpath("//h2[.='Apartment Room']/../form/div[6]/following-sibling::button")).click();
		WebElement message = driver.findElement(By.xpath("(//div[.='Registration successfull. Thank you'])[1]"));
		System.out.println("print message: "+ message.getText());
		driver.findElement(By.xpath("//a[.='Logout']")).click();
		String homePage1 = driver.getCurrentUrl();
		if(homePage1.equals("http://rmgtestingserver/domain/House_Rental_Application/index.php"))
		{
			System.out.println("home page is displayed after logout");
		}
		
		//search and verify
		driver.findElement(By.xpath("//a[.='Search']")).click();
		driver.findElement(By.xpath("//input[@id='keywords']")).sendKeys(map.get("Room"));
		driver.findElement(By.xpath("//input[@id='location']")).sendKeys(map.get("City")); 
		driver.findElement(By.xpath("//button[@name='search']")).click();
		List<WebElement> list = driver.findElements(By.xpath("//h4[.='Room Details']/following-sibling::div[1]"));
		String actualApartmentName="";
		for(WebElement ele:list)
		{
			actualApartmentName = ele.getText();
		}
		verificationUtility.partialVerify(actualApartmentName, expApartmentName);
      
		
		seleniumUtility.closeBrowser();
	}
}
