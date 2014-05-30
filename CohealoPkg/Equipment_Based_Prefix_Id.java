package CohealoPkg;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import InitializationPkg.Initialization;
import UtilityPkg.ClickActionOnObjects;
import UtilityPkg.Element_Exist;
import UtilityPkg.GetDate;
import UtilityPkg.ReadingTextFromObjects;
import UtilityPkg.SendingTestValueInTextBox;

public class Equipment_Based_Prefix_Id {
  @Test
  public void Equipment_Based_Prefix_Id_Test() throws InterruptedException 
  {
	  WebDriver driver = Initialization.getDriver();
	  Properties or = Initialization.getor();
	  WebDriverWait wait = new WebDriverWait(driver , 100);
	  ClickActionOnObjects.ClickByJavascript(driver, or.getProperty("Entity_Manager"));
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, or.getProperty("Health_System"));
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, or.getProperty("New_HS"));
	  Thread.sleep(5000);
	  String day = GetDate.getday();
	  String Month = GetDate.getMonth();
	  String year = GetDate.getyear();
	  SendingTestValueInTextBox.SendValue(driver, "id", "health_system_name", "Real Health System");
	  SendingTestValueInTextBox.SendValue(driver, "id", "health_system_equipment_id_prefix", "CohealoHS");
	  SendingTestValueInTextBox.SendValue(driver, "id", "health_system_ca_contact_attributes_email", "cohealohs"+day+year+Month+GetDate.gettime()+"@cohealo.com");
	  SendingTestValueInTextBox.SendValue(driver, "id", "health_system_ca_contact_attributes_office_phone", "5555555555");
	  String domain = "cohealo"+GetDate.gettime()+".com";
	  SendingTestValueInTextBox.SendValue(driver, "id", "health_system_domain_names_attributes_0_name", domain);
	  ClickActionOnObjects.ClickByJavascript(driver, or.getProperty("Create_HS"));
	  Thread.sleep(1000);
	  try
	  	{
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jGrowl-message']")));
	  	}
	  
	  	catch(Exception e)
	  	{
	  	  Reporter.log("Confirmation message is not displayed after creating the device within specified time");
	  	}
	  Assert.assertEquals(driver.getTitle(), "Health System Details", "Page is not navigated to Health System Details page after creating HS");
	  ClickActionOnObjects.ClickByJavascript(driver, or.getProperty("Health_System"));
	  Thread.sleep(5000);
	  SendingTestValueInTextBox.SendValue(driver, "xpath", or.getProperty("Search_Entity_Manger"), domain);
	  Thread.sleep(4000);
	  Assert.assertEquals(Element_Exist.existsElement(or.getProperty("No_Matching_Records"), driver), false, "Health System is not displayed at HS list");
	  ClickActionOnObjects.ClickByJavascript(driver, or.getProperty("Facilities"));
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, or.getProperty("New_Facility"));
	  Thread.sleep(5000);
	  SendingTestValueInTextBox.SendValue(driver, "id", "facility_name", "RealHS Facility");
	  SendingTestValueInTextBox.SendValue(driver, "id", "facility_address_attributes_address1", "421 E DRACHMAN");
	  SendingTestValueInTextBox.SendValue(driver, "id", "facility_address_attributes_city", "Florida");
	  SendingTestValueInTextBox.SendValue(driver, "id", "facility_address_attributes_state", "CA");
	  SendingTestValueInTextBox.SendValue(driver, "id", "facility_address_attributes_zip", "34747");
	  ClickActionOnObjects.ClickObject(driver, "health_system_id", "id");
	  Thread.sleep(5000);
	  Select s = new Select(driver.findElement(By.id("health_system_id")));
	  s.selectByVisibleText("Real Health System");
	  ClickActionOnObjects.ClickObject(driver, or.getProperty("Create_Facility"), "xpath");
	  Thread.sleep(1000);
	  try
	  	{
		  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='jGrowl-message']"))));
	  	}
	  catch(Exception e)
	  	{
		  Reporter.log("Confirmation message is not displayed after creating the Facility within specified time");
	  	}
	  Assert.assertEquals(driver.getTitle(), "Facility Details", "Page is not navigated to Facility Details page after creating Facility");
	  ClickActionOnObjects.ClickByJavascript(driver, or.getProperty("Facilities"));
	  Thread.sleep(5000);
	  SendingTestValueInTextBox.SendValue(driver, "xpath", or.getProperty("Search_Entity_Manger"), "RealHS Facility");
	  Assert.assertEquals(Element_Exist.existsElement(or.getProperty("No_Matching_Records"), driver), false, "Facility is not displayed at Facility list");
	  ClickActionOnObjects.ClickByJavascript(driver, or.getProperty("Add_Single_Device"));
	  Thread.sleep(5000);
	  SendingTestValueInTextBox.SendValue(driver, "id", "equipment_manufacturer", "Philips");
	  SendingTestValueInTextBox.SendValue(driver, "id", "equipment_model", "2.41");
	  SendingTestValueInTextBox.SendValue(driver, "id", "equipment_description", "Surgery");
	  ClickActionOnObjects.ClickObject(driver, "health_system_id", "id");
	  Thread.sleep(4000);
	  Select s1 = new Select(driver.findElement(By.id("health_system_id")));
	  s1.selectByVisibleText("Real Health System");
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickObject(driver, "equipment_owned_by_facility_id", "id");
	  Thread.sleep(4000);
	  Select s2 = new Select(driver.findElement(By.id("equipment_owned_by_facility_id")));
	  s2.selectByVisibleText("RealHS Facility");
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, or.getProperty("Continue"));
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, or.getProperty("Extra_Information"));
	  Thread.sleep(3000);
	  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//label[@for='equipment_hs_equipment_ident']", "xpath"), "CohealoHS", "Equipment Prefix Id is not matching with given during creation of HS");
	  ClickActionOnObjects.ClickObject(driver, "equipment_year", "id");
	  Thread.sleep(4000);
	  Select s3 = new Select(driver.findElement(By.id("equipment_year")));
	  s3.selectByIndex(3);
	  SendingTestValueInTextBox.SendValue(driver, "id", "equipment_serial", day+year+Month);
	  SendingTestValueInTextBox.SendValue(driver, "id", "equipment_hs_equipment_ident", Month+year+day);
	  SendingTestValueInTextBox.SendValue(driver, "id", "equipment_height", "10");
	  SendingTestValueInTextBox.SendValue(driver, "id", "equipment_width", "10");
	  SendingTestValueInTextBox.SendValue(driver, "id", "equipment_length", "10");
	  SendingTestValueInTextBox.SendValue(driver, "id", "equipment_weight", "100");
	  ClickActionOnObjects.ClickByJavascript(driver, or.getProperty("Save"));
	  Thread.sleep(1000);
	  try
	  	{
		  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='jGrowl-message']"))));
	  	}
	  catch(Exception e)
	  	{
		  Reporter.log("Confirmation message is not displayed after creating the device within specified time");
	  	}
	  Thread.sleep(5000);
	  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//tr[3]/td[1]", "xpath"), "CohealoHS", "Equipment Prefix Id is not matching with given during creation of HS");
	  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//tr[3]/td[2]", "xpath"), Month+year+day, "Equipment Prefix Id value is not matching with given during creation of device");
  }
  
  @BeforeTest
  public void beforetest() throws InterruptedException
  {
	  WebDriver driver = Initialization.getDriver();
	  Properties config = Initialization.getconfig();
	  SendingTestValueInTextBox.SendValue(driver, "id", "user_email",config.getProperty("ROMUser"));
	  SendingTestValueInTextBox.SendValue(driver, "id", "user_password",config.getProperty("Password"));
	  ClickActionOnObjects.ClickObject(driver, "//input[@value='Sign in']", "xpath");
	  Thread.sleep(10000);
  }
}
