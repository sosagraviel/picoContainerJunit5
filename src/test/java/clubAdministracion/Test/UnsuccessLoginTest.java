package clubAdministracion.Test;

import clubAdministracion.Base.BaseTest;
import clubAdministracion.Utilities.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class UnsuccessLoginTest extends BaseTest {

    @Test
    public void incorrectPasswordUserLoginTest() {
        loginPage = startTest();

        loginPage.setAddressOrUserInput(utilities.getPropertyByValue(prop, "club_email"));
        loginPage.setPasswordInput("bad_password");

        loginPage.clickOnContinueBtn();

        WebElement errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage.getText(), Constants.USR_PASSWORD_INCORRECT_ERROR_MESSAGE);

    }
    @Test
    public void nonExistentUserLoginTest(){
        loginPage = startTest();

        loginPage.setAddressOrUserInput("nonexistent@email.com");
        loginPage.setPasswordInput(utilities.getPropertyByValue(prop, "club_password"));

        loginPage.clickOnContinueBtn();

        WebElement errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage.getText(), Constants.USR_PASSWORD_INCORRECT_ERROR_MESSAGE);

    }
    @Test
    public void passwordNotSetLoginTest(){
        loginPage = startTest();

        loginPage.setAddressOrUserInput(utilities.getPropertyByValue(prop, "club_email"));

        String continueBtnDisabledValue = getAttributeValue(loginPage.getContinueBtn(), "disabled");
        Assert.assertEquals(continueBtnDisabledValue, "true");

    }
    @Test
    public void userOrEmailNotSetLoginTest(){
        loginPage = startTest();

        loginPage.setPasswordInput(utilities.getPropertyByValue(prop, "club_password"));

        String continueBtnDisabledValue = getAttributeValue(loginPage.getContinueBtn(), "disabled");
        Assert.assertEquals(continueBtnDisabledValue, "true");

    }
}
