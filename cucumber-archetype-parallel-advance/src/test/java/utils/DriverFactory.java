package utils;

import io.cucumber.java.Scenario;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import workshopcucumberadvance.Pages.Category.CategoryPage;
import workshopcucumberadvance.Pages.Login.LoginPage;
import workshopcucumberadvance.Pages.MenuPage;

@Data
public class DriverFactory {
	private RemoteWebDriver driver;
	private Scenario scenario;
	private LoginPage loginPage;
	private MenuPage menuPage;
	private RequestFactory requestFactory;
	private HelperMethods helperMethods;
	//	private ForgotPasswordPage forgotPasswordPage;
	private CategoryPage categoryPage;
//	private NavMenuPage navMenuPage;
//	private NavBreadCrumbsPage navBreadCrumbsPage;

	public DriverFactory(RequestFactory requestFactory) {
		this.requestFactory = requestFactory;
	}

	/**
	 * this method initializes every page with the driver and scenario defined by pico container
	 * from cucumber.
	 *
	 * @param driver   it is the driver defined to use in any selenium action
	 * @param scenario it is the scenario defined by cucumber and useful to track
	 *                 the behavior of our scenarios
	 */
	public void InitializePageObject(WebDriver driver, Scenario scenario) {
		setLoginPage(new LoginPage(driver, scenario));
		setMenuPage(new MenuPage(driver, scenario));
		setHelperMethods(new HelperMethods(driver, getRequestFactory()));
//		setForgotPasswordPage(new ForgotPasswordPage(driver, scenario));
//		setNavMenuPage(new NavMenuPage(driver, scenario));
//		setNavBreadCrumbsPage(new NavBreadCrumbsPage(driver, scenario));
		setCategoryPage(new CategoryPage(driver, scenario));
	}
}
