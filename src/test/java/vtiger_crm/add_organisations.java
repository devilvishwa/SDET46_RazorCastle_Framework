package vtiger_crm;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class add_organisations {
	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "./seleniumexe/geckodriver.exe");
		WebDriver driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://localhost:8888/");
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		driver.findElement(By.xpath("(//a[.='Organizations'])[1]")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys("oxym");
		driver.findElement(By.xpath("//input[@name='website']")).sendKeys("oxym.com");
		WebElement ele = driver.findElement(By.xpath("//select[@name='industry']"));
		Select s=new Select(ele);
		s.selectByIndex(8);
		driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("7797561203");
		driver.findElement(By.xpath("//input[@name='bill_city']")).sendKeys("london");
		driver.findElement(By.xpath("(//input[@class='crmbutton small save' and @name='button'])[2]")).click();
		driver.findElement(By.xpath("(//a[.='Organizations'])[1]")).click();
	}

}
