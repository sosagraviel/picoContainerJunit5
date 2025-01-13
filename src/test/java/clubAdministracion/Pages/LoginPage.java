package clubAdministracion.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver remoteDriver){
        driver = remoteDriver;
        initElements(driver, this);

    }
    @FindBy(xpath = "//*[@type='email']")
    WebElement emailAddressOrUserInput;
    public void setAddressOrUserInput(String email){
        waitForAWebElementToFullyLoad(emailAddressOrUserInput, 15, 3);
        emailAddressOrUserInput.sendKeys(email);
    }
    @FindBy(xpath = "//*[@type='submit']")
    WebElement continueBtn;
    public WebElement getContinueBtn(){
        return continueBtn;
    }
    public MainPage clickOnContinueBtn(){
        this.getContinueBtn().click();

        mainPage = new MainPage(driver);
        return mainPage;

    }
    @FindBy(xpath = "//*[@type='password']")
    WebElement passwordInput;
    public void setPasswordInput(String password){
        passwordInput.sendKeys(password);

    }
    @FindBy(id = " customCheckLogin")
    WebElement rememberMeInput;
    private WebElement getRememberMeInput(){
        return rememberMeInput;
    }
    public void clickOnRememberMeInput(){
        this.getRememberMeInput().click();

    }
    @FindBy(id = "toast-container")
    WebElement errorMessage;
    public WebElement getErrorMessage(){
        waitForAWebElementToFullyLoad(errorMessage, 15, 1);
        return errorMessage;

    }

}
