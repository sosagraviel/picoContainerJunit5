package workshopcucumberadvance.Test.StepsDef.menu;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import utils.DriverFactory;
import utils.enums.UserRole;

public class MenuStepDefs {
	DriverFactory driverFactory;

	public MenuStepDefs(DriverFactory driverFactory) {
		this.driverFactory = driverFactory;
	}

	@Then("^the Home page is displayed$")
	public void validateHomePageIsDisplayed() {
		Assertions.assertThat(this.driverFactory.getMenuPage().isHomePageDisplayed())
						.withFailMessage("Menu page is not displayed").isTrue();
	}

	@And("^\"([^\"]*)\" options are displayed$")
	public void validateOptionsAreDisplayed(UserRole role) {
		Assertions.assertThat(this.driverFactory.getMenuPage().isLogoutOptionDisplayed())
						.withFailMessage("The logout button is not displayed").isTrue();
	}

	@When("^User hits category option$")
	public void userHitsCategoryOption() {
		this.driverFactory.getMenuPage().clickCategoryOption();
	}
}
