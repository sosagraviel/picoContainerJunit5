package LocatorsAndFunctions.Basic;

import TestNGFramework.CustomListener;
import TestNGFramework.CustomReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
//@Listeners(CustomListener.class)
@Listeners({CustomReporter.class, CustomListener.class})
public class Locators {
    WebDriver driver;
    String neocitiesUrl = "https://seleniumjavalocators.neocities.org/";
    @Test
    public void idLocator()  {
        driver = new ChromeDriver();

        driver.get(neocitiesUrl);//va a la url indicada por parametro
        driver.manage().window().maximize();//maximiza la pantalla

        //id
        WebElement loginLink = driver.findElement(By.id("loginLink"));
        String loginLinkText = loginLink.getText();
        String loginLinkTagName = loginLink.getTagName();

        System.out.println("Texto: " + loginLinkText);
        System.out.println("Etiqueta HTML: " + loginLinkTagName);

        loginLink.click();

        WebElement titulo = driver.findElement(By.id("titulo"));
        System.out.println(titulo.getText());

        WebElement usernameInput = driver.findElement(By.id("username_id"));
        usernameInput.sendKeys("JuanPablo1092");

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("pepe32003");

        WebElement inicioSesionBtn = driver.findElement(By.id("iniciarSesionBtn"));
        //inicioSesionBtn.click();
        inicioSesionBtn.sendKeys(Keys.ENTER);

        WebElement mensajeInicioSesion = driver.findElement(By.id("mensajeInicioSesion"));
        System.out.println(mensajeInicioSesion.getText());

        usernameInput.clear();
        passwordInput.clear();

        usernameInput.sendKeys("JuanSanchez31");
        passwordInput.sendKeys("Juani1123");

        inicioSesionBtn.click();

        WebElement mensajeInicioSesionDespuesDeClear = driver.findElement(By.id("mensajeInicioSesion"));
        System.out.println(">>> " + mensajeInicioSesionDespuesDeClear.getText());

        driver.close();
    }
    @Test
    public void locatorNameClassNameTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(neocitiesUrl);

        List<WebElement> contenidoSectionList = driver.findElements(By.className("contenido-section"));
        System.out.println("Cantidad de elementos contenido-section: " + contenidoSectionList.size());

        //WebElement contenidoSectionSegundo = contenidoSectionList.get(1);
        //System.out.println(contenidoSectionSegundo.getText());

        WebElement loginLink = driver.findElement(By.id("loginLink"));
        loginLink.click();

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("NombreCompleto");

        WebElement passwordInput = driver.findElement(By.name("passwd"));
        passwordInput.sendKeys("321Password123");

        WebElement mostrarMensajeBtn = driver.findElement(By.id("iniciarSesionBtn"));
        mostrarMensajeBtn.click();

        WebElement mensajeInicioSesion = driver.findElement(By.className("mensaje"));
        System.out.println(mensajeInicioSesion.getText());

        driver.close();

    }
    @Test
    public void locatorLinkTextTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(neocitiesUrl);

        /*
         * <a class="nav-link" href="./pages/registro.html">Registro</a>
         *
         * */

        WebElement registroLink = driver.findElement(By.linkText("Registro"));
        registroLink.click();

        WebElement titulo = driver.findElement(By.id("titulo"));
        System.out.println(titulo.getText());

        driver.close();

    }
    @Test
    public void locatorPartialLinkTextTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(neocitiesUrl);

        /*
         * <a class="nav-link" href="https://productsapi-six.vercel.app/">Catalogo de Productos</a>
         *
         * */

        WebElement catalogoLink = driver.findElement(By.partialLinkText("Catalogo"));
        catalogoLink.click();

        WebElement titulo = driver.findElement(By.tagName("h1"));
        System.out.println(titulo.getText());

        driver.close();

    }

    @Test
    public void xpathTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(neocitiesUrl);


        WebElement linksUtilesLink = driver.findElement(By.xpath("//a[@href='./pages/linksutiles']"));
        linksUtilesLink.click();

        WebElement titulo = driver.findElement(By.tagName("h1"));
        System.out.println(titulo.getText());

        driver.close();

    }
    @Test
    public void xpathContainsTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(neocitiesUrl);

        WebElement seccionTitulo = driver.findElement(By.xpath("//*[contains(text(),'¿Qué es el Control')]"));
        String secciontTituloText = seccionTitulo.getText();
        System.out.println(secciontTituloText);


        WebElement catalogoLink = driver.findElement(By.xpath("//*[contains(@href,'products')]"));
        catalogoLink.click();

        WebElement titulo = driver.findElement(By.tagName("h1"));
        System.out.println(titulo.getText());

        driver.close();

    }
    @Test
    public void xpathOrAndTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(neocitiesUrl);

        List<WebElement> imagenesBckWhtList = driver.findElements(By.xpath("//img[@src='https://picsum.photos/id/41/320/240' or @alt ='third']"));
        System.out.println("XPATH OR ejemplo cantidad de elementos: " + imagenesBckWhtList.size());

        WebElement loginLinkXpathBtn = driver.findElement(By.xpath("//a[@class='nav-link' and @id='loginLink']"));
        System.out.println("XPATH AND ejemplo: " + loginLinkXpathBtn.getText());

        driver.close();

    }
    @Test
    public void xpathTextTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(neocitiesUrl);

        WebElement seleniumLocatorPractice = driver.findElement(By.xpath("//*[text()='Selenium Locator Practice']"));
        System.out.println(seleniumLocatorPractice.getText());


        driver.close();

    }
    @Test
    public void cssSelectorTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(neocitiesUrl);

        WebElement loginLinkByCss = driver.findElement(By.cssSelector("#loginLink"));
        System.out.println(loginLinkByCss.getText());

        WebElement navBar = driver.findElement(By.cssSelector("nav.navbar.navbar-expand-lg.bg-body-tertiary"));
        System.out.println(navBar.getText());

        WebElement linksUtilesLink = driver.findElement(By.cssSelector("a[href='./pages/linksutiles']"));
        linksUtilesLink.click();

        WebElement titulo = driver.findElement(By.tagName("h1"));
        System.out.println(titulo.getText());

        driver.close();

    }
}
