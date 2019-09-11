package dropDown_Handling;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import utils.DriverCreation;

public class DropDownMenu extends DriverCreation
{

	DriverCreation DC = new DriverCreation();
	static WebDriver driver;
	
	public static void main(String[] args) 
	{
		DriverCreation DC = new DriverCreation();
		driver = DC.driver("chrome");
		
		driver.get( "https://www.urbanladder.com/" );
		
		// Common Xpath for all dropdown menus
		String DropdownMenus = "//span[@class='topnav_itemname']";
		
		// Identifying all DropDown Menus WebElements
		List<WebElement> Menus = driver.findElements(By.xpath(DropdownMenus));
		
		for( int i = 0 ; i < Menus.size() ; i++ )
		{	
			// Reading Header text of each dropdown menus one-by-one
			String HeaderText = Menus.get(i).getText();
			
			// Printing Header text from each dropdown menus one-by-one
			System.out.println( "Menu[" + (i+1) + "] = " + HeaderText + "\n" );

			// Collecting all sub list Headers and Items as WebElements -> present inside each dropdown menu
			List<WebElement> SubLists = (List<WebElement>)driver.findElements(By.xpath( "//span[contains(text(),'" + HeaderText + "')]/../div//div/ul/li["+i+"]/div/a" ));
			
			for( int j = 1 ; j < SubLists.size() ; j++ )
			{	
				// Collecting all items as WebElements -> present inside each sub list -> present inside each dropdown menu as Coloumn
				List<WebElement> subListItems = (List<WebElement>)driver.findElements(By.xpath( "//span[contains(text(),'" + HeaderText + "')]/../../li[1]/div/div/ul/li["+j+"]/ul/li/a/span" ));
				
				for( int k = 1 ; k < subListItems.size()-1 ; k++ )
				{
					// Printing all sub list Headers -> present inside each dropdown menus as Coloumn Headings
					System.out.print( "Sub List[" + (i+1) + "] = " + subListItems.get(0) + " -> List Items = [" );
					
					for( int l = 1 ; l < subListItems.size()-1 ; l++ )
					// Printing all items -> present inside each sub lists -> present inside each dropdown menus as Coloumn values one-by-one
					System.out.print( subListItems.get(l) + "," );
				}
				System.out.println( subListItems.get(subListItems.size()-1) + "]\n" );
			}
		}
		
		driver.close();
	}
}
