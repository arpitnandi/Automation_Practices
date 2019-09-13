package utils;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Methods extends DriverCreation
{
	public void waitForPageLoaded() 
	{
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() 
        {
        	public Boolean apply(WebDriver driver)
        	{
        		return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
        	}
        };
        try 
        {
        	Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(D, 30);
            wait.until(expectation);
        } 
        catch (Throwable error)
        {
        	Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
	
	public static void writeData( String Value, String Path, String Name, String Sheet, int row, int column) throws  IOException, EncryptedDocumentException, InvalidFormatException 
	{
		FileOutputStream FO = new FileOutputStream(Path+Name+".xlsx");		
		
		XSSFWorkbook W = new XSSFWorkbook();
		W.createSheet(Sheet);
		
		XSSFSheet S = W.getSheet(Sheet);
		S.createRow(row).createCell(column).setCellValue(Value);
		
		W.write(FO);
		W.close();
		FO.flush();
		FO.close();
	}
}
