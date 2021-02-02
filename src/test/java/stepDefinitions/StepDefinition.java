package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	RequestSpecification res;
	Response response;
	

	@Given("Add place payload")
	public void add_place_payload() throws IOException {
		TestDataBuild testData = new TestDataBuild();
		
		// Rest Assured code start here
		reqSpec = new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri(getGlobalPropertyValue("baseURL"))
				.addQueryParam("key", "qaclick123").build();

		resSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();

		res = given().spec(requestSpecification()).body(testData.addPlacePayload());
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String string, String string2) {
		response = res.when().post("/maps/api/place/add/json").then().spec(resSpec).extract().response();
	}

	@Then("the API call is success with status code {string}")
	public void the_api_call_is_success_with_status_code(String string) {
		assertEquals(response.getStatusCode(), 200);
	}

	/*
	 * This step definitions can be used for both statements And "status" in
	 * response body is "OK" And "scope" in response body is "APP"
	 */

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		assertEquals(js.get(keyValue).toString(), expectedValue);
	}

}
