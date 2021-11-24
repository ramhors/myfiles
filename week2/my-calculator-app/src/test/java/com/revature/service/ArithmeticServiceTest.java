package com.revature.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ArithmeticServiceTest {
	
	public ArithmeticService arithmeticService;
	
	@BeforeEach
	public void setUp() {
		this.arithmeticService = new ArithmeticService();
	}
	
	/******************************************************************
	 * When writing tests, we have the acronym AAA (Arrange, Act, Assert
	 *******************************************************************/
	@Test
	public void testCheckInputMethodWithNoBlankInputs() {
		/*************************************************************************
		 * Arrange
		 * Our arrange happens to be in the beforeEach method, where we create the AritmeticService object
		 * We are arranging the initial values that we need
		 * 
		 * Act
		 * Here we are acting on what we want to test. We are invoking the checkInputs method
		 *******************************************************************************/
		int result = this.arithmeticService.checkInputs("10.5", "100.34");
		/*****************************************************************************
		 * Assert
		 * We want to assert that we acted on giving us an appropriate output. The output SHOULD be 0 if the logic is correct
		 **********************************************************************/
		Assertions.assertEquals(0, result);
		/*********************************************************************************************
		 *JUnit 5 common assert methods (static method belonging to the assertion class):
		 * - assertArrayEquals(int[] expected, it[] actual) 
		 * - assertEquals(int expected, int actual)
		 * - assertTrue(boolean actual)
		 * - fail(): automatically fails the test
		 *********************************************************************************************/
	}
	@Test
	public void testCheckInputsMethodWithLeftBlankInputAndNonBlankRightInput() {
		/********************
		 * Arrange
		 * 
		 * Act
		 ******************/
		int result = this.arithmeticService.checkInputs(" ", "10.53");
		//Assert
		Assertions.assertEquals(1, result);
	}
	@Test
	public void testCheckInputMethoWithNonBlankLeftInputAndBlankRightInput() {
		/***********************
		 * Arrange
		 * 
		 * Act
		 **********************/
		int result = this.arithmeticService.checkInputs("10.3", " ");
		//Assert
		Assertions.assertEquals(2, result);		
	}
	@Test
	public void testCheckInputMethodWithBothInputBlank() {
		/*************************
		 * Arrange
		 * 
		 * Act
		 ************************/
		int result = this.arithmeticService.checkInputs(" ", " ");
		//Assert
		Assertions.assertEquals(3, result);
	}
}
