package com.cg.tests;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import org.apache.commons.io.IOUtils;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static io.restassured.RestAssured.baseURI;

public class SoapXMLValidation {
	@Test
	public void soapXMLValidation() throws IOException {
		
		File file= new File("./Soapfile/add.xml");
		
		if(file.exists())
			System.out.println("  >>File Exists");
		
		FileInputStream fis=new FileInputStream(file);
		
		String request= IOUtils.toString(fis, "UTF-8");
		baseURI="http://www.dneonline.com";
		
		given().contentType("text/xml").
		accept(ContentType.XML).
		body(request).
		when().
		 post("/calculator.asmx").
		 then().
		 statusCode(500).log().all();
	
	}

}
