package TYjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class write_db_check_ui {
	public static void main(String[] args) throws SQLException {



		Driver dbDriver= new Driver();
		DriverManager.registerDriver(dbDriver);

		Connection connection=null;
		try 
		{
			connection = DriverManager.getConnection("jdbc:mysql://rmgtestingserver:3333/projects","root@%","root");
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate("insert into project values('av20','tommy','07/02/2023','avatar6','future',5);");

			if(result==1)
			{
				System.err.println("data added successfully");
			}

		} 
		finally
		{
			connection.close();
			System.out.println("connection closed");
		}

		WebDriverManager.firefoxdriver().setup();
		WebDriver driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String un="rmgyantra";
		String pwd="rmgy@9999";
		String projectID ="av20";
		driver.get("http://rmgtestingserver:8084/");
		String titleRmg = driver.getTitle();
		if(titleRmg.equals("React App"))
		{
			System.out.println("rmg welcome page is displayed");
		}
		driver.findElement(By.xpath("//input[@id='usernmae']")).sendKeys(un);
		driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys(pwd);
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		driver.findElement(By.xpath("//a[.='Projects']")).click();
		
		//verify project id
		List<WebElement> list = driver.findElements(By.xpath("//th[.='ProjectId']/../../following-sibling::tbody/tr/td[1]"));
		for(WebElement ele:list)
		{
			String text = ele.getText();
			if(text.equals(projectID))
			{
				System.out.println(projectID+ " is present in UI");
			}
		}


	}
}
