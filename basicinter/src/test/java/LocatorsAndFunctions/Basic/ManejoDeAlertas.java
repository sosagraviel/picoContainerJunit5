package LocatorsAndFunctions.Basic;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.*;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;


public class ManejoDeAlertas {
	WebDriver driver;
	Faker faker;
	String url = "https://seleniumjavalocators.neocities.org/pages/alerts";
	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get(url);
		
	}
	@Test
	public void alertTest() {
		WebElement alertButton = driver.findElement(By.id("alertButton"));
		alertButton.click();

		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());

		Assert.assertEquals(alert.getText(), "You clicked a button");

		alert.accept();

	}
	@Test
	public void alertDelayedTest() {
		WebElement alertButton = driver.findElement(By.id("timerAlertButton"));
		alertButton.click();

		Wait<WebDriver> waitAlert = new WebDriverWait(driver, Duration.ofSeconds(10));
		waitAlert.until(ExpectedConditions.alertIsPresent());

		Alert alert = driver.switchTo().alert();

		System.out.println(alert.getText());
		Assert.assertEquals(alert.getText(), "This alert appeared after 5 seconds");

		alert.accept();

	}
	@Test
	public void alertConfirmTest() {
		WebElement alertButton = driver.findElement(By.id("confirmButton"));
		alertButton.click();

		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());

		Assert.assertEquals(alert.getText(), "Do you want to proceed?");

		alert.accept();

		WebElement result = driver.findElement(By.id("resultContainerConfirmButton"));
		System.out.println(result.getText());

		Assert.assertEquals(result.getText(), "You selected OK");

	}
	@Test
	public void alertDismissTest() {
		WebElement alertButton = driver.findElement(By.id("confirmButton"));
		alertButton.click();

		Alert alert = driver.switchTo().alert();
		alert.dismiss();

		WebElement result = driver.findElement(By.id("resultContainerConfirmButton"));
		System.out.println(result.getText());

		Assert.assertEquals(result.getText(), "You selected Cancel");

	}
	@Test
	public void alertPromptTest() {
		faker = new Faker();
		String name = faker.name().firstName();

		WebElement alertButton = driver.findElement(By.id("promptButton"));
		alertButton.click();

		Alert alert = driver.switchTo().alert();

		alert.sendKeys(name);
		alert.accept();

		WebElement result = driver.findElement(By.id("resultContainerPromptButton"));
		System.out.println(result.getText());

		Assert.assertEquals(result.getText(), "You entered '" + name + "'");

	}
	@AfterMethod
	public void finTest() {
		driver.close();
		
	}
}
