package utils;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
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
import utils.constants.DataConstantQueries;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static utils.constants.DataConstant.PLATFORM_GOAL_EXECUTION_TEST;

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
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable-browser-side-navigation");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-gpu");
		if (!getEnvironmentVariable(PLATFORM_GOAL_EXECUTION_TEST).equalsIgnoreCase(localEnvironment)) {
			options.addArguments("--headless");
		}
		options.addArguments("window-size=1980,1080");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-setuid-sandbox");
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
	 * Gets the current timestamp as a string.
	 *
	 * @return the current timestamp as a string
	 */
	public static String getTimeStamp() {
		Date date = new Date();
		return String.valueOf(date.getTime());
	}

	public String generateEmailQuery(String hhPath) {
		return DataConstantQueries.GET_EMAILS_QUERY_PREFIX + hhPath + DataConstantQueries.GET_EMAILS_QUERY;
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

	public void waitForElementPresentLongest(WebElement element) {
		waitForElementPresent(element, Constant.LONGEST_TIMEOUT, driver);
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
