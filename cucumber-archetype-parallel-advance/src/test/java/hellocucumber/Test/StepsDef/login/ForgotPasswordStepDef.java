//package hellocucumber.Test.StepDef.login;
//
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.assertj.core.api.Assertions;
//import utils.DriverFactory;
//import utils.RequestFactory;
//import utils.context.ScenarioContextInfoHolder;
//import utils.enums.UserRole;
//
//public class ForgotPasswordStepDef {
////	LoginPageEndpoint loginPageEndpoint;
////	FirmEndpoint firmEndpoint;
//	ScenarioContextInfoHolder context;
//	RequestFactory requestFactory;
//	DriverFactory driverFactory;
//
//	public ForgotPasswordStepDef(ScenarioContextInfoHolder context, RequestFactory requestFactory, DriverFactory driverFactory) {
//		this.context = context;
//		this.requestFactory = requestFactory;
//		this.driverFactory = driverFactory;
////		this.loginPageEndpoint = new LoginPageEndpoint(requestFactory, context);
////		this.firmEndpoint = new FirmEndpoint(requestFactory);
//	}
//
//	@Then("^the Forgot Password page is displayed$")
//	public void validateForgotPasswordPageIsDisplayed() {
////        Assert.assertTrue("Forgot password page is not displayed", this.driverFactory.getForgotPasswordPage().isForgotPasswordPageDisplayed());
//		Assertions.assertThat(this.driverFactory.getForgotPasswordPage().isForgotPasswordPageDisplayed())
//						.withFailMessage("Forgot password page is not displayed")
//						.isTrue();
//	}
//
//	@When("^User completes Email field with invalid email$")
//	public void completeEmailFieldWithInvalidEmail() {
//		this.driverFactory.getForgotPasswordPage().completeInvalidEmail();
//	}
//
//	@Then("^the Reset my password button is not enabled$")
//	public void validateResetPasswordButtonIsNotEnabled() {
////        Assert.assertFalse("Reset my password button is enabled", this.driverFactory.getForgotPasswordPage().isResetMyPasswordButtonEnabled());
//		Assertions.assertThat(this.driverFactory.getForgotPasswordPage().isResetMyPasswordButtonEnabled())
//						.withFailMessage("Reset my password button is enabled")
//						.isFalse();
//	}
//
//	@When("^User completes Email field with \"([^\"]*)\" email$")
//	public void completeEmailFieldWithEmail(UserRole role) {
//		this.driverFactory.getForgotPasswordPage().completeEmail(role);
//	}
//
//	@And("^User clicks on Reset my password button$")
//	public void clicksOnLoginButton() {
//		this.driverFactory.getForgotPasswordPage().clickResetMyPasswordButton();
//	}
//
//	@Then("^a Success message is shown$")
//	public void validateSuccessMessageIsShown() {
////        Assert.assertTrue("Success message is not shown", this.driverFactory.getForgotPasswordPage().isSuccessMessageShown());
//		Assertions.assertThat(this.driverFactory.getForgotPasswordPage().isSuccessMessageShown())
//						.withFailMessage("Success message is not shown")
//						.isTrue();
//	}
//}
