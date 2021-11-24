Feature: Subtract numbers

Scenario: Subtracting numbers successfully (positive test)
	Given I am at the calculator page
	When I type in the number 10.5 into the left subtract input
	When I type in the number 5.5 into the right subtract input
	And I click the subtract button
	Then I should see an subtraction result of 5.0

Scenario: Only left input missing (negative test)
	Given I am at the calculator page
	When I type in the number 34.3 into the right subtract input
	And I click the subtract button
	Then I should see an error message in the subtraction output area of "Left input is missing"
	
Scenario: Only right input missing (negative test)
	Given I am at the calculator page
	When I type in the number 12.12 into the left subtract input
	And I click the subtract button
	Then I should see an error message in the subtraction output area of "Right input is missing"
	
Scenario: Both input missing (negative test)
	Given I am at the calculator page
	And I click the subtract button
	Then I should see an error message in the subtraction output area of "Both inputs are missing"