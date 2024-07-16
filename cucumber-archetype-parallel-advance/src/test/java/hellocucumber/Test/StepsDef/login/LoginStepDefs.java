package hellocucumber.Test.StepsDef.login;

import hellocucumber.endpoints.login.LoginEndpoint;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import utils.DriverFactory;
import utils.HelperMethods;
import utils.RequestFactory;
import utils.context.ScenarioContextInfoHolder;
import utils.enums.RegisterAccounts;
import utils.enums.UserRole;

public class LoginStepDefs {
	LoginEndpoint  loginPageEndpoint;
//	FirmEndpoint firmEndpoint;
	ScenarioContextInfoHolder context;
	RequestFactory requestFactory;
	DriverFactory driverFactory;
	HelperMethods helperMethods;
	String errorMessage = "The error message is not shown";

	public LoginStepDefs(ScenarioContextInfoHolder context, RequestFactory requestFactory, DriverFactory driverFactory) {
		this.context = context;
		this.requestFactory = requestFactory;
		this.driverFactory = driverFactory;
		this.loginPageEndpoint = new LoginEndpoint(requestFactory, context);
//		this.firmEndpoint = new FirmEndpoint(requestFactory);
		this.helperMethods = new HelperMethods(requestFactory);
	}

	/**
	 * This anAuthorizationUser method triggers the login request and store in the context each value necessary
	 *
	 * @param registerAccounts it's the mail associated with the account
	 */
	@Given("^An \"([^\"]*)\" is logged in the system$")
	public void anAuthorizationUser(RegisterAccounts registerAccounts) {
		Response response = loginPageEndpoint.anAuthorizedUserLogged(registerAccounts);
		String token = loginPageEndpoint.getToken(response);
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
