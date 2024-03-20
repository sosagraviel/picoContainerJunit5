package LocatorsAndFunctions.Intermediate;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Utilities.Utilities;

public class DownloadFile {
	WebDriver driver;
	String url = "https://demo.guru99.com/test/yahoo.html";
	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get(url);
		
	}
	
	@Test
	public void downloadFile() {
		WebElement messengerDownload = driver.findElement(By.id("messenger-download"));
		String messengerDownloadHref = messengerDownload.getAttribute("href");
		System.out.println(messengerDownloadHref);
		
		String wget_command = 
        		"cmd /c D:\\EducacionIT-70885\\ProyectoIntegrador\\src\\test\\resources\\wget.exe -P D:\\EducacionIT-70885\\ProyectoIntegrador\\src\\test\\resources\\downloads\\ --no-check-certificate " + messengerDownloadHref;
		
		Utilities.downloadFile(wget_command);

	}
	
	@AfterMethod
	public void finTest(ITestContext context) throws IOException, InvalidFormatException {
		driver.close();
		
	}
}
