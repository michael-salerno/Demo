package UtilityPkg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ReadingTextFromObjects {

	/**
	 * THIS CLASS WILL READ THE VALUE OF THE OBJECTS
	 */
	
	static String string;
	
	public static String GetText(WebDriver driver , String locater , String type) {
		
		 
		 
		 if(type=="className"){
			
			string = driver.findElement(By.className(locater)).getText();
		 }
		 if(type=="cssSelecter"){
			  string = driver.findElement(By.cssSelector(locater)).getText();
		 }
		  if(type=="id"){
			  string = driver.findElement(By.id(locater)).getText();
		 }
		  if(type=="linktext"){
			 string = driver.findElement(By.linkText(locater)).getText();
		 }
		  if(type=="name"){
			 string = driver.findElement(By.name(locater)).getText();
		 }
		  if(type=="partiallink"){
			 string = driver.findElement(By.partialLinkText(locater)).getText();
		 }
		 if(type=="xpath"){
			  string = driver.findElement(By.xpath(locater)).getText();
		 }
		 if(type=="tag"){
			  string = driver.findElement(By.tagName(locater)).getText();
		 }
		
		return string;
	}

}
