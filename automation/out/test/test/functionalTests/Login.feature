Feature: Application Login 

Scenario Outline: Home Page login 
	Given User is on check Home Page 
	When User Logins with multiple values <UserName> and <PassWord> 
	Then Homepage is displayed 
	
	Examples: 
		|UserName | PassWord |
		|Riffat   | Shahzad  |
		|HAdi   | Shahzad  |
	