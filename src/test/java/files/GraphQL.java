package files;
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
public class GraphQL {
	
	public static void main(String[] args) {
		
		String response = given().log().all().header("Content-Type", "application/json")
		.body("{\"query\":\"query{\\n  location(locationId:17111){\\n    name\\n  }\\n character(characterId:11638)\\n   \\t {\\n      name\\n   \\t }    \\n}\",\"variables\":null}")
		.when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		String gs = js.getString("data.location.name");
		System.out.println("Rsponse : "+gs);
		
		String mutresponse = given().log().all().header("Content-Type", "application/json")
				.body("{\"query\":\"mutation ($locationName: String!, $CharName: String!) {\\n  createLocation(location: {name: $locationName, type: \\\"SouthZone\\\", dimension: \\\"234\\\"}) {\\n    id\\n  }\\n  createCharacter(character: {name: $CharName, type: \\\"per\\\", status: \\\"barely Survving\\\", species: \\\"human\\\", gender: \\\"female\\\", image: \\\"not availblE\\\", originId: 789, locationId: 17111}) {\\n    id\\n  }\\n}\\n\",\"variables\":{\"locationName\":\"japan\",\"CharName\":\"Noodle\"}}")
				.when().post("https://rahulshettyacademy.com/gq/graphql")
				.then().extract().response().asString();
				
				System.out.println(mutresponse);
				
				JsonPath js1 = new JsonPath(mutresponse);
				String gs1 = js1.getString("data.createLocation.id");
				System.out.println("Rsponse2 : "+gs1);
				
				System.out.println("response above is querry");
				System.out.println("Spelling of query is query not querry");
				System.out.println("ok boss");
				
		
	}

}
