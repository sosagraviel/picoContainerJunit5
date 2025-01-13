package clubAdministracionRaw.Test.Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SuccessLoginTest {
    WebDriver driver;
    String url = "https://club-administration.qa.qubika.com/#/auth/login";
    @Test
    public void successfulUserLoginWithUsernameTest(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement emailAddressOrUserInput = driver.findElement(By.xpath("//*[@type='email']"));
        emailAddressOrUserInput.sendKeys("test.qubika");


        WebElement passwordInput = driver.findElement(By.xpath("//*[@type='password']"));
        passwordInput.sendKeys("12345678");

        WebElement continueToLoginBtn = driver.findElement(By.xpath("//*[@type='submit']"));
        continueToLoginBtn.click();

        WebElement qubikaImage = driver.findElement(By.xpath("//*[@src='../../../assets/img/qubika.png']"));
        Assert.assertTrue(qubikaImage.isDisplayed());

        driver.close();
    }
    @Test
    public void successfulUserLoginwithEmailTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement emailAddressOrUserInput = driver.findElement(By.xpath("//*[@type='email']"));
        emailAddressOrUserInput.sendKeys("test.qubika@qubika.com");


        WebElement passwordInput = driver.findElement(By.xpath("//*[@type='password']"));
        passwordInput.sendKeys("12345678");

        WebElement continueToLoginBtn = driver.findElement(By.xpath("//*[@type='submit']"));
        continueToLoginBtn.click();

        WebElement qubikaImage = driver.findElement(By.xpath("//*[@src='../../../assets/img/qubika.png']"));
        Assert.assertTrue(qubikaImage.isDisplayed());

        driver.close();
    }

}
