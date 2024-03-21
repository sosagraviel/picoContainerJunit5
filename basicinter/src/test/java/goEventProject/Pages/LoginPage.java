package goEventProject.Pages;

import goEventProject.Utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver remoteDriver){
        driver = remoteDriver;
        initElements(driver, this);

    }
    @FindBy(id = "identifier-field")
    WebElement emailAddressOrUserInput;
    public void setAddressOrUserInput(String email){
        waitForAWebElementToFullyLoad(emailAddressOrUserInput, 15, 3);
        emailAddressOrUserInput.sendKeys(email);
    }
    @FindBy(xpath = "//button[@data-localization-key='formButtonPrimary']")
    WebElement continueBtn;
    public MainPage clickOnContinueBtn(boolean redirectsToMain){
        continueBtn.click();
        if(redirectsToMain){
            mainPage = new MainPage(driver);
            return mainPage;

        }else{
            return null;
        }

    }
    @FindBy(xpath = "//p[@class='cl-identityPreviewText \uD83D\uDD12\uFE0F cl-internal-1ptfnbv']")
    WebElement emailSetted;
    public WebElement getEmailSetted(){
        waitForAWebElementToFullyLoad(emailSetted, 15, 3);
        return emailSetted;
    }
    @FindBy(id = "password-field")
    WebElement passwordInput;
    public void setPasswordInput(String password){
        passwordInput.sendKeys(password);
    }
    @FindBy(xpath = "//a[@data-localization-key='signIn.password.actionLink']")
    WebElement useAnotherMethodLink;
    public void clickOnUseAnotherMethodLink(){
        waitForAWebElementToBeClickable(useAnotherMethodLink, 15, 3);
        useAnotherMethodLink.click();
    }
    @FindBy(xpath = "//span[@data-localization-key='signIn.alternativeMethods.blockButton__emailCode']")
    WebElement emailCodeLink;
    public void clickOnEmailCodeLink(){
        waitForAWebElementToBeClickable(emailCodeLink, 15, 3);
        emailCodeLink.click();

    }
    @FindBy(xpath = "//input[@autocomplete='one-time-code']")
    List<WebElement> otpCodeInputList;
    public MainPage completeOTPCode(String[] otpCode){
        waitForAListOfWebElementsToFullyLoad(otpCodeInputList, 15, 3);
        for (int i = 0; i < otpCodeInputList.size(); i++) {
            otpCodeInputList.get(i).sendKeys(otpCode[i]);

        }
        mainPage = new MainPage(driver);
        return mainPage;

    }
    @FindBy(xpath = "//p[contains(text(),'You are not authorized to perform this request')]")
    WebElement bannedErrorMessage;
    public WebElement getBannedErrorMessage(){
        waitForAWebElementToFullyLoad(bannedErrorMessage, 15, 3);
        return bannedErrorMessage;
    }
}
