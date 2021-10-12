package day2;

import org.testng.Assert;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.ExtractableResponse;

public class NegativeContactListType {
	
	@Test(enabled=false,description="Getting all contact list")
	  public void recordNotFound() {
		  
		  given()
		  .when()
		  		.get("http://3.13.86.142:3000/contacts/5")
		  .then()
		  		.log()
		  		.body()
		  		.statusCode(404);
	  }
	
	 @Test(enabled=false,description="Adding specific contact with missing details")
	  public void addingContactMissingParameter() {
		  JSONObject details=new JSONObject();
		  JSONObject loc=new JSONObject();
		  JSONObject emp=new JSONObject();
		  
		  loc.put("city", "Mumbai");
		  loc.put("country", "India");
		  emp.put("jobTitle", "QA");
		  emp.put("company", "LTI");
		  details.put("firstName", "");
		  details.put("lastName", "JK");
		  details.put("email", "Jeon@lti.com");

		  details.put("employer", emp);
		  details.put("location", loc);
		  
		  String error=given()
		  		.header("Content-Type","application/json")
		  		.body(details.toJSONString())
		  
		  .when()
		  		.post("http://3.13.86.142:3000/contacts")
		  		
		  .then()
		  		.log()
		  		.body()
		  		.statusCode(400)
		  		.extract()
		  		.path("err");
		  Assert.assertTrue(error.contains("firstName: First Name is required"));
	  }
	 
	 @Test(enabled=false,description="Adding contact with too many characters")
	  public void addingContactBigSize() {
		  JSONObject details=new JSONObject();
		  JSONObject loc=new JSONObject();
		  JSONObject emp=new JSONObject();
		  
		  loc.put("city", "MumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbai");
		  loc.put("country", "India");
		  emp.put("jobTitle", "QA");
		  emp.put("company", "LTI");
		  details.put("firstName", "Jeon");
		  details.put("lastName", "JK");
		  details.put("email", "Jeon@lti.com");

		  details.put("employer", emp);
		  details.put("location", loc);
		  
		  String error=given()
		  		.header("Content-Type","application/json")
		  		.body(details.toJSONString())
		  
		  .when()
		  		.post("http://3.13.86.142:3000/contacts")
		  		
		  .then()
		  		.log()
		  		.body()
		  		.statusCode(400)
		  		.extract()
		  		.path("err");
		  Assert.assertTrue(error.contains("is longer than the maximum allowed length (30)"));
	  }
	 @Test(enabled=true,description="Adding contact with Invalid Character")
	  public void addingContactInvalidCharacter() {
		  JSONObject details=new JSONObject();
		  JSONObject loc=new JSONObject();
		  JSONObject emp=new JSONObject();
		  
		  loc.put("city", "Mumbai");
		  loc.put("country", "India");
		  emp.put("jobTitle", "QA");
		  emp.put("company", "LTI");
		  details.put("firstName", "Jeon234");
		  details.put("lastName", "JK");
		  details.put("email", "Jeon@lti.com");

		  details.put("employer", emp);
		  details.put("location", loc);
		  
		  String error=given()
		  		.header("Content-Type","application/json")
		  		.body(details.toJSONString())
		  
		  .when()
		  		.post("http://3.13.86.142:3000/contacts")
		  		
		  .then()
		  		.log()
		  		.body()
		  		.statusCode(400)
		  		.extract()
		  		.path("err");
		  Assert.assertTrue(error.contains("firstName: Validator failed for path `firstName` with value `Jeon234`"));
	  }
	 @Test(enabled=true,description="Adding contact with improper format")
	  public void addingContactImproperFormat() {
		  JSONObject details=new JSONObject();
		  JSONObject loc=new JSONObject();
		  JSONObject emp=new JSONObject();
		  
		  loc.put("city", "Mumbai");
		  loc.put("country", "India");
		  emp.put("jobTitle", "QA");
		  emp.put("company", "LTI");
		  details.put("firstName", "Tae");
		  details.put("lastName", "JK");
		  details.put("email", "Jeonlti.com");

		  details.put("employer", emp);
		  details.put("location", loc);
		  
		  String error=given()
		  		.header("Content-Type","application/json")
		  		.body(details.toJSONString())
		  
		  .when()
		  		.post("http://3.13.86.142:3000/contacts")
		  		
		  .then()
		  		.log()
		  		.body()
		  		.statusCode(400)
		  		.extract()
		  		.path("err");
		 Assert.assertTrue(error.contains("email: Validator failed for path `email` with value `Jeonlti.com`"));
		 
	  }
}
