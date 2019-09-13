package dropDown_Handling;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DriverCreation;
import utils.Methods;

public class DropDownMenu extends DriverCreation
{
	DriverCreation DC = new DriverCreation();
	static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException, IOException, EncryptedDocumentException, InvalidFormatException 
	{
		DriverCreation DC = new DriverCreation();
		driver = DC.driver("firefox");

		driver.manage().window().maximize();
		driver.get( "https://www.urbanladder.com/" );
		
		//String Page = driver.getTitle();
		String Path  = ".\\target\\";
		
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		WebDriverWait W = new WebDriverWait( driver, 20 );
		Actions A = new Actions(driver);

		// Clicking the Close symbol for dismiss the banner
		WebElement Close = driver.findElement( By.xpath( "//a[contains(text(),'Close')]" ) );
		W.until( ExpectedConditions.elementToBeClickable( Close ) );
		Close.click();

		// Clicking the Cross symbol for dismiss the head-alert
		WebElement Cross = driver.findElement(By.xpath("//div[@class='right close-head-alert']/div"));
		W.until( ExpectedConditions.elementToBeClickable( Cross ) );
		Cross.click();

		// Common Xpath for all dropdown menus
		String MenusPath = "//ul[@class='topnav bodytext']/li/span";
		
		// Common Xpath for all Lists inside each dropdown menus
		String ListsPath = "//div/div/ul/li/div/a";
		
		// Common Xpath for all Items inside each list inside each dropdown menus
		String ListItemsPath = "//div/div/ul/li[j]/ul/li/a/span";
		
		// Identifying all DropDown Menus WebElements
		List<WebElement> Menus = driver.findElements( By.xpath( MenusPath ) );
		
		System.out.println( "Total DropDown Menus = "+ Menus.size() );
		
		for( int i = 1 ; i <= Menus.size() ; i++ )
		{	
			// Locating WebElement of each dropdown menu one-by-one
			WebElement Menu = driver.findElement(By.xpath(MenusPath.replace("li","li["+i+"]")));
			
			// Hovering the cursor on each dropdown menu to make the lists inside visible
			A.moveToElement( Menu ).perform();
			
			// Printing Header text from each dropdown menu one-by-one
			System.out.print( "\nMenu[" + (i) + "] -> " + Menu.getText() );

			// Gathering all lists as WebElements -> present inside each dropdown menu one-by-one
			List<WebElement> Lists = ( List<WebElement> )driver.findElements( By.xpath( MenusPath.replace("li", "li["+ i +"]" ) + "/.." + ListsPath ) );
			
			System.out.println( " , Total Lists inside = "+ Lists.size() );
			
			for( int j = 1 ; j <= Lists.size() ; j++ )
			{	
				W.until( ExpectedConditions.elementToBeClickable( Lists.get( j-1 ) ) );
				
				// Printing the list Headers -> present inside each dropdown menus as Coloumn Headings
				System.out.print( "     List[" + j + "] -> " + Lists.get(j-1).getText() + " , List Items -> [" );

				// Gathering all items as WebElements -> present inside each list one-by-one -> present inside each dropdown menu
				List<WebElement> ListItems = ( List<WebElement> )driver.findElements( By.xpath( MenusPath.replace( "li", "li["+ i +"]" ) + "/.." + ListItemsPath.replace( "[j]", "["+ j +"]" ) ) );

				
				// Printing all items -> present inside each list -> present inside each dropdown menu as Coloumn values  as Coloumn
				for( int k = 0 ; k < ListItems.size()-1 ; k++ )
				{
					System.out.print( ( ListItems.get( k ).getText() + "," ) );
					Methods.writeData( ListItems.get( k ).getText(), Path, Menu.getText(), "Furniture", (k+1) , (j-1) );
				}
				
				// Printing the last item -> present inside each list -> present inside each dropdown menu
				System.out.println( ListItems.get( ListItems.size()-1 ).getText() + "]" );
				Methods.writeData( ListItems.get( ListItems.size()-1 ).getText(), Path, Menu.getText(), "Furniture", ( ListItems.size() ) , (j-1) );
			}
		}
		
		driver.close();
	}
}
