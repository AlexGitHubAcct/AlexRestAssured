package stepsRestAssured;

import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class PRequest 
{
	public static void main(String args [])
	{
		String url = "https://hr-scrum.noortecktraining.com/employee/create";
		String expMessage = "Created employee";
		int expStatusCode = 201;
		
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
		
		Response responseObj;
		
		responseObj = given()
							.header("Content-Type", "application/json")
							.body(requestBody)
						.when()
							.post(url)
						.then()
							.extract()
							.response();
		responseObj.prettyPrint();			
		
		int actualStatusCode = responseObj.getStatusCode();
		
		JsonPath jsonPathObj = responseObj.jsonPath();
		
		String actualMessage = jsonPathObj.getString("message");
		
		SoftAssert softObj = new SoftAssert();
		
		softObj.assertEquals(expStatusCode, actualStatusCode);
		softObj.assertEquals(expMessage, actualMessage);
		
		softObj.assertAll();
	}
}
