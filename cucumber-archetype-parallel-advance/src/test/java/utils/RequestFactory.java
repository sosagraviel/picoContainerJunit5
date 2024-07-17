package utils;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import utils.constants.DataConstant;

@Data
public class RequestFactory {
	private String token;
	static final String headerTargetAmz = "x-amz-target";
	static final String contentTypeSso = "application/x-amz-json-1.1";
	static final String headerApiVersion = "x-api-version";

	static {
		RestAssured.filters(new AllureRestAssured());
	}

	/**
	 * make Request
	 *
	 * @return A RequestSpecification with authenticated true
	 */
	public RequestSpecification makeRequest() {
		return makeRequest(true);
	}

	/**
	 * make Request
	 *
	 * @param authenticated is false when to need be logged Everything else will be true
	 * @return A RequestSpecification with authenticated value and the specific number of api versions
	 */
	public RequestSpecification makeRequest(Boolean authenticated) {
		RequestSpecification request = RestAssured.given()
						.baseUri(DataConstant.BASE_URL)
						.contentType(ContentType.JSON)
						.accept("*/*");
		if (Boolean.TRUE.equals(authenticated)) {
			request = request.auth().oauth2(getToken());
		}
		return request;
	}
}

