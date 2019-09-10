package popup_Handling;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DriverCreation;

public class PromptPopUp 
{
	public static void main(String[] args) throws InterruptedException 
	{
		DriverCreation DC = new DriverCreation();
		WebDriver driver = DC.driver("firefox");

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebDriverWait W = new WebDriverWait(driver, 5);
		
		driver.get("http://toolsqa.com/handling-alerts-using-selenium-webdriver/");

		//Accepting for required Cookies could be enable
		W.until(ExpectedConditions.elementToBeClickable(By.linkText("ACCEPT")));
		driver.findElement(By.linkText("ACCEPT")).click();
		
		//Clicking on the button for which the Alert will pop out
		driver.findElement(By.xpath("//button[text()='Prompt Pop up']")).click();
		
		//Getting the Popup message as a String
		String AlertMSG = driver.switchTo().alert().getText();
		
		//Printing the gethered Popup message
		System.out.println(AlertMSG);

		//Entering the required text inside the Popup Edit prompt
		driver.switchTo().alert().sendKeys("I don't like ToolsQA.com");

		Thread.sleep(1000);
		
		//Clicking "Cancel" button inside the popup
		driver.switchTo().alert().dismiss();

		//Clicking again on the button for which the Alert will pop out
		driver.findElement(By.xpath("//button[text()='Prompt Pop up']")).click();

		//Getting the Popup message as a String
		AlertMSG = driver.switchTo().alert().getText();
		
		//Printing the gethered Popup message
		System.out.println(AlertMSG);

		//Entering the required text inside the Popup Edit prompt
		driver.switchTo().alert().sendKeys("I like ToolsQA.com");

		Thread.sleep(1000);
		
		//Clicking "Ok" button inside the popup
		driver.switchTo().alert().accept();

		Thread.sleep(1000);
		
		driver.close();
	}
}
