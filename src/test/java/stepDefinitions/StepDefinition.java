package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
	RequestSpecification reqSpec;
	public static ResponseSpecification resSpec;
	RequestSpecification res;
	Response response;
	TestDataBuild testData = new TestDataBuild();
	static String place_id;
	

	@Given("Add place payload")
	public void add_place_payload() throws IOException {
		res = given().spec(requestSpecification()).body(testData.addPlacePayload());
	}
	
	@Given("Add place payload with {string}, {string}, {string}")
	public void add_place_payload_with(String Address, String Language, String Name) throws IOException {
		res = given().spec(requestSpecification()).body(testData.addPlacePayloadDataDriven(Address,Language,Name));
	}
	
	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
		res = given().spec(requestSpecification()).body(testData.deletePlacePayload(place_id));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) throws FileNotFoundException {
		APIResources objResourceAPI=APIResources.valueOf(resource);
		if(method.equalsIgnoreCase("POST")) {
			response = res.when().post(objResourceAPI.getResource()).then().spec(responseSpecification()).extract().response();
		}
		else if(method.equalsIgnoreCase("GET")) {
			response = res.when().get(objResourceAPI.getResource()).then().spec(responseSpecification()).extract().response();
		}
		else if(method.equalsIgnoreCase("DELETE")) {
			response = res.when().delete(objResourceAPI.getResource()).then().spec(responseSpecification()).extract().response();
		}
		else if(method.equalsIgnoreCase("PUT")) {
			response = res.when().put(objResourceAPI.getResource()).then().spec(responseSpecification()).extract().response();
		}
	}

	@Then("the API call is success with status code {string}")
	public void the_api_call_is_success_with_status_code(String string) {
		assertEquals(response.getStatusCode(), 200);
	}


	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		assertEquals(getJsonPath(response,keyValue), expectedValue);
	}
	
	@Then("verify place_ID created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		place_id = getJsonPath(response,"place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);	
		user_calls_with_http_request(resource, "GET");
		String actualName = getJsonPath(response,"name");
		assertEquals(actualName,expectedName);
	}

}
