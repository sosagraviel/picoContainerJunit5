package TestNGFramework;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class DataProviderUsage {
    String url = "http://www.automationpractice.pl/";
    Faker faker;
    public WebDriver driver;
    @BeforeSuite
    public void inicioSuite() {
        System.out.println("Inicio de la suite de pruebas");
    }
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        faker = new Faker();

        driver.get(url);
        driver.manage().window().maximize();
    }
    @Test(dataProvider="usuariosInvalidos", dataProviderClass = DataProviderExample.class)
    public void inicioSesionInvalido(String email, String password) {
        WebElement loginBtn = driver.findElement(By.className("login"));
        loginBtn.click();

        WebElement emailLoginInput = driver.findElement(By.id("email"));
        emailLoginInput.sendKeys(email);

        WebElement passwordLoginInput = driver.findElement(By.id("passwd"));
        passwordLoginInput.sendKeys(email);

        WebElement signinBtn = driver.findElement(By.id("SubmitLogin"));
        signinBtn.click();

        WebElement authenticationTitle = driver.findElement(By.className("page-heading"));
        Assert.assertEquals(authenticationTitle.getText(), "AUTHENTICATION");

    }
    @Test(dataProvider="usuariosInvalidosExcel", dataProviderClass = DataProviderExample.class)
    public void inicioSesionInvalidoExcel(String email, String password) {
        WebElement loginBtn = driver.findElement(By.className("login"));
        loginBtn.click();

        WebElement emailLoginInput = driver.findElement(By.id("email"));
        emailLoginInput.sendKeys(email);

        WebElement passwordLoginInput = driver.findElement(By.id("passwd"));
        passwordLoginInput.sendKeys(email);

        WebElement signinBtn = driver.findElement(By.id("SubmitLogin"));
        signinBtn.click();

        WebElement authenticationTitle = driver.findElement(By.className("page-heading"));
        Assert.assertEquals(authenticationTitle.getText(), "AUTHENTICATION");

    }
    @AfterMethod
    public void finTest(){
        driver.close();

    }

}
