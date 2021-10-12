package day2;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;



import java.util.concurrent.TimeUnit;

public class GitHub {
	
	@BeforeTest
	public void beforeTest() {
		authentication=oauth2("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");
		baseURI="https://api.github.com/user/repos";
	}
  @Test(enabled=true)
  public void gettingAllRepositories() {
	  
	  given()
//	  		.auth()
//	  		.oauth2("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")
	  .when()
	  		.get()
	  .then()
	  		.log()
	  		.body()
	  		.statusCode(200);
}
  @Test(enabled=true)
  public void createRepositories() {
	  JSONObject data=new JSONObject();
	  data.put("name","RestAssuredCreation2");
	  data.put("description", "Im created by RestAssured tool");
	  data.put("homepage","https://github.com/Maryam5858");
	  given()
	  		//.auth()
//	  		.oauth2("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")
	  		.header("Content-Type","application/json")
	  		.body(data.toJSONString())
	  .when()
	  		.post()
	  .then()
	  		.log()
	  		.body()
	  		.statusCode(201)
	  		.time(Matchers.lessThan(1000L), TimeUnit.MILLISECONDS);
}
}
