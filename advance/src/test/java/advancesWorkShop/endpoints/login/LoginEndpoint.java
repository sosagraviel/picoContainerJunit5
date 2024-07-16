package advances.endpoints.login;

import advances.entities.login.LoginPayload;
import advances.utils.RequestFactory;
import advances.utils.constants.DataConstantQueries;
import advances.utils.enums.RegisterAccounts;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class LoginEndpoint {
	protected RequestFactory requestFactory;
	String pathIdToken = "AuthenticationResult.IdToken";

	public LoginEndpoint(RequestFactory requestFactory) {
		this.requestFactory = requestFactory;
	}

	/**
	 * The anAuthorizedUserLogged method makes a request to log in with an account that was passed via parameters
	 *
	 * @param registerAccounts it's the account to be logged
	 * @return the login token.
	 */
	public Response anAuthorizedUserLogged(RegisterAccounts registerAccounts) {
		LoginPayload loginPayload = new LoginPayload(registerAccounts);
		try {
			String body = getJsonPayload(loginPayload);
			Response response = this.requestFactory.makeRequest(false)
							.body(body)
							.post(DataConstantQueries.PATH_TO_LOGIN);
			return response;
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * This method returns the payload of the request to send
	 *
	 * @param loginCognitoPayload its Page where the payload exists
	 * @return the object serialized
	 */
	private String getJsonPayload(Object loginCognitoPayload) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(loginCognitoPayload);
	}

	public String getToken(Response response) {
		return JsonPath.from(response.asString()).getString(pathIdToken);
	}
}
