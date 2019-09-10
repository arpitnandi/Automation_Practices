package tab_Handling;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DriverCreation;

public class Switch_by_Index 
{
	public static void main(String[] args) throws AWTException
	{
		DriverCreation DC = new DriverCreation();
			
		WebDriver D = DC.driver("ie");
			
		//Wait statement
		D.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait W = new WebDriverWait(D,15);
			
		//Load "Naukri" app
		D.get("http://www.naukri.com/mnjuser/homepage");
			
		//Login to user acount
		//W.until(ExpectedConditions.presenceOfElementLocated(By.id("usernameField")));
		D.findElement(By.cssSelector("#usernameField")).sendKeys("arpitnandi1@gmail.com");
			
		W.until(ExpectedConditions.elementToBeClickable(By.id("passwordField")));
		D.findElement(By.id("passwordField")).sendKeys("#Diya@11");
			
		W.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Login']")));
		D.findElement(By.xpath("//button[text()='Login']")).click();
			
		//Click on to "Job Recommendations" 5 times in each last window
		for(int i = 0 ; i < 5 ; i++)
		{
			W.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='topIcon notify']")));
			//D.findElement(By.xpath("//div[@class='topIcon notify']")).click();
			Actions A = new Actions(D);
			A.moveToElement(D.findElement(By.xpath("//div[@class='topIcon notify']"))).build().perform();
			W.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Job Recommendations']")));
			D.findElement(By.xpath("//span[text()='Job Recommendations']")).click();
		}
			
		//Geting session ID's of opened Windows
		Set<String> Windows = D.getWindowHandles();
		ArrayList<String> IDs = new ArrayList(Windows);
			
		//Switching between old to new window
		for(int i = IDs.size()-1 ; i >= 0 ; i--)
		{
			D.switchTo().window(IDs.get(i));
			D.close();
		}
	}
}
