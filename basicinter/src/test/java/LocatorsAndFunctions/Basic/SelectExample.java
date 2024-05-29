package LocatorsAndFunctions.Basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class SelectExample {
    WebDriver driver;
    String url = "https://seleniumjavalocators.neocities.org/";
    @Test
    public void usoSelect() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(url);

        WebElement fechasLink = driver.findElement(By.xpath("//a[@href='./pages/fechas.html']"));
        fechasLink.click();

        WebElement dia = driver.findElement(By.id("dia"));
        Select diaDropDown = new Select(dia);
        diaDropDown.selectByValue("14");

        WebElement mes = driver.findElement(By.id("mes"));
        Select mesDropDown = new Select(mes);
        mesDropDown.selectByVisibleText("Julio");

        WebElement anio = driver.findElement(By.id("año"));
        Select anioDropDown = new Select(anio);
        anioDropDown.selectByIndex(3);

        WebElement mostrarFechaBtn = driver.findElement(By.id("mostrarFechaBtn"));
        mostrarFechaBtn.click();

        WebElement textoFecha = driver.findElement(By.id("fechaNacimiento"));
        System.out.println(textoFecha.getText());

        driver.close();

    }
}
