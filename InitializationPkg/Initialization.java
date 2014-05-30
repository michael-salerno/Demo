package InitializationPkg;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Parameters;
import UtilityPkg.*;


public class Initialization {
	
	public static WebDriver driver;
	public static Properties CONFIG;
	public static Properties OR;
	
	public static Properties getconfig()
	{
		return CONFIG;
	}
	
	public static Properties getor()
	{
		return OR;
	}
  public static WebDriver getDriver()
    {
	    return driver;
	}
  @Parameters({"Browser"})
  @BeforeSuite
  public static void setTestSuite(String brwser) throws InterruptedException, IOException  {
	  //Initializing Webdriver object

	   if(brwser.equalsIgnoreCase("IE"))
	   {
 			 System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\Browser_Driver\\IEDriverServer_Win32_2.37.0\\IEDriverServer.exe");
 			
 			 driver = new InternetExplorerDriver();
 			 CONFIG = new Properties();
 			 OR = new Properties();
 			 driver.manage().deleteAllCookies();
 			 driver.manage().window().maximize();
 			 
 			 
 			 FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"//src//Config/config.properties");
 			 CONFIG.load(ip);
 			 FileInputStream ip1 = new FileInputStream(System.getProperty("user.dir")+"//src//Config/OR.properties");
 			 OR.load(ip1);
 			 driver.get(CONFIG.getProperty("URL"));
 			 Thread.sleep(10000);
 			
			 String url = driver.getCurrentUrl();
 			 Assert.assertEquals(url, CONFIG.getProperty("URL")+"users/sign_in", "Website is not navigated to login page");
 			 Assert.assertEquals(driver.getTitle(), "User Login", "Title of login page is not as expected");
 		 }
	   
	   if(brwser.equalsIgnoreCase("chrome"))
	   {
 			 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Browser_Driver\\chromedriver_win32\\chromedriver.exe");
 			
 			  driver = new ChromeDriver();
 			  driver.manage().deleteAllCookies();
 			  driver.manage().window().maximize();
 			  driver.get("https://stage.cohealo.com/");
 			  Thread.sleep(5000L);
 			  String Title = driver.getTitle();
 			  if(!Title.equalsIgnoreCase("User Login"))
 			  {
 				  Reporter.log("URL Navigate to wrong page");
 				  Assert.fail();
 			  }

 		 }
	   
	   if(brwser.equalsIgnoreCase("Firefox"))
	   {
 			  driver = new FirefoxDriver();
 			  driver.manage().deleteAllCookies();
 			  driver.manage().window().maximize();
 			  driver.get("http://stage.cohealo.com/");
 			  Thread.sleep(5000L);
 			  String Title = driver.getTitle();
 			  if(!Title.equalsIgnoreCase("App - Scheduler"))
 			  {
 				  Reporter.log("URL Navigate to wrong page");
 				  Assert.fail();
 			  }
 			  boolean flag = Element_Exist.existsElement("//*[@id='new_user']/div[4]/div[1]/a", driver);
 			  if(flag==false)
 			  {
 				  Reporter.log("Forgot password link not present");
 				  Assert.fail();
 			  }
 		 }
	  


	}

  @AfterSuite
  public void endTestSuite() 
    {
	  	driver.close();
	}




}
