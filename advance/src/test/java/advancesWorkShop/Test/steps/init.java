package advances.Test.steps;

import advances.utils.DriverFactory;
import advances.utils.constants.Constant;
import advances.utils.context.ScenarioContextInfoHolder;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.time.LocalTime;

import static advances.utils.HelperMethods.chromeOptionsConfig;
import static advances.utils.HelperMethods.getEnvironmentVariable;
import static advances.utils.constants.DataConstant.PLATFORM_GOAL_EXECUTION_TEST;

public class init {
	static final String loginUrl = "LOGIN_URL";
	static final String seleniumGrid = "SELENIUM_GRID";
	private static String dummy = "@Dummy";
	private static String dummyError = "@DummyError";
	private static String envCheck = "@EnvCheck";
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
			this.driverFactory.getDriver().manage().window().maximize();
			this.driverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Constant.SHORT_TIMEOUT));
			this.driverFactory.setRequestFactory(this.driverFactory.getRequestFactory());
			this.driverFactory.InitializePageObject(this.driverFactory.getDriver(), scenario);
		}
	}

	@After
	public void TearDownTest(Scenario sc) {
		if (
						sc.getSourceTagNames().contains(dummy)
										|| sc.getSourceTagNames().contains(dummyError)
										|| sc.getSourceTagNames().contains(envCheck)
		) {
			return;
		}
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