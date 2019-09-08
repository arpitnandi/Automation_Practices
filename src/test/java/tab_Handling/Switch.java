package tab_Handling;

import java.awt.AWTException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Switch 
{
	public static void main(String[] args) throws AWTException
	{
		//System.setProperty("webdriver.chrome.driver", "C:\\Eclps_Projects\\Browser-Drivers\\chromedriver.exe");
		//WebDriver D = new ChromeDriver();

//		System.setProperty("webdriver.ie.driver", "C:\\Eclps_Projects\\Browser-Drivers\\IEDriverServer.exe");
//		WebDriver D = new InternetExplorerDriver();
		
		System.setProperty("webdriver.gecko.driver", "C:\\Eclps_Projects\\Browser-Drivers\\geckodriver.exe");
		WebDriver D = new FirefoxDriver();
		
		//Wait statement
		D.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait W = new WebDriverWait(D,10);
		
		//Load "Naukri" app
		D.get("https://www.naukri.com/mnjuser/homepage");
		
		//Login to user acount
		W.until(ExpectedConditions.elementToBeClickable(By.id("usernameField")));
		D.findElement(By.id("usernameField")).sendKeys("arpitnandi1@gmail.com");
		
		W.until(ExpectedConditions.elementToBeClickable(By.id("passwordField")));
		D.findElement(By.id("passwordField")).sendKeys("#Diya@11");
		
		W.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Login']")));
		D.findElement(By.xpath("//button[text()='Login']")).click();
		
		//D.findElement(By.xpath("//button[text()='SKIP AND CONTINUE']")).click();

		//Point cursor on "Notifications" 
//		Actions A = new Actions(D);
//		A.moveToElement(D.findElement(By.xpath("//div[@class='topIcon notify']"))).perform();
		
		//Click on to "Job Recommendations" 5 times in each last window
		for(int i = 0 ; i < 5 ; i++)
		{
			W.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='topIcon notify']")));
			D.findElement(By.xpath("//div[@class='topIcon notify']")).click();
			W.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Job Recommendations']")));
			D.findElement(By.xpath("//span[text()='Job Recommendations']")).click();
		}
		
		//Geting session ID's of opened Windows
		Set<String> Windows = D.getWindowHandles();
		Iterator I = Windows.iterator();
		
		//Switching between old to new window
		for(int i = 0 ; i < Windows.size() ; i++)
		{
			D.switchTo().window(I.next().toString());
			D.close();
		}
	}
}
