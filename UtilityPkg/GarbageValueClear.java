/**
 * 
 */
package UtilityPkg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class GarbageValueClear {

	/**
	 * THIS WILL CLEAR THE GARBAGE VALUE WHICH IN FORM OF TEXT.
	 */
	
	
	public static void Clear(WebDriver driver , String locater, String type) {
		
		 if(type=="className"){
			 driver.findElement(By.className(locater)).clear();
		 }
		 else if(type=="cssSelecter"){
			 driver.findElement(By.cssSelector(locater)).clear();
		 }
		 else if(type=="id"){
			 driver.findElement(By.id(locater)).clear();
		 }
		 else if(type=="linktext"){
			 driver.findElement(By.linkText(locater)).clear();
		 }
		 else if(type=="name"){
			 driver.findElement(By.name(locater)).clear();
		 }
		 else if(type=="partiallink"){
			 driver.findElement(By.partialLinkText(locater)).clear();
		 }
		 else if(type=="xpath"){
			 driver.findElement(By.xpath(locater)).clear();
		 }
		
		
	}

}
