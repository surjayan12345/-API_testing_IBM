package RestAssured.REST;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class RestAssured {
	


	

	@Test(enabled=true,priority=1)
	public void CreateUser()
	{
		io.restassured.RestAssured.baseURI = "https://petstore.swagger.io/v2";
		//RestAssured.baseURI = "https://petstore.swagger.io/v2";
		JSONObject obj = new JSONObject();
		
		obj.put("username", "SuDu");
		obj.put("firstName", "Surjayan");
		obj.put("lastName", "Dutta");
		obj.put("email", "Abc@Xyz");
		obj.put("password", "98765");
		obj.put("phone", "1234567890");
		obj.put("userStatus", 1);
		
		given()
			.contentType(ContentType.JSON).body(obj.toJSONString()).
		when()	
			.post("/user").
		then()
			.statusCode(200).log().all();
		
	}
	
	@Test(enabled=true,priority=2)
	public void update(){
		io.restassured.RestAssured.baseURI = "https://petstore.swagger.io/v2";
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
			.put("/user/AbcXyz").
		then()
			.statusCode(200).log().all();
	}
	
	@Test(enabled=true,priority=3)
	public void login(){
		io.restassured.RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		given()
			.queryParam("username", "AbcXyz123").queryParam("password", "12345").get("/user/login").
		then()
			.statusCode(200).log().all();
	}
	
	@Test(enabled=true,priority=4)
	public void logout(){
		io.restassured.RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		given()
			.get("/user/logout").
		then()
			.statusCode(200).log().all();
	}
	
	@Test(enabled=true,priority=5)
	public void delete(){
		io.restassured.RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		given()
			.contentType(ContentType.JSON).
		when()
			.delete("/user/AbcXyz123").
		then()
			.statusCode(200).log().all();
	}

}
