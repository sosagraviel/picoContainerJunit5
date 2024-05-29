package goEventProjectRaw.Test.Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class UnsuccessLoginTest {
    WebDriver driver;
    String url = "https://goevent-platform.vercel.app/";
    @Test
    public void incorrectPasswordUserLoginTest() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement loginBtn = driver.findElement(By.xpath("//a[@href='/sign-in']"));
        loginBtn.click();

        WebElement emailAddressOrUserInput = driver.findElement(By.id("identifier-field"));
        emailAddressOrUserInput.sendKeys("testing.goevent@gmail.com");

        WebElement continueToPasswordBtn = driver.findElement(By.xpath("//button[@data-localization-key='formButtonPrimary']"));
        continueToPasswordBtn.click();

        WebElement emailSetted = driver.findElement(By.xpath("//p[@class='cl-identityPreviewText \uD83D\uDD12\uFE0F cl-internal-1ptfnbv']"));
        Assert.assertEquals(emailSetted.getText(),"testing.goevent@gmail.com" );

        WebElement passwordInput = driver.findElement(By.id("password-field"));
        passwordInput.sendKeys("incorrect");

        WebElement continueToLoginBtn = driver.findElement(By.xpath("//button[@data-localization-key='formButtonPrimary']"));
        continueToLoginBtn.click();

        List<WebElement> errorMessage = driver.findElements(By.id("error-password"));

        Wait<WebDriver> waitErrorMessage = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errmsg = waitErrorMessage.until(ExpectedConditions.visibilityOf(errorMessage.get(1)));

        Assert.assertEquals(errmsg.getText(), "Password is incorrect. Try again, or use another method.", "Error message is not found or not as expected");

        WebElement useAnotherMethodLink = driver.findElement(By.xpath("//a[@data-localization-key='signIn.password.actionLink']"));
        Assert.assertEquals(useAnotherMethodLink.getText(), "Use another method", "Error message is not found or not as expected");

        driver.close();

    }
    @Test
    public void nonExistentUserLoginTest() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement loginBtn = driver.findElement(By.xpath("//a[@href='/sign-in']"));
        loginBtn.click();

        WebElement emailAddressOrUserInput = driver.findElement(By.id("identifier-field"));
        emailAddressOrUserInput.sendKeys("nonexistentuser@gmail.com");

        WebElement continueToPasswordBtn = driver.findElement(By.xpath("//button[@data-localization-key='formButtonPrimary']"));
        continueToPasswordBtn.click();

        List<WebElement> errorMessage = driver.findElements(By.id("error-identifier"));

        Wait<WebDriver> waitErrorMessage = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errmsg = waitErrorMessage.until(ExpectedConditions.visibilityOf(errorMessage.get(1)));

        Assert.assertEquals(errmsg.getText(), "Couldn't find your account.", "Error message is not found or not as expected");

        driver.close();

    }

}
