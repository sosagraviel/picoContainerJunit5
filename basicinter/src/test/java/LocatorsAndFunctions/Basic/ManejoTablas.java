package LocatorsAndFunctions.Basic;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class ManejoTablas {
	WebDriver driver;
	String url = "https://demo.guru99.com/test/table.html";
	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get(url);
		
	}
	
	@Test
	public void downloadFile() {
		String elementoABuscar = "6";
		
		List<WebElement> tableList = driver.findElements(By.xpath("//table[@cellspacing='1']//tr//td"));
		
		int i = 0;
		for(WebElement element : tableList) {
			String elementText = element.getText();
			if(elementText.equals(elementoABuscar)) {
				i++;
				System.out.println("valor: " + elementText + ", iterador: " + i);
				
			}
			
		}
		Assert.assertEquals(i, 3);
		System.out.println("Elementos con el " + elementoABuscar + ": " + i);
		
	}
	
	@AfterMethod
	public void finTest(ITestContext context) throws IOException {
		driver.close();
		
	}
}
