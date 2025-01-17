package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddLocation;
import pojo.location;
import resources.APIResources;
import resources.TestdataBuild;
import resources.Utils;

public class StepDefination extends Utils{
	RequestSpecification res;
	ResponseSpecification respec ;
	Response response ;
	TestdataBuild data = new TestdataBuild();
	JsonPath js ;
	static String place_id;
	
	@Given("Add Place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
			
		 res = given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));
	
	}
	
	@When("User call {string} with {string} http request")
	public void User_call_with_http_request(String resource ,String method){
		
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		respec = 	new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST")) 
			{			
				response = res.when().post(resourceAPI.getResource());
			}
		else if(method.equalsIgnoreCase("GET"))
			{
				response = res.when().get(resourceAPI.getResource());
			}
		else if(method.equalsIgnoreCase("DELETE"))
		{
			response = res.when().delete(resourceAPI.getResource());
		}
				
	}
	
	@Then("the API is success with status code {int}")
	public void the_API_is_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);		
	}	

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String Expectedvalue) {
		
		assertEquals(getJsonPath(response,keyvalue),Expectedvalue);	
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		
		place_id= getJsonPath(response,"place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		User_call_with_http_request(resource, "GET");
		String actualname= getJsonPath(response,"name");
		assertEquals(actualname,expectedName);
		}
	
	@Given("deletePlaceAPI payload")
	public void delete_place_api_payload() throws IOException {	    
		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
		
	}
	
	
}
