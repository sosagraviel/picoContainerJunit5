package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.constants.Constant;
import utils.constants.DataConstant;
import utils.constants.DataConstantQueries;
import utils.enums.TargetEmailToDelete;
import utils.enums.TargetPlansToDelete;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static utils.constants.DataConstant.CAPTCHA_LOCAL_STORAGE_VALUE;
import static utils.constants.DataConstant.PLATFORM_GOAL_EXECUTION_TEST;
import static utils.enums.TargetEmailToDelete.accountIncluded;

@Getter
@Setter
public class HelperMethods {
	static final String screenshotsPath = "./Screenshots/";
	protected static String BLOCK_UI_XPATH = "//*[@class='block-ui-wrapper active']";
	@FindBy(how = How.XPATH, using = "//app-custom-snack-bar//*[@class='message-content']//*[not(@role='img')][1]")
	private WebElement SUCCESS_MESSAGE;
	@FindBy(how = How.XPATH, using = "//*[contains(@class,'block-ui-main active')]")
	protected WebElement LOAD;
	private static final String xpathSuccessMessage = "//*[@class='message-content']//*[not(@role='img')]";
	WebDriver driver;
	private static final Random random = new Random();
	@Getter
	private static String browsers;
	private static final String localEnvironment = "LOCAL";
	private static final String qaEnvironment = "QA";
	private static final String demoEnvironment = "DEMO";
	private static final String prodEnvironment = "PROD";
	private static final String devEnvironment = "DEV";
	@Getter
	public static String environments;
	protected RequestFactory requestFactory;
	protected static final String AWS = "AWS";

	public HelperMethods(WebDriver driver, RequestFactory requestFactory) {
		this.driver = driver;
		this.requestFactory = requestFactory;
		PageFactory.initElements(driver, this);
	}

	public HelperMethods(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public HelperMethods(RequestFactory requestFactory) {
		this.requestFactory = requestFactory;
	}

	/**
	 * A method to test environments and return the corresponding environment value.
	 *
	 * @return the environment value based on the current environment
	 */
	protected static String testEnvironments() {
		setEnvironments(System.getenv("ENVIRONMENTS"));
		setEnvironments(getEnvironments() != null ? getEnvironments() : "QA");
		final String qa = "qa";
		final String demo = "demo";
		final String prod = "prod";
		return switch (getEnvironments().toLowerCase()) {
			case qa -> qaEnvironment;
			case demo -> demoEnvironment;
			case prod -> prodEnvironment;
			default -> devEnvironment;
		};
	}

	/**
	 * A function that returns the selected browser.
	 *
	 * @return the selected browser
	 */
	public static String browserSelected() {
		String browser = "BROWSER";
		setBrowsers(System.getenv(browser));
		String chrome = "CHROME";
		setBrowsers(getBrowsers() != null ? getBrowsers() : chrome);
		return getBrowsers();
	}

	/**
	 * This getPassword method gets the map of environment variables defined in the system
	 *
	 * @param key it's the key of the password
	 * @return the value of the password defined on the environment variables
	 */
	public static String getEnvironmentVariable(String key) {
		return System.getenv().get(key);
	}

	public static String specificDate(int month, int day) {
		LocalDate localDate = LocalDate.now();
		return (localDate.withDayOfMonth(month).withMonth(day)).toString();
	}

	/**
	 * Create a Date
	 *
	 * @param option it is the part of the Date that you will need
	 */
	public static int currentYearDayOrMonthOfDate(String option) {
		LocalDate currentDate = LocalDate.now();
		final String year = "year";
		final String day = "day";
		final String month = "month";
		return switch (option.toLowerCase()) {
			case year -> currentDate.getYear();
			case day -> currentDate.getDayOfMonth();
			case month -> currentDate.getMonthValue();
			default -> throw new IllegalStateException("This value does not exist: " + option);
		};
	}

	/**
	 * Create random String
	 *
	 * @param value number of digits
	 */
	public static String randomString(int value) {
		return RandomStringUtils.randomAlphabetic(value);
	}

	public static void setEnvironments(String environments) {
		HelperMethods.environments = environments;
	}

	/**
	 * Create random alfa Numeric Code
	 *
	 * @param length is the length of the code to want to arrive.
	 */
	public static String randomAlfaNumeric(int length) {
		return RandomStringUtils.randomAlphanumeric(length).toUpperCase();
	}

	/**
	 * This function is used to configure the ChromeOptions object used to create a ChromeDriver object
	 *
	 * @return A ChromeOptions object
	 */
	public static ChromeOptions chromeOptionsConfig() {
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "downloads" + File.separator + "ActualPdf");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		//this block should be commented to execute in aws through console
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable-browser-side-navigation");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-gpu");
		if (!getEnvironmentVariable(PLATFORM_GOAL_EXECUTION_TEST).equalsIgnoreCase(AWS) && !getEnvironmentVariable(PLATFORM_GOAL_EXECUTION_TEST).equalsIgnoreCase(localEnvironment)) {
			options.addArguments("--headless");
		}
		//TO HERE
		options.addArguments("window-size=1980,1080");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-setuid-sandbox");
		//this block should be commented to execute in local
		if (!getEnvironmentVariable(PLATFORM_GOAL_EXECUTION_TEST).equalsIgnoreCase(localEnvironment)) {
			options.setCapability("browserName", "chrome");
			options.setCapability("browserVersion", "latest");
			options.setCapability("platformName", "windows");
		}
		//TO HERE
		return options;
	}

	/**
	 * `SafariOptions options = new SafariOptions();`
	 * <p>
	 * This is the line that creates the SafariOptions object
	 *
	 * @return SafariOptions options = new SafariOptions();
	 */
	public static SafariOptions safariOptionsConfig() {
		return new SafariOptions();
	}

	/**
	 * This function is used to configure the FirefoxOptions object, which is used to configure the FirefoxDriver object
	 *
	 * @return FirefoxOptions
	 */
	public static FirefoxOptions firefoxOptionsConfig() {
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--headless");
		options.addArguments("window-size=1980,1080");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-browser-side-navigation");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-gpu");
		options.addArguments("--disable-setuid-sandbox");
		return options;
	}

	public static void setBrowsers(String browsers) {
		HelperMethods.browsers = browsers;
	}

	/**
	 * This getPostResponse method triggers the post-request
	 *
	 * @param payload it's the payload related with the update goal
	 * @return miss
	 */
	public Response getPostResponse(Object payload) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String payloadFormatted = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload);
			return requestFactory.makeRequest(2.0f).body("[\n" + payloadFormatted + "\n]")
							.post(DataConstantQueries.PATH_FOR_MEMBER);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * This getPutResponse method triggers the put request
	 *
	 * @param payload it's the payload related with the update goal
	 * @param id      it's the id necessary to complete the request path
	 * @return the response related with the request
	 */
	public Response getPutResponse(Object payload, Long id, boolean isPayloadSurroundBrackets) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String payloadFormatted = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload);
			String payloadSurrounded = isPayloadSurroundBrackets ? "[\n" + payloadFormatted + "\n]" : payloadFormatted;
			return requestFactory.makeRequest(2.0f).body(payloadSurrounded)
							.put(DataConstantQueries.PATH_FOR_MEMBER + id.toString());
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * Gets the current timestamp as a string.
	 *
	 * @return the current timestamp as a string
	 */
	public static String getTimeStamp() {
		Date date = new Date();
		return String.valueOf(date.getTime());
	}

	/**
	 * Checks and deletes the given email based on the specified email type and user ID.
	 *
	 * @param emailToDelete the type of email to delete
	 * @param firmUserId    the user ID associated with the email
	 */
	public void checkByEmail(TargetEmailToDelete emailToDelete, Long firmUserId) {
		switch (emailToDelete) {
			case planPortfolioInvestment ->
							removeEmailIfExist(DataConstantQueries.GET_PORTFOLIO_INVESTMENT_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case portfolioInvestmentHHInfoAWS ->
							removeEmailIfExist(DataConstantQueries.GET_INVESTMENT_FROM_HH_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case portfolioInvestmentSidePanelAWS ->
							removeEmailIfExist(DataConstantQueries.GET_INVESTMENT_FROM_SIDE_PANEL_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case firmDistributionSettings ->
							removeEmailIfExist(DataConstantQueries.GET_FIRM_DISTRIBUTION_SETTINGS_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case distributionOther ->
							removeEmailIfExist(DataConstantQueries.GET_FIRM_DISTRIBUTION_SETTINGS_OTHER_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case distributionQualifiedRetirement ->
							removeEmailIfExist(DataConstantQueries.GET_FIRM_DISTRIBUTION_SETTINGS_QUALIFIED_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case distributionRoth ->
							removeEmailIfExist(DataConstantQueries.GET_FIRM_DISTRIBUTION_SETTINGS_ROTH_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case distributionInherited ->
							removeEmailIfExist(DataConstantQueries.GET_FIRM_DISTRIBUTION_SETTINGS_INHERITED_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case distributionTaxable ->
							removeEmailIfExist(DataConstantQueries.GET_FIRM_DISTRIBUTION_SETTINGS_TAXABLE_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case portfolioInvestmentWarningsAWS ->
							removeEmailIfExist(DataConstantQueries.GET_INVESTMENT_FROM_WARNINGS_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case planPortfolioBanking ->
							removeEmailIfExist(DataConstantQueries.GET_PORTFOLIO_BANKING_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case planPortfolioOtherAssets ->
							removeEmailIfExist(DataConstantQueries.GET_PORTFOLIO_OTHER_ASSET_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case mfa -> removeEmailIfExist(DataConstantQueries.GET_HH_MFA_PATH_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case modelAllocations ->
							removeEmailIfExist(DataConstantQueries.GET_HH_MODEL_ALLOCATION_PATH_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case modelAllocationFromSettings ->
							removeEmailIfExist(DataConstantQueries.GET_HH_MODEL_ALLOCATION_FROM_SETTINGS_PATH_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case modelAllocationFromHh ->
							removeEmailIfExist(DataConstantQueries.GET_HH_MODEL_ALLOCATION_FROM_HH_PATH_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case taxLab ->
							removeEmailIfExist(DataConstantQueries.GET_TAX_CENTER_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case activateLink ->
							removeEmailIfExist(DataConstantQueries.GET_HH_ACTIVATE_LINK_PATH_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case resetPassword ->
							removeEmailIfExist(DataConstantQueries.GET_HH_RESET_PASSWORD_PATH_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case member ->
							removeEmailIfExist(DataConstantQueries.GET_MEMBER_PATH_QUERY_PRE + firmUserId + DataConstantQueries.GET_MEMBER_PATH_QUERY, DataConstantQueries.DELETE_MEMBER_PATH);
			case firmFlatEditMember ->
							removeEmailIfExist(DataConstantQueries.GET_MEMBER_PATH_QUERY_PRE + firmUserId + DataConstantQueries.GET_MEMBER_EDITED_PATH_QUERY, DataConstantQueries.DELETE_MEMBER_PATH);
			case firmFlatDeleteMember ->
							removeEmailIfExist(DataConstantQueries.GET_MEMBER_PATH_QUERY_PRE + firmUserId + DataConstantQueries.GET_MEMBER_DELETED_PATH_QUERY, DataConstantQueries.DELETE_MEMBER_PATH);
			case firmFlatCancelMember ->
							removeEmailIfExist(DataConstantQueries.GET_MEMBER_PATH_QUERY_PRE + firmUserId + DataConstantQueries.GET_MEMBER_CANCEL_PATH_QUERY, DataConstantQueries.DELETE_MEMBER_PATH);
			case integrations -> removeEmailIfExist(DataConstantQueries.DELETE_INTEGRATION_PATH, DataConstant.INTEGRATIONS);
			case reportCenter ->
							removeEmailIfExist(DataConstantQueries.GET_REPORT_CENTER_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case signUp ->
							removeEmailIfExist(DataConstantQueries.GET_FIRM_SIGNUP_PATH_QUERY, DataConstantQueries.DELETE_FIRM_PATH);
			case importHousehold ->
							removeEmailIfExist(DataConstantQueries.GET_IMPORTED_HH_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case lifeHub ->
							removeEmailIfExist(DataConstantQueries.GET_LIFE_HUB_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case onlyLifeHub ->
							removeEmailIfExist(DataConstantQueries.GET_ONLY_LIFE_HUB_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case planIncomeSocialSecurity ->
							removeEmailIfExist(DataConstantQueries.GET_PLAN_INCOME_S_SECURITY_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case ssEstimatePrimaryInsurance ->
							removeEmailIfExist(DataConstantQueries.GET_PLAN_INCOME_S_SECURITY_ESTIMATE_PRIMARY_INSURANCE_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case ssAllBenefitsMethods ->
							removeEmailIfExist(DataConstantQueries.GET_PLAN_INCOME_S_SECURITY_ALL_BENEFIT_METHODS_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case planDashboard ->
							removeEmailIfExist(DataConstantQueries.GET_PLAN_DASHBOARD_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case adjustmentDashboard ->
							removeEmailIfExist(DataConstantQueries.GET_PLAN_ADJUSTMENT_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case balanceDashboard ->
							removeEmailIfExist(DataConstantQueries.GET_PLAN_BALANCE_DASHBOARD_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case spendingCapacityDashboard ->
							removeEmailIfExist(DataConstantQueries.GET_PLAN_BALANCE_SPENDING_CAPACITY_DASHBOARD_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case investmentCostBasis ->
							removeEmailIfExist(DataConstantQueries.GET_PLAN_INVESTMENT_COST_BASIS_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case otherIncomeTaxTreatment ->
							removeEmailIfExist(DataConstantQueries.GET_PLAN_OTHER_INCOME_TAX_TREATMENT_EMAILS_QUERY, DataConstantQueries.DELETE_HH_PATH);
			case accountIncluded -> testEmail(accountIncluded.name(), DataConstantQueries.DELETE_HH_PATH);
		}
	}

	public void testEmail(String hhPath, String hhPathToDelete) {
		String query = generateEmailQuery(hhPath);
		removeEmailIfExist(query, hhPathToDelete);
	}

	public String generateEmailQuery(String hhPath) {
		return DataConstantQueries.GET_EMAILS_QUERY_PREFIX + hhPath + DataConstantQueries.GET_EMAILS_QUERY;
	}

	/**
	 * Login with a specific role, remove an email, and set an email.
	 *
	 * @param hhPath         The path to the email you want to delete.
	 * @param hhPathToDelete The path to the email you want to delete.
	 */
	public void removeEmailIfExist(String hhPath, String hhPathToDelete) {
		removeEmail(hhPath, hhPathToDelete);
	}

	public void removeEmailIfExistExample(String hhPath, String hhPathToDelete) {
		removeEmail(hhPath, hhPathToDelete);
	}

	/**
	 * This function takes a path, a target to delete, and a goal. It then gets the id by email, and if the id by email
	 * is not null, it sets the specification api to false and the goal, and then for each map in the ids by email, it
	 * sets the account id to the string value of the id in the map, and then sets the response to the delete request of
	 * the target to delete and the account id
	 *
	 * @param path           the path to the file containing the email addresses
	 * @param targetToDelete the target to delete, for example, /v1/accounts/
	 */
	public void removeEmail(String path, String targetToDelete) {
		String integration = "integration";
		String idParam = targetToDelete.contains(integration) ? "id" : "Id";
		List<Map<String, String>> idsByEmail = getIdByEmail(path);
		if (idsByEmail != null) {
			idsByEmail.forEach(stringStringMap -> {
				String accountId = String.valueOf(stringStringMap.get(idParam));
				if (targetToDelete.contains("Member")) {
					this.requestFactory.makeRequest(2.0F)
									.body("{}")
									.delete(targetToDelete + accountId);
				} else if (targetToDelete.contains(integration)) {
					this.requestFactory.makeRequest()
									.body("{}")
									.delete(path + accountId);
				} else {
					this.requestFactory.makeRequest()
									.body("{}")
									.delete(targetToDelete + accountId);
				}
			});
		}
	}

	/**
	 * This function is used to get the id of a user by email
	 *
	 * @param path the path to the resource you want to get
	 * @return A list of maps.
	 */
	public List<Map<String, String>> getIdByEmail(String path) {
		List<Map<String, String>> idsList = null;
		Response response = requestFactory.makeRequest().get(path);
		try {
			if (!path.contains("integration")) {
				idsList = JsonPath.from(response.asString()).get("value");
			} else {
				idsList = JsonPath.from(response.asString()).get("result");
			}

		} catch (Exception e) {
			return Collections.emptyList();
		}
		return idsList;
	}

	/**
	 * This function creates a URL
	 * <a href="https://app-qa.incomelaboratory.com/dashboard/household/9721/plan-compare">...</a>
	 *
	 * @return the URL created as string
	 */
	public static String createPlanUrl(String hhAccountId) {
		return DataConstant.BASE_EDIT_HOUSEHOLD_PLAN_URL + hhAccountId + "/plans";
	}

	/**
	 * This function creates a URL
	 * <a href="https://app-qa.incomelaboratory.com/dashboard/household/9721/plan-compare">...</a>
	 *
	 * @return the URL created as string
	 */
	public static String createPlanDashboardUrl(String hhAccountId) {
		String dashboard = "/plans/household-plan/dashboard";
		return DataConstant.BASE_EDIT_HOUSEHOLD_PLAN_URL + hhAccountId + dashboard;
	}

	public static String createEditHouseholdPlanUrl(String hhAccountId, String planId) {
		return DataConstant.BASE_EDIT_HOUSEHOLD_PLAN_URL + hhAccountId + "/plans/" + planId + "/edit?from=newPlanDashboard";
	}

	/**
	 * Wait for an element to be present and then click the element
	 *
	 * @param element to click on
	 */
	public void clickWithActions(WebElement element) {
		if (environments.equalsIgnoreCase(localEnvironment)) {
			waitBlockUIDisappear();
		}
		waitForElementPresentLong(element);
		Actions action = new Actions(driver);
		waitForElementClickableLong(element);
		action.moveToElement(element).click(element).perform();
	}

	public void waitForElementPresentLong(WebElement element) {
		waitForElementPresent(element, Constant.LONGEST_TIMEOUT, this.driver);
	}

	protected void waitForElementPresent(WebElement element, int time, WebDriver optionalDriver) {
		WebDriverWait wait = new WebDriverWait(optionalDriver, Duration.ofSeconds(time));
		wait.pollingEvery(Duration.ofMillis(Constant.LONGEST_TIMEOUT));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitBlockUIDisappear() {
		int waited = 0;
		while (isBlockUIPresent() && waited < 200) {
			waitForElements(1);
			waited++;
		}
	}

	/**
	 * Check if a block UI is present.
	 *
	 * @return true if the block UI is present, false otherwise
	 */
	public boolean isBlockUIPresent() {
		try {
			List<WebElement> blockUIs = driver.findElements(By.xpath(BLOCK_UI_XPATH));
			return blockUIs.size() > 0;
		} catch (NoSuchElementException ex) {
			return false;
		}
	}

	public void waitForElementClickableLong(WebElement element) {
		if (environments.equalsIgnoreCase(localEnvironment)) {
			waitBlockUIDisappear();
		}
		waitForElementPresentLong(element);
		waitForElementClickable(element, Constant.LONG_TIMEOUT, driver);
	}

	protected void waitForElementClickable(WebElement element, int time, WebDriver optionalDriver) {
		if (environments.equalsIgnoreCase(localEnvironment)) {
			waitBlockUIDisappear();
		}
		WebDriverWait wait = new WebDriverWait(optionalDriver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElements(int value) {
		LockSupport.parkNanos(Duration.ofNanos(value * 500L).toNanos());
	}

	public boolean isElementChecked(WebElement element, String attribute, String expectedValue) {
		return element.getAttribute(attribute).contains(expectedValue);
	}

	/**
	 * If the value is true, then the function will execute a JavaScript command to set the local storage item
	 * "disablePendo" to true. If the value is false, then the function will execute a JavaScript command to set the local
	 * storage item "disablePendo" to false
	 *
	 * @param value true or false
	 */
	public void handlePendo(boolean value) {
		if (value) {
			javaScriptWebDriver().executeScript("localStorage.setItem(\"disablePendo\", true)");
		} else {
			javaScriptWebDriver().executeScript("localStorage.setItem(\"disablePendo\", false)");
		}
	}

	/**
	 * Create a JavaScript driver
	 *
	 * @return driver to execute javascript scripts
	 */
	private JavascriptExecutor javaScriptWebDriver() {
		if (this.driver instanceof JavascriptExecutor) {
			return ((JavascriptExecutor) this.driver);
		} else {
			throw new IllegalStateException("This driver does not support JavaScript...!");
		}
	}

	/**
	 * This function will set the local storage value of the automation key to the correct value for the environment that is being tested
	 */
	public void handleCaptcha() {
		String token = getEnvironmentVariable(CAPTCHA_LOCAL_STORAGE_VALUE);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("function myFunction(token) { " + "var qaValue = token; " + "localStorage.setItem(\"automationKey\", qaValue);" + "} " +
						"myFunction(arguments[0]);", token);
	}

	public boolean isElementEnabled(WebElement element) {
		try {
			waitForElementPresentLong(element);
			return element.isEnabled();
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			return false;
		}
	}

	/**
	 * this isElementPresent method search to find if the element exists
	 *
	 * @param element it's the goal element
	 * @return true or false because is tried with NoSuchElementException
	 */
	public boolean isElementPresent(WebElement element) {
		try {
			return element.isEnabled();
		} catch (NoSuchElementException ex) {
			return false;
		}
	}

	/**
	 * Wait for an element to be present, clear the field and complete the field with the value
	 *
	 * @param element to send the value
	 * @param value   to send to the value
	 */
	public void sendKeysInElement(WebElement element, String value) {
		waitForElementPresentLong(element);
		element.clear();
		element.sendKeys(Keys.chord(Keys.DELETE));
		element.sendKeys(value);
	}

	public boolean isElementExistWaitLongTime(WebElement element) {
		try {
			waitForElementPresentLong(element);
			return element.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * It waits for the element to be present, then scrolls to it
	 *
	 * @param element The element to scroll to.
	 */
	public void scrollToElement(WebElement element) {
		if (environments.equalsIgnoreCase(localEnvironment)) {
			waitBlockUIDisappear();
		}
		waitForElementPresentLong(element);
		javaScriptWebDriver()
						.executeScript("arguments[0].scrollIntoView();", element);
	}

	/**
	 * taking a screenshot
	 *
	 * @param name name of screenshot where will be saved in the screenshot folder.
	 */
	public void takePicture(String name, WebDriver driver) {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {

			FileUtils.copyFile(screenshot, new File(screenshotsPath + name + ".png"));
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * taking a screenshot
	 *
	 * @param name name of screenshot where will be saved in the screenshot folder.
	 */
	public void takePicture(String name) {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(screenshotsPath + name + ".png"));
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * The twoFactorAuth method gets the secret key provided by readQRMfa() and generates a related authentication code.
	 *
	 * @param secreteKey it's the secrete QR key
	 * @return the auth code
	 */
	public String twoFactorAuth(String secreteKey) {
//		Totp totp = null;
//		totp = new Totp(secreteKey);
//		return totp.now();
		return ";";
	}

	/**
	 * This readQRMfa method read the QR and take from there the secrete key
	 *
	 * @param name it's the name relate with the picture that will be saved
	 * @return return the key secrete related with the QR
	 */
	public String readQRMfa(String name) {
//		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		try {
//			FileUtils.copyFile(file, new File(screenshotsPath + name + ".png"));
//			BufferedImage bufferedImage = ImageIO.read(file);
//			LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
//			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//			Result result = new MultiFormatReader().decodeWithState(bitmap);
//			Pattern pattern = Pattern.compile("secret=([^&]+)");
//			Matcher matcher = pattern.matcher(result.getText());
//			if (matcher.find()) {
//				return matcher.group(1);
//			} else {
//				throw new RuntimeException("Secret value not found.");
//			}
//		} catch (IOException e) {
//			throw new RuntimeException("Could not decode QR Code, IOException :: " + e.getMessage());
//		} catch (NotFoundException e) {
//			throw new RuntimeException(e.getMessage());
//		}
		return ";";
	}

	/**
	 * It deletes all files in a folder that contain a specific string
	 *
	 * @param folder   The folder where the files are located.
	 * @param fileName The name of the file you want to delete.
	 */
	public static void deleteFilesIntoSpecificFolder(File folder, String fileName) {
		File[] files = folder.listFiles();
		if (files != null) {
			for (File f : files) {
				if (f.getName().contains(fileName)) {
					f.deleteOnExit();
				}
			}
		}
	}

	/**
	 * This function will click on an element, wait for a blockUI to disappear if is in the LOCAL environment, and then wait for another element to
	 * appear. If the element does not appear, it will click on the element again and wait for the element to appear. It
	 * will do this for a specified number of iterations
	 *
	 * @param elementToClick The element you want to click
	 * @param elementToWait  The element that you want to wait for to appear.
	 * @param iterations     The number of times you want to click the element.
	 * @param page           The page object that contains the element to click.
	 */
	public void clickWithIterations(WebElement elementToClick, WebElement elementToWait, int iterations, Object page) {
		if (environments.equalsIgnoreCase(localEnvironment)) {
			waitBlockUIDisappear();
		}
		IntStream.range(0, iterations)
						.filter(i -> {
							clickWithActions(elementToClick);
							return isElementPresent(elementToWait);
						})
						.findFirst();

		if (!isElementPresent(elementToWait)) {
			PageFactory.initElements(driver, page);
		}
	}

	public String getAnyAttributeValue(WebElement element, String goal) {
		waitForElements(Constant.LONG_TIMEOUT);
		return element.getAttribute(goal);
	}

	public void refreshPage() {
		driver.navigate().refresh();
	}

	/**
	 * It waits for the element to be present, then waits for it to be clickable, then returns the element
	 *
	 * @param xpath The xpath of the element you want to create a WebElement for.
	 * @return WebElement
	 */
	public WebElement createWebElement(String xpath) {
		WebElement webElement = driver.findElement(By.xpath(xpath));
		try {
			waitForElementPresentLongest(webElement);
			waitForElementClickableLong(webElement);
			return webElement;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return webElement;
	}

	public void waitForElementPresentLongest(WebElement element) {
		waitForElementPresent(element, Constant.LONGEST_TIMEOUT, driver);
	}

	public boolean isExistWebElement(String xpath) {
		try {
			WebElement webElement = driver.findElement(By.xpath(xpath));
			waitForElementPresentLongest(webElement);
			waitForElementClickableLong(webElement);
			return driver.findElement(By.xpath(xpath)).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementDisplayed(WebElement element, int time) {
		try {
			waitForElements(time);
			return element.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * Wait for an element to be present and get the text
	 *
	 * @param element to get text
	 * @return text of the element
	 */
	public String getText(WebElement element) {
		try {
			if (element.isDisplayed()) {
				waitForElementPresentLong(element);
				return element.getText();
			} else {
				return "The element does not exist exception";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public boolean isElementDisabled(WebElement element) {
		return element.getAttribute("class").contains("disabled");
	}

	public boolean isSuccessMessagePresent(String action) {
		waitForElementPresentLongest(SUCCESS_MESSAGE);
		return getText(SUCCESS_MESSAGE).contains(action);
	}

	public boolean waitForLoadFinished() {
		waitForElements(Constant.SHORT_TIMEOUT);
		return true;
	}

	/**
	 * Waits for the element to finish loading and become clickable.
	 *
	 * @param element   the element to wait for
	 * @param clickable the clickable element to wait for
	 */
	public void waitElementFinishes(WebElement element, WebElement clickable) {
		while (!isElementExistWaitLongTime(element)) {
			waitForElementClickableLong(clickable);
		}
	}

	public boolean isElementEnabledMinimumTime(WebElement element) {
		try {
			return element.isEnabled();
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			return false;
		}
	}

	/**
	 * Create random int
	 *
	 * @param value is up to the number you want it to arrive
	 *              + 1, so you never have a 0
	 */
	public static String randomInt(int value) {
		int result;
		do {
			result = (int) (Math.random() * value) + 1;
		} while (result == value || result == 0);
		return String.valueOf(result);
	}

	public String getAttributeValue(WebElement element) {
		waitForElementPresentLong(element);
		return element.getAttribute("value");
	}

	public void waitLoaderFinishes() {
		waitElementFinishes(LOAD, SUCCESS_MESSAGE);
	}

	/**
	 * Swipe one slide
	 *
	 * @param element slider to move,
	 * @param x       will be moved starting from the current x position,
	 * @param y       will be moved starting from the current x position
	 *                (-130=1,-50=2,20=3,100=4,130-5)
	 */
	public void swiperSlider(WebElement element, int x, int y) {
		Actions actions = new Actions(driver);
		actions.dragAndDropBy(element, x, y).build().perform();
	}

	/**
	 * It creates a random double between zero and one, and then formats it to two decimal places
	 *
	 * @return A random double value.
	 */
	public static double randomDouble() {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.parseDouble(twoDForm.format(random.nextDouble()));
	}

	/**
	 * this function clears the input field
	 *
	 * @param element the element you want to clear
	 */
	public void clearInputField(WebElement element) {
		element.clear();
		element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		element.sendKeys(Keys.chord("a", Keys.BACK_SPACE));
		element.sendKeys(Keys.DELETE);
	}

	/**
	 * This function takes in an enum and returns a string
	 *
	 * @param planStep This is the step you want to delete.
	 * @return The value of the key that is passed in.
	 */
	public static String getStep(TargetPlansToDelete planStep) {
		HashMap<Enum<TargetPlansToDelete>, String> steps = new HashMap<>();
		steps.put(TargetPlansToDelete.planLiabilities, "/liabilities");
		steps.put(TargetPlansToDelete.planSavings, "/cashflows");
		steps.put(TargetPlansToDelete.planBaselineExpenses, "/baselineExpenses");
		steps.put(TargetPlansToDelete.planOtherExpenses, "/budgets");
		steps.put(TargetPlansToDelete.planRetirementIncomeLegacy, "/retirement");
		steps.put(TargetPlansToDelete.planRetirementPowerPlanning, "/retirement");
		steps.put(TargetPlansToDelete.taxSettings, "/tax-settings");
		steps.put(TargetPlansToDelete.planPortfolioInvestment, "/portfolios");
		return steps.get(planStep);
	}

	/**
	 * It reads a JSON file and returns a JSON object
	 *
	 * @param json_file_path The path to the JSON file you want to read.
	 * @return A JSONObject
	 */
	public static Object readJson(String json_file_path) {
		JSONParser parser = new JSONParser();
		try {
			return parser.parse(new FileReader(json_file_path));
		} catch (IOException | ParseException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * "Wait for the element to appear on the page, and if it doesn't appear within the specified time, throw an
	 * exception."
	 *
	 * @param elementFactory A lambda expression that returns a list of WebElements.
	 * @param waitTime       The amount of time to wait between each try
	 * @param maxTries       The number of times to try to find the element before giving up.
	 */
	public void waitForSelector(Supplier<List<WebElement>> elementFactory, int waitTime, int maxTries) {
		if (environments.equalsIgnoreCase(localEnvironment)) {
			IntStream.range(0, maxTries)
							.filter(i -> {
								waitForElements(waitTime);
								return elementFactory.get().size() > 0;
							})
							.findFirst();
		}
	}

	public void clickEsc(WebElement element) {
		if (environments.equalsIgnoreCase(localEnvironment)) {
			waitBlockUIDisappear();
		}
		element.sendKeys(Keys.ESCAPE);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * @param valueFrom
	 * @param valueTo
	 * @return
	 */
	public static String randomIntSpecificAmountOfDigits(int valueFrom, int valueTo) {
		int result = random.nextInt(valueFrom) + valueTo;
		return String.valueOf(result);
	}

	/**
	 * @param valueMin this is the minimum number
	 * @param valueMax this is the maximum number
	 * @return a number between those numbers
	 */
	public static String randomNumberIntoRange(int valueMin, int valueMax) {
		return String.valueOf((int) (Math.floor(Math.random() * (valueMax - valueMin + 1) + valueMin)) - 1);
	}

	public boolean isElementExist(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * It opens a new tab, navigates to the given URL, and sets the parent and child window IDs
	 *
	 * @param url The URL of the page you want to open in a new tab.
	 * @return miss
	 */
	public List<String> openNewTab(String url) {
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get(url);
		List<String> handles = new ArrayList<>(driver.getWindowHandles());
		waitForElements(Constant.SHORT_TIMEOUT);
		return handles;
	}

	public void waitForOneSpecificElementDisappear(WebElement element) {
		String xpath = element.toString().split("xpath:")[1];
		Wait<WebDriver> wait = new FluentWait<>(driver)
						.withTimeout(Duration.ofSeconds(Constant.LONGEST_TIMEOUT))
						.pollingEvery(Duration.ofMillis(Constant.SHORT_TIMEOUT));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath.replaceFirst("]", ""))));
	}

	public void navigateToUrl(String url) {
		driver.navigate().to(url);
	}

	public void waitForSuccessMessage() {
		waitBlockUIDisappear();
		waitForElementPresentLong(SUCCESS_MESSAGE);
	}

	/**
	 * Generates the current date and time in full format.
	 *
	 * @return the current date and time in full format as a string
	 */
	public static String currentDateFull() {
		return String.valueOf(java.time.Clock.systemUTC().instant());
	}

	/**
	 * A method that takes in year, month, and dayOfMonth and returns a string representation of the date and time.
	 *
	 * @param year       the year
	 * @param month      the month-of-year
	 * @param dayOfMonth the day-of-month
	 * @return the string representation of the date and time
	 */
	public static String customDate(int year, int month, int dayOfMonth) {
		LocalDateTime dateTime = LocalDateTime.of(year, month, dayOfMonth, 0, 0, 0);
		return dateTime.toString();
	}

	/**
	 * @param xpath miss
	 * @param time  miss
	 * @return miss
	 */
	public boolean isResetPasswordElementDisplayed(String xpath, int time) {
		WebElement element = driver.findElement(By.xpath(xpath));
		try {
			waitForElements(time);
			return element.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public void waitForElementPresentShort(WebElement element) {
		waitForElementPresent(element, Constant.SHORT_TIMEOUT, driver);
	}

	/**
	 * Check if the success message disappears after a certain timeout.
	 */
	public void isSuccessMessageDisappear() {
		Wait<WebDriver> wait = new FluentWait<>(driver)
						.withTimeout(Duration.ofSeconds(Constant.MEDIUM_TIMEOUT))
						.pollingEvery(Duration.ofMillis(Constant.SHORT_TIMEOUT));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpathSuccessMessage)));
	}

	public String obtainCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public void mouseOver(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}

	public String generateNewEmail(String steps) {
		return "automation+" + steps + getTimeStamp() + "@gmail.com";
	}

	/**
	 * this method is used to select a random element from the list
	 *
	 * @param elements the list of elements
	 */
	public void selectARandomElementFromDropdownList(List<WebElement> elements) {
		elements.get(random.nextInt(elements.size())).click();
	}

	/**
	 * Round a Double to N Decimal places
	 *
	 * @param length is the length to want to arrive.
	 * @param number is the number to be formatted
	 */
	public static double roundNumber(String length, Double number) {
		DecimalFormat format = new DecimalFormat("#." + length);
		return Double.parseDouble(format.format(number));
	}

	/**
	 * this method is used to generate a random number
	 *
	 * @return a random number
	 */
	public static String randomNegativeNumber() {
		double negativeNumber = -random.nextDouble() * 100; // Generates a negative double number between -0.0 and -100.0
		String format = String.format("%.2f", negativeNumber);
		return String.valueOf(format);
	}

	/**
	 * A function that wraps given JSON objects into a list converts it to JSON format, and removes square brackets.
	 *
	 * @param jsons array of JSON objects to be wrapped
	 * @return JSON string without square brackets
	 */
	public static String wrapJsons(Object[] jsons) {
		List<Object> wrappedJsons = new ArrayList<>();
		wrappedJsons.addAll(Arrays.asList(jsons));
		String json = new Gson().toJson(wrappedJsons).replaceAll("^\\[|\\]$", "");
		return json;
	}
	/**
	 * This getPassword method gets the map of environment variables defined in the system
	 *
	 * @param key it's the key of the password
	 * @return the value of the password defined on the environment variables
	 */
	public static String getPassword(String key) {
		return System.getenv().get(key);
	}
}
