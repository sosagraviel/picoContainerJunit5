package workshopcucumberadvance.Test.StepsDef.Example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import utils.DriverFactory;
import utils.RequestFactory;
import utils.constants.DataConstant;
import utils.context.ScenarioContextInfoHolder;
import utils.enums.RegisterAccounts;
import workshopcucumberadvance.endpoints.login.LoginEndpoint;

public class PasswordStepDefs {
	LoginEndpoint loginPageEndpoint;
	RequestFactory requestFactory;
	DriverFactory driverFactory;
	ScenarioContextInfoHolder context;

	public PasswordStepDefs(ScenarioContextInfoHolder context,RequestFactory requestFactory, DriverFactory driverFactory) {
		this.context = context;
		this.requestFactory = requestFactory;
		this.driverFactory = driverFactory;
		this.loginPageEndpoint = new LoginEndpoint(requestFactory);
	}


	@When("Lets send only the {string} password")
	public void letsSendOnlyThe(String password) {
		this.context.setScenarioContext(DataConstant.PASSWORD_EXAMPLE, password);
		System.out.println("The password is : " + this.context.getScenarioContext(DataConstant.PASSWORD_EXAMPLE));
	}
}
