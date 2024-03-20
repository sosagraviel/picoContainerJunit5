package goEventProject.Pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import goEventProject.Utilities.Utilities;

public class BasePage {
    public ChromeOptions chromeOptions;
    public WebDriver driver;
    public LandingPage landingPage;
    public LoginPage loginPage;
    public MainPage mainPage;
    protected static Utilities utilities;
    public static Properties prop;
    public LandingPage startTest() {
        landingPage = new LandingPage(driver);
        return landingPage;

    }
    public void initElements(WebDriver remoteDriver, Object aPage) {
        PageFactory.initElements(remoteDriver, aPage);

    }
    public static void setProps() throws IOException {
        utilities = new Utilities();

        prop = utilities.init_prop();

    }
    Wait<WebDriver> waitGeneral;
    public Wait<WebDriver> generalFluentWait(long timeToWait, long pollingTime) {
        waitGeneral = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeToWait))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class);

        return waitGeneral;

    }
    Wait<WebDriver> waitParticular;
    public void waitForAListOfWebElementsToFullyLoad(List<WebElement> aListOfWebElements, long timeToWait, long pollingTime) {
        waitParticular = generalFluentWait(timeToWait, pollingTime);
        waitParticular.until(ExpectedConditions.visibilityOfAllElements(aListOfWebElements));

    }
    public void waitForAWebElementToFullyLoad(List<WebElement> aListOfWebElements, int pos, long timeToWait, long pollingTime) {
        waitParticular = generalFluentWait(timeToWait, pollingTime);
        waitParticular.until(ExpectedConditions.visibilityOf(aListOfWebElements.get(pos)));

    }
    public void waitForAWebElementToFullyLoad(WebElement anElement, long timeToWait, long pollingTime) {
        waitParticular = generalFluentWait(timeToWait, pollingTime);
        waitParticular.until(ExpectedConditions.visibilityOf(anElement));

    }
    public void waitForAWebElementToBeClickable(WebElement anElement, long timeToWait, long pollingTime) {
        waitParticular = generalFluentWait(timeToWait, pollingTime);
        waitParticular.until(ExpectedConditions.elementToBeClickable(anElement));

    }
    public void waitForAWebElementToBeInvisible(WebElement anElement, long timeToWait, long pollingTime) {
        waitParticular = generalFluentWait(timeToWait, pollingTime);
        waitParticular.until(ExpectedConditions.invisibilityOf(anElement));

    }
    public void waitForAWebElementToBeClickable(List<WebElement> aListOfWebElements, int pos, long timeToWait, long pollingTime) {
        waitParticular = generalFluentWait(timeToWait, pollingTime);
        waitParticular.until(ExpectedConditions.elementToBeClickable(aListOfWebElements.get(pos)));

    }
    public void waitForAWebElementHaveAnAttribute(WebElement anElement, long timeToWait, long pollingTime, String anAttribute, String aValue) {
        waitParticular = generalFluentWait(timeToWait, pollingTime);
        waitParticular.until(ExpectedConditions.attributeToBe(anElement, anAttribute, aValue));

    }
    public void waitForTextToAppear(WebElement anElement, long timeToWait, long pollingTime, String textToAppear) {
        waitParticular = generalFluentWait(timeToWait, pollingTime);
        waitParticular.until(ExpectedConditions.textToBePresentInElement(anElement, textToAppear));

    }
    public void waitForTextToAppear(List<WebElement> aListOfWebElements, int pos, long timeToWait, long pollingTime, String textToAppear) {
        waitParticular = generalFluentWait(timeToWait, pollingTime);
        waitParticular.until(ExpectedConditions.textToBePresentInElement(aListOfWebElements.get(pos), textToAppear));

    }
}
