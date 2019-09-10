package dropDown_Handling;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DriverCreation;

public class Dynamic extends DriverCreation
{
	public static void main(String[] args)
	{
		DriverCreation DC = new DriverCreation();
		WebDriver driver = DC.driver("chrome");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait W = new WebDriverWait(driver,15);
		
		driver.get("https://www.google.co.in/");
		
		String search = "Java";
		
		driver.findElement(By.xpath("//input[@title='Search']")).sendKeys(search);
		
		ArrayList<WebElement> Sugestions = (ArrayList<WebElement>) driver.findElements(By.xpath("//ul[@role='listbox']/li[*]/div[1]/div[2]/div[1]/span[1]/b"));
		
		for(int i = 2 ; i <= Sugestions.size() ; i++ )
		{
			WebElement Value = driver.findElement(By.xpath("//ul[@role='listbox']/li["+i+"]/div[1]/div[2]/div[1]/span[1]/b"));
			System.out.println(Value.getText());
		}
		
		driver.findElement(By.xpath("//input[@title='Search']")).clear();
		
		driver.close();
	}
}
