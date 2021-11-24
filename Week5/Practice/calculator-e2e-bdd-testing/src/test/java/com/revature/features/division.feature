Feature: Divide numbers

Scenario: Dividing numbers successfully (positive test)
	Given I am at the calculator page
	When I type in the number 4.0 into the left division input
	When I type in the number 2.0 into the right division input
	And I click the division button
	Then I should see a division result of 2.0
	
Scenario: Only left input missing (negative test)
	Given I am at the calculator page
	When I type in the number 10.0 into the right division input
	And I click the division button
	Then I should see an error message in the division output area of "Left input is missing"
	
Scenario: Only right input missing (negative test)
	Given I am at the calculator page
	When I type in the number 6.0 into the left division input
	And I click the division button
	Then I should see an error message in the division output area of "Right input is missing"
	
Scenario: Both input missing (negative test)
	Given I am at the calculator page
	And I click the division button
	Then I should see an error message in the division output area of "Both inputs are missing"