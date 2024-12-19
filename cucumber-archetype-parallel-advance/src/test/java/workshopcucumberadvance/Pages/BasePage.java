package workshopcucumberadvance.Pages;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected Scenario scenario;

    public BasePage(WebDriver driver, Scenario scenario) {
        this.scenario = scenario;
        PageFactory.initElements(driver, this);
    }
}
