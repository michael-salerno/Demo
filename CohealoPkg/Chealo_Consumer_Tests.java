package CohealoPkg;
import InitializationPkg.*;
import java.io.FileInputStream;
import UtilityPkg.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.Properties;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Chealo_Consumer_Tests {
	//Function to find correct count displayed at Keyword Search
	@Test
	public void Keyword_Search_Help_Bubble_Test() throws InterruptedException
	{
		WebDriver driver = Initialization.getDriver();
		Properties c = Initialization.getor();
		ClickActionOnObjects.ClickByJavascript(driver, c.getProperty("BookADevice"));
		Thread.sleep(5000);
		String d = ReadingTextFromObjects.GetText(driver, c.getProperty("SearchTitle"), "xpath").trim();
		String Device_count[] = d.split(" ");
		Assert.assertEquals(Element_Exist.existsElement(c.getProperty("Keyword_Search_Help_Bubble"), driver), true, "Keyword Search Help Bubble is not present");
		ClickActionOnObjects.ClickByJavascript(driver, c.getProperty("Keyword_Search_Help_Bubble"));
		Thread.sleep(5000);
		Assert.assertEquals(Element_Exist.existsElement(c.getProperty("Keyword_popup"), driver), true, "Keyword Search Help Bubble popup is not displayed");
		String d1 = ReadingTextFromObjects.GetText(driver, c.getProperty("Keyword_popup")+"/div[@class='popover-content']", "xpath").trim();
		String Keyword_Device_count[] = d1.split(" ");
		System.out.println(Keyword_Device_count[2]);
		System.out.println(Device_count[0]);
		Assert.assertEquals(Keyword_Device_count[2], Device_count[0], "Device count at Equipment page and at Keyword Search Help Bubble count is not matching");
		Reporter.log("Device count displayed correctly at Keyword Search Help Bubble");
	}
	
	//Function to check whether user can Edit/Delete todays bookings
	@Test
	public void Edit_or_delete_todays_bookings() throws InterruptedException
	{
		WebDriver driver = Initialization.getDriver();
		Properties c = Initialization.getor();
		ClickActionOnObjects.ClickByJavascript(driver, c.getProperty("MyBookings"));
		Thread.sleep(5000);
		String Current_date = GetDate.getDate();
		Point h =driver.findElement(By.xpath("//td[@data-date="+"'"+Current_date+"'"+"]"+"")).getLocation();
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+(h.getY()-50)+");"); 
		Thread.sleep(10000);
		if(Element_Exist.existsElement("//span[@data-date="+"'"+Current_date+"'"+"]"+"", driver))
		{
		driver.findElement(By.xpath("//span[@data-date="+"'"+Current_date+"'"+"]"+"")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='booking-popup modal hide fade in' and @aria-hidden='false']/div/div[@class='control-group text-center']/div/div/a[1]")).click();
		Thread.sleep(5000);
		Assert.assertEquals(Element_Exist.existsElement("//div[@class='edit-popup popup' and @style='display: block;']", driver), true	, "Edit booking popup is not generated after clicking on edit");
		Assert.assertEquals(Element_Exist.existsElement("//a[@class='btn btn-warning popup-cancel']", driver), true	, "Ok button is not present at Edit booking popup");
		WebElement e = driver.findElement(By.xpath("//div[@class='edit-popup popup']/div[@class='box']/div[@class='box-content']/div/a"));
		e.click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='booking-popup modal hide fade in' and @aria-hidden='false']/div/div[@class='control-group text-center']/div/div/a[2]")).click();
		Thread.sleep(5000);
		Assert.assertEquals(Element_Exist.existsElement("//div[@class='delete-popup popup' and @style='display: block;']", driver),true,"Edit booking popup is not generated after clicking on edit");
		Assert.assertEquals(Element_Exist.existsElement("//a[@class='btn btn-warning popup-cancel']", driver),true,"Ok button is not present at Edit booking popup");
		WebElement e1 = driver.findElement(By.xpath("//div[@class='delete-popup popup']/div[@class='box']/div[@class='box-content']/div/a"));
		e1.click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='actions']/a[3]")).click();
		Reporter.log("Edit or Delete today's bookings is working proerply");
		}
		else
		{
			Reporter.log("No Booking exist for today");
		}
	}
	
	//Function to check bookings are displaying on calendar
	@Test
	public void check_display_of_bookings_on_calendar() throws InterruptedException
	{
		WebDriver driver = Initialization.getDriver();
		Properties c1 = Initialization.getor();
		int f=0;
		ClickActionOnObjects.ClickByJavascript(driver, c1.getProperty("MyBookings"));
		Thread.sleep(5000);
		boolean flag = Element_Exist.existsElement("//div[@class='fc-event-inner']", driver);
		if(flag==false)
		{
			System.out.println("entered");
			String Month_and_year[] = ReadingTextFromObjects.GetText(driver, "//span[@class='fc-header-title']", "xpath").split(" ");
			String Current_month = Month_and_year[0];
			WebElement e = driver.findElement(By.xpath("//div[starts-with(@class , 'booking clearfix event-')]/div[@class='event']"));
			int c = e.findElements(By.xpath("//p[@class='date']")).size();
			
		
			for(int i=1;i<c;i++)
			{
				if(driver.findElement(By.xpath("//div[starts-with(@class , 'booking clearfix event-')]["+i+"]/div[@class='event']/p[1]")).getText().trim().contains(Current_month))
				{
					
					f=1;
					i=c;
					
				}
			}
			if(f==1)
			{
				Reporter.log("No Bookings are displaying");
				Assert.fail("No Bookings are displaying");
			}
		}
		else
		{
			Reporter.log("Bookings are displaying properly");
		}
		
	}
	
	//Function to display correct Month/year displayed
	@Test
  	public void Month_year_display_test() throws InterruptedException
  	{
  		WebDriver driver = Initialization.getDriver();
  		Properties c = Initialization.getor();
  		String[] months = {"January" , "February" , "March" , "April" , "May" , "June" , "July" , "August" , "September" , "October" , "November" , "December"};
  		//Get current year and month
  		Calendar cal=Calendar.getInstance();
  		int year=cal.get(Calendar.YEAR);
  		int Month = cal.get(Calendar.MONTH);
  		//Click on My bookings tab
  		ClickActionOnObjects.ClickByJavascript(driver, c.getProperty("MyBookings"));
  		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='primary-navigation']/ul/li[2]/a"))).click();
  		Thread.sleep(10000);
  		//Getting Displayed Month and Year in calendar
  		String s = ReadingTextFromObjects.GetText(driver, "//span[@class='fc-header-title']/h2", "xpath");
  		String[] Display_month = s.split(" ");
  		//checking actual running month with displayed month 
  		if(months[Month].contentEquals(Display_month[0]))
  		{
  			int Display_year = Integer.parseInt(Display_month[1]);
  			if(Display_year==year)
  			{
  				Reporter.log("Month and year displayed correctly");
  				
  			}
  			else
  			{
  				Reporter.log("Actual year displayed:-"+Display_year+" "+"Expected year:-"+year);
  				Assert.fail("Actual year displayed:-"+Display_year+" "+"Expected year:-"+year);
  			}
  		}
  		
  		else
  		{
				Reporter.log("Actual Month displayed:-"+Display_month[0]+" "+"Expected Month:-"+months[Month]);
				Assert.fail("Actual Month displayed:-"+Display_month[0]+" "+"Expected Month:-"+months[Month]);
  		}
  		
  		
  	}
	
	//Function to check search device functionality
	@Test
	public void search_Device_Test() throws InterruptedException
	{
		WebDriver driver = Initialization.getDriver();
		Properties c1 = Initialization.getor();
		ClickActionOnObjects.ClickByJavascript(driver, c1.getProperty("BookADevice"));
		Thread.sleep(5000);
		String Default_Device_found = ReadingTextFromObjects.GetText(driver, c1.getProperty("SearchTitle"), "xpath");
		//click on browse button with blank input
		ClickActionOnObjects.ClickByJavascript(driver, c1.getProperty("Browse"));
		Thread.sleep(5000);
		//checking result with blank input
	    Assert.assertEquals(ReadingTextFromObjects.GetText(driver, c1.getProperty("SearchTitle"), "xpath"), Default_Device_found, "Search with blank is not working properly");
	    Reporter.log("Search with blank input working properly");
	    //get manufacturer name from list
	    String get_manufacturer_name = ReadingTextFromObjects.GetText(driver, "//li[@class='manufacturers'][7]/label", "xpath");
	    String c[] = get_manufacturer_name.split(" ");
	    Assert.assertEquals(Element_Exist.existsElement(c1.getProperty("Searchtextbox"), driver), true, "Search Textbox is not present");
	    //Enter the first word of manufacturer in search text box
	    SendingTestValueInTextBox.SendValue(driver, "xpath", c1.getProperty("Searchtextbox"), c[0]);
	    //Checking the suggestion generated.
	    Thread.sleep(5000);
	    	
	    	
	    	WebElement suggestion_div = driver.findElement(By.xpath("//div[@class='typeahead-container' and @style='display: block;']"));
	    	int suggestion_count = suggestion_div.findElements(By.xpath("//div[@class='equipment-typeahead']")).size();
	    	
	    	ClickActionOnObjects.ClickByJavascript(driver, c1.getProperty("Browse"));
	    	Thread.sleep(8000);
	    	String device_found[] = ReadingTextFromObjects.GetText(driver, c1.getProperty("SearchTitle"), "xpath").split(" ");
	    	int Actual_count = Integer.parseInt(device_found[0]);
	    	
	    	Assert.assertEquals(suggestion_count, Actual_count, "Number of devices displayed is not as per the suggested device count");
	        Reporter.log("<br/>");
	    	Reporter.log("Number of devices displayed as per the suggested device count");
	        for(int i=1;i<Actual_count;i++)
	        {
	        	Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//tr[@class='clickableRow']["+i+"]/td[2]", "xpath").contains(c[0]),true,"Search result is not as per requested");
	        }
	        
	        Reporter.log("<br/>");
	        Reporter.log("Search functionality working properly with one of the word of manufacturer name");
	        GarbageValueClear.Clear(driver, c1.getProperty("Searchtextbox"), "xpath");
	        SendingTestValueInTextBox.SendValue(driver, "xpath", c1.getProperty("Searchtextbox"), get_manufacturer_name);
	    	Thread.sleep(8000);
	    	WebElement suggestion_div1 = driver.findElement(By.xpath("//div[@class='typeahead-container' and @style='display: block;']"));
	    	int suggestion_count1 = suggestion_div1.findElements(By.xpath("//div[@class='equipment-typeahead']")).size();
	    	ClickActionOnObjects.ClickByJavascript(driver, c1.getProperty("Browse"));
	    	Thread.sleep(8000);
	    	String device_found1[] = ReadingTextFromObjects.GetText(driver, c1.getProperty("SearchTitle"), "xpath").split(" ");
	    	int Actual_count1 = Integer.parseInt(device_found1[0]);
	    	Assert.assertEquals(suggestion_count1, Actual_count1, "Number of devices displayed is not as per the suggested device count");
	    	for(int i=1;i<Actual_count1;i++)
	        {
	        	Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//tr[@class='clickableRow']["+i+"]/td[2]", "xpath").contains(c[0]),true,"Search result is not as per requested");
	        }
	    	Reporter.log("Search functionality working properly with manufacturer name");
	    	GarbageValueClear.Clear(driver, c1.getProperty("Searchtextbox"), "xpath");
	    	SendingTestValueInTextBox.SendValue(driver, "xpath", c1.getProperty("Searchtextbox"), "Hello");
	    	ClickActionOnObjects.ClickByJavascript(driver, c1.getProperty("Browse"));
	    	Thread.sleep(5000);
	    	Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//div[@class='no-results']/h3", "xpath").trim().contains("No results found"),true, "Meesage is displayed is not as expected");
	    	Reporter.log("<br/>");
	    	Reporter.log("Search functionality working properly with manufacturer name that is not exist");
	}
	
	//Function to check fitered result after pressing back
	@Test
	public void Check_Filtered_result_after_pressing_back() throws InterruptedException
	{
		  WebDriver driver = Initialization.getDriver();
		  Properties c = Initialization.getor();
		  ClickActionOnObjects.ClickByJavascript(driver, c.getProperty("BookADevice"));
		  Thread.sleep(5000);
		  ClickActionOnObjects.ClickByJavascript(driver, "//li[@class='manufacturers'][1]/input[@name='manufacturers[]']");
		  ClickActionOnObjects.ClickByJavascript(driver, "//li[@class='manufacturers'][2]/input[@name='manufacturers[]']");
		  ClickActionOnObjects.ClickByJavascript(driver, "//li[@class='manufacturers'][3]/input[@name='manufacturers[]']");
		  Thread.sleep(3000);
		  String dc[] = ReadingTextFromObjects.GetText(driver, c.getProperty("SearchTitle"), "xpath").split(" ");
		  ClickActionOnObjects.ClickByJavascript(driver, "//tr[@class='clickableRow'][1]/td[2]");
		  Thread.sleep(5000);
		  driver.navigate().back();
		  String Adc[]=ReadingTextFromObjects.GetText(driver, c.getProperty("SearchTitle"), "xpath").split(" ");
		  Assert.assertEquals(Adc[0], dc[0] , "Filtered results are not displayed");
		  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='manufacturers'][1]/input[@name='manufacturers[]']")).isSelected(), true , "Checkbox of the manufacturer gor unchecked");
		  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='manufacturers'][2]/input[@name='manufacturers[]']")).isSelected(), true , "Checkbox of the manufacturer gor unchecked");
		  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='manufacturers'][3]/input[@name='manufacturers[]']")).isSelected(), true , "Checkbox of the manufacturer gor unchecked");
	}
	

	//Checking Filter devices by Manufacture functionality
	  @Test
	  public void Filter_By_Manufacture_Test() throws InterruptedException
	  {
		  WebDriver driver = Initialization.getDriver();
		  Properties c = Initialization.getor();
		  ClickActionOnObjects.ClickByJavascript(driver, c.getProperty("BookADevice"));
		  Thread.sleep(5000);
		  //click on first  manufacturer check box from list
		  ClickActionOnObjects.ClickByJavascript(driver, "//li[@class='manufacturers'][1]/input[@name='manufacturers[]']");
		 
		  //Getting name of Manufacture that is selected
		  String Manufactures_Name = ReadingTextFromObjects.GetText(driver, "//li[@class='manufacturers'][1]/label", "xpath");
		  Thread.sleep(10000);
		  //After selecting manufacture checking tag is generated for that manufacture
		  boolean flag = Element_Exist.existsElement("//div[@class='tag manufacturers'][1]/span", driver);
		  if(flag==true)
		  {
			  //checking Manufacture name with Tag generated with manufacturer name
			  if(Manufactures_Name.contentEquals(ReadingTextFromObjects.GetText(driver, "//div[@class='tag manufacturers'][1]/span", "xpath").trim()))
			  {

				  String Actual_Device_count = ReadingTextFromObjects.GetText(driver, "//li[@class='manufacturers'][1]/span", "xpath").trim();
				  int ADC1 = Integer.parseInt(Actual_Device_count);
				  
				  String[]D_count = ReadingTextFromObjects.GetText(driver, "//*[@id='equipment-container']/h1", "xpath").split(" ");
				  if(D_count[0].contains(","))
				  {
				  String[] D1_Count = D_count[0].split("\\,");
				  String Display_Count = D1_Count[0].concat(D1_Count[1]);
				  
				  
				  //checking Actual device count with displayed device count after selecting manufacturer
				  if(Display_Count.contentEquals(Actual_Device_count))
				  {
					  ClickActionOnObjects.ClickByJavascript(driver, "//li[@class='manufacturers'][2]/input[@name='manufacturers[]']");
					  
					  Thread.sleep(10000);
					  String Manufactures_Name1 = ReadingTextFromObjects.GetText(driver, "//li[@class='manufacturers'][2]/label", "xpath");
					  Thread.sleep(10000);
					  boolean flag1 = Element_Exist.existsElement("//span[@class='manufacturers'][1]/div/button", driver);
					  if(flag1==true)
					  {
						  if(Manufactures_Name1.contentEquals(ReadingTextFromObjects.GetText(driver, "//li[@class='manufacturers'][2]/label", "xpath").trim()))
						  {

							  String Actual_Device_count1 = ReadingTextFromObjects.GetText(driver, "//li[@class='manufacturers'][2]/span", "xpath").trim();
							  int ADC2 = Integer.parseInt(Actual_Device_count1);
							  int Total_Count = ADC2 + ADC1;
							  
							  
							  String[]D_count1 = ReadingTextFromObjects.GetText(driver, "//*[@id='equipment-container']/h1", "xpath").split(" ");
							  	if(D_count1[0].contains(","))
							  	{
							  		String[] D1_Count1 = D_count1[0].split("\\,");
							  		String Display_Count1 = D1_Count1[0].concat(D1_Count1[1]);
							  		int DC1 = Integer.parseInt(Display_Count1);
							  
							  //checking total device count after selecting multiple manufactures
							  
							  			if(Total_Count == DC1)
							  			{
							  				ClickActionOnObjects.ClickByJavascript(driver, "//li[@class='manufacturers'][2]/input[@name='manufacturers[]']");
							  				
							  				Thread.sleep(5000L);
							  				WebElement table = driver.findElement(By.xpath("//table[@class='table']/tbody"));
							  				int count = table.findElements(By.xpath("//tr[@class='clickableRow']")).size();
							  				int fail=0;
							  				for(int i=1;i<=count;i++)
							  				{
							  					if(!ReadingTextFromObjects.GetText(driver, "//table[@class='table']/tbody/tr["+i+"]/td[2]", "xpath").trim().contentEquals(Manufactures_Name))
							  					{   fail=1;
							  						Reporter.log("Actual Result after selecting manufacture is:-"+ReadingTextFromObjects.GetText(driver, "//*[@id='equipment-container']/h1", "xpath")+" "+"Expected Result:-"+Manufactures_Name);
							  						Assert.fail("Actual Result after selecting manufacture is:-"+ReadingTextFromObjects.GetText(driver, "//*[@id='equipment-container']/h1", "xpath")+" "+"Expected Result:-"+Manufactures_Name);
							  					}
							  				}	
							  					if(fail==0)
							  					{
							  						Reporter.log("Device displayed correctly as per the manufacture");
							  					}
							  			}
							  		}
							  		else
							  		{
							  			int DC1 = Integer.parseInt(D_count1[0]);
										  
										  
										  
							  			if(Total_Count == DC1)
							  			{
							  				ClickActionOnObjects.ClickByJavascript(driver, "//li[@class='manufacturers'][2]/input[@name='manufacturers[]']");
							  				
							  				Thread.sleep(5000);
							  				WebElement table = driver.findElement(By.xpath("//table[@class='table']/tbody"));
							  				int count = table.findElements(By.xpath("//tr[@class='clickableRow']")).size();
							  				int fail=0;
							  				for(int i=1;i<=count;i++)
							  				{
							  					if(!ReadingTextFromObjects.GetText(driver, "//table[@class='table']/tbody/tr["+i+"]/td[2]", "xpath").trim().contentEquals(Manufactures_Name))
							  					{   fail=1;
							  						Reporter.log("Actual Result after selecting manufacture is:-"+ReadingTextFromObjects.GetText(driver, "//*[@id='equipment-container']/h1", "xpath")+" "+"Expected Result:-"+Manufactures_Name);
							  						Assert.fail("Actual Result after selecting manufacture is:-"+ReadingTextFromObjects.GetText(driver, "//*[@id='equipment-container']/h1", "xpath")+" "+"Expected Result:-"+Manufactures_Name);
							  					}
							  				}	
							  					if(fail==0)
							  					{
							  						Reporter.log("Device displayed correctly as per the manufacture");
							  					}
							  			}
							  		}
							  	}
						  
						  else
						  {
							  Reporter.log("Tag generated after selecting Manufacture is:-"+ReadingTextFromObjects.GetText(driver, "//li[@class='manufacturers'][2]/label", "xpath")+" "+"Expected for:-"+" "+Manufactures_Name1);
							  Assert.fail("Tag generated after selecting Manufacture is:-"+ReadingTextFromObjects.GetText(driver, "//li[@class='manufacturers'][2]/label", "xpath")+" "+"Expected for:-"+" "+Manufactures_Name1);
						  }
						  }
					  }
				  
				  }  
				  
				  else
				  {
					  
					  
					  String Display_Count = D_count[0];
					  if(Display_Count.contentEquals(Actual_Device_count))
					  {
						  ClickActionOnObjects.ClickByJavascript(driver, "//li[@class='manufacturers'][2]/input[@name='manufacturers[]']");
						  
						  Thread.sleep(2000L);
						  String Manufactures_Name1 = ReadingTextFromObjects.GetText(driver, "//li[@class='manufacturers'][2]/label", "xpath");
						  Thread.sleep(5000L);
						  boolean flag1 = Element_Exist.existsElement("//li[@class='manufacturers'][2]/span", driver);
						  if(flag1==true)
						  {
							  if(Manufactures_Name1.contentEquals(ReadingTextFromObjects.GetText(driver, "//li[@class='manufacturers'][2]/label", "xpath").trim()))
							  {

								  String Actual_Device_count1 = ReadingTextFromObjects.GetText(driver, "//li[@class='manufacturers'][2]/span", "xpath").trim();
								  int ADC2 = Integer.parseInt(Actual_Device_count1);
								  int Total_Count = ADC2 + ADC1;
								  
								  
								  String[]D_count1 = ReadingTextFromObjects.GetText(driver, "//*[@id='equipment-container']/h1", "xpath").split(" ");
								  	if(D_count1[0].contains(","))
								  	{
								  		String[] D1_Count1 = D_count1[0].split("\\,");
								  		String Display_Count1 = D1_Count1[0].concat(D1_Count1[1]);
								  		int DC1 = Integer.parseInt(Display_Count1);
								  
								  
								  
								  			if(Total_Count == DC1)
								  			{
								  				ClickActionOnObjects.ClickByJavascript(driver, "//li[@class='manufacturers'][2]/input[@name='manufacturers[]']");
								  				
								  				Thread.sleep(2000L);
								  				WebElement table = driver.findElement(By.xpath("//table[@class='table']/tbody"));
								  				int count = table.findElements(By.xpath("//tr[@class='clickableRow']")).size();
								  				int fail=0;
								  				for(int i=1;i<=count;i++)
								  				{
								  					if(!ReadingTextFromObjects.GetText(driver, "//table[@class='table']/tbody/tr["+i+"]/td[2]", "xpath").trim().contentEquals(Manufactures_Name))
								  					{   fail=1;
								  						Reporter.log("Actual Result after selecting manufacture is:-"+ReadingTextFromObjects.GetText(driver, "//*[@id='equipment-container']/h1", "xpath")+" "+"Expected Result:-"+Manufactures_Name);
								  						Assert.fail("Actual Result after selecting manufacture is:-"+ReadingTextFromObjects.GetText(driver, "//*[@id='equipment-container']/h1", "xpath")+" "+"Expected Result:-"+Manufactures_Name);
								  					}
								  				}	
								  					if(fail==0)
								  					{
								  						Reporter.log("Device displayed correctly as per the manufacture");
								  					}
								  			}
								  		}
								  		else
								  		{
								  			int DC1 = Integer.parseInt(D_count1[0]);
											  
											  
											  
								  			if(Total_Count == DC1)
								  			{
								  				ClickActionOnObjects.ClickByJavascript(driver, "//li[@class='manufacturers'][2]/input[@name='manufacturers[]']");
								  				
								  				Thread.sleep(2000L);
								  				WebElement table = driver.findElement(By.xpath("//table[@class='table']/tbody"));
								  				int count = table.findElements(By.xpath("//tr[@class='clickableRow']")).size();
								  				int fail=0;
								  				for(int i=1;i<=count;i++)
								  				{
								  					if(!ReadingTextFromObjects.GetText(driver, "//table[@class='table']/tbody/tr["+i+"]/td[2]", "xpath").trim().contentEquals(Manufactures_Name))
								  					{   fail=1;
								  						Reporter.log("Actual Result after selecting manufacture is:-"+ReadingTextFromObjects.GetText(driver, "//*[@id='equipment-container']/h1", "xpath")+" "+"Expected Result:-"+Manufactures_Name);
								  						Assert.fail("Actual Result after selecting manufacture is:-"+ReadingTextFromObjects.GetText(driver, "//*[@id='equipment-container']/h1", "xpath")+" "+"Expected Result:-"+Manufactures_Name);
								  					}
								  				}	
								  					if(fail==0)
								  					{
								  						Reporter.log("Device displayed correctly as per the manufacture");
								  					}
								  			}
								  		}
								  	}
							  
							  else
							  {
								  Reporter.log("Tag generated after selecting Manufacture is:-"+ReadingTextFromObjects.GetText(driver, "//li[@class='manufacturers'][2]/label", "xpath")+" "+"Expected for:-"+Manufactures_Name1);
								  Assert.fail("Tag generated after selecting Manufacture is:-"+ReadingTextFromObjects.GetText(driver, "//li[@class='manufacturers'][2]/label", "xpath")+" "+"Expected for:-"+Manufactures_Name1);
							  }
							  }
						  }
					  
					  }  
				  }
			  }
			  
			  else
			  {
				  Reporter.log("Tag generated after selecting Manufacture is:-"+ReadingTextFromObjects.GetText(driver, "//li[@class='manufacturers'][1]/label", "xpath").trim()+" "+"Expected tag to generate"+" "+Manufactures_Name);
				  Assert.fail("Tag generated after selecting Manufacture is:-"+ReadingTextFromObjects.GetText(driver, "//li[@class='manufacturers'][1]/label", "xpath").trim()+" "+"Expected tag to generate"+" "+Manufactures_Name);
			  }
		  }
	 
	//Function to check Forward,Backward and Today button test
	@Test
	public void Month_forword_backword_test() throws InterruptedException
	{   int j , i;
		WebDriver driver = Initialization.getDriver();
		Properties c = Initialization.getor();
		ClickActionOnObjects.ClickByJavascript(driver, c.getProperty("MonthForword"));
		Thread.sleep(5000);
		//Get current year and month
  		Calendar cal=Calendar.getInstance();
  		int year=cal.get(Calendar.YEAR);
  		int Month = cal.get(Calendar.MONTH);
		String[] months = {"January" , "February" , "March" , "April" , "May" , "June" , "July" , "August" , "September" , "October" , "November" , "December"};
  		//Getting Displayed Month and Year in calendar
  		String s = ReadingTextFromObjects.GetText(driver, "//span[@class='fc-header-title']/h2", "xpath");
  		String[] Display_month = s.split(" ");
  		
		for(i=0;i<months.length;i++)
		{
			if(Display_month[0].contentEquals(months[i]))
			{
				 break;
			}
		}
		
		
		int fail=0;
		for(j=i;j<months.length-1;j++)
		{
			ClickActionOnObjects.ClickByJavascript(driver, c.getProperty("MonthForword"));
			
			Thread.sleep(4000);
	  		String s1 = ReadingTextFromObjects.GetText(driver, "//span[@class='fc-header-title']/h2", "xpath");
	  		String[] current_Display_month = s1.split(" ");
	  		if(!current_Display_month[0].contentEquals(months[j+1]))
	  		{
	  			Reporter.log("Actual displayed month after pressing forword button is:-"+current_Display_month[0]+" "+"Expected displayed month:-"+months[j+1]);
	  			fail=1;
	  			Assert.fail("Actual displayed month after pressing forword button is:-"+current_Display_month[0]+" "+"Expected displayed month:-"+months[j+1]);
	  		}
	  			
		}
		
		Thread.sleep(4000L);
		if(fail==0)
		{
			ClickActionOnObjects.ClickByJavascript(driver, c.getProperty("MonthForword"));
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(c.getProperty("MonthForword")))).click();
	  		String s1 = ReadingTextFromObjects.GetText(driver, "//span[@class='fc-header-title']/h2", "xpath");
	  		String[] current_Display_month = s1.split(" ");
	  		year = year + 1;
	  		int y = Integer.parseInt(current_Display_month[1]);
	  		if(!current_Display_month[0].contentEquals(months[0]) & y!=year)
	  		{
	  			Reporter.log("Actual displayed year:-"+y+" "+"Expected displayed year:-"+year);
	  			Assert.fail("Actual displayed year:-"+y+" "+"Expected displayed year:-"+year);
	  		}
	  		
	  		else
	  		{
	  			Thread.sleep(4000L);
	  			for(j=months.length-1;j>=0;j--)
	  			{
	  				ClickActionOnObjects.ClickByJavascript(driver, c.getProperty("MonthBack"));
	  				
	  				Thread.sleep(4000);
	  				String s2 = ReadingTextFromObjects.GetText(driver, "//span[@class='fc-header-title']/h2", "xpath");
	  		  		String[] Current_Display_month = s2.split(" ");
	  		  		if(!Current_Display_month[0].contentEquals(months[j]))
	  		  		{
	  		  			Reporter.log("Actual displayed month after pressing backword button is:-"+current_Display_month[0]+" "+"Expected displayed month:-"+months[j]);
	  		  			fail=1;
	  		  			Assert.fail("Actual displayed month after pressing backword button is:-"+current_Display_month[0]+" "+"Expected displayed month:-"+months[j]);
	  		  		}
	  			}
	  			
	  			Thread.sleep(4000L);
	  			
	  			if(fail==0)
	  			{
	  				ClickActionOnObjects.ClickByJavascript(driver, c.getProperty("MonthBack"));
	  				
	  		  		String s2 = ReadingTextFromObjects.GetText(driver, "//span[@class='fc-header-title']/h2", "xpath");
	  		  		String[] Current_Display_month = s2.split(" ");
	  		  		int year1=cal.get(Calendar.YEAR);
	  		  		year1 = year1 - 1;
	  		  		y = Integer.parseInt(Current_Display_month[1]);
	  		  		
	  		  		
	  		  		
	  		  		if(!current_Display_month[0].contentEquals(months[11]) & y!=year1)
	  		  		{
	  		  			Reporter.log("Actual displayed year:-"+y+" "+"Expected displayed year:-"+year1);
	  		  			Assert.fail("Actual displayed year:-"+y+" "+"Expected displayed year:-"+year1);
	  		  		}
	  		  		else
	  		  		{
	  		  		
	  		  		Thread.sleep(4000L);
	  		  		ClickActionOnObjects.ClickByJavascript(driver, c.getProperty("CurrentMonth"));
	  		  		
	  		  		Thread.sleep(4000);
	  		  		String s3 = ReadingTextFromObjects.GetText(driver, "//span[@class='fc-header-title']/h2", "xpath");
	  		  		String[] Current_month = s3.split(" ");
	  		  		int Current_year = cal.get(Calendar.YEAR);
	  		  		int cy=Integer.parseInt(Current_month[1]);
	  		  		if(months[Month].contentEquals(Current_month[0]) & Current_year==cy)
	  		  		{
	  		  			Reporter.log("Forword,Backword and Today button working properly");
	  		  			
	  		  		}
	  		  	  }
	  			}
	  		}
	  			
		}

		
	}
	//Checking Total Device count displayed while Booking A Device
  @Test
  public void Total_Device_Count_Check() throws InterruptedException 
  {
	  WebDriver driver = Initialization.getDriver(); 
	  Properties c = Initialization.getor();
	  WebDriverWait wait =new WebDriverWait(driver, 100);
	  //Click on Book A Device tab
	  ClickActionOnObjects.ClickByJavascript(driver, c.getProperty("BookADevice"));
	  
	  Thread.sleep(10000);
	  //Click on View all link
	  ClickActionOnObjects.ClickByJavascript(driver, "//a[@class='popup-view-all text-blue']");
	  
	  Thread.sleep(5000);
	  //Checking Filter by Manufacture page opened or not
	  boolean flag = Element_Exist.existsElement("//div[@class='equipment-popup modal hide fade in']", driver);
	  if(flag==true)
	  {
		  //Get total count of manufactures are present
		  WebElement Manufacture_filter = driver.findElement(By.xpath("//div[@class='manufacturers-filter']"));
	  
		  int count = Manufacture_filter.findElements(By.xpath("//div[@class='manufacturer-filter span4']")).size();
		  
		  int Total_DC = 0;
		  
		  for(int i=1;i<=count;i++)
		  {
		  
		  String count1 = driver.findElement(By.xpath("//div[@class='manufacturer-filter span4']["+i+"]/span[@class='count']")).getText();
		  String[] u = count1.split("\\(");
		  String dc = u[1].trim();
		  String[] Device_count = dc.split("\\)");
		  int DC = Integer.parseInt(Device_count[0]);
		  //Adding up the number of devices present for each manufacture
		  Total_DC = Total_DC + DC;
		  }
	  
		  String[] Display_count = (driver.findElement(By.xpath("//*[@id='equipment-container']/h1")).getText()).split(" ");
		  if(Display_count[0].contains(","))
		  {
			  String[] d = Display_count[0].split("\\,");
			  String D_Count = d[0].concat(d[1]);
			  int DC1 = Integer.parseInt(D_Count);
			  if(DC1==Total_DC)
			  {
				  Reporter.log("Total device count is correct");
				  
				  wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Cancel"))).click();

			  }
			 else
			 {
				 Reporter.log("Count displayed as:-"+DC1+" "+"Actual available device count is:-"+Total_DC);
				 wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Cancel"))).click();
				 Assert.fail("Count displayed as:-"+DC1+" "+"Actual available device count is:-"+Total_DC);
			 }
	     }  
		  
		  else
		  {
			  
			  int DC1 = Integer.parseInt(Display_count[0]);
			  //checking display count and actual total number of devices count
			  if(DC1==Total_DC)
			  {
				  Reporter.log("Total device count is correct");
				  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='actions']/a"))).click();
			  }
			  //if not matched error message will add to reporter log
			 else
			 {
				 Reporter.log("Count displayed as:-"+DC1+" "+"Actual available device count is:-"+Total_DC);
				 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='actions']/a"))).click();
				 Assert.fail("Count displayed as:-"+DC1+" "+"Actual available device count is:-"+Total_DC);
			 }
		  }
	  

}
	
		  
   }
  
  
  //Function to test Account Settings form validation
  @Test(dataProvider = "Account Settings")
  public void Account_Settings(String Fname , String Lname , String Position , String Email ,String Office_Phone , String Cell_Phone , String Scenario) throws InterruptedException
  {
	  WebDriver driver = Initialization.getDriver();

	  if(!Element_Exist.existsElement("//div[@class='page-header']/h1", driver))
	  {  
	  //Click on Drop down of account settings
	  ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='menu inline dropdown user']/a");
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, "//i[@class='icon-user']");
	  Thread.sleep(5000);

	  
	  }

		  if(Scenario.equalsIgnoreCase("Blank Input"))
		  {
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_first_name']", driver), true, "First Name Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_last_name']", driver), true, "Last Name Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_position']", driver), true, "Position Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_email']", driver), true, "Email Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_contact_attributes_office_phone']", driver), true, "Office phone Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_contact_attributes_cell_phone']", driver), true, "Cell phone Text box not present");
			  GarbageValueClear.Clear(driver, "user_first_name", "id");
			  GarbageValueClear.Clear(driver, "user_last_name", "id");
			  GarbageValueClear.Clear(driver, "user_position", "id");
			  GarbageValueClear.Clear(driver, "user_email", "id");
			  GarbageValueClear.Clear(driver, "user_contact_attributes_office_phone", "id");
			  GarbageValueClear.Clear(driver, "user_contact_attributes_cell_phone", "id");
			  Thread.sleep(1000L);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_first_name", Fname);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_last_name", Lname);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_position", Position);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Email);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_office_phone", Office_Phone);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_cell_phone", Cell_Phone);
			  Thread.sleep(3000L);
			  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Update']");
			  
			  Thread.sleep(3000L);
			  if(!Element_Exist.existsElement("//label[@class='error']", driver))
			  {
				  Reporter.log("No Error Message Displayed");
				  Assert.fail("No Error Message Displayed");
			  }
			  else
			  {
				  Reporter.log("Account Seetings functionality working properly with Blank Input");
				  driver.navigate().refresh();
				  Thread.sleep(2000L);
			  }
		  }
		  
		  if(Scenario.equalsIgnoreCase("Blank First and Last Name"))
		  {
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_first_name']", driver), true, "First Name Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_last_name']", driver), true, "Last Name Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_position']", driver), true, "Position Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_email']", driver), true, "Email Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_contact_attributes_office_phone']", driver), true, "Office phone Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_contact_attributes_cell_phone']", driver), true, "Cell phone Text box not present");
			  GarbageValueClear.Clear(driver, "user_first_name", "id");
			  GarbageValueClear.Clear(driver, "user_last_name", "id");
			  GarbageValueClear.Clear(driver, "user_position", "id");
			  GarbageValueClear.Clear(driver, "user_email", "id");
			  GarbageValueClear.Clear(driver, "user_contact_attributes_office_phone", "id");
			  GarbageValueClear.Clear(driver, "user_contact_attributes_cell_phone", "id");
			  Thread.sleep(1000L);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_first_name", Fname);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_last_name", Lname);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_position", Position);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Email);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_office_phone", Office_Phone);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_cell_phone", Cell_Phone);
			  Thread.sleep(1000L);
			  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Update']");
			
			  Thread.sleep(3000L);
			  if(!Element_Exist.existsElement("//label[@class='error']", driver))
			  {
				  Reporter.log("No Error Message Displayed");
				  Assert.fail("No Error Message Displayed");
			  }
			  else
			  {
				  Reporter.log("Account Seetings functionality working properly with Blank Input");
				  driver.navigate().refresh();
				  Thread.sleep(2000L);
			  }
		  }
		  
		  if(Scenario.equalsIgnoreCase("Invalid Position"))
		  {
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_first_name']", driver), true, "First Name Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_last_name']", driver), true, "Last Name Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_position']", driver), true, "Position Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_email']", driver), true, "Email Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_contact_attributes_office_phone']", driver), true, "Office phone Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_contact_attributes_cell_phone']", driver), true, "Cell phone Text box not present");
			  GarbageValueClear.Clear(driver, "user_first_name", "id");
			  GarbageValueClear.Clear(driver, "user_last_name", "id");
			  GarbageValueClear.Clear(driver, "user_position", "id");
			  GarbageValueClear.Clear(driver, "user_email", "id");
			  GarbageValueClear.Clear(driver, "user_contact_attributes_office_phone", "id");
			  GarbageValueClear.Clear(driver, "user_contact_attributes_cell_phone", "id");
			  Thread.sleep(1000L);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_first_name", Fname);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_last_name", Lname);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_position", Position);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Email);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_office_phone", Office_Phone);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_cell_phone", Cell_Phone);
			  Thread.sleep(1000L);
			  if(!Element_Exist.existsElement("//label[@class='error' and @for='user_position']", driver))
			  {
				  Reporter.log("No Error Message Displayed");
				  Assert.fail("No Error Message Displayed");
			  }
			  else
			  {
				  Reporter.log("Account Seetings functionality working properly with Invalid Position");
				  driver.navigate().refresh();
				  Thread.sleep(2000L);
			  }
		  }
		  
		  if(Scenario.equalsIgnoreCase("Invalid Email"))
		  {
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_first_name']", driver), true, "First Name Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_last_name']", driver), true, "Last Name Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_position']", driver), true, "Position Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_email']", driver), true, "Email Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_contact_attributes_office_phone']", driver), true, "Office phone Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_contact_attributes_cell_phone']", driver), true, "Cell phone Text box not present");
			  GarbageValueClear.Clear(driver, "user_first_name", "id");
			  GarbageValueClear.Clear(driver, "user_last_name", "id");
			  GarbageValueClear.Clear(driver, "user_position", "id");
			  GarbageValueClear.Clear(driver, "user_email", "id");
			  GarbageValueClear.Clear(driver, "user_contact_attributes_office_phone", "id");
			  GarbageValueClear.Clear(driver, "user_contact_attributes_cell_phone", "id");
			  Thread.sleep(1000L);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_first_name", Fname);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_last_name", Lname);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_position", Position);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Email);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_office_phone", Office_Phone);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_cell_phone", Cell_Phone);
			  Thread.sleep(1000L);
			  if(!Element_Exist.existsElement("//label[@class='error' and @for='user_email']", driver))
			  {
				  Reporter.log("No Error Message Displayed");
				  Assert.fail("No Error Message Displayed");
			  }
			  else
			  {
				  Reporter.log("Account Seetings functionality working properly with Invalid Email");
				  driver.navigate().refresh();
				  Thread.sleep(2000L);
			  }
		  }
		  
		  if(Scenario.equalsIgnoreCase("Invalid Office and Cell Phone"))
		  {
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_first_name']", driver), true, "First Name Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_last_name']", driver), true, "Last Name Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_position']", driver), true, "Position Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_email']", driver), true, "Email Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_contact_attributes_office_phone']", driver), true, "Office phone Text box not present");
			  Assert.assertEquals(Element_Exist.existsElement("//input[@id='user_contact_attributes_cell_phone']", driver), true, "Cell phone Text box not present");
			  GarbageValueClear.Clear(driver, "user_first_name", "id");
			  GarbageValueClear.Clear(driver, "user_last_name", "id");
			  GarbageValueClear.Clear(driver, "user_position", "id");
			  GarbageValueClear.Clear(driver, "user_email", "id");
			  GarbageValueClear.Clear(driver, "user_contact_attributes_office_phone", "id");
			  GarbageValueClear.Clear(driver, "user_contact_attributes_cell_phone", "id");
			  Thread.sleep(1000L);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_first_name", Fname);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_last_name", Lname);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_position", Position);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_email", Email);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_office_phone", Office_Phone);
			  SendingTestValueInTextBox.SendValue(driver, "id", "user_contact_attributes_cell_phone", Cell_Phone);
			  Thread.sleep(1000L);
			  if(!Element_Exist.existsElement("//label[@class='error' and @for='user_contact_attributes_office_phone']", driver))
			  {
				  Reporter.log("No Error Message Displayed");
				  Assert.fail("No Error Message Displayed");
			  }
			  else
			  {
				  Reporter.log("Account Seetings functionality working properly with Invalid Office and Cell Phone");
				  driver.navigate().refresh();
				  Thread.sleep(2000L);
				  ClickActionOnObjects.ClickObject(driver, "//*[@id='logo']/a/img", "xpath");
				  Thread.sleep(4000L);
			  }
		  }
	  }
	  
  
  //Function to test whether user can Book A Device on current date
  @Test
  public void Book_A_Device_On_Todays_Date_Test() throws InterruptedException
  {
	  WebDriver driver = Initialization.getDriver();
	  
	  ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='/equipment']");
	  Thread.sleep(5000);
	  String Manufacturer = ReadingTextFromObjects.GetText(driver, "//table[@class='table']/tbody/tr[1]/td[2]", "xpath");
	  String Model = ReadingTextFromObjects.GetText(driver, "//table[@class='table']/tbody/tr[1]/td[3]", "xpath");
	  String Device_Description = Manufacturer.concat(" "+Model);
	  ClickActionOnObjects.ClickByJavascript(driver, "//table[@class='table']/tbody/tr[1]/td[3]");
	  Thread.sleep(10000);
	  
	  String Actual_Device_Description = ReadingTextFromObjects.GetText(driver, "//h1[@class='listing-mfr-model']", "xpath").trim();
	  if(!Device_Description.contentEquals(Actual_Device_Description))
	   {
		  			Reporter.log("Actual Device Description is :-"+Actual_Device_Description+" "+"Expected Device Description:-"+Device_Description);
		  			Assert.fail("Actual Device Description is :-"+Actual_Device_Description+" "+"Expected Device Description:-"+Device_Description);
	   }
	  else
	  {
		  DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		  Date date = new Date();
		  String Default_Book_Date = ReadingTextFromObjects.GetText(driver, "//input[@id='events_booking_start']", "xpath");
		  GarbageValueClear.Clear(driver, "//input[@id='events_booking_start']", "xpath");
		  Thread.sleep(2000L);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_start']", dateFormat.format(date));
		  Thread.sleep(1000L);
		  driver.findElement(By.xpath("//div[@class='book-controls']/h3")).click();
		  Thread.sleep(8000);
		  if(!ReadingTextFromObjects.GetText(driver, "//input[@id='events_booking_start']", "xpath").contentEquals(Default_Book_Date))
		  {
			  Reporter.log("Booking date is displayed as:-"+ReadingTextFromObjects.GetText(driver, "//input[@id='events_booking_start']", "xpath")+" "+"Expected :-"+Default_Book_Date);
			  Assert.fail("Booking date is displayed as:-"+ReadingTextFromObjects.GetText(driver, "//input[@id='events_booking_start']", "xpath")+" "+"Expected :-"+Default_Book_Date);
		  }
		  else
		  {
			  Reporter.log("Application is working properly when user tried to book a device on current date");
			  Thread.sleep(5000);
		  }
	  }
  }
  
  //Function to test Repeat functionality with invalid occurrences
  @Test
  public void Repeat_with_invalid_occurrences() throws InterruptedException
  {
	  WebDriver driver = Initialization.getDriver();
	  ClickActionOnObjects.ClickByJavascript(driver, "//a[@href='/equipment']");
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, "//table[@class='table']/tbody/tr[1]/td[3]");
	  Thread.sleep(8000);
	  if(Element_Exist.existsElement("//input[@id='repeat']", driver))
		{
			ClickActionOnObjects.ClickObject(driver, "//input[@id='repeat']", "xpath");
			Thread.sleep(8000);
			if(Element_Exist.existsElement("//div[@class='rrule control-group on']", driver))
		 {
			boolean Never =  Element_Exist.existsElement("//input[@id='events_booking_rrule_attributes_end_type_none']", driver);
			boolean After =  Element_Exist.existsElement("//input[@id='events_booking_rrule_attributes_end_type_count']", driver);
			boolean on =  Element_Exist.existsElement("//input[@id='events_booking_rrule_attributes_end_type_until']", driver);
			if(Never==false)
			{
				Reporter.log("Repeat Never radio button not present");
				Assert.fail("Repeat Never radio button not present");
			}
			
			if(After==false)
			{
				Reporter.log("Repeat After radio button not present");
				Assert.fail("Repeat After radio button not present");
			}
			
			if(on==false)
			{
				Reporter.log("Repeat on radio button not present");
				Assert.fail("Repeat on radio button not present");
			}
			
			if(Element_Exist.existsElement("//input[@id='events_booking_rrule_attributes_end_value_count']", driver))
			{
				SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_rrule_attributes_end_value_count']", "0");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@class='humanize']")).click();
				Thread.sleep(8000);
				if(!Element_Exist.existsElement("//span[@class='help-block error']", driver))
				{
					Reporter.log("Error message for occurrences less than 2 is not displayed");
					Assert.fail("Error message for occurrences less than 2 is not displayed");
				}
				
				else
				{
					Reporter.log("Repeat with invalid occurrences is working properly");
				}
			}
			else
			{
				Reporter.log("Repeat after textbox not present");
				Assert.fail("Repeat after textbox not present");
			}
		}
		else
		{
			Reporter.log("Repeat options box is not displayed after selecting the Repeat checkbox");
			Assert.fail("Repeat options box is not displayed after selecting the Repeat checkbox");
		}
	}
	else
	{
		Reporter.log("Repeat checkbox not present");
		Assert.fail("Repeat checkbox not present");
	}

  }
  
  //Function to Test Book A Device validation and functionality
  @Test(dataProvider = "Book A Device")
  public void Book_A_Device(String Begin_Time , String End_Time , String Cases , String Procedure , String Lname , String Scenario) throws InterruptedException
  {
	  WebDriver driver = Initialization.getDriver();
	  
	  Properties c1 = Initialization.getor();
	  WebDriverWait wait =new WebDriverWait(driver, 100);
	  
	  ClickActionOnObjects.ClickByJavascript(driver, c1.getProperty("BookADevice"));
	  Thread.sleep(5000);
	  ClickActionOnObjects.ClickByJavascript(driver, "//table[@class='table']/tbody/tr[1]/td[3]");
	  Thread.sleep(10000);
	  String Ref_no = ReadingTextFromObjects.GetText(driver, "//table[@class='table table-striped additional-details']/tbody/tr[7]/td[2]", "xpath").trim();
	  if(Element_Exist.existsElement("//h1[@class='listing-mfr-model']", driver))
	  {
		 if(driver.findElement(By.xpath("//input[@id='repeat']")).isSelected())
		  {
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@id='repeat']");
		  Thread.sleep(3000);
		  }
	  SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	  Calendar c = Calendar.getInstance();
	  c.setTime(new Date());
	  c.add(Calendar.DATE, 90);
	  
	  String output1 = sdf.format(c.getTime());

	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_start']", "xpath");
	  Thread.sleep(3000);
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_start']", output1);
	 
	  int i = 101;
	  driver.findElement(By.xpath("//h1[@class='listing-mfr-model']")).click();
	  Thread.sleep(2000L);
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Book Asset']"))).click();
	  Thread.sleep(5000);
	  while(Element_Exist.existsElement("//div[@class='alert alert-danger alert-dismissible']", driver))
	  {
		  
		  c.setTime(new Date());
		  c.add(Calendar.DATE, i);
		  output1 = sdf.format(c.getTime());
		  GarbageValueClear.Clear(driver, "//input[@id='events_booking_start']", "xpath");
		  Thread.sleep(3000);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_start']", output1);
		  i++;
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Book Asset']");
		  
		  Thread.sleep(5000);
		 
	  }
	} 

    

	if(Scenario.contentEquals("Valid Inputs"))
	{
	  
	  String[] months = {"January" , "February" , "March" , "April" , "May" , "June" , "July" , "August" , "September" , "October" , "November" , "December"};
	  String Date_of_booking = driver.findElement(By.id("events_booking_start")).getAttribute("value");
		  
	  String m[] = Date_of_booking.split("\\/");
	  int Month = Integer.parseInt(m[0]);
	  String Book_month = months[Month-1];

	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_needed_at']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_needed_til']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_cases']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_procedure']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_md_name']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_patient_id']", "xpath");
	  Thread.sleep(5000);
	  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//div[@class='tooltip-inner']", "xpath"), "Do not enter Patient Name");
	  GarbageValueClear.Clear(driver, "//textarea[@id='events_booking_booking_data_attributes_message']", "xpath");
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_needed_at']", Begin_Time);
	  Thread.sleep(2000);
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_needed_til']", "xpath");
	  Thread.sleep(2000);
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_needed_til']", End_Time);
	  Thread.sleep(2000);
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_cases']", Cases);
	  Thread.sleep(2000);
	  String Needed_until = driver.findElement(By.id("events_booking_booking_data_attributes_needed_til")).getAttribute("value");
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_procedure']", Procedure);
	  Thread.sleep(2000);
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_md_name']", Lname);
	  Thread.sleep(2000);
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_patient_id']", "Test");
	  Thread.sleep(2000);
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//textarea[@id='events_booking_booking_data_attributes_message']", "Test");
	  Thread.sleep(3000);
	  ClickActionOnObjects.ClickObject(driver, "//button[@class='btn btn-large btn-primary']", "xpath");
	  Thread.sleep(10000);
	  boolean flag = Element_Exist.existsElement("//div[@class='alert alert-success']", driver);
	  if(flag==true)
	  {   
		  
		  Reporter.log("Book A Device functionality is working properly with valid inputs");  
		  ClickActionOnObjects.ClickByJavascript(driver, c1.getProperty("BookADevice"));
		  Thread.sleep(5000);
		  ClickActionOnObjects.ClickByJavascript(driver, "//table[@class='table']/tbody/tr[1]/td[3]");
		  Thread.sleep(5000);
		  GarbageValueClear.Clear(driver, "//input[@id='events_booking_start']", "xpath");
		  Thread.sleep(3000);
		  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_start']", m[0]+"/"+m[1]+"/"+m[2]);
		  ClickActionOnObjects.ClickByJavascript(driver, "//input[@value='Book Asset']");
		  Thread.sleep(5000);
		  Assert.assertEquals(Element_Exist.existsElement("//div[@class='alert alert-danger alert-dismissible']", driver), true, "Application allowing for booking same device on same day");
		  ClickActionOnObjects.ClickByJavascript(driver, c1.getProperty("MyBookings"));
		  Thread.sleep(10000);
		  

	  	  for(int i=0;i<months.length;i++)
	  	  {
	  		  String s = ReadingTextFromObjects.GetText(driver, "//span[@class='fc-header-title']/h2", "xpath");
	  	   	  String[] Display_month = s.split(" ");
	  	   	  String month = Display_month[0];
	  	   	  
	  		  if(month.contentEquals(Book_month))
	  		  {
	  			  break;
	  		  }
	  		  else
	  		  {
			 
	  		  ClickActionOnObjects.ClickByJavascript(driver, c1.getProperty("MonthForword"));
			  Thread.sleep(2000);


	  		  }
	  	  }
	  	  Thread.sleep(5000);
	  	  System.out.println("//span[@data-date="+"'"+m[2]+"-"+m[0]+"-"+m[1]+"'"+"and @data-equipment-id="+"'"+Ref_no+"'"+"]"+"");
	  	  Assert.assertEquals(Element_Exist.existsElement("//span[@data-date="+"'"+m[2]+"-"+m[0]+"-"+m[1]+"'"+"and @data-equipment-id="+"'"+Ref_no+"'"+"]"+"", driver), true , "Booked Device is not visible on calendar");
	  	  Reporter.log("Booked device is visible at calendar");
	  	  Point h =driver.findElement(By.xpath("//td[@data-date="+"'"+m[2]+"-"+m[0]+"-"+m[1]+"'"+"]"+"")).getLocation();
		  ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+(h.getY()-50)+");"); 
		  Thread.sleep(5000);
		  driver.findElement(By.xpath("//span[@data-date="+"'"+m[2]+"-"+m[0]+"-"+m[1]+"'"+"and @data-equipment-id="+"'"+Ref_no+"'"+"]"+"")).click();
	  	  Thread.sleep(5000);
	  	  Assert.assertEquals(Element_Exist.existsElement("//div[@class='booking-popup modal hide fade in' and @aria-hidden='false']", driver), true , "Popup is not displaying after clicking on Booked device entry");
	  	  
	  	  Assert.assertEquals(Element_Exist.existsElement("//div[@class='booking-popup modal hide fade in' and @aria-hidden='false']/div/div[@class='control-group text-center']/div/div/a[1]", driver), true , "Edit button is not displaying on Booked device entry");
	  	  Assert.assertEquals(Element_Exist.existsElement("//div[@class='booking-popup modal hide fade in' and @aria-hidden='false']/div/div[@class='control-group text-center']/div/div/a[2]", driver), true , "Delete button is not displaying on Booked device entry");
	  	  Assert.assertEquals(Element_Exist.existsElement("//div[@class='booking-popup modal hide fade in' and @aria-hidden='false']/div/div[@class='control-group text-center']/div/div/a[3]", driver), true , "Close button is not displaying on Booked device entry");
	  	  String B_Time[] = Begin_Time.split(" ");
	  	  String E_Time[] = Needed_until.trim().split(" ");

	  	  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//table[@class='case table']/tbody/tr/td[1]", "xpath"), B_Time[0]+B_Time[1] , "Needed At time is not matching with actual Needed At time");
	  	  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//table[@class='case table']/tbody/tr/td[2]", "xpath"), E_Time[0]+E_Time[1] , "Needed until time is not matching with actual Needed until time");
	  	  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//table[@class='case table']/tbody/tr/td[3]", "xpath"), Cases , "Cases are not matching with actual Cases");
	  	  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//table[@class='case table']/tbody/tr/td[5]", "xpath"), Procedure , "Procedure is not matching with actual Procedure");
	  	  ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='booking-popup modal hide fade in' and @aria-hidden='false']/div/div[@class='control-group text-center']/div/div/a[3]");
	  	  Thread.sleep(5000);
	  	  Assert.assertEquals(Element_Exist.existsElement("//div[@class='booking-popup modal hide fade in' and @aria-hidden='false']", driver), false , "Popup is  displaying after clicking on close button");
	  	  driver.findElement(By.xpath("//span[@data-date="+"'"+m[2]+"-"+m[0]+"-"+m[1]+"'"+"and @data-equipment-id="+"'"+Ref_no+"'"+"]"+"")).click();
	  	  Thread.sleep(5000);
	  	  ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='booking-popup modal hide fade in' and @aria-hidden='false']/div/div[@class='control-group text-center']/div/div/a[1]");
	  	  Thread.sleep(8000);
	  	  Assert.assertEquals(Element_Exist.existsElement("//div[starts-with(@class,'edit-booking-popup modal') and @aria-hidden='false']", driver), true, "Event edit popup is not displaying");
	  	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_start']")).getAttribute("value"), Date_of_booking, "Booking date is not maching with event edit popup showing");
	  	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_needed_at']")).getAttribute("placeholder"), Begin_Time, "Needed At time is not matching at Event edit popup with actual Needed At time");
	  	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_needed_til']")).getAttribute("placeholder"), Needed_until, "Needed_until time is not matching at Evenr edit popup with actual Needed_until time");
	  	  Assert.assertEquals(Element_Exist.existsElement( "//input[@id='repeat']", driver), true, "Repeat check box is not present");
	  	  ClickActionOnObjects.ClickByJavascript(driver, "//input[@id='repeat']");
	  	  Thread.sleep(5000);
	  	  Assert.assertEquals(Element_Exist.existsElement("//div[@class='box-content edit']", driver), true, "Edit Box for repeat functionality is not present");
	  	  Assert.assertEquals(Element_Exist.existsElement("//a[@class='edit btn btn-mini pull-right']", driver), true, "Edit link is not present");
	  	  ClickActionOnObjects.ClickByJavascript(driver, "//a[@class='edit btn btn-mini pull-right']");
	  	  Thread.sleep(8000);
	  	  Assert.assertEquals(Element_Exist.existsElement("//div[@class='box-content save' and @style='display: block;']", driver), true, "Save Box for repeat functionality is not present");
	  	  Assert.assertEquals(Element_Exist.existsElement("//a[@class='save btn btn-mini pull-right']", driver), true, "Save link is not present");
	  	  ClickActionOnObjects.ClickByJavascript(driver, "//input[@id='events_booking_rrule_attributes_end_type_count']");
	  	  Thread.sleep(5000);
	  	  Assert.assertEquals(Element_Exist.existsElement("//input[@id='events_booking_rrule_attributes_end_value_count']", driver), true, "Occurrences text box is not present");
	  	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_rrule_attributes_end_value_count']", "0");
	  	  Thread.sleep(5000);
	  	  driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_cases']")).click();
	  	  Thread.sleep(5000);
	  	  Assert.assertEquals(Element_Exist.existsElement("//label[@class='error']", driver), true, "Error message is not displayed for occurrences less than 2");
	  	  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//label[@class='error']", "xpath"), "Please enter a value greater than or equal to 2.");
	  	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_rrule_attributes_end_value_count']", "xpath");
	  	  Thread.sleep(2000);
	  	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_rrule_attributes_end_value_count']", GetDate.getMonth());
	  	  Thread.sleep(2000);
	  	  ClickActionOnObjects.ClickByJavascript(driver, "//a[@class='save btn btn-mini pull-right']");
	  	  Thread.sleep(3000);
	  	  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//div[@class='ends']", "xpath"), "For "+GetDate.getMonth()+" Occurrences");
	  	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_procedure']", "xpath");
	  	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_procedure']", "TestName");
	  	  Thread.sleep(5000);
	  	  ClickActionOnObjects.ClickByJavascript(driver, "//input[@class='btn btn-primary' and @value='Save']");
	  	  Thread.sleep(5000);
	  	  driver.findElement(By.xpath("//span[@data-date="+"'"+m[2]+"-"+m[0]+"-"+m[1]+"'"+"and @data-equipment-id="+"'"+Ref_no+"'"+"]"+"")).click();
	  	  Thread.sleep(5000);
	  	  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//table[@class='case table']/tbody/tr/td[5]", "xpath"), "TestName" , "Procedure is not updated after editing");
	  	  ClickActionOnObjects.ClickByJavascript(driver, "//a[@class='btn modal-cancel']");
	  	  Thread.sleep(5000);
	  	  driver.findElement(By.xpath("//span[@data-date="+"'"+m[2]+"-"+m[0]+"-"+m[1]+"'"+"and @data-equipment-id="+"'"+Ref_no+"'"+"]"+"")).click();
	  	  Thread.sleep(5000);
	  	  driver.findElement(By.xpath("//div[@class='booking-popup modal hide fade in' and @aria-hidden='false']/div/div[@class='control-group text-center']/div/div/a[1]")).click();
	  	  Thread.sleep(5000);
	  	  Assert.assertEquals(Element_Exist.existsElement("//div[@class='controls text-center']/a[@class='btn edit-single']" , driver), true, "This Occurrence button is not present");
	  	  Assert.assertEquals(Element_Exist.existsElement("//div[@class='controls text-center']/a[@class='btn edit-all']" , driver), true, "All Occurrence button is not present");
	  	  Assert.assertEquals(Element_Exist.existsElement("//div[@class='controls text-center']/a[@class='btn popup-cancel']" , driver), true, "Cancel button is not present");
	  	  driver.findElement(By.xpath("//div[@class='controls text-center']/a[@class='btn popup-cancel']")).click();
	  	  Thread.sleep(5000);
	  	  ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='booking-popup modal hide fade in' and @aria-hidden='false']/div/div[@class='control-group text-center']/div/div/a[3]");
	  }
	  
	  else
	  {
		  Reporter.log("Confirmation message is not displayed after booking");
	  }
	}
	if(Scenario.contentEquals("Zero Begin and End Time"))
	{
		Assert.assertEquals(Element_Exist.existsElement("//div[@class='control-group input-append bootstrap-timepicker'][1]/span/i", driver), true, "Time icon is not displayed for Begin Time");
	  	ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='control-group input-append bootstrap-timepicker'][1]/span/i");
	  	Thread.sleep(5000);
	  	Assert.assertEquals(Element_Exist.existsElement("//input[@class='bootstrap-timepicker-hour']", driver), true, "Timepicker textbox for hour is not displayed");
	  	ClickActionOnObjects.ClickByJavascript(driver, "//input[@class='bootstrap-timepicker-hour']");
	  	Thread.sleep(5000);
	  	driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_cases']")).click();
	  	//ClickActionOnObjects.ClickByJavascript(driver, "//input[@id='events_booking_booking_data_attributes_cases']");
	  	Thread.sleep(5000);

	  	Assert.assertEquals(Element_Exist.existsElement("//div[@class='control-group input-append bootstrap-timepicker'][2]/span/i", driver), true, "Time icon is not displayed for End Time");
	  	driver.findElement(By.xpath("//div[@class='control-group input-append bootstrap-timepicker'][2]/span/i")).click();
	  	Thread.sleep(2000);
	  	ClickActionOnObjects.ClickByJavascript(driver, "//a[@data-action='toggleMeridian']/i[@class='icon-chevron-down']");
	  	Thread.sleep(5000);
	  	driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_cases']")).click();
	  	Thread.sleep(5000);
	  	System.out.println(driver.findElement(By.id("events_booking_booking_data_attributes_needed_at")).getAttribute("value"));
	  	System.out.println(driver.findElement(By.id("events_booking_booking_data_attributes_needed_til")).getAttribute("value"));
	  	if(driver.findElement(By.id("events_booking_booking_data_attributes_needed_at")).getAttribute("value").contentEquals(Begin_Time)||driver.findElement(By.id("events_booking_booking_data_attributes_needed_til")).getAttribute("value").contentEquals(End_Time))
	  	{
	  		if(driver.findElement(By.id("events_booking_booking_data_attributes_needed_at")).getAttribute("value").contentEquals(Begin_Time))
	  		{
	  			if(driver.findElement(By.id("events_booking_booking_data_attributes_needed_til")).getAttribute("value").contentEquals(End_Time))
	  			{
	  				Reporter.log("0:00 is accepted as valid time for both Begin and End Time");
	  				Assert.fail("0:00 is accepted as valid time for both Begin and End Time");
	  			}
	  			else
	  			{
	  				Reporter.log("0:00 is accepted as valid time for Begin Time");
	  				Assert.fail("0:00 is accepted as valid time for Begin Time");
	  			}
	  		}
	  		else
	  		{
	  			Reporter.log("0:00 is accepted as valid time for Begin Time");
	  			Assert.fail("0:00 is accepted as valid time for Begin Time");
	  		}
	  	}
	  	
	  	else
	  	{
	  		Reporter.log("0:00 is not accepted at needed_at and needed_til as valid time");
	  	}
	}
	
	if(Scenario.contentEquals("Less than 1 hour Difference in Begin and End Time"))
	{
		Assert.assertEquals(Element_Exist.existsElement("//div[@class='control-group input-append bootstrap-timepicker'][1]/span/i", driver), true, "Time icon is not displayed for Begin Time");
	  	ClickActionOnObjects.ClickByJavascript(driver, "//div[@class='control-group input-append bootstrap-timepicker'][1]/span/i");
	  	Thread.sleep(5000);
	  	Assert.assertEquals(Element_Exist.existsElement("//input[@class='bootstrap-timepicker-hour']", driver), true, "Timepicker textbox for hour is not displayed");
	  	ClickActionOnObjects.ClickByJavascript(driver, "//input[@class='bootstrap-timepicker-hour']");
	  	ClickActionOnObjects.ClickByJavascript(driver, "//a[@data-action='toggleMeridian']/i[@class='icon-chevron-down']");
	  	Thread.sleep(5000);
	  	driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_cases']")).click();
	  	//ClickActionOnObjects.ClickByJavascript(driver, "//input[@id='events_booking_booking_data_attributes_cases']");
	  	Thread.sleep(5000);

	  	Assert.assertEquals(Element_Exist.existsElement("//div[@class='control-group input-append bootstrap-timepicker'][2]/span/i", driver), true, "Time icon is not displayed for End Time");
	  	driver.findElement(By.xpath("//div[@class='control-group input-append bootstrap-timepicker'][2]/span/i")).click();
	  	Thread.sleep(2000);
	  	ClickActionOnObjects.ClickByJavascript(driver, "//a[@data-action='incrementMinute']/i");
	  	ClickActionOnObjects.ClickByJavascript(driver, "//a[@data-action='toggleMeridian']/i[@class='icon-chevron-down']");
	  	Thread.sleep(5000);
	  	driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_cases']")).click();
	  	Thread.sleep(5000);
	  	System.out.println(driver.findElement(By.id("events_booking_booking_data_attributes_needed_at")).getAttribute("value"));
	  	System.out.println(driver.findElement(By.id("events_booking_booking_data_attributes_needed_til")).getAttribute("value"));
	  	if(driver.findElement(By.id("events_booking_booking_data_attributes_needed_at")).getAttribute("value").contentEquals(Begin_Time) && driver.findElement(By.id("events_booking_booking_data_attributes_needed_til")).getAttribute("value").contentEquals(End_Time))
	  	{
	  		Reporter.log("Less than 1 hour difference in Begin and End time is accepted");
	  		Assert.fail("Less than 1 hour difference in Begin and End time is accepted");
	  			
	  	}
	  	else
	  	{
	  		Reporter.log("Less than 1 hr difference in Begin and End time is not accepted");
	  	}
	}
	if(Scenario.contentEquals("Blank"))
	{

	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_needed_at']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_needed_til']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_cases']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_procedure']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_md_name']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_patient_id']", "xpath");
	  Thread.sleep(1000);
	  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//div[@class='tooltip-inner']", "xpath"), "Do not enter Patient Name");
	  GarbageValueClear.Clear(driver, "//textarea[@id='events_booking_booking_data_attributes_message']", "xpath");
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_needed_at']", Begin_Time);
	  
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_needed_til']", "xpath");
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_needed_til']", End_Time);
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_cases']", Cases);
	 
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_procedure']", Procedure);
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_md_name']", Lname);
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_patient_id']", "Test");
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//textarea[@id='events_booking_booking_data_attributes_message']", "Test");
	  
	  ClickActionOnObjects.ClickObject(driver, "//button[@class='btn btn-large btn-primary']", "xpath");
	  Thread.sleep(8000);  
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_needed_at']")).getCssValue("border-color"), "#953b39", "Border color has not changed to Red for Begin Time validation");
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_needed_til']")).getCssValue("border-color"), "#b94a48", "Border color has not changed to Red for End Time validation");
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_cases']")).getCssValue("border-color"), "#b94a48", "Border color has not changed to Red for Cases validation");
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_md_name']")).getCssValue("border-color"), "#b94a48", "Border color has not changed to Red for MD Last Name validation");
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_procedure']")).getCssValue("border-color"), "#b94a48", "Border color has not changed to Red for Procedure validation");
	  Reporter.log("Book A Device for Scenario Blank inputs Working properly");
	  driver.navigate().refresh();
	}
	
	if(Scenario.contentEquals("Blank Begin Time"))
	{
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_needed_at']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_needed_til']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_cases']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_procedure']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_md_name']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_patient_id']", "xpath");
	  Thread.sleep(1000);
	  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//div[@class='tooltip-inner']", "xpath"), "Do not enter Patient Name");
	  GarbageValueClear.Clear(driver, "//textarea[@id='events_booking_booking_data_attributes_message']", "xpath");
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_needed_at']", Begin_Time);
	  
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_needed_til']", "xpath");
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_needed_til']", End_Time);
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_cases']", Cases);
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_procedure']", Procedure);
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_md_name']", Lname);
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_patient_id']", "Test");
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//textarea[@id='events_booking_booking_data_attributes_message']", "Test");
	  
	  ClickActionOnObjects.ClickObject(driver, "//button[@class='btn btn-large btn-primary']", "xpath");
	  Thread.sleep(8000);	  
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_needed_at']")).getCssValue("border-color"), "#953b39", "Border color has not changed to Red for Begin Time validation");
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_needed_til']")).getCssValue("border-color"), "#d0d0d0", "Border color has not changed to Red for End Time validation");
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_cases']")).getCssValue("border-color"), "#d0d0d0", "Border color has not changed to Red for Cases validation");
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_md_name']")).getCssValue("border-color"), "#d0d0d0", "Border color has not changed to Red for MD Last Name validation");
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_procedure']")).getCssValue("border-color"), "#d0d0d0", "Border color has not changed to Red for Procedure validation");
	  Reporter.log("Book A Device for Scenario Blank Begin Time Working properly");
	  driver.navigate().refresh();
	}
	
	if(Scenario.contentEquals("Blank Cases"))
	{
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_needed_at']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_needed_til']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_cases']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_procedure']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_md_name']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_patient_id']", "xpath");
	  Thread.sleep(1000);
	  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//div[@class='tooltip-inner']", "xpath"), "Do not enter Patient Name");
	  GarbageValueClear.Clear(driver, "//textarea[@id='events_booking_booking_data_attributes_message']", "xpath");
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_needed_at']", Begin_Time);
	  
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_needed_til']", "xpath");
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_needed_til']", End_Time);
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_cases']", Cases);
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_procedure']", Procedure);
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_md_name']", Lname);
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_patient_id']", "Test");
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//textarea[@id='events_booking_booking_data_attributes_message']", "Test");
	  
	  ClickActionOnObjects.ClickObject(driver, "//button[@class='btn btn-large btn-primary']", "xpath");
	  Thread.sleep(8000);	  
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_needed_at']")).getCssValue("border-color"), "#d0d0d0", "Border color has not changed to Red for Begin Time validation");
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_needed_til']")).getCssValue("border-color"), "#d0d0d0", "Border color has not changed to Red for End Time validation");
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_cases']")).getCssValue("border-color"), "#953b39", "Border color has not changed to Red for Cases validation");
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_md_name']")).getCssValue("border-color"), "#d0d0d0", "Border color has not changed to Red for MD Last Name validation");
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_procedure']")).getCssValue("border-color"), "#d0d0d0", "Border color has not changed to Red for Procedure validation");
	  Reporter.log("Book A Device for Scenario Blank Cases Working properly");
	  driver.navigate().refresh();
	}
	
	if(Scenario.contentEquals("Blank Procedure"))
	{
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_needed_at']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_needed_til']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_cases']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_procedure']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_md_name']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_patient_id']", "xpath");
	  Thread.sleep(1000);
	  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//div[@class='tooltip-inner']", "xpath"), "Do not enter Patient Name");
	  GarbageValueClear.Clear(driver, "//textarea[@id='events_booking_booking_data_attributes_message']", "xpath");
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_needed_at']", Begin_Time);
	  
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_needed_til']", "xpath");
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_needed_til']", End_Time);
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_cases']", Cases);
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_procedure']", Procedure);
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_md_name']", Lname);
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_patient_id']", "Test");
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//textarea[@id='events_booking_booking_data_attributes_message']", "Test");
	  
	  ClickActionOnObjects.ClickObject(driver, "//button[@class='btn btn-large btn-primary']", "xpath");
	  Thread.sleep(8000);
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_needed_at']")).getCssValue("border-color"), "#d0d0d0", "Border color has not changed to Red for Begin Time validation");
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_needed_til']")).getCssValue("border-color"), "#d0d0d0", "Border color has not changed to Red for End Time validation");
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_cases']")).getCssValue("border-color"), "#d0d0d0", "Border color has not changed to Red for Cases validation");
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_md_name']")).getCssValue("border-color"), "#d0d0d0", "Border color has not changed to Red for MD Last Name validation");
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_procedure']")).getCssValue("border-color"), "#953b39", "Border color has not changed to Red for Procedure validation");
	  Reporter.log("Book A Device for Scenario Blank Procedure Working properly");
	  driver.navigate().refresh();
	}
	
	if(Scenario.contentEquals("Blank Lname"))
	{
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_needed_at']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_needed_til']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_cases']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_procedure']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_md_name']", "xpath");
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_patient_id']", "xpath");
	  Thread.sleep(1000);
	  Assert.assertEquals(ReadingTextFromObjects.GetText(driver, "//div[@class='tooltip-inner']", "xpath"), "Do not enter Patient Name");
	  GarbageValueClear.Clear(driver, "//textarea[@id='events_booking_booking_data_attributes_message']", "xpath");
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_needed_at']", Begin_Time);
	  
	  GarbageValueClear.Clear(driver, "//input[@id='events_booking_booking_data_attributes_needed_til']", "xpath");
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_needed_til']", End_Time);
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_cases']", Cases);
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_procedure']", Procedure);
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_md_name']", Lname);
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//input[@id='events_booking_booking_data_attributes_patient_id']", "Test");
	  
	  SendingTestValueInTextBox.SendValue(driver, "xpath", "//textarea[@id='events_booking_booking_data_attributes_message']", "Test");
	  
	  ClickActionOnObjects.ClickObject(driver, "//button[@class='btn btn-large btn-primary']", "xpath");
	  Thread.sleep(8000);	  
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_needed_at']")).getCssValue("border-color"), "#d0d0d0", "Border color has not changed to Red for Begin Time validation");
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_needed_til']")).getCssValue("border-color"), "#d0d0d0", "Border color has not changed to Red for End Time validation");
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_cases']")).getCssValue("border-color"), "#d0d0d0", "Border color has not changed to Red for Cases validation");
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_md_name']")).getCssValue("border-color"), "#953b39", "Border color has not changed to Red for MD Last Name validation");
	  Assert.assertEquals(driver.findElement(By.xpath("//input[@id='events_booking_booking_data_attributes_procedure']")).getCssValue("border-color"), "#d0d0d0", "Border color has not changed to Red for Procedure validation");
	  Reporter.log("Book A Device for Scenario Blank MD's Last Name Working properly");
	  driver.navigate().refresh();
	}
	  
  }
  

  
	@DataProvider(name = "Account Settings") 
	 
	 public static Object[][] Account_Settings() throws BiffException, IOException{
	  
	   FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"\\src\\xls\\Cohealo.xls");
	   Workbook w = Workbook.getWorkbook(fi);
	   Sheet  s = w.getSheet("Account Settings");
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
	
	@DataProvider(name = "Book A Device") 
	 
	 public static Object[][] BookDevice() throws BiffException, IOException{
	  
	   FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"\\src\\xls\\Cohealo.xls");
	   Workbook w = Workbook.getWorkbook(fi);
	   Sheet  s = w.getSheet("BookADevice");
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
	
	@BeforeMethod
	public void beforemethod() throws InterruptedException, IOException
	{
		Properties CONFIG = Initialization.getconfig();
		WebDriver driver = Initialization.getDriver();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"//src//Config/config.properties");
		CONFIG.load(ip);
		driver.get(CONFIG.getProperty("URL"));
		driver.navigate().refresh();
		Thread.sleep(5000);
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
	


 }
  
  

