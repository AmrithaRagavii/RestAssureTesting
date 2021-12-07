package com.cg.tests;


import static org.hamcrest.Matchers.*;


import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class FirstDemo {
	@Test
	public static void getUsersByID() {
		Response r= get("https://reqres.in/api/users?page=2");

		System.out.println(r.getStatusCode()); 
		System.out.println(r.getTime());
		System.out.println(r.getBody().asString());
		System.out.println(r.getStatusLine());
		System.out.println(r.contentType());

		int statusCode=r.getStatusCode();
		Assert.assertEquals(statusCode,200);
	}
	@Test
	public void testExample() {

		baseURI="https://reqres.in/api";

		given().
		get("/users?page=2").
		then().
		statusCode(200).
		body("data[4].first_name",equalTo("George")).
		body("data.first_name",hasItems("George","Rachel"));
	}
	
	
	@Test
	public void postID() {
		//      Map<String,Object> m=new HashMap<String,Object>();
		
//		m.put("name","morpheus");
//		m.put("job","leader");
//		
//		System.out.println(m);
		
		JSONObject req=new JSONObject();
		req.put("name","morpheus");
		req.put("job","leader");
		
		System.out.println(req.toJSONString());	
		baseURI="https://reqres.in/api";
		
		given().
		header("Content_Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(req.toJSONString()).
		when().
		post("/users").
		then().
		statusCode(201);
		
		
	}
}

