package Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v129.network.Network;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Optional;

public class WaitsInSelenium {
	WebDriver driver;
	String url = "https://seleniumjavalocators.neocities.org/";

	@BeforeMethod
	public void setUp() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		driver = new ChromeDriver(options);

		Utilities.Utilities.devToolsCreateSession(driver);
		Utilities.Utilities.setMobileMetrics(driver, 768, 858);

		driver.get(url);

	}

	@Test
	public void esperasTestSinMaximizar() {
		//ESPERA IMPLICITA
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebElement button = driver.findElement(By.tagName("button"));
		button.click();

		WebElement loginLink = driver.findElement(By.id("loginLink"));
		String loginLinkText = loginLink.getText();
		String loginLinkTagName = loginLink.getTagName();

		System.out.println("Texto: " + loginLinkText);
		System.out.println("Etiqueta HTML: " + loginLinkTagName);

		loginLink.click();

		driver.close();

	}

	@Test
	public void esperasTestExplicitaSinMaximizar() {

		//ESPERA EXPLICITA
		Wait<WebDriver> waitDropDown = new WebDriverWait(driver, Duration.ofSeconds(5));

		WebElement button = waitDropDown.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.tagName("button"))));
		//ELEMENTO---------ESPERAHASTA-CLASEEXPCOND-------CONDICION-------------ELEMENTO-----------------------------------

		button.click();

		//ESPERA EXPLICITA
		Wait<WebDriver> waitLoginLink = new WebDriverWait(driver, Duration.ofSeconds(4));

		WebElement loginLink = waitLoginLink.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("loginLink"))));
		String loginLinkText = loginLink.getText();
		String loginLinkTagName = loginLink.getTagName();

		System.out.println("Texto: " + loginLinkText);
		System.out.println("Etiqueta HTML: " + loginLinkTagName);

		loginLink.click();

		driver.close();

	}


	/**
	 * this is an example of a fluent wait test in selenium with a maximum wait time of 10 seconds
	 */
	@Test
	public void fluentWaitTest() {
		//ESPERA Fluent
		Wait<WebDriver> fluentWait = new FluentWait<>(driver)
						.withTimeout(Duration.ofSeconds(10))
						.pollingEvery(Duration.ofMillis(500))
						.ignoring(NoSuchElementException.class);

		WebElement button = fluentWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.tagName("button"))));
		//ELEMENTO---------ESPERAHASTA-CLASEEXPCOND-------CONDICION-------------ELEMENTO-----------------------------------

		button.click();

		//ESPERA EXPLICITA
		Wait<WebDriver> waitLoginLink = new FluentWait<>(driver)
						.withTimeout(Duration.ofSeconds(10))
						.pollingEvery(Duration.ofMillis(500))
						.ignoring(NoSuchElementException.class);

		WebElement loginLink = waitLoginLink.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("loginLink"))));
		String loginLinkText = loginLink.getText();
		String loginLinkTagName = loginLink.getTagName();

		System.out.println("Texto: " + loginLinkText);
		System.out.println("Etiqueta HTML: " + loginLinkTagName);

		loginLink.click();

		driver.close();

	}
    @Test
    public void monitorNetworkRequests() {
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.requestWillBeSent(), request -> System.out.println("Request: " + request.getRequest().getUrl()));
        devTools.addListener(Network.responseReceived(), response -> {
            System.out.println("Response: " + response.getResponse().getStatus());
        });
        devTools.addListener(Network.requestWillBeSent(), request -> {
            if (request.getRequest().getMethod().equals("POST")) {
                System.out.println("POST request sent to " + request.getRequest().getUrl());
            } else if (request.getRequest().getMethod().equals("GET")) {
                System.out.println("GET request sent to " + request.getRequest().getUrl());
            } else {
                System.out.println("Other request method sent to " + request.getRequest().getUrl());
            }
        });

        driver.get(url);
        driver.close();
  }
}

