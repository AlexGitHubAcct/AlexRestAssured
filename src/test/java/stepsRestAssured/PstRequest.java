package stepsRestAssured;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;

public class PstRequest 
{
	public static void main(String args [])
	{
	//variables for our built-in restAssured methods in gherkin format
		String url = "https://hr-scrum.noortecktraining.com/employee/create";
		int expStatusCode = 201;
		String expMessage = "Created employee";
		
		String requestBody = 
				"{\r\n"
				+ "  \"firstName\": \"John\",\r\n"
				+ "  \"lastName\": \"Doe\",\r\n"
				+ "  \"email\": \"example@example.com\",\r\n"
				+ "  \"phoneNumber\": \"<000792000>\",\r\n"
				+ "  \"hireDate\": \"01/12/1999\",\r\n"
				+ "  \"jobId\": \"AD_VP\",\r\n"
				+ "  \"salary\": 2000,\r\n"
				+ "  \"comissionPct\": 0.1,\r\n"
				+ "  \"managerId\": 114,\r\n"
				+ "  \"departmentId\": 20\r\n"
				+ 
				"}";
		
	//creating response object
		Response responseObj; 
		
	//setting response object to the restAssured methods
		responseObj = given()
						.header("Content-Type", "application/json")
						.body(requestBody)
					  .when()
					  	.post(url)
					  .then()
					  	.extract()
					  	.response();
		responseObj.prettyPrint();
		
	//validating the status code
		int actualStatusCode = responseObj.getStatusCode();
		
	//validating the response body (to-do must create jsonPath object)
		JsonPath jsonPathObj = responseObj.jsonPath();
		
	//validating the response message
		String actualMessage = jsonPathObj.getString("message");
		
	//soft assertions coming from TestNG
		SoftAssert softObj = new SoftAssert();
		
	//comparing server response status code, message to what we expected them to be
		softObj.assertEquals(actualStatusCode, expStatusCode);
		softObj.assertEquals(actualMessage, expMessage);
		
		softObj.assertAll();
	}
}
