package Razorcastle;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class register_owner_apartment_complaint {
	public static void main(String[] args) throws IOException {
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
		//owner registration
		driver.findElement(By.xpath("//a[.='Register']")).click();
		driver.findElement(By.xpath("//input[@id='fullname']")).sendKeys("john");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("john");
		driver.findElement(By.xpath("//input[@id='mobile']")).sendKeys("3794563778");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("john@razor.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='c_password']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[.='Submit']")).click();
		WebElement message = driver.findElement(By.xpath("//div[.='Registration successfull. Now you can login']"));
		System.out.println("message: "+message.getText());
		//owner login
		driver.findElement(By.xpath("//a[.='Login']")).click();
		driver.findElement(By.xpath("//input[@id='exampleInputEmail1']")).sendKeys("john");
		driver.findElement(By.xpath("//input[@id='exampleInputPassword1']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[.='Submit']")).click();
		//owner register room
		driver.findElement(By.xpath("//a[.='Register']")).click();
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='fullname']")).sendKeys("ninja");
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='mobile']")).sendKeys("8974561810");
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='alternat_mobile']")).sendKeys("3699721470");
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='email']")).sendKeys("ninja@razor.com");
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='plot_number']")).sendKeys("9789");
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='rooms']")).sendKeys("1bhk");
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='country']")).sendKeys("India");
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='state']")).sendKeys("bihar");
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='city']")).sendKeys("patna");
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='rent']")).sendKeys("1000");
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='sale']")).sendKeys("560000");;
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='deposit']")).sendKeys("15000");
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='landmark']")).sendKeys("forest");
		driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='address']")).sendKeys("123 ntt ");
		WebElement ele = driver.findElement(By.xpath("//h2[.='Register Room']/..//div/input[@id='image']"));
		ele.sendKeys("C:\\MANUAL ASSIGN\\WB.png");
		driver.findElement(By.xpath("//h2[.='Register Room']/../form/div[6]/following-sibling::button")).click();
		
		WebElement success = driver.findElement(By.xpath("//h2[.='Register Room']/preceding-sibling::div[1]"));
		System.out.println("message 2: "+success.getText());
		//owner complaint
		driver.findElement(By.xpath("//a[.='Details/Update']")).click();
		driver.findElement(By.xpath("//a[.='Complaint']")).click();
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("john");
		driver.findElement(By.xpath("//input[@id='cmp']")).sendKeys("tv not working");
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
		WebElement name = driver.findElement(By.xpath("//td[.='john']"));
		WebElement name2 = driver.findElement(By.xpath("//td[.='tv not working']"));
		System.out.println("complaint by: "+name.getText());
		System.out.println("complaint name: "+name2.getText());
		
	}
}
