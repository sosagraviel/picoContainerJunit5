package LocatorsAndFunctions.Basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.List;

public class Functionalities {
        WebDriver driver;
        @Test
        public void chromeTest() {
            driver = new ChromeDriver();

            driver.get("https://seleniumjavalocators.neocities.org/");
            System.out.println("Titulo de la pagina: "  + driver.getTitle());
            System.out.println("URL: " + driver.getCurrentUrl());

            driver.close();
        }
        @Test
        public void firefoxTest() {
            WebDriver driver = new FirefoxDriver();

            driver.get("https://seleniumjavalocators.neocities.org/");
            System.out.println("Titulo de la pagina: "  + driver.getTitle());
            System.out.println("URL: " + driver.getCurrentUrl());

            driver.close();
        }
        @Test
        public void navigateAndFindElements() throws InterruptedException {
            WebDriver driver = new ChromeDriver();

            driver.get("https://seleniumjavalocators.neocities.org/");

            System.out.println("Titulo de la pagina: "  + driver.getTitle());
            System.out.println("URL: " + driver.getCurrentUrl());

            driver.manage().window().maximize();
            driver.navigate().to("https://seleniumjavalocators.neocities.org/pages/fechas");
            System.out.println("URL al usar navigate.to: " + driver.getCurrentUrl());

            driver.navigate().back();
            System.out.println("URL al usar navigate.back: " + driver.getCurrentUrl());

            driver.navigate().forward();
            System.out.println("URL al usar navigate.forward: " + driver.getCurrentUrl());

            WebElement tituloH2 = driver.findElement((By.tagName("h2")));
            System.out.println(tituloH2.getText());

            driver.navigate().back();
            System.out.println("URL al usar navigate.back: " + driver.getCurrentUrl());

            List<WebElement> listaParrafos = driver.findElements((By.tagName("p")));

            for(WebElement parrafo : listaParrafos) {
                System.out.println(parrafo.getText());
            }

            driver.close();
        }
}
