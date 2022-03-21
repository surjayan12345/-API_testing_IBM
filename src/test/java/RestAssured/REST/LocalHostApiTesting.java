package RestAssured.REST;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LocalHostApiTesting {
	


	

	@Test(enabled=true,priority=1)
	public void CreateUser(ITestContext val)
	{
		io.restassured.RestAssured.baseURI = "http://localhost:3000";
		//RestAssured.baseURI = "https://petstore.swagger.io/v2";
		JSONObject obj = new JSONObject();
		
		obj.put("username", "SuDu");
		obj.put("firstName", "Surjayan");
		obj.put("lastName", "Dutta");
		obj.put("email", "Abc@Xyz");
		obj.put("password", "98765");
		obj.put("phone", "1234567890");
		obj.put("userStatus", 1);
		
	Response resp = 	given()
			.contentType(ContentType.JSON).body(obj.toJSONString()).
		when()	
			.post("/userdetails").
		then()
			.statusCode(201).log().all().extract().response();
		
	String id = resp.jsonPath().getString("id");
	val.setAttribute("id", id);
	
	}
	
	@Test(enabled=true,priority=2)
	public void update(ITestContext val){
		io.restassured.RestAssured.baseURI = "http://localhost:3000";
		JSONObject obj = new JSONObject();
		
		obj.put("username", "AbcXyz123");
		obj.put("firstName", "Abc");
		obj.put("lastName", "Xyz");
		obj.put("email", "Abc@Xyz");
		obj.put("password", "12345");
		obj.put("phone", "9876543210");
		obj.put("userStatus", 1);
		
		given()
			.contentType(ContentType.JSON).body(obj.toJSONString()).
		when()
			.put("/userdetails/" + val.getAttribute("id")).
		then()
			.statusCode(200).log().all();
	}
	
	
	
	@Test(enabled=true,priority=4)
	public void getafteredit(ITestContext val){
		io.restassured.RestAssured.baseURI = "http://localhost:3000";
		
		given()
			.get("/userdetails/" + val.getAttribute("id")).
		then()
			.statusCode(200).log().all();
	}
	
	@Test(enabled=true,priority=5)
	public void delete(ITestContext val){
		io.restassured.RestAssured.baseURI = "http://localhost:3000";
		
		
		given()
			.contentType(ContentType.JSON).
		when()
			.delete("/userdetails/" + val.getAttribute("id")).
		then()
			.statusCode(200).log().all();
	}

}
