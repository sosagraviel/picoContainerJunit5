package goEventProject.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class MainPage extends BasePage{
    public MainPage(WebDriver remoteDriver){
        driver = remoteDriver;
        initElements(driver, this);

    }
    @FindBy(xpath = "//img[@crossorigin='anonymous']")
    WebElement userAvatar;
    public WebElement getUserAvatar(){
        waitForAWebElementToFullyLoad(userAvatar, 15, 3);
        return userAvatar;
    }
    @FindBy(linkText = "Home")
    WebElement homeLink;
    public WebElement getHomeLink(){
        waitForAWebElementToFullyLoad(homeLink, 15, 3);
        return homeLink;
    }
    @FindBy(xpath = "//a[@href='/events/create']")
    WebElement createEventLink;
    public WebElement getCreateEventLink(){
        return createEventLink;
    }
    @FindBy(xpath = "//a[@href='/profile']")
    WebElement myProfileLink;
    public WebElement getMyProfileLink(){
        return myProfileLink;
    }

}
