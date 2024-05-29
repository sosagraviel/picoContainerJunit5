package Utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtilities {
	/**
	 * Espera por la visibilidad de los elementos.
	 * 
	 * @param driver WebDriver de Selenium
	 * @param seconds int tiempo en segundos.
	 * @param webElementToBeVisible WebElement elemento sobre lo que se desea esperar.
	 *
	 * @return WebElement elemento con la espera ya realizada.
	 */
	public static WebElement waitForVisibility(WebDriver driver, int seconds, WebElement webElementToBeVisible) {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.visibilityOf(webElementToBeVisible));
		
	}
	public static WebElement waitForClickability(WebDriver driver, int seconds, WebElement webElementToBeClickable) {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.elementToBeClickable(webElementToBeClickable));
		
	}
}
