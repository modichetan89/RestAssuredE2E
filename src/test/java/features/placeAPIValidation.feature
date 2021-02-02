Feature: Validating Place API's

Scenario: Verify if place is being successfully added using AddPlaceAPI
Given Add place payload
When user calls "AddPlaceAPI" with "POST" http request
Then the API call is success with status code "200"
And "status" in response body is "OK"
And "scope" in response body is "APP"

Scenario Outline: Verify AddPlaceAPI with multiple data
Given Add place payload with "<Address>", "<Language>", "<Name>"
When user calls "AddPlaceAPI" with "POST" http request
Then the API call is success with status code "200"
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_ID created maps to "<Name>" using "GetPlaceAPI"
Examples:
|Address|Language|Name|
|AA|BB|CC|
|DD|EE|FF|

Scenario: Verify if place is being successfully deleted using DeletePlaceAPI
Given Delete place payload
When user calls "DeletePlaceAPI" with "DELETE" http request
Then the API call is success with status code "200"
And "status" in response body is "OK"
