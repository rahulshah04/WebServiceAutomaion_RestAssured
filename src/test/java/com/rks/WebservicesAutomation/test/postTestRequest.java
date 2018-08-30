package com.rks.WebservicesAutomation.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class postTestRequest {
	@Test
	public void RegistrationSuccessful()
	{		
		RestAssured.baseURI ="http://restapi.demoqa.com/customer";
		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "rtgsdr"); // Cast
		requestParams.put("LastName", "Shah");
		requestParams.put("UserName", "rtgsdrShah04");
		requestParams.put("Password", "password1");
		requestParams.put("Email", "rtgsdrShah0492@gmail.com");
		
		request.body(requestParams.toJSONString());
		System.out.println(request.body(requestParams.toJSONString()));
		Response response = request.post("/register");
		
		System.out.println("Response body is: "+response.body().asString());
		
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200);
		String successCode = response.jsonPath().get("SuccessCode");
		System.out.println(successCode);
		Assert.assertEquals("OPERATION_SUCCESS", successCode, "Correct Success code was returned");
		
//		RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";
//		RequestSpecification requestAuth = RestAssured.given();
//	 
//		Response responseAuth = requestAuth.get();
//		System.out.println("Status code: " + responseAuth.getStatusCode());
//		System.out.println("Status message " + responseAuth.body().asString());
	}

}
