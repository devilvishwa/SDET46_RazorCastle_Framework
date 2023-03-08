package Razorcastle;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class admin_create_apartment_and_search {
	public static void main(String[] args) throws IOException {
		
		String expTestScriptName="AdminTest";
		String tcData="Sheet1";
		//use excel data
		DataFormatter df=new DataFormatter();
		FileInputStream fis1=new FileInputStream("./src/test/resources/exceldata/ProjectData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sheet = wb.getSheet(tcData);
		int rowCount = sheet.getLastRowNum(); //index
		HashMap<String, String> map = new HashMap<>();
		for(int i=1; i<=rowCount; i++)
		{
			String testScriptName = df.formatCellValue(sheet.getRow(i).getCell(0));
			
			if(testScriptName.equalsIgnoreCase(expTestScriptName))
			{
				
				for(int j=1;j<sheet.getRow(i).getLastCellNum(); j++)
				{
						String key = df.formatCellValue(sheet.getRow(i+5).getCell(j));
						String value = df.formatCellValue(sheet.getRow(i+6).getCell(j));
						map.put(key, value);
						
						
				}
				break;
			}
				
		}	
		System.out.println(map);
		
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		FileInputStream fis=new FileInputStream("./src/test/resources/commonData/razor.properties");
		Properties prop = new Properties();
		prop.load(fis);
		
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		fis.close();
		
		driver.get(url);
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
		driver.findElement(By.xpath("//h2[.='Apartment Room']/..//div/input[@id='apartment_name']")).sendKeys(map.get("Apartment Name"));
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
		for(WebElement ele:list)
		{
			String apartment = ele.getText();
			if(apartment.contains(map.get("Apartment Name")))
			{
				System.out.println(apartment+ " apartment is displayed");
				break;
			}
			
			
		}
	}
}
