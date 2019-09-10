package tab_Handling;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DriverCreation;
import utils.Methods;

public class GetTitles extends DriverCreation
{
	public static void main(String[] args) throws InterruptedException 
	{	
		DriverCreation DC = new DriverCreation();
		
		WebDriver D = DC.driver("firefox");
		
		//Wait statement
		D.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait W = new WebDriverWait(D,17);
		
		//Load "Naukri" app
		D.get("https://www.naukri.com/mnjuser/homepage");
		
		//Login to user acount
		W.until(ExpectedConditions.elementToBeClickable(By.id("usernameField")));
		D.findElement(By.id("usernameField")).sendKeys("arpitnandi1@gmail.com");
		
		W.until(ExpectedConditions.elementToBeClickable(By.id("passwordField")));
		D.findElement(By.id("passwordField")).sendKeys("#Diya@11");
		
		W.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Login']")));
		D.findElement(By.xpath("//button[text()='Login']")).click();
		
		//Click on to "Job Recommendations" 5 times from first window
		for(int i = 0 ; i < 5 ; i++)
		{
			W.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='topIcon notify']")));
			Actions A = new Actions(D);
			A.moveToElement(D.findElement(By.xpath("//div[@class='topIcon notify']"))).build().perform();
			//W.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Job Recommendations']")));
			D.findElement(By.xpath("//span[text()='Job Recommendations']")).click();
		}
		
		//Geting session ID's of opened Windows
		Set<String> Windows = D.getWindowHandles();
		ArrayList<String> IDs = new ArrayList<String>(Windows);
		
		//Switching between old to new window
		for(int i = 0 ; i < IDs.size() ; i++)
		{
			D.switchTo().window(IDs.get(i));
			Methods M = new Methods();
			M.waitForPageLoaded();
			System.out.println(D.getTitle());
			D.close();
			//}while(PageLoad.equals("complete"));
		}
	}
}
