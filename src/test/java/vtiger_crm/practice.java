package vtiger_crm;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class practice {
	public static void main(String[] args) {

		String expOrgName="Sdet46_"+new Random().nextInt(1000);
		String expContactName="Sdet46_Contact_"+new Random().nextInt(1000);
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Actions act=new Actions(driver);
		
		driver.get("http://localhost:8888");
		String actLoginPageText = driver.findElement(By.xpath("//input[@name='user_name']/../preceding-sibling::div")).getText();
		if(actLoginPageText.equals("User Name"))
		{
			System.out.println("Login Page Dispayed");
		}
		else {
			System.out.println("Login Page not Displyed");
			//		System.exit(0);
		}
        

		//login to the app
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();

		if(	driver.getCurrentUrl().contains("Home")) 
		{
			System.out.println("Home Page Dispayed");
		}
		else {
			System.out.println("Home Page not Displyed");
		}

		driver.findElement(By.linkText("Organizations")).click();
		if(	driver.getTitle().contains("Organizations")) 
		{
			System.out.println("Organizations Page Dispayed");
		}
		else {
			System.out.println("Organizations Page not Displyed");
		}

		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		String actCreateOrgPage = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();

		if(	actCreateOrgPage.equals("Creating New Organization")) 
		{
			System.out.println("Creating New Organization Page Dispayed");
		}
		else {
			System.out.println("Creating New Organization Page not Displyed");
		}


		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(expOrgName);

		WebElement industryDropDown = driver.findElement(By.xpath("//select[@name='industry']"));
		Select indDD = new Select(industryDropDown);
		indDD.selectByValue("Education");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		try{
			Alert alert = driver.switchTo().alert();
			alert.accept();
			System.out.println("Given organization is existed --> trying to create with new Organization");
			expOrgName=	expOrgName+new Random().nextInt(100);
			driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(expOrgName);
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();	
		}
		catch(Exception e) {
			System.out.println("Given organization is not existed");
		}

		String actOrgName = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
		String actIndustry=driver.findElement(By.xpath("//span[@id='dtlview_Industry']/font")).getText();

		if(expOrgName.equals(actOrgName)) {
			if(actIndustry.equals("Education")) {
				System.out.println("Both industry and orgName is showing");
			}
			else {
				System.out.println("only orgName is showing");
			}
		}
		else {
			if(actIndustry.equals("Education")) {
				System.out.println("only industy is showing");
			}else {
				System.out.println("Both industry and orgName is not showing");
			}
		}

	
		//create contat
		driver.findElement(By.xpath("//a[.='Contacts']")).click();
		
		//verify contact page
		
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//verify create contact page
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(expContactName);
		
		
		driver.findElement(By.xpath("//td[normalize-space(.)='Organization Name']/following-sibling::td/img")).click();
		
		Set<String> windows = driver.getWindowHandles();
		for (String id : windows) {
			driver.switchTo().window(id);
		if(	driver.getCurrentUrl().contains("Popup")){
			break;
		}
		}
		
		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(expOrgName);
		driver.findElement(By.xpath("//input[@name='search']")).click();
		driver.findElement(By.xpath("//a[text()='"+expOrgName+"']")).click();
		
		Set<String> windows2 = driver.getWindowHandles();
		for (String id : windows2) {
			driver.switchTo().window(id);
		if(	driver.getCurrentUrl().contains("Contacts")){
			break;
		}
		}
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		String actLastName = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
		String actOrgInCon = driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']/a")).getText();
		if(actLastName.equals(expContactName)) {
			if(actOrgInCon.equals(expOrgName)) {
				System.out.println("Both lastname and orgName is showing");
			}
			else {
				System.out.println("only lastName is showing");
			}
		}
		else {
			if(actOrgInCon.equals(expOrgName)) {
				System.out.println("only orgName is showing");
			}else {
				System.out.println("Both lastName and orgName is not showing");
			}
		}
		
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	
	driver.quit();
	}

}
