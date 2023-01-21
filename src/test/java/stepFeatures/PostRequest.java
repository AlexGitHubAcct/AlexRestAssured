package stepFeatures;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostRequest 
{
	Map<String, String> requestHeaders = new HashMap<String, String>();
	
	//objects
	public Response responseObj;
	public JsonPath jsonPathObj;
	
	//variable
	public String requestBody;
	
	SoftAssert softAssert = new SoftAssert();
	
	@Given("User provides Header Parameter {string} {string}")
	public void user_provides_header_parameter(String headerType, String headerValue)
	{
	    // Write code here that turns the phrase above into concrete actions
		requestHeaders.put(headerType, headerValue);
	}

	@Given("User set the requestBody {string}")
	public void user_set_the_request_body(String requestBody) 
	{
	    // Write code here that turns the phrase above into concrete actions
	    this.requestBody = requestBody;
	}

	@Given("User makes POST request {string}")
	public void user_makes_post_request(String endpoint) 
	{
	    // Write code here that turns the phrase above into concrete actions
	    this.responseObj = given()
								.headers(requestHeaders)
								.body(requestBody)
							.when()
							  	.post(endpoint)
							.then()
							  	.extract()
							  	.response();
	    responseObj.prettyPrint();
	}

	@Then("User validates status code {string}")
	public void user_validates_status_code(String statusCode) 
	{
	    // Write code here that turns the phrase above into concrete actions
	    int expStatusCode = Integer.valueOf(statusCode);
	    int actStatusCode = responseObj.getStatusCode();
	    
	    this.softAssert.assertEquals(actStatusCode, expStatusCode);
	}

	@Then("User validates response body {string}")
	public void user_validates_response_body(String expMessage) 
	{
	    // Write code here that turns the phrase above into concrete actions
	    this.jsonPathObj = responseObj.jsonPath();
	    String actMessage = jsonPathObj.getString("message");
	    
	    this.softAssert.assertEquals(expMessage, actMessage);
	    
	    System.out.println();
	    
	    this.softAssert.assertAll();
	}
}
