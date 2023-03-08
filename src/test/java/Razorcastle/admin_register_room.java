package Razorcastle;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class admin_register_room {
	public static void main(String[] args) throws InterruptedException, IOException {
		
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
						String key = df.formatCellValue(sheet.getRow(i+1).getCell(j));
						String value = df.formatCellValue(sheet.getRow(i+2).getCell(j));
						map.put(key, value);
						
						
				}
				break;
			}
				
		}
		
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//use property file
		FileInputStream fis=new FileInputStream("./src/test/resources/commonData/razor.properties");
		Properties prop = new Properties();
		prop.load(fis);
		
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		fis.close();
		
		//admin register room
		driver.get(url);
		driver.findElement(By.xpath("//a[.='Login']")).click();
		driver.findElement(By.xpath("//input[@id='exampleInputEmail1']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='exampleInputPassword1']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
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
		if(success.getText().equals("Registration successfull. Thank you"))
		{
			System.out.println("message : "+ success.getText());
			System.out.println("verification successful");
			driver.close();
		}
		else
		{
			System.out.println("defect");
		}
	}
}






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