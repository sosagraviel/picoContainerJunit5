package clubAdministracionRaw.Test.Login;

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
    String url = "https://club-administration.qa.qubika.com/#/auth/login";
    @Test
    public void incorrectPasswordUserLoginTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement emailAddressOrUserInput = driver.findElement(By.xpath("//*[@type='email']"));
        emailAddressOrUserInput.sendKeys("test.qubika");


        WebElement passwordInput = driver.findElement(By.xpath("//*[@type='password']"));
        passwordInput.sendKeys("124431234");

        WebElement continueToLoginBtn = driver.findElement(By.xpath("//*[@type='submit']"));
        continueToLoginBtn.click();

        WebElement toastMessage = driver.findElement(By.id("toast-container"));
        Assert.assertEquals(toastMessage.getText(), "Usuario o contraseña incorrectos");

        driver.close();

    }
    @Test
    public void nonExistentUserLoginTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement emailAddressOrUserInput = driver.findElement(By.xpath("//*[@type='email']"));
        emailAddressOrUserInput.sendKeys("test.qu");

        WebElement passwordInput = driver.findElement(By.xpath("//*[@type='password']"));
        passwordInput.sendKeys("12345678");

        WebElement continueToLoginBtn = driver.findElement(By.xpath("//*[@type='submit']"));
        continueToLoginBtn.click();

        WebElement toastMessage = driver.findElement(By.id("toast-container"));
        Assert.assertEquals(toastMessage.getText(), "Usuario o contraseña incorrectos");

        driver.close();

    }

}
