package advances.Test.steps.login;

import advances.endpoints.login.LoginEndpoint;
import advances.utils.DriverFactory;
import advances.utils.RequestFactory;
import advances.utils.context.ScenarioContextInfoHolder;
import advances.utils.enums.RegisterAccounts;
import advances.utils.enums.UserRole;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

public class LoginStepDefs {
	LoginEndpoint loginEndpoint;
	ScenarioContextInfoHolder context;
	RequestFactory requestFactory;
	DriverFactory driverFactory;

	public LoginStepDefs(ScenarioContextInfoHolder context, RequestFactory requestFactory, DriverFactory driverFactory) {
		this.context = context;
		this.requestFactory = requestFactory;
		this.driverFactory = driverFactory;
		this.loginEndpoint = new LoginEndpoint(requestFactory);
	}

	/**
	 * This anAuthorizationUser method triggers the login request and store in the context each value necessary
	 *
	 * @param registerAccounts it's the mail associated with the account
	 */
	@Given("^An \"([^\"]*)\" is logged in the system$")
	public void anAuthorizationUser(RegisterAccounts registerAccounts) {
		Response response = loginEndpoint.anAuthorizedUserLogged(registerAccounts);
		String token = loginEndpoint.getToken(response);
		this.requestFactory.setToken(token);
	}

	@When("^User completes Email field with \"([^\"]*)\" credentials$")
	public void completeEmailFieldWithCredentials(UserRole role) {
		this.driverFactory.getLoginPage().completeEmail(role);
	}

	@When("^User completes Email field with any credentials \"([^\"]*)\"$")
	public void completeEmailFieldWithAnyCredentials(String user) {
		this.driverFactory.getLoginPage().completeEmail(user);
	}

	@And("^User completes Password field with \"([^\"]*)\" credentials$")
	public void completePasswordFieldWithRoleCredentials(UserRole role) {
		this.driverFactory.getLoginPage().completePassword(role);
	}

	@And("^User completes Password field with any credentials \"([^\"]*)\"$")
	public void completePasswordFieldWithAnyCredentials(String password) {
		this.driverFactory.getLoginPage().completePassword(password);
	}

	@Given("^Login page is displayed$")
	public void validateLoginPageIsDisplayed() {
		Assertions.assertThat(this.driverFactory.getLoginPage().isLoginPageDisplayed())
						.withFailMessage("Login page is not displayed").isTrue();
	}

	@And("^User clicks on Login button$")
	public void clickOnLoginButton() {
		this.driverFactory.getLoginPage().clickLoginBtn();
	}

	@And("^the Login button is \"([^\"]*)\"$")
	public void isLoginButtonDisabled(String statusExpected) {
		boolean status = this.driverFactory.getLoginPage().loginButtonVisibility();
		Assertions.assertThat(status).withFailMessage("The Login button visibility expected was false and the current is: " + status).isFalse();
	}

	@And("^the Toast message is Displayed$")
	public void isToastMessageDisplayed() {
		Assertions.assertThat(this.driverFactory.getLoginPage().isToastMessageDisplayed())
						.withFailMessage("The Toast message is not displayed").isTrue();
	}
}
