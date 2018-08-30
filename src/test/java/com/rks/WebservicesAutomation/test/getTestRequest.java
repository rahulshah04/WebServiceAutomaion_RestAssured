package com.rks.WebservicesAutomation.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class getTestRequest {
	@Test
	public void getWeatherDetails() {
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();

		System.out.println(httpRequest);

		// Make a request to the server by specifying the method Type and the method URL.
		// This will return the Response from the server. Store the response in a variable.
		Response response = httpRequest.request(Method.GET, "/Pune");
		System.out.println("response body =>" +response);
		// Now let us print the body of the message to see what response
		// we have recieved from the server
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);

		// Get the status code from the Response. In case of 
		// a successfull interaction with the web service, we
		// should get a status code of 200.
		int statusCode = response.getStatusCode();
		System.out.println("Status Code: "+statusCode);

		Assert.assertEquals(statusCode, 200, "Correct status code is returned");

		// Get the status line from the Response and store it in a variable called statusLine
		String statusLine = response.getStatusLine();
		System.out.println("Status Line: "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK", "Correct status Line is returned");

		// Reader header of a give name. In this line we will get
		// Header named Content-Type
		String contentType = response.header("Content-Type");
		System.out.println("Content-Type value: " + contentType);
		Assert.assertEquals(contentType, "application/json");

		// Reader header of a give name. In this line we will get
		// Header named Server
		String serverType =  response.header("Server");
		System.out.println("Server value: " + serverType);
		Assert.assertEquals(serverType, "nginx/1.14.0");

		// Reader header of a give name. In this line we will get
		// Header named Content-Encoding
		String contentEncoding  = response.header("Content-Encoding");
		System.out.println("Content-Encoding: " + contentEncoding );
		Assert.assertEquals(contentEncoding, "gzip");

		//Get all the headers. Return value is of type Headers.
		// Headers class implements Iterable interface, hence we
		// can apply an advance for loop to go through all Headers
		// as shown in the code below
		Headers allHeaders = response.headers();

		// Iterate over all the Headers
		for(Header header : allHeaders)
		{
			System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
		}
		
		
		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
	 
		// Then simply query the JsonPath object to get a String value of the node
		// specified by JsonPath: City (Note: You should not put $. in the Java code)
		String city = jsonPathEvaluator.get("City");
	 
		// Let us print the city variable to see what we got
		System.out.println("City received from Response: " + city);
	 
		// Validate the response
		Assert.assertEquals(city, "Pune", "Correct city name received in the Response");
		
		// Print the temperature node
		System.out.println("Temperature received from Response: " + jsonPathEvaluator.get("Temperature"));
	 
		// Print the humidity node
		System.out.println("Humidity received from Response: " + jsonPathEvaluator.get("Humidity"));
	 
		// Print weather description
		System.out.println("Weather description received from Response: " + jsonPathEvaluator.get("WeatherDescription"));
	 
		// Print Wind Speed
		System.out.println("City received from Response: " + jsonPathEvaluator.get("WindSpeed"));
	 
		// Print Wind Direction Degree
		System.out.println("City received from Response: " + jsonPathEvaluator.get("WindDirectionDegree"));

	}
}
