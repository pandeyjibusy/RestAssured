Feature: Validating place API's 

@AddPlace @Regression
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
	Given Add Place payload with "<name>" "<language>" "<address>"
	When User call "AddPlaceAPI" with "POST" http request
	Then the API is success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "getPlaceAPI"

Examples:
	|name 		| language 		| address 							|
	|Jyotssn	| HINDI				| Lucknow Road, Pune		|
#	|Pande		| German			| Pune Road, Lucknow		|

@DeletePlace @Regression
Scenario Outline: Verify if delete place functionality is working
Given deletePlaceAPI payload
When User call "deletePlaceAPI" with "POST" http request
Then the API is success with status code 200
And  "status" in response body is "OK"	