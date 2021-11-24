package com.revature.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddNumbersTest {

	private WebDriver driver;
	
	@Given("I am at the calculator page")
	public void i_am_at_the_calculator_page() {
	    System.setProperty("webdriver.chrome.driver","C:\\webdriver/chromedriver.exe");
	    
	    this.driver = new ChromeDriver();
	    
	    driver.get("http://localhost8080/");
	}

	@When("I type in the number {int} into the upper add input")
	public void i_type_in_the_number_into_the_upper_add_input(Integer int1) {
	    
	}

	@When("I type in the number {int} into the lower add input")
	public void i_type_in_the_number_into_the_lower_add_input(Integer int1) {
	    
	}

	@Then("I should see an addition result of {double}")
	public void i_should_see_an_addition_result_of(Double double1) {
	    
	}
}
