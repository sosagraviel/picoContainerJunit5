package workshopcucumberadvance.Test.StepsDef.hook;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.DriverFactory;
import utils.ReadProperties;
import utils.constants.Constant;
import utils.context.ScenarioContextInfoHolder;

import java.time.Duration;
import java.time.LocalTime;

import static utils.HelperMethods.chromeOptionsConfig;
import static utils.HelperMethods.getEnvironmentVariable;
import static utils.constants.DataConstant.PLATFORM_GOAL_EXECUTION_TEST;

public class init {
	static final String loginUrl = "LOGIN_URL";
	DriverFactory driverFactory;
	ScenarioContextInfoHolder context;

	private RequestSpecification requestSpecification;

	public init(ScenarioContextInfoHolder context, DriverFactory driverFactory) {
		this.context = context;
		this.driverFactory = driverFactory;
	}

	@Before
	public void initializeTest(Scenario scenario) {
		String local = "LOCAL";
		if (getEnvironmentVariable(PLATFORM_GOAL_EXECUTION_TEST).equalsIgnoreCase(local)) {
			this.driverFactory.setDriver(new ChromeDriver(chromeOptionsConfig()));
			System.out.println("Starting the session id " + this.driverFactory.getDriver().getSessionId()
							+ " related to scenario called : " + scenario.getName()
							+ " and environment : " + LocalTime.now());
		}
		this.driverFactory.getDriver().manage().window().maximize();
		this.driverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Constant.SHORT_TIMEOUT));
		this.driverFactory.getDriver().get(ReadProperties.getInstance().getProperty(loginUrl));
		this.driverFactory.setRequestFactory(this.driverFactory.getRequestFactory());
		this.driverFactory.InitializePageObject(this.driverFactory.getDriver(), scenario);
	}

	@After
	public void TearDownTest(Scenario sc) {
		if (this.driverFactory.getDriver() != null) {
			System.out.println("Closing the session id " + this.driverFactory.getDriver().getSessionId() + " related to the scenario called : " + sc.getName() + " and finished : " + LocalTime.now());
			System.out.println("The source tag names invoked in the scenario are : " + sc.getSourceTagNames().toString());
			this.driverFactory.getDriver().quit();
		} else {
			System.out.println("Scenario name: " + sc.getName());
			System.out.println("The source tag names invoked in the scenario are : " + sc.getSourceTagNames().toString());
		}
	}
}