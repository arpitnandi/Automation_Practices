package dropDown_Handling;

import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
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
	static WebDriverWait W;
	static Actions A; 
	public static void main( String[] args ) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		DriverCreation DC = new DriverCreation();
		driver = DC.driver( "firefox" );

		driver.manage().window().maximize();
		driver.get( "https://www.urbanladder.com/" );

		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		W = new WebDriverWait( driver, 20 );
		A = new Actions(driver);

		// Clicking the Close symbol for dismiss the banner
		WebElement Close = driver.findElement( By.xpath( "//a[contains(text(),'Close')]" ) );
		W.until( ExpectedConditions.elementToBeClickable( Close ) );
		Close.click();

		// Clicking the Cross symbol for dismiss the head-alert
		WebElement Cross = driver.findElement(By.xpath("//div[@class='right close-head-alert']/div"));
		W.until( ExpectedConditions.elementToBeClickable( Cross ) );
		Cross.click();

		// Common Xpath for all dropdown menus
		String MenusPath = "//ul[@class='topnav bodytext']/li[*]/span";
		
		// Common Xpath for all Lists inside each dropdown menus
		String ListsPath = "//div/div/ul/li[*]/div/a";
		
		// Common Xpath for all Items inside each list inside each dropdown menus
		String ListItemsPath = "//ul/li[*]/a/span";

		DropDownMenu.console(MenusPath, ListsPath, ListItemsPath);
		DropDownMenu.workbook(MenusPath, ListsPath, ListItemsPath);
		
		driver.close();
	}
	
	public static void console(String MenusPath, String ListsPath, String ListItemsPath)
	{
		// Identifying all DropDown Menus WebElements
		List<WebElement> Menus = driver.findElements( By.xpath( MenusPath ) );
		
		System.out.println( "Total DropDown Menus = "+ Menus.size() );
		
		for( int i = 1 ; i <= Menus.size() ; i++ )
		{	
			// Hovering the cursor on each dropdown menu to make the lists inside visible
			A.moveToElement( Menus.get(i-1) ).perform();
			
			// Printing Header text from each dropdown menu one-by-one
			System.out.print( "\nMenu[" + (i) + "] -> " + Menus.get(i-1).getText() );

			// Gathering all lists as WebElements -> present inside each dropdown menu one-by-one
			List<WebElement> Lists = ( List<WebElement> )driver.findElements( By.xpath( MenusPath.replace("[*]", "["+ i +"]" ) + "/.." + ListsPath ) );
			
			System.out.println( " , Total Lists inside = "+ Lists.size() );

			for( int j = 1 ; j <= Lists.size() ; j++ )
			{	
				W.until( ExpectedConditions.elementToBeClickable( Lists.get( j-1 ) ) );
				
				// Printing the list Headers -> present inside each dropdown menus as Column Headings
				System.out.print( "     List[" + j + "] -> " + Lists.get( j-1 ).getText() );
				
				// Gathering all items as WebElements -> present inside each list one-by-one -> present inside each dropdown menu
				List<WebElement> ListItems = ( List<WebElement> )driver.findElements( By.xpath( MenusPath.replace( "[*]", "["+i+"]" ) + "/.." + ListsPath.replace("[*]", "["+ j +"]") + "/../.." + ListItemsPath ) );
				System.out.print( " , List Items ("+ ListItems.size() +") -> [ " );
				
				// Printing all items -> present inside each list -> present inside each dropdown menu as Column values
				for( int k = 0; k < ListItems.size()-1 ; k++ )
				{
					// Hovering the cursor on each dropdown menu to make the lists inside visible
					A.moveToElement( Menus.get(i-1) ).perform();
					
					// Printing all items -> present inside each list -> present inside each dropdown menu as Column value
					System.out.print( ( ListItems.get( k ).getText() + ", " ) );
				}

				// Hovering the cursor on each dropdown menu to make the lists inside visible
				A.moveToElement( Menus.get(i-1) ).perform();
				
				// Printing the last item -> present inside each list -> present inside each dropdown menu as Column value
				System.out.println( ListItems.get( ListItems.size()-1 ).getText() + " ]" );
			}
		}
	}
	
	public static void workbook(String MenusPath, String ListsPath, String ListItemsPath) throws EncryptedDocumentException, InvalidFormatException, IOException 
	{
		String Page = driver.getTitle();
		String Path  = ".\\target\\Captured Data\\";

		FileOutputStream FO = new FileOutputStream( Path + Page.replace(':','-') +".xlsx");
		XSSFWorkbook Wb = new XSSFWorkbook();
		
		List<WebElement> Menus = driver.findElements( By.xpath( MenusPath ) );
		
		for( int i = 1 ; i <= Menus.size() ; i++ ) // Sheets creating for each Menu
		{	
			A.moveToElement( Menus.get( i-1 )).perform();
			
			Wb.createSheet( Menus.get( i-1 ).getText() );
			XSSFSheet S = Wb.getSheet( Menus.get( i-1 ).getText() );

			List<WebElement> Lists = ( List<WebElement> )driver.findElements( By.xpath( MenusPath.replace("[*]", "["+ i +"]" ) + "/.." + ListsPath ) );

			int I[] = new int[ Lists.size()+1 ]; 
			int J;
			
			for( int k = 0 ; k <= Lists.size() ; k++ ) // ListItems for each Lists
			{
				List<WebElement> ListItems = ( List<WebElement> )driver.findElements( By.xpath( MenusPath.replace( "[*]", "["+i+"]" ) + "/.." + ListsPath.replace("[*]", "["+ k +"]") + "/../.." + ListItemsPath ) );
				I[ k ] = ListItems.size() ;
			}
			J = DropDownMenu.biggest(I);

			for( int j = 0 ; j <= J ; j++ ) // Rows shifts with List Items
			{	
				XSSFRow R = S.createRow( j );
				
				for( int k = 1 ; k <= Lists.size() ; k++ ) // Columns shifts with Lists
				{
					List<WebElement> ListItems = ( List<WebElement> )driver.findElements( By.xpath( MenusPath.replace( "[*]", "["+i+"]" ) + "/.." + ListsPath.replace("[*]", "["+ k +"]") + "/../.." + ListItemsPath ) );
					
					int l = ListItems.size() ;
					
					XSSFCell C = R.createCell( k-1 );
					
					if( j == 0 )
					{
						WebElement List = driver.findElement( By.xpath( MenusPath.replace( "[*]", "["+i+"]" ) + "/.." + ListsPath.replace("[*]", "["+ k +"]") ) );
						W.until(ExpectedConditions.elementToBeClickable( List ) );
						C.setCellValue( List.getText() );
					}
					if( j >= 1 && j <= l )
					{
						WebElement Item = driver.findElement( By.xpath( MenusPath.replace( "[*]", "["+ i +"]" ) + "/.." + ListsPath.replace("[*]", "["+ k +"]") + "/../.." + ListItemsPath.replace( "[*]", "["+j+"]" ) ) );
						W.until(ExpectedConditions.elementToBeClickable( Item ) );
						C.setCellValue( Item.getText() );
					}	
				}
			}
		}
		
		Wb.write(FO);
		Wb.close();
		FO.flush();
		FO.close();
	}
	
	public static int biggest(int arr[]) 
	{
		int biggest = arr[0];
		
		for(int i=1;i<=arr.length-1;i++)
		{
			if(biggest<arr[i])
				biggest=arr[i];
		}
		
		return biggest;
	}
}