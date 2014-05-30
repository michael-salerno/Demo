package CohealoPkg;
import InitializationPkg.*;
import UtilityPkg.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;


import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import org.testng.Assert;
import org.testng.Reporter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Login {
  @Test(dataProvider = "Login")
  public void Login_Cohealo(String Username , String Password , String Scenario) throws InterruptedException {
	  WebDriver driver = Initialization.getDriver();
	  Properties CONFIG = Initialization.getconfig();
	  //driver.manage().timeouts().implicitlyWait(10000,TimeUnit.SECONDS);
	
		//Checking Login Functionality for Both Invalid Scenario
	  if(Scenario.equalsIgnoreCase("Both Invalid"))
	  {
		  GarbageValueClear.Clear(driver, "user_email", "id");
		  GarbageValueClear.Clear(driver, "user_password", "id");
		  
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Username);
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_password", Password);
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Sign in']");
		  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Sign in']"))).click();
		  Thread.sleep(10000);
		  boolean flag = Element_Exist.existsElement("//div[@class='flash alert']", driver);
		  if(flag==true)
		  {
			  String s = ReadingTextFromObjects.GetText(driver, "//div[@class='flash alert']", "xpath");
			  if(s.contentEquals("Invalid email or password."))
			  {
				  Reporter.log("Login functionality working properly with Both invalid scenario");
			  }
			  else
			  {
				  Reporter.log("Expected message: Invalid email or password."+" "+"Displayed as:"+" "+s);
				  Assert.fail("Expected message: Invalid email or password."+" "+"Displayed as:"+" "+s);
			  }
			  
		  }
		  else
		  {
			  Reporter.log("Error message for Both Invalid scenario not displayed");
			  Assert.fail("Error message for Both Invalid scenario not displayed");
		  }
	  }
	  
		//Checking Login Functionality for Both Blank Scenario
	  if(Scenario.equalsIgnoreCase("Both Blank"))
	  {
		  GarbageValueClear.Clear(driver, "user_email", "id");
		  GarbageValueClear.Clear(driver, "user_password", "id");
		  
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Username);
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_password", Password);
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Sign in']");
		 // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Sign in']"))).click();
		  Thread.sleep(10000);
		  boolean flag = Element_Exist.existsElement("//div[@class='flash alert']", driver);
		  if(flag==true)
		  {
			  String s = ReadingTextFromObjects.GetText(driver, "//div[@class='flash alert']", "xpath");
			  if(s.contentEquals("Invalid email or password."))
			  {
				  Reporter.log("Login functionality working properly with Both Blank scenario");
			  }
			  else
			  {
				  Reporter.log("Expected message: Invalid email or password."+" "+"Displayed as:"+" "+s);
				  Assert.fail("Expected message: Invalid email or password."+" "+"Displayed as:"+" "+s);
			  }
			  
		  }
		  else
		  {
			  Reporter.log("Error message for Both Blank scenario not displayed");
			  Assert.fail("Error message for Both Blank scenario not displayed");
		  }
	  }
	  
		//Checking Login Functionality for Valid User name and Blank Password Scenario
	  if(Scenario.equalsIgnoreCase("Valid Username and Blank Password"))
	  {
		  GarbageValueClear.Clear(driver, "user_email", "id");
		  GarbageValueClear.Clear(driver, "user_password", "id");
		  
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Username);
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_password", Password);
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Sign in']");
		 // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Sign in']"))).click();
		  Thread.sleep(10000);
		  boolean flag = Element_Exist.existsElement("//div[@class='flash alert']", driver);
		  if(flag==true)
		  {
			  String s = ReadingTextFromObjects.GetText(driver, "//div[@class='flash alert']", "xpath");
			  if(s.contentEquals("Invalid email or password."))
			  {
				  Reporter.log("Login functionality working properly with Valid Username and Blank Password scenario");
			  }
			  else
			  {
				  Reporter.log("Expected message: Invalid email or password."+" "+"Displayed as:"+" "+s);
				  Assert.fail("Expected message: Invalid email or password."+" "+"Displayed as:"+" "+s);
			  }
			  
		  }
		  else
		  {
			  Reporter.log("Error message for Valid Username and Blank Password scenario not displayed");
			  Assert.fail("Error message for Valid Username and Blank Password scenario not displayed");
		  }
	  }
	  
		//Checking Login Functionality for Invalid User name and Blank Password Scenario
	  if(Scenario.equalsIgnoreCase("Invalid Username and Blank Password"))
	  {
		  GarbageValueClear.Clear(driver, "user_email", "id");
		  GarbageValueClear.Clear(driver, "user_password", "id");
		  
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Username);
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_password", Password);
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Sign in']");
		  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Sign in']"))).click();
		  Thread.sleep(10000);
		  boolean flag = Element_Exist.existsElement("//div[@class='flash alert']", driver);
		  if(flag==true)
		  {
			  String s = ReadingTextFromObjects.GetText(driver, "//div[@class='flash alert']", "xpath");
			  if(s.contentEquals("Invalid email or password."))
			  {
				  Reporter.log("Login functionality working properly with Invalid Username and Blank Password scenario");
			  }
			  else
			  {
				  Reporter.log("Expected message: Invalid email or password."+" "+"Displayed as:"+" "+s);
				  Assert.fail("Expected message: Invalid email or password."+" "+"Displayed as:"+" "+s);
			  }
			  
		  }
		  else
		  {
			  Reporter.log("Error message for Invalid Username and Blank Password not displayed");
			  Assert.fail("Error message for Invalid Username and Blank Password not displayed");
		  }
	  }
	  
		//Checking Login Functionality for Invalid User name and Valid Password name Scenario
	  if(Scenario.equalsIgnoreCase("Invalid Username and Valid Password"))
	  {
		  GarbageValueClear.Clear(driver, "user_email", "id");
		  GarbageValueClear.Clear(driver, "user_password", "id");
		  
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Username);
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_password", Password);
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Sign in']");
		  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Sign in']"))).click();
		  Thread.sleep(10000);

		  boolean flag = Element_Exist.existsElement("//div[@class='flash alert']", driver);
		  if(flag==true)
		  {
			  String s = ReadingTextFromObjects.GetText(driver, "//div[@class='flash alert']", "xpath");
			  if(s.contentEquals("Invalid email or password."))
			  {
				  Reporter.log("Login functionality working properly with Invalid Username and Valid Password scenario");
			  }
			  else
			  {
				  Reporter.log("Expected message: Invalid email or password."+" "+"Displayed as:"+" "+s);
				  Assert.fail("Expected message: Invalid email or password."+" "+"Displayed as:"+" "+s);
			  }
			  
		  }
		  else
		  {
			  Reporter.log("Error message for Invalid Username and Valid Password scenario not displayed");
			  Assert.fail("Error message for Invalid Username and Valid Password scenario not displayed");
		  }
	  }
	  
		//Checking Login Functionality for Valid User name and Invalid Password name Scenario
	  if(Scenario.equalsIgnoreCase("Valid Username and Invalid Password"))
	  {
		  GarbageValueClear.Clear(driver, "user_email", "id");
		  GarbageValueClear.Clear(driver, "user_password", "id");
		  
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Username);
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_password", Password);
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Sign in']");
		  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Sign in']"))).click();
		  Thread.sleep(10000);

		  boolean flag = Element_Exist.existsElement("//div[@class='flash alert']", driver);
		  if(flag==true)
		  {
			  String s = ReadingTextFromObjects.GetText(driver, "//div[@class='flash alert']", "xpath");
			  if(s.contentEquals("Invalid email or password."))
			  {
				  Reporter.log("Login functionality working properly with Valid Username and Invalid Password scenario");
			  }
			  else
			  {
				  Reporter.log("Expected message: Invalid email or password."+" "+"Displayed as:"+" "+s);
				  Assert.fail("Expected message: Invalid email or password."+" "+"Displayed as:"+" "+s);
			  }
			  
		  }
		  else
		  {
			  Reporter.log("Error message for Valid Username and Invalid Password scenario not displayed");
			  Assert.fail("Error message for Valid Username and Invalid Password scenario not displayed");
		  }
	  }
	  
		//Checking Login Functionality for Valid inputs Scenario
	  if(Scenario.equalsIgnoreCase("Valid inputs"))
	  {
		  GarbageValueClear.Clear(driver, "user_email", "id");
		  GarbageValueClear.Clear(driver, "user_password", "id");
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", CONFIG.getProperty("ROMUser"));
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_password", CONFIG.getProperty("Password"));
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Sign in']");
		  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Sign in']"))).click();
		  Thread.sleep(15000);
		 
		
			  boolean flag = Element_Exist.existsElement("//*[@id='content-wrapper']/div/div[1]", driver);
			  if(flag==true)
			  {
				  Reporter.log("Login functionality working properly with Both Valid scenario");
			  }
			  else
			  {
				  Reporter.log("Home page not displayed");
				  Assert.fail("Home page not displayed");
			  }
			  
			  
			  boolean flag1 = Element_Exist.existsElement("//*[@id='session-nav']/div[2]/a", driver);
			  if(flag1==true)
			  {
				  ClickActionOnObjects.ClickByJavascript(driver, "//a[@class='dropdown-toggle btn btn-primary btn-small']");
				  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='session-nav']/div[2]/a"))).click();
				  Thread.sleep(5000);
				  ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='/users/sign_out']/span");
				  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='session-nav']/div[2]/ul/li[3]/a/span"))).click();
				  Thread.sleep(10000);
				  boolean flag2 = Element_Exist.existsElement("//*[@id='new_user']/h1", driver);
				  if(flag2==true)
				  {
					  Reporter.log("Logout Functionality working properly");
				  }
				  else
				  {
					  Reporter.log("After Logout page navigate to:-"+driver.getCurrentUrl()+"Expected to:-https://.cohealo.com/users/sign_in");
					  Assert.fail("After Logout page navigate to:-"+driver.getCurrentUrl()+"Expected to:-https://.cohealo.com/users/sign_in");
				  }
			  }
			  
			  else
			  {
				  Reporter.log("My Account drop down list not displayed");
				  Assert.fail("My Account drop down list not displayed");
			  }
	  	}
	  
		//Checking Login Functionality for Blank User name and Valid Password Scenario
	  if(Scenario.equalsIgnoreCase("Blank Username and Valid Password"))
	  {
		  GarbageValueClear.Clear(driver, "user_email", "id");
		  GarbageValueClear.Clear(driver, "user_password", "id");
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Username);
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_password", Password);
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Sign in']");
		  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Sign in']"))).click();
		  Thread.sleep(10000);
		  boolean flag = Element_Exist.existsElement("//div[@class='flash alert']", driver);
		  if(flag==true)
		  {
			  String s = ReadingTextFromObjects.GetText(driver, "//div[@class='flash alert']", "xpath");
			  if(s.contentEquals("Invalid email or password."))
			  {
				  Reporter.log("Login functionality working properly with Valid Username and Invalid Password scenario");
			  }
			  else
			  {
				  Reporter.log("Expected message: Invalid email or password."+" "+"Displayed as:"+" "+s);
				  Assert.fail("Expected message: Invalid email or password."+" "+"Displayed as:"+" "+s);
			  }
			  
		  }
		  else
		  {
			  Reporter.log("Error message for Valid Username and Invalid Password scenario not displayed");
			  Assert.fail("Error message for Valid Username and Invalid Password scenario not displayed");
		  }
		  
	  }
	  
	  
		//Checking Login Functionality for Blank User name and Invalid Password Scenario
	  if(Scenario.equalsIgnoreCase("Blank Username and Invalid Password"))
	  {
		  GarbageValueClear.Clear(driver, "user_email", "id");
		  GarbageValueClear.Clear(driver, "user_password", "id");
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Username);
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_password", Password);
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Sign in']");
		  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Sign in']"))).click();
		  Thread.sleep(10000);
		  boolean flag = Element_Exist.existsElement("//div[@class='flash alert']", driver);
		  if(flag==true)
		  {
			  String s = ReadingTextFromObjects.GetText(driver, "//div[@class='flash alert']", "xpath");
			  if(s.contentEquals("Invalid email or password."))
			  {
				  Reporter.log("Login functionality working properly with Valid Username and Invalid Password scenario");
			  }
			  else
			  {
				  Reporter.log("Expected message: Invalid email or password."+" "+"Displayed as:"+" "+s);
				  Assert.fail("Expected message: Invalid email or password."+" "+"Displayed as:"+" "+s);
			  }
			  
		  }
		  else
		  {
			  Reporter.log("Error message for Valid Username and Invalid Password scenario not displayed");
			  Assert.fail("Error message for Valid Username and Invalid Password scenario not displayed");
		  }
		  
	  }
	  
	//Checking Login Functionality for SQL injection Scenario
	  if(Scenario.equalsIgnoreCase("SQL injections"))
	  {
		  GarbageValueClear.Clear(driver, "user_email", "id");
		  GarbageValueClear.Clear(driver, "user_password", "id");
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Username);
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_password", Password);
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Sign in']");
		  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Sign in']"))).click();
		  Thread.sleep(10000);
		  boolean flag = Element_Exist.existsElement("//label[@class='error' and @for='user_email']", driver);
		  if(flag==true)
		  {
			  String s = ReadingTextFromObjects.GetText(driver, "//label[@class='error' and @for='user_email']", "xpath");
			  if(s.contentEquals("Please enter a valid email address."))
			  {
				  Reporter.log("Login functionality working properly with Valid Username and Invalid Password scenario");
			  }
			  else
			  {
				  Reporter.log("Expected message: Please enter a valid email address."+" "+"Displayed as:"+" "+s);
				  Assert.fail("Expected message: Please enter a valid email address."+" "+"Displayed as:"+" "+s);
			  }
			  
		  }
		  else
		  {
			  Reporter.log("Error message for Valid Username and Invalid Password scenario not displayed");
			  Assert.fail("Error message for Valid Username and Invalid Password scenario not displayed");
		  }
		  
	  }
	  
	  if(Scenario.equalsIgnoreCase("ROM user Login"))
	  {
		  GarbageValueClear.Clear(driver, "user_email", "id");
		  GarbageValueClear.Clear(driver, "user_password", "id");
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", CONFIG.getProperty("ROMUser"));
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_password", CONFIG.getProperty("Password"));
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Sign in']");
		  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Sign in']"))).click();
		  Thread.sleep(8000);
		  Assert.assertEquals(Element_Exist.existsElement("//i[@class='icon icon-cogs']", driver),true,"Entity Manager tab is not present");
		  ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon icon-cogs']");
		  Thread.sleep(8000);
		  Assert.assertEquals(Element_Exist.existsElement("//div[@class='entity-navigation']", driver),true,"Entity navigation is not present");
		  WebElement Left_menu = driver.findElement(By.xpath("//div[@class='entity-navigation']"));
		  Assert.assertEquals(Left_menu.findElements(By.tagName("li")).size(),6, "Number of menus and submenus not matching"); 
		  Reporter.log("Number of options of menus and submenus are correct");

		  ClickActionOnObjects.ClickByJavascript(driver, "//a[@class='dropdown-toggle btn btn-primary btn-small']");
		  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='session-nav']/div[2]/a"))).click();
		  Thread.sleep(10000);
		  ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='/users/sign_out']/span");
		  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='session-nav']/div[2]/ul/li[3]/a/span"))).click();
		  Thread.sleep(10000);
	  }
	  
	  if(Scenario.equalsIgnoreCase("Consumer user Login"))
	  {
		  GarbageValueClear.Clear(driver, "user_email", "id");
		  GarbageValueClear.Clear(driver, "user_password", "id");
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", CONFIG.getProperty("ConsumerUser"));
		  SendingTestValueInTextBox.SendValue(driver, "id", "user_password", CONFIG.getProperty("Password"));
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Sign in']");
		  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Sign in']"))).click();
		  Thread.sleep(15000);
		  Assert.assertEquals(Element_Exist.existsElement("//i[@class='icon icon-cogs']", driver),false, "Entity Navigation menus is displaying for consumer user");
		  ClickActionOnObjects.ClickByJavascript(driver, "//a[@class='dropdown-toggle btn btn-primary btn-small']");
		  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='session-nav']/div[2]/a"))).click();
		  Thread.sleep(10000);
		  ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='/users/sign_out']/span");
		  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='session-nav']/div[2]/ul/li[3]/a/span"))).click();
		  Thread.sleep(10000);
	  }
	  

  }
  
  @Test(dataProvider = "Forgot_Password")
  public void Forgot_Password(String Email , String Scenario) throws InterruptedException
  {
	  	  WebDriver driver = Initialization.getDriver();
	  	Properties CONFIG = Initialization.getconfig();
	  	  //driver.manage().timeouts().implicitlyWait(10000,TimeUnit.SECONDS);
	  	  
	  	 
		  boolean flag = Element_Exist.existsElement("//*[@id='new_user']/div[4]/div[1]/a", driver);
		  if(flag==false)
		  {
			  Reporter.log("Forgot password link not present");
			  Assert.fail("Forgot password link not present");
		  }
		  
		  else
		  {   
			  ClickActionOnObjects.ClickByJavascript(driver, "//*[@id='new_user']/div[4]/div[1]/a");
			  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='new_user']/div[4]/div[1]/a"))).click();
			  Thread.sleep(15000L);
			  if(ReadingTextFromObjects.GetText(driver, "//*[@id='new_user']/h1", "xpath").contentEquals("Forgot Password"))
			  {
				  if(Scenario.equalsIgnoreCase("Blank Email/Username"))
				  {
					  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Email);
					  Thread.sleep(1000);
					  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Recover Account']");
					  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Recover Account']"))).click();
					  Thread.sleep(10000);
					  if(!Element_Exist.existsElement("//label[@class='error']", driver))
					  {
						  Reporter.log("Error message for Blank Email/Username is not displaying");
						  Assert.fail("Error message for Blank Email/Username is not displaying");
					  }
					  else
					  {
						  Reporter.log("Forgot Password functionality working properly with Blank Email/Username Scenario");
					  }
					  if(!Element_Exist.existsElement("//i[@class='icon icon-circle-arrow-left']/span", driver))
					  {
						  Reporter.log("Back to Sign in link not present at Forgot Password page");
						  Assert.fail("Back to Sign in link not present at Forgot Password page");
					  }
					  
					  else
					  {
						  ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon icon-circle-arrow-left']/span");
						  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='icon icon-circle-arrow-left']/span"))).click();
						  Thread.sleep(10000);
						  if(!driver.getCurrentUrl().contentEquals(CONFIG.getProperty("URL")+"users/sign_in"))
						  {
							  Reporter.log("Page navigate to:-"+driver.getCurrentUrl()+" "+"Expected to:-"+CONFIG.getProperty("URL")+"users/sign_in");
						      Assert.fail("Page navigate to:-"+driver.getCurrentUrl()+" "+"Expected to:-"+CONFIG.getProperty("URL")+"users/sign_in");
						  }
					  }
				  }
				  
				  if(Scenario.equalsIgnoreCase("Invalid Email/Username"))
				  {
					  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Email);
					  Thread.sleep(1000);
					  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Recover Account']");
					  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Recover Account']"))).click();
					  Thread.sleep(10000);
					  if(!driver.getCurrentUrl().contentEquals(CONFIG.getProperty("URL")+"users/password/new"))
					  {
						  Reporter.log("Page Navigate to:-"+driver.getCurrentUrl()+" "+"Expected to:-"+CONFIG.getProperty("URL")+"users/password/new");
						  
						  Assert.fail("Page Navigate to:-"+driver.getCurrentUrl()+" "+"Expected to:-"+CONFIG.getProperty("URL")+"users/password/new");
					  }
					  else
					  {
						  Reporter.log("Forgot Password functionality working properly with Invalid Email/Username Scenario");
						  
					  }
					  
					  if(!Element_Exist.existsElement("//i[@class='icon icon-circle-arrow-left']/span", driver))
					  {
						  Reporter.log("Back to Sign in link not present at Forgot Password page");
						  Assert.fail("Back to Sign in link not present at Forgot Password page");
					  }
					  
					  else
					  {
						  ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon icon-circle-arrow-left']/span");
						  
						  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='icon icon-circle-arrow-left']/span"))).click();
						  Thread.sleep(10000);
						  if(!driver.getCurrentUrl().contentEquals(CONFIG.getProperty("URL")+"users/sign_in"))
						  {
							  Reporter.log("Page navigate to:-"+driver.getCurrentUrl()+" "+"Expected to:-"+CONFIG.getProperty("URL")+"users/sign_in");
						      Assert.fail("Page navigate to:-"+driver.getCurrentUrl()+" "+"Expected to:-"+CONFIG.getProperty("URL")+"users/sign_in");
						  }
					  }
				  }
				  
				  if(Scenario.equalsIgnoreCase("Valid Email/Username"))
				  {
					  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", CONFIG.getProperty("ROMUser"));
					  Thread.sleep(1000);
					  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Recover Account']");
					  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Recover Account']"))).click();
					  Thread.sleep(15000);
					  if(!driver.getCurrentUrl().contentEquals(CONFIG.getProperty("URL")+"users/sign_in"))
					  {
						  Reporter.log("Page Navigate to:-"+driver.getCurrentUrl()+" "+"Expected to:-"+CONFIG.getProperty("URL")+"users/sign_in");		  
						  Assert.fail("Page Navigate to:-"+driver.getCurrentUrl()+" "+"Expected to:-"+CONFIG.getProperty("URL")+"users/sign_in");
					  }
					  
					  else
					  {
						  if(!Element_Exist.existsElement("//div[@class='flash alert']", driver))
						  {
							  Reporter.log("Confirmation message for Reset password is not displayed");
							  Assert.fail("Confirmation message for Reset password is not displayed");
						  }
						  
						  else
						  {
							  Reporter.log("Forgot Password for Valid Email/Username is working properly");
						  }
					  }
					  
				  }
			  }
			  else
			  {
				  Reporter.log("Page navigate to:-"+driver.getCurrentUrl()+" "+"Expected to:-"+CONFIG.getProperty("URL")+"users/password/new");
				  Assert.fail("Page navigate to:-"+driver.getCurrentUrl()+" "+"Expected to:-"+CONFIG.getProperty("URL")+"users/password/new");
			  }
			  
		  }
  }
  
  @Test(dataProvider = "Create User")
  public void create_user(String Email , String Scenario) throws InterruptedException, IOException
  {
	  WebDriver driver = Initialization.getDriver();
	  Properties CONFIG = Initialization.getconfig();
	  //driver.manage().timeouts().implicitlyWait(10000,TimeUnit.SECONDS);
	  
	 
	  boolean flag = Element_Exist.existsElement("//div[@class='row-fluid create-account']/div/a", driver);
	  if(flag==false)
	  {
		  Reporter.log("Create Account Button is not present");
		  Assert.fail("Create Account Button is not present");
	  }
	  else
	  {
		  ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='row-fluid create-account']/div/a");
		  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='row-fluid create-account']/div/a"))).click();
		  Thread.sleep(10000);

		  if(driver.getCurrentUrl().contentEquals(CONFIG.getProperty("URL")+"users/sign_up"))
		  {
			  if(Scenario.equalsIgnoreCase("Blank Email Address"))
			  {
				  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Email);
				  Thread.sleep(1000);
				  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Create Account']");
				  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Create Account']"))).click();
				  Thread.sleep(5000);
				  if(Element_Exist.existsElement("//label[@class='error' and @for='user_email']", driver)||Element_Exist.existsElement("//div[@class='flash alert']", driver) )
				  {
					  if(Element_Exist.existsElement("//label[@class='error' and @for='user_email']", driver))
					  {
						  if(ReadingTextFromObjects.GetText(driver, "//label[@class='error' and @for='user_email']", "xpath").contains("This field is required."))
						  {
							  Reporter.log("Create Account functionality working properly for Blank Email address");
						  }
						  else
						  {
								  Reporter.log("Actual Error message displayed:-"+ReadingTextFromObjects.GetText(driver, "//*[@id='new_user']/div[2]/div/label", "xpath")+" "+"Expected :-This field is required.");
								  ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon icon-circle-arrow-left']/span");
								  Thread.sleep(2000);
								  Assert.fail("Actual Error message displayed:-"+ReadingTextFromObjects.GetText(driver, "//*[@id='new_user']/div[2]/div/label", "xpath")+" "+"Expected :-This field is required.");
						  }
					  }  
					
					  if(Element_Exist.existsElement("//div[@class='flash alert']", driver))
						  {
							  if(ReadingTextFromObjects.GetText(driver, "//div[@class='flash alert']", "xpath").contains("Email domain unknown"))
							  {
								  Reporter.log("Create Account functionality working properly for Blank Email address");
							  }
							  else
							  {
								  Reporter.log("Actual Error message displayed:-"+ReadingTextFromObjects.GetText(driver, "//div[@class='flash alert']", "xpath")+" "+"Expected :-Email domain unknown");
								  ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon icon-circle-arrow-left']/span");
								  Thread.sleep(2000);
								  Assert.fail("Actual Error message displayed:-"+ReadingTextFromObjects.GetText(driver, "//div[@class='flash alert']", "xpath")+" "+"Expected :-Email domain unknown");
							  }
						  }
					  
				  }
				  else
				  {
					  Reporter.log("No Error Message displayed");
					  ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon icon-circle-arrow-left']/span");
					  Thread.sleep(2000);
					  Assert.fail("No Error Message displayed");
				  }
				  
				  if(Element_Exist.existsElement("//i[@class='icon icon-circle-arrow-left']/span", driver))
				  {
					  Thread.sleep(1000);
					  ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon icon-circle-arrow-left']/span");
					  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='icon icon-circle-arrow-left']/span"))).click();
					  Thread.sleep(10000);
					  if(!driver.getCurrentUrl().contentEquals(CONFIG.getProperty("URL")+"users/sign_in"))
					  {
						  Reporter.log("Page Navigate after clicking on 'Have an account? Sign in.' link to :-"+driver.getCurrentUrl()+" "+"Expected to:-"+CONFIG.getProperty("URL")+"users/sign_in");
						  Assert.fail("Page Navigate after clicking on 'Have an account? Sign in.' link to :-"+driver.getCurrentUrl()+" "+"Expected to:-"+CONFIG.getProperty("URL")+"users/sign_in");
					  }
				  }
				  else
				  {
					  Reporter.log("Have an account? Sign in. link not present");
					  Assert.fail("Have an account? Sign in. link not present");
				  }
			  }
			  if(Scenario.equalsIgnoreCase("Invalid Email Address"))
			  {
				  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Email);
				  Thread.sleep(1000);
				  //ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon icon-user muted']");
				  driver.findElement(By.xpath("//input[@value='Create Account']")).click();
				  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Create Account']"))).click();
				  Thread.sleep(5000);
				  if(Element_Exist.existsElement("//label[@class='error' and @for='user_email']", driver))
				  {
					   if(ReadingTextFromObjects.GetText(driver, "//label[@class='error' and @for='user_email']", "xpath").contains("Please enter a valid email address."))
						  {
							  Reporter.log("Create Account functionality working properly for Blank Email address");
						  }
						  else
						  {
								  Reporter.log("Actual Error message displayed:-"+ReadingTextFromObjects.GetText(driver, "//label[@class='error' and @for='user_email']", "xpath")+" "+"Expected :-Please enter a valid email address.");
								  ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon icon-circle-arrow-left']/span");
								  Thread.sleep(2000);
								  Assert.fail("Actual Error message displayed:-"+ReadingTextFromObjects.GetText(driver, "//label[@class='error' and @for='user_email']", "xpath")+" "+"Expected :-Please enter a valid email address.");
						  }
					  
				  }
				  else
				  {
					  Reporter.log("No Error Message displayed");
					  Assert.fail("No Error Message displayed");
				  }
				  
				  if(Element_Exist.existsElement("//i[@class='icon icon-circle-arrow-left']/span", driver))
				  {
					  ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon icon-circle-arrow-left']/span");
					  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='icon icon-circle-arrow-left']/span"))).click();
					  Thread.sleep(5000);
					  if(!driver.getCurrentUrl().contentEquals(CONFIG.getProperty("URL")+"users/sign_in"))
					  {
						  Reporter.log("Page Navigate after clicking on 'Have an account? Sign in.' link to :-"+driver.getCurrentUrl()+" "+"Expected to:-"+CONFIG.getProperty("URL")+"users/sign_in");
						  Assert.fail("Page Navigate after clicking on 'Have an account? Sign in.' link to :-"+driver.getCurrentUrl()+" "+"Expected to:-"+CONFIG.getProperty("URL")+"users/sign_in");
					  }
				  }
				  else
				  {
					  Reporter.log("Have an account? Sign in. link not present");
					  Assert.fail("Have an account? Sign in. link not present");
				  }
			  }
			  if(Scenario.equalsIgnoreCase("Email with different Domain"))
			  {
				  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Email);
				  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Create Account']");
				  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Create Account']"))).click();
				  Thread.sleep(2000);
				  if(Element_Exist.existsElement("//div[@class='flash alert']", driver))
				  {
					  if(ReadingTextFromObjects.GetText(driver, "//div[@class='flash alert']", "xpath").contains("Email domain unknown"))
					  {
						  Reporter.log("Create Account functionality working properly for Email address with different domain");
					  }
					  else
					  {
						  Reporter.log("Actual Error message displayed:-"+ReadingTextFromObjects.GetText(driver, "//*[@id='new_user']/div[2]", "xpath")+" "+"Expected :-Email domain unknown");
						  Assert.fail("Actual Error message displayed:-"+ReadingTextFromObjects.GetText(driver, "//*[@id='new_user']/div[2]", "xpath")+" "+"Expected :-Email domain unknown");
					  }
				  }
				  else
				  {
					  Reporter.log("No Error Message displayed");
					  Assert.fail("No Error Message displayed");
				  }
				  
				  if(Element_Exist.existsElement("//i[@class='icon icon-circle-arrow-left']/span", driver))
				  {
					  ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon icon-circle-arrow-left']/span");
					  //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='icon icon-circle-arrow-left']/span"))).click();
					  Thread.sleep(6000);
					  if(!driver.getCurrentUrl().contentEquals(CONFIG.getProperty("URL")+"users/sign_in"))
					  {
						  Reporter.log("Page Navigate after clicking on 'Have an account? Sign in.' link to :-"+driver.getCurrentUrl()+" "+"Expected to:-"+CONFIG.getProperty("URL")+"users/sign_in");
						  Assert.fail("Page Navigate after clicking on 'Have an account? Sign in.' link to :-"+driver.getCurrentUrl()+" "+"Expected to:-"+CONFIG.getProperty("URL")+"users/sign_in");
					  }
				  }
				  else
				  {
					  Reporter.log("Have an account? Sign in. link not present");
					  Assert.fail("Have an account? Sign in. link not present");
				  }
			  }
			  
			  if(Scenario.contentEquals("Valid Inputs"))
			  {
				  WebDriverWait wait = new WebDriverWait(driver, 30);
				  Calendar cal=Calendar.getInstance();
				  int date = cal.get(Calendar.DATE);
				  int month = cal.get(Calendar.MONTH);
				  int year = cal.get(Calendar.YEAR);
				  int time = cal.get(Calendar.MINUTE);
				  int milisec = cal.get(Calendar.MILLISECOND);
				  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", "test"+Integer.toString(date)+Integer.toString(month)+Integer.toString(year)+Integer.toString(time)+Integer.toString(milisec)+"@cohealo.com");
				  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Create Account']");
				  Thread.sleep(5000);
				  Assert.assertEquals(driver.getTitle().trim().contains("The page you were looking for doesn't exist."), false, "Error Message The page you were looking for doesn't exist. displaying after clicking on Create Account button");
				  WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='flash alert']")));
				  Thread.sleep(5000);
				  String msg = element.getText();
				  Assert.assertEquals(Element_Exist.existsElement("//div[@class='flash alert']", driver), true, "No confirmation message displayed");
				  if(element.getText().contentEquals("You will receive a login link in your email momentarily."))
				  {		/*String Main_window = driver.getWindowHandle().toString();
						 System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\Browser_Driver\\IEDriverServer_Win32_2.37.0\\IEDriverServer.exe");
							
						 WebDriver driver = new InternetExplorerDriver();
						 
						 driver1.manage().deleteAllCookies();
						 driver1.manage().window().maximize();*/
					  
					  
					  driver.get("http://mailtrap.io/auth/sessions/new");
					  
					  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", "atul.nikam@clariontechnologies.co.in");
					  SendingTestValueInTextBox.SendValue(driver, "id", "user_password", "atul@123");
					  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Sign In']");
					  Thread.sleep(8000);
					  driver.findElement(By.xpath("//*[@id='main']/div/div[2]/div/div[2]/div/div[2]/table/tbody/tr[1]/td[1]/div[1]/strong/a/span")).click();
					  //driver.findElement(By.linkText("Cohealo Stage")).click();
					  Thread.sleep(5000);
					  driver.findElement(By.xpath("//i[@class='icon-reload']")).click();
					  Thread.sleep(5000);
					  if(ReadingTextFromObjects.GetText(driver, "//*[@id='main']/div/div[1]/div/ul/li[1]/div/span[1]/span", "xpath").trim().contentEquals("test"+Integer.toString(date)+Integer.toString(month)+Integer.toString(year)+Integer.toString(time)+Integer.toString(milisec)+"@cohealo.com"))
					  {
						  driver.findElement(By.xpath("//*[@id='main']/div/div[1]/div/ul/li[1]/div/span[1]/span")).click();
						  Thread.sleep(5000);
						  driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='main']/div/div[2]/div/div[2]/div[2]/div[1]/iframe")));
						  Assert.assertEquals(existsElementbylinktext("Activate Account" , driver), true, "Activate account button is not present at mail");
						  driver.findElement(By.linkText("Activate Account")).click();
						  Thread.sleep(10000);
						  String parentwindow = (String) driver.getWindowHandles().toArray()[0];
						  String childwindow = (String) driver.getWindowHandles().toArray()[1];
						  driver.switchTo().window(childwindow);
						  Thread.sleep(5000);
						  driver.manage().window().maximize();
						  Assert.assertEquals(driver.getTitle(), "Account", "Page title is not as expected");
						  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//h1", "xpath"), "Activate Account", "Header of Page is not as expected");
						  Assert.assertEquals(Element_Exist.existsElement("//select[@id='facility_facility_id']", driver), true, "Facility dropdown is not present");
						  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_first_name']", driver), true, "First Name textbox is not present");
						  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_last_name']", driver), true, "Last Name textbox is not present");
						  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_position']", driver), true, "Position textbox is not present");
						  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_contact_attributes_office_phone']", driver), true, "Office Phone textbox is not present");
						  Assert.assertEquals(Element_Exist.existsElement("//input[@id='edit-password']", driver), true, "New password textbox is not present");
						  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_password_confirmation']", driver), true, "Confirm password textbox is not present");
						  Assert.assertEquals(Element_Exist.existsElement("//div[@id='gen-btn']", driver), true, "Generate password button is not present");
						  Assert.assertEquals(Element_Exist.existsElement("//input[@value='Activate Account']", driver), true, "Activate Account button is not present");
						  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Activate Account']");
						  Thread.sleep(5000);
						  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//label[@for='user_first_name']", "xpath"), "This field is required.", "No Error message displayed for blank first name");
						  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//label[@for='user_last_name']", "xpath"), "This field is required.", "No Error message displayed for blank last name");
						  SendingTestValueInTextBox.SendValue(driver, "id", "user_first_name", "Test");
						  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Activate Account']");
						  Thread.sleep(5000);
						  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//label[@for='user_first_name']", "xpath"), "", "Error message displayed for first name even if it is populated");
						  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//label[@for='user_last_name']", "xpath"), "This field is required.", "No Error message displayed for blank last name");
						  GarbageValueClear.Clear(driver, "user_first_name", "id");
						  GarbageValueClear.Clear(driver, "user_last_name", "id");
						  GarbageValueClear.Clear(driver, "user_position", "id");
						  GarbageValueClear.Clear(driver, "user_contact_attributes_office_phone", "id");
						  GarbageValueClear.Clear(driver, "edit-password", "id");
						  GarbageValueClear.Clear(driver, "user_password_confirmation", "id");
						  SendingTestValueInTextBox.SendValue(driver, "id", "user_first_name", "Test");
						  SendingTestValueInTextBox.SendValue(driver, "id", "user_last_name", "Test");
						  SendingTestValueInTextBox.SendValue(driver, "id", "user_position", "Test");
						  SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_office_phone", "5555555555");
						  SendingTestValueInTextBox.SendValue(driver, "id", "edit-password","Test@123");
						  SendingTestValueInTextBox.SendValue(driver, "id", "user_password_confirmation","Test@123");
						  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Activate Account']");
						  Thread.sleep(5000);
						 
						  if(driver.getTitle().contentEquals("My Bookings"))
						  {	
							driver.close();
							driver.switchTo().window(parentwindow);
							driver.get("http://stage.cohealo.com");
							Thread.sleep(5000);
							driver.manage().window().maximize();
							driver.navigate().refresh();
							Thread.sleep(5000);
							Assert.assertEquals(driver.getTitle().contentEquals("My Bookings"), true);
							ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='menu inline dropdown user']/a");
							ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon-signout']");
							Thread.sleep(5000);
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email")));
							
							SendingTestValueInTextBox.SendValue(driver, "id", "user_email", "test"+Integer.toString(date)+Integer.toString(month)+Integer.toString(year)+Integer.toString(time)+Integer.toString(milisec)+"@cohealo.com");
							SendingTestValueInTextBox.SendValue(driver, "id", "user_password","Test@123");
							ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Sign in']");
							Thread.sleep(10000);
							Assert.assertEquals(Element_Exist.existsElement("//div[@class='flash alert']", driver), false, "User is not able to login after activating the account");
							Assert.assertEquals(driver.getTitle().contains("My Bookings"), true, "Page is not navigated as expected");
							ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='menu inline dropdown user']/a");
							ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon-signout']");
							Thread.sleep(10000);
							SendingTestValueInTextBox.SendValue(driver, "id", "user_email", CONFIG.getProperty("ROMUser"));
							SendingTestValueInTextBox.SendValue(driver, "id", "user_password", CONFIG.getProperty("Password"));
							//click on Sign In button
							ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Sign in']");
							Thread.sleep(10000);
							ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='/admin/users']/span/i");
							Thread.sleep(5000);
							SendingTestValueInTextBox.SendValue(driver, "xpath", "//*[@id='DataTables_Table_0_filter']/label/input", "test"+Integer.toString(date)+Integer.toString(month)+Integer.toString(year)+Integer.toString(time)+Integer.toString(milisec)+"@cohealo.com");
							Assert.assertEquals(Element_Exist.existsElement("//td[@class='dataTables_empty']", driver), false, "Activated account not displayed at user list");
							Reporter.log("Account has been activated and successfully Loged in and inserted into the user list");
						  }
						  else
						  {
							  Reporter.log("Page Title is not as expected"+" "+"Expected page title:-My Bookings");
							  Assert.fail("Page Title is not as expected"+" "+"Expected page title:-My Bookings");
						  }
					  }
					  else
					  {
						  Reporter.log("Account activate mail not received or took longer time");
						  Assert.fail("Account activate mail not received or took longer time");
					  }
				  }
				  
				  else
				  {
					  Reporter.log("Confrimation message is not displayed as expected"+" "+"Message displayed as:- "+msg+" "+"Expected as :-You will receive a login link in your email momentarily.");
					  Assert.fail("Confrimation message is not displayed as expected"+" "+"Message displayed as:- "+msg+" "+"Expected as :-You will receive a login link in your email momentarily.");
				  }
			  }
		  }
		  else
		  {
			  Reporter.log("Page navigate to:-"+driver.getCurrentUrl()+" "+"Expected to:-"+CONFIG.getProperty("URL")+"users/sign_up");
			  Assert.fail("Page navigate to:-"+driver.getCurrentUrl()+" "+"Expected to:-"+CONFIG.getProperty("URL")+"users/sign_up");
		  }
	  }
  }
  

	  public boolean existsElementbylinktext(String linktext , WebDriver driver) throws InterruptedException {
		    try 
		    {
		    	driver.findElement(By.linkText(linktext));
		    } catch (Exception e) {
		        return false;
		    }

		    return true;
		}
	@DataProvider(name = "Login") 
	 
	 public static Object[][] Login_Cohealo() throws BiffException, IOException{
	  
	   FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"\\src\\xls\\Cohealo.xls");
	   Workbook w = Workbook.getWorkbook(fi);
	   Sheet  s = w.getSheet("Login Test");
	   int rows = s.getRows();
	   int cols = s.getColumns();
	   Object[][] data =new Object[rows-1][cols];
	     
    for(int r=1;r<=rows-1;r++){
	    	 
	    	 for(int c=0;c<=cols-1;c++)
	    	 {
	    		 data[r-1][c]=s.getCell(c, r).getContents();
	    		 
	    	 }
	     }
		 
		 return data;
	 }
	
	@DataProvider(name = "Forgot_Password") 
	 
	 public static Object[][] ForgotPassword() throws BiffException, IOException{
	  
	   FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"\\src\\xls\\Cohealo.xls");
	   Workbook w = Workbook.getWorkbook(fi);
	   Sheet  s = w.getSheet("Forgot_Password");
	   int rows = s.getRows();
	   int cols = s.getColumns();
	   Object[][] data =new Object[rows-1][cols];
	     
   for(int r=1;r<=rows-1;r++){
	    	 
	    	 for(int c=0;c<=cols-1;c++)
	    	 {
	    		 data[r-1][c]=s.getCell(c, r).getContents();
	    		 
	    	 }
	     }
		 
		 return data;
	 }
	
	@DataProvider(name = "Create User") 
	 
	 public static Object[][] Create_User() throws BiffException, IOException{
	  
	   FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"\\src\\xls\\Cohealo.xls");
	   Workbook w = Workbook.getWorkbook(fi);
	   Sheet  s = w.getSheet("Create_User");
	   int rows = s.getRows();
	   int cols = s.getColumns();
	   Object[][] data =new Object[rows-1][cols];
	     
  for(int r=1;r<=rows-1;r++){
	    	 
	    	 for(int c=0;c<=cols-1;c++)
	    	 {
	    		 data[r-1][c]=s.getCell(c, r).getContents();
	    		 
	    	 }
	     }
		 
		 return data;
	 }
	
/*@BeforeMethod
public void beforemethod() throws InterruptedException
{
	WebDriver driver = Initialization.getDriver();
	Thread.sleep(2000);
	driver.navigate().refresh();
}*/

}
