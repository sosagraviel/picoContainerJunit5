package hellocucumber.endpoints.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hellocucumber.entities.login.LoginPayload;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.RequestFactory;
import utils.constants.DataConstantQueries;
import utils.context.ScenarioContextInfoHolder;
import utils.enums.RegisterAccounts;

public class LoginEndpoint {
	ScenarioContextInfoHolder context;
	protected RequestFactory requestFactory;
	String pathIdToken = "AuthenticationResult.IdToken";

	public LoginEndpoint(RequestFactory requestFactory, ScenarioContextInfoHolder context) {
		this.context = context;
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
