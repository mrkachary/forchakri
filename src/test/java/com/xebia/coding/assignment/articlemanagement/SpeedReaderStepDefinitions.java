package com.xebia.coding.assignment.articlemanagement;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java8.En;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SpeedReaderStepDefinitions extends SpringCucumberIntegrationTests implements En {

	//private final Logger log = LoggerFactory.getLogger(SpeedReaderStepDefinitions.class);
	private final String SERVER_URL ="http://localhost";
	private final String THINGS_ENDPOINT="/article";

	 private static Response response;
	public SpeedReaderStepDefinitions() {
		}
	
	@Given("An article")
	public void getArticle() {
	 
	}
	
	@When("I do added an article")
	public void addAnArticle(){
		RestAssured.baseURI = SERVER_URL;
		 RequestSpecification request = RestAssured.given();
		 
		 request.header("Content-Type", "application/json");
		 response = request.body("{ \"title\":\"" + "hello bava" + "\", \"description\":\"" + "my own description" + "\", \"body\":\"" + "my own description" + "\"}")
				 .post(THINGS_ENDPOINT);	
	}
	
	@Then(" the article be created")
	public void articleIsAdded(){
		Assert.assertEquals(201, response.getStatusCode());
	}
}
