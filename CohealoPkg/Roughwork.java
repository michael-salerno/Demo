package CohealoPkg;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import InitializationPkg.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import UtilityPkg.ClickActionOnObjects;
import UtilityPkg.Element_Exist;
import UtilityPkg.GarbageValueClear;
import UtilityPkg.GetDate;
import UtilityPkg.ReadingTextFromObjects;
import UtilityPkg.SendingTestValueInTextBox;

public class Roughwork {

	
	public int i;
	
	
	@Test
	public void add()
	{
		System.out.println(GetDate.gettime());
	}
	

	
 /* WebDriver driver;
  String b , n;
  
  @BeforeTest
  public void beforetest() throws MalformedURLException
  {
	  b="http://www.google.com";
	  n="http://10.0.2.15:5566/wd/hub";
	     //File file = new File("C://Jars//IEDriverServer.exe");
	  File file = new File("C:\\IEDriverServer.exe");     
	  System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
	  DesiredCapabilities capability = DesiredCapabilities.firefox();
	  capability.setBrowserName("firefox");
	  capability.setPlatform(Platform.WINDOWS);
	 driver = new RemoteWebDriver(new URL(n),capability);
	  //driver = new InternetExplorerDriver(capability);	  
  }
  
  @Test
  public void simple()
  {
	  driver.get("b");
  }
  @AfterTest
  public void close()
  {
	  driver.close();
  }*/
/*	WebDriver driver;
	@Test
     public void simple() throws InterruptedException
     {
    	
    		WebDriverWait wait =new WebDriverWait(driver, 10);
    		Point h =driver.findElement(By.xpath(".//*[@id='content-wrapper']/div/div[1]/div[2]/div/div/div/div/div[1]")).getLocation();
    		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+(h.getY()-50)+");");
    		Thread.sleep(5000);
    		driver.findElement(By.xpath(".//*[@id='content-wrapper']/div/div[1]/div[2]/div/div/div/div/div[1]/div/span")).click();
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='booking-popup modal hide fade in']/div")));
    		driver.findElement(By.xpath("//a[@class='btn btn-primary edit-all']")).click();
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jGrowl-message']")));
    		driver.navigate().refresh();
    		Thread.sleep(10000);
    		driver.findElement(By.xpath(".//*[@id='content-wrapper']/div/div[1]/div[2]/div/div/div/div/div[1]/div/span")).click();
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='booking-popup modal hide fade in']/div")));
    		driver.findElement(By.xpath("//a[@class='btn btn-danger booking-delete']")).click();
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='delete-popup popup']")));
    		driver.findElement(By.xpath("//div[@class='booking-popup-content clearfix']/div[@class='delete-popup popup']/div/div[2]/div/a[1]")).click();
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jGrowl-message']")));
    		
    		Point h =driver.findElement(By.xpath(".//*[@id='content-wrapper']/div/div[1]/div[2]/div/div/div/div/div[1]")).getLocation();
    		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+(h.getY()-50)+");");
    		Thread.sleep(5000);
    		driver.findElement(By.xpath(".//*[@id='content-wrapper']/div/div[1]/div[2]/div/div/div/div/div[1]/div/span")).click();
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='booking-popup modal hide fade in']/div")));
    		driver.findElement(By.xpath("//a[@class='btn btn-primary edit-all']")).click();
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jGrowl-message']")));
    		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='jGrowl-message']")).getText().trim().contentEquals("You are not authorized to access this page."), true, "Error message displayed is not as expected");
    		driver.navigate().refresh();
    		Thread.sleep(10000);
    		driver.findElement(By.xpath(".//*[@id='content-wrapper']/div/div[1]/div[2]/div/div/div/div/div[1]/div/span")).click();
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='booking-popup modal hide fade in']/div")));
    		driver.findElement(By.xpath("//a[@class='btn btn-danger booking-delete']")).click();
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='delete-popup popup']")));
    		driver.findElement(By.xpath("//div[@class='booking-popup-content clearfix']/div[@class='delete-popup popup']/div/div[2]/div/a[1]")).click();
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jGrowl-message']")));
    		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='jGrowl-message']")).getText().trim().contentEquals("You are not authorized to access this page."), true, "Error message displayed is not as expected");
    		driver.navigate().refresh();
    		Thread.sleep(10000);
    
     }
	  @BeforeTest
	  public void beforetest() throws IOException, InterruptedException
	  {
			
		  System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\Browser_Driver\\IEDriverServer_Win32_2.37.0\\IEDriverServer.exe");
	 			
 		  driver = new InternetExplorerDriver(); 
 		  driver.get("http://stage.cohealo.com");
		  Thread.sleep(10000);
		//	FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"//src//Config/config.properties");
			
			//clearing the user name and password fields of login
			GarbageValueClear.Clear(driver, "user_email", "id");
			GarbageValueClear.Clear(driver, "user_password", "id");
			//Sending User name password values to user name password text boxes	
			SendingTestValueInTextBox.SendValue(driver, "id", "user_email", "demo-driver@test.com");
			SendingTestValueInTextBox.SendValue(driver, "id", "user_password", "Cohealo1");
			//click on Sign In button
			ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Sign in']");
			Thread.sleep(10000);
	  }*/
}
