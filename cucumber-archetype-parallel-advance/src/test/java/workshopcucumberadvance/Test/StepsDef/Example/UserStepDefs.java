package workshopcucumberadvance.Test.StepsDef.Example;

import io.cucumber.java.en.When;
import utils.DriverFactory;
import utils.RequestFactory;
import utils.context.ScenarioContextInfoHolder;
import workshopcucumberadvance.endpoints.login.LoginEndpoint;

import static utils.constants.DataConstant.USER_EXAMPLE;

public class UserStepDefs {
	LoginEndpoint loginPageEndpoint;
	RequestFactory requestFactory;
	DriverFactory driverFactory;
	ScenarioContextInfoHolder context;

	public UserStepDefs(ScenarioContextInfoHolder context, RequestFactory requestFactory, DriverFactory driverFactory) {
		this.context = context;
		this.requestFactory = requestFactory;
		this.driverFactory = driverFactory;
		this.loginPageEndpoint = new LoginEndpoint(requestFactory);
	}


	@When("Lets send only {string} user")
	public void letsSendOnlyCredentials(String user) {
		this.context.setScenarioContext(USER_EXAMPLE, user);
		System.out.println("The user is : " + this.context.getScenarioContext(USER_EXAMPLE));
	}
}
