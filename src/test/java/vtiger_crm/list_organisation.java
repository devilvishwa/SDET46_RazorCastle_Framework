package vtiger_crm;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class list_organisation {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./seleniumexe/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://localhost:8888/");
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		driver.findElement(By.xpath("(//a[.='Organizations'])[1]")).click();
		List<WebElement> list = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr/td[3]"));
		String orgname="demox";
		for(WebElement ele:list)
		{
			String actorg = ele.getText();
			if(orgname.equals(actorg))
			{
				System.out.println("element is present");
			}
		}
	}

}
