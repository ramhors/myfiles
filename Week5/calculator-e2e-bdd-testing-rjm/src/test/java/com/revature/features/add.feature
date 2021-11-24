Feature: add numbers

Scenario: Adding numbers successfully (positive test)
	Given I am at the calculator page
	When I type in the number 10 into the upper add input
	And I type in the number 20 into the lower add input
	Then I should see an addition result of 30.0
