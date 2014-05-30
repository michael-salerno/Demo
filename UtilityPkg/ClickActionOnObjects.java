package UtilityPkg;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/* THIS CLASS WILL PERFORME THE CLICK ACTION LIKE 
 * 1. BUTTON CLICK ACTION.
 * 2. LINK CLICK ACTION.
 * 3. CHECKBOX CHECK.
 * 4. ETC ..
*/
public class ClickActionOnObjects {
	

	public static void ClickObject(WebDriver driver,String value, String type) {
		 if(type=="className"){
			 driver.findElement(By.className(value)).click();
		 }
		 else if(type=="cssSelecter"){
			 driver.findElement(By.cssSelector(value)).click();
		 }
		 else if(type=="id"){
			 driver.findElement(By.id(value)).click();
		 }
		 else if(type=="linktext"){
			 driver.findElement(By.linkText(value)).click();
		 }
		 else if(type=="name"){
			 driver.findElement(By.name(value)).click();
		 }
		 else if(type=="partiallink"){
			 driver.findElement(By.partialLinkText(value)).click();
		 }
		 else if(type=="xpath"){
			 driver.findElement(By.xpath(value)).click();
		 }
		 else if(type=="xpath"){
			 driver.findElement(By.tagName(value)).click();
		 }
	}
	
	public static void ClickByJavascript(WebDriver driver,String value) throws InterruptedException
	{
		  WebElement element = driver.findElement(By.xpath(value));
		    /*((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", element);*/
		    JavascriptExecutor executor = (JavascriptExecutor)driver;
		    executor.executeScript("arguments[0].click();", element);
	}

}
