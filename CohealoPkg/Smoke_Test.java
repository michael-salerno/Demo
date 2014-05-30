package CohealoPkg;
import java.io.File;
import java.io.FileInputStream;
import InitializationPkg.*;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import UtilityPkg.*;
public class Smoke_Test {
  @Test
  public void SmokeTest() throws IOException, InterruptedException 
  {
		WebDriver driver = Initialization.getDriver();
		Properties CONFIG = Initialization.getconfig();
		Assert.assertEquals(driver.getTitle(), "My Bookings", "Page is not navigated to My Bookings");
		
		Assert.assertEquals(existsElement("//*[@id='primary-navigation']/ul/li[1]/a/span", driver), true, "My Bookings tab is not present");
		Assert.assertEquals(existsElement("//*[@id='primary-navigation']/ul/li[2]/a/span", driver), true, "Book A Device tab is not present");
		Assert.assertEquals(existsElement("//input[@id='q' and @class='span3 search-query' and @placeholder='Find a Device']", driver), true, "Find A Device textbox not present");
		Assert.assertEquals(existsElement("//input[@value='Browse']", driver), true, "Browse button is not present");
		Assert.assertEquals(existsElement("//div[@class='menu inline dropdown user']", driver), true, "Dropdown for Account Settings and Logout is not present");
		Assert.assertEquals(existsElement("//div[@class='menu inline dropdown help']", driver), true, "Dropdown for Help is not present");
		
		ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='menu inline dropdown user']");
		Thread.sleep(5000);
		
		Assert.assertEquals(existsElement("//a[@href='/users/edit']/i[@class='icon-user']", driver), true, "Account settings option is not present");
		Assert.assertEquals(existsElement("//a[@href='/users/sign_out']/i[@class='icon-signout']", driver), true, "Sign out option is not present");
		ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='/users/edit']/i[@class='icon-user']");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(), "Account Settings", "Page title is not as expected after clicking Settings");
		Assert.assertEquals(driver.getCurrentUrl(), "https://stage.cohealo.com/users/edit", "Page url is not as expected");
		Assert.assertEquals(existsElement("//div[@class='page-header']/h1", driver), true, "Page header is not present");
		Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//div[@class='page-header']/h1", "xpath"), "Account Settings", "Page header is not as expected");
		Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//*[@id='edit_user']/div[2]/div[1]/div", "xpath"), "Personal Information");
		Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//*[@id='edit_user']/div[3]/div[1]/div", "xpath"), "Contact Information");
		Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//*[@id='edit_user']/div[4]/div[1]/div", "xpath").contains("Health System"), true);
		Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//*[@id='edit_user']/div[5]/div[1]/div", "xpath"), "Change Password");
		
		Assert.assertEquals(existsElement("//*[@id='user_first_name']", driver), true, "First Name textbox is not present");
		Assert.assertEquals(existsElement("//*[@id='user_last_name']", driver), true, "Last Name textbox is not present");
		Assert.assertEquals(existsElement("//*[@id='user_position']", driver), true, "Position textbox is not present");
		
		Assert.assertEquals(existsElement("//*[@id='user_email']", driver), true, "Email textbox is not present");
		Assert.assertEquals(existsElement("//*[@id='user_contact_attributes_office_phone']", driver), true, "Office phone textbox is not present");
		Assert.assertEquals(existsElement("//*[@id='user_contact_attributes_cell_phone']", driver), true, "Cell phone textbox is not present");
		
		Assert.assertEquals(existsElement("//input[@id='user_health_system_name' and @disabled='disabled']", driver), true, "Health system textbox is not present or it is not disabled");
		Assert.assertEquals(existsElement("//*[@id='user_facility_id']", driver), true, "Facility dropdown is not present");
		
		Assert.assertEquals(existsElement("//*[@id='user_current_password']", driver), true, "Current password text box is not present");
		Assert.assertEquals(existsElement("//*[@id='edit-password']", driver), true, "Password textbox is not present");
		Assert.assertEquals(existsElement("//*[@id='user_password_confirmation']", driver), true, "Password confirmation textbox is not present");
		Assert.assertEquals(existsElement("//*[@id='user_time_zone']", driver), true, "Timezone dropdown is not present at Account Setting page");
		Assert.assertEquals(existsElement("//*[@id='gen-btn']", driver), true, "Generate button is not present");
		Assert.assertEquals(existsElement("//input[@value='Update']", driver), true, "Update button is not present");
		Assert.assertEquals(existsElement("//div[@id='logo']", driver), true, "Cohealo logo is not present");
		driver.findElement(By.xpath("//div[@id='logo']")).click();
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(), "My Bookings", "Page is not navigated to My Bookings page after clicking on logo");
		Assert.assertEquals(existsElement("//div[@class='fc-event-inner']", driver), true, "Bookings are not displaying on calendar");
		Assert.assertEquals(existsElement("//td[@class='fc-header-left']/span[@class='fc-button fc-button-prev fc-state-default fc-corner-left']", driver), true, "Previous button to go previous months is not present");
		Assert.assertEquals(existsElement("//*[@id='content-wrapper']/div/div[1]/div[2]/div/table/tbody/tr/td[1]/span[2]", driver), true, "Today button to go current month is not present");
		Assert.assertEquals(existsElement("//*[@id='content-wrapper']/div/div[1]/div[2]/div/table/tbody/tr/td[1]/span[3]", driver), true, "Forword button to go next month is not present");
		Assert.assertEquals(existsElement("//span[@class='fc-header-title']", driver), true, "Month header to display current month is not present");
		
		Assert.assertEquals(existsElement("//*[@id='content-wrapper']/div/div[1]/div[2]/div/table/tbody/tr/td[3]/span[1]", driver), true, "Day view button is not present");
		Assert.assertEquals(existsElement("//*[@id='content-wrapper']/div/div[1]/div[2]/div/table/tbody/tr/td[3]/span[2]", driver), true, "Week view button is not present");
		Assert.assertEquals(existsElement("//*[@id='content-wrapper']/div/div[1]/div[2]/div/table/tbody/tr/td[3]/span[3]", driver), true, "Month view button is not present");
		
		ClickActionOnObjects.ClickByJavascript(driver, "//*[@id='primary-navigation']/ul/li[2]/a/span");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(), "Equipment", "Page title is not as expected after clicking on Book A Device");
		ClickActionOnObjects.ClickByJavascript(driver, "//tr[@class='clickableRow'][1]/td[2]");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(), "Equipment Details", "Page title is not as expected after clicking on Equipment");
		Assert.assertEquals(existsElement("//div[@class='active item photo-frame']", driver), true, "Equipment photo frame is not present");
		Assert.assertEquals(existsElement("//li[@class='active']/a", driver), true, "Additional information tab is not present");
		Assert.assertEquals(existsElement("//*[@id='content-wrapper']/div/div/div[1]/div/div[2]/div/ul/li[2]/a", driver), true, "Available Accessories tab is not present");
		Assert.assertEquals(existsElement("//*[@id='content-wrapper']/div/div/div[1]/div/div[2]/div/ul/li[3]/a", driver), true, "Disposables tab is not present");
		Assert.assertEquals(existsElement("//*[@id='events_booking_start']", driver), true, "Event Booking Start textbox is not present");
		Assert.assertEquals(existsElement("//input[@value='Book Asset']", driver), true, "Book Aseet button is not present");
		Assert.assertEquals(existsElement("//*[@id='repeat']", driver), true, "Repeat check box is not present");
		ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='menu inline dropdown user']/a");
		Thread.sleep(3000);
		ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon-signout']");
		Thread.sleep(10000);
		Assert.assertEquals(driver.getTitle(), "User Login", "Page is not navigated to user login after signout");
		Assert.assertEquals(driver.getCurrentUrl(), "https://stage.cohealo.com/users/sign_in", "Page is not navigated to expected URL");
		SendingTestValueInTextBox.SendValue(driver, "id", "user_email", CONFIG.getProperty("ROMUser"));
		SendingTestValueInTextBox.SendValue(driver, "id", "user_password", CONFIG.getProperty("Password"));
		ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Sign in']");
		Thread.sleep(10000);
		Assert.assertEquals(existsElement("//*[@id='primary-navigation']/ul/li[3]/a/span", driver), true, "Entity manager tab is not present for ROM user");
		ClickActionOnObjects.ClickByJavascript(driver, "//*[@id='primary-navigation']/ul/li[3]/a/span");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getCurrentUrl(), "https://stage.cohealo.com/admin/users", "Page navigated to url is not as expected");
		Assert.assertEquals(driver.getTitle(), "Users", "Page title is not as expected");
		Assert.assertEquals(existsElement("//div[@class='entity-navigation']", driver), true, "Entity navigation menus not present");
		WebElement e = driver.findElement(By.xpath("//div[@class='entity-navigation']/ul"));
		Assert.assertEquals(e.findElements(By.tagName("li")).size(), 6, "Number of menus and submenus of Entity Navigation is not as expected");
		Assert.assertEquals(existsElement("//div[@class='box-header blue-background']", driver), true, "Page header is not present");
		Assert.assertEquals(existsElement("//input[@type='text' and @aria-controls='DataTables_Table_0']", driver), true, "Search text box is not present");
		Assert.assertEquals(existsElement("//button[@type='submit']", driver), true, "Create new button is not present");
		Assert.assertEquals(existsElement("//*[@id='DataTables_Table_0_length']/label/select", driver), true, "Dropdown for page switch is not present");
		Assert.assertEquals(existsElement("//i[@class='icon icon-group']", driver), true, "User group logo is not displaying");
		Assert.assertEquals(existsElement("//i[@class='icon icon-h-sign']", driver), true, "Health system logo is not displaying");
		Assert.assertEquals(existsElement("//i[@class='icon icon-hospital']", driver), true, "Facilities logo is not displaying");
		Assert.assertEquals(existsElement("//li[@class='dropdown-submenu']/a/i[@class='icon icon-medkit']", driver), true, "Devices logo is not present");
		Assert.assertEquals(existsElement("//i[@class='icon-exchange']", driver), true, "Swith User logo is not present");
		Assert.assertEquals(existsElement("//i[@class='icon-edit']", driver), true, "Edit User logo is not present");
		Assert.assertEquals(existsElement("//i[@class='icon-envelope']", driver), true, "Resend Email logo is not present");
		Assert.assertEquals(existsElement("//i[@class='icon-remove']", driver), true, "Remove User logo is not present");
		driver.get("https://stage.cohealo.com/admin/users/212/edit");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(), "Edit User", "Page title is not as expected");
		Assert.assertEquals(existsElement("//legend", driver), true, "Header for Edit user page is not present");
		Assert.assertEquals(existsElement("//*[@id='user_first_name']", driver), true, "First Name text box is not present");
		Assert.assertEquals(existsElement("//*[@id='user_last_name']", driver), true, "Last Name text box is not present");
		Assert.assertEquals(existsElement("//*[@id='user_email']", driver), true, "Email address text box is not present");
		Assert.assertEquals(existsElement("//*[@id='user_position']", driver), true, "Position text box is not present");
		Assert.assertEquals(existsElement("//*[@id='user_contact_attributes_office_phone']", driver), true, "Office phone text box is not present");
		Assert.assertEquals(existsElement("//*[@id='user_contact_attributes_cell_phone']", driver), true, "Cell phone text box is not present");
		Assert.assertEquals(existsElement("//*[@id='user_roles_producer']", driver), true, "Producer checkbox is not present");
		Assert.assertEquals(existsElement("//*[@id='user_roles_consumer']", driver), true, "Consumer checkbox is not present");
		Assert.assertEquals(existsElement("//*[@id='user_roles_health_system_admin']", driver), true, "HS Admin checkbox is not present");
		Assert.assertEquals(existsElement("//*[@id='user_roles_driver']", driver), true, "Driver checkbox is not present");
		Assert.assertEquals(existsElement("//*[@id='s2id_health_system_id']/ul", driver), true, "Health System dropdown is not present");
		Assert.assertEquals(existsElement("//*[@id='s2id_user_facility_id']/a", driver), true, "Facility dropdown is not present");
		Assert.assertEquals(existsElement("//*[@id='user_npi']", driver), true, "NPI text box is not present");
		Assert.assertEquals(existsElement("//*[@id='s2id_user_time_zone']/a", driver), true, "Time Zone dropdown is not present");
		Assert.assertEquals(existsElement("//input[@value='Update User']", driver), true, "Update User button is not present");
		Assert.assertEquals(existsElement("//div[@class='controls']/a[@href='/admin/users']", driver), true, "Cancel button is not present at Edit User page");
		ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='controls']/a[@href='/admin/users']");
		Thread.sleep(5000);
		ClickActionOnObjects.ClickByJavascript(driver, "//button[@type='submit']");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getCurrentUrl(), "https://stage.cohealo.com/admin/users/new", "Page navigated to url is not as expected");
		Assert.assertEquals(driver.getTitle(), "New User", "Page title is not as expected");
		Assert.assertEquals(existsElement("//legend", driver), true, "Header for create user page is not present");
		Assert.assertEquals(existsElement("//*[@id='user_first_name']", driver), true, "First Name text box is not present");
		Assert.assertEquals(existsElement("//*[@id='user_last_name']", driver), true, "Last Name text box is not present");
		Assert.assertEquals(existsElement("//*[@id='user_email']", driver), true, "Email address text box is not present");
		Assert.assertEquals(existsElement("//*[@id='user_position']", driver), true, "Position text box is not present");
		Assert.assertEquals(existsElement("//*[@id='user_contact_attributes_office_phone']", driver), true, "Office phone text box is not present");
		Assert.assertEquals(existsElement("//*[@id='user_contact_attributes_cell_phone']", driver), true, "Cell phone text box is not present");
		Assert.assertEquals(existsElement("//*[@id='user_roles_producer']", driver), true, "Producer checkbox is not present");
		Assert.assertEquals(existsElement("//*[@id='user_roles_consumer']", driver), true, "Consumer checkbox is not present");
		Assert.assertEquals(existsElement("//*[@id='user_roles_health_system_admin']", driver), true, "HS Admin checkbox is not present");
		Assert.assertEquals(existsElement("//*[@id='user_roles_driver']", driver), true, "Driver checkbox is not present");
		Assert.assertEquals(existsElement("//*[@id='s2id_health_system_id']/ul", driver), true, "Health System dropdown is not present");
		Assert.assertEquals(existsElement("//*[@id='s2id_user_facility_id']/a", driver), true, "Facility dropdown is not present");
		Assert.assertEquals(existsElement("//*[@id='user_npi']", driver), true, "NPI text box is not present");
		Assert.assertEquals(existsElement("//*[@id='s2id_user_time_zone']/a", driver), true, "Time Zone dropdown is not present");
		Assert.assertEquals(existsElement("//input[@value='Create User']", driver), true, "Create user button is not present");
		Assert.assertEquals(existsElement("//div[@class='controls']/a[@href='/admin/users']", driver), true, "Cancel button is not present at New User page");
		ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='controls']/a[@href='/admin/users']");
		Thread.sleep(5000);
		ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='/admin/health_systems']");
		Thread.sleep(5000);
		Assert.assertEquals(existsElement("//div[@class='box-header blue-background']", driver), true, "Header for Health system is not present");
		Assert.assertEquals(existsElement("//div[@class='dataTables_filter']/label/input", driver), true, "Search text box for HS is not present");
		Assert.assertEquals(existsElement("//div[@class='dataTables_length']/label/select", driver), true, "Dropdown for page swithing is not present");
		Assert.assertEquals(existsElement("//i[@class='icon-eye-open']", driver), true, "Show icon for HS is not present");
		Assert.assertEquals(existsElement("//i[@class='icon-edit-sign']", driver), true, "Edit icon for HS is not present");
		Assert.assertEquals(existsElement("//i[@class='icon-remove']", driver), true, "Remove icon for HS is not present");
		Assert.assertEquals(existsElement("//input[@value='New Health System']", driver), true, "New Health System is not present");
		ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='New Health System']");
		Thread.sleep(5000);
		Assert.assertEquals(existsElement("//legend", driver), true, "Header for Health Sytem is not present");
		Assert.assertEquals(existsElement("//*[@id='health_system_name']", driver), true, "Health System Name text box is not present");
		Assert.assertEquals(existsElement("//*[@id='health_system_ca_contact_attributes_email']", driver), true, "Support Email text box is not present");
		Assert.assertEquals(existsElement("//*[@id='health_system_ca_contact_attributes_office_phone']", driver), true, "Support Phone text box is not present");
		Assert.assertEquals(existsElement("//input[@value='Create Health System']", driver), true, "Create HS button is not present");
		Assert.assertEquals(existsElement("//div[@class='form-actions']/a[@href='/admin/health_systems']", driver), true, "Cancel button is not present at New Health System page");
		ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='form-actions']/a[@href='/admin/health_systems']");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(), "Health Systems", "Page title is not as expected");
		ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon-eye-open']");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(), "Health System Details", "Page title is not as expected");
		Assert.assertEquals(existsElement("//*[@id='content-wrapper']/div[2]/div/div/fieldset/div[2]/div/div/a[1]", driver), true, "Edit button for HS is not present");
		Assert.assertEquals(existsElement("//*[@id='content-wrapper']/div[2]/div/div/fieldset/div[2]/div/div/a[2]", driver), true, "Cancel button for HS is not present at HS Details page");
		ClickActionOnObjects.ClickByJavascript(driver, "//*[@id='content-wrapper']/div[2]/div/div/fieldset/div[2]/div/div/a[1]");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(), "Edit Health System", "Page title is not as expected");
		Assert.assertEquals(existsElement("//legend", driver), true, "Header for Health Sytem is not present");
		Assert.assertEquals(existsElement("//*[@id='health_system_name']", driver), true, "Health System Name text box is not present");
		Assert.assertEquals(existsElement("//*[@id='health_system_ca_contact_attributes_email']", driver), true, "Support Email text box is not present");
		Assert.assertEquals(existsElement("//*[@id='health_system_ca_contact_attributes_office_phone']", driver), true, "Support Phone text box is not present");
		Assert.assertEquals(existsElement("//input[@value='Update Health System']", driver), true, "Create HS button is not present");
		Assert.assertEquals(existsElement("//div[@class='form-actions']/a[@href='/admin/health_systems']", driver), true, "Cancel button for HS is not present at Edit Health System");
		ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='form-actions']/a[@href='/admin/health_systems']");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(), "Health Systems", "Page title is not as expected");
		ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon-edit-sign']");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(), "Edit Health System", "Page title is not as expected");
		Assert.assertEquals(existsElement("//legend", driver), true, "Header for Health Sytem is not present");
		Assert.assertEquals(existsElement("//*[@id='health_system_name']", driver), true, "Health System Name text box is not present");
		Assert.assertEquals(existsElement("//*[@id='health_system_ca_contact_attributes_email']", driver), true, "Support Email text box is not present");
		Assert.assertEquals(existsElement("//*[@id='health_system_ca_contact_attributes_office_phone']", driver), true, "Support Phone text box is not present");
		Assert.assertEquals(existsElement("//input[@value='Update Health System']", driver), true, "Create HS button is not present");
		Assert.assertEquals(existsElement("//div[@class='form-actions']/a[@href='/admin/health_systems']", driver), true, "Cancel button for HS is not present at Edit Health System page");
		ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='form-actions']/a[@href='/admin/health_systems']");
		Thread.sleep(5000);
		ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='/admin/facilities']");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(), "Facilities", "Page title is not as expected");
		Assert.assertEquals(existsElement("//div[@class='box-header blue-background']", driver), true, "Header for Facilities is not present");
		Assert.assertEquals(existsElement("//div[@class='dataTables_filter']/label/input", driver), true, "Search text box for Facilities is not present");
		Assert.assertEquals(existsElement("//div[@class='dataTables_length']/label/select", driver), true, "Dropdown for page swithing is not present");
		Assert.assertEquals(existsElement("//i[@class='icon-eye-open']", driver), true, "Show icon for Facilities is not present");
		Assert.assertEquals(existsElement("//i[@class='icon-edit-sign']", driver), true, "Edit icon for Facilities is not present");
		Assert.assertEquals(existsElement("//i[@class='icon-remove']", driver), true, "Remove icon for Facilities is not present");
		Assert.assertEquals(existsElement("//input[@value='New Facility']", driver), true, "New Facility button is not present");
		ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='New Facility']");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(), "Add Facility", "Page title is not as expected");
		Assert.assertEquals(existsElement("//*[@id='facility_name']", driver), true, "Facility name text box is not present");
		Assert.assertEquals(existsElement("//*[@id='facility_address_attributes_address1']", driver), true, "Address1 text box is not present");
		Assert.assertEquals(existsElement("//*[@id='facility_address_attributes_address2']", driver), true, "Address2 text box is not present");
		Assert.assertEquals(existsElement("//*[@id='facility_address_attributes_city']", driver), true, "City text box is not present");
		Assert.assertEquals(existsElement("//*[@id='facility_address_attributes_state']", driver), true, "State text box is not present");
		Assert.assertEquals(existsElement("//*[@id='facility_address_attributes_zip']", driver), true, "Zip text box is not present");
		Assert.assertEquals(existsElement("//*[@id='health_system_id']", driver), true, "Health system dropdown is not present");
		Assert.assertEquals(existsElement("//*[@id='facility_delivery_instructions']", driver), true, "Delivery instructions text box is not present");
		Assert.assertEquals(existsElement("//input[@value='Create Facility']", driver), true, "Create Facility button is not present");
		Assert.assertEquals(existsElement("//div[@class='form-actions']/a[@href='/admin/facilities']", driver), true, "Cancel button is not present");
		ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='form-actions']/a[@href='/admin/facilities']");
		Thread.sleep(5000);
		ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon-eye-open']");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(), "Facility Details", "Page title is not as expected");
		Assert.assertEquals(existsElement("//*[@id='content-wrapper']/div[2]/div/div/fieldset/div[2]/div/div/a[1]", driver), true, "Edit button for facilities is not present");
		Assert.assertEquals(existsElement("//*[@id='content-wrapper']/div[2]/div/div/fieldset/div[2]/div/div/a[2]", driver), true, "Cancel button for facilities is not present at Facilities Details page");
		ClickActionOnObjects.ClickByJavascript(driver, "//*[@id='content-wrapper']/div[2]/div/div/fieldset/div[2]/div/div/a[1]");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(), "Edit Facility", "Page title is not as expected");
		Assert.assertEquals(existsElement("//*[@id='facility_name']", driver), true, "Facility name text box is not present");
		Assert.assertEquals(existsElement("//*[@id='facility_address_attributes_address1']", driver), true, "Address1 text box is not present");
		Assert.assertEquals(existsElement("//*[@id='facility_address_attributes_address2']", driver), true, "Address2 text box is not present");
		Assert.assertEquals(existsElement("//*[@id='facility_address_attributes_city']", driver), true, "City text box is not present");
		Assert.assertEquals(existsElement("//*[@id='facility_address_attributes_state']", driver), true, "State text box is not present");
		Assert.assertEquals(existsElement("//*[@id='facility_address_attributes_zip']", driver), true, "Zip text box is not present");
		Assert.assertEquals(existsElement("//*[@id='health_system_id']", driver), true, "Health system dropdown is not present");
		Assert.assertEquals(existsElement("//*[@id='facility_delivery_instructions']", driver), true, "Delivery instructions text box is not present");
		Assert.assertEquals(existsElement("//input[@value='Update Facility']", driver), true, "Create Facility button is not present");
		Assert.assertEquals(existsElement("//div[@class='form-actions']/a[@href='/admin/facilities']", driver), true, "Cancel button is not present");
		ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='form-actions']/a[@href='/admin/facilities']");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(), "Facilities", "Page title is not as expected");
		ClickActionOnObjects.ClickByJavascript(driver, "//li[@class='dropdown-submenu']");
		Thread.sleep(2000);
		ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='/equipment/new']/span");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(), "Add Equipment", "Page title is not as expected");
		Assert.assertEquals(existsElement("//*[@id='list-device-basic']/div[1]/h1", driver), true, "Header is not present for Add Equipment is not present");
		Assert.assertEquals(existsElement("//*[@id='equipment_manufacturer']", driver), true, "Manufacturer text box is not present");
		Assert.assertEquals(existsElement("//*[@id='equipment_model']", driver), true, "Model text box is not present");
		Assert.assertEquals(existsElement("//*[@id='equipment_description']", driver), true, "Equipment Description text box is not present");
		Assert.assertEquals(existsElement("//*[@id='health_system_id']", driver), true, "Health System dropdown is not present");
		Assert.assertEquals(existsElement("//*[@id='equipment_owned_by_facility_id']", driver), true, "Owned by facility dropdown is not present");
		Assert.assertEquals(existsElement("//div[@class='actions']/button", driver), true, "Continue button is not present");
		Reporter.log("Titles of all pages are appropriate , navigation is proper and all form fields are present");
  }
  
	@BeforeTest
	public void beforetest() throws InterruptedException, IOException
	{
		WebDriver driver = Initialization.getDriver();
		Properties CONFIG = Initialization.getconfig();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"//src//Config/config.properties");
		CONFIG.load(ip);
		//clearing the user name and password fields of login
		GarbageValueClear.Clear(driver, "user_email", "id");
		GarbageValueClear.Clear(driver, "user_password", "id");
		//Sending User name password values to user name password text boxes	
		SendingTestValueInTextBox.SendValue(driver, "id", "user_email", CONFIG.getProperty("ConsumerUser"));
		SendingTestValueInTextBox.SendValue(driver, "id", "user_password", CONFIG.getProperty("Password"));
		//click on Sign In button
		ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Sign in']");
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Sign in']"))).click();

		Thread.sleep(10000);
	}
	

	@AfterTest
	public void aftertest() throws InterruptedException
	{
		WebDriver driver = Initialization.getDriver();
		ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='menu inline dropdown user']/a");
		Thread.sleep(3000);
		ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon-signout']");
		Thread.sleep(10000);
	}
	

		  public boolean existsElement(String xpath1 , WebDriver driver) throws InterruptedException {
			    try 
			    {
			    	driver.findElement(By.xpath(xpath1));
			    } catch (Exception e) {
			        return false;
			    }
			    WebElement e = driver.findElement(By.xpath(xpath1));
			    ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'",e);
			    Thread.sleep(2000);
			    ((JavascriptExecutor)driver).executeScript("arguments[0].style.border=''", e);
			    return true;
			}
		  
}
