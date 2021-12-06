package com.cg.tests;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;


public class LocalAPITest {
	
	@BeforeTest
	public void setUp(){
		baseURI="http://localhost:3000/";
		
	}
	@Test(priority = 0)
	public void get() {

	
		given().get("/Subjects").
		then().statusCode(200).log().all();

	}

	@Test(priority = 1)
	public void post() {



		JSONObject request= new JSONObject();

		request.put("firstName","Ragavi");
		request.put("lastName", "Priya");
		request.put("subjectid", 1);

		

		given().contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(request.toJSONString()).
		post("/users").
		then().
		statusCode(201);

	}


	@Test(priority = 2)
	public void put() {



		JSONObject request= new JSONObject();

		request.put("firstName","Ragavi");
		request.put("lastName", "Priya");
		request.put("subjectid", 1);

	
		given().contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(request.toJSONString()).
		when().
		put("/users").
		then().
		statusCode(404);
	}
	@Test(priority = 3)
	public void patch() {

		JSONObject request= new JSONObject();

		request.put("firstName","Amritha");
		request.put("lastName", "Ragavi");
		request.put("subjectid", 1);

		

		given().contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(request.toJSONString()).
		when().
		patch("/users").
		then().
		statusCode(404);
	}
	
	@Test(priority = 4)
	public void delete() {


		when().
		delete("/users").
		then().
		statusCode(404);
	}


}
