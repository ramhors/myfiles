Feature: Login

Scenario: Invalid password, valid username (negative test)
	Given I am at the login page
	When I type in a username of "week10"
	But I type in a password of "sdfdgsd"
	And I click the login button
	Then I should see a message of "Incorrect username and/or password"
	
Scenario: Invalid password, invalid username (negative test)

	Given I am at the login page
	When I type in a username of "lsdfsdf"
	And I type in a password of "sdfsd"
	And I click the login button
	Then I should see a message of "Incorrect username and/or password"