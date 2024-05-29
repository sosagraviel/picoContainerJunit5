package LocatorsAndFunctions.Intermediate;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IframeExample {
    @Test
    public void iframeTest(){
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String email = faker.internet().emailAddress();
        String username = faker.name().username();
        String password = faker.internet().password();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://seleniumjavalocators.neocities.org/pages/page-with-iframe");

        driver.switchTo().frame("outer-iframe");

        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("confirmPassword")).sendKeys(password);

        driver.findElement(By.id("mostrarMensajeBtn")).click();

        WebElement message = driver.findElement(By.id("mensajeInicioSesion"));
        String messageText = message.getText();

        Assert.assertEquals(messageText, "Intentaste registrarte con nombre " + "'" + name +
                                                  "', usuario '" + username + "' y correo electronico '" + email + "'.");
        System.out.println(messageText);

        driver.close();
    }
    @Test
    public void iframeInsideIframeTest(){
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String email = faker.internet().emailAddress();
        String username = faker.name().username();
        String password = faker.internet().password();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://seleniumjavalocators.neocities.org/pages/iframe-of-iframe");

        driver.switchTo().frame("outer-iframe");
        driver.switchTo().frame("regFrame");

        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("confirmPassword")).sendKeys(password);

        driver.findElement(By.id("mostrarMensajeBtn")).click();

        WebElement message = driver.findElement(By.id("mensajeInicioSesion"));
        String messageText = message.getText();

        Assert.assertEquals(messageText, "Intentaste registrarte con nombre " + "'" + name +
                "', usuario '" + username + "' y correo electronico '" + email + "'.");
        System.out.println(messageText);

        driver.close();
    }
}
