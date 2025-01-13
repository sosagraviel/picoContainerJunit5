package clubAdministracion.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage{
    public MainPage(WebDriver remoteDriver){
        driver = remoteDriver;
        initElements(driver, this);

    }
    @FindBy(xpath = "//*[@src='../../../assets/img/qubika.png']")
    WebElement qubikaImage;
    public WebElement getQubikaImage(){
        waitForAWebElementToFullyLoad(qubikaImage, 15, 3);
        return qubikaImage;
    }

}
