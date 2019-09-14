package dropDown_Handling;

import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
	
	public static void main( String[] args ) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		DriverCreation DC = new DriverCreation();
		driver = DC.driver( "firefox" );

		boolean ConsoleEnable = false, WorkbookEnable = true ;
		DropDownMenu.execute( ConsoleEnable, WorkbookEnable );
	}
	
	public static void execute( boolean ConsoleEnable, boolean WorkbookEnable ) throws EncryptedDocumentException, InvalidFormatException, IOException 
	{
		driver.manage().window().maximize();
		driver.get( "https://www.urbanladder.com/" );
		
		String Page = driver.getTitle();
		String Path  = ".\\target\\Captured Data\\";

		FileOutputStream FO = new FileOutputStream( Path + Page.replace(':','-') +".xlsx");
		XSSFWorkbook Wb = new XSSFWorkbook();
		
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		WebDriverWait W = new WebDriverWait( driver, 20 );
		Actions A = new Actions(driver);

		// Clicking the Cross symbol for dismiss the head-alert
		WebElement Cross = driver.findElement(By.xpath("//div[@class='right close-head-alert']/div"));
		W.until( ExpectedConditions.elementToBeClickable( Cross ) );
		Cross.click();

		// Clicking the Close symbol for dismiss the banner
		WebElement Close = driver.findElement( By.xpath( "//a[contains(text(),'Close')]" ) );
		W.until( ExpectedConditions.elementToBeClickable( Close ) );
		Close.click();

		// Common Xpath for all dropdown menus
		String MenusPath = "//ul[@class='topnav bodytext']/li/span";
		
		// Common Xpath for all Lists inside each dropdown menus
		String ListsPath = "//div/div/ul/li/div/a";
		
		// Common Xpath for all Items inside each list inside each dropdown menus
		String ListItemsPath = "//div/div/ul/li[j]/ul/li/a/span";
		
		// Identifying all DropDown Menus WebElements
		List<WebElement> Menus = driver.findElements( By.xpath( MenusPath ) );
		
		if( ConsoleEnable )
			System.out.println( "Total DropDown Menus = "+ Menus.size() );
		
		for( int i = 1 ; i <= Menus.size() ; i++ )
		{	
			// Locating WebElement of each dropdown menu one-by-one
			WebElement Menu = driver.findElement(By.xpath(MenusPath.replace("li","li["+i+"]")));
			
			// Hovering the cursor on each dropdown menu to make the lists inside visible
			A.moveToElement( Menu ).perform();
			
			// Printing Header text from each dropdown menu one-by-one
			if( ConsoleEnable )
				System.out.print( "\nMenu[" + (i) + "] -> " + Menu.getText() );

			// Gathering all lists as WebElements -> present inside each dropdown menu one-by-one
			List<WebElement> Lists = ( List<WebElement> )driver.findElements( By.xpath( MenusPath.replace("li", "li["+ i +"]" ) + "/.." + ListsPath ) );
			
			if( ConsoleEnable )
				System.out.println( " , Total Lists inside = "+ Lists.size() );

			Wb.createSheet( Menu.getText() );
			XSSFSheet S = Wb.getSheet( Menu.getText() );
			
			for( int j = 1 ; j <= Lists.size() ; j++ )
			{	
				W.until( ExpectedConditions.elementToBeClickable( Lists.get( j-1 ) ) );
				XSSFRow R = S.createRow( j-1 );
				
				// Printing the list Headers -> present inside each dropdown menus as Column Headings
				if(ConsoleEnable)
					System.out.print( "     List[" + j + "] -> " + Lists.get( j-1 ).getText() );
				else if( WorkbookEnable )
					R.createCell( 0 ).setCellValue( Lists.get( j-1 ).getText() +" => " );

				// Gathering all items as WebElements -> present inside each list one-by-one -> present inside each dropdown menu
				List<WebElement> ListItems = ( List<WebElement> )driver.findElements( By.xpath( MenusPath.replace( "li", "li["+i+"]" ) + "/.." + ListItemsPath.replace( "[j]", "["+j+"]" ) ) );
				if(ConsoleEnable)
					System.out.print( " , List Items ("+ ListItems.size() +") -> [ " );
				
				// Printing all items -> present inside each list -> present inside each dropdown menu as Column values
				for( int k = 0; k < ListItems.size()-1 ; k++ )
				{
					if( ConsoleEnable )
						System.out.print( ( ListItems.get( k ).getText() + ", " ) );
					else if( WorkbookEnable )
						R.createCell( k+2 ).setCellValue( ListItems.get( k ).getText() );
				}
				
				// Printing the last item -> present inside each list -> present inside each dropdown menu as Column value
				if( ConsoleEnable )
					System.out.println( ListItems.get( ListItems.size()-1 ).getText() + " ]" );
				else if( WorkbookEnable )
					R.createCell( ListItems.size()+1 ).setCellValue( ListItems.get( ListItems.size()-1 ).getText() );
			}
		}
		
		Wb.write(FO);
		Wb.close();
		FO.flush();
		FO.close();
		
		driver.close();
	}
}
