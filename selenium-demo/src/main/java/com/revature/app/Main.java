package com.revature.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:/Users/rjm20/driver/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080");

		/****************************************************************************
		 * Locate the 2 input elements and button for adding numbers And location the
		 * Button
		 ****************************************************************************/
		WebElement addInput1 = driver.findElement(By.id("addId1"));
		WebElement addInput2 = driver.findElement(By.id("addId2"));
		WebElement addButton = driver.findElement(By.id("addButton"));

		addInput1.sendKeys("10.5");
		addInput2.sendKeys("15.3");
		addButton.click();

		driver.switchTo().frame("add-result");
		WebElement addOutput = driver.findElement(By.tagName("pre"));			
		
	//	System.out.print("RESULT: ");
		System.out.println("\t10.5 + 15.3 = " + addOutput.getText());

		driver.switchTo().defaultContent();
		// to pause
		Thread.sleep(1000);

		/*********************************************
		 * Locating elements for subtract button And locating the button
		 *********************************************/
		WebElement subInput1 = driver.findElement(By.id("subId1"));
		WebElement subInput2 = driver.findElement(By.id("subId2"));
		WebElement subButton = driver.findElement(By.id("subButton"));

		subInput1.sendKeys("30");
		subInput2.sendKeys("20");
		subButton.click();

		driver.switchTo().frame("sub-result");

		WebElement subOutput = driver.findElement(By.tagName("pre"));
		System.out.println("\t30 - 20 =  " + subOutput.getText());
		

		driver.switchTo().defaultContent();
		// to pause
		Thread.sleep(1000);

		/***************************************
		 * Locating elements for multiplication And locating the button
		 ***************************************/
		WebElement mltInput1 = driver.findElement(By.id("mltId1"));
		WebElement mltInput2 = driver.findElement(By.id("mltId2"));
		WebElement mltButton = driver.findElement(By.id("mltButtonId"));

		mltInput1.sendKeys("23.2");
		mltInput2.sendKeys("11.7");
		mltButton.click();

		driver.switchTo().frame("mlt-result");

		WebElement mltOutput = driver.findElement(By.tagName("pre"));
		System.out.println("\t23.2 * 11.7 = " + mltOutput.getText());

		driver.switchTo().defaultContent();
		// to pause
		Thread.sleep(1000);

		/**********************************
		 * Locating Element for Division And also locating the button
		 **********************************/

		WebElement dvInput1 = driver.findElement(By.id("divId1"));
		WebElement divInput2 = driver.findElement(By.id("divId2"));
		WebElement dvButton = driver.findElement(By.id("divButtonId"));

		dvInput1.sendKeys("23.2");
		divInput2.sendKeys("11.7");
		dvButton.click();

		driver.switchTo().frame("dv-result");

		WebElement divOutput = driver.findElement(By.tagName("pre"));
		System.out.println("\t23.2 / 11.7 = " + divOutput.getText());

		driver.switchTo().defaultContent();
		
		
		// to pause
		Thread.sleep(1000);
		
		// Close the window	
		
		driver.quit();			
		
	

	}

}