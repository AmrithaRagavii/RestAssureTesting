package com.cg.tests;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import org.json.simple.JSONObject;
import org.testng.annotations.*;
import io.restassured.http.ContentType;

public class LocalAPITest {

	@BeforeTest
	public void setUp(){
		baseURI="http://localhost:3000";

	}
	@Test(priority = 0)
	public void getSubjects() {
		given().get("/Subjects").
		then().statusCode(200).log().all();
	}
	@Test(priority = 5)
	public void getUsers() {
		given().get("/users").
		then().statusCode(200).log().all();
	}

	@Test(priority = 1)
	public void post() {
		//		Map<String,Object> m=new HashMap<String,Object>();
		//		m.put("firstName","Amritha");
		//		m.put("lastName","Preethi");
		//		m.put("id",33);
		JSONObject request= new JSONObject();
		request.put("firstName","Puma");
		request.put("lastName","Ragu");

		given().contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(request.toJSONString()).
		post("/users").
		then().
		statusCode(201).log().all();
	}

	@Test(priority = 2)
	public void put() {

		JSONObject request= new JSONObject();
		request.put("lastName","Amala");

		given().contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(request.toJSONString()).
		when().
		put("/users/2").
		then().
		statusCode(200);
	}


	@Test(priority = 3)
	public void patch() {

		JSONObject request= new JSONObject();

		request.put("lastName","Roshan");	

		given().contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(request.toJSONString()).
		when().
		patch("/users/1").
		then().
		statusCode(200);
	}

	@Test(priority = 4)
	public void delete() {
		when().
		delete("/users/35").
		then().
		statusCode(404);
	}
}
