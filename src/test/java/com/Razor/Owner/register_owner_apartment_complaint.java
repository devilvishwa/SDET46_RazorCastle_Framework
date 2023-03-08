package com.Razor.Owner;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class register_owner_apartment_complaint {
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
		String username = javaUtility.decode(propertyUtils.getPropertyData(PropertyKeyData.USERNAME));
		String password = javaUtility.decode(propertyUtils.getPropertyData(PropertyKeyData.PASSWORD));
		long timeout = Long.parseLong(propertyUtils.getPropertyData(PropertyKeyData.TIMEOUT));

		//test data
		String sheetName= ExcelSheet.SHEET1.getSheetName();
		String expModuleName="OwnerTest";
		String expTestCaseName="RegisterOwnerAndRoom";
		Map<String, String> map = excelUtils.getData(sheetName, expModuleName, expTestCaseName);
		System.out.println(map);

		String expComplaint=map.get("Complaint")+" "+javaUtility.getRandomNumber(100);

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
		
		//owner registration
		driver.findElement(By.xpath("//a[.='Register']")).click();
		driver.findElement(By.xpath("//input[@id='fullname']")).sendKeys(map.get("Full Name"));
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(map.get("User Name"));
		driver.findElement(By.xpath("//input[@id='mobile']")).sendKeys(map.get("Mobile"));
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(map.get("Email"));
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(map.get("Password"));
		driver.findElement(By.xpath("//input[@id='c_password']")).sendKeys(map.get("Confirm Password"));
		driver.findElement(By.xpath("//button[.='Submit']")).click();
		WebElement message = driver.findElement(By.xpath("//div[.='Registration successfull. Now you can login']"));
		System.out.println("message: "+message.getText());
		//owner login
		driver.findElement(By.xpath("//a[.='Login']")).click();
		driver.findElement(By.xpath("//input[@id='exampleInputEmail1']")).sendKeys(map.get("User Name"));
		driver.findElement(By.xpath("//input[@id='exampleInputPassword1']")).sendKeys(map.get("Password"));
		driver.findElement(By.xpath("//button[.='Submit']")).click();
		
		//owner register room
		driver.findElement(By.xpath("//a[.='Register']")).click();
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='fullname']")).sendKeys(map.get("Full Name"));
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
		System.out.println("message 2: "+success.getText());
		//owner complaint
		driver.findElement(By.xpath("//a[.='Details/Update']")).click();
		driver.findElement(By.xpath("//a[.='Complaint']")).click();
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys(map.get("Full Name"));
		driver.findElement(By.xpath("//input[@id='cmp']")).sendKeys(expComplaint);
		driver.findElement(By.xpath("//button[.='Submit']")).click();
		WebElement comp = driver.findElement(By.xpath("//div[.='Sent Successfully']"));
		System.out.println("complaint message: "+comp.getText());
		driver.findElement(By.xpath("//a[.='Logout']")).click();
		//admin login
		driver.findElement(By.xpath("//a[.='Login']")).click();
		driver.findElement(By.xpath("//input[@id='exampleInputEmail1']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='exampleInputPassword1']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//a[.='Complaint List']")).click();
		String compPage = driver.getCurrentUrl();
		if(compPage.equals("http://rmgtestingserver/domain/House_Rental_Application/app/cmplist.php"))
		{
			System.out.println("complaint page is displayed");
		}

		//verification
		
		String actualComplaint = driver.findElement(By.xpath("(//table[@class='table table-bordered']//tbody//tr//td)[last()-1]")).getText();
		System.out.println(actualComplaint);
		verificationUtility.partialVerify(actualComplaint, expComplaint);
		
		

	}
}
