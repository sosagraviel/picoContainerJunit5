package goEventProject.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;

public class LandingPage extends BasePage {
    public LandingPage(WebDriver remoteDriver){
        driver = remoteDriver;
        initElements(driver, this);

    }
    @FindBy(xpath = "//a[@href='/sign-in']")
    WebElement loginBtn;
    public LoginPage clickOnLoginBtn(){
        waitForAWebElementToBeClickable(loginBtn,15, 3);
        loginBtn.click();

        loginPage = new LoginPage(driver);
        return loginPage;
    }

}
