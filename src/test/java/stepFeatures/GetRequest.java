package stepFeatures;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetRequest 
{
	// map for my headers
	Map<String, String> requestHeaders = new HashMap<String, String>();

	// objects
	public Response responseObj;
	public JsonPath jsonPathObj;

	// variable
	public String endPoint;

	SoftAssert softAssert = new SoftAssert();

	@Given("User adds Header Parameter {string} {string}")
	public void user_adds_header_parameter(String headerType, String headerValue) 
	{
		// Write code here that turns the phrase above into concrete actions
		requestHeaders.put(headerType, headerValue);
	}

	@When("User makes Get request {string}")
	public void user_makes_get_request(String endPoint) 
	{
		// Write code here that turns the phrase above into concrete actions
		this.endPoint = endPoint;

		this.responseObj = given()
								.headers(requestHeaders)
							.when().
								get(endPoint)
							.then()
								.extract()
								.response();
		this.responseObj.prettyPrint();
	}

	@Then("User validates status code {int}")
	public void user_validates_status_code(Integer statusCode) 
	{
		// Write code here that turns the phrase above into concrete actions
		int expStatusCode = Integer.valueOf(statusCode);
		int actStatusCode = responseObj.getStatusCode();

		this.softAssert.assertEquals(actStatusCode, expStatusCode);
		this.softAssert.assertAll();
	}
}
