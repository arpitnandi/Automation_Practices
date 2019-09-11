package dropDown_Handling;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DriverCreation;

public class DropDownMenu extends DriverCreation
{

	DriverCreation DC = new DriverCreation();
	static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException 
	{
		DriverCreation DC = new DriverCreation();
		driver = DC.driver("chrome");
		
		driver.get( "https://www.urbanladder.com/" );
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		WebDriverWait W = new WebDriverWait(driver, 15);
		
		WebElement Close = driver.findElement(By.xpath("//a[contains(text(),'Close')]"));
		W.until(ExpectedConditions.elementToBeClickable(Close));
		Close.click();
		
		// Common Xpath for all dropdown menus
		String DropdownMenus = "//span[@class='topnav_itemname']";
		
		// Identifying all DropDown Menus WebElements
		List<WebElement> Menus = driver.findElements(By.xpath(DropdownMenus));
		
		for( int i = 1 ; i <= Menus.size() ; i++ )
		{	
			Actions A = new Actions(driver);
			A.moveToElement(Menus.get(i-1)).perform();
			
			// Reading Header text of each dropdown menus one-by-one
			String HeaderText = Menus.get(i-1).getText();
			
			// Printing Header text from each dropdown menus one-by-one
			System.out.println( "Menu[" + (i) + "] = " + HeaderText + "\n" );

			// Collecting all sub list Headers as WebElements -> present inside each dropdown menu
			List<WebElement> SubLists = (List<WebElement>)driver.findElements(By.xpath( "//span[contains(text(),'" + HeaderText + "')]/../div//div/ul/li["+i+"]/div/a" ));
			
			for( int j = 1 ; j < SubLists.size() ; j++ )
			{	
				W.until(ExpectedConditions.elementToBeClickable(SubLists.get(j-1)));
				// Printing all sub list Headers -> present inside each dropdown menus as Coloumn Headings
				System.out.print( "Sub List[" + (j) + "] = " + SubLists.get(j-1).getText() + " -> List Items = [" );

				// Collecting all items as WebElements -> present inside each sub list -> present inside each dropdown menu as Coloumn
				List<WebElement> subListItems = (List<WebElement>)driver.findElements(By.xpath( "//span[contains(text(),'" + HeaderText + "')]/../div/div/ul/li["+j+"]/ul/li/a/span" ));
				
				for( int k = 0 ; k < subListItems.size()-1 ; k++ )
				{
					W.until(ExpectedConditions.elementToBeClickable(subListItems.get(k-1)));
					// Printing all items -> present inside each sub list -> present inside each dropdown menu as Coloumn values one-by-one
					System.out.print( subListItems.get(k).getText() + "," );
				}
				W.until(ExpectedConditions.elementToBeClickable(subListItems.get(subListItems.size()-1)));
				System.out.println( subListItems.get(subListItems.size()-1).getText() + "]\n" );
			}
		}
		
		driver.close();
	}
}
