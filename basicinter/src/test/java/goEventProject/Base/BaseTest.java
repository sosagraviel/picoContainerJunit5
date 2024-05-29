package goEventProject.Base;

import goEventProject.Pages.BasePage;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import goEventProject.Utilities.Constants;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import goEventProject.Utilities.Utilities;

public class BaseTest extends BasePage {
    @BeforeTest
    public void setUpProps() throws IOException {
        setProps();

    }
    @BeforeMethod
    public void setUp(){
        utilities = new Utilities(driver);

        chromeOptions = new ChromeOptions();

        addOptionsArgumentsForBrowser(chromeOptions);
        setExperimentalOptions(chromeOptions);

        driver = new ChromeDriver(chromeOptions);
        initElements(driver, this);

        driver.get(Constants.URL);

    }
    @AfterMethod
    public void closeDriver() throws InterruptedException {
        Thread.sleep(100);
        if (driver != null) {
            driver.quit();

        }

    }
    public void addOptionsArgumentsForBrowser(ChromeOptions aChromeDriverOptions) {
        aChromeDriverOptions.addArguments(Constants.START_MAXIMIZED, Constants.DISABLE_INFOBARS, Constants.DISABLE_EXTENSIONS, Constants.DISABLE_NOTIFICATIONS);

    }
    public void setExperimentalOptions(ChromeOptions aChromeDriverOptions) {
        Map<String, Object> preferences = setHashMapExperimentalOptions();

        aChromeDriverOptions.setExperimentalOption(Constants.EXCLUDE_SWITCHES, Collections.singletonList(Constants.ENABLE_AUTOMATION));
        aChromeDriverOptions.setExperimentalOption(Constants.HASHMAP_PREFERENCES, preferences);
        aChromeDriverOptions.setAcceptInsecureCerts(true);

    }
    public Map<String, Object> setHashMapExperimentalOptions(){
        Map<String, Object> prefs = new HashMap<String, Object>();

        prefs.put(Constants.CREDENTIALS_ENABLE_SERVICE, false);
        prefs.put(Constants.PROFILE_PASSWORD_MANAGER_ENABLED, false);

        return prefs;

    }
    public void addOptionsArgumentsForHeadlessRun(ChromeOptions aChromeDriverOptions) {
        aChromeDriverOptions.addArguments(Constants.HEADLESS, Constants.DISABLE_GPU, Constants.WINDOW_SIZE_FULLSCREEN, Constants.IGNORE_CERTIFICATE_ERROR,
                Constants.DISABLE_EXTENSIONS, Constants.NO_SANDBOX, Constants.DISABLE_DEV_SHM_USAGE);
        aChromeDriverOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);

    }

}
