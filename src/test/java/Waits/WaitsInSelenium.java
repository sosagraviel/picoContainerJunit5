package Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class WaitsInSelenium {
    WebDriver driver;
    String url = "https://seleniumjavalocators.neocities.org/";
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();

        Utilities.Utilities.devToolsCreateSession(driver);
        Utilities.Utilities.setMobileMetrics(driver, 768, 858);

        driver.get(url);

    }
    @Test
    public void esperasTestSinMaximizar() {
        //ESPERA IMPLICITA
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

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
}

