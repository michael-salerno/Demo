package UtilityPkg;

import org.openqa.selenium.By;


import org.openqa.selenium.WebDriver;

//Function to check particular WebElement present or not
public class Element_Exist {
	  public static boolean existsElement(String xpath1 , WebDriver driver) throws InterruptedException {
		    try 
		    {
		    	driver.findElement(By.xpath(xpath1));
		    } catch (Exception e) {
		        return false;
		    }

		    return true;
		}
	  
	  public static boolean isAlertPresent(WebDriver driver) 
	  { 
	      try 
	      { 
	          driver.switchTo().alert(); 
	          return true; 
	      }    
	      catch (Exception Ex) 
	      { 
	          return false; 
	      }  
	  } 
	  
	  public static boolean elementvisible(String xpath1 , WebDriver driver)
	  {
		    try 
		    {
		    	driver.findElement(By.xpath(xpath1)).isDisplayed();
		    } catch (Exception e) {
		        return false;
		    }
		    return true;
	  }
}
