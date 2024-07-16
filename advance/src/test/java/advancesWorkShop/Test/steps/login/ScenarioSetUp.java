//package advances.Test.steps.login;
//
//import advances.utils.DriverFactory;
//import advances.utils.HelperMethods;
//import advances.utils.ReadProperties;
//import advances.utils.constants.Constant;
//import io.cucumber.java.Scenario;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import org.assertj.core.api.Assertions;
//import org.junit.After;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import java.time.Duration;
//
//import static advances.utils.HelperMethods.chromeOptionsConfig;
//
//public class ScenarioSetUp {
//
//	Scenario sc;
//	DriverFactory driverFactory;
//	HelperMethods helperMethods;
//	boolean flagDriver = false;
//
//	public ScenarioSetUp(DriverFactory driverFactory) {
//		this.driverFactory = driverFactory;
//		helperMethods = new HelperMethods(this.driverFactory.getDriver());
//	}
//
//	@Given("^setUp \"([^\"]*)\"$")
//	public void scenarioDriverSetUp(boolean upDriver) {
//		if (upDriver) {
//			flagDriver = true;
//			this.driverFactory.setDriver(new ChromeDriver(chromeOptionsConfig()));
//			this.driverFactory.getDriver().manage().window().maximize();
//			this.driverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Constant.SHORT_TIMEOUT));
//			this.driverFactory.getDriver().get(ReadProperties.getInstance().getProperty("LOGIN_URL"));
//		}
////        scenario.write(scenario.getName());
//		this.driverFactory.setDriver(this.driverFactory.getDriver());
//		this.driverFactory.InitializePageObject(this.driverFactory.getDriver(), sc);
//
//	}
//
//	@Given("setUp {string}")
//	public void set_up(String string) {
//		Assertions.assertThat(true).withFailMessage("Login page is not displayed").isTrue();
//		throw new io.cucumber.java.PendingException();
//	}
//
//	@After
//	public void TearDownTest() {
//		if (flagDriver) {
//			if (sc.isFailed()) {
//				helperMethods.takePicture(sc.getId(), driverFactory.getDriver());
//			}
//			this.driverFactory.getDriver().quit();
////            sc.write(scenario.getName());
//		}
//	}
//}
