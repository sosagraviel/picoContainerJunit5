package util;

import advancesWorkShop.utils.constants.DataConstant;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Data;


@Data
public class RequestFactory {
	private String token;

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
	 * @return A RequestSpecification with authenticated value and the specific number of api version
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

