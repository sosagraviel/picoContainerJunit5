package LocatorsAndFunctions.Intermediate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DownloadFile {
	WebDriver driver;
	String url = "file:///Users/gravielsosa/Desktop/download.html";

	@BeforeMethod
	public void setUp() {
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "downloads");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		driver.get(url);

	}

	@Test
	public void downloadFile() {
		WebElement messengerDownload = driver.findElement(By.id("downloadButton"));
		String messengerDownloadHref = messengerDownload.getText();
		System.out.println(messengerDownloadHref);
		messengerDownload.click();
	}

	@AfterMethod
	public void finTest(ITestContext context) {
		driver.close();

	}
}
