
package CohealoPkg;
import InitializationPkg.*;
import UtilityPkg.*;
import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;


public class ROM_user_Tests {
 
//Function for Add single Device tests
 @Test(dataProvider = "Add_Device")
 public void Add_Single_Device(String Manufacturer , String Model , String Description , String Scenario) throws InterruptedException
 {
	 WebDriver driver = Initialization.getDriver();
	 //click on Entity Manager
	 ClickActionOnObjects.ClickByJavascript(driver, "//div[@id='primary-navigation']/ul/li[3]/a");
	 Thread.sleep(5000);
	 //click on Add Single device
	 ClickActionOnObjects.ClickByJavascript(driver, "//li[@class='dropdown-submenu']");
	 Thread.sleep(5000);
	 ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='/equipment/new']/span");
	 Thread.sleep(5000);
	 //check for whether any confirmation message displayed as "You are not authorized to access this page."
	 if(Element_Exist.existsElement("//div[@class='jGrowl-message']", driver))
	 {
		 if(driver.getTitle().contentEquals("Users"))	
		 {
			 Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//div[@class='jGrowl-message']", "xpath").contentEquals(""), true, "ROM user is not able to create device,Error message displayed as :- You are not authorized to access this page.");
		 }
	 }
	 else
	 {
		 //check for title of web page to confirm correct navigation
		 if(driver.getTitle().contentEquals("Add Equipment"))
		 {
			 //check the Add Equipment form with Blank input
			 if(Scenario.contentEquals("Blank"))
			 {
				 ClickActionOnObjects.ClickByJavascript(driver, "//button[@name='button']");
				 Thread.sleep(5000);
				 Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='equipment_manufacturer']", "xpath").contentEquals("This field is required."), true, "No Error message displayed for Manufacturer");
				 Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='equipment_model']", "xpath").contentEquals("This field is required."), true, "No Error message displayed for Model");
				 Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='equipment_description']", "xpath").contentEquals("This field is required."), true, "No Error message displayed for Description");
				 Reporter.log("Add Device functionality is working properly with Blank input");
				 ClickActionOnObjects.ClickByJavascript(driver, "//div[@id='primary-navigation']/ul/li[3]/a");
			 }
			 
			//check the Add Equipment form with Blank Manufacturer
			 if(Scenario.contentEquals("Blank Manufacturer"))
			 {
				 GarbageValueClear.Clear(driver, "equipment_manufacturer", "id");
				 GarbageValueClear.Clear(driver, "equipment_model", "id");
				 GarbageValueClear.Clear(driver, "equipment_description", "id");
				 SendingTestValueInTextBox.SendValue(driver, "id", "equipment_manufacturer", Manufacturer);
				 SendingTestValueInTextBox.SendValue(driver, "id", "equipment_model", Model);
				 SendingTestValueInTextBox.SendValue(driver, "id", "equipment_description", Description);
				 ClickActionOnObjects.ClickByJavascript(driver, "//button[@name='button']");
				 Thread.sleep(5000);
				 Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='equipment_manufacturer']", "xpath").contentEquals("This field is required."), true, "No Error message displayed for Manufacturer");
				 Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='equipment_model']", "xpath").contentEquals("This field is required."), false, "Error message displayed for Model even if it is populated");
				 Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='equipment_description']", "xpath").contentEquals("This field is required."), false, "Error message displayed for Description even if it is populated");
				 Reporter.log("Add Device functionality is working properly with Blank Manufacturer");
				 ClickActionOnObjects.ClickByJavascript(driver, "//div[@id='primary-navigation']/ul/li[3]/a");
			 }
			 
			//check the Add Equipment form with Blank Model
			 if(Scenario.contentEquals("Blank Model"))
			 {
				 GarbageValueClear.Clear(driver, "equipment_manufacturer", "id");
				 GarbageValueClear.Clear(driver, "equipment_model", "id");
				 GarbageValueClear.Clear(driver, "equipment_description", "id");
				 SendingTestValueInTextBox.SendValue(driver, "id", "equipment_manufacturer", Manufacturer);
				 SendingTestValueInTextBox.SendValue(driver, "id", "equipment_model", Model);
				 SendingTestValueInTextBox.SendValue(driver, "id", "equipment_description", Description);
				 ClickActionOnObjects.ClickByJavascript(driver, "//button[@name='button']");
				 Thread.sleep(5000);
				 Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='equipment_manufacturer']", "xpath").contentEquals("This field is required."), false, "Error message displayed for Model even if it is populated");
				 Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='equipment_model']", "xpath").contentEquals("This field is required."), true, "No Error message displayed for Model");
				 Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='equipment_description']", "xpath").contentEquals("This field is required."), false, "Error message displayed for Description even if it is populated");
				 Reporter.log("Add Device functionality is working properly with Blank Model");
				 ClickActionOnObjects.ClickByJavascript(driver, "//div[@id='primary-navigation']/ul/li[3]/a");
			 }
			 
			//check the Add Equipment form with Blank Description
			 if(Scenario.contentEquals("Blank Description"))
			 {
				 GarbageValueClear.Clear(driver, "equipment_manufacturer", "id");
				 GarbageValueClear.Clear(driver, "equipment_model", "id");
				 GarbageValueClear.Clear(driver, "equipment_description", "id");
				 SendingTestValueInTextBox.SendValue(driver, "id", "equipment_manufacturer", Manufacturer);
				 SendingTestValueInTextBox.SendValue(driver, "id", "equipment_model", Model);
				 SendingTestValueInTextBox.SendValue(driver, "id", "equipment_description", Description);
				 ClickActionOnObjects.ClickByJavascript(driver, "//button[@name='button']");
				 Thread.sleep(5000);
				 Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='equipment_manufacturer']", "xpath").contentEquals("This field is required."), false, "Error message displayed for Model even if it is populated");
				 Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='equipment_model']", "xpath").contentEquals("This field is required."), false, "Error message displayed for Model even if it is populated");
				 Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='equipment_description']", "xpath").contentEquals("This field is required."), true, "No Error message displayed for Description");
				 Reporter.log("Add Device functionality is working properly with Blank Description");
				 ClickActionOnObjects.ClickByJavascript(driver, "//div[@id='primary-navigation']/ul/li[3]/a");
			 }
			 
			//check the Add Equipment form with Valid Inputs
			 if(Scenario.contentEquals("Valid Inputs"))
			 {
				 WebDriverWait wait = new WebDriverWait(driver, 30);
				 //clearing the garbage values from text boxes
				 GarbageValueClear.Clear(driver, "equipment_manufacturer", "id");
				 GarbageValueClear.Clear(driver, "equipment_model", "id");
				 GarbageValueClear.Clear(driver, "equipment_description", "id");
				 
				 //Entering the valid inputs to "Equipment Manufacturer" , "Model" and "Description" text boxes
				 SendingTestValueInTextBox.SendValue(driver, "id", "equipment_manufacturer", Manufacturer);
				 SendingTestValueInTextBox.SendValue(driver, "id", "equipment_model", Model);
				 SendingTestValueInTextBox.SendValue(driver, "id", "equipment_description", Description);
				 String Health_system = driver.findElement(By.xpath("//select[@id='health_system_id']/option[1]")).getText();
				 String Owned_facility = driver.findElement(By.xpath("//select[@id='equipment_owned_by_facility_id']/option[1]")).getText();
				 //click on Add Equipment button
				 ClickActionOnObjects.ClickByJavascript(driver, "//button[@name='button']");
				 Thread.sleep(5000);
				 //check the title of web page for correct navigation
				 Assert.assertEquals(driver.getTitle(),"Edit Equipment" ,"Page is not navigated as expected");
				 //check the information displayed at top of page with actual information entered
				 Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//h1", "xpath").trim(), Manufacturer+" "+Model+" "+Description, "Information of device is not matching with the actual information entered");
				 //check total number of navigation tabs
				 Assert.assertEquals(Element_Exist.existsElement("//ul[@class='nav nav-tabs']", driver), true, "Navigation tabs are not displayed at Edit Equippment page");
				 WebElement e = driver.findElement(By.xpath("//ul[@class='nav nav-tabs']"));
				 int count = e.findElements(By.tagName("li")).size();
				 Assert.assertEquals(count, 4, "Number of navigation tabs are not as expected");
				 //click on Name & Pictures tab
				 driver.findElement(By.xpath("//ul[@class='nav nav-tabs']/li[2]")).click();	
				 Thread.sleep(5000);
				 //check the information of Manufacturer,Model,Description,HS and facility displayed correctly
				 Assert.assertEquals(Element_Exist.existsElement("//*[@id='equipment_manufacturer']", driver), true, "Manufacturer text box is not present");
				 Assert.assertEquals(Element_Exist.existsElement("//*[@id='equipment_model']", driver), true, "Model text box is not present");
				 Assert.assertEquals(Element_Exist.existsElement("//*[@id='equipment_description']", driver), true, "Description text box is not present");
				 Assert.assertEquals(Element_Exist.existsElement("//select[@id='health_system_id' and @disabled='disabled']", driver), true, "Health System drop down is not present");
				 Assert.assertEquals(Element_Exist.existsElement("//*[@id='equipment_owned_by_facility_id']", driver), true, "Owned by facility drop down is not present");
				 Assert.assertEquals(Element_Exist.existsElement("//*[@id='equipment_manufacturer']", driver), true, "Manufacturer text box is not present");
				 Assert.assertEquals(driver.findElement(By.xpath("//*[@id='equipment_manufacturer']")).getAttribute("value"), Manufacturer, "Manufacturer is not matching with actual manufaturer entered");
				 Assert.assertEquals(driver.findElement(By.xpath("//*[@id='equipment_model']")).getAttribute("value"), Model, "Model is not matching with actual Model entered");
				 Assert.assertEquals(driver.findElement(By.xpath("//*[@id='equipment_description']")).getAttribute("value"), Description, "Description is not matching with actual Description entered");
				 Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//select[@id='health_system_id']/option", "xpath"), Health_system, "Health_system is not matching with actual Health_system selected");
				 Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//select[@id='equipment_owned_by_facility_id']/option", "xpath"), Owned_facility, "Owned_facility is not matching with actual Owned_facility selected");
				 //click on save button
				 ClickActionOnObjects.ClickByJavascript(driver, "//button[@type='submit']");
				 Thread.sleep(5000);
				 //wait for confirmation message displayed after saving device
				 WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jGrowl-message']")));
				 //check the confirmation message text
				 Assert.assertEquals(element.getText(),"Device listed." ,"Confirmation message is not displayed as expected");
				//check the title of web page for correct navigation
				 Assert.assertEquals(driver.getTitle(),"Equipment Details" ,"Page is not navigated as expected");
				 String ref_no = driver.findElement(By.xpath("//*[@id='retab2']/table/tbody/tr[7]/td[2]")).getText();
				 Assert.assertEquals(Element_Exist.existsElement("//a[@href='/equipment/"+ref_no+"/edit']", driver),true ,"Edit link is not present at Additional information");
				 //click on edit equipment
				 ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='/equipment/"+ref_no+"/edit']");
				 Thread.sleep(5000);
				//check the title of web page for correct navigation
				 Assert.assertEquals(driver.getTitle(),"Edit Equipment" ,"Page is not navigated as expected");
				 //click on delete button
				 driver.findElement(By.xpath("//a[@data-method='delete' and @data-confirm='Are you sure?']")).click();
				 Thread.sleep(3000);
				 //check for alert
				 Assert.assertEquals(Element_Exist.isAlertPresent(driver), true, "Alert for delete device is not generated");
				 driver.switchTo().alert().accept();
				 Reporter.log("Device is added and deleted successfully");
				 ClickActionOnObjects.ClickByJavascript(driver, "//div[@id='primary-navigation']/ul/li[3]/a");
				 Thread.sleep(5000);
			 }
			 

		 }
	 }
 }
//Function to text User manager cancel button functionality
  @Test
  public void User_Index_Cancel() throws InterruptedException 
  {
	  WebDriver driver = Initialization.getDriver();
	  Properties CONFIG = Initialization.getconfig();
	  //click on Entity Manager tab
	  ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='/admin/users']/span/i");
	  Thread.sleep(10000);
	  //check the URL for correct navigation test
	  if(driver.getCurrentUrl().contentEquals(CONFIG.getProperty("URL")+"admin/users"))
		{
		  //check existence of Create New User button
			if(Element_Exist.existsElement("//i[@class='icon icon-plus-sign-alt']", driver))
			{
				//click on Create New user button
				ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon icon-plus-sign-alt']");
				Thread.sleep(5000);
				//check existence of  cancel button
				if(Element_Exist.existsElement("//a[@href='/admin/users' and @class='btn btn-large']", driver))
				{
					//click on cancel button
					ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='/admin/users' and @class='btn btn-large']");
					Thread.sleep(8000);
					//check the URL for correct navigation test
					if(driver.getCurrentUrl().contentEquals(CONFIG.getProperty("URL")+"admin/users"))
					 {
						Reporter.log("Cancel button working properly");
					 }
					
					else
					{
						Reporter.log("Page Navigate after clicking cancel:-"+driver.getCurrentUrl()+" "+"Expected to:-"+CONFIG.getProperty("URL")+"admin/users");
						Assert.fail("Page Navigate after clicking cancel:-"+driver.getCurrentUrl()+" "+"Expected to:-"+CONFIG.getProperty("URL")+"admin/users");
					} 
					
				}
				else
				{
					Reporter.log("Cancel button not present");
					Assert.fail("Cancel button not present");
				}
			}
			
			else
			{
				Reporter.log("Create user button is not displayed");
				Assert.fail("Create user button is not displayed");
			}
		}
		else
		{
			Reporter.log("Page navigate to:-"+driver.getCurrentUrl()+" "+"Expected to:-"+CONFIG.getProperty("URL")+"admin/users");
			Assert.fail("Page navigate to:-"+driver.getCurrentUrl()+" "+"Expected to:-"+CONFIG.getProperty("URL")+"admin/users");
		}
  }
  
  //Function to test Create user functionality
  @Test(dataProvider = "Create User")
  public void User_Index_Create_Edit_and_Delete_User(String FirstName , String LastName , String Email , String Scenario) throws InterruptedException, IOException, AWTException
  {
	  WebDriver driver = Initialization.getDriver();
	  Properties CONFIG = Initialization.getconfig();
	  WebDriverWait wait = new WebDriverWait(driver, 100);
	  //click on User Manager
	  ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='/admin/users']/i[1]");
	  Thread.sleep(10000);
	//check the URL for correct navigation test
	  if(driver.getCurrentUrl().contentEquals(CONFIG.getProperty("URL")+"admin/users"))
		{
		//check existence of Create New User button
		  if(Element_Exist.existsElement("//i[@class='icon icon-plus-sign-alt']", driver))
			{
			//click on Create New user button
				ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon icon-plus-sign-alt']");
				Thread.sleep(10000);
				//Check the Create user functionality form for Blank Input
				if(Scenario.equalsIgnoreCase("Blank"))
				{	
					ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Create User']");
					Thread.sleep(5000);
					Assert.assertEquals(Element_Exist.existsElement("//span[@for='user_first_name']", driver), true, "No error message displayed for User Name");
					Assert.assertEquals(Element_Exist.existsElement("//span[@for='user_last_name']", driver), true, "No error message displayed for Last Name");
					Assert.assertEquals(Element_Exist.existsElement("//span[@for='user_email']", driver), true, "No error message displayed for Email");
					Assert.assertEquals(Element_Exist.existsElement("//span[@for='user[roles][]']", driver), true, "No error message displayed for Roles");
					Reporter.log("Create user functionality working properly with Blank Input");
				}
				//Check the Create user functionality form for Blank First Name
				if(Scenario.equalsIgnoreCase("Blank FirstName"))
				{	
					SendingTestValueInTextBox.SendValue(driver, "id", "user_first_name", FirstName);
					SendingTestValueInTextBox.SendValue(driver, "id", "user_last_name", LastName);
					SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Email);
					SendingTestValueInTextBox.SendValue(driver, "id", "user_position", "Test");
					SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_office_phone", "5555555555");
					SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_cell_phone", "5555555555");
					ClickActionOnObjects.ClickByJavascript(driver, "//*[@id='user_roles_consumer']");
					Thread.sleep(5000);
					Assert.assertEquals(Element_Exist.existsElement("//div[@id='s2id_user_facility_id' and @class='select2-container select2-container-disabled']/a", driver), true , "Facility dropdown is not disabled before selecting HS");
					String HS = driver.findElement(By.xpath("//select[@id='health_system_id']/option")).getText();
					driver.findElement(By.xpath("//input[@id='s2id_autogen1']")).sendKeys(HS);
					Thread.sleep(2000);
					driver.findElement(By.xpath("//input[@id='s2id_autogen1']")).sendKeys(Keys.TAB);
					Thread.sleep(8000);
					Assert.assertEquals(Element_Exist.existsElement("//div[@id='s2id_user_facility_id' and @class='select2-container select2-container-disabled']/a", driver), false , "Facility dropdown is not enabled after selecting HS");
					driver.findElement(By.xpath("//div[@id='s2id_user_facility_id']/a/span[@role='presentation']/b")).click();
					Thread.sleep(5000);
					driver.findElement(By.xpath("//div[@id='select2-drop']/ul/li[1]")).click();
					Thread.sleep(3000);
					ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Create User']");
					Thread.sleep(5000);
					Assert.assertEquals(driver.findElement(By.xpath("//span[@for='user_first_name']")).getText(), "This field is required.", "No error message displayed for User Name");
					Assert.assertEquals(driver.findElement(By.xpath("//span[@for='user_last_name']")).getText(), "", "Error message displayed for Last Name even if it is populated");
					Assert.assertEquals(driver.findElement(By.xpath("//span[@for='user_email']")).getText(), "", "Error message displayed for Email even if it is populated");
					Assert.assertEquals(driver.findElement(By.xpath("//span[@for='user[roles][]']")).getText(), "", "Error message displayed for User roles even if it is selected");
					Reporter.log("Create user functionality working properly with Blank First Name");
				}
				
				//Check the Create user functionality form for Blank Last
				if(Scenario.equalsIgnoreCase("Blank LastName"))
				{	
					SendingTestValueInTextBox.SendValue(driver, "id", "user_first_name", FirstName);
					SendingTestValueInTextBox.SendValue(driver, "id", "user_last_name", LastName);
					SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Email);
					SendingTestValueInTextBox.SendValue(driver, "id", "user_position", "Test");
					SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_office_phone", "5555555555");
					SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_cell_phone", "5555555555");
					ClickActionOnObjects.ClickByJavascript(driver, "//*[@id='user_roles_consumer']");
					Thread.sleep(5000);
					Assert.assertEquals(Element_Exist.existsElement("//div[@id='s2id_user_facility_id' and @class='select2-container select2-container-disabled']/a", driver), true , "Facility dropdown is not disabled before selecting HS");
					String HS = driver.findElement(By.xpath("//select[@id='health_system_id']/option")).getText();
					driver.findElement(By.xpath("//input[@id='s2id_autogen1']")).sendKeys(HS);
					Thread.sleep(2000);
					driver.findElement(By.xpath("//input[@id='s2id_autogen1']")).sendKeys(Keys.TAB);
					Thread.sleep(8000);
					Assert.assertEquals(Element_Exist.existsElement("//div[@id='s2id_user_facility_id' and @class='select2-container select2-container-disabled']/a", driver), false , "Facility dropdown is not enabled after selecting HS");
					driver.findElement(By.xpath("//div[@id='s2id_user_facility_id']/a/span[@role='presentation']/b")).click();
					Thread.sleep(5000);
					driver.findElement(By.xpath("//div[@id='select2-drop']/ul/li[1]")).click();
					Thread.sleep(3000);
					ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Create User']");
					Thread.sleep(5000);
					Assert.assertEquals(driver.findElement(By.xpath("//span[@for='user_first_name']")).getText(), "", "Error message displayed for First Name even if it is populated");
					Assert.assertEquals(driver.findElement(By.xpath("//span[@for='user_last_name']")).getText(), "This field is required.", "No error message displayed for Last Name");
					Assert.assertEquals(driver.findElement(By.xpath("//span[@for='user_email']")).getText(), "", "Error message displayed for Email even if it is populated");
					Assert.assertEquals(driver.findElement(By.xpath("//span[@for='user[roles][]']")).getText(), "", "Error message displayed for User roles even if it is selected");
					Reporter.log("Create user functionality working properly with Blank Last Name");
				}
				
				//Check the Create user functionality form for Blank Email
				if(Scenario.equalsIgnoreCase("Blank Email"))
				{	
					SendingTestValueInTextBox.SendValue(driver, "id", "user_first_name", FirstName);
					SendingTestValueInTextBox.SendValue(driver, "id", "user_last_name", LastName);
					SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Email);
					SendingTestValueInTextBox.SendValue(driver, "id", "user_position", "Test");
					SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_office_phone", "5555555555");
					SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_cell_phone", "5555555555");
					ClickActionOnObjects.ClickByJavascript(driver, "//*[@id='user_roles_consumer']");
					Thread.sleep(5000);
					Assert.assertEquals(Element_Exist.existsElement("//div[@id='s2id_user_facility_id' and @class='select2-container select2-container-disabled']/a", driver), true , "Facility dropdown is not disabled before selecting HS");
					String HS = driver.findElement(By.xpath("//select[@id='health_system_id']/option")).getText();
					driver.findElement(By.xpath("//input[@id='s2id_autogen1']")).sendKeys(HS);
					Thread.sleep(2000);
					driver.findElement(By.xpath("//input[@id='s2id_autogen1']")).sendKeys(Keys.TAB);
					Thread.sleep(8000);
					Assert.assertEquals(Element_Exist.existsElement("//div[@id='s2id_user_facility_id' and @class='select2-container select2-container-disabled']/a", driver), false , "Facility dropdown is not enabled after selecting HS");
					driver.findElement(By.xpath("//div[@id='s2id_user_facility_id']/a/span[@role='presentation']/b")).click();
					Thread.sleep(5000);
					driver.findElement(By.xpath("//div[@id='select2-drop']/ul/li[1]")).click();
					Thread.sleep(3000);
					ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Create User']");
					Thread.sleep(5000);
					Assert.assertEquals(driver.findElement(By.xpath("//span[@for='user_first_name']")).getText(), "", "Error message displayed for First Name even if it is populated");
					Assert.assertEquals(driver.findElement(By.xpath("//span[@for='user_last_name']")).getText(), "", "Error message displayed for Last Name even if it is populated");
					Assert.assertEquals(driver.findElement(By.xpath("//span[@for='user_email']")).getText(), "This field is required.", "No error message displayed for Email");
					Assert.assertEquals(driver.findElement(By.xpath("//span[@for='user[roles][]']")).getText(), "", "Error message displayed for User roles even if it is selected");
					Reporter.log("Create user functionality working properly with Blank Email");
				}
				
				//Check the Create user functionality form for Invalid Email
				if(Scenario.equalsIgnoreCase("Invalid Email"))
				{	
					SendingTestValueInTextBox.SendValue(driver, "id", "user_first_name", FirstName);
					SendingTestValueInTextBox.SendValue(driver, "id", "user_last_name", LastName);
					SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Email);
					SendingTestValueInTextBox.SendValue(driver, "id", "user_position", "Test");
					SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_office_phone", "5555555555");
					SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_cell_phone", "5555555555");
					ClickActionOnObjects.ClickByJavascript(driver, "//*[@id='user_roles_consumer']");
					Thread.sleep(5000);
					Assert.assertEquals(Element_Exist.existsElement("//div[@id='s2id_user_facility_id' and @class='select2-container select2-container-disabled']/a", driver), true , "Facility dropdown is not disabled before selecting HS");
					String HS = driver.findElement(By.xpath("//select[@id='health_system_id']/option")).getText();
					driver.findElement(By.xpath("//input[@id='s2id_autogen1']")).sendKeys(HS);
					Thread.sleep(2000);
					driver.findElement(By.xpath("//input[@id='s2id_autogen1']")).sendKeys(Keys.TAB);
					Thread.sleep(8000);
					Assert.assertEquals(Element_Exist.existsElement("//div[@id='s2id_user_facility_id' and @class='select2-container select2-container-disabled']/a", driver), false , "Facility dropdown is not enabled after selecting HS");
					driver.findElement(By.xpath("//div[@id='s2id_user_facility_id']/a/span[@role='presentation']/b")).click();
					Thread.sleep(5000);
					driver.findElement(By.xpath("//div[@id='select2-drop']/ul/li[1]")).click();
					Thread.sleep(3000);
					ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Create User']");
					Thread.sleep(5000);
					Assert.assertEquals(driver.findElement(By.xpath("//span[@for='user_first_name']")).getText(), "", "Error message displayed for First Name even if it is populated");
					Assert.assertEquals(driver.findElement(By.xpath("//span[@for='user_last_name']")).getText(), "", "Error message displayed for Last Name even if it is populated");
					Assert.assertEquals(driver.findElement(By.xpath("//span[@for='user[roles][]']")).getText(), "", "Error message displayed for User roles even if it is selected");
					Assert.assertEquals(ReadingTextFromObjects.GetText(driver,"//span[@for='user_email']", "xpath"), "Please enter a valid email address.", "Expected error message not displayed for Invalid Email");
					Reporter.log("Create user functionality working properly with Invalid Email");
				}
				
				//Check the Create user functionality form for Blank Role
				if(Scenario.equalsIgnoreCase("Blank Role"))
				{	
					SendingTestValueInTextBox.SendValue(driver, "id", "user_first_name", FirstName);
					SendingTestValueInTextBox.SendValue(driver, "id", "user_last_name", LastName);
					SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Email);
					SendingTestValueInTextBox.SendValue(driver, "id", "user_position", "Test");
					SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_office_phone", "5555555555");
					SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_cell_phone", "5555555555");
					Thread.sleep(2000);
					Assert.assertEquals(Element_Exist.existsElement("//div[@id='s2id_user_facility_id' and @class='select2-container select2-container-disabled']/a", driver), true , "Facility dropdown is not disabled before selecting HS");
					String HS = driver.findElement(By.xpath("//select[@id='health_system_id']/option")).getText();
					driver.findElement(By.xpath("//input[@id='s2id_autogen1']")).sendKeys(HS);
					Thread.sleep(2000);
					driver.findElement(By.xpath("//input[@id='s2id_autogen1']")).sendKeys(Keys.TAB);
					Thread.sleep(8000);
					Assert.assertEquals(Element_Exist.existsElement("//div[@id='s2id_user_facility_id' and @class='select2-container select2-container-disabled']/a", driver), false , "Facility dropdown is not enabled after selecting HS");
					driver.findElement(By.xpath("//div[@id='s2id_user_facility_id']/a/span[@role='presentation']/b")).click();
					Thread.sleep(5000);
					driver.findElement(By.xpath("//div[@id='select2-drop']/ul/li[1]")).click();
					Thread.sleep(3000);
					ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Create User']");
					Thread.sleep(5000);
					Assert.assertEquals(driver.findElement(By.xpath("//span[@for='user_first_name']")).getText(), "", "Error message displayed for First Name even if it is populated");
					Assert.assertEquals(driver.findElement(By.xpath("//span[@for='user_last_name']")).getText(), "", "Error message displayed for Last Name even if it is populated");
					Assert.assertEquals(driver.findElement(By.xpath("//span[@for='user_email']")).getText(), "", "Error message displayed for Email even if it is populated");
					Assert.assertEquals(driver.findElement(By.xpath("//span[@for='user[roles][]']")).getText(), "This field is required.", "No error message displayed for User roles");
					Reporter.log("Create user functionality working properly with Blank Role");
				}
				
				//Check the Create user functionality form for Valid inputs
				if(Scenario.equalsIgnoreCase("Valid Inputs"))
				{	
					Calendar cal=Calendar.getInstance();
					int date = cal.get(Calendar.DATE);
					int month = cal.get(Calendar.MONTH)+1;
					int year = cal.get(Calendar.YEAR);
					int time = cal.get(Calendar.MINUTE);
					int hour = cal.get(Calendar.HOUR);
					int milisec = cal.get(Calendar.MILLISECOND);
					int flag = 0;
					String userid = Integer.toString(date)+"-"+Integer.toString(month)+"-"+Integer.toString(year)+Integer.toString(hour)+Integer.toString(time)+Integer.toString(milisec)+Email;
					SendingTestValueInTextBox.SendValue(driver, "id", "user_first_name", FirstName);
					SendingTestValueInTextBox.SendValue(driver, "id", "user_last_name", LastName);
					SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Integer.toString(date)+"-"+Integer.toString(month)+"-"+Integer.toString(year)+Integer.toString(hour)+Integer.toString(time)+Integer.toString(milisec)+Email);
					SendingTestValueInTextBox.SendValue(driver, "id", "user_position", "Test");
					SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_office_phone", "5555555555");
					SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_cell_phone", "5555555555");
					ClickActionOnObjects.ClickByJavascript(driver, "//*[@id='user_roles_consumer']");
					Thread.sleep(5000);
					Assert.assertEquals(Element_Exist.existsElement("//div[@id='s2id_user_facility_id' and @class='select2-container select2-container-disabled']/a", driver), true , "Facility dropdown is not disabled before selecting HS");
					String HS = driver.findElement(By.xpath("//select[@id='health_system_id']/option")).getText();
					driver.findElement(By.xpath("//input[@id='s2id_autogen1']")).sendKeys(HS);
					Thread.sleep(2000);
					driver.findElement(By.xpath("//input[@id='s2id_autogen1']")).sendKeys(Keys.TAB);
					Thread.sleep(8000);
					Assert.assertEquals(Element_Exist.existsElement("//div[@id='s2id_user_facility_id' and @class='select2-container select2-container-disabled']/a", driver), false , "Facility dropdown is not enabled after selecting HS");
					driver.findElement(By.xpath("//div[@id='s2id_user_facility_id']/a/span[@role='presentation']/b")).click();
					Thread.sleep(5000);
					driver.findElement(By.xpath("//div[@id='select2-drop']/ul/li[1]")).click();
					Thread.sleep(3000);
					ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Create User']");
					Thread.sleep(5000);
					WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jGrowl-message']")));
					if(element.getText().contentEquals("User created.")||element.getText().contentEquals("Error creating user: Email has already been taken."))
					{	
						if(element.getText().contentEquals("User created."))
						{	
							Assert.assertEquals(Element_Exist.existsElement("//div[@class='box-header blue-background']", driver), true, "Page is not navigated to users list after creation of user");
							Assert.assertEquals(Element_Exist.existsElement("//div[@class='dataTables_filter']/label/input", driver), true, "Search user text box is not displaying");
							SendingTestValueInTextBox.SendValue(driver, "xpath", "//div[@class='dataTables_filter']/label/input", Integer.toString(date)+"-"+Integer.toString(month)+"-"+Integer.toString(year)+Integer.toString(hour)+Integer.toString(time)+Integer.toString(milisec)+Email);
							Thread.sleep(5000);
							Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//tbody[@role='alert']/tr[1]/td[1]", "xpath"), LastName+","+" "+FirstName, "User is not displaying in list after creation");
							Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//tbody[@role='alert']/tr[1]/td[2]", "xpath").equalsIgnoreCase(Integer.toString(date)+"-"+Integer.toString(month)+"-"+Integer.toString(year)+Integer.toString(hour)+Integer.toString(time)+Integer.toString(milisec)+Email), true, "Wrong user record displayed...Email address is not matching");
							Reporter.log("Create user functionality working properly with Valid Inputs");
							Assert.assertEquals(Element_Exist.existsElement("//i[@class='icon-edit']", driver), true, "Edit button is not displaying");
							ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon-edit']");
							Thread.sleep(8000);
							Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//legend", "xpath").trim(), "Edit User Settings", "Page is not navigated to Edit User Settings");					
							GarbageValueClear.Clear(driver, "user_first_name", "id");
							GarbageValueClear.Clear(driver, "user_last_name", "id");
							SendingTestValueInTextBox.SendValue(driver, "id", "user_first_name", "FirstNameTest");
							SendingTestValueInTextBox.SendValue(driver, "id", "user_last_name", "LastNameTest");
							ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='/admin/users' and @class='btn btn-large']");
							Thread.sleep(8000);
							Assert.assertEquals(Element_Exist.existsElement("//div[@class='box-header blue-background']", driver), true, "Page is not navigated to user list after clicking on cancel button");
							SendingTestValueInTextBox.SendValue(driver, "xpath", "//div[@class='dataTables_filter']/label/input", Integer.toString(date)+"-"+Integer.toString(month)+"-"+Integer.toString(year)+Integer.toString(hour)+Integer.toString(time)+Integer.toString(milisec)+Email);
							Thread.sleep(5000);	
							Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//tbody[@role='alert']/tr[1]/td[1]", "xpath"), LastName+","+" "+FirstName, "User's First and Last name got updated after clicking on cancel button");
							ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon-edit']");
							Thread.sleep(8000);
							Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//legend", "xpath").trim(), "Edit User Settings", "Page is not navigated to Edit User Settings");
							GarbageValueClear.Clear(driver, "user_first_name", "id");
							GarbageValueClear.Clear(driver, "user_last_name", "id");
							SendingTestValueInTextBox.SendValue(driver, "id", "user_first_name", "FirstNameTest");
							SendingTestValueInTextBox.SendValue(driver, "id", "user_last_name", "LastNameTest");
							ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Update User']");
							WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jGrowl-message']")));
							Assert.assertEquals(element2.getText(), "User sucessfully updated.", "Confirmation message not displayed as expected after user infor updated");
							SendingTestValueInTextBox.SendValue(driver, "xpath", "//div[@class='dataTables_filter']/label/input", Integer.toString(date)+"-"+Integer.toString(month)+"-"+Integer.toString(year)+Integer.toString(hour)+Integer.toString(time)+Integer.toString(milisec)+Email);
							Thread.sleep(5000);	
							Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//tbody[@role='alert']/tr[1]/td[1]", "xpath"), "LastNameTest"+","+" "+"FirstNameTest", "User's First and Last name are not updated after clicking on cancel button");
							Assert.assertEquals(Element_Exist.existsElement("//a[@data-confirm='Delete User?']/i", driver), true, "Delete button is not displaying");
							driver.findElement(By.xpath("//a[@data-confirm='Delete User?']/i")).click();
							Thread.sleep(3000);
							Assert.assertEquals(Element_Exist.isAlertPresent(driver), true, "Alert for delete user is not generated");
							driver.switchTo().alert().accept();
							WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jGrowl-message']")));
							Assert.assertEquals(element1.getText(), "User successfully deleted.", "Confirmation message not displayed as expected after user Deleted");
							SendingTestValueInTextBox.SendValue(driver, "xpath", "//div[@class='dataTables_filter']/label/input", Integer.toString(date)+"-"+Integer.toString(month)+"-"+Integer.toString(year)+Integer.toString(hour)+Integer.toString(time)+Integer.toString(milisec)+Email);
							Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//td[@class='dataTables_empty']", "xpath"), "No matching records found", "User is still present after delete");
							Reporter.log("<br/>");
							Reporter.log("Delete and Edit User functionality working properly");
							
					   }
						
						if(driver.findElement(By.xpath("//div[@class='jGrowl-message']")).getText().contentEquals("Error creating user: Email has already been taken."))
						{
							ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon icon-group']");
							Thread.sleep(5000);
							SendingTestValueInTextBox.SendValue(driver, "xpath", "//div[@class='dataTables_filter']/label/input", Integer.toString(date)+"-"+Integer.toString(month)+"-"+Integer.toString(year)+Integer.toString(hour)+Integer.toString(time)+Integer.toString(milisec)+Email);
							Thread.sleep(2000);
							
							if(!Element_Exist.existsElement("//td[@class='dataTables_empty']", driver))
							{
								flag=1;
							}	
								Assert.assertEquals(Element_Exist.existsElement("//i[@class='icon-edit']", driver), true, "Edit button is not displaying");
								ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon-edit']");
								Thread.sleep(8000);
								Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//legend", "xpath").trim(), "Edit User Settings", "Page is not navigated to Edit User Settings");					
								GarbageValueClear.Clear(driver, "user_first_name", "id");
								GarbageValueClear.Clear(driver, "user_last_name", "id");
								SendingTestValueInTextBox.SendValue(driver, "id", "user_first_name", "FirstNameTest");
								SendingTestValueInTextBox.SendValue(driver, "id", "user_last_name", "LastNameTest");
								ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='/admin/users' and @class='btn btn-large']");
								Thread.sleep(8000);
								Assert.assertEquals(Element_Exist.existsElement("//div[@class='box-header blue-background']", driver), true, "Page is not navigated to user list after clicking on cancel button");
								SendingTestValueInTextBox.SendValue(driver, "xpath", "//div[@class='dataTables_filter']/label/input", Integer.toString(date)+"-"+Integer.toString(month)+"-"+Integer.toString(year)+Integer.toString(hour)+Integer.toString(time)+Integer.toString(milisec)+Email);
								Thread.sleep(5000);	
								Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//tbody[@role='alert']/tr[1]/td[1]", "xpath"), LastName+","+" "+FirstName, "User's First and Last name got updated after clicking on cancel button");
								Assert.assertEquals(Element_Exist.existsElement("//a[@data-confirm='Delete User?']/i", driver), true, "Delete button is not displaying");
								java.lang.Runtime.getRuntime().exec("E:\\Cohealo\\Delete_user.exe");
								ClickActionOnObjects.ClickByJavascript(driver, "//a[@data-confirm='Delete User?']/i");
								WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jGrowl-message']")));
								Assert.assertEquals(element1.getText(), "User successfully deleted.", "Confirmation message not displayed as expected after user Deleted");
								SendingTestValueInTextBox.SendValue(driver, "xpath", "//div[@class='dataTables_filter']/label/input", Integer.toString(date)+"-"+Integer.toString(month)+"-"+Integer.toString(year)+Integer.toString(hour)+Integer.toString(time)+Integer.toString(milisec)+Email);
								Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//td[@class='dataTables_empty']", "xpath"), "No matching records found", "User is still present after delete");
								Reporter.log("<br/>");
								Reporter.log("Delete and Edit User functionality working properly");
								if(flag==1)
								{
								Reporter.log("User is added into the list , but the message was displayed as:- Error creating user: Email has already been taken.");
								Assert.fail("User is added into the list , but the message was displayed as:- Error creating user: Email has already been taken.");
								}
						}
					}	
				}
				
			}
		  
		}
	  
	  else
	  {
		  Reporter.log("Page Navigated to:-"+" "+driver.getCurrentUrl()+" "+"Expected to:-"+" "+CONFIG.getProperty("URL")+"admin/users");
		  Assert.fail("Page Navigated to:-"+" "+driver.getCurrentUrl()+" "+"Expected to:-"+" "+CONFIG.getProperty("URL")+"admin/users");
	  }
	  
  }
  
  //Function to test New Facility functionality
  @Test(dataProvider = "New Facility")
  public void New_Facility_Add(String Name , String Address , String City , String State , String zip , String Scenario) throws InterruptedException
  {
	  WebDriver driver = Initialization.getDriver();
	  Properties CONFIG = Initialization.getconfig();
	  WebDriverWait wait = new WebDriverWait(driver, 30);
	  //Check the existence of User Manager tab
	  Assert.assertEquals(Element_Exist.existsElement("//a[@href='/admin/facilities']/i[1]", driver), true, "Facility link is not present");
	  //Click on User Manager tab
	  ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='/admin/facilities']/i[1]");
	  Thread.sleep(8000);
	  //Check for URL for correct navigation
	  Assert.assertEquals(driver.getCurrentUrl(), CONFIG.getProperty("URL")+"admin/facilities", "Page is not navigated to facilities:-"+" "+driver.getCurrentUrl());
	  Assert.assertEquals(Element_Exist.existsElement("//*[@class='btn btn-primary btn-entity']", driver), true, "New Facilities link is not present");
	  ClickActionOnObjects.ClickByJavascript(driver, "//*[@class='btn btn-primary btn-entity']");
	  Thread.sleep(10000);
	  Assert.assertEquals(driver.getCurrentUrl(), CONFIG.getProperty("URL")+"admin/facilities/new", "Page is navigated to:-"+" "+driver.getCurrentUrl());
	  //Check Facility functionality with Blank input
	  if(Scenario.contentEquals("Blank"))
	  {
		  Assert.assertEquals(Element_Exist.existsElement("//input[@value='Create Facility']", driver), true, "Create Facility button is not present");
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Create Facility']");
		  Thread.sleep(5000);
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_name']", "xpath"), "This field is required.", "Error message for Name field is not displayed");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_address1']", "xpath"), "This field is required.", "Error message for Address field is not displayed");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_city']", "xpath"), "This field is required.", "Error message for city field is not displayed");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_state']", "xpath"), "This field is required.", "Error message for state field is not displayed");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_zip']", "xpath"), "This field is required.", "Error message for zip field is not displayed");
		  Reporter.log("New Facility functionality is working properly with Blank input");
	  }
	//Check Facility functionality with Blank Name
	  if(Scenario.contentEquals("Blank Name"))
	  {
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_name']", Name);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_address1']", Address);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_city']", City);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_state']", State);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_zip']", zip);
		  Assert.assertEquals(Element_Exist.existsElement("//input[@value='Create Facility']", driver), true, "Save button is not present");
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Create Facility']");
		  Thread.sleep(5000);
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_name']", "xpath"), "This field is required.", "Error message for Name field is not displayed");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_address1']", "xpath"), "", "Error message for Address field is displayed even if Address has been entered");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_city']", "xpath"), "", "Error message for City field is displayed even if city has been entered");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_state']", "xpath"), "", "Error message for State field is displayed even if state has been entered");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_zip']", "xpath"), "", "Error message for Zip field is displayed even if zip has been entered");
		  Reporter.log("New Facility functionality is working properly with Blank Name");
	  }
	  
	//Check Facility functionality with Blank Address
	  if(Scenario.contentEquals("Blank Address"))
	  {
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_name']", Name);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_address1']", Address);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_city']", City);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_state']", State);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_zip']", zip);
		  Assert.assertEquals(Element_Exist.existsElement("//input[@value='Create Facility']", driver), true, "Save button is not present");
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Create Facility']");
		  Thread.sleep(5000);
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_name']", "xpath"), "", "Error message for Name field is displayed even if Name has been entered");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_address1']", "xpath"), "This field is required.", "Error message for Address field is not displayed");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_city']", "xpath"), "", "Error message for City field is displayed even if city has been entered");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_state']", "xpath"), "", "Error message for State field is displayed even if state has been entered");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_zip']", "xpath"), "", "Error message for Zip field is displayed even if zip has been entered");
		  Reporter.log("New Facility functionality is working properly with Blank Address");
	  }
	  
	//Check Facility functionality with Blank City
	  if(Scenario.contentEquals("Blank City"))
	  {
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_name']", Name);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_address1']", Address);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_city']", City);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_state']", State);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_zip']", zip);
		  Assert.assertEquals(Element_Exist.existsElement("//input[@value='Create Facility']", driver), true, "Save button is not present");
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Create Facility']");
		  Thread.sleep(5000);
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_name']", "xpath"), "", "Error message for Name field is displayed even if Name has been entered");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_address1']", "xpath"), "", "Error message for Address field is displayed even if Address has been entered");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_city']", "xpath"), "This field is required.", "Error message for City field is not displayed");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_state']", "xpath"), "", "Error message for State field is displayed even if state has been entered");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_zip']", "xpath"), "", "Error message for Zip field is displayed even if zip has been entered");
		  Reporter.log("New Facility functionality is working properly with Blank City");
	  }
	  
	//Check Facility functionality with Blank State
	  if(Scenario.contentEquals("Blank State"))
	  {
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_name']", Name);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_address1']", Address);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_city']", City);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_state']", State);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_zip']", zip);
		  Assert.assertEquals(Element_Exist.existsElement("//input[@value='Create Facility']", driver), true, "Save button is not present");
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Create Facility']");
		  Thread.sleep(5000);
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_name']", "xpath"), "", "Error message for Name field is displayed even if Name has been entered");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_address1']", "xpath"), "", "Error message for Address field is displayed even if Address has been entered");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_city']", "xpath"), "", "Error message for City field is not displayed");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_state']", "xpath"), "This field is required.", "Error message for State field is not displayed");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_zip']", "xpath"), "", "Error message for Zip field is displayed even if zip has been entered");
		  Reporter.log("New Facility functionality is working properly with Blank State");
	  }
	  
	//Check Facility functionality with Blank zip
	  if(Scenario.contentEquals("Blank Zip"))
	  {
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_name']", Name);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_address1']", Address);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_city']", City);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_state']", State);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_zip']", zip);
		  Assert.assertEquals(Element_Exist.existsElement("//input[@value='Create Facility']", driver), true, "Save button is not present");
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Create Facility']");
		  Thread.sleep(5000);
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_name']", "xpath"), "", "Error message for Name field is displayed even if Name has been entered");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_address1']", "xpath"), "", "Error message for Address field is displayed even if Address has been entered");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_city']", "xpath"), "", "Error message for City field is displayed even if city has been entered");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_state']", "xpath"), "", "Error message for State field is displayed even if state has been entered");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//span[@class='help-block error' and @for='facility_address_attributes_zip']", "xpath"), "This field is required.", "Error message for Zip field is not displayed");
		  Reporter.log("New Facility functionality is working properly with Blank Zip");
	  }
	  
	//Check Facility functionality with Valid Inputs
	  if(Scenario.contentEquals("Valid Inputs"))
	  {
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_name']", Name);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_address1']", Address);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_city']", City);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_state']", State);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='facility_address_attributes_zip']", zip);
		  Assert.assertEquals(Element_Exist.existsElement("//input[@value='Create Facility']", driver), true, "Save button is not present");
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Create Facility']");
		  Thread.sleep(5000);
		  Assert.assertEquals(driver.getTitle().contains("We're sorry, but something went wrong"), false, "Error message displayed as We're sorry something went wrong while creating facility");
		  WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jGrowl-message']")));
		  Assert.assertEquals(element.getText(), "Facility created.", "Confirmation message not displayed as expected after user creation");
		  Assert.assertEquals(driver.getTitle(), "Facilities", "Page is not navigated to Facilities list after creation of Facility");
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//*[@id='DataTables_Table_0_filter']/label/input", Name);
		  Assert.assertEquals(Element_Exist.existsElement("//td[@class='dataTables_empty']", driver), true, "Facility is not added to list after creation");
		  Reporter.log("Create Facility functionality working properly with Valid Inputs");
	  }
  }
  //Function to check the Health System functionality
 @Test(dataProvider = "Health System")
public void HealthSystem(String Name , String Support_Email , String Supoort_phone , String Scenario) throws InterruptedException
{
	  WebDriver driver = Initialization.getDriver();
	  
	  WebDriverWait wait = new WebDriverWait(driver, 30);
	  ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='/admin/health_systems']");
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='New Health System']");
	  Thread.sleep(5000);
	  //check the health system functionality with Blank input
	  if(Scenario.contentEquals("Blank"))
	  {
		  GarbageValueClear.Clear(driver, "//*[@id='health_system_name']", "xpath");
		  GarbageValueClear.Clear(driver, "//*[@id='health_system_ca_contact_attributes_email']", "xpath");
		  GarbageValueClear.Clear(driver, "//*[@id='health_system_ca_contact_attributes_office_phone']", "xpath");
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Create Health System']");
		  Thread.sleep(5000);
		  Assert.assertEquals(driver.findElement(By.xpath("//span[@for='health_system_name']")).getText(), "This field is required.", "No Error message displayed for Name");
		  Assert.assertEquals(driver.findElement(By.xpath("//span[@for='health_system_ca_contact_attributes_email']")).getText(), "This field is required.", "No Error message displayed for Support email");
		  Reporter.log("Health system functionality is working properly with Blank Input");
	  }
	  
	//check the health system functionality with Blank Name
	  if(Scenario.contentEquals("Blank Name"))
	  {
		  GarbageValueClear.Clear(driver, "//*[@id='health_system_name']", "xpath");
		  GarbageValueClear.Clear(driver, "//*[@id='health_system_ca_contact_attributes_email']", "xpath");
		  GarbageValueClear.Clear(driver, "//*[@id='health_system_ca_contact_attributes_office_phone']", "xpath");
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//*[@id='health_system_name']", Name);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//*[@id='health_system_ca_contact_attributes_email']", Support_Email);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//*[@id='health_system_ca_contact_attributes_office_phone']", Supoort_phone);
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Create Health System']");
		  Thread.sleep(5000);
		  Assert.assertEquals(driver.findElement(By.xpath("//span[@for='health_system_name']")).getText(), "This field is required.", "No Error message displayed for Name");
		  Assert.assertEquals(driver.findElement(By.xpath("//span[@for='health_system_ca_contact_attributes_email']")).getText(), "", "Error message is displayed for Support email even if it is populated");
		  Reporter.log("Health system functionality is working properly with Blank Name");
	  }
	  
	//check the health system functionality with Blank Support Email
	  if(Scenario.contentEquals("Blank Support Email"))
	  {
		  GarbageValueClear.Clear(driver, "//*[@id='health_system_name']", "xpath");
		  GarbageValueClear.Clear(driver, "//*[@id='health_system_ca_contact_attributes_email']", "xpath");
		  GarbageValueClear.Clear(driver, "//*[@id='health_system_ca_contact_attributes_office_phone']", "xpath");
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//*[@id='health_system_name']", Name);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//*[@id='health_system_ca_contact_attributes_email']", Support_Email);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//*[@id='health_system_ca_contact_attributes_office_phone']", Supoort_phone);
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Create Health System']");
		  Thread.sleep(5000);
		  Assert.assertEquals(driver.findElement(By.xpath("//span[@for='health_system_name']")).getText(), "", "Error message is displayed for Name even if it is populated");
		  Assert.assertEquals(driver.findElement(By.xpath("//span[@for='health_system_ca_contact_attributes_email']")).getText(), "This field is required.", "No Error message displayed for Support email");
		  Reporter.log("Health system functionality is working properly with Blank Support Email");
	  }
	  
	//check the health system functionality with Invalid Support Email
	  if(Scenario.contentEquals("Invalid Support Email"))
	  {
		  GarbageValueClear.Clear(driver, "//*[@id='health_system_name']", "xpath");
		  GarbageValueClear.Clear(driver, "//*[@id='health_system_ca_contact_attributes_email']", "xpath");
		  GarbageValueClear.Clear(driver, "//*[@id='health_system_ca_contact_attributes_office_phone']", "xpath");
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//*[@id='health_system_name']", Name);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//*[@id='health_system_ca_contact_attributes_email']", Support_Email);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//*[@id='health_system_ca_contact_attributes_office_phone']", Supoort_phone);
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Create Health System']");
		  Thread.sleep(5000);
		  Assert.assertEquals(driver.findElement(By.xpath("//span[@for='health_system_name']")).getText(), "", "Error message is displayed for Name even if it is populated");
		  Assert.assertEquals(driver.findElement(By.xpath("//span[@for='health_system_ca_contact_attributes_email']")).getText(), "Please enter a valid email address.", "No Error message displayed for Support email");
		  Reporter.log("Health system functionality is working properly with Invalid Support Email");
	  }
	//check the health system functionality with Valid Inputs
	  if(Scenario.contentEquals("Valid Inputs"))
	  {
		  GarbageValueClear.Clear(driver, "//*[@id='health_system_name']", "xpath");
		  GarbageValueClear.Clear(driver, "//*[@id='health_system_ca_contact_attributes_email']", "xpath");
		  GarbageValueClear.Clear(driver, "//*[@id='health_system_ca_contact_attributes_office_phone']", "xpath");
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//*[@id='health_system_name']", Name);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//*[@id='health_system_ca_contact_attributes_email']", Support_Email);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//*[@id='health_system_ca_contact_attributes_office_phone']", Supoort_phone);
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Create Health System']");
		  Thread.sleep(5000);
		  Assert.assertEquals(driver.getTitle().contains("We're sorry, but something went wrong"), false, "Error message displayed as We're sorry something went wrong while creating facility");
		  WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jGrowl-message']")));
		  Assert.assertEquals(element.getText(), "Health System created.", "Confirmation message not displayed as expected after user creation");
		  Assert.assertEquals(driver.getTitle(), "Health System Details", "Page is not navigated to Health System Details page after creating the HS");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//legend", "xpath"), Name, "Health system Name is not matching with Name used while creating or it is not displayed");
		  ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='/admin/health_systems']");
		  Thread.sleep(5000);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//*[@id='DataTables_Table_0_filter']/label/input", Name);
		  Thread.sleep(5000);
		  Assert.assertEquals(Element_Exist.existsElement("//td[@class='dataTables_empty']", driver), false, "Health system is not updated at list");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//tr[@class='odd']/td[1]", "xpath"), Name, "Name of HS is not matching at list of Health system");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//tr[@class='odd']/td[2]", "xpath"), Support_Email, "Support Email of HS is not matching at list of Health system");
		  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//tr[@class='odd']/td[3]", "xpath"), Supoort_phone, "Supoort phone of HS is not matching at list of Health system");
		  Reporter.log("Health system functionality is working properly with Valid Inputs");
	  }
}
  
	@DataProvider(name = "Create User") 
	 
	 public static Object[][] BookDevice() throws BiffException, IOException{
	  
	   FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"\\src\\xls\\Cohealo.xls");
	   Workbook w = Workbook.getWorkbook(fi);
	   Sheet  s = w.getSheet("CreateUser");
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
	
	@DataProvider(name = "Health System") 
	 
	 public static Object[][] Health_System() throws BiffException, IOException{
	  
	   FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"\\src\\xls\\Cohealo.xls");
	   Workbook w = Workbook.getWorkbook(fi);
	   Sheet  s = w.getSheet("Health_System");
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
	
	@DataProvider(name = "New Facility") 
	 
	 public static Object[][] NewFacility() throws BiffException, IOException{
	  
	   FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"\\src\\xls\\Cohealo.xls");
	   Workbook w = Workbook.getWorkbook(fi);
	   Sheet  s = w.getSheet("NewFacility");
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
	
	@DataProvider(name = "Add_Device") 
	 
	 public static Object[][] Add_Device() throws BiffException, IOException{
	  
	   FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"\\src\\xls\\Cohealo.xls");
	   Workbook w = Workbook.getWorkbook(fi);
	   Sheet  s = w.getSheet("Add Single Device");
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
	

	
  @BeforeTest
  public void beforeTest() throws InterruptedException 
	{
		WebDriver driver = Initialization.getDriver();
		Properties CONFIG = Initialization.getconfig();
		//clearing the user name and password fields of login
		GarbageValueClear.Clear(driver, "user_email", "id");
		GarbageValueClear.Clear(driver, "user_password", "id");
		//Sending User name password values to user name password text boxes	
		SendingTestValueInTextBox.SendValue(driver, "id", "user_email", CONFIG.getProperty("ROMUser"));
		SendingTestValueInTextBox.SendValue(driver, "id", "user_password", CONFIG.getProperty("Password"));
		//click on Sign In button
		ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Sign in']");
		Thread.sleep(10000);

	}

  @AfterTest
  public void afterTest() throws InterruptedException 	
  {
		WebDriver driver = Initialization.getDriver();
		ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='menu inline dropdown user']/a");
		Thread.sleep(3000);
		ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon-signout']");
		Thread.sleep(10000);		
  }

}
