package com.cg.tests;

import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PutPatchDelete {

		@BeforeTest
		public void setUp(){
			baseURI="https://reqres.in/api";
		}
	@Test(priority = 0)
	public void putID() {


		JSONObject req=new JSONObject();
		req.put("name","Ragavi");
		req.put("job","Employee");

		System.out.println(req.toJSONString());	
		//baseURI="https://reqres.in/api";

		given().
		header("Content_Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(req.toJSONString()).
		when().
		put("/users/2").
		then().
		statusCode(200);
	}
	@Test(priority = 1)
	public void patchID() {

		JSONObject req=new JSONObject();
		req.put("name","Ragavi");
		req.put("job","EmployeeIn CG");

		System.out.println(req.toJSONString());	
		//baseURI="https://reqres.in";

		given().
		header("Content_Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(req.toJSONString()).
		when().
		patch("/api/users/2").
		then().
		statusCode(200).
		log().all();
	}

	@Test(priority = 2)
	public void deleteID() {

		//baseURI="https://reqres.in/api";

		when().
		delete("/api/users/2").
		then().
		statusCode(204).
		log().all();
	}




}
