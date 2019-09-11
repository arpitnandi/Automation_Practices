package dropDown_Handling;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.DriverCreation;

public class DropDownMenu extends DriverCreation
{
	public static void main(String[] args) 
	{
		
	}
	
	private void getHeaderItems() 
	{
		DriverCreation DC = new DriverCreation();
		WebDriver driver = DC.driver("chrome");
		
		driver.get("https://www.urbanladder.com/");
		
		List<WebElement> HeaderItems = driver.findElements(By.xpath("//span[@class='topnav_itemname']/../div/ul/li"));
		
		for(WebElement Item : HeaderItems)
			System.out.println(Item.getText());
		
		driver.close();
	}
	
	private void getSubHeaderItems(String HeaderText) 
	{
		DriverCreation DC = new DriverCreation();
		WebDriver driver = DC.driver("chrome");
		
		driver.get("https://www.urbanladder.com/");
		
		List<WebElement> HeaderItems = driver.findElements(By.xpath("//span[text()='Sale']/../div[1]"));
		// sublist = "//span[@class='topnav_itemname']/../div/div/ul/li[1]/div[1]"
		// sublistItems = "//span[@class='topnav_itemname']/../../li[1]/div/div/ul/li/ul/li/a/span"
		
		for(WebElement Item : HeaderItems)
			System.out.println(Item.getText());
		
		driver.close();
	}
}
