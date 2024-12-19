package util;

import advancesWorkShop.Pages.Category.CategoryPage;
import advancesWorkShop.Pages.Login.LoginPage;
import advancesWorkShop.Pages.MenuPage;
import io.cucumber.java.Scenario;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

@Data
public class DriverFactory {
	private RemoteWebDriver driver;
	private LoginPage loginPage;
	private MenuPage menuPage;
	private CategoryPage categoryPage;
	private HelperMethods helperMethods;
	private RequestFactory requestFactory;

	public DriverFactory(RequestFactory requestFactory) {
		this.requestFactory = requestFactory;
	}

	public void InitializePageObject(WebDriver driver, Scenario scenario) {
		setLoginPage(new LoginPage(driver, scenario));
		setMenuPage(new MenuPage(driver, scenario));
		setCategoryPage(new CategoryPage(driver, scenario));
		setHelperMethods(new HelperMethods(driver));
	}
}
