package TestNGFramework;

import Utilities.WaitUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class CrossBrowserTesting {
    String url = "http://www.automationpractice.pl/";
    WebDriver driver;

    @BeforeTest
    @Parameters("navegador")
    public void setUp(@Optional("chrome")String navegador) {
        if(navegador.equalsIgnoreCase("chrome")) {
            System.out.println("Inicio de pruebas en Chrome");
            driver = new ChromeDriver();

        }
        if(navegador.equalsIgnoreCase("firefox")) {
            System.out.println("Inicio de pruebas en Firefox");
            driver = new FirefoxDriver();

        }
        driver.get(url);
        driver.manage().window().maximize();

    }
    @Test
    public void searchItem() throws InterruptedException {
        WebElement searchItemInput = driver.findElement((By.id("search_query_top")));
        searchItemInput.sendKeys("t-shirts");

        WebElement searchButton = driver.findElement(By.name("submit_search"));
        searchButton.click();

        WebElement searchResult = WaitUtilities.waitForVisibility(driver, 10, driver.findElement(By.tagName("h1")));
        System.out.println(searchResult.getText());

    }
    @AfterTest
    public void tearDown() {
        System.out.println("Fin de pruebas");
        driver.close();

    }
}