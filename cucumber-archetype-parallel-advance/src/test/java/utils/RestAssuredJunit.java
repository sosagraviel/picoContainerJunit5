package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import workshopcucumberadvance.entities.BodyPayload;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static utils.HelperMethods.readJson;
import static utils.constants.DataConstantQueries.EXAMPLE_BODY;


public class RestAssuredJunit {

	@Test
	public void testGetUsersOriginal() {
		baseURI = "https://jsonplaceholder.typicode.com";

		given()
						.when()
						.get("/users")
						.then()
						.statusCode(200)
						.body("size()", greaterThan(0));
	}
	@Test
	public void testGetUsersOriginalOne() {
		baseURI = "https://jsonplaceholder.typicode.com";

		Response response = given()
						.when()
						.get("/users");

		JsonPath.from(response.asString()).getString("[0].company.name");
	}

	@Test
	public void testGetUsersOnePost1() {
		baseURI = "https://jsonplaceholder.typicode.com";
		given()
						.header("Content-Type", "application/json")
						.body("{\"name\":\"John\"}")
						.when()
						.post("/users")
						.then()
						.statusCode(201);
	}

	@Test
	public void testGetUsersOnePostVersion1() {
		BodyPayload body = new BodyPayload("Pepe");
//		Object body =readJson(EXAMPLE_BODY);
		baseURI = "https://jsonplaceholder.typicode.com";
		ObjectMapper objectMaper = new ObjectMapper();
		String paylod=null;

		try {
			paylod=objectMaper
							.writerWithDefaultPrettyPrinter()
											.writeValueAsString(body);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		given()
						.header("Content-Type", "application/json")
						.body(paylod)
						.when()
						.post("/users")
						.then()
						.statusCode(201);
	}
}
