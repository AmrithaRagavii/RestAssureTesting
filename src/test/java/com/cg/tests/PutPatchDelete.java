package com.cg.tests;

import static io.restassured.RestAssured.*;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PutPatchDelete extends BasicSetUp {

		
	@Test(priority = 0)
	public static void updateForPut() {

		JSONObject putRequest = new JSONObject();

		putRequest.put("name", "Amritha");
		putRequest.put("job", "Baker");

		Response rep = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(putRequest.toJSONString()).
	               	   when().put("/users/2").
		               then().extract().response();

		JsonPath jsonPath = new JsonPath(rep.asString());

		Assert.assertEquals(rep.statusCode(), 200);
		Assert.assertEquals(jsonPath.getString("name"), "Amritha");
		Assert.assertEquals(jsonPath.getString("job"), "Baker");
		Assert.assertNotNull(jsonPath.getString("updatedAt"));

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
