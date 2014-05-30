package CohealoPkg;

import java.io.FileInputStream;

import java.io.IOException;

import java.util.Calendar;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import InitializationPkg.Initialization;
import UtilityPkg.ClickActionOnObjects;
import UtilityPkg.Element_Exist;
import UtilityPkg.GarbageValueClear;
import UtilityPkg.ReadingTextFromObjects;
import UtilityPkg.SendingTestValueInTextBox;

public class All_User_Roles_Test {
  @Test
  public void Check_All_User_Roles() throws InterruptedException, IOException 
  {
	  WebDriver driver = Initialization.getDriver();
	  WebDriverWait wait =new WebDriverWait(driver, 100);
	  ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='menu inline dropdown user']/a");
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon-user']");
	  Thread.sleep(5000);
	  Assert.assertEquals(Element_Exist.existsElement("//*[@id='user_health_system_name']", driver), false, "Admin is assigned to the Health system");
	  Assert.assertEquals(Element_Exist.existsElement("//*[@id='user_facility_id']", driver), false, "Admin is assigned to the Facility");
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, "//div[@id='primary-navigation']/ul/li[3]/a");
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon icon-plus-sign-alt']");
	  Thread.sleep(5000);
	  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_producer']", driver), true, "Checkbox for Producer user is not present for Admin User");
	  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_consumer']", driver), true, "Checkbox for Consumer user is not present for Admin User");
	  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_health_system_admin']", driver), true, "Checkbox for HS Admin user is not present for Admin User");
	  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_driver']", driver), true, "Checkbox for driver user is not present for Admin User");
	  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_admin']", driver), true, "Checkbox for Admin user is not present for Admin User");
	  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_rom']", driver), true, "Checkbox for ROM user is not present for Admin User");
	  String Email = create_user("Producer");
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//div[@class='dataTables_filter']/label/input", Email);
	  Thread.sleep(2000);
	  Assert.assertEquals(Element_Exist.existsElement("//td[@class='dataTables_empty']", driver), false, "User is not displaying at list after creating");
	  driver.findElement(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr/td[6]/div/a[1]/i")).click();
	  Thread.sleep(10000);
	  check_producer_roles();
	  signin();
	  ClickActionOnObjects.ClickByJavascript(driver, "//div[@id='primary-navigation']/ul/li[3]/a");
	  Thread.sleep(5000);
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//div[@class='dataTables_filter']/label/input", Email);
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//i[@class='icon-edit']")).click();
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, "//label[@for='user_roles_producer']");
	  ClickActionOnObjects.ClickByJavascript(driver, "//label[@for='user_roles_consumer']");
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Update User']");
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jGrowl-message']")));
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//div[@class='dataTables_filter']/label/input", Email);
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr/td[6]/div/a[1]/i")).click();
	  Thread.sleep(10000);
	  check_Consumer_roles();
	  signin();
	  ClickActionOnObjects.ClickByJavascript(driver, "//div[@id='primary-navigation']/ul/li[3]/a");
	  Thread.sleep(5000);
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//div[@class='dataTables_filter']/label/input", Email);
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//i[@class='icon-edit']")).click();
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, "//label[@for='user_roles_consumer']");
	  ClickActionOnObjects.ClickByJavascript(driver, "//label[@for='user_roles_health_system_admin']");
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Update User']");
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jGrowl-message']")));
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//div[@class='dataTables_filter']/label/input", Email);
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr/td[6]/div/a[1]/i")).click();
	  Thread.sleep(10000);
	  check_HS_Admin(); 
	  signin();
	  ClickActionOnObjects.ClickByJavascript(driver, "//div[@id='primary-navigation']/ul/li[3]/a");
	  Thread.sleep(5000);
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//div[@class='dataTables_filter']/label/input", Email);
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//i[@class='icon-edit']")).click();
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, "//label[@for='user_roles_health_system_admin']");
	  ClickActionOnObjects.ClickByJavascript(driver, "//label[@for='user_roles_driver']");
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Update User']");
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jGrowl-message']")));
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//div[@class='dataTables_filter']/label/input", Email);
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr/td[6]/div/a[1]/i")).click();
	  Thread.sleep(10000);
	  check_Driver_roles();
	  signin();
	  ClickActionOnObjects.ClickByJavascript(driver, "//div[@id='primary-navigation']/ul/li[3]/a");
	  Thread.sleep(5000);
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//div[@class='dataTables_filter']/label/input", Email);
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//i[@class='icon-edit']")).click();
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, "//label[@for='user_roles_driver']");
	  ClickActionOnObjects.ClickByJavascript(driver, "//label[@for='user_roles_rom']");
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Update User']");
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jGrowl-message']")));
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//div[@class='dataTables_filter']/label/input", Email);
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr/td[6]/div/a[1]/i")).click();
	  Thread.sleep(10000);
	  check_ROM_roles();
  }
  
  public void check_producer_roles() throws InterruptedException
   {
	  WebDriver driver = Initialization.getDriver();
	  Assert.assertEquals(driver.getTitle().contentEquals("My Bookings"), true, "Swith user functionality not properly woking for producer user");
	  Assert.assertEquals(Element_Exist.existsElement("//div[@id='primary-navigation']/ul/li[3]/a", driver), false, "Producer user has Entity Manager access");
	  Assert.assertEquals(Element_Exist.existsElement("//div[@id='primary-navigation']/ul/li[1]/a", driver), true, "My Bookings tab is not present for Producer user");
	  Assert.assertEquals(Element_Exist.existsElement("//div[@id='primary-navigation']/ul/li[2]/a", driver), true, "Book A Device tab is not present for Producer user");
	  ClickActionOnObjects.ClickByJavascript(driver, "//div[@id='primary-navigation']/ul/li[2]/a");
	  Thread.sleep(5000);
	  if(Element_Exist.existsElement("//div[@class='popover fade bottom in']", driver))
	  {
		  ClickActionOnObjects.ClickByJavascript(driver, "//*[@id='header']/div/div/div/form/div[3]/div[2]/div");
		  Thread.sleep(5000);
	  }
	  
	  Assert.assertEquals(driver.getTitle().contentEquals("Equipment"), true, "Page is not navigated to Equipment page after clicking on Book A Device tab");
	  ClickActionOnObjects.ClickByJavascript(driver, "//table[@class='table']/tbody/tr[1]/td[3]");
	  Thread.sleep(5000);
	  if(Element_Exist.existsElement("//div[@class='popover fade left in']", driver))
	  {
		  ClickActionOnObjects.ClickByJavascript(driver, "//*[@id='new_events_booking']/div[2]/div[2]/div[2]/div");
		  Thread.sleep(5000);
	  }
	  String ref_no = ReadingTextFromObjects.GetText(driver, "//*[@id='retab2']/table/tbody/tr[7]/td[2]", "xpath");
	  Assert.assertEquals(Element_Exist.existsElement("//a[@href='/equipment/"+ref_no+"/edit']", driver), true, "Edit link for Additional information is not present for Producer user");
	  ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='#retab1']");
	  Thread.sleep(5000);
	  Assert.assertEquals(Element_Exist.existsElement("//a[@href='/equipment/"+ref_no+"/edit']", driver), true, "Edit link for Available Accessories is not present for Producer user");
	  ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='#retab3']");
	  Thread.sleep(5000);
	  Assert.assertEquals(Element_Exist.existsElement("//a[@href='/equipment/"+ref_no+"/edit']", driver), true, "Edit link for Disposables is not present for Producer user");
	  ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='menu inline dropdown user']/a");
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon-user']");
	  Thread.sleep(5000);
	  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_health_system_name' and @disabled='disabled']", driver), true, "HS dropdown is not present or it is not disbled for Producer user");
	  Assert.assertEquals(Element_Exist.existsElement("//*[@id='user_facility_id']", driver), true, "Facility dropdown is not present for Producer user");
	  Reporter.log("<br/>");
	  Reporter.log("Producer User");
	  Reporter.log("<br/>");
	  Reporter.log("1.Producer has no Entity Manager access"+"<br/>"+"2.Producer can Edit Devices"+"<br/>"+"3.Producer has HS assigned and canot edit it , also it can select facilities");
	  signout();
  }
  
  public void check_Consumer_roles() throws InterruptedException
  {
	  WebDriver driver = Initialization.getDriver();
	  Assert.assertEquals(driver.getTitle().contentEquals("My Bookings"), true, "Swith user functionality not properly woking for Consumer user");
	  Assert.assertEquals(Element_Exist.existsElement("//div[@id='primary-navigation']/ul/li[3]/a", driver), false, "Consumer user has Entity Manager access");
	  Assert.assertEquals(Element_Exist.existsElement("//div[@id='primary-navigation']/ul/li[1]/a", driver), true, "My Bookings tab is not present for Consumer user");
	  Assert.assertEquals(Element_Exist.existsElement("//div[@id='primary-navigation']/ul/li[2]/a", driver), true, "Book A Device tab is not present for Consumer user");
	  ClickActionOnObjects.ClickByJavascript(driver, "//div[@id='primary-navigation']/ul/li[2]/a");
	  Thread.sleep(5000);
	  if(Element_Exist.existsElement("//div[@class='popover fade bottom in']", driver))
	  {
		  ClickActionOnObjects.ClickByJavascript(driver, "//*[@id='header']/div/div/div/form/div[3]/div[2]/div");
		  Thread.sleep(5000);
	  }
	  Assert.assertEquals(driver.getTitle().contentEquals("Equipment"), true, "Page is not navigated to Equipment page after clicking on Book A Device tab");
	  ClickActionOnObjects.ClickByJavascript(driver, "//table[@class='table']/tbody/tr[1]/td[3]");
	  Thread.sleep(5000);
	  if(Element_Exist.existsElement("//div[@class='popover fade left in']", driver))
	  {
		  ClickActionOnObjects.ClickByJavascript(driver, "//*[@id='new_events_booking']/div[2]/div[2]/div[2]/div");
		  Thread.sleep(5000);
	  }
	  String ref_no = ReadingTextFromObjects.GetText(driver, "//*[@id='retab2']/table/tbody/tr[7]/td[2]", "xpath");
	  Assert.assertEquals(Element_Exist.existsElement("//a[@href='/equipment/"+ref_no+"/edit']", driver), false, "Edit link for Additional information is present for Consumer user");
	  ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='#retab1']");
	  Thread.sleep(5000);
	  Assert.assertEquals(Element_Exist.existsElement("//a[@href='/equipment/"+ref_no+"/edit']", driver), false, "Edit link for Available Accessories is present for Consumer user");
	  ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='#retab3']");
	  Thread.sleep(5000);
	  Assert.assertEquals(Element_Exist.existsElement("//a[@href='/equipment/"+ref_no+"/edit']", driver), false, "Edit link for Disposables is present for Consumer user");
	  ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='menu inline dropdown user']/a");
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon-user']");
	  Thread.sleep(5000);
	  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_health_system_name' and @disabled='disabled']", driver), true, "HS dropdown is not present or it is not disbled for Consumer user");
	  Assert.assertEquals(Element_Exist.existsElement("//*[@id='user_facility_id']", driver), true, "Facility dropdown is not present for Consumer user");
	  Reporter.log("<br/>");
	  Reporter.log("Consumer User");
	  Reporter.log("<br/>");
	  Reporter.log("1.Consumer has no Entity Manager access"+"<br/>"+"2.Consumer cannot Edit Devices"+"<br/>"+"3.Consumer has HS assigned and canot edit it , also it can select facilities");
	  signout();
  }
  
  public void check_ROM_roles() throws InterruptedException
  {
	  WebDriver driver = Initialization.getDriver();
	  Assert.assertEquals(driver.getTitle().contentEquals("My Bookings"), true, "Swith user functionality not properly woking for ROM user");
	  Assert.assertEquals(Element_Exist.existsElement("//div[@id='primary-navigation']/ul/li[3]/a", driver), true, "ROM user has no Entity Manager access");
	  Assert.assertEquals(Element_Exist.existsElement("//div[@id='primary-navigation']/ul/li[1]/a", driver), true, "My Bookings tab is not present for ROM user");
	  Assert.assertEquals(Element_Exist.existsElement("//div[@id='primary-navigation']/ul/li[2]/a", driver), true, "Book A Device tab is not present for ROM user");
	  ClickActionOnObjects.ClickByJavascript(driver, "//div[@id='primary-navigation']/ul/li[2]/a");
	  Thread.sleep(5000);
	  if(Element_Exist.existsElement("//div[@class='popover fade bottom in']", driver))
	  {
		  ClickActionOnObjects.ClickByJavascript(driver, "//*[@id='header']/div/div/div/form/div[3]/div[2]/div");
		  Thread.sleep(5000);
	  }
	  Assert.assertEquals(driver.getTitle().contentEquals("Equipment"), true, "Page is not navigated to Equipment page after clicking on Book A Device tab");
	  ClickActionOnObjects.ClickByJavascript(driver, "//table[@class='table']/tbody/tr[1]/td[3]");
	  Thread.sleep(5000);
	  if(Element_Exist.existsElement("//div[@class='popover fade left in']", driver))
	  {
		  ClickActionOnObjects.ClickByJavascript(driver, "//*[@id='new_events_booking']/div[2]/div[2]/div[2]/div");
		  Thread.sleep(5000);
	  }
	  String ref_no = ReadingTextFromObjects.GetText(driver, "//*[@id='retab2']/table/tbody/tr[7]/td[2]", "xpath");
	  Assert.assertEquals(Element_Exist.existsElement("//a[@href='/equipment/"+ref_no+"/edit']", driver), true, "Edit link for Additional information is not present for ROM user");
	  ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='#retab1']");
	  Thread.sleep(5000);
	  Assert.assertEquals(Element_Exist.existsElement("//a[@href='/equipment/"+ref_no+"/edit']", driver), true, "Edit link for Available Accessories is not present for ROM user");
	  ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='#retab3']");
	  Thread.sleep(5000);
	  Assert.assertEquals(Element_Exist.existsElement("//a[@href='/equipment/"+ref_no+"/edit']", driver), true, "Edit link for Disposables is not present for ROM user");
	  ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='menu inline dropdown user']/a");
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon-user']");
	  Thread.sleep(5000);
	  Assert.assertEquals(Element_Exist.existsElement("//div[@class='well well-small']", driver), true, "Health system is not displayed for ROM user");
	  Assert.assertEquals(Element_Exist.existsElement("//*[@id='user_facility_id']", driver), false, "Facility dropdown is present for ROM user");
	  ClickActionOnObjects.ClickByJavascript(driver, "//div[@id='primary-navigation']/ul/li[3]/a");
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon icon-plus-sign-alt']");
	  Thread.sleep(5000);
	  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_producer']", driver), true, "Checkbox for Producer user is not present for ROM User");
	  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_consumer']", driver), true, "Checkbox for Consumer user is not present for ROM User");
	  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_health_system_admin']", driver), true, "Checkbox for HS Admin user is not present for ROM User");
	  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_driver']", driver), true, "Checkbox for driver user is not present for ROM User");
	  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_admin']", driver), false, "Checkbox for Admin user is present for ROM User");
	  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_rom']", driver), false, "Checkbox for ROM user is present for ROM User");
	  Reporter.log("<br/>");
	  Reporter.log("ROM User");
	  Reporter.log("<br/>");
	  Reporter.log("1.ROM has Entity Manager access"+"<br/>"+"2.ROM can Edit Devices"+"<br/>"+"3.ROM can not create ROM/Admin , can create rest of roles");
	  signout();
  }
  
  
  public void check_HS_Admin() throws InterruptedException
  {
	  WebDriver driver = Initialization.getDriver();
	  Assert.assertEquals(driver.getTitle().contentEquals("My Bookings"), true, "Swith user functionality not properly woking for HS Admin user");
	  Assert.assertEquals(Element_Exist.existsElement("//div[@id='primary-navigation']/ul/li[3]/a", driver), true, "HS Admin user has no Entity Manager access");
	  Assert.assertEquals(Element_Exist.existsElement("//div[@id='primary-navigation']/ul/li[1]/a", driver), true, "My Bookings tab is not present for HS Admin user");
	  Assert.assertEquals(Element_Exist.existsElement("//div[@id='primary-navigation']/ul/li[2]/a", driver), true, "Book A Device tab is not present for HS Admin user");
	  ClickActionOnObjects.ClickByJavascript(driver, "//div[@id='primary-navigation']/ul/li[2]/a");
	  Thread.sleep(5000);
	  if(Element_Exist.existsElement("//div[@class='popover fade bottom in']", driver))
	  {
		  ClickActionOnObjects.ClickByJavascript(driver, "//*[@id='header']/div/div/div/form/div[3]/div[2]/div");
		  Thread.sleep(5000);
	  }
	  Assert.assertEquals(driver.getTitle().contentEquals("Equipment"), true, "Page is not navigated to Equipment page after clicking on Book A Device tab");
	  ClickActionOnObjects.ClickByJavascript(driver, "//table[@class='table']/tbody/tr[1]/td[3]");
	  Thread.sleep(5000);
	  if(Element_Exist.existsElement("//div[@class='popover fade left in']", driver))
	  {
		  ClickActionOnObjects.ClickByJavascript(driver, "//*[@id='new_events_booking']/div[2]/div[2]/div[2]/div");
		  Thread.sleep(5000);
	  }
	  String ref_no = ReadingTextFromObjects.GetText(driver, "//*[@id='retab2']/table/tbody/tr[7]/td[2]", "xpath");
	  Assert.assertEquals(Element_Exist.existsElement("//a[@href='/equipment/"+ref_no+"/edit']", driver), false, "Edit link for Additional information is present for HS Admin user");
	  ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='#retab1']");
	  Thread.sleep(5000);
	  Assert.assertEquals(Element_Exist.existsElement("//a[@href='/equipment/"+ref_no+"/edit']", driver), false, "Edit link for Available Accessories is present for HS Admin user");
	  ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='#retab3']");
	  Thread.sleep(5000);
	  Assert.assertEquals(Element_Exist.existsElement("//a[@href='/equipment/"+ref_no+"/edit']", driver), false, "Edit link for Disposables is present for HS Admin user");
	  ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='menu inline dropdown user']/a");
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon-user']");
	  Thread.sleep(5000);
	  Assert.assertEquals(Element_Exist.existsElement("//div[@class='well well-small']", driver), true, "Health system is not displayed for HS Admin user");
	  Assert.assertEquals(Element_Exist.existsElement("//*[@id='user_facility_id']", driver), false, "Facility dropdown is present for HS Admin user");
	  ClickActionOnObjects.ClickByJavascript(driver, "//div[@id='primary-navigation']/ul/li[3]/a");
	  Thread.sleep(5000);
	  Assert.assertEquals(Element_Exist.existsElement("//i[@class='icon-exchange']", driver), false, "Switch user option is present for HS Admin user");
	  ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon icon-plus-sign-alt']");
	  Thread.sleep(5000);
	  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_producer']", driver), true, "Checkbox for Producer user is not present for HS Admin User");
	  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_consumer']", driver), true, "Checkbox for Consumer user is not present for HS Admin User");
	  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_health_system_admin']", driver), false, "Checkbox for HS Admin user is present for HS Admin User");
	  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_driver']", driver), false, "Checkbox for driver user is not present for HS Admin User");
	  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_admin']", driver), false, "Checkbox for Admin user is present for HS Admin User");
	  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_rom']", driver), false, "Checkbox for ROM user is present for HS Admin User");
	  Assert.assertEquals(Element_Exist.existsElement("//li[@class='dropdown-submenu']", driver), false, "Devices option is present for HS Admin");
	  ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='/admin/health_systems']");
	  Thread.sleep(5000);
	  Assert.assertEquals(Element_Exist.existsElement("//i[@class='icon-edit-sign']", driver), false, "Edit HS option is present for HS Admin");
	  Assert.assertEquals(Element_Exist.existsElement("//i[@class='icon-remove']", driver), false, "Delete HS option is present for HS Admin");
	  Assert.assertEquals(Element_Exist.existsElement("//i[@class='icon-eye-open']", driver), true, "Show HS option is not present for HS Admin");
	  Assert.assertEquals(Element_Exist.existsElement("//input[@value='New Health System']", driver), false, "Create new HS option is present for HS Admin");
	  ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='/admin/facilities']");
	  Thread.sleep(5000);
	  Assert.assertEquals(Element_Exist.existsElement("//input[@value='New Facility']", driver), false, "Create new facility option is present for HS Admin");
	  Assert.assertEquals(Element_Exist.existsElement("//i[@class='icon-edit-sign']", driver), false, "Edit Facility option is present for HS Admin");
	  Assert.assertEquals(Element_Exist.existsElement("//i[@class='icon-remove']", driver), false, "Delete Facility option is present for HS Admin");
	  Assert.assertEquals(Element_Exist.existsElement("//i[@class='icon-eye-open']", driver), true, "Show Facility option is not present for HS Admin");
	  Reporter.log("<br/>");
	  Reporter.log("HS Admin");
	  Reporter.log("<br/>");
	  Reporter.log("1.HS Admin has no Facility Assignment"+"<br/>"+"2.Cannot edit device"+"<br/>"+"3.Cannot switch user"+"<br/>"+"4.Can create consumer/producer roles only"+"<br/>"+"5.Cannot create/edit/delete facilities/HS, also cannot create device.");
	  signout();
  }
  
  public void check_Driver_roles() throws InterruptedException
  {
	  	WebDriver driver = Initialization.getDriver();
	  	WebDriverWait wait =new WebDriverWait(driver, 10);
	  	Assert.assertEquals(driver.getTitle().contentEquals("My Bookings"), true, "Swith user functionality not properly woking for Driver user");
		Assert.assertEquals(Element_Exist.existsElement("//div[@id='primary-navigation']/ul/li[3]/a", driver), true, "Driver user has no Entity Manager access");
		Assert.assertEquals(Element_Exist.existsElement("//div[@id='primary-navigation']/ul/li[1]/a", driver), true, "My Bookings tab is not present for Driver user");
		Assert.assertEquals(Element_Exist.existsElement("//div[@id='primary-navigation']/ul/li[2]/a", driver), true, "Book A Device tab is not present for Driver user");
		ClickActionOnObjects.ClickByJavascript(driver, "//div[@id='primary-navigation']/ul/li[2]/a");
		Thread.sleep(5000);
		if(Element_Exist.existsElement("//div[@class='popover fade bottom in']", driver))
		  {
			  ClickActionOnObjects.ClickByJavascript(driver, "//*[@id='header']/div/div/div/form/div[3]/div[2]/div");
			  Thread.sleep(5000);
		  }
		Assert.assertEquals(driver.getTitle().contentEquals("Equipment"), true, "Page is not navigated to Equipment page after clicking on Book A Device tab");
		ClickActionOnObjects.ClickByJavascript(driver, "//table[@class='table']/tbody/tr[1]/td[3]");
		Thread.sleep(5000);
		if(Element_Exist.existsElement("//div[@class='popover fade left in']", driver))
		  {
			  ClickActionOnObjects.ClickByJavascript(driver, "//*[@id='new_events_booking']/div[2]/div[2]/div[2]/div");
			  Thread.sleep(5000);
		  }
		
		  String ref_no = ReadingTextFromObjects.GetText(driver, "//*[@id='retab2']/table/tbody/tr[7]/td[2]", "xpath");
		  Assert.assertEquals(Element_Exist.existsElement("//a[@href='/equipment/"+ref_no+"/edit']", driver), true, "Edit link for Additional information is not present for Driver user");
		  ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='#retab1']");
		  Thread.sleep(5000);
		  Assert.assertEquals(Element_Exist.existsElement("//a[@href='/equipment/"+ref_no+"/edit']", driver), true, "Edit link for Available Accessories is not present for Driver user");
		  ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='#retab3']");
		  Thread.sleep(5000);
		  Assert.assertEquals(Element_Exist.existsElement("//a[@href='/equipment/"+ref_no+"/edit']", driver), true, "Edit link for Disposables is not present for Driver user");
		  ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='menu inline dropdown user']/a");
		  Thread.sleep(5000);
		  ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon-user']");
		  Thread.sleep(5000);
		  Assert.assertEquals(Element_Exist.existsElement("//div[@class='well well-small']", driver), true, "Health system is not displayed for Driver user");
		  Assert.assertEquals(Element_Exist.existsElement("//*[@id='user_facility_id']", driver), false, "Facility dropdown is present for Driver user");
		  ClickActionOnObjects.ClickByJavascript(driver, "//div[@id='primary-navigation']/ul/li[3]/a");
		  Thread.sleep(5000);
		  Assert.assertEquals(Element_Exist.existsElement("//i[@class='icon-exchange']", driver), false, "Switch user option is present for Driver user");
		  ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon icon-plus-sign-alt']");
		  Thread.sleep(5000);
		  if(Element_Exist.existsElement("//div[@class='jGrowl-message']", driver) && driver.getTitle().contains("Users"))
		  {
			  Reporter.log("<br/>");
			  Reporter.log("Driver User");
			  Reporter.log("<br/>");
			  Reporter.log("Driver is not allowed to create any user...but it should allow to create Producer/Consumer");
			  Assert.fail("Driver is not allowed to create any user...but it should allow to create Producer/Consumer");
		  }
		  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_producer']", driver), true, "Checkbox for Producer user is not present for Driver User");
		  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_consumer']", driver), true, "Checkbox for Consumer user is not present for Driver User");
		  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_health_system_admin']", driver), false, "Checkbox for HS Admin user is present for Driver User");
		  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_driver']", driver), false, "Checkbox for driver user is not present for Driver User");
		  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_admin']", driver), false, "Checkbox for Admin user is present for Driver User");
		  Assert.assertEquals(Element_Exist.existsElement("//label[@for='user_roles_rom']", driver), false, "Checkbox for ROM user is present for Driver User");
		  Reporter.log("<br/>");
		  Reporter.log("Driver User");
		  Reporter.log("<br/>");
		  Reporter.log("1.Driver Has Entity Manager Access"+"<br/>"+"2.Driver cannot edit/delete bookings"+"<br/>"+"3.Driver can edit Devices"+"<br/>"+"Driver has no Facility Assignment");
  }
  
  public String create_user(String role) throws InterruptedException
  {
	  	WebDriver driver = Initialization.getDriver();
	  	WebDriverWait wait =new WebDriverWait(driver, 100);
	  	Calendar cal=Calendar.getInstance();
		int date = cal.get(Calendar.DATE);
		int month = cal.get(Calendar.MONTH)+1;
		int year = cal.get(Calendar.YEAR);
		int time = cal.get(Calendar.MINUTE);
		int hour = cal.get(Calendar.HOUR);
		int milisec = cal.get(Calendar.MILLISECOND);
		int flag = 0;
		String userid = Integer.toString(date)+Integer.toString(month)+Integer.toString(year)+Integer.toString(hour)+Integer.toString(time)+Integer.toString(milisec)+"test@cohealo.com";
		SendingTestValueInTextBox.SendValue(driver, "id", "user_first_name", "Test");
		SendingTestValueInTextBox.SendValue(driver, "id", "user_last_name", "Test");
		SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Integer.toString(date)+Integer.toString(month)+Integer.toString(year)+Integer.toString(hour)+Integer.toString(time)+Integer.toString(milisec)+"test@cohealo.com");
		SendingTestValueInTextBox.SendValue(driver, "id", "user_position", "Test");
		SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_office_phone", "5555555555");
		SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_cell_phone", "5555555555");
		if(role.contentEquals("Producer"))
		{
			ClickActionOnObjects.ClickByJavascript(driver, "//label[@for='user_roles_producer']");
		}
		
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
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jGrowl-message']")));
		return userid;	
  }
  
  public void signout() throws InterruptedException
  {
		WebDriver driver = Initialization.getDriver();
		ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='menu inline dropdown user']/a");
		Thread.sleep(3000);
		ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon-signout']");
		Thread.sleep(10000);
  }
  public void signin() throws InterruptedException, IOException
  {
		WebDriver driver = Initialization.getDriver();
		Properties CONFIG = Initialization.getconfig();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"//src//Config/config.properties");
		CONFIG.load(ip);
		//clearing the user name and password fields of login
		GarbageValueClear.Clear(driver, "user_email", "id");
		GarbageValueClear.Clear(driver, "user_password", "id");
		//Sending User name password values to user name password text boxes	
		SendingTestValueInTextBox.SendValue(driver, "id", "user_email", CONFIG.getProperty("Adminuser"));
		SendingTestValueInTextBox.SendValue(driver, "id", "user_password", CONFIG.getProperty("Password"));
		//click on Sign In button
		ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Sign in']");
		Thread.sleep(10000);
  }
  @BeforeTest
  public void beforetest() throws IOException, InterruptedException
  {
		WebDriver driver = Initialization.getDriver();
		Properties CONFIG = Initialization.getconfig();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"//src//Config/config.properties");
		CONFIG.load(ip);
		//clearing the user name and password fields of login
		GarbageValueClear.Clear(driver, "user_email", "id");
		GarbageValueClear.Clear(driver, "user_password", "id");
		//Sending User name password values to user name password text boxes	
		SendingTestValueInTextBox.SendValue(driver, "id", "user_email", CONFIG.getProperty("Adminuser"));
		SendingTestValueInTextBox.SendValue(driver, "id", "user_password", CONFIG.getProperty("Password"));
		//click on Sign In button
		ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Sign in']");
		Thread.sleep(10000);
  }
}
