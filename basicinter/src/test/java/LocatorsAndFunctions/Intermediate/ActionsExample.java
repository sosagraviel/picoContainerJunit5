package LocatorsAndFunctions.Intermediate;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SourceType;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ActionsExample {
    @Test
    public void dragAndDropTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://seleniumjavalocators.neocities.org/pages/drag-and-drop");

        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement container = driver.findElement(By.id("container"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(draggable, container).perform();

        WebElement draggableInContainer = container.findElement(By.id("draggable"));
        Assert.assertNotNull(draggableInContainer, "El elemento arrastrable no se encuentra en el contenedor");

        driver.close();
    }
    @Test
    public void doubleClickTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://seleniumjavalocators.neocities.org/pages/actions");

        WebElement dclick = driver.findElement(By.id("dclick"));

        Actions actions = new Actions(driver);
        actions.doubleClick(dclick).perform();

        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());

        Assert.assertEquals(alert.getText(), "¡Doble clic detectado!");
        alert.accept();

        driver.close();
    }
    @Test
    public void contextClickTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://seleniumjavalocators.neocities.org/pages/actions");

        WebElement cclick = driver.findElement(By.id("cclick"));

        Actions actions = new Actions(driver);
        actions.contextClick(cclick).perform();

        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());

        Assert.assertEquals(alert.getText(), "¡Popup abierto con click derecho!");
        alert.accept();

        driver.close();
    }
    @Test
    public void moveToElementTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://seleniumjavalocators.neocities.org/pages/actions");

        WebElement textElement = driver.findElement(By.className("text-hover"));

        String textElementColorAttribute = textElement.getCssValue("color");
        String textElementColorAttributeHex = Color.fromString(textElementColorAttribute).asHex();

        Assert.assertEquals(textElementColorAttributeHex, "#000000");
        System.out.println(textElementColorAttributeHex);

        Thread.sleep(3000);

        Actions actions = new Actions(driver);
        actions.moveToElement(textElement).click().perform();

        Thread.sleep(3000);

        WebElement textElementOnHover = driver.findElement(By.className("text-hover"));
        String textElementColorAttributeOnHover = textElementOnHover.getCssValue("color");
        String textElementColorAttributeOnHoverHex = Color.fromString(textElementColorAttributeOnHover).asHex();

        Assert.assertEquals(textElementColorAttributeOnHoverHex, "#ff0000");
        System.out.println(textElementColorAttributeOnHoverHex);

        driver.close();
    }
}
