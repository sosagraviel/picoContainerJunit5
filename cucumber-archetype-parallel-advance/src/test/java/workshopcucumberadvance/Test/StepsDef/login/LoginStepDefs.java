package workshopcucumberadvance.Test.StepsDef.login;

import workshopcucumberadvance.endpoints.login.LoginEndpoint;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import utils.DriverFactory;
import utils.RequestFactory;
import utils.context.ScenarioContextInfoHolder;
import utils.enums.RegisterAccounts;
import utils.enums.UserRole;

import java.util.List;
import java.util.Map;

public class LoginStepDefs {
	LoginEndpoint loginPageEndpoint;
	ScenarioContextInfoHolder context;
	RequestFactory requestFactory;
	DriverFactory driverFactory;

	public LoginStepDefs(ScenarioContextInfoHolder context, RequestFactory requestFactory, DriverFactory driverFactory) {
		this.context = context;
		this.requestFactory = requestFactory;
		this.driverFactory = driverFactory;
		this.loginPageEndpoint = new LoginEndpoint(requestFactory, context);
	}

	/**
	 * This anAuthorizationUser method triggers the login request and store in the context each value necessary
	 *
	 * @param registerAccounts it's the mail associated with the account
	 */
	@Step("^An \"([^\"]*)\" is logged in the system$")
	@Given("^An \"([^\"]*)\" is logged in the system$")
	public void anAuthorizationUser(RegisterAccounts registerAccounts) {
		Response response = loginPageEndpoint.anAuthorizedUserLogged(registerAccounts);
		String token = loginPageEndpoint.getToken(response);
		this.requestFactory.setToken(token);
	}

	@Step("^User completes Email field with \"([^\"]*)\" credentials$")
	@When("^User completes Email field with \"([^\"]*)\" credentials$")
	public void completeEmailFieldWithCredentials(UserRole role) {
		this.driverFactory.getLoginPage().completeEmail(role);
	}

	@Step("^User completes Email field with any credentials$")
	@When("^User completes Email field with any credentials \"([^\"]*)\"$")
	public void completeEmailFieldWithAnyCredentials(String user) {
		this.driverFactory.getLoginPage().completeEmail(user);
	}

	@Step("^User completes Password field with \"([^\"]*)\" credentials$")
	@And("^User completes Password field with \"([^\"]*)\" credentials$")
	public void completePasswordFieldWithRoleCredentials(UserRole role) {
		this.driverFactory.getLoginPage().completePassword(role);
	}

	@Step("^User completes Password field with any credentials \"([^\"]*)\"$")
	@And("^User completes Password field with any credentials \"([^\"]*)\"$")
	public void completePasswordFieldWithAnyCredentials(String password) {
		this.driverFactory.getLoginPage().completePassword(password);
	}

	@Step("Login page is displayed")
	@Given("^Login page is displayed$")
	public void validateLoginPageIsDisplayed() {
		Assertions.assertThat(this.driverFactory.getLoginPage().isLoginPageDisplayed())
						.withFailMessage("Login page is not displayed").isTrue();
	}

	@Step("^User clicks on Login button$")
	@And("^User clicks on Login button$")
	public void clickOnLoginButton() {
		this.driverFactory.getLoginPage().clickLoginBtn();
	}

	@Step("^the Login button is \"([^\"]*)\"$")
	@And("^the Login button is \"([^\"]*)\"$")
	public void isLoginButtonDisabled(String statusExpected) {
		boolean status = this.driverFactory.getLoginPage().loginButtonVisibility();
		Assertions.assertThat(status).withFailMessage("The Login button visibility expected was false and the current is: " + status).isFalse();
	}

	@Step("^the Toast message is Displayed$")
	@And("^the Toast message is Displayed$")
	public void isToastMessageDisplayed() {
		Assertions.assertThat(this.driverFactory.getLoginPage().isToastMessageDisplayed())
						.withFailMessage("The Toast message is not displayed").isTrue();
		System.out.println("The Toast message is displayed");
	}

	@Step("Login failed with wrong credentials")
	@When("Login failed with wrong credentials")
	public void usersTryToLogin(DataTable dataTable) {
		List<Map<String, String>> users = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> user : users) {
			String username = user.get("user");
			String password = user.get("password");
			completeEmailFieldWithAnyCredentials(username);
			completePasswordFieldWithAnyCredentials(password);
			clickOnLoginButton();
			isToastMessageDisplayed();
		}
	}
}
