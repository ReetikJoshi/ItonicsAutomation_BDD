Feature: Validate basic login scenario of Facebook 

Scenario: Validate user can login with valid credentials 
	Given Intialize The Driver 
	And Visit the link "https://www.facebook.com" 
	When User enters email "testing.automate.facebook@gmail.com" and password "reetik12345" 
	And User clicks on login button 
	Then User is redirected to homepage 
	And User checks if the full name is "Lucy Adams" and welcome text contains firstName "Lucy" 
	And Close the browser