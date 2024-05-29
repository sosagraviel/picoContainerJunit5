package LocatorsAndFunctions.Intermediate;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class JavaScriptExecutorExample {
    @Test
    public void scrollPageTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://seleniumjavalocators.neocities.org/pages/scroll-page");

        WebElement sectionTwentyFour = driver.findElement(By.xpath("//h2[contains(text(),'Sección 24')]"));

        Thread.sleep(3000);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView();", sectionTwentyFour);

        Thread.sleep(1000);
        System.out.println(sectionTwentyFour.getText());

        driver.close();
    }
}
