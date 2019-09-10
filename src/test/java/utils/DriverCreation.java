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
		//Language binder path = "C:\\Users\\Arpith\\eclipse-workspace\\LanguageBinders\\chromedriver.exe"
		if(Browser.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Arpith\\eclipse-workspace\\LanguageBinders\\chromedriver.exe");
			D = new ChromeDriver();
		}
		else if(Browser.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Arpith\\eclipse-workspace\\LanguageBinders\\geckodriver.exe");
			D = new FirefoxDriver();
		}
		else if(Browser.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", "C:\\Users\\Arpith\\eclipse-workspace\\LanguageBinders\\IEDriverServer.exe");
			D = new InternetExplorerDriver();
		}	
		return D;
	}
}
