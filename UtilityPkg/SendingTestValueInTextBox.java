

package UtilityPkg;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;


public class SendingTestValueInTextBox {

	/**
	 * THIS WILL SEND VALUE IN TEXTBOX
	 * @throws InterruptedException 
	 */
	
	public static void SendValue(WebDriver driver  , String type, String locator , String value) throws InterruptedException {
		
	    

		if(type=="className")
		{
			
			driver.findElement(By.className(locator)).sendKeys(value);
			
		}
		if(type=="cssSelector")
		{
			
			driver.findElement(By.cssSelector(locator)).sendKeys(value);
			
		}
		if(type=="id")
		{
			
			driver.findElement(By.id(locator)).sendKeys(value);
			
		}
		if(type=="linkText")
		{
			
			driver.findElement(By.linkText(locator)).sendKeys(value);
			
		}
		if(type=="name")
		{
			
			driver.findElement(By.name(locator)).sendKeys(value);
			
		}
		if(type=="partialLinkText")
		{
			
			driver.findElement(By.partialLinkText(locator)).sendKeys(value);
			
		}
		if(type=="xpath")
		{
			
			driver.findElement(By.xpath(locator)).sendKeys(value);
			
		}
	   
	}

}
