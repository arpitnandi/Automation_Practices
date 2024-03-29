package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverCreation 
{
	protected WebDriver D = null;

	public WebDriver driver(String Browser)
	{
		String Path = "C:\\Language_Binders\\";
		
		if(Browser.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", Path + "chromedriver.exe");
			D = new ChromeDriver();
		}
		else if(Browser.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", Path + "geckodriver.exe");
			D = new FirefoxDriver();
		}
		else if(Browser.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", Path + "IEDriverServer.exe");
			D = new InternetExplorerDriver();
		}	
		return D;
	}
}
