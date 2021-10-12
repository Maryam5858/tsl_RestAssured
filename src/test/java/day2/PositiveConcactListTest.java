package day2;
import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class PositiveConcactListTest {
 
	String id;
	@Test(enabled=false,description="Getting all contact list")
  public void getContactListInfo() {
	  
	  given()
	  .when()
	  		.get("http://3.13.86.142:3000/contacts")
	  .then()
	  		.log()
	  		.body()
	  		.statusCode(200);
  }
  
  @Test(enabled=false,description="Getting specific contact")
  public void getSpecificContact() {
	  
	  given()
	  .when()
	  		.get("http://3.13.86.142:3000/contacts/615fa6d8f2967f0ec893ae99")
	  .then()
	  		.log()
	  		.body()
	  		.statusCode(200);
  }
  @Test(enabled=false,description="Getting specific contact")
  public void getSpecificContact2() {
	  
	  Response res=given()
	  .when()
	  		.get("http://3.13.86.142:3000/contacts/615fa6d8f2967f0ec893ae99");
	  	System.out.println(res.getTime());	
	  		
	  res.then()
	  		.log()
	  		.body()
	  		.statusCode(200);
  }
  
  @Test(enabled=true,description="Adding specific contact")
  public void addingContact() {
	  JSONObject details=new JSONObject();
	  JSONObject loc=new JSONObject();
	  JSONObject emp=new JSONObject();
	  
	  loc.put("city", "Mumbai");
	  loc.put("country", "India");
	  emp.put("jobTitle", "QA");
	  emp.put("company", "LTI");
	  details.put("firstName", "Jeon");
	  details.put("lastName", "JK");
	  details.put("email", "Jeon@lti.com");

	  details.put("employer", emp);
	  details.put("location", loc);
	  
	  ExtractableResponse ex=given()
	  		.header("Content-Type","application/json")
	  		.body(details.toJSONString())
	  
	  .when()
	  		.post("http://3.13.86.142:3000/contacts")
	  		
	  .then()
	  		.log()
	  		.body()
	  		.statusCode(200)
	  		.extract();
	  		//.path("_id");
	  id=ex.path("_id");		
	  System.out.println(ex.path("_id"));
	  System.out.println(ex.path("firstName"));
	  System.out.println(ex.path("lastName"));
	  System.out.println(ex.path("location.city"));
  }
  
  @Test(enabled=true,dependsOnMethods="addingContact",description="Updating specific contact")
  public void updatingContact() {
	  JSONObject details=new JSONObject();
	  JSONObject loc=new JSONObject();
	  JSONObject emp=new JSONObject();
	  
	  loc.put("city", "Mumbai");
	  loc.put("country", "India");
	  emp.put("jobTitle", "QA");
	  emp.put("company", "LTI");
	  details.put("firstName", "Tae");
	  details.put("lastName", "Stewart");
	  details.put("email", "taestw@lti.com");

	  details.put("employer", emp);
	  details.put("location", loc);
	  
	  given()
	  		.header("Content-Type","application/json")
	  		.body(details.toJSONString())
	  
	  .when()
	  		.put("http://3.13.86.142:3000/contacts/"+id)
	  		
	  .then()
	  		.log()
	  		.body()
	  		.statusCode(204);
  
  } 
  
  @Test(enabled=true,dependsOnMethods="updatingContact",description="Getting specific contact")
  public void getSpecificContact3() {
	  
	  given()
	  .when()
	  		.get("http://3.13.86.142:3000/contacts/"+id)
	  .then()
	  		.log()
	  		.body()
	  		.statusCode(200);
  }
  @Test(enabled=false,dependsOnMethods="getSpecificContact3",description="deleting specific contact")
  public void deletingContact() {
	  
	  given()
	  .when()
	  		.delete("http://3.13.86.142:3000/contacts/"+id)
	  .then()
	  		.log()
	  		.body()
	  		.statusCode(204);
  }
  
  @Test(enabled=false,dependsOnMethods="deletingContact",description="Getting specific contact for deleted content")
  public void getSpecificContact4() {
	  
	  given()
	  .when()
	  		.get("http://3.13.86.142:3000/contacts/"+id)
	  .then()
	  		.log()
	  		.body()
	  		.statusCode(404);
  }
}
