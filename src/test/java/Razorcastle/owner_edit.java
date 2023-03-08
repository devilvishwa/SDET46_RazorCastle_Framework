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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class owner_edit {
	public static void main(String[] args) throws IOException {
		
		String expTestScriptName="OwnerTest";
		String tcData="Tc";
		//use excel data
		DataFormatter df=new DataFormatter();
		FileInputStream fis1=new FileInputStream("./src/test/resources/exceldata/ProjectData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sheet = wb.getSheet(tcData);
		int rowCount = sheet.getLastRowNum(); //index
		HashMap<String, String> map = new HashMap<>();
		for(int i=9; i<=rowCount; i++)
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
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		FileInputStream fis=new FileInputStream("./src/test/resources/commonData/razor.properties");
		Properties prop = new Properties();
		prop.load(fis);
		
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		fis.close();
		
		driver.get(url);
		//owner login
		driver.findElement(By.xpath("//a[.='Login']")).click();
		driver.findElement(By.xpath("//input[@id='exampleInputEmail1']")).sendKeys(map.get("User Name"));
		driver.findElement(By.xpath("//input[@id='exampleInputPassword1']")).sendKeys(map.get("Password"));
		driver.findElement(By.xpath("//button[.='Submit']")).click();
		//owner update
		driver.findElement(By.xpath("//a[.='Details/Update']")).click();
		driver.findElement(By.xpath("//a[.='Edit']")).click();
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='sale']")).clear();
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='sale']")).sendKeys(map.get("UpdatedSale"));
		driver.findElement(By.xpath("//input[@type='other']")).sendKeys(map.get("Other"));
		WebElement ele = driver.findElement(By.xpath("//select[@id='vacant']"));
		Select s=new Select(ele);
		s.selectByValue("0");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//a[.='Logout']")).click();
		//admin login
		driver.findElement(By.xpath("//a[.='Login']")).click();
		driver.findElement(By.xpath("//input[@id='exampleInputEmail1']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='exampleInputPassword1']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//a[.='Details/Update']")).click();
		//verify
		List<WebElement> list = driver.findElements(By.xpath("//h4[.='Room Details']/..//p[2]"));
		for(WebElement ele1:list)
		{
			String Sale = ele1.getText();
			if(Sale.contains(map.get("UpdatedSale")))
			{
				System.out.println(Sale+ " amount is updated");
				System.out.println("verification successfull");
				break;
			}
			
			
		}
		
		
		
		
		
		
		
		
//		driver.findElement(By.xpath("//div/h4[.='Other Details']/../div//b"));
//		WebElement text = driver.findElement(By.xpath("//div/h4[.='Other Details']/../div//b"));
//		System.out.println(text);
//		driver.findElement(By.xpath("//a[.='Edit']")).click();
		
	}
}
