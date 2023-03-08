package generic_utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserUtils {
	
	public static WebDriver launchChromeBrowser() {
        return new ChromeDriver();
    }
    
    public static WebDriver launchFirefoxBrowser() {
        return new FirefoxDriver();
    }
    
    public static WebDriver launchInternetExplorerBrowser() {
        return new InternetExplorerDriver();
    }
    
    public static WebDriver launchOperaBrowser() {
        return new OperaDriver();
    }
    
    public static WebDriver launchSafariBrowser() {
        return new SafariDriver();
    }
}






