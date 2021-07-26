Feature: Select random department from the dropdown on each run 

Scenario: Validate user can select random department from dropdown 
	Given Initialize the driver 
	And The user visits "https://www.amazon.com" 
	When User generates random number and selects dropdown option based on it
	Then validate random department is selected 
	And Quit the browser