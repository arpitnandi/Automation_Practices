package dropDown_Handling;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.text.WordUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DriverCreation;

public class Dynamic extends DriverCreation
{
	public static void main(String[] args) throws InterruptedException
	{
		DriverCreation DC = new DriverCreation();
		WebDriver driver = DC.driver("chrome");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait W = new WebDriverWait(driver,15);
		
		driver.get("https://www.google.co.in/");
		
		String search = "seLenium";
		
		driver.findElement(By.xpath("//input[@title='Search']")).sendKeys(search);
		
		ArrayList<WebElement> Sugestions = (ArrayList<WebElement>) driver.findElements(By.xpath("//ul[@role='listbox']/li[*]/div[1]/div[2]/div[1]/span[1]/b"));
		
		for(int i = 2 ; i <= Sugestions.size() ; i++ )
		{
			WebElement Value = driver.findElement(By.xpath("//ul[@role='listbox']/li["+i+"]/div[1]/div[2]/div[1]/span[1]/b"));
			System.out.println(search+Value.getText());
		}
		
		driver.findElement(By.xpath("//ul[@role='listbox']/li["+2+"]/div[1]/div[2]/div[1]/span[1]/b")).click();
		
		ArrayList<WebElement> Searches = (ArrayList<WebElement>)driver.findElements(By.xpath("//h1[text()='Search Results']/..//a/div"));
		
		W.until(ExpectedConditions.elementToBeClickable(Searches.get(1)));
		Searches.get(1).click();
		
		try 
		{
			String Header = driver.findElement(By.xpath("//h1[1]")).getText();
			if(Header.contains(search.toLowerCase())||Header.contains(WordUtils.capitalizeFully(search,' '))||Header.contains(search.toUpperCase()))
				System.out.println("\nPage Header '"+Header+"' contains the search '"+search+"'");
			else
				System.out.println("\nPage Header '"+Header+"' dose not contains the search "+search+"'");

		}
		catch(Throwable E)
		{
			System.out.println("Page Header not found by the locater used.");
		}
		
		Thread.sleep(1000);

		driver.close();
	}
}
