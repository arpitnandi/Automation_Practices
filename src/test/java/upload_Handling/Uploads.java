package upload_Handling;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DriverCreation;

public class Uploads extends DriverCreation
{
	public static void main(String[] args) throws InterruptedException, AWTException 
	{
		DriverCreation DC = new DriverCreation();
		WebDriver driver = DC.driver("firefox");
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebDriverWait W = new WebDriverWait(driver, 5);
		
		//Loading google search page
		driver.get("https://gmail.com");

		// enter a valid email address
		driver.findElement(By.id("identifierId")).sendKeys("arpitnandi1@gmail.com");

		// click on Next in button
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		
		// enter a valid password
		W.until(ExpectedConditions.elementToBeClickable((By.xpath("//input[@name='password']"))));
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("#Diya@11");
		
		// click on Next in button
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		
		// click on compose button
		driver.findElement(By.xpath("//div[contains(text(),'Compose')]")).click();
		
		// click on attach files icon
		driver.findElement(By.xpath("//div[@class='bAK']/div[1]")).click();
		
		//Upload window code 1
		// creating instance of Robot class (A java based utility)
		Robot rb =new Robot();
		
		//Setting the upload location in backend key
		StringSelection Doc = new StringSelection("C:\\Users\\Arpith\\Desktop\\Arpit\\Office\\WindowHandling.docx");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(Doc, null);
		
		// pressing Enter key to upload the file
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		
		//Upload window code 2
//		try
//		{
//			Process exec = Runtime.getRuntime().exec("C:\\Users\\Arpith\\Desktop\\Arpit\\Office\\WindowHandling.docx");
//			int exitVal = exec.waitFor();
//			System.out.println("Exit value: " + exitVal);
//		}
//		catch (IOException ex)
//		{
//			System.out.println(ex.toString());
//		}
	}
}
