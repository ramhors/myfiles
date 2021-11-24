package com.revature;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionsClassDemo {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\webdrivers/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.amazon.com/");
		
		WebElement signInElement = driver.findElement(By.id("nav-link-accountList"));
		
		// If we want to, for example, hover over an element, we need to use the Actions class
		// The Actions class contains a lot of useful "actions" that can help us to simulate user activity (such as the above)
		// We could also, for example, simulate holding down the SHIFT key and typing in lowercase letters (which will of course capitalize them)
		
		Actions a = new Actions(driver);
		a.moveToElement(signInElement).build().perform(); // This will "move the mouse" to that element
		
		Thread.sleep(3000);
		
		/*
		 * Keyboard actions
		 */
		Actions a2 = new Actions(driver);
		a2.moveToElement(driver.findElement(By.id("twotabsearchtextbox"))).click()
			.keyDown(Keys.SHIFT).sendKeys("x").keyUp(Keys.SHIFT).sendKeys("box one").build().perform();
		
		Thread.sleep(10000);
		
		driver.quit();

	}

}
