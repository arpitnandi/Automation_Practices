package utils;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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
	
	public static void writeData( String Value, String File, String Page, String Sheet, int row, int column) throws EncryptedDocumentException, IOException 
	{
		FileOutputStream FO = new FileOutputStream(File+Page.replace(' ','_')+".xlsx");
		XSSFWorkbook W = new XSSFWorkbook();
		XSSFSheet S = W.createSheet(Sheet);
		
		Row R = S.createRow(row);
		Cell C = R.createCell(column);
		C.setCellValue(Value);

		W.write(FO);
		W.close();
		FO.close();
	}
}
