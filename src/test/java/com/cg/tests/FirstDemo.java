package com.cg.tests;


import static org.hamcrest.Matchers.*;

import org.codehaus.groovy.ast.builder.AstStringCompiler;
import org.testng.annotations.BeforeTest;


import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class FirstDemo extends BasicSetUp {

	@Test
	public static void getUsersByPageNo() {
		int pageno=2;
		Response r= given().queryParam("page",pageno)
				.when().get("users")
				.then().extract().response();
		Assert.assertEquals(r.getStatusCode(),200);
		JsonPath jp= new JsonPath(r.asString());

		Assert.assertEquals(jp.getInt("page"),pageno);
		Assert.assertEquals(jp.getInt("per_page"),6);
		Assert.assertEquals(jp.getInt("total"),12);
		Assert.assertEquals(jp.getList("data").size(),6);
		Assert.assertTrue(jp.getString("data[0].email").contains("@reqres.in"));
		Assert.assertEquals(jp.getString("data[1].email"),"lindsay.ferguson@reqres.in");
	}


	@Test
	public void testExample() {

		given().
		get("/users?page=2").
		then().
		statusCode(200).
		body("data[4].first_name",equalTo("George")).
		body("data.first_name",hasItems("George","Rachel"));
	}

    //postMethod
	@Test
	public static void createUser() {

		JSONObject jo = new JSONObject();
		jo.put("name", "Ragavi");
		jo.put("job", "Tester");

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(jo.toJSONString())
				      .when().post("/users")
				      .then().extract().response();

		JsonPath jpath = new JsonPath(response.asString());

		Assert.assertEquals(response.statusCode(), 201);
		Assert.assertNotNull(jpath.getInt("id"));
		Assert.assertEquals(jpath.getString("name"), "Ragavi");
		Assert.assertEquals(jpath.getString("job"), "Tester");
		Assert.assertNotNull(jpath.getString("createdAt"));
	}
}

