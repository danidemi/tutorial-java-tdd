Scenario: when a user increases a counter, its value is increased by 1

Given a counter
And the counter has any integral value
When the user increases the counter
Then the value of the counter must be 2 greater than previous value