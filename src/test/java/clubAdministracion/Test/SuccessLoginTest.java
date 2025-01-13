package clubAdministracion.Test;

import clubAdministracion.Base.BaseTest;
import clubAdministracion.Utilities.Constants;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SuccessLoginTest extends BaseTest {

    @Test
    public void successfulUserLoginWithUsernameTest() {
        loginPage = startTest();

        loginPage.setAddressOrUserInput(utilities.getPropertyByValue(prop, "club_username"));
        loginPage.setPasswordInput(utilities.getPropertyByValue(prop, "club_password"));

        mainPage = loginPage.clickOnContinueBtn();

        WebElement qubikaImage = mainPage.getQubikaImage();
        Assert.assertTrue(qubikaImage.isDisplayed());

    }
    @Test
    public void successfulUserLoginwithEmailTest() {
        loginPage = startTest();

        loginPage.setAddressOrUserInput(utilities.getPropertyByValue(prop, "club_email"));
        loginPage.setPasswordInput(utilities.getPropertyByValue(prop, "club_password"));

        mainPage = loginPage.clickOnContinueBtn();

        WebElement qubikaImage = mainPage.getQubikaImage();
        Assert.assertTrue(qubikaImage.isDisplayed());

    }

}
