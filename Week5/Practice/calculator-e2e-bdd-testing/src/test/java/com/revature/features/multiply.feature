Feature: Multiply numbers

Scenario: Multiply numbers successfully (positive test)
	Given I am at the calculator page
	When I type in the number 12.0 into the left multiplication input
	When I type in the number 2.0 into the right multiplication input
	And I click the multiplication button
	Then I should see a multiplication result of 24.0
	
Scenario: Only left input missing (negative test)
	Given I am at the calculator page
	When I type in the number 4.3 into the right multiplication input
	And I click the multiplication button
	Then I should see an error message in the multiplication output area of "Left input is missing"
	
Scenario: Only right input missing (negative test)
	Given I am at the calculator page
	When I type in the number 8.0 into the left multiplication input
	And I click the multiplication button
	Then I should see an error message in the multiplication output area of "Right input is missing"
	
Scenario: Both input missing (negative test)
	Given I am at the calculator page
	And I click the multiplication button
	Then I should see an error message in the multiplication output area of "Both inputs are missing"